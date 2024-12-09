        CPU  8080
        .ORG 00000h
lwmn    EQU 08000h
lkih    EQU 09000h
lyro    EQU 08400h
lasp    EQU 0D800h
lyeb    EQU 0C000h
lyso    EQU 08001h
lgrs    EQU 08002h
lijg    EQU 0F802h
leqr    EQU 08003h
larp    EQU 08005h
lwaa    EQU 08007h
liut    EQU 08008h
lwun    EQU 0BE09h
lccd    EQU 0C010h
laec    EQU 08010h
lced    EQU 0A818h
lwon    EQU 08018h
lszy    EQU 08020h
lizt    EQU 0AA20h
lkzu    EQU 0A421h
lyib    EQU 0AA21h
lknh    EQU 0B222h
lkau    EQU 0AA22h
lyvo    EQU 0BC22h
lckd    EQU 09A28h
lsll    EQU 09030h
lycb    EQU 08030h
luez    EQU 09A35h
lkuu    EQU 0C037h
lmhi    EQU 0C438h
lapp    EQU 01840h
lcfd    EQU 08040h
locw    EQU 0A840h
lomj    EQU 08041h
lopj    EQU 0B444h
liyt    EQU 0984Ah
limg    EQU 0B250h
labc    EQU 08051h
lsxy    EQU 08052h
lajc    EQU 0B252h
lklh    EQU 03060h
lsey    EQU 0A260h
lawp    EQU 0B863h
lqex    EQU 09C63h
lumm    EQU 03070h
lwfa    EQU 09270h
lyhb    EQU 0A671h
lwga    EQU 0A479h
lcgd    EQU 02080h
lygb    EQU 09481h
lcwq    EQU 0A290h
lkbu    EQU 09890h
lwha    EQU 0A291h
lexr    EQU 0A292h
lqrk    EQU 0AE97h
lmmi    EQU 0909Fh
lyoo    EQU 0A6A0h
lcjd    EQU 098A8h
lakc    EQU 098A9h
leke    EQU 098AAh
lgmf    EQU 0AAAAh
lkoh    EQU 0A0D1h
lovw    EQU 044DBh
lmpi    EQU 0A2E0h
lwza    EQU 044E6h
lyuo    EQU 0A6EBh
lusm    EQU 0AEEEh
lwtn    EQU 0A4FEh
lmqi    EQU 0FF00h
lstl    EQU 0FF02h
lcld    EQU 05703h
leme    EQU 07703h
liog    EQU 0A703h
lkph    EQU 0FF03h
lgzs    EQU 09F05h
lqqk    EQU 0B509h
liat    EQU 09B12h
lmoi    EQU 0B320h
laic    EQU 0AB25h
lele    EQU 09925h
lmav    EQU 0A727h
ling    EQU 09727h
leyr    EQU 09929h
lede    EQU 02530h
lsdy    EQU 09B30h
lssl    EQU 0BB33h
leee    EQU 0C337h
lglf    EQU 0AF40h
lutm    EQU 0BB42h
lqjk    EQU 09D48h
lcvq    EQU 09D50h
lugz    EQU 09D51h
lgys    EQU 09D52h
lufz    EQU 0A356h
lcxq    EQU 0B761h
lodw    EQU 09B61h
lobw    EQU 0AB66h
lwca    EQU 0C170h
lqcx    EQU 0A970h
lqdx    EQU 0A177h
lmcv    EQU 09982h
lsfy    EQU 09B84h
lmbv    EQU 0A985h
lsrl    EQU 0B590h
lqxx    EQU 049B4h
lewr    EQU 091C0h
lqlk    EQU 0B5CDh
loqj    EQU 0A3E3h
lggf    EQU 0A7E8h
leie    EQU 0FFF0h
lgxs    EQU 093F0h
lavp    EQU 099F0h
lubz    EQU 08FF1h
lqax    EQU 0FFF8h
lgif    EQU 08FFAh
lgef    EQU 08FFCh
lehe    EQU 0FFFFh
        LXI H,00000h
        SHLD labc
lqnk:   CALL lccd
        LXI H,lede
        SHLD lgef
        CALL lifg
        LXI H,lkgh
        CALL lmhi
        CALL loij
        LXI H,lqjk
        LXI B,lskl
        CALL lulm
        CALL lifg
        LXI H,lwmn
        MVI A,04Fh
lyno:   MVI M,000h
        INX H
        DCR A
        JNZ lyno
        LXI H,laop
        MVI A,020h
lcpq:   MVI M,000h
        INX H
        DCR A
        JNZ lcpq
        SHLD leqr
        MVI A,097h
        STA lgrs
        LXI D,list
        LXI H,lktu
        SHLD lgef
        MOV B,H
        XCHG
lmuv:   XCHG
        LHLD lgef
        MOV H,B
        MOV A,L
        ADI 00Ah
        MOV L,A
        SHLD lgef
        XCHG
        CALL lmhi
        INX H
        MOV A,M
        ANA A
        JNZ lmuv
        LHLD labc
        MOV A,L
        MOV D,H
        LXI H,lovw
        SHLD lgef
        CMP D
        JC lqwx
        MOV D,A
        STA lsxy
lqwx:   CALL luyz
        MOV A,D
        LXI H,lwza
        SHLD lgef
        CALL luyz
lmii:   LHLD lyab
        CALL lacc
        LXI B,0051Fh
        CALL lcdd
        CALL lifg
        CALL leee
        CALL lgff
        CPI 002h
        JZ ligg
        CPI 003h
        JZ lkhh
        CPI 006h
        JNZ lmii
        MOV A,L
        CPI 0BEh
        RNC
        CPI 0B4h
        JNC lojj
        CPI 0AAh
        JNC lqkk
        LXI H,lsll
        LXI D,lumm
        MVI C,000h
        CALL lwnn
        LXI H,lyoo
        LXI D,lapp
        CALL lwnn
        LXI H,lcqq
        SHLD lgef
        LXI H,lerr
        CALL lmhi
        JMP lmii
lcdd:   MOV M,C
        DCX H
        DCR B
        JNZ lcdd
        RET
ligg:   LDA lyab
        CPI 0AAh
        JC lmii
        MVI A,0F6h
        JMP lgss
lkhh:   LDA lyab
        CPI 0BEh
        JNC lmii
        MVI A,00Ah
lgss:   LHLD lyab
        ADD L
        STA lyab
        LXI B,00500h
        CALL lcdd
        JMP lmii
lwnn:   MOV B,E
litt:   MOV M,C
        INX H
        DCR B
        JNZ litt
        MOV A,L
        SUB E
        MOV L,A
        INR H
        DCR D
        JNZ lwnn
        RET
lsyy:   PUSH B
        MVI C,07Fh
        CALL lkuu
        MVI C,008h
        CALL lkuu
        POP B
        JMP lkuu
lqkk:   LDA lmvv
        INR A
        CPI 034h
        JC loww
        MVI A,031h
loww:   STA lmvv
        MOV C,A
        LXI H,lqxx
        SHLD lgef
        CALL lsyy
        JMP lmii
lojj:   CALL lccd
        LDA lmvv
        SUI 02Fh
        MOV C,A
        MVI A,0FFh
luzz:   ANA A
        RAR
        DCR C
        JNZ luzz
        STA lwaa
        CALL lybb
        CALL ladc
        LXI H,lced
        SHLD lgef
        LXI H,lefe
        CALL lmhi
        LXI H,lggf
        LXI B,lihg
        CALL lulm
        LXI H,lkih
        LXI B,lmji
        CALL lokj
        CALL lulm
        LXI H,lqlk
        LXI B,lsml
        CALL lifg
        CALL lulm
        CALL lunm
        XRA A
lixt:   STA lwon
        LXI H,lwaa
        MOV C,M
        ANA C
        JZ lypo
        ANI 001h
        JZ laqp
        JMP lcrq
lypo:   CALL lesr
        MOV A,L
        ANI 0ABh
        JNZ lgts
        MVI B,007h
        LXI H,liut
loxw:   CALL lkvu
        JZ lmwv
        DCR B
        JNZ loxw
        JMP laqp

lyab:   DB 0A8h, 092h
list:   DB 069h, 06Eh, 073h, 074h, 072h, 075h, 06Bh, 063h, 069h, 071h
        DB 000h, 075h, 072h, 06Fh, 077h, 065h, 06Eh, 078h, 020h, 073h
        DB 06Ch, 06Fh, 076h, 06Eh, 06Fh, 073h, 074h, 069h, 020h, 020h
lmvv:   DB 032h, 000h, 069h, 067h, 072h, 061h, 000h, 077h, 079h, 068h
        DB 06Fh, 064h, 000h, 020h, 000h, 075h, 06Eh, 069h, 07Eh, 074h
        DB 06Fh, 076h, 065h, 06Eh, 06Fh, 020h, 063h, 065h, 06Ch, 065h
        DB 06Ah, 000h, 06Ch, 075h, 07Eh, 07Bh, 069h, 06Ah, 020h, 072h
        DB 065h, 07Ah, 075h, 06Ch, 078h, 074h, 061h, 074h, 000h, 000h
lmwv:   INR A
        CALL lqyx
        CALL lesr
        XCHG
        LXI H,lszy
        CALL luaz
lwba:   CALL lesr
        MOV A,H
        ANI 07Eh
        CPI 011h
        JC lwba
        MOV D,A
        MOV E,L
        LXI H,lycb
        CALL luaz
        MVI A,080h
        LXI H,laec
        CALL lqyx
        CALL lybb
        LXI H,lcfd
        INR M
        CALL lybb
lgts:   CALL lunm
        MVI B,001h
lsol:   LXI H,liut
        CALL lkvu
        JZ lege
        LXI H,lszy
        CALL lghf
        PUSH D
        LXI H,lycb
        CALL lghf
        XCHG
        POP D
        CALL liig
        DCR L
        DCR L
        MVI C,003h
lmki:   MVI E,004h
lkjh:   MVI M,000h
        INR L
        DCR E
        JNZ lkjh
        LXI D,000FCh
        DAD D
        DCR C
        JNZ lmki
        CALL lesr
        MOV A,H
        MVI E,004h
        CALL lolj
        ANA A
        RAR
        JC lqmk
        CMA
lqmk:   MOV C,A
lwpn:   LXI H,laec
        CALL lkvu
        ADD C
        CALL lqyx
        CALL lsnl
        MOV A,D
        ANA A
        JNZ luom
lyqo:   MVI C,080h
        JMP lwpn
luom:   MOV A,H
        CPI 010h
        JC lyqo
        CPI 080h
        JNC lyqo
        CALL liig
        DCR L
        DCR L
        SHLD larp
        LXI H,lwon
        CALL lkvu
        PUSH H
        PUSH PSW
        MVI E,005h
        CALL lolj
        LXI H,lcsq
        LXI D,00008h
        CALL letr
        POP PSW
        POP H
        INR A
        CALL lqyx
        ANA A
        JNZ lege
        PUSH B
        LXI H,lszy
        CALL lghf
        PUSH D
        LXI H,lycb
        CALL lghf
        INR D
        CALL luaz
        POP H
        XRA A
        STA lwmn
        MOV B,A
        MOV C,A
        MVI A,088h
        SUB H
        JNC lgus
        STA lwmn
        CMA
        INR A
lgus:   MOV H,A
        MVI A,0E0h
        SUB D
        CMA
        INR A
        MVI D,0FFh
        MOV E,A
livt:   DAD D
        INX B
        MOV A,H
        ANA A
        JNZ livt
        MOV A,B
        ANA A
        JZ lkwu
        MVI C,0FFh
lkwu:   MOV A,C
        ANA A
        RAR
        ANA A
        RAR
        ANA A
        RAR
        LXI H,lwmn
        INR M
        DCR M
        JNZ lmxv
        CMA
        INR A
lmxv:   ADI 080h
        POP B
        PUSH PSW
lsay:   POP PSW
        PUSH PSW
        CALL lsnl
        CALL liig
        INR H
        CALL lacc
        MVI A,080h
        JZ loyw
lqzx:   RRC
        DCR C
        JNZ lqzx
loyw:   MOV M,A
        MOV A,L
        ANA A
        JNZ lsay
        POP PSW
        LXI H,00101h
lydb:   SHLD lubz
        CALL lwca
        INR H
        INR L
        INR L
        MOV A,L
        CPI 03Ch
        JC lydb
lafc:   SHLD lubz
        CALL lwca
        DCR H
        DCR L
        DCR L
        JP lafc
        LXI H,lcgd
        SHLD lubz
        CALL loij
        LXI H,lehe
        SHLD lgif
        CALL lccd
        CALL lifg
        LXI H,lijg
        LXI D,00020h
        MVI B,032h
lmli:   MOV M,E
        CALL lkkh
        MVI M,000h
        MOV A,B
        CALL lkkh
        INR B
        MOV A,B
        CPI 0C8h
        JNZ lmli
        XRA A
        MOV H,A
        MOV L,A
        SHLD lgif
        LDA lomj
        STA labc
        JMP lqnk
        DB 0AFh, 057h, 05Fh, 01Ah, 067h, 013h, 01Ah, 06Fh, 013h, 022h
        DB 003h, 080h, 0CDh, 032h, 007h, 0C0h, 07Ah, 0FEh, 009h, 0C2h
        DB 09Dh, 003h, 0C3h, 09Ah, 003h
lege:   INR B
        MOV A,B
        CPI 008h
        JNZ lsol
        MVI B,000h
laqp:   CALL lgff
        PUSH PSW
        LDA liut
        ANA A
        JNZ lupm
        POP PSW
        CPI 006h
        JNZ lwqn
        LXI H,liut
        INR M
        LXI H,lyro
        SHLD lszy
        LXI H,lasp
        SHLD lycb
        XRA A
        STA laec
        JMP lctq
lupm:   LDA lwon
        ANI 003h
        JZ leur
        POP H
        PUSH PSW
leur:   POP PSW
        LXI H,laec
        CPI 009h
        JZ lgvs
        CPI 007h
        JNZ liwt
        DCR M
liwt:   DCR M
lgvs:   INR M
        LDA laec
lctq:   CALL lsnl
        PUSH H
        PUSH D
        MVI B,007h
lspl:   LXI H,liut
        CALL lkvu
        JZ lkxu
        POP H
        PUSH H
        MOV A,H
        LXI H,lszy
        CALL lghf
        SUB D
        JP lmyv
        CMA
        INR A
lmyv:   CPI 003h
        JNC lkxu
        POP D
        POP H
        PUSH H
        PUSH D
        MOV A,H
        LXI H,lycb
        CALL lghf
        SUB D
        JP lozw
        CMA
        INR A
lozw:   CPI 003h
        JNC lkxu
        POP D
        POP H
        CALL liig
        XRA A
        LXI D,lqax
lgjf:   DAD D
        SHLD larp
        PUSH PSW
        CALL lacc
        POP PSW
        PUSH PSW
        LXI D,00020h
        LXI H,lsby
        CALL letr
        CALL lifg
        POP PSW
        PUSH PSW
        PUSH B
        ANA A
        JNZ lucz
        MVI B,014h
lwda:   DCX B
        MOV A,B
        ANA A
        JNZ lwda
lucz:   ADI 003h
        RLC
        RLC
        LXI H,lyeb
        MVI E,000h
        MOV B,A
lchd:   MOV C,B
        MOV A,M
        ANI 020h
lagc:   STA lijg
        DCR C
        JNZ lagc
        INX H
        DCR E
        JNZ lchd
        POP B
        LHLD larp
        LXI D,leie
        POP PSW
        INR A
        CPI 007h
        JC lgjf
        XRA A
        STA lwmn
        LXI H,liut
        MOV M,A
        CALL lqyx
        LXI H,lwon
        CALL lqyx
        CALL lybb
        CALL ladc
        LXI H,lomj
        MOV A,M
        INR A
        DAA
        MOV M,A
        DCX H
        DCR M
        CPI 080h
        JNC likg
        CALL lybb
        CALL ladc
        JMP lcrq
likg:   MVI C,033h
        CALL lccd
        CALL lokj
        LXI H,lkih
        LXI D,lklh
        PUSH D
        CALL lwnn
        POP D
        LXI H,lmmi
        CALL lwnn
        CALL lifg
        LXI H,lonj
        SHLD lgef
        LXI H,lqok
        CALL lmhi
        CALL leee
        JMP lqnk
lkxu:   DCR B
        JNZ lspl
        POP D
        POP H
        MOV A,H
        ANA A
        JZ luqm
        MOV A,D
        ANA A
        JZ luqm
        INR A
        JNZ lwrn
luqm:   STA liut
        JMP lcrq
lwrn:   PUSH H
        LHLD lyso
        LDA lwmn
        MOV M,A
        POP H
        CALL liig
        INR H
        MVI A,080h
        JZ latp
lcuq:   RRC
        DCR C
        JNZ lcuq
latp:   MOV C,M
        MOV M,A
        MOV A,C
        STA lwmn
        SHLD lyso
lwqn:   ANA A
levr:   DCR A
        JNZ levr
lcrq:   LDA lomj
        RLC
        CMA
lgws:   DCR A
        JNZ lgws
        LDA lwon
        INR A
        JMP lixt
lsnl:   LXI H,lszy
        CALL lghf
        PUSH D
        LXI H,lycb
        CALL lghf
        MOV H,B
        POP B
        ANA A
        RAL
        JNC lkyu
        DCR B
        ANA A
        RAL
        JNC lmzv
        DCR D
        ANA A
        RAL
        JNC loaw
ludz:   ADD C
        JNC lqbx
        INR B
lqbx:   MOV C,A
        JMP lscy
lmzv:   ANA A
        RAL
        JC loaw
lcid:   INR D
        CMA
        JMP ludz
lkyu:   ANA A
        RAL
        JC lwea
        DCR D
        ANA A
        RAL
        JNC ludz
lahc:   INR B
        JMP lyfb
lwea:   ANA A
        RAL
        JNC lahc
        JMP lcid
loaw:   CMA
lyfb:   ADD E
        JNC leje
        INR D
leje:   MOV E,A
lscy:   PUSH B
        MOV B,H
        LXI H,lycb
        CALL luaz
        POP H
        XCHG
        PUSH H
        LXI H,lszy
        CALL luaz
        POP H
        RET
lolj:   ANA A
        RAR
        DCR E
        JNZ lolj
        RET
letr:   ANA A
        JZ lgkf
lilg:   DAD D
        DCR A
        JNZ lilg
lgkf:   MOV A,E
        ANA A
        RAR
        PUSH B
looj:   PUSH PSW
        MOV D,M
        INX H
        MOV E,M
        INX H
        MVI B,000h
        PUSH H
        MOV A,C
        ANA A
        JZ lkmh
        MOV H,C
lmni:   MOV A,D
        RAR
        MOV D,A
        MOV A,E
        RAR
        MOV E,A
        MOV A,B
        RAR
        MOV B,A
        DCR H
        JNZ lmni
lkmh:   LHLD larp
        MOV M,D
        INR H
        MOV M,E
        INR H
        MOV M,B
        DCR H
        DCR H
        INR L
        SHLD larp
        POP H
        POP PSW
        DCR A
        JNZ looj
        POP B
        RET
liig:   MOV L,H
        MOV A,D
        MOV C,D
        MVI E,003h
        CALL lolj
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
lkvu:   PUSH B
        PUSH H
lqpk:   INX H
        DCR B
        JNZ lqpk
        MOV A,M
        POP H
        POP B
        ANA A
        RET
lqyx:   PUSH B
        PUSH H
lsql:   INX H
        DCR B
        JNZ lsql
        MOV M,A
        POP H
        POP B
        RET
lghf:   PUSH B
        PUSH H
        CALL lurm
        MOV E,M
        INX H
        MOV D,M
        POP H
        POP B
        RET
luaz:   PUSH B
        PUSH H
        CALL lurm
        MOV M,E
        INX H
        MOV M,D
        POP H
        POP B
        RET
lurm:   INR B
        DCR B
        RZ
lwsn:   INX H
        INX H
        DCR B
        JNZ lwsn
        RET
lesr:   LHLD leqr
        MVI C,010h
laup:   MOV A,H
        DAD H
        ANI 060h
        JPE lyto
        INX H
lyto:   DCR C
        JNZ laup
        SHLD leqr
        RET
lunm:   CALL lokj
        MVI A,004h
        STA lcvq
        STA lewr
        STA lgxs
        STA liyt
        STA lkzu
        STA lmav
        STA lobw
        STA lqcx
        STA lsdy
        STA luez
        STA lwfa
        STA lygb
        STA laic
        STA lcjd
        STA leke
        STA lglf
        STA limg
        STA lknh
        STA lmoi
        STA lopj
        STA lqqk
        STA lsrl
        STA lusm
        STA lwtn
        STA lyuo
        STA lavp
        STA lcwq
        STA lexr
        STA lgys
        STA lizt
        STA lkau
        STA lmbv
        STA locw
        STA lqdx
        STA lsey
        STA lufz
        STA lwga
        STA lyhb
        MVI A,001h
        STA lajc
        STA lckd
        STA lele
        STA lgmf
        STA ling
        STA lkoh
        STA lmpi
        STA loqj
        STA lqrk
        STA lssl
        STA lutm
        STA lwun
        STA lyvo
        STA lawp
        STA lcxq
        STA leyr
        STA lgzs
        STA liat
        STA lkbu
        STA lmcv
        STA lodw
        STA lqex
        STA lsfy
        MVI A,00Eh
        STA lugz
        STA lwha
        STA lyib
        STA lakc
        CALL lifg
        RET
lkkh:   DCR A
        XTHL
        XTHL
        JNZ lkkh
        RET
lulm:   LDAX B
        INX B
        MOV D,A
lgnf:   LDAX B
        INX B
        MOV M,A
        INX H
        DCR D
        JNZ lgnf
        LDAX B
        INX B
        ANA A
        RZ
        MOV E,A
        DAD D
        JMP lulm
lgff:   PUSH B
        MVI A,091h
        STA lkph
        MVI C,000h
        LDA lmqi
        MVI B,008h
lqsk:   RAR
        JNC lorj
        INR C
        DCR B
        JNZ lqsk
        LDA lstl
        MVI B,003h
luum:   RAR
        JNC lorj
        INR C
        DCR B
        JNZ luum
lorj:   MVI A,082h
        STA lkph
        MOV A,C
        CMA
        ADI 00Ch
        POP B
        RET
lybb:   LXI H,005D0h
        SHLD lgef
        LXI H,lwvn
        CALL lmhi
        LDA lcfd
luyz:   PUSH B
        MOV B,A
        CALL lywo
        MOV A,B
        CALL laxp
        POP B
        RET
lywo:   RRC
        RRC
        RRC
        RRC
laxp:   ANI 00Fh
        CPI 00Ah
        JC lcyq
        ADI 007h
lcyq:   ADI 030h
        MOV C,A
        JMP lkuu
ladc:   LXI H,005E0h
        SHLD lgef
        LXI H,lezr
        CALL lmhi
        LDA lomj
        JMP luyz
lifg:   XRA A
        JMP lgas
lokj:   MVI A,040h
        JMP lgas
loij:   MVI A,080h
        JMP lgas
lacc:   MVI A,0C0h
lgas:   STA lstl
        RET

lefe:   DB 02Ah, 020h, 041h, 044h, 044h, 020h, 02Ah, 000h, 020h, 02Ah
        DB 020h, 031h, 039h, 038h, 039h, 020h, 02Ah
lerr:   DB 077h, 079h, 020h, 02Dh, 020h, 070h, 069h, 06Ch, 06Fh, 074h
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
lwvn:   DB 077h, 069h, 076h, 075h, 020h, 063h, 065h, 06Ch, 065h, 06Ah
        DB 020h, 02Dh, 020h, 000h
lezr:   DB 075h, 06Eh, 069h, 07Eh, 074h, 06Fh, 076h, 065h, 06Eh, 06Fh
        DB 020h, 02Dh, 020h, 000h
lkgh:   DB 02Ah, 020h, 02Ah, 020h, 02Ah, 020h, 020h, 07Ah, 020h, 077h
        DB 020h, 065h, 020h, 07Ah, 020h, 064h, 020h, 06Eh, 020h, 079h
        DB 020h, 065h, 020h, 020h, 020h, 077h, 020h, 06Fh, 020h, 06Ah
        DB 020h, 06Eh, 020h, 079h, 020h, 02Ah, 020h, 02Ah, 020h, 02Ah
        DB 000h
lqok:   DB 075h, 063h, 065h, 06Ch, 065h, 077h, 07Bh, 069h, 065h, 020h
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
lskl:   DB 00Dh, 00Fh, 030h, 040h, 080h, 081h, 080h, 042h, 020h, 010h
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
lmji:   DB 041h, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FAh
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
lcqq:   DB 0AAh, 055h, 0AAh, 05Fh, 0BFh, 07Fh, 0FEh, 0FCh, 0F8h, 0F0h
        DB 0E0h, 0C0h, 0CFh, 02Ah, 080h, 0C0h, 0C0h, 0E0h, 060h, 0F0h
        DB 070h, 0B8h, 058h, 0ACh, 054h, 0ACh, 056h, 0AAh, 0D6h, 0AAh
        DB 0DEh, 0AEh, 0DDh, 0ABh, 0D5h, 0ABh, 0F5h, 0AAh, 0D6h, 0AAh
        DB 0D6h, 0AAh, 056h
lonj:   DB 0ACh, 054h, 0ACh, 050h, 0A8h, 058h, 0B0h, 050h, 0A0h, 060h
        DB 0C0h, 0C0h, 080h, 000h
lsml:   DB 02Dh, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 002h, 003h, 002h, 003h, 002h, 003h, 002h, 003h, 002h
        DB 003h
lktu:   DB 002h, 003h, 002h, 003h, 002h, 003h, 002h, 001h, 001h, 001h
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
lihg:   DB 011h, 001h, 001h, 001h, 001h, 002h, 002h, 002h, 002h, 002h
        DB 00Ch, 030h, 0C0h, 080h, 080h, 09Fh, 0F0h, 01Fh, 0DFh, 028h
        DB 018h, 024h, 05Ah, 042h, 042h, 099h, 0A5h, 0A5h, 0BDh, 081h
        DB 099h, 081h, 081h, 0A5h, 081h, 081h, 081h, 081h, 081h, 081h
        DB 081h, 081h, 081h, 081h, 081h, 081h, 081h, 081h, 081h, 099h
        DB 099h, 099h, 099h, 099h, 099h, 0FFh, 099h, 0FFh, 018h, 018h
        DB 0E8h, 011h, 080h, 080h, 080h, 080h, 040h, 040h, 040h, 040h
        DB 040h, 030h, 00Ch, 003h, 001h, 001h, 0F9h, 00Fh, 0F8h, 000h
lcsq:   DB 000h, 000h, 000h, 000h, 000h, 080h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 001h, 0C0h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 002h, 0A0h, 000h, 000h, 000h, 000h, 000h, 000h, 006h, 0B0h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 01Dh, 0B8h, 000h, 000h
        DB 000h, 000h, 01Dh, 0B8h, 01Dh, 0B8h, 000h, 000h, 000h, 000h
        DB 03Dh, 0DEh, 025h, 0D2h, 03Dh, 0DEh, 0F9h, 09Fh, 08Fh, 0F1h
        DB 08Bh, 0D1h, 0F9h, 09Fh
lsby:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
laop:   DB 025h, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h
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