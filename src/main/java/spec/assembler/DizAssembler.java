package spec.assembler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import spec.Data;
import spec.Range;
import spec.mods.Info;

import java.util.*;

import static spec.Constants.x10000;
import static spec.math.WordMath.*;
import static spec.mods.WhereIsData.Type.*;
import static spec.mods.WhereIsData.markCommand;

public class DizAssembler {

    private Assembler asm;
    private Data data;
    private Range range;
    private Info[] infoData;
    private Map<Integer, String> labels;
    private Map<String, Integer> labels2;
    private int pad;

    public DizAssembler(Data data) {
        this.data = data;
        this.asm = new Assembler();
    }

    public void clarifyInfo() {
        List<Integer> processed = new LinkedList<>();
        Queue<Integer> toProcess = new LinkedList<>();
        toProcess.add(range.begin());
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            Info info = infoData[addr];
            if (info.type == COMMAND || info.type == PROBABLE_COMMAND) {
                toProcess.add(addr);
            }
        }
        while (!toProcess.isEmpty()) {
            int addr = toProcess.remove();
            if (processed.contains(addr)) {
                continue;
            }
            processed.add(addr);
            do {
                Command command = asm.find(data.read8(addr));
                Info info = markCommand(infoData, addr, data, command, true);
                if (command.isJump() || command.isCall()) {
                    int addr2 = info.data;
                    if (range.includes(addr2)) {
                        toProcess.add(addr2);
                    } else {
                        // куда ссылаются игрушки за пределами своей памяти (полезно для исследований)
                        // System.out.println(hex16(addr2));
                    }
                }
                if (command.name().startsWith("RET")
                        || command.name().startsWith("JMP")
                        || command.name().startsWith("PCHL")) {
                    break;
                }

                addr += command.size();
                addr = wordShift(addr);
            } while (true);
        }
    }

    public String program(Range range, Info[] inputInfo) {
        this.range = range;
        this.infoData = clone(inputInfo);
        boolean canonicalData = true;

        clarifyInfo();
        arrangeLabels(range);
        dizAssembly(range, canonicalData);
        return buildProgram(range, canonicalData);
    }

    private void arrangeLabels(Range range) {
        labels = new TreeMap<>();
        labels2 = new LinkedHashMap<>();
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            Info info = infoData[addr];

            if (info.type == COMMAND) {
                if (info.command.isCall()
                    || info.command.isJump()
                    || info.command.name().startsWith("SHLD")
                    || info.command.name().startsWith("LHLD")
                    || info.command.name().startsWith("LDA")
                    || info.command.name().startsWith("STA")
                    || (info.command.name().startsWith("LXI") && infoData[info.data].type == DATA))
                {
                    int newAddr = info.data;
                    String label = labels.get(newAddr);
                    if (label == null) {
                        label = toLabel(newAddr);
                        labels.put(newAddr, label);
                        if (labels2.containsKey(label)) {
                            throw new RuntimeException(String.format(
                                    "Found collision: label '%s' already exists for address '%s' but new one is '%s'",
                                    label, hex16(labels2.get(label)), hex16(newAddr)));
                        }
                        labels2.put(label, newAddr);
                        infoData[newAddr].label = label;
                    } else {
                        if (!label.equals(toLabel(newAddr))) {
                            throw new RuntimeException(String.format(
                                    "Found collision: we already have label '%s' for address '%s' but valid one is '%s'",
                                    label, hex16(newAddr), toLabel(newAddr)));
                        }
                    }
                    info.labelTo = label;
                }
            }
        }
    }

    public static String toLabel(int value) {
        try {
            // Инициализируем MessageDigest для SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Создаем соль на основе значения, изменяем соль каждый раз разным образом
            String salt = "Salt" + (value % 100); // Пример простого изменения соли
            String toHash = value + salt; // Комбинируем значение и соль перед хэшированием

            byte[] hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));

            final int base = 26;
            final int numChars = 7; // Увеличиваем длину метки
            StringBuilder label = new StringBuilder("l");

            for (int i = 0; i < numChars; i++) {
                int byteIndex = (i * 2) % hash.length;
                int index = ((hash[byteIndex] & 0xFF) + (hash[(byteIndex + 1) % hash.length] & 0xFF)) % base;
                label.append((char) ('a' + index));
            }

            return label.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void dizAssembly(Range range, boolean canonical) {
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            Info info = infoData[addr];

            if (info.type == DATA) {
                // если у нас данные
                String hex = hex8(data.read8(addr));
                hex = canonical ? canonical(hex) : hex;
                info.asm("DB " + hex);
            } else if (info.type == COMMAND) {
                // если у нас команды
                info.asm(canonical);
            }
        }
    }

    private String buildProgram(Range range, boolean canonicalData) {
        StringBuilder result = new StringBuilder();

        pad = ("lab: " + toLabel(x10000 - 1)).length() - 1;

        // настройка ассемблера
        String start = hex16(range.begin());
        start = canonicalData ? canonical(start) : start;
        result.append(pad("")).append("CPU  8080\n")
              .append(pad("")).append(".ORG ").append(start).append('\n');

        // константы адресов памяти за пределами программы
        for (Map.Entry<Integer, String> entry : labels.entrySet()) {
            int addr = entry.getKey();
            if (range.includes(addr)) {
                continue; // не записываем его как EQU поскольку будет внутри программы ссылка на него
            }

            String label = entry.getValue();
            String hex = hex16(addr);
            hex = canonicalData ? canonical(hex) : hex;
            result.append(pad(label))
                    .append("EQU ")
                    .append(hex)
                    .append('\n');
        }

        // текст программы
        int count = 0;
        boolean first = true;
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            Info info = infoData[addr];

            // если у нас данные, выстраиваем их по 10 в ряд
            if (info.type == DATA) {
                if (count == 10 || info.label != null) {
                    count = 0;
                    first = true;
                    result.append('\n');
                }
                if (first) {
                    first = false;
                    if (info.label != null) {
                        result.append(pad(info.label + ":"));
                    } else {
                        result.append(pad(""));
                    }
                    result.append(info.asm);
                } else {
                    result.append(", ")
                          .append(info.asm.replace("DB ", ""));
                }
                count++;
                continue;
            }
            if (count != 0) {
                result.append('\n');
                count = 0;
                first = true;
            }

            // если у нас команды
            if (info.type == COMMAND) {
                if (info.label != null) {
                    result.append(pad(info.label + ":"));
                } else {
                    result.append(pad(""));
                }
                if (info.labelTo != null) {
                    result.append(info.asm.replace(info.data(true), info.labelTo));
                } else {
                    result.append(info.asm);
                }
                result.append('\n');
            }
        }
        result.append("\nEND");
        return result.toString();
    }

    private String pad(String string) {
        return padRight(string, pad, ' ');
    }

    private Info[] clone(Info[] info) {
        Info[] result = new Info[info.length];
        for (int addr = 0; addr < info.length; addr++) {
            result[addr] = new Info(info[addr]);
        }
        return result;
    }
}