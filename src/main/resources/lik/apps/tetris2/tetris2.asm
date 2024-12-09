        CPU  8080
        .ORG 00000h
leie    EQU 0FF00h
lyso    EQU 0B000h
lklh    EQU 0FF01h
lyeb    EQU 0FF02h
lsby    EQU 0FF03h
lubz    EQU 08008h
letr    EQU 02313h
lcsq    EQU 0231Bh
lqwx    EQU 01323h
labc    EQU 0C037h
lgvs    EQU 0C337h
loij    EQU 0C438h
lmki    EQU 0A338h
lqmk    EQU 0AC38h
lmli    EQU 0A438h
lgus    EQU 0C170h
lgef    EQU 02377h
lkvu    EQU 0A0A8h
loxw    EQU 012B6h
lomj    EQU 022CDh
lkkh    EQU 08FF1h
lyqo    EQU 08FF2h
luqm    EQU 08FF4h
lwca    EQU 08FFCh
        MVI C,01Fh
        CALL labc
        LXI H,lccd
        LXI D,lede
        MVI C,018h
lifg:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lifg
        CALL lkgh
lgss:   MVI C,00Ch
        CALL labc
        LXI H,lmhi
        CALL loij
        CALL lqjk
        ANI 00Fh
        RLC
        RLC
        RLC
        RLC
        STA lskl
        CALL lulm
        LXI H,lwmn
        MVI C,050h
lyno:   MVI M,000h
        INX H
        DCR C
        JNZ lyno
        MVI A,0FFh
        STA laop
        CALL lcpq
        CALL leqr
lqxx:   CALL lgrs
        CALL list
        LXI H,lktu
        LXI D,lmuv
        JMP lovw

lktu:   DB 004h, 000h, 000h, 004h, 000h, 000h, 000h, 001h, 000h, 000h
lovw:   MVI C,00Ah
lsxy:   MOV A,M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lsxy
lerr:   CALL luyz
        CALL lwza
        CALL lyab
        CALL lacc
        LDA lcdd
        CPI 000h
        JNZ leee
        MVI A,000h
        STA lgff
        STA ligg
        CALL lkhh
        CALL lmii
        CALL lkgh
        LDA lojj
        CPI 000h
        CZ lqkk
        LDA lsll
        STA lmuv
        LDA lumm
        STA lwnn
        LDA lyoo
        STA lapp
        CALL lcqq
        JMP lerr
leee:   LDA lgff
        CPI 001h
        JZ lgss
        LDA lumm
        MOV B,A
        LDA lwnn
        CMP B
        JZ litt
lsyy:   LDA lwnn
        STA lumm
        CALL luyz
        CALL lwza
        CALL lyab
        CALL lkhh
        CALL lkuu
        CALL lmii
        CALL lmvv
        CALL loww
        JMP lqxx
litt:   LDA ligg
        CPI 001h
        JZ lsyy
        LDA lmuv
        STA lsll
        LDA lapp
        STA lyoo
        MVI A,001h
        STA ligg
        LDA lwnn
        INR A
        STA lumm
        JMP lerr
liwt:   LDA lskl
        MOV B,A
lwaa:   MVI C,0FFh
luzz:   DCR C
        JNZ luzz
        DCR B
        JNZ lwaa
        RET
lmii:   LXI H,lccd
        LXI D,lybb
        CALL ladc
        RET
lkuu:   LXI H,lced
        LXI D,lybb
        CALL ladc
        RET
loww:   LXI H,lced
        LXI D,lccd
        CALL ladc
        RET
ladc:   MVI C,018h
lefe:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lefe
        RET
luyz:   CALL lggf
        LXI D,lihg
        MVI C,004h
lkih:   MOV A,M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lkih
        RET
lggf:   LXI H,lmji
        LDA lokj
        MVI D,000h
        MOV E,A
        MVI C,010h
lqlk:   DAD D
        DCR C
        JNZ lqlk
        LDA lyoo
        MOV E,A
        MVI C,004h
lsml:   DAD D
        DCR C
        JNZ lsml
        RET
lwza:   LXI H,lcdd
        MVI M,000h
        LXI D,lihg
        LDA lsll
        CPI 004h
        RZ
        JC lunm
        SUI 004h
        MOV B,A
laqp:   MVI C,004h
        PUSH D
        POP H
lypo:   MOV A,M
        RRC
        JC lwon
        MOV M,A
        INX H
        DCR C
        JNZ lypo
        DCR B
        JNZ laqp
        RET
lunm:   MOV B,A
        MVI A,004h
        SUB B
        MOV B,A
lesr:   MVI C,004h
        PUSH D
        POP H
lcrq:   MOV A,M
        RLC
        JC lwon
        MOV M,A
        INX H
        DCR C
        JNZ lcrq
        DCR B
        JNZ lesr
        RET
lwon:   LXI H,lcdd
        MVI M,001h
        RET
lacc:   LDA lgts
        CPI 000h
        JNZ liut
        LXI H,lced
        LXI D,lybb
        MVI C,018h
lmwv:   MOV B,M
        LDAX D
        XRA B
        ANA B
        CMP B
        JNZ liut
        INX H
        INX D
        DCR C
        JNZ lmwv
        RET
liut:   MVI A,001h
        STA lcdd
        RET
lkhh:   LXI H,lced
        LXI D,lybb
        MVI C,018h
lqyx:   LDAX D
        ORA M
        STAX D
        INX H
        INX D
        DCR C
        JNZ lqyx
        RET
        DB 021h, 076h, 004h, 011h, 0B6h, 004h, 00Eh, 018h, 01Ah, 077h
        DB 023h, 013h, 00Dh, 0C2h, 0F3h, 001h, 0C9h
lyab:   LXI H,lybb
        MVI C,019h
        PUSH H
lszy:   MVI M,000h
        INX H
        DCR C
        JNZ lszy
        POP H
        LDA lumm
        MOV E,A
        MVI D,000h
        DAD D
        LXI D,lihg
        MVI C,004h
luaz:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ luaz
        RET
lcqq:   PUSH B
        PUSH PSW
        CALL lwba
        CPI 008h
        JZ lycb
        CPI 018h
        JZ laec
        CPI 019h
        JZ lcfd
        CPI 01Ah
        JZ lege
        LDA lwnn
        INR A
        STA lumm
        JMP lghf
lycb:   LDA lmuv
        DCR A
        STA lsll
        JMP lghf
laec:   LDA lmuv
        INR A
        STA lsll
        JMP lghf
lcfd:   LDA lapp
        INR A
        CPI 004h
        JNZ liig
        MVI A,000h
liig:   STA lyoo
        JMP lghf
lege:   MVI A,001h
        STA lojj
        LDA lwnn
        INR A
        STA lumm
        JMP lkjh
lghf:   XRA A
        STA lojj
lkjh:   POP PSW
        POP B
        RET

lmji:   DB 010h, 010h, 010h, 010h, 000h, 078h, 000h, 000h, 010h, 010h
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
lwpn:   DB 020h, 080h, 02Bh, 060h, 033h, 050h, 030h, 055h, 02Bh, 060h
        DB 033h, 050h, 02Bh, 060h, 030h, 055h, 02Bh, 060h, 022h, 078h
        DB 026h, 06Bh, 0ABh, 080h, 020h, 080h, 02Bh, 060h, 033h, 050h
        DB 030h, 055h, 02Bh, 060h, 033h, 050h, 02Bh, 060h, 030h, 055h
        DB 02Bh, 060h, 020h, 080h, 01Eh, 087h, 098h, 08Fh, 01Dh, 08Fh
        DB 022h, 078h, 028h, 065h, 0FFh, 055h, 01Dh, 08Fh, 022h, 078h
        DB 028h, 065h, 0E4h, 060h, 020h, 080h, 026h, 06Bh, 022h, 078h
        DB 022h, 078h, 020h, 080h, 026h, 06Bh, 022h, 078h, 022h, 078h
        DB 020h, 07Fh, 010h, 0FFh, 014h, 0CAh, 055h, 0BFh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h
lede:   DB 000h, 070h, 020h, 02Eh, 028h, 00Ch, 0E8h, 04Eh, 040h, 04Eh
        DB 00Ah, 00Eh, 00Ch, 04Ah, 000h, 04Eh, 048h, 04Eh, 042h, 00Eh
        DB 000h, 055h, 0AAh
lcpq:   PUSH B
        PUSH H
        PUSH PSW
        MVI A,033h
        LXI H,lmki
        MVI C,0C8h
        CALL lolj
        LXI H,lqmk
        MVI C,0C8h
        CALL lolj
        MVI H,0A4h
        CALL lsnl
        POP PSW
        POP H
        POP B
        RET
lsnl:   MVI B,008h
luom:   MVI C,008h
        MVI L,0F8h
        CALL lolj
        INR H
        DCR B
        JNZ luom
        RET
lolj:   MOV M,A
        RLC
        INR L
        DCR C
        JNZ lolj
        RET
lulm:   PUSH H
        PUSH D
        PUSH PSW
        LXI H,lwpn
        LXI D,lyqo
lkwu:   MOV A,M
        CPI 000h
        JZ larp
        STAX D
        DCX D
        INX H
        MOV A,M
        STAX D
        INX D
        INX H
        CALL lgus
        CALL livt
        JMP lkwu
larp:   POP PSW
        POP D
        POP H
        RET
livt:   PUSH B
        MVI B,025h
loyw:   MVI C,0FFh
lmxv:   DCR C
        JNZ lmxv
        DCR B
        JNZ loyw
        POP B
        RET
leqr:   PUSH H
        LXI H,00000h
        SHLD lqzx
        POP H
        RET
latp:   PUSH B
        PUSH PSW
        PUSH H
        LHLD lqzx
        MOV A,L
        MVI B,001h
        ADD B
        DAA
        MOV L,A
        JNC lsay
        MOV A,H
        MVI B,001h
        ADD B
        DAA
        MOV H,A
lsay:   SHLD lqzx
        LXI H,lubz
        SHLD lwca
        LHLD lqzx
        MOV A,H
        CALL lydb
        MOV A,L
        CALL lydb
        POP H
        POP PSW
        POP B
        RET

lqzx:   DB 023h, 000h
lydb:   PUSH PSW
        ANI 0F0h
        RRC
        RRC
        RRC
        RRC
        CALL lafc
        POP PSW
        ANI 00Fh
        CALL lafc
        RET
lafc:   ORI 030h
        MOV C,A
        CALL labc
        RET
lmvv:   LXI H,lcgd
        MVI C,018h
lgif:   MOV A,M
        CPI 0FFh
        JZ lehe
        DCX H
        DCR C
        JNZ lgif
        CALL lkgh
        RET
lehe:   DCX H
        MOV A,M
        INX H
        MOV M,A
        DCX H
        DCR C
        JNZ lehe
        CALL lkgh
        CALL lijg
        JMP lmvv
lijg:   MVI A,080h
        STA lkkh
        MVI A,010h
        STA lyqo
        CALL lgus
        RET

lskl:   DB 010h
lmhi:   DB 020h, 020h, 073h, 06Bh, 06Fh, 072h, 06Fh, 073h, 074h, 078h
        DB 020h, 028h, 030h, 02Dh, 039h, 029h, 020h, 03Fh, 000h
lmuv:   DB 004h
lwnn:   DB 000h
lapp:   DB 000h
lsll:   DB 004h
lumm:   DB 000h
lyoo:   DB 000h
lojj:   DB 000h
lgff:   DB 001h, 000h
lcdd:   DB 001h
ligg:   DB 000h, 0D1h, 0E1h, 0C9h, 0E5h, 0C5h, 00Eh, 008h
lwmn:   DB 000h
lccd:   DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
        DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
        DB 0FBh, 0E6h, 0ECh
lcgd:   DB 03Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lced:   DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
        DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
        DB 0FBh, 0E6h, 0ECh, 03Fh
laop:   DB 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lybb:   DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lgts:   DB 000h, 000h, 000h, 072h, 06Fh, 000h, 000h, 000h
lihg:   DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
lkgh:   PUSH H
        PUSH D
        PUSH B
        PUSH PSW
        LXI H,lmli
        LXI D,lccd
        MVI C,018h
lsol:   LDAX D
        CALL lqnk
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
        JNZ lsol
        POP PSW
        POP B
        POP D
        POP H
        RET
lqnk:   PUSH H
        PUSH B
        MVI C,008h
lasp:   RLC
        JC lupm
        CALL lwqn
leur:   INR H
        DCR C
        JZ lyro
        JMP lasp
lupm:   CALL lctq
        JMP leur
lyro:   POP B
        POP H
        RET
lctq:   PUSH H
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
lwqn:   PUSH H
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
lqjk:   CALL lgvs
        CPI 030h
        JC lqjk
        CPI 03Ah
        JNC lqjk
        INR A
        MVI C,01Fh
        CALL labc
        RET
lqkk:   LDA lumm
        ADI 004h
        RAL
        RAL
        STA lkkh
        MVI A,008h
        STA lyqo
        CALL lgus
        CALL liwt
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
lgrs:   CALL lkxu
        LDA lmyv
        ANI 007h
        CPI 007h
        JZ lgrs
        STA lokj
        RET
lkxu:   LHLD lmyv
        MVI C,010h
lqax:   MOV A,H
        DAD H
        ANI 060h
        JPE lozw
        INX H
lozw:   DCR C
        JNZ lqax
        SHLD lmyv
        RET

lmyv:   DB 061h, 08Bh
lokj:   DB 001h
likg:   MVI A,082h
lucz:   STA lsby
        RET
lwda:   MVI A,091h
        JMP lucz
lwba:   PUSH B
        PUSH D
        PUSH H
        LXI H,006A7h
        LXI D,0000Ch
        CALL lwda
        MVI C,001h
        LDA lyeb
        RLC
        RLC
        RLC
        RLC
        MVI B,004h
lchd:   INX H
        RLC
        JNC lagc
        DCR B
        JNZ lchd
        LDA leie
        MVI B,008h
        DCR C
        JZ lchd
        JMP lgjf
lagc:   CALL likg
        LDA lklh
        RLC
        MVI B,005h
lonj:   DAD D
        RLC
        JNC lmmi
        DCR B
        JNZ lonj
lgjf:   CALL likg
        MVI A,0FFh
        JMP lqok
lmmi:   LDA lklh
        ANI 002h
        RRC
        MOV E,A
        MOV A,M
        CPI 021h
        JC lqok
        CPI 080h
        JNC lqok
        DCR E
        JNZ lspl
        CPI 040h
        JC lqok
        LDA luqm
        ADD M
        JMP lqok
lspl:   CPI 040h
        JC lwrn
        ANI 01Fh
        JMP lqok
lwrn:   XRI 010h
lqok:   POP H
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
list:   LXI H,lyso
        XRA A
        CALL lqnk
        JMP latp
        DB 0E6h, 0D0h, 000h, 000h, 000h, 000h
END