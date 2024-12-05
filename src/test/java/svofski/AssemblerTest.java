package svofski;

import com.google.gson.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.stuff.FileAssert;

import java.lang.reflect.Type;
import java.util.*;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static spec.IntegrationTest.TEST_RESOURCES;
import static spec.stuff.FileAssert.write;

public class AssemblerTest {

    private Assembler asm;

    @Rule
    public TestName test = new TestName();
    private FileAssert fileAssert;

    @Before
    public void setup() {
        asm = new Assembler();

        fileAssert = new FileAssert(TEST_RESOURCES + AssemblerTest.class.getSimpleName());
    }

    @Test
    public void assemble() {
        Map<String, Object> data = asm.process(PROGRAM);

        assertValue("memory.json", asString(data.get("mem")));
        assertValue("hex.json", asString(data.get("hex")));
        assertValue("gutter.json", asString(data.get("gutter")));
        assertValue("listing.json", asString(data.get("listing")));
        assertValue("errors.json", asString(data.get("errors")));
        assertValue("xref.json", asString(data.get("xref")));
        assertValue("labels.json", asString(data.get("labels")));
        assertValue("info.json", asString(data.get("info")));
    }

    private String asString(Object data) {
        boolean isJson = data instanceof Map || data instanceof List;
        if (isJson) {
            return new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(HashMap.class, new MapSerializer())
                    .create().toJson(data);
        } else {
            return data.toString();
        }
    }

    static class MapSerializer implements JsonSerializer<Map<?, ?>> {
        @Override
        public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            src = new TreeMap<>(src);
            src.forEach((key, value) -> json.add(String.valueOf(key), context.serialize(value)));
            return json;
        }
    }

    @Test
    public void splitParts() {
        // given
        String[] lines = PROGRAM.split("\n");

        // when then
        assertValue("splitParts.log", splitParts(lines));
    }

    private String splitParts(String[] lines) {
        return Arrays.stream(lines)
                .map(asm::splitParts)
                .map(this::asString)
                .collect(joining("\n"));
    }

    @Test
    public void splitParts_cornerCases() {
        assertEquals("[{<DB>, <00Dh,>, <00Ah,>, <'VERSION 1.0  (C) 1980'>, <,>, <00Dh,>, <00Ah,>, <'$'>}]",
                asString(asm.splitParts("        DB      00Dh, 00Ah, 'VERSION 1.0  (C) 1980', 00Dh, 00Ah, '$'\n")));

        assertEquals("[{<hello:>, <DB>, <00Dh,>, <00Ah,>, <'MICROCOSM ASSOCIATES'>}]",
                asString(asm.splitParts("hello:  DB      00Dh, 00Ah, 'MICROCOSM ASSOCIATES'\n")));
    }

    private String asString(List<List<String>> lists) {
        return lists.stream()
                .map(list -> list.stream().map(
                        it -> "<" + it + ">"
                ).collect(joining(", ", "{", "}")))
                .collect(joining("; ", "[", "]"));
    }

    private void assertValue(String name, String result) {
        fileAssert.check(name, name,
                file -> write(file, result));
    }

    public static final String PROGRAM =
            ";***********************************************************************\n" +
                    "; MICROCOSM ASSOCIATES  8080/8085 CPU DIAGNOSTIC VERSION 1.0  (C) 1980\n" +
                    ";***********************************************************************\n" +
                    "; Load into virtual altair with: ALTAIR L=TEST.HEX\n" +
                    "; Then press F2 to view screen, and 'G' to execute the test.\n" +
                    ";\n" +
                    ";DONATED TO THE 'SIG/M' CP/M USER'S GROUP BY:\n" +
                    ";KELLY SMITH, MICROCOSM ASSOCIATES\n" +
                    ";3055 WACO AVENUE\n" +
                    ";SIMI VALLEY, CALIFORNIA, 93065\n" +
                    ";(805) 527-9321 (MODEM, CP/M-NET (TM))\n" +
                    ";(805) 527-0518 (VERBAL)\n" +
                    ";\n" +
                    ";******************************************************************************\n" +
                    ";\n" +
                    "; Modified by Oleksandr Baglai to be able to work on LIK and assemble with\n" +
                    ";     https://svofski.github.io/pretty-8080-assembler/\n" +
                    ";     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)\n" +
                    ";\n" +
                    ";******************************************************************************\n" +
                    "\n" +
                    "        .PROJECT test.rks\n" +
                    "        .tape специалистъ-rks\n" +
                    "        CPU     8080\n" +
                    "        .ORG    00000h\n" +
                    "\n" +
                    "start:  LXI     H,hello\n" +
                    "        CALL    msg\n" +
                    "        JMP     test       ; JUMP TO 8080 CPU DIAGNOSTIC\n" +
                    ";\n" +
                    "hello:  DB      00Dh, 00Ah, 'MICROCOSM ASSOCIATES'\n" +
                    "        DB      00Dh, 00Ah, '8080/8085 CPU DIAGNOSTIC'\n" +
                    "        DB      00Dh, 00Ah, 'VERSION 1.0  (C) 1980', 00Dh, 00Ah, '$'\n" +
                    ";\n" +
                    "bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE\n" +
                    "wboot:  JMP     0C800h     ; LIK MONITOR-1M\n" +
                    ";\n" +
                    ";MESSAGE OUTPUT ROUTINE\n" +
                    ";\n" +
                    "msg:    PUSH    B          ; Push state\n" +
                    "        PUSH    D\n" +
                    "        PUSH    H\n" +
                    "        PUSH    PSW\n" +
                    "msgs:   MOV     A,M        ; Get data\n" +
                    "        CPI     '$'        ; End?\n" +
                    "        JZ      msge       ; Exit\n" +
                    "        MOV     A,M\n" +
                    "        CALL    pchar      ; Output\n" +
                    "        INX     H          ; Next\n" +
                    "        JMP     msgs       ; Do all\n" +
                    "msge:   POP     PSW        ; Pop state\n" +
                    "        POP     H\n" +
                    "        POP     D\n" +
                    "        POP     B\n" +
                    "        RET\n" +
                    ";\n" +
                    ";CHARACTER OUTPUT ROUTINE\n" +
                    ";\n" +
                    "pchar:  PUSH    B\n" +
                    "        PUSH    D\n" +
                    "        PUSH    H\n" +
                    "        PUSH    PSW\n" +
                    "        MOV     C,A\n" +
                    "        CALL    bdos\n" +
                    "        POP     PSW\n" +
                    "        POP     H\n" +
                    "        POP     D\n" +
                    "        POP     B\n" +
                    "        RET\n" +
                    ";\n" +
                    ";HEX BYTE OUTPUT ROUTINE\n" +
                    ";\n" +
                    "byteo:  PUSH    B\n" +
                    "        PUSH    D\n" +
                    "        PUSH    H\n" +
                    "        PUSH    PSW\n" +
                    "        PUSH    PSW\n" +
                    "        CALL    byto1\n" +
                    "        CALL    pchar\n" +
                    "        POP     PSW\n" +
                    "        CALL    byto2\n" +
                    "        CALL    pchar\n" +
                    "        POP     PSW\n" +
                    "        POP     H\n" +
                    "        POP     D\n" +
                    "        POP     B\n" +
                    "        RET\n" +
                    "byto1:  RRC\n" +
                    "        RRC\n" +
                    "        RRC\n" +
                    "        RRC\n" +
                    "byto2:  ANI     00Fh\n" +
                    "        CPI     00Ah\n" +
                    "        JM      byto3\n" +
                    "        ADI     7\n" +
                    "byto3:  ADI     030h\n" +
                    "        RET\n" +
                    ";\n" +
                    ";************************************************************\n" +
                    ";           MESSAGE TABLE FOR OPERATIONAL CPU TEST\n" +
                    ";************************************************************\n" +
                    ";\n" +
                    "okcpu:  DB      00Dh, 00Ah, 'CPU IS OPERATIONAL$'\n" +
                    ";\n" +
                    "ngcpu:  DB      00Dh, 00Ah, 'CPU HAS FAILED!', 00Dh, 00Ah, 'ERROR EXIT=$'\n" +
                    ";\n" +
                    ";\n" +
                    ";************************************************************\n" +
                    ";                8080/8085 CPU TEST/DIAGNOSTIC\n" +
                    ";************************************************************\n" +
                    ";\n" +
                    ";NOTE: (1) PROGRAM ASSUMES 'CALL',AND 'LXI SP' INSTRUCTIONS WORK!\n" +
                    ";\n" +
                    ";      (2) INSTRUCTIONS NOT TESTED ARE 'HLT','DI','EI',\n" +
                    ";          AND 'RST 0' THRU 'RST 7'\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    ";TEST JUMP INSTRUCTIONS AND FLAGS\n" +
                    ";\n" +
                    "test:   LXI     SP,STACK    ;SET THE STACK POINTER\n" +
                    "        ANI     0       ;INITIALIZE A REG. AND CLEAR ALL FLAGS\n" +
                    "        JZ      j010    ;TEST 'JZ'\n" +
                    "        CALL    cpuer\n" +
                    "j010:   JNC     j020    ;TEST 'JNC'\n" +
                    "        CALL    cpuer\n" +
                    "j020:   JPE     j030    ;TEST 'JPE'\n" +
                    "        CALL    cpuer\n" +
                    "j030:   JP      j040    ;TEST 'JP'\n" +
                    "        CALL    cpuer\n" +
                    "j040:   JNZ     j050    ;TEST 'JNZ'\n" +
                    "        JC      j050    ;TEST 'JC'\n" +
                    "        JPO     j050    ;TEST 'JPO'\n" +
                    "        JM      j050    ;TEST 'JM'\n" +
                    "        JMP     j060    ;TEST 'JMP' (IT'S A LITTLE LATE,BUT WHAT THE HELL!\n" +
                    "j050:   CALL    cpuer\n" +
                    "j060:   ADI     6       ;A=6,C=0,P=1,S=0,Z=0\n" +
                    "        JNZ     j070    ;TEST 'JNZ'\n" +
                    "        CALL    cpuer\n" +
                    "j070:   JC      j080    ;TEST 'JC'\n" +
                    "        JPO     j080    ;TEST 'JPO'\n" +
                    "        JP      j090    ;TEST 'JP'\n" +
                    "j080:   CALL    cpuer\n" +
                    "j090:   ADI     070h    ;A=76H,C=0,P=0,S=0,Z=0\n" +
                    "        JPO     j100    ;TEST 'JPO'\n" +
                    "        CALL    cpuer\n" +
                    "j100:   JM      j110    ;TEST 'JM'\n" +
                    "        JZ      j110    ;TEST 'JZ'\n" +
                    "        JNC     j120    ;TEST 'JNC'\n" +
                    "j110:   CALL    cpuer\n" +
                    "j120:   ADI     081h    ;A=F7H,C=0,P=0,S=1,Z=0\n" +
                    "        JM      j130    ;TEST 'JM'\n" +
                    "        CALL    cpuer\n" +
                    "j130:   JZ      j140    ;TEST 'JZ'\n" +
                    "        JC      j140    ;TEST 'JC'\n" +
                    "        JPO     j150    ;TEST 'JPO'\n" +
                    "j140:   CALL    cpuer\n" +
                    "j150:   ADI     0FEh    ;A=F5H,C=1,P=1,S=1,Z=0\n" +
                    "        JC      j160    ;TEST 'JC'\n" +
                    "        CALL    cpuer\n" +
                    "j160:   JZ      j170    ;TEST 'JZ'\n" +
                    "        JPO     j170    ;TEST 'JPO'\n" +
                    "        JM      aimm    ;TEST 'JM'\n" +
                    "j170:   CALL    cpuer\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    ";TEST ACCUMULATOR IMMEDIATE INSTRUCTIONS\n" +
                    ";\n" +
                    "aimm:   CPI     0       ;A=F5,C=0,Z=0\n" +
                    "        JC      cpie    ;TEST 'CPI' FOR RE-SET CARRY\n" +
                    "        JZ      cpie    ;TEST 'CPI' FOR RE-SET ZERO\n" +
                    "        CPI     0F5h    ;A=F5,C=0,Z=1\n" +
                    "        JC      cpie    ;TEST 'CPI' FOR RE-SET CARRY ('ADI')\n" +
                    "        JNZ     cpie    ;TEST 'CPI' FOR RE-SET ZERO\n" +
                    "        CPI     0FFh    ;A=F5,C=1,Z=0\n" +
                    "        JZ      cpie    ;TEST 'CPI' FOR RE-SET ZERO\n" +
                    "        JC      acii    ;TEST 'CPI' FOR SET CARRY\n" +
                    "cpie:   CALL    cpuer\n" +
                    "acii:   ACI     00Ah    ;A=F5+0A+CARRY(1)=00,C=1\n" +
                    "        ACI     00Ah    ;A=00+0A+CARRY(0)=0B,C=0\n" +
                    "        CPI     00Bh\n" +
                    "        JZ      suii    ;TEST 'ACI'\n" +
                    "        CALL    cpuer\n" +
                    "suii:   SUI     00Ch    ;A=FF,C=0\n" +
                    "        SUI     00Fh    ;A=F0,C=1\n" +
                    "        CPI     0F0h\n" +
                    "        JZ      sbii    ;TEST 'SUI'\n" +
                    "        CALL    cpuer\n" +
                    "sbii:   SBI     0F1h    ;A=F0-F1-CARRY(0)=FF,C=1\n" +
                    "        SBI     00Eh    ;A=FF-OE-CARRY(1)=F0,C=0\n" +
                    "        CPI     0F0h\n" +
                    "        JZ      anii    ;TEST 'SBI'\n" +
                    "        CALL    cpuer\n" +
                    "anii:   ANI     055h    ;A=F0<AND>55=50,C=0,P=1,S=0,Z=0\n" +
                    "        CPI     050h\n" +
                    "        JZ      orii    ;TEST 'ANI'\n" +
                    "        CALL    cpuer\n" +
                    "orii:   ORI     03Ah    ;A=50<OR>3A=7A,C=0,P=0,S=0,Z=0\n" +
                    "        CPI     07Ah\n" +
                    "        JZ      xrii    ;TEST 'ORI'\n" +
                    "        CALL    cpuer\n" +
                    "xrii:   XRI     00Fh    ;A=7A<XOR>0F=75,C=0,P=0,S=0,Z=0\n" +
                    "        CPI     075h\n" +
                    "        JZ      c010    ;TEST 'XRI'\n" +
                    "        CALL    cpuer\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    ";TEST CALLS AND RETURNS\n" +
                    ";\n" +
                    "c010:   ANI     000h    ;A=0,C=0,P=1,S=0,Z=1\n" +
                    "        CC      cpuer   ;TEST 'CC'\n" +
                    "        CPO     cpuer   ;TEST 'CPO'\n" +
                    "        CM      cpuer   ;TEST 'CM'\n" +
                    "        CNZ     cpuer   ;TEST 'CNZ'\n" +
                    "        CPI     000h\n" +
                    "        JZ      c020    ;A=0,C=0,P=0,S=0,Z=1\n" +
                    "        CALL    cpuer\n" +
                    "c020:   SUI     077h    ;A=89,C=1,P=0,S=1,Z=0\n" +
                    "        CNC     cpuer   ;TEST 'CNC'\n" +
                    "        CPE     cpuer   ;TEST 'CPE'\n" +
                    "        CP      cpuer   ;TEST 'CP'\n" +
                    "        CZ      cpuer   ;TEST 'CZ'\n" +
                    "        CPI     089h\n" +
                    "        JZ      c030    ;TEST FOR 'CALLS' TAKING BRANCH\n" +
                    "        CALL    cpuer\n" +
                    "c030:   ANI     0FFh    ;SET FLAGS BACK!\n" +
                    "        CPO     cpoi    ;TEST 'CPO'\n" +
                    "        CPI     0D9h\n" +
                    "        JZ      movi    ;TEST 'CALL' SEQUENCE SUCCESS\n" +
                    "        CALL    cpuer\n" +
                    "cpoi:   RPE             ;TEST 'RPE'\n" +
                    "        ADI     010h    ;A=99,C=0,P=0,S=1,Z=0\n" +
                    "        CPE     cpei    ;TEST 'CPE'\n" +
                    "        ADI     002h    ;A=D9,C=0,P=0,S=1,Z=0\n" +
                    "        RPO             ;TEST 'RPO'\n" +
                    "        CALL    cpuer\n" +
                    "cpei:   RPO             ;TEST 'RPO'\n" +
                    "        ADI     020h    ;A=B9,C=0,P=0,S=1,Z=0\n" +
                    "        CM      cmi     ;TEST 'CM'\n" +
                    "        ADI     004h    ;A=D7,C=0,P=1,S=1,Z=0\n" +
                    "        RPE             ;TEST 'RPE'\n" +
                    "        CALL    cpuer\n" +
                    "cmi:    RP              ;TEST 'RP'\n" +
                    "        ADI     080h    ;A=39,C=1,P=1,S=0,Z=0\n" +
                    "        CP      tcpi    ;TEST 'CP'\n" +
                    "        ADI     080h    ;A=D3,C=0,P=0,S=1,Z=0\n" +
                    "        RM              ;TEST 'RM'\n" +
                    "        CALL    cpuer\n" +
                    "tcpi:   RM              ;TEST 'RM'\n" +
                    "        ADI     040h    ;A=79,C=0,P=0,S=0,Z=0\n" +
                    "        CNC     cnci    ;TEST 'CNC'\n" +
                    "        ADI     040h    ;A=53,C=0,P=1,S=0,Z=0\n" +
                    "        RP              ;TEST 'RP'\n" +
                    "        CALL    cpuer\n" +
                    "cnci:   RC              ;TEST 'RC'\n" +
                    "        ADI     08Fh    ;A=08,C=1,P=0,S=0,Z=0\n" +
                    "        CC      cci     ;TEST 'CC'\n" +
                    "        SUI     002h    ;A=13,C=0,P=0,S=0,Z=0\n" +
                    "        RNC             ;TEST 'RNC'\n" +
                    "        CALL    cpuer\n" +
                    "cci:    RNC             ;TEST 'RNC'\n" +
                    "        ADI     0F7h    ;A=FF,C=0,P=1,S=1,Z=0\n" +
                    "        CNZ     cnzi    ;TEST 'CNZ'\n" +
                    "        ADI     0FEh    ;A=15,C=1,P=0,S=0,Z=0\n" +
                    "        RC              ;TEST 'RC'\n" +
                    "        CALL    cpuer\n" +
                    "cnzi:   RZ              ;TEST 'RZ'\n" +
                    "        ADI     001h    ;A=00,C=1,P=1,S=0,Z=1\n" +
                    "        CZ      czi     ;TEST 'CZ'\n" +
                    "        ADI     0D0h    ;A=17,C=1,P=1,S=0,Z=0\n" +
                    "        RNZ             ;TEST 'RNZ'\n" +
                    "        CALL    cpuer\n" +
                    "czi:    RNZ             ;TEST 'RNZ'\n" +
                    "        ADI     047h    ;A=47,C=0,P=1,S=0,Z=0\n" +
                    "        CPI     047h    ;A=47,C=0,P=1,S=0,Z=1\n" +
                    "        RZ              ;TEST 'RZ'\n" +
                    "        CALL    cpuer\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    ";TEST 'MOV','INR',AND 'DCR' INSTRUCTIONS\n" +
                    ";\n" +
                    "movi:   MVI     A,077h\n" +
                    "        INR     A\n" +
                    "        MOV     B,A\n" +
                    "        INR     B\n" +
                    "        MOV     C,B\n" +
                    "        DCR     C\n" +
                    "        MOV     D,C\n" +
                    "        MOV     E,D\n" +
                    "        MOV     H,E\n" +
                    "        MOV     L,H\n" +
                    "        MOV     A,L     ;TEST 'MOV' A,L,H,E,D,C,B,A\n" +
                    "        DCR     A\n" +
                    "        MOV     C,A\n" +
                    "        MOV     E,C\n" +
                    "        MOV     L,E\n" +
                    "        MOV     B,L\n" +
                    "        MOV     D,B\n" +
                    "        MOV     H,D\n" +
                    "        MOV     A,H     ;TEST 'MOV' A,H,D,B,L,E,C,A\n" +
                    "        MOV     D,A\n" +
                    "        INR     D\n" +
                    "        MOV     L,D\n" +
                    "        MOV     C,L\n" +
                    "        INR     C\n" +
                    "        MOV     H,C\n" +
                    "        MOV     B,H\n" +
                    "        DCR     B\n" +
                    "        MOV     E,B\n" +
                    "        MOV     A,E     ;TEST 'MOV' A,E,B,H,C,L,D,A\n" +
                    "        MOV     E,A\n" +
                    "        INR     E\n" +
                    "        MOV     B,E\n" +
                    "        MOV     H,B\n" +
                    "        INR     H\n" +
                    "        MOV     C,H\n" +
                    "        MOV     L,C\n" +
                    "        MOV     D,L\n" +
                    "        DCR     D\n" +
                    "        MOV     A,D     ;TEST 'MOV' A,D,L,C,H,B,E,A\n" +
                    "        MOV     H,A\n" +
                    "        DCR     H\n" +
                    "        MOV     D,H\n" +
                    "        MOV     B,D\n" +
                    "        MOV     L,B\n" +
                    "        INR     L\n" +
                    "        MOV     E,L\n" +
                    "        DCR     E\n" +
                    "        MOV     C,E\n" +
                    "        MOV     A,C     ;TEST 'MOV' A,C,E,L,B,D,H,A\n" +
                    "        MOV     L,A\n" +
                    "        DCR     L\n" +
                    "        MOV     H,L\n" +
                    "        MOV     E,H\n" +
                    "        MOV     D,E\n" +
                    "        MOV     C,D\n" +
                    "        MOV     B,C\n" +
                    "        MOV     A,B\n" +
                    "        CPI     077h\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' A,B,C,D,E,H,L,A\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    ";TEST ARITHMETIC AND LOGIC INSTRUCTIONS\n" +
                    ";\n" +
                    "        XRA     A\n" +
                    "        MVI     B,001h\n" +
                    "        MVI     C,003h\n" +
                    "        MVI     D,007h\n" +
                    "        MVI     E,00Fh\n" +
                    "        MVI     H,01Fh\n" +
                    "        MVI     L,03Fh\n" +
                    "        ADD     B\n" +
                    "        ADD     C\n" +
                    "        ADD     D\n" +
                    "        ADD     E\n" +
                    "        ADD     H\n" +
                    "        ADD     L\n" +
                    "        ADD     A\n" +
                    "        CPI     0F0h\n" +
                    "        CNZ     cpuer    ;TEST 'ADD' B,C,D,E,H,L,A\n" +
                    "        SUB     B\n" +
                    "        SUB     C\n" +
                    "        SUB     D\n" +
                    "        SUB     E\n" +
                    "        SUB     H\n" +
                    "        SUB     L\n" +
                    "        CPI     078h\n" +
                    "        CNZ     cpuer    ;TEST 'SUB' B,C,D,E,H,L\n" +
                    "        SUB     A\n" +
                    "        CNZ     cpuer    ;TEST 'SUB' A\n" +
                    "        MVI     A,080h\n" +
                    "        ADD     A\n" +
                    "        MVI     B,001h\n" +
                    "        MVI     C,002h\n" +
                    "        MVI     D,003h\n" +
                    "        MVI     E,004h\n" +
                    "        MVI     H,005h\n" +
                    "        MVI     L,006h\n" +
                    "        ADC     B\n" +
                    "        MVI     B,080h\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     C\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     D\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     E\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     H\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     L\n" +
                    "        ADD     B\n" +
                    "        ADD     B\n" +
                    "        ADC     A\n" +
                    "        CPI     037h\n" +
                    "        CNZ     cpuer    ;TEST 'ADC' B,C,D,E,H,L,A\n" +
                    "        MVI     A,080h\n" +
                    "        ADD     A\n" +
                    "        MVI     B,001h\n" +
                    "        SBB     B\n" +
                    "        MVI     B,0FFh\n" +
                    "        ADD     B\n" +
                    "        SBB     C\n" +
                    "        ADD     B\n" +
                    "        SBB     D\n" +
                    "        ADD     B\n" +
                    "        SBB     E\n" +
                    "        ADD     B\n" +
                    "        SBB     H\n" +
                    "        ADD     B\n" +
                    "        SBB     L\n" +
                    "        CPI     0E0h\n" +
                    "        CNZ     cpuer    ;TEST 'SBB' B,C,D,E,H,L\n" +
                    "        MVI     A,080h\n" +
                    "        ADD     A\n" +
                    "        SBB     A\n" +
                    "        CPI     0FFH\n" +
                    "        CNZ     cpuer    ;TEST 'SBB' A\n" +
                    "        MVI     A,0FFh\n" +
                    "        MVI     B,0FEh\n" +
                    "        MVI     C,0FCh\n" +
                    "        MVI     D,0EFh\n" +
                    "        MVI     E,07Fh\n" +
                    "        MVI     H,0F4h\n" +
                    "        MVI     L,0BFh\n" +
                    "        ANA     A\n" +
                    "        ANA     C\n" +
                    "        ANA     D\n" +
                    "        ANA     E\n" +
                    "        ANA     H\n" +
                    "        ANA     L\n" +
                    "        ANA     A\n" +
                    "        CPI     024h\n" +
                    "        CNZ     cpuer    ;TEST 'ANA' B,C,D,E,H,L,A\n" +
                    "        XRA     A\n" +
                    "        MVI     B,001h\n" +
                    "        MVI     C,002h\n" +
                    "        MVI     D,004h\n" +
                    "        MVI     E,008h\n" +
                    "        MVI     H,010h\n" +
                    "        MVI     L,020h\n" +
                    "        ORA     B\n" +
                    "        ORA     C\n" +
                    "        ORA     D\n" +
                    "        ORA     E\n" +
                    "        ORA     H\n" +
                    "        ORA     L\n" +
                    "        ORA     A\n" +
                    "        CPI     03Fh\n" +
                    "        CNZ     cpuer    ;TEST 'ORA' B,C,D,E,H,L,A\n" +
                    "        MVI     A,000h\n" +
                    "        MVI     H,08Fh\n" +
                    "        MVI     L,04Fh\n" +
                    "        XRA     B\n" +
                    "        XRA     C\n" +
                    "        XRA     D\n" +
                    "        XRA     E\n" +
                    "        XRA     H\n" +
                    "        XRA     L\n" +
                    "        CPI     0CFh\n" +
                    "        CNZ     cpuer    ;TEST 'XRA' B,C,D,E,H,L\n" +
                    "        XRA     A\n" +
                    "        CNZ     cpuer    ;TEST 'XRA' A\n" +
                    "        MVI     B,044h\n" +
                    "        MVI     C,045h\n" +
                    "        MVI     D,046h\n" +
                    "        MVI     E,047h\n" +
                    "        MVI     H,(temp0 / 0FFh)    ;HIGH BYTE OF TEST MEMORY LOCATION\n" +
                    "        MVI     L,(temp0 & 0FFh)    ;LOW BYTE OF TEST MEMORY LOCATION\n" +
                    "        MOV     M,B\n" +
                    "        MVI     B,000h\n" +
                    "        MOV     B,M\n" +
                    "        MVI     A,044h\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,B AND B,M\n" +
                    "        MOV     M,D\n" +
                    "        MVI     D,000h\n" +
                    "        MOV     D,M\n" +
                    "        MVI     A,046h\n" +
                    "        CMP     D\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,D AND D,M\n" +
                    "        MOV     M,E\n" +
                    "        MVI     E,000h\n" +
                    "        MOV     E,M\n" +
                    "        MVI     A,047h\n" +
                    "        CMP     E\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,E AND E,M\n" +
                    "        MOV     M,H\n" +
                    "        MVI     H,(temp0 / 0FFh)\n" +
                    "        MVI     L,(temp0 & 0FFh)\n" +
                    "        MOV     H,M\n" +
                    "        MVI     A,(temp0 / 0FFh)\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,H AND H,M\n" +
                    "        MOV     M,L\n" +
                    "        MVI     H,(temp0 / 0FFh)\n" +
                    "        MVI     L,(temp0 & 0FFh)\n" +
                    "        MOV     L,M\n" +
                    "        MVI     A,(temp0 & 0FFh)\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,L AND L,M\n" +
                    "        MVI     H,(temp0 / 0FFh)\n" +
                    "        MVI     L,(temp0 & 0FFh)\n" +
                    "        MVI     A,032h\n" +
                    "        MOV     M,A\n" +
                    "        CMP     M\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' M,A\n" +
                    "        ADD     M\n" +
                    "        CPI     064h\n" +
                    "        CNZ     cpuer    ;TEST 'ADD' M\n" +
                    "        XRA     A\n" +
                    "        MOV     A,M\n" +
                    "        CPI     032h\n" +
                    "        CNZ     cpuer    ;TEST 'MOV' A,M\n" +
                    "        MVI     H,(temp0 / 0FFh)\n" +
                    "        MVI     L,(temp0 & 0FFh)\n" +
                    "        MOV     A,M\n" +
                    "        SUB     M\n" +
                    "        CNZ     cpuer    ;TEST 'SUB' M\n" +
                    "        MVI     A,080h\n" +
                    "        ADD     A\n" +
                    "        ADC     M\n" +
                    "        CPI     033h\n" +
                    "        CNZ     cpuer    ;TEST 'ADC' M\n" +
                    "        MVI     A,080h\n" +
                    "        ADD     A\n" +
                    "        SBB     M\n" +
                    "        CPI     0CDh\n" +
                    "        CNZ     cpuer    ;TEST 'SBB' M\n" +
                    "        ANA     M\n" +
                    "        CNZ     cpuer    ;TEST 'ANA' M\n" +
                    "        MVI     A,025h\n" +
                    "        ORA     M\n" +
                    "        CPI     037h\n" +
                    "        CNZ     cpuer    ;TEST 'ORA' M\n" +
                    "        XRA     M\n" +
                    "        CPI     005h\n" +
                    "        CNZ     cpuer    ;TEST 'XRA' M\n" +
                    "        MVI     M,055h\n" +
                    "        INR     M\n" +
                    "        DCR     M\n" +
                    "        ADD     M\n" +
                    "        CPI     05Ah\n" +
                    "        CNZ     cpuer    ;TEST 'INR','DCR',AND 'MVI' M\n" +
                    "        LXI     B,12FFh\n" +
                    "        LXI     D,12FFh\n" +
                    "        LXI     H,12FFh\n" +
                    "        INX     B\n" +
                    "        INX     D\n" +
                    "        INX     H\n" +
                    "        MVI     A,013h\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' B\n" +
                    "        CMP     D\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' D\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' H\n" +
                    "        MVI     A,000h\n" +
                    "        CMP     C\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' B\n" +
                    "        CMP     E\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' D\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'LXI' AND 'INX' H\n" +
                    "        DCX     B\n" +
                    "        DCX     D\n" +
                    "        DCX     H\n" +
                    "        MVI     A,012h\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' B\n" +
                    "        CMP     D\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' D\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' H\n" +
                    "        MVI     A,0FFh\n" +
                    "        CMP     C\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' B\n" +
                    "        CMP     E\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' D\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'DCX' H\n" +
                    "        STA     temp0\n" +
                    "        XRA     A\n" +
                    "        LDA     temp0\n" +
                    "        CPI     0FFh\n" +
                    "        CNZ     cpuer    ;TEST 'LDA' AND 'STA'\n" +
                    "        LHLD    tempp\n" +
                    "        SHLD    temp0\n" +
                    "        LDA     tempp\n" +
                    "        MOV     B,A\n" +
                    "        LDA     temp0\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'LHLD' AND 'SHLD'\n" +
                    "        LDA     tempp+1\n" +
                    "        MOV     B,A\n" +
                    "        LDA     temp0+1\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'LHLD' AND 'SHLD'\n" +
                    "        MVI     A,0AAh\n" +
                    "        STA     temp0\n" +
                    "        MOV     B,H\n" +
                    "        MOV     C,L\n" +
                    "        XRA     A\n" +
                    "        LDAX    B\n" +
                    "        CPI     0AAh\n" +
                    "        CNZ     cpuer    ;TEST 'LDAX' B\n" +
                    "        INR     A\n" +
                    "        STAX    B\n" +
                    "        LDA     temp0\n" +
                    "        CPI     0ABh\n" +
                    "        CNZ     cpuer    ;TEST 'STAX' B\n" +
                    "        MVI     A,077h\n" +
                    "        STA     temp0\n" +
                    "        LHLD    tempp\n" +
                    "        LXI     D,00000h\n" +
                    "        XCHG\n" +
                    "        XRA     A\n" +
                    "        LDAX    D\n" +
                    "        CPI     077h\n" +
                    "        CNZ     cpuer    ;TEST 'LDAX' D AND 'XCHG'\n" +
                    "        XRA     A\n" +
                    "        ADD     H\n" +
                    "        ADD     L\n" +
                    "        CNZ     cpuer    ;TEST 'XCHG'\n" +
                    "        MVI     A,0CCh\n" +
                    "        STAX    D\n" +
                    "        LDA     temp0\n" +
                    "        CPI     0CCh\n" +
                    "        STAX    D\n" +
                    "        LDA     temp0\n" +
                    "        CPI     0CCh\n" +
                    "        CNZ     cpuer    ;TEST 'STAX' D\n" +
                    "        LXI     H,07777h\n" +
                    "        DAD     H\n" +
                    "        MVI     A,0EEh\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' H\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' H\n" +
                    "        LXI     H,05555h\n" +
                    "        LXI     B,0FFFFh\n" +
                    "        DAD     B\n" +
                    "        MVI     A,055h\n" +
                    "        CNC     cpuer    ;TEST 'DAD' B\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' B\n" +
                    "        MVI     A,054h\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' B\n" +
                    "        LXI     H,0AAAAh\n" +
                    "        LXI     D,03333h\n" +
                    "        DAD     D\n" +
                    "        MVI     A,0DDh\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' D\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'DAD' B\n" +
                    "        STC\n" +
                    "        CNC     cpuer    ;TEST 'STC'\n" +
                    "        CMC\n" +
                    "        CC      cpuer    ;TEST 'CMC'\n" +
                    "        MVI     A,0AAh\n" +
                    "        CMA\n" +
                    "        CPI     055h\n" +
                    "        CNZ     cpuer    ;TEST 'CMA'\n" +
                    "        ORA     A        ;RE-SET AUXILIARY CARRY\n" +
                    "        DAA\n" +
                    "        CPI     055h\n" +
                    "        CNZ     cpuer    ;TEST 'DAA'\n" +
                    "        MVI     A,088h\n" +
                    "        ADD     A\n" +
                    "        DAA\n" +
                    "        CPI     076h\n" +
                    "        CNZ     cpuer    ;TEST 'DAA'\n" +
                    "        XRA     A\n" +
                    "        MVI     A,0AAh\n" +
                    "        DAA\n" +
                    "        CNC     cpuer    ;TEST 'DAA'\n" +
                    "        CPI     010h\n" +
                    "        CNZ     cpuer    ;TEST 'DAA'\n" +
                    "        XRA     A\n" +
                    "        MVI     A,09Ah\n" +
                    "        DAA\n" +
                    "        CNC     cpuer    ;TEST 'DAA'\n" +
                    "        CNZ     cpuer    ;TEST 'DAA'\n" +
                    "        STC\n" +
                    "        MVI     A,042h\n" +
                    "        RLC\n" +
                    "        CC      cpuer    ;TEST 'RLC' FOR RE-SET CARRY\n" +
                    "        RLC\n" +
                    "        CNC     cpuer    ;TEST 'RLC' FOR SET CARRY\n" +
                    "        CPI     009h\n" +
                    "        CNZ     cpuer    ;TEST 'RLC' FOR ROTATION\n" +
                    "        RRC\n" +
                    "        CNC     cpuer    ;TEST 'RRC' FOR SET CARRY\n" +
                    "        RRC\n" +
                    "        CPI     042h\n" +
                    "        CNZ     cpuer    ;TEST 'RRC' FOR ROTATION\n" +
                    "        RAL\n" +
                    "        RAL\n" +
                    "        CNC     cpuer    ;TEST 'RAL' FOR SET CARRY\n" +
                    "        CPI     008h\n" +
                    "        CNZ     cpuer    ;TEST 'RAL' FOR ROTATION\n" +
                    "        RAR\n" +
                    "        RAR\n" +
                    "        CC      cpuer    ;TEST 'RAR' FOR RE-SET CARRY\n" +
                    "        CPI     002h\n" +
                    "        CNZ     cpuer    ;TEST 'RAR' FOR ROTATION\n" +
                    "        LXI     B,01234h\n" +
                    "        LXI     D,0AAAAh\n" +
                    "        LXI     H,05555h\n" +
                    "        XRA     A\n" +
                    "        PUSH    B\n" +
                    "        PUSH    D\n" +
                    "        PUSH    H\n" +
                    "        PUSH    PSW\n" +
                    "        LXI     B,00000h\n" +
                    "        LXI     D,00000h\n" +
                    "        LXI     H,00000h\n" +
                    "        MVI     A,0C0h\n" +
                    "        ADI     0F0h\n" +
                    "        POP     PSW\n" +
                    "        POP     H\n" +
                    "        POP     D\n" +
                    "        POP     B\n" +
                    "        CC      cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'\n" +
                    "        CPO     cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'\n" +
                    "        CM      cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'\n" +
                    "        MVI     A,012h\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH B' AND 'POP B'\n" +
                    "        MVI     A,034h\n" +
                    "        CMP     C\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH B' AND 'POP B'\n" +
                    "        MVI     A,0AAh\n" +
                    "        CMP     D\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH D' AND 'POP D'\n" +
                    "        CMP     E\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH D' AND 'POP D'\n" +
                    "        MVI     A,055h\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH H' AND 'POP H'\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'PUSH H' AND 'POP H'\n" +
                    "        LXI     H,00000h\n" +
                    "        DAD     SP\n" +
                    "        SHLD    savstk   ;SAVE THE 'OLD' STACK-POINTER!\n" +
                    "        LXI     SP,temp4\n" +
                    "        DCX     SP\n" +
                    "        DCX     SP\n" +
                    "        INX     SP\n" +
                    "        DCX     SP\n" +
                    "        MVI     A,055h\n" +
                    "        STA     temp2\n" +
                    "        CMA\n" +
                    "        STA     temp3\n" +
                    "        POP     B\n" +
                    "        CMP     B\n" +
                    "        CNZ     cpuer    ;TEST 'LXI','DAD','INX' AND 'DCX' SP\n" +
                    "        CMA\n" +
                    "        CMP     C\n" +
                    "        CNZ     cpuer    ;TEST 'LXI','DAD','INX' AND 'DCX' SP\n" +
                    "        LXI     H,temp4\n" +
                    "        SPHL\n" +
                    "        LXI     H,07733h\n" +
                    "        DCX     SP\n" +
                    "        DCX     SP\n" +
                    "        XTHL\n" +
                    "        LDA     temp3\n" +
                    "        CPI     077h\n" +
                    "        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'\n" +
                    "        LDA     temp2\n" +
                    "        CPI     033h\n" +
                    "        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'\n" +
                    "        MVI     A,055h\n" +
                    "        CMP     L\n" +
                    "        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'\n" +
                    "        CMA\n" +
                    "        CMP     H\n" +
                    "        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'\n" +
                    "        LHLD    savstk   ;RESTORE THE 'OLD' STACK-POINTER\n" +
                    "        SPHL\n" +
                    "        LXI     H,cpuok\n" +
                    "        PCHL             ;TEST 'PCHL'\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    "cpuer:  LXI     H,ngcpu  ;OUTPUT ERROR MESSAGE TO CONSOLE\n" +
                    "        CALL    msg\n" +
                    "        XTHL\n" +
                    "        DCX     H\n" +
                    "        DCX     H\n" +
                    "        DCX     H\n" +
                    "        MOV     A,H\n" +
                    "        CALL    byteo    ;SHOW ERROR EXIT ADDRESS HIGH BYTE\n" +
                    "        MOV     A,L\n" +
                    "        CALL    byteo    ;SHOW ERROR EXIT ADDRESS LOW BYTE\n" +
                    "        JMP     wboot    ;EXIT TO CP/M WARM BOOT\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    "cpuok:  LXI     H,okcpu  ;OUTPUT TEST SUCCESSFUL TO CONSOLE\n" +
                    "        CALL    msg\n" +
                    "        JMP     wboot    ;EXIT TO CP/M WARM BOOT\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    "tempp:  DW      temp0    ;POINTER USED TO TEST 'LHLD','SHLD',\n" +
                    "                         ; AND 'LDAX' INSTRUCTIONS\n" +
                    ";\n" +
                    "temp0:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS\n" +
                    "temp1:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS\n" +
                    "temp2   DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS\n" +
                    "temp3:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS\n" +
                    "temp4:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS\n" +
                    "savstk: DS      2        ;TEMPORARY STACK-POINTER STORAGE LOCATION\n" +
                    ";\n" +
                    ";\n" +
                    ";\n" +
                    "STACK   EQU    tempp+256    ;DE-BUG STACK POINTER STORAGE AREA\n" +
                    ";\n" +
                    "end:";

}