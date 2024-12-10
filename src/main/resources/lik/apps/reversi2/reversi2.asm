        CPU  8080
        .ORG 00000h
loww    EQU 09000h
lmji    EQU 02000h
lypo    EQU 01000h
lwnn    EQU 0121Dh
lojj    EQU 01423h
lgrs    EQU 0C427h
lsnl    EQU 02033h
lqmk    EQU 02034h
lccd    EQU 0C037h
lwaa    EQU 0C438h
lolj    EQU 02043h
luom    EQU 02044h
lefe    EQU 04450h
laqp    EQU 04C50h
lghf    EQU 0905Bh
leyr    EQU 09660h
lgts    EQU 05673h
lodw    EQU 02078h
lkjh    EQU 02080h
lgzs    EQU 0AAA0h
locw    EQU 0CAB7h
lwrn    EQU 0B4C3h
lycb    EQU 090FFh
lqcx    EQU 0FF00h
lobw    EQU 0FF01h
lsdy    EQU 0FF02h
lmav    EQU 0FF03h
lulm    EQU 02313h
lapp    EQU 0D514h
lcfd    EQU 0851Bh
lyoo    EQU 0AF1Ch
lyqo    EQU 09534h
lkbu    EQU 09766h
lutm    EQU 0C170h
lcsq    EQU 09574h
latp    EQU 02377h
lspl    EQU 01F80h
leme    EQU 02388h
liog    EQU 02392h
lmcv    EQU 0A99Ah
lmni    EQU 077B6h
lsyy    EQU 03DC0h
lede    EQU 08FE1h
lssl    EQU 08FF1h
lqrk    EQU 08FF2h
luzz    EQU 08FFCh
labc    EQU 03FFFh
lmvv    EQU 0FFFFh
        LXI SP,labc
        MVI C,01Fh
        CALL lccd
        LXI H,00000h
        SHLD lede
        JMP lgef
laop:   PUSH H
        PUSH D
        MVI E,000h
loij:   MOV A,C
        RAL
        MOV C,A
        JC lifg
        LXI H,lkgh
lskl:   CALL lmhi
        INR E
        MOV A,E
        CPI 008h
        JNZ loij
        POP D
        POP H
        RET
lifg:   LXI H,lqjk
        JMP lskl
lmhi:   PUSH D
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
lwmn:   MOV A,M
        STAX D
        INX D
        INX H
        DCR B
        JNZ lwmn
        POP D
        RET
luyz:   PUSH H
        PUSH D
        LXI H,lyno
        MVI D,000h
lcpq:   MOV C,M
        CALL laop
        INX H
        INR D
        MOV A,D
        CPI 030h
        JNZ lcpq
        POP D
        POP H
        RET
lmuv:   LXI H,lyno
        LXI D,leqr
list:   MVI M,000h
        INX H
        CALL lgrs
        JNZ list
        RET
lsxy:   PUSH H
        PUSH D
        LXI H,lyno
        LXI D,leqr
lktu:   INX H
        MOV A,M
        DCX H
        MOV M,A
        INX H
        CALL lgrs
        JNZ lktu
        POP D
        POP H
        RET
lkuu:   CALL lmuv
        LXI H,lovw
        LXI D,lqwx
lwza:   MOV A,M
        STA leqr
        CALL lsxy
        CALL luyz
        INX H
        CALL lgrs
        JNZ lwza
        RET
lgss:   MVI C,080h
lcdd:   MOV L,C
        CALL lyab
        MOV A,C
        CMA
        MOV L,A
        CALL lyab
        LXI D,00100h
        CALL lacc
        INR C
        JNZ lcdd
        RET
lyab:   MVI H,090h
ligg:   MOV A,B
        ORA A
        CZ leee
        CNZ lgff
        INR H
        MOV A,H
        CPI 0C0h
        JNZ ligg
        RET
leee:   XRA A
        MOV M,A
        RET
lgff:   MOV A,M
        CMA
        MOV M,A
        RET
lacc:   PUSH H
        LXI H,00000h
lkhh:   INX H
        CALL lgrs
        JNZ lkhh
        POP H
        RET
litt:   LXI H,lmii
        MVI B,010h
lumm:   MVI C,01Ch
        MVI D,09Ah
        MVI A,0F8h
        SUB B
        MOV E,A
lqkk:   MOV A,M
        STAX D
        INX H
        INR D
        DCR C
        JNZ lqkk
        CALL lsll
        DCR B
        JNZ lumm
        RET
lsll:   PUSH B
        MVI B,0C8h
lerr:   MVI D,09Ah
        MVI C,01Ch
lcqq:   LDAX D
        DCR E
        STAX D
        INR E
        XRA A
        STAX D
        INR D
        PUSH D
        LXI D,00001h
        CALL lacc
        POP D
        DCR C
        JNZ lcqq
        DCR E
        DCR B
        JNZ lerr
        POP B
        RET
lgef:   MVI B,000h
        CALL lgss
        CALL litt
        CALL lkuu
        LXI D,lmvv
        CALL lacc
        MVI B,005h
lqxx:   CALL lgss
        LXI D,loww
        CALL lacc
        DCR B
        JNZ lqxx
        LXI H,lsyy
        SHLD luzz
        LXI H,lqwx
        CALL lwaa
        LXI D,lmvv
lced:   CALL lybb
        ORA A
        JNZ ladc
        PUSH D
        LXI D,00400h
        CALL lacc
        POP D
        DCX D
        MOV A,D
        CMP E
        JNZ lced
        XRA A
        MOV B,A
        CALL lgss
        JMP lgef

lkgh:   DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
lqjk:   DB 000h, 03Ch, 07Eh, 07Eh, 07Eh, 07Eh, 03Ch, 000h
lyno:   DB 0D3h, 008h, 03Ah, 097h, 00Bh, 0FEh, 001h, 0CAh, 089h, 005h
        DB 03Ah, 097h, 00Bh, 04Fh, 006h, 000h, 0AFh, 0CDh, 080h, 009h
        DB 0B7h, 0C2h, 061h, 005h, 004h, 078h, 0E6h, 00Fh, 0FEh, 008h
        DB 0C2h, 037h, 005h, 03Eh, 010h, 080h, 0E6h, 0F0h, 047h, 0FEh
        DB 080h, 0DAh, 037h, 005h, 03Ah, 097h, 00Bh, 0B9h
leqr:   DB 000h
lovw:   DB 000h, 000h, 000h, 000h, 07Ch, 050h, 050h, 020h, 000h, 000h
        DB 07Ch, 054h, 054h, 054h, 000h, 000h, 07Ch, 054h, 054h, 028h
        DB 000h, 000h, 07Ch, 054h, 054h, 054h, 000h, 000h, 07Ch, 050h
        DB 050h, 020h, 000h, 000h, 038h, 044h, 044h, 028h, 000h, 000h
        DB 07Ch, 008h, 010h, 07Ch, 000h, 000h, 000h, 000h
lqwx:   DB 020h, 020h, 020h, 06Eh, 061h, 076h, 06Dh, 069h, 074h, 065h
        DB 020h, 022h, 070h, 072h, 06Fh, 062h, 065h, 06Ch, 022h, 021h
        DB 000h, 020h, 021h, 000h
lmii:   DB 0E0h, 01Ch, 0FFh, 0FCh, 0FFh, 0E0h, 0E0h, 01Ch, 03Fh, 0F0h
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
ladc:   MVI B,000h
        CALL lgss
        LXI H,lefe
        SHLD luzz
        LXI H,lggf
        CALL lwaa
        MVI B,053h
        MVI A,001h
        CALL lihg
        CALL lkih
        MVI B,054h
        MVI A,002h
        CALL lihg
        CALL lkih
        MVI C,001h
lsml:   MVI A,052h
        ADD C
        MOV B,A
        XRA A
        PUSH B
        CALL lihg
        LXI D,lmji
        CALL lokj
        POP B
        PUSH B
        MOV A,C
        CALL lihg
        CALL lkih
        LXI D,lmji
        CALL lokj
        POP B
        CALL lybb
        CPI 020h
        JZ lqlk
        ORA A
        JZ lsml
        MVI A,003h
        SUB C
        MOV C,A
        JMP lsml
lqlk:   MOV A,C
        STA lunm
        MVI A,003h
        SUB C
        STA lwon
        LXI D,lypo
        CALL lokj
        MVI B,000h
        CALL lgss
        LXI H,laqp
        SHLD luzz
        LXI H,lcrq
        CALL lwaa
        MVI C,003h
        MVI B,053h
lesr:   PUSH B
        MVI A,001h
        CALL lihg
        CALL lkih
        POP B
        INR B
        DCR C
        JNZ lesr
        LXI H,lgts
        LXI D,00020h
        MVI C,031h
liut:   SHLD luzz
        CALL lccd
        DAD D
        INR C
        MOV A,C
        CPI 034h
        JNZ liut
lqyx:   MVI B,053h
lmwv:   PUSH B
        CALL lkvu
        LXI D,lmji
        CALL lokj
        POP B
        CALL lybb
        ORA A
        JZ lmwv
        CPI 020h
        JZ loxw
        INR B
        MOV A,B
        CPI 056h
        JNZ lmwv
        JMP lqyx
loxw:   MOV A,B
        SUI 053h
        STA lszy
        MVI B,000h
        CALL lgss
        LXI H,loww
        LXI B,00100h
        MVI E,0FFh
lwba:   PUSH H
        MVI D,020h
        CALL luaz
        POP H
        MOV A,L
        ADI 020h
        MOV L,A
        JNC lwba
        LXI H,lycb
        MVI D,020h
        CALL luaz
        LXI H,loww
        LXI B,00001h
        MVI E,080h
laec:   PUSH H
        MVI D,0FFh
        CALL luaz
        POP H
        MOV A,H
        ADI 004h
        MOV H,A
        CPI 0B4h
        JNZ laec
        LDA lwon
        MVI B,091h
        CALL lihg
        CALL lkih
        MVI B,093h
        LDA lunm
        CALL lihg
        CALL lkih
        LXI H,lcfd
        SHLD luzz
        LXI H,lege
        CALL lwaa
        LXI H,lghf
        SHLD luzz
        LXI H,liig
        CALL lwaa
        LXI H,lmji
        LXI D,lkjh
lmki:   MVI M,000h
        INX H
        CALL lgrs
        JNZ lmki
        MVI A,001h
        STA lolj
        STA lqmk
        INR A
        STA lsnl
        STA luom
        CALL lwpn
        LXI H,lyqo
        SHLD luzz
        LXI H,larp
        CALL lwaa
        LXI H,lcsq
        SHLD luzz
        LXI H,larp
        MVI A,002h
        STA letr
        STA lgus
        CALL lwaa
        CALL livt
        LDA lunm
        CPI 001h
        JZ lkwu
lmyv:   LDA lunm
        MOV C,A
lubz:   MVI B,000h
lqzx:   XRA A
        CALL lmxv
        ORA A
        JNZ loyw
        INR B
        MOV A,B
        ANI 00Fh
        CPI 008h
        JNZ lqzx
        MVI A,010h
        ADD B
        ANI 0F0h
        MOV B,A
        CPI 080h
        JC lqzx
        LDA lunm
        CMP C
        JNZ lsay
        LDA lwon
        MOV C,A
        JMP lubz
loyw:   LDA lwon
        CMP C
        JZ lkwu
        CALL lwca
        LDA lunm
        MOV C,A
        XRA A
        CALL lmxv
        ORA A
        JZ lydb
        CALL lmxv
        CALL lafc
        CALL lcgd
        CALL lwpn
        CALL livt
        JMP lkwu
lkwu:   LDA lwon
        MOV C,A
        MVI A,005h
        STA lehe
        XRA A
        STA lgif
        LXI H,lijg
lkxu:   MVI A,004h
        STA lkkh
        XRA A
        STA lmli
        STA lomj
lgvs:   MOV A,M
        STA lqnk
        STA lsol
        INX H
        MOV A,M
        STA lupm
        INX H
        CALL lwqn
        LDA lqnk
leur:   MOV B,A
        XRA A
        PUSH H
        CALL lmxv
        POP H
        ORA A
        JNZ lyro
lqax:   LDA lupm
        MOV E,A
        LDA lqnk
        CMP E
        JZ lasp
        MOV E,A
        LDA lctq
        ADD E
        STA lqnk
        JMP leur
lasp:   LDA lkkh
        DCR A
        STA lkkh
        JNZ lgvs
        LDA lmli
        ORA A
        JNZ liwt
        LDA lehe
        DCR A
        STA lehe
        JNZ lkxu
        LDA lgif
        ORA A
        JZ lmyv
        MOV B,A
        JMP lozw
lyro:   MOV E,A
        LDA lszy
        ORA A
        JZ lozw
        LDA lmli
        CMP E
        JNC lqax
        LDA lszy
        DCR A
        JNZ lsby
lucz:   MOV A,E
        STA lmli
        MOV A,B
        STA lomj
        JMP lqax
lsby:   LDA lehe
        CPI 004h
        JNZ lucz
        MOV A,B
        STA lgif
        CALL lwda
        ORA A
        JZ lqax
        JMP lucz
liwt:   LDA lomj
        MOV B,A
lozw:   MVI A,001h
        CALL lmxv
        CALL lafc
        CALL lcgd
        CALL lwpn
        CALL livt
        JMP lmyv
lwqn:   LDA lqnk
        MOV E,A
        LDA lupm
        SUB E
        CPI 010h
        JC lyeb
        MVI A,010h
lagc:   STA lctq
        RET
lyeb:   MVI A,001h
        JMP lagc
lwda:   PUSH H
        CALL lchd
        MVI A,001h
        CALL lmxv
        CALL lafc
        CALL leie
        MOV A,D
        STA lgjf
        MOV A,E
        STA likg
        CALL lklh
        CALL leie
        CALL lmmi
        POP H
        LDA lgjf
        CMP D
        JZ lonj
        JNC lqok
lonj:   LDA likg
        CMP E
        JNZ lqok
        MVI A,001h
        RET
lqok:   XRA A
        RET
lchd:   PUSH H
        PUSH D
        PUSH B
        MVI C,001h
levr:   LXI H,lmji
        LXI D,lspl
lcuq:   MOV A,C
        ORA A
        JZ luqm
        MOV A,M
        STAX D
        JMP lyso
luqm:   LDAX D
        MOV M,A
lyso:   INX H
        INX D
        MOV A,L
        CPI 080h
        JC lcuq
        POP B
        POP D
        POP H
        RET
lmmi:   PUSH H
        PUSH D
        PUSH B
        MVI C,000h
        JMP levr
leie:   PUSH H
        PUSH B
        XRA A
        MOV D,A
        MOV E,A
        LXI H,lmji
        LDA lsol
        MOV L,A
        LDA lupm
        MOV C,A
lkyu:   LDA lwon
        CMP M
        CZ lgws
        MOV A,L
        CMP C
        JZ lixt
        LDA lctq
        ADD L
        MOV L,A
        JMP lkyu
lgws:   INR D
        RET
lixt:   LDA lctq
        MOV C,A
        LDA lsol
        SUB C
        CALL lmzv
        LDA lupm
        ADD C
        CALL lmzv
        POP B
        POP H
        RET
lmzv:   MOV L,A
        MOV A,M
        RZ
        INR E
        RET
lklh:   PUSH H
        PUSH D
        PUSH B
        LDA lctq
        MOV D,A
        LDA lunm
        MOV C,A
        LDA lsol
        SUB D
        CALL loaw
        LDA lupm
        ADD D
        CALL loaw
        XRA A
        STA lqbx
        STA lscy
        LDA lsol
        MOV E,A
lyfb:   CALL ludz
        LDA lupm
        CMP E
        JZ lwea
        MOV A,D
        ADD E
        MOV E,A
        JMP lyfb
lwea:   LDA lscy
        ORA A
        JNZ lahc
leje:   POP B
        POP D
        POP H
        RET
lahc:   LDA lqbx
        CALL loaw
loaw:   PUSH D
        CALL lcid
        POP D
        ORA A
        RZ
        MVI A,001h
        CALL lmxv
        CALL lafc
        POP H
        JMP leje
lcid:   MOV B,A
        XRA A
        PUSH D
        CALL lmxv
        POP D
        RET
ludz:   CALL lcid
        ORA A
        RZ
        MOV H,A
        LDA lscy
        CMP H
        RNC
        MOV A,H
        STA lscy
        MOV A,B
        STA lqbx
        RET

lgif:   DB 082h
lscy:   DB 082h
lqbx:   DB 082h
lehe:   DB 082h
lkkh:   DB 082h
lmli:   DB 082h
lomj:   DB 082h
lqnk:   DB 082h
lsol:   DB 082h
lupm:   DB 082h
lctq:   DB 082h
likg:   DB 082h
lgjf:   DB 082h
lijg:   DB 000h, 000h, 007h, 007h, 077h, 077h, 070h, 070h, 001h, 006h
        DB 010h, 060h, 071h, 076h, 017h, 067h, 022h, 025h, 035h, 045h
        DB 052h, 055h, 032h, 042h, 012h, 015h, 026h, 056h, 021h, 051h
        DB 062h, 065h, 011h, 011h, 016h, 016h, 066h, 066h, 061h, 061h
larp:   DB 030h, 032h, 000h
luaz:   MOV A,M
        ORA E
        MOV M,A
        DAD B
        DCR D
        JNZ luaz
        RET
lilg:   MVI C,0A6h
        RET
lihg:   PUSH B
        PUSH H
        CALL lgkf
        INX H
        MVI C,000h
        ORA A
        CZ lilg
        MOV A,C
        STA lkmh
        MOV A,H
        ADI 003h
        MOV C,A
lsql:   PUSH H
        MVI B,01Eh
looj:   LDAX D
lkmh:   ORA M
        MOV M,A
        INR L
        INX D
        DCR B
        JNZ looj
        POP H
        MOV A,H
        CMP C
        JZ lqpk
        INR H
        JMP lsql
lqpk:   POP H
        POP B
        RET
lgkf:   PUSH PSW
        LXI D,lurm
        DCR A
        CZ lwsn
        DCR A
        CZ lyto
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
lwsn:   LXI D,laup
        RET
lyto:   LXI D,lcvq
        RET
lkvu:   CALL lgkf
        PUSH H
        CALL lewr
        LXI D,lypo
        CALL lokj
        POP H
        CALL lewr
        RET
lewr:   MVI A,003h
        ADD H
        MOV D,A
liyt:   MVI C,020h
        PUSH H
lgxs:   MOV A,M
        CMA
        MOV M,A
        INR L
        DCR C
        JNZ lgxs
        POP H
        MOV A,H
        CMP D
        RZ
        INR H
        JMP liyt
lokj:   LXI H,00000h
lkzu:   INX H
        CALL lgrs
        JNZ lkzu
        RET
lybb:   PUSH B
        MVI A,091h
        STA lmav
        MVI A,0FBh
        STA lobw
        LDA lqcx
        ANI 03Fh
        MOV B,A
        LDA lsdy
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
lwca:   MVI B,000h
lknh:   CALL lybb
        CPI 004h
        JZ luez
        CPI 001h
        JZ ladc
        CPI 010h
        JZ lwfa
        CPI 040h
        JZ lygb
        CPI 080h
        JZ laic
        CPI 020h
        RZ
        JMP lcjd
lydb:   CALL leke
        JMP loyw
leke:   PUSH H
        LXI H,lglf
        JMP limg
lcjd:   PUSH B
        CALL lkvu
        LXI D,lypo
        CALL lokj
        POP B
        JMP lknh
lwfa:   MOV A,B
        ANI 0F0h
        JZ lcjd
        MOV A,B
        SUI 010h
        MOV B,A
        JMP lcjd
luez:   MOV A,B
        ANI 0F0h
        CPI 070h
        JZ lcjd
        MOV A,B
        ADI 010h
        MOV B,A
        JMP lcjd
lygb:   MOV A,B
        ANI 00Fh
        CPI 007h
        JZ lcjd
        INR B
        JMP lcjd
laic:   MOV A,B
        ANI 00Fh
        JZ lcjd
        DCR B
        JMP lcjd
livt:   LDA letr
        LXI H,lyqo
        SHLD luzz
        CALL lmoi
        LDA lgus
        LXI H,lcsq
        SHLD luzz
        CALL lmoi
        LXI H,letr
        XRA A
        MOV M,A
        INX H
        MOV M,A
        LXI H,lmji
        LXI D,lkjh
lusm:   MOV A,M
        PUSH H
        ORA A
        JZ lopj
        MOV H,A
        LDA lunm
        CMP H
        JZ lqqk
        JMP lsrl
lopj:   POP H
        INX H
        CALL lgrs
        JNZ lusm
        LDA letr
        LXI H,lyqo
        SHLD luzz
        CALL lmoi
        LDA lgus
        LXI H,lcsq
        SHLD luzz
        JMP lmoi
lsrl:   LXI H,letr
        JMP lwtn
lqqk:   LXI H,lgus
        JMP lwtn
lmoi:   PUSH PSW
        ANI 0F0h
        RRC
        RRC
        RRC
        RRC
        CALL lyuo
        POP PSW
        ANI 00Fh
lyuo:   ADI 030h
        MOV C,A
        JMP lccd
lwtn:   CALL lavp
        JMP lopj
lavp:   MOV A,M
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
lwpn:   LXI H,lmji
lexr:   MOV A,M
        ORA A
        JZ lcwq
        MOV B,L
        PUSH H
        CALL lihg
        POP H
lcwq:   INR L
        MOV A,L
        ANI 00Fh
        CPI 008h
        JC lexr
        MOV A,L
        ANI 0F0h
        ADI 010h
        MOV L,A
        CPI 080h
        JC lexr
        RET
lmxv:   STA lgys
        XRA A
        STA lizt
        LXI H,lmji
        MOV A,L
        ADD B
        MOV L,A
        MOV A,M
        ORA A
        JNZ lkau
        LXI D,lmbv
lele:   LDAX D
        ORA A
        JZ lkau
        STA lqdx
        PUSH H
        XRA A
        STA lsey
lajc:   LDA lqdx
        ADD L
        MOV L,A
        CALL lufz
        JNC lwga
        MOV A,M
        ORA A
        JZ lwga
        CMP C
        JZ lyhb
        LDA lsey
        INR A
        STA lsey
        JMP lajc
lyhb:   LXI H,lizt
        LDA lsey
        ADD M
        MOV M,A
        POP H
        LDA lgys
        ORA A
        CNZ lckd
        INX D
        JMP lele
lwga:   POP H
        INX D
        JMP lele
lufz:   CPI 080h
        RNC
        ANI 00Fh
        CPI 008h
        RET
lckd:   LDA lsey
        ORA A
        RZ
        PUSH H
        PUSH D
        MOV D,A
lgmf:   LDA lqdx
        ADD L
        MOV L,A
        MOV M,C
        DCR D
        JNZ lgmf
        POP D
        POP H
        RET
lkau:   LDA lizt
        RET

lmbv:   DB 001h, 0FFh, 010h, 0F0h, 00Fh, 011h, 0F1h, 0EFh, 000h
lgys:   DB 082h
lizt:   DB 082h
lqdx:   DB 082h
lsey:   DB 082h
lafc:   LXI H,lmji
        MOV A,B
        ADD L
        MOV L,A
        MOV M,C
        RET
lcgd:   PUSH B
        MOV A,C
        CALL lihg
        POP B
        CALL ling
        LXI D,lmji
        CALL lokj
        RET
ling:   PUSH H
        LXI H,lkoh
        LDA lunm
        CMP C
        JZ limg
        LXI H,lmpi
        JMP limg
limg:   MOV A,M
        ORA A
        JZ loqj
        STA lqrk
lwun:   INX H
        MOV A,M
        ORA A
        JZ loqj
        STA lssl
        CALL lutm
        JMP lwun
loqj:   POP H
        RET
lcxq:   PUSH H
        LXI H,lyvo
        JMP limg
        DB 0E5h, 021h, 055h, 00Bh, 0C3h, 030h, 00Ah
liat:   MOV B,L
lawp:   MOV M,C
        INR L
        MOV A,L
        CMP E
        JNZ lawp
        INR H
        MOV L,B
        MOV A,H
        CMP D
        JNZ lawp
        RET
lsay:   CALL lcxq
        LXI H,leyr
        LXI D,lgzs
        MVI C,0FFh
        CALL liat
        LXI H,lkbu
        LXI D,lmcv
        MVI C,000h
        CALL liat
        LXI H,lodw
        SHLD luzz
        LDA letr
        MOV C,A
        LDA lgus
        SUB C
        LXI H,lqex
        JZ lsfy
        JC lugz
lakc:   CALL lwaa
        JMP lwha
lsfy:   LXI H,lyib
        JMP lakc
lugz:   LXI H,lcld
        JMP lakc
lwha:   LXI H,leme
        SHLD luzz
        LXI H,lgnf
        CALL lwaa
        LXI H,liog
        SHLD luzz
        LXI H,lkph
        CALL lwaa
lmqi:   CALL lybb
        ORA A
        JZ lmqi
        JMP lgef
lkih:   PUSH B
        PUSH H
        CALL lgkf
        INX H
        MVI C,01Eh
lorj:   MVI A,07Fh
        ANA M
        MOV M,A
        INX H
        DCR C
        JNZ lorj
        POP H
        POP B
        RET

lyib:   DB 020h, 020h, 020h, 020h, 020h, 06Eh, 069h, 07Eh, 078h, 071h
        DB 020h, 021h, 000h
lqex:   DB 070h, 06Fh, 07Ah, 064h, 072h, 061h, 077h, 06Ch, 071h, 060h
        DB 020h, 073h, 020h, 070h, 06Fh, 062h, 065h, 064h, 06Fh, 06Ah
        DB 020h, 021h, 000h
lcld:   DB 020h, 020h, 020h, 020h, 077h, 079h, 020h, 070h, 072h, 06Fh
        DB 069h, 067h, 072h, 061h, 06Ch, 069h, 020h, 021h, 000h
lgnf:   DB 064h, 06Ch, 071h, 020h, 070h, 072h, 06Fh, 064h, 06Fh, 06Ch
        DB 076h, 065h, 06Eh, 069h, 071h, 020h, 069h, 067h, 072h, 079h
        DB 000h
lkph:   DB 020h, 020h, 06Eh, 061h, 076h, 06Dh, 069h, 074h, 065h, 020h
        DB 022h, 070h, 072h, 06Fh, 062h, 065h, 06Ch, 022h, 000h
lkoh:   DB 028h, 083h, 075h, 067h, 061h, 056h, 000h
lmpi:   DB 028h, 056h, 061h, 067h, 075h, 083h, 000h
lglf:   DB 03Ch, 075h, 083h, 094h, 09Dh, 0B0h, 0C8h, 0DFh, 000h
lyvo:   DB 050h, 0FBh, 0C8h, 0A6h, 07Ch, 061h, 051h, 03Ch, 03Ch, 03Ch
        DB 03Ch, 061h, 051h, 03Ch, 051h, 061h, 051h, 0A6h, 0A6h, 0A6h
        DB 000h
letr:   DB 082h
lgus:   DB 082h
lege:   DB 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h, 074h, 065h, 072h
        DB 020h, 020h, 000h
liig:   DB 069h, 067h, 072h, 06Fh, 06Bh, 000h
lggf:   DB 077h, 079h, 062h, 065h, 072h, 069h, 074h, 065h, 020h, 063h
        DB 077h, 065h, 074h, 03Ah, 000h
lcrq:   DB 075h, 072h, 06Fh, 077h, 065h, 06Eh, 078h, 03Ah, 000h
lunm:   DB 082h
lwon:   DB 082h
lszy:   DB 082h
laup:   DB 080h, 080h, 080h, 080h, 081h, 083h, 086h, 08Eh, 08Ch, 098h
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
lurm:   DB 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h, 080h
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
lcvq:   DB 080h, 080h, 080h, 080h, 081h, 083h, 087h, 08Fh, 08Fh, 09Fh
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