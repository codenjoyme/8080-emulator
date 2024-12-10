        CPU  8080
        .ORG 00000h
lugz    EQU 0C000h
lstl    EQU 09000h
lmri    EQU 09004h
lkuu    EQU 0B40Ah
luhz    EQU 0C010h
lwia    EQU 07A13h
lsml    EQU 09E14h
lgxs    EQU 09A14h
lqcx    EQU 0A414h
lsul    EQU 09418h
lear    EQU 09618h
lkdu    EQU 0BC18h
lofw    EQU 09820h
loyw    EQU 0C427h
lkgh    EQU 0C037h
lgnf    EQU 0A074h
lkph    EQU 0A07Ch
lorj    EQU 0A084h
lcod    EQU 0B898h
lwyn    EQU 0A0C0h
losj    EQU 090FAh
lqtk    EQU 090FEh
lehe    EQU 0FF00h
lalc    EQU 0BF00h
lcgd    EQU 0FF01h
lgif    EQU 0FF02h
lafc    EQU 0FF03h
lyno    EQU 09B0Ah
layp    EQU 07B13h
leqr    EQU 09B14h
lqxx    EQU 0B314h
lczq    EQU 09318h
lgbs    EQU 0B918h
lict    EQU 0BB18h
lsvl    EQU 0991Ch
lykb    EQU 0AB20h
luyz    EQU 09128h
lyab    EQU 0B928h
lcdd    EQU 0A130h
lujz    EQU 0AF30h
lgff    EQU 0A154h
leme    EQU 0C170h
liqg    EQU 099A0h
lkhh    EQU 0A1CCh
lccd    EQU 08FE1h
lmuv    EQU 0AFE6h
list    EQU 09DEEh
lqwx    EQU 0AFEEh
lcld    EQU 08FF1h
lakc    EQU 08FF2h
lsfy    EQU 0FFFEh
lkcu    EQU 0FFFFh
laxp:   JMP labc
lcbq:   LXI H,00000h
        SHLD lccd
        LXI H,lede
        SHLD lgef
        LXI H,00700h
        SHLD lifg
        MVI C,01Fh
        CALL lkgh
        MVI A,005h
        STA lmhi
        XRA A
        STA loij
        STA lqjk
        STA lskl
        MVI A,03Ah
        STA lulm
        CALL lwmn
        LXI H,lyno
        LXI D,laop
        CALL lcpq
        LXI H,leqr
        LXI D,lgrs
        CALL lcpq
        LXI H,list
        LXI D,lktu
        CALL lcpq
        LXI H,lmuv
        LXI D,lovw
        CALL lcpq
        LXI H,lqwx
        LXI D,lsxy
        CALL lcpq
        LXI H,luyz
        CALL lwza
        LXI H,lyab
        CALL lwza
libt:   LXI D,lede
        CALL lacc
        LXI H,lcdd
        LXI D,leee
        CALL lcpq
        LXI H,lgff
        LXI D,ligg
        CALL lcpq
        LXI H,lkhh
        LXI D,lmii
        NOP
        NOP
        NOP
        CALL lojj
        LDA lqkk
        CPI 008h
        JNZ lsll
        LXI H,lumm
        MVI D,002h
        MVI E,031h
lsll:   CPI 00Bh
        JNZ lwnn
        LXI H,lyoo
        MVI D,004h
        MVI E,032h
lwnn:   CPI 00Eh
        JNZ lapp
        LXI H,00900h
        MVI D,006h
        MVI E,033h
lapp:   CPI 011h
        JNZ lcqq
        LXI H,00600h
        MVI D,008h
        MVI E,034h
lcqq:   CPI 014h
        JNZ lerr
        LXI H,00400h
        MVI D,00Bh
        MVI E,035h
lerr:   CPI 017h
        JNZ lgss
        LXI H,00200h
        MVI D,00Eh
        MVI E,036h
lgss:   SHLD lifg
        MOV A,D
        STA litt
        LXI H,lkuu
        MOV A,E
        CALL lmvv
lwaa:   LHLD lgef
        LXI D,00170h
        DAD D
        SHLD lgef
        LDA lskl
        INR A
        DAA
        CPI 031h
        JZ loww
        STA lskl
        XCHG
        LXI H,lqxx
        PUSH D
        CALL lsyy
        POP D
        CALL luzz
        JMP lwaa
luzz:   CALL lacc
lojj:   MVI B,003h
lkih:   PUSH B
        LHLD lifg
        XCHG
        CALL lybb
        MVI A,001h
        CALL ladc
        CALL lced
        CALL lefe
        CALL lggf
        CALL lihg
        POP B
        DCR B
        JZ lojj
        PUSH B
        MVI A,002h
        CALL ladc
        MVI A,003h
        CALL ladc
        POP B
        JMP lkih
lwza:   MVI B,016h
lokj:   LXI D,lmji
        PUSH H
        CALL lcpq
        POP H
        MOV A,L
        ADI 008h
        MOV L,A
        DCR B
        JNZ lokj
        RET
lihg:   LDA lulm
        CPI 03Ah
        RNC
        CPI 039h
        JZ lqlk
        INR A
        STA lulm
        LXI H,lsml
        JMP lmvv
lacc:   LXI H,lqkk
        PUSH H
        LXI H,lunm
        MVI C,010h
lypo:   LDAX D
        MOV M,A
        INX H
        XTHL
        MOV M,A
        INX H
        XTHL
        INX D
        DCR C
        JNZ lypo
        POP H
        PUSH D
        POP B
        XCHG
        MVI L,028h
lesr:   MVI H,098h
lcrq:   XCHG
        LDAX B
        RRC
        RRC
        RRC
        RRC
        ANI 00Fh
        MOV M,A
        INX H
        XCHG
        CALL lmvv
        INR H
        XCHG
        LDAX B
        ANI 00Fh
        MOV M,A
        INX H
        XCHG
        CALL lmvv
        INR H
        INX B
        MOV A,H
        CPI 0B8h
        JNZ lcrq
        MOV A,L
        ADI 008h
        MOV L,A
        CPI 0D8h
        JNZ lesr
        MVI A,001h
        CALL lgts
        MVI A,018h
        STA liut
        LDA lkvu
        STA lmwv
        XRA A
        STA loxw
        STA lqyx
        INR A
        STA lszy
        LXI H,luaz
        MVI C,081h
lwba:   MVI M,000h
        INX H
        DCR C
        JNZ lwba
        RET
lmvv:   PUSH B
        PUSH D
        PUSH H
        LXI H,lycb
        MVI D,000h
        MVI C,003h
        MOV E,A
laec:   MOV A,E
        STC
        CMC
        RAL
        MOV E,A
        MOV A,D
        RAL
        MOV D,A
        DCR C
        JNZ laec
        DAD D
        XCHG
        POP H
        PUSH H
        MVI C,008h
lcfd:   LDAX D
        MOV M,A
        INX H
        INX D
        DCR C
        JNZ lcfd
        POP H
        POP D
        POP B
        RET
lmxv:   MOV A,M
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
        LXI H,lege
        PUSH D
        LXI D,00000h
        CPI 003h
        JC lghf
        CPI 005h
        JZ liig
        MOV A,B
        RAR
        JC liig
        MVI E,010h
        JMP liig
lghf:   MVI E,020h
        CPI 001h
        JZ lkjh
        MVI E,060h
lkjh:   MOV A,C
        RLC
        RLC
        RLC
        RLC
        ADD E
        MOV E,A
liig:   DAD D
        POP D
        PUSH D
        CALL lmki
        POP D
        INR D
        CALL lmki
        RET
lmki:   MVI C,008h
lsnl:   LDAX D
        XRA M
        STAX D
        INX D
        INX H
        DCR C
        JNZ lsnl
        RET
lkwu:   PUSH PSW
        CALL luom
        POP PSW
        XCHG
        CALL lwpn
        MVI C,005h
        MOV A,B
        ORA A
        JZ lyqo
lcsq:   LDAX D
        MOV M,A
        INX D
        INX H
        DCR C
        JNZ lcsq
        RET
lyqo:   MOV A,M
        STAX D
        INX D
        INX H
        DCR C
        JNZ lyqo
        RET
lgus:   MOV C,A
        MVI A,0FBh
letr:   ADI 005h
        DCR C
        JNZ letr
        PUSH D
        MVI D,000h
        MOV E,A
        DAD D
        POP D
        RET
luom:   LXI H,lqkk
        JMP lgus
lwpn:   LXI H,livt
        JMP lgus
ling:   LXI H,lunm
        JMP lgus
lgts:   PUSH PSW
        MVI B,001h
        CALL lkwu
        POP PSW
        CALL luom
        JMP lmxv
lsrl:   PUSH PSW
        CALL luom
        CALL lmxv
        POP PSW
        PUSH PSW
        CALL lwpn
        CALL lmxv
        POP PSW
        MVI B,001h
        JMP lkwu
lybb:   PUSH H
        LXI H,00000h
lqzx:   INX H
        CALL loyw
        JNZ lqzx
        POP H
        RET
lilg:   PUSH PSW
        CALL luom
        INX H
        LDA lsay
        ADD M
        MOV M,A
        CALL lubz
        INX H
        INX H
        LDA lwca
        ADD M
        MOV M,A
        CALL lubz
        POP PSW
        RET
lubz:   ORA A
        JM lydb
        CPI 004h
        RNZ
        DCX H
        INR M
        INX H
        MVI M,000h
        RET
lydb:   DCX H
        DCR M
        INX H
        MVI M,003h
        RET
lmyv:   PUSH B
        MVI A,091h
        STA lafc
        MVI A,0FBh
        STA lcgd
        LDA lehe
        ANI 03Fh
        MOV B,A
        LDA lgif
        ANI 003h
        RRC
        RRC
        ORA B
        CMA
        POP B
        RET
ladc:   STA lijg
        CALL luom
        SHLD lkkh
        LDA lijg
        CPI 002h
        PUSH H
        CNC lmli
        POP H
        MOV D,M
        INX H
        SHLD lomj
        INX H
        MOV E,M
        INX H
        SHLD lqnk
        INX H
        SHLD lsol
        XCHG
        SHLD lupm
        PUSH D
        CALL lwqn
        SHLD lyro
        POP D
        LDAX D
        CPI 005h
        JZ lctq
        CALL lwqn
        SHLD lyro
        INR L
        CALL leur
        MOV A,M
        ANI 00Fh
        CPI 006h
        JNC lgvs
        LHLD lyro
        CALL leur
        MOV A,M
        ANI 00Fh
        CPI 007h
        JZ lgvs
        JC lctq
        LDA lijg
        CPI 002h
        JC lgvs
        JMP lctq
lkxu:   LHLD lsol
        MVI M,005h
        JMP liwt
lctq:   LDA lijg
        CPI 002h
        JNC lkxu
        CALL lmyv
        CPI 024h
        JNZ lozw
        CALL lqax
        JMP lkxu
lozw:   CPI 030h
        JNZ lsby
        CALL lucz
        JMP lkxu
lwda:   LXI H,liut
        SUI 002h
        ADD L
        MOV L,A
        MOV A,H
        ACI 000h
        MOV H,A
        RET
lmli:   CALL lwda
        MOV A,M
        ORA A
        RZ
        CPI 0FFh
        JZ lyeb
        DCR A
        MOV M,A
        JZ lagc
lyeb:   POP H
        POP H
        RET
lagc:   LDA lijg
        CALL lgts
        POP H
        POP H
        RET
lwqn:   LHLD lupm
        XCHG
        LHLD lomj
        CALL lchd
        ADD D
        MOV D,A
        LHLD lqnk
        CALL lchd
        ADD E
        MOV E,A
        XCHG
        RET
lchd:   MOV A,M
        CPI 002h
        MVI A,000h
        RC
        INR A
        RET
leur:   LXI D,00000h
        MOV E,L
        MVI C,005h
leie:   STC
        CMC
        MOV A,E
        RAL
        MOV E,A
        MOV A,D
        RAL
        MOV D,A
        DCR C
        JNZ leie
        MOV C,H
        LXI H,lgjf
        DAD D
        MVI B,000h
        DAD B
        RET
luqm:   LHLD lyro
        XCHG
        LHLD lkkh
        MOV M,D
likg:   INX H
        MVI M,000h
        RET
lyso:   LHLD lyro
        XCHG
        LHLD lomj
        INX H
        MOV M,E
        JMP likg
lgvs:   LDA lijg
        CPI 002h
        JNC lklh
        CALL lmyv
        LHLD lsol
        CPI 030h
        JZ lucz
        CPI 024h
        JZ lqax
        CPI 004h
        JZ lmmi
        CPI 010h
        JZ lonj
        CPI 080h
        JZ lqok
        CPI 040h
        JZ lspl
        RET
lspl:   MVI M,004h
liwt:   CALL luqm
        XRA A
        STA lsay
        INR A
        STA lwca
        JMP lwrn
lqok:   MVI M,003h
        CALL luqm
        XRA A
        STA lsay
        DCR A
        STA lwca
        JMP lwrn
lonj:   MVI M,001h
        CALL lyso
        XRA A
        STA lwca
        DCR A
        STA lsay
        JMP latp
lmmi:   MVI M,002h
        CALL lyso
        XRA A
        STA lwca
        INR A
        STA lsay
        JMP latp
lwrn:   CALL lcuq
        XCHG
        LHLD lsol
        MOV A,M
        CPI 005h
        JZ levr
        LHLD lyro
        PUSH D
        CALL leur
        POP D
        MOV A,M
        CPI 007h
        JNZ lgws
        LDAX D
        ANI 00Fh
        MOV B,A
        CPI 007h
        JNZ lixt
        JMP lkyu
lgws:   LHLD lsol
        MOV A,M
        CPI 003h
        JZ lmzv
        LDAX D
        ANI 00Fh
        MOV B,A
        CPI 008h
        JNC lmzv
        CPI 006h
        JZ lmzv
        JMP lkyu
levr:   LDAX D
        ANI 00Fh
        MOV B,A
        CPI 007h
lixt:   JNC lmzv
        CPI 006h
        JNZ lkyu
        LHLD lsol
        MOV A,M
        CPI 005h
        JNZ lmzv
lkyu:   LDA lijg
        CPI 002h
        JC loaw
lcjd:   LXI H,lqbx
        LDA lijg
        CPI 003h
        JZ lscy
        LXI H,ludz
lscy:   CALL lwea
        CALL loyw
        JZ lyfb
        LXI H,lqkk
        CALL lwea
        LHLD lyro
        CALL loyw
        JZ lahc
        JMP lcid
lwea:   MOV D,M
        INX H
        CALL lchd
        ADD D
        MOV D,A
        INX H
        MOV E,M
        INX H
        CALL lchd
        ADD E
        MOV E,A
        LHLD lupm
        RET
lcid:   MOV A,B
        CPI 004h
        JZ leje
        CPI 005h
        JZ leje
        JMP lgkf
lcuq:   LDA lijg
        CALL lilg
        LHLD lkkh
        MOV D,M
        INX H
        INX H
        MOV E,M
        CALL lkmh
        XCHG
        SHLD lupm
        CALL leur
        RET
loaw:   MOV A,B
        ORA A
        JZ lgkf
        DCR A
        JZ lmni
        DCR A
        JZ looj
        DCR A
        JZ lqpk
        DCR A
        JZ lsql
        DCR A
        JZ lsql
        LXI H,lurm
        MOV A,M
        INX H
        ORA M
        JNZ lgkf
        LHLD lsol
        MOV A,M
        CPI 003h
        JZ lwsn
        JMP lgkf
lmni:   LDA lulm
        CPI 03Ah
        CNZ lqlk
        MVI A,030h
        STA lulm
        CALL lyto
        LXI H,laup
        CALL lcvq
        JMP lgkf
lqlk:   INR A
        STA lulm
        LXI H,loij
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
        LXI H,leqr
lsdy:   CALL lsyy
        MOV A,B
        CALL lsyy
        MOV A,C
        ORA A
        CNZ lewr
        RET
lewr:   MVI A,031h
        LXI H,lgxs
        JMP lmvv
looj:   MVI A,001h
        STA lqyx
        LXI H,liyt
        CALL lcvq
        LHLD lupm
        SHLD lkzu
        CALL lyto
        CALL lmav
        JMP lgkf
lqpk:   LXI H,lobw
        CALL lcvq
        LXI H,lmhi
        MOV A,M
        INR A
        DAA
        MOV M,A
        MOV B,A
        XRA A
        MVI C,000h
        LXI H,lqcx
        CALL lsdy
        CALL lyto
        JMP lgkf
        DB 07Fh, 07Fh, 07Fh, 07Fh, 000h
lsyy:   MOV E,A
        RRC
        RRC
        RRC
        RRC
lasp:   CALL luez
        MOV A,E
luez:   ANI 00Fh
        ADI 030h
        CALL lmvv
        INR H
        RET
lyto:   LHLD lupm
        PUSH H
        CALL leur
        MVI M,000h
        POP H
        CALL lwfa
        MVI A,000h
        CALL lmvv
        RET
lmav:   MVI A,00Ch
        CALL lmvv
        RET
latp:   CALL lcuq
        MOV A,M
        ANI 00Fh
        MOV B,A
        LDA lijg
        CPI 002h
        JC lygb
        MOV A,B
        CPI 008h
        JNC laic
        JMP lcjd
lyfb:   LHLD lsol
        MOV A,M
        CPI 003h
        JC laic
        JMP lmzv
lygb:   MOV A,B
        CPI 00Ah
        JNC lmzv
        CPI 009h
        JNZ leke
        LDA lqyx
        ORA A
        JNZ lglf
        JMP lmzv
leke:   CPI 008h
        JZ lglf
        CPI 006h
        JNC lgkf
        JMP loaw
lglf:   LXI H,limg
        CALL lcvq
        LHLD lupm
        PUSH H
        CALL leur
        MVI M,000h
        POP H
        CALL lwfa
        MVI A,000h
        CALL lmvv
        MVI A,00Dh
        CALL lmvv
        JMP lgkf
lwfa:   MOV A,H
        ADI 098h
        MOV H,A
        MOV A,L
        RLC
        RLC
        RLC
        ADI 028h
        MOV L,A
        RET
lklh:   LHLD lyro
        XCHG
        LXI H,lqkk
        MOV E,M
        INX H
        CALL lchd
        ADD E
        CMP D
        LHLD lsol
        JZ lknh
        JC lonj
        JMP lmmi
lknh:   MVI A,001h
        STA lmoi
laic:   LDA lijg
        MVI B,000h
        CALL lkwu
        LHLD lyro
        XCHG
        LXI H,lurm
        MOV D,M
        INX H
        CALL lchd
        ADD D
        CMP E
        LHLD lsol
        JZ lopj
        MVI A,000h
        STA lmoi
        JC lqok
        JMP lspl
lopj:   LDA lmoi
        ORA A
        JNZ lqqk
        JMP lgkf
lmzv:   LHLD lsol
        MOV A,M
        PUSH PSW
        LDA lijg
        MVI B,000h
        CALL lkwu
        POP PSW
        LHLD lsol
        MOV A,M
        CPI 005h
        RNZ
        DCR A
        MOV M,A
        RET
lgkf:   LDA lijg
        CALL lsrl
        RET
lkmh:   LHLD lqnk
        MOV A,M
        ORA A
        JZ lusm
        LHLD lsol
        MOV A,M
        CPI 004h
        JC lusm
        INR E
lusm:   LHLD lomj
        MOV A,M
        ORA A
        RZ
        LHLD lsol
        MOV A,M
        CPI 001h
        RZ
        INR D
        RET
lucz:   LDA loxw
        ORA A
        RNZ
        DCR A
        STA loxw
        JMP lwtn
lqax:   LDA loxw
        ORA A
        RNZ
        INR A
        STA loxw
lwtn:   CALL lyuo
        LHLD lyro
        SHLD lavp
lkau:   CALL lwfa
        XCHG
        LXI H,lcwq
        CALL lmki
        RET
lced:   LDA loxw
        ORA A
        RZ
        LHLD lavp
        ADD H
        MOV H,A
        SHLD lexr
        CALL leur
        MOV A,M
        ANI 00Fh
        CPI 008h
        JC lgys
        CPI 00Ah
        JZ lizt
lsey:   LHLD lavp
        XRA A
        STA loxw
        JMP lkau
lizt:   MOV A,M
        ANI 0F0h
        PUSH H
        PUSH PSW
        LXI B,00300h
        CALL lmbv
        POP PSW
        POP H
        CALL locw
        CPI 020h
        JZ lqdx
        MOV A,M
        ADI 010h
        MOV M,A
        JMP lsey
lqdx:   MVI M,000h
        LHLD lexr
        PUSH H
        LDA luaz
        MOV C,A
        LXI H,lufz
        CALL lwga
        MVI M,000h
        INX H
        MVI M,002h
        CALL lyhb
        POP D
        MOV M,E
        INX H
        MOV M,D
        MOV A,C
        INR A
        CPI 020h
        CZ lajc
        STA luaz
        JMP lsey
lajc:   XRA A
        RET
locw:   PUSH PSW
        PUSH H
        RRC
        RRC
        RRC
        RRC
        ADI 00Eh
        MOV D,A
        LDA loxw
        CPI 001h
        JZ lckd
        MOV A,D
        ADI 003h
        MOV D,A
lckd:   MOV A,D
        LHLD lexr
        PUSH PSW
        CALL lwfa
        POP PSW
        CALL lmvv
        POP H
        POP PSW
        RET
lgys:   LHLD lavp
        CALL lkau
        LHLD lexr
        SHLD lavp
        JMP lkau
lahc:   LXI H,lele
        CALL lcvq
        LXI H,lmhi
        MVI A,099h
        ADD M
        DAA
        MOV M,A
        MOV B,A
        XRA A
        LXI H,lqcx
        CALL lsyy
        MOV A,B
        CALL lsyy
        MOV A,B
        ORA A
        POP H
        POP H
        LHLD lgef
        XCHG
        JNZ luzz
        JMP lgmf
lsql:   CALL lyuo
        JMP lahc
leje:   LDA lijg
        MVI B,000h
        CALL lkwu
        LDA lijg
        CALL lgts
        LDA lijg
        CALL ling
        XCHG
        LDA lijg
        CALL luom
        MVI C,005h
        CALL lcsq
        LDA lijg
        CALL lwda
        MVI M,018h
        RET
lefe:   MVI C,000h
        LXI H,lufz
loqj:   MOV A,M
        MOV E,M
        INX H
        MOV D,M
        ORA M
        JZ lkoh
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
        JZ lmpi
lutm:   POP B
        POP H
lkoh:   INX H
        INR C
        MOV A,C
        CPI 020h
        JNZ loqj
        RET
lmpi:   MOV A,D
        RLC
        RLC
        RLC
        LXI H,lqrk
        MVI D,000h
        MOV E,A
        DAD D
        PUSH H
        CALL lyhb
        MOV E,M
        INX H
        MOV D,M
        XCHG
        SHLD lssl
        CALL lwfa
        XCHG
        POP H
        PUSH H
        CALL lmki
        POP H
        LXI D,lqrk
        CALL loyw
        JNZ lutm
        LHLD lssl
        CALL leur
        MVI M,00Ah
        JMP lutm
lyhb:   LXI H,lwun
lwga:   MOV A,C
        RLC
        ADD L
        MOV L,A
        MOV A,H
        ACI 000h
        MOV H,A
        RET
lggf:   LDA lqyx
        ORA A
        RZ
        LDA lszy
        DCR A
        STA lszy
        RNZ
        LDA litt
        STA lszy
        LHLD lkzu
        CALL lwfa
        XCHG
        LXI H,lyvo
        CALL lmki
        RET
lwsn:   LXI H,lawp
        CALL lcvq
        POP H
        POP H
        RET
lwmn:   LXI H,lcxq
lodw:   MOV A,M
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
        JNZ leyr
        DCR D
leyr:   DCR A
        JNZ lgzs
        INR D
lgzs:   DCR A
        JNZ liat
        DCX D
liat:   DCR A
        JNZ lkbu
        INX D
lkbu:   INX H
        MOV C,M
        INX H
        MOV B,M
        INX H
        XTHL
lqex:   PUSH D
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
        CALL lmvv
        POP H
        POP D
        DCR C
        JNZ lmcv
        POP H
        JMP lodw
lmcv:   DAD D
        JMP lqex
lcpq:   LDAX D
        CPI 0FFh
        RZ
        CALL lmvv
        INR H
        INX D
        JMP lcpq
lyuo:   LXI B,00800h
lmbv:   LXI H,lugz
lwha:   MOV A,M
        RAR
        MVI A,00Ah
        ACI 000h
        STA lafc
        INX H
        DCX B
        MOV A,B
        ORA C
        JNZ lwha
        RET
lcvq:   MOV A,M
        ORA A
        RZ
        CPI 0FFh
        JZ lyib
        STA lakc
        INX H
        MOV A,M
        STA lcld
        INX H
        CALL leme
        JMP lcvq
lyib:   MOV E,M
        INX H
        MOV D,M
        INX H
        CALL lybb
        JMP lcvq
lgmf:   LXI H,lgnf
        LXI D,liog
        CALL lcpq
        LXI H,lkph
        LXI D,lmqi
        CALL lcpq
        LXI H,lorj
        LXI D,lqsk
        CALL lcpq
        LXI D,lstl
        CALL lybb
        LXI H,luum
        CALL lcvq
        CALL lwvn
        CALL lywo
        JMP laxp
loww:   LXI H,lgnf
        LXI D,liog
        CALL lcpq
        LXI H,lkph
        LXI D,lcyq
        CALL lcpq
        LXI H,lorj
        LXI D,lqsk
        CALL lcpq
        LXI D,lstl
        CALL lybb
        LXI H,lezr
        CALL lcvq
        CALL lwvn
        LXI SP,lgas
        LXI H,lede
        SHLD lgef
        LXI H,lmhi
        MOV A,M
        ADI 005h
        DAA
        MOV M,A
        MOV B,A
        XRA A
        MVI C,000h
        LXI H,lqcx
        CALL lsdy
        XRA A
        STA lskl
        JMP libt
lwvn:   MVI H,004h
lmdv:   LXI D,lkcu
        CALL lybb
        DCR H
        JNZ lmdv
        RET
lywo:   MVI C,080h
lqfx:   MOV L,C
        CALL loew
        MOV A,C
        CMA
        MOV L,A
        CALL loew
        INR C
        JNZ lqfx
        RET
loew:   MVI H,090h
lsgy:   MVI M,000h
        INR H
        MOV A,H
        CPI 0C0h
        JNZ lsgy
        LXI D,00090h
        JMP lybb

lele:   DB 005h, 0FAh, 005h, 0E6h, 005h, 0D2h, 005h, 0BEh, 007h, 0AAh
        DB 00Ah, 096h, 00Ah, 08Ch, 00Ah, 082h, 00Ah, 078h, 00Ah, 06Eh
        DB 00Ah, 064h, 00Ah, 05Ah, 000h
liyt:   DB 028h, 056h, 0FFh, 002h, 02Dh, 04Dh, 0FFh, 002h, 032h, 044h
        DB 0FFh, 002h, 038h, 040h, 0FFh, 002h, 03Fh, 038h, 000h
lawp:   DB 028h, 056h, 0FFh, 002h, 032h, 04Dh, 0FFh, 002h, 03Ch, 044h
        DB 0FFh, 002h, 046h, 040h, 0FFh, 002h, 050h, 038h, 0FFh, 002h
        DB 05Ah, 031h, 0FFh, 002h, 064h, 02Bh, 0FFh, 002h, 06Eh, 028h
        DB 000h
laup:   DB 018h, 056h, 01Ah, 04Dh, 01Eh, 044h, 000h
lobw:   DB 028h, 056h, 0FFh, 003h, 028h, 056h, 0FFh, 003h, 028h, 056h
        DB 0FFh, 003h, 028h, 056h, 0FFh, 003h, 028h, 056h, 000h
limg:   DB 003h, 03Ch, 0FFh, 004h, 003h, 046h, 0FFh, 004h, 003h, 050h
        DB 0FFh, 004h, 003h, 05Ah, 0FFh, 004h, 003h, 064h, 0FFh, 004h
        DB 003h, 06Eh, 0FFh, 004h, 003h, 078h, 0FFh, 004h, 003h, 082h
        DB 0FFh, 004h, 003h, 08Ch, 0FFh, 004h, 000h
luum:   DB 0BEh, 0EDh, 0FFh, 007h, 0BEh, 0EDh, 0FFh, 007h, 032h, 0EDh
        DB 0FFh, 003h, 0BEh, 0EDh, 0FFh, 007h, 0DFh, 0C8h, 0FFh, 007h
        DB 03Ch, 0D4h, 0FFh, 003h, 0CDh, 0D4h, 0FFh, 007h, 032h, 0EDh
        DB 0FFh, 003h, 0BEh, 0EDh, 0FFh, 007h, 028h, 0FBh, 0FFh, 003h
        DB 0BEh, 0EDh, 000h
lezr:   DB 096h, 0B0h, 05Ah, 08Bh, 06Eh, 075h, 03Ch, 056h, 0FFh, 004h
        DB 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 03Ch, 056h
        DB 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h
        DB 03Ch, 056h, 0FFh, 004h, 03Ch, 056h, 0FFh, 004h, 041h, 04Dh
        DB 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h
        DB 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh
        DB 0FFh, 004h, 041h, 04Dh, 0FFh, 004h, 041h, 04Dh, 0FFh, 004h
        DB 0FEh, 044h, 096h, 044h, 0FCh, 04Dh, 078h, 04Dh, 064h, 056h
        DB 0FFh, 005h, 050h, 075h, 0FFh, 005h, 06Eh, 056h, 000h
lmqi:   DB 015h, 025h, 040h, 027h, 041h, 000h, 021h, 024h, 021h, 02Ah
        DB 026h, 029h, 02Ah, 041h, 015h, 0FFh
lcyq:   DB 015h, 020h, 027h, 025h, 033h, 021h, 028h, 041h, 042h, 000h
        DB 025h, 040h, 027h, 041h, 015h, 0FFh
liog:   DB 016h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h
        DB 014h, 014h, 014h, 014h, 017h, 0FFh
lqsk:   DB 019h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h, 014h
        DB 014h, 014h, 014h, 014h, 018h, 0FFh
laop:   DB 021h, 026h, 024h, 025h, 000h, 000h, 020h, 021h, 020h, 022h
        DB 023h, 024h, 025h, 000h, 000h, 02Dh, 024h, 021h, 027h, 021h
        DB 02Dh, 023h, 02Bh, 03Ah, 030h, 030h, 0FFh
lgrs:   DB 030h, 030h, 030h, 030h, 000h, 000h, 000h, 000h, 000h, 030h
        DB 030h, 030h, 035h, 000h, 000h, 000h, 02Ch, 027h, 021h, 028h
        DB 029h, 02Ah, 02Bh, 03Ah, 030h, 030h, 0FFh
lktu:   DB 026h, 029h, 027h, 02Ah, 021h, 028h, 02Eh, 022h, 02Fh, 031h
        DB 039h, 038h, 038h, 000h, 000h, 03Bh, 0FFh
lovw:   DB 01Ah, 01Bh, 01Ch, 0FFh
lsxy:   DB 01Dh, 01Eh, 01Fh, 0FFh
lmii:   DB 03Ch, 025h, 03Dh, 025h, 02Ah, 02Dh, 024h, 025h, 03Eh, 000h
        DB 024h, 03Fh, 028h, 03Fh, 0FFh
lmji:   DB 007h, 007h, 007h, 007h, 007h, 007h, 0FFh
lcxq:   DB 000h, 000h, 001h, 001h, 016h, 000h, 001h, 004h, 01Eh, 015h
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
leee:   DB 031h, 00Ah, 007h, 032h, 00Ah, 007h, 033h, 00Ah, 007h, 034h
        DB 00Ah, 007h, 035h, 00Ah, 007h, 036h, 00Ah, 0FFh
ligg:   DB 02Dh, 000h, 024h, 000h, 021h, 000h, 027h, 000h, 021h, 000h
        DB 02Dh, 000h, 023h, 000h, 02Bh, 0FFh
lycb:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 07Eh, 02Ah, 07Eh, 07Eh, 000h, 000h, 000h, 000h, 07Eh
        DB 02Ah, 07Eh, 07Eh, 000h, 000h, 000h, 000h, 07Eh, 02Ah, 07Eh
        DB 07Eh, 000h, 000h, 022h, 044h, 0AAh, 000h, 0AAh, 000h, 055h
        DB 000h, 055h, 000h, 0AAh, 000h, 0AAh, 000h, 055h, 0FFh, 055h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 066h, 0FFh, 066h, 066h
        DB 066h, 0FFh, 066h, 066h, 038h, 020h, 038h, 020h, 038h, 020h
        DB 038h, 020h, 038h, 020h, 038h, 020h, 038h, 020h, 038h, 020h
        DB 0D6h, 0B5h, 05Bh, 0ADh, 0D6h, 0B5h, 05Bh, 0ADh, 0C6h, 0EEh
        DB 039h, 0BAh, 06Ch, 0EEh, 093h, 0ABh
lyvo:   DB 007h, 005h, 00Fh, 018h, 030h, 060h, 0F0h, 0A0h, 000h, 07Eh
        DB 000h, 07Eh, 000h, 07Eh, 000h, 07Eh, 0D6h, 0B5h, 05Bh, 00Dh
        DB 006h, 031h, 05Bh, 0ADh, 0D6h, 035h, 003h, 001h, 002h, 001h
        DB 01Bh, 0ADh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 0D6h, 0B5h, 05Bh, 084h, 0D0h, 0B0h, 05Bh, 0ADh, 0D6h, 0B4h
        DB 050h, 080h, 0C0h, 0A0h, 058h, 0ADh, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 0FFh, 0FFh, 0FFh, 0FFh
        DB 000h, 000h, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch, 03Ch
        DB 000h, 000h, 003h
lyoo:   DB 00Fh, 01Fh, 01Fh, 03Eh, 03Ch, 000h, 000h, 0C0h, 0F0h, 0F8h
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
lege:   DB 058h, 058h, 04Ah, 03Eh, 018h, 028h, 028h, 008h, 000h, 000h
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
lcwq:   DB 000h, 000h, 010h, 024h, 008h, 000h, 000h, 000h
lqrk:   DB 000h, 000h
laqp:   DB 000h, 001h, 0D6h, 080h, 000h, 000h, 000h, 000h, 001h, 0ACh
        DB 000h, 001h, 000h, 000h, 080h, 091h, 05Ah, 000h, 000h, 034h
        DB 013h, 081h, 056h, 024h, 000h, 000h, 000h, 000h, 048h, 02Ch
lssl:   DB 007h, 00Bh
lavp:   DB 014h, 01Dh
lexr:   DB 014h, 01Eh
loxw:   DB 000h
lqyx:   DB 000h
lkzu:   DB 008h, 011h
lszy:   DB 001h
lsay:   DB 000h
lwca:   DB 001h
lgef:   DB 000h, 013h
lskl:   DB 000h
litt:   DB 006h
lifg:   DB 000h, 007h
lijg:   DB 001h
lulm:   DB 03Ah
lkkh:   DB 0F6h, 00Fh
lomj:   DB 0F7h, 00Fh
lqnk:   DB 0F9h, 00Fh
lsol:   DB 0FAh, 00Fh
lmoi:   DB 000h
lupm:   DB 003h, 002h, 017h, 0B7h
lyro:   DB 003h, 002h
lqjk:   DB 000h
loij:   DB 000h
lmhi:   DB 005h
luaz:   DB 000h
lufz:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lwun:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
livt:   DB 002h, 000h, 003h, 000h, 005h, 004h, 000h, 000h, 000h, 001h
        DB 01Ah, 000h, 000h, 000h, 001h
liut:   DB 000h
lmwv:   DB 000h
lunm:   DB 002h, 000h, 000h, 000h, 004h, 005h, 000h, 000h, 000h, 004h
        DB 01Bh, 000h, 000h, 000h, 004h, 018h
lqkk:   DB 002h, 000h
lurm:   DB 003h, 000h, 004h
lqbx:   DB 004h, 000h, 000h, 000h, 001h
ludz:   DB 01Ah, 000h, 000h, 000h, 001h
lkvu:   DB 018h
lgjf:   DB 00Bh, 00Bh, 000h, 00Ah, 000h, 000h, 00Ah, 00Bh, 007h, 00Ah
        DB 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah, 00Ah
        DB 007h, 00Ah, 00Ah, 007h, 00Bh, 00Ah, 000h, 000h, 00Ah, 000h
        DB 00Bh, 00Bh, 00Bh, 00Bh, 000h, 00Ah, 00Ah, 00Ah, 00Ah, 00Bh
        DB 007h, 00Bh, 00Ah, 007h, 00Bh, 00Ah, 007h, 00Bh, 00Ah, 007h
        DB 00Bh, 00Ah, 007h, 00Bh, 00Ah, 007h, 00Bh, 00Ah, 00Ah, 00Ah
        DB 00Ah, 000h, 00Bh, 00Bh, 00Bh, 00Bh, 000h, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah
        DB 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah, 00Ah, 007h, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 000h, 00Bh, 00Bh, 00Bh, 00Bh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 007h, 000h, 000h, 007h, 000h, 000h
        DB 007h, 000h, 000h, 007h, 000h, 000h, 007h, 000h, 000h, 007h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 00Bh, 00Bh, 00Bh, 00Bh
        DB 007h, 006h, 007h, 00Bh, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah, 00Ah
        DB 00Ah, 00Ah, 00Ah, 00Ah, 00Bh, 007h, 006h, 007h, 00Bh, 00Bh
        DB 00Bh, 00Bh, 007h, 000h, 007h, 00Bh, 00Ah, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 00Ah, 00Bh, 007h, 000h, 007h
        DB 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 007h, 00Bh, 00Ah, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Ah, 00Bh, 007h
        DB 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h, 007h, 00Bh
        DB 00Ah, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h
        DB 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 007h, 00Ah
        DB 00Bh, 007h, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 007h, 000h
        DB 007h, 00Ah, 00Ah, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 00Ah, 00Ah, 007h, 000h, 007h, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Ah, 007h, 00Bh, 006h, 006h, 006h, 006h, 006h, 006h, 006h
        DB 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h
        DB 006h, 006h, 006h, 006h, 006h, 00Bh, 007h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Ah, 007h, 00Bh, 001h, 001h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h, 001h
        DB 001h, 001h, 001h, 001h, 001h, 001h, 001h, 00Bh, 007h, 00Ah
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h, 00Bh, 004h, 00Bh, 00Bh
        DB 00Bh, 004h, 00Bh, 00Bh, 00Bh, 004h, 004h, 00Bh, 00Bh, 004h
        DB 004h, 004h, 00Bh, 00Bh, 00Bh, 00Bh, 004h, 004h, 004h, 00Bh
        DB 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h, 00Bh, 005h
        DB 00Bh, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h, 005h, 005h, 00Bh
        DB 005h, 005h, 005h, 005h, 005h, 00Bh, 00Bh, 005h, 005h, 005h
        DB 005h, 00Bh, 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h
        DB 00Bh, 005h, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 00Bh, 005h, 00Bh
        DB 005h, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h, 00Bh, 00Bh, 005h
        DB 00Bh, 00Bh, 005h, 00Bh, 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Ah, 007h, 00Bh, 005h, 005h, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 005h, 00Bh, 005h, 00Bh, 005h, 005h, 005h, 005h, 005h, 00Bh
        DB 00Bh, 005h, 00Bh, 00Bh, 005h, 00Bh, 007h, 00Ah, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Ah, 007h, 00Bh, 005h, 00Bh, 005h, 00Bh, 00Bh
        DB 00Bh, 00Bh, 005h, 00Bh, 005h, 00Bh, 005h, 00Bh, 00Bh, 00Bh
        DB 005h, 00Bh, 00Bh, 005h, 00Bh, 00Bh
lumm:   DB 005h, 00Bh, 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h
        DB 00Bh, 005h, 00Bh, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h
lipg:   DB 00Bh, 005h, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h, 00Bh, 00Bh
        DB 005h, 00Bh, 00Bh, 005h, 00Bh, 007h, 00Ah, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Ah, 007h, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h, 00Bh
        DB 005h, 005h, 00Bh, 005h, 00Bh, 005h, 00Bh, 00Bh, 00Bh, 005h
        DB 00Bh, 005h, 005h, 005h, 005h, 005h, 00Bh, 007h, 00Ah, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Ah, 007h, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh, 007h
        DB 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Ah, 007h, 000h
        DB 007h, 00Bh, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h
        DB 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h, 006h
        DB 00Bh, 007h, 000h, 007h, 00Ah, 00Bh, 00Bh, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh, 004h
lolj:   DB 004h, 004h, 004h, 004h, 004h, 004h, 004h, 004h, 004h, 004h
        DB 004h, 004h, 004h, 004h, 004h, 004h, 004h, 00Bh, 00Bh, 00Bh
        DB 00Bh, 00Bh, 00Bh, 00Bh
lqqk:   XRA A
        STA lmoi
        JMP lahc
lsby:   CPI 043h
        JZ lwsn
        CPI 083h
        JNZ lkxu
        LDA lmhi
        INR A
        DAA
        STA lmhi
        JMP lkxu
        DB 037h, 03Fh, 017h, 05Fh, 07Ah, 017h, 057h, 00Dh, 0C2h, 0E1h
        DB 001h, 019h, 0EBh, 0E1h, 0E5h, 00Eh, 008h, 01Ah, 077h, 023h
        DB 013h, 00Dh, 0C2h, 0F3h, 001h, 0E1h, 0D1h, 0C1h, 0C9h, 07Eh
lede:   DB 002h, 000h, 000h, 000h, 004h, 005h, 000h, 000h, 000h, 004h
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
larp:   DB 000h, 000h, 000h, 000h, 000h, 00Ah, 0B7h, 007h, 0BBh, 0BBh
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
lkqh:   DB 002h, 003h, 000h, 000h, 000h, 004h, 01Ch, 000h, 000h, 000h
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
lqmk:   DB 000h, 000h, 000h, 00Ah, 0AAh, 0AAh, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0AAh
        DB 0A0h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh
        DB 070h, 000h, 000h, 000h, 000h, 000h, 0A1h, 0A0h, 000h, 000h
        DB 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h
        DB 000h, 0AAh, 0A7h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh
        DB 0BBh, 0BBh, 070h, 000h, 000h, 000h, 00Ah, 0AAh, 007h, 00Ah
        DB 0AAh, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh, 070h, 000h
        DB 000h, 000h, 000h, 00Ah, 017h, 01Ah, 000h, 000h, 000h, 000h
        DB 000h, 07Bh, 0BBh, 0BBh, 070h, 000h, 000h, 000h, 000h, 0AAh
lwon:   DB 0A7h, 0AAh, 0A0h, 000h, 000h, 000h, 000h, 07Bh, 0BBh, 0BBh
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
lyxo:   DB 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh, 0BBh
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
        DB 030h, 09Eh, 014h, 09Dh, 014h, 0A6h, 014h, 0A7h, 000h, 006h
        DB 085h, 0C1h, 000h, 000h, 0A3h, 00Ah, 05Bh, 009h, 08Fh, 009h
        DB 00Ah, 001h
lgas:   DB 011h
labc:   CALL luhz
        LXI D,lstl
lyjb:   MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 091h
        JNZ lyjb
        LXI D,lalc
lcmd:   MVI A,066h
        STAX D
        INX D
        MOV A,D
        CPI 0C0h
        JNZ lcmd
        LXI D,lstl
        CALL lene
        JMP lgof
lene:   MVI A,0FFh
        STAX D
        INX D
        STAX D
        INR D
        DCR E
        MOV A,D
        CPI 0C0h
        JNZ lene
        RET
lgof:   LXI D,lmri
        CALL lene
        LXI D,losj
        CALL lene
        LXI D,lqtk
        CALL lene
        LXI D,lsul
        CALL luvm
        JMP lwwn
luvm:   MVI A,0FFh
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
        JNZ luvm
        RET
lwwn:   LXI D,lczq
        CALL luvm
        LXI D,lear
        CALL luvm
        LXI D,lgbs
        CALL luvm
        LXI D,lict
        CALL luvm
        LXI D,lkdu
        CALL luvm
        LXI B,lmev
        LXI D,lofw
        MVI H,000h
luiz:   LDAX B
        SUI 001h
        JZ lqgx
        ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ luiz
        INR D
        INR D
        MVI H,000h
        JMP luiz
lqgx:   LXI B,lwja
        LXI D,lykb
        MVI H,000h
leoe:   LDAX B
        SUI 001h
        JZ lamc
        CALL lcnd
        JNZ leoe
        INR D
        INR D
        MVI H,000h
        MVI E,020h
        JMP leoe
lamc:   LXI B,lgpf
        LXI D,liqg
        MVI H,000h
lotj:   LDAX B
        SUI 001h
        JZ lkrh
        CALL lcnd
        JMP lmsi
lcnd:   ADI 001h
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        RET
lmsi:   JNZ lotj
        INR D
        INR D
        MVI H,000h
        MVI E,0A0h
        JMP lotj
lkrh:   LXI B,lquk
        LXI D,lsvl
        MVI L,000h
lkeu:   PUSH B
        PUSH D
        MVI H,000h
lwxn:   LDAX B
        SUI 001h
        JZ luwm
        CALL lcnd
        JNZ lwxn
luwm:   CALL lyyo
        POP D
        LXI B,lazp
        MVI H,000h
lebr:   LDAX B
        SUI 001h
        JZ lcaq
        CALL lcnd
        JNZ lebr
lcaq:   POP B
        MOV A,C
        CPI 078h
        JNZ lgcs
        MVI C,048h
        INR L
        MOV A,L
        CPI 00Ah
        JZ lidt
lgcs:   MOV A,C
        ADI 010h
        MOV C,A
        INR D
        DCR E
        DCR E
        DCR E
        DCR E
        JMP lkeu
lyyo:   MVI A,001h
lqhx:   MVI D,0E0h
logw:   MVI E,080h
lmfv:   DCR E
        JNZ lmfv
        DCR D
        JNZ logw
        DCR A
        JNZ lqhx
        RET
louj:   LXI B,lsiy
        LXI D,lujz
        MVI H,000h
lylb:   LDAX B
        SUI 001h
        JZ lwka
        CALL lcnd
        JNZ lylb
        INR D
        MVI H,000h
        MVI E,030h
        JMP lylb
lidt:   LXI B,lanc
        LXI D,lcod
        LXI H,00000h
lswl:   PUSH B
        PUSH D
        MVI H,000h
lgqf:   LDAX B
        SUI 001h
        JZ lepe
        CALL lcnd
        JNZ lgqf
lepe:   CALL lyyo
        POP D
        LXI B,lazp
        MVI H,000h
lksh:   LDAX B
        SUI 001h
        JZ lirg
        CALL lcnd
        JNZ lksh
lirg:   POP B
        MOV A,C
        CPI 0F0h
        JNZ lmti
        MVI C,0E0h
        INR L
        MOV A,L
        CPI 009h
        JZ louj
        JMP lqvk
lmti:   MOV A,C
        ADI 010h
        MOV C,A
lqvk:   LXI D,lcod
        JMP lswl
lwka:   LXI B,luxm
        LXI D,lwyn
        MVI H,000h
laap:   LDAX B
        SUI 001h
        JZ lyzo
        ADI 001h
        STAX D
        INX D
        STAX D
        INX D
        INX B
        INR H
        MOV A,H
        CPI 008h
        JNZ laap
        INR D
        INR D
        INR D
        INR D
        MVI H,000h
        MVI E,0C0h
        JMP laap
lyzo:   MVI A,00Fh
        CALL lqhx
        CALL luhz
        LXI SP,lgas
        JMP lcbq
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h, 011h
        DB 011h, 011h
lmev:   DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 07Eh, 066h
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
lwja:   DB 03Ch, 066h, 060h, 060h, 060h, 060h, 066h, 03Ch, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 0FEh, 0FEh, 030h, 030h
        DB 030h, 030h, 030h, 030h, 07Eh, 060h, 060h, 07Eh, 066h, 066h
        DB 066h, 07Ch, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 001h, 0FFh, 000h, 0FFh
        DB 0FFh, 000h, 0FFh, 000h
lgpf:   DB 066h, 066h, 066h, 066h, 03Eh, 006h, 006h, 006h, 0FEh, 060h
        DB 060h, 060h, 07Ch, 060h, 060h, 0FEh, 07Eh, 066h, 066h, 066h
        DB 07Eh, 060h, 060h, 060h, 066h, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch
        DB 07Eh, 066h, 064h, 07Eh, 066h, 066h, 066h, 07Ch, 066h, 066h
        DB 066h, 066h, 066h, 066h, 07Fh, 07Fh, 063h, 063h, 063h, 073h
        DB 06Bh, 06Bh, 06Bh, 073h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h
lquk:   DB 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah, 001h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 007h, 007h, 00Eh, 03Eh
        DB 00Eh, 007h, 01Eh, 006h, 001h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 00Eh, 00Eh, 03Ch, 02Fh, 02Ch, 00Eh, 005h, 00Ah
        DB 001h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Eh, 00Eh
        DB 0FCh, 01Fh, 038h, 0F6h, 002h, 003h, 001h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h
lazp:   DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 001h, 000h
        DB 0FFh, 000h, 000h, 0FFh, 000h, 0FFh
lsiy:   DB 03Ch, 066h, 066h, 066h, 066h, 066h, 066h, 03Ch, 0FEh, 0FEh
        DB 030h, 030h, 030h, 030h, 030h, 030h, 07Eh, 066h, 066h, 066h
        DB 066h, 066h, 0FFh, 0FFh, 000h, 000h, 000h, 000h, 000h, 060h
        DB 060h, 060h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 006h, 00Eh, 006h, 006h, 006h, 006h, 006h, 006h, 03Ch, 066h
        DB 066h, 066h, 066h, 066h, 066h, 03Ch, 03Ch, 066h, 066h, 066h
        DB 066h, 066h, 066h, 03Ch, 001h, 000h, 0FFh, 000h, 000h, 0FFh
        DB 000h, 0FFh
lanc:   DB 04Eh, 04Eh, 024h, 01Eh, 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h
        DB 0FFh, 000h, 000h, 0FFh, 000h, 0FFh, 02Eh, 02Eh, 024h, 01Eh
        DB 00Eh, 00Dh, 00Ah, 00Ah, 001h, 000h, 0FFh, 000h, 000h, 0FFh
        DB 000h, 0FFh
luxm:   DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 063h, 066h
        DB 06Ch, 078h, 07Ch, 066h, 063h, 063h, 03Eh, 066h, 066h, 066h
        DB 066h, 066h, 066h, 066h, 03Eh, 066h, 066h, 066h, 07Eh, 066h
        DB 066h, 066h, 03Eh, 066h, 066h, 066h, 066h, 066h, 0FFh, 0FFh
        DB 066h, 066h, 066h, 000h, 000h, 000h, 000h, 000h, 001h
END