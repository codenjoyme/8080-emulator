;***********************************************************************
; MICROCOSM ASSOCIATES  8080/8085 CPU DIAGNOSTIC VERSION 1.0  (C) 1980
;***********************************************************************
; Load into virtual altair with: ALTAIR L=TEST.HEX
; Then press F2 to view screen, and 'G' to execute the test.
;
;DONATED TO THE 'SIG/M' CP/M USER'S GROUP BY:
;KELLY SMITH, MICROCOSM ASSOCIATES
;3055 WACO AVENUE
;SIMI VALLEY, CALIFORNIA, 93065
;(805) 527-9321 (MODEM, CP/M-NET (TM))
;(805) 527-0518 (VERBAL)
;
;******************************************************************************
;
; Modified by Oleksandr Baglai to be able to work on LIK and assemble with
;     https://svofski.github.io/pretty-8080-assembler/
;     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)
;
;******************************************************************************

        .PROJECT test.mem
        .tape специалистъ-mon
        CPU     8080
        .ORG    00000h

start:  LXI     H,hello
        CALL    msg
        JMP     test       ; JUMP TO 8080 CPU DIAGNOSTIC
;
hello:  DB      00Dh, 00Ah, 'MICROCOSM ASSOCIATES'
        DB      00Dh, 00Ah, '8080/8085 CPU DIAGNOSTIC'
        DB      00Dh, 00Ah, 'VERSION 1.0  (C) 1980', 00Dh, 00Ah, '$'
;
bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800h     ; LIK MONITOR-1M
;
;MESSAGE OUTPUT ROUTINE
;
msg:    PUSH    B          ; Push state
        PUSH    D
        PUSH    H
        PUSH    PSW
msgs:   MOV     A,M        ; Get data
        CPI     '$'        ; End?
        JZ      msge       ; Exit
        MOV     A,M
        CALL    pchar      ; Output
        INX     H          ; Next
        JMP     msgs       ; Do all
msge:   POP     PSW        ; Pop state
        POP     H
        POP     D
        POP     B
        RET
;
;CHARACTER OUTPUT ROUTINE
;
pchar:  PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        MOV     C,A
        CALL    bdos
        POP     PSW
        POP     H
        POP     D
        POP     B
        RET
;
;HEX BYTE OUTPUT ROUTINE
;
byteo:  PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        PUSH    PSW
        CALL    byto1
        CALL    pchar
        POP     PSW
        CALL    byto2
        CALL    pchar
        POP     PSW
        POP     H
        POP     D
        POP     B
        RET
byto1:  RRC
        RRC
        RRC
        RRC
byto2:  ANI     00Fh
        CPI     00Ah
        JM      byto3
        ADI     7
byto3:  ADI     030h
        RET
;
;************************************************************
;           MESSAGE TABLE FOR OPERATIONAL CPU TEST
;************************************************************
;
okcpu:  DB      00Dh, 00Ah, 'CPU IS OPERATIONAL$'
;
ngcpu:  DB      00Dh, 00Ah, 'CPU HAS FAILED!', 00Dh, 00Ah, 'ERROR EXIT=$'
;
;
;************************************************************
;                8080/8085 CPU TEST/DIAGNOSTIC
;************************************************************
;
;NOTE: (1) PROGRAM ASSUMES 'CALL',AND 'LXI SP' INSTRUCTIONS WORK!
;
;      (2) INSTRUCTIONS NOT TESTED ARE 'HLT','DI','EI',
;          AND 'RST 0' THRU 'RST 7'
;
;
;
;TEST JUMP INSTRUCTIONS AND FLAGS
;
test:   LXI     SP,STACK    ;SET THE STACK POINTER
        ANI     0       ;INITIALIZE A REG. AND CLEAR ALL FLAGS
        JZ      j010    ;TEST 'JZ'
        CALL    cpuer
j010:   JNC     j020    ;TEST 'JNC'
        CALL    cpuer
j020:   JPE     j030    ;TEST 'JPE'
        CALL    cpuer
j030:   JP      j040    ;TEST 'JP'
        CALL    cpuer
j040:   JNZ     j050    ;TEST 'JNZ'
        JC      j050    ;TEST 'JC'
        JPO     j050    ;TEST 'JPO'
        JM      j050    ;TEST 'JM'
        JMP     j060    ;TEST 'JMP' (IT'S A LITTLE LATE,BUT WHAT THE HELL!
j050:   CALL    cpuer
j060:   ADI     6       ;A=6,C=0,P=1,S=0,Z=0
        JNZ     j070    ;TEST 'JNZ'
        CALL    cpuer
j070:   JC      j080    ;TEST 'JC'
        JPO     j080    ;TEST 'JPO'
        JP      j090    ;TEST 'JP'
j080:   CALL    cpuer
j090:   ADI     070h    ;A=76H,C=0,P=0,S=0,Z=0
        JPO     j100    ;TEST 'JPO'
        CALL    cpuer
j100:   JM      j110    ;TEST 'JM'
        JZ      j110    ;TEST 'JZ'
        JNC     j120    ;TEST 'JNC'
j110:   CALL    cpuer
j120:   ADI     081h    ;A=F7H,C=0,P=0,S=1,Z=0
        JM      j130    ;TEST 'JM'
        CALL    cpuer
j130:   JZ      j140    ;TEST 'JZ'
        JC      j140    ;TEST 'JC'
        JPO     j150    ;TEST 'JPO'
j140:   CALL    cpuer
j150:   ADI     0FEh    ;A=F5H,C=1,P=1,S=1,Z=0
        JC      j160    ;TEST 'JC'
        CALL    cpuer
j160:   JZ      j170    ;TEST 'JZ'
        JPO     j170    ;TEST 'JPO'
        JM      aimm    ;TEST 'JM'
j170:   CALL    cpuer
;
;
;
;TEST ACCUMULATOR IMMEDIATE INSTRUCTIONS
;
aimm:   CPI     0       ;A=F5,C=0,Z=0
        JC      cpie    ;TEST 'CPI' FOR RE-SET CARRY
        JZ      cpie    ;TEST 'CPI' FOR RE-SET ZERO
        CPI     0F5h    ;A=F5,C=0,Z=1
        JC      cpie    ;TEST 'CPI' FOR RE-SET CARRY ('ADI')
        JNZ     cpie    ;TEST 'CPI' FOR RE-SET ZERO
        CPI     0FFh    ;A=F5,C=1,Z=0
        JZ      cpie    ;TEST 'CPI' FOR RE-SET ZERO
        JC      acii    ;TEST 'CPI' FOR SET CARRY
cpie:   CALL    cpuer
acii:   ACI     00Ah    ;A=F5+0A+CARRY(1)=00,C=1
        ACI     00Ah    ;A=00+0A+CARRY(0)=0B,C=0
        CPI     00Bh
        JZ      suii    ;TEST 'ACI'
        CALL    cpuer
suii:   SUI     00Ch    ;A=FF,C=0
        SUI     00Fh    ;A=F0,C=1
        CPI     0F0h
        JZ      sbii    ;TEST 'SUI'
        CALL    cpuer
sbii:   SBI     0F1h    ;A=F0-F1-CARRY(0)=FF,C=1
        SBI     00Eh    ;A=FF-OE-CARRY(1)=F0,C=0
        CPI     0F0h
        JZ      anii    ;TEST 'SBI'
        CALL    cpuer
anii:   ANI     055h    ;A=F0<AND>55=50,C=0,P=1,S=0,Z=0
        CPI     050h
        JZ      orii    ;TEST 'ANI'
        CALL    cpuer
orii:   ORI     03Ah    ;A=50<OR>3A=7A,C=0,P=0,S=0,Z=0
        CPI     07Ah
        JZ      xrii    ;TEST 'ORI'
        CALL    cpuer
xrii:   XRI     00Fh    ;A=7A<XOR>0F=75,C=0,P=0,S=0,Z=0
        CPI     075h
        JZ      c010    ;TEST 'XRI'
        CALL    cpuer
;
;
;
;TEST CALLS AND RETURNS
;
c010:   ANI     000h    ;A=0,C=0,P=1,S=0,Z=1
        CC      cpuer   ;TEST 'CC'
        CPO     cpuer   ;TEST 'CPO'
        CM      cpuer   ;TEST 'CM'
        CNZ     cpuer   ;TEST 'CNZ'
        CPI     000h
        JZ      c020    ;A=0,C=0,P=0,S=0,Z=1
        CALL    cpuer
c020:   SUI     077h    ;A=89,C=1,P=0,S=1,Z=0
        CNC     cpuer   ;TEST 'CNC'
        CPE     cpuer   ;TEST 'CPE'
        CP      cpuer   ;TEST 'CP'
        CZ      cpuer   ;TEST 'CZ'
        CPI     089h
        JZ      c030    ;TEST FOR 'CALLS' TAKING BRANCH
        CALL    cpuer
c030:   ANI     0FFh    ;SET FLAGS BACK!
        CPO     cpoi    ;TEST 'CPO'
        CPI     0D9h
        JZ      movi    ;TEST 'CALL' SEQUENCE SUCCESS
        CALL    cpuer
cpoi:   RPE             ;TEST 'RPE'
        ADI     010h    ;A=99,C=0,P=0,S=1,Z=0
        CPE     cpei    ;TEST 'CPE'
        ADI     002h    ;A=D9,C=0,P=0,S=1,Z=0
        RPO             ;TEST 'RPO'
        CALL    cpuer
cpei:   RPO             ;TEST 'RPO'
        ADI     020h    ;A=B9,C=0,P=0,S=1,Z=0
        CM      cmi     ;TEST 'CM'
        ADI     004h    ;A=D7,C=0,P=1,S=1,Z=0
        RPE             ;TEST 'RPE'
        CALL    cpuer
cmi:    RP              ;TEST 'RP'
        ADI     080h    ;A=39,C=1,P=1,S=0,Z=0
        CP      tcpi    ;TEST 'CP'
        ADI     080h    ;A=D3,C=0,P=0,S=1,Z=0
        RM              ;TEST 'RM'
        CALL    cpuer
tcpi:   RM              ;TEST 'RM'
        ADI     040h    ;A=79,C=0,P=0,S=0,Z=0
        CNC     cnci    ;TEST 'CNC'
        ADI     040h    ;A=53,C=0,P=1,S=0,Z=0
        RP              ;TEST 'RP'
        CALL    cpuer
cnci:   RC              ;TEST 'RC'
        ADI     08Fh    ;A=08,C=1,P=0,S=0,Z=0
        CC      cci     ;TEST 'CC'
        SUI     002h    ;A=13,C=0,P=0,S=0,Z=0
        RNC             ;TEST 'RNC'
        CALL    cpuer
cci:    RNC             ;TEST 'RNC'
        ADI     0F7h    ;A=FF,C=0,P=1,S=1,Z=0
        CNZ     cnzi    ;TEST 'CNZ'
        ADI     0FEh    ;A=15,C=1,P=0,S=0,Z=0
        RC              ;TEST 'RC'
        CALL    cpuer
cnzi:   RZ              ;TEST 'RZ'
        ADI     001h    ;A=00,C=1,P=1,S=0,Z=1
        CZ      czi     ;TEST 'CZ'
        ADI     0D0h    ;A=17,C=1,P=1,S=0,Z=0
        RNZ             ;TEST 'RNZ'
        CALL    cpuer
czi:    RNZ             ;TEST 'RNZ'
        ADI     047h    ;A=47,C=0,P=1,S=0,Z=0
        CPI     047h    ;A=47,C=0,P=1,S=0,Z=1
        RZ              ;TEST 'RZ'
        CALL    cpuer
;
;
;
;TEST 'MOV','INR',AND 'DCR' INSTRUCTIONS
;
movi:   MVI     A,077h
        INR     A
        MOV     B,A
        INR     B
        MOV     C,B
        DCR     C
        MOV     D,C
        MOV     E,D
        MOV     H,E
        MOV     L,H
        MOV     A,L     ;TEST 'MOV' A,L,H,E,D,C,B,A
        DCR     A
        MOV     C,A
        MOV     E,C
        MOV     L,E
        MOV     B,L
        MOV     D,B
        MOV     H,D
        MOV     A,H     ;TEST 'MOV' A,H,D,B,L,E,C,A
        MOV     D,A
        INR     D
        MOV     L,D
        MOV     C,L
        INR     C
        MOV     H,C
        MOV     B,H
        DCR     B
        MOV     E,B
        MOV     A,E     ;TEST 'MOV' A,E,B,H,C,L,D,A
        MOV     E,A
        INR     E
        MOV     B,E
        MOV     H,B
        INR     H
        MOV     C,H
        MOV     L,C
        MOV     D,L
        DCR     D
        MOV     A,D     ;TEST 'MOV' A,D,L,C,H,B,E,A
        MOV     H,A
        DCR     H
        MOV     D,H
        MOV     B,D
        MOV     L,B
        INR     L
        MOV     E,L
        DCR     E
        MOV     C,E
        MOV     A,C     ;TEST 'MOV' A,C,E,L,B,D,H,A
        MOV     L,A
        DCR     L
        MOV     H,L
        MOV     E,H
        MOV     D,E
        MOV     C,D
        MOV     B,C
        MOV     A,B
        CPI     077h
        CNZ     cpuer    ;TEST 'MOV' A,B,C,D,E,H,L,A
;
;
;
;TEST ARITHMETIC AND LOGIC INSTRUCTIONS
;
        XRA     A
        MVI     B,001h
        MVI     C,003h
        MVI     D,007h
        MVI     E,00Fh
        MVI     H,01Fh
        MVI     L,03Fh
        ADD     B
        ADD     C
        ADD     D
        ADD     E
        ADD     H
        ADD     L
        ADD     A
        CPI     0F0h
        CNZ     cpuer    ;TEST 'ADD' B,C,D,E,H,L,A
        SUB     B
        SUB     C
        SUB     D
        SUB     E
        SUB     H
        SUB     L
        CPI     078h
        CNZ     cpuer    ;TEST 'SUB' B,C,D,E,H,L
        SUB     A
        CNZ     cpuer    ;TEST 'SUB' A
        MVI     A,080h
        ADD     A
        MVI     B,001h
        MVI     C,002h
        MVI     D,003h
        MVI     E,004h
        MVI     H,005h
        MVI     L,006h
        ADC     B
        MVI     B,080h
        ADD     B
        ADD     B
        ADC     C
        ADD     B
        ADD     B
        ADC     D
        ADD     B
        ADD     B
        ADC     E
        ADD     B
        ADD     B
        ADC     H
        ADD     B
        ADD     B
        ADC     L
        ADD     B
        ADD     B
        ADC     A
        CPI     037h
        CNZ     cpuer    ;TEST 'ADC' B,C,D,E,H,L,A
        MVI     A,080h
        ADD     A
        MVI     B,001h
        SBB     B
        MVI     B,0FFh
        ADD     B
        SBB     C
        ADD     B
        SBB     D
        ADD     B
        SBB     E
        ADD     B
        SBB     H
        ADD     B
        SBB     L
        CPI     0E0h
        CNZ     cpuer    ;TEST 'SBB' B,C,D,E,H,L
        MVI     A,080h
        ADD     A
        SBB     A
        CPI     0FFH
        CNZ     cpuer    ;TEST 'SBB' A
        MVI     A,0FFh
        MVI     B,0FEh
        MVI     C,0FCh
        MVI     D,0EFh
        MVI     E,07Fh
        MVI     H,0F4h
        MVI     L,0BFh
        ANA     A
        ANA     C
        ANA     D
        ANA     E
        ANA     H
        ANA     L
        ANA     A
        CPI     024h
        CNZ     cpuer    ;TEST 'ANA' B,C,D,E,H,L,A
        XRA     A
        MVI     B,001h
        MVI     C,002h
        MVI     D,004h
        MVI     E,008h
        MVI     H,010h
        MVI     L,020h
        ORA     B
        ORA     C
        ORA     D
        ORA     E
        ORA     H
        ORA     L
        ORA     A
        CPI     03Fh
        CNZ     cpuer    ;TEST 'ORA' B,C,D,E,H,L,A
        MVI     A,000h
        MVI     H,08Fh
        MVI     L,04Fh
        XRA     B
        XRA     C
        XRA     D
        XRA     E
        XRA     H
        XRA     L
        CPI     0CFh
        CNZ     cpuer    ;TEST 'XRA' B,C,D,E,H,L
        XRA     A
        CNZ     cpuer    ;TEST 'XRA' A
        MVI     B,044h
        MVI     C,045h
        MVI     D,046h
        MVI     E,047h
        MVI     H,(temp0 / 0FFh)    ;HIGH BYTE OF TEST MEMORY LOCATION
        MVI     L,(temp0 & 0FFh)    ;LOW BYTE OF TEST MEMORY LOCATION
        MOV     M,B
        MVI     B,000h
        MOV     B,M
        MVI     A,044h
        CMP     B
        CNZ     cpuer    ;TEST 'MOV' M,B AND B,M
        MOV     M,D
        MVI     D,000h
        MOV     D,M
        MVI     A,046h
        CMP     D
        CNZ     cpuer    ;TEST 'MOV' M,D AND D,M
        MOV     M,E
        MVI     E,000h
        MOV     E,M
        MVI     A,047h
        CMP     E
        CNZ     cpuer    ;TEST 'MOV' M,E AND E,M
        MOV     M,H
        MVI     H,(temp0 / 0FFh)
        MVI     L,(temp0 & 0FFh)
        MOV     H,M
        MVI     A,(temp0 / 0FFh)
        CMP     H
        CNZ     cpuer    ;TEST 'MOV' M,H AND H,M
        MOV     M,L
        MVI     H,(temp0 / 0FFh)
        MVI     L,(temp0 & 0FFh)
        MOV     L,M
        MVI     A,(temp0 & 0FFh)
        CMP     L
        CNZ     cpuer    ;TEST 'MOV' M,L AND L,M
        MVI     H,(temp0 / 0FFh)
        MVI     L,(temp0 & 0FFh)
        MVI     A,032h
        MOV     M,A
        CMP     M
        CNZ     cpuer    ;TEST 'MOV' M,A
        ADD     M
        CPI     064h
        CNZ     cpuer    ;TEST 'ADD' M
        XRA     A
        MOV     A,M
        CPI     032h
        CNZ     cpuer    ;TEST 'MOV' A,M
        MVI     H,(temp0 / 0FFh)
        MVI     L,(temp0 & 0FFh)
        MOV     A,M
        SUB     M
        CNZ     cpuer    ;TEST 'SUB' M
        MVI     A,080h
        ADD     A
        ADC     M
        CPI     033h
        CNZ     cpuer    ;TEST 'ADC' M
        MVI     A,080h
        ADD     A
        SBB     M
        CPI     0CDh
        CNZ     cpuer    ;TEST 'SBB' M
        ANA     M
        CNZ     cpuer    ;TEST 'ANA' M
        MVI     A,025h
        ORA     M
        CPI     037h
        CNZ     cpuer    ;TEST 'ORA' M
        XRA     M
        CPI     005h
        CNZ     cpuer    ;TEST 'XRA' M
        MVI     M,055h
        INR     M
        DCR     M
        ADD     M
        CPI     05Ah
        CNZ     cpuer    ;TEST 'INR','DCR',AND 'MVI' M
        LXI     B,12FFh
        LXI     D,12FFh
        LXI     H,12FFh
        INX     B
        INX     D
        INX     H
        MVI     A,013h
        CMP     B
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' B
        CMP     D
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' D
        CMP     H
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' H
        MVI     A,000h
        CMP     C
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' B
        CMP     E
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' D
        CMP     L
        CNZ     cpuer    ;TEST 'LXI' AND 'INX' H
        DCX     B
        DCX     D
        DCX     H
        MVI     A,012h
        CMP     B
        CNZ     cpuer    ;TEST 'DCX' B
        CMP     D
        CNZ     cpuer    ;TEST 'DCX' D
        CMP     H
        CNZ     cpuer    ;TEST 'DCX' H
        MVI     A,0FFh
        CMP     C
        CNZ     cpuer    ;TEST 'DCX' B
        CMP     E
        CNZ     cpuer    ;TEST 'DCX' D
        CMP     L
        CNZ     cpuer    ;TEST 'DCX' H
        STA     temp0
        XRA     A
        LDA     temp0
        CPI     0FFh
        CNZ     cpuer    ;TEST 'LDA' AND 'STA'
        LHLD    tempp
        SHLD    temp0
        LDA     tempp
        MOV     B,A
        LDA     temp0
        CMP     B
        CNZ     cpuer    ;TEST 'LHLD' AND 'SHLD'
        LDA     tempp+1
        MOV     B,A
        LDA     temp0+1
        CMP     B
        CNZ     cpuer    ;TEST 'LHLD' AND 'SHLD'
        MVI     A,0AAh
        STA     temp0
        MOV     B,H
        MOV     C,L
        XRA     A
        LDAX    B
        CPI     0AAh
        CNZ     cpuer    ;TEST 'LDAX' B
        INR     A
        STAX    B
        LDA     temp0
        CPI     0ABh
        CNZ     cpuer    ;TEST 'STAX' B
        MVI     A,077h
        STA     temp0
        LHLD    tempp
        LXI     D,00000h
        XCHG
        XRA     A
        LDAX    D
        CPI     077h
        CNZ     cpuer    ;TEST 'LDAX' D AND 'XCHG'
        XRA     A
        ADD     H
        ADD     L
        CNZ     cpuer    ;TEST 'XCHG'
        MVI     A,0CCh
        STAX    D
        LDA     temp0
        CPI     0CCh
        STAX    D
        LDA     temp0
        CPI     0CCh
        CNZ     cpuer    ;TEST 'STAX' D
        LXI     H,07777h
        DAD     H
        MVI     A,0EEh
        CMP     H
        CNZ     cpuer    ;TEST 'DAD' H
        CMP     L
        CNZ     cpuer    ;TEST 'DAD' H
        LXI     H,05555h
        LXI     B,0FFFFh
        DAD     B
        MVI     A,055h
        CNC     cpuer    ;TEST 'DAD' B
        CMP     H
        CNZ     cpuer    ;TEST 'DAD' B
        MVI     A,054h
        CMP     L
        CNZ     cpuer    ;TEST 'DAD' B
        LXI     H,0AAAAh
        LXI     D,03333h
        DAD     D
        MVI     A,0DDh
        CMP     H
        CNZ     cpuer    ;TEST 'DAD' D
        CMP     L
        CNZ     cpuer    ;TEST 'DAD' B
        STC
        CNC     cpuer    ;TEST 'STC'
        CMC
        CC      cpuer    ;TEST 'CMC'
        MVI     A,0AAh
        CMA
        CPI     055h
        CNZ     cpuer    ;TEST 'CMA'
        ORA     A        ;RE-SET AUXILIARY CARRY
        DAA
        CPI     055h
        CNZ     cpuer    ;TEST 'DAA'
        MVI     A,088h
        ADD     A
        DAA
        CPI     076h
        CNZ     cpuer    ;TEST 'DAA'
        XRA     A
        MVI     A,0AAh
        DAA
        CNC     cpuer    ;TEST 'DAA'
        CPI     010h
        CNZ     cpuer    ;TEST 'DAA'
        XRA     A
        MVI     A,09Ah
        DAA
        CNC     cpuer    ;TEST 'DAA'
        CNZ     cpuer    ;TEST 'DAA'
        STC
        MVI     A,042h
        RLC
        CC      cpuer    ;TEST 'RLC' FOR RE-SET CARRY
        RLC
        CNC     cpuer    ;TEST 'RLC' FOR SET CARRY
        CPI     009h
        CNZ     cpuer    ;TEST 'RLC' FOR ROTATION
        RRC
        CNC     cpuer    ;TEST 'RRC' FOR SET CARRY
        RRC
        CPI     042h
        CNZ     cpuer    ;TEST 'RRC' FOR ROTATION
        RAL
        RAL
        CNC     cpuer    ;TEST 'RAL' FOR SET CARRY
        CPI     008h
        CNZ     cpuer    ;TEST 'RAL' FOR ROTATION
        RAR
        RAR
        CC      cpuer    ;TEST 'RAR' FOR RE-SET CARRY
        CPI     002h
        CNZ     cpuer    ;TEST 'RAR' FOR ROTATION
        LXI     B,01234h
        LXI     D,0AAAAh
        LXI     H,05555h
        XRA     A
        PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        LXI     B,00000h
        LXI     D,00000h
        LXI     H,00000h
        MVI     A,0C0h
        ADI     0F0h
        POP     PSW
        POP     H
        POP     D
        POP     B
        CC      cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'
        CNZ     cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'
        CPO     cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'
        CM      cpuer    ;TEST 'PUSH PSW' AND 'POP PSW'
        MVI     A,012h
        CMP     B
        CNZ     cpuer    ;TEST 'PUSH B' AND 'POP B'
        MVI     A,034h
        CMP     C
        CNZ     cpuer    ;TEST 'PUSH B' AND 'POP B'
        MVI     A,0AAh
        CMP     D
        CNZ     cpuer    ;TEST 'PUSH D' AND 'POP D'
        CMP     E
        CNZ     cpuer    ;TEST 'PUSH D' AND 'POP D'
        MVI     A,055h
        CMP     H
        CNZ     cpuer    ;TEST 'PUSH H' AND 'POP H'
        CMP     L
        CNZ     cpuer    ;TEST 'PUSH H' AND 'POP H'
        LXI     H,00000h
        DAD     SP
        SHLD    savstk   ;SAVE THE 'OLD' STACK-POINTER!
        LXI     SP,temp4
        DCX     SP
        DCX     SP
        INX     SP
        DCX     SP
        MVI     A,055h
        STA     temp2
        CMA
        STA     temp3
        POP     B
        CMP     B
        CNZ     cpuer    ;TEST 'LXI','DAD','INX' AND 'DCX' SP
        CMA
        CMP     C
        CNZ     cpuer    ;TEST 'LXI','DAD','INX' AND 'DCX' SP
        LXI     H,temp4
        SPHL
        LXI     H,07733h
        DCX     SP
        DCX     SP
        XTHL
        LDA     temp3
        CPI     077h
        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'
        LDA     temp2
        CPI     033h
        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'
        MVI     A,055h
        CMP     L
        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'
        CMA
        CMP     H
        CNZ     cpuer    ;TEST 'SPHL' AND 'XTHL'
        LHLD    savstk   ;RESTORE THE 'OLD' STACK-POINTER
        SPHL
        LXI     H,cpuok
        PCHL             ;TEST 'PCHL'
;
;
;
cpuer:  LXI     H,ngcpu  ;OUTPUT ERROR MESSAGE TO CONSOLE
        CALL    msg
        XTHL
        DCX     H
        DCX     H
        DCX     H
        MOV     A,H
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS HIGH BYTE
        MOV     A,L
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS LOW BYTE
        JMP     wboot    ;EXIT TO CP/M WARM BOOT
;
;
;
cpuok:  LXI     H,okcpu  ;OUTPUT TEST SUCCESSFUL TO CONSOLE
        CALL    msg
        JMP     wboot    ;EXIT TO CP/M WARM BOOT
;
;
;
tempp:  DW      temp0    ;POINTER USED TO TEST 'LHLD','SHLD',
                         ; AND 'LDAX' INSTRUCTIONS
;
temp0:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS
temp1:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS
temp2   DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS
temp3:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS
temp4:  DS      1        ;TEMPORARY STORAGE FOR CPU TEST MEMORY LOCATIONS
savstk: DS      2        ;TEMPORARY STACK-POINTER STORAGE LOCATION
;
;
;
STACK   EQU    tempp+256    ;DE-BUG STACK POINTER STORAGE AREA
;
end: