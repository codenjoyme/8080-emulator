        CPU  8080
        .ORG 00000h
lktu    EQU 09000h
lgff    EQU 08800h
lsyy    EQU 0F800h
latp    EQU 01800h
lirg    EQU 0C800h
luyz    EQU 0F801h
lokj    EQU 08801h
lkuu    EQU 0E60Bh
lsfy    EQU 0880Dh
lede    EQU 0C010h
lwka    EQU 09010h
lkhh    EQU 0A415h
ludz    EQU 08819h
lizt    EQU 0881Ah
laic    EQU 0881Ch
lcjd    EQU 0881Eh
lcgd    EQU 08820h
lsay    EQU 08821h
lsul    EQU 08822h
lmxv    EQU 08823h
lolj    EQU 08824h
lafc    EQU 08825h
lspl    EQU 08826h
lwca    EQU 08829h
lydb    EQU 0882Eh
lubz    EQU 0882Fh
lugz    EQU 03C2Fh
leee    EQU 08830h
lmki    EQU 09A30h
lcsq    EQU 0A030h
lccd    EQU 08831h
loij    EQU 08833h
lixt    EQU 0C037h
lmhi    EQU 0C438h
lyoo    EQU 0F243h
lcqq    EQU 0F24Eh
lerr    EQU 0184Eh
lmsi    EQU 0B050h
lcuq    EQU 02E55h
lgef    EQU 05060h
lycb    EQU 09680h
loyw    EQU 09080h
lotj    EQU 02080h
lipg    EQU 090ADh
lwvn    EQU 0A6C4h
lqsk    EQU 0A6D6h
lggf    EQU 0A6DFh
lanc    EQU 090E0h
lybb    EQU 058F7h
ladc    EQU 08CF7h
lgof    EQU 0FAF8h
lgss    EQU 0F300h
liat    EQU 0FF00h
loxw    EQU 0FF01h
lodw    EQU 0FF02h
lgzs    EQU 0FF03h
lwha    EQU 02303h
lqvk    EQU 07703h
lsll    EQU 0E508h
letr    EQU 0FF08h
lqxx    EQU 0B512h
lwaa    EQU 08112h
lkjh    EQU 0C337h
lakc    EQU 0775Eh
lyno    EQU 09F6Ch
lsxy    EQU 0C170h
lgrs    EQU 03F88h
lqwx    EQU 08FF1h
lihg    EQU 0FFF7h
lifg    EQU 08FFCh
        LXI H,labc
        SHLD lccd
        CALL lede
        LXI H,lgef
        SHLD lifg
        LXI H,lkgh
        CALL lmhi
        LXI H,loij
        LXI B,00300h
lqjk:   MVI M,000h
        INX H
        DCR C
        JNZ lqjk
        DCR B
        JNZ lqjk
lwmn:   CALL lskl
        CALL lulm
        JZ lwmn
        CALL lede
        LXI H,lyno
        LXI B,laop
        LXI D,lcpq
        CALL leqr
        LXI H,lgrs
        SHLD lifg
        LXI H,list
        CALL lmhi
        MVI A,055h
lacc:   INR A
        PUSH PSW
        LXI H,lktu
lovw:   POP PSW
        RLC
        MOV M,A
        INX H
        PUSH PSW
        MOV A,L
        CPI 064h
        JNZ lmuv
        ADI 032h
lmuv:   MOV L,A
        MOV A,H
        CPI 0C0h
        JNZ lovw
        MVI A,01Eh
lyab:   STA lqwx
        PUSH PSW
        CALL lsxy
        LDA luyz
        ANI 0FCh
        CPI 0FCh
        JNZ lwza
        POP PSW
        DCR A
        JNZ lyab
        MVI A,080h
        STA lqwx
        POP PSW
        JMP lacc
lwza:   INX SP
        INX SP
        CALL lulm
        DCR A
        JZ lcdd
        MVI A,001h
lcdd:   STA leee
lyuo:   LXI H,lgff
        MVI A,030h
ligg:   MVI M,000h
        INX H
        DCR A
        JNZ ligg
lmni:   CALL lede
        LXI H,lkhh
        LXI D,00108h
        LXI B,lmii
        CALL lojj
        LXI B,lqkk
        CALL leqr
        LXI B,lsll
        DAD B
        PUSH H
        CALL lumm
        POP H
        CALL lwnn
        CALL lwnn
        INR H
        CALL lumm
        LXI B,lyoo
        MVI E,090h
        CALL lapp
        LXI B,lcqq
        CALL lapp
        LXI B,lerr
        MVI E,009h
        CALL lapp
        LXI B,lcqq
        CALL lapp
        LXI B,lgss
        DAD B
        CALL litt
        CALL litt
        LXI B,lkuu
        DAD B
        LXI D,00108h
        LXI B,lmvv
        CALL lojj
        LXI B,loww
        CALL leqr
        LXI H,lqxx
        LXI D,lsyy
        MVI C,030h
        MVI B,036h
        CALL luzz
        LXI H,lwaa
        MVI B,03Ah
        CALL luzz
        MVI C,041h
        MVI B,043h
        CALL luzz
        LXI H,lybb
        LXI D,00800h
        MVI B,049h
        CALL luzz
        LXI H,ladc
        MVI B,04Fh
        CALL luzz
        LXI B,00000h
        MOV A,C
lesr:   RLC
        CPI 018h
        JNC lced
        CALL lefe
        LXI H,lggf
        LXI D,lihg
        ADD H
        JMP lkih
lced:   SUI 018h
        CALL lefe
        MOV D,A
        MVI A,0BDh
        SUB D
        MVI L,01Bh
        LXI D,00009h
lkih:   MOV H,A
        PUSH H
        LXI H,lmji
        DAD B
        MOV A,M
        LXI H,lokj
        DAD B
        MOV M,A
        POP H
        PUSH B
        ANA A
        JZ lqlk
        JM lsml
        LXI B,lunm
lypo:   CALL lwon
        DCR A
        JNZ lypo
        JMP lqlk
lwon:   DAD D
        PUSH D
        LXI D,00108h
        PUSH H
        PUSH B
        CALL leqr
        POP B
        POP H
        POP D
        RET
lsml:   LXI B,laqp
lcrq:   CALL lwon
        INR A
        JNZ lcrq
lqlk:   POP B
        INR C
        MOV A,C
        CPI 018h
        JC lesr
        LXI H,lgts
        SHLD lifg
        LXI H,lkgh
        CALL lmhi
        CALL liut
        CALL lkvu
        LXI H,lmwv
        CALL lmhi
lqyx:   LDA loxw
        ANI 0FCh
        CPI 0FCh
        JNZ lqyx
        LXI H,lszy
        LDA leee
        ANA A
        JZ luaz
        LXI H,lwba
luaz:   CALL lmhi
        LXI H,lycb
        CALL laec
        PUSH PSW
        CALL lkvu
        LXI H,lcfd
        LDA leee
        ANA A
        JNZ lege
        LXI H,lghf
lege:   CALL lmhi
        LDA leee
        ANA A
        JNZ liig
        CALL lkjh
liig:   LXI H,lmki
        LDA lolj
        CALL lqmk
        MOV C,A
        POP PSW
        SUB C
        JZ lsnl
        JC luom
        LXI H,lwpn
        LDA leee
        ANA A
        JNZ lyqo
        LXI H,larp
lyqo:   CALL lmhi
        CALL lkjh
        LXI H,lcsq
        LXI B,letr
        CALL lgus
luez:   LXI H,livt
        LDA leee
        ANA A
        JNZ lkwu
        LXI H,lszy
lkwu:   CALL lmhi
        LXI H,lycb
        CALL laec
        PUSH PSW
        LDA lolj
        STA lmxv
        LXI H,loyw
        CALL laec
        MOV D,A
        POP PSW
        MOV E,A
        PUSH D
        CALL lqzx
        CALL lkvu
        POP D
        MVI A,001h
        STA lsay
lgvs:   STA lubz
        STA lwca
        XRA A
        STA lydb
        STA lafc
lupm:   STA lcgd
        MVI C,000h
lomj:   CALL lehe
        MOV A,C
        INR A
        JZ lgif
        DCR A
        JNZ lijg
        LDA lcgd
        ANA A
        JNZ lkkh
        LDA lafc
        ANA A
        JNZ lmli
        INR A
        STA lafc
        MOV A,D
        MOV D,E
        MOV E,A
        JMP lomj
lijg:   PUSH D
        CALL lqnk
        MVI C,000h
        CALL lehe
        MOV A,C
        INR A
        JZ lgif
        DCR A
        JZ lkkh
        CALL lqnk
        POP D
        MOV A,D
        CMP E
        JNZ lsol
        LDA lcgd
        ANA A
        JNZ lsol
        INR A
        JMP lupm
lgif:   CALL lkvu
        LDA leee
        ANA A
        JZ lwqn
        LXI H,lyro
        JMP lasp
lwqn:   LDA lsay
        LXI H,lctq
        ANA A
        JZ lasp
        LXI H,leur
        JMP lasp
liyt:   INR A
        JMP lgvs
luqm:   PUSH PSW
        LDA lsay
        ANA A
        JNZ liwt
        POP PSW
        CPI 00Ch
        JMP lkxu
liwt:   POP PSW
        ANA A
lkxu:   JNZ lmyv
        MOV A,B
        SUB H
        CMP E
        JZ lozw
        JNC lqax
        CALL lsby
        JM lozw
lqax:   MOV A,B
        SUB H
        CMP D
        JZ lucz
        JNC lwda
        CALL lsby
        JM lucz
lwda:   LXI H,lyeb
        CALL lmhi
        CALL lagc
        CALL lagc
        CALL lagc
        JMP lchd
lonj:   CPI 00Ch
        MVI L,007h
        MVI B,00Ch
        JNZ leie
        JMP lgjf
lqnk:   LDA lsay
        ANA A
        PUSH PSW
        JNZ lchd
        CALL likg
lchd:   PUSH D
        CALL lkvu
        LXI H,lklh
        CALL lmhi
        POP D
        CALL lmmi
        MOV C,A
        MVI L,013h
        MVI B,018h
        LDA lsay
        ANA A
        MOV A,C
        JZ lonj
        ANA A
        JNZ leie
lgjf:   LDA lydb
        ANA A
        JNZ lwda
leie:   MOV A,C
        CALL lqok
        DCR A
        JM lwda
        MOV H,C
        CALL lmmi
        CMP H
        JZ lwda
        MOV C,A
        LDA lspl
        ANA A
        MOV A,C
        JNZ luqm
lmyv:   CALL lqok
        JM lwda
        LDA lsay
        ANA A
        MOV A,C
        JNZ lwrn
        SUB H
        JNC lyso
        ADI 018h
        JMP lyso
lwrn:   SUB H
lyso:   CMP E
        JZ lozw
        CMP D
        JNZ lwda
lucz:   MOV A,E
        MOV E,D
        MOV D,A
        LDA lwca
        DCR A
        STA lwca
lozw:   PUSH B
        PUSH H
        LXI B,latp
        LXI H,lcuq
        LDA lsay
        ANA A
        JNZ levr
        LXI H,0097Ch
levr:   LDA lwca
        ANA A
        JZ lgws
        DAD B
lgws:   SHLD lifg
        MVI C,02Ah
        CALL lixt
        POP H
        POP B
        MOV L,C
        MOV B,H
        LDA lsay
        ANA A
        JNZ lkyu
        MOV A,H
        CPI 00Ch
        JNZ lmzv
        MVI A,001h
        STA lydb
        JMP lmzv
lkyu:   CALL loaw
lmzv:   MOV A,C
        SUI 00Ch
        JNC lqbx
        ADI 018h
lqbx:   MOV L,A
        MOV A,B
        SUI 00Ch
        JNC lscy
        ADI 018h
lscy:   MOV H,A
        CALL likg
        LDA ludz
        INR A
        INR A
        STA ludz
        CALL lwea
        LDA ludz
        DCR A
        DCR A
        STA ludz
        CALL likg
        MOV H,B
        MOV L,C
        MVI C,000h
        LDA lsay
        ANA A
        JNZ lyfb
        MVI C,00Ch
lyfb:   MOV A,L
        CMP C
        JNZ lahc
        MVI L,018h
lahc:   CALL lcid
        PUSH B
        PUSH D
        CALL lkvu
        POP D
        POP B
        MVI E,000h
        POP PSW
        RNZ
        CALL likg
        RET
lsby:   PUSH H
        MOV A,H
lgkf:   DCR A
        MOV H,A
        CALL lqok
        DCR A
        JP leje
        MOV A,H
        CMP L
        JNC lgkf
leje:   POP H
        RET
loaw:   MOV A,H
        ANA A
        RNZ
        INR A
        STA lydb
        RET
lsnl:   LXI H,lilg
        CALL lmhi
        MVI A,004h
lkmh:   CALL lagc
        DCR A
        JNZ lkmh
        CALL lede
        JMP lmni
luom:   LXI H,looj
        LDA leee
        ANA A
        JNZ lqpk
        LXI H,lsql
lqpk:   CALL lmhi
lyto:   MVI D,006h
lwsn:   LDA luyz
        ANI 0FCh
        CPI 0FCh
        JNZ lurm
        DCR D
        JNZ lwsn
        JMP lyto
lurm:   MOV A,D
        STA lmxv
        LXI H,loyw
        LXI B,000F8h
        CALL lgus
lygb:   LXI H,laup
        LDA leee
        ANA A
        PUSH PSW
        JNZ lcvq
        LXI H,lghf
lcvq:   CALL lmhi
        POP PSW
        JNZ lewr
        CALL lkjh
lewr:   LXI H,lmki
        LDA lmxv
        CALL lqmk
        LDA leee
        ANA A
        JNZ lgxs
        CALL lkjh
lgxs:   LXI H,lcsq
        LDA lolj
        CALL lqmk
        LHLD lmxv
        MOV D,L
        MOV E,H
        PUSH D
        CALL lkvu
        POP D
        XRA A
        STA lsay
        LDA leee
        ANA A
        JZ liyt
        DCR A
        STA lcgd
        STA lydb
lglf:   MOV C,A
        MOV B,A
        CALL lkzu
        JNZ lmav
        INR A
        CALL lkzu
        JNZ lobw
        LDA lcgd
        JZ lmli
lkkh:   LXI H,lqcx
        CALL lmhi
lmli:   LXI H,lsdy
        CALL lmhi
        LDA leee
        ANA A
        JZ lsol
        LDA lsay
        ANA A
        JZ luez
        LXI H,lwfa
        CALL lmhi
        CALL lkjh
lsol:   LDA lsay
        ANA A
        JZ luez
        CALL lqzx
        JMP lygb
lmav:   LHLD laic
        CALL loaw
        CALL lwea
        CALL lcid
lobw:   LHLD lcjd
        CALL loaw
        CALL lwea
        CALL lcid
        MOV A,C
        INR A
        JZ leke
        LDA lubz
        ANA A
        JNZ lkkh
        MOV A,D
        CMP E
        JNZ lsol
        LDA lcgd
        ANA A
        JNZ lsol
        INR A
        STA lcgd
        DCR A
        JMP lglf
leke:   CALL lkvu
        LXI H,limg
lasp:   CALL lmhi
        LDA lgff
        ANA A
        JNZ lknh
        LDA leee
        ANA A
        JZ lmoi
        LDA lsay
        ANA A
        JNZ lmoi
        LXI H,lopj
        JMP lqqk
lmoi:   LXI H,lsrl
lqqk:   CALL lmhi
lknh:   LXI H,lusm
        CALL lmhi
        CALL lkjh
        CPI 030h
        JZ lwtn
        JMP lyuo
lkzu:   STA lubz
        CALL lehe
        MOV A,D
        MOV D,E
        MOV E,A
        CALL lehe
        MOV A,C
        ANA A
        RET
lehe:   MVI H,000h
        MVI A,001h
        STA lspl
        MOV A,H
lqdx:   CALL lqok
        JM lavp
        JZ lavp
        MOV A,H
        ADD D
        MOV L,A
        MOV A,H
        CPI 012h
        JNC lcwq
        XRA A
        STA lspl
        MOV A,H
        ANA A
        JNZ lexr
        LDA lydb
        ANA A
        JNZ lavp
lexr:   MOV A,L
        CALL lqok
        JM lavp
lwga:   CALL lcid
        PUSH D
        PUSH H
        MOV A,B
        ANA A
        JNZ lgys
        LDA lubz
        ANA A
        JNZ lgys
        SHLD lizt
        MOV D,E
        INR B
        CALL loaw
        CALL lehe
        DCR B
leyr:   POP H
        MOV A,H
        CPI 012h
        JNC lkau
        XRA A
        STA lspl
lkau:   MOV A,H
        ANA A
        JNZ lmbv
        STA lydb
lmbv:   POP D
        CALL locw
lavp:   INR H
        MOV A,H
        CPI 018h
        JNZ lqdx
        RET
lcwq:   LDA lspl
        ANA A
        JNZ lsey
        MOV A,L
        CPI 018h
        JNC lavp
        JMP lexr
lsey:   INR A
        STA lspl
        CPI 002h
        MOV A,L
        JZ lufz
        CPI 019h
        JNC lavp
lufz:   CPI 018h
        JC lexr
        MVI L,018h
        JMP lwga
lgys:   PUSH B
        XRA A
        MOV D,A
        MOV E,A
        MOV B,A
        MVI C,00Ch
        MOV A,C
loqj:   CALL lqok
        JZ lyhb
        JM lajc
        MOV A,B
        ANA A
        JNZ lckd
        MOV A,C
        CPI 012h
        JC lckd
        CALL lqok
        ADD E
        MOV E,A
lckd:   INR D
        MOV A,D
        CPI 006h
        JC lele
        MOV A,B
        CPI 00Fh
        JC lele
        MVI E,000h
        JMP lgmf
lyhb:   MOV A,B
        ANA A
        JMP ling
lajc:   CMA
        INR A
        ADD B
        INR B
        DCR B
        MOV B,A
ling:   JZ lkoh
        MOV A,D
        ANI 007h
        ADD E
        MOV E,A
lkoh:   MVI D,000h
lele:   MOV A,C
        INR A
        CPI 018h
        JNZ lmpi
        XRA A
lmpi:   MOV C,A
        CPI 00Ch
        JNZ loqj
        XRA A
        MOV D,A
        MOV B,A
        MOV C,A
lawp:   CALL lqok
        JZ lqrk
        JM lssl
        INR B
        INR E
        INR E
        MOV A,D
        CPI 003h
        JC lutm
        RAR
        ADD E
        MOV E,A
        MOV A,D
        CMA
        ADD C
        CALL lqok
        JZ lutm
        JM lutm
        MOV H,A
        MOV A,B
        CPI 003h
        JMP lwun
lqrk:   MOV A,D
        CPI 003h
        JC lutm
        CMA
        ADD C
        CALL lqok
        JZ lutm
        JM lutm
        MOV H,A
        MOV A,B
        CPI 002h
lwun:   JC lutm
        MOV A,D
        RAR
        ADD H
        ADD E
        MOV E,A
lutm:   MVI D,000h
        JMP lyvo
lssl:   INR D
lyvo:   INR C
        MOV A,C
        CPI 018h
        JC lawp
        DCR B
        JP lcxq
        MVI E,0FFh
        JMP lgmf
lcxq:   LDA ludz
        RLC
        RLC
        ADD E
        MOV E,A
lgmf:   POP B
        MOV A,C
        CMP E
        JNC leyr
        MOV C,E
        LHLD lizt
        SHLD laic
        POP H
        SHLD lcjd
        JMP lmbv
lcid:   PUSH D
        XCHG
        LXI H,lokj
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
locw:   PUSH D
        XCHG
        LXI H,lokj
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
lqok:   PUSH H
        LXI H,lokj
        ADD L
        MOV L,A
        MOV A,M
        ANA A
        POP H
        RET
lulm:   PUSH B
        MVI A,091h
        STA lgzs
        MVI C,000h
        LDA liat
        MVI B,008h
lmcv:   RAR
        JNC lkbu
        INR C
        DCR B
        JNZ lmcv
        LDA lodw
        MVI B,003h
lqex:   RAR
        JNC lkbu
        INR C
        DCR B
        JNZ lqex
lkbu:   MVI A,082h
        STA lgzs
        MOV A,C
        CMA
        ADI 00Ch
        POP B
        RET
lqzx:   MVI D,00Ch
        LXI H,lgff
        MOV E,M
        LDA ludz
        MOV M,A
        MOV A,E
        STA ludz
        INX H
        LXI B,lsfy
lyib:   LDAX B
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
        JNZ lyib
        RET
likg:   PUSH B
        PUSH D
        PUSH H
        MVI D,00Ch
        LXI H,lokj
        LXI B,lsfy
lcld:   LDAX B
        MOV E,M
        MOV M,A
        MOV A,E
        STAX B
        INX B
        INX H
        DCR D
        JNZ lcld
        POP H
        POP D
        POP B
        RET
lmmi:   CALL lkjh
        CPI 060h
        JC leme
        SUI 020h
leme:   CPI 030h
        JC lmmi
        CPI 04Fh
        JNC lmmi
        CPI 041h
        JNC lgnf
        CPI 03Ah
        JNC lmmi
lgnf:   MOV C,A
        CALL lixt
        SUI 030h
        CPI 00Ah
        RC
        SUI 007h
        RET
lwea:   PUSH B
        PUSH D
        PUSH H
        MOV B,H
        MVI C,0FFh
        CALL liog
        CALL lkph
        MVI A,050h
        CALL lmqi
        POP H
        PUSH H
        MOV B,L
        MVI C,000h
        CALL liog
        MVI A,050h
        CALL lmqi
        POP H
        POP D
        POP B
        RET
liog:   MOV A,B
        RLC
        CPI 018h
        JNC lorj
        CALL lefe
        LXI H,lqsk
lywo:   LXI D,lihg
        ADD H
        JMP lstl
lorj:   CPI 030h
        JNZ luum
        XRA A
        LXI H,lwvn
        JMP lywo
luum:   SUI 018h
        CALL lefe
        MOV D,A
        MVI A,0BDh
        SUB D
        MVI L,024h
        LXI D,00009h
lstl:   MOV H,A
        LDA lsay
        ANA A
        JZ laxp
        MOV A,B
        CPI 00Ch
lmdv:   JNZ lcyq
        MOV A,C
        ANA A
        JNZ lcyq
        MVI B,018h
lcyq:   MOV A,B
        CALL lqok
        ADD C
        JZ lezr
lgas:   DAD D
        DCR A
        JNZ lgas
lezr:   LXI D,00108h
        LXI B,laqp
        LDA lsay
        ANA A
        JNZ libt
        LXI B,lunm
libt:   MVI A,004h
lkcu:   CALL lkph
        PUSH PSW
        MVI A,014h
        CALL lmqi
        PUSH B
        CALL leqr
        POP B
        CALL lsxy
        MVI A,03Ch
        CALL lmqi
        POP PSW
        DCR A
        JNZ lkcu
        RET
laxp:   LDA leee
        ANA A
        JNZ lcyq
        MOV A,B
        ANA A
        JMP lmdv
lefe:   CPI 00Ch
        JC loew
        INR A
loew:   RET
lkph:   PUSH D
        PUSH H
lqfx:   MVI M,000h
        INX H
        DCR E
        JNZ lqfx
        POP H
        POP D
        RET
lqmk:   LXI B,letr
        MOV D,A
        MVI A,05Eh
        CALL lsgy
        CALL lagc
        RET
laec:   LXI B,000F8h
        XRA A
        CALL luhz
        CALL lagc
        RET
lgus:   MVI A,00Bh
        LXI D,0041Fh
lalc:   PUSH B
        LXI B,lwia
        CALL leqr
        POP B
        PUSH PSW
        CPI 00Bh
        JZ lyjb
        MOV A,B
        ANA A
        JZ lyjb
        PUSH H
        PUSH B
        PUSH D
        LXI B,loij
        LXI D,001FBh
        DAD D
        LXI D,00205h
        CALL leqr
        INR H
        INR H
        LXI D,00114h
        CALL leqr
        POP D
        POP B
        POP H
lyjb:   MVI A,032h
        CALL lmqi
        POP PSW
        DAD B
        DCR A
        JNZ lalc
        MOV A,B
        ANA A
        JNZ lcmd
        LXI B,00508h
        JMP lene
lcmd:   LXI B,lgof
lene:   DAD B
        LXI B,lwia
        CALL leqr
lkvu:   LXI H,lipg
        LXI D,lkqh
        LXI B,loij
        CALL leqr
        LXI H,000B4h
        SHLD lifg
        RET
lskl:   LHLD lccd
        MVI C,010h
losj:   MOV A,H
        DAD H
        ANI 060h
        JPE lmri
        INX H
lmri:   DCR C
        JNZ losj
        SHLD lccd
        RET
luhz:   PUSH PSW
        PUSH B
        PUSH H
lqtk:   CALL lskl
        MOV A,H
        ANI 007h
        JZ lqtk
        CPI 007h
        JZ lqtk
        MOV D,A
        MOV A,L
        ANI 007h
        JZ lqtk
        CPI 007h
        JZ lqtk
        MOV E,A
        POP H
        POP B
        CALL lkjh
        MOV A,E
        STA lolj
        POP PSW
lsgy:   PUSH D
        LXI D,0041Fh
        PUSH B
lgpf:   STA lsul
        ANI 007h
        JNZ luvm
        POP B
        PUSH B
        MOV A,B
        ANA A
        JNZ lwwn
        DAD B
lwwn:   LXI B,lyxo
        JMP layp
luvm:   CPI 001h
        JNZ lczq
        LXI B,lear
        JMP layp
lczq:   CPI 002h
        JNZ lgbs
        LXI B,lict
        JMP layp
lgbs:   CPI 003h
        JNZ lkdu
        LXI B,lmev
        JMP layp
lkdu:   CPI 004h
        JNZ lofw
        LXI B,lqgx
        JMP layp
lofw:   CPI 005h
        JNZ lshy
        LXI B,luiz
        JMP layp
lshy:   CPI 006h
        JNZ lwja
        LXI B,lykb
        JMP layp
lwja:   POP B
        PUSH B
        MOV A,B
        ANA A
        JZ lamc
        DAD B
lamc:   LXI B,lwia
layp:   CALL leqr
        MVI A,00Ah
        CALL lmqi
        LDA lsul
        INR A
        POP B
        PUSH B
        INR B
        JNZ lcnd
        DCR A
        DCR A
        CPI 00Eh
        JMP leoe
lcnd:   CPI 050h
leoe:   JNZ lgpf
        POP B
        POP PSW
        PUSH PSW
        LXI B,0010Ah
        DAD B
        PUSH H
        LXI H,liqg
        LXI B,00014h
lkrh:   DAD B
        DCR A
        JNZ lkrh
        MOV B,H
        MOV C,L
        POP H
        LXI D,0020Ah
        CALL leqr
        POP PSW
        RET
liut:   LXI H,lmki
        LXI D,0041Fh
        LXI B,lwia
        CALL leqr
        LXI H,lycb
        LXI B,lwia
        CALL leqr
        RET
lagc:   PUSH PSW
        PUSH H
        LXI H,lmsi
        SHLD lqwx
        CALL lsxy
        MVI L,048h
        SHLD lqwx
        CALL lsxy
        LXI H,lotj
        SHLD lqwx
        POP H
        POP PSW
        RET
luzz:   SHLD lifg
        CALL lixt
        DAD D
        INR C
        MOV A,C
        CMP B
        JC luzz
        RET
lojj:   CALL leqr
        MVI A,01Ah
lsvl:   INR H
        LXI B,lquk
        CALL leqr
        DCR A
        JNZ lsvl
        INR H
        RET
lapp:   DAD B
        MVI A,0B2h
luwm:   MOV M,E
        INR L
        DCR A
        JNZ luwm
        RET
lumm:   LXI D,00102h
        MVI A,064h
lyyo:   LXI B,lwxn
        CALL leqr
        INR L
        INR L
        DCR A
        JNZ lyyo
        RET
lwnn:   LXI D,0010Bh
        LXI B,lazp
        INR H
        CALL leqr
        MVI D,002h
        MVI A,005h
lebr:   INR H
        LXI B,lcaq
        CALL leqr
        INR H
        DCR A
        JNZ lebr
        INR H
        MVI D,001h
        LXI B,lcaq
        CALL leqr
        INR H
        LXI B,lgcs
        CALL leqr
        RET
litt:   LXI D,0010Bh
        LXI B,lidt
        INR H
        CALL leqr
        MVI D,002h
        MVI A,005h
lmfv:   INR H
        LXI B,lkeu
        CALL leqr
        INR H
        DCR A
        JNZ lmfv
        INR H
        MVI D,001h
        LXI B,lkeu
        CALL leqr
        INR H
        LXI B,logw
        CALL leqr
        RET
leqr:   PUSH PSW
        PUSH B
        MOV C,D
lsiy:   MOV B,E
lqhx:   XTHL
        MOV A,M
        INX H
        XTHL
        MOV M,A
        INX H
        DCR B
        JNZ lqhx
        INR H
        MOV A,L
        SUB E
        MOV L,A
        DCR C
        JNZ lsiy
        MOV A,H
        SUB D
        MOV H,A
        POP B
        POP PSW
        RET
lmqi:   PUSH B
        MVI C,000h
lujz:   DCR C
        JNZ lujz
        DCR A
        JNZ lujz
        POP B
        RET
        DB 0F5h, 03Eh, 000h, 0CDh, 0B9h, 00Ah, 0F1h, 03Dh, 0C2h, 0C6h
        DB 00Ah, 0C9h
lwtn:   CALL lede
        LXI H,lwka
        CALL lylb
        LXI H,lanc
        CALL lylb
        LXI H,00019h
        SHLD lifg
        LXI H,lcod
        MVI D,006h
lgqf:   CALL lepe
        INX H
        DCR D
        JNZ lgqf
        CALL lkjh
        CALL lede
        JMP lirg

lkgh:   DB 02Ah, 020h, 041h, 044h, 044h, 020h, 02Ah, 000h, 020h, 02Ah
        DB 020h, 031h, 039h, 038h, 039h, 020h, 02Ah
lksh:   DB 00Ah, 00Ah, 00Ah, 020h, 020h, 02Ah, 020h, 041h, 044h, 044h
        DB 020h, 02Ah, 020h, 020h, 02Dh, 020h, 020h, 0FCh, 0F4h, 0EFh
        DB 020h, 000h
lcod:   DB 0F0h, 0F2h, 0EFh, 0E7h, 0F2h, 0E1h, 0EDh, 0EDh, 0EEh, 0EFh
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
louj:   DB 0FFh, 0FFh, 060h, 070h, 030h, 038h, 038h, 018h, 018h, 038h
        DB 038h, 030h, 070h, 060h, 0FFh, 0FFh, 0FFh, 0FFh, 000h, 000h
        DB 00Fh, 00Bh, 013h, 013h, 023h, 03Fh, 043h, 0C3h, 000h, 000h
        DB 0FFh, 0FFh, 0FFh, 0FFh, 000h, 000h, 078h, 064h, 066h, 066h
        DB 066h, 066h, 064h, 078h, 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh
        DB 000h, 000h, 0F0h, 0C8h, 0CCh, 0CCh, 0CCh, 0CCh, 0C8h, 0F0h
        DB 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh, 006h, 00Eh, 00Ch, 01Ch
        DB 01Ch, 018h, 018h, 01Ch, 01Ch, 00Ch, 00Eh, 006h, 0FFh, 0FFh
lepe:   PUSH H
        LXI H,lksh
        CALL lmti
        POP H
lmti:   MOV A,M
        ANI 07Fh
        RZ
        INX H
        MOV C,A
        CALL lixt
        JMP lmti
lylb:   MVI A,008h
lwyn:   PUSH PSW
        LXI B,louj
        LXI D,00510h
luxm:   PUSH D
lswl:   LDAX B
        INX B
        MOV M,A
        INX H
        DCR E
        JNZ lswl
        LXI D,000F0h
        DAD D
        POP D
        DCR D
        JNZ luxm
        INR H
        POP PSW
        DCR A
        JNZ lwyn
        RET

list:   DB 031h, 020h, 02Dh, 020h, 069h, 067h, 072h, 061h, 020h, 073h
        DB 020h, 070h, 061h, 072h, 074h, 06Eh, 065h, 072h, 06Fh, 06Dh
        DB 00Ah, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h, 020h
        DB 020h, 020h, 032h, 020h, 02Dh, 020h, 069h, 067h, 072h, 061h
        DB 020h, 073h, 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h, 074h
        DB 065h, 072h, 06Fh, 06Dh, 000h
lmwv:   DB 020h, 072h, 061h, 07Ah, 079h, 067h, 072h, 061h, 065h, 06Dh
        DB 020h, 070h, 072h, 061h, 077h, 06Fh, 020h, 068h, 06Fh, 064h
        DB 061h, 00Ah, 000h
lwba:   DB 020h, 062h, 072h, 06Fh, 073h, 061h, 06Ah, 020h, 073h, 06Eh
        DB 061h, 07Eh, 061h, 06Ch, 061h, 020h, 074h, 079h, 000h
lszy:   DB 020h, 062h, 065h, 06Ch, 079h, 065h, 020h, 062h, 072h, 06Fh
        DB 073h, 061h, 060h, 074h, 000h
lghf:   DB 020h, 07Eh, 065h, 072h, 06Eh, 079h, 065h, 020h, 062h, 072h
        DB 06Fh, 073h, 061h, 060h, 074h, 000h
lcfd:   DB 020h, 074h, 065h, 070h, 065h, 072h, 078h, 020h, 071h, 000h
lwpn:   DB 00Ah, 00Ah, 020h, 075h, 020h, 06Dh, 065h, 06Eh, 071h, 020h
        DB 06Dh, 065h, 06Eh, 078h, 07Bh, 065h, 00Ah, 020h, 074h, 065h
        DB 062h, 065h, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h
        DB 078h, 000h
looj:   DB 00Ah, 00Ah, 020h, 075h, 020h, 06Dh, 065h, 06Eh, 071h, 020h
        DB 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h, 045h, 058h
        DB 043h, 055h, 05Ah, 045h, 020h, 04Dh, 045h, 000h
larp:   DB 00Ah, 00Ah, 020h, 075h, 020h, 062h, 065h, 06Ch, 079h, 068h
        DB 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h, 069h
        DB 06Dh, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h, 078h
        DB 000h
lsql:   DB 00Ah, 00Ah, 020h, 075h, 020h, 07Eh, 065h, 072h, 06Eh, 079h
        DB 068h, 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 00Ah, 020h
        DB 069h, 06Dh, 020h, 06Eh, 061h, 07Eh, 069h, 06Eh, 061h, 074h
        DB 078h, 000h
lilg:   DB 00Ah, 00Ah, 020h, 070h, 06Fh, 072h, 06Fh, 077h, 06Eh, 075h
        DB 02Eh, 00Ah, 020h, 070h, 072h, 069h, 064h, 065h, 074h, 073h
        DB 071h, 020h, 070h, 06Fh, 077h, 074h, 06Fh, 072h, 069h, 074h
        DB 078h, 000h
livt:   DB 020h, 062h, 072h, 06Fh, 073h, 061h, 06Ah, 000h
laup:   DB 020h, 062h, 072h, 06Fh, 073h, 061h, 060h, 000h
leur:   DB 020h, 062h, 065h, 06Ch, 079h, 065h, 020h, 077h, 079h, 069h
        DB 067h, 072h, 061h, 06Ch, 069h, 000h
lctq:   DB 020h, 07Eh, 065h, 072h, 06Eh, 079h, 065h, 020h, 077h, 079h
        DB 069h, 067h, 072h, 061h, 06Ch, 069h, 000h
lsrl:   DB 00Ah, 020h, 070h, 06Fh, 07Ah, 064h, 072h, 061h, 077h, 06Ch
        DB 071h, 060h, 020h, 073h, 020h, 06Dh, 061h, 072h, 073h, 06Fh
        DB 06Dh, 021h, 000h
lopj:   DB 00Ah, 020h, 065h, 073h, 074h, 078h, 020h, 06Ch, 069h, 020h
        DB 076h, 069h, 07Ah, 06Eh, 078h, 020h, 06Eh, 061h, 020h, 06Dh
        DB 061h, 072h, 073h, 065h, 03Fh, 000h
lyeb:   DB 00Ah, 020h, 06Eh, 065h, 06Ch, 078h, 07Ah, 071h, 021h, 021h
        DB 021h, 000h
lqcx:   DB 00Ah, 020h, 062h, 06Fh, 06Ch, 078h, 07Bh, 065h, 000h
lsdy:   DB 020h, 068h, 06Fh, 064h, 06Fh, 077h, 020h, 06Eh, 065h, 020h
        DB 077h, 069h, 076h, 075h, 02Eh, 00Ah, 000h
lwfa:   DB 020h, 06Dh, 06Fh, 067h, 075h, 020h, 062h, 072h, 06Fh, 073h
        DB 061h, 074h, 078h, 03Fh, 00Ah, 000h
lklh:   DB 00Ah, 020h, 068h, 06Fh, 064h, 069h, 020h, 000h
limg:   DB 020h, 071h, 020h, 077h, 073h, 065h, 067h, 064h, 061h, 020h
        DB 067h, 06Fh, 077h, 06Fh, 072h, 069h, 06Ch, 02Ch, 00Ah, 020h
        DB 07Eh, 074h, 06Fh, 020h, 06Bh, 06Fh, 06Dh, 070h, 078h, 060h
        DB 074h, 065h, 072h, 020h, 075h, 06Dh, 06Eh, 065h, 065h, 00Ah
        DB 020h, 07Eh, 065h, 06Ch, 06Fh, 077h, 065h, 06Bh, 061h, 020h
        DB 021h, 000h
lyro:   DB 020h, 071h, 020h, 070h, 06Fh, 07Eh, 065h, 06Dh, 075h, 02Dh
        DB 074h, 06Fh, 020h, 070h, 072h, 06Fh, 069h, 067h, 072h, 061h
        DB 06Ch, 02Eh, 02Eh, 02Eh, 000h
lusm:   DB 00Ah, 00Ah, 020h, 065h, 07Dh, 065h, 020h, 070h, 061h, 072h
        DB 074h, 069h, 060h, 020h, 03Fh, 000h
laop:   DB 0FCh, 078h, 078h, 078h, 078h, 078h, 078h, 078h, 07Fh, 07Fh
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
lyxo:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
lear:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 01Fh, 010h, 010h
        DB 010h, 010h, 020h, 020h, 020h
lgts:   DB 020h, 020h, 040h, 040h, 040h, 040h, 07Fh, 020h, 020h, 010h
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
lict:   DB 000h, 000h, 000h, 000h, 00Fh, 008h, 008h, 008h, 010h, 010h
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
lmev:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 002h
        DB 004h, 00Fh, 008h, 008h, 008h, 008h, 008h, 008h, 008h
lcpq:   DB 008h, 008h, 008h, 008h, 008h, 008h, 008h, 00Fh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0FFh, 003h, 005h, 009h, 0F1h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 012h, 014h, 018h, 0F0h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lqgx:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Fh, 008h, 008h
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
luiz:   DB 000h, 000h, 000h, 001h, 001h, 001h, 001h, 001h, 002h, 002h
        DB 002h, 002h, 002h, 004h, 004h, 004h, 004h, 007h, 002h, 002h
        DB 001h, 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 000h, 000h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 0FEh, 003h, 003h, 002h, 002h
        DB 004h, 004h, 004h
labc:   DB 004h, 004h, 008h, 008h, 008h, 008h, 0F8h, 005h, 005h, 003h
        DB 003h, 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 080h, 080h, 040h, 040h
        DB 040h, 040h, 040h, 080h, 080h, 080h, 080h, 080h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h
lykb:   DB 000h, 000h, 000h, 000h, 001h, 001h, 001h, 001h, 002h, 002h
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
lwia:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
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
lquk:   DB 0FFh, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0FFh
lmii:   DB 0FFh, 0AAh, 0D5h, 0AAh, 0D5h, 0AAh
lwxn:   DB 0D5h, 0ABh
lazp:   DB 0FFh, 080h, 080h, 09Fh, 090h, 090h, 090h, 091h, 092h, 094h
        DB 09Ch
lcaq:   DB 0FFh, 000h, 000h, 0FFh, 000h, 03Ch, 0C3h, 000h, 000h, 000h
        DB 000h, 0FFh, 000h, 000h, 0FFh, 000h, 000h, 000h, 081h, 042h
        DB 024h, 03Ch
lqkk:   DB 0FFh, 0ABh, 055h, 0ABh, 055h, 0ABh, 055h, 0ABh
lgcs:   DB 0FFh, 001h, 001h, 0F9h, 009h, 009h, 009h, 089h, 049h, 029h
        DB 039h
lmvv:   DB 0ABh, 0D5h, 0AAh, 0D5h, 0AAh, 0D5h, 0AAh, 0FFh
lidt:   DB 09Ch, 094h, 092h, 091h, 090h, 090h, 090h, 09Fh, 080h, 080h
        DB 0FFh
lkeu:   DB 000h, 000h, 000h, 000h, 0C3h, 03Ch, 000h, 0FFh, 000h, 000h
        DB 0FFh, 03Ch, 024h, 042h, 081h, 000h, 000h, 000h, 0FFh, 000h
        DB 000h, 0FFh
loww:   DB 0ABh, 055h, 0ABh, 055h, 0ABh, 055h, 0ABh, 0FFh
logw:   DB 039h, 029h, 049h, 089h, 009h, 009h, 009h
liqg:   DB 0F9h, 001h, 001h, 0FFh
laqp:   DB 03Ch, 07Eh, 0FFh, 0E7h, 0E7h, 0FFh, 07Eh, 03Ch
lunm:   DB 03Ch, 042h, 081h, 099h, 099h, 081h, 042h, 03Ch, 080h, 080h
        DB 080h, 080h, 081h, 081h, 080h, 080h, 080h, 080h, 001h, 001h
        DB 001h, 001h, 081h, 081h, 001h, 001h, 001h, 001h, 080h, 080h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 098h, 098h, 019h, 019h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 080h, 080h
        DB 080h, 080h, 081h, 081h, 080h, 080h, 098h, 098h, 019h, 019h
        DB 001h, 001h, 081h, 081h, 001h, 001h, 001h, 001h, 098h, 098h
        DB 080h, 080h, 080h, 080h, 080h, 080h, 098h, 098h, 019h
lkqh:   DB 019h, 001h, 001h, 001h, 001h, 001h, 001h, 019h, 019h, 098h
        DB 098h, 080h, 080h, 081h, 081h, 080h, 080h, 098h, 098h, 019h
        DB 019h, 001h, 001h, 081h, 081h, 001h, 001h, 019h, 019h, 098h
        DB 098h, 080h, 080h, 098h, 098h, 080h, 080h, 098h, 098h, 019h
        DB 019h, 001h, 001h, 019h, 019h, 001h, 001h, 019h, 019h
lmji:   DB 00Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0F1h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 0FFh, 0FFh
END