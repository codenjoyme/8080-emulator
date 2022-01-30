;***********************************************************************
; MICROCOSM ASSOCIATES  8080/8085 CPU DIAGNOSTIC VERSION 1.0  (C) 1980
;***********************************************************************
; Load into virtual altair with: ALTAIR L=TEST.HEX
; Then press F2 to view screen, and 'G' to execute the test.
;
;DONATED TO THE "SIG/M" CP/M USER'S GROUP BY:
;KELLY SMITH, MICROCOSM ASSOCIATES
;3055 WACO AVENUE
;SIMI VALLEY, CALIFORNIA, 93065
;(805) 527-9321 (MODEM, CP/M-NET (TM))
;(805) 527-0518 (VERBAL)
;

        .project test.rks
        CPU     8080
        ORG     0000H
        DB 04h, 00h     ; RKS HEADER/START MEMORY
        DB 0C4h, 05h    ; RKS HEADER/END MEMORY
        
        LXI     H, lolz
        CALL    msg
        JMP     cpu     ;JUMP TO 8080 CPU DIAGNOSTIC
;
lolz:   DB      0dh, 0ah, "MICROCOSM ASSOCIATES 8080/8085 CPU DIAGNOSTIC VERSION 1.0  (C) 1980", 0dh, 0ah, 24h
;
bdos    EQU     0C037H  ;LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800H  ;LIK MONITOR-1M
;
;MESSAGE OUTPUT ROUTINE
;
msg:    MOV     A,M     ; Get data
        CPI     '$'     ; End?
        RZ
        CALL    pchar   ; Output
        INX     H       ; Next
        JMP     msg     ; Do all
;
;
;CHARACTER OUTPUT ROUTINE
;
pchar:  PUSH    PSW
        PUSH    D
        PUSH    H
        MOV     C,M
        CALL    bdos
        POP     H
        POP     D
        POP     PSW
        RET
;
;
;
byteo:  PUSH    PSW
        CALL    byto1
        MOV     M,A
        CALL    pchar
        POP     PSW
        CALL    byto2
        MOV     M,A
        JMP     pchar
byto1:  RRC
        RRC
        RRC
        RRC
byto2:  ANI     0FH
        CPI     0AH
        JM      byto3
        ADI     7
byto3:  ADI     30H
        RET
;
;
;
;************************************************************
;           MESSAGE TABLE FOR OPERATIONAL CPU TEST
;************************************************************
;
okcpu:  DB      0DH,0AH
        DB      "CPU IS OPERATIONAL$"
;
ngcpu:  DB      0DH,0AH
        DB      " CPU HAS FAILED!    ERROR EXIT=$"
;
;
;
;************************************************************
;                8080/8085 CPU TEST/DIAGNOSTIC
;************************************************************
;
;NOTE: (1) PROGRAM ASSUMES "CALL",AND "LXI SP" INSTRUCTIONS WORK!
;
;      (2) INSTRUCTIONS NOT TESTED ARE "HLT","DI","EI",
;          AND "RST 0" THRU "RST 7"
;
;
;
;TEST JUMP INSTRUCTIONS AND FLAGS
;
cpu:    LXI     SP,STACK    ;SET THE STACK POINTER
        ANI     0       ;INITIALIZE A REG. AND CLEAR ALL FLAGS
        JZ      j010    ;TEST "JZ"
        CALL    cpuer
j010:   JNC     j020    ;TEST "JNC"
        CALL    cpuer
j020:   JPE     j030    ;TEST "JPE"
        CALL    cpuer
j030:   JP      j040    ;TEST "JP"
        CALL    cpuer
j040:   JNZ     j050    ;TEST "JNZ"
        JC      j050    ;TEST "JC"
        JPO     j050    ;TEST "JPO"
        JM      j050    ;TEST "JM"
        JMP     j060    ;TEST "JMP" (IT'S A LITTLE LATE,BUT WHAT THE HELL!
j050:   CALL    cpuer
j060:   ADI     6       ;A=6,C=0,P=1,S=0,Z=0
        JNZ     j070    ;TEST "JNZ"
        CALL    cpuer
j070:   JC      j080    ;TEST "JC"
        JPO     j080    ;TEST "JPO"
        JP      j090    ;TEST "JP"
j080:   CALL    cpuer
j090:   ADI     070H    ;A=76H,C=0,P=0,S=0,Z=0
        JPO     j100    ;TEST "JPO"
        CALL    cpuer
j100:   JM      j110    ;TEST "JM"
        JZ      j110    ;TEST "JZ"
        JNC     j120    ;TEST "JNC"
j110:   CALL    cpuer
j120:   ADI     081H    ;A=F7H,C=0,P=0,S=1,Z=0
        JM      j130    ;TEST "JM"
        CALL    cpuer
j130:   JZ      j140    ;TEST "JZ"
        JC      j140    ;TEST "JC"
        JPO     j150    ;TEST "JPO"
j140:   CALL    cpuer
j150:   ADI     0FEH    ;A=F5H,C=1,P=1,S=1,Z=0
        JC      j160    ;TEST "JC"
        CALL    cpuer
j160:   JZ      j170    ;TEST "JZ"
        JPO     j170    ;TEST "JPO"
        JM      aimm    ;TEST "JM"
j170:   CALL    cpuer
;
;
;
;TEST ACCUMULATOR IMMEDIATE INSTRUCTIONS
;
aimm:   CPI     0       ;A=F5H,C=0,Z=0
        JC      cpie    ;TEST "CPI" FOR RE-SET CARRY
        JZ      cpie    ;TEST "CPI" FOR RE-SET ZERO
        CPI     0F5H    ;A=F5H,C=0,Z=1
        JC      cpie    ;TEST "CPI" FOR RE-SET CARRY ("ADI")
        JNZ     cpie    ;TEST "CPI" FOR RE-SET ZERO
        CPI     0FFH    ;A=F5H,C=1,Z=0
        JZ      cpie    ;TEST "CPI" FOR RE-SET ZERO
        JC      acii    ;TEST "CPI" FOR SET CARRY
cpie:   CALL    cpuer
acii:   ACI     00AH    ;A=F5H+0AH+CARRY(1)=0,C=1
        ACI     00AH    ;A=0+0AH+CARRY(0)=0BH,C=0
        CPI     00BH
        JZ      suii    ;TEST "ACI"
        CALL    cpuer
suii:   SUI     00CH    ;A=FFH,C=0
        SUI     00FH    ;A=F0H,C=1
        CPI     0F0H
        JZ      sbii    ;TEST "SUI"
        CALL    cpuer
sbii:   SBI     0F1H    ;A=F0H-0F1H-CARRY(0)=FFH,C=1
        SBI     00EH    ;A=FFH-OEH-CARRY(1)=F0H,C=0
        CPI     0F0H
        JZ      anii    ;TEST "SBI"
        CALL    cpuer
anii:   ANI     055H    ;A=F0H<AND>55H=50H,C=0,P=1,S=0,Z=0
        CPI     050H
        JZ      orii    ;TEST "ANI"
        CALL    cpuer
orii:   ORI     03AH    ;A=50H<OR>3AH=7AH,C=0,P=0,S=0,Z=0
        CPI     07AH
        JZ      xrii    ;TEST "ORI"
        CALL    cpuer
xrii:   XRI     00FH    ;A=7AH<XOR>0FH=75H,C=0,P=0,S=0,Z=0
        CPI     075H
        JZ      c010    ;TEST "XRI"
        CALL    cpuer
;
;
;
;TEST CALLS AND RETURNS
;
c010:   ANI     000H    ;A=0,C=0,P=1,S=0,Z=1
        CC      cpuer   ;TEST "CC"
        CPO     cpuer   ;TEST "CPO"
        CM      cpuer   ;TEST "CM"
        CNZ     cpuer   ;TEST "CNZ"
        CPI     000H
        JZ      c020    ;A=0,C=0,P=0,S=0,Z=1
        CALL    cpuer
c020:   SUI     077H    ;A=89H,C=1,P=0,S=1,Z=0
        CNC     cpuer   ;TEST "CNC"
        CPE     cpuer   ;TEST "CPE"
        CP      cpuer   ;TEST "CP"
        CZ      cpuer   ;TEST "CZ"
        CPI     089H
        JZ      c030    ;TEST FOR "CALLS" TAKING BRANCH
        CALL    cpuer
c030:   ANI     0FFH    ;SET FLAGS BACK!
        CPO     cpoi    ;TEST "CPO"
        CPI     0D9H
        JZ      movi    ;TEST "CALL" SEQUENCE SUCCESS
        CALL    cpuer
cpoi:   RPE             ;TEST "RPE"
        ADI     010H    ;A=99H,C=0,P=0,S=1,Z=0
        CPE     cpei    ;TEST "CPE"
        ADI     002H    ;A=D9H,C=0,P=0,S=1,Z=0
        RPO             ;TEST "RPO"
        CALL    cpuer
cpei:   RPO             ;TEST "RPO"
        ADI     020H    ;A=B9H,C=0,P=0,S=1,Z=0
        CM      cmi     ;TEST "CM"
        ADI     004H    ;A=D7H,C=0,P=1,S=1,Z=0
        RPE             ;TEST "RPE"
        CALL    cpuer
cmi:    RP              ;TEST "RP"
        ADI     080H    ;A=39H,C=1,P=1,S=0,Z=0
        CP      tcpi    ;TEST "CP"
        ADI     080H    ;A=D3H,C=0,P=0,S=1,Z=0
        RM              ;TEST "RM"
        CALL    cpuer
tcpi:   RM              ;TEST "RM"
        ADI     040H    ;A=79H,C=0,P=0,S=0,Z=0
        CNC     cnci    ;TEST "CNC"
        ADI     040H    ;A=53H,C=0,P=1,S=0,Z=0
        RP              ;TEST "RP"
        CALL    cpuer
cnci:   RC              ;TEST "RC"
        ADI     08FH    ;A=08H,C=1,P=0,S=0,Z=0
        CC      cci     ;TEST "CC"
        SUI     002H    ;A=13H,C=0,P=0,S=0,Z=0
        RNC             ;TEST "RNC"
        CALL    cpuer
cci:    RNC             ;TEST "RNC"
        ADI     0F7H    ;A=FFH,C=0,P=1,S=1,Z=0
        CNZ     cnzi    ;TEST "CNZ"
        ADI     0FEH    ;A=15H,C=1,P=0,S=0,Z=0
        RC              ;TEST "RC"
        CALL    cpuer
cnzi:   RZ              ;TEST "RZ"
        ADI     001H    ;A=00H,C=1,P=1,S=0,Z=1
        CZ      czi     ;TEST "CZ"
        ADI     0D0H    ;A=17H,C=1,P=1,S=0,Z=0
        RNZ             ;TEST "RNZ"
        CALL    cpuer
czi:    RNZ             ;TEST "RNZ"
        ADI     047H    ;A=47H,C=0,P=1,S=0,Z=0
        CPI     047H    ;A=47H,C=0,P=1,S=0,Z=1
        RZ              ;TEST "RZ"
        CALL    cpuer
;
;
;
;TEST "MOV","INR",AND "DCR" INSTRUCTIONS
;
movi:   MVI     A,077H
        INR     A
        MOV     B,A
        INR     B
        MOV     C,B
        DCR     C
        MOV     D,C
        MOV     E,D
        MOV     H,E
        MOV     L,H
        MOV     A,L     ;TEST "MOV" A,L,H,E,D,C,B,A
        DCR     A
        MOV     C,A
        MOV     E,C
        MOV     L,E
        MOV     B,L
        MOV     D,B
        MOV     H,D
        MOV     A,H     ;TEST "MOV" A,H,D,B,L,E,C,A
        MOV     D,A
        INR     D
        MOV     L,D
        MOV     C,L
        INR     C
        MOV     H,C
        MOV     B,H
        DCR     B
        MOV     E,B
        MOV     A,E     ;TEST "MOV" A,E,B,H,C,L,D,A
        MOV     E,A
        INR     E
        MOV     B,E
        MOV     H,B
        INR     H
        MOV     C,H
        MOV     L,C
        MOV     D,L
        DCR     D
        MOV     A,D     ;TEST "MOV" A,D,L,C,H,B,E,A
        MOV     H,A
        DCR     H
        MOV     D,H
        MOV     B,D
        MOV     L,B
        INR     L
        MOV     E,L
        DCR     E
        MOV     C,E
        MOV     A,C     ;TEST "MOV" A,C,E,L,B,D,H,A
        MOV     L,A
        DCR     L
        MOV     H,L
        MOV     E,H
        MOV     D,E
        MOV     C,D
        MOV     B,C
        MOV     A,B
        CPI     077H
        CNZ     cpuer    ;TEST "MOV" A,B,C,D,E,H,L,A
;
;
;
;TEST ARITHMETIC AND LOGIC INSTRUCTIONS
;
        XRA     A
        MVI     B,001H
        MVI     C,003H
        MVI     D,007H
        MVI     E,00FH
        MVI     H,01FH
        MVI     L,03FH
        ADD     B
        ADD     C
        ADD     D
        ADD     E
        ADD     H
        ADD     L
        ADD     A
        CPI     0F0H
        CNZ     cpuer    ;TEST "ADD" B,C,D,E,H,L,A
        SUB     B
        SUB     C
        SUB     D
        SUB     E
        SUB     H
        SUB     L
        CPI     078H
        CNZ     cpuer    ;TEST "SUB" B,C,D,E,H,L
        SUB     A
        CNZ     cpuer    ;TEST "SUB" A
        MVI     A,080H
        ADD     A
        MVI     B,001H
        MVI     C,002H
        MVI     D,003H
        MVI     E,004H
        MVI     H,005H
        MVI     L,006H
        ADC     B
        MVI     B,080H
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
        CPI     037H
        CNZ     cpuer    ;TEST "ADC" B,C,D,E,H,L,A
        MVI     A,080H
        ADD     A
        MVI     B,001H
        SBB     B
        MVI     B,0FFH
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
        CPI     0E0H
        CNZ     cpuer    ;TEST "SBB" B,C,D,E,H,L
        MVI     A,080H
        ADD     A
        SBB     A
        CPI     0FFH
        CNZ     cpuer    ;TEST "SBB" A
        MVI     A,0FFH
        MVI     B,0FEH
        MVI     C,0FCH
        MVI     D,0EFH
        MVI     E,07FH
        MVI     H,0F4H
        MVI     L,0BFH
        ANA     A
        ANA     C
        ANA     D
        ANA     E
        ANA     H
        ANA     L
        ANA     A
        CPI     024H
        CNZ     cpuer    ;TEST "ANA" B,C,D,E,H,L,A
        XRA     A
        MVI     B,001H
        MVI     C,002H
        MVI     D,004H
        MVI     E,008H
        MVI     H,010H
        MVI     L,020H
        ORA     B
        ORA     C
        ORA     D
        ORA     E
        ORA     H
        ORA     L
        ORA     A
        CPI     03FH
        CNZ     cpuer    ;TEST "ORA" B,C,D,E,H,L,A
        MVI     A,000H
        MVI     H,08FH
        MVI     L,04FH
        XRA     B
        XRA     C
        XRA     D
        XRA     E
        XRA     H
        XRA     L
        CPI     0CFH
        CNZ     cpuer    ;TEST "XRA" B,C,D,E,H,L
        XRA     A
        CNZ     cpuer    ;TEST "XRA" A
        MVI     B,044H
        MVI     C,045H
        MVI     D,046H
        MVI     E,047H
        MVI     H,(temp0/0FFH)    ;HIGH BYTE OF TEST MEMORY LOCATION
        MVI     L,(temp0&0FFH)    ;LOW BYTE OF TEST MEMORY LOCATION
        MOV     M,B
        MVI     B,000H
        MOV     B,M
        MVI     A,044H
        CMP     B
        CNZ     cpuer    ;TEST "MOV" M,B AND B,M
        MOV     M,D
        MVI     D,000H
        MOV     D,M
        MVI     A,046H
        CMP     D
        CNZ     cpuer    ;TEST "MOV" M,D AND D,M
        MOV     M,E
        MVI     E,000H
        MOV     E,M
        MVI     A,047H
        CMP     E
        CNZ     cpuer    ;TEST "MOV" M,E AND E,M
        MOV     M,H
        MVI     H,(temp0/0FFH)
        MVI     L,(temp0&0FFH)
        MOV     H,M
        MVI     A,(temp0/0FFH)
        CMP     H
        CNZ     cpuer    ;TEST "MOV" M,H AND H,M
        MOV     M,L
        MVI     H,(temp0/0FFH)
        MVI     L,(temp0&0FFH)
        MOV     L,M
        MVI     A,(temp0&0FFH)
        CMP     L
        CNZ     cpuer    ;TEST "MOV" M,L AND L,M
        MVI     H,(temp0/0FFH)
        MVI     L,(temp0&0FFH)
        MVI     A,032H
        MOV     M,A
        CMP     M
        CNZ     cpuer    ;TEST "MOV" M,A
        ADD     M
        CPI     064H
        CNZ     cpuer    ;TEST "ADD" M
        XRA     A
        MOV     A,M
        CPI     032H
        CNZ     cpuer    ;TEST "MOV" A,M
        MVI     H,(temp0/0FFH)
        MVI     L,(temp0&0FFH)
        MOV     A,M
        SUB     M
        CNZ     cpuer    ;TEST "SUB" M
        MVI     A,080H
        ADD     A
        ADC     M
        CPI     033H
        CNZ     cpuer    ;TEST "ADC" M
        MVI     A,080H
        ADD     A
        SBB     M
        CPI     0CDH
        CNZ     cpuer    ;TEST "SBB" M
        ANA     M
        CNZ     cpuer    ;TEST "ANA" M
        MVI     A,025H
        ORA     M
        CPI     037H
        CNZ     cpuer    ;TEST "ORA" M
        XRA     M
        CPI     005H
        CNZ     cpuer    ;TEST "XRA" M
        MVI     M,055H
        INR     M
        DCR     M
        ADD     M
        CPI     05AH
        CNZ     cpuer    ;TEST "INR","DCR",AND "MVI" M
        LXI     B,12FFH
        LXI     D,12FFH
        LXI     H,12FFH
        INX     B
        INX     D
        INX     H
        MVI     A,013H
        CMP     B
        CNZ     cpuer    ;TEST "LXI" AND "INX" B
        CMP     D
        CNZ     cpuer    ;TEST "LXI" AND "INX" D
        CMP     H
        CNZ     cpuer    ;TEST "LXI" AND "INX" H
        MVI     A,000H
        CMP     C
        CNZ     cpuer    ;TEST "LXI" AND "INX" B
        CMP     E
        CNZ     cpuer    ;TEST "LXI" AND "INX" D
        CMP     L
        CNZ     cpuer    ;TEST "LXI" AND "INX" H
        DCX     B
        DCX     D
        DCX     H
        MVI     A,012H
        CMP     B
        CNZ     cpuer    ;TEST "DCX" B
        CMP     D
        CNZ     cpuer    ;TEST "DCX" D
        CMP     H
        CNZ     cpuer    ;TEST "DCX" H
        MVI     A,0FFH
        CMP     C
        CNZ     cpuer    ;TEST "DCX" B
        CMP     E
        CNZ     cpuer    ;TEST "DCX" D
        CMP     L
        CNZ     cpuer    ;TEST "DCX" H
        STA     temp0
        XRA     A
        LDA     temp0
        CPI     0FFH
        CNZ     cpuer    ;TEST "LDA" AND "STA"
        LHLD    tempp
        SHLD    temp0
        LDA     tempp
        MOV     B,A
        LDA     temp0
        CMP     B
        CNZ     cpuer    ;TEST "LHLD" AND "SHLD"
        LDA     tempp+1
        MOV     B,A
        LDA     temp0+1
        CMP     B
        CNZ     cpuer    ;TEST "LHLD" AND "SHLD"
        MVI     A,0AAH
        STA     temp0
        MOV     B,H
        MOV     C,L
        XRA     A
        LDAX    B
        CPI     0AAH
        CNZ     cpuer    ;TEST "LDAX" B
        INR     A
        STAX    B
        LDA     temp0
        CPI     0ABH
        CNZ     cpuer    ;TEST "STAX" B
        MVI     A,077H
        STA     temp0
        LHLD    tempp
        LXI     D,00000H
        XCHG
        XRA     A
        LDAX    D
        CPI     077H
        CNZ     cpuer    ;TEST "LDAX" D AND "XCHG"
        XRA     A
        ADD     H
        ADD     L
        CNZ     cpuer    ;TEST "XCHG"
        MVI     A,0CCH
        STAX    D
        LDA     temp0
        CPI     0CCH
        STAX    D
        LDA     temp0
        CPI     0CCH
        CNZ     cpuer    ;TEST "STAX" D
        LXI     H,07777H
        DAD     H
        MVI     A,0EEH
        CMP     H
        CNZ     cpuer    ;TEST "DAD" H
        CMP     L
        CNZ     cpuer    ;TEST "DAD" H
        LXI     H,05555H
        LXI     B,0FFFFH
        DAD     B
        MVI     A,055H
        CNC     cpuer    ;TEST "DAD" B
        CMP     H
        CNZ     cpuer    ;TEST "DAD" B
        MVI     A,054H
        CMP     L
        CNZ     cpuer    ;TEST "DAD" B
        LXI     H,0AAAAH
        LXI     D,03333H
        DAD     D
        MVI     A,0DDH
        CMP     H
        CNZ     cpuer    ;TEST "DAD" D
        CMP     L
        CNZ     cpuer    ;TEST "DAD" B
        STC
        CNC     cpuer    ;TEST "STC"
        CMC
        CC      cpuer    ;TEST "CMC
        MVI     A,0AAH
        CMA
        CPI     055H
        CNZ     cpuer    ;TEST "CMA"
        ORA     A        ;RE-SET AUXILIARY CARRY
        DAA
        CPI     055H
        CNZ     cpuer    ;TEST "DAA"
        MVI     A,088H
        ADD     A
        DAA
        CPI     076H
        CNZ     cpuer    ;TEST "DAA"
        XRA     A
        MVI     A,0AAH
        DAA
        CNC     cpuer    ;TEST "DAA"
        CPI     010H
        CNZ     cpuer    ;TEST "DAA"
        XRA     A
        MVI     A,09AH
        DAA
        CNC     cpuer    ;TEST "DAA"
        CNZ     cpuer    ;TEST "DAA"
        STC
        MVI     A,042H
        RLC
        CC      cpuer    ;TEST "RLC" FOR RE-SET CARRY
        RLC
        CNC     cpuer    ;TEST "RLC" FOR SET CARRY
        CPI     009H
        CNZ     cpuer    ;TEST "RLC" FOR ROTATION
        RRC
        CNC     cpuer    ;TEST "RRC" FOR SET CARRY
        RRC
        CPI     042H
        CNZ     cpuer    ;TEST "RRC" FOR ROTATION
        RAL
        RAL
        CNC     cpuer    ;TEST "RAL" FOR SET CARRY
        CPI     008H
        CNZ     cpuer    ;TEST "RAL" FOR ROTATION
        RAR
        RAR
        CC      cpuer    ;TEST "RAR" FOR RE-SET CARRY
        CPI     002H
        CNZ     cpuer    ;TEST "RAR" FOR ROTATION
        LXI     B,01234H
        LXI     D,0AAAAH
        LXI     H,05555H
        XRA     A
        PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        LXI     B,00000H
        LXI     D,00000H
        LXI     H,00000H
        MVI     A,0C0H
        ADI     0F0H
        POP     PSW
        POP     H
        POP     D
        POP     B
        CC      cpuer    ;TEST "PUSH PSW" AND "POP PSW"
        CNZ     cpuer    ;TEST "PUSH PSW" AND "POP PSW"
        CPO     cpuer    ;TEST "PUSH PSW" AND "POP PSW"
        CM      cpuer    ;TEST "PUSH PSW" AND "POP PSW"
        MVI     A,012H
        CMP     B
        CNZ     cpuer    ;TEST "PUSH B" AND "POP B"
        MVI     A,034H
        CMP     C
        CNZ     cpuer    ;TEST "PUSH B" AND "POP B"
        MVI     A,0AAH
        CMP     D
        CNZ     cpuer    ;TEST "PUSH D" AND "POP D"
        CMP     E
        CNZ     cpuer    ;TEST "PUSH D" AND "POP D"
        MVI     A,055H
        CMP     H
        CNZ     cpuer    ;TEST "PUSH H" AND "POP H"
        CMP     L
        CNZ     cpuer    ;TEST "PUSH H" AND "POP H"
        LXI     H,00000H
        DAD     SP
        SHLD    savstk   ;SAVE THE "OLD" STACK-POINTER!
        LXI     SP,temp4
        DCX     SP
        DCX     SP
        INX     SP
        DCX     SP
        MVI     A,055H
        STA     temp2
        CMA
        STA     temp3
        POP     B
        CMP     B
        CNZ     cpuer    ;TEST "LXI","DAD","INX",AND "DCX" SP
        CMA
        CMP     C
        CNZ     cpuer    ;TEST "LXI","DAD","INX", AND "DCX" SP
        LXI     H,temp4
        SPHL
        LXI     H,07733H
        DCX     SP
        DCX     SP
        XTHL
        LDA     temp3
        CPI     077H
        CNZ     cpuer    ;TEST "SPHL" AND "XTHL"
        LDA     temp2
        CPI     033H
        CNZ     cpuer    ;TEST "SPHL" AND "XTHL"
        MVI     A,055H
        CMP     L
        CNZ     cpuer    ;TEST "SPHL" AND "XTHL"
        CMA
        CMP     H
        CNZ     cpuer    ;TEST "SPHL" AND "XTHL"
        LHLD    savstk   ;RESTORE THE "OLD" STACK-POINTER
        SPHL
        LXI     H,cpuok
        PCHL             ;TEST "PCHL"
;
;
;
cpuer:  LXI     H,ngcpu  ;OUTPUT "CPU HAS FAILED    ERROR EXIT=" TO CONSOLE
        CALL    msg
        XTHL
        MOV     A,H
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS HIGH BYTE
        MOV     A,L
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS LOW BYTE
        JMP     wboot    ;EXIT TO CP/M WARM BOOT
;
;
;
cpuok:  LXI     H,okcpu  ;OUTPUT "CPU IS OPERATIONAL" TO CONSOLE
        CALL    msg
        JMP     wboot    ;EXIT TO CP/M WARM BOOT
;
;
;
tempp:  DW      temp0    ;POINTER USED TO TEST "LHLD","SHLD",
                         ; AND "LDAX" INSTRUCTIONS
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
        END

