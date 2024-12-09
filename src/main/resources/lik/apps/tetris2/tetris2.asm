        CPU  8080
        .ORG 00000h
lab132  EQU 0FF00h
lab142  EQU 0B000h
lab135  EQU 0FF01h
lab129  EQU 0FF02h
lab126  EQU 0FF03h
lab101  EQU 08008h
lab93   EQU 02313h
lab92   EQU 0231Bh
lab21   EQU 01323h
lab0    EQU 0C037h
lab120  EQU 0C337h
lab7    EQU 0C438h
lab84   EQU 0A338h
lab86   EQU 0AC38h
lab110  EQU 0A438h
lab94   EQU 0C170h
lab3    EQU 02377h
lab70   EQU 0A0A8h
lab72   EQU 012B6h
lab111  EQU 022CDh
lab109  EQU 08FF1h
lab90   EQU 08FF2h
lab140  EQU 08FF4h
lab102  EQU 08FFCh
        MVI C,01Fh
        CALL lab0
        LXI H,lab1
        LXI D,lab2
        MVI C,018h
lab4:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lab4
        CALL lab5
lab42:  MVI C,00Ch
        CALL lab0
        LXI H,lab6
        CALL lab7
        CALL lab8
        ANI 00Fh
        RLC
        RLC
        RLC
        RLC
        STA lab9
        CALL lab10
        LXI H,lab11
        MVI C,050h
lab12:  MVI M,000h
        INX H
        DCR C
        JNZ lab12
        MVI A,0FFh
        STA lab13
        CALL lab14
        CALL lab15
lab47:  CALL lab16
        CALL lab17
        LXI H,lab18
        LXI D,lab19
        JMP lab20

lab18:  DB 004h, 000h, 000h, 004h, 000h, 000h, 000h, 001h, 000h, 000h
lab20:  MVI C,00Ah
lab22:  MOV A,M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lab22
lab41:  CALL lab23
        CALL lab24
        CALL lab25
        CALL lab26
        LDA lab27
        CPI 000h
        JNZ lab28
        MVI A,000h
        STA lab29
        STA lab30
        CALL lab31
        CALL lab32
        CALL lab5
        LDA lab33
        CPI 000h
        CZ lab34
        LDA lab35
        STA lab19
        LDA lab36
        STA lab37
        LDA lab38
        STA lab39
        CALL lab40
        JMP lab41
lab28:  LDA lab29
        CPI 001h
        JZ lab42
        LDA lab36
        MOV B,A
        LDA lab37
        CMP B
        JZ lab43
lab48:  LDA lab37
        STA lab36
        CALL lab23
        CALL lab24
        CALL lab25
        CALL lab31
        CALL lab44
        CALL lab32
        CALL lab45
        CALL lab46
        JMP lab47
lab43:  LDA lab30
        CPI 001h
        JZ lab48
        LDA lab19
        STA lab35
        LDA lab39
        STA lab38
        MVI A,001h
        STA lab30
        LDA lab37
        INR A
        STA lab36
        JMP lab41
lab121: LDA lab9
        MOV B,A
lab50:  MVI C,0FFh
lab49:  DCR C
        JNZ lab49
        DCR B
        JNZ lab50
        RET
lab32:  LXI H,lab1
        LXI D,lab51
        CALL lab52
        RET
lab44:  LXI H,lab53
        LXI D,lab51
        CALL lab52
        RET
lab46:  LXI H,lab53
        LXI D,lab1
        CALL lab52
        RET
lab52:  MVI C,018h
lab54:  LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lab54
        RET
lab23:  CALL lab55
        LXI D,lab56
        MVI C,004h
lab57:  MOV A,M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lab57
        RET
lab55:  LXI H,lab58
        LDA lab59
        MVI D,000h
        MOV E,A
        MVI C,010h
lab60:  DAD D
        DCR C
        JNZ lab60
        LDA lab38
        MOV E,A
        MVI C,004h
lab61:  DAD D
        DCR C
        JNZ lab61
        RET
lab24:  LXI H,lab27
        MVI M,000h
        LXI D,lab56
        LDA lab35
        CPI 004h
        RZ
        JC lab62
        SUI 004h
        MOV B,A
lab65:  MVI C,004h
        PUSH D
        POP H
lab64:  MOV A,M
        RRC
        JC lab63
        MOV M,A
        INX H
        DCR C
        JNZ lab64
        DCR B
        JNZ lab65
        RET
lab62:  MOV B,A
        MVI A,004h
        SUB B
        MOV B,A
lab67:  MVI C,004h
        PUSH D
        POP H
lab66:  MOV A,M
        RLC
        JC lab63
        MOV M,A
        INX H
        DCR C
        JNZ lab66
        DCR B
        JNZ lab67
        RET
lab63:  LXI H,lab27
        MVI M,001h
        RET
lab26:  LDA lab68
        CPI 000h
        JNZ lab69
        LXI H,lab53
        LXI D,lab51
        MVI C,018h
lab71:  MOV B,M
        LDAX D
        XRA B
        ANA B
        CMP B
        JNZ lab69
        INX H
        INX D
        DCR C
        JNZ lab71
        RET
lab69:  MVI A,001h
        STA lab27
        RET
lab31:  LXI H,lab53
        LXI D,lab51
        MVI C,018h
lab73:  LDAX D
        ORA M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lab73
        RET
        DB 021h, 076h, 004h, 011h, 0B6h, 004h, 00Eh, 018h, 01Ah, 077h
        DB 023h, 013h, 00Dh, 0C2h, 0F3h, 001h, 0C9h
lab25:  LXI H,lab51
        MVI C,019h
        PUSH H
lab74:  MVI M,000h
        INX H
        DCR C
        JNZ lab74
        POP H
        LDA lab36
        MOV E,A
        MVI D,000h
        DAD D
        LXI D,lab56
        MVI C,004h
lab75:  LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lab75
        RET
lab40:  PUSH B
        PUSH PSW
        CALL lab76
        CPI 008h
        JZ lab77
        CPI 018h
        JZ lab78
        CPI 019h
        JZ lab79
        CPI 01Ah
        JZ lab80
        LDA lab37
        INR A
        STA lab36
        JMP lab81
lab77:  LDA lab19
        DCR A
        STA lab35
        JMP lab81
lab78:  LDA lab19
        INR A
        STA lab35
        JMP lab81
lab79:  LDA lab39
        INR A
        CPI 004h
        JNZ lab82
        MVI A,000h
lab82:  STA lab38
        JMP lab81
lab80:  MVI A,001h
        STA lab33
        LDA lab37
        INR A
        STA lab36
        JMP lab83
lab81:  XRA A
        STA lab33
lab83:  POP PSW
        POP B
        RET

lab58:  DB 010h, 010h, 010h, 010h, 000h, 078h, 000h, 000h, 010h, 010h
        DB 010h, 010h, 000h, 078h, 000h, 000h, 000h, 018h, 018h, 000h
        DB 000h, 018h, 018h, 000h, 000h, 018h, 018h, 000h, 000h, 018h
        DB 018h, 000h, 000h, 020h, 038h, 000h, 000h, 018h, 010h, 010h
        DB 000h, 000h, 038h, 008h, 000h, 010h, 010h, 030h, 000h, 010h
        DB 038h, 000h, 000h, 010h, 018h, 010h, 000h, 000h, 038h, 010h
        DB 000h, 010h, 030h, 010h, 000h, 008h, 038h, 000h, 000h, 010h
        DB 010h, 018h, 000h, 000h, 038h, 020h, 000h, 030h, 010h, 010h
        DB 000h, 018h, 030h, 000h, 000h, 010h, 018h, 008h, 000h, 018h
        DB 030h, 000h, 000h, 010h, 018h, 008h, 000h, 030h, 018h, 000h
        DB 000h, 008h, 018h, 010h, 000h, 030h, 018h, 000h, 000h, 008h
        DB 018h, 010h
lab89:  DB 020h, 080h, 02Bh, 060h, 033h, 050h, 030h, 055h, 02Bh, 060h
        DB 033h, 050h, 02Bh, 060h, 030h, 055h, 02Bh, 060h, 022h, 078h
        DB 026h, 06Bh, 0ABh, 080h, 020h, 080h, 02Bh, 060h, 033h, 050h
        DB 030h, 055h, 02Bh, 060h, 033h, 050h, 02Bh, 060h, 030h, 055h
        DB 02Bh, 060h, 020h, 080h, 01Eh, 087h, 098h, 08Fh, 01Dh, 08Fh
        DB 022h, 078h, 028h, 065h, 0FFh, 055h, 01Dh, 08Fh, 022h, 078h
        DB 028h, 065h, 0E4h, 060h, 020h, 080h, 026h, 06Bh, 022h, 078h
        DB 022h, 078h, 020h, 080h, 026h, 06Bh, 022h, 078h, 022h, 078h
        DB 020h, 07Fh, 010h, 0FFh, 014h, 0CAh, 055h, 0BFh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h
lab2:   DB 000h, 070h, 020h, 02Eh, 028h, 00Ch, 0E8h, 04Eh, 040h, 04Eh
        DB 00Ah, 00Eh, 00Ch, 04Ah, 000h, 04Eh, 048h, 04Eh, 042h, 00Eh
        DB 000h, 055h, 0AAh
lab14:  PUSH B
        PUSH H
        PUSH PSW
        MVI A,033h
        LXI H,lab84
        MVI C,0C8h
        CALL lab85
        LXI H,lab86
        MVI C,0C8h
        CALL lab85
        MVI H,0A4h
        CALL lab87
        POP PSW
        POP H
        POP B
        RET
lab87:  MVI B,008h
lab88:  MVI C,008h
        MVI L,0F8h
        CALL lab85
        INR H
        DCR B
        JNZ lab88
        RET
lab85:  MOV M,A
        RLC
        INR L
        DCR C
        JNZ lab85
        RET
lab10:  PUSH H
        PUSH D
        PUSH PSW
        LXI H,lab89
        LXI D,lab90
lab96:  MOV A,M
        CPI 000h
        JZ lab91
        STAX D
        DCX D
        INX H
        MOV A,M
        STAX D
        INX D
        INX H
        CALL lab94
        CALL lab95
        JMP lab96
lab91:  POP PSW
        POP D
        POP H
        RET
lab95:  PUSH B
        MVI B,025h
lab98:  MVI C,0FFh
lab97:  DCR C
        JNZ lab97
        DCR B
        JNZ lab98
        POP B
        RET
lab15:  PUSH H
        LXI H,00000h
        SHLD lab99
        POP H
        RET
lab143: PUSH B
        PUSH PSW
        PUSH H
        LHLD lab99
        MOV A,L
        MVI B,001h
        ADD B
        DAA
        MOV L,A
        JNC lab100
        MOV A,H
        MVI B,001h
        ADD B
        DAA
        MOV H,A
lab100: SHLD lab99
        LXI H,lab101
        SHLD lab102
        LHLD lab99
        MOV A,H
        CALL lab103
        MOV A,L
        CALL lab103
        POP H
        POP PSW
        POP B
        RET

lab99:  DB 023h, 000h
lab103: PUSH PSW
        ANI 0F0h
        RRC
        RRC
        RRC
        RRC
        CALL lab104
        POP PSW
        ANI 00Fh
        CALL lab104
        RET
lab104: ORI 030h
        MOV C,A
        CALL lab0
        RET
lab45:  LXI H,lab105
        MVI C,018h
lab107: MOV A,M
        CPI 0FFh
        JZ lab106
        DCX H
        DCR C
        JNZ lab107
        CALL lab5
        RET
lab106: DCX H
        MOV A,M
        INX H
        MOV M,A
        DCX H
        DCR C
        JNZ lab106
        CALL lab5
        CALL lab108
        JMP lab45
lab108: MVI A,080h
        STA lab109
        MVI A,010h
        STA lab90
        CALL lab94
        RET

lab9:   DB 010h
lab6:   DB 020h, 020h, 073h, 06Bh, 06Fh, 072h, 06Fh, 073h, 074h, 078h
        DB 020h, 028h, 030h, 02Dh, 039h, 029h, 020h, 03Fh, 000h
lab19:  DB 004h
lab37:  DB 000h
lab39:  DB 000h
lab35:  DB 004h
lab36:  DB 000h
lab38:  DB 000h
lab33:  DB 000h
lab29:  DB 001h, 000h
lab27:  DB 001h
lab30:  DB 000h, 0D1h, 0E1h, 0C9h, 0E5h, 0C5h, 00Eh, 008h
lab11:  DB 000h
lab1:   DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
        DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
        DB 0FBh, 0E6h, 0ECh
lab105: DB 03Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab53:  DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
        DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
        DB 0FBh, 0E6h, 0ECh, 03Fh
lab13:  DB 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab51:  DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lab68:  DB 000h, 000h, 000h, 072h, 06Fh, 000h, 000h, 000h
lab56:  DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
lab5:   PUSH H
        PUSH D
        PUSH B
        PUSH PSW
        LXI H,lab110
        LXI D,lab1
        MVI C,018h
lab113: LDAX D
        CALL lab112
        INX D
        INX H
        INX H
        INX H
        INX H
        INX H
        INX H
        INX H
        INX H
        DCR C
        JNZ lab113
        POP PSW
        POP B
        POP D
        POP H
        RET
lab112: PUSH H
        PUSH B
        MVI C,008h
lab117: RLC
        JC lab114
        CALL lab115
lab119: INR H
        DCR C
        JZ lab116
        JMP lab117
lab114: CALL lab118
        JMP lab119
lab116: POP B
        POP H
        RET
lab118: PUSH H
        MVI M,07Fh
        INX H
        MVI M,06Bh
        INX H
        MVI M,055h
        INX H
        MVI M,06Bh
        INX H
        MVI M,055h
        INX H
        MVI M,06Bh
        INX H
        MVI M,07Fh
        INX H
        MVI M,000h
        POP H
        RET
lab115: PUSH H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        MVI M,000h
        INX H
        POP H
        RET
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h
lab8:   CALL lab120
        CPI 030h
        JC lab8
        CPI 03Ah
        JNC lab8
        INR A
        MVI C,01Fh
        CALL lab0
        RET
lab34:  LDA lab36
        ADI 004h
        RAL
        RAL
        STA lab109
        MVI A,008h
        STA lab90
        CALL lab94
        CALL lab121
        RET
        DB 000h, 000h, 000h, 000h, 000h, 000h, 036h, 032h, 034h, 030h
        DB 039h, 030h, 020h, 020h, 020h, 020h, 073h, 077h, 065h, 072h
        DB 064h, 06Ch, 06Fh, 077h, 073h, 06Bh, 061h, 071h, 009h, 06Fh
        DB 062h, 06Ch, 02Eh, 067h, 02Eh, 070h, 06Fh, 06Ch, 065h, 077h
        DB 073h, 06Bh, 06Fh, 06Ah, 020h, 032h, 02Dh, 06Ah, 020h, 06Dh
        DB 02Fh, 072h, 02Dh, 06Eh, 020h, 032h, 02Ch, 032h, 077h, 079h
        DB 07Eh, 065h, 067h, 076h, 061h, 06Eh, 069h, 06Eh, 020h, 061h
        DB 02Eh, 06Ch, 02Eh, 020h, 074h, 065h, 06Ch, 020h, 032h, 020h
        DB 030h, 035h, 020h, 031h, 033h, 000h
lab16:  CALL lab122
        LDA lab123
        ANI 007h
        CPI 007h
        JZ lab16
        STA lab59
        RET
lab122: LHLD lab123
        MVI C,010h
lab125: MOV A,H
        DAD H
        ANI 060h
        JPE lab124
        INX H
lab124: DCR C
        JNZ lab125
        SHLD lab123
        RET

lab123: DB 061h, 08Bh
lab59:  DB 001h
lab134: MVI A,082h
lab127: STA lab126
        RET
lab128: MVI A,091h
        JMP lab127
lab76:  PUSH B
        PUSH D
        PUSH H
        LXI H,006A7h
        LXI D,0000Ch
        CALL lab128
        MVI C,001h
        LDA lab129
        RLC
        RLC
        RLC
        RLC
        MVI B,004h
lab131: INX H
        RLC
        JNC lab130
        DCR B
        JNZ lab131
        LDA lab132
        MVI B,008h
        DCR C
        JZ lab131
        JMP lab133
lab130: CALL lab134
        LDA lab135
        RLC
        MVI B,005h
lab137: DAD D
        RLC
        JNC lab136
        DCR B
        JNZ lab137
lab133: CALL lab134
        MVI A,0FFh
        JMP lab138
lab136: LDA lab135
        ANI 002h
        RRC
        MOV E,A
        MOV A,M
        CPI 021h
        JC lab138
        CPI 080h
        JNC lab138
        DCR E
        JNZ lab139
        CPI 040h
        JC lab138
        LDA lab140
        ADD M
        JMP lab138
lab139: CPI 040h
        JC lab141
        ANI 01Fh
        JMP lab138
lab141: XRI 010h
lab138: POP H
        POP D
        POP B
        RET
        DB 0CDh, 034h, 006h, 03Ch, 0C8h, 03Eh, 0FFh, 0C9h, 03Bh, 031h
        DB 032h, 033h, 034h, 035h, 036h, 037h, 038h, 039h, 030h, 02Dh
        DB 04Ah, 043h, 055h, 04Bh, 045h, 04Eh, 047h, 05Bh, 05Dh, 05Ah
        DB 048h, 03Ah, 046h, 059h, 057h, 041h, 050h, 052h, 04Fh, 04Ch
        DB 044h, 056h, 05Ch, 02Eh, 051h, 05Eh, 053h, 04Dh, 049h, 054h
        DB 058h, 042h, 040h, 02Ch, 02Fh, 05Fh, 081h, 00Ch, 019h, 01Ah
        DB 08Bh, 08Ch, 020h, 008h, 080h, 018h, 01Fh, 00Dh
lab17:  LXI H,lab142
        XRA A
        CALL lab112
        JMP lab143
        DB 0E6h, 0D0h, 000h, 000h, 000h, 000h
END