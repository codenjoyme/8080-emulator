package svofski;

//
// Pretty 8080 Assembler
//
// Send comments to svofski at gmail dit com
//
// Copyright (c) 2009 Viacheslav Slavinsky
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//
//
// Translation help:
// Leonid Kirillov, Alexander Timoshenko, Upi Tamminen,
// Cristopher Green, Nard Awater, Ali Asadzadeh,
// Guillermo S. Romero, Anna Merkulova, Stephan Henningsen
//
// Revison Log
// Rev.A:  Initial release
// Rev.B:  A lot of fixes to compile TINIDISK.ASM by Dr. Li-Chen Wang
// Rev.C:  Performance optimizations and cleanup, labels->hash
// Rev.D:  More syntax fixes; opera navigation and Back Button Toolbar
// Rev.E:  Navigation to label references (backref menu)
//         Nice labels table
//         Some Opera-related fixes
// Rev.F:  fixed '.' and semi-colon in db
//         tab scroll fixed
// Rev.G:  $ can now work as hex prefix
// Rev.H:  Fixed spaces in reg-reg, .binfile, .hexfile
// Rev.I:  Fixed bug in evaluation of hex literals ending with d
// Rev.J:  Backport from offline version: register highlighting
// Rev.K:  Target encodings support
// Rev.K2: Ported to Java by Oleksandr Baglai
// Rev.K3: Fixed resolveNumber for processing numbers with '_' in them.
//         Fixed corner cases with '0b' prefix in combination with 'h' suffix.
//
// -- all of the above is kept for historical reasons only --

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import spec.math.Bites;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    private final ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public boolean debug = false;
    public String tapeFormat = "rk-bin";
    public boolean doHexDump = false;
    public boolean doIntelHex = false;
    public String targetEncoding = "koi8-r";
    public String project = "test";

    public Map<String, Integer> labels;
    public Map<String, List<Integer>> xref;
    public Map<Integer, Integer> mem = new HashMap<>();
    public Integer org = null;
    public ArrayList<String> textlabels = new ArrayList<>();
    public ArrayList<String> references = new ArrayList<>();
    public Map<Integer, String> errors = new HashMap<>();
    public List<Map<String, Object>> gutterContent;
    public String listingText;

    private ArrayList<Expression> expressions = new ArrayList<>();
    private Map<String, Map<String, Object>> label_resolutions;

    public String objCopy;
    public String postbuild;
    public String hexText;

    public String getBinFileName() {
        if (!project.contains(".")) {
            return project + ".bin";
        } else {
            return project;
        }
    }

    public String getHexFileName() {
        if (!project.contains(".")) {
            return project + ".hex";
        } else {
            return Util.replaceExt(project, ".hex");
        }
    }

    public String getTapFileName() {
        if (!project.contains(".")) {
            return project + ".cas";
        } else {
            return Util.replaceExt(project, ".cas");
        }
    }

    public static Map<String, String> rpmap = new HashMap<>();
    static {
        rpmap.put("h", "l");
        rpmap.put("d", "e");
    }

    // Assembler instructions maps
    public static Map<String, Integer> ops0 = new HashMap<>();
    static {
        ops0.put("nop",   0x00);
        ops0.put("hlt",   0x76);
        ops0.put("ei",    0xfb);
        ops0.put("di",    0xf3);
        ops0.put("sphl",  0xf9);
        ops0.put("xchg",  0xeb);
        ops0.put("xthl",  0xe3);
        ops0.put("daa",   0x27);
        ops0.put("cma",   0x2f);
        ops0.put("stc",   0x37);
        ops0.put("cmc",   0x3f);
        ops0.put("rlc",   0x07);
        ops0.put("rrc",   0x0f);
        ops0.put("ral",   0x17);
        ops0.put("rar",   0x1f);
        ops0.put("pchl",  0xe9);
        ops0.put("ret",   0xc9);
        ops0.put("rnz",   0xc0);
        ops0.put("rz",    0xc8);
        ops0.put("rnc",   0xd0);
        ops0.put("rc",    0xd8);
        ops0.put("rpo",   0xe0);
        ops0.put("rpe",   0xe8);
        ops0.put("rp",    0xf0);
        ops0.put("rm",    0xf8);
    }

    public static Map<String, Integer> opsIm16 = new HashMap<>();
    static {
        opsIm16.put("lda",  0x3a);
        opsIm16.put("sta",  0x32);
        opsIm16.put("lhld", 0x2a);
        opsIm16.put("shld", 0x22);
        opsIm16.put("jmp",  0xc3);
        opsIm16.put("jnz",  0xc2);
        opsIm16.put("jz",   0xca);
        opsIm16.put("jnc",  0xd2);
        opsIm16.put("jc",   0xda);
        opsIm16.put("jpo",  0xe2);
        opsIm16.put("jpe",  0xea);
        opsIm16.put("jp",   0xf2);
        opsIm16.put("jm",   0xfa);
        opsIm16.put("call", 0xcd);
        opsIm16.put("cnz",  0xc4);
        opsIm16.put("cz",   0xcc);
        opsIm16.put("cnc",  0xd4);
        opsIm16.put("cc",   0xdc);
        opsIm16.put("cpo",  0xe4);
        opsIm16.put("cpe",  0xec);
        opsIm16.put("cp",   0xf4);
        opsIm16.put("cm",   0xfc);
    }

    public static Map<String, Integer> opsRpIm16 = new HashMap<>();
    static {
        opsRpIm16.put("lxi", 0x01); // 00rp0001, bc=00, de=01,hl=10, sp=11
    }

    public static Map<String, Integer> opsIm8 = new HashMap<>();
    static {
        opsIm8.put("adi",  0xc6);
        opsIm8.put("aci",  0xce);
        opsIm8.put("sui",  0xd6);
        opsIm8.put("sbi",  0xde);
        opsIm8.put("ani",  0xe6);
        opsIm8.put("xri",  0xee);
        opsIm8.put("ori",  0xf6);
        opsIm8.put("cpi",  0xfe);
        opsIm8.put("in",   0xdb);
        opsIm8.put("out",  0xd3);
    }

    public static Map<String, Integer> opsRegIm8 = new HashMap<>();
    static {
        opsRegIm8.put("mvi", 0x06);
    }

    public static Map<String, Integer> opsRegReg = new HashMap<>();
    static {
        opsRegReg.put("mov", 0x40);
    }

    public static Map<String, Integer> opsReg = new HashMap<>();
    static {
        opsReg.put("add", 0x80); // regsrc
        opsReg.put("adc", 0x88);
        opsReg.put("sub", 0x90);
        opsReg.put("sbb", 0x98);
        opsReg.put("ana", 0xa0);
        opsReg.put("xra", 0xa8);
        opsReg.put("ora", 0xb0);
        opsReg.put("cmp", 0xb8);
        opsReg.put("inr", 0x04); // regdst (<<3)
        opsReg.put("dcr", 0x05);
    }

    // Direct register operations, regdst
    public static ArrayList<String> opsRegDst = new ArrayList<>();
    static {
        opsRegDst.add("inr");
        opsRegDst.add("dcr");
    }

    // Register pair operations
    public static Map<String, Integer> opsRp = new HashMap<>();
    static {
        opsRp.put("ldax", 0x0a); // rp << 4 (only B, D)
        opsRp.put("stax", 0x02); // rp << 4 (only B, D)
        opsRp.put("dad",  0x09); // rp << 4
        opsRp.put("inx",  0x03); // rp << 4
        opsRp.put("dcx",  0x0b); // rp << 4
        opsRp.put("push", 0xc5); // rp << 4
        opsRp.put("pop",  0xc1); // rp << 4
    }

    /* Try to resolve a number literal, return value or null */
    public Integer resolveNumber(String identifier) {
        if (identifier == null || identifier.length() == 0) return null;

        // Remove underscores
        identifier = identifier.replace("_", "");

        char first = identifier.charAt(0);
        if ((first == '\'' || first == '"') && identifier.length() == 3) {
            return 0xff & identifier.charAt(1);
        }

        // Handling hexadecimal numbers with $ prefix
        if (first == '$') {
            if (identifier.matches("^\\$[0-9a-fA-F_]+$")) {
                try {
                    return Integer.parseInt(identifier.substring(1), 16);
                } catch (NumberFormatException e) {
                    // continue
                }
            }
        }

        // Handling hexadecimal numbers with 0x or 0X prefix
        if (identifier.matches("^0[xX][0-9a-fA-F_]+$")) {
            try {
                return Integer.parseInt(identifier.substring(2), 16);
            } catch (NumberFormatException e) {
                // continue
            }
        }

        // Handling binary numbers with 0b or 0B prefix
        if (identifier.matches("^0[bB][01_]+(?![hHbBqQdD])$")) {
            try {
                return Integer.parseInt(identifier.substring(2), 2);
            } catch (NumberFormatException e) {
                // continue
            }
        }

        // Handling numbers without specific base prefix
        if (identifier.matches("^[+-]?[0-9a-fA-F][0-9a-fA-F_]*[hHbBqQdD]?$")) {
            try {
                // Default conversion in case no suffix is present
                return Integer.valueOf(identifier);
            } catch (NumberFormatException e) {
                // continue
            }

            char suffix = identifier.charAt(identifier.length() - 1);
            switch (Character.toLowerCase(suffix)) {
                case 'd':
                    return safeParseInt(identifier, 10);
                case 'h':
                    return safeParseInt(identifier, 16);
                case 'b':
                    return safeParseInt(identifier, 2);
                case 'q':
                    return safeParseInt(identifier, 8);
            }
        }
        return null;
    }

    private Integer safeParseInt(String identifier, int radix) {
        try {
            return Integer.parseInt(identifier.substring(0, identifier.length() - 1), radix);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Methods related to referencing labels and setting memory
    public void referencesLabel(String identifier, int linenumber) {
        if (linenumber < references.size() && references.get(linenumber) == null) {
            references.set(linenumber, identifier.toLowerCase());
        } else if (linenumber >= references.size()) {
            // Handle case when linenumber exceeds current size
            while (references.size() < linenumber) {
                references.add(null);
            }
            references.add(identifier.toLowerCase());
        }
    }

    public void setmem16(int addr, int immediate) {
        if (immediate >= 0) {
            mem.put(addr, immediate & 0xff);
            mem.put(addr + 1, immediate >> 8);
        } else {
            mem.put(addr, immediate);
            mem.put(addr + 1, immediate);
        }
    }

    public void setmem8(int addr, int immediate) {
        mem.put(addr, immediate < 0 ? immediate : immediate & 0xff);
    }

    // Static utility methods to parse the register pair and single registers
    public static int parseRegisterPair(String s, boolean forstack) {
        if (s != null) {
            s = s.trim().split(";")[0].toLowerCase();
            if (s.equals("b") || s.equals("bc")) return 0;
            if (s.equals("d") || s.equals("de")) return 1;
            if (s.equals("h") || s.equals("hl")) return 2;
            if (forstack) { // push/pop
                if (s.equals("psw") || s.equals("a")) return 3;
            } else { // lxi, dad, etc.
                if (s.equals("sp")) return 3;
            }
        }
        return -1;
    }

    public static String registers = "bcdehlma";  // Registers encoding

    // Parse a single register identifier string to a register code
    public static int parseRegister(String s) {
        if (s == null || s.length() > 1) return -1;
        s = s.toLowerCase();
        return registers.indexOf(s.charAt(0));
    }

    public int tokenDBDW(String s, int addr, int length, int linenumber) {
        s = s.trim();
        if (s.length() == 0) return 0;
        useExpression(new String[]{s}, addr, length, linenumber);
        referencesLabel(s, linenumber);

        return length;
    }

    public int tokenString(String s, int addr, int linenumber) {
        for (int i = 0; i < s.length(); i++) {
            setmem8(addr + i, s.charAt(i));
        }
        return s.length();
    }

    public int parseDeclBase64(String[] args, int addr, int linenumber) {
        String text = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        byte[] raw = Base64.getDecoder().decode(text);
        int length = raw.length;
        for (int i = 0; i < length; i++) {
            setmem8(addr + i, raw[i]);
        }
        return length;
    }

    public int parseDeclDB(String[] args, int addr, int linenumber, int dw) {
        String text = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        int mode = 0;
        char cork = '\0';
        int nbytes = 0;
        int arg_start = 0;
        int i, end_i;
        for (i = 0, end_i = text.length(); i < end_i && mode != 10; i++) {
            char ch = text.charAt(i);
            switch (mode) {
                case 0:
                    if (ch == '"' || ch == '\'') {
                        mode = 1;
                        cork = ch;
                        arg_start = i + 1;
                    } else if (ch == ',') {
                        int len = tokenDBDW(text.substring(arg_start, i), addr + nbytes, dw, linenumber);
                        if (len < 0) {
                            return -1;
                        }
                        nbytes += len;
                        arg_start = i + 1;
                    } else if (ch == ';') {
                        // parse what's left, don't include the ';' symbol itself
                        i -= 1;
                        mode = 10;
                    }
                    break;
                case 1:
                    if (ch == cork) {
                        cork = '\0';
                        mode = 0;
                        int len = tokenString(text.substring(arg_start, i), addr + nbytes, linenumber);
                        if (len < 0) {
                            return -1;
                        }
                        nbytes += len;
                        arg_start = i + 1;
                    }
                    break;
            }
        }
        if (mode == 1) return -1;    // unterminated string
        int len = tokenDBDW(text.substring(arg_start, i), addr + nbytes, dw, linenumber);
        if (len < 0) return -1;
        nbytes += len;

        return nbytes;
    }

    public Expression useExpression(String[] s, int addr, int length, int linenumber) {
        Expression result = new Expression(addr, length, s, linenumber);
        expressions.add(result);
        return result;
    }

    public Object labelResolution(String label, String value, int addr, int lineno, boolean rewrite) {
        if (label_resolutions.containsKey(label) && !rewrite) {
            return null;
        }
        Integer numberwang = resolveNumber(value);
        Map<String, Object> lr = new HashMap<>();
        if (numberwang != null) {
            lr.put("number", numberwang);
            lr.put("linenumber", lineno);
        } else {
            lr.put("expression", value);
            lr.put("addr", addr);
            lr.put("linenumber", lineno);
        }
        label_resolutions.put(label, lr);
        return lr;
    }

    public int setNewEncoding(String encoding) {
        try {
            String encoded = Util.toTargetEncoding("test", encoding);
            this.targetEncoding = encoding;
        } catch (Exception err) {
            return -1;  // In Java typically you would not use -1 but handle the exception more gracefully
        }
        return -100000;
    }

    public List<List<String>> splitParts(String s) {
        List<List<String>> lines = new ArrayList<>();
        List<String> parts = new ArrayList<>();
        int state = 0;
        char cork = '\0';
        String remainder = s.trim();

        for (;state != 100;) {
            switch (state) {
                case 0:
                    boolean def = remainder.isEmpty();
                    if (!def) {
                        char firstChar = remainder.charAt(0);
                        if (firstChar == ';') {
                            parts.add(remainder);
                            state = 100;
                        } else if (firstChar == '"') {
                            cork = '"';
                            state = 10;
                        } else if (firstChar == '\'') {
                            cork = '\'';
                            state = 10;
                        } else if (firstChar == '\\') {
                            lines.add(new ArrayList<>(parts));
                            parts.clear();
                            remainder = remainder.substring(1).trim();
                        } else {
                            def = true;
                        }
                    }
                    if (def) {
                        int at = findDelimiter(remainder, new char[] {' ', '\\', '"', '\''});
                        if (at >= 0) {
                            parts.add(remainder.substring(0, at));
                            remainder = remainder.substring(at).trim();
                        } else {
                            parts.add(remainder);
                            state = 100;
                        }
                    }
                    break;
                case 10:
                    int n = remainder.substring(1).indexOf(cork);
                    if (n > 0) {
                        n += 2;
                        parts.add(remainder.substring(0, n));
                        remainder = StringUtils.stripStart(remainder.substring(n), " ");
                        state = 0;
                    } else {
                        parts.add(remainder);
                        remainder = "";
                        state = 100;
                    }
                    break;
                case 100:
                    break;
            }
            if (remainder.isEmpty()) {
                break;
            }
        }
        if (!parts.isEmpty()) {
            lines.add(parts);
        }
        return lines;
    }

    private int findDelimiter(String str, char[] delimiters) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (char delimiter : delimiters) {
                if (c == delimiter) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int parseInstruction(String[] parts, int addr, int linenumber) {
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].isEmpty() && parts[i].charAt(0) == ';') {
                parts = Arrays.copyOf(parts, i);
                break;
            }
        }

        String labelTag = null;
        String immediate = null;
        String[] regusage;
        int result = 0;
        Object label_obj;

        while (parts.length > 0) {
            Integer opcs;
            String mnemonic = parts[0].toLowerCase();

            if (mnemonic.length() == 0) {
                parts = Arrays.copyOfRange(parts, 1, parts.length);
                continue;
            }

            // no operands
            if ((opcs = Assembler.ops0.get(mnemonic)) != null) {
                this.mem.put(addr, opcs);
                if (mnemonic.equals("xchg")) {
                    regusage = new String[]{"#", "h", "l", "d", "e"};
                } else if (mnemonic.equals("sphl") || mnemonic.equals("xthl")) {
                    regusage = new String[]{"#", "sp", "h"};
                } else if (Arrays.asList("ral", "rar", "rla", "rra", "cma").contains(mnemonic)) {
                    regusage = new String[]{"#", "a"};
                }

                result = 1;
                break;
            }

            // immediate word
            if ((opcs = Assembler.opsIm16.get(mnemonic)) != null) {
                this.mem.put(addr, opcs);

                this.useExpression(Arrays.copyOfRange(parts, 1, parts.length), addr + 1, 2, linenumber);

                if (Arrays.asList("lhld", "shld").contains(mnemonic)) {
                    regusage = new String[]{"#", "h", "l"};
                } else if (Arrays.asList("lda", "sta").contains(mnemonic)) {
                    regusage = new String[]{"#", "a"};
                }

                result = 3;
                break;
            }

            // register pair <- immediate
            if ((opcs = Assembler.opsRpIm16.get(mnemonic)) != null) {
                String[] subparts = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)).split(",");
                if (subparts.length < 2) return -3;
                int rp = Assembler.parseRegisterPair(subparts[0], false);
                if (rp == -1) return -3;

                this.mem.put(addr, opcs | (rp << 4));

                this.useExpression(Arrays.copyOfRange(subparts, 1, subparts.length), addr + 1, 2, linenumber);
                result = 3;
                break;
            }

            // immediate byte
            if ((opcs = Assembler.opsIm8.get(mnemonic)) != null) {
                this.mem.put(addr, opcs);
                this.useExpression(Arrays.copyOfRange(parts, 1, parts.length), addr + 1, 1, linenumber);
                result = 2;
                break;
            }

            // single register, im8
            if ((opcs = Assembler.opsRegIm8.get(mnemonic)) != null) {
                String[] args = String.join("", Arrays.copyOfRange(parts, 1, parts.length)).split(",");
                if (args.length < 2) {
                    result = -2;
                    break;
                }
                String[] subparts = new String[]{args[0], args[1]};
                int reg = Assembler.parseRegister(subparts[0]);
                if (reg == -1) {
                    result = -2;
                    break;
                }

                this.mem.put(addr, opcs | (reg << 3));

                this.useExpression(Arrays.copyOfRange(subparts, 1, subparts.length), addr + 1, 1, linenumber);
                result = 2;
                break;
            }

            // dual register (mov)
            if ((opcs = Assembler.opsRegReg.get(mnemonic)) != null) {
                String[] subparts = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)).split(",");
                if (subparts.length < 2) {
                    result = -1;
                    break;
                }
                int reg1 = Assembler.parseRegister(subparts[0].trim());
                int reg2 = Assembler.parseRegister(subparts[1].trim());
                if (reg1 == -1 || reg2 == -1) {
                    result = -1;
                    break;
                }
                this.mem.put(addr, opcs | (reg1 << 3) | reg2);
                regusage = new String[]{subparts[0].trim(), subparts[1].trim()};
                result = 1;
                break;
            }

            // single register
            if ((opcs = Assembler.opsReg.get(mnemonic)) != null) {
                int reg = Assembler.parseRegister(parts[1]);
                if (reg == -1) {
                    result = -1;
                    break;
                }

                if (opsRegDst.contains(mnemonic)) {
                    reg <<= 3;
                }
                this.mem.put(addr, opcs | reg);

                regusage = new String[]{parts[1].trim()};
                if (Arrays.asList("ora", "ana", "xra", "add", "adc", "sub", "sbc", "cmp").contains(mnemonic)) {
                    regusage = new String[]{parts[1].trim(), "#", "a"};
                }

                result = 1;
                break;
            }

            // single register pair
            if ((opcs = Assembler.opsRp.get(mnemonic)) != null) {
                boolean stack = Arrays.asList("push", "pop").contains(mnemonic);
                int rp = Assembler.parseRegisterPair(parts[1], stack);
                if (rp == -1) {
                    result = -1;
                    break;
                }
                if (Arrays.asList("ldax", "stax").contains(mnemonic) && rp > 1) {
                    result = -1;
                    break;
                }
                this.mem.put(addr, opcs | (rp << 4));

                regusage = new String[]{"@" + parts[1].trim()};
                if (mnemonic.equals("dad")) {
                    regusage = new String[]{"#", "h", "l"};
                } else if (Arrays.asList("inx", "dcx").contains(mnemonic) && Arrays.asList("h", "d").contains(parts[1].trim())) {
                    regusage = new String[]{"#", Assembler.rpmap.get(parts[1].trim())};
                }
                result = 1;
                break;
            }

            // rst
            if (mnemonic.equals("rst")) {
                int n = this.resolveNumber(parts[1]);
                if (n >= 0 && n < 8) {
                    this.mem.put(addr, 0xC7 | (n << 3));
                    result = 1;
                } else {
                    result = -1;
                }
                break;
            }

            if (mnemonic.equals(".org") || mnemonic.equals("org")) {
                this.processLabelResolutions();
                int n = this.evaluateExpression2(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)), addr,
                        linenumber);
                if (n >= 0 && n < 65536) {
                    if (this.org == null || n < this.org) {
                        this.org = n;
                    }
                    result = -100000 - n;
                } else {
                    result = -1;
                }
                break;
            }

            if (mnemonic.equals(".project")) {
                if (parts[1] != null && parts[1].trim().length() > 0) {
                    this.project = parts[1];
                }
                result = -100000;
                break;
            }

            if (mnemonic.equals(".tape")) {
                if (parts[1] != null && parts[1].trim().length() > 0) {
                    this.tapeFormat = parts[1];
                    TapeFormat test = new TapeFormat(this.tapeFormat, null);
                    if (test.getFormat() != null) {
                        result = -100000;
                        break;
                    }
                }
            }

            if (mnemonic.equals(".nodump")) {
                this.doHexDump = false;
                result = -100000;
                break;
            }

            if (mnemonic.equals(".nohex")) {
                this.doIntelHex = false;
                result = -100000;
                break;
            }

            // assign immediate value to label
            if (mnemonic.equals(".equ") || mnemonic.equals("equ")) {
                if (labelTag == null) {
                    return -1;
                }
                Expression ex = new Expression(-1, 2, Arrays.copyOfRange(parts, 1, parts.length), linenumber);
                this.labelResolution(labelTag, ex.text, addr, linenumber, true);
                result = 0;
                break;
            }

            if (mnemonic.equals(".encoding")) {
                String encoding = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                return this.setNewEncoding(encoding);
            }

            if (mnemonic.equals("cpu") ||
                    mnemonic.equals("aseg") ||
                    mnemonic.equals(".aseg")) return 0;

            if (mnemonic.equals("db") || mnemonic.equals(".db") || mnemonic.equals("str")) {
                result = this.parseDeclDB(parts, addr, linenumber, 1);
                break;
            }
            if (mnemonic.equals("dw") || mnemonic.equals(".dw")) {
                result = this.parseDeclDB(parts, addr, linenumber, 2);
                break;
            }
            if (mnemonic.equals("ds") || mnemonic.equals(".ds")) {
                int size = this.evaluateExpression2(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)), addr,
                        linenumber);
                if (size >= 0) {
                    for (int i = 0; i < size; i++) {
                        this.setmem8(addr + i, 0);
                    }
                    result = size;
                } else {
                    result = -1;
                }
                break;
            }
            if (mnemonic.equals("db64")) {
                result = this.parseDeclBase64(parts, addr, linenumber);
                break;
            }

            if (parts[0].charAt(0) == ';') {
                return 0;
            }

            // nothing else works, it must be a label
            if (labelTag == null) {
                String[] splat = mnemonic.split(":", -1);
                labelTag = splat[0];
                label_obj = this.labelResolution(labelTag, String.valueOf(addr), addr,
                        linenumber, false);
                if (label_obj == null) {
                    result = -1;
                    break;
                }
                parts[0] = String.join(":", Arrays.copyOfRange(splat, 1, splat.length));
                continue;
            }

            this.mem.put(addr, -2);
            result = -1; // error
            break;
        }

        return result;
    }

    // -- output --

    public String labelList() {
        final String s = "                        ";
        Function<String, Function<Integer, String>> f = (String label) -> (Integer addr) -> {
            String result = label.substring(0, Math.min(s.length(), label.length()));
            if (result.length() < s.length()) {
                result += s.substring(result.length());
            }
            result += addr < 0 ? "????" : Util.hex16(addr);
            return result;
        };

        List<String> sorted = new ArrayList<>(new TreeMap<>(labels).keySet());

        String result = "Labels:\n";
        int col = 1;
        for (String i : sorted) {
            Integer label = labels.get(i);
            if (label == null) continue;
            if (String.valueOf(i).length() == 0) continue;

            String resultClass = (col % 4 == 0 ? "t2" : "t1"); // This variable in the JavaScript version does not affect the logic

            result += f.apply(i).apply(label);
            if ((col + 1) % 2 == 0) {
                result += "\n";
            } else {
                result += "\t";
            }
            col++;
        }
        return result;
    }

    public String dumpspan(int org, int mode) {
        String result = "";
        boolean nonempty = false;
        Function<Integer, String> conv = mode == 1 ? Util::char8 : Util::hex8;
        for (int i = org; i < org + 16; i++) {
            if (mem.get(i) != null) nonempty = true;
            if (mode == 1) {
                result += conv.apply(mem.get(i));
            } else {
                result += (i > org && i % 8 == 0) ? "-" : " ";
                if (mem.get(i) == null) {
                    result += "  ";
                } else {
                    result += conv.apply(mem.get(i));
                }
            }
        }

        return nonempty ? result : "";
    }

    public String dump() {
        int org = 0;

        if (org % 16 != 0) org -= org % 16;

        String result = "Memory dump:\n";
        boolean lastempty = false;

        int printline = 0;

        for (int i = org, end_i = this.mem.size(); i < end_i; i += 16) {
            String span = this.dumpspan(i, 0);
            //if (span != null || !lastempty) {
            //    result += "<pre class=\"d" + (printline++ % 2) + "\">";
            //}
            if (span != null && !span.isEmpty()) {
                result += Util.hex16(i) + ": ";
                result += span;
                result += "  ";
                result += this.dumpspan(i, 1);
                result += '\n';
                lastempty = false;
            }
            if ((span == null || span.isEmpty()) && !lastempty) {
                result += " \n"; //"</pre><br/>";
                lastempty = true;
            }
        }

        return result;
    }

    public String intelHex() {
        int i, j;
        String line = "";
        String r = "";
        String pureHex = "";

        r += "<pre>Intel HEX:</pre>";
        r += "<div class=\"hordiv\"></div>";

        r += "<pre>";
        r += "cat >" + this.getHexFileName() + " <<X<br/>";
        // r += "ed<br>i<br>";
        for (i = 0; i < this.mem.size();) {
            for (j = i; j < this.mem.size() && this.mem.get(j) == null; j++);
            i = j;
            if (i >= this.mem.size()) break;

            line = ":";

            int cs = 0;

            String rec = "";
            for (j = 0; j < 32 && this.mem.get(i + j) != null; j++) {
                int bb = this.mem.get(i + j);
                if (bb < 0) bb = 0;
                rec += Util.hex8(bb);
                cs += bb;
            }

            cs += j; line += Util.hex8(j);   // byte count
            cs += (i >> 8) & 255;
            cs += i & 255;
            line += Util.hex16(i);  // record address
            cs += 0;
            line += "00";      // record type 0, data
            line += rec;

            cs = 0xff & (- (cs & 255));
            line += Util.hex8(cs);
            pureHex += line + '\n';
            r += line + "<br/>";

            i += j;
        }
        r += ":00000001FF<br/>";
        pureHex += ":00000001FF\n";
        // r += ".<br>w " + this.hexFileName + "<br>q<br>";
        r += "X<br/>";
        r += this.objCopy + " -I ihex " + this.getHexFileName() + " -O binary " + this.getBinFileName() + "<br/>";
        if (!this.postbuild.isEmpty()) {
            r += this.postbuild + "<br/>";
        }
        r += "</pre>";

        this.hexText = pureHex;
        return r;
    }

    public Integer getLabel(String l) {
        return this.labels.get(l.toLowerCase());
    }

    public List<Map<String, Object>> gutter(List<String> text, Map<Integer, Integer> lengths, Map<Integer, Integer> addresses) {
        List<Map<String, Object>> result = new ArrayList<>();
        int addr = 0;

        for (int i = 0, end_i = text.size(); i < end_i; i += 1) {
            boolean unresolved = false;
            int width = 0;
            Map<Integer, Integer> hexes = new HashMap<>(lengths.get(i));
            for (int b = 0; b < lengths.get(i); ++b) {
                Integer bytte = this.mem.get(addresses.get(i) + b);
                if (bytte == null || bytte < 0) {
                    unresolved = true;
                    hexes.put(b, -1);
                } else {
                    hexes.put(b, bytte);
                }
            }

            boolean err = this.errors.get(i) != null || unresolved;

            Map<String, Object> gutobj = new HashMap<>();
            gutobj.put("addr", addresses.get(i));
            gutobj.put("hex", hexes);
            gutobj.put("error", err);

            result.add(gutobj);
        }

        return result;
    }

    public String listing(List<String> text, Map<Integer, Integer> lengths, Map<Integer, Integer> addresses) {
        List<String> result = new ArrayList<>();
        int addr = 0;
        for (int i = 0, end_i = text.size(); i < end_i; i += 1) {
            String labeltext = "";
            String remainder = text.get(i);
            String comment = "";
            String[] parts = text.get(i).split("[\\:\\s]", 1);
            if (parts.length > 1) {
                if (parts[0].trim().charAt(0) != ';' && this.getLabel(parts[0]) != -1) {
                    labeltext = parts[0];
                    remainder = text.get(i).substring(labeltext.length());
                }
            }

            int semicolon = remainder.indexOf(';');
            if (semicolon != -1) {
                comment = remainder.substring(semicolon);
                remainder = remainder.substring(0, semicolon);
            }

            // truncate awfully long lines in the listing before processRegUsage
            if (remainder.length() > 128) {
                remainder = remainder.substring(0, 128) + "…";
            }

            String hexes = "";
            boolean unresolved = false;
            int width = 0;

            int len = lengths.get(i) > 4 ? 4 : lengths.get(i);
            for (int b = 0; b < len; b++) {
                hexes += Util.hex8(this.mem.get(addresses.get(i) + b)) + " ";
                width += 3;
                if (this.mem.get(addresses.get(i) + b) < 0) unresolved = true;
            }
            hexes += "                ".substring(width);

            result.add((lengths.get(i) > 0 ? Util.hex16(addresses.get(i)) : "") + "\t" + hexes);

            if (labeltext.length() > 0) {
                result.add(labeltext);
            }
            String padding = "";
            int b = 0;
            for (; b < remainder.length() && Util.isWhitespace(remainder.charAt(b)); b++) {
                padding += remainder.charAt(b); // copy to preserve tabs
            }
            result.add(padding);
            remainder = remainder.substring(b);
            if (remainder.length() > 0) {
                result.add(remainder);
            }

            if (comment.length() > 0) {
                result.add(comment);
            }

            // display only first and last lines of db thingies
            if (len < lengths.get(i)) {
                result.add("\n\t. . .\n");
                String subresult = "";
                for (int subline = 1; subline * 4 < lengths.get(i); subline++) {
                    subresult = "";
                    subresult += Util.hex16(addresses.get(i) + subline * 4) + '\t';
                    for (int sofs = 0; sofs < 4; sofs += 1) {
                        int adr = subline * 4 + sofs;
                        if (adr < lengths.get(i)) {
                            subresult += Util.hex8(this.mem.get(addresses.get(i) + adr)) + ' ';
                        }
                    }
                }
                result.add(subresult + '\n');
            }
            result.add("\n");

            addr += lengths.get(i);
        }

        result.add(this.labelList());

        result.add("\n");

        if (this.doHexDump) {
            result.add(this.dump());
        }

        if (this.doIntelHex) {
            result.add(this.intelHex());
        }

        return String.join("", result);
    }

    public void error(int line, String text) {
        this.errors.put(line, text);
    }

    public static class Expression {
        int addr;
        int length;
        int linenumber;
        String text;

        public Expression(int addr, int length, String[] arr, int linenumber) {
            this.addr = addr;
            this.length = length;
            this.linenumber = linenumber;
            update(arr);
        }

        public void update(String[] arr) {
            String ex = String.join(" ", arr).trim();
            if (ex.charAt(0) == '"' || ex.charAt(0) == '\'') {
                this.text = ex;
            } else {
                this.text = ex.split(";")[0];
            }
        }
    }

    public void assemble(String src) {
        Map<Integer, Integer> lengths = new HashMap<>();
        Map<Integer, Integer> addresses = new HashMap<>();

        String[] inputlines = src.split("\n");

        int addr = 0;
        this.labels = new HashMap<>();
        this.mem.clear();
        this.org = null;
        this.errors.clear();
        this.postbuild = "";
        this.objCopy = "gobjcopy";
        this.hexText = "";
        this.xref = new HashMap<>();

        this.expressions = new ArrayList<>();          // expressions to evaluate after label resolution
        this.label_resolutions = new LinkedHashMap<>();       // labels, resolved and not

        for (int line = 0, end = inputlines.length; line < end; line += 1) {
            String encodedLine = Util.toTargetEncoding(inputlines[line].trim(), this.targetEncoding);
            List<List<String>> sublines = this.splitParts(encodedLine);

            for (int sul = 0; sul < sublines.size(); ++sul) {
                int size = this.parseInstruction(sublines.get(sul).toArray(new String[0]), addr, line);
                if (size <= -100000) {
                    addr = -size - 100000;
                    size = 0;
                } else if (size < 0) {
                    this.error(line, "syntax error");
                    size = 0;
                    break;
                }

                if (!lengths.containsKey(line)) {
                    lengths.put(line, size);
                } else {
                    lengths.put(line, lengths.get(line) + size);
                }
                if (sul == 0) addresses.put(line, addr);
                addr += size;
            }
        }

        this.resolveExpressions();

        /* If org was not defined explicitly, take first defined address */
        if (this.org == null) {
            Integer org;
            for (org = 0; org < this.mem.size() && this.mem.get(org) == null; org++);
            this.org = org;
        }

        this.gutterContent = this.gutter(Arrays.asList(inputlines), lengths, addresses);
        this.listingText = this.listing(Arrays.asList(inputlines), lengths, addresses);
    }

    public void addxref(String ident, int linenumber) {
        ident = ident.toLowerCase();
        if (!this.xref.containsKey(ident)) {
            this.xref.put(ident, new ArrayList<>());
        }
        this.xref.get(ident).add(linenumber);
    }

    public int evaluateExpression2(String input, int addr0, int linenumber) {
        String originput = input;
        input = this.evalPrepareExpr(input, addr0);
        if (input == null) {
            return -1;
        }
        try {
            String[] q = input.split("<<|>>|[+\\-*\\/()\\^\\&\\|]");
            String expr = "";
            for (int ident = 0; ident < q.length; ident++) {
                String qident = q[ident].trim();
                if (this.resolveNumber(qident) != null) continue;
                Integer addr = this.labels.get(qident.toLowerCase());
                if (addr != null) {
                    expr += "var _" + qident + " = " + addr + ";\n";
                    String regex = "\\b" + qident + "\\b";
                    input = input.replaceAll(regex, "_" + qident);
                    this.addxref(qident, linenumber);
                }
            }
            // System.out.println("0 input=" + input);
            // System.out.println("1 expr=" + expr);
            Pattern pattern = Pattern.compile(
                    "\\b\\$[0-9a-fA-F_]+\\b|\\b0[xX][0-9a-fA-F_]+\\b|\\b0[bB][01_]+(?![hHbBqQdD])\\b|\\b[0-9a-fA-F][0-9a-fA-F_]*[hHbBqQdD]?\\b|'.'");
            Matcher matcher = pattern.matcher(input);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String match = matcher.group();
                // Resolve each number and append replacement
                matcher.appendReplacement(sb, String.valueOf(resolveNumber(match)));
            }
            matcher.appendTail(sb);

            expr += sb.toString();

            // System.out.println("expr=" + expr);
            return this.evalInvoke(expr.toLowerCase());
        } catch (Exception err) {
            this.errors.put(linenumber, err.toString());
            return -1;
        }
    }

    public int processLabelResolutionsOnce() {
        Map<String, Map<String, Object>> lr2 = new HashMap<>();
        int unresolvedCount = 0;
        for (String label : this.label_resolutions.keySet()) {
            Map<String, Object> lr = this.label_resolutions.get(label);
            if (lr.get("expression") != null) {
                Integer ev = this.evaluateExpression2((String) lr.get("expression"), (int) lr.get("addr"),
                        (int) lr.get("linenumber"));
                if (ev == null) {
                    lr2.put(label, lr);
                    ++unresolvedCount;
                } else {
                    this.labels.put(label, ev);
                    this.addxref(label, (int) lr.get("linenumber"));
                }
            } else {
                this.labels.put(label, (int) lr.get("number"));
                this.addxref(label, (int) lr.get("linenumber"));
            }
        }
        // System.out.println("resolveExpressions: labels=" + this.labels + " lr2=" + lr2);
        this.label_resolutions = lr2;
        return unresolvedCount;
    }

    public void processLabelResolutions() {
        int maxIteration = this.label_resolutions.size();
        int unresolvedCount = maxIteration;
        for (int i = 0; i < maxIteration && unresolvedCount > 0; ++i) {
            unresolvedCount = this.processLabelResolutionsOnce();
        }
    }

    public void resolveExpressions() {
        this.processLabelResolutions();
        for (int i = 0; i < this.expressions.size(); ++i) {
            Expression eobj = this.expressions.get(i);
            Integer ev = this.evaluateExpression2(eobj.text, eobj.addr - 1, eobj.linenumber);
            if (ev != null) {
                if (eobj.length == 1) {
                    if (ev >= -128 && ev < 256) {
                        this.setmem8(eobj.addr, ev & 0xff);
                    }
                }
                else if (eobj.length == 2) {
                    if (ev >= -32768 && ev < 65536) {
                        this.setmem16(eobj.addr, ev & 0xffff);
                    }
                }
            }
        }
    }

    // scapegoat functionis for V8 because try/catch
    public String evalPrepareExpr(String input, int addr) {
        try {
            input = input.replaceAll("\\$([0-9a-fA-F]+)", "0x$1");
            input = input.replaceAll("(?:^|[^'])([\\$\\.])", " " + addr + " ");
            input = input.replaceAll("([\\d\\w]+)\\s(shr|shl|and|or|xor)\\s([\\d\\w]+)", "($1 $2 $3)");

            Pattern pattern = Pattern.compile("\\b(shl|shr|xor|or|and|[+\\-*\\/^])\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String command = matcher.group().toLowerCase();
                String value;
                switch (command) {
                    case "and": value = "&"; break;
                    case "or": value = "|"; break;
                    case "xor": value = "^"; break;
                    case "shl": value = "<<"; break;
                    case "shr": value = ">>"; break;
                    default: value = command;
                }
                matcher.appendReplacement(sb, value);
            }
            input = matcher.appendTail(sb).toString();
        } catch (Exception e) {
            return null;
        }
        return input;
    }

    public Integer evalInvoke(String expr) {
        try {
            Object eval = engine.eval(expr);
            if (eval instanceof Double) {
                return ((Double) eval).intValue();
            } else {
                return (Integer) eval;
            }
        } catch (ScriptException err) {
            err.printStackTrace();
            System.out.println("Expression was: " + expr);
            System.out.print(err.toString());
        }

        return null;
    }

    private Bites toBin(Object data) {
        List<Integer> mem = (List<Integer>) data;
        Bites bytes = new Bites(mem.size());
        for (int i = 0; i < mem.size(); i++) {
            bytes.set(i, mem.get(i));
        }
        return bytes;
    }

    public Map<String, Object> process(String sourceCode) {
        assemble(sourceCode);
        intelHex();

        Map<String, Object> result = new HashMap<>();

        List<Integer> memory = toList(mem);
        result.put("mem", memory);
        result.put("hex", hexText);
        Bites bin = toBin(memory);
        result.put("bin", bin);
        result.put("gutter", gutterContent);
        result.put("listing", listingText);
        result.put("errors", errors);
        result.put("xref", xref);
        result.put("labels", labels);

        Map<String, Object> info = new HashMap<>();
        info.put("org", org);
        info.put("kind", "assemble");
        info.put("binFileName", getBinFileName());
        info.put("hexFileName", getHexFileName());
        info.put("tapFileName", getTapFileName());
        info.put("tapeFormat", tapeFormat);

        result.put("info", info);
        return result;
    }

    private List<Integer> toList(Map<Integer, Integer> mem) {
        List<Integer> result = new ArrayList<>();
        int maxIndex = Collections.max(mem.keySet());
        for (int i = 0; i <= maxIndex; i++) {
            result.add(mem.getOrDefault(i, 1));
        }
        return result;
    }

    public Bites compile(String sourceCode) {
        return toBin(process(sourceCode).get("mem"));
    }

    public static void main(String[] args) throws IOException {
        Assembler assembler = new Assembler();

        String sourceCode =
                ";************************\n" +
                "; Prints 'HELLO WORLD\\n'\n" +
                ";************************\n" +
                "\n" +
                "        .project hello-world.mem\n" +
                "        .tape специалистъ-mon\n" +
                "        CPU     8080\n" +
                "        .ORG    00000h\n" +
                "\n" +
                "start:  LXI     H,hello\n" +
                "        CALL    bdos\n" +
                "        JMP     wboot      ; exit\n" +
                ";\n" +
                "hello:  DB      00Dh, 00Ah, 'HELLO WORLD', 00Dh, 00Ah, '$'\n" +
                ";\n" +
                "bdos    EQU     0C037h     ; PRINT CHAR PROCEDURE\n" +
                "wboot:  JMP     0C800h     ; BACK TO SYSTEM\n" +
                "end:";

        // компилируем
        Bites result = assembler.compile(sourceCode);

        // скомпилированный код
        byte[] bytes = result.byteArray();

        // сохраняем в файл
        FileUtils.writeByteArrayToFile(new File("target/program.mem"), bytes);

        // дамп памяти
        System.out.println(result);

        // напечатает
        //       00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F
        // 0000: 21 09 00 CD 37 C0 C3 19 00 0D 0A 48 45 4C 4C 4F
        // 0010: 20 57 4F 52 4C 44 0D 0A 24 C3 00 C8
    }
}