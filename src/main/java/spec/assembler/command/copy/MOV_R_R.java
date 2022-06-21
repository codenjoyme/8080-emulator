package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

// TODO continue with tests
public class MOV_R_R extends Command {

    private int[] indexes2;

    private static final List<Integer> CODES = from(
//          R=B   R=C   R=D   R=E   R=H   R=L   R=M   R=A
            0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47,  // MVI B,R
            0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F,  // MVI C,R
            0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57,  // MVI D,R
            0x58, 0x59, 0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F,  // MVI E,R
            0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67,  // MVI H,R
            0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,  // MVI L,R
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, /* */ 0x77,  // MVI M,R, 0x76 is HLT
            0x78, 0x79, 0x7A, 0x7B, 0x7C, 0x7D, 0x7E, 0x7F); // MVI M,R

    @Override
    public void initIndexes(List<Integer> codes, List<String> registers) {
        indexes2 = new int[0x100];
        for (int i = 0; i < codes.size(); i++) {
            int code = codes.get(i);
            indexes[code] = (code & 0b0011_1000) >> 3;
            indexes2[code] = (code & 0b0000_0111);
        }
    }

    @Override
    public List<Integer> code(String... params) {
        int reg1Index = registers().indexOf(params[0]);
        int reg2Index = registers().indexOf(params[1]);
        int code = 0x40 | reg2Index | reg1Index << 3;
        if (code == 0x76) {
            throw new IllegalArgumentException(
                    "There is no MVI M,M in i8080, only HLT with this code");
        }
        return Arrays.asList(code);
    }

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }

    @Override
    public String operands() {
        return "R,R";
    }

    @Override
    public String print(List<Integer> bites, boolean canonical) {
        return super.print(bites, canonical)
                .replaceFirst(Pattern.quote("(B|C|D|E|H|L|M|A)"),
                        registers().get(rindex2(bites.get(0))));
    }

    @Override
    public int ticks(int command) {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.reg8(rindex2(command)).get();
        r.reg8(rindex(command)).set(bite);
    }

    private int rindex2(int command) {
        return indexes2[command];
    }
}