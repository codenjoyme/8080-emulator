package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.assembler.Assembler;
import spec.stuff.SmartAssert;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;
import static spec.stuff.SmartAssert.*;

public class AssemblerTest {

    private Assembler assembler;

    @Before
    public void setup() {
        assembler = new Assembler();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    @Test
    public void testDizAssembly() {
        asrtAll("01 12 23=>LXI B,2312\n" +
                "02=>STAX B\n" +
                "03=>INX B\n" +
                "04=>INR B\n" +
                "05=>DCR B\n" +
                "06 12=>MVI B,12\n" +
                "07=>RLC\n" +
                "08=>NONE\n" +
                "09=>DAD B\n" +
                "0A=>LDAX B\n" +
                "0B=>DCX B\n" +
                "0C=>INR C\n" +
                "0D=>DCR C\n" +
                "0E 12=>MVI C,12\n" +
                "0F=>RRC\n" +
                "10=>NONE\n" +
                "11 12 23=>LXI D,2312\n" +
                "12=>STAX D\n" +
                "13=>INX D\n" +
                "14=>INR D\n" +
                "15=>DCR D\n" +
                "16 12=>MVI D,12\n" +
                "17=>RAL\n" +
                "18=>NONE\n" +
                "19=>DAD D\n" +
                "1A=>LDAX D\n" +
                "1B=>DCX D\n" +
                "1C=>INR E\n" +
                "1D=>DCR E\n" +
                "1E 12=>MVI E,12\n" +
                "1F=>RAR\n" +
                "20=>NONE\n" +
                "21 12 23=>LXI H,2312\n" +
                "22 12 23=>SHLD 2312\n" +
                "23=>INX H\n" +
                "24=>INR H\n" +
                "25=>DCR H\n" +
                "26 12=>MVI H,12\n" +
                "27=>DAA\n" +
                "28=>NONE\n" +
                "29=>DAD H\n" +
                "2A 12 23=>LHLD 2312\n" +
                "2B=>DCX H\n" +
                "2C=>INR L\n" +
                "2D=>DCR L\n" +
                "2E 12=>MVI L,12\n" +
                "2F=>CMA\n" +
                "30=>NONE\n" +
                "31 12 23=>LXI SP,2312\n" +
                "32 12 23=>STA 2312\n" +
                "33=>INX SP\n" +
                "34=>INR M\n" +
                "35=>DCR M\n" +
                "36 12=>MVI M,12\n" +
                "37=>STC\n" +
                "38=>NONE\n" +
                "39=>DAD SP\n" +
                "3A 12 23=>LDA 2312\n" +
                "3B=>DCX SP\n" +
                "3C=>INR A\n" +
                "3D=>DCR A\n" +
                "3E 12=>MVI A,12\n" +
                "3F=>CMC\n" +
                "40=>MOV B,B\n" +
                "41=>MOV B,C\n" +
                "42=>MOV B,D\n" +
                "43=>MOV B,E\n" +
                "44=>MOV B,H\n" +
                "45=>MOV B,L\n" +
                "46=>MOV B,M\n" +
                "47=>MOV B,A\n" +
                "48=>MOV C,B\n" +
                "49=>MOV C,C\n" +
                "4A=>MOV C,D\n" +
                "4B=>MOV C,E\n" +
                "4C=>MOV C,H\n" +
                "4D=>MOV C,L\n" +
                "4E=>MOV C,M\n" +
                "4F=>MOV C,A\n" +
                "50=>MOV D,B\n" +
                "51=>MOV D,C\n" +
                "52=>MOV D,D\n" +
                "53=>MOV D,E\n" +
                "54=>MOV D,H\n" +
                "55=>MOV D,L\n" +
                "56=>MOV D,M\n" +
                "57=>MOV D,A\n" +
                "58=>MOV E,B\n" +
                "59=>MOV E,C\n" +
                "5A=>MOV E,D\n" +
                "5B=>MOV E,E\n" +
                "5C=>MOV E,H\n" +
                "5D=>MOV E,L\n" +
                "5E=>MOV E,M\n" +
                "5F=>MOV E,A\n" +
                "60=>MOV H,B\n" +
                "61=>MOV H,C\n" +
                "62=>MOV H,D\n" +
                "63=>MOV H,E\n" +
                "64=>MOV H,H\n" +
                "65=>MOV H,L\n" +
                "66=>MOV H,M\n" +
                "67=>MOV H,A\n" +
                "68=>MOV L,B\n" +
                "69=>MOV L,C\n" +
                "6A=>MOV L,D\n" +
                "6B=>MOV L,E\n" +
                "6C=>MOV L,H\n" +
                "6D=>MOV L,L\n" +
                "6E=>MOV L,M\n" +
                "6F=>MOV L,A\n" +
                "70=>MOV M,B\n" +
                "71=>MOV M,C\n" +
                "72=>MOV M,D\n" +
                "73=>MOV M,E\n" +
                "74=>MOV M,H\n" +
                "75=>MOV M,L\n" +
                "76=>HLT\n" +
                "77=>MOV M,A\n" +
                "78=>MOV A,B\n" +
                "79=>MOV A,C\n" +
                "7A=>MOV A,D\n" +
                "7B=>MOV A,E\n" +
                "7C=>MOV A,H\n" +
                "7D=>MOV A,L\n" +
                "7E=>MOV A,M\n" +
                "7F=>MOV A,A\n" +
                "80=>ADD B\n" +
                "81=>ADD C\n" +
                "82=>ADD D\n" +
                "83=>ADD E\n" +
                "84=>ADD H\n" +
                "85=>ADD L\n" +
                "86=>ADD M\n" +
                "87=>ADD A\n" +
                "88=>ADC B\n" +
                "89=>ADC C\n" +
                "8A=>ADC D\n" +
                "8B=>ADC E\n" +
                "8C=>ADC H\n" +
                "8D=>ADC L\n" +
                "8E=>ADC M\n" +
                "8F=>ADC A\n" +
                "90=>SUB B\n" +
                "91=>SUB C\n" +
                "92=>SUB D\n" +
                "93=>SUB E\n" +
                "94=>SUB H\n" +
                "95=>SUB L\n" +
                "96=>SUB M\n" +
                "97=>SUB A\n" +
                "98=>SBB B\n" +
                "99=>SBB C\n" +
                "9A=>SBB D\n" +
                "9B=>SBB E\n" +
                "9C=>SBB H\n" +
                "9D=>SBB L\n" +
                "9E=>SBB M\n" +
                "9F=>SBB A\n" +
                "A0=>ANA B\n" +
                "A1=>ANA C\n" +
                "A2=>ANA D\n" +
                "A3=>ANA E\n" +
                "A4=>ANA H\n" +
                "A5=>ANA L\n" +
                "A6=>ANA M\n" +
                "A7=>ANA A\n" +
                "A8=>XRA B\n" +
                "A9=>XRA C\n" +
                "AA=>XRA D\n" +
                "AB=>XRA E\n" +
                "AC=>XRA H\n" +
                "AD=>XRA L\n" +
                "AE=>XRA M\n" +
                "AF=>XRA A\n" +
                "B0=>ORA B\n" +
                "B1=>ORA C\n" +
                "B2=>ORA D\n" +
                "B3=>ORA E\n" +
                "B4=>ORA H\n" +
                "B5=>ORA L\n" +
                "B6=>ORA M\n" +
                "B7=>ORA A\n" +
                "B8=>CMP B\n" +
                "B9=>CMP C\n" +
                "BA=>CMP D\n" +
                "BB=>CMP E\n" +
                "BC=>CMP H\n" +
                "BD=>CMP L\n" +
                "BE=>CMP M\n" +
                "BF=>CMP A\n" +
                "C0=>RNZ\n" +
                "C1=>POP B\n" +
                "C2 12 34=>JNZ 3412\n" +
                "C3 12 34=>JMP 3412\n" +
                "C4 12 34=>CNZ 3412\n" +
                "C5=>PUSH B\n" +
                "C6 12=>ADI 12\n" +
                "C7=>RST 0\n" +
                "C8=>RZ\n" +
                "C9=>RET\n" +
                "CA 12 34=>JZ 3412\n" +
                "CB=>NONE\n" +
                "CC 12 34=>CZ 3412\n" +
                "CD 12 34=>CALL 3412\n" +
                "CE 12=>ACI 12\n" +
                "CF=>RST 1\n" +
                "D0=>RNC\n" +
                "D1=>POP D\n" +
                "D2 12 34=>JNC 3412\n" +
                "D3 12=>OUT 12\n" +
                "D4 12 34=>CNC 3412\n" +
                "D5=>PUSH D\n" +
                "D6 12=>SUI 12\n" +
                "D7=>RST 2\n" +
                "D8=>RC\n" +
                "D9=>NONE\n" +
                "DA 12 34=>JC 3412\n" +
                "DB 12=>IN 12\n" +
                "DC 12 34=>CC 3412\n" +
                "DD=>NONE\n" +
                "DE 12=>SBI 12\n" +
                "DF=>RST 3\n" +
                "E0=>RPO\n" +
                "E1=>POP H\n" +
                "E2 12 34=>JPO 3412\n" +
                "E3=>XTHL\n" +
                "E4 12 34=>CPO 3412\n" +
                "E5=>PUSH H\n" +
                "E6 12=>ANI 12\n" +
                "E7=>RST 4\n" +
                "E8=>RPE\n" +
                "E9=>PCHL\n" +
                "EA 12 34=>JPE 3412\n" +
                "EB=>XCHG\n" +
                "EC 12 34=>CPE 3412\n" +
                "ED=>NONE\n" +
                "EE 12=>XRI 12\n" +
                "EF=>RST 5\n" +
                "F0=>RP\n" +
                "F1=>POP PSW\n" +
                "F2 12 34=>JP 3412\n" +
                "F3=>DI\n" +
                "F4 12 34=>CP 3412\n" +
                "F5=>PUSH PSW\n" +
                "F6 12=>ORI 12\n" +
                "F7=>RST 6\n" +
                "F8=>RM\n" +
                "F9=>SPHL\n" +
                "FA 12 34=>JM 3412\n" +
                "FB=>EI\n" +
                "FC 12 34=>CM 3412\n" +
                "FD=>NONE\n" +
                "FE 12=>CPI 12\n" +
                "FF=>RST 7");
    }

    private void asrtAll(String data) {
        assertEquals(data,
                Arrays.stream(data.split("\n"))
                        .peek(line -> System.out.printf("Processing: '%s'%n", line))
                        .map(line -> line.split("=>"))
                        .map(array -> array[0] + "=>" + call(array[0]))
                        .collect(joining("\n")));
    }

    private String call(String bites) {
        try {
            return assembler.dizAssembly(bites)
                    .replace("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}