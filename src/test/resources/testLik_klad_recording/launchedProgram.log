        CPU  8080
        .ORG 00000h
lab231  EQU 0C000h
lab243  EQU 09000h
lab266  EQU 09004h
lab44   EQU 0B40Ah
lab257  EQU 0C010h
lab258  EQU 07A13h
lab61   EQU 09E14h
lab172  EQU 09A14h
lab177  EQU 0A414h
lab269  EQU 09418h
lab275  EQU 09618h
lab278  EQU 0BC18h
lab280  EQU 09820h
lab98   EQU 0C427h
lab5    EQU 0C037h
lab237  EQU 0A074h
lab239  EQU 0A07Ch
lab241  EQU 0A084h
lab313  EQU 0B898h
lab323  EQU 0A0C0h
lab267  EQU 090FAh
lab268  EQU 090FEh
lab106  EQU 0FF00h
lab260  EQU 0BF00h
lab105  EQU 0FF01h
lab107  EQU 0FF02h
lab104  EQU 0FF03h
lab12   EQU 09B0Ah
lab273  EQU 07B13h
lab15   EQU 09B14h
lab47   EQU 0B314h
lab274  EQU 09318h
lab276  EQU 0B918h
lab277  EQU 0BB18h
lab295  EQU 0991Ch
lab285  EQU 0AB20h
lab23   EQU 09128h
lab25   EQU 0B928h
lab27   EQU 0A130h
lab309  EQU 0AF30h
lab29   EQU 0A154h
lab236  EQU 0C170h
lab290  EQU 099A0h
lab31   EQU 0A1CCh
lab1    EQU 08FE1h
lab19   EQU 0AFE6h
lab17   EQU 09DEEh
lab21   EQU 0AFEEh
lab235  EQU 08FF1h
lab234  EQU 08FF2h
lab230  EQU 0FFFEh
lab252  EQU 0FFFFh
lab247: JMP lab0
lab326: LXI H,00000h
        SHLD lab1
        LXI H,lab2
        SHLD lab3
        LXI H,00700h
        SHLD lab4
        MVI C,01Fh
        CALL lab5
        MVI A,005h
        STA lab6
        XRA A
        STA lab7
        STA lab8
        STA lab9
        MVI A,03Ah
        STA lab10
        CALL lab11
        LXI H,lab12
        LXI D,lab13
        CALL lab14
        LXI H,lab15
        LXI D,lab16
        CALL lab14
        LXI H,lab17
        LXI D,lab18
        CALL lab14
        LXI H,lab19
        LXI D,lab20
        CALL lab14
        LXI H,lab21
        LXI D,lab22
        CALL lab14
        LXI H,lab23
        CALL lab24
        LXI H,lab25
        CALL lab24
lab251: LXI D,lab2
        CALL lab26
        LXI H,lab27
        LXI D,lab28
        CALL lab14
        LXI H,lab29
        LXI D,lab30
        CALL lab14
        LXI H,lab31
        LXI D,lab32
        NOP
        NOP
        NOP
        CALL lab33
        LDA lab34
        CPI 008h
        JNZ lab35
        LXI H,lab36
        MVI D,002h
        MVI E,031h
lab35:  CPI 00Bh
        JNZ lab37
        LXI H,lab38
        MVI D,004h
        MVI E,032h
lab37:  CPI 00Eh
        JNZ lab39
        LXI H,00900h
        MVI D,006h
        MVI E,033h
lab39:  CPI 011h
        JNZ lab40
        LXI H,00600h
        MVI D,008h
        MVI E,034h
lab40:  CPI 014h
        JNZ lab41
        LXI H,00400h
        MVI D,00Bh
        MVI E,035h
lab41:  CPI 017h
        JNZ lab42
        LXI H,00200h
        MVI D,00Eh
        MVI E,036h
lab42:  SHLD lab4
        MOV A,D
        STA lab43
        LXI H,lab44
        MOV A,E
        CALL lab45
lab50:  LHLD lab3
        LXI D,00170h
        DAD D
        SHLD lab3
        LDA lab9
        INR A
        DAA
        CPI 031h
        JZ lab46
        STA lab9
        XCHG
        LXI H,lab47
        PUSH D
        CALL lab48
        POP D
        CALL lab49
        JMP lab50
lab49:  CALL lab26
lab33:  MVI B,003h
lab57:  PUSH B
        LHLD lab4
        XCHG
        CALL lab51
        MVI A,001h
        CALL lab52
        CALL lab53
        CALL lab54
        CALL lab55
        CALL lab56
        POP B
        DCR B
        JZ lab33
        PUSH B
        MVI A,002h
        CALL lab52
        MVI A,003h
        CALL lab52
        POP B
        JMP lab57
lab24:  MVI B,016h
lab59:  LXI D,lab58
        PUSH H
        CALL lab14
        POP H
        MOV A,L
        ADI 008h
        MOV L,A
        DCR B
        JNZ lab59
        RET
lab56:  LDA lab10
        CPI 03Ah
        RNC
        CPI 039h
        JZ lab60
        INR A
        STA lab10
        LXI H,lab61
        JMP lab45
lab26:  LXI H,lab34
        PUSH H
        LXI H,lab62
        MVI C,010h
lab64:  LDAX D
        MOV M,A
        INX H
        XTHL
        MOV M,A
        INX H
        XTHL
        INX D
        DCR C
        JNZ lab64
        POP H
        PUSH D
        POP B
        XCHG
        MVI L,028h
lab67:  MVI H,098h
lab66:  XCHG
        LDAX B
        RRC
        RRC
        RRC
        RRC
        ANI 00Fh
        MOV M,A
        INX H
        XCHG
        CALL lab45
        INR H
        XCHG
        LDAX B
        ANI 00Fh
        MOV M,A
        INX H
        XCHG
        CALL lab45
        INR H
        INX B
        MOV A,H
        CPI 0B8h
        JNZ lab66
        MOV A,L
        ADI 008h
        MOV L,A
        CPI 0D8h
        JNZ lab67
        MVI A,001h
        CALL lab68
        MVI A,018h
        STA lab69
        LDA lab70
        STA lab71
        XRA A
        STA lab72
        STA lab73
        INR A
        STA lab74
        LXI H,lab75
        MVI C,081h
lab76:  MVI M,000h
        INX H
        DCR C
        JNZ lab76
        RET
lab45:  PUSH B
        PUSH D
        PUSH H
        LXI H,lab77
        MVI D,000h
        MVI C,003h
        MOV E,A
lab78:  MOV A,E
        STC
        CMC
        RAL
        MOV E,A
        MOV A,D
        RAL
        MOV D,A
        DCR C
        JNZ lab78
        DAD D
        XCHG
        POP H
        PUSH H
        MVI C,008h
lab79:  LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lab79
        POP H
        POP D
        POP B
        RET
lab97:  MOV A,M
        ADI 098h
        MOV D,A
        INX H
        MOV C,M
        INX H
        MOV A,M
        RLC
        RLC
        RLC
        ADI 028h
        INX H
        ADD M
        ADD M
        MOV E,A
        MOV B,M
        INX H
        MOV A,M
        LXI H,lab80
        PUSH D
        LXI D,00000h
        CPI 003h
        JC lab81
        CPI 005h
        JZ lab82
        MOV A,B
        RAR
        JC lab82
        MVI E,010h
        JMP lab82
lab81:  MVI E,020h
        CPI 001h
        JZ lab83
        MVI E,060h
lab83:  MOV A,C
        RLC
        RLC
        RLC
        RLC
        ADD E
        MOV E,A
lab82:  DAD D
        POP D
        PUSH D
        CALL lab84
        POP D
        INR D
        CALL lab84
        RET
lab84:  MVI C,008h
lab87:  LDAX D
        XRA M
        STAX D
        INX D
        INX H
        DCR C
        JNZ lab87
        RET
lab96:  PUSH PSW
        CALL lab88
        POP PSW
        XCHG
        CALL lab89
        MVI C,005h
        MOV A,B
        ORA A
        JZ lab90
lab92:  LDAX D
        MOV M,A
        INX D
        INX H
        DCR C
        JNZ lab92
        RET
lab90:  MOV A,M
        STAX D
        INX D
        INX H
        DCR C
        JNZ lab90
        RET
lab94:  MOV C,A
        MVI A,0FBh
lab93:  ADI 005h
        DCR C
        JNZ lab93
        PUSH D
        MVI D,000h
        MOV E,A
        DAD D
        POP D
        RET
lab88:  LXI H,lab34
        JMP lab94
lab89:  LXI H,lab95
        JMP lab94
lab212: LXI H,lab62
        JMP lab94
lab68:  PUSH PSW
        MVI B,001h
        CALL lab96
        POP PSW
        CALL lab88
        JMP lab97
lab191: PUSH PSW
        CALL lab88
        CALL lab97
        POP PSW
        PUSH PSW
        CALL lab89
        CALL lab97
        POP PSW
        MVI B,001h
        JMP lab96
lab51:  PUSH H
        LXI H,00000h
lab99:  INX H
        CALL lab98
        JNZ lab99
        POP H
        RET
lab160: PUSH PSW
        CALL lab88
        INX H
        LDA lab100
        ADD M
        MOV M,A
        CALL lab101
        INX H
        INX H
        LDA lab102
        ADD M
        MOV M,A
        CALL lab101
        POP PSW
        RET
lab101: ORA A
        JM lab103
        CPI 004h
        RNZ
        DCX H
        INR M
        INX H
        MVI M,000h
        RET
lab103: DCX H
        DCR M
        INX H
        MVI M,003h
        RET
lab123: PUSH B
        MVI A,091h
        STA lab104
        MVI A,0FBh
        STA lab105
        LDA lab106
        ANI 03Fh
        MOV B,A
        LDA lab107
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
lab52:  STA lab108
        CALL lab88
        SHLD lab109
        LDA lab108
        CPI 002h
        PUSH H
        CNC lab110
        POP H
        MOV D,M
        INX H
        SHLD lab111
        INX H
        MOV E,M
        INX H
        SHLD lab112
        INX H
        SHLD lab113
        XCHG
        SHLD lab114
        PUSH D
        CALL lab115
        SHLD lab116
        POP D
        LDAX D
        CPI 005h
        JZ lab118
        CALL lab115
        SHLD lab116
        INR L
        CALL lab119
        MOV A,M
        ANI 00Fh
        CPI 006h
        JNC lab120
        LHLD lab116
        CALL lab119
        MOV A,M
        ANI 00Fh
        CPI 007h
        JZ lab120
        JC lab118
        LDA lab108
        CPI 002h
        JC lab120
        JMP lab118
lab122: LHLD lab113
        MVI M,005h
        JMP lab121
lab118: LDA lab108
        CPI 002h
        JNC lab122
        CALL lab123
        CPI 024h
        JNZ lab124
        CALL lab125
        JMP lab122
lab124: CPI 030h
        JNZ lab126
        CALL lab127
        JMP lab122
lab128: LXI H,lab69
        SUI 002h
        ADD L
        MOV L,A
        MOV A,H
        ACI 000h
        MOV H,A
        RET
lab110: CALL lab128
        MOV A,M
        ORA A
        RZ
        CPI 0FFh
        JZ lab129
        DCR A
        MOV M,A
        JZ lab130
lab129: POP H
        POP H
        RET
lab130: LDA lab108
        CALL lab68
        POP H
        POP H
        RET
lab115: LHLD lab114
        XCHG
        LHLD lab111
        CALL lab131
        ADD D
        MOV D,A
        LHLD lab112
        CALL lab131
        ADD E
        MOV E,A
        XCHG
        RET
lab131: MOV A,M
        CPI 002h
        MVI A,000h
        RC
        INR A
        RET
lab119: LXI D,00000h
        MOV E,L
        MVI C,005h
lab132: STC
        CMC
        MOV A,E
        RAL
        MOV E,A
        MOV A,D
        RAL
        MOV D,A
        DCR C
        JNZ lab132
        MOV C,H
        LXI H,lab133
        DAD D
        MVI B,000h
        DAD B
        RET
lab140: LHLD lab116
        XCHG
        LHLD lab109
        MOV M,D
lab134: INX H
        MVI M,000h
        RET
lab142: LHLD lab116
        XCHG
        LHLD lab111
        INX H
        MOV M,E
        JMP lab134
lab120: LDA lab108
        CPI 002h
        JNC lab135
        CALL lab123
        LHLD lab113
        CPI 030h
        JZ lab127
        CPI 024h
        JZ lab125
        CPI 004h
        JZ lab136
        CPI 010h
        JZ lab137
        CPI 080h
        JZ lab138
        CPI 040h
        JZ lab139
        RET
lab139: MVI M,004h
lab121: CALL lab140
        XRA A
        STA lab100
        INR A
        STA lab102
        JMP lab141
lab138: MVI M,003h
        CALL lab140
        XRA A
        STA lab100
        DCR A
        STA lab102
        JMP lab141
lab137: MVI M,001h
        CALL lab142
        XRA A
        STA lab102
        DCR A
        STA lab100
        JMP lab143
lab136: MVI M,002h
        CALL lab142
        XRA A
        STA lab102
        INR A
        STA lab100
        JMP lab143
lab141: CALL lab144
        XCHG
        LHLD lab113
        MOV A,M
        CPI 005h
        JZ lab145
        LHLD lab116
        PUSH D
        CALL lab119
        POP D
        MOV A,M
        CPI 007h
        JNZ lab146
        LDAX D
        ANI 00Fh
        MOV B,A
        CPI 007h
        JNZ lab147
        JMP lab148
lab146: LHLD lab113
        MOV A,M
        CPI 003h
        JZ lab149
        LDAX D
        ANI 00Fh
        MOV B,A
        CPI 008h
        JNC lab149
        CPI 006h
        JZ lab149
        JMP lab148
lab145: LDAX D
        ANI 00Fh
        MOV B,A
        CPI 007h
lab147: JNC lab149
        CPI 006h
        JNZ lab148
        LHLD lab113
        MOV A,M
        CPI 005h
        JNZ lab149
lab148: LDA lab108
        CPI 002h
        JC lab150
lab183: LXI H,lab151
        LDA lab108
        CPI 003h
        JZ lab152
        LXI H,lab153
lab152: CALL lab154
        CALL lab98
        JZ lab155
        LXI H,lab34
        CALL lab154
        LHLD lab116
        CALL lab98
        JZ lab156
        JMP lab157
lab154: MOV D,M
        INX H
        CALL lab131
        ADD D
        MOV D,A
        INX H
        MOV E,M
        INX H
        CALL lab131
        ADD E
        MOV E,A
        LHLD lab114
        RET
lab157: MOV A,B
        CPI 004h
        JZ lab158
        CPI 005h
        JZ lab158
        JMP lab159
lab144: LDA lab108
        CALL lab160
        LHLD lab109
        MOV D,M
        INX H
        INX H
        MOV E,M
        CALL lab161
        XCHG
        SHLD lab114
        CALL lab119
        RET
lab150: MOV A,B
        ORA A
        JZ lab159
        DCR A
        JZ lab162
        DCR A
        JZ lab163
        DCR A
        JZ lab164
        DCR A
        JZ lab165
        DCR A
        JZ lab165
        LXI H,lab166
        MOV A,M
        INX H
        ORA M
        JNZ lab159
        LHLD lab113
        MOV A,M
        CPI 003h
        JZ lab167
        JMP lab159
lab162: LDA lab10
        CPI 03Ah
        CNZ lab60
        MVI A,030h
        STA lab10
        CALL lab168
        LXI H,lab169
        CALL lab170
        JMP lab159
lab60:  INR A
        STA lab10
        LXI H,lab7
        MOV A,M
        ADI 010h
        DAA
        MOV M,A
        DCX H
        MOV A,M
        ACI 000h
        DAA
        MOV M,A
        MVI A,000h
        ACI 000h
        MOV C,A
        MOV A,M
        INX H
        MOV B,M
        LXI H,lab15
lab178: CALL lab48
        MOV A,B
        CALL lab48
        MOV A,C
        ORA A
        CNZ lab171
        RET
lab171: MVI A,031h
        LXI H,lab172
        JMP lab45
lab163: MVI A,001h
        STA lab73
        LXI H,lab173
        CALL lab170
        LHLD lab114
        SHLD lab174
        CALL lab168
        CALL lab175
        JMP lab159
lab164: LXI H,lab176
        CALL lab170
        LXI H,lab6
        MOV A,M
        INR A
        DAA
        MOV M,A
        MOV B,A
        XRA A
        MVI C,000h
        LXI H,lab177
        CALL lab178
        CALL lab168
        JMP lab159
        DB 07Fh, 07Fh, 07Fh, 07Fh, 000h
lab48:  MOV E,A
        RRC
        RRC
        RRC
        RRC
lab117: CALL lab179
        MOV A,E
lab179: ANI 00Fh
        ADI 030h
        CALL lab45
        INR H
        RET
lab168: LHLD lab114
        PUSH H
        CALL lab119
        MVI M,000h
        POP H
        CALL lab180
        MVI A,000h
        CALL lab45
        RET
lab175: MVI A,00Ch
        CALL lab45
        RET
lab143: CALL lab144
        MOV A,M
        ANI 00Fh
        MOV B,A
        LDA lab108
        CPI 002h
        JC lab181
        MOV A,B
        CPI 008h
        JNC lab182
        JMP lab183
lab155: LHLD lab113
        MOV A,M
        CPI 003h
        JC lab182
        JMP lab149
lab181: MOV A,B
        CPI 00Ah
        JNC lab149
        CPI 009h
        JNZ lab184
        LDA lab73
        ORA A
        JNZ lab185
        JMP lab149
lab184: CPI 008h
        JZ lab185
        CPI 006h
        JNC lab159
        JMP lab150
lab185: LXI H,lab186
        CALL lab170
        LHLD lab114
        PUSH H
        CALL lab119
        MVI M,000h
        POP H
        CALL lab180
        MVI A,000h
        CALL lab45
        MVI A,00Dh
        CALL lab45
        JMP lab159
lab180: MOV A,H
        ADI 098h
        MOV H,A
        MOV A,L
        RLC
        RLC
        RLC
        ADI 028h
        MOV L,A
        RET
lab135: LHLD lab116
        XCHG
        LXI H,lab34
        MOV E,M
        INX H
        CALL lab131
        ADD E
        CMP D
        LHLD lab113
        JZ lab187
        JC lab137
        JMP lab136
lab187: MVI A,001h
        STA lab188
lab182: LDA lab108
        MVI B,000h
        CALL lab96
        LHLD lab116
        XCHG
        LXI H,lab166
        MOV D,M
        INX H
        CALL lab131
        ADD D
        CMP E
        LHLD lab113
        JZ lab189
        MVI A,000h
        STA lab188
        JC lab138
        JMP lab139
lab189: LDA lab188
        ORA A
        JNZ lab190
        JMP lab159
lab149: LHLD lab113
        MOV A,M
        PUSH PSW
        LDA lab108
        MVI B,000h
        CALL lab96
        POP PSW
        LHLD lab113
        MOV A,M
        CPI 005h
        RNZ
        DCR A
        MOV M,A
        RET
lab159: LDA lab108
        CALL lab191
        RET
lab161: LHLD lab112
        MOV A,M
        ORA A
        JZ lab192
        LHLD lab113
        MOV A,M
        CPI 004h
        JC lab192
        INR E
lab192: LHLD lab111
        MOV A,M
        ORA A
        RZ
        LHLD lab113
        MOV A,M
        CPI 001h
        RZ
        INR D
        RET
lab127: LDA lab72
        ORA A
        RNZ
        DCR A
        STA lab72
        JMP lab193
lab125: LDA lab72
        ORA A
        RNZ
        INR A
        STA lab72
lab193: CALL lab194
        LHLD lab116
        SHLD lab195
lab200: CALL lab180
        XCHG
        LXI H,lab196
        CALL lab84
        RET
lab53:  LDA lab72
        ORA A
        RZ
        LHLD lab195
        ADD H
        MOV H,A
        SHLD lab197
        CALL lab119
        MOV A,M
        ANI 00Fh
        CPI 008h
        JC lab198
        CPI 00Ah
        JZ lab199
lab204: LHLD lab195
        XRA A
        STA lab72
        JMP lab200
lab199: MOV A,M
        ANI 0F0h
        PUSH H
        PUSH PSW
        LXI B,00300h
        CALL lab201
        POP PSW
        POP H
        CALL lab202
        CPI 020h
        JZ lab203
        MOV A,M
        ADI 010h
        MOV M,A
        JMP lab204
lab203: MVI M,000h
        LHLD lab197
        PUSH H
        LDA lab75
        MOV C,A
        LXI H,lab205
        CALL lab206
        MVI M,000h
        INX H
        MVI M,002h
        CALL lab207
        POP D
        MOV M,E
        INX H
        MOV M,D
        MOV A,C
        INR A
        CPI 020h
        CZ lab208
        STA lab75
        JMP lab204
lab208: XRA A
        RET
lab202: PUSH PSW
        PUSH H
        RRC
        RRC
        RRC
        RRC
        ADI 00Eh
        MOV D,A
        LDA lab72
        CPI 001h
        JZ lab209
        MOV A,D
        ADI 003h
        MOV D,A
lab209: MOV A,D
        LHLD lab197
        PUSH PSW
        CALL lab180
        POP PSW
        CALL lab45
        POP H
        POP PSW
        RET
lab198: LHLD lab195
        CALL lab200
        LHLD lab197
        SHLD lab195
        JMP lab200
lab156: LXI H,lab210
        CALL lab170
        LXI H,lab6
        MVI A,099h
        ADD M
        DAA
        MOV M,A
        MOV B,A
        XRA A
        LXI H,lab177
        CALL lab48
        MOV A,B
        CALL lab48
        MOV A,B
        ORA A
        POP H
        POP H
        LHLD lab3
        XCHG
        JNZ lab49
        JMP lab211
lab165: CALL lab194
        JMP lab156
lab158: LDA lab108
        MVI B,000h
        CALL lab96
        LDA lab108
        CALL lab68
        LDA lab108
        CALL lab212
        XCHG
        LDA lab108
        CALL lab88
        MVI C,005h
        CALL lab92
        LDA lab108
        CALL lab128
        MVI M,018h
        RET
lab54:  MVI C,000h
        LXI H,lab205
lab215: MOV A,M
        MOV E,M
        INX H
        MOV D,M
        ORA M
        JZ lab213
        DCX D
        MOV M,D
        DCX H
        MOV M,E
        MOV A,E
        STC
        CMC
        RAL
        MOV E,A
        MOV A,D
        RAL
        MOV D,A
        MOV A,E
        ORA A
        INX H
        PUSH H
        PUSH B
        JZ lab214
lab218: POP B
        POP H
lab213: INX H
        INR C
        MOV A,C
        CPI 020h
        JNZ lab215
        RET
lab214: MOV A,D
        RLC
        RLC
        RLC
        LXI H,lab216
        MVI D,000h
        MOV E,A
        DAD D
        PUSH H
        CALL lab207
        MOV E,M
        INX H
        MOV D,M
        XCHG
        SHLD lab217
        CALL lab180
        XCHG
        POP H
        PUSH H
        CALL lab84
        POP H
        LXI D,lab216
        CALL lab98
        JNZ lab218
        LHLD lab217
        CALL lab119
        MVI M,00Ah
        JMP lab218
lab207: LXI H,lab219
lab206: MOV A,C
        RLC
        ADD L
        MOV L,A
        MOV A,H
        ACI 000h
        MOV H,A
        RET
lab55:  LDA lab73
        ORA A
        RZ
        LDA lab74
        DCR A
        STA lab74
        RNZ
        LDA lab43
        STA lab74
        LHLD lab174
        CALL lab180
        XCHG
        LXI H,lab220
        CALL lab84
        RET
lab167: LXI H,lab221
        CALL lab170
        POP H
        POP H
        RET
lab11:  LXI H,lab222
lab228: MOV A,M
        CPI 0FFh
        RZ
        MOV D,M
        INX H
        MOV E,M
        PUSH D
        INX H
        LXI D,00000h
        MOV A,M
        DCR A
        JNZ lab223
        DCR D
lab223: DCR A
        JNZ lab224
        INR D
lab224: DCR A
        JNZ lab225
        DCX D
lab225: DCR A
        JNZ lab226
        INX D
lab226: INX H
        MOV C,M
        INX H
        MOV B,M
        INX H
        XTHL
lab229: PUSH D
        PUSH H
        MOV A,H
        ADI 090h
        MOV H,A
        MOV A,L
        RLC
        RLC
        RLC
        MOV L,A
        MOV A,B
        CALL lab45
        POP H
        POP D
        DCR C
        JNZ lab227
        POP H
        JMP lab228
lab227: DAD D
        JMP lab229
lab14:  LDAX D
        CPI 0FFh
        RZ
        CALL lab45
        INR H
        INX D
        JMP lab14
lab194: LXI B,00800h
lab201: LXI H,lab231
lab232: MOV A,M
        RAR
        MVI A,00Ah
        ACI 000h
        STA lab104
        INX H
        DCX B
        MOV A,B
        ORA C
        JNZ lab232
        RET
lab170: MOV A,M
        ORA A
        RZ
        CPI 0FFh
        JZ lab233
        STA lab234
        INX H
        MOV A,M
        STA lab235
        INX H
        CALL lab236
        JMP lab170
lab233: MOV E,M
        INX H
        MOV D,M
        INX H
        CALL lab51
        JMP lab170
lab211: LXI H,lab237
        LXI D,lab238
        CALL lab14
        LXI H,lab239
        LXI D,lab240
        CALL lab14
        LXI H,lab241
        LXI D,lab242
        CALL lab14
        LXI D,lab243
        CALL lab51
        LXI H,lab244
        CALL lab170
        CALL lab245
        CALL lab246
        JMP lab247
lab46:  LXI H,lab237
        LXI D,lab238
        CALL lab14
        LXI H,lab239
        LXI D,lab248
        CALL lab14
        LXI H,lab241
        LXI D,lab242
        CALL lab14
        LXI D,lab243
        CALL lab51
        LXI H,lab249
        CALL lab170
        CALL lab245
        LXI SP,lab250
        LXI H,lab2
        SHLD lab3
        LXI H,lab6
        MOV A,M
        ADI 005h
        DAA
        MOV M,A
        MOV B,A
        XRA A
        MVI C,000h
        LXI H,lab177
        CALL lab178
        XRA A
        STA lab9
        JMP lab251
lab245: MVI H,004h
lab253: LXI D,lab252
        CALL lab51
        DCR H
        JNZ lab253
        RET
lab246: MVI C,080h
lab255: MOV L,C
        CALL lab254
        MOV A,C
        CMA
        MOV L,A
        CALL lab254
        INR C
        JNZ lab255
        RET
lab254: MVI H,090h
lab256: MVI M,000h
        INR H
        MOV A,H
        CPI 0C0h
        JNZ lab256
        LXI D,00090h
        JMP lab51

lab210: DB 005h, 0FAh, 005h, 0E6h, 005h, 0D2h, 005h, 0BEh, 007h, 0AAh
        DB 00Ah, 096h, 00Ah, 08Ch, 00Ah, 082h, 00Ah, 078h, 00Ah, 06Eh
        DB 00Ah, 064h, 00Ah, 05Ah, 000h
lab173: DB 028h, 056h, 0FFh, 002h, 02Dh, 04Dh, 0FFh, 002h, 032h, 044h
        DB 0FFh, 002h, 038h, 040h, 0FFh, 002h, 03Fh, 038h, 000h
lab221: DB 028h, 056h, 0FFh, 002h, 032h, 04Dh, 0FFh, 002h, 03Ch, 044h
        DB 0FFh, 002h, 046h, 040h, 0FFh, 002h, 050h, 038h, 0FFh, 002h
        DB 05Ah, 031h, 0FFh, 002h, 064h, 02Bh, 0FFh, 002h, 06Eh, 028h
        DB 000h
lab169: DB 018h, 056h, 01Ah, 04Dh, 01Eh, 044h, 000h
lab176: DB 028h, 056h, 0FFh, 003h, 028h, 056h, 0FFh, 003h, 028h, 056h
        DB 0FFh, 003h, 028h, 056h, 0FFh, 003h, 028h, 056h, 000h
lab186: DB 003h, 03Ch, 0FFh, 004h, 003h, 046h, 0FFh, 004h, 003h, 050h
        DB 0FFh, 004h, 003h, 05Ah, 0FFh, 004h, 003h, 064h, 0FFh, 004h
        DB 003h, 06Eh, 0FFh, 004h, 003h, 078h, 0FFh, 004h, 003h, 082h
        DB 0FFh, 004h, 003h, 08Ch, 0FFh, 004h, 000h
lab244: DB 0BEh, 0EDh, 0FFh, 007h, 0BEh, 0EDh, 0FFh, 007h, 032h, 0EDh
        DB 0FFh, 003h, 0BEh, 0EDh, 0FFh, 007h, 0DFh, 0C8h, 0FFh, 007h
        DB 03Ch, 0D4h, 0FFh, 003h, 0CDh, 0D4h, 0FFh, 007h, 032h, 0EDh
        DB 0FFh, 003h, 0BEh, 0EDh, 0FFh, 007h, 028h, 0FBh, 0FFh, 003h
        DB 0BEh, 0EDh, 000h
lab249: DB 096h, 0B0h, 05Ah, 08Bh, 06Eh, 075h, 03Ch, 056h, 0FFh, 004h
        DB 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 03Ch, 056h
        DB 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h
        DB 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 041h, 04Dh
        DB 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h
        DB 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh
        DB 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h
        DB 0FEh, 044h, 096h, 044h, 0FCh, 04Dh, 078h, 04Dh, 064h, 056h
        DB 0FFh, 005h, 050h, 075h, 0FFh, 005h, 06Eh, 056h, 000h
lab240: DB 015h, 025h, 040h, 027h, 041h, 000h, 021h, 024h, 021h, 02Ah
        DB 026h, 029h, 02Ah, 041h, 015h, 0FFh
lab248: DB 015h, 020h, 027h, 025h, 033h, 021h, 028h, 041h, 042h, 000h
        DB 025h, 040h, 027h, 041h, 015h, 0FFh
lab238: DB 016h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h
        DB 014h, 014h, 014h, 014h, 017h, 0FFh
lab242: DB 019h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h
        DB 014h, 014h, 014h, 014h, 018h, 0FFh
lab13:  DB 021h, 026h, 024h, 025h, 000h, 000h, 020h, 021h, 020h, 022h
        DB 023h, 024h, 025h, 000h, 000h, 02Dh, 024h, 021h, 027h, 021h
        DB 02Dh, 023h, 02Bh, 03Ah, 030h, 030h, 0FFh
lab16:  DB 030h, 030h, 030h, 030h, 000h, 000h, 000h, 000h, 000h, 030h
        DB 030h, 030h, 035h, 000h, 000h, 000h, 02Ch, 027h, 021h, 028h
        DB 029h, 02Ah, 02Bh, 03Ah, 030h, 030h, 0FFh
lab18:  DB 026h, 029h, 027h, 02Ah, 021h, 028h, 02Eh, 022h, 02Fh, 031h
        DB 039h, 038h, 038h, 000h, 000h, 03Bh, 0FFh
lab20:  DB 01Ah, 01Bh, 01Ch, 0FFh
lab22:  DB 01Dh, 01Eh, 01Fh, 0FFh
lab32:  DB 03Ch, 025h, 03Dh, 025h, 02Ah, 02Dh, 024h, 025h, 03Eh, 000h
        DB 024h, 03Fh, 028h, 03Fh, 0FFh
lab58:  DB 007h, 007h, 007h, 007h, 007h, 007h, 0FFh
lab222: DB 000h, 000h, 001h, 001h, 016h, 000h, 001h, 004h, 01Eh, 015h
        DB 000h, 01Fh, 001h, 001h, 019h, 001h, 01Fh, 002h, 02Eh, 014h
        DB 02Fh, 01Fh, 001h, 001h, 018h, 02Fh, 01Eh, 003h, 01Eh, 015h
        DB 02Fh, 000h, 001h, 001h, 017h, 02Eh, 000h, 001h, 02Eh, 014h
        DB 001h, 001h, 002h, 007h, 014h, 001h, 002h, 002h, 007h, 014h
        DB 001h, 003h, 002h, 007h, 014h, 028h, 001h, 002h, 007h, 014h
        DB 028h, 002h, 002h, 007h, 014h, 028h, 003h, 002h, 007h, 014h
        DB 001h, 01Ch, 002h, 00Bh, 014h, 001h, 01Dh, 002h, 00Bh, 014h
        DB 001h, 01Eh, 002h, 00Bh, 014h, 024h, 01Ch, 002h, 00Bh, 014h
        DB 024h, 01Dh, 002h, 00Bh, 014h, 024h, 01Eh, 002h, 00Bh, 014h
        DB 0FFh
lab28:  DB 031h, 00Ah, 007h, 032h, 00Ah, 007h, 033h, 00Ah, 007h, 034h
        DB 00Ah, 007h, 035h, 00Ah, 007h, 036h, 00Ah, 0FFh
lab30:  DB 02Dh, 000h, 024h, 000h, 021h, 000h, 027h, 000h, 021h, 000h
        DB 02Dh, 000h, 023h, 000h, 02Bh, 0FFh
lab77:  DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 07Eh, 02Ah, 07Eh, 07Eh, 000h, 000h, 000h, 000h, 07Eh
        DB 02Ah, 07Eh, 07Eh, 000h, 000h, 000h, 000h, 07Eh, 02Ah, 07Eh
        DB 07Eh, 000h, 000h, 022h, 044h, 0AAh, 000h, 0AAh, 000h, 055h
        DB 000h, 055h, 000h, 0AAh, 000h, 0AAh, 000h, 055h, 0FFh, 055h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 066h, 0FFh, 066h, 066h
        DB 066h, 0FFh, 066h, 066h, 038h, 020h, 038h, 020h, 038h, 020h
        DB 038h, 020h, 038h, 020h, 038h, 020h, 038h, 020h, 038h, 020h
        DB 0D6h, 0B5h, 05Bh, 0ADh, 0D6h, 0B5h, 05Bh, 0ADh, 0C6h, 0EEh
        DB 039h, 0BAh, 06Ch, 0EEh, 093h, 0ABh
lab220: DB 007h, 005h, 00Fh, 018h, 030h, 060h, 0F0h, 0A0h, 000h, 07Eh
        DB 000h, 07Eh, 000h, 07Eh, 000h, 07Eh, 0D6h, 0B5h, 05Bh, 00Dh
        DB 006h, 031h, 05Bh, 0ADh, 0D6h, 035h, 003h, 001h, 002h, 001h
        DB 01Bh, 0ADh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0D6h, 0B5h, 05Bh, 084h, 0D0h, 0B0h, 05Bh, 0ADh, 0D6h, 0B4h
        DB 050h, 080h, 0C0h, 0A0h, 058h, 0ADh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh
        DB 000h, 000h, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch
        DB 000h, 000h, 003h
lab38:  DB 00Fh, 01Fh, 01Fh, 03Eh, 03Ch, 000h, 000h, 0C0h, 0F0h, 0F8h
        DB 0F8h, 07Ch, 03Ch, 03Ch, 07Ch, 0F8h, 0F8h, 0F0h, 0C0h, 000h
        DB 000h, 03Ch, 03Eh, 01Fh, 01Fh, 00Fh, 003h, 000h, 000h, 000h
        DB 000h, 001h, 003h, 007h, 00Fh, 00Fh, 00Fh, 03Ch, 0BFh, 0BFh
        DB 0B3h, 0B3h, 0BCh, 0BCh, 0B3h, 000h, 000h, 080h, 020h, 030h
        DB 0F0h, 0F0h, 030h, 00Fh, 00Fh, 00Fh, 007h, 003h, 001h, 000h
        DB 000h, 0B3h, 0BFh, 083h, 0FBh, 0FBh, 0FBh, 0FBh, 03Ch, 030h
        DB 0F0h, 0F0h, 0E0h, 0C0h, 080h, 000h, 000h, 0FEh, 0C6h, 0C6h
        DB 0C6h, 0C6h, 0C6h, 0C6h, 0C6h, 07Ch, 0C6h, 0C6h, 0C6h, 0C6h
        DB 0C6h, 0C6h, 07Ch, 0C2h, 0C2h, 0C2h, 0F2h, 0DAh, 0DAh, 0DAh
        DB 0F2h, 0FEh, 0BAh, 038h, 038h, 038h, 038h, 038h, 038h, 0C6h
        DB 0CCh, 0D8h, 0F0h, 0F0h, 0D8h, 0CCh, 0C6h, 0C6h, 0C6h, 0C6h
        DB 0C6h, 0CEh, 0D6h, 0E6h, 0C6h, 0C6h, 0C6h, 0C6h, 0C6h, 07Eh
        DB 006h, 006h, 006h, 0FCh, 0C6h, 0C6h, 0C6h, 0FCh, 0C0h, 0C0h
        DB 0C0h, 0FCh, 0C6h, 0C6h, 0FCh, 0FCh, 0C6h, 0C6h, 0FCh, 0FEh
        DB 0C2h, 0C0h, 0F0h, 0F0h, 0C0h, 0C2h, 0FEh, 0C6h, 0C6h, 0C6h
        DB 0C6h, 0FEh, 0C6h, 0C6h, 0C6h, 0C0h, 0C0h, 0C0h, 0FCh, 0C6h
        DB 0C6h, 0C6h, 0FCh, 0C6h, 0C6h, 0C6h, 0C6h, 07Eh, 006h, 0CCh
        DB 078h, 07Ch, 0C6h, 0C6h, 0C0h, 0C0h, 0C6h, 0C6h, 07Ch, 0CCh
        DB 0CCh, 0CCh, 0CCh, 0CCh, 0CCh, 0CCh, 0FEh, 000h, 000h, 032h
        DB 07Eh, 04Ch, 000h, 000h, 000h, 07Ch, 0C6h, 0C6h, 0CEh, 0D6h
        DB 0E6h, 0C6h, 07Ch, 018h, 038h, 058h, 018h, 018h, 018h, 018h
        DB 07Eh, 07Ch, 0C6h, 0C6h, 00Ch, 038h, 060h, 0C6h, 0FEh, 07Ch
        DB 0C6h, 0C6h, 01Ch, 01Ch, 0C6h, 0C6h, 07Ch, 01Ch, 03Ch, 06Ch
        DB 0CCh, 08Ch, 0FEh, 00Ch, 00Ch, 0FEh, 0C0h, 0C0h, 0FCh, 006h
        DB 0C6h, 0C6h, 07Ch, 03Ch, 066h, 0C0h, 0FCh, 0C6h, 0C6h, 0C6h
        DB 07Ch, 0FEh, 0C6h, 006h, 00Ch, 018h, 030h, 030h, 030h, 07Ch
        DB 0C6h, 0C6h, 07Ch, 07Ch, 0C6h, 0C6h, 07Ch, 07Ch, 0C6h, 0C6h
        DB 0C6h, 07Eh, 006h, 0CCh, 078h, 000h, 000h, 000h, 0FEh, 0FEh
        DB 000h, 000h, 000h, 000h, 000h, 018h, 03Ch, 03Ch, 018h, 000h
        DB 000h, 03Eh, 036h, 036h, 036h, 036h, 036h, 066h, 0C6h, 0D6h
        DB 0D6h, 0D6h, 0D6h, 0D6h, 0D6h, 0FEh, 006h, 0D6h, 0D6h, 0C6h
        DB 0C6h, 0CEh, 0D6h, 0E6h, 0C6h, 000h, 000h, 000h, 000h, 070h
        DB 070h, 070h, 000h, 0FEh, 0C6h, 0C0h, 0C0h, 0C0h, 0C0h, 0C0h
        DB 0C0h, 038h, 07Ch, 0C6h, 0C6h, 0FEh, 0C6h, 0C6h, 0C6h, 07Eh
        DB 0C6h, 0C6h, 0C6h, 07Eh, 036h, 066h, 0C6h
lab80:  DB 058h, 058h, 04Ah, 03Eh, 018h, 028h, 028h, 008h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 01Ah, 01Ah, 04Ah, 07Ch
        DB 018h, 014h, 014h, 010h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 030h, 030h, 05Eh, 07Bh, 01Ch, 037h, 020h, 060h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Ch, 00Ch
        DB 007h, 01Eh, 006h, 00Eh, 00Ah, 005h, 000h, 000h, 000h, 080h
        DB 080h, 000h, 000h, 000h, 003h, 003h, 001h, 001h, 001h, 003h
        DB 003h, 001h, 000h, 000h, 080h, 0C0h, 080h, 000h, 0C0h, 080h
        DB 000h, 000h, 000h, 001h, 000h, 000h, 000h, 000h, 0C0h, 0C0h
        DB 070h, 0E8h, 068h, 0E0h, 0A0h, 050h, 00Ch, 00Ch, 07Ah, 0DEh
        DB 038h, 0ECh, 004h, 006h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 003h, 003h, 00Eh, 017h, 016h, 007h, 005h, 00Ah
        DB 000h, 000h, 000h, 080h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 001h, 003h, 001h, 000h, 003h, 001h, 0C0h, 0C0h, 080h, 080h
        DB 080h, 0C0h, 0C0h, 080h, 000h, 000h, 000h, 001h, 001h, 000h
        DB 000h, 000h, 030h, 030h, 0E0h, 078h, 060h, 078h, 050h, 0A0h
lab196: DB 000h, 000h, 010h, 024h, 008h, 000h, 000h, 000h
lab216: DB 000h, 000h
lab65:  DB 000h, 001h, 0D6h, 080h, 000h, 000h, 000h, 000h, 001h, 0ACh
        DB 000h, 001h, 000h, 000h, 080h, 091h, 05Ah, 000h, 000h, 034h
        DB 013h, 081h, 056h, 024h, 000h, 000h, 000h, 000h, 048h, 02Ch
lab217: DB 009h, 015h
lab195: DB 001h, 002h
lab197: DB 001h, 001h
lab72:  DB 0FFh
lab73:  DB 000h
lab174: DB 010h, 010h
lab74:  DB 001h
lab100: DB 000h
lab102: DB 0FFh
lab3:   DB 080h, 01Eh
lab9:   DB 008h
lab43:  DB 006h
lab4:   DB 000h, 009h
lab108: DB 001h
lab10:  DB 03Ah
lab109: DB 0F6h, 00Fh
lab111: DB 0F7h, 00Fh
lab112: DB 0F9h, 00Fh
lab113: DB 0FAh, 00Fh
lab188: DB 000h
lab114: DB 014h, 003h, 017h, 0B7h
lab116: DB 014h, 003h
lab8:   DB 016h
lab7:   DB 070h
lab6:   DB 012h
lab75:  DB 000h
lab205: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lab219: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lab95:  DB 003h, 000h, 014h, 000h, 002h, 00Fh, 002h, 012h, 000h, 002h
        DB 00Fh, 002h, 014h, 000h, 002h
lab69:  DB 018h
lab71:  DB 018h
lab62:  DB 003h, 000h, 014h, 000h, 002h, 00Fh, 000h, 000h, 000h, 004h
        DB 010h, 000h, 000h, 000h, 004h, 018h
lab34:  DB 003h, 000h
lab166: DB 014h, 000h, 002h
lab151: DB 00Fh, 000h, 000h, 000h, 004h
lab153: DB 010h, 000h, 000h, 000h, 004h
lab70:  DB 018h
lab133: DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 000h, 000h, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 000h, 000h, 000h, 000h, 000h, 00Ah
        DB 00Ah, 00Bh, 000h, 002h, 00Bh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 000h, 000h, 000h
        DB 007h, 000h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 00Ah, 00Ah, 00Ah
        DB 000h, 000h, 000h, 00Ah, 007h, 00Ah, 00Bh, 001h, 000h, 00Ah
        DB 00Ah, 00Ah, 007h, 000h, 000h, 000h, 000h, 000h, 00Ah, 007h
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 00Ah
        DB 001h, 00Ah, 006h, 006h, 000h, 00Ah, 007h, 00Ah, 000h, 006h
        DB 006h, 00Ah, 00Ah, 00Ah, 007h, 00Ah, 00Ah, 00Ah, 000h, 00Ah
        DB 00Ah, 007h, 000h, 000h, 000h, 000h, 00Bh, 00Bh, 00Bh, 00Bh
        DB 007h, 00Ah, 00Ah, 00Ah, 000h, 006h, 006h, 00Ah, 007h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 007h, 00Ah, 00Ah, 000h
        DB 000h, 009h, 000h, 007h, 000h, 000h, 000h, 007h, 00Bh, 00Bh
        DB 00Bh, 00Bh, 007h, 000h, 000h, 001h, 000h, 000h, 000h, 00Bh
        DB 007h, 000h, 006h, 006h, 006h, 00Bh, 007h, 000h, 007h, 000h
        DB 00Ah, 00Ah, 006h, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 000h, 007h
        DB 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 000h, 00Ah, 000h, 000h
        DB 000h, 00Bh, 007h, 000h, 000h, 000h, 000h, 00Bh, 007h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 00Bh, 007h, 000h, 000h, 00Bh, 007h, 00Bh
        DB 007h, 000h, 007h, 006h, 006h, 006h, 006h, 006h, 006h, 006h
        DB 006h, 006h, 006h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h
        DB 007h, 00Ah, 006h, 000h, 000h, 00Ah, 00Ah, 00Ah, 006h, 000h
        DB 007h, 00Bh, 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh
        DB 007h, 000h, 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 007h, 00Bh, 007h, 000h, 00Ah, 00Ah, 00Ah, 00Ah
        DB 007h, 00Ah, 00Ah, 00Bh, 004h, 00Bh, 006h, 007h, 00Bh, 00Bh
        DB 00Bh, 00Bh, 007h, 000h, 007h, 000h, 000h, 00Ah, 006h, 00Ah
        DB 00Ah, 00Ah, 006h, 00Ah, 00Ah, 00Bh, 007h, 000h, 000h, 001h
        DB 00Ah, 00Bh, 007h, 00Ah, 000h, 00Bh, 00Bh, 00Bh, 000h, 007h
        DB 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 007h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h, 000h
        DB 006h, 00Ah, 00Ah, 00Ah, 007h, 00Ah, 000h, 00Ah, 00Ah, 00Ah
        DB 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 007h, 000h
        DB 00Bh, 00Bh, 006h, 00Bh, 00Bh, 00Bh, 006h, 00Bh, 00Bh, 00Bh
        DB 00Bh, 000h, 000h, 00Ah, 00Ah, 00Ah, 007h, 00Ah, 000h, 00Ah
        DB 001h, 00Ah, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 00Ah, 00Bh, 007h, 00Ah
        DB 006h, 00Ah, 00Ah, 00Ah, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh
        DB 007h, 000h, 00Ah, 006h, 006h, 006h, 00Ah, 00Ah, 00Ah, 000h
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 006h, 00Bh, 000h, 000h, 000h
        DB 007h, 000h, 000h, 00Ah, 00Ah, 00Ah, 000h, 007h, 00Bh, 00Bh
        DB 00Bh, 00Bh, 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Bh, 000h
        DB 003h, 000h, 007h, 000h, 00Ah, 000h
lab36:  DB 00Ah, 00Ah, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 00Bh
        DB 00Bh, 004h, 004h, 004h, 004h, 004h, 004h, 004h, 004h
lab264: DB 004h, 004h, 004h, 004h, 004h, 00Bh, 000h, 00Bh, 000h, 007h
        DB 000h, 00Ah, 00Ah, 000h, 001h, 000h, 007h, 00Bh, 00Bh, 00Bh
        DB 00Bh, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 000h, 00Bh
        DB 000h, 007h, 000h, 00Ah, 00Ah, 00Ah, 00Ah, 000h, 007h, 00Bh
        DB 00Bh, 00Bh, 00Bh, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 00Ah, 000h, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 007h, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 007h, 006h, 006h, 006h, 006h
        DB 006h, 006h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 00Ah, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
lab85:  DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 004h, 004h, 004h, 004h
        DB 004h, 00Ah, 00Bh, 00Bh
lab190: XRA A
        STA lab188
        JMP lab156
lab126: CPI 043h
        JZ lab167
        CPI 083h
        JNZ lab122
        LDA lab6
        INR A
        DAA
        STA lab6
        JMP lab122
        DB 037h, 03Fh, 017h, 05Fh, 07Ah, 017h, 057h, 00Dh, 0C2h, 0E1h
        DB 001h, 019h, 0EBh, 0E1h, 0E5h, 00Eh, 008h, 01Ah, 077h, 023h
        DB 013h, 00Dh, 0C2h, 0F3h, 001h, 0E1h, 0D1h, 0C1h, 0C9h, 07Eh
lab2:   DB 002h, 000h, 000h, 000h, 004h, 005h, 000h, 000h, 000h, 004h
        DB 01Bh, 000h, 000h, 000h, 004h, 018h, 0BBh, 00Ah, 000h, 0ABh
        DB 07Ah, 0A7h, 0AAh, 07Ah, 0A7h, 0AAh, 07Ah, 0A7h, 0BAh, 000h
        DB 0A0h, 0BBh, 0BBh, 00Ah, 0AAh, 0ABh, 07Bh, 0A7h, 0BAh, 07Bh
        DB 0A7h, 0BAh, 07Bh, 0A7h, 0BAh, 0AAh, 0A0h, 0BBh, 0BBh, 00Ah
        DB 0AAh, 0AAh, 07Ah, 0A7h, 0AAh, 07Ah, 0A7h, 0AAh, 07Ah, 0A7h
        DB 0AAh, 0AAh, 0A0h, 0BBh, 0BBh, 000h, 000h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 000h, 070h, 007h, 000h, 000h, 000h, 0BBh
        DB 0BBh, 076h, 07Bh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0B7h, 067h, 0BBh, 0BBh, 070h, 07Bh, 0A0h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Ah, 0B7h
        DB 007h, 0BBh, 0BBh, 070h, 07Bh, 0A0h, 000h, 000h, 000h
lab91:  DB 000h, 000h, 000h, 000h, 000h, 00Ah, 0B7h, 007h, 0BBh, 0BBh
        DB 070h, 07Bh, 0A7h, 077h, 077h, 077h, 077h, 077h, 077h, 077h
        DB 077h, 07Ah, 0B7h, 007h, 0BBh, 0BBh, 070h, 07Ah, 0A0h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Ah, 0A7h, 007h
        DB 0BBh, 0BBh, 0A7h, 0B6h, 066h, 066h, 066h, 066h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 06Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0B1h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 01Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0B4h, 0BBh, 0B4h, 0BBh, 0B4h
        DB 04Bh, 0B4h, 044h, 0BBh, 0BBh, 044h, 04Bh, 07Ah, 0BBh, 0BBh
        DB 0A7h, 0B5h, 0BBh, 05Bh, 0BBh, 055h, 05Bh, 055h, 055h, 05Bh
        DB 0B5h, 055h, 05Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0B5h, 0B5h, 0BBh
        DB 0BBh, 05Bh, 05Bh, 05Bh, 0BBh, 05Bh, 0B5h, 0BBh, 05Bh, 07Ah
        DB 0BBh, 0BBh, 0A7h, 0B5h, 05Bh, 0BBh, 0BBh, 05Bh, 05Bh, 055h
        DB 055h, 05Bh, 0B5h, 0BBh, 05Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0B5h
        DB 0B5h, 0BBh, 0BBh, 05Bh, 05Bh, 05Bh, 0BBh, 05Bh, 0B5h, 0BBh
        DB 05Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0B5h, 0BBh, 05Bh, 0BBh, 05Bh
        DB 05Bh, 05Bh, 0BBh, 05Bh, 0B5h, 0BBh, 05Bh, 07Ah, 0BBh, 0BBh
        DB 0A7h, 0B5h, 0BBh, 0B5h, 0B5h, 05Bh, 05Bh, 05Bh, 0BBh, 05Bh
        DB 055h, 055h, 05Bh, 07Ah, 0BBh, 0BBh, 0A7h, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 07Ah
        DB 0BBh, 0BBh, 0A7h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 07Ah, 0BBh, 0BBh, 0A7h, 007h
        DB 0B6h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 06Bh
        DB 070h, 07Ah, 0BBh, 0BBh, 0BBh, 0BBh, 0B4h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 04Bh, 0BBh, 0BBh, 0BBh, 002h
        DB 000h, 000h, 000h, 004h, 011h, 000h, 001h, 000h, 004h, 000h
        DB 000h, 000h, 000h, 001h, 0FFh, 0BBh, 00Bh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 07Ah
        DB 0BBh, 0BBh, 00Bh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 0BBh, 0BBh, 00Bh, 07Ah
        DB 0A0h, 007h, 0AAh, 000h, 000h, 000h, 007h, 0AAh, 0AAh, 0A0h
        DB 000h, 070h, 0BBh, 0BBh, 00Bh, 07Ah, 0A0h, 007h, 0AAh, 06Ah
        DB 07Ah, 0AAh, 0AAh, 0AAh, 0AAh, 0A6h, 0AAh, 07Ah, 0BBh, 0BBh
        DB 00Bh, 07Ah, 0A6h, 0A7h, 000h, 000h, 070h, 000h, 000h, 000h
        DB 000h, 090h, 000h, 070h, 0BBh, 0BBh, 00Bh, 070h, 000h, 007h
        DB 000h, 000h, 070h, 000h, 000h, 000h, 07Ah, 0AAh, 0AAh, 0A0h
        DB 0BBh, 0BBh, 00Bh, 070h, 000h, 007h, 000h, 00Ah, 0AAh, 0AAh
        DB 0AAh, 0AAh, 070h, 000h, 000h, 070h, 0BBh, 0BBh, 00Bh, 070h
        DB 000h, 007h, 000h, 000h, 000h, 000h, 000h, 00Ah, 0AAh, 070h
        DB 000h, 070h, 0BBh, 0BBh, 00Bh, 070h, 0AAh, 0A7h, 0AAh, 06Ah
        DB 0A0h, 002h, 000h, 000h, 000h, 076h, 066h, 070h, 0BBh, 0BBh
        DB 00Bh, 070h, 000h, 007h, 000h, 000h, 0AAh, 0AAh, 0AAh, 0AAh
        DB 000h, 070h, 000h, 070h, 0BBh, 0BBh, 00Bh, 070h, 000h, 007h
        DB 000h, 000h, 000h, 001h, 001h, 000h, 000h, 070h, 000h, 070h
        DB 0BBh, 0BBh, 00Bh, 070h, 00Ah, 0AAh, 0AAh, 06Ah, 0A7h, 0AAh
        DB 00Ah, 0AAh, 0A0h, 070h, 000h, 070h, 0BBh, 0BBh, 00Bh, 070h
        DB 000h, 000h, 000h, 000h, 007h, 000h, 000h, 000h, 000h, 070h
        DB 000h, 070h, 0BBh, 0BBh, 00Bh, 0A6h, 06Ah, 0A7h, 000h, 000h
        DB 007h, 000h, 00Ah, 0AAh, 000h, 070h, 007h, 0A0h, 0BBh, 0BBh
        DB 00Bh, 000h, 000h, 007h, 000h, 000h, 007h, 0AAh, 06Ah, 0AAh
        DB 06Ah, 0A0h, 007h, 000h, 0BBh, 0BBh, 00Bh, 030h, 000h, 0AAh
        DB 0AAh, 0AAh, 067h, 000h, 000h, 000h, 000h, 000h, 007h, 001h
        DB 0BBh, 0BBh, 00Bh, 0AAh, 000h, 000h, 000h, 000h, 007h, 000h
        DB 000h, 000h, 00Ah, 07Ah, 007h, 0AAh, 0BBh, 0BBh, 00Bh, 010h
        DB 000h, 0A7h, 0AAh, 0AAh, 0AAh, 0AAh, 06Ah, 0A0h, 000h, 070h
        DB 007h, 00Ah, 0BBh, 0BBh, 00Bh, 0AAh, 0A0h, 007h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0AAh, 07Ah, 0AAh, 00Ah, 0BBh, 0BBh
        DB 00Bh, 000h, 000h, 007h, 0AAh, 0AAh, 0AAh, 0AAh, 06Ah, 000h
        DB 000h, 070h, 000h, 01Ah, 0BBh, 0BBh, 000h, 000h, 000h, 007h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 070h, 00Ah, 0AAh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 0BBh, 0BBh, 0BBh, 0BBh, 002h, 000h, 014h
        DB 000h, 002h, 018h, 000h, 000h, 000h, 004h, 000h, 000h, 000h
        DB 000h, 001h, 0FFh, 0BBh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 00Ah, 07Ah, 0AAh, 0BBh, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 030h, 000h, 000h
        DB 000h, 000h, 070h, 000h, 0BBh, 0BBh, 000h, 007h, 0AAh, 0AAh
        DB 066h, 06Ah, 0AAh, 0AAh, 0A6h, 066h, 0AAh, 0AAh, 0A0h, 000h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh, 010h, 007h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0BBh, 0BBh, 0A7h, 0AAh, 000h, 00Ah, 07Ah, 0AAh
        DB 0A6h, 066h, 066h, 0AAh, 0AAh, 0A7h, 000h, 000h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 000h, 000h, 0BBh, 0BBh, 007h, 000h, 000h, 000h
        DB 070h, 000h, 000h, 000h, 000h, 000h, 000h, 007h, 000h, 000h
        DB 0BBh, 0BBh, 007h, 001h, 000h, 000h, 070h, 000h, 001h, 000h
        DB 000h, 000h, 000h, 017h, 000h, 000h, 0BBh, 0BBh, 007h, 00Ah
        DB 07Ah, 0AAh, 0A0h, 000h, 07Ah, 0AAh, 0A7h, 0A0h, 000h, 0AAh
        DB 0A7h, 000h, 0BBh, 0BBh, 007h, 000h, 070h, 000h, 000h, 000h
        DB 070h, 000h, 007h, 000h, 000h, 000h, 007h, 000h, 0BBh, 0BBh
        DB 007h, 000h, 070h, 000h, 000h, 000h, 070h, 000h, 007h, 000h
        DB 000h, 000h, 007h, 000h, 0BBh, 0BBh, 007h, 000h, 070h, 000h
        DB 000h, 000h, 070h, 000h, 007h, 000h, 000h, 000h, 007h, 000h
        DB 0BBh, 0BBh, 0AAh, 0AAh, 0A0h, 000h, 07Ah, 0AAh, 0A0h, 000h
        DB 007h, 0AAh, 0AAh, 070h, 007h, 000h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 070h, 000h, 000h, 000h, 007h, 000h, 000h, 070h
        DB 007h, 000h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 070h, 000h
        DB 000h, 000h, 007h, 000h, 000h, 070h, 007h, 000h, 0BBh, 0BBh
        DB 000h, 001h, 000h, 000h, 070h, 000h, 001h, 000h, 007h, 000h
        DB 000h, 070h, 007h, 000h, 0BBh, 0BBh, 000h, 00Ah, 0A7h, 0AAh
        DB 0AAh, 0A0h, 00Ah, 0AAh, 0AAh, 0A0h, 000h, 0AAh, 0AAh, 000h
        DB 0BBh, 0BBh, 000h, 000h, 007h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh, 000h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0BBh, 0BBh, 000h, 000h, 007h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0B4h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 0BBh, 002h, 000h, 014h, 000h, 002h
        DB 00Fh, 000h, 000h, 000h, 004h, 000h, 000h, 000h, 000h, 004h
        DB 0FFh, 0BBh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0A0h, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 07Ah, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 010h, 0A0h
        DB 090h, 070h, 0BBh, 0BBh, 000h, 067h, 0AAh, 0AAh, 0AAh, 0A7h
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0A0h, 0A7h, 0AAh, 0AAh, 0BBh, 0BBh
        DB 001h, 007h, 000h, 000h, 002h, 007h, 000h, 000h, 000h, 007h
        DB 000h, 007h, 0A7h, 0A3h, 0BBh, 0BBh, 00Ah, 007h, 0A0h, 0AAh
        DB 0AAh, 0AAh, 00Ah, 007h, 0AAh, 0A7h, 0A6h, 0AAh, 007h, 0AAh
        DB 0BBh, 0BBh, 00Ah, 007h, 000h, 000h, 000h, 000h, 000h, 007h
        DB 000h, 00Ah, 000h, 000h, 007h, 0A0h, 0BBh, 0BBh, 00Ah, 007h
        DB 000h, 006h, 0AAh, 0AAh, 06Ah, 06Ah, 0AAh, 0AAh, 076h, 066h
        DB 0A0h, 0A0h, 0BBh, 0BBh, 00Ah, 007h, 0A6h, 070h, 000h, 000h
        DB 00Ah, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 00Ah, 000h, 000h, 076h, 0AAh, 0AAh, 00Ah, 007h, 0AAh, 0AAh
        DB 0A6h, 0A6h, 0AAh, 0AAh, 0BBh, 0BBh, 00Ah, 066h, 0A0h, 070h
        DB 001h, 00Ah, 00Ah, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0BBh, 0BBh, 00Ah, 000h, 0A0h, 07Ah, 0AAh, 00Ah, 000h, 00Bh
        DB 066h, 0AAh, 0A6h, 0A6h, 0AAh, 070h, 0BBh, 0BBh, 00Ah, 010h
        DB 0A0h, 070h, 00Ah, 00Ah, 0AAh, 00Bh, 044h, 04Bh, 001h, 0A0h
        DB 0AAh, 070h, 0BBh, 0BBh, 00Ah, 0A0h, 0A0h, 070h, 000h, 000h
        DB 00Ah, 00Bh, 0BBh, 0BBh, 00Ah, 0A0h, 000h, 070h, 0BBh, 0BBh
        DB 00Ah, 0A0h, 0B4h, 0BAh, 07Ah, 06Ah, 000h, 000h, 000h, 000h
        DB 00Ah, 0A6h, 0AAh, 070h, 0BBh, 0BBh, 00Ah, 0A0h, 0BBh, 0BAh
        DB 07Ah, 00Ah, 0AAh, 06Ah, 0AAh, 0A0h, 000h, 000h, 0AAh, 070h
        DB 0BBh, 0BBh, 00Ah, 0A1h, 000h, 000h, 070h, 000h, 000h, 000h
        DB 000h, 0A6h, 06Ah, 0A0h, 0AAh, 070h, 0BBh, 0BBh, 00Ah, 0AAh
        DB 00Ah, 07Ah, 0AAh, 06Ah, 0AAh, 0AAh, 0A0h, 000h, 000h, 000h
        DB 0AAh, 070h, 0BBh, 0BBh, 00Ah, 0AAh, 000h, 070h, 000h, 000h
        DB 001h, 000h, 000h, 0B7h, 06Ah, 000h, 000h, 070h, 0BBh, 0BBh
        DB 00Ah, 0AAh, 0AAh, 07Ah, 0AAh, 066h, 0AAh, 0AAh, 066h, 0B7h
        DB 00Ah, 0A6h, 0A0h, 070h, 0BBh, 0BBh, 000h, 000h, 000h, 070h
        DB 000h, 000h, 000h, 000h, 000h, 017h, 00Ah, 0A0h, 0A0h, 070h
        DB 0BBh, 0BBh, 000h, 000h, 000h, 07Bh, 044h, 044h, 044h, 044h
        DB 044h, 0BBh, 044h, 044h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 0BBh, 0BBh, 0BBh, 003h, 000h, 014h, 000h, 002h, 003h, 000h
        DB 000h, 000h, 004h, 000h, 000h, 000h, 000h, 001h, 0FFh, 0BBh
        DB 000h, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0BBh, 0BBh, 000h, 070h, 000h, 000h
        DB 000h, 000h, 010h, 000h, 066h, 066h, 066h, 066h, 066h, 070h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 000h, 000h, 000h, 066h, 070h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 0BBh, 0BBh, 00Ah, 0A0h
        DB 070h, 000h, 000h, 000h, 000h, 070h, 001h, 066h, 066h, 070h
        DB 000h, 070h, 0BBh, 0BBh, 00Ah, 0A0h, 007h, 000h, 000h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 007h, 000h, 070h, 0BBh, 0BBh
        DB 000h, 090h, 000h, 070h, 000h, 000h, 070h, 000h, 007h, 066h
        DB 066h, 067h, 000h, 070h, 0BBh, 0BBh, 007h, 0A6h, 066h, 067h
        DB 000h, 000h, 070h, 000h, 007h, 000h, 000h, 000h, 000h, 070h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 000h, 070h, 000h, 007h, 000h
        DB 007h, 066h, 066h, 070h, 010h, 070h, 0BBh, 0BBh, 007h, 020h
        DB 000h, 000h, 007h, 000h, 000h, 070h, 000h, 000h, 000h, 070h
        DB 000h, 070h, 0BBh, 0BBh, 007h, 0A0h, 000h, 000h, 000h, 070h
        DB 000h, 070h, 007h, 066h, 066h, 070h, 010h, 070h, 0BBh, 0BBh
        DB 007h, 001h, 011h, 011h, 010h, 007h, 070h, 007h, 007h, 000h
        DB 000h, 000h, 000h, 070h, 0BBh, 0BBh, 007h, 00Ah, 066h, 066h
        DB 060h, 017h, 007h, 007h, 007h, 066h, 066h, 070h, 010h, 070h
        DB 0BBh, 0BBh, 007h, 030h, 011h, 011h, 010h, 066h, 000h, 007h
        DB 000h, 000h, 000h, 070h, 000h, 070h, 0BBh, 0BBh, 000h, 0A0h
        DB 0A6h, 066h, 066h, 066h, 070h, 070h, 0A6h, 066h, 066h, 070h
        DB 0A6h, 070h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 000h, 000h, 070h, 0BBh, 0BBh
        DB 000h, 007h, 066h, 066h, 066h, 067h, 000h, 007h, 066h, 066h
        DB 066h, 066h, 066h, 070h, 0BBh, 0BBh, 000h, 007h, 000h, 000h
        DB 000h, 000h, 066h, 060h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0BBh, 0BBh, 000h, 007h, 066h, 066h, 066h, 070h, 000h, 000h
        DB 076h, 066h, 066h, 066h, 066h, 070h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 006h, 066h, 066h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 0BBh, 0BBh, 000h, 007h, 066h, 066h, 067h, 000h
        DB 000h, 000h, 007h, 066h, 066h, 066h, 066h, 000h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 000h, 000h, 066h, 066h, 066h, 060h, 000h
        DB 000h, 000h, 000h, 000h, 0BBh, 0BBh, 0AAh, 0AAh, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 0BBh, 002h, 000h, 013h, 000h, 002h, 006h, 000h, 000h, 000h
        DB 004h, 018h, 000h, 000h, 000h, 004h, 018h, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0B7h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0B7h, 0BBh, 0BBh
        DB 001h, 077h, 077h, 071h, 077h, 077h, 072h, 077h, 077h, 071h
        DB 077h, 077h, 073h, 097h, 0BBh, 0BBh, 007h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 0ABh
        DB 0BBh, 0BBh, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h
        DB 070h, 007h, 000h, 070h, 007h, 00Bh, 0BBh, 0BBh, 007h, 077h
        DB 017h, 077h, 077h, 017h, 077h, 077h, 017h, 077h, 077h, 017h
        DB 077h, 00Bh, 0BBh, 0BBh, 007h, 000h, 070h, 007h, 000h, 070h
        DB 007h, 000h, 070h, 007h, 000h, 070h, 007h, 00Bh, 0BBh, 0BBh
        DB 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 00Bh, 0BBh, 0BBh, 001h, 077h, 077h, 071h
        DB 077h, 077h, 071h, 077h, 077h, 071h, 077h, 077h, 071h, 00Bh
        DB 0BBh, 0BBh, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h
        DB 070h, 007h, 000h, 070h, 007h, 00Bh, 0BBh, 0BBh, 007h, 000h
        DB 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h
        DB 007h, 00Bh, 0BBh, 0BBh, 007h, 077h, 017h, 077h, 077h, 017h
        DB 077h, 077h, 017h, 077h, 077h, 017h, 077h, 00Bh, 0BBh, 0BBh
        DB 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 00Bh, 0BBh, 0BBh, 007h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 00Bh
        DB 0BBh, 0BBh, 001h, 077h, 077h, 071h, 077h, 077h, 071h, 077h
        DB 077h, 071h, 077h, 077h, 071h, 00Bh, 0BBh, 0BBh, 007h, 000h
        DB 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h
        DB 007h, 00Bh, 0BBh, 0BBh, 007h, 000h, 070h, 007h, 000h, 070h
        DB 007h, 000h, 070h, 007h, 000h, 070h, 007h, 00Bh, 0BBh, 0BBh
        DB 007h, 077h, 017h, 077h, 077h, 017h, 077h, 077h, 017h, 077h
        DB 077h, 017h, 077h, 00Bh, 0BBh, 0BBh, 007h, 000h, 070h, 007h
        DB 000h, 070h, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 00Bh
        DB 0BBh, 0BBh, 007h, 000h, 070h, 007h, 000h, 070h, 007h, 000h
        DB 070h, 007h, 000h, 070h, 007h, 00Bh, 0BBh, 0BBh, 0BBh, 044h
        DB 0B4h, 04Bh, 044h, 0B4h, 04Bh, 044h, 0B4h, 04Bh, 044h, 0B4h
        DB 04Bh, 04Bh, 0BBh, 0BBh, 0BBh, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 05Bh, 0BBh, 002h
        DB 000h, 014h, 000h, 002h, 004h, 000h, 000h, 000h, 004h, 01Bh
        DB 000h, 000h, 000h, 004h, 018h, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Bh, 0BBh, 000h, 0AAh, 0AAh, 0AAh, 0AAh, 067h, 066h, 066h
        DB 076h, 0AAh, 0AAh, 0AAh, 0AAh, 000h, 07Bh, 0BBh, 000h, 0A0h
        DB 001h, 010h, 00Ah, 007h, 000h, 000h, 070h, 0A0h, 001h, 010h
        DB 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah, 0AAh, 0AAh, 0A0h, 007h
        DB 000h, 000h, 070h, 00Ah, 0AAh, 0AAh, 0A0h, 000h, 07Bh, 0BBh
        DB 000h, 0A0h, 001h, 010h, 00Ah, 007h, 000h, 000h, 070h, 0A0h
        DB 001h, 010h, 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah, 0AAh, 0AAh
        DB 0A0h, 007h, 000h, 000h, 070h, 00Ah, 0AAh, 0AAh, 0A0h, 000h
        DB 07Bh, 0BBh, 000h, 0A0h, 001h, 010h, 00Ah, 007h, 000h, 000h
        DB 070h, 0A0h, 001h, 010h, 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah
        DB 0AAh, 0AAh, 0A0h, 007h, 000h, 000h, 070h, 00Ah, 0AAh, 0AAh
        DB 0A0h, 000h, 07Bh, 0BBh, 000h, 0A0h, 001h, 010h, 00Ah, 007h
        DB 000h, 000h, 070h, 0A0h, 001h, 010h, 00Ah, 000h, 07Bh, 0BBh
        DB 000h, 00Ah, 0AAh, 0AAh, 0A0h, 007h, 000h, 000h, 070h, 00Ah
        DB 0AAh, 0AAh, 0A0h, 000h, 07Bh, 0BBh, 000h, 0A0h, 001h, 010h
        DB 00Ah, 007h, 000h, 000h, 070h, 0A0h, 001h, 010h, 00Ah, 000h
        DB 07Bh, 0BBh, 000h, 00Ah, 0AAh, 0AAh, 0A0h, 007h, 000h, 000h
        DB 070h, 00Ah, 0AAh, 0AAh, 0A0h, 000h, 07Bh, 0BBh, 000h, 0A0h
        DB 001h, 010h, 00Ah, 007h, 000h, 000h, 070h, 0A0h, 001h, 010h
        DB 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah, 0AAh, 0AAh, 0A0h, 007h
        DB 000h, 000h, 070h, 00Ah, 0AAh, 0AAh, 0A0h, 000h, 07Bh, 0BBh
        DB 000h, 0A0h, 001h, 010h, 00Ah, 007h, 000h, 000h, 070h, 0A0h
        DB 001h, 010h, 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah, 0AAh, 0AAh
        DB 0A0h, 007h, 000h, 000h, 070h, 00Ah, 0AAh, 0AAh, 0A0h, 000h
        DB 07Bh, 0BBh, 000h, 0A0h, 001h, 010h, 00Ah, 007h, 000h, 000h
        DB 070h, 0A0h, 001h, 010h, 00Ah, 000h, 07Bh, 0BBh, 000h, 00Ah
        DB 0AAh, 0AAh, 0A0h, 007h, 000h, 000h, 070h, 00Ah, 0AAh, 0AAh
        DB 0A0h, 000h, 07Bh, 0BBh, 000h, 0A0h, 002h, 010h, 00Ah, 007h
        DB 000h, 000h, 070h, 0A0h, 003h, 010h, 00Ah, 000h, 07Bh, 0BBh
        DB 000h, 0AAh, 0AAh, 0AAh, 0AAh, 007h, 000h, 000h, 070h, 0AAh
        DB 0AAh, 0AAh, 0AAh, 00Bh, 07Bh, 0BBh, 000h, 080h, 000h, 000h
        DB 008h, 007h, 000h, 000h, 070h, 080h, 000h, 000h, 008h, 009h
        DB 07Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 044h, 044h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 002h, 000h, 014h
        DB 000h
lab265: DB 002h, 003h, 000h, 000h, 000h, 004h, 01Ch, 000h, 000h, 000h
        DB 004h, 018h, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h, 00Ah
        DB 07Bh, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 009h, 07Bh, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0BBh, 0BBh, 076h, 066h, 066h, 066h, 066h
        DB 066h, 06Bh, 0BBh, 066h, 066h, 066h, 066h, 066h, 067h, 0BBh
        DB 0BBh, 071h, 011h, 011h, 011h, 011h, 011h, 011h, 031h, 011h
        DB 011h, 011h, 011h, 011h, 017h, 0BBh, 0BBh, 076h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h
        DB 067h, 0BBh, 0BBh, 071h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 017h, 0BBh, 0BBh, 071h
        DB 0BBh, 044h, 04Bh, 0BBh, 044h, 04Bh, 0BBh, 044h, 04Bh, 0B4h
        DB 0BBh, 04Bh, 017h, 0BBh, 0BBh, 071h, 0B5h, 055h, 055h, 0B5h
        DB 055h, 055h, 0B5h, 055h, 055h, 0B5h, 0BBh, 05Bh, 017h, 0BBh
        DB 0BBh, 071h, 0B5h, 0BBh, 0B5h, 0B5h, 0BBh, 0B5h, 0B5h, 0BBh
        DB 0B5h, 0B5h, 0BBh, 05Bh, 017h, 0BBh, 0BBh, 071h, 0B5h, 0BBh
        DB 0B5h, 0B5h, 0BBh, 0BBh, 0B5h, 0BBh, 0BBh, 0B5h, 0BBh, 05Bh
        DB 017h, 0BBh, 0BBh, 071h, 0B5h, 0BBh, 0B5h, 0B5h, 055h, 055h
        DB 0B5h, 0BBh, 0BBh, 0B5h, 0BBh, 05Bh, 017h, 0BBh, 0BBh, 071h
        DB 0B5h, 055h, 055h, 0BBh, 0BBh, 0B5h, 0B5h, 0BBh, 0BBh, 0B5h
        DB 0BBh, 05Bh, 017h, 0BBh, 0BBh, 071h, 0B5h, 055h, 055h, 0B5h
        DB 0BBh, 0B5h, 0B5h, 0BBh, 0B5h, 0B5h, 0BBh, 05Bh, 017h, 0BBh
        DB 0BBh, 071h, 0B5h, 0BBh, 0B5h, 0B5h, 055h, 055h, 0B5h, 055h
        DB 055h, 0B5h, 0BBh, 05Bh, 017h, 0BBh, 0BBh, 071h, 0B5h, 0BBh
        DB 0B5h, 0BBh, 055h, 05Bh, 0BBh, 055h, 05Bh, 0B5h, 0BBh, 05Bh
        DB 017h, 0BBh, 0BBh, 071h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 017h, 0BBh, 0BBh, 071h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 021h, 011h, 011h, 011h
        DB 011h, 011h, 017h, 0BBh, 0BBh, 070h, 070h, 070h, 070h, 070h
        DB 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 0BBh
        DB 0BBh, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h
        DB 007h, 007h, 007h, 007h, 007h, 0BBh, 0BBh, 070h, 070h, 070h
        DB 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h
        DB 070h, 0BBh, 0BBh, 007h, 007h, 007h, 007h, 007h, 007h, 007h
        DB 007h, 007h, 007h, 007h, 007h, 007h, 007h, 0BBh, 0BBh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0BBh, 003h, 000h, 014h, 000h, 002h, 00Fh
        DB 000h, 000h, 000h, 004h, 010h, 000h, 000h, 000h, 004h, 018h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B0h, 00Bh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 000h, 000h, 00Ah
        DB 0ABh, 002h, 0B0h, 000h, 000h, 000h, 0AAh, 0AAh, 0A0h, 000h
        DB 070h, 0BBh, 0BBh, 07Ah, 0AAh, 000h, 00Ah, 07Ah, 0B1h, 00Ah
        DB 0AAh, 070h, 000h, 000h, 0A7h, 0AAh, 0AAh, 0BBh, 0BBh, 07Ah
        DB 01Ah, 066h, 00Ah, 07Ah, 006h, 06Ah, 0AAh, 07Ah, 0AAh, 00Ah
        DB 0A7h, 000h, 000h, 0BBh, 0BBh, 07Ah, 0AAh, 006h, 06Ah, 070h
        DB 000h, 000h, 000h, 07Ah, 0A0h, 009h, 007h, 000h, 007h, 0BBh
        DB 0BBh, 070h, 001h, 000h, 00Bh, 070h, 066h, 06Bh, 070h, 070h
        DB 0AAh, 06Ah, 0AAh, 0AAh, 007h, 0BBh, 0BBh, 070h, 00Ah, 000h
        DB 00Bh, 070h, 000h, 00Bh, 070h, 070h, 000h, 000h, 000h, 000h
        DB 007h, 0BBh, 0BBh, 070h, 000h, 000h, 00Bh, 070h, 00Bh, 07Bh
        DB 070h, 076h, 066h, 066h, 066h, 066h, 067h, 0BBh, 0BBh, 070h
        DB 07Ah, 060h, 00Ah, 0AAh, 060h, 07Bh, 070h, 000h, 000h, 000h
        DB 000h, 000h, 007h, 0BBh, 0BBh, 070h, 070h, 000h, 000h, 000h
        DB 000h, 07Bh, 070h, 0AAh, 0AAh, 07Ah, 0ABh, 04Bh, 067h, 0BBh
        DB 0BBh, 070h, 070h, 00Ah, 06Ah, 0AAh, 06Ah, 0ABh, 070h, 001h
        DB 0ABh, 07Ah, 00Bh, 0BBh, 007h, 0BBh, 0BBh, 070h, 070h, 000h
        DB 000h, 000h, 000h, 000h, 070h, 06Ah, 0AAh, 07Ah, 00Ah, 0AAh
        DB 007h, 0BBh, 0BBh, 070h, 070h, 0BBh, 06Bh, 0BBh, 06Bh, 0BBh
        DB 0B0h, 00Ah, 0AAh, 07Ah, 00Ah, 01Ah, 007h, 0BBh, 0BBh, 070h
        DB 070h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0ABh, 07Ah
        DB 06Ah, 0AAh, 007h, 0BBh, 0BBh, 070h, 0A6h, 066h, 0AAh, 0A0h
        DB 0AAh, 0AAh, 0A6h, 0B0h, 000h, 070h, 00Ah, 0AAh, 007h, 0BBh
        DB 0BBh, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0B0h
        DB 030h, 070h, 0A0h, 0AAh, 007h, 0BBh, 0BBh, 07Bh, 0B4h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 0B0h, 0B0h, 070h, 0AAh, 001h
        DB 007h, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0B0h, 0B0h, 070h, 0AAh, 0AAh, 007h, 0BBh, 0BBh, 070h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0A0h, 070h
        DB 000h, 000h, 007h, 0BBh, 0BBh, 070h, 0AAh, 0AAh, 0AAh, 0A7h
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 076h, 066h, 066h, 067h, 0BBh
        DB 0BBh, 070h, 000h, 000h, 000h, 0A0h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 000h, 007h, 0BBh, 0BBh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 044h, 044h
        DB 04Ah, 0BBh, 002h, 000h, 014h, 000h, 002h, 00Bh, 000h, 003h
        DB 000h, 004h, 01Ah, 000h, 006h, 000h, 004h, 018h, 0BBh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0B7h, 0BBh, 0BBh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 07Ah, 0AAh, 0AAh, 0AAh, 07Ah, 0AAh, 0AAh, 0A7h, 0B7h, 0BBh
        DB 0BBh, 000h, 00Bh, 066h, 06Ah, 066h, 07Bh, 066h, 06Ah, 066h
        DB 07Bh, 066h, 06Ah, 067h, 0B7h, 0BBh, 0BBh, 000h, 00Bh, 000h
        DB 00Bh, 000h, 07Bh, 000h, 00Bh, 010h, 07Bh, 000h, 00Bh, 007h
        DB 0B7h, 0BBh, 0BBh, 000h, 00Bh, 011h, 01Ah, 010h, 07Ah, 011h
        DB 01Bh, 0A0h, 07Ah, 011h, 01Bh, 017h, 0B7h, 0BBh, 0BBh, 000h
        DB 0BBh, 0BBh, 0BBh, 066h, 06Bh, 0BBh, 0BBh, 066h, 06Bh, 0BBh
        DB 0BBh, 0B7h, 0B7h, 0BBh, 0BBh, 000h, 080h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h, 0B7h, 0BBh
        DB 0BBh, 00Bh, 0BBh, 0BBh, 0BBh, 066h, 06Bh, 0BBh, 0BBh, 060h
        DB 06Bh, 0BBh, 0BBh, 0B7h, 0B7h, 0BBh, 0BBh, 001h, 018h, 000h
        DB 000h, 000h, 008h, 000h, 000h, 000h, 008h, 000h, 000h, 007h
        DB 097h, 0BBh, 0B4h, 04Bh, 0BBh, 0B7h, 0BBh, 0BBh, 0B7h, 0BBh
        DB 0BBh, 0B6h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B5h, 05Bh
        DB 00Bh, 007h, 00Bh, 000h, 007h, 000h, 00Bh, 044h, 04Bh, 000h
        DB 01Bh, 000h, 00Bh, 0BBh, 0BBh, 0BBh, 00Bh, 007h, 00Bh, 000h
        DB 0BBh, 000h, 00Bh, 0BBh, 0BBh, 000h, 07Bh, 000h, 00Bh, 0BBh
        DB 0BBh, 000h, 008h, 007h, 008h, 000h, 0B0h, 000h, 00Ah, 001h
        DB 000h, 000h, 07Bh, 003h, 00Bh, 0BBh, 0BBh, 0B7h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 07Bh, 0BBh
        DB 07Bh, 0BBh, 0BBh, 007h, 00Ah, 000h, 00Bh, 000h, 00Bh, 066h
        DB 06Bh, 066h, 06Bh, 0BBh, 0BBh, 000h, 07Bh, 0BBh, 0BBh, 067h
        DB 06Ah, 007h, 00Bh, 000h, 00Bh, 044h, 04Bh, 044h, 04Bh, 0B1h
        DB 0AAh, 000h, 07Bh, 0BBh, 0BBh, 007h, 00Ah, 007h, 01Bh, 000h
        DB 01Bh, 055h, 05Bh, 055h, 05Bh, 07Ah, 07Ah, 00Bh, 07Bh, 0BBh
        DB 0BBh, 0B7h, 0BBh, 0B7h, 0BBh, 07Bh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 07Bh, 07Bh, 0BBh, 07Bh, 0BBh, 0BBh, 007h, 00Ah, 007h
        DB 00Bh, 070h, 000h, 000h, 02Bh, 000h, 00Bh, 07Bh, 07Bh, 000h
        DB 07Bh, 0BBh, 0BBh, 067h, 06Ah, 007h, 00Bh, 006h, 066h, 066h
        DB 00Bh, 000h, 00Bh, 07Bh, 07Bh, 000h, 07Bh, 0BBh, 0BBh, 007h
        DB 008h, 007h, 008h, 00Ah, 0AAh, 0AAh, 0AAh, 000h, 00Ah, 07Bh
        DB 078h, 000h, 07Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 003h, 000h, 014h, 000h, 002h, 005h, 000h, 000h, 000h, 004h
        DB 01Ah, 000h, 000h, 000h, 004h, 018h, 0BBh, 0BBh, 0B0h, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 00Bh
        DB 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 011h, 07Bh, 0B7h, 0BBh, 070h
        DB 007h, 0BBh, 071h, 017h, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh
        DB 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h
        DB 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h
        DB 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh
        DB 0BBh, 070h, 007h, 0BBh, 070h, 007h, 000h, 07Bh, 0B7h, 000h
        DB 07Bh, 0B7h, 011h, 07Bh, 0B7h, 0BBh, 0BBh, 0BBh, 0B7h, 0BBh
        DB 0BBh, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 0BBh
        DB 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh
        DB 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh
        DB 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h
        DB 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 000h, 070h, 007h
        DB 0BBh, 071h, 017h, 0BBh, 070h, 007h, 000h, 07Bh, 0B7h, 0BBh
        DB 0BBh, 07Bh, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 07Bh, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh
        DB 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh
        DB 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh
        DB 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 071h
        DB 017h, 0BBh, 07Bh, 0B7h, 000h, 07Bh, 0B7h, 011h, 07Bh, 0B7h
        DB 000h, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 0BBh, 0B7h
        DB 0BBh, 07Bh, 0BBh, 0BBh, 0BBh, 0B7h, 0BBh, 0BBh, 0B7h, 0BBh
        DB 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh
        DB 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh
        DB 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh
        DB 0B7h, 0BBh, 0BBh, 070h, 007h, 0BBh, 070h, 007h, 0B0h, 070h
        DB 007h, 000h, 07Bh, 0B7h, 000h, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh
        DB 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0BBh, 0BBh, 07Bh, 0BBh
        DB 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h
        DB 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh
        DB 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh
        DB 07Bh, 0B7h, 0BBh, 07Bh, 0B7h, 0BBh, 0BBh, 070h, 007h, 000h
        DB 07Bh, 0B7h, 011h, 070h, 007h, 0BBh, 071h, 017h, 0BBh, 071h
        DB 037h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 003h, 000h
        DB 012h, 000h, 001h, 00Dh, 000h, 012h, 000h, 002h, 011h, 000h
        DB 012h, 000h, 002h, 018h, 0BBh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 07Bh, 0BBh
        DB 0BBh, 076h, 066h, 066h, 066h, 066h, 066h, 06Ah, 000h, 000h
        DB 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h
        DB 000h, 000h, 000h, 00Ah, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Bh, 0BBh, 0BBh, 070h, 000h
lab86:  DB 000h, 000h, 000h, 00Ah, 0AAh, 0AAh, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0AAh
        DB 0A0h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh
        DB 070h, 000h, 000h, 000h, 000h, 000h, 0A1h, 0A0h, 000h, 000h
        DB 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 000h, 0AAh, 0A7h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 00Ah, 0AAh, 007h, 00Ah
        DB 0AAh, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h
        DB 000h, 000h, 000h, 00Ah, 017h, 01Ah, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0AAh
lab63:  DB 0A7h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh
        DB 070h, 000h, 000h, 000h, 00Ah, 0AAh, 007h, 00Ah, 0AAh, 000h
        DB 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 000h, 00Ah, 017h, 01Ah, 000h, 000h, 000h, 000h, 000h, 07Bh
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 00Ah, 0AAh, 0A7h, 0AAh
        DB 0AAh, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h
        DB 000h, 000h, 0AAh, 0AAh, 007h, 00Ah, 0AAh, 0A0h, 000h, 000h
        DB 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 00Ah
        DB 017h, 01Ah, 000h, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh
        DB 070h, 000h, 000h, 000h, 0AAh, 0AAh, 0A7h, 0AAh, 0AAh, 0A0h
        DB 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 00Ah
        DB 0AAh, 0AAh, 007h, 00Ah, 0AAh, 0AAh, 000h, 000h, 000h, 07Bh
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0A0h, 007h, 000h
        DB 0A0h, 000h, 000h, 000h, 00Bh, 07Bh, 0BBh, 0BBh, 070h, 0B0h
        DB 000h, 000h, 000h, 0A0h, 037h, 020h, 0A0h, 000h, 000h, 000h
        DB 009h, 07Bh, 0BBh, 0BBh, 0BBh, 0B6h, 066h, 06Ah, 0AAh, 0AAh
        DB 0AAh, 0AAh, 0AAh, 0AAh, 066h, 066h, 06Bh, 0BBh, 0BBh, 0BBh
        DB 044h, 044h, 044h, 04Ah, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 044h, 044h, 044h, 04Bh, 0BBh, 0BBh, 055h, 055h, 055h, 05Ah
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 055h, 055h, 055h, 05Bh
        DB 0BBh, 003h, 000h, 014h, 000h, 001h, 00Dh, 000h, 000h, 000h
        DB 004h, 014h, 000h, 000h, 000h, 004h, 018h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 0B0h, 0B4h, 044h, 04Bh, 00Bh, 044h, 044h
        DB 04Bh, 07Bh, 044h, 055h, 0BBh, 0BBh, 0BBh, 0B5h, 055h, 0B0h
        DB 0B5h, 055h, 05Bh, 00Bh, 0BBh, 0B5h, 05Bh, 07Bh, 055h, 055h
        DB 0B1h, 000h, 001h, 0B5h, 0BBh, 0B0h, 0BBh, 0B5h, 05Bh, 030h
        DB 007h, 0B5h, 05Bh, 07Bh, 055h, 055h, 0BBh, 07Bh, 0BBh, 0B5h
        DB 0B0h, 007h, 000h, 0B5h, 05Bh, 0BBh, 0B7h, 0BBh, 0BBh, 07Bh
        DB 0B5h, 055h, 05Bh, 07Bh, 055h, 055h, 0B7h, 0BBh, 0B7h, 0B5h
        DB 055h, 055h, 0B7h, 000h, 009h, 070h, 0B5h, 055h, 05Bh, 07Bh
        DB 055h, 055h, 0B7h, 0B5h, 0B7h, 0B5h, 055h, 055h, 0BBh, 07Bh
        DB 0BBh, 0BBh, 0B5h, 055h, 05Bh, 07Bh, 055h, 055h, 0B7h, 0B5h
        DB 0B7h, 0B5h, 055h, 055h, 05Bh, 07Bh, 055h, 055h, 055h, 055h
        DB 05Bh, 07Bh, 0BBh, 0BBh, 0B7h, 0B5h, 0B7h, 0BBh, 0BBh, 0BBh
        DB 05Bh, 07Bh, 0BBh, 0BBh, 0B5h, 055h, 05Bh, 070h, 000h, 000h
        DB 007h, 0B5h, 0B7h, 000h, 000h, 01Bh, 05Bh, 070h, 000h, 010h
        DB 0B5h, 055h, 05Bh, 07Bh, 0BBh, 0BBh, 0B7h, 0B5h, 0B7h, 0BBh
        DB 0BBh, 0BBh, 05Bh, 0B7h, 0BBh, 0B4h, 0B5h, 055h, 05Bh, 07Bh
        DB 055h, 055h, 0B7h, 0B5h, 0B7h, 0B5h, 055h, 055h, 055h, 0B7h
        DB 0B5h, 0BBh, 0B5h, 05Bh, 0BBh, 07Bh, 055h, 055h, 0B7h, 0B5h
        DB 0B7h, 0BBh, 0BBh, 0B5h, 055h, 0B7h, 0B5h, 055h, 055h, 05Bh
        DB 000h, 07Bh, 055h, 055h, 0B7h, 0B5h, 0B7h, 000h, 001h, 0B5h
        DB 055h, 0B7h, 0B5h, 0BBh, 0BBh, 05Bh, 07Bh, 0BBh, 0BBh, 0BBh
        DB 0B7h, 0BBh, 0BBh, 0BBh, 07Bh, 0B5h, 0BBh, 0B7h, 0B5h, 0B0h
        DB 02Bh, 05Bh, 070h, 000h, 000h, 000h, 007h, 000h, 0B5h, 05Bh
        DB 07Bh, 055h, 0B0h, 007h, 0B5h, 0B7h, 0BBh, 05Bh, 07Bh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0B5h, 05Bh, 07Bh, 055h, 0B7h, 0BBh
        DB 0B5h, 0B7h, 0B5h, 05Bh, 07Bh, 055h, 055h, 055h, 055h, 055h
        DB 055h, 05Bh, 07Bh, 055h, 0B7h, 0B5h, 055h, 0B7h, 0B5h, 05Bh
        DB 07Bh, 055h, 05Bh, 0BBh, 05Bh, 0BBh, 0BBh, 0BBh, 07Bh, 0BBh
        DB 0B7h, 0BBh, 055h, 0B7h, 0B5h, 05Bh, 07Bh, 055h, 05Bh, 01Bh
        DB 05Bh, 001h, 000h, 000h, 070h, 000h, 007h, 00Bh, 0BBh, 0B7h
        DB 0B5h, 05Bh, 07Bh, 0BBh, 0BBh, 07Bh, 05Bh, 04Bh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 070h, 000h, 007h, 0B5h, 05Bh, 070h, 000h
        DB 000h, 07Bh, 05Bh, 0BBh, 055h, 055h, 055h, 055h, 05Bh, 0BBh
        DB 0BBh, 0BBh, 0B5h, 05Bh, 0BBh, 0BBh, 0BBh, 0BBh, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 00Dh
        DB 000h, 014h, 000h, 002h, 013h, 000h, 002h, 000h, 004h, 016h
        DB 000h, 002h, 000h, 004h, 018h, 0BBh, 0B0h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 0AAh
        DB 066h, 066h, 066h, 066h, 066h, 070h, 07Bh, 0BBh, 0B0h, 000h
        DB 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h, 000h, 000h
        DB 000h, 070h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h
        DB 00Ah, 01Ah, 000h, 000h, 000h, 000h, 000h, 070h, 07Bh, 0BBh
        DB 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h
        DB 000h, 000h, 000h, 070h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h
        DB 000h, 000h, 00Ah, 01Ah, 000h, 000h, 000h, 000h, 000h, 070h
        DB 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah
        DB 000h, 000h, 000h, 000h, 000h, 070h, 07Bh, 0BBh, 0B0h, 000h
        DB 000h, 000h, 0AAh, 0AAh, 0AAh, 06Ah, 0AAh, 0AAh, 0A0h, 000h
        DB 000h, 070h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 0A1h, 011h
        DB 031h, 011h, 011h, 011h, 0A0h, 000h, 000h, 070h, 07Bh, 0BBh
        DB 0B0h, 000h, 000h, 000h, 0AAh, 0AAh, 0AAh, 06Ah, 0AAh, 0AAh
        DB 0A6h, 066h, 000h, 070h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h
        DB 000h, 000h, 00Ah, 02Ah, 000h, 000h, 000h, 000h, 000h, 070h
        DB 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah
        DB 000h, 000h, 000h, 076h, 066h, 060h, 07Bh, 0BBh, 0B0h, 000h
        DB 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h, 000h, 070h
        DB 000h, 000h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h
        DB 00Ah, 01Ah, 000h, 000h, 000h, 070h, 06Bh, 0B0h, 07Bh, 0BBh
        DB 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h
        DB 000h, 070h, 00Ah, 090h, 07Bh, 0BBh, 0B0h, 000h, 000h, 000h
        DB 000h, 000h, 00Ah, 01Ah, 000h, 000h, 000h, 070h, 0BBh, 0B6h
        DB 07Bh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah
        DB 000h, 000h, 000h, 070h, 000h, 000h, 0BBh, 0BBh, 0B0h, 000h
        DB 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h, 000h, 070h
        DB 000h, 000h, 0BBh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h
        DB 00Ah, 01Ah, 000h, 000h, 000h, 070h, 000h, 000h, 0BBh, 0BBh
        DB 010h, 000h, 000h, 000h, 000h, 000h, 00Ah, 01Ah, 000h, 000h
        DB 000h, 070h, 000h, 000h, 0BBh, 0BBh, 0B6h, 066h, 066h, 066h
        DB 070h, 000h, 00Ah, 01Ah, 000h, 000h, 076h, 060h, 000h, 000h
        DB 0BBh, 0BBh, 0B4h, 044h, 044h, 044h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0B4h, 044h, 044h, 044h, 0BBh, 002h, 000h, 013h
        DB 000h, 004h, 00Ah, 000h, 000h, 000h, 004h, 016h, 000h, 000h
        DB 000h, 004h, 018h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 00Bh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 00Bh, 0BBh, 0BBh, 0B7h, 0BBh, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0B7h, 0BBh, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0B7h
        DB 0BBh, 0BBh, 000h, 007h, 066h, 076h, 067h, 066h, 076h, 0A6h
        DB 076h, 067h, 066h, 076h, 067h, 0B7h, 0BBh, 0BBh, 000h, 007h
        DB 000h, 070h, 007h, 000h, 070h, 0A0h, 070h, 007h, 000h, 070h
        DB 007h, 0B7h, 0BBh, 0BBh, 000h, 007h, 066h, 076h, 067h, 066h
        DB 07Ah, 00Ah, 076h, 067h, 066h, 076h, 067h, 0B7h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 070h, 007h, 000h, 07Ah, 01Ah, 070h, 007h
        DB 000h, 070h, 007h, 0B7h, 0BBh, 0BBh, 000h, 007h, 066h, 076h
        DB 067h, 066h, 0A6h, 066h, 0A6h, 067h, 066h, 076h, 067h, 0B7h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 070h, 007h, 000h, 0A0h, 010h
        DB 0A0h, 007h, 000h, 070h, 007h, 0B7h, 0BBh, 0BBh, 000h, 007h
        DB 066h, 076h, 007h, 00Ah, 066h, 066h, 06Ah, 007h, 006h, 076h
        DB 067h, 0B7h, 0BBh, 0BBh, 000h, 007h, 000h, 070h, 007h, 000h
        DB 000h, 010h, 000h, 007h, 000h, 070h, 007h, 0B7h, 0BBh, 0BBh
        DB 000h, 007h, 066h, 076h, 007h, 0A0h, 070h, 066h, 070h, 0A7h
        DB 006h, 076h, 067h, 0B7h, 0BBh, 0BBh, 000h, 007h, 000h, 070h
        DB 007h, 0A0h, 070h, 030h, 070h, 0A7h, 000h, 070h, 007h, 0B7h
        DB 0BBh, 0BBh, 000h, 007h, 066h, 076h, 00Ah, 000h, 076h, 060h
        DB 070h, 00Ah, 006h, 076h, 067h, 0B7h, 0BBh, 0BBh, 000h, 007h
        DB 000h, 070h, 008h, 006h, 070h, 020h, 076h, 008h, 000h, 070h
        DB 007h, 0B7h, 0BBh, 0BBh, 000h, 007h, 066h, 076h, 0AAh, 0A0h
        DB 070h, 066h, 070h, 0AAh, 0A6h, 076h, 067h, 0B7h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 070h, 000h, 000h, 070h, 010h, 070h, 000h
        DB 000h, 070h, 007h, 097h, 0BBh, 0BBh, 0B7h, 0B7h, 066h, 070h
        DB 0A0h, 006h, 076h, 060h, 076h, 000h, 0A0h, 076h, 067h, 0BBh
        DB 0BBh, 0BBh, 0B7h, 0B4h, 044h, 044h, 0AAh, 0A4h, 044h, 044h
        DB 044h, 0AAh, 0A4h, 044h, 044h, 0BBh, 0BBh, 0BBh, 007h, 0B5h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 0BBh, 0BBh, 007h, 0B5h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 0BBh, 0BBh
        DB 0BBh, 0B5h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 0BBh, 002h, 000h, 014h, 000h, 002h
        DB 00Eh, 000h, 000h, 000h, 004h, 012h, 000h, 000h, 000h, 004h
        DB 018h, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0A0h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 007h, 066h, 066h, 066h, 066h, 066h, 06Ah, 06Ah, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 001h, 000h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 000h, 0AAh, 06Ah
        DB 0A0h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 001h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 00Ah
        DB 00Ah, 00Ah, 00Ah, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 000h, 000h, 001h, 002h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 000h
        DB 000h, 0AAh, 00Ah, 00Ah, 00Ah, 0A0h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 000h, 001h, 001h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h
        DB 000h, 000h, 00Ah, 0A0h, 0AAh, 00Ah, 0A0h, 0AAh, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 000h
        DB 001h, 003h, 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 0AAh, 00Ah, 0AAh, 00Ah, 0AAh, 00Ah
        DB 0A0h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 001h, 000h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 00Ah, 0A0h, 0AAh, 0AAh, 00Ah
        DB 0AAh, 0A0h, 0AAh, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 001h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 007h, 000h, 000h, 0AAh, 00Ah, 0AAh
        DB 0AAh, 00Ah, 0AAh, 0AAh, 00Ah, 0A0h, 000h, 007h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 000h, 000h, 001h, 001h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 000h, 00Ah, 0AAh
        DB 0AAh, 0AAh, 0AAh, 00Ah, 0AAh, 0AAh, 0AAh, 0AAh, 000h, 0B7h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 097h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0B4h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 0BBh, 0BBh, 0BBh, 002h, 000h, 014h, 000h, 002h, 00Fh, 000h
        DB 012h, 000h, 002h, 013h, 000h, 005h, 000h, 002h, 018h, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0B7h, 0BBh, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 001h, 071h, 000h, 0B7h
        DB 0BBh, 0BBh, 000h, 007h, 0AAh, 0AAh, 0AAh, 0ABh, 0BBh, 0BBh
        DB 0AAh, 070h, 007h, 077h, 000h, 0B7h, 0BBh, 0BBh, 001h, 000h
        DB 000h, 020h, 010h, 01Bh, 000h, 00Bh, 000h, 070h, 077h, 007h
        DB 070h, 0B7h, 0BBh, 0BBh, 007h, 000h, 000h, 0A6h, 0AAh, 0ABh
        DB 07Ah, 07Bh, 0B0h, 070h, 070h, 000h, 070h, 0B7h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 000h, 000h, 07Ah, 071h, 000h, 07Ah
        DB 0A0h, 000h, 0BBh, 0B7h, 0BBh, 0BBh, 007h, 066h, 066h, 066h
        DB 066h, 060h, 07Ah, 0AAh, 0A6h, 000h, 000h, 000h, 007h, 0B7h
        DB 0BBh, 0BBh, 007h, 000h, 000h, 000h, 000h, 000h, 070h, 000h
        DB 000h, 000h, 066h, 066h, 067h, 0B7h, 0BBh, 0BBh, 077h, 00Ah
        DB 0AAh, 0AAh, 0AAh, 0AAh, 0A0h, 001h, 01Bh, 06Bh, 00Bh, 00Bh
        DB 007h, 0B7h, 0BBh, 0BBh, 070h, 000h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0B6h, 06Bh, 0BBh, 00Bh, 00Bh, 04Bh, 007h, 0B7h, 0BBh, 0BBh
        DB 070h, 000h, 0BBh, 0BBh, 0BBh, 0BBh, 0B4h, 04Bh, 055h, 04Bh
        DB 00Bh, 0BBh, 007h, 0B7h, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 000h, 00Bh, 0BBh, 055h, 055h, 0BBh, 000h, 000h, 007h, 0B7h
        DB 0BBh, 0BBh, 070h, 00Bh, 0B6h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 0BBh, 0A0h, 0A0h, 001h, 007h, 0B7h, 0BBh, 0BBh, 07Bh, 044h
        DB 044h, 0B6h, 000h, 000h, 000h, 0AAh, 0A0h, 00Bh, 077h, 0A6h
        DB 007h, 0B7h, 0BBh, 0BBh, 07Bh, 055h, 055h, 0B0h, 011h, 011h
        DB 07Bh, 07Bh, 07Ah, 00Bh, 07Ah, 0B1h, 007h, 0B7h, 0BBh, 0BBh
        DB 07Bh, 0BBh, 0B5h, 0B0h, 066h, 066h, 066h, 070h, 070h, 000h
        DB 000h, 006h, 007h, 0B7h, 0BBh, 0BBh, 070h, 000h, 0B5h, 0B0h
        DB 00Bh, 000h, 000h, 0A6h, 0A6h, 0A6h, 0A6h, 0A0h, 007h, 0B7h
        DB 0BBh, 0BBh, 070h, 000h, 0B5h, 0B0h, 00Bh, 0BBh, 0BBh, 07Ah
        DB 07Ah, 07Ah, 07Ah, 07Ah, 007h, 0B7h, 0BBh, 0BBh, 077h, 000h
        DB 0B5h, 0B0h, 009h, 00Bh, 030h, 0A7h, 0A7h, 0A7h, 0A7h, 0A7h
        DB 007h, 0B7h, 0BBh, 0BBh, 007h, 000h, 0B5h, 0B4h, 0BBh, 00Bh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h, 0BBh, 0BBh
        DB 007h, 000h, 0B5h, 055h, 0B0h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 002h, 000h, 010h, 000h, 002h, 00Fh, 000h, 000h, 000h
        DB 004h, 011h, 000h, 000h, 000h, 004h, 018h, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0B0h, 0B0h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 07Bh, 0B0h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Bh, 07Bh, 0B7h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h
        DB 066h, 066h, 066h, 07Bh, 07Bh, 0B7h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0A0h, 000h, 000h, 000h, 000h, 000h, 07Bh
        DB 07Bh, 0B7h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0A0h
        DB 000h, 000h, 000h, 000h, 000h, 07Bh, 07Bh, 0B7h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 00Ah, 0AAh, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 07Bh, 0B7h, 007h, 066h, 066h, 066h, 066h, 066h
        DB 06Ah, 0AAh, 066h, 066h, 066h, 066h, 066h, 07Bh, 07Bh, 0B7h
        DB 007h, 000h, 000h, 000h, 000h, 000h, 0AAh, 00Ah, 0A0h, 000h
        DB 000h, 000h, 000h, 07Bh, 07Bh, 0B7h, 000h, 066h, 066h, 070h
        DB 000h, 000h, 0AAh, 01Ah, 0A0h, 000h, 000h, 076h, 066h, 00Bh
        DB 07Bh, 0B7h, 007h, 066h, 066h, 060h, 000h, 00Ah, 0A6h, 066h
        DB 0AAh, 000h, 000h, 066h, 066h, 07Bh, 07Bh, 0B7h, 007h, 000h
        DB 000h, 000h, 000h, 00Ah, 0A0h, 010h, 0AAh, 000h, 000h, 000h
        DB 000h, 07Bh, 07Bh, 0B7h, 006h, 066h, 066h, 066h, 066h, 0AAh
        DB 066h, 066h, 06Ah, 0A6h, 066h, 066h, 066h, 06Bh, 07Bh, 0B7h
        DB 000h, 000h, 000h, 000h, 000h, 0AAh, 000h, 020h, 00Ah, 0A0h
        DB 000h, 000h, 000h, 00Bh, 07Bh, 0B7h, 000h, 000h, 000h, 000h
        DB 00Ah, 0A6h, 076h, 060h, 076h, 0AAh, 000h, 000h, 000h, 00Bh
        DB 07Bh, 0B7h, 000h, 000h, 000h, 000h, 00Ah, 0A0h, 070h, 030h
        DB 070h, 0AAh, 000h, 000h, 000h, 00Bh, 07Bh, 0B7h, 000h, 000h
        DB 000h, 000h, 0AAh, 006h, 070h, 066h, 076h, 00Ah, 0A0h, 000h
        DB 000h, 00Bh, 07Bh, 0B7h, 000h, 000h, 000h, 000h, 0A0h, 000h
        DB 070h, 010h, 070h, 000h, 0A0h, 000h, 000h, 009h, 07Bh, 0BBh
        DB 066h, 066h, 066h, 066h, 0A0h, 0A6h, 076h, 060h, 076h, 0A0h
        DB 0A6h, 066h, 066h, 06Bh, 0BBh, 0BBh, 044h, 044h, 044h, 044h
        DB 0AAh, 0A4h, 044h, 044h, 044h, 0AAh, 0A4h, 044h, 044h, 04Bh
        DB 0BBh, 0BBh, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 05Bh, 0BBh, 0BBh, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 05Bh, 0BBh, 0BBh, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 05Bh, 0BBh, 003h
        DB 000h, 014h, 000h, 002h, 00Eh, 000h, 000h, 000h, 004h, 012h
        DB 000h, 000h, 000h, 004h, 018h, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0A0h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 007h, 066h, 066h, 066h, 066h
        DB 06Ah, 0AAh, 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 000h, 000h, 000h, 000h, 010h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h, 000h, 000h
        DB 000h, 000h, 0A6h, 0A6h, 0A0h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h
        DB 000h, 000h, 000h, 00Ah, 00Ah, 0AAh, 00Ah, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 007h, 000h, 000h, 000h, 000h
        DB 010h, 000h, 010h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 000h, 000h, 0A0h, 0AAh, 00Ah, 0A0h, 0A0h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h, 000h, 000h
        DB 000h, 001h, 000h, 000h, 001h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 000h, 00Ah, 006h, 0B0h, 000h
        DB 0BAh, 06Ah, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h
        DB 000h, 000h, 000h, 000h, 0B4h, 044h, 0B0h, 000h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 007h, 000h, 000h, 0A0h, 070h
        DB 0BBh, 0BBh, 0B0h, 070h, 0A0h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 000h, 007h, 000h, 000h, 001h, 008h, 000h, 000h, 000h, 001h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h, 000h, 00Ah
        DB 00Ah, 066h, 0AAh, 00Ah, 0AAh, 0AAh, 00Ah, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 007h, 000h, 000h, 010h, 000h, 002h, 000h
        DB 000h, 000h, 010h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 007h
        DB 000h, 0A6h, 0AAh, 00Ah, 0AAh, 060h, 0AAh, 00Ah, 0A6h, 0A0h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 070h, 000h, 080h, 000h, 000h
        DB 000h, 003h, 000h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 000h, 070h, 00Ah, 0AAh, 00Ah, 0A0h, 0AAh, 00Ah, 0AAh, 0A0h
        DB 0AAh, 00Ah, 000h, 0B7h, 0BBh, 0BBh, 000h, 070h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 097h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 006h, 000h, 013h
        DB 000h, 001h, 008h, 000h, 000h, 000h, 004h, 01Ah, 000h, 000h
        DB 000h, 004h, 018h, 0BBh, 000h, 000h, 007h, 003h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 00Bh, 00Bh, 0BBh, 0BBh, 0BBh
        DB 000h, 001h, 007h, 036h, 066h, 066h, 066h, 066h, 066h, 076h
        DB 0B7h, 008h, 010h, 000h, 0BBh, 0BBh, 076h, 066h, 006h, 066h
        DB 066h, 066h, 066h, 066h, 066h, 060h, 007h, 0AAh, 07Ah, 070h
        DB 0BBh, 0BBh, 07Bh, 000h, 000h, 000h, 066h, 066h, 066h, 066h
        DB 066h, 067h, 000h, 000h, 010h, 070h, 0BBh, 0BBh, 07Bh, 076h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 060h, 007h, 000h, 07Ah
        DB 07Ah, 000h, 0BBh, 0BBh, 07Bh, 070h, 01Bh, 000h, 000h, 010h
        DB 001h, 000h, 001h, 007h, 000h, 070h, 010h, 000h, 0BBh, 0BBh
        DB 07Bh, 006h, 06Bh, 076h, 066h, 060h, 007h, 066h, 067h, 007h
        DB 000h, 00Ah, 07Ah, 070h, 0BBh, 0BBh, 07Bh, 070h, 01Bh, 070h
        DB 000h, 000h, 010h, 010h, 000h, 007h, 000h, 000h, 010h, 070h
        DB 0BBh, 0BBh, 07Bh, 076h, 06Bh, 070h, 00Bh, 076h, 067h, 060h
        DB 000h, 007h, 000h, 07Ah, 07Ah, 000h, 0BBh, 0BBh, 07Bh, 000h
        DB 000h, 066h, 07Bh, 070h, 010h, 007h, 066h, 066h, 000h, 070h
        DB 010h, 000h, 0BBh, 0BBh, 07Bh, 000h, 001h, 000h, 07Bh, 070h
        DB 0A0h, 007h, 000h, 000h, 000h, 00Ah, 07Ah, 070h, 0BBh, 0BBh
        DB 07Bh, 001h, 0BBh, 000h, 07Bh, 070h, 010h, 007h, 007h, 061h
        DB 000h, 000h, 010h, 070h, 0BBh, 0BBh, 07Bh, 007h, 000h, 000h
        DB 07Bh, 070h, 0A0h, 007h, 007h, 00Ah, 000h, 07Ah, 07Ah, 000h
        DB 0BBh, 0BBh, 07Bh, 04Bh, 07Bh, 066h, 066h, 070h, 000h, 007h
        DB 007h, 001h, 000h, 070h, 010h, 000h, 0BBh, 0BBh, 07Bh, 0BBh
        DB 07Bh, 0B6h, 0BBh, 0B0h, 076h, 0AAh, 00Bh, 06Ah, 066h, 0AAh
        DB 07Ah, 000h, 0BBh, 0BBh, 07Bh, 000h, 07Bh, 000h, 000h, 007h
        DB 0B6h, 066h, 000h, 001h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 07Bh, 07Bh, 0BBh, 008h, 000h, 007h, 0BBh, 0BBh, 06Bh, 06Ah
        DB 066h, 07Ah, 00Ah, 070h, 0BBh, 0BBh, 07Bh, 07Bh, 000h, 006h
        DB 066h, 069h, 029h, 010h, 000h, 001h, 000h, 008h, 000h, 070h
        DB 0BBh, 0BBh, 07Bh, 07Bh, 006h, 06Bh, 066h, 06Bh, 07Bh, 0BBh
        DB 06Bh, 06Ah, 000h, 007h, 06Ah, 0A0h, 0BBh, 0BBh, 07Bh, 070h
        DB 00Bh, 010h, 000h, 000h, 070h, 000h, 000h, 000h, 000h, 007h
        DB 000h, 000h, 0BBh, 0BBh, 070h, 066h, 06Bh, 066h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 044h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 0BBh, 010h, 000h, 014h, 000h, 002h
        DB 00Bh, 000h, 001h, 000h, 004h, 015h, 000h, 001h, 000h, 004h
        DB 018h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 07Bh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B0h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0BBh, 0BBh, 0B0h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 0B7h, 0AAh, 0AAh, 070h, 000h, 000h, 000h, 070h, 000h, 000h
        DB 000h, 07Ah, 0AAh, 0A7h, 0BBh, 0BBh, 0B7h, 0B1h, 011h, 0B0h
        DB 000h, 000h, 000h, 070h, 000h, 000h, 000h, 0B1h, 011h, 0B7h
        DB 0BBh, 0BBh, 0B7h, 0B1h, 011h, 0B0h, 000h, 000h, 000h, 070h
        DB 000h, 000h, 000h, 0B1h, 011h, 0B7h, 0BBh, 0BBh, 0B7h, 0B1h
        DB 011h, 0A0h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 0A1h
        DB 011h, 0B7h, 0BBh, 0BBh, 0B7h, 0AAh, 0AAh, 0A6h, 066h, 066h
        DB 000h, 070h, 006h, 066h, 066h, 0AAh, 0AAh, 0A7h, 0BBh, 0BBh
        DB 0B7h, 0B6h, 066h, 0B0h, 0A0h, 000h, 000h, 070h, 000h, 000h
        DB 0A0h, 0B6h, 066h, 0B7h, 0BBh, 0BBh, 0B7h, 0B1h, 011h, 0B0h
        DB 0B7h, 000h, 000h, 070h, 000h, 007h, 0B0h, 0B1h, 011h, 0B7h
        DB 0BBh, 0BBh, 0B7h, 0B1h, 011h, 0B0h, 0B7h, 000h, 000h, 070h
        DB 000h, 007h, 0B0h, 0B1h, 011h, 0B7h, 0BBh, 0BBh, 0B7h, 0B1h
        DB 011h, 0B0h, 0B7h, 000h, 000h, 070h, 000h, 007h, 0B0h, 0B1h
        DB 011h, 0B7h, 0BBh, 0BBh, 0B7h, 000h, 000h, 000h, 0B7h, 0BBh
        DB 000h, 070h, 00Bh, 0B7h, 0B0h, 000h, 000h, 007h, 0BBh, 0BBh
        DB 0B7h, 007h, 0BBh, 0BBh, 0BBh, 0BBh, 0B6h, 076h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0B7h, 007h, 0BBh, 0BBh, 0B6h, 067h, 0B5h, 055h
        DB 055h, 055h, 054h, 0B4h, 055h, 055h, 055h, 055h, 0B7h, 066h
        DB 0BBh, 0BBh, 0B0h, 007h, 0B5h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 0B7h, 000h, 0BBh, 0BBh, 0B0h, 007h
        DB 0B5h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 0B7h, 000h, 0BBh, 0BBh, 0B0h, 007h, 0B5h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 0B7h, 000h, 0BBh, 0BBh
        DB 0B0h, 007h, 0B5h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 0B7h, 000h, 0BBh, 0BBh, 0B0h, 007h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h, 000h
        DB 0BBh, 0BBh, 0B0h, 007h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 007h, 000h, 0BBh, 0BBh, 0B4h, 04Bh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 044h, 0BBh, 002h, 000h, 014h, 000h, 002h, 005h, 000h
        DB 000h, 000h, 004h, 01Bh, 000h, 000h, 000h, 004h, 018h, 0BBh
        DB 0BBh, 0B0h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0B0h, 0B7h, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 00Ah, 000h, 000h, 00Ah, 000h, 000h, 000h, 000h, 000h, 0B7h
        DB 0BBh, 0BBh, 070h, 000h, 000h, 011h, 00Ah, 001h, 010h, 00Ah
        DB 000h, 076h, 000h, 000h, 000h, 0B7h, 0BBh, 0BBh, 070h, 000h
        DB 007h, 066h, 07Ah, 006h, 067h, 00Bh, 000h, 070h, 000h, 010h
        DB 000h, 0B7h, 0BBh, 0BBh, 076h, 0B6h, 007h, 000h, 07Ah, 000h
        DB 007h, 00Bh, 000h, 070h, 000h, 0A7h, 000h, 0B7h, 0BBh, 0BBh
        DB 070h, 0B6h, 076h, 066h, 006h, 066h, 060h, 06Bh, 000h, 070h
        DB 000h, 070h, 003h, 0B7h, 0BBh, 0BBh, 070h, 066h, 066h, 000h
        DB 00Ah, 000h, 000h, 000h, 000h, 070h, 000h, 070h, 0AAh, 0A7h
        DB 0BBh, 0BBh, 076h, 066h, 001h, 011h, 010h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 070h, 0B0h, 097h, 0BBh, 0BBh, 070h, 000h
        DB 0A6h, 066h, 06Ah, 066h, 066h, 000h, 000h, 070h, 010h, 070h
        DB 0AAh, 0AAh, 0BBh, 0BBh, 07Bh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 070h, 0BBh, 0B0h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 07Ah, 000h, 000h, 066h, 067h, 000h, 000h, 010h, 000h, 070h
        DB 000h, 000h, 000h, 090h, 0BBh, 0BBh, 07Ah, 0AAh, 07Bh, 001h
        DB 007h, 001h, 00Ah, 0B0h, 000h, 0A6h, 0AAh, 0AAh, 0A6h, 067h
        DB 0BBh, 0BBh, 000h, 00Bh, 07Bh, 006h, 067h, 066h, 000h, 0B4h
        DB 044h, 044h, 044h, 044h, 044h, 0B7h, 0BBh, 0BBh, 010h, 00Bh
        DB 07Bh, 000h, 007h, 000h, 000h, 0B5h, 055h, 055h, 055h, 055h
        DB 055h, 0B7h, 0BBh, 0BBh, 070h, 00Bh, 07Bh, 000h, 007h, 000h
        DB 000h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h, 0BBh, 0BBh
        DB 070h, 00Bh, 07Bh, 001h, 007h, 001h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 071h, 00Bh, 07Bh, 006h
        DB 067h, 066h, 007h, 066h, 011h, 011h, 020h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 077h, 000h, 07Bh, 000h, 007h, 000h, 007h, 000h
        DB 0AAh, 0AAh, 0AAh, 000h, 000h, 007h, 0BBh, 0BBh, 007h, 066h
        DB 000h, 000h, 007h, 000h, 007h, 000h, 076h, 066h, 067h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 007h, 0AAh, 0AAh, 001h, 007h, 001h
        DB 000h, 066h, 060h, 000h, 000h, 066h, 066h, 067h, 0BBh, 0BBh
        DB 007h, 0B0h, 000h, 006h, 060h, 06Ah, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 0BBh, 0BBh, 0BBh, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 04Bh
        DB 0BBh, 002h, 000h, 014h, 000h, 002h, 004h, 000h, 000h, 000h
        DB 004h, 019h, 000h, 000h, 000h, 004h, 018h, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0B7h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0B7h, 0BBh, 0BBh
        DB 007h, 017h, 001h, 010h, 071h, 070h, 011h, 007h, 017h, 001h
        DB 010h, 071h, 070h, 0B7h, 0BBh, 0BBh, 000h, 070h, 066h, 066h
        DB 007h, 006h, 066h, 060h, 070h, 066h, 066h, 007h, 000h, 0B7h
        DB 0BBh, 0BBh, 000h, 070h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 070h, 000h, 000h, 007h, 000h, 0B7h, 0BBh, 0BBh, 000h, 070h
        DB 071h, 017h, 007h, 007h, 013h, 070h, 070h, 071h, 017h, 007h
        DB 000h, 0B7h, 0BBh, 0BBh, 000h, 070h, 006h, 060h, 007h, 000h
        DB 066h, 000h, 070h, 006h, 060h, 007h, 000h, 0B7h, 0BBh, 0BBh
        DB 007h, 017h, 001h, 010h, 071h, 070h, 011h, 007h, 017h, 001h
        DB 010h, 071h, 070h, 0B7h, 0BBh, 0BBh, 000h, 070h, 066h, 066h
        DB 007h, 006h, 066h, 060h, 070h, 066h, 066h, 007h, 000h, 0B7h
        DB 0BBh, 0BBh, 000h, 070h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 070h, 000h, 000h, 007h, 000h, 0B7h, 0BBh, 0BBh, 000h, 070h
        DB 071h, 017h, 007h, 007h, 011h, 070h, 070h, 071h, 017h, 007h
        DB 000h, 0B7h, 0BBh, 0BBh, 000h, 070h, 006h, 060h, 007h, 000h
        DB 066h, 000h, 070h, 006h, 060h, 007h, 000h, 0B7h, 0BBh, 0BBh
        DB 007h, 017h, 001h, 010h, 071h, 070h, 011h, 007h, 017h, 001h
        DB 010h, 071h, 070h, 0B7h, 0BBh, 0BBh, 000h, 070h, 066h, 066h
        DB 007h, 006h, 066h, 060h, 070h, 066h, 066h, 007h, 00Ah, 0B7h
        DB 0BBh, 0BBh, 000h, 070h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 070h, 000h, 000h, 007h, 00Ah, 0B7h, 0BBh, 0BBh, 000h, 070h
        DB 071h, 017h, 007h, 007h, 012h, 070h, 070h, 071h, 017h, 007h
        DB 00Ah, 097h, 0BBh, 0BBh, 000h, 070h, 006h, 060h, 007h, 000h
        DB 066h, 000h, 070h, 006h, 060h, 007h, 00Ah, 0A7h, 0BBh, 0BBh
        DB 007h, 017h, 001h, 010h, 071h, 070h, 011h, 007h, 017h, 001h
        DB 010h, 071h, 070h, 007h, 0BBh, 0BBh, 000h, 070h, 066h, 066h
        DB 007h, 006h, 066h, 060h, 070h, 066h, 066h, 007h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 070h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 070h, 000h, 000h, 007h, 000h, 007h, 0BBh, 0BBh, 000h, 070h
        DB 000h, 000h, 007h, 000h, 000h, 000h, 070h, 000h, 000h, 007h
        DB 000h, 007h, 0BBh, 0BBh, 0BBh, 0B4h, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 04Bh, 0BBh, 004h
        DB 000h, 012h, 000h, 002h, 00Ah, 000h, 000h, 000h, 004h, 016h
        DB 000h, 000h, 000h, 004h, 018h, 0BAh, 0AAh, 0AAh, 0AAh, 0AAh
        DB 00Ah, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 00Ah, 0AAh, 0AAh, 0A7h
        DB 0ABh, 0B0h, 072h, 000h, 010h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 001h, 000h, 017h, 00Bh, 0B0h, 07Ah, 066h
        DB 060h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 006h
        DB 066h, 0A7h, 00Bh, 0B0h, 070h, 000h, 000h, 001h, 001h, 030h
        DB 06Bh, 07Bh, 060h, 011h, 001h, 000h, 000h, 007h, 00Bh, 0B0h
        DB 071h, 000h, 000h, 00Ah, 06Ah, 0ABh, 0AAh, 07Ah, 0ABh, 0AAh
        DB 06Ah, 000h, 000h, 017h, 00Bh, 0B0h, 07Ah, 000h, 000h, 000h
        DB 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h, 000h, 0A7h
        DB 00Bh, 0B0h, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 070h
        DB 000h, 000h, 000h, 000h, 000h, 007h, 00Bh, 0B0h, 071h, 000h
        DB 000h, 011h, 011h, 011h, 010h, 070h, 011h, 011h, 011h, 010h
        DB 000h, 017h, 00Bh, 0B0h, 07Ah, 000h, 066h, 066h, 066h, 066h
        DB 066h, 076h, 066h, 066h, 066h, 066h, 000h, 0A7h, 00Bh, 0B0h
        DB 070h, 000h, 000h, 000h, 000h, 000h, 000h, 070h, 000h, 000h
        DB 000h, 000h, 000h, 007h, 00Bh, 0B0h, 071h, 000h, 000h, 000h
        DB 001h, 011h, 010h, 070h, 011h, 011h, 000h, 000h, 000h, 017h
        DB 00Bh, 0B0h, 07Ah, 000h, 000h, 000h, 066h, 066h, 066h, 076h
        DB 066h, 066h, 060h, 000h, 000h, 0A7h, 00Bh, 0B0h, 078h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h
        DB 000h, 097h, 00Bh, 044h, 0BAh, 070h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 000h, 000h, 000h, 007h, 0ABh, 044h, 055h
        DB 0BAh, 070h, 000h, 000h, 06Bh, 0AAh, 0AAh, 07Ah, 0AAh, 0ABh
        DB 060h, 000h, 007h, 0ABh, 055h, 055h, 0B7h, 000h, 000h, 000h
        DB 00Ah, 001h, 00Ah, 07Ah, 001h, 00Ah, 000h, 000h, 000h, 07Bh
        DB 055h, 055h, 0B7h, 000h, 000h, 000h, 00Ah, 0AAh, 0AAh, 07Ah
        DB 0AAh, 0AAh, 000h, 000h, 000h, 07Bh, 055h, 055h, 0B7h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 055h, 055h, 0B7h, 000h, 000h, 000h, 000h, 001h
        DB 010h, 070h, 011h, 000h, 000h, 000h, 000h, 07Bh, 055h, 055h
        DB 0B7h, 0AAh, 066h, 066h, 0AAh, 0A6h, 06Ah, 07Ah, 066h, 0AAh
        DB 0A6h, 066h, 0AAh, 07Bh, 055h, 055h, 054h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 045h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 055h, 002h, 000h, 014h
        DB 000h, 002h, 00Eh, 000h, 002h, 000h, 004h, 012h, 000h, 002h
        DB 000h, 004h, 018h, 0BBh, 000h, 010h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 070h, 010h, 0BBh, 0BBh
        DB 000h, 070h, 076h, 066h, 066h, 066h, 066h, 000h, 000h, 000h
        DB 000h, 000h, 070h, 070h, 0BBh, 0BBh, 000h, 076h, 010h, 000h
        DB 000h, 000h, 006h, 0A6h, 000h, 000h, 000h, 000h, 016h, 070h
        DB 0BBh, 0BBh, 000h, 070h, 070h, 000h, 000h, 007h, 000h, 000h
        DB 007h, 000h, 000h, 000h, 070h, 070h, 0BBh, 0BBh, 000h, 006h
        DB 070h, 000h, 000h, 000h, 066h, 076h, 060h, 000h, 000h, 000h
        DB 076h, 000h, 0BBh, 0BBh, 000h, 000h, 070h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 070h, 000h, 0BBh, 0BBh
        DB 000h, 010h, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 070h, 010h, 0BBh, 0BBh, 000h, 070h, 070h, 000h
        DB 000h, 000h, 00Ah, 00Ah, 000h, 000h, 000h, 000h, 070h, 070h
        DB 0BBh, 0BBh, 000h, 076h, 010h, 000h, 000h, 000h, 091h, 001h
        DB 090h, 000h, 000h, 000h, 016h, 070h, 0BBh, 0BBh, 000h, 070h
        DB 070h, 000h, 000h, 000h, 0AAh, 00Ah, 0A0h, 000h, 000h, 000h
        DB 070h, 070h, 0BBh, 0BBh, 000h, 006h, 070h, 000h, 000h, 000h
        DB 092h, 003h, 090h, 000h, 000h, 000h, 076h, 000h, 0BBh, 0BBh
        DB 000h, 000h, 070h, 000h, 000h, 00Ah, 0AAh, 00Ah, 0AAh, 000h
        DB 000h, 000h, 070h, 000h, 0BBh, 0BBh, 000h, 010h, 070h, 000h
        DB 000h, 000h, 091h, 001h, 090h, 000h, 000h, 000h, 070h, 010h
        DB 0BBh, 0BBh, 000h, 070h, 070h, 000h, 000h, 0AAh, 0AAh, 00Ah
        DB 0AAh, 0A0h, 000h, 000h, 070h, 070h, 0BBh, 0BBh, 000h, 076h
        DB 010h, 000h, 000h, 000h, 091h, 001h, 090h, 000h, 000h, 000h
        DB 016h, 070h, 0BBh, 0BBh, 000h, 070h, 070h, 000h, 00Ah, 0AAh
        DB 0AAh, 00Ah, 0AAh, 0AAh, 000h, 000h, 070h, 070h, 0BBh, 0BBh
        DB 000h, 006h, 070h, 000h, 000h, 000h, 091h, 001h, 090h, 000h
        DB 000h, 000h, 076h, 000h, 0BBh, 0BBh, 000h, 000h, 070h, 000h
        DB 0AAh, 0AAh, 0AAh, 00Ah, 0AAh, 0AAh, 0A0h, 000h, 070h, 000h
        DB 0BBh, 0BBh, 000h, 000h, 070h, 000h, 000h, 000h, 091h, 001h
        DB 090h, 000h, 000h, 000h, 070h, 000h, 0BBh, 0BBh, 000h, 000h
        DB 07Ah, 00Ah, 0AAh, 0AAh, 0AAh, 00Ah, 0AAh, 0AAh, 0AAh, 00Ah
        DB 070h, 000h, 0BBh, 0BBh, 000h, 000h, 079h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 009h, 070h, 000h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B4h, 044h, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 002h, 000h, 014h, 000h, 002h
        DB 003h, 000h, 000h, 000h, 004h, 01Bh, 000h, 000h, 000h, 004h
        DB 018h, 0B1h, 0B0h, 0BBh, 000h, 011h, 030h, 011h, 010h, 000h
        DB 00Bh, 000h, 000h, 000h, 000h, 0B0h, 07Bh, 0B7h, 0B0h, 0BBh
        DB 006h, 066h, 066h, 066h, 067h, 006h, 07Bh, 07Bh, 00Bh, 000h
        DB 0B0h, 0B0h, 07Bh, 0B7h, 0B0h, 0BBh, 044h, 044h, 044h, 044h
        DB 04Bh, 0B0h, 07Bh, 07Bh, 00Bh, 044h, 0B0h, 0B0h, 07Bh, 0B7h
        DB 0B0h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B0h, 07Bh, 07Bh
        DB 00Bh, 055h, 0B0h, 0B0h, 07Bh, 0B7h, 010h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 00Bh, 07Bh, 07Bh, 00Bh, 055h, 0B0h, 0B0h
        DB 07Bh, 0B0h, 0B6h, 0B7h, 0BBh, 0B6h, 070h, 07Bh, 0B0h, 00Bh
        DB 07Bh, 07Bh, 00Bh, 055h, 0B0h, 0B0h, 07Bh, 0B0h, 0B0h, 007h
        DB 0BBh, 0B0h, 070h, 07Bh, 0BBh, 07Bh, 07Bh, 07Bh, 00Bh, 055h
        DB 0B0h, 0B0h, 07Bh, 0B0h, 0B0h, 007h, 0BBh, 000h, 070h, 07Bh
        DB 0BBh, 07Bh, 07Bh, 07Bh, 00Bh, 055h, 0B0h, 0B0h, 07Bh, 0B0h
        DB 010h, 007h, 0BBh, 0B7h, 0BBh, 0BBh, 0BBh, 07Bh, 07Bh, 077h
        DB 00Bh, 0BBh, 0B0h, 0B0h, 07Bh, 0B0h, 0B6h, 0B7h, 001h, 017h
        DB 0B0h, 000h, 00Bh, 07Bh, 070h, 007h, 000h, 000h, 000h, 0B0h
        DB 07Bh, 0B0h, 0B0h, 0B7h, 0BBh, 0B7h, 0B0h, 000h, 000h, 070h
        DB 07Bh, 0B7h, 0B7h, 000h, 000h, 0B0h, 07Bh, 0B0h, 0B0h, 0B7h
        DB 0BBh, 0B7h, 0B7h, 0B4h, 04Bh, 0BBh, 07Bh, 0B7h, 0BBh, 0B7h
        DB 06Bh, 0B0h, 07Bh, 0B0h, 0B0h, 0B7h, 0BBh, 0B7h, 0B7h, 0BBh
        DB 0B0h, 000h, 07Bh, 007h, 000h, 0B7h, 000h, 0B0h, 07Bh, 0B0h
        DB 080h, 000h, 00Bh, 007h, 0B7h, 000h, 000h, 000h, 0BBh, 07Bh
        DB 0B1h, 0B7h, 000h, 0B0h, 07Bh, 0B0h, 0B7h, 0B7h, 0BBh, 07Bh
        DB 0B7h, 000h, 000h, 000h, 080h, 070h, 0B7h, 0B7h, 000h, 0B0h
        DB 07Bh, 000h, 0B7h, 017h, 0BBh, 070h, 007h, 06Bh, 0BBh, 0B6h
        DB 0BBh, 0B7h, 0B7h, 0B7h, 000h, 0B0h, 07Bh, 04Bh, 0B7h, 0B7h
        DB 0BBh, 07Bh, 0B7h, 00Bh, 020h, 0B4h, 0B0h, 0B7h, 0B7h, 0B7h
        DB 000h, 0B0h, 07Bh, 0B0h, 007h, 007h, 0BBh, 070h, 0BBh, 0BBh
        DB 0B7h, 07Bh, 001h, 0B7h, 0B7h, 0BBh, 0B0h, 0B0h, 07Bh, 0B7h
        DB 0BBh, 0B7h, 011h, 07Bh, 010h, 000h, 01Bh, 071h, 07Bh, 044h
        DB 0B7h, 0B0h, 000h, 0BBh, 07Bh, 0B7h, 00Bh, 0BBh, 0BBh, 0BBh
        DB 0B7h, 07Bh, 0BBh, 0BBh, 070h, 0BBh, 0B7h, 0B1h, 0B0h, 009h
        DB 07Bh, 0B7h, 000h, 000h, 011h, 000h, 007h, 000h, 011h, 000h
        DB 070h, 001h, 007h, 007h, 0B0h, 07Bh, 04Bh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 04Bh, 0BBh, 00Eh, 000h, 012h, 000h, 002h, 007h, 000h
        DB 000h, 000h, 004h, 014h, 000h, 000h, 000h, 004h, 018h, 0BBh
        DB 0AAh, 0AAh, 0A0h, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 00Ah
        DB 0AAh, 0AAh, 0AAh, 0A7h, 0BBh, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 00Ah, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 00Ah, 000h, 007h, 060h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 010h, 010h, 007h, 001h, 003h, 00Ah, 000h, 007h
        DB 00Ah, 0A7h, 0BBh, 0BBh, 000h, 000h, 006h, 0AAh, 0AAh, 0AAh
        DB 0A7h, 0AAh, 0AAh, 0AAh, 000h, 0B7h, 00Ah, 097h, 0BBh, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 000h, 007h, 00Bh, 0BBh, 0BBh, 0BBh, 006h, 066h, 070h, 000h
        DB 000h, 000h, 007h, 000h, 000h, 000h, 000h, 000h, 070h, 000h
        DB 0BBh, 0BBh, 00Ah, 000h, 006h, 066h, 066h, 070h, 007h, 000h
        DB 076h, 066h, 066h, 000h, 066h, 067h, 0BBh, 0BBh, 01Ah, 000h
        DB 000h, 000h, 000h, 070h, 007h, 000h, 070h, 000h, 000h, 010h
        DB 001h, 0B7h, 0BBh, 0BBh, 01Ah, 000h, 000h, 076h, 066h, 060h
        DB 007h, 000h, 066h, 066h, 070h, 0B0h, 00Ah, 0A7h, 0BBh, 0BBh
        DB 01Ah, 000h, 000h, 070h, 000h, 001h, 007h, 001h, 000h, 000h
        DB 070h, 0B4h, 04Bh, 0B7h, 0BBh, 0BBh, 01Ah, 000h, 000h, 070h
        DB 000h, 001h, 007h, 001h, 000h, 000h, 070h, 0BBh, 0BBh, 0B7h
        DB 0BBh, 0BBh, 00Ah, 000h, 000h, 070h, 000h, 000h, 070h, 070h
        DB 000h, 000h, 070h, 000h, 000h, 007h, 0BBh, 0BBh, 00Ah, 000h
        DB 000h, 006h, 066h, 066h, 007h, 006h, 066h, 066h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 01Ah, 007h, 066h, 066h, 060h, 000h
        DB 007h, 000h, 000h, 066h, 066h, 067h, 000h, 007h, 0BBh, 0BBh
        DB 01Ah, 007h, 000h, 000h, 000h, 000h, 007h, 000h, 000h, 000h
        DB 000h, 007h, 000h, 007h, 0BBh, 0BBh, 01Ah, 007h, 007h, 0BBh
        DB 0BBh, 0BBh, 0B7h, 0BBh, 0BBh, 0BBh, 0B7h, 007h, 000h, 007h
        DB 0BBh, 0BBh, 02Ah, 007h, 007h, 000h, 000h, 000h, 007h, 000h
        DB 000h, 000h, 007h, 007h, 000h, 0A7h, 0BBh, 0BBh, 0BAh, 007h
        DB 00Bh, 001h, 001h, 001h, 007h, 001h, 001h, 001h, 00Bh, 007h
        DB 000h, 070h, 0BBh, 0BBh, 0BBh, 066h, 06Bh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 066h, 06Bh, 0BBh, 0BBh, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0BBh, 0BBh, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 0BBh, 003h, 000h, 014h, 000h, 001h, 004h, 000h, 002h, 000h
        DB 004h, 006h, 000h, 002h, 000h, 004h, 018h, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 070h, 090h, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 006h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h, 066h
        DB 066h, 066h, 066h, 067h, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 000h, 001h, 000h, 001h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 000h, 007h, 000h
        DB 007h, 000h, 006h, 067h, 000h, 007h, 0BBh, 0BBh, 076h, 066h
        DB 066h, 067h, 000h, 000h, 070h, 071h, 070h, 070h, 000h, 007h
        DB 000h, 007h, 0BBh, 0BBh, 001h, 013h, 010h, 007h, 000h, 007h
        DB 000h, 007h, 000h, 007h, 000h, 007h, 000h, 007h, 0BBh, 0BBh
        DB 066h, 066h, 060h, 007h, 066h, 066h, 000h, 000h, 000h, 006h
        DB 066h, 066h, 000h, 007h, 0BBh, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 007h, 066h, 066h, 060h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 007h, 066h, 060h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 007h, 000h, 000h, 00Bh, 066h, 06Bh, 0BBh, 0BBh
        DB 000h, 066h, 066h, 066h, 066h, 067h, 000h, 000h, 007h, 000h
        DB 000h, 000h, 000h, 000h, 0BBh, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 007h, 006h, 067h, 007h, 066h, 066h, 060h, 000h, 000h
        DB 0BBh, 0BBh, 000h, 00Bh, 000h, 000h, 000h, 007h, 0A0h, 007h
        DB 007h, 000h, 000h, 000h, 011h, 000h, 0BBh, 0BBh, 07Bh, 07Bh
        DB 07Bh, 07Bh, 00Bh, 000h, 001h, 007h, 007h, 000h, 000h, 007h
        DB 0AAh, 070h, 0BBh, 0BBh, 07Bh, 07Bh, 07Bh, 07Bh, 04Bh, 000h
        DB 00Bh, 007h, 007h, 000h, 000h, 007h, 011h, 070h, 0BBh, 0BBh
        DB 07Bh, 07Bh, 07Bh, 07Bh, 05Bh, 044h, 04Bh, 007h, 007h, 0B1h
        DB 011h, 007h, 0AAh, 070h, 0BBh, 0BBh, 07Bh, 07Bh, 07Bh, 072h
        DB 0BBh, 055h, 05Bh, 007h, 007h, 0AAh, 0AAh, 007h, 011h, 070h
        DB 0BBh, 0BBh, 07Bh, 070h, 07Bh, 0BBh, 0BBh, 0BBh, 0BBh, 007h
        DB 007h, 0AAh, 0AAh, 007h, 0AAh, 070h, 0BBh, 0BBh, 070h, 0AAh
        DB 0AAh, 000h, 000h, 000h, 000h, 007h, 007h, 000h, 000h, 007h
        DB 000h, 070h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 003h
        DB 000h, 011h, 000h, 001h, 017h, 000h, 000h, 000h, 004h, 01Bh
        DB 000h, 000h, 000h, 004h, 018h, 0BBh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 001h, 000h, 001h, 000h, 001h, 000h, 001h
        DB 000h, 001h, 000h, 002h, 000h, 007h, 0BBh, 0BBh, 000h, 076h
        DB 070h, 076h, 070h, 076h, 070h, 076h, 070h, 076h, 070h, 076h
        DB 070h, 007h, 0BBh, 0BBh, 000h, 070h, 070h, 070h, 070h, 070h
        DB 070h, 070h, 070h, 070h, 070h, 070h, 070h, 007h, 0BBh, 0BBh
        DB 000h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h, 070h
        DB 070h, 070h, 070h, 007h, 0BBh, 0BBh, 000h, 070h, 071h, 070h
        DB 071h, 070h, 071h, 070h, 071h, 070h, 071h, 070h, 070h, 007h
        DB 0BBh, 0BBh, 000h, 070h, 006h, 000h, 006h, 000h, 006h, 000h
        DB 006h, 000h, 006h, 000h, 000h, 007h, 0BBh, 0BBh, 000h, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h, 000h, 000h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 0B0h, 0BBh, 0B7h, 0BBh, 007h, 0BBh, 0BBh
        DB 000h, 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h, 010h
        DB 010h, 007h, 000h, 007h, 0BBh, 0BBh, 006h, 066h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 066h, 066h, 066h, 067h, 000h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 00Ah, 000h, 000h, 000h, 000h, 000h
        DB 00Ah, 000h, 000h, 007h, 000h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 00Ah, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 0AAh, 07Ah, 000h, 007h
        DB 000h, 007h, 0BBh, 0BBh, 000h, 000h, 010h, 000h, 010h, 000h
        DB 010h, 000h, 010h, 000h, 010h, 007h, 000h, 007h, 0BBh, 0BBh
        DB 000h, 007h, 0B7h, 007h, 0B7h, 007h, 0B7h, 007h, 0B7h, 007h
        DB 0B7h, 007h, 000h, 007h, 0BBh, 0BBh, 000h, 007h, 007h, 007h
        DB 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 000h, 0B7h
        DB 0BBh, 0BBh, 000h, 007h, 007h, 007h, 007h, 007h, 007h, 007h
        DB 007h, 007h, 007h, 007h, 000h, 0B7h, 0BBh, 0BBh, 000h, 007h
        DB 007h, 017h, 007h, 017h, 007h, 017h, 007h, 017h, 007h, 017h
        DB 000h, 097h, 0BBh, 0BBh, 006h, 06Bh, 00Bh, 0BBh, 00Bh, 0BBh
        DB 00Bh, 0BBh, 00Bh, 0BBh, 00Bh, 0BBh, 00Bh, 0BBh, 0BBh, 0BBh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0BBh, 0BBh, 044h, 044h, 044h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h, 044h
        DB 0BBh, 0BBh, 055h, 055h, 055h, 055h, 055h, 055h, 055h, 055h
        DB 055h, 055h, 055h, 055h, 055h, 055h, 0BBh, 00Fh, 000h, 014h
        DB 000h, 002h, 006h, 000h, 000h, 000h, 004h, 019h, 000h, 000h
        DB 000h, 004h, 018h, 0BBh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 000h, 000h, 007h, 000h, 000h, 000h, 000h, 070h, 000h, 076h
        DB 066h, 070h, 000h, 000h, 0BBh, 0BBh, 000h, 000h, 006h, 066h
        DB 070h, 000h, 000h, 070h, 000h, 000h, 000h, 070h, 000h, 000h
        DB 0BBh, 0BBh, 007h, 067h, 000h, 000h, 070h, 066h, 066h, 006h
        DB 066h, 060h, 066h, 066h, 066h, 070h, 0BBh, 0BBh, 007h, 000h
        DB 066h, 066h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 070h, 0BBh, 0BBh, 007h, 000h, 006h, 066h, 066h, 066h
        DB 066h, 076h, 066h, 066h, 066h, 060h, 000h, 070h, 0BBh, 0BBh
        DB 007h, 000h, 000h, 000h, 000h, 000h, 000h, 070h, 000h, 000h
        DB 000h, 000h, 000h, 070h, 0BBh, 0BBh, 006h, 066h, 067h, 000h
        DB 000h, 000h, 000h, 070h, 000h, 000h, 000h, 076h, 066h, 060h
        DB 0BBh, 0BBh, 000h, 000h, 007h, 000h, 000h, 000h, 000h, 070h
        DB 000h, 000h, 000h, 070h, 000h, 000h, 0BBh, 0BBh, 000h, 000h
        DB 007h, 000h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 070h
        DB 000h, 000h, 0BBh, 0BBh, 000h, 000h, 007h, 000h, 000h, 0BBh
        DB 0BBh, 07Bh, 0BBh, 000h, 000h, 070h, 000h, 000h, 0BBh, 0BBh
        DB 000h, 000h, 007h, 000h, 000h, 0BBh, 0BBh, 07Bh, 0AAh, 000h
        DB 000h, 070h, 000h, 000h, 0BBh, 0BBh, 0BBh, 0B0h, 007h, 000h
        DB 000h, 00Ah, 0ABh, 07Bh, 07Bh, 066h, 066h, 070h, 00Bh, 0BBh
        DB 0BBh, 0BBh, 030h, 000h, 007h, 066h, 066h, 0BBh, 07Ah, 07Ah
        DB 07Bh, 044h, 04Bh, 070h, 000h, 001h, 0BBh, 0BBh, 0BBh, 0BBh
        DB 00Bh, 044h, 044h, 0BBh, 0BBh, 07Bh, 0BBh, 0BBh, 0BBh, 0B0h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 000h, 000h, 00Bh, 0BBh, 0BBh, 0B0h
        DB 00Bh, 079h, 000h, 000h, 000h, 000h, 000h, 000h, 0BBh, 0BBh
        DB 002h, 000h, 000h, 000h, 000h, 000h, 00Bh, 0BBh, 0BBh, 07Bh
        DB 0BBh, 0B6h, 0BBh, 070h, 0BBh, 0BBh, 07Bh, 0BBh, 06Bh, 0BBh
        DB 0BBh, 0B0h, 000h, 000h, 000h, 070h, 000h, 000h, 000h, 070h
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0B0h, 000h, 000h
        DB 000h, 070h, 000h, 000h, 000h, 000h, 0BBh, 0BBh, 07Ah, 0AAh
        DB 0AAh, 0AAh, 07Ah, 0AAh, 0AAh, 07Ah, 0AAh, 0AAh, 0AAh, 0AAh
        DB 0AAh, 0A7h, 0BBh, 0BBh, 001h, 000h, 000h, 000h, 070h, 000h
        DB 000h, 070h, 010h, 010h, 0AAh, 0A0h, 000h, 007h, 0BBh, 0BBh
        DB 0BBh, 0BBh
lab272: DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 010h, 000h, 014h, 000h, 002h, 001h, 000h
        DB 000h, 000h, 004h, 019h, 000h, 000h, 000h, 004h, 018h, 0B0h
        DB 000h, 000h, 000h, 070h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 00Bh, 0B0h, 000h, 000h, 000h, 070h
        DB 000h, 007h, 066h, 066h, 066h, 070h, 000h, 000h, 007h, 066h
        DB 07Bh, 0B0h, 000h, 000h, 000h, 007h, 009h, 000h, 000h, 000h
        DB 000h, 006h, 066h, 066h, 060h, 000h, 07Bh, 0B0h, 0B4h, 044h
        DB 044h, 04Bh, 066h, 066h, 004h, 044h, 044h, 044h, 044h, 0B0h
        DB 00Bh, 04Bh, 07Bh, 0B0h, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 00Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0B0h, 00Bh, 0BBh, 07Bh, 0B0h
        DB 000h, 001h, 000h, 000h, 020h, 001h, 001h, 000h, 010h, 010h
        DB 003h, 001h, 001h, 001h, 07Bh, 0B0h, 001h, 017h, 011h, 000h
        DB 070h, 007h, 007h, 000h, 070h, 070h, 007h, 007h, 007h, 007h
        DB 00Bh, 0B0h, 076h, 067h, 066h, 070h, 070h, 007h, 007h, 000h
        DB 070h, 070h, 007h, 007h, 007h, 007h, 00Bh, 0B0h, 070h, 007h
        DB 000h, 070h, 070h, 007h, 007h, 011h, 070h, 070h, 007h, 007h
        DB 007h, 007h, 00Bh, 0B0h, 070h, 007h, 000h, 070h, 070h, 017h
        DB 007h, 066h, 070h, 070h, 017h, 007h, 007h, 007h, 00Bh, 0B0h
        DB 071h, 017h, 011h, 070h, 071h, 077h, 007h, 000h, 070h, 071h
        DB 077h, 007h, 007h, 007h, 00Bh, 0B0h, 006h, 067h, 066h, 006h
        DB 077h, 007h, 007h, 000h, 070h, 077h, 007h, 007h, 007h, 007h
        DB 00Bh, 0B0h, 000h, 007h, 000h, 000h, 070h, 007h, 007h, 000h
        DB 070h, 070h, 007h, 007h, 017h, 017h, 00Bh, 0B0h, 0A0h, 007h
        DB 00Bh, 000h, 006h, 0A0h, 0A0h, 0AAh, 00Ah, 00Ah, 0A0h, 0A0h
        DB 060h, 060h, 00Bh, 0B7h, 0BBh, 0BBh, 0BBh, 0BBh, 0B4h, 044h
        DB 044h, 044h, 044h, 044h, 044h, 044h, 044h, 04Bh, 0BBh, 0B7h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 00Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 07Bh, 0B0h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 07Bh, 0B7h, 0AAh, 0ABh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0B7h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 00Bh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
        DB 0BBh, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 030h, 09Ch, 014h, 09Dh, 014h, 09Dh, 0C6h, 012h, 0F6h, 00Fh
        DB 0D6h, 003h, 003h, 000h, 03Eh, 007h, 01Fh, 001h, 000h, 003h
        DB 00Ah, 001h
lab250: DB 011h
lab0:   CALL lab257
        LXI D,lab243
lab259: MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 091h
        JNZ lab259
        LXI D,lab260
lab261: MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 0C0h
        JNZ lab261
        LXI D,lab243
        CALL lab262
        JMP lab263
lab262: MVI A,0FFh
        STAX D
        INX D
        STAX D
        INR D
        DCR E
        MOV A,D
        CPI 0C0h
        JNZ lab262
        RET
lab263: LXI D,lab266
        CALL lab262
        LXI D,lab267
        CALL lab262
        LXI D,lab268
        CALL lab262
        LXI D,lab269
        CALL lab270
        JMP lab271
lab270: MVI A,0FFh
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
        JNZ lab270
        RET
lab271: LXI D,lab274
        CALL lab270
        LXI D,lab275
        CALL lab270
        LXI D,lab276
        CALL lab270
        LXI D,lab277
        CALL lab270
        LXI D,lab278
        CALL lab270
        LXI B,lab279
        LXI D,lab280
        MVI H,000h
lab283: LDAX B
        SUI 001h
        JZ lab281
        ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lab283
        INR D
        INR D
        MVI H,000h
        JMP lab283
lab281: LXI B,lab284
        LXI D,lab285
        MVI H,000h
lab288: LDAX B
        SUI 001h
        JZ lab286
        CALL lab287
        JNZ lab288
        INR D
        INR D
        MVI H,000h
        MVI E,020h
        JMP lab288
lab286: LXI B,lab289
        LXI D,lab290
        MVI H,000h
lab293: LDAX B
        SUI 001h
        JZ lab291
        CALL lab287
        JMP lab292
lab287: ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        RET
lab292: JNZ lab293
        INR D
        INR D
        MVI H,000h
        MVI E,0A0h
        JMP lab293
lab291: LXI B,lab294
        LXI D,lab295
        MVI L,000h
lab304: PUSH B
        PUSH D
        MVI H,000h
lab297: LDAX B
        SUI 001h
        JZ lab296
        CALL lab287
        JNZ lab297
lab296: CALL lab298
        POP D
        LXI B,lab299
        MVI H,000h
lab301: LDAX B
        SUI 001h
        JZ lab300
        CALL lab287
        JNZ lab301
lab300: POP B
        MOV A,C
        CPI 078h
        JNZ lab302
        MVI C,048h
        INR L
        MOV A,L
        CPI 00Ah
        JZ lab303
lab302: MOV A,C
        ADI 010h
        MOV C,A
        INR D
        DCR E
        DCR E
        DCR E
        DCR E
        JMP lab304
lab298: MVI A,001h
lab307: MVI D,0E0h
lab306: MVI E,080h
lab305: DCR E
        JNZ lab305
        DCR D
        JNZ lab306
        DCR A
        JNZ lab307
        RET
lab319: LXI B,lab308
        LXI D,lab309
        MVI H,000h
lab311: LDAX B
        SUI 001h
        JZ lab310
        CALL lab287
        JNZ lab311
        INR D
        MVI H,000h
        MVI E,030h
        JMP lab311
lab303: LXI B,lab312
        LXI D,lab313
        LXI H,00000h
lab321: PUSH B
        PUSH D
        MVI H,000h
lab315: LDAX B
        SUI 001h
        JZ lab314
        CALL lab287
        JNZ lab315
lab314: CALL lab298
        POP D
        LXI B,lab299
        MVI H,000h
lab317: LDAX B
        SUI 001h
        JZ lab316
        CALL lab287
        JNZ lab317
lab316: POP B
        MOV A,C
        CPI 0F0h
        JNZ lab318
        MVI C,0E0h
        INR L
        MOV A,L
        CPI 009h
        JZ lab319
        JMP lab320
lab318: MOV A,C
        ADI 010h
        MOV C,A
lab320: LXI D,lab313
        JMP lab321
lab310: LXI B,lab322
        LXI D,lab323
        MVI H,000h
lab325: LDAX B
        SUI 001h
        JZ lab324
        ADI 001h
        STAX D
        INX D
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lab325
        INR D
        INR D
        INR D
        INR D
        MVI H,000h
        MVI E,0C0h
        JMP lab325
lab324: MVI A,00Fh
        CALL lab307
        CALL lab257
        LXI SP,lab250
        JMP lab326
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h
lab279: DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 07Eh, 066h
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
        DB 066h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 0FFh, 000h, 0FFh, 0FFh, 000h
        DB 0FFh, 000h
lab284: DB 03Ch, 066h, 060h, 060h, 060h, 060h, 066h, 03Ch, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 0FEh, 0FEh, 030h, 030h
        DB 030h, 030h, 030h, 030h, 07Eh, 060h, 060h, 07Eh, 066h, 066h
        DB 066h, 07Ch, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 001h, 0FFh, 000h, 0FFh
        DB 0FFh, 000h, 0FFh, 000h
lab289: DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 0FEh, 060h
        DB 060h, 060h, 07Ch, 060h, 060h, 0FEh, 07Eh, 066h, 066h, 066h
        DB 07Eh, 060h, 060h, 060h, 066h, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 066h, 066h
        DB 066h, 066h, 066h, 066h, 07Fh, 07Fh, 063h, 063h, 063h, 073h
        DB 06Bh, 06Bh, 06Bh, 073h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
lab294: DB 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 007h, 007h, 00Eh, 03Eh
        DB 00Eh, 007h, 01Eh, 006h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah
        DB 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Eh, 00Eh
        DB 0FCh, 01Fh, 038h, 0F6h, 002h, 003h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lab299: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 0FFh, 000h, 000h, 0FFh, 000h, 0FFh
lab308: DB 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 07Eh, 066h, 066h, 066h
        DB 066h, 066h, 0FFh, 0FFh, 000h, 000h, 000h, 000h, 000h, 060h
        DB 060h, 060h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 006h, 00Eh, 006h, 006h, 006h, 006h, 006h, 006h, 03Ch, 066h
        DB 066h, 066h, 066h, 066h, 066h, 03Ch, 03Ch, 066h, 066h, 066h
        DB 066h, 066h, 066h, 03Ch, 001h, 000h, 0FFh, 000h, 000h, 0FFh
        DB 000h, 0FFh
lab312: DB 04Eh, 04Eh, 024h, 01Eh, 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h
        DB 0FFh, 000h, 000h, 0FFh, 000h, 0FFh, 02Eh, 02Eh, 024h, 01Eh
        DB 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h, 0FFh, 000h, 000h, 0FFh
        DB 000h, 0FFh
lab322: DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 03Eh, 066h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 03Eh, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Eh, 066h, 066h, 066h, 066h, 066h, 0FFh, 0FFh
        DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 001h
END