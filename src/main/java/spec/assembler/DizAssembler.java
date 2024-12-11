package spec.assembler;

import spec.Data;
import spec.Range;
import spec.mods.Info;

import java.util.*;

import static spec.math.WordMath.*;
import static spec.mods.WhereIsData.Type.*;
import static spec.mods.WhereIsData.markCommand;

public class DizAssembler {

    private Assembler asm;
    private Data data;
    private Range range;
    private Info[] infoData;
    private Map<Integer, String> labels;
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
                    toProcess.add(info.data);
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
        labels = new HashMap<>();
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
                        label = toLabel(labels.size());
                        labels.put(newAddr, label);
                        infoData[newAddr].label = label;
                    }
                    info.labelTo = label;
                }
            }
        }
    }

    private String toLabel(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be between 1 and " + Integer.MAX_VALUE);
        }

        final int base = 26;
        final int maxLength = 3;
        char[] chars = new char[base];
        for (int i = 0; i < base; i++) {
            chars[i] = (char) ('a' + i);
        }

        StringBuilder label = new StringBuilder("l");
        int shift = value % base;
        for (int i = 0; i < maxLength; i++) {
            int index = (shift + value) % base;
            label.append(chars[index]);
            shift = (shift + 1) % base;
            value /= base;
        }

        return label.toString();
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

        pad = "lab: ".length() + String.valueOf(labels.size()).length();

        // настройка ассемблера
        String start = hex16(range.begin());
        start = canonicalData ? canonical(start) : start;
        result.append(pad("")).append("CPU  8080\n")
              .append(pad("")).append(".ORG ").append(start).append('\n');

        // константы адресов памяти за пределами программы
        for (Map.Entry<Integer, String> entry : labels.entrySet()) {
            int addr = entry.getKey();
            String label = entry.getValue();
            if (!range.includes(addr)) {
                String hex = hex16(addr);
                hex = canonicalData ? canonical(hex) : hex;
                result.append(pad(label))
                        .append("EQU ")
                        .append(hex)
                        .append('\n');
            }
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