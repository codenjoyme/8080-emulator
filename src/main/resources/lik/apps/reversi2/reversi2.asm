        CPU  8080
        .ORG 00000h
lab46   EQU 09000h
lab58   EQU 02000h
lab64   EQU 01000h
lab37   EQU 0121Dh
lab33   EQU 01423h
lab16   EQU 0C427h
lab87   EQU 02033h
lab86   EQU 02034h
lab1    EQU 0C037h
lab50   EQU 0C438h
lab85   EQU 02043h
lab88   EQU 02044h
lab54   EQU 04450h
lab65   EQU 04C50h
lab81   EQU 0905Bh
lab223  EQU 09660h
lab68   EQU 05673h
lab228  EQU 02078h
lab83   EQU 02080h
lab224  EQU 0AAA0h
lab202  EQU 0CAB7h
lab141  EQU 0B4C3h
lab77   EQU 090FFh
lab177  EQU 0FF00h
lab176  EQU 0FF01h
lab178  EQU 0FF02h
lab175  EQU 0FF03h
lab10   EQU 02313h
lab39   EQU 0D514h
lab79   EQU 0851Bh
lab38   EQU 0AF1Ch
lab90   EQU 09534h
lab226  EQU 09766h
lab218  EQU 0C170h
lab92   EQU 09574h
lab143  EQU 02377h
lab139  EQU 01F80h
lab236  EQU 02388h
lab238  EQU 02392h
lab227  EQU 0A99Ah
lab162  EQU 077B6h
lab48   EQU 03DC0h
lab2    EQU 08FE1h
lab217  EQU 08FF1h
lab216  EQU 08FF2h
lab49   EQU 08FFCh
lab0    EQU 03FFFh
lab45   EQU 0FFFFh
        LXI SP,lab0
        MVI C,01Fh
        CALL lab1
        LXI H,00000h
        SHLD lab2
        JMP lab3
lab13:  PUSH H
        PUSH D
        MVI E,000h
lab7:   MOV A,C
        RAL
        MOV C,A
        JC lab4
        LXI H,lab5
lab9:   CALL lab6
        INR E
        MOV A,E
        CPI 008h
        JNZ lab7
        POP D
        POP H
        RET
lab4:   LXI H,lab8
        JMP lab9
lab6:   PUSH D
        MOV A,E
        RLC
        RLC
        RLC
        ADI 060h
        MOV E,A
        MVI A,090h
        ADD D
        MOV D,A
        MVI B,008h
lab11:  MOV A,M
        STAX D
        INX D
        INX H
        DCR B
        JNZ lab11
        POP D
        RET
lab23:  PUSH H
        PUSH D
        LXI H,lab12
        MVI D,000h
lab14:  MOV C,M
        CALL lab13
        INX H
        INR D
        MOV A,D
        CPI 030h
        JNZ lab14
        POP D
        POP H
        RET
lab19:  LXI H,lab12
        LXI D,lab15
lab17:  MVI M,000h
        INX H
        CALL lab16
        JNZ lab17
        RET
lab22:  PUSH H
        PUSH D
        LXI H,lab12
        LXI D,lab15
lab18:  INX H
        MOV A,M
        DCX H
        MOV M,A
        INX H
        CALL lab16
        JNZ lab18
        POP D
        POP H
        RET
lab44:  CALL lab19
        LXI H,lab20
        LXI D,lab21
lab24:  MOV A,M
        STA lab15
        CALL lab22
        CALL lab23
        INX H
        CALL lab16
        JNZ lab24
        RET
lab42:  MVI C,080h
lab27:  MOV L,C
        CALL lab25
        MOV A,C
        CMA
        MOV L,A
        CALL lab25
        LXI D,00100h
        CALL lab26
        INR C
        JNZ lab27
        RET
lab25:  MVI H,090h
lab30:  MOV A,B
        ORA A
        CZ lab28
        CNZ lab29
        INR H
        MOV A,H
        CPI 0C0h
        JNZ lab30
        RET
lab28:  XRA A
        MOV M,A
        RET
lab29:  MOV A,M
        CMA
        MOV M,A
        RET
lab26:  PUSH H
        LXI H,00000h
lab31:  INX H
        CALL lab16
        JNZ lab31
        POP H
        RET
lab43:  LXI H,lab32
        MVI B,010h
lab36:  MVI C,01Ch
        MVI D,09Ah
        MVI A,0F8h
        SUB B
        MOV E,A
lab34:  MOV A,M
        STAX D
        INX H
        INR D
        DCR C
        JNZ lab34
        CALL lab35
        DCR B
        JNZ lab36
        RET
lab35:  PUSH B
        MVI B,0C8h
lab41:  MVI D,09Ah
        MVI C,01Ch
lab40:  LDAX D
        DCR E
        STAX D
        INR E
        XRA A
        STAX D
        INR D
        PUSH D
        LXI D,00001h
        CALL lab26
        POP D
        DCR C
        JNZ lab40
        DCR E
        DCR B
        JNZ lab41
        POP B
        RET
lab3:   MVI B,000h
        CALL lab42
        CALL lab43
        CALL lab44
        LXI D,lab45
        CALL lab26
        MVI B,005h
lab47:  CALL lab42
        LXI D,lab46
        CALL lab26
        DCR B
        JNZ lab47
        LXI H,lab48
        SHLD lab49
        LXI H,lab21
        CALL lab50
        LXI D,lab45
lab53:  CALL lab51
        ORA A
        JNZ lab52
        PUSH D
        LXI D,00400h
        CALL lab26
        POP D
        DCX D
        MOV A,D
        CMP E
        JNZ lab53
        XRA A
        MOV B,A
        CALL lab42
        JMP lab3

lab5:   DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
lab8:   DB 000h, 03Ch, 07Eh, 07Eh, 07Eh, 07Eh, 03Ch, 000h
lab12:  DB 0D3h, 008h, 03Ah, 097h, 00Bh, 0FEh, 001h, 0CAh, 089h, 005h
        DB 03Ah, 097h, 00Bh, 04Fh, 006h, 000h, 0AFh, 0CDh, 080h, 009h
        DB 0B7h, 0C2h, 061h, 005h, 004h, 078h, 0E6h, 00Fh, 0FEh, 008h
        DB 0C2h, 037h, 005h, 03Eh, 010h, 080h, 0E6h, 0F0h, 047h, 0FEh
        DB 080h, 0DAh, 037h, 005h, 03Ah, 097h, 00Bh, 0B9h
lab15:  DB 000h
lab20:  DB 000h, 000h, 000h, 000h, 07Ch, 050h, 050h, 020h, 000h, 000h
        DB 07Ch, 054h, 054h, 054h, 000h, 000h, 07Ch, 054h, 054h, 028h
        DB 000h, 000h, 07Ch, 054h, 054h, 054h, 000h, 000h, 07Ch, 050h
        DB 050h, 020h, 000h, 000h, 038h, 044h, 044h, 028h, 000h, 000h
        DB 07Ch, 008h, 010h, 07Ch, 000h, 000h, 000h, 000h
lab21:  DB 020h, 020h, 020h, 06Eh, 061h, 076h, 06Dh, 069h, 074h, 065h
        DB 020h, 022h, 070h, 072h, 06Fh, 062h, 065h, 06Ch, 022h, 021h
        DB 000h, 020h, 021h, 000h
lab32:  DB 0E0h, 01Ch, 0FFh, 0FCh, 0FFh, 0E0h, 0E0h, 01Ch, 03Fh, 0F0h
        DB 0FFh, 0F0h, 0E0h, 070h, 0E0h, 01Ch, 000h, 000h, 003h, 080h
        DB 03Fh, 0F0h, 03Fh, 0F0h, 03Fh, 0F0h, 000h, 000h, 0E0h, 01Ch
        DB 0FFh, 0FCh, 0FFh, 0F8h, 0E0h, 01Ch, 07Fh, 0F8h, 0FFh, 0F8h
        DB 0E0h, 070h, 0E0h, 01Ch, 000h, 000h, 007h, 080h, 07Fh, 0F8h
        DB 07Fh, 0F8h, 07Fh, 0F8h, 000h, 000h, 0E0h, 01Ch, 0FFh, 0FCh
        DB 0FFh, 0F8h, 0E0h, 01Ch, 0FFh, 0FCh, 0FFh, 0FCh, 0E0h, 070h
        DB 0E0h, 01Ch, 000h, 000h, 00Fh, 080h, 0FFh, 0FCh, 0FFh, 0FCh
        DB 0FFh, 0FCh, 000h, 000h, 0E0h, 01Ch, 0E0h, 000h, 0E0h, 03Ch
        DB 0E0h, 01Ch, 0F0h, 03Ch, 0E0h, 03Ch, 0E0h, 070h, 0E0h, 01Ch
        DB 000h, 000h, 01Fh, 080h, 0F0h, 03Ch, 0E0h, 01Ch, 0E0h, 01Ch
        DB 000h, 000h, 0E0h, 01Ch, 0E0h, 000h, 0E0h, 01Ch, 0E0h, 01Ch
        DB 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 070h, 0E0h, 01Ch, 000h, 000h
        DB 03Fh, 080h, 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 01Ch, 000h, 000h
        DB 0E0h, 01Ch, 0E0h, 000h, 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 01Ch
        DB 0E0h, 01Ch, 0E0h, 070h, 0E0h, 01Ch, 000h, 000h, 07Fh, 080h
        DB 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 01Ch, 000h, 000h, 0E0h, 01Ch
        DB 0E0h, 000h, 0E0h, 03Ch, 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 038h
        DB 0E0h, 070h, 0E0h, 01Ch, 001h, 080h, 003h, 080h, 0F0h, 03Ch
        DB 070h, 038h, 070h, 038h, 00Fh, 0E0h, 0FFh, 0FCh, 0FFh, 0C0h
        DB 0FFh, 0F8h, 0FFh, 0FCh, 0E0h, 01Ch, 0FFh, 0F0h, 0E0h, 070h
        DB 0FFh, 01Ch, 003h, 0C0h, 003h, 080h, 0FFh, 0FCh, 03Fh, 0F0h
        DB 03Fh, 0F0h, 00Fh, 0E0h, 07Fh, 0FCh, 0FFh, 0C0h, 0FFh, 0F8h
        DB 0FFh, 0FCh, 0E0h, 01Ch, 0FFh, 0F8h, 0E0h, 070h, 0FFh, 09Ch
        DB 007h, 0E0h, 003h, 080h, 07Fh, 0FCh, 03Fh, 0F0h, 03Fh, 0F0h
        DB 00Ch, 000h, 03Fh, 0FCh, 0E0h, 000h, 0FFh, 0E0h, 0FFh, 0FCh
        DB 0E0h, 01Ch, 0E0h, 03Ch, 0E0h, 070h, 0FFh, 0DCh, 007h, 0E0h
        DB 003h, 080h, 03Fh, 0FCh, 070h, 038h, 070h, 038h, 00Ch, 000h
        DB 000h, 01Ch, 0E0h, 000h, 0E0h, 000h, 0E0h, 01Ch, 0E0h, 01Ch
        DB 0E0h, 01Ch, 0E0h, 070h, 0E3h, 0DCh, 003h, 0E0h, 003h, 080h
        DB 000h, 01Ch, 0E0h, 01Ch, 0E0h, 01Ch, 00Ch, 000h, 000h, 01Ch
        DB 0E0h, 000h, 0E0h, 000h, 0E0h, 01Ch, 0E0h, 01Ch, 0E0h, 01Ch
        DB 0E0h, 070h, 0E1h, 0DCh, 001h, 0E0h, 003h, 080h, 000h, 01Ch
        DB 0E0h, 01Ch, 0E0h, 01Ch, 00Ch, 00Eh, 000h, 01Ch, 0E0h, 000h
        DB 0E0h, 000h, 0E0h, 01Ch, 0F0h, 03Ch, 0E0h, 03Ch, 0E0h, 070h
        DB 0E3h, 0DCh, 000h, 060h, 003h, 080h, 0E0h, 038h, 0E0h, 01Ch
        DB 0E0h, 01Ch, 00Ch, 01Fh, 000h, 01Ch, 0FFh, 0FCh, 0E0h, 000h
        DB 0E0h, 01Ch, 0FFh, 0FCh, 0FFh, 0FCh, 0FFh, 0FCh, 0FFh, 0DCh
        DB 000h, 0C0h, 07Fh, 0FCh, 0FFh, 0F0h, 0FFh, 0FCh, 0FFh, 0FCh
        DB 00Ch, 01Fh, 000h, 01Ch, 0FFh, 0FCh, 0E0h, 000h, 0E0h, 01Ch
        DB 07Fh, 0FCh, 0FFh, 0F8h, 0FFh, 0FCh, 0FFh, 09Ch, 001h, 080h
        DB 07Fh, 0FCh, 07Fh, 0E0h, 07Fh, 0F8h, 07Fh, 0F8h, 00Ch, 01Fh
        DB 000h, 01Ch, 0FFh, 0FCh, 0E0h, 000h, 0E0h, 01Ch, 03Fh, 0F0h
        DB 0FFh, 0F0h, 0FFh, 0FCh, 0FFh, 01Ch, 000h, 000h, 03Fh, 0FCh
        DB 03Fh, 0C0h, 03Fh, 0F0h, 03Fh, 0F0h, 00Ch, 00Eh
lab52:  MVI B,000h
        CALL lab42
        LXI H,lab54
        SHLD lab49
        LXI H,lab55
        CALL lab50
        MVI B,053h
        MVI A,001h
        CALL lab56
        CALL lab57
        MVI B,054h
        MVI A,002h
        CALL lab56
        CALL lab57
        MVI C,001h
lab61:  MVI A,052h
        ADD C
        MOV B,A
        XRA A
        PUSH B
        CALL lab56
        LXI D,lab58
        CALL lab59
        POP B
        PUSH B
        MOV A,C
        CALL lab56
        CALL lab57
        LXI D,lab58
        CALL lab59
        POP B
        CALL lab51
        CPI 020h
        JZ lab60
        ORA A
        JZ lab61
        MVI A,003h
        SUB C
        MOV C,A
        JMP lab61
lab60:  MOV A,C
        STA lab62
        MVI A,003h
        SUB C
        STA lab63
        LXI D,lab64
        CALL lab59
        MVI B,000h
        CALL lab42
        LXI H,lab65
        SHLD lab49
        LXI H,lab66
        CALL lab50
        MVI C,003h
        MVI B,053h
lab67:  PUSH B
        MVI A,001h
        CALL lab56
        CALL lab57
        POP B
        INR B
        DCR C
        JNZ lab67
        LXI H,lab68
        LXI D,00020h
        MVI C,031h
lab69:  SHLD lab49
        CALL lab1
        DAD D
        INR C
        MOV A,C
        CPI 034h
        JNZ lab69
lab73:  MVI B,053h
lab71:  PUSH B
        CALL lab70
        LXI D,lab58
        CALL lab59
        POP B
        CALL lab51
        ORA A
        JZ lab71
        CPI 020h
        JZ lab72
        INR B
        MOV A,B
        CPI 056h
        JNZ lab71
        JMP lab73
lab72:  MOV A,B
        SUI 053h
        STA lab74
        MVI B,000h
        CALL lab42
        LXI H,lab46
        LXI B,00100h
        MVI E,0FFh
lab76:  PUSH H
        MVI D,020h
        CALL lab75
        POP H
        MOV A,L
        ADI 020h
        MOV L,A
        JNC lab76
        LXI H,lab77
        MVI D,020h
        CALL lab75
        LXI H,lab46
        LXI B,00001h
        MVI E,080h
lab78:  PUSH H
        MVI D,0FFh
        CALL lab75
        POP H
        MOV A,H
        ADI 004h
        MOV H,A
        CPI 0B4h
        JNZ lab78
        LDA lab63
        MVI B,091h
        CALL lab56
        CALL lab57
        MVI B,093h
        LDA lab62
        CALL lab56
        CALL lab57
        LXI H,lab79
        SHLD lab49
        LXI H,lab80
        CALL lab50
        LXI H,lab81
        SHLD lab49
        LXI H,lab82
        CALL lab50
        LXI H,lab58
        LXI D,lab83
lab84:  MVI M,000h
        INX H
        CALL lab16
        JNZ lab84
        MVI A,001h
        STA lab85
        STA lab86
        INR A
        STA lab87
        STA lab88
        CALL lab89
        LXI H,lab90
        SHLD lab49
        LXI H,lab91
        CALL lab50
        LXI H,lab92
        SHLD lab49
        LXI H,lab91
        MVI A,002h
        STA lab93
        STA lab94
        CALL lab50
        CALL lab95
        LDA lab62
        CPI 001h
        JZ lab96
lab123: LDA lab62
        MOV C,A
lab101: MVI B,000h
lab99:  XRA A
        CALL lab97
        ORA A
        JNZ lab98
        INR B
        MOV A,B
        ANI 00Fh
        CPI 008h
        JNZ lab99
        MVI A,010h
        ADD B
        ANI 0F0h
        MOV B,A
        CPI 080h
        JC lab99
        LDA lab62
        CMP C
        JNZ lab100
        LDA lab63
        MOV C,A
        JMP lab101
lab98:  LDA lab63
        CMP C
        JZ lab96
        CALL lab102
        LDA lab62
        MOV C,A
        XRA A
        CALL lab97
        ORA A
        JZ lab103
        CALL lab97
        CALL lab104
        CALL lab105
        CALL lab89
        CALL lab95
        JMP lab96
lab96:  LDA lab63
        MOV C,A
        MVI A,005h
        STA lab106
        XRA A
        STA lab107
        LXI H,lab108
lab122: MVI A,004h
        STA lab109
        XRA A
        STA lab110
        STA lab111
lab120: MOV A,M
        STA lab112
        STA lab113
        INX H
        MOV A,M
        STA lab114
        INX H
        CALL lab115
        LDA lab112
lab119: MOV B,A
        XRA A
        PUSH H
        CALL lab97
        POP H
        ORA A
        JNZ lab116
lab125: LDA lab114
        MOV E,A
        LDA lab112
        CMP E
        JZ lab117
        MOV E,A
        LDA lab118
        ADD E
        STA lab112
        JMP lab119
lab117: LDA lab109
        DCR A
        STA lab109
        JNZ lab120
        LDA lab110
        ORA A
        JNZ lab121
        LDA lab106
        DCR A
        STA lab106
        JNZ lab122
        LDA lab107
        ORA A
        JZ lab123
        MOV B,A
        JMP lab124
lab116: MOV E,A
        LDA lab74
        ORA A
        JZ lab124
        LDA lab110
        CMP E
        JNC lab125
        LDA lab74
        DCR A
        JNZ lab126
lab127: MOV A,E
        STA lab110
        MOV A,B
        STA lab111
        JMP lab125
lab126: LDA lab106
        CPI 004h
        JNZ lab127
        MOV A,B
        STA lab107
        CALL lab128
        ORA A
        JZ lab125
        JMP lab127
lab121: LDA lab111
        MOV B,A
lab124: MVI A,001h
        CALL lab97
        CALL lab104
        CALL lab105
        CALL lab89
        CALL lab95
        JMP lab123
lab115: LDA lab112
        MOV E,A
        LDA lab114
        SUB E
        CPI 010h
        JC lab129
        MVI A,010h
lab130: STA lab118
        RET
lab129: MVI A,001h
        JMP lab130
lab128: PUSH H
        CALL lab131
        MVI A,001h
        CALL lab97
        CALL lab104
        CALL lab132
        MOV A,D
        STA lab133
        MOV A,E
        STA lab134
        CALL lab135
        CALL lab132
        CALL lab136
        POP H
        LDA lab133
        CMP D
        JZ lab137
        JNC lab138
lab137: LDA lab134
        CMP E
        JNZ lab138
        MVI A,001h
        RET
lab138: XRA A
        RET
lab131: PUSH H
        PUSH D
        PUSH B
        MVI C,001h
lab145: LXI H,lab58
        LXI D,lab139
lab144: MOV A,C
        ORA A
        JZ lab140
        MOV A,M
        STAX D
        JMP lab142
lab140: LDAX D
        MOV M,A
lab142: INX H
        INX D
        MOV A,L
        CPI 080h
        JC lab144
        POP B
        POP D
        POP H
        RET
lab136: PUSH H
        PUSH D
        PUSH B
        MVI C,000h
        JMP lab145
lab132: PUSH H
        PUSH B
        XRA A
        MOV D,A
        MOV E,A
        LXI H,lab58
        LDA lab113
        MOV L,A
        LDA lab114
        MOV C,A
lab148: LDA lab63
        CMP M
        CZ lab146
        MOV A,L
        CMP C
        JZ lab147
        LDA lab118
        ADD L
        MOV L,A
        JMP lab148
lab146: INR D
        RET
lab147: LDA lab118
        MOV C,A
        LDA lab113
        SUB C
        CALL lab149
        LDA lab114
        ADD C
        CALL lab149
        POP B
        POP H
        RET
lab149: MOV L,A
        MOV A,M
        RZ
        INR E
        RET
lab135: PUSH H
        PUSH D
        PUSH B
        LDA lab118
        MOV D,A
        LDA lab62
        MOV C,A
        LDA lab113
        SUB D
        CALL lab150
        LDA lab114
        ADD D
        CALL lab150
        XRA A
        STA lab151
        STA lab152
        LDA lab113
        MOV E,A
lab155: CALL lab153
        LDA lab114
        CMP E
        JZ lab154
        MOV A,D
        ADD E
        MOV E,A
        JMP lab155
lab154: LDA lab152
        ORA A
        JNZ lab156
lab158: POP B
        POP D
        POP H
        RET
lab156: LDA lab151
        CALL lab150
lab150: PUSH D
        CALL lab157
        POP D
        ORA A
        RZ
        MVI A,001h
        CALL lab97
        CALL lab104
        POP H
        JMP lab158
lab157: MOV B,A
        XRA A
        PUSH D
        CALL lab97
        POP D
        RET
lab153: CALL lab157
        ORA A
        RZ
        MOV H,A
        LDA lab152
        CMP H
        RNC
        MOV A,H
        STA lab152
        MOV A,B
        STA lab151
        RET

lab107: DB 082h
lab152: DB 082h
lab151: DB 082h
lab106: DB 082h
lab109: DB 082h
lab110: DB 082h
lab111: DB 082h
lab112: DB 082h
lab113: DB 082h
lab114: DB 082h
lab118: DB 082h
lab134: DB 082h
lab133: DB 082h
lab108: DB 000h, 000h, 007h, 007h, 077h, 077h, 070h, 070h, 001h, 006h
        DB 010h, 060h, 071h, 076h, 017h, 067h, 022h, 025h, 035h, 045h
        DB 052h, 055h, 032h, 042h, 012h, 015h, 026h, 056h, 021h, 051h
        DB 062h, 065h, 011h, 011h, 016h, 016h, 066h, 066h, 061h, 061h
lab91:  DB 030h, 032h, 000h
lab75:  MOV A,M
        ORA E
        MOV M,A
        DAD B
        DCR D
        JNZ lab75
        RET
lab160: MVI C,0A6h
        RET
lab56:  PUSH B
        PUSH H
        CALL lab159
        INX H
        MVI C,000h
        ORA A
        CZ lab160
        MOV A,C
        STA lab161
        MOV A,H
        ADI 003h
        MOV C,A
lab165: PUSH H
        MVI B,01Eh
lab163: LDAX D
lab161: ORA M
        MOV M,A
        INR L
        INX D
        DCR B
        JNZ lab163
        POP H
        MOV A,H
        CMP C
        JZ lab164
        INR H
        JMP lab165
lab164: POP H
        POP B
        RET
lab159: PUSH PSW
        LXI D,lab166
        DCR A
        CZ lab167
        DCR A
        CZ lab168
        MOV A,B
        ANI 00Fh
        RRC
        RRC
        RRC
        MOV L,A
        MOV A,B
        ANI 0F0h
        RRC
        RRC
        ADI 090h
        MOV H,A
        POP PSW
        RET
lab167: LXI D,lab169
        RET
lab168: LXI D,lab170
        RET
lab70:  CALL lab159
        PUSH H
        CALL lab171
        LXI D,lab64
        CALL lab59
        POP H
        CALL lab171
        RET
lab171: MVI A,003h
        ADD H
        MOV D,A
lab173: MVI C,020h
        PUSH H
lab172: MOV A,M
        CMA
        MOV M,A
        INR L
        DCR C
        JNZ lab172
        POP H
        MOV A,H
        CMP D
        RZ
        INR H
        JMP lab173
lab59:  LXI H,00000h
lab174: INX H
        CALL lab16
        JNZ lab174
        RET
lab51:  PUSH B
        MVI A,091h
        STA lab175
        MVI A,0FBh
        STA lab176
        LDA lab177
        ANI 03Fh
        MOV B,A
        LDA lab178
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
lab102: MVI B,000h
lab187: CALL lab51
        CPI 004h
        JZ lab179
        CPI 001h
        JZ lab52
        CPI 010h
        JZ lab180
        CPI 040h
        JZ lab181
        CPI 080h
        JZ lab182
        CPI 020h
        RZ
        JMP lab183
lab103: CALL lab184
        JMP lab98
lab184: PUSH H
        LXI H,lab185
        JMP lab186
lab183: PUSH B
        CALL lab70
        LXI D,lab64
        CALL lab59
        POP B
        JMP lab187
lab180: MOV A,B
        ANI 0F0h
        JZ lab183
        MOV A,B
        SUI 010h
        MOV B,A
        JMP lab183
lab179: MOV A,B
        ANI 0F0h
        CPI 070h
        JZ lab183
        MOV A,B
        ADI 010h
        MOV B,A
        JMP lab183
lab181: MOV A,B
        ANI 00Fh
        CPI 007h
        JZ lab183
        INR B
        JMP lab183
lab182: MOV A,B
        ANI 00Fh
        JZ lab183
        DCR B
        JMP lab183
lab95:  LDA lab93
        LXI H,lab90
        SHLD lab49
        CALL lab188
        LDA lab94
        LXI H,lab92
        SHLD lab49
        CALL lab188
        LXI H,lab93
        XRA A
        MOV M,A
        INX H
        MOV M,A
        LXI H,lab58
        LXI D,lab83
lab192: MOV A,M
        PUSH H
        ORA A
        JZ lab189
        MOV H,A
        LDA lab62
        CMP H
        JZ lab190
        JMP lab191
lab189: POP H
        INX H
        CALL lab16
        JNZ lab192
        LDA lab93
        LXI H,lab90
        SHLD lab49
        CALL lab188
        LDA lab94
        LXI H,lab92
        SHLD lab49
        JMP lab188
lab191: LXI H,lab93
        JMP lab193
lab190: LXI H,lab94
        JMP lab193
lab188: PUSH PSW
        ANI 0F0h
        RRC
        RRC
        RRC
        RRC
        CALL lab194
        POP PSW
        ANI 00Fh
lab194: ADI 030h
        MOV C,A
        JMP lab1
lab193: CALL lab195
        JMP lab189
lab195: MOV A,M
        INR A
        MOV M,A
        ANI 00Fh
        CPI 00Ah
        RC
        MOV A,M
        ADI 010h
        ANI 0F0h
        MOV M,A
        RET
lab89:  LXI H,lab58
lab197: MOV A,M
        ORA A
        JZ lab196
        MOV B,L
        PUSH H
        CALL lab56
        POP H
lab196: INR L
        MOV A,L
        ANI 00Fh
        CPI 008h
        JC lab197
        MOV A,L
        ANI 0F0h
        ADI 010h
        MOV L,A
        CPI 080h
        JC lab197
        RET
lab97:  STA lab198
        XRA A
        STA lab199
        LXI H,lab58
        MOV A,L
        ADD B
        MOV L,A
        MOV A,M
        ORA A
        JNZ lab200
        LXI D,lab201
lab210: LDAX D
        ORA A
        JZ lab200
        STA lab203
        PUSH H
        XRA A
        STA lab204
lab208: LDA lab203
        ADD L
        MOV L,A
        CALL lab205
        JNC lab206
        MOV A,M
        ORA A
        JZ lab206
        CMP C
        JZ lab207
        LDA lab204
        INR A
        STA lab204
        JMP lab208
lab207: LXI H,lab199
        LDA lab204
        ADD M
        MOV M,A
        POP H
        LDA lab198
        ORA A
        CNZ lab209
        INX D
        JMP lab210
lab206: POP H
        INX D
        JMP lab210
lab205: CPI 080h
        RNC
        ANI 00Fh
        CPI 008h
        RET
lab209: LDA lab204
        ORA A
        RZ
        PUSH H
        PUSH D
        MOV D,A
lab211: LDA lab203
        ADD L
        MOV L,A
        MOV M,C
        DCR D
        JNZ lab211
        POP D
        POP H
        RET
lab200: LDA lab199
        RET

lab201: DB 001h, 0FFh, 010h, 0F0h, 00Fh, 011h, 0F1h, 0EFh, 000h
lab198: DB 082h
lab199: DB 082h
lab203: DB 082h
lab204: DB 082h
lab104: LXI H,lab58
        MOV A,B
        ADD L
        MOV L,A
        MOV M,C
        RET
lab105: PUSH B
        MOV A,C
        CALL lab56
        POP B
        CALL lab212
        LXI D,lab58
        CALL lab59
        RET
lab212: PUSH H
        LXI H,lab213
        LDA lab62
        CMP C
        JZ lab186
        LXI H,lab214
        JMP lab186
lab186: MOV A,M
        ORA A
        JZ lab215
        STA lab216
lab219: INX H
        MOV A,M
        ORA A
        JZ lab215
        STA lab217
        CALL lab218
        JMP lab219
lab215: POP H
        RET
lab222: PUSH H
        LXI H,lab220
        JMP lab186
        DB 0E5h, 021h, 055h, 00Bh, 0C3h, 030h, 00Ah
lab225: MOV B,L
lab221: MOV M,C
        INR L
        MOV A,L
        CMP E
        JNZ lab221
        INR H
        MOV L,B
        MOV A,H
        CMP D
        JNZ lab221
        RET
lab100: CALL lab222
        LXI H,lab223
        LXI D,lab224
        MVI C,0FFh
        CALL lab225
        LXI H,lab226
        LXI D,lab227
        MVI C,000h
        CALL lab225
        LXI H,lab228
        SHLD lab49
        LDA lab93
        MOV C,A
        LDA lab94
        SUB C
        LXI H,lab229
        JZ lab230
        JC lab231
lab234: CALL lab50
        JMP lab232
lab230: LXI H,lab233
        JMP lab234
lab231: LXI H,lab235
        JMP lab234
lab232: LXI H,lab236
        SHLD lab49
        LXI H,lab237
        CALL lab50
        LXI H,lab238
        SHLD lab49
        LXI H,lab239
        CALL lab50
lab240: CALL lab51
        ORA A
        JZ lab240
        JMP lab3
lab57:  PUSH B
        PUSH H
        CALL lab159
        INX H
        MVI C,01Eh
lab241: MVI A,07Fh
        ANA M
        MOV M,A
        INX H
        DCR C
        JNZ lab241
        POP H
        POP B
        RET

lab233: DB 020h, 020h, 020h, 020h, 020h, 06Eh, 069h, 07Eh, 078h, 071h
        DB 020h, 021h, 000h
lab229: DB 070h, 06Fh, 07Ah, 064h, 072h, 061h, 077h, 06Ch, 071h, 060h
        DB 020h, 073h, 020h, 070h, 06Fh, 062h, 065h, 064h, 06Fh, 06Ah
        DB 020h, 021h, 000h
lab235: DB 020h, 020h, 020h, 020h, 077h, 079h, 020h, 070h, 072h, 06Fh
        DB 069h, 067h, 072h, 061h, 06Ch, 069h, 020h, 021h, 000h
lab237: DB 064h, 06Ch, 071h, 020h, 070h, 072h, 06Fh, 064h, 06Fh, 06Ch
        DB 076h, 065h, 06Eh, 069h, 071h, 020h, 069h, 067h, 072h, 079h
        DB 000h
lab239: DB 020h, 020h, 06Eh, 061h, 076h, 06Dh, 069h, 074h, 065h, 020h
        DB 022h, 070h, 072h, 06Fh, 062h, 065h, 06Ch, 022h, 000h
lab213: DB 028h, 083h, 075h, 067h, 061h, 056h, 000h
lab214: DB 028h, 056h, 061h, 067h, 075h, 083h, 000h
lab185: DB 03Ch, 075h, 083h, 094h, 09Dh, 0B0h, 0C8h, 0DFh, 000h
lab220: DB 050h, 0FBh, 0C8h, 0A6h, 07Ch, 061h, 051h, 03Ch, 03Ch, 03Ch
        DB 03Ch, 061h, 051h, 03Ch, 051h, 061h, 051h, 0A6h, 0A6h, 0A6h
        DB 000h
lab93:  DB 082h
lab94:  DB 082h
lab80:  DB 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h, 074h, 065h, 072h
        DB 020h, 020h, 000h
lab82:  DB 069h, 067h, 072h, 06Fh, 06Bh, 000h
lab55:  DB 077h, 079h, 062h, 065h, 072h, 069h, 074h, 065h, 020h, 063h
        DB 077h, 065h, 074h, 03Ah, 000h
lab66:  DB 075h, 072h, 06Fh, 077h, 065h, 06Eh, 078h, 03Ah, 000h
lab62:  DB 082h
lab63:  DB 082h
lab74:  DB 082h
lab169: DB 080h, 080h, 080h, 080h, 081h, 083h, 086h, 08Eh, 08Ch, 098h
        DB 098h, 0B0h, 0B0h, 0B0h, 0B0h, 0B0h, 0B0h, 0B0h, 0B0h, 098h
        DB 098h, 08Ch, 08Eh, 086h, 083h, 081h, 080h, 080h, 080h, 080h
        DB 000h, 007h, 03Fh, 0F8h, 0C0h, 080h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 0C0h, 0F8h, 03Fh, 007h, 000h
        DB 000h, 0E0h, 0FCh, 01Fh, 003h, 001h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 003h, 01Fh, 0FCh, 0E0h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 0C0h, 060h, 070h, 030h, 018h
        DB 018h, 00Ch, 00Ch, 00Ch, 00Ch, 00Ch, 00Ch, 00Ch, 00Ch, 018h
        DB 018h, 030h, 070h, 060h, 0C0h, 080h, 000h, 000h, 000h, 000h
lab166: DB 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab170: DB 080h, 080h, 080h, 080h, 081h, 083h, 087h, 08Fh, 08Fh, 09Fh
        DB 09Fh, 0BFh, 0BFh, 0BFh, 0BFh, 0BFh, 0BFh, 0BFh, 0BFh, 09Fh
        DB 09Fh, 08Fh, 08Fh, 087h, 083h, 081h, 080h, 080h, 080h, 080h
        DB 000h, 007h, 03Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 03Fh, 007h, 000h
        DB 000h, 0E0h, 0FCh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FCh, 0E0h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 0C0h, 0E0h, 0F0h, 0F0h, 0F8h
        DB 0F8h, 0FCh, 0FCh, 0FCh, 0FCh, 0FCh, 0FCh, 0FCh, 0FCh, 0F8h
        DB 0F8h, 0F0h, 0F0h, 0E0h, 0C0h, 080h, 000h, 000h, 000h, 000h
END