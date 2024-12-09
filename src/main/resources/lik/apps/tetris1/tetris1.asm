        CPU  8080
        .ORG 00000h
lab7    EQU 04000h
lab47   EQU 02601h
lab50   EQU 02603h
lab173  EQU 02605h
lab25   EQU 02007h
lab21   EQU 0240Bh
lab230  EQU 0C010h
lab231  EQU 07A13h
lab239  EQU 03E13h
lab228  EQU 0C427h
lab214  EQU 0C037h
lab114  EQU 03A4Fh
lab136  EQU 0CAB7h
lab80   EQU 0C0BEh
lab73   EQU 08100h
lab194  EQU 0FF00h
lab192  EQU 0FF01h
lab195  EQU 0FF02h
lab191  EQU 0FF03h
lab150  EQU 0B707h
lab18   EQU 0170Bh
lab240  EQU 07B13h
lab236  EQU 01D14h
lab28   EQU 0C337h
lab174  EQU 02786h
lab175  EQU 0278Eh
lab111  EQU 077AEh
lab227  EQU 027CDh
lab168  EQU 0FFE0h
lab163  EQU 0FFF0h
lab224  EQU 0FFF7h
lab26   EQU 08FFCh
lab91   EQU 0FFFFh
        JMP lab0
        DB 000h, 000h
lab282: LXI H,lab1
        MVI M,02Bh
        INX H
        MVI A,080h
lab2:   MVI M,000h
        INX H
        DCR A
        JNZ lab2
lab222: XRA A
        STA lab3
        STA lab4
        STA lab5
        STA lab6
        LXI H,lab7
        SHLD lab8
        STA lab9
        LXI H,00000h
        SHLD lab10
        SHLD lab11
        SHLD lab12
        MOV D,A
        LXI B,00000h
        LXI H,lab13
        CALL lab14
        LXI H,lab15
        LXI B,00005h
        MVI D,014h
        CALL lab14
        MVI D,000h
        LXI H,lab16
        CALL lab14
        LXI H,lab17
        MVI E,001h
lab23:  PUSH D
        PUSH H
        LXI B,lab18
        MOV A,E
        ADI 030h
        CALL lab19
        MVI A,03Eh
        CALL lab19
        MVI D,000h
        CALL lab14
        LXI H,lab20
        CALL lab14
        POP H
        PUSH H
        LXI D,00009h
        DAD D
        LXI B,lab21
        CALL lab22
        POP H
        LXI D,0000Ch
        DAD D
        LDA lab4
        ADI 00Eh
        STA lab4
        POP D
        INR E
        MOV A,E
        CPI 00Ah
        JNZ lab23
        XRA A
        STA lab4
        LXI H,lab24
        MVI D,000h
        CALL lab14
        LXI B,lab25
        LXI H,lab1
        MVI D,009h
lab34:  PUSH H
        LXI H,0C010h
        SHLD lab26
        POP H
        MVI A,001h
        CALL lab27
lab31:  CALL lab28
        CPI 00Dh
        JZ lab29
        CPI 008h
        JZ lab30
        DCR D
        INR D
        JZ lab31
        CPI 020h
        JNZ lab32
        MVI A,02Bh
        JMP lab33
lab32:  CPI 041h
        JC lab31
        CPI 060h
        JC lab33
        SUI 020h
lab33:  MOV M,A
        CALL lab19
        INX H
        DCR D
        JMP lab34
lab30:  MOV A,D
        CPI 009h
        JZ lab31
        MVI A,00Bh
        CALL lab27
        DCR B
        INR D
        DCX H
        JMP lab34
lab29:  MOV A,D
        CPI 009h
        JNZ lab35
        MVI D,000h
        CALL lab14
        JMP lab36
lab35:  MVI M,000h
        MVI A,00Bh
        CALL lab27
lab36:  CALL lab37
        CALL lab38
        CPI 020h
        JNZ lab36
lab217: CALL lab39
        LXI H,00000h
        SHLD lab40
        SHLD lab41
        SHLD lab12
        LXI B,lab42
        LXI H,lab40
        CALL lab43
        CALL lab44
        XRA A
        STA lab45
        STA lab46
        LXI B,lab47
        CALL lab48
        LXI H,lab8
        LXI B,lab49
        CALL lab43
        LDA lab5
        LXI B,lab50
        CALL lab48
        LDA lab5
        ANI 00Fh
        MVI B,007h
        DCR A
        JNZ lab51
        MVI B,00Eh
lab51:  DCR A
        JNZ lab52
        MVI B,015h
lab52:  MOV A,B
        STA lab53
        LXI H,00120h
        SHLD lab54
lab155: CALL lab55
        CALL lab56
        XRA A
        STA lab57
        LDA lab5
        ANI 00Fh
        CPI 003h
        JNC lab58
        INR A
        MOV C,A
        MVI A,030h
        MOV B,A
lab60:  DCR C
        JZ lab59
        ADD B
        JMP lab60
lab58:  MVI A,060h
lab59:  ADI 000h
        DAA
        STA lab61
        CALL lab62
lab64:  CALL lab63
        LDA lab57
        ORA A
        JNZ lab64
        LDA lab65
        ORA A
        JNZ lab66
        LDA lab61
        MVI B,099h
        ADD B
        DAA
        STA lab61
        CALL lab62
lab66:  CALL lab67
        CPI 010h
        JZ lab68
        CPI 004h
        JZ lab69
        CPI 080h
        JZ lab70
        CPI 020h
        JZ lab71
        CPI 001h
        JZ lab72
        CPI 002h
lab242: JZ lab73
        JMP lab64
lab72:  LHLD lab54
        SHLD lab74
        LXI H,00700h
        SHLD lab54
        XRA A
        STA lab4
        STA lab6
lab78:  LXI H,lab75
        CALL lab14
        CALL lab67
        CPI 001h
        JZ lab76
        LXI H,lab77
        CALL lab14
        CALL lab67
        CPI 001h
        JNZ lab78
lab76:  LXI H,lab79
        CALL lab14
        LHLD lab74
        SHLD lab54
        JMP lab64
lab215: MVI B,003h
        PUSH PSW
lab81:  POP PSW
        LDAX D
        CMP M
        RNZ
        PUSH PSW
        DCR B
        INX H
        INX D
        JNZ lab81
        POP PSW
        RET
lab39:  LXI H,lab82
        MVI C,01Ah
lab85:  MVI B,010h
        LXI D,lab83
        CALL lab84
        DCR C
        JNZ lab85
        CALL lab86
        LXI H,lab87
        CALL lab14
        LDA lab5
        LXI B,lab88
        CALL lab48
        LXI H,lab89
        CALL lab14
        LXI H,lab8
        LXI B,lab90
        CALL lab43
        LXI H,lab91
        CALL lab92
        LXI H,lab91
        CALL lab92
        LXI H,lab91
        CALL lab92
        LXI H,lab82
        MVI C,019h
lab94:  MVI B,010h
        LXI D,lab93
        CALL lab84
        DCR C
        JNZ lab94
        LXI D,lab95
        MVI B,010h
        CALL lab84
        LDA lab5
        ANI 00Fh
        CPI 003h
        JC lab86
        SUI 003h
        RRC
        RRC
        RRC
        LXI D,00000h
        MOV E,A
        LXI H,lab96
        DAD D
        LXI D,lab97
        XCHG
        MVI B,010h
lab102: PUSH B
        MVI B,002h
lab101: LDAX D
        MVI C,008h
lab100: RAL
        JNC lab99
        MVI M,002h
lab99:  INX H
        DCR C
        JNZ lab100
        DCR B
        INX D
        JNZ lab101
        POP B
        DCR B
        JNZ lab102
lab86:  XRA A
        STA lab6
        STA lab4
        LXI B,00000h
lab106: CALL lab103
        MOV A,M
        CALL lab104
        INX B
        LXI H,001A0h
        CALL lab105
        JNZ lab106
        RET
lab92:  DCX H
        MOV A,H
        ORA L
        JNZ lab92
        RET
lab103: LXI H,lab82
        DAD B
        RET
lab84:  LDAX D
        MOV M,A
        INX D
        INX H
        DCR B
        JNZ lab84
        RET
lab27:  PUSH B
        PUSH D
        PUSH H
        PUSH PSW
        MOV A,B
        ADI 090h
        MOV D,A
        MOV A,C
        RLC
        RLC
        RLC
        MOV C,A
        LDA lab4
        ADD C
        MOV E,A
        POP PSW
        PUSH PSW
        MOV L,A
        MVI H,000h
        DAD H
        DAD H
        DAD H
        LXI B,lab108
        DAD B
        XCHG
        LDA lab3
        ORA A
lab244: JZ lab109
        DCR A
        JZ lab110
lab113: LDAX D
        XRA M
        MOV M,A
        JMP lab112
lab110: LXI B,00007h
        DAD B
        JMP lab113
lab109: MVI B,008h
lab115: LDAX D
        MOV C,A
        LDA lab6
        ANA M
        XRA C
        MOV M,A
        INX H
        INX D
        DCR B
        JNZ lab115
lab112: POP PSW
        POP H
        POP D
        POP B
        RET
lab104: PUSH B
        PUSH D
        PUSH PSW
        MOV A,C
        ANI 00Fh
        ADI 003h
        MOV D,A
        MOV A,C
        ANI 0F0h
        ORA B
        RRC
        RRC
        RRC
        RRC
        ADI 005h
        MOV E,A
        PUSH D
        POP B
        POP PSW
        CALL lab27
        POP D
        POP B
        RET
lab105: MOV A,H
        CMP B
        RNZ
        MOV A,L
        CMP C
        RET
lab55:  CALL lab37
        LDA lab53
        MOV C,A
        MOV A,L
lab117: CMP C
        JC lab116
        SUB C
        JMP lab117
lab116: RLC
        RLC
        RLC
        MOV L,A
        MVI H,000h
        LXI D,lab118
        DAD D
        LXI D,lab119
        MVI C,008h
lab122: MOV A,M
        MVI B,008h
lab121: RAL
        PUSH PSW
        MVI A,000h
        RAL
        STAX D
        POP PSW
        INX D
        DCR B
        JNZ lab121
        INX H
        DCR C
        JNZ lab122
        XRA A
        STA lab65
        LHLD lab123
        MOV A,H
        ANI 003h
        STA lab124
        MOV A,H
        RRC
        RRC
        ANI 007h
        JZ lab125
        DCR A
lab125: ADI 003h
        MOV L,A
        MVI H,000h
        SHLD lab126
        RET
lab37:  LHLD lab123
        MVI C,010h
lab128: MOV A,H
        DAD H
        ANI 060h
        JPE lab127
        INX H
lab127: DCR C
        JNZ lab128
        SHLD lab123
        RET
lab56:  CALL lab129
        JZ lab130
        POP H
        JMP lab131
lab129: CALL lab132
        XRA A
        STA lab133
        CALL lab134
        LDA lab65
        ORA A
        JZ lab135
        LXI B,00010h
        DAD B
        CALL lab134
lab135: LDA lab133
        ORA A
        RET
lab134: PUSH H
        PUSH D
        MVI B,004h
lab140: MVI C,004h
lab139: LDAX D
        ORA A
        JZ lab137
        MOV A,M
        ORA A
        JZ lab137
        MVI A,001h
        STA lab133
        JMP lab138
lab137: INX D
        INX H
        DCR C
        JNZ lab139
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ lab140
lab138: POP D
        POP H
        RET
lab132: LXI H,lab119
        LDA lab124
        RLC
        RLC
        RLC
        RLC
        MOV E,A
        MVI D,000h
        DAD D
        XCHG
        LHLD lab126
        LXI B,lab82
        DAD B
        RET
lab130: CALL lab132
        LHLD lab126
        LDA lab65
        STA lab4
        MVI A,0FFh
        STA lab6
        MVI B,004h
lab143: MVI C,004h
lab142: PUSH B
        PUSH H
        POP B
        LDAX D
        ORA A
        JZ lab141
        CALL lab104
lab141: PUSH B
        POP H
        POP B
        INX H
        INX D
        DCR C
        JNZ lab142
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ lab143
        RET
lab63:  MVI A,002h
        STA lab3
        LDA lab57
        ORA A
        CZ lab130
        LDA lab65
        ORA A
        JZ lab144
lab149: INR A
        CPI 008h
        JZ lab145
lab146: STA lab65
        MVI A,001h
        STA lab3
        LDA lab57
        ORA A
        CZ lab130
        XRA A
        STA lab3
        RET
lab145: LHLD lab126
        LXI B,00010h
        DAD B
        SHLD lab126
        XRA A
        JMP lab146
lab144: LHLD lab126
        SHLD lab147
        LXI B,00010h
        DAD B
        SHLD lab126
        CALL lab129
        LHLD lab147
        SHLD lab126
        JNZ lab148
        JMP lab149
lab148: CALL lab132
        MVI B,004h
lab153: MVI C,004h
lab152: LDAX D
        RLC
        ORA A
        JZ lab151
        MOV M,A
lab151: INX H
        INX D
        DCR C
        JNZ lab152
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ lab153
        XRA A
        STA lab3
        CALL lab86
        CALL lab154
        JMP lab155
lab154: LXI H,lab156
        LXI D,lab61
        CALL lab157
        LXI B,lab42
        CALL lab43
        LXI H,lab158
        LXI D,lab61
        CALL lab157
        LXI B,lab159
        CALL lab43
        XRA A
        STA lab160
lab185: LXI H,lab161
lab165: CALL lab162
        LXI B,lab163
        DAD B
        LXI B,lab164
        CALL lab105
        JNZ lab165
        PUSH B
        PUSH H
        LDA lab160
        ORA A
        JZ lab166
        MOV B,A
        LDA lab46
        ADD B
        STA lab46
        CPI 008h
        JC lab167
        SUI 008h
        STA lab46
        LDA lab45
        CPI 008h
        JZ lab167
        INR A
        STA lab45
        LXI B,lab47
        CALL lab48
        LHLD lab54
        LXI B,lab168
        DAD B
        SHLD lab54
lab167: LDA lab160
        MVI B,001h
        DCR A
        JZ lab169
        MVI B,003h
        DCR A
        JZ lab169
        MVI B,006h
        DCR A
        JZ lab169
        MVI B,010h
lab169: MOV A,B
        STA lab170
        LXI D,lab171
        LXI H,lab156
        CALL lab157
        LXI B,lab42
        CALL lab43
        LXI H,lab158
        LXI D,lab171
        CALL lab157
        LXI B,lab159
        CALL lab43
        LDA lab160
        LHLD lab12
        MOV E,A
        MVI D,000h
        CALL lab172
        SHLD lab12
        CALL lab44
lab166: POP H
        POP B
        RET
lab62:  LXI B,00303h
        LDA lab61
        JMP lab48
lab44:  LHLD lab12
        LXI B,lab173
        MOV A,H
        CALL lab48
        MOV A,L
        JMP lab48
lab157: LDAX D
        ADD M
        DAA
        MOV M,A
        DCX H
        DCX D
        LDAX D
        ADC M
        DAA
        MOV M,A
        DCX H
        DCX D
        LDAX D
        ADC M
        DAA
        MOV M,A
        RET
lab43:  MVI E,003h
lab176: MOV A,M
        CALL lab48
        INX H
        DCR E
        JNZ lab176
        RET
lab22:  MVI E,003h
lab178: MOV A,M
        CALL lab177
        INX H
        DCR E
        JNZ lab178
        RET
lab48:  PUSH PSW
        XRA A
        STA lab6
        STA lab4
        POP PSW
lab177: MOV D,A
        RRC
        RRC
        RRC
        RRC
        ANI 00Fh
        ADI 030h
        CALL lab19
        MOV A,D
        ANI 00Fh
        ADI 030h
        CALL lab19
        RET
lab162: PUSH H
        MVI B,00Ah
lab180: MOV A,M
        ORA A
        JZ lab179
        INX H
        DCR B
        JNZ lab180
        POP H
        PUSH H
lab184: PUSH H
        LXI B,lab163
        PUSH H
        POP D
        DAD B
        MVI B,00Ah
lab182: MOV A,M
        STAX D
        INX H
        INX D
        DCR B
        JNZ lab182
        POP H
        LXI B,lab164
        CALL lab105
        JZ lab183
        LXI B,lab163
        DAD B
        JMP lab184
lab183: CALL lab86
        LDA lab160
        INR A
        STA lab160
        POP H
        POP H
        JMP lab185
lab179: POP H
        RET
lab67:  LHLD lab54
        XRA A
        STA lab186
lab190: CALL lab38
        ORA A
        JNZ lab187
        STA lab188
        JMP lab189
lab187: MOV B,A
        LDA lab188
        ORA A
        MOV A,B
        STA lab188
        JNZ lab189
        LDA lab186
        ORA A
        JNZ lab189
        MOV A,B
        STA lab186
lab189: DCX H
        MOV A,H
        ORA L
        JNZ lab190
        LDA lab186
        ORA A
        RET
lab38:  MVI A,091h
        STA lab191
        MVI A,0FBh
        STA lab192
        MVI C,00Fh
lab193: DCR C
        JNZ lab193
        LDA lab194
        ANI 03Fh
        MOV B,A
        LDA lab195
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        RET
lab68:  CALL lab130
        LHLD lab126
        SHLD lab147
        LXI B,lab91
lab198: DAD B
        SHLD lab126
        CALL lab129
        JNZ lab196
        JMP lab197
lab196: LHLD lab147
        SHLD lab126
        JMP lab197
lab69:  CALL lab130
        LHLD lab126
        SHLD lab147
        LXI B,00001h
        JMP lab198
lab71:  MVI A,001h
        STA lab57
        JMP lab64
lab70:  CALL lab130
        LDA lab124
        STA lab199
        INR A
        CPI 004h
        JC lab200
        XRA A
lab200: STA lab124
        CALL lab129
        JNZ lab201
lab197: CALL lab130
        JMP lab64
lab201: LDA lab199
        STA lab124
        JMP lab197
lab14:  PUSH H
        PUSH B
lab203: MOV A,M
        ORA A
        JZ lab202
        CALL lab19
        INX H
        JMP lab203
lab202: MOV A,D
        ORA A
        POP B
        POP H
        RZ
        DCR D
        INR C
        JMP lab14
lab19:  CPI 020h
        JNC lab204
        CPI 01Fh
        JZ lab205
        CPI 008h
        JZ lab206
        CPI 018h
        JZ lab207
        CPI 019h
        JZ lab208
        CPI 01Ah
        JZ lab209
        CPI 00Ah
        JZ lab210
        CPI 00Dh
        JZ lab211
        CPI 007h
        JZ lab212
        RET
lab204: PUSH PSW
        ANI 07Fh
        SUI 020h
        CALL lab27
        POP PSW
        CPI 080h
        JNC lab213
        INR B
        RET
lab213: INR C
        RET
lab206: DCR B
        RET
lab207: INR B
        RET
lab209: INR C
        RET
lab208: DCR C
        RET
lab211: MVI B,000h
        RET
lab210: INR C
        MVI B,000h
        RET
lab212: INX H
        MOV B,M
        INX H
        MOV C,M
        RET
lab205: PUSH B
        MVI C,01Fh
        CALL lab214
        POP B
        RET
lab131: LXI D,lab8
        LXI H,lab40
        CALL lab215
        JNC lab216
        LDA lab5
        ADI 001h
        DAA
        STA lab5
        ANI 00Fh
        JNZ lab217
        LXI H,00040h
        LXI D,00015h
        LDA lab5
lab218: CALL lab172
        SUI 010h
        JNZ lab218
        MOV A,H
        MOV H,L
        MOV L,A
        SHLD lab8
        JMP lab217
lab172: PUSH PSW
        MOV A,L
        ADD E
        DAA
        MOV L,A
        MOV A,H
        ADC D
        DAA
        MOV H,A
        POP PSW
        RET
lab216: LXI H,lab219
        MVI D,009h
lab221: PUSH H
        PUSH D
        LXI D,lab10
        CALL lab215
        JNC lab220
        POP D
        POP H
        LXI B,0000Ch
        DAD B
        DCR D
        JNZ lab221
        JMP lab222
lab220: POP D
        MOV A,D
        CPI 001h
        JZ lab223
        POP H
        PUSH H
        LXI B,lab224
        DAD B
        XCHG
        LXI B,lab225
        LXI H,lab226
lab229: DCX B
        DCX H
        MOV A,M
        STAX B
        CALL lab228
        JNZ lab229
lab223: POP H
        LXI B,lab224
        DAD B
        LXI D,lab1
        MVI B,009h
        CALL lab84
        LXI D,lab10
        MVI B,003h
        CALL lab84
        JMP lab222

lab13:  DB 02Ch, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
lab98:  DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 02Dh, 00Ah, 061h, 02Bh, 052h
        DB 045h, 05Ah, 055h, 04Ch, 058h, 054h, 041h, 054h, 03Ah, 030h
        DB 030h, 030h, 030h, 030h, 030h, 02Bh, 02Bh, 04Fh, 05Eh, 04Bh
        DB 049h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 053h, 04Bh, 04Fh, 052h
        DB 04Fh, 053h, 054h, 058h, 03Dh, 030h, 030h, 02Bh, 02Bh, 02Bh
        DB 061h, 00Ah, 062h, 060h, 060h, 060h, 060h, 060h, 02Dh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 061h, 00Ah, 061h, 02Bh, 030h
        DB 030h, 030h, 02Bh, 061h, 043h, 045h, 04Ch, 058h, 03Ah, 030h
        DB 030h, 030h, 030h, 030h, 030h, 02Bh, 02Bh, 030h, 030h, 030h
        DB 030h, 030h, 030h, 02Bh, 02Bh, 02Bh, 02Bh, 055h, 052h, 04Fh
        DB 057h, 045h, 04Eh, 058h, 03Dh, 030h, 030h, 02Bh, 02Bh, 02Bh
        DB 061h, 00Ah, 062h, 060h, 060h, 060h, 060h, 060h, 065h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 063h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 064h, 00Ah, 000h
lab15:  DB 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 061h, 000h
lab16:  DB 007h, 000h, 01Ah, 061h, 02Bh, 02Bh, 02Bh, 02Bh
lab90:  DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 062h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 064h, 00Ah
        DB 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 061h, 00Ah, 061h, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 061h, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 061h, 00Ah
        DB 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 061h, 02Bh, 053h, 04Bh, 054h, 042h, 02Bh, 057h, 054h
        DB 02Bh, 02Bh, 04Fh, 054h, 044h, 02Bh, 031h, 030h, 030h, 02Bh
        DB 02Bh, 02Bh, 02Bh, 061h, 00Ah, 061h, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 061h, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 061h, 00Ah
        DB 02Fh, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 065h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 02Eh, 000h
lab75:  DB 007h, 007h, 01Eh, 02Bh, 050h, 041h, 055h, 05Ah, 041h, 02Bh
        DB 000h
lab79:  DB 007h, 007h, 01Eh, 023h, 023h, 023h, 023h, 023h, 023h, 023h
        DB 000h
lab77:  DB 007h, 007h, 01Eh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 000h
lab87:  DB 007h, 003h, 011h, 055h, 052h, 04Fh, 057h, 045h, 04Eh, 058h
        DB 03Dh, 02Bh, 02Bh, 000h
lab89:  DB 007h, 003h, 013h, 043h, 045h, 04Ch, 058h, 03Dh, 02Bh, 000h
lab24:  DB 007h, 016h, 008h, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh
        DB 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh
        DB 03Dh, 03Dh, 03Dh, 03Dh, 007h, 018h, 009h, 054h, 041h, 042h
        DB 04Ch, 049h, 043h, 041h, 02Bh, 052h, 045h, 04Bh, 04Fh, 052h
        DB 044h, 04Fh, 057h, 007h, 017h, 005h, 053h, 042h, 052h, 04Fh
        DB 05Bh, 045h, 04Eh, 04Fh, 02Bh, 052h, 051h, 044h, 04Fh, 057h
        DB 03Ah, 030h, 030h, 030h, 030h, 007h, 015h, 006h, 062h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 064h, 007h, 019h, 007h, 049h, 047h, 052h, 041h, 045h, 054h
        DB 03Ah, 000h
lab20:  DB 007h, 021h, 00Bh, 02Bh, 03Dh, 02Bh, 000h
lab65:  DB 000h
lab4:   DB 000h
lab124: DB 000h
lab199: DB 000h
lab126: DB 008h, 000h
lab147: DB 018h, 000h
lab6:   DB 000h
lab3:   DB 000h
lab123: DB 038h, 018h
lab5:   DB 000h, 000h, 000h
lab61:  DB 030h
lab57:  DB 001h
lab133: DB 001h
lab54:  DB 020h, 001h
lab74:  DB 000h, 000h
lab12:  DB 000h, 000h
lab188: DB 020h
lab186: DB 020h
lab40:  DB 000h
lab41:  DB 004h
lab156: DB 066h, 000h
lab170: DB 000h
lab171: DB 000h
lab160: DB 000h
lab46:  DB 000h
lab45:  DB 000h
lab53:  DB 007h
lab8:   DB 000h, 040h
lab9:   DB 000h
lab10:  DB 000h
lab11:  DB 000h
lab158: DB 000h
lab95:  DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 003h, 003h
lab93:  DB 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 003h, 003h, 003h
lab83:  DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
lab118: DB 027h, 000h, 023h, 020h, 072h, 000h, 013h, 010h, 00Fh, 000h
        DB 022h, 022h, 00Fh, 000h, 022h, 022h, 047h, 000h, 032h, 020h
        DB 071h, 000h, 011h, 030h, 074h, 000h, 031h, 010h, 017h, 000h
        DB 022h, 030h, 033h, 000h, 033h, 000h, 033h, 000h, 033h, 000h
        DB 036h, 000h, 023h, 010h, 036h, 000h, 023h, 010h, 063h, 000h
        DB 013h, 020h, 063h, 000h, 013h, 020h, 027h, 020h, 027h, 020h
        DB 027h, 020h, 027h, 020h, 076h, 000h, 033h, 010h, 037h, 000h
        DB 023h, 030h, 067h, 000h, 033h, 020h, 073h, 000h, 013h, 030h
        DB 022h, 070h, 047h, 040h, 072h, 020h, 017h, 010h, 075h, 000h
        DB 031h, 030h, 057h, 000h, 032h, 030h, 064h, 044h, 00Fh, 010h
        DB 022h, 026h, 008h, 0F0h, 062h, 022h, 001h, 0F0h, 044h, 046h
        DB 00Fh, 080h, 047h, 010h, 032h, 060h, 047h, 010h, 032h, 060h
        DB 017h, 040h, 062h, 030h, 017h, 040h, 062h, 030h, 022h, 062h
        DB 004h, 0F0h, 046h, 044h, 00Fh, 020h, 044h, 064h, 00Fh, 040h
        DB 026h, 022h, 002h, 0F0h, 044h, 070h, 074h, 040h, 071h, 010h
        DB 011h, 070h, 023h, 060h, 047h, 020h, 036h, 020h, 027h, 010h
        DB 026h, 030h, 027h, 040h, 063h, 020h, 017h, 020h
lab119: DB 000h, 000h, 001h, 000h, 000h, 001h, 001h, 001h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 001h, 001h, 000h, 000h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 001h, 001h, 001h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 001h, 000h, 000h, 001h, 001h, 000h, 000h, 000h, 001h
        DB 000h
lab42:  DB 000h, 000h
lab49:  DB 000h, 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 003h, 003h, 003h
lab82:  DB 003h, 003h, 003h
lab164: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 002h, 000h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 002h
        DB 002h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 002h, 000h, 000h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h, 002h, 002h
        DB 002h, 002h, 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 002h, 000h, 000h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 002h, 000h, 000h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 002h
        DB 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 002h, 000h, 000h, 003h, 003h
        DB 003h
lab97:  DB 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 002h, 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 002h, 000h, 000h, 003h
        DB 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 002h, 000h, 000h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 000h, 002h, 002h, 000h, 000h, 000h, 000h, 002h, 000h
        DB 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 002h
        DB 002h, 002h, 002h, 002h, 002h, 000h, 000h, 003h, 003h, 003h
        DB 003h, 003h, 003h, 000h, 000h, 002h, 002h, 002h, 002h, 000h
        DB 000h, 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h
        DB 000h, 000h, 000h, 000h, 002h, 000h, 000h, 000h, 000h
lab88:  DB 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h
        DB 002h, 002h, 002h, 000h, 000h, 000h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 000h, 000h, 002h, 002h, 000h, 002h, 000h, 000h
        DB 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h
        DB 002h, 002h, 000h, 002h, 000h, 000h, 000h, 000h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 000h, 002h, 002h, 002h, 002h, 002h
        DB 002h, 002h, 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 000h, 000h, 000h, 000h, 002h, 000h, 002h, 002h, 002h, 000h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h
        DB 002h, 000h, 000h, 002h, 000h, 000h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 000h, 000h, 000h, 002h, 002h, 002h, 000h, 002h
        DB 000h, 000h, 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h
        DB 000h, 002h, 002h, 002h, 000h, 002h, 000h, 000h, 003h, 003h
        DB 003h, 003h, 003h, 003h
lab161: DB 000h, 000h, 002h, 002h, 000h, 002h, 002h, 002h, 000h, 000h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
lab1:   DB 02Bh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab17:  DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab219: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab226: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
lab225: DB 000h, 00Eh, 00Eh, 020h, 0CDh, 05Eh, 00Eh, 02Ah, 00Ah, 010h
        DB 0CDh, 056h, 00Eh, 00Eh, 029h, 0CDh, 05Eh, 00Eh, 001h, 020h
        DB 004h, 0C9h, 006h, 00Ch, 0C3h, 056h, 00Eh, 0C5h, 0CDh, 067h
        DB 00Eh, 0C1h, 0C9h, 07Ch, 0CDh, 050h, 00Eh, 07Dh, 0CDh, 050h
        DB 00Eh, 0C3h, 037h, 0C0h, 0C3h, 037h, 0C3h, 0AFh, 0C9h, 0F8h
        DB 0C3h, 027h, 006h, 0C3h, 038h, 0C4h, 0C3h, 000h, 000h, 00Bh
        DB 000h, 008h, 001h, 02Eh, 001h, 000h, 001h, 016h, 002h, 00Eh
        DB 001h, 026h, 002h, 02Bh, 001h
lab96:  DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 010h, 008h, 010h, 008h, 018h, 018h, 01Ch, 038h
        DB 01Eh, 078h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 011h, 088h, 011h, 088h, 011h, 088h, 011h, 088h, 011h, 088h
        DB 011h, 088h, 011h, 088h, 011h, 088h, 011h, 088h, 011h, 088h
        DB 011h, 088h, 011h, 088h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 010h, 008h, 000h, 000h
        DB 000h, 000h, 018h, 018h, 000h, 000h, 000h, 000h, 01Ch, 038h
        DB 000h, 000h, 000h, 000h, 01Eh, 078h, 000h, 000h, 000h, 000h
        DB 003h, 0F8h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 01Fh, 0C0h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 003h, 0F8h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 01Eh, 078h, 01Eh, 078h
        DB 01Eh, 078h, 01Eh, 008h, 01Eh, 078h, 000h, 000h, 000h, 000h
        DB 01Eh, 078h, 01Eh, 078h, 01Eh, 078h, 01Eh, 078h, 01Eh, 078h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 010h, 008h, 018h, 018h, 01Ch, 038h, 01Eh, 078h, 01Eh, 078h
        DB 01Eh, 078h, 01Eh, 078h, 01Eh, 078h, 01Ch, 038h, 018h, 018h
        DB 010h, 008h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 01Bh, 0D8h, 017h, 0B8h, 00Fh, 078h, 01Eh, 0F0h, 01Bh, 0D8h
        DB 017h, 0B8h, 00Fh, 078h, 01Eh, 0F0h, 01Bh, 0D8h, 017h, 0B8h
        DB 00Fh, 078h, 01Eh, 0F0h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h
lab108: DB 055h, 000h, 001h, 000h, 001h, 000h, 001h, 000h, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0C3h, 0A5h, 099h
        DB 099h, 0A5h, 0C3h, 0FFh, 049h, 092h, 024h, 0C9h, 092h, 024h
        DB 049h, 092h, 000h, 000h, 001h, 002h, 007h, 007h, 00Fh, 00Fh
        DB 07Fh, 083h, 005h, 009h, 0F1h, 0F3h, 0F3h, 0F7h, 01Eh, 026h
        DB 04Ch, 094h, 0E8h, 0C8h, 0CCh, 094h, 01Eh, 01Eh, 03Ch, 03Dh
        DB 07Ah, 07Ch, 0F8h, 0F0h, 0F7h, 0FFh, 0FFh, 0F7h, 0F7h, 0F7h
        DB 0FBh, 0F1h, 0A2h, 042h, 081h, 082h, 084h, 0C8h, 0D0h, 0E0h
        DB 03Ch, 042h, 099h, 0A1h, 0A1h, 099h, 042h, 03Ch, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 01Fh
        DB 01Fh, 018h, 018h, 018h, 000h, 000h, 000h, 0F8h, 0F8h, 018h
        DB 018h, 018h, 018h, 018h, 018h, 0F8h, 0F8h, 000h, 000h, 000h
        DB 018h, 018h, 018h, 01Fh, 01Fh, 000h, 000h, 000h, 0FEh, 082h
        DB 082h, 082h, 0C2h, 0C2h, 0C2h, 0FEh, 008h, 008h, 018h, 018h
        DB 018h, 018h, 018h, 018h, 07Eh, 002h, 002h, 0FEh, 080h, 0C0h
        DB 0C2h, 0FEh, 03Eh, 002h, 002h, 03Eh, 002h, 006h, 006h, 0FEh
        DB 082h, 082h, 0C2h, 0C2h, 0FEh, 006h, 006h, 006h, 0FCh, 080h
        DB 080h, 0FEh, 002h, 006h, 086h, 0FEh, 0F8h, 0C8h, 0C0h, 0FEh
        DB 082h, 086h, 086h, 0FEh, 0FEh, 082h, 002h, 002h, 006h, 006h
        DB 006h, 006h, 07Ch, 064h, 064h, 0FEh, 082h, 0C2h, 0C2h, 0FEh
        DB 0FEh, 082h, 0C2h, 0C2h, 0FEh, 006h, 026h, 03Eh, 000h, 018h
        DB 018h, 000h, 000h, 018h, 018h, 000h, 000h, 018h, 018h, 0FEh
        DB 0FEh, 018h, 018h, 000h, 000h, 000h, 000h, 038h, 038h, 038h
        DB 010h, 020h, 000h, 000h, 000h, 07Eh, 07Eh, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 038h, 038h, 038h, 0FEh, 006h
        DB 002h, 01Eh, 030h, 030h, 000h, 030h, 09Eh, 092h, 092h, 0F2h
        DB 096h, 0D6h, 0D6h, 0DEh, 03Eh, 022h, 022h, 0FEh, 082h, 0C2h
        DB 0C2h, 0C2h, 0F8h, 080h, 080h, 0FEh, 082h, 0C2h, 0C2h, 0FEh
        DB 084h, 084h, 084h, 084h, 0C4h, 0C4h, 0C4h, 0FEh, 01Ch, 014h
        DB 024h, 044h, 064h, 064h, 064h, 0FEh, 0FEh, 080h, 080h, 0F8h
        DB 0C0h, 0C0h, 0C0h, 0FEh, 0FEh, 092h, 092h, 0D2h, 0D2h, 0FEh
        DB 010h, 020h, 0FEh, 080h, 080h, 080h, 0C0h, 0C0h, 0C0h, 0C0h
        DB 044h, 044h, 044h, 038h, 044h, 0C6h, 0C6h, 0C6h, 082h, 086h
        DB 08Ah, 092h, 0A2h, 0C6h, 086h, 086h, 038h, 082h, 086h, 08Ah
        DB 092h, 0A6h, 0C6h, 086h, 088h, 088h, 088h, 0FEh, 0C2h, 0C2h
        DB 0C2h, 0C2h, 00Eh, 012h, 012h, 012h, 026h, 026h, 066h, 066h
        DB 082h, 0C6h, 0AAh, 092h, 0C2h, 0C2h, 0C2h, 0C2h, 082h, 082h
        DB 082h, 0FEh, 0C2h, 0C2h, 0C2h, 0C2h, 0FEh, 08Eh, 086h, 082h
        DB 082h, 082h, 082h, 0FEh, 0FEh, 082h, 082h, 082h, 0C2h, 0C2h
        DB 0C2h, 0C2h, 0FEh, 082h, 0C2h, 0C2h, 0FEh, 026h, 046h, 086h
        DB 0FEh, 082h, 0C2h, 0C2h, 0FEh, 080h, 080h, 080h, 0FEh, 080h
        DB 080h, 080h, 0C0h, 0C0h, 0C0h, 0FEh, 0FEh, 010h, 010h, 010h
        DB 018h, 018h, 018h, 018h, 082h, 082h, 0C2h, 0C2h, 0FEh, 002h
        DB 062h, 07Eh, 054h, 054h, 054h, 07Ch, 054h, 0D6h, 0D6h, 0D6h
        DB 0FCh, 084h, 084h, 0FEh, 0C2h, 0C2h, 0C2h, 0FEh, 080h, 080h
        DB 080h, 0FEh, 082h, 0C2h, 0C2h, 0FEh, 082h, 082h, 082h, 0FAh
        DB 08Ah, 0CAh, 0CAh, 0FAh, 0F8h, 018h, 018h, 07Eh, 002h, 006h
        DB 086h, 0FEh, 092h, 092h, 092h, 092h, 0D2h, 0D2h, 0D2h, 0FEh
        DB 0FEh, 082h, 002h, 01Eh, 006h, 006h, 086h, 0FEh, 094h, 094h
        DB 094h, 094h, 0D4h, 0D4h, 0D4h, 0FEh, 082h, 082h, 0C2h, 0C2h
        DB 0FEh, 006h, 006h, 006h, 0C0h, 040h, 040h, 07Eh, 042h, 062h
        DB 062h, 07Eh, 000h, 000h, 000h, 0FFh, 0FFh, 000h, 000h, 000h
        DB 018h, 018h, 018h, 018h, 018h, 018h, 018h, 018h, 018h, 018h
        DB 018h, 01Fh, 01Fh, 018h, 018h, 018h, 000h, 000h, 000h, 0FFh
        DB 0FFh, 018h, 018h, 018h, 018h, 018h, 018h, 0F8h, 0F8h, 018h
        DB 018h, 018h, 018h, 018h, 018h, 0FFh, 0FFh, 000h, 000h, 000h
        DB 03Eh, 001h, 0CDh, 0F0h, 002h, 0CDh, 037h, 0C3h, 0FEh, 00Dh
        DB 0CAh, 0F7h, 000h, 0FEh, 008h, 0CAh, 0E6h, 000h, 015h, 014h
        DB 0CAh, 0B5h, 000h, 0FEh, 020h, 0C2h, 0D1h, 000h, 03Eh, 02Bh
        DB 0C3h, 0DDh, 000h, 0FEh, 041h, 0DAh, 0B5h, 000h, 0FEh, 060h
        DB 0DAh, 0DDh, 000h, 0D6h, 020h, 077h, 0CDh, 00Eh, 007h, 023h
        DB 015h, 0C3h, 0A8h, 000h, 07Ah, 0FEh, 009h, 0CAh, 0B5h, 000h
        DB 03Eh, 00Bh, 0CDh, 0F0h, 002h, 005h, 014h, 02Bh, 0C3h, 0A8h
        DB 000h, 07Ah, 0FEh, 009h, 0C2h, 005h, 001h, 016h, 000h, 0CDh
lab0:   CALL lab230
        LXI D,09000h
lab232: MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 091h
        JNZ lab232
        LXI D,0BF00h
lab233: MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 0C0h
        JNZ lab233
        LXI D,09000h
        CALL lab234
        JMP lab235
lab234: MVI A,0FFh
        STAX D
        INX D
        STAX D
        INR D
        DCR E
        MOV A,D
        CPI 0C0h
        JNZ lab234
        RET
lab235: LXI D,09004h
        CALL lab234
        LXI D,090FAh
        CALL lab234
        LXI D,090FEh
        CALL lab234
        LXI D,09418h
        CALL lab237
        JMP lab238
lab237: MVI A,0FFh
        STAX D
        INX D
        MVI A,066h
        STAX D
        INX D
        STAX D
        INX D
        STAX D
        INX D
        MOV A,E
        CPI 0E4h
        JNZ lab237
        RET
lab238: LXI D,09318h
        CALL lab237
        LXI D,09618h
        CALL lab237
        LXI D,0B918h
        CALL lab237
        LXI D,0BB18h
        CALL lab237
        LXI D,0BC18h
        CALL lab237
        LXI B,lab241
        LXI D,09820h
        MVI H,000h
lab245: LDAX B
        SUI 001h
        JZ lab243
        ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lab245
        INR D
        INR D
        MVI H,000h
        JMP lab245
lab243: LXI B,lab246
        LXI D,0AB20h
        MVI H,000h
lab249: LDAX B
        SUI 001h
        JZ lab247
        CALL lab248
        JNZ lab249
        INR D
        INR D
        MVI H,000h
        MVI E,020h
        JMP lab249
lab247: LXI B,lab250
        LXI D,099A0h
        MVI H,000h
lab253: LDAX B
        SUI 001h
        JZ lab251
        CALL lab248
        JMP lab252
lab248: ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        RET
lab252: JNZ lab253
        INR D
        INR D
        MVI H,000h
        MVI E,0A0h
        JMP lab253
lab251: LXI B,lab254
        LXI D,0991Ch
        MVI L,000h
lab263: PUSH B
        PUSH D
        MVI H,000h
lab256: LDAX B
        SUI 001h
        JZ lab255
        CALL lab248
        JNZ lab256
lab255: CALL lab257
        POP D
        LXI B,lab258
        MVI H,000h
lab260: LDAX B
        SUI 001h
        JZ lab259
        CALL lab248
        JNZ lab260
lab259: POP B
        MOV A,C
        CPI 078h
        JNZ lab261
        MVI C,048h
        INR L
        MOV A,L
        CPI 00Ah
        JZ lab262
lab261: MOV A,C
        ADI 010h
        MOV C,A
        INR D
        DCR E
        DCR E
        DCR E
        DCR E
        JMP lab263
lab257: MVI A,001h
lab266: MVI D,0E0h
lab265: MVI E,080h
lab264: DCR E
        JNZ lab264
        DCR D
        JNZ lab265
        DCR A
        JNZ lab266
        RET
lab276: LXI B,lab267
        LXI D,0AF30h
        MVI H,000h
lab269: LDAX B
        SUI 001h
        JZ lab268
        CALL lab248
        JNZ lab269
        INR D
        MVI H,000h
        MVI E,030h
        JMP lab269
lab262: LXI B,lab270
        LXI D,0B898h
        LXI H,00000h
lab278: PUSH B
        PUSH D
        MVI H,000h
lab272: LDAX B
        SUI 001h
        JZ lab271
        CALL lab248
        JNZ lab272
lab271: CALL lab257
        POP D
        LXI B,lab258
        MVI H,000h
lab274: LDAX B
        SUI 001h
        JZ lab273
        CALL lab248
        JNZ lab274
lab273: POP B
        MOV A,C
        CPI 0F0h
        JNZ lab275
        MVI C,0E0h
        INR L
        MOV A,L
        CPI 009h
        JZ lab276
        JMP lab277
lab275: MOV A,C
        ADI 010h
        MOV C,A
lab277: LXI D,0B898h
        JMP lab278
lab268: LXI B,lab279
        LXI D,099C0h
        MVI H,000h
lab281: LDAX B
        SUI 001h
        JZ lab280
        ADI 001h
        STAX D
        INX D
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lab281
        INR D
        INR D
        INR D
        INR D
        MVI H,000h
        MVI E,0C0h
        JMP lab281
lab280: MVI A,00Fh
        CALL lab266
        CALL lab230
        MVI C,01Fh
        CALL lab214
        JMP lab282
        DB 007h, 06Bh, 0BBh, 0B6h, 0BBh, 0B7h, 0B7h, 0B7h, 000h, 0B0h
        DB 07Bh, 04Bh, 0B7h, 0B7h, 0BBh
lab120: DB 07Bh, 0B7h, 00Bh, 020h, 0B4h, 0B0h, 0B7h, 0B7h, 0B7h, 000h
        DB 0B0h, 07Bh, 0B0h, 007h, 007h, 0BBh, 070h, 0BBh
lab159: DB 0BBh, 0B7h, 07Bh, 001h, 0B7h, 0B7h, 0BBh, 0B0h, 0B0h, 07Bh
        DB 0B7h, 0BBh, 0B7h, 011h, 07Bh, 010h, 000h, 01Bh, 071h, 07Bh
        DB 044h, 0B7h, 0B0h, 000h, 0BBh, 07Bh, 0B7h, 00Bh, 0BBh, 0BBh
        DB 0BBh, 0B7h, 07Bh, 0BBh, 0BBh, 070h, 0BBh
lab241: DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 07Eh, 066h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 03Ch, 066h, 066h, 066h
        DB 066h, 066h, 066h, 03Ch, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h
        DB 03Ch, 066h, 006h, 006h, 03Eh, 006h, 066h, 03Ch, 03Eh, 066h
        DB 066h, 066h, 066h, 066h, 0C6h, 0C6h, 0FEh, 060h, 060h, 060h
        DB 07Ch, 060h, 060h, 0FEh, 063h, 066h, 06Ch, 078h, 07Ch, 066h
        DB 063h, 063h, 0FEh, 0FEh, 030h, 030h, 030h, 030h, 030h, 030h
        DB 07Eh, 066h, 066h, 066h, 07Eh, 060h, 060h, 060h, 03Ch, 066h
        DB 066h, 066h, 066h, 066h, 066h, 03Ch, 066h, 066h, 066h, 066h
        DB 07Eh, 066h, 066h, 066h, 0C3h, 0E7h, 0FFh, 0DBh, 0C3h, 0C3h
        DB 0C3h, 0C3h, 03Eh, 066h, 066h, 066h, 07Eh, 066h, 066h, 066h
        DB 0DBh, 0DBh, 0DBh, 0DBh, 0DBh, 0DBh, 0FFh, 0FFh, 066h, 066h
        DB 066h, 000h, 000h, 000h, 000h, 000h, 001h, 007h, 0BBh, 0BBh
        DB 01Ah, 007h, 066h, 066h, 060h, 000h, 007h, 000h, 000h, 066h
        DB 066h, 067h
lab246: DB 03Ch, 066h, 060h, 060h, 060h, 060h, 066h, 03Ch, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 0FEh, 0FEh, 030h, 030h
        DB 030h, 030h, 030h, 030h, 07Eh, 060h, 060h, 07Eh, 066h, 066h
        DB 066h, 07Ch, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 001h, 000h, 001h, 000h
        DB 001h, 000h, 001h, 000h
lab250: DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 0FEh, 060h
        DB 060h, 060h, 07Ch, 060h, 060h, 0FEh, 07Eh, 066h, 066h, 066h
        DB 07Eh, 060h, 060h, 060h, 066h, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 066h, 066h
        DB 066h, 066h, 066h, 066h, 07Fh, 07Fh, 063h, 063h, 063h, 073h
        DB 06Bh, 06Bh, 06Bh, 073h, 001h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h
lab254: DB 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah, 001h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 07Bh, 07Bh, 007h, 007h, 00Eh, 03Eh
        DB 00Eh, 007h, 01Eh, 006h, 001h, 070h, 0BBh, 0BBh, 07Bh, 07Bh
        DB 07Bh, 072h, 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah
        DB 001h, 070h, 0BBh, 0BBh, 07Bh, 070h, 07Bh, 0BBh, 00Eh, 00Eh
        DB 0FCh, 01Fh, 038h, 0F6h, 002h, 003h, 001h, 070h, 0BBh, 0BBh
        DB 070h, 0AAh, 0AAh, 000h
lab258: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 070h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
lab267: DB 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 03Eh, 066h, 066h, 066h
        DB 066h, 066h, 0FFh, 0FFh, 000h, 000h, 000h, 000h, 000h, 060h
        DB 060h, 060h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 03Ch, 066h
        DB 066h, 066h, 066h, 066h, 066h, 03Ch, 03Ch, 066h, 066h, 066h
        DB 066h, 066h, 066h, 03Ch, 001h, 066h, 066h, 066h, 066h, 066h
        DB 066h, 067h
lab270: DB 04Eh, 04Eh, 024h, 01Eh, 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h
        DB 000h, 000h, 000h, 007h, 000h, 007h, 02Eh, 02Eh, 024h, 01Eh
        DB 00Eh, 00Dh, 00Ah, 00Ah, 001h, 0AAh, 07Ah, 000h, 007h, 000h
        DB 007h, 0BBh
lab279: DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 0FEh, 060h, 060h, 060h
        DB 07Ch, 060h, 060h, 0FEh, 0FEh, 0FEh, 030h, 030h, 030h, 030h
        DB 030h, 030h, 07Ch, 066h, 066h, 066h, 07Eh, 07Ch, 066h, 066h
        DB 030h, 030h, 000h, 030h, 030h, 030h, 030h, 030h, 03Ch, 066h
        DB 060h, 030h, 00Ch, 006h, 066h, 03Ch, 066h, 066h, 066h, 000h
        DB 000h, 000h, 000h, 000h, 001h
END