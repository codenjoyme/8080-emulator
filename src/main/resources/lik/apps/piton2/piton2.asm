        CPU  8080
        .ORG 00000h
lsxy    EQU 05700h
luyz    EQU 0F700h
lgus    EQU 01900h
lwca    EQU 0FF00h
lubz    EQU 0FF01h
lydb    EQU 0FF02h
lsay    EQU 0FF03h
lmli    EQU 0110Eh
laop    EQU 0100Fh
lqkk    EQU 0180Fh
lijg    EQU 01323h
lihg    EQU 0C427h
lmhi    EQU 0C037h
loyw    EQU 02377h
lccd    EQU 00A80h
ligg    EQU 01080h
lggf    EQU 02080h
lcsq    EQU 0FFE0h
labc    EQU 03FFFh
letr    EQU 0FFFFh
lqnk:   LXI SP,labc
        LXI H,lccd
        LXI D,lede
        CALL lgef
        MVI C,02Eh
lkgh:   LXI D,lifg
        CALL lgef
        DCR C
        JNZ lkgh
        LXI D,lede
        CALL lgef
        MVI C,01Fh
        CALL lmhi
        LXI B,00000h
        LXI H,loij
        CALL lqjk
lulm:   CALL lskl
        ORA A
        JNZ lulm
lyno:   CALL lwmn
        CALL lskl
        CPI 020h
        JNZ lyno
        MVI C,01Fh
        CALL lmhi
        LXI B,laop
        LXI H,lcpq
        CALL lqjk
        DCR B
        LDA leqr
        MOV D,A
        JMP lgrs
list:   CALL lskl
        ORA A
        JNZ list
lktu:   CALL lskl
        ORA A
        JZ lktu
        CPI 020h
        JZ lmuv
        INR D
        MOV A,D
        CPI 01Ah
        CZ lovw
lgrs:   MOV A,D
        CALL lqwx
        JMP list
lovw:   MVI D,011h
        RET
lmuv:   MOV A,D
        STA leqr
        SUI 010h
        LXI H,lsxy
        LXI B,luyz
lwza:   DAD B
        DCR A
        JNZ lwza
        SHLD lyab
        MVI C,01Fh
        CALL lmhi
        LXI B,00000h
        LXI H,lacc
        CALL lqjk
        MVI D,01Dh
leee:   LXI H,lcdd
        CALL lqjk
        DCR D
        JNZ leee
        LXI H,lgff
        CALL lqjk
        LXI H,ligg
        SHLD lkhh
        SHLD lmii
        LXI H,00100h
        SHLD lojj
        LXI H,0030Fh
        SHLD ligg
        LXI B,lccd
        DAD B
        MVI M,002h
        LXI B,lqkk
        MVI A,00Dh
        CALL lqwx
        MVI A,004h
        STA lsll
        STA lumm
        MVI A,001h
        STA lwnn
        XRA A
        STA lyoo
        STA lapp
        STA lcqq
        STA lerr
        LXI B,0031Fh
        MVI A,043h
        CALL lqwx
        MVI D,005h
litt:   PUSH D
        CALL lgss
        POP D
        DCR D
        JNZ litt
lqmk:   CALL lwmn
        MOV A,H
        ANI 03Fh
        CZ lgss
        CALL lwmn
        MOV A,L
        ANI 07Fh
        CZ lkuu
        CALL lwmn
        MOV A,H
        ANI 07Fh
        CZ lmvv
        CALL lwmn
        MOV A,H
        CPI 034h
        PUSH PSW
        CZ loww
        POP PSW
        ANI 03Fh
        CPI 020h
        CZ lqxx
        LDA lyoo
        ORA A
        JNZ lsyy
        CALL luzz
        STA lsll
        LHLD lkhh
        CALL lwaa
        PUSH H
        CALL lybb
        MVI A,002h
        CALL lqwx
        LDA lsll
        POP H
        CALL ladc
        DAD B
        PUSH H
        LXI B,lccd
        DAD B
        MOV A,M
        STA lced
        MVI M,002h
        POP H
        PUSH H
        CALL lefe
        MOV A,D
        CALL lqwx
        POP D
        LHLD lkhh
        INX H
        INX H
        PUSH D
        LXI D,lggf
        CALL lihg
        POP D
        CZ lkih
        SHLD lkhh
        CALL lmji
        LDA lokj
        ORA A
        CNZ lqlk
lqyx:   LDA lumm
        ORA A
        JZ lsml
        DCR A
        STA lumm
        CALL lunm
        JMP lwon
lqlk:   LDA lcqq
        ORA A
        RZ
        MVI D,01Bh
        LDA lsll
        CPI 004h
        JZ lypo
        CPI 010h
        JZ lypo
        INR D
lypo:   CALL laqp
        MVI D,000h
        CALL laqp
        XRA A
        STA lokj
        RET
laqp:   LHLD lkhh
        CALL lwaa
        MVI E,003h
liut:   LDA lsll
        CALL ladc
        DAD B
        PUSH H
        LXI B,lccd
        DAD B
        MOV A,M
        ORA A
        JZ lcrq
        CPI 040h
        JZ lcrq
        CPI 00Bh
        JZ lcrq
        JMP lesr
lcrq:   MVI M,000h
        POP H
        PUSH H
        CALL lybb
        MOV A,D
        CALL lqwx
        LXI H,00100h
        CALL lgts
        POP H
        DCR E
        JNZ liut
        RET
lqxx:   LDA lwnn
        CPI 00Fh
        RZ
        LDA lerr
        ORA A
        RNZ
        MVI A,001h
        STA lerr
        MVI A,043h
        JMP lkvu
lsyy:   LDA lyoo
        DCR A
        STA lyoo
        CALL lmwv
        CALL loxw
        JZ lesr
        JMP lqyx
lkuu:   MVI A,003h
lkvu:   STA lszy
        JMP luaz
loww:   LDA lapp
        ORA A
        RNZ
        CALL lwba
        MVI A,042h
        STA lapp
        JMP lkvu
lgss:   MVI A,00Bh
        JMP lkvu
lmvv:   MVI A,040h
        JMP lkvu
lunm:   LXI H,lycb
        MOV A,M
        ADI 001h
        DAA
        MOV M,A
        DCX H
        MOV A,M
        ACI 000h
        DAA
        MOV M,A
        JMP laec
lmwv:   LXI H,lycb
        MOV A,M
        ADI 099h
        DAA
        MOV M,A
        DCX H
        CPI 099h
        MOV A,M
        JNZ lcfd
        ADI 099h
        DAA
lcfd:   MOV M,A
        JMP laec
loxw:   LXI H,lojj
        XRA A
        ORA M
        INX H
        ORA M
        RET
lkih:   LXI H,ligg
        RET
luaz:   CALL lwba
        PUSH H
        LXI B,lccd
        DAD B
        MOV A,M
        ORA A
        POP H
        JNZ lgss
        PUSH H
        LXI B,lccd
        DAD B
        LDA lszy
        MOV M,A
        POP H
        CALL lybb
        LDA lszy
        CALL lqwx
        RET
lwba:   CALL lwmn
        MOV A,H
        ANI 007h
        CPI 006h
        JC lege
        SUI 006h
lege:   MOV H,A
        RET
lsml:   LHLD lmii
        PUSH H
        INX H
        INX H
        PUSH D
        LXI D,lggf
        CALL lihg
        POP D
        CZ lkih
        SHLD lmii
        POP H
        CALL lwaa
        PUSH H
        LXI B,lccd
        DAD B
        MVI M,000h
        POP H
        CALL lybb
        XRA A
        CALL lqwx
lwon:   CALL lghf
        LDA lced
        CPI 00Bh
        JZ liig
        CPI 040h
        JZ lkjh
        CPI 042h
        JZ lmki
        CPI 043h
        JZ lolj
        ORA A
        JZ lqmk
        JMP lesr
lolj:   XRA A
        STA lerr
        LDA lwnn
        INR A
        STA lwnn
        ADI 002h
        MOV B,A
        MVI C,01Fh
        MVI A,043h
        CALL lqwx
        JMP lqmk
lmki:   MVI A,001h
        STA lcqq
        LXI B,0011Fh
        MVI A,042h
        CALL lqwx
        JMP lqmk
lkjh:   MVI A,014h
        STA lyoo
        XRA A
        STA lced
        LXI B,0011Fh
        CALL lqwx
        LDA lwnn
        MVI D,002h
luom:   CPI 001h
        JZ lsnl
        DCR A
        MOV E,A
        ADI 003h
        MOV B,A
        MVI C,01Fh
        XRA A
        CALL lqwx
        MOV A,E
        DCR D
        STA lwnn
        JNZ luom
lsnl:   LDA lcqq
        ORA A
        JZ lqmk
        XRA A
        STA lcqq
        STA lapp
        JMP lqmk
liig:   LDA lumm
        MOV B,A
        LDA lwnn
        ADD B
        STA lumm
        JMP lqmk
lghf:   LHLD lyab
lgts:   DCX H
        MOV A,H
        ORA L
        JNZ lgts
        RET
lefe:   CALL lybb
        MVI D,00Ch
        LDA lsll
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
luzz:   CALL lskl
        ORA A
        CZ lwpn
        CPI 004h
        RZ
        CPI 010h
        RZ
        CPI 040h
        RZ
        CPI 080h
        RZ
        CPI 020h
        CZ lyqo
        LDA lsll
        RET
lwpn:   PUSH PSW
        XRA A
        STA larp
        POP PSW
        RET
lyqo:   LDA larp
        ORA A
        RNZ
        INR A
        STA larp
        STA lokj
        RET
ladc:   LXI B,00020h
        CPI 004h
        RZ
        LXI B,lcsq
        CPI 010h
        RZ
        LXI B,letr
        CPI 080h
        RZ
        LXI B,00001h
        RET
lwaa:   PUSH D
        MOV E,M
        INX H
        MOV D,M
        XCHG
        POP D
        RET
lmji:   MOV M,E
        INX H
        MOV M,D
        RET
laec:   PUSH B
        PUSH H
        LXI B,lgus
        LXI H,lojj
        MOV A,M
        CALL livt
        INX H
        MOV A,M
        CALL livt
        POP H
        POP B
        RET
livt:   PUSH D
        MOV E,A
        RRC
        RRC
        RRC
        RRC
        CALL lkwu
        MOV D,A
        MOV A,E
        CALL lkwu
        MOV E,A
        MOV A,D
        SUI 020h
        CALL lqwx
        INR B
        MOV A,E
        SUI 020h
        CALL lqwx
        INR B
        POP D
        RET
lkwu:   ANI 00Fh
        CPI 00Ah
        JC lmxv
        ADI 007h
lmxv:   ADI 030h
        RET
lybb:   PUSH H
        DAD H
        DAD H
        DAD H
        MOV B,H
        POP H
        MOV A,L
        ANI 01Fh
        MOV C,A
        RET
lgef:   MVI B,020h
lqzx:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR B
        JNZ lqzx
        RET
lskl:   PUSH B
        MVI A,091h
        STA lsay
        MVI A,0FBh
        STA lubz
        LDA lwca
        ANI 03Fh
        MOV B,A
        LDA lydb
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
lwmn:   LHLD lafc
        MVI C,010h
lehe:   MOV A,H
        DAD H
        ANI 060h
        JPE lcgd
        INX H
lcgd:   DCR C
        JNZ lehe
        SHLD lafc
        RET
lqwx:   PUSH H
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
        LXI B,lgif
        MVI H,000h
        MOV L,A
        DAD H
        DAD H
        DAD H
        DAD B
        MVI B,008h
lkkh:   MOV A,M
        STAX D
        INX H
        INX D
        DCR B
        JNZ lkkh
        POP PSW
        POP B
        POP D
        POP H
        RET
lesr:   LXI B,lmli
        LXI H,lomj
        CALL lqjk
        MVI D,006h
lupm:   LXI H,letr
lsol:   CALL lskl
        CPI 020h
        JZ lqnk
        DCX H
        MOV A,H
        ORA L
        JNZ lsol
        DCR D
        JNZ lupm
        JMP lqnk
lqjk:   MOV A,M
        ORA A
        RZ
        CPI 00Ah
        JZ lwqn
        CPI 00Bh
        JZ lyro
        SUI 020h
        JZ lasp
        CALL lqwx
lasp:   INR B
lctq:   INX H
        JMP lqjk
lwqn:   MVI B,000h
        INR C
        JMP lctq
lyro:   INR B
        INR B
        INR B
        INR B
        JMP lctq

lede:   DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h, 041h
        DB 041h, 041h
lifg:   DB 041h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 041h, 041h
loij:   DB 00Ah, 00Ah, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 020h, 020h
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
lacc:   DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 020h, 020h, 020h, 020h, 044h
        DB 04Ch, 049h, 04Eh, 041h, 020h, 030h, 030h, 030h, 030h, 020h
        DB 020h, 020h, 020h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 00Ah, 000h
lcdd:   DB 021h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 021h, 00Ah, 000h
lgff:   DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h
        DB 021h, 021h, 021h, 021h, 021h, 021h, 021h, 021h, 00Ah, 000h
lcpq:   DB 053h, 04Bh, 04Fh, 052h, 04Fh, 053h, 054h, 058h, 020h, 02Dh
        DB 020h, 031h, 000h
lomj:   DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 020h, 020h, 064h, 064h, 049h, 047h, 052h, 041h, 064h
        DB 04Fh, 04Bh, 04Fh, 04Eh, 05Eh, 045h, 04Eh, 041h, 064h, 064h
        DB 00Ah, 00Bh, 00Bh, 00Bh, 020h, 020h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h, 064h
        DB 064h, 064h, 064h, 000h
lafc:   DB 001h, 000h
lojj:   DB 000h
lycb:   DB 006h
lkhh:   DB 096h, 079h
lmii:   DB 08Ch, 079h
lumm:   DB 000h
lsll:   DB 080h
lced:   DB 041h
lszy:   DB 00Bh
lyoo:   DB 000h
lyab:   DB 000h, 00Fh
leqr:   DB 014h
larp:   DB 000h
lokj:   DB 000h
lapp:   DB 000h
lcqq:   DB 000h
lwnn:   DB 001h
lerr:   DB 000h, 0CDh, 0E1h, 00Dh, 0D6h, 031h, 0FAh, 000h, 081h, 0FEh
        DB 003h, 032h, 012h, 010h, 0FAh, 02Fh, 008h, 021h
lgif:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 0FFh
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