        CPU  8080
        .ORG 00000h
loij    EQU 04000h
lqxx    EQU 02601h
lwaa    EQU 02603h
liyt    EQU 02605h
lyab    EQU 02007h
lqwx    EQU 0240Bh
lsfy    EQU 0C010h
lugz    EQU 07A13h
lkph    EQU 03E13h
lodw    EQU 0C427h
lmpi    EQU 0C037h
lupm    EQU 03A4Fh
lmmi    EQU 0CAB7h
lege    EQU 0C0BEh
lqyx    EQU 08100h
lyuo    EQU 0FF00h
lusm    EQU 0FF01h
lavp    EQU 0FF02h
lsrl    EQU 0FF03h
loaw    EQU 0B707h
lktu    EQU 0170Bh
lmqi    EQU 07B13h
leme    EQU 01D14h
leee    EQU 0C337h
lkzu    EQU 02786h
lmav    EQU 0278Eh
lomj    EQU 077AEh
lmcv    EQU 027CDh
lyto    EQU 0FFE0h
looj    EQU 0FFF0h
lgzs    EQU 0FFF7h
lacc    EQU 08FFCh
larp    EQU 0FFFFh
        JMP labc
        DB 000h, 000h
lshy:   LXI H,lccd
        MVI M,02Bh
        INX H
        MVI A,080h
lede:   MVI M,000h
        INX H
        DCR A
        JNZ lede
lcxq:   XRA A
        STA lgef
        STA lifg
        STA lkgh
        STA lmhi
        LXI H,loij
        SHLD lqjk
        STA lskl
        LXI H,00000h
        SHLD lulm
        SHLD lwmn
        SHLD lyno
        MOV D,A
        LXI B,00000h
        LXI H,laop
        CALL lcpq
        LXI H,leqr
        LXI B,00005h
        MVI D,014h
        CALL lcpq
        MVI D,000h
        LXI H,lgrs
        CALL lcpq
        LXI H,list
        MVI E,001h
luyz:   PUSH D
        PUSH H
        LXI B,lktu
        MOV A,E
        ADI 030h
        CALL lmuv
        MVI A,03Eh
        CALL lmuv
        MVI D,000h
        CALL lcpq
        LXI H,lovw
        CALL lcpq
        POP H
        PUSH H
        LXI D,00009h
        DAD D
        LXI B,lqwx
        CALL lsxy
        POP H
        LXI D,0000Ch
        DAD D
        LDA lifg
        ADI 00Eh
        STA lifg
        POP D
        INR E
        MOV A,E
        CPI 00Ah
        JNZ luyz
        XRA A
        STA lifg
        LXI H,lwza
        MVI D,000h
        CALL lcpq
        LXI B,lyab
        LXI H,lccd
        MVI D,009h
lqkk:   PUSH H
        LXI H,0C010h
        SHLD lacc
        POP H
        MVI A,001h
        CALL lcdd
lkhh:   CALL leee
        CPI 00Dh
        JZ lgff
        CPI 008h
        JZ ligg
        DCR D
        INR D
        JZ lkhh
        CPI 020h
        JNZ lmii
        MVI A,02Bh
        JMP lojj
lmii:   CPI 041h
        JC lkhh
        CPI 060h
        JC lojj
        SUI 020h
lojj:   MOV M,A
        CALL lmuv
        INX H
        DCR D
        JMP lqkk
ligg:   MOV A,D
        CPI 009h
        JZ lkhh
        MVI A,00Bh
        CALL lcdd
        DCR B
        INR D
        DCX H
        JMP lqkk
lgff:   MOV A,D
        CPI 009h
        JNZ lsll
        MVI D,000h
        CALL lcpq
        JMP lumm
lsll:   MVI M,000h
        MVI A,00Bh
        CALL lcdd
lumm:   CALL lwnn
        CALL lyoo
        CPI 020h
        JNZ lumm
lssl:   CALL lapp
        LXI H,00000h
        SHLD lcqq
        SHLD lerr
        SHLD lyno
        LXI B,lgss
        LXI H,lcqq
        CALL litt
        CALL lkuu
        XRA A
        STA lmvv
        STA loww
        LXI B,lqxx
        CALL lsyy
        LXI H,lqjk
        LXI B,luzz
        CALL litt
        LDA lkgh
        LXI B,lwaa
        CALL lsyy
        LDA lkgh
        ANI 00Fh
        MVI B,007h
        DCR A
        JNZ lybb
        MVI B,00Eh
lybb:   DCR A
        JNZ ladc
        MVI B,015h
ladc:   MOV A,B
        STA lced
        LXI H,00120h
        SHLD lefe
lyfb:   CALL lggf
        CALL lihg
        XRA A
        STA lkih
        LDA lkgh
        ANI 00Fh
        CPI 003h
        JNC lmji
        INR A
        MOV C,A
        MVI A,030h
        MOV B,A
lqlk:   DCR C
        JZ lokj
        ADD B
        JMP lqlk
lmji:   MVI A,060h
lokj:   ADI 000h
        DAA
        STA lsml
        CALL lunm
lypo:   CALL lwon
        LDA lkih
        ORA A
        JNZ lypo
        LDA laqp
        ORA A
        JNZ lcrq
        LDA lsml
        MVI B,099h
        ADD B
        DAA
        STA lsml
        CALL lunm
lcrq:   CALL lesr
        CPI 010h
        JZ lgts
        CPI 004h
        JZ liut
        CPI 080h
        JZ lkvu
        CPI 020h
        JZ lmwv
        CPI 001h
        JZ loxw
        CPI 002h
lqsk:   JZ lqyx
        JMP lypo
loxw:   LHLD lefe
        SHLD lszy
        LXI H,00700h
        SHLD lefe
        XRA A
        STA lifg
        STA lmhi
laec:   LXI H,luaz
        CALL lcpq
        CALL lesr
        CPI 001h
        JZ lwba
        LXI H,lycb
        CALL lcpq
        CALL lesr
        CPI 001h
        JNZ laec
lwba:   LXI H,lcfd
        CALL lcpq
        LHLD lszy
        SHLD lefe
        JMP lypo
loqj:   MVI B,003h
        PUSH PSW
lghf:   POP PSW
        LDAX D
        CMP M
        RNZ
        PUSH PSW
        DCR B
        INX H
        INX D
        JNZ lghf
        POP PSW
        RET
lapp:   LXI H,liig
        MVI C,01Ah
lolj:   MVI B,010h
        LXI D,lkjh
        CALL lmki
        DCR C
        JNZ lolj
        CALL lqmk
        LXI H,lsnl
        CALL lcpq
        LDA lkgh
        LXI B,luom
        CALL lsyy
        LXI H,lwpn
        CALL lcpq
        LXI H,lqjk
        LXI B,lyqo
        CALL litt
        LXI H,larp
        CALL lcsq
        LXI H,larp
        CALL lcsq
        LXI H,larp
        CALL lcsq
        LXI H,liig
        MVI C,019h
lgus:   MVI B,010h
        LXI D,letr
        CALL lmki
        DCR C
        JNZ lgus
        LXI D,livt
        MVI B,010h
        CALL lmki
        LDA lkgh
        ANI 00Fh
        CPI 003h
        JC lqmk
        SUI 003h
        RRC
        RRC
        RRC
        LXI D,00000h
        MOV E,A
        LXI H,lkwu
        DAD D
        LXI D,lmxv
        XCHG
        MVI B,010h
lwca:   PUSH B
        MVI B,002h
lubz:   LDAX D
        MVI C,008h
lsay:   RAL
        JNC lqzx
        MVI M,002h
lqzx:   INX H
        DCR C
        JNZ lsay
        DCR B
        INX D
        JNZ lubz
        POP B
        DCR B
        JNZ lwca
lqmk:   XRA A
        STA lmhi
        STA lifg
        LXI B,00000h
lehe:   CALL lydb
        MOV A,M
        CALL lafc
        INX B
        LXI H,001A0h
        CALL lcgd
        JNZ lehe
        RET
lcsq:   DCX H
        MOV A,H
        ORA L
        JNZ lcsq
        RET
lydb:   LXI H,liig
        DAD B
        RET
lmki:   LDAX D
        MOV M,A
        INX D
        INX H
        DCR B
        JNZ lmki
        RET
lcdd:   PUSH B
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
        LDA lifg
        ADD C
        MOV E,A
        POP PSW
        PUSH PSW
        MOV L,A
        MVI H,000h
        DAD H
        DAD H
        DAD H
        LXI B,lijg
        DAD B
        XCHG
        LDA lgef
        ORA A
luum:   JZ lkkh
        DCR A
        JZ lmli
lsol:   LDAX D
        XRA M
        MOV M,A
        JMP lqnk
lmli:   LXI B,00007h
        DAD B
        JMP lsol
lkkh:   MVI B,008h
lwqn:   LDAX D
        MOV C,A
        LDA lmhi
        ANA M
        XRA C
        MOV M,A
        INX H
        INX D
        DCR B
        JNZ lwqn
lqnk:   POP PSW
        POP H
        POP D
        POP B
        RET
lafc:   PUSH B
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
        CALL lcdd
        POP D
        POP B
        RET
lcgd:   MOV A,H
        CMP B
        RNZ
        MOV A,L
        CMP C
        RET
lggf:   CALL lwnn
        LDA lced
        MOV C,A
        MOV A,L
lasp:   CMP C
        JC lyro
        SUB C
        JMP lasp
lyro:   RLC
        RLC
        RLC
        MOV L,A
        MVI H,000h
        LXI D,lctq
        DAD D
        LXI D,leur
        MVI C,008h
lkxu:   MOV A,M
        MVI B,008h
liwt:   RAL
        PUSH PSW
        MVI A,000h
        RAL
        STAX D
        POP PSW
        INX D
        DCR B
        JNZ liwt
        INX H
        DCR C
        JNZ lkxu
        XRA A
        STA laqp
        LHLD lmyv
        MOV A,H
        ANI 003h
        STA lozw
        MOV A,H
        RRC
        RRC
        ANI 007h
        JZ lqax
        DCR A
lqax:   ADI 003h
        MOV L,A
        MVI H,000h
        SHLD lsby
        RET
lwnn:   LHLD lmyv
        MVI C,010h
lwda:   MOV A,H
        DAD H
        ANI 060h
        JPE lucz
        INX H
lucz:   DCR C
        JNZ lwda
        SHLD lmyv
        RET
lihg:   CALL lyeb
        JZ lagc
        POP H
        JMP lchd
lyeb:   CALL leie
        XRA A
        STA lgjf
        CALL likg
        LDA laqp
        ORA A
        JZ lklh
        LXI B,00010h
        DAD B
        CALL likg
lklh:   LDA lgjf
        ORA A
        RET
likg:   PUSH H
        PUSH D
        MVI B,004h
luqm:   MVI C,004h
lspl:   LDAX D
        ORA A
        JZ lonj
        MOV A,M
        ORA A
        JZ lonj
        MVI A,001h
        STA lgjf
        JMP lqok
lonj:   INX D
        INX H
        DCR C
        JNZ lspl
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ luqm
lqok:   POP D
        POP H
        RET
leie:   LXI H,leur
        LDA lozw
        RLC
        RLC
        RLC
        RLC
        MOV E,A
        MVI D,000h
        DAD D
        XCHG
        LHLD lsby
        LXI B,liig
        DAD B
        RET
lagc:   CALL leie
        LHLD lsby
        LDA laqp
        STA lifg
        MVI A,0FFh
        STA lmhi
        MVI B,004h
latp:   MVI C,004h
lyso:   PUSH B
        PUSH H
        POP B
        LDAX D
        ORA A
        JZ lwrn
        CALL lafc
lwrn:   PUSH B
        POP H
        POP B
        INX H
        INX D
        DCR C
        JNZ lyso
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ latp
        RET
lwon:   MVI A,002h
        STA lgef
        LDA lkih
        ORA A
        CZ lagc
        LDA laqp
        ORA A
        JZ lcuq
lmzv:   INR A
        CPI 008h
        JZ levr
lgws:   STA laqp
        MVI A,001h
        STA lgef
        LDA lkih
        ORA A
        CZ lagc
        XRA A
        STA lgef
        RET
levr:   LHLD lsby
        LXI B,00010h
        DAD B
        SHLD lsby
        XRA A
        JMP lgws
lcuq:   LHLD lsby
        SHLD lixt
        LXI B,00010h
        DAD B
        SHLD lsby
        CALL lyeb
        LHLD lixt
        SHLD lsby
        JNZ lkyu
        JMP lmzv
lkyu:   CALL leie
        MVI B,004h
ludz:   MVI C,004h
lscy:   LDAX D
        RLC
        ORA A
        JZ lqbx
        MOV M,A
lqbx:   INX H
        INX D
        DCR C
        JNZ lscy
        PUSH B
        LXI B,0000Ch
        DAD B
        POP B
        DCR B
        JNZ ludz
        XRA A
        STA lgef
        CALL lqmk
        CALL lwea
        JMP lyfb
lwea:   LXI H,lahc
        LXI D,lsml
        CALL lcid
        LXI B,lgss
        CALL litt
        LXI H,leje
        LXI D,lsml
        CALL lcid
        LXI B,lgkf
        CALL litt
        XRA A
        STA lilg
lglf:   LXI H,lkmh
lsql:   CALL lmni
        LXI B,looj
        DAD B
        LXI B,lqpk
        CALL lcgd
        JNZ lsql
        PUSH B
        PUSH H
        LDA lilg
        ORA A
        JZ lurm
        MOV B,A
        LDA loww
        ADD B
        STA loww
        CPI 008h
        JC lwsn
        SUI 008h
        STA loww
        LDA lmvv
        CPI 008h
        JZ lwsn
        INR A
        STA lmvv
        LXI B,lqxx
        CALL lsyy
        LHLD lefe
        LXI B,lyto
        DAD B
        SHLD lefe
lwsn:   LDA lilg
        MVI B,001h
        DCR A
        JZ laup
        MVI B,003h
        DCR A
        JZ laup
        MVI B,006h
        DCR A
        JZ laup
        MVI B,010h
laup:   MOV A,B
        STA lcvq
        LXI D,lewr
        LXI H,lahc
        CALL lcid
        LXI B,lgss
        CALL litt
        LXI H,leje
        LXI D,lewr
        CALL lcid
        LXI B,lgkf
        CALL litt
        LDA lilg
        LHLD lyno
        MOV E,A
        MVI D,000h
        CALL lgxs
        SHLD lyno
        CALL lkuu
lurm:   POP H
        POP B
        RET
lunm:   LXI B,00303h
        LDA lsml
        JMP lsyy
lkuu:   LHLD lyno
        LXI B,liyt
        MOV A,H
        CALL lsyy
        MOV A,L
        JMP lsyy
lcid:   LDAX D
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
litt:   MVI E,003h
lobw:   MOV A,M
        CALL lsyy
        INX H
        DCR E
        JNZ lobw
        RET
lsxy:   MVI E,003h
lsdy:   MOV A,M
        CALL lqcx
        INX H
        DCR E
        JNZ lsdy
        RET
lsyy:   PUSH PSW
        XRA A
        STA lmhi
        STA lifg
        POP PSW
lqcx:   MOV D,A
        RRC
        RRC
        RRC
        RRC
        ANI 00Fh
        ADI 030h
        CALL lmuv
        MOV A,D
        ANI 00Fh
        ADI 030h
        CALL lmuv
        RET
lmni:   PUSH H
        MVI B,00Ah
lwfa:   MOV A,M
        ORA A
        JZ luez
        INX H
        DCR B
        JNZ lwfa
        POP H
        PUSH H
leke:   PUSH H
        LXI B,looj
        PUSH H
        POP D
        DAD B
        MVI B,00Ah
laic:   MOV A,M
        STAX D
        INX H
        INX D
        DCR B
        JNZ laic
        POP H
        LXI B,lqpk
        CALL lcgd
        JZ lcjd
        LXI B,looj
        DAD B
        JMP leke
lcjd:   CALL lqmk
        LDA lilg
        INR A
        STA lilg
        POP H
        POP H
        JMP lglf
luez:   POP H
        RET
lesr:   LHLD lefe
        XRA A
        STA limg
lqqk:   CALL lyoo
        ORA A
        JNZ lknh
        STA lmoi
        JMP lopj
lknh:   MOV B,A
        LDA lmoi
        ORA A
        MOV A,B
        STA lmoi
        JNZ lopj
        LDA limg
        ORA A
        JNZ lopj
        MOV A,B
        STA limg
lopj:   DCX H
        MOV A,H
        ORA L
        JNZ lqqk
        LDA limg
        ORA A
        RET
lyoo:   MVI A,091h
        STA lsrl
        MVI A,0FBh
        STA lusm
        MVI C,00Fh
lwtn:   DCR C
        JNZ lwtn
        LDA lyuo
        ANI 03Fh
        MOV B,A
        LDA lavp
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        RET
lgts:   CALL lagc
        LHLD lsby
        SHLD lixt
        LXI B,larp
lgys:   DAD B
        SHLD lsby
        CALL lyeb
        JNZ lcwq
        JMP lexr
lcwq:   LHLD lixt
        SHLD lsby
        JMP lexr
liut:   CALL lagc
        LHLD lsby
        SHLD lixt
        LXI B,00001h
        JMP lgys
lmwv:   MVI A,001h
        STA lkih
        JMP lypo
lkvu:   CALL lagc
        LDA lozw
        STA lizt
        INR A
        CPI 004h
        JC lkau
        XRA A
lkau:   STA lozw
        CALL lyeb
        JNZ lmbv
lexr:   CALL lagc
        JMP lypo
lmbv:   LDA lizt
        STA lozw
        JMP lexr
lcpq:   PUSH H
        PUSH B
lqdx:   MOV A,M
        ORA A
        JZ locw
        CALL lmuv
        INX H
        JMP lqdx
locw:   MOV A,D
        ORA A
        POP B
        POP H
        RZ
        DCR D
        INR C
        JMP lcpq
lmuv:   CPI 020h
        JNC lsey
        CPI 01Fh
        JZ lufz
        CPI 008h
        JZ lwga
        CPI 018h
        JZ lyhb
        CPI 019h
        JZ lajc
        CPI 01Ah
        JZ lckd
        CPI 00Ah
        JZ lele
        CPI 00Dh
        JZ lgmf
        CPI 007h
        JZ ling
        RET
lsey:   PUSH PSW
        ANI 07Fh
        SUI 020h
        CALL lcdd
        POP PSW
        CPI 080h
        JNC lkoh
        INR B
        RET
lkoh:   INR C
        RET
lwga:   DCR B
        RET
lyhb:   INR B
        RET
lckd:   INR C
        RET
lajc:   DCR C
        RET
lgmf:   MVI B,000h
        RET
lele:   INR C
        MVI B,000h
        RET
ling:   INX H
        MOV B,M
        INX H
        MOV C,M
        RET
lufz:   PUSH B
        MVI C,01Fh
        CALL lmpi
        POP B
        RET
lchd:   LXI D,lqjk
        LXI H,lcqq
        CALL loqj
        JNC lqrk
        LDA lkgh
        ADI 001h
        DAA
        STA lkgh
        ANI 00Fh
        JNZ lssl
        LXI H,00040h
        LXI D,00015h
        LDA lkgh
lutm:   CALL lgxs
        SUI 010h
        JNZ lutm
        MOV A,H
        MOV H,L
        MOV L,A
        SHLD lqjk
        JMP lssl
lgxs:   PUSH PSW
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
lqrk:   LXI H,lwun
        MVI D,009h
lawp:   PUSH H
        PUSH D
        LXI D,lulm
        CALL loqj
        JNC lyvo
        POP D
        POP H
        LXI B,0000Ch
        DAD B
        DCR D
        JNZ lawp
        JMP lcxq
lyvo:   POP D
        MOV A,D
        CPI 001h
        JZ leyr
        POP H
        PUSH H
        LXI B,lgzs
        DAD B
        XCHG
        LXI B,liat
        LXI H,lkbu
lqex:   DCX B
        DCX H
        MOV A,M
        STAX B
        CALL lodw
        JNZ lqex
leyr:   POP H
        LXI B,lgzs
        DAD B
        LXI D,lccd
        MVI B,009h
        CALL lmki
        LXI D,lulm
        MVI B,003h
        CALL lmki
        JMP lcxq

laop:   DB 02Ch, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
        DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
loyw:   DB 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h, 060h
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
leqr:   DB 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 061h, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 02Bh, 02Bh, 02Bh, 061h, 000h
lgrs:   DB 007h, 000h, 01Ah, 061h, 02Bh, 02Bh, 02Bh, 02Bh
lyqo:   DB 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
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
luaz:   DB 007h, 007h, 01Eh, 02Bh, 050h, 041h, 055h, 05Ah, 041h, 02Bh
        DB 000h
lcfd:   DB 007h, 007h, 01Eh, 023h, 023h, 023h, 023h, 023h, 023h, 023h
        DB 000h
lycb:   DB 007h, 007h, 01Eh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh, 02Bh
        DB 000h
lsnl:   DB 007h, 003h, 011h, 055h, 052h, 04Fh, 057h, 045h, 04Eh, 058h
        DB 03Dh, 02Bh, 02Bh, 000h
lwpn:   DB 007h, 003h, 013h, 043h, 045h, 04Ch, 058h, 03Dh, 02Bh, 000h
lwza:   DB 007h, 016h, 008h, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh, 03Dh
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
lovw:   DB 007h, 021h, 00Bh, 02Bh, 03Dh, 02Bh, 000h
laqp:   DB 000h
lifg:   DB 000h
lozw:   DB 000h
lizt:   DB 000h
lsby:   DB 008h, 000h
lixt:   DB 018h, 000h
lmhi:   DB 000h
lgef:   DB 000h
lmyv:   DB 038h, 018h
lkgh:   DB 000h, 000h, 000h
lsml:   DB 030h
lkih:   DB 001h
lgjf:   DB 001h
lefe:   DB 020h, 001h
lszy:   DB 000h, 000h
lyno:   DB 000h, 000h
lmoi:   DB 020h
limg:   DB 020h
lcqq:   DB 000h
lerr:   DB 004h
lahc:   DB 066h, 000h
lcvq:   DB 000h
lewr:   DB 000h
lilg:   DB 000h
loww:   DB 000h
lmvv:   DB 000h
lced:   DB 007h
lqjk:   DB 000h, 040h
lskl:   DB 000h
lulm:   DB 000h
lwmn:   DB 000h
leje:   DB 000h
livt:   DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 003h, 003h
letr:   DB 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 003h, 003h, 003h
lkjh:   DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
lctq:   DB 027h, 000h, 023h, 020h, 072h, 000h, 013h, 010h, 00Fh, 000h
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
leur:   DB 000h, 000h, 001h, 000h, 000h, 001h, 001h, 001h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 001h, 001h, 000h, 000h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 001h, 001h, 001h, 000h, 000h, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 001h, 000h, 000h, 001h, 001h, 000h, 000h, 000h, 001h
        DB 000h
lgss:   DB 000h, 000h
luzz:   DB 000h, 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 003h, 003h, 003h
liig:   DB 003h, 003h, 003h
lqpk:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
lmxv:   DB 003h, 003h, 003h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
luom:   DB 003h, 003h, 003h, 003h, 003h, 003h, 000h, 000h, 000h, 000h
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
lkmh:   DB 000h, 000h, 002h, 002h, 000h, 002h, 002h, 002h, 000h, 000h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
        DB 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h, 003h
lccd:   DB 02Bh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
list:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lwun:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lkbu:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
liat:   DB 000h, 00Eh, 00Eh, 020h, 0CDh, 05Eh, 00Eh, 02Ah, 00Ah, 010h
        DB 0CDh, 056h, 00Eh, 00Eh, 029h, 0CDh, 05Eh, 00Eh, 001h, 020h
        DB 004h, 0C9h, 006h, 00Ch, 0C3h, 056h, 00Eh, 0C5h, 0CDh, 067h
        DB 00Eh, 0C1h, 0C9h, 07Ch, 0CDh, 050h, 00Eh, 07Dh, 0CDh, 050h
        DB 00Eh, 0C3h, 037h, 0C0h, 0C3h, 037h, 0C3h, 0AFh, 0C9h, 0F8h
        DB 0C3h, 027h, 006h, 0C3h, 038h, 0C4h, 0C3h, 000h, 000h, 00Bh
        DB 000h, 008h, 001h, 02Eh, 001h, 000h, 001h, 016h, 002h, 00Eh
        DB 001h, 026h, 002h, 02Bh, 001h
lkwu:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
lijg:   DB 055h, 000h, 001h, 000h, 001h, 000h, 001h, 000h, 0FFh, 0FFh
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
labc:   CALL lsfy
        LXI D,09000h
lwha:   MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 091h
        JNZ lwha
        LXI D,0BF00h
lyib:   MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 0C0h
        JNZ lyib
        LXI D,09000h
        CALL lakc
        JMP lcld
lakc:   MVI A,0FFh
        STAX D
        INX D
        STAX D
        INR D
        DCR E
        MOV A,D
        CPI 0C0h
        JNZ lakc
        RET
lcld:   LXI D,09004h
        CALL lakc
        LXI D,090FAh
        CALL lakc
        LXI D,090FEh
        CALL lakc
        LXI D,09418h
        CALL lgnf
        JMP liog
lgnf:   MVI A,0FFh
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
        JNZ lgnf
        RET
liog:   LXI D,09318h
        CALL lgnf
        LXI D,09618h
        CALL lgnf
        LXI D,0B918h
        CALL lgnf
        LXI D,0BB18h
        CALL lgnf
        LXI D,0BC18h
        CALL lgnf
        LXI B,lorj
        LXI D,09820h
        MVI H,000h
lwvn:   LDAX B
        SUI 001h
        JZ lstl
        ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lwvn
        INR D
        INR D
        MVI H,000h
        JMP lwvn
lstl:   LXI B,lywo
        LXI D,0AB20h
        MVI H,000h
lezr:   LDAX B
        SUI 001h
        JZ laxp
        CALL lcyq
        JNZ lezr
        INR D
        INR D
        MVI H,000h
        MVI E,020h
        JMP lezr
laxp:   LXI B,lgas
        LXI D,099A0h
        MVI H,000h
lmdv:   LDAX B
        SUI 001h
        JZ libt
        CALL lcyq
        JMP lkcu
lcyq:   ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        RET
lkcu:   JNZ lmdv
        INR D
        INR D
        MVI H,000h
        MVI E,0A0h
        JMP lmdv
libt:   LXI B,loew
        LXI D,0991Ch
        MVI L,000h
lgof:   PUSH B
        PUSH D
        MVI H,000h
lsgy:   LDAX B
        SUI 001h
        JZ lqfx
        CALL lcyq
        JNZ lsgy
lqfx:   CALL luhz
        POP D
        LXI B,lwia
        MVI H,000h
lalc:   LDAX B
        SUI 001h
        JZ lyjb
        CALL lcyq
        JNZ lalc
lyjb:   POP B
        MOV A,C
        CPI 078h
        JNZ lcmd
        MVI C,048h
        INR L
        MOV A,L
        CPI 00Ah
        JZ lene
lcmd:   MOV A,C
        ADI 010h
        MOV C,A
        INR D
        DCR E
        DCR E
        DCR E
        DCR E
        JMP lgof
luhz:   MVI A,001h
lmri:   MVI D,0E0h
lkqh:   MVI E,080h
lipg:   DCR E
        JNZ lipg
        DCR D
        JNZ lkqh
        DCR A
        JNZ lmri
        RET
lgbs:   LXI B,losj
        LXI D,0AF30h
        MVI H,000h
lsul:   LDAX B
        SUI 001h
        JZ lqtk
        CALL lcyq
        JNZ lsul
        INR D
        MVI H,000h
        MVI E,030h
        JMP lsul
lene:   LXI B,luvm
        LXI D,0B898h
        LXI H,00000h
lkdu:   PUSH B
        PUSH D
        MVI H,000h
lyxo:   LDAX B
        SUI 001h
        JZ lwwn
        CALL lcyq
        JNZ lyxo
lwwn:   CALL luhz
        POP D
        LXI B,lwia
        MVI H,000h
lczq:   LDAX B
        SUI 001h
        JZ layp
        CALL lcyq
        JNZ lczq
layp:   POP B
        MOV A,C
        CPI 0F0h
        JNZ lear
        MVI C,0E0h
        INR L
        MOV A,L
        CPI 009h
        JZ lgbs
        JMP lict
lear:   MOV A,C
        ADI 010h
        MOV C,A
lict:   LXI D,0B898h
        JMP lkdu
lqtk:   LXI B,lmev
        LXI D,099C0h
        MVI H,000h
lqgx:   LDAX B
        SUI 001h
        JZ lofw
        ADI 001h
        STAX D
        INX D
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ lqgx
        INR D
        INR D
        INR D
        INR D
        MVI H,000h
        MVI E,0C0h
        JMP lqgx
lofw:   MVI A,00Fh
        CALL lmri
        CALL lsfy
        MVI C,01Fh
        CALL lmpi
        JMP lshy
        DB 007h, 06Bh, 0BBh, 0B6h, 0BBh, 0B7h, 0B7h, 0B7h, 000h, 0B0h
        DB 07Bh, 04Bh, 0B7h, 0B7h, 0BBh
lgvs:   DB 07Bh, 0B7h, 00Bh, 020h, 0B4h, 0B0h, 0B7h, 0B7h, 0B7h, 000h
        DB 0B0h, 07Bh, 0B0h, 007h, 007h, 0BBh, 070h, 0BBh
lgkf:   DB 0BBh, 0B7h, 07Bh, 001h, 0B7h, 0B7h, 0BBh, 0B0h, 0B0h, 07Bh
        DB 0B7h, 0BBh, 0B7h, 011h, 07Bh, 010h, 000h, 01Bh, 071h, 07Bh
        DB 044h, 0B7h, 0B0h, 000h, 0BBh, 07Bh, 0B7h, 00Bh, 0BBh, 0BBh
        DB 0BBh, 0B7h, 07Bh, 0BBh, 0BBh, 070h, 0BBh
lorj:   DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 07Eh, 066h
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
lywo:   DB 03Ch, 066h, 060h, 060h, 060h, 060h, 066h, 03Ch, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 0FEh, 0FEh, 030h, 030h
        DB 030h, 030h, 030h, 030h, 07Eh, 060h, 060h, 07Eh, 066h, 066h
        DB 066h, 07Ch, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 001h, 000h, 001h, 000h
        DB 001h, 000h, 001h, 000h
lgas:   DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 0FEh, 060h
        DB 060h, 060h, 07Ch, 060h, 060h, 0FEh, 07Eh, 066h, 066h, 066h
        DB 07Eh, 060h, 060h, 060h, 066h, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 066h, 066h
        DB 066h, 066h, 066h, 066h, 07Fh, 07Fh, 063h, 063h, 063h, 073h
        DB 06Bh, 06Bh, 06Bh, 073h, 001h, 007h, 0BBh, 0BBh, 000h, 000h
        DB 000h, 000h
loew:   DB 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah, 001h, 007h
        DB 0BBh, 0BBh, 000h, 000h, 07Bh, 07Bh, 007h, 007h, 00Eh, 03Eh
        DB 00Eh, 007h, 01Eh, 006h, 001h, 070h, 0BBh, 0BBh, 07Bh, 07Bh
        DB 07Bh, 072h, 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah
        DB 001h, 070h, 0BBh, 0BBh, 07Bh, 070h, 07Bh, 0BBh, 00Eh, 00Eh
        DB 0FCh, 01Fh, 038h, 0F6h, 002h, 003h, 001h, 070h, 0BBh, 0BBh
        DB 070h, 0AAh, 0AAh, 000h
lwia:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 070h
        DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
losj:   DB 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 03Eh, 066h, 066h, 066h
        DB 066h, 066h, 0FFh, 0FFh, 000h, 000h, 000h, 000h, 000h, 060h
        DB 060h, 060h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 03Ch, 066h
        DB 066h, 066h, 066h, 066h, 066h, 03Ch, 03Ch, 066h, 066h, 066h
        DB 066h, 066h, 066h, 03Ch, 001h, 066h, 066h, 066h, 066h, 066h
        DB 066h, 067h
luvm:   DB 04Eh, 04Eh, 024h, 01Eh, 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h
        DB 000h, 000h, 000h, 007h, 000h, 007h, 02Eh, 02Eh, 024h, 01Eh
        DB 00Eh, 00Dh, 00Ah, 00Ah, 001h, 0AAh, 07Ah, 000h, 007h, 000h
        DB 007h, 0BBh
lmev:   DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 0FEh, 060h, 060h, 060h
        DB 07Ch, 060h, 060h, 0FEh, 0FEh, 0FEh, 030h, 030h, 030h, 030h
        DB 030h, 030h, 07Ch, 066h, 066h, 066h, 07Eh, 07Ch, 066h, 066h
        DB 030h, 030h, 000h, 030h, 030h, 030h, 030h, 030h, 03Ch, 066h
        DB 060h, 030h, 00Ch, 006h, 066h, 03Ch, 066h, 066h, 066h, 000h
        DB 000h, 000h, 000h, 000h, 001h
END