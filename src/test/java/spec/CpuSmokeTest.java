package spec;

import org.junit.Test;
import spec.stuff.AbstractTest;

/**
 * Служит для бістрой проверки всех команд
 */
public class CpuSmokeTest extends AbstractTest {

    @Test
    public void smoke_assemble() {
        // given when
        givenPr("LDA 1234\n" +
                "NOP\n" +
                "LDAX B\n" +
                "NOP\n" +
                "LDAX D\n" +
                "NOP\n" +
                "LHLD 1234\n" +
                "NOP\n" +
                "LXI B,1234\n" +
                "NOP\n" +
                "LXI D,1234\n" +
                "NOP\n" +
                "LXI H,1234\n" +
                "NOP\n" +
                "LXI SP,1234\n" +
                "NOP\n" +

                "MOV B,B\n" +
                "NOP\n" +
                "MOV B,C\n" +
                "NOP\n" +
                "MOV B,D\n" +
                "NOP\n" +
                "MOV B,E\n" +
                "NOP\n" +
                "MOV B,H\n" +
                "NOP\n" +
                "MOV B,L\n" +
                "NOP\n" +
                "MOV B,M\n" +
                "NOP\n" +
                "MOV B,A\n" +
                "NOP\n" +

                "MOV C,B\n" +
                "NOP\n" +
                "MOV C,C\n" +
                "NOP\n" +
                "MOV C,D\n" +
                "NOP\n" +
                "MOV C,E\n" +
                "NOP\n" +
                "MOV C,H\n" +
                "NOP\n" +
                "MOV C,L\n" +
                "NOP\n" +
                "MOV C,M\n" +
                "NOP\n" +
                "MOV C,A\n" +
                "NOP\n" +

                "MOV D,B\n" +
                "NOP\n" +
                "MOV D,C\n" +
                "NOP\n" +
                "MOV D,D\n" +
                "NOP\n" +
                "MOV D,E\n" +
                "NOP\n" +
                "MOV D,H\n" +
                "NOP\n" +
                "MOV D,L\n" +
                "NOP\n" +
                "MOV D,M\n" +
                "NOP\n" +
                "MOV D,A\n" +
                "NOP\n" +

                "MOV E,B\n" +
                "NOP\n" +
                "MOV E,C\n" +
                "NOP\n" +
                "MOV E,D\n" +
                "NOP\n" +
                "MOV E,E\n" +
                "NOP\n" +
                "MOV E,H\n" +
                "NOP\n" +
                "MOV E,L\n" +
                "NOP\n" +
                "MOV E,M\n" +
                "NOP\n" +
                "MOV E,A\n" +
                "NOP\n" +

                "MOV H,B\n" +
                "NOP\n" +
                "MOV H,C\n" +
                "NOP\n" +
                "MOV H,D\n" +
                "NOP\n" +
                "MOV H,E\n" +
                "NOP\n" +
                "MOV H,H\n" +
                "NOP\n" +
                "MOV H,L\n" +
                "NOP\n" +
                "MOV H,M\n" +
                "NOP\n" +
                "MOV H,A\n" +
                "NOP\n" +

                "MOV L,B\n" +
                "NOP\n" +
                "MOV L,C\n" +
                "NOP\n" +
                "MOV L,D\n" +
                "NOP\n" +
                "MOV L,E\n" +
                "NOP\n" +
                "MOV L,H\n" +
                "NOP\n" +
                "MOV L,L\n" +
                "NOP\n" +
                "MOV L,M\n" +
                "NOP\n" +
                "MOV L,A\n" +
                "NOP\n" +

                "MOV M,B\n" +
                "NOP\n" +
                "MOV M,C\n" +
                "NOP\n" +
                "MOV M,D\n" +
                "NOP\n" +
                "MOV M,E\n" +
                "NOP\n" +
                "MOV M,H\n" +
                "NOP\n" +
                "MOV M,L\n" +
                "NOP\n" +
                //"MOV M,M\n" +  not exists, but HLT
                //"NOP\n" +
                "MOV M,A\n" +
                "NOP\n" +

                "MVI B,12\n" +
                "NOP\n" +
                "MVI C,12\n" +
                "NOP\n" +
                "MVI D,12\n" +
                "NOP\n" +
                "MVI E,12\n" +
                "NOP\n" +
                "MVI H,12\n" +
                "NOP\n" +
                "MVI L,12\n" +
                "NOP\n" +
                "MVI M,12\n" +
                "NOP\n" +
                "MVI A,12\n" +
                "NOP\n" +

                "PCHL\n" +
                "NOP\n" +
                "SHLD 1234\n" +
                "NOP\n" +
                "STA 1234\n" +
                "NOP\n" +
                "STAX B\n" +
                "NOP\n" +
                "STAX D\n" +
                "NOP\n" +
                "XCHG\n" +
                "NOP\n" +
                "XTHL\n" +

                "NOP\n" +
                "JMP 1234\n" +

                "NOP\n" +
                "JC 1234\n" +
                "NOP\n" +
                "JM 1234\n" +
                "NOP\n" +
                "JNC 1234\n" +
                "NOP\n" +
                "JNZ 1234\n" +
                "NOP\n" +
                "JP 1234\n" +
                "NOP\n" +
                "JPE 1234\n" +
                "NOP\n" +
                "JPO 1234\n" +
                "NOP\n" +
                "JZ 1234\n" +
                "NOP\n" +

                "RAL\n" +
                "NOP\n" +
                "RAR\n" +
                "NOP\n" +
                "RLC\n" +
                "NOP\n" +
                "RRC\n" +
                "NOP\n" +

                "CMA\n" +
                "NOP\n" +
                "CMC\n" +
                "NOP\n" +

                "CMP B\n" +
                "NOP\n" +
                "CMP C\n" +
                "NOP\n" +
                "CMP D\n" +
                "NOP\n" +
                "CMP E\n" +
                "NOP\n" +
                "CMP H\n" +
                "NOP\n" +
                "CMP L\n" +
                "NOP\n" +
                "CMP M\n" +
                "NOP\n" +
                "CMP A\n" +
                "NOP\n" +

                "CPI 12\n" +
                "NOP\n" +

                "DAA\n" +
                "NOP\n" +
                "STC\n" +
                "NOP\n" +

                "DCR B\n" +
                "NOP\n" +
                "DCR C\n" +
                "NOP\n" +
                "DCR D\n" +
                "NOP\n" +
                "DCR E\n" +
                "NOP\n" +
                "DCR H\n" +
                "NOP\n" +
                "DCR L\n" +
                "NOP\n" +
                "DCR M\n" +
                "NOP\n" +
                "DCR A\n" +
                "NOP\n" +

                "DCX B\n" +
                "NOP\n" +
                "DCX D\n" +
                "NOP\n" +
                "DCX H\n" +
                "NOP\n" +
                "DCX SP\n" +
                "NOP\n" +

                "INR B\n" +
                "NOP\n" +
                "INR C\n" +
                "NOP\n" +
                "INR D\n" +
                "NOP\n" +
                "INR E\n" +
                "NOP\n" +
                "INR H\n" +
                "NOP\n" +
                "INR L\n" +
                "NOP\n" +
                "INR M\n" +
                "NOP\n" +
                "INR A\n" +
                "NOP\n" +

                "INX B\n" +
                "NOP\n" +
                "INX D\n" +
                "NOP\n" +
                "INX H\n" +
                "NOP\n" +
                "INX SP\n" +
                "NOP\n" +

                "ANA B\n" +
                "NOP\n" +
                "ANA C\n" +
                "NOP\n" +
                "ANA D\n" +
                "NOP\n" +
                "ANA E\n" +
                "NOP\n" +
                "ANA H\n" +
                "NOP\n" +
                "ANA L\n" +
                "NOP\n" +
                "ANA M\n" +
                "NOP\n" +
                "ANA A\n" +
                "NOP\n" +

                "ANI 12\n" +
                "NOP\n" +

                "ORA B\n" +
                "NOP\n" +
                "ORA C\n" +
                "NOP\n" +
                "ORA D\n" +
                "NOP\n" +
                "ORA E\n" +
                "NOP\n" +
                "ORA H\n" +
                "NOP\n" +
                "ORA L\n" +
                "NOP\n" +
                "ORA M\n" +
                "NOP\n" +
                "ORA A\n" +
                "NOP\n" +

                "ORI 12\n" +
                "NOP\n" +

                "XRA B\n" +
                "NOP\n" +
                "XRA C\n" +
                "NOP\n" +
                "XRA D\n" +
                "NOP\n" +
                "XRA E\n" +
                "NOP\n" +
                "XRA H\n" +
                "NOP\n" +
                "XRA L\n" +
                "NOP\n" +
                "XRA M\n" +
                "NOP\n" +
                "XRA A\n" +
                "NOP\n" +

                "XRI 12\n" +
                "NOP\n" +

                "ADC B\n" +
                "NOP\n" +
                "ADC C\n" +
                "NOP\n" +
                "ADC D\n" +
                "NOP\n" +
                "ADC E\n" +
                "NOP\n" +
                "ADC H\n" +
                "NOP\n" +
                "ADC L\n" +
                "NOP\n" +
                "ADC M\n" +
                "NOP\n" +
                "ADC A\n" +
                "NOP\n" +

                "ACI 12\n" +
                "NOP\n" +

                "ADD B\n" +
                "NOP\n" +
                "ADD C\n" +
                "NOP\n" +
                "ADD D\n" +
                "NOP\n" +
                "ADD E\n" +
                "NOP\n" +
                "ADD H\n" +
                "NOP\n" +
                "ADD L\n" +
                "NOP\n" +
                "ADD M\n" +
                "NOP\n" +
                "ADD A\n" +
                "NOP\n" +

                "ADI 12\n" +
                "NOP\n" +

                "DAD B\n" +
                "NOP\n" +
                "DAD D\n" +
                "NOP\n" +
                "DAD H\n" +
                "NOP\n" +
                "DAD SP\n" +
                "NOP\n" +

                "SUB B\n" +
                "NOP\n" +
                "SUB C\n" +
                "NOP\n" +
                "SUB D\n" +
                "NOP\n" +
                "SUB E\n" +
                "NOP\n" +
                "SUB H\n" +
                "NOP\n" +
                "SUB L\n" +
                "NOP\n" +
                "SUB M\n" +
                "NOP\n" +
                "SUB A\n" +
                "NOP\n" +

                "SBI 12\n" +
                "NOP\n" +

                "SUB B\n" +
                "NOP\n" +
                "SUB C\n" +
                "NOP\n" +
                "SUB D\n" +
                "NOP\n" +
                "SUB E\n" +
                "NOP\n" +
                "SUB H\n" +
                "NOP\n" +
                "SUB L\n" +
                "NOP\n" +
                "SUB M\n" +
                "NOP\n" +
                "SUB A\n" +
                "NOP\n" +

                "SUI 12\n" +
                "NOP\n" +

                "IN 12\n" +
                "NOP\n" +
                "OUT 12\n" +
                "NOP\n" +

                "NOP\n" +
                "CALL 1234\n" +

                "NOP\n" +
                "CC 1234\n" +
                "NOP\n" +
                "CM 1234\n" +
                "NOP\n" +
                "CNC 1234\n" +
                "NOP\n" +
                "CNZ 1234\n" +
                "NOP\n" +
                "CP 1234\n" +
                "NOP\n" +
                "CPE 1234\n" +
                "NOP\n" +
                "CPO 1234\n" +
                "NOP\n" +
                "CZ 1234\n" +
                "NOP\n" +

                "RET\n" +
                "NOP\n" +

                "NOP\n" +
                "RC\n" +
                "NOP\n" +
                "RM\n" +
                "NOP\n" +
                "RNC\n" +
                "NOP\n" +
                "RNZ\n" +
                "NOP\n" +
                "RP\n" +
                "NOP\n" +
                "RPE\n" +
                "NOP\n" +
                "RPO\n" +
                "NOP\n" +
                "RZ\n" +
                "NOP\n" +

                "POP B\n" +
                "NOP\n" +
                "POP D\n" +
                "NOP\n" +
                "POP H\n" +
                "NOP\n" +
                "POP PSW\n" +
                "NOP\n" +

                "PUSH B\n" +
                "NOP\n" +
                "PUSH D\n" +
                "NOP\n" +
                "PUSH H\n" +
                "NOP\n" +
                "PUSH PSW\n" +
                "NOP\n" +

                "DI\n" +
                "NOP\n" +
                "EI\n" +
                "NOP\n" +
                // "HLT\n" + TODO добавить
                "NOP\n");

        // then
        givenMm("3A 34 12\n" +
                "00\n" +
                "0A\n" +
                "00\n" +
                "1A\n" +
                "00\n" +
                "2A 34 12\n" +
                "00\n" +
                "01 34 12\n" +
                "00\n" +
                "11 34 12\n" +
                "00\n" +
                "21 34 12\n" +
                "00\n" +
                "31 34 12\n" +
                "00\n" +
                "40\n" +
                "00\n" +
                "41\n" +
                "00\n" +
                "42\n" +
                "00\n" +
                "43\n" +
                "00\n" +
                "44\n" +
                "00\n" +
                "45\n" +
                "00\n" +
                "46\n" +
                "00\n" +
                "47\n" +
                "00\n" +
                "48\n" +
                "00\n" +
                "49\n" +
                "00\n" +
                "4A\n" +
                "00\n" +
                "4B\n" +
                "00\n" +
                "4C\n" +
                "00\n" +
                "4D\n" +
                "00\n" +
                "4E\n" +
                "00\n" +
                "4F\n" +
                "00\n" +
                "50\n" +
                "00\n" +
                "51\n" +
                "00\n" +
                "52\n" +
                "00\n" +
                "53\n" +
                "00\n" +
                "54\n" +
                "00\n" +
                "55\n" +
                "00\n" +
                "56\n" +
                "00\n" +
                "57\n" +
                "00\n" +
                "58\n" +
                "00\n" +
                "59\n" +
                "00\n" +
                "5A\n" +
                "00\n" +
                "5B\n" +
                "00\n" +
                "5C\n" +
                "00\n" +
                "5D\n" +
                "00\n" +
                "5E\n" +
                "00\n" +
                "5F\n" +
                "00\n" +
                "60\n" +
                "00\n" +
                "61\n" +
                "00\n" +
                "62\n" +
                "00\n" +
                "63\n" +
                "00\n" +
                "64\n" +
                "00\n" +
                "65\n" +
                "00\n" +
                "66\n" +
                "00\n" +
                "67\n" +
                "00\n" +
                "68\n" +
                "00\n" +
                "69\n" +
                "00\n" +
                "6A\n" +
                "00\n" +
                "6B\n" +
                "00\n" +
                "6C\n" +
                "00\n" +
                "6D\n" +
                "00\n" +
                "6E\n" +
                "00\n" +
                "6F\n" +
                "00\n" +
                "70\n" +
                "00\n" +
                "71\n" +
                "00\n" +
                "72\n" +
                "00\n" +
                "73\n" +
                "00\n" +
                "74\n" +
                "00\n" +
                "75\n" +
                "00\n" +
                "77\n" +
                "00\n" +
                "06 12\n" +
                "00\n" +
                "0E 12\n" +
                "00\n" +
                "16 12\n" +
                "00\n" +
                "1E 12\n" +
                "00\n" +
                "26 12\n" +
                "00\n" +
                "2E 12\n" +
                "00\n" +
                "36 12\n" +
                "00\n" +
                "3E 12\n" +
                "00\n" +
                "E9\n" +
                "00\n" +
                "22 34 12\n" +
                "00\n" +
                "32 34 12\n" +
                "00\n" +
                "02\n" +
                "00\n" +
                "12\n" +
                "00\n" +
                "EB\n" +
                "00\n" +
                "E3\n" +
                "00\n" +
                "C3 34 12\n" +
                "00\n" +
                "DA 34 12\n" +
                "00\n" +
                "FA 34 12\n" +
                "00\n" +
                "D2 34 12\n" +
                "00\n" +
                "C2 34 12\n" +
                "00\n" +
                "F2 34 12\n" +
                "00\n" +
                "EA 34 12\n" +
                "00\n" +
                "E2 34 12\n" +
                "00\n" +
                "CA 34 12\n" +
                "00\n" +
                "17\n" +
                "00\n" +
                "1F\n" +
                "00\n" +
                "07\n" +
                "00\n" +
                "0F\n" +
                "00\n" +
                "2F\n" +
                "00\n" +
                "3F\n" +
                "00\n" +
                "B8\n" +
                "00\n" +
                "B9\n" +
                "00\n" +
                "BA\n" +
                "00\n" +
                "BB\n" +
                "00\n" +
                "BC\n" +
                "00\n" +
                "BD\n" +
                "00\n" +
                "BE\n" +
                "00\n" +
                "BF\n" +
                "00\n" +
                "FE 12\n" +
                "00\n" +
                "27\n" +
                "00\n" +
                "37\n" +
                "00\n" +
                "05\n" +
                "00\n" +
                "0D\n" +
                "00\n" +
                "15\n" +
                "00\n" +
                "1D\n" +
                "00\n" +
                "25\n" +
                "00\n" +
                "2D\n" +
                "00\n" +
                "35\n" +
                "00\n" +
                "3D\n" +
                "00\n" +
                "0B\n" +
                "00\n" +
                "1B\n" +
                "00\n" +
                "2B\n" +
                "00\n" +
                "3B\n" +
                "00\n" +
                "04\n" +
                "00\n" +
                "0C\n" +
                "00\n" +
                "14\n" +
                "00\n" +
                "1C\n" +
                "00\n" +
                "24\n" +
                "00\n" +
                "2C\n" +
                "00\n" +
                "34\n" +
                "00\n" +
                "3C\n" +
                "00\n" +
                "03\n" +
                "00\n" +
                "13\n" +
                "00\n" +
                "23\n" +
                "00\n" +
                "33\n" +
                "00\n" +
                "A0\n" +
                "00\n" +
                "A1\n" +
                "00\n" +
                "A2\n" +
                "00\n" +
                "A3\n" +
                "00\n" +
                "A4\n" +
                "00\n" +
                "A5\n" +
                "00\n" +
                "A6\n" +
                "00\n" +
                "A7\n" +
                "00\n" +
                "E6 12\n" +
                "00\n" +
                "B0\n" +
                "00\n" +
                "B1\n" +
                "00\n" +
                "B2\n" +
                "00\n" +
                "B3\n" +
                "00\n" +
                "B4\n" +
                "00\n" +
                "B5\n" +
                "00\n" +
                "B6\n" +
                "00\n" +
                "B7\n" +
                "00\n" +
                "F6 12\n" +
                "00\n" +
                "A8\n" +
                "00\n" +
                "A9\n" +
                "00\n" +
                "AA\n" +
                "00\n" +
                "AB\n" +
                "00\n" +
                "AC\n" +
                "00\n" +
                "AD\n" +
                "00\n" +
                "AE\n" +
                "00\n" +
                "AF\n" +
                "00\n" +
                "EE 12\n" +
                "00\n" +
                "88\n" +
                "00\n" +
                "89\n" +
                "00\n" +
                "8A\n" +
                "00\n" +
                "8B\n" +
                "00\n" +
                "8C\n" +
                "00\n" +
                "8D\n" +
                "00\n" +
                "8E\n" +
                "00\n" +
                "8F\n" +
                "00\n" +
                "CE 12\n" +
                "00\n" +
                "80\n" +
                "00\n" +
                "81\n" +
                "00\n" +
                "82\n" +
                "00\n" +
                "83\n" +
                "00\n" +
                "84\n" +
                "00\n" +
                "85\n" +
                "00\n" +
                "86\n" +
                "00\n" +
                "87\n" +
                "00\n" +
                "C6 12\n" +
                "00\n" +
                "09\n" +
                "00\n" +
                "19\n" +
                "00\n" +
                "29\n" +
                "00\n" +
                "39\n" +
                "00\n" +
                "90\n" +
                "00\n" +
                "91\n" +
                "00\n" +
                "92\n" +
                "00\n" +
                "93\n" +
                "00\n" +
                "94\n" +
                "00\n" +
                "95\n" +
                "00\n" +
                "96\n" +
                "00\n" +
                "97\n" +
                "00\n" +
                "DE 12\n" +
                "00\n" +
                "90\n" +
                "00\n" +
                "91\n" +
                "00\n" +
                "92\n" +
                "00\n" +
                "93\n" +
                "00\n" +
                "94\n" +
                "00\n" +
                "95\n" +
                "00\n" +
                "96\n" +
                "00\n" +
                "97\n" +
                "00\n" +
                "D6 12\n" +
                "00\n" +
                "DB 12\n" +
                "00\n" +
                "D3 12\n" +
                "00\n" +
                "00\n" +
                "CD 34 12\n" +
                "00\n" +
                "DC 34 12\n" +
                "00\n" +
                "FC 34 12\n" +
                "00\n" +
                "D4 34 12\n" +
                "00\n" +
                "C4 34 12\n" +
                "00\n" +
                "F4 34 12\n" +
                "00\n" +
                "EC 34 12\n" +
                "00\n" +
                "E4 34 12\n" +
                "00\n" +
                "CC 34 12\n" +
                "00\n" +
                "C9\n" +
                "00\n" +
                "00\n" +
                "D8\n" +
                "00\n" +
                "F8\n" +
                "00\n" +
                "D0\n" +
                "00\n" +
                "C0\n" +
                "00\n" +
                "F0\n" +
                "00\n" +
                "E8\n" +
                "00\n" +
                "E0\n" +
                "00\n" +
                "C8\n" +
                "00\n" +
                "C1\n" +
                "00\n" +
                "D1\n" +
                "00\n" +
                "E1\n" +
                "00\n" +
                "F1\n" +
                "00\n" +
                "C5\n" +
                "00\n" +
                "D5\n" +
                "00\n" +
                "E5\n" +
                "00\n" +
                "F5\n" +
                "00\n" +
                "F3\n" +
                "00\n" +
                "FB\n" +
                "00\n" +
                "00");
    }

}