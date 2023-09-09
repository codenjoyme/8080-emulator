package spec;

import org.junit.Test;
import spec.mods.StopWhen;

/**
 * Служит для замеров производительности эмуляции CPU.
 *
 * Внимание! Время выполнения тестов зависит от
 * производительности хост-машины и будет несколько отличаться
 * от запуска к запуску на одной и той же машине.
 */
public class CpuPerformanceTest extends CpuSmokeTest {

    @Test
    public void performance_assemble() {
        // given

        // about 2.1 sec / 1000
        int ticks = 1000;

        // when then
        for (int tick = 0; tick < ticks; tick++) {
            memoryInit = false;
            smoke_assemble();
        }
    }

    @Test
    public void performance_execute() {
        // given

        // about 2.8 sec / 100_000
        int ticks = 100_000;

        givenPr("LDA 1234\n" +
                "LDAX B\n" +
                "LDAX D\n" +
                "LHLD 1234\n" +
                "LXI B,1234\n" +
                "LXI D,1234\n" +
                "LXI H,1234\n" +
                "LXI SP,1234\n" +

                "MOV B,B\n" +
                "MOV B,C\n" +
                "MOV B,D\n" +
                "MOV B,E\n" +
                "MOV B,H\n" +
                "MOV B,L\n" +
                "MOV B,M\n" +
                "MOV B,A\n" +

                "MOV C,B\n" +
                "MOV C,C\n" +
                "MOV C,D\n" +
                "MOV C,E\n" +
                "MOV C,H\n" +
                "MOV C,L\n" +
                "MOV C,M\n" +
                "MOV C,A\n" +

                "MOV D,B\n" +
                "MOV D,C\n" +
                "MOV D,D\n" +
                "MOV D,E\n" +
                "MOV D,H\n" +
                "MOV D,L\n" +
                "MOV D,M\n" +
                "MOV D,A\n" +

                "MOV E,B\n" +
                "MOV E,C\n" +
                "MOV E,D\n" +
                "MOV E,E\n" +
                "MOV E,H\n" +
                "MOV E,L\n" +
                "MOV E,M\n" +
                "MOV E,A\n" +

                "MOV H,B\n" +
                "MOV H,C\n" +
                "MOV H,D\n" +
                "MOV H,E\n" +
                "MOV H,H\n" +
                "MOV H,L\n" +
                "MOV H,M\n" +
                "MOV H,A\n" +

                "MOV L,B\n" +
                "MOV L,C\n" +
                "MOV L,D\n" +
                "MOV L,E\n" +
                "MOV L,H\n" +
                "MOV L,L\n" +
                "MOV L,M\n" +
                "MOV L,A\n" +

                "MOV M,B\n" +
                "MOV M,C\n" +
                "MOV M,D\n" +
                "MOV M,E\n" +
                "MOV M,H\n" +
                "MOV M,L\n" +
                "MOV M,A\n" +

                "MVI B,12\n" +
                "MVI C,12\n" +
                "MVI D,12\n" +
                "MVI E,12\n" +
                "MVI H,12\n" +
                "MVI L,12\n" +
                "MVI M,12\n" +
                "MVI A,12\n" +

                // TODO эта и все, что дальше закомментировано - команды перехода
                //  продумать для них условия чтобы они тоже отрабатывали
                // "PCHL\n" +
                "SHLD 1234\n" +
                "STA 1234\n" +
                "STAX B\n" +
                "STAX D\n" +
                "XCHG\n" +
                "XTHL\n" +

                // "JMP 1234\n" +
                // "JC 1234\n" +
                // "JM 1234\n" +
                // "JNC 1234\n" +
                // "JNZ 1234\n" +
                // "JP 1234\n" +
                // "JPE 1234\n" +
                // "JPO 1234\n" +
                // "JZ 1234\n" +

                "RAL\n" +
                "RAR\n" +
                "RLC\n" +
                "RRC\n" +

                "CMA\n" +
                "CMC\n" +

                "CMP B\n" +
                "CMP C\n" +
                "CMP D\n" +
                "CMP E\n" +
                "CMP H\n" +
                "CMP L\n" +
                "CMP M\n" +
                "CMP A\n" +

                "CPI 12\n" +

                "DAA\n" +
                "STC\n" +

                "DCR B\n" +
                "DCR C\n" +
                "DCR D\n" +
                "DCR E\n" +
                "DCR H\n" +
                "DCR L\n" +
                "DCR M\n" +
                "DCR A\n" +

                "DCX B\n" +
                "DCX D\n" +
                "DCX H\n" +
                "DCX SP\n" +

                "INR B\n" +
                "INR C\n" +
                "INR D\n" +
                "INR E\n" +
                "INR H\n" +
                "INR L\n" +
                "INR M\n" +
                "INR A\n" +

                "INX B\n" +
                "INX D\n" +
                "INX H\n" +
                "INX SP\n" +

                "ANA B\n" +
                "ANA C\n" +
                "ANA D\n" +
                "ANA E\n" +
                "ANA H\n" +
                "ANA L\n" +
                "ANA M\n" +
                "ANA A\n" +

                "ANI 12\n" +

                "ORA B\n" +
                "ORA C\n" +
                "ORA D\n" +
                "ORA E\n" +
                "ORA H\n" +
                "ORA L\n" +
                "ORA M\n" +
                "ORA A\n" +

                "ORI 12\n" +

                "XRA B\n" +
                "XRA C\n" +
                "XRA D\n" +
                "XRA E\n" +
                "XRA H\n" +
                "XRA L\n" +
                "XRA M\n" +
                "XRA A\n" +

                "XRI 12\n" +

                "ADC B\n" +
                "ADC C\n" +
                "ADC D\n" +
                "ADC E\n" +
                "ADC H\n" +
                "ADC L\n" +
                "ADC M\n" +
                "ADC A\n" +

                "ACI 12\n" +

                "ADD B\n" +
                "ADD C\n" +
                "ADD D\n" +
                "ADD E\n" +
                "ADD H\n" +
                "ADD L\n" +
                "ADD M\n" +
                "ADD A\n" +

                "ADI 12\n" +

                "DAD B\n" +
                "DAD D\n" +
                "DAD H\n" +
                "DAD SP\n" +

                "SUB B\n" +
                "SUB C\n" +
                "SUB D\n" +
                "SUB E\n" +
                "SUB H\n" +
                "SUB L\n" +
                "SUB M\n" +
                "SUB A\n" +

                "SBI 12\n" +

                "SUB B\n" +
                "SUB C\n" +
                "SUB D\n" +
                "SUB E\n" +
                "SUB H\n" +
                "SUB L\n" +
                "SUB M\n" +
                "SUB A\n" +

                "SUI 12\n" +

                "IN 12\n" +
                "OUT 12\n" +

                // "CALL 1234\n" +

                // "CC 1234\n" +
                // "CM 1234\n" +
                // "CNC 1234\n" +
                // "CNZ 1234\n" +
                // "CP 1234\n" +
                // "CPE 1234\n" +
                // "CPO 1234\n" +
                // "CZ 1234\n" +

                // "RET\n" +

                // "RC\n" +
                // "RM\n" +
                // "RNC\n" +
                // "RNZ\n" +
                // "RP\n" +
                // "RPE\n" +
                // "RPO\n" +
                // "RZ\n" +

                "POP B\n" +
                "POP D\n" +
                "POP H\n" +
                "POP PSW\n" +

                "PUSH B\n" +
                "PUSH D\n" +
                "PUSH H\n" +
                "PUSH PSW\n" +

                "DI\n" +
                "EI\n");

        cpu.modAdd(new StopWhen(0x0100));
        record.reset();

        // when then
        for (int tick = 0; tick < ticks; tick++) {
            hard.reset();
            cpu.PC(0x0000);
            cpu.execute();
        }
    }

}