        CPU  8080
        .ORG 00000h
lab22   EQU 05700h
lab23   EQU 0F700h
lab94   EQU 01900h
lab102  EQU 0FF00h
lab101  EQU 0FF01h
lab103  EQU 0FF02h
lab100  EQU 0FF03h
lab110  EQU 0110Eh
lab13   EQU 0100Fh
lab34   EQU 0180Fh
lab108  EQU 01323h
lab56   EQU 0C427h
lab6    EQU 0C037h
lab98   EQU 02377h
lab1    EQU 00A80h
lab30   EQU 01080h
lab55   EQU 02080h
lab92   EQU 0FFE0h
lab0    EQU 03FFFh
lab93   EQU 0FFFFh
lab112: LXI SP,lab0
        LXI H,lab1
        LXI D,lab2
        CALL lab3
        MVI C,02Eh
lab5:   LXI D,lab4
        CALL lab3
        DCR C
        JNZ lab5
        LXI D,lab2
        CALL lab3
        MVI C,01Fh
        CALL lab6
        LXI B,00000h
        LXI H,lab7
        CALL lab8
lab10:  CALL lab9
        ORA A
        JNZ lab10
lab12:  CALL lab11
        CALL lab9
        CPI 020h
        JNZ lab12
        MVI C,01Fh
        CALL lab6
        LXI B,lab13
        LXI H,lab14
        CALL lab8
        DCR B
        LDA lab15
        MOV D,A
        JMP lab16
lab17:  CALL lab9
        ORA A
        JNZ lab17
lab18:  CALL lab9
        ORA A
        JZ lab18
        CPI 020h
        JZ lab19
        INR D
        MOV A,D
        CPI 01Ah
        CZ lab20
lab16:  MOV A,D
        CALL lab21
        JMP lab17
lab20:  MVI D,011h
        RET
lab19:  MOV A,D
        STA lab15
        SUI 010h
        LXI H,lab22
        LXI B,lab23
lab24:  DAD B
        DCR A
        JNZ lab24
        SHLD lab25
        MVI C,01Fh
        CALL lab6
        LXI B,00000h
        LXI H,lab26
        CALL lab8
        MVI D,01Dh
lab28:  LXI H,lab27
        CALL lab8
        DCR D
        JNZ lab28
        LXI H,lab29
        CALL lab8
        LXI H,lab30
        SHLD lab31
        SHLD lab32
        LXI H,00100h
        SHLD lab33
        LXI H,0030Fh
        SHLD lab30
        LXI B,lab1
        DAD B
        MVI M,002h
        LXI B,lab34
        MVI A,00Dh
        CALL lab21
        MVI A,004h
        STA lab35
        STA lab36
        MVI A,001h
        STA lab37
        XRA A
        STA lab38
        STA lab39
        STA lab40
        STA lab41
        LXI B,0031Fh
        MVI A,043h
        CALL lab21
        MVI D,005h
lab43:  PUSH D
        CALL lab42
        POP D
        DCR D
        JNZ lab43
lab86:  CALL lab11
        MOV A,H
        ANI 03Fh
        CZ lab42
        CALL lab11
        MOV A,L
        ANI 07Fh
        CZ lab44
        CALL lab11
        MOV A,H
        ANI 07Fh
        CZ lab45
        CALL lab11
        MOV A,H
        CPI 034h
        PUSH PSW
        CZ lab46
        POP PSW
        ANI 03Fh
        CPI 020h
        CZ lab47
        LDA lab38
        ORA A
        JNZ lab48
        CALL lab49
        STA lab35
        LHLD lab31
        CALL lab50
        PUSH H
        CALL lab51
        MVI A,002h
        CALL lab21
        LDA lab35
        POP H
        CALL lab52
        DAD B
        PUSH H
        LXI B,lab1
        DAD B
        MOV A,M
        STA lab53
        MVI M,002h
        POP H
        PUSH H
        CALL lab54
        MOV A,D
        CALL lab21
        POP D
        LHLD lab31
        INX H
        INX H
        PUSH D
        LXI D,lab55
        CALL lab56
        POP D
        CZ lab57
        SHLD lab31
        CALL lab58
        LDA lab59
        ORA A
        CNZ lab60
lab73:  LDA lab36
        ORA A
        JZ lab61
        DCR A
        STA lab36
        CALL lab62
        JMP lab63
lab60:  LDA lab40
        ORA A
        RZ
        MVI D,01Bh
        LDA lab35
        CPI 004h
        JZ lab64
        CPI 010h
        JZ lab64
        INR D
lab64:  CALL lab65
        MVI D,000h
        CALL lab65
        XRA A
        STA lab59
        RET
lab65:  LHLD lab31
        CALL lab50
        MVI E,003h
lab69:  LDA lab35
        CALL lab52
        DAD B
        PUSH H
        LXI B,lab1
        DAD B
        MOV A,M
        ORA A
        JZ lab66
        CPI 040h
        JZ lab66
        CPI 00Bh
        JZ lab66
        JMP lab67
lab66:  MVI M,000h
        POP H
        PUSH H
        CALL lab51
        MOV A,D
        CALL lab21
        LXI H,00100h
        CALL lab68
        POP H
        DCR E
        JNZ lab69
        RET
lab47:  LDA lab37
        CPI 00Fh
        RZ
        LDA lab41
        ORA A
        RNZ
        MVI A,001h
        STA lab41
        MVI A,043h
        JMP lab70
lab48:  LDA lab38
        DCR A
        STA lab38
        CALL lab71
        CALL lab72
        JZ lab67
        JMP lab73
lab44:  MVI A,003h
lab70:  STA lab74
        JMP lab75
lab46:  LDA lab39
        ORA A
        RNZ
        CALL lab76
        MVI A,042h
        STA lab39
        JMP lab70
lab42:  MVI A,00Bh
        JMP lab70
lab45:  MVI A,040h
        JMP lab70
lab62:  LXI H,lab77
        MOV A,M
        ADI 001h
        DAA
        MOV M,A
        DCX H
        MOV A,M
        ACI 000h
        DAA
        MOV M,A
        JMP lab78
lab71:  LXI H,lab77
        MOV A,M
        ADI 099h
        DAA
        MOV M,A
        DCX H
        CPI 099h
        MOV A,M
        JNZ lab79
        ADI 099h
        DAA
lab79:  MOV M,A
        JMP lab78
lab72:  LXI H,lab33
        XRA A
        ORA M
        INX H
        ORA M
        RET
lab57:  LXI H,lab30
        RET
lab75:  CALL lab76
        PUSH H
        LXI B,lab1
        DAD B
        MOV A,M
        ORA A
        POP H
        JNZ lab42
        PUSH H
        LXI B,lab1
        DAD B
        LDA lab74
        MOV M,A
        POP H
        CALL lab51
        LDA lab74
        CALL lab21
        RET
lab76:  CALL lab11
        MOV A,H
        ANI 007h
        CPI 006h
        JC lab80
        SUI 006h
lab80:  MOV H,A
        RET
lab61:  LHLD lab32
        PUSH H
        INX H
        INX H
        PUSH D
        LXI D,lab55
        CALL lab56
        POP D
        CZ lab57
        SHLD lab32
        POP H
        CALL lab50
        PUSH H
        LXI B,lab1
        DAD B
        MVI M,000h
        POP H
        CALL lab51
        XRA A
        CALL lab21
lab63:  CALL lab81
        LDA lab53
        CPI 00Bh
        JZ lab82
        CPI 040h
        JZ lab83
        CPI 042h
        JZ lab84
        CPI 043h
        JZ lab85
        ORA A
        JZ lab86
        JMP lab67
lab85:  XRA A
        STA lab41
        LDA lab37
        INR A
        STA lab37
        ADI 002h
        MOV B,A
        MVI C,01Fh
        MVI A,043h
        CALL lab21
        JMP lab86
lab84:  MVI A,001h
        STA lab40
        LXI B,0011Fh
        MVI A,042h
        CALL lab21
        JMP lab86
lab83:  MVI A,014h
        STA lab38
        XRA A
        STA lab53
        LXI B,0011Fh
        CALL lab21
        LDA lab37
        MVI D,002h
lab88:  CPI 001h
        JZ lab87
        DCR A
        MOV E,A
        ADI 003h
        MOV B,A
        MVI C,01Fh
        XRA A
        CALL lab21
        MOV A,E
        DCR D
        STA lab37
        JNZ lab88
lab87:  LDA lab40
        ORA A
        JZ lab86
        XRA A
        STA lab40
        STA lab39
        JMP lab86
lab82:  LDA lab36
        MOV B,A
        LDA lab37
        ADD B
        STA lab36
        JMP lab86
lab81:  LHLD lab25
lab68:  DCX H
        MOV A,H
        ORA L
        JNZ lab68
        RET
lab54:  CALL lab51
        MVI D,00Ch
        LDA lab35
        CPI 080h
        RZ
        INR D
        CPI 004h
        RZ
        INR D
        CPI 040h
        RZ
        INR D
        RET
lab49:  CALL lab9
        ORA A
        CZ lab89
        CPI 004h
        RZ
        CPI 010h
        RZ
        CPI 040h
        RZ
        CPI 080h
        RZ
        CPI 020h
        CZ lab90
        LDA lab35
        RET
lab89:  PUSH PSW
        XRA A
        STA lab91
        POP PSW
        RET
lab90:  LDA lab91
        ORA A
        RNZ
        INR A
        STA lab91
        STA lab59
        RET
lab52:  LXI B,00020h
        CPI 004h
        RZ
        LXI B,lab92
        CPI 010h
        RZ
        LXI B,lab93
        CPI 080h
        RZ
        LXI B,00001h
        RET
lab50:  PUSH D
        MOV E,M
        INX H
        MOV D,M
        XCHG
        POP D
        RET
lab58:  MOV M,E
        INX H
        MOV M,D
        RET
lab78:  PUSH B
        PUSH H
        LXI B,lab94
        LXI H,lab33
        MOV A,M
        CALL lab95
        INX H
        MOV A,M
        CALL lab95
        POP H
        POP B
        RET
lab95:  PUSH D
        MOV E,A
        RRC
        RRC
        RRC
        RRC
        CALL lab96
        MOV D,A
        MOV A,E
        CALL lab96
        MOV E,A
        MOV A,D
        SUI 020h
        CALL lab21
        INR B
        MOV A,E
        SUI 020h
        CALL lab21
        INR B
        POP D
        RET
lab96:  ANI 00Fh
        CPI 00Ah
        JC lab97
        ADI 007h
lab97:  ADI 030h
        RET
lab51:  PUSH H
        DAD H
        DAD H
        DAD H
        MOV B,H
        POP H
        MOV A,L
        ANI 01Fh
        MOV C,A
        RET
lab3:   MVI B,020h
lab99:  LDAX D
        MOV M,A
        INX H
        INX D
        DCR B
        JNZ lab99
        RET
lab9:   PUSH B
        MVI A,091h
        STA lab100
        MVI A,0FBh
        STA lab101
        LDA lab102
        ANI 03Fh
        MOV B,A
        LDA lab103
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
lab11:  LHLD lab104
        MVI C,010h
lab106: MOV A,H
        DAD H
        ANI 060h
        JPE lab105
        INX H
lab105: DCR C
        JNZ lab106
        SHLD lab104
        RET
lab21:  PUSH H
        PUSH D
        PUSH B
        PUSH PSW
        MOV A,C
        RLC
        RLC
        RLC
        MOV L,A
        MOV A,B
        ADI 090h
        MOV H,A
        POP PSW
        PUSH PSW
        XCHG
        LXI B,lab107
        MVI H,000h
        MOV L,A
        DAD H
        DAD H
        DAD H
        DAD B
        MVI B,008h
lab109: MOV A,M
        STAX D
        INX H
        INX D
        DCR B
        JNZ lab109
        POP PSW
        POP B
        POP D
        POP H
        RET
lab67:  LXI B,lab110
        LXI H,lab111
        CALL lab8
        MVI D,006h
lab114: LXI H,lab93
lab113: CALL lab9
        CPI 020h
        JZ lab112
        DCX H
        MOV A,H
        ORA L
        JNZ lab113
        DCR D
        JNZ lab114
        JMP lab112
lab8:   MOV A,M
        ORA A
        RZ
        CPI 00Ah
        JZ lab115
        CPI 00Bh
        JZ lab116
        SUI 020h
        JZ lab117
        CALL lab21
lab117: INR B
lab118: INX H
        JMP lab8
lab115: MVI B,000h
        INR C
        JMP lab118
lab116: INR B
        INR B
        INR B
        INR B
        JMP lab118

lab2:   DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h
lab4:   DB 041h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 041h, 041h
lab7:   DB 00Ah, 00Ah, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 020h, 020h
        DB 024h, 025h, 026h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 020h
        DB 020h, 027h, 028h, 029h, 00Ah, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh
        DB 05Eh, 045h, 052h, 04Eh, 04Fh, 057h, 043h, 059h, 020h, 02Ah
        DB 020h, 031h, 039h, 038h, 038h, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Bh, 023h, 023h, 023h, 023h, 023h, 023h, 023h, 020h
        DB 023h, 020h, 020h, 020h, 020h, 020h, 023h, 020h, 023h, 023h
        DB 023h, 023h, 023h, 023h, 023h, 020h, 023h, 023h, 023h, 023h
        DB 023h, 023h, 023h, 020h, 023h, 020h, 020h, 020h, 020h, 020h
        DB 023h, 00Ah, 00Bh, 023h, 020h, 020h, 020h, 020h, 020h, 023h
        DB 020h, 023h, 020h, 020h, 020h, 020h, 023h, 023h, 020h, 020h
        DB 020h, 020h, 023h, 020h, 020h, 020h, 020h, 023h, 020h, 020h
        DB 020h, 020h, 020h, 023h, 020h, 023h, 020h, 020h, 020h, 020h
        DB 020h, 023h, 00Ah, 00Bh, 023h, 020h, 020h, 020h, 020h, 020h
        DB 023h, 020h, 023h, 020h, 020h, 020h, 023h, 020h, 023h, 020h
        DB 020h, 020h, 020h, 023h, 020h, 020h, 020h, 020h, 023h, 020h
        DB 020h, 020h, 020h, 020h, 023h, 020h, 023h, 020h, 020h, 020h
        DB 020h, 020h, 023h, 00Ah, 00Bh, 023h, 020h, 020h, 020h, 020h
        DB 020h, 023h, 020h, 023h, 020h, 020h, 023h, 020h, 020h, 023h
        DB 020h, 020h, 020h, 020h, 023h, 020h, 020h, 020h, 020h, 023h
        DB 020h, 020h, 020h, 020h, 020h, 023h, 020h, 023h, 023h, 023h
        DB 023h, 023h, 023h, 023h, 00Ah, 00Bh, 023h, 023h, 020h, 020h
        DB 020h, 020h, 023h, 020h, 023h, 020h, 023h, 020h, 020h, 023h
        DB 023h, 020h, 020h, 020h, 020h, 023h, 023h, 020h, 020h, 020h
        DB 023h, 023h, 023h, 020h, 020h, 020h, 023h, 020h, 023h, 023h
        DB 020h, 020h, 020h, 020h, 023h, 00Ah, 00Bh, 023h, 023h, 020h
        DB 020h, 020h, 020h, 023h, 020h, 023h, 023h, 020h, 020h, 020h
        DB 023h, 023h, 020h, 020h, 020h, 020h, 023h, 023h, 020h, 020h
        DB 020h, 023h, 023h, 023h, 020h, 020h, 020h, 023h, 020h, 023h
        DB 023h, 020h, 020h, 020h, 020h, 023h, 00Ah, 00Bh, 023h, 023h
        DB 020h, 020h, 020h, 020h, 023h, 020h, 023h, 020h, 020h, 020h
        DB 020h, 023h, 023h, 020h, 020h, 020h, 020h, 023h, 023h, 020h
        DB 020h, 020h, 023h, 023h, 023h, 020h, 020h, 020h, 023h, 020h
        DB 023h, 023h, 020h, 020h, 020h, 020h, 023h, 00Ah, 00Bh, 023h
        DB 023h, 020h, 020h, 020h, 020h, 023h, 020h, 023h, 020h, 020h
        DB 020h, 020h, 023h, 023h, 020h, 020h, 020h, 020h, 023h, 023h
        DB 020h, 020h, 020h, 023h, 023h, 023h, 023h, 023h, 023h, 023h
        DB 020h, 023h, 023h, 020h, 020h, 020h, 020h, 023h, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Bh, 00Bh, 00Bh, 020h, 020h, 020h, 022h
        DB 022h, 022h, 022h, 022h, 022h, 02Dh, 020h, 02Bh, 020h, 022h
        DB 022h, 022h, 022h, 022h, 022h, 022h, 020h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 020h, 020h, 022h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 022h, 00Ah, 00Bh, 00Bh, 00Bh, 020h, 020h, 022h, 020h
        DB 060h, 020h, 020h, 020h, 060h, 020h, 020h, 020h, 060h, 020h
        DB 020h, 020h, 060h, 020h, 020h, 020h, 022h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 020h, 020h, 022h, 020h, 020h, 020h, 060h, 020h, 020h
        DB 020h, 060h, 020h, 020h, 020h, 060h, 020h, 020h, 020h, 060h
        DB 020h, 022h, 00Ah, 00Bh, 00Bh, 00Bh, 020h, 020h, 022h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 022h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 020h, 020h, 020h, 022h, 022h, 022h, 022h, 022h, 022h
        DB 022h, 022h, 022h, 022h, 022h, 022h, 022h, 022h, 022h, 022h
        DB 022h, 020h, 000h
lab26:  DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 020h, 020h, 020h, 020h, 044h
        DB 04Ch, 049h, 04Eh, 041h, 020h, 030h, 030h, 030h, 030h, 020h
        DB 020h, 020h, 020h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 00Ah, 000h
lab27:  DB 021h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 021h, 00Ah, 000h
lab29:  DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 00Ah, 000h
lab14:  DB 053h, 04Bh, 04Fh, 052h, 04Fh, 053h, 054h, 058h, 020h, 02Dh
        DB 020h, 031h, 000h
lab111: DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 020h, 020h, 064h, 064h, 049h, 047h, 052h, 041h, 064h
        DB 04Fh, 04Bh, 04Fh, 04Eh, 05Eh, 045h, 04Eh, 041h, 064h, 064h
        DB 00Ah, 00Bh, 00Bh, 00Bh, 020h, 020h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 000h
lab104: DB 001h, 000h
lab33:  DB 000h
lab77:  DB 006h
lab31:  DB 096h, 079h
lab32:  DB 08Ch, 079h
lab36:  DB 000h
lab35:  DB 080h
lab53:  DB 041h
lab74:  DB 00Bh
lab38:  DB 000h
lab25:  DB 000h, 00Fh
lab15:  DB 014h
lab91:  DB 000h
lab59:  DB 000h
lab39:  DB 000h
lab40:  DB 000h
lab37:  DB 001h
lab41:  DB 000h, 0CDh, 0E1h, 00Dh, 0D6h, 031h, 0FAh, 000h, 081h, 0FEh
        DB 003h, 032h, 012h, 010h, 0FAh, 02Fh, 008h, 021h
lab107: DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 03Ch, 042h, 099h, 0BDh
        DB 0BDh, 099h, 042h, 03Ch, 049h, 092h, 024h, 0C9h, 092h, 024h
        DB 049h, 092h, 000h, 000h, 001h, 002h, 007h, 007h, 00Fh, 00Fh
        DB 07Fh, 083h, 005h, 009h, 0F1h, 0F3h, 0F3h, 0F7h, 01Eh, 026h
        DB 04Ch, 094h, 0E8h, 0C8h, 0CCh, 094h, 01Eh, 01Eh, 03Ch, 03Dh
        DB 07Ah, 07Ch, 0F8h, 0F0h, 0F7h, 0FFh, 0FFh, 0F7h, 0F7h, 0F7h
        DB 0FBh, 0F1h, 0A2h, 042h, 081h, 082h, 084h, 0C8h, 0D0h, 0E0h
        DB 03Ch, 042h, 099h, 0A1h, 0A1h, 099h, 042h, 03Ch, 008h, 004h
        DB 003h, 0C3h, 0FCh, 03Ch, 044h, 022h, 018h, 024h, 042h, 081h
        DB 0A5h, 081h, 042h, 03Ch, 038h, 044h, 092h, 081h, 081h, 092h
        DB 044h, 038h, 03Ch, 042h, 081h, 0A5h, 081h, 042h, 024h, 018h
        DB 01Ch, 022h, 049h, 081h, 081h, 049h, 022h, 01Ch, 0FEh, 082h
        DB 082h, 082h, 0C2h, 0C2h, 0C2h, 0FEh, 008h, 008h, 018h, 018h
        DB 018h, 018h, 018h, 018h, 07Eh, 002h, 002h, 0FEh, 080h, 0C0h
        DB 0C2h, 0FEh, 03Eh, 002h, 002h, 03Eh, 002h, 006h, 006h, 0FEh
        DB 082h, 082h, 0C2h, 0C2h, 0FEh, 006h, 006h, 006h, 0FCh, 080h
        DB 080h, 0FEh, 002h, 006h, 086h, 0FEh, 0F8h, 0C8h, 0C0h, 0FEh
        DB 082h, 086h, 086h, 0FEh, 0FEh, 082h, 002h, 002h, 006h, 006h
        DB 006h, 006h, 07Ch, 064h, 064h, 0FEh, 082h, 0C2h, 0C2h, 0FEh
        DB 0FEh, 082h, 0C2h, 0C2h, 0FEh, 006h, 026h, 03Eh, 000h, 018h
        DB 018h, 000h, 000h, 018h, 018h, 000h, 000h, 000h, 030h, 048h
        DB 085h, 002h, 000h, 000h, 008h, 004h, 008h, 010h, 020h, 020h
        DB 010h, 008h, 000h, 000h, 000h, 07Eh, 07Eh, 000h, 000h, 000h
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
        DB 062h, 07Eh, 03Ch, 066h, 0E7h, 0FFh, 0AFh, 007h, 056h, 03Ch
        DB 018h, 018h, 018h, 018h, 018h, 018h, 018h, 018h, 042h, 024h
        DB 024h, 042h, 0A5h, 099h, 0A5h, 07Eh, 004h, 068h, 09Eh, 081h
        DB 081h, 082h, 044h, 038h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 008h, 018h, 018h, 0FFh, 0FFh, 000h, 000h, 000h
        DB 000h, 000h, 0FFh, 0FFh, 000h, 000h, 0FFh, 0FFh, 000h, 000h
        DB 0FFh, 0FFh, 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh, 000h, 000h
        DB 0FFh, 0FFh, 000h, 000h, 0FFh, 0FFh, 000h, 000h, 0FFh, 0FFh
        DB 000h, 000h, 004h, 010h, 07Eh, 0B0h, 077h, 021h, 002h, 010h
        DB 07Eh, 03Ch, 027h, 077h, 0E1h, 0C9h, 006h, 004h
END