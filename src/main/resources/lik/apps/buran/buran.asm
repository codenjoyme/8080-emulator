        CPU  8080
        .ORG 00000h
lab11   EQU 08000h
lab57   EQU 09000h
lab116  EQU 08400h
lab117  EQU 0D800h
lab129  EQU 0C000h
lab142  EQU 08001h
lab16   EQU 08002h
lab108  EQU 0F802h
lab15   EQU 08003h
lab91   EQU 08005h
lab50   EQU 08007h
lab69   EQU 08008h
lab219  EQU 0BE09h
lab1    EQU 0C010h
lab78   EQU 08010h
lab53   EQU 0A818h
lab63   EQU 08018h
lab74   EQU 08020h
lab199  EQU 0AA20h
lab174  EQU 0A421h
lab233  EQU 0AA21h
lab187  EQU 0B222h
lab200  EQU 0AA22h
lab220  EQU 0BC22h
lab209  EQU 09A28h
lab35   EQU 09030h
lab77   EQU 08030h
lab179  EQU 09A35h
lab44   EQU 0C037h
lab6    EQU 0C438h
lab39   EQU 01840h
lab79   EQU 08040h
lab202  EQU 0A840h
lab111  EQU 08041h
lab189  EQU 0B444h
lab173  EQU 0984Ah
lab186  EQU 0B250h
lab0    EQU 08051h
lab22   EQU 08052h
lab208  EQU 0B252h
lab135  EQU 03060h
lab204  EQU 0A260h
lab221  EQU 0B863h
lab229  EQU 09C63h
lab36   EQU 03070h
lab180  EQU 09270h
lab207  EQU 0A671h
lab206  EQU 0A479h
lab105  EQU 02080h
lab181  EQU 09481h
lab196  EQU 0A290h
lab226  EQU 09890h
lab232  EQU 0A291h
lab197  EQU 0A292h
lab216  EQU 0AE97h
lab136  EQU 0909Fh
lab38   EQU 0A6A0h
lab183  EQU 098A8h
lab234  EQU 098A9h
lab184  EQU 098AAh
lab211  EQU 0AAAAh
lab213  EQU 0A0D1h
lab20   EQU 044DBh
lab214  EQU 0A2E0h
lab24   EQU 044E6h
lab194  EQU 0A6EBh
lab192  EQU 0AEEEh
lab193  EQU 0A4FEh
lab240  EQU 0FF00h
lab243  EQU 0FF02h
lab235  EQU 05703h
lab236  EQU 07703h
lab238  EQU 0A703h
lab239  EQU 0FF03h
lab224  EQU 09F05h
lab190  EQU 0B509h
lab225  EQU 09B12h
lab188  EQU 0B320h
lab182  EQU 0AB25h
lab210  EQU 09925h
lab175  EQU 0A727h
lab212  EQU 09727h
lab223  EQU 09929h
lab2    EQU 02530h
lab178  EQU 09B30h
lab217  EQU 0BB33h
lab28   EQU 0C337h
lab185  EQU 0AF40h
lab218  EQU 0BB42h
lab8    EQU 09D48h
lab170  EQU 09D50h
lab231  EQU 09D51h
lab198  EQU 09D52h
lab205  EQU 0A356h
lab222  EQU 0B761h
lab228  EQU 09B61h
lab176  EQU 0AB66h
lab102  EQU 0C170h
lab177  EQU 0A970h
lab203  EQU 0A177h
lab227  EQU 09982h
lab230  EQU 09B84h
lab201  EQU 0A985h
lab191  EQU 0B590h
lab47   EQU 049B4h
lab171  EQU 091C0h
lab60   EQU 0B5CDh
lab215  EQU 0A3E3h
lab55   EQU 0A7E8h
lab132  EQU 0FFF0h
lab172  EQU 093F0h
lab195  EQU 099F0h
lab101  EQU 08FF1h
lab125  EQU 0FFF8h
lab107  EQU 08FFAh
lab3    EQU 08FFCh
lab106  EQU 0FFFFh
        LXI H,00000h
        SHLD lab0
lab112: CALL lab1
        LXI H,lab2
        SHLD lab3
        CALL lab4
        LXI H,lab5
        CALL lab6
        CALL lab7
        LXI H,lab8
        LXI B,lab9
        CALL lab10
        CALL lab4
        LXI H,lab11
        MVI A,04Fh
lab12:  MVI M,000h
        INX H
        DCR A
        JNZ lab12
        LXI H,lab13
        MVI A,020h
lab14:  MVI M,000h
        INX H
        DCR A
        JNZ lab14
        SHLD lab15
        MVI A,097h
        STA lab16
        LXI D,lab17
        LXI H,lab18
        SHLD lab3
        MOV B,H
        XCHG
lab19:  XCHG
        LHLD lab3
        MOV H,B
        MOV A,L
        ADI 00Ah
        MOV L,A
        SHLD lab3
        XCHG
        CALL lab6
        INX H
        MOV A,M
        ANA A
        JNZ lab19
        LHLD lab0
        MOV A,L
        MOV D,H
        LXI H,lab20
        SHLD lab3
        CMP D
        JC lab21
        MOV D,A
        STA lab22
lab21:  CALL lab23
        MOV A,D
        LXI H,lab24
        SHLD lab3
        CALL lab23
lab32:  LHLD lab25
        CALL lab26
        LXI B,0051Fh
        CALL lab27
        CALL lab4
        CALL lab28
        CALL lab29
        CPI 002h
        JZ lab30
        CPI 003h
        JZ lab31
        CPI 006h
        JNZ lab32
        MOV A,L
        CPI 0BEh
        RNC
        CPI 0B4h
        JNC lab33
        CPI 0AAh
        JNC lab34
        LXI H,lab35
        LXI D,lab36
        MVI C,000h
        CALL lab37
        LXI H,lab38
        LXI D,lab39
        CALL lab37
        LXI H,lab40
        SHLD lab3
        LXI H,lab41
        CALL lab6
        JMP lab32
lab27:  MOV M,C
        DCX H
        DCR B
        JNZ lab27
        RET
lab30:  LDA lab25
        CPI 0AAh
        JC lab32
        MVI A,0F6h
        JMP lab42
lab31:  LDA lab25
        CPI 0BEh
        JNC lab32
        MVI A,00Ah
lab42:  LHLD lab25
        ADD L
        STA lab25
        LXI B,00500h
        CALL lab27
        JMP lab32
lab37:  MOV B,E
lab43:  MOV M,C
        INX H
        DCR B
        JNZ lab43
        MOV A,L
        SUB E
        MOV L,A
        INR H
        DCR D
        JNZ lab37
        RET
lab48:  PUSH B
        MVI C,07Fh
        CALL lab44
        MVI C,008h
        CALL lab44
        POP B
        JMP lab44
lab34:  LDA lab45
        INR A
        CPI 034h
        JC lab46
        MVI A,031h
lab46:  STA lab45
        MOV C,A
        LXI H,lab47
        SHLD lab3
        CALL lab48
        JMP lab32
lab33:  CALL lab1
        LDA lab45
        SUI 02Fh
        MOV C,A
        MVI A,0FFh
lab49:  ANA A
        RAR
        DCR C
        JNZ lab49
        STA lab50
        CALL lab51
        CALL lab52
        LXI H,lab53
        SHLD lab3
        LXI H,lab54
        CALL lab6
        LXI H,lab55
        LXI B,lab56
        CALL lab10
        LXI H,lab57
        LXI B,lab58
        CALL lab59
        CALL lab10
        LXI H,lab60
        LXI B,lab61
        CALL lab4
        CALL lab10
        CALL lab62
        XRA A
lab147: STA lab63
        LXI H,lab50
        MOV C,M
        ANA C
        JZ lab64
        ANI 001h
        JZ lab65
        JMP lab66
lab64:  CALL lab67
        MOV A,L
        ANI 0ABh
        JNZ lab68
        MVI B,007h
        LXI H,lab69
lab72:  CALL lab70
        JZ lab71
        DCR B
        JNZ lab72
        JMP lab65

lab25:  DB 0A8h, 092h
lab17:  DB 069h, 06Eh, 073h, 074h, 072h, 075h, 06Bh, 063h, 069h, 071h
        DB 000h, 075h, 072h, 06Fh, 077h, 065h, 06Eh, 078h, 020h, 073h
        DB 06Ch, 06Fh, 076h, 06Eh, 06Fh, 073h, 074h, 069h, 020h, 020h
lab45:  DB 032h, 000h, 069h, 067h, 072h, 061h, 000h, 077h, 079h, 068h
        DB 06Fh, 064h, 000h, 020h, 000h, 075h, 06Eh, 069h, 07Eh, 074h
        DB 06Fh, 076h, 065h, 06Eh, 06Fh, 020h, 063h, 065h, 06Ch, 065h
        DB 06Ah, 000h, 06Ch, 075h, 07Eh, 07Bh, 069h, 06Ah, 020h, 072h
        DB 065h, 07Ah, 075h, 06Ch, 078h, 074h, 061h, 074h, 000h, 000h
lab71:  INR A
        CALL lab73
        CALL lab67
        XCHG
        LXI H,lab74
        CALL lab75
lab76:  CALL lab67
        MOV A,H
        ANI 07Eh
        CPI 011h
        JC lab76
        MOV D,A
        MOV E,L
        LXI H,lab77
        CALL lab75
        MVI A,080h
        LXI H,lab78
        CALL lab73
        CALL lab51
        LXI H,lab79
        INR M
        CALL lab51
lab68:  CALL lab62
        MVI B,001h
lab113: LXI H,lab69
        CALL lab70
        JZ lab80
        LXI H,lab74
        CALL lab81
        PUSH D
        LXI H,lab77
        CALL lab81
        XCHG
        POP D
        CALL lab82
        DCR L
        DCR L
        MVI C,003h
lab84:  MVI E,004h
lab83:  MVI M,000h
        INR L
        DCR E
        JNZ lab83
        LXI D,000FCh
        DAD D
        DCR C
        JNZ lab84
        CALL lab67
        MOV A,H
        MVI E,004h
        CALL lab85
        ANA A
        RAR
        JC lab86
        CMA
lab86:  MOV C,A
lab89:  LXI H,lab78
        CALL lab70
        ADD C
        CALL lab73
        CALL lab87
        MOV A,D
        ANA A
        JNZ lab88
lab90:  MVI C,080h
        JMP lab89
lab88:  MOV A,H
        CPI 010h
        JC lab90
        CPI 080h
        JNC lab90
        CALL lab82
        DCR L
        DCR L
        SHLD lab91
        LXI H,lab63
        CALL lab70
        PUSH H
        PUSH PSW
        MVI E,005h
        CALL lab85
        LXI H,lab92
        LXI D,00008h
        CALL lab93
        POP PSW
        POP H
        INR A
        CALL lab73
        ANA A
        JNZ lab80
        PUSH B
        LXI H,lab74
        CALL lab81
        PUSH D
        LXI H,lab77
        CALL lab81
        INR D
        CALL lab75
        POP H
        XRA A
        STA lab11
        MOV B,A
        MOV C,A
        MVI A,088h
        SUB H
        JNC lab94
        STA lab11
        CMA
        INR A
lab94:  MOV H,A
        MVI A,0E0h
        SUB D
        CMA
        INR A
        MVI D,0FFh
        MOV E,A
lab95:  DAD D
        INX B
        MOV A,H
        ANA A
        JNZ lab95
        MOV A,B
        ANA A
        JZ lab96
        MVI C,0FFh
lab96:  MOV A,C
        ANA A
        RAR
        ANA A
        RAR
        ANA A
        RAR
        LXI H,lab11
        INR M
        DCR M
        JNZ lab97
        CMA
        INR A
lab97:  ADI 080h
        POP B
        PUSH PSW
lab100: POP PSW
        PUSH PSW
        CALL lab87
        CALL lab82
        INR H
        CALL lab26
        MVI A,080h
        JZ lab98
lab99:  RRC
        DCR C
        JNZ lab99
lab98:  MOV M,A
        MOV A,L
        ANA A
        JNZ lab100
        POP PSW
        LXI H,00101h
lab103: SHLD lab101
        CALL lab102
        INR H
        INR L
        INR L
        MOV A,L
        CPI 03Ch
        JC lab103
lab104: SHLD lab101
        CALL lab102
        DCR H
        DCR L
        DCR L
        JP lab104
        LXI H,lab105
        SHLD lab101
        CALL lab7
        LXI H,lab106
        SHLD lab107
        CALL lab1
        CALL lab4
        LXI H,lab108
        LXI D,00020h
        MVI B,032h
lab110: MOV M,E
        CALL lab109
        MVI M,000h
        MOV A,B
        CALL lab109
        INR B
        MOV A,B
        CPI 0C8h
        JNZ lab110
        XRA A
        MOV H,A
        MOV L,A
        SHLD lab107
        LDA lab111
        STA lab0
        JMP lab112
        DB 0AFh, 057h, 05Fh, 01Ah, 067h, 013h, 01Ah, 06Fh, 013h, 022h
        DB 003h, 080h, 0CDh, 032h, 007h, 0C0h, 07Ah, 0FEh, 009h, 0C2h
        DB 09Dh, 003h, 0C3h, 09Ah, 003h
lab80:  INR B
        MOV A,B
        CPI 008h
        JNZ lab113
        MVI B,000h
lab65:  CALL lab29
        PUSH PSW
        LDA lab69
        ANA A
        JNZ lab114
        POP PSW
        CPI 006h
        JNZ lab115
        LXI H,lab69
        INR M
        LXI H,lab116
        SHLD lab74
        LXI H,lab117
        SHLD lab77
        XRA A
        STA lab78
        JMP lab118
lab114: LDA lab63
        ANI 003h
        JZ lab119
        POP H
        PUSH PSW
lab119: POP PSW
        LXI H,lab78
        CPI 009h
        JZ lab120
        CPI 007h
        JNZ lab121
        DCR M
lab121: DCR M
lab120: INR M
        LDA lab78
lab118: CALL lab87
        PUSH H
        PUSH D
        MVI B,007h
lab139: LXI H,lab69
        CALL lab70
        JZ lab122
        POP H
        PUSH H
        MOV A,H
        LXI H,lab74
        CALL lab81
        SUB D
        JP lab123
        CMA
        INR A
lab123: CPI 003h
        JNC lab122
        POP D
        POP H
        PUSH H
        PUSH D
        MOV A,H
        LXI H,lab77
        CALL lab81
        SUB D
        JP lab124
        CMA
        INR A
lab124: CPI 003h
        JNC lab122
        POP D
        POP H
        CALL lab82
        XRA A
        LXI D,lab125
lab133: DAD D
        SHLD lab91
        PUSH PSW
        CALL lab26
        POP PSW
        PUSH PSW
        LXI D,00020h
        LXI H,lab126
        CALL lab93
        CALL lab4
        POP PSW
        PUSH PSW
        PUSH B
        ANA A
        JNZ lab127
        MVI B,014h
lab128: DCX B
        MOV A,B
        ANA A
        JNZ lab128
lab127: ADI 003h
        RLC
        RLC
        LXI H,lab129
        MVI E,000h
        MOV B,A
lab131: MOV C,B
        MOV A,M
        ANI 020h
lab130: STA lab108
        DCR C
        JNZ lab130
        INX H
        DCR E
        JNZ lab131
        POP B
        LHLD lab91
        LXI D,lab132
        POP PSW
        INR A
        CPI 007h
        JC lab133
        XRA A
        STA lab11
        LXI H,lab69
        MOV M,A
        CALL lab73
        LXI H,lab63
        CALL lab73
        CALL lab51
        CALL lab52
        LXI H,lab111
        MOV A,M
        INR A
        DAA
        MOV M,A
        DCX H
        DCR M
        CPI 080h
        JNC lab134
        CALL lab51
        CALL lab52
        JMP lab66
lab134: MVI C,033h
        CALL lab1
        CALL lab59
        LXI H,lab57
        LXI D,lab135
        PUSH D
        CALL lab37
        POP D
        LXI H,lab136
        CALL lab37
        CALL lab4
        LXI H,lab137
        SHLD lab3
        LXI H,lab138
        CALL lab6
        CALL lab28
        JMP lab112
lab122: DCR B
        JNZ lab139
        POP D
        POP H
        MOV A,H
        ANA A
        JZ lab140
        MOV A,D
        ANA A
        JZ lab140
        INR A
        JNZ lab141
lab140: STA lab69
        JMP lab66
lab141: PUSH H
        LHLD lab142
        LDA lab11
        MOV M,A
        POP H
        CALL lab82
        INR H
        MVI A,080h
        JZ lab143
lab144: RRC
        DCR C
        JNZ lab144
lab143: MOV C,M
        MOV M,A
        MOV A,C
        STA lab11
        SHLD lab142
lab115: ANA A
lab145: DCR A
        JNZ lab145
lab66:  LDA lab111
        RLC
        CMA
lab146: DCR A
        JNZ lab146
        LDA lab63
        INR A
        JMP lab147
lab87:  LXI H,lab74
        CALL lab81
        PUSH D
        LXI H,lab77
        CALL lab81
        MOV H,B
        POP B
        ANA A
        RAL
        JNC lab148
        DCR B
        ANA A
        RAL
        JNC lab149
        DCR D
        ANA A
        RAL
        JNC lab150
lab153: ADD C
        JNC lab151
        INR B
lab151: MOV C,A
        JMP lab152
lab149: ANA A
        RAL
        JC lab150
lab157: INR D
        CMA
        JMP lab153
lab148: ANA A
        RAL
        JC lab154
        DCR D
        ANA A
        RAL
        JNC lab153
lab156: INR B
        JMP lab155
lab154: ANA A
        RAL
        JNC lab156
        JMP lab157
lab150: CMA
lab155: ADD E
        JNC lab158
        INR D
lab158: MOV E,A
lab152: PUSH B
        MOV B,H
        LXI H,lab77
        CALL lab75
        POP H
        XCHG
        PUSH H
        LXI H,lab74
        CALL lab75
        POP H
        RET
lab85:  ANA A
        RAR
        DCR E
        JNZ lab85
        RET
lab93:  ANA A
        JZ lab159
lab160: DAD D
        DCR A
        JNZ lab160
lab159: MOV A,E
        ANA A
        RAR
        PUSH B
lab163: PUSH PSW
        MOV D,M
        INX H
        MOV E,M
        INX H
        MVI B,000h
        PUSH H
        MOV A,C
        ANA A
        JZ lab161
        MOV H,C
lab162: MOV A,D
        RAR
        MOV D,A
        MOV A,E
        RAR
        MOV E,A
        MOV A,B
        RAR
        MOV B,A
        DCR H
        JNZ lab162
lab161: LHLD lab91
        MOV M,D
        INR H
        MOV M,E
        INR H
        MOV M,B
        DCR H
        DCR H
        INR L
        SHLD lab91
        POP H
        POP PSW
        DCR A
        JNZ lab163
        POP B
        RET
lab82:  MOV L,H
        MOV A,D
        MOV C,D
        MVI E,003h
        CALL lab85
        MOV E,A
        ADI 097h
        MOV H,A
        MOV A,E
        RLC
        RLC
        RLC
        SUB C
        CMA
        INR A
        MOV C,A
        RET
lab70:  PUSH B
        PUSH H
lab164: INX H
        DCR B
        JNZ lab164
        MOV A,M
        POP H
        POP B
        ANA A
        RET
lab73:  PUSH B
        PUSH H
lab165: INX H
        DCR B
        JNZ lab165
        MOV M,A
        POP H
        POP B
        RET
lab81:  PUSH B
        PUSH H
        CALL lab166
        MOV E,M
        INX H
        MOV D,M
        POP H
        POP B
        RET
lab75:  PUSH B
        PUSH H
        CALL lab166
        MOV M,E
        INX H
        MOV M,D
        POP H
        POP B
        RET
lab166: INR B
        DCR B
        RZ
lab167: INX H
        INX H
        DCR B
        JNZ lab167
        RET
lab67:  LHLD lab15
        MVI C,010h
lab169: MOV A,H
        DAD H
        ANI 060h
        JPE lab168
        INX H
lab168: DCR C
        JNZ lab169
        SHLD lab15
        RET
lab62:  CALL lab59
        MVI A,004h
        STA lab170
        STA lab171
        STA lab172
        STA lab173
        STA lab174
        STA lab175
        STA lab176
        STA lab177
        STA lab178
        STA lab179
        STA lab180
        STA lab181
        STA lab182
        STA lab183
        STA lab184
        STA lab185
        STA lab186
        STA lab187
        STA lab188
        STA lab189
        STA lab190
        STA lab191
        STA lab192
        STA lab193
        STA lab194
        STA lab195
        STA lab196
        STA lab197
        STA lab198
        STA lab199
        STA lab200
        STA lab201
        STA lab202
        STA lab203
        STA lab204
        STA lab205
        STA lab206
        STA lab207
        MVI A,001h
        STA lab208
        STA lab209
        STA lab210
        STA lab211
        STA lab212
        STA lab213
        STA lab214
        STA lab215
        STA lab216
        STA lab217
        STA lab218
        STA lab219
        STA lab220
        STA lab221
        STA lab222
        STA lab223
        STA lab224
        STA lab225
        STA lab226
        STA lab227
        STA lab228
        STA lab229
        STA lab230
        MVI A,00Eh
        STA lab231
        STA lab232
        STA lab233
        STA lab234
        CALL lab4
        RET
lab109: DCR A
        XTHL
        XTHL
        JNZ lab109
        RET
lab10:  LDAX B
        INX B
        MOV D,A
lab237: LDAX B
        INX B
        MOV M,A
        INX H
        DCR D
        JNZ lab237
        LDAX B
        INX B
        ANA A
        RZ
        MOV E,A
        DAD D
        JMP lab10
lab29:  PUSH B
        MVI A,091h
        STA lab239
        MVI C,000h
        LDA lab240
        MVI B,008h
lab242: RAR
        JNC lab241
        INR C
        DCR B
        JNZ lab242
        LDA lab243
        MVI B,003h
lab244: RAR
        JNC lab241
        INR C
        DCR B
        JNZ lab244
lab241: MVI A,082h
        STA lab239
        MOV A,C
        CMA
        ADI 00Ch
        POP B
        RET
lab51:  LXI H,005D0h
        SHLD lab3
        LXI H,lab245
        CALL lab6
        LDA lab79
lab23:  PUSH B
        MOV B,A
        CALL lab246
        MOV A,B
        CALL lab247
        POP B
        RET
lab246: RRC
        RRC
        RRC
        RRC
lab247: ANI 00Fh
        CPI 00Ah
        JC lab248
        ADI 007h
lab248: ADI 030h
        MOV C,A
        JMP lab44
lab52:  LXI H,005E0h
        SHLD lab3
        LXI H,lab249
        CALL lab6
        LDA lab111
        JMP lab23
lab4:   XRA A
        JMP lab250
lab59:  MVI A,040h
        JMP lab250
lab7:   MVI A,080h
        JMP lab250
lab26:  MVI A,0C0h
lab250: STA lab243
        RET

lab54:  DB 02Ah, 020h, 041h, 044h, 044h, 020h, 02Ah, 000h, 020h, 02Ah
        DB 020h, 031h, 039h, 038h, 039h, 020h, 02Ah
lab41:  DB 077h, 079h, 020h, 02Dh, 020h, 070h, 069h, 06Ch, 06Fh, 074h
        DB 020h, 06Bh, 06Fh, 073h, 06Dh, 069h, 07Eh, 065h, 073h, 06Bh
        DB 06Fh, 067h, 06Fh, 020h, 06Bh, 06Fh, 072h, 061h, 062h, 06Ch
        DB 071h, 020h, 074h, 069h, 070h, 061h, 020h, 022h, 062h, 075h
        DB 072h, 061h, 06Eh, 022h, 00Ah, 020h, 020h, 020h, 06Eh, 061h
        DB 020h, 073h, 074h, 06Fh, 072h, 06Fh, 06Eh, 065h, 020h, 070h
        DB 072h, 06Fh, 074h, 069h, 077h, 06Eh, 069h, 06Bh, 061h, 020h
        DB 06Dh, 061h, 06Eh, 065h, 077h, 072h, 065h, 06Eh, 06Eh, 079h
        DB 065h, 020h, 073h, 070h, 075h, 074h, 06Eh, 069h, 06Bh, 069h
        DB 02Dh, 069h, 073h, 074h, 072h, 065h, 062h, 069h, 074h, 065h
        DB 06Ch, 069h, 00Ah, 020h, 020h, 020h, 073h, 020h, 06Ch, 061h
        DB 07Ah, 065h, 072h, 06Eh, 079h, 06Dh, 020h, 077h, 06Fh, 06Fh
        DB 072h, 075h, 076h, 065h, 06Eh, 069h, 065h, 06Dh, 020h, 06Dh
        DB 061h, 06Ch, 06Fh, 06Ah, 020h, 064h, 061h, 06Ch, 078h, 06Eh
        DB 06Fh, 073h, 074h, 069h, 00Ah, 020h, 020h, 020h, 077h, 061h
        DB 07Bh, 061h, 020h, 07Ah, 061h, 064h, 061h, 07Eh, 061h, 020h
        DB 02Dh, 020h, 075h, 06Eh, 069h, 07Eh, 074h, 06Fh, 076h, 069h
        DB 074h, 078h, 020h, 061h, 070h, 070h, 061h, 072h, 061h, 074h
        DB 079h, 020h, 070h, 072h, 069h, 07Bh, 065h, 06Ch, 078h, 063h
        DB 065h, 077h, 020h, 072h, 061h, 06Eh, 078h, 07Bh, 065h, 02Ch
        DB 00Ah, 020h, 020h, 020h, 07Eh, 065h, 06Dh, 020h, 06Fh, 06Eh
        DB 069h, 020h, 070h, 072h, 069h, 062h, 06Ch, 069h, 07Ah, 071h
        DB 074h, 073h, 071h, 020h, 06Eh, 061h, 020h, 072h, 061h, 073h
        DB 073h, 074h, 06Fh, 071h, 06Eh, 069h, 065h, 020h, 077h, 079h
        DB 073h, 074h, 072h, 065h, 06Ch, 061h, 00Ah, 020h, 020h, 020h
        DB 06Eh, 061h, 020h, 06Bh, 06Fh, 072h, 061h, 062h, 06Ch, 065h
        DB 020h, 069h, 06Dh, 065h, 065h, 074h, 073h, 071h, 020h, 06Eh
        DB 065h, 06Fh, 067h, 072h, 061h, 06Eh, 069h, 07Eh, 065h, 06Eh
        DB 06Eh, 079h, 06Ah, 020h, 07Ah, 061h, 070h, 061h, 073h, 020h
        DB 075h, 070h, 072h, 061h, 077h, 06Ch, 071h, 065h, 06Dh, 079h
        DB 068h, 020h, 072h, 061h, 06Bh, 065h, 074h, 00Ah, 020h, 020h
        DB 020h, 070h, 075h, 073h, 06Bh, 020h, 072h, 061h, 06Bh, 065h
        DB 074h, 079h, 020h, 02Dh, 020h, 070h, 072h, 06Fh, 062h, 065h
        DB 06Ch, 00Ah, 020h, 020h, 020h, 075h, 070h, 072h, 061h, 077h
        DB 06Ch, 065h, 06Eh, 069h, 065h, 020h, 02Dh, 020h, 06Bh, 06Ch
        DB 061h, 077h, 069h, 07Bh, 069h, 020h, 06Bh, 075h, 072h, 073h
        DB 06Fh, 072h, 061h, 000h
lab245: DB 077h, 069h, 076h, 075h, 020h, 063h, 065h, 06Ch, 065h, 06Ah
        DB 020h, 02Dh, 020h, 000h
lab249: DB 075h, 06Eh, 069h, 07Eh, 074h, 06Fh, 076h, 065h, 06Eh, 06Fh
        DB 020h, 02Dh, 020h, 000h
lab5:   DB 02Ah, 020h, 02Ah, 020h, 02Ah, 020h, 020h, 07Ah, 020h, 077h
        DB 020h, 065h, 020h, 07Ah, 020h, 064h, 020h, 06Eh, 020h, 079h
        DB 020h, 065h, 020h, 020h, 020h, 077h, 020h, 06Fh, 020h, 06Ah
        DB 020h, 06Eh, 020h, 079h, 020h, 02Ah, 020h, 02Ah, 020h, 02Ah
        DB 000h
lab138: DB 075h, 063h, 065h, 06Ch, 065h, 077h, 07Bh, 069h, 065h, 020h
        DB 069h, 073h, 074h, 072h, 065h, 062h, 069h, 074h, 065h, 06Ch
        DB 069h, 020h, 061h, 067h, 072h, 065h, 073h, 073h, 069h, 077h
        DB 06Eh, 079h, 068h, 020h, 067h, 075h, 06Dh, 061h, 06Eh, 06Fh
        DB 069h, 064h, 06Fh, 077h, 00Ah, 020h, 020h, 020h, 020h, 077h
        DB 020h, 070h, 061h, 06Eh, 069h, 06Bh, 065h, 020h, 070h, 06Fh
        DB 06Bh, 069h, 064h, 061h, 060h, 074h, 020h, 06Fh, 06Bh, 06Fh
        DB 06Ch, 06Fh, 07Ah, 065h, 06Dh, 06Eh, 06Fh, 065h, 020h, 070h
        DB 072h, 06Fh, 073h, 074h, 072h, 061h, 06Eh, 073h, 074h, 077h
        DB 06Fh, 02Eh, 00Ah, 020h, 020h, 020h, 020h, 072h, 065h, 07Bh
        DB 065h, 06Eh, 069h, 065h, 06Dh, 020h, 077h, 073h, 065h, 06Dh
        DB 069h, 072h, 06Eh, 06Fh, 067h, 06Fh, 020h, 073h, 06Fh, 077h
        DB 065h, 074h, 061h, 020h, 077h, 061h, 06Dh, 020h, 070h, 072h
        DB 069h, 073h, 077h, 061h, 069h, 077h, 061h, 065h, 074h, 073h
        DB 071h, 020h, 07Ah, 077h, 061h, 06Eh, 069h, 065h, 00Ah, 020h
        DB 020h, 020h, 020h, 022h, 073h, 06Bh, 072h, 06Fh, 06Dh, 06Eh
        DB 079h, 06Ah, 020h, 067h, 065h, 072h, 06Fh, 06Ah, 020h, 06Fh
        DB 062h, 06Fh, 07Ah, 072h, 069h, 06Dh, 06Fh, 06Ah, 020h, 077h
        DB 073h, 065h, 06Ch, 065h, 06Eh, 06Eh, 06Fh, 06Ah, 022h, 02Eh
        DB 020h, 070h, 06Fh, 07Ah, 064h, 072h, 061h, 077h, 06Ch, 071h
        DB 060h, 021h, 000h
lab9:   DB 00Dh, 00Fh, 030h, 040h, 080h, 081h, 080h, 042h, 020h, 010h
        DB 008h, 004h, 002h, 001h, 0F3h, 015h, 0FFh, 000h, 088h, 000h
        DB 010h, 000h, 020h, 000h, 000h, 000h, 000h, 000h, 000h, 080h
        DB 040h, 020h, 010h, 008h, 004h, 002h, 001h, 0EBh, 01Bh, 0FFh
        DB 000h, 007h, 00Ah, 01Ch, 000h, 038h, 028h, 028h, 038h, 028h
        DB 026h, 029h, 011h, 009h, 007h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 040h, 020h, 018h, 006h, 001h, 0E5h, 01Fh, 0FFh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 060h, 018h, 006h, 001h, 0E1h
        DB 023h, 0FFh, 000h, 000h, 000h, 000h, 000h, 030h, 07Ch, 018h
        DB 0C0h, 0F0h, 060h, 000h, 000h, 000h, 000h, 010h, 000h, 000h
        DB 000h, 000h, 040h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 080h, 060h, 018h, 006h, 001h, 0DDh, 02Bh, 080h, 078h
        DB 006h, 001h, 000h, 000h, 000h, 040h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 004h
        DB 000h, 000h, 000h, 000h, 010h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 0E0h, 058h, 026h, 011h, 008h, 004h, 002h
        DB 001h, 0D8h, 030h, 080h, 060h, 018h, 006h, 001h, 000h, 000h
        DB 000h, 000h, 010h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h, 000h, 000h
        DB 000h, 004h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 060h
        DB 018h, 006h, 001h, 080h, 040h, 020h, 010h, 008h, 004h, 002h
        DB 001h, 0D4h, 034h, 080h, 060h, 018h, 006h, 001h, 000h, 000h
        DB 000h, 000h, 000h, 004h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 000h, 000h, 000h, 000h, 000h, 080h, 060h
        DB 018h, 006h, 001h, 000h, 000h, 000h, 000h, 080h, 040h, 020h
        DB 010h, 008h, 004h, 002h, 001h, 0D0h, 03Ch, 080h, 060h, 018h
        DB 006h, 001h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 040h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 060h, 018h, 006h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 080h, 040h, 020h, 010h, 008h
        DB 008h, 004h, 004h, 002h, 002h, 001h, 001h, 0C8h, 048h, 080h
        DB 060h, 018h, 006h, 001h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 010h, 000h, 000h, 000h, 000h, 040h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 060h, 018h, 006h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 080h, 080h, 040h, 040h, 020h
        DB 020h, 010h, 010h, 008h, 008h, 004h, 004h, 002h, 002h, 001h
        DB 001h, 0BCh, 052h, 080h, 060h, 018h, 006h, 001h, 000h, 000h
        DB 000h, 040h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 004h, 000h, 000h, 000h, 000h
        DB 010h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 060h
        DB 018h, 006h, 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 080h, 080h, 040h, 040h, 020h, 020h, 010h, 010h, 008h
        DB 008h, 004h, 004h, 002h, 001h, 0B2h, 052h, 080h, 067h, 018h
        DB 006h, 001h, 000h, 000h, 000h, 000h, 010h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 001h, 000h, 000h, 000h, 000h, 004h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 060h, 018h, 006h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 061h, 019h, 006h, 001h, 0AEh
        DB 056h, 01Fh, 0E0h, 000h, 000h, 080h, 060h, 018h, 006h, 001h
        DB 000h, 000h, 000h, 000h, 000h, 004h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 060h, 018h, 006h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 001h, 002h
        DB 004h, 004h, 009h, 011h, 012h, 024h, 048h, 048h, 090h, 020h
        DB 040h, 040h, 080h, 060h, 018h, 006h, 001h, 0A8h, 057h, 001h
        DB 07Eh, 080h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h
        DB 060h, 018h, 006h, 001h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 040h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 060h, 018h, 006h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 001h, 001h, 002h, 004h, 004h
        DB 008h, 010h, 011h, 022h, 044h, 044h, 088h, 010h, 02Ch, 023h
        DB 040h, 080h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 002h
        DB 004h, 008h, 010h, 020h, 040h, 080h, 0A8h, 050h, 007h, 0F8h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 080h, 060h, 018h, 006h, 001h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 001h, 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 060h
        DB 018h, 026h, 041h, 042h, 084h, 008h, 010h, 010h, 020h, 040h
        DB 080h, 080h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0C1h, 032h, 00Ch, 008h, 010h, 020h, 040h, 080h, 0AFh, 049h
        DB 01Fh, 0E0h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 060h, 018h, 006h, 001h, 000h, 000h, 000h, 03Fh, 040h
        DB 040h, 080h, 080h, 000h, 000h, 0C0h, 030h, 00Ch, 003h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0FFh, 080h, 040h, 020h, 010h
        DB 008h, 004h, 002h, 001h, 000h, 001h, 002h, 004h, 008h, 010h
        DB 020h, 040h, 080h, 0B6h, 042h, 07Fh, 080h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 001h, 002h, 004h, 004h, 008h
        DB 090h, 060h, 019h, 007h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0C0h, 030h, 00Ch, 003h, 000h
        DB 001h, 001h, 002h, 002h, 004h, 004h, 008h, 008h, 010h, 010h
        DB 020h, 0E0h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 0BEh, 02Fh, 0E0h, 018h, 006h, 001h, 000h, 000h, 000h
        DB 001h, 001h, 002h, 004h, 004h, 008h, 011h, 012h, 022h, 044h
        DB 044h, 088h, 00Ch, 013h, 010h, 020h, 040h, 040h, 080h, 080h
        DB 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 001h, 000h, 000h, 0E0h, 01Eh, 001h
        DB 0D4h, 02Dh, 080h, 060h, 058h, 096h, 021h, 020h, 041h, 041h
        DB 082h, 082h, 002h, 004h, 004h, 008h, 008h, 008h, 010h, 010h
        DB 0E0h, 020h, 020h, 040h, 040h, 080h, 080h, 080h, 0FFh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 001h, 006h, 018h, 060h
        DB 080h, 060h, 018h, 006h, 001h, 0E0h, 01Fh, 0D7h, 028h, 080h
        DB 080h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0FFh, 000h, 000h, 000h, 001h, 006h, 018h, 060h, 098h
        DB 006h, 001h, 000h, 001h, 006h, 018h, 060h, 080h, 080h, 0EEh
        DB 00Dh, 0F8h, 006h, 001h, 060h, 098h, 006h, 001h, 000h, 001h
        DB 006h, 098h, 060h, 080h, 0F5h, 007h, 080h, 060h, 018h, 006h
        DB 098h, 060h, 080h, 000h
lab58:  DB 041h, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FAh
        DB 0F5h, 0FAh, 0F5h, 0EFh, 0FFh, 0EBh, 0F5h, 0EAh, 0F5h, 0EAh
        DB 0BFh, 0FFh, 0FFh, 0FFh, 0FFh, 0EBh, 0BEh, 0FFh, 07Fh, 0FFh
        DB 07Fh, 0FFh, 0FFh, 0FFh, 0EBh, 0FDh, 0FEh, 0FDh, 0FEh, 0FDh
        DB 0EAh, 0F5h, 0EFh, 0FDh, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h
        DB 0FFh, 055h, 0BFh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 02Ah, 015h, 002h, 001h, 0BFh, 043h, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 05Fh, 0BFh, 06Fh, 0BFh, 05Fh
        DB 0FFh, 0DFh, 0FFh, 0DFh, 0EFh, 0FFh, 0FFh, 07Fh, 0F7h, 0FFh
        DB 0F7h, 0FBh, 0FDh, 0FEh, 0F5h, 0FAh, 0F5h, 0FAh, 0F5h, 0FAh
        DB 0F5h, 0EAh, 0D5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0FFh
        DB 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh
        DB 055h, 0AFh, 055h, 0ABh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 02Ah, 005h, 0BDh, 044h, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0F3h, 0EFh, 0DFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0EFh, 0FFh, 0EEh, 0DDh, 0FEh, 0FDh, 0FAh, 0F5h
        DB 0FAh, 0F5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0EAh, 055h, 0EAh, 055h, 0FAh, 055h
        DB 0FAh, 055h, 0FAh, 055h, 0FEh, 055h, 0BFh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 01Fh, 0BCh, 043h, 0FFh, 0FFh, 0FFh
        DB 0F5h, 0FFh, 0F5h, 0FFh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FDh, 0EAh, 0D5h, 0BFh, 07Fh, 0EAh, 0FFh, 0AAh, 0FFh
        DB 0AAh, 07Fh, 0AAh, 07Fh, 0AAh, 05Fh, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0FBh, 055h
        DB 0AAh, 055h, 0ABh, 058h, 0BDh, 042h, 0C0h, 0F0h, 0FCh, 07Eh
        DB 0FFh, 057h, 0FFh, 055h, 0FFh, 0FDh, 0FFh, 0FFh, 05Fh, 0B9h
        DB 075h, 0EAh, 07Fh, 0AAh, 0FFh, 0AAh, 0FFh, 0AAh, 0FFh, 0AAh
        DB 0FFh, 0AAh, 0F7h, 0AAh, 0D5h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 057h, 0AFh, 05Fh, 0BFh, 0FFh, 0FFh, 0FEh, 0F8h
        DB 0E0h, 080h, 0C2h, 039h, 080h, 0C0h, 0F0h, 0F8h, 0FCh, 0FEh
        DB 0FEh, 0FFh, 0FFh, 0EBh, 0FDh, 0AFh, 0FDh, 0AAh, 0FDh, 0AAh
        DB 0FDh, 0AAh, 0FDh, 0AAh, 0FFh, 0AAh, 0FFh, 0AAh, 0FFh, 0AAh
        DB 07Fh, 0EAh, 07Fh, 0AAh, 07Fh, 0AAh, 05Fh, 0AAh, 057h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
lab40:  DB 0AAh, 055h, 0AAh, 05Fh, 0BFh, 07Fh, 0FEh, 0FCh, 0F8h, 0F0h
        DB 0E0h, 0C0h, 0CFh, 02Ah, 080h, 0C0h, 0C0h, 0E0h, 060h, 0F0h
        DB 070h, 0B8h, 058h, 0ACh, 054h, 0ACh, 056h, 0AAh, 0D6h, 0AAh
        DB 0DEh, 0AEh, 0DDh, 0ABh, 0D5h, 0ABh, 0F5h, 0AAh, 0D6h, 0AAh
        DB 0D6h, 0AAh, 056h
lab137: DB 0ACh, 054h, 0ACh, 050h, 0A8h, 058h, 0B0h, 050h, 0A0h, 060h
        DB 0C0h, 0C0h, 080h, 000h
lab61:  DB 02Dh, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 002h, 003h, 002h, 003h, 002h, 003h, 002h, 003h, 002h
        DB 003h
lab18:  DB 002h, 003h, 002h, 003h, 002h, 003h, 002h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 001h, 0B5h, 051h, 001h, 001h, 002h
        DB 003h, 002h, 005h, 006h, 005h, 00Ah, 00Dh, 00Ah, 00Dh, 01Bh
        DB 015h, 01Bh, 015h, 02Bh, 035h, 02Bh, 035h, 06Fh, 057h, 07Fh
        DB 07Fh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FDh, 0FDh, 0FFh, 0FFh, 0FFh, 0DFh, 0DFh, 0DFh, 0FDh
        DB 0FAh, 0FAh, 0FAh, 0FAh, 0FDh, 0FFh, 0FFh, 0FFh, 07Fh, 05Bh
        DB 057h, 0ABh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0ABh, 057h, 0AFh, 05Fh, 0AFh
        DB 05Fh, 0AFh, 0DFh, 0BBh, 0FBh, 0FBh, 0FFh, 0FFh, 09Dh, 063h
        DB 001h, 001h, 003h, 003h, 007h, 005h, 00Eh, 00Dh, 01Ah, 015h
        DB 02Ah, 035h, 06Ah, 055h, 06Ah, 0D5h, 0AAh, 0D5h, 0AAh, 055h
        DB 0AAh, 055h, 0ABh, 055h, 0ABh, 055h, 0BFh, 055h, 0BFh, 055h
        DB 0FFh, 055h, 0FFh, 055h, 0FFh, 0F5h, 0FFh, 0F5h, 0FFh, 0FDh
        DB 0FFh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FEh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0DFh, 0DFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 07Fh
        DB 0FDh, 07Ah, 0FAh, 0FAh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0EFh, 0EFh, 0FFh, 0BFh, 0BFh, 0FFh, 0FFh, 0FFh, 0FFh, 090h
        DB 070h, 001h, 003h, 003h, 007h, 00Fh, 00Fh, 01Fh, 03Fh, 03Fh
        DB 07Fh, 07Bh, 0F7h, 0F7h, 0EFh, 0FFh, 0FFh, 0FFh, 0FFh, 055h
        DB 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0BFh, 055h, 0BFh, 055h
        DB 0BFh, 055h, 0FFh, 055h, 0BFh, 055h, 0FFh, 055h, 0FFh, 055h
        DB 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h
        DB 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 055h, 0FFh, 0D5h
        DB 0FFh, 0D5h, 0FFh, 0F7h, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FDh, 0FFh
        DB 0FFh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 085h, 07Bh, 001h, 003h, 007h, 007h, 00Fh
        DB 01Fh, 03Fh, 03Fh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 07Fh, 0FFh
        DB 07Fh, 0FFh, 07Fh, 0FFh, 05Fh, 0FFh, 057h, 0FFh, 057h, 0FFh
        DB 057h, 0FFh, 057h, 0FFh, 057h, 0FFh, 057h, 0FFh, 057h, 0FFh
        DB 057h, 0FFh, 057h, 0FFh, 05Fh, 0FFh, 05Fh, 0FFh, 05Fh, 0FFh
        DB 07Fh, 0FFh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FEh, 0FEh, 0FEh, 0FEh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh
        DB 0FDh, 0FAh, 0FDh, 0FAh, 0F5h, 0EAh, 0D5h, 0AAh, 07Ch, 084h
        DB 001h, 003h, 007h, 00Fh, 01Fh, 03Fh, 07Fh, 07Fh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FBh, 0FBh, 0F7h, 0F7h, 0F9h
        DB 0FFh, 0FEh, 0FDh, 0FAh, 0F9h, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0F3h, 0CBh, 0DBh, 0BBh
        DB 0BBh, 077h, 077h, 06Fh, 04Fh, 03Fh, 0FFh, 0FFh, 0BFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0BFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0BFh, 05Fh, 05Fh, 0BFh
        DB 0FEh, 0FEh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 09Fh, 06Fh, 0F7h, 0F7h, 0F7h, 0F7h, 06Fh, 09Fh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FAh
        DB 0D5h, 0AEh, 057h, 0AFh, 05Fh, 0BFh, 05Fh, 0BFh, 05Fh, 0BFh
        DB 07Fh, 0FEh, 074h, 08Ch, 001h, 002h, 005h, 00Fh, 01Fh, 03Fh
        DB 07Fh, 0FFh, 0FFh, 0FEh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0EEh
        DB 0DFh, 0FEh, 0FFh, 0CEh, 037h, 0F7h, 0EFh, 0EFh, 0DFh, 0BFh
        DB 07Fh, 0FFh, 0FFh, 07Fh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FFh
        DB 0FEh, 0FFh, 0FFh, 0FDh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 07Fh, 0BFh, 0BFh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0DFh, 0DFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0EAh, 0D5h, 0AAh
        DB 075h, 0AAh, 075h, 0EAh, 0F5h, 0EAh, 0D5h, 0AAh, 0D5h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 06Dh, 093h, 001h, 003h, 006h, 01Dh
        DB 02Ah, 055h, 0AAh, 055h, 0AAh, 055h, 0EAh, 0D5h, 0EAh, 0D5h
        DB 0AAh, 055h, 0AAh, 055h, 0EAh, 0D5h, 0AAh, 055h, 0ABh, 057h
        DB 0AFh, 05Fh, 0FFh, 0E3h, 0DBh, 0BBh, 077h, 06Fh, 01Fh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0DFh, 0AFh, 0AFh, 0DFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FEh, 0FDh, 0FAh, 0FDh, 0FAh, 0F5h, 0FAh, 0F5h, 0FAh, 0FDh
        DB 0FEh, 0FDh, 0FEh, 0FFh, 0FEh, 0FDh, 0FEh, 0FDh, 0FEh, 0FDh
        DB 0FAh, 0F5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0ABh, 057h, 0AFh, 05Fh, 0BFh, 055h, 0AAh, 055h, 0AAh, 057h
        DB 0ABh, 05Fh, 0AFh, 067h, 099h, 001h, 003h, 00Eh, 015h, 02Ah
        DB 0D5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0ABh
        DB 057h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0ABh
        DB 057h, 0AFh, 05Fh, 0FEh, 0FEh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FAh
        DB 0F5h, 0EAh, 0D5h, 0EAh, 0D5h, 0EAh, 0F5h, 0EAh, 0D5h, 0EAh
        DB 0D5h, 0AAh, 0D5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 0D5h, 0AAh
        DB 0D5h, 0AAh, 0D5h, 0FAh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FBh
        DB 0FBh, 0BBh, 0FDh, 0FEh, 0FFh, 0FFh, 0EFh, 0FFh, 0FFh, 0FFh
        DB 0FDh, 0FAh, 0F5h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0BAh
        DB 07Fh, 0BFh, 07Fh, 0FFh, 0FFh, 0FFh, 0F7h, 0FFh, 0FFh, 0FEh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 062h, 09Eh
        DB 001h, 006h, 00Dh, 03Ah, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 05Dh, 0FFh, 0FFh, 0FAh
        DB 0F5h, 0EAh, 055h, 0BAh, 055h, 0AAh, 055h, 0AAh, 0FDh, 0FAh
        DB 0FDh, 09Fh, 05Fh, 0BFh, 07Fh, 0FFh, 0FFh, 0FFh, 0EFh, 0DFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0F7h, 0EBh, 0D5h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0ABh
        DB 055h, 0ABh, 05Fh, 0BFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0F7h, 0FFh, 0FFh, 0EFh, 0FFh, 0FFh, 0FFh, 0FFh, 03Fh, 03Fh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FDh, 03Eh, 0DFh, 0EFh
        DB 0EFh, 0EFh, 0DFh, 03Fh, 0FFh, 0FFh, 0FFh, 0EAh, 0D5h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0ABh, 057h, 0AFh
        DB 0FFh, 0FCh, 0FBh, 0FBh, 0BCh, 05Fh, 0BFh, 0FFh, 07Fh, 0B3h
        DB 073h, 0FFh, 0FFh, 0FFh, 07Fh, 0FFh, 0DFh, 0FFh, 05Eh, 0A2h
        DB 003h, 00Eh, 035h, 06Ah, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 057h, 0AFh, 057h, 0AFh, 05Fh, 0BFh, 05Fh, 0BFh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AFh, 05Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FEh, 0FDh, 0FAh, 0F5h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0ABh
        DB 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FBh
        DB 0F5h, 0FBh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0EFh, 0FFh, 0FFh, 0FFh, 07Fh, 0BFh
        DB 0BFh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
        DB 055h, 0AAh, 0FDh, 0FEh, 0FFh, 0FEh, 07Fh, 07Fh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 000h
lab56:  DB 011h, 001h, 001h, 001h, 001h, 002h, 002h, 002h, 002h, 002h
        DB 00Ch, 030h, 0C0h, 080h, 080h, 09Fh, 0F0h, 01Fh, 0DFh, 028h
        DB 018h, 024h, 05Ah, 042h, 042h, 099h, 0A5h, 0A5h, 0BDh, 081h
        DB 099h, 081h, 081h, 0A5h, 081h, 081h, 081h, 081h, 081h, 081h
        DB 081h, 081h, 081h, 081h, 081h, 081h, 081h, 081h, 081h, 099h
        DB 099h, 099h, 099h, 099h, 099h, 0FFh, 099h, 0FFh, 018h, 018h
        DB 0E8h, 011h, 080h, 080h, 080h, 080h, 040h, 040h, 040h, 040h
        DB 040h, 030h, 00Ch, 003h, 001h, 001h, 0F9h, 00Fh, 0F8h, 000h
lab92:  DB 000h, 000h, 000h, 000h, 000h, 080h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 0C0h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 002h, 0A0h, 000h, 000h, 000h, 000h, 000h, 000h, 006h, 0B0h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 01Dh, 0B8h, 000h, 000h
        DB 000h, 000h, 01Dh, 0B8h, 01Dh, 0B8h, 000h, 000h, 000h, 000h
        DB 03Dh, 0DEh, 025h, 0D2h, 03Dh, 0DEh, 0F9h, 09Fh, 08Fh, 0F1h
        DB 08Bh, 0D1h, 0F9h, 09Fh
lab126: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 0C0h, 003h, 0E0h, 003h, 0E0h, 003h, 0E0h
        DB 001h, 0C0h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 00Ah, 010h, 000h, 000h, 012h, 080h, 003h, 0E0h, 00Bh, 0F0h
        DB 003h, 0E0h, 001h, 0A8h, 010h, 040h, 000h, 088h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 010h, 008h, 002h, 080h, 001h, 0C0h, 005h, 000h, 00Dh, 0D8h
        DB 011h, 060h, 007h, 052h, 005h, 0A8h, 002h, 080h, 020h, 020h
        DB 000h, 004h, 000h, 000h, 002h, 020h, 000h, 000h, 000h, 000h
        DB 010h, 008h, 000h, 080h, 000h, 040h, 00Bh, 020h, 002h, 044h
        DB 004h, 0A0h, 021h, 048h, 005h, 041h, 012h, 090h, 008h, 040h
        DB 040h, 000h, 002h, 020h, 000h, 002h, 000h, 000h, 000h, 000h
        DB 020h, 004h, 000h, 080h, 000h, 000h, 090h, 000h, 004h, 040h
        DB 008h, 012h, 001h, 000h, 042h, 0A1h, 004h, 000h, 001h, 040h
        DB 040h, 024h, 008h, 000h, 000h, 000h, 000h, 020h, 004h, 000h
        DB 000h, 000h, 000h, 080h, 000h, 000h, 020h, 002h, 008h, 000h
        DB 000h, 000h, 001h, 009h, 000h, 000h, 080h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 000h, 000h, 001h, 010h, 000h, 000h, 000h
        DB 000h, 010h
lab13:  DB 025h, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
        DB 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 0FDh, 0FEh, 0FFh
        DB 0FEh, 07Fh, 07Fh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 000h, 011h, 001h
        DB 001h, 001h, 001h, 002h, 002h, 002h, 002h, 002h, 00Ch, 030h
        DB 0C0h, 080h, 080h, 09Fh, 0F0h, 01Fh, 0DFh, 028h, 018h, 024h
        DB 05Ah, 042h, 042h, 099h, 0A5h, 0A5h, 0BDh, 081h, 099h, 081h
        DB 081h, 0A5h, 081h, 081h, 081h, 081h, 081h, 081h, 081h, 081h
        DB 081h, 081h, 081h, 081h, 081h, 081h, 081h, 099h, 000h, 000h
        DB 000h, 000h, 001h, 000h, 000h, 001h, 010h, 000h
END