        CPU  8080
        .ORG 00000h
lab18   EQU 09000h
lab29   EQU 08800h
lab48   EQU 0F800h
lab143  EQU 01800h
lab316  EQU 0C800h
lab23   EQU 0F801h
lab59   EQU 08801h
lab44   EQU 0E60Bh
lab230  EQU 0880Dh
lab2    EQU 0C010h
lab310  EQU 09010h
lab31   EQU 0A415h
lab153  EQU 08819h
lab199  EQU 0881Ah
lab182  EQU 0881Ch
lab183  EQU 0881Eh
lab105  EQU 08820h
lab100  EQU 08821h
lab269  EQU 08822h
lab97   EQU 08823h
lab85   EQU 08824h
lab104  EQU 08825h
lab139  EQU 08826h
lab102  EQU 08829h
lab103  EQU 0882Eh
lab101  EQU 0882Fh
lab231  EQU 03C2Fh
lab28   EQU 08830h
lab84   EQU 09A30h
lab92   EQU 0A030h
lab1    EQU 08831h
lab7    EQU 08833h
lab147  EQU 0C037h
lab6    EQU 0C438h
lab38   EQU 0F243h
lab40   EQU 0F24Eh
lab41   EQU 0184Eh
lab292  EQU 0B050h
lab144  EQU 02E55h
lab3    EQU 05060h
lab77   EQU 09680h
lab98   EQU 09080h
lab293  EQU 02080h
lab264  EQU 090ADh
lab245  EQU 0A6C4h
lab242  EQU 0A6D6h
lab55   EQU 0A6DFh
lab312  EQU 090E0h
lab51   EQU 058F7h
lab52   EQU 08CF7h
lab263  EQU 0FAF8h
lab42   EQU 0F300h
lab225  EQU 0FF00h
lab72   EQU 0FF01h
lab228  EQU 0FF02h
lab224  EQU 0FF03h
lab232  EQU 02303h
lab320  EQU 07703h
lab35   EQU 0E508h
lab93   EQU 0FF08h
lab47   EQU 0B512h
lab50   EQU 08112h
lab83   EQU 0C337h
lab234  EQU 0775Eh
lab12   EQU 09F6Ch
lab22   EQU 0C170h
lab16   EQU 03F88h
lab21   EQU 08FF1h
lab56   EQU 0FFF7h
lab4    EQU 08FFCh
        LXI H,lab0
        SHLD lab1
        CALL lab2
        LXI H,lab3
        SHLD lab4
        LXI H,lab5
        CALL lab6
        LXI H,lab7
        LXI B,00300h
lab8:   MVI M,000h
        INX H
        DCR C
        JNZ lab8
        DCR B
        JNZ lab8
lab11:  CALL lab9
        CALL lab10
        JZ lab11
        CALL lab2
        LXI H,lab12
        LXI B,lab13
        LXI D,lab14
        CALL lab15
        LXI H,lab16
        SHLD lab4
        LXI H,lab17
        CALL lab6
        MVI A,055h
lab26:  INR A
        PUSH PSW
        LXI H,lab18
lab20:  POP PSW
        RLC
        MOV M,A
        INX H
        PUSH PSW
        MOV A,L
        CPI 064h
        JNZ lab19
        ADI 032h
lab19:  MOV L,A
        MOV A,H
        CPI 0C0h
        JNZ lab20
        MVI A,01Eh
lab25:  STA lab21
        PUSH PSW
        CALL lab22
        LDA lab23
        ANI 0FCh
        CPI 0FCh
        JNZ lab24
        POP PSW
        DCR A
        JNZ lab25
        MVI A,080h
        STA lab21
        POP PSW
        JMP lab26
lab24:  INX SP
        INX SP
        CALL lab10
        DCR A
        JZ lab27
        MVI A,001h
lab27:  STA lab28
lab194: LXI H,lab29
        MVI A,030h
lab30:  MVI M,000h
        INX H
        DCR A
        JNZ lab30
lab162: CALL lab2
        LXI H,lab31
        LXI D,00108h
        LXI B,lab32
        CALL lab33
        LXI B,lab34
        CALL lab15
        LXI B,lab35
        DAD B
        PUSH H
        CALL lab36
        POP H
        CALL lab37
        CALL lab37
        INR H
        CALL lab36
        LXI B,lab38
        MVI E,090h
        CALL lab39
        LXI B,lab40
        CALL lab39
        LXI B,lab41
        MVI E,009h
        CALL lab39
        LXI B,lab40
        CALL lab39
        LXI B,lab42
        DAD B
        CALL lab43
        CALL lab43
        LXI B,lab44
        DAD B
        LXI D,00108h
        LXI B,lab45
        CALL lab33
        LXI B,lab46
        CALL lab15
        LXI H,lab47
        LXI D,lab48
        MVI C,030h
        MVI B,036h
        CALL lab49
        LXI H,lab50
        MVI B,03Ah
        CALL lab49
        MVI C,041h
        MVI B,043h
        CALL lab49
        LXI H,lab51
        LXI D,00800h
        MVI B,049h
        CALL lab49
        LXI H,lab52
        MVI B,04Fh
        CALL lab49
        LXI B,00000h
        MOV A,C
lab67:  RLC
        CPI 018h
        JNC lab53
        CALL lab54
        LXI H,lab55
        LXI D,lab56
        ADD H
        JMP lab57
lab53:  SUI 018h
        CALL lab54
        MOV D,A
        MVI A,0BDh
        SUB D
        MVI L,01Bh
        LXI D,00009h
lab57:  MOV H,A
        PUSH H
        LXI H,lab58
        DAD B
        MOV A,M
        LXI H,lab59
        DAD B
        MOV M,A
        POP H
        PUSH B
        ANA A
        JZ lab60
        JM lab61
        LXI B,lab62
lab64:  CALL lab63
        DCR A
        JNZ lab64
        JMP lab60
lab63:  DAD D
        PUSH D
        LXI D,00108h
        PUSH H
        PUSH B
        CALL lab15
        POP B
        POP H
        POP D
        RET
lab61:  LXI B,lab65
lab66:  CALL lab63
        INR A
        JNZ lab66
lab60:  POP B
        INR C
        MOV A,C
        CPI 018h
        JC lab67
        LXI H,lab68
        SHLD lab4
        LXI H,lab5
        CALL lab6
        CALL lab69
        CALL lab70
        LXI H,lab71
        CALL lab6
lab73:  LDA lab72
        ANI 0FCh
        CPI 0FCh
        JNZ lab73
        LXI H,lab74
        LDA lab28
        ANA A
        JZ lab75
        LXI H,lab76
lab75:  CALL lab6
        LXI H,lab77
        CALL lab78
        PUSH PSW
        CALL lab70
        LXI H,lab79
        LDA lab28
        ANA A
        JNZ lab80
        LXI H,lab81
lab80:  CALL lab6
        LDA lab28
        ANA A
        JNZ lab82
        CALL lab83
lab82:  LXI H,lab84
        LDA lab85
        CALL lab86
        MOV C,A
        POP PSW
        SUB C
        JZ lab87
        JC lab88
        LXI H,lab89
        LDA lab28
        ANA A
        JNZ lab90
        LXI H,lab91
lab90:  CALL lab6
        CALL lab83
        LXI H,lab92
        LXI B,lab93
        CALL lab94
lab179: LXI H,lab95
        LDA lab28
        ANA A
        JNZ lab96
        LXI H,lab74
lab96:  CALL lab6
        LXI H,lab77
        CALL lab78
        PUSH PSW
        LDA lab85
        STA lab97
        LXI H,lab98
        CALL lab78
        MOV D,A
        POP PSW
        MOV E,A
        PUSH D
        CALL lab99
        CALL lab70
        POP D
        MVI A,001h
        STA lab100
lab120: STA lab101
        STA lab102
        XRA A
        STA lab103
        STA lab104
lab114: STA lab105
        MVI C,000h
lab111: CALL lab106
        MOV A,C
        INR A
        JZ lab107
        DCR A
        JNZ lab108
        LDA lab105
        ANA A
        JNZ lab109
        LDA lab104
        ANA A
        JNZ lab110
        INR A
        STA lab104
        MOV A,D
        MOV D,E
        MOV E,A
        JMP lab111
lab108: PUSH D
        CALL lab112
        MVI C,000h
        CALL lab106
        MOV A,C
        INR A
        JZ lab107
        DCR A
        JZ lab109
        CALL lab112
        POP D
        MOV A,D
        CMP E
        JNZ lab113
        LDA lab105
        ANA A
        JNZ lab113
        INR A
        JMP lab114
lab107: CALL lab70
        LDA lab28
        ANA A
        JZ lab115
        LXI H,lab116
        JMP lab117
lab115: LDA lab100
        LXI H,lab118
        ANA A
        JZ lab117
        LXI H,lab119
        JMP lab117
lab173: INR A
        JMP lab120
lab140: PUSH PSW
        LDA lab100
        ANA A
        JNZ lab121
        POP PSW
        CPI 00Ch
        JMP lab122
lab121: POP PSW
        ANA A
lab122: JNZ lab123
        MOV A,B
        SUB H
        CMP E
        JZ lab124
        JNC lab125
        CALL lab126
        JM lab124
lab125: MOV A,B
        SUB H
        CMP D
        JZ lab127
        JNC lab128
        CALL lab126
        JM lab127
lab128: LXI H,lab129
        CALL lab6
        CALL lab130
        CALL lab130
        CALL lab130
        JMP lab131
lab137: CPI 00Ch
        MVI L,007h
        MVI B,00Ch
        JNZ lab132
        JMP lab133
lab112: LDA lab100
        ANA A
        PUSH PSW
        JNZ lab131
        CALL lab134
lab131: PUSH D
        CALL lab70
        LXI H,lab135
        CALL lab6
        POP D
        CALL lab136
        MOV C,A
        MVI L,013h
        MVI B,018h
        LDA lab100
        ANA A
        MOV A,C
        JZ lab137
        ANA A
        JNZ lab132
lab133: LDA lab103
        ANA A
        JNZ lab128
lab132: MOV A,C
        CALL lab138
        DCR A
        JM lab128
        MOV H,C
        CALL lab136
        CMP H
        JZ lab128
        MOV C,A
        LDA lab139
        ANA A
        MOV A,C
        JNZ lab140
lab123: CALL lab138
        JM lab128
        LDA lab100
        ANA A
        MOV A,C
        JNZ lab141
        SUB H
        JNC lab142
        ADI 018h
        JMP lab142
lab141: SUB H
lab142: CMP E
        JZ lab124
        CMP D
        JNZ lab128
lab127: MOV A,E
        MOV E,D
        MOV D,A
        LDA lab102
        DCR A
        STA lab102
lab124: PUSH B
        PUSH H
        LXI B,lab143
        LXI H,lab144
        LDA lab100
        ANA A
        JNZ lab145
        LXI H,0097Ch
lab145: LDA lab102
        ANA A
        JZ lab146
        DAD B
lab146: SHLD lab4
        MVI C,02Ah
        CALL lab147
        POP H
        POP B
        MOV L,C
        MOV B,H
        LDA lab100
        ANA A
        JNZ lab148
        MOV A,H
        CPI 00Ch
        JNZ lab149
        MVI A,001h
        STA lab103
        JMP lab149
lab148: CALL lab150
lab149: MOV A,C
        SUI 00Ch
        JNC lab151
        ADI 018h
lab151: MOV L,A
        MOV A,B
        SUI 00Ch
        JNC lab152
        ADI 018h
lab152: MOV H,A
        CALL lab134
        LDA lab153
        INR A
        INR A
        STA lab153
        CALL lab154
        LDA lab153
        DCR A
        DCR A
        STA lab153
        CALL lab134
        MOV H,B
        MOV L,C
        MVI C,000h
        LDA lab100
        ANA A
        JNZ lab155
        MVI C,00Ch
lab155: MOV A,L
        CMP C
        JNZ lab156
        MVI L,018h
lab156: CALL lab157
        PUSH B
        PUSH D
        CALL lab70
        POP D
        POP B
        MVI E,000h
        POP PSW
        RNZ
        CALL lab134
        RET
lab126: PUSH H
        MOV A,H
lab159: DCR A
        MOV H,A
        CALL lab138
        DCR A
        JP lab158
        MOV A,H
        CMP L
        JNC lab159
lab158: POP H
        RET
lab150: MOV A,H
        ANA A
        RNZ
        INR A
        STA lab103
        RET
lab87:  LXI H,lab160
        CALL lab6
        MVI A,004h
lab161: CALL lab130
        DCR A
        JNZ lab161
        CALL lab2
        JMP lab162
lab88:  LXI H,lab163
        LDA lab28
        ANA A
        JNZ lab164
        LXI H,lab165
lab164: CALL lab6
lab168: MVI D,006h
lab167: LDA lab23
        ANI 0FCh
        CPI 0FCh
        JNZ lab166
        DCR D
        JNZ lab167
        JMP lab168
lab166: MOV A,D
        STA lab97
        LXI H,lab98
        LXI B,000F8h
        CALL lab94
lab181: LXI H,lab169
        LDA lab28
        ANA A
        PUSH PSW
        JNZ lab170
        LXI H,lab81
lab170: CALL lab6
        POP PSW
        JNZ lab171
        CALL lab83
lab171: LXI H,lab84
        LDA lab97
        CALL lab86
        LDA lab28
        ANA A
        JNZ lab172
        CALL lab83
lab172: LXI H,lab92
        LDA lab85
        CALL lab86
        LHLD lab97
        MOV D,L
        MOV E,H
        PUSH D
        CALL lab70
        POP D
        XRA A
        STA lab100
        LDA lab28
        ANA A
        JZ lab173
        DCR A
        STA lab105
        STA lab103
lab185: MOV C,A
        MOV B,A
        CALL lab174
        JNZ lab175
        INR A
        CALL lab174
        JNZ lab176
        LDA lab105
        JZ lab110
lab109: LXI H,lab177
        CALL lab6
lab110: LXI H,lab178
        CALL lab6
        LDA lab28
        ANA A
        JZ lab113
        LDA lab100
        ANA A
        JZ lab179
        LXI H,lab180
        CALL lab6
        CALL lab83
lab113: LDA lab100
        ANA A
        JZ lab179
        CALL lab99
        JMP lab181
lab175: LHLD lab182
        CALL lab150
        CALL lab154
        CALL lab157
lab176: LHLD lab183
        CALL lab150
        CALL lab154
        CALL lab157
        MOV A,C
        INR A
        JZ lab184
        LDA lab101
        ANA A
        JNZ lab109
        MOV A,D
        CMP E
        JNZ lab113
        LDA lab105
        ANA A
        JNZ lab113
        INR A
        STA lab105
        DCR A
        JMP lab185
lab184: CALL lab70
        LXI H,lab186
lab117: CALL lab6
        LDA lab29
        ANA A
        JNZ lab187
        LDA lab28
        ANA A
        JZ lab188
        LDA lab100
        ANA A
        JNZ lab188
        LXI H,lab189
        JMP lab190
lab188: LXI H,lab191
lab190: CALL lab6
lab187: LXI H,lab192
        CALL lab6
        CALL lab83
        CPI 030h
        JZ lab193
        JMP lab194
lab174: STA lab101
        CALL lab106
        MOV A,D
        MOV D,E
        MOV E,A
        CALL lab106
        MOV A,C
        ANA A
        RET
lab106: MVI H,000h
        MVI A,001h
        STA lab139
        MOV A,H
lab203: CALL lab138
        JM lab195
        JZ lab195
        MOV A,H
        ADD D
        MOV L,A
        MOV A,H
        CPI 012h
        JNC lab196
        XRA A
        STA lab139
        MOV A,H
        ANA A
        JNZ lab197
        LDA lab103
        ANA A
        JNZ lab195
lab197: MOV A,L
        CALL lab138
        JM lab195
lab206: CALL lab157
        PUSH D
        PUSH H
        MOV A,B
        ANA A
        JNZ lab198
        LDA lab101
        ANA A
        JNZ lab198
        SHLD lab199
        MOV D,E
        INR B
        CALL lab150
        CALL lab106
        DCR B
lab223: POP H
        MOV A,H
        CPI 012h
        JNC lab200
        XRA A
        STA lab139
lab200: MOV A,H
        ANA A
        JNZ lab201
        STA lab103
lab201: POP D
        CALL lab202
lab195: INR H
        MOV A,H
        CPI 018h
        JNZ lab203
        RET
lab196: LDA lab139
        ANA A
        JNZ lab204
        MOV A,L
        CPI 018h
        JNC lab195
        JMP lab197
lab204: INR A
        STA lab139
        CPI 002h
        MOV A,L
        JZ lab205
        CPI 019h
        JNC lab195
lab205: CPI 018h
        JC lab197
        MVI L,018h
        JMP lab206
lab198: PUSH B
        XRA A
        MOV D,A
        MOV E,A
        MOV B,A
        MVI C,00Ch
        MOV A,C
lab215: CALL lab138
        JZ lab207
        JM lab208
        MOV A,B
        ANA A
        JNZ lab209
        MOV A,C
        CPI 012h
        JC lab209
        CALL lab138
        ADD E
        MOV E,A
lab209: INR D
        MOV A,D
        CPI 006h
        JC lab210
        MOV A,B
        CPI 00Fh
        JC lab210
        MVI E,000h
        JMP lab211
lab207: MOV A,B
        ANA A
        JMP lab212
lab208: CMA
        INR A
        ADD B
        INR B
        DCR B
        MOV B,A
lab212: JZ lab213
        MOV A,D
        ANI 007h
        ADD E
        MOV E,A
lab213: MVI D,000h
lab210: MOV A,C
        INR A
        CPI 018h
        JNZ lab214
        XRA A
lab214: MOV C,A
        CPI 00Ch
        JNZ lab215
        XRA A
        MOV D,A
        MOV B,A
        MOV C,A
lab221: CALL lab138
        JZ lab216
        JM lab217
        INR B
        INR E
        INR E
        MOV A,D
        CPI 003h
        JC lab218
        RAR
        ADD E
        MOV E,A
        MOV A,D
        CMA
        ADD C
        CALL lab138
        JZ lab218
        JM lab218
        MOV H,A
        MOV A,B
        CPI 003h
        JMP lab219
lab216: MOV A,D
        CPI 003h
        JC lab218
        CMA
        ADD C
        CALL lab138
        JZ lab218
        JM lab218
        MOV H,A
        MOV A,B
        CPI 002h
lab219: JC lab218
        MOV A,D
        RAR
        ADD H
        ADD E
        MOV E,A
lab218: MVI D,000h
        JMP lab220
lab217: INR D
lab220: INR C
        MOV A,C
        CPI 018h
        JC lab221
        DCR B
        JP lab222
        MVI E,0FFh
        JMP lab211
lab222: LDA lab153
        RLC
        RLC
        ADD E
        MOV E,A
lab211: POP B
        MOV A,C
        CMP E
        JNC lab223
        MOV C,E
        LHLD lab199
        SHLD lab182
        POP H
        SHLD lab183
        JMP lab201
lab157: PUSH D
        XCHG
        LXI H,lab59
        MOV A,D
        ADD L
        MOV L,A
        DCR M
        SUB D
        ADD E
        MOV L,A
        INR M
        XCHG
        POP D
        RET
lab202: PUSH D
        XCHG
        LXI H,lab59
        MOV A,D
        ADD L
        MOV L,A
        INR M
        SUB D
        ADD E
        MOV L,A
        DCR M
        XCHG
        POP D
        RET
lab138: PUSH H
        LXI H,lab59
        ADD L
        MOV L,A
        MOV A,M
        ANA A
        POP H
        RET
lab10:  PUSH B
        MVI A,091h
        STA lab224
        MVI C,000h
        LDA lab225
        MVI B,008h
lab227: RAR
        JNC lab226
        INR C
        DCR B
        JNZ lab227
        LDA lab228
        MVI B,003h
lab229: RAR
        JNC lab226
        INR C
        DCR B
        JNZ lab229
lab226: MVI A,082h
        STA lab224
        MOV A,C
        CMA
        ADI 00Ch
        POP B
        RET
lab99:  MVI D,00Ch
        LXI H,lab29
        MOV E,M
        LDA lab153
        MOV M,A
        MOV A,E
        STA lab153
        INX H
        LXI B,lab230
lab233: LDAX B
        CMA
        INR A
        MOV E,M
        MOV M,A
        MOV A,E
        CMA
        INR A
        STAX B
        INX B
        INX H
        DCR D
        JNZ lab233
        RET
lab134: PUSH B
        PUSH D
        PUSH H
        MVI D,00Ch
        LXI H,lab59
        LXI B,lab230
lab235: LDAX B
        MOV E,M
        MOV M,A
        MOV A,E
        STAX B
        INX B
        INX H
        DCR D
        JNZ lab235
        POP H
        POP D
        POP B
        RET
lab136: CALL lab83
        CPI 060h
        JC lab236
        SUI 020h
lab236: CPI 030h
        JC lab136
        CPI 04Fh
        JNC lab136
        CPI 041h
        JNC lab237
        CPI 03Ah
        JNC lab136
lab237: MOV C,A
        CALL lab147
        SUI 030h
        CPI 00Ah
        RC
        SUI 007h
        RET
lab154: PUSH B
        PUSH D
        PUSH H
        MOV B,H
        MVI C,0FFh
        CALL lab238
        CALL lab239
        MVI A,050h
        CALL lab240
        POP H
        PUSH H
        MOV B,L
        MVI C,000h
        CALL lab238
        MVI A,050h
        CALL lab240
        POP H
        POP D
        POP B
        RET
lab238: MOV A,B
        RLC
        CPI 018h
        JNC lab241
        CALL lab54
        LXI H,lab242
lab246: LXI D,lab56
        ADD H
        JMP lab243
lab241: CPI 030h
        JNZ lab244
        XRA A
        LXI H,lab245
        JMP lab246
lab244: SUI 018h
        CALL lab54
        MOV D,A
        MVI A,0BDh
        SUB D
        MVI L,024h
        LXI D,00009h
lab243: MOV H,A
        LDA lab100
        ANA A
        JZ lab247
        MOV A,B
        CPI 00Ch
lab253: JNZ lab248
        MOV A,C
        ANA A
        JNZ lab248
        MVI B,018h
lab248: MOV A,B
        CALL lab138
        ADD C
        JZ lab249
lab250: DAD D
        DCR A
        JNZ lab250
lab249: LXI D,00108h
        LXI B,lab65
        LDA lab100
        ANA A
        JNZ lab251
        LXI B,lab62
lab251: MVI A,004h
lab252: CALL lab239
        PUSH PSW
        MVI A,014h
        CALL lab240
        PUSH B
        CALL lab15
        POP B
        CALL lab22
        MVI A,03Ch
        CALL lab240
        POP PSW
        DCR A
        JNZ lab252
        RET
lab247: LDA lab28
        ANA A
        JNZ lab248
        MOV A,B
        ANA A
        JMP lab253
lab54:  CPI 00Ch
        JC lab254
        INR A
lab254: RET
lab239: PUSH D
        PUSH H
lab255: MVI M,000h
        INX H
        DCR E
        JNZ lab255
        POP H
        POP D
        RET
lab86:  LXI B,lab93
        MOV D,A
        MVI A,05Eh
        CALL lab256
        CALL lab130
        RET
lab78:  LXI B,000F8h
        XRA A
        CALL lab257
        CALL lab130
        RET
lab94:  MVI A,00Bh
        LXI D,0041Fh
lab260: PUSH B
        LXI B,lab258
        CALL lab15
        POP B
        PUSH PSW
        CPI 00Bh
        JZ lab259
        MOV A,B
        ANA A
        JZ lab259
        PUSH H
        PUSH B
        PUSH D
        LXI B,lab7
        LXI D,001FBh
        DAD D
        LXI D,00205h
        CALL lab15
        INR H
        INR H
        LXI D,00114h
        CALL lab15
        POP D
        POP B
        POP H
lab259: MVI A,032h
        CALL lab240
        POP PSW
        DAD B
        DCR A
        JNZ lab260
        MOV A,B
        ANA A
        JNZ lab261
        LXI B,00508h
        JMP lab262
lab261: LXI B,lab263
lab262: DAD B
        LXI B,lab258
        CALL lab15
lab70:  LXI H,lab264
        LXI D,lab265
        LXI B,lab7
        CALL lab15
        LXI H,000B4h
        SHLD lab4
        RET
lab9:   LHLD lab1
        MVI C,010h
lab267: MOV A,H
        DAD H
        ANI 060h
        JPE lab266
        INX H
lab266: DCR C
        JNZ lab267
        SHLD lab1
        RET
lab257: PUSH PSW
        PUSH B
        PUSH H
lab268: CALL lab9
        MOV A,H
        ANI 007h
        JZ lab268
        CPI 007h
        JZ lab268
        MOV D,A
        MOV A,L
        ANI 007h
        JZ lab268
        CPI 007h
        JZ lab268
        MOV E,A
        POP H
        POP B
        CALL lab83
        MOV A,E
        STA lab85
        POP PSW
lab256: PUSH D
        LXI D,0041Fh
        PUSH B
lab289: STA lab269
        ANI 007h
        JNZ lab270
        POP B
        PUSH B
        MOV A,B
        ANA A
        JNZ lab271
        DAD B
lab271: LXI B,lab272
        JMP lab273
lab270: CPI 001h
        JNZ lab274
        LXI B,lab275
        JMP lab273
lab274: CPI 002h
        JNZ lab276
        LXI B,lab277
        JMP lab273
lab276: CPI 003h
        JNZ lab278
        LXI B,lab279
        JMP lab273
lab278: CPI 004h
        JNZ lab280
        LXI B,lab281
        JMP lab273
lab280: CPI 005h
        JNZ lab282
        LXI B,lab283
        JMP lab273
lab282: CPI 006h
        JNZ lab284
        LXI B,lab285
        JMP lab273
lab284: POP B
        PUSH B
        MOV A,B
        ANA A
        JZ lab286
        DAD B
lab286: LXI B,lab258
lab273: CALL lab15
        MVI A,00Ah
        CALL lab240
        LDA lab269
        INR A
        POP B
        PUSH B
        INR B
        JNZ lab287
        DCR A
        DCR A
        CPI 00Eh
        JMP lab288
lab287: CPI 050h
lab288: JNZ lab289
        POP B
        POP PSW
        PUSH PSW
        LXI B,0010Ah
        DAD B
        PUSH H
        LXI H,lab290
        LXI B,00014h
lab291: DAD B
        DCR A
        JNZ lab291
        MOV B,H
        MOV C,L
        POP H
        LXI D,0020Ah
        CALL lab15
        POP PSW
        RET
lab69:  LXI H,lab84
        LXI D,0041Fh
        LXI B,lab258
        CALL lab15
        LXI H,lab77
        LXI B,lab258
        CALL lab15
        RET
lab130: PUSH PSW
        PUSH H
        LXI H,lab292
        SHLD lab21
        CALL lab22
        MVI L,048h
        SHLD lab21
        CALL lab22
        LXI H,lab293
        SHLD lab21
        POP H
        POP PSW
        RET
lab49:  SHLD lab4
        CALL lab147
        DAD D
        INR C
        MOV A,C
        CMP B
        JC lab49
        RET
lab33:  CALL lab15
        MVI A,01Ah
lab295: INR H
        LXI B,lab294
        CALL lab15
        DCR A
        JNZ lab295
        INR H
        RET
lab39:  DAD B
        MVI A,0B2h
lab296: MOV M,E
        INR L
        DCR A
        JNZ lab296
        RET
lab36:  LXI D,00102h
        MVI A,064h
lab298: LXI B,lab297
        CALL lab15
        INR L
        INR L
        DCR A
        JNZ lab298
        RET
lab37:  LXI D,0010Bh
        LXI B,lab299
        INR H
        CALL lab15
        MVI D,002h
        MVI A,005h
lab301: INR H
        LXI B,lab300
        CALL lab15
        INR H
        DCR A
        JNZ lab301
        INR H
        MVI D,001h
        LXI B,lab300
        CALL lab15
        INR H
        LXI B,lab302
        CALL lab15
        RET
lab43:  LXI D,0010Bh
        LXI B,lab303
        INR H
        CALL lab15
        MVI D,002h
        MVI A,005h
lab305: INR H
        LXI B,lab304
        CALL lab15
        INR H
        DCR A
        JNZ lab305
        INR H
        MVI D,001h
        LXI B,lab304
        CALL lab15
        INR H
        LXI B,lab306
        CALL lab15
        RET
lab15:  PUSH PSW
        PUSH B
        MOV C,D
lab308: MOV B,E
lab307: XTHL
        MOV A,M
        INX H
        XTHL
        MOV M,A
        INX H
        DCR B
        JNZ lab307
        INR H
        MOV A,L
        SUB E
        MOV L,A
        DCR C
        JNZ lab308
        MOV A,H
        SUB D
        MOV H,A
        POP B
        POP PSW
        RET
lab240: PUSH B
        MVI C,000h
lab309: DCR C
        JNZ lab309
        DCR A
        JNZ lab309
        POP B
        RET
        DB 0F5h, 03Eh, 000h, 0CDh, 0B9h, 00Ah, 0F1h, 03Dh, 0C2h, 0C6h
        DB 00Ah, 0C9h
lab193: CALL lab2
        LXI H,lab310
        CALL lab311
        LXI H,lab312
        CALL lab311
        LXI H,00019h
        SHLD lab4
        LXI H,lab313
        MVI D,006h
lab315: CALL lab314
        INX H
        DCR D
        JNZ lab315
        CALL lab83
        CALL lab2
        JMP lab316

lab5:   DB 02Ah, 020h, 041h, 044h, 044h, 020h, 02Ah, 000h, 020h, 02Ah
        DB 020h, 031h, 039h, 038h, 039h, 020h, 02Ah
lab317: DB 00Ah, 00Ah, 00Ah, 020h, 020h, 02Ah, 020h, 041h, 044h, 044h
        DB 020h, 02Ah, 020h, 020h, 02Dh, 020h, 020h, 0FCh, 0F4h, 0EFh
        DB 020h, 000h
lab313: DB 0F0h, 0F2h, 0EFh, 0E7h, 0F2h, 0E1h, 0EDh, 0EDh, 0EEh, 0EFh
        DB 0E5h, 020h, 0EFh, 0E2h, 0E5h, 0F3h, 0F0h, 0E5h, 0FEh, 0E5h
        DB 0EEh, 0E9h, 0E5h, 020h, 0F0h, 02Eh, 0EBh, 02Eh, 020h, 022h
        DB 0F3h, 0F0h, 0E5h, 0E3h, 0E9h, 0E1h, 0ECh, 0E9h, 0F3h, 0F4h
        DB 022h, 000h, 022h, 0CDh, 0EFh, 0CEh, 0C9h, 0F4h, 0EFh, 0D2h
        DB 020h, 02Dh, 020h, 0D3h, 022h, 000h, 0EEh, 0EFh, 0F7h, 0F9h
        DB 0E5h, 020h, 0E9h, 0E7h, 0F2h, 0EFh, 0F7h, 0F9h, 0E5h, 020h
        DB 0F0h, 0F2h, 0EFh, 0E7h, 0F2h, 0E1h, 0EDh, 0EDh, 0F9h, 000h
        DB 0F0h, 0E1h, 0EBh, 0E5h, 0F4h, 020h, 022h, 0EDh, 0C9h, 0C3h
        DB 0D2h, 0EFh, 0CEh, 022h, 020h, 0E4h, 0ECh, 0F1h, 020h, 022h
        DB 0F3h, 0F0h, 0E5h, 0E3h, 0E9h, 0E1h, 0ECh, 0E9h, 0F3h, 0F4h
        DB 0E1h, 022h, 000h, 0EBh, 0F7h, 0E1h, 0ECh, 0E9h, 0E6h, 0E9h
        DB 0E3h, 0E9h, 0F2h, 0EFh, 0F7h, 0E1h, 0EEh, 0EEh, 0F9h, 0E5h
        DB 020h, 0EBh, 0EFh, 0EEh, 0F3h, 0F5h, 0ECh, 0F8h, 0F4h, 0E1h
        DB 0E3h, 0E9h, 0E9h, 000h, 0E4h, 0EEh, 0E5h, 0F0h, 0F2h, 0EFh
        DB 0F0h, 0E5h, 0F4h, 0F2h, 0EFh, 0F7h, 0F3h, 0EBh, 0E9h, 0EAh
        DB 020h, 022h, 0F4h, 0EFh, 0ECh, 0FEh, 0EFh, 0EBh, 022h, 02Ch
        DB 020h, 0F7h, 0EFh, 0F3h, 0EBh, 0F2h, 0E5h, 0F3h, 0E5h, 0EEh
        DB 0F8h, 0E5h, 02Eh, 000h
lab319: DB 0FFh, 0FFh, 060h, 070h, 030h, 038h, 038h, 018h, 018h, 038h
        DB 038h, 030h, 070h, 060h, 0FFh, 0FFh, 0FFh, 0FFh, 000h, 000h
        DB 00Fh, 00Bh, 013h, 013h, 023h, 03Fh, 043h, 0C3h, 000h, 000h
        DB 0FFh, 0FFh, 0FFh, 0FFh, 000h, 000h, 078h, 064h, 066h, 066h
        DB 066h, 066h, 064h, 078h, 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh
        DB 000h, 000h, 0F0h, 0C8h, 0CCh, 0CCh, 0CCh, 0CCh, 0C8h, 0F0h
        DB 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh, 006h, 00Eh, 00Ch, 01Ch
        DB 01Ch, 018h, 018h, 01Ch, 01Ch, 00Ch, 00Eh, 006h, 0FFh, 0FFh
lab314: PUSH H
        LXI H,lab317
        CALL lab318
        POP H
lab318: MOV A,M
        ANI 07Fh
        RZ
        INX H
        MOV C,A
        CALL lab147
        JMP lab318
lab311: MVI A,008h
lab323: PUSH PSW
        LXI B,lab319
        LXI D,00510h
lab322: PUSH D
lab321: LDAX B
        INX B
        MOV M,A
        INX H
        DCR E
        JNZ lab321
        LXI D,000F0h
        DAD D
        POP D
        DCR D
        JNZ lab322
        INR H
        POP PSW
        DCR A
        JNZ lab323
        RET

lab17:  DB 031h, 020h, 02Dh, 020h, 069h, 067h, 072h, 061h, 020h, 073h
        DB 020h, 070h, 061h, 072h, 074h, 06Eh, 065h, 072h, 06Fh, 06Dh
        DB 00Ah, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 032h, 020h, 02Dh, 020h, 069h, 067h, 072h, 061h
        DB 020h, 073h, 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h, 074h
        DB 065h, 072h, 06Fh, 06Dh, 000h
lab71:  DB 020h, 072h, 061h, 07Ah, 079h, 067h, 072h, 061h, 065h, 06Dh
        DB 020h, 070h, 072h, 061h, 077h, 06Fh, 020h, 068h, 06Fh, 064h
        DB 061h, 00Ah, 000h
lab76:  DB 020h, 062h, 072h, 06Fh, 073h, 061h, 06Ah, 020h, 073h, 06Eh
        DB 061h, 07Eh, 061h, 06Ch, 061h, 020h, 074h, 079h, 000h
lab74:  DB 020h, 062h, 065h, 06Ch, 079h, 065h, 020h, 062h, 072h, 06Fh
        DB 073h, 061h, 060h, 074h, 000h
lab81:  DB 020h, 07Eh, 065h, 072h, 06Eh, 079h, 065h, 020h, 062h, 072h
        DB 06Fh, 073h, 061h, 060h, 074h, 000h
lab79:  DB 020h, 074h, 065h, 070h, 065h, 072h, 078h, 020h, 071h, 000h
lab89:  DB 00Ah, 00Ah, 020h, 075h, 020h, 06Dh, 065h, 06Eh, 071h, 020h
        DB 06Dh, 065h, 06Eh, 078h, 07Bh, 065h, 00Ah, 020h, 074h, 065h
        DB 062h, 065h, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h
        DB 078h, 000h
lab163: DB 00Ah, 00Ah, 020h, 075h, 020h, 06Dh, 065h, 06Eh, 071h, 020h
        DB 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h, 045h, 058h
        DB 043h, 055h, 05Ah, 045h, 020h, 04Dh, 045h, 000h
lab91:  DB 00Ah, 00Ah, 020h, 075h, 020h, 062h, 065h, 06Ch, 079h, 068h
        DB 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h, 069h
        DB 06Dh, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h, 078h
        DB 000h
lab165: DB 00Ah, 00Ah, 020h, 075h, 020h, 07Eh, 065h, 072h, 06Eh, 079h
        DB 068h, 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h
        DB 069h, 06Dh, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h
        DB 078h, 000h
lab160: DB 00Ah, 00Ah, 020h, 070h, 06Fh, 072h, 06Fh, 077h, 06Eh, 075h
        DB 02Eh, 00Ah, 020h, 070h, 072h, 069h, 064h, 065h, 074h, 073h
        DB 071h, 020h, 070h, 06Fh, 077h, 074h, 06Fh, 072h, 069h, 074h
        DB 078h, 000h
lab95:  DB 020h, 062h, 072h, 06Fh, 073h, 061h, 06Ah, 000h
lab169: DB 020h, 062h, 072h, 06Fh, 073h, 061h, 060h, 000h
lab119: DB 020h, 062h, 065h, 06Ch, 079h, 065h, 020h, 077h, 079h, 069h
        DB 067h, 072h, 061h, 06Ch, 069h, 000h
lab118: DB 020h, 07Eh, 065h, 072h, 06Eh, 079h, 065h, 020h, 077h, 079h
        DB 069h, 067h, 072h, 061h, 06Ch, 069h, 000h
lab191: DB 00Ah, 020h, 070h, 06Fh, 07Ah, 064h, 072h, 061h, 077h, 06Ch
        DB 071h, 060h, 020h, 073h, 020h, 06Dh, 061h, 072h, 073h, 06Fh
        DB 06Dh, 021h, 000h
lab189: DB 00Ah, 020h, 065h, 073h, 074h, 078h, 020h, 06Ch, 069h, 020h
        DB 076h, 069h, 07Ah, 06Eh, 078h, 020h, 06Eh, 061h, 020h, 06Dh
        DB 061h, 072h, 073h, 065h, 03Fh, 000h
lab129: DB 00Ah, 020h, 06Eh, 065h, 06Ch, 078h, 07Ah, 071h, 021h, 021h
        DB 021h, 000h
lab177: DB 00Ah, 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 000h
lab178: DB 020h, 068h, 06Fh, 064h, 06Fh, 077h, 020h, 06Eh, 065h, 020h
        DB 077h, 069h, 076h, 075h, 02Eh, 00Ah, 000h
lab180: DB 020h, 06Dh, 06Fh, 067h, 075h, 020h, 062h, 072h, 06Fh, 073h
        DB 061h, 074h, 078h, 03Fh, 00Ah, 000h
lab135: DB 00Ah, 020h, 068h, 06Fh, 064h, 069h, 020h, 000h
lab186: DB 020h, 071h, 020h, 077h, 073h, 065h, 067h, 064h, 061h, 020h
        DB 067h, 06Fh, 077h, 06Fh, 072h, 069h, 06Ch, 02Ch, 00Ah, 020h
        DB 07Eh, 074h, 06Fh, 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h
        DB 074h, 065h, 072h, 020h, 075h, 06Dh, 06Eh, 065h, 065h, 00Ah
        DB 020h, 07Eh, 065h, 06Ch, 06Fh, 077h, 065h, 06Bh, 061h, 020h
        DB 021h, 000h
lab116: DB 020h, 071h, 020h, 070h, 06Fh, 07Eh, 065h, 06Dh, 075h, 02Dh
        DB 074h, 06Fh, 020h, 070h, 072h, 06Fh, 069h, 067h, 072h, 061h
        DB 06Ch, 02Eh, 02Eh, 02Eh, 000h
lab192: DB 00Ah, 00Ah, 020h, 065h, 07Dh, 065h, 020h, 070h, 061h, 072h
        DB 074h, 069h, 060h, 020h, 03Fh, 000h
lab13:  DB 0FCh, 078h, 078h, 078h, 078h, 078h, 078h, 078h, 07Fh, 07Fh
        DB 078h, 078h, 078h, 0FCh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0FFh, 0FFh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0FCh, 078h, 078h, 078h, 078h, 078h, 078h, 078h
        DB 0F8h, 0F8h, 078h, 078h, 078h, 0FCh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 031h, 07Bh, 07Fh
        DB 07Eh, 03Ch, 000h, 000h, 000h, 001h, 003h, 007h, 00Eh, 01Ch
        DB 038h, 070h, 0FFh, 0FFh, 080h, 000h, 000h, 000h, 000h, 000h
        DB 0FFh, 0FEh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 0FEh, 0FEh
        DB 01Eh, 01Eh, 01Eh, 03Fh, 000h, 000h, 003h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 003h
        DB 000h, 000h, 0FFh, 0FFh, 0E0h, 0E0h, 0E0h, 0E0h, 0E0h, 0E0h
        DB 0FFh, 0FFh, 0E0h, 0E0h, 0E0h, 0F0h, 000h, 000h, 0FEh, 0FFh
        DB 003h, 001h, 001h, 001h, 001h, 003h, 0FFh, 0FEh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 080h, 0C0h, 0E0h, 0E0h, 0E0h
        DB 0E0h, 0C0h, 080h, 000h, 000h, 000h, 007h, 003h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 001h, 003h, 007h, 00Eh
        DB 01Ch, 038h, 0FFh, 0FFh, 080h, 080h, 007h, 00Fh, 01Ch, 038h
        DB 070h, 0E0h, 0C0h, 080h, 000h, 000h, 000h, 000h, 0FFh, 0FFh
        DB 000h, 000h, 0F8h, 0F0h, 0F0h, 0F0h, 0F0h, 0F0h, 0F0h, 0F0h
        DB 0F0h, 0F0h, 0F0h, 0F0h, 0FFh, 0FEh, 00Ch, 008h, 00Fh, 007h
        DB 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h
        DB 007h, 00Fh, 000h, 000h, 0C0h, 080h, 080h, 080h, 0FFh, 0FFh
        DB 080h, 080h, 080h, 080h, 080h, 080h, 0FFh, 0FFh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0F0h, 0FCh, 01Eh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 01Eh, 0FCh, 0F0h, 000h, 000h, 03Fh, 01Eh, 01Eh, 01Eh
        DB 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 01Eh, 03Fh
lab272: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0FFh, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 0FFh, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0FFh, 001h, 001h, 001h, 001h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 0FFh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0F0h, 010h, 010h, 010h, 010h, 010h, 010h
        DB 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h, 0F0h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab275: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 01Fh, 010h, 010h
        DB 010h, 010h, 020h, 020h, 020h
lab68:  DB 020h, 020h, 040h, 040h, 040h, 040h, 07Fh, 020h, 020h, 010h
        DB 010h, 00Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h, 000h
        DB 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0E0h, 030h, 030h, 028h, 028h, 044h
        DB 044h, 044h, 044h, 044h, 088h, 088h, 088h, 088h, 088h, 050h
        DB 050h, 030h, 030h, 0F0h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h
lab277: DB 000h, 000h, 000h, 000h, 00Fh, 008h, 008h, 008h, 010h, 010h
        DB 010h, 010h, 020h, 020h, 020h, 03Fh, 020h, 020h, 020h, 010h
        DB 010h, 010h, 010h, 008h, 008h, 008h, 00Fh, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0F0h, 010h, 010h, 010h
        DB 028h, 028h, 028h, 028h, 044h, 044h, 044h, 0C4h, 044h, 044h
        DB 044h, 028h, 028h, 028h, 028h, 010h, 010h, 010h, 0F0h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h
lab279: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 002h
        DB 004h, 00Fh, 008h, 008h, 008h, 008h, 008h, 008h, 008h
lab14:  DB 008h, 008h, 008h, 008h, 008h, 008h, 008h, 00Fh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0FFh, 003h, 005h, 009h, 0F1h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 012h, 014h, 018h, 0F0h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab281: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Fh, 008h, 008h
        DB 008h, 008h, 008h, 008h, 008h, 008h, 008h, 008h, 008h, 008h
        DB 008h, 008h, 00Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h
lab283: DB 000h, 000h, 000h, 001h, 001h, 001h, 001h, 001h, 002h, 002h
        DB 002h, 002h, 002h, 004h, 004h, 004h, 004h, 007h, 002h, 002h
        DB 001h, 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0FEh, 003h, 003h, 002h, 002h
        DB 004h, 004h, 004h
lab0:   DB 004h, 004h, 008h, 008h, 008h, 008h, 0F8h, 005h, 005h, 003h
        DB 003h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 080h, 080h, 040h, 040h
        DB 040h, 040h, 040h, 080h, 080h, 080h, 080h, 080h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h
lab285: DB 000h, 000h, 000h, 000h, 001h, 001h, 001h, 001h, 002h, 002h
        DB 002h, 003h, 002h, 002h, 002h, 001h, 001h, 001h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0FFh, 080h, 080h, 080h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 080h, 080h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0FFh, 001h, 001h, 001h, 002h, 002h, 002h, 002h
        DB 004h, 004h, 004h, 0FCh, 004h, 004h, 004h, 002h, 002h, 002h
        DB 002h, 001h, 001h, 001h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 080h, 080h
        DB 080h, 040h, 040h, 040h, 040h, 040h, 040h, 040h, 080h, 080h
        DB 080h, 080h
lab258: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 00Fh, 010h, 020h, 040h, 0FFh, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
        DB 080h, 080h, 080h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 0FFh
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0F0h, 030h, 050h, 090h
        DB 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h
        DB 010h, 010h, 020h, 040h, 080h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lab294: DB 0FFh, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0FFh
lab32:  DB 0FFh, 0AAh, 0D5h, 0AAh, 0D5h, 0AAh
lab297: DB 0D5h, 0ABh
lab299: DB 0FFh, 080h, 080h, 09Fh, 090h, 090h, 090h, 091h, 092h, 094h
        DB 09Ch
lab300: DB 0FFh, 000h, 000h, 0FFh, 000h, 03Ch, 0C3h, 000h, 000h, 000h
        DB 000h, 0FFh, 000h, 000h, 0FFh, 000h, 000h, 000h, 081h, 042h
        DB 024h, 03Ch
lab34:  DB 0FFh, 0ABh, 055h, 0ABh, 055h, 0ABh, 055h, 0ABh
lab302: DB 0FFh, 001h, 001h, 0F9h, 009h, 009h, 009h, 089h, 049h, 029h
        DB 039h
lab45:  DB 0ABh, 0D5h, 0AAh, 0D5h, 0AAh, 0D5h, 0AAh, 0FFh
lab303: DB 09Ch, 094h, 092h, 091h, 090h, 090h, 090h, 09Fh, 080h, 080h
        DB 0FFh
lab304: DB 000h, 000h, 000h, 000h, 0C3h, 03Ch, 000h, 0FFh, 000h, 000h
        DB 0FFh, 03Ch, 024h, 042h, 081h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 0FFh
lab46:  DB 0ABh, 055h, 0ABh, 055h, 0ABh, 055h, 0ABh, 0FFh
lab306: DB 039h, 029h, 049h, 089h, 009h, 009h, 009h
lab290: DB 0F9h, 001h, 001h, 0FFh
lab65:  DB 03Ch, 07Eh, 0FFh, 0E7h, 0E7h, 0FFh, 07Eh, 03Ch
lab62:  DB 03Ch, 042h, 081h, 099h, 099h, 081h, 042h, 03Ch, 080h, 080h
        DB 080h, 080h, 081h, 081h, 080h, 080h, 080h, 080h, 001h, 001h
        DB 001h, 001h, 081h, 081h, 001h, 001h, 001h, 001h, 080h, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 098h, 098h, 019h, 019h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 080h, 080h
        DB 080h, 080h, 081h, 081h, 080h, 080h, 098h, 098h, 019h, 019h
        DB 001h, 001h, 081h, 081h, 001h, 001h, 001h, 001h, 098h, 098h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 098h, 098h, 019h
lab265: DB 019h, 001h, 001h, 001h, 001h, 001h, 001h, 019h, 019h, 098h
        DB 098h, 080h, 080h, 081h, 081h, 080h, 080h, 098h, 098h, 019h
        DB 019h, 001h, 001h, 081h, 081h, 001h, 001h, 019h, 019h, 098h
        DB 098h, 080h, 080h, 098h, 098h, 080h, 080h, 098h, 098h, 019h
        DB 019h, 001h, 001h, 019h, 019h, 001h, 001h, 019h, 019h
lab58:  DB 00Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0F1h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0FFh, 0FFh
END