        CPU  8080
        .ORG 0C000h
luzz    EQU 0FF00h
lupm    EQU 0C800h
loww    EQU 0FF01h
lybb    EQU 0FF02h
lccd    EQU 0FF03h
lehe    EQU 00323h
laop    EQU 0266Fh
lasp    EQU 08FE1h
lydb    EQU 08FE3h
lmki    EQU 08FE5h
lwmn    EQU 08FE7h
lulm    EQU 08FE9h
laec    EQU 08FEBh
lesr    EQU 08FEDh
lwon    EQU 08FEFh
lwba    EQU 08FF0h
lumm    EQU 08FF1h
lcqq    EQU 08FF2h
lsay    EQU 08FF3h
litt    EQU 08FF4h
lgef    EQU 08FF6h
lyno    EQU 08FF8h
lifg    EQU 08FFAh
loij    EQU 08FFCh
lskl    EQU 08FFDh
lubz    EQU 08FFFh
        JMP labc
labc:   LXI SP,07FFFh
        MVI A,082h
        STA lccd
        JMP lede
        DB 000h, 000h
lsxy:   PUSH H
        PUSH B
        LXI H,00000h
        DAD SP
        SHLD lgef
        LXI SP,0C000h
        LHLD lifg
        LXI B,00300h
lkgh:   PUSH H
        PUSH H
        PUSH H
        PUSH H
        PUSH H
        PUSH H
        PUSH H
        PUSH H
        DCX B
        MOV A,B
        ORA C
        JNZ lkgh
        LHLD lgef
        SPHL
        POP B
        POP H
        RET
lijg:   PUSH H
        PUSH D
        PUSH B
        PUSH PSW
        MOV A,C
        CPI 021h
        JC lmhi
luyz:   LHLD loij
        MOV A,H
        CPI 0BEh
        JNC lqjk
lovw:   ADI 003h
        STA lskl
        XCHG
        MOV A,C
        STA lulm
        SUI 020h
        LHLD lwmn
        ADD L
        MOV L,A
        DAD H
        DAD H
        DAD H
        XCHG
        NOP
        MOV A,H
        ANI 003h
        MOV C,A
        MVI A,005h
        SUB C
        MOV C,A
        MOV A,H
        ANI 0FCh
        RRC
        RRC
        ADI 090h
        MOV H,A
        SHLD lyno
        MVI B,008h
        NOP
leqr:   LDAX D
        MOV L,A
        MVI H,000h
        MOV A,C
lcpq:   DAD H
        DAD H
        DCR A
        JNZ lcpq
        PUSH H
        INX D
        DCR B
        JNZ leqr
        MVI B,008h
        LHLD lyno
list:   POP D
        MOV A,D
        CALL lgrs
        MOV M,A
        INR H
        MOV A,E
        CALL lgrs
        MOV M,A
        DCR H
        DCR L
        DCR B
        JNZ list
lqkk:   POP PSW
        POP B
        POP D
        POP H
        RET
lsll:   MOV C,A
        LDA lifg
        ORA A
        JNZ lktu
        MOV A,C
        CMA
        ANA M
        RET
lktu:   MOV A,M
        ORA C
        RET
lqjk:   MOV A,L
        CPI 0F5h
        JNC lmuv
        ADI 00Ah
        MOV L,A
        STA loij
        MVI H,000h
        MOV A,H
        JMP lovw
lmuv:   CALL lqwx
        NOP
        CALL lsxy
        LXI H,00008h
        SHLD loij
        JMP luyz
lmhi:   LHLD loij
        CPI 020h
        JZ lwza
        CPI 00Ah
        JZ lyab
        CPI 00Dh
        JZ lacc
        CPI 018h
        JZ lwza
        CPI 008h
        JZ lcdd
        CPI 019h
        JZ leee
        CPI 01Ah
        JZ lgff
        CPI 00Ch
        JZ ligg
        CPI 01Fh
        JZ lkhh
        JMP lmii
lwza:   MOV A,H
        CPI 0BEh
        JNC lyab
        ADI 003h
        MOV H,A
        JMP lmii
lyab:   MVI H,000h
        MOV A,L
        CPI 0F5h
        JNC lojj
        ADI 00Ah
        MOV L,A
        JMP lmii
lojj:   CALL lqwx
        NOP
        JMP lkhh
lacc:   MVI H,000h
        JMP lmii
lcdd:   MOV A,H
        CPI 002h
        JC lmii
        SUI 003h
        MOV H,A
        JMP lmii
leee:   MOV A,L
        CPI 011h
        JC lmii
        SUI 00Ah
        MOV L,A
        JMP lmii
lgff:   MOV A,L
        CPI 0F5h
        JNC lmii
        ADI 00Ah
        MOV L,A
        JMP lmii
ligg:   LXI H,00008h
        JMP lmii
lkhh:   CALL lsxy
        JMP ligg
lmii:   SHLD loij
        JMP lqkk
lgrs:   MOV C,A
        LDA lulm
        CPI 07Fh
        MOV A,C
        JZ lsll
        XRA M
        RET
        DB 000h
lerr:   PUSH H
        PUSH B
        LHLD lumm
lyoo:   MVI A,00Bh
        STA lccd
        CALL lwnn
        MVI A,00Ah
        STA lccd
        CALL lwnn
        DCR H
        JNZ lyoo
        NOP
        NOP
        NOP
        POP B
        POP H
        RET
lwnn:   MOV B,L
lapp:   DCR B
        JNZ lapp
        RET
loxw:   PUSH PSW
        MVI A,040h
        STA lcqq
        CALL lerr
        POP PSW
        RET
liig:   PUSH H
        MVI A,040h
lgss:   STA lumm
        CALL lerr
        POP H
        RET
lghf:   PUSH H
        MVI A,050h
        JMP lgss
luom:   PUSH H
        PUSH B
        MVI B,0FFh
lced:   NOP
        NOP
        NOP
        LDA litt
        ORA A
        JZ lkuu
        CALL lmvv
        LDA loww
        ANI 002h
        JZ lqxx
lggf:   CALL lsyy
        LDA luzz
        CPI 0FFh
        JNZ lwaa
        LDA lybb
        ORI 0F0h
        CPI 0FFh
        JNZ ladc
        JMP lced
lkuu:   CALL lmvv
        LDA loww
        ANI 002h
        JNZ lefe
        MVI B,0FFh
        JMP lggf
lefe:   DCR B
        JNZ lggf
        STA litt
        NOP
        NOP
        NOP
        JMP lced
lwaa:   MOV L,A
        MVI H,0FFh
        JMP lihg
ladc:   MOV H,A
        MVI L,0FFh
lihg:   MVI C,0FBh
lkih:   INR C
        DAD H
        JC lkih
        MOV L,C
        MVI B,0FFh
lokj:   CALL lmvv
        LDA loww
        ORI 003h
        CPI 0FFh
        JNZ lmji
        DCR B
        JNZ lokj
        JMP lced
lqlk:   LDA loww
        CMA
        ANI 0F7h
        RET
lqwx:   CALL lqlk
        CNZ lsml
        RET
        DB 000h
lmji:   MVI C,0FDh
lunm:   INR C
        RRC
        JC lunm
        MOV A,C
        RLC
        RLC
        RLC
        RLC
        ADI 0A0h
        ORA L
        MOV L,A
        MVI H,0C4h
        MOV A,M
        POP B
        POP H
        RET
lqxx:   STA litt
        NOP
        NOP
        NOP
        JMP lced
lmvv:   MVI A,082h
        STA lccd
        RET
lsyy:   MVI A,091h
        STA lccd
        RET
lsml:   PUSH B
        LDA lwon
        CPI 080h
        JZ lypo
laqp:   MVI C,0FFh
lcrq:   CALL lmvv
        LDA loww
        ORI 003h
        CPI 0FFh
        JNZ laqp
        MVI B,015h
        CALL lapp
        DCR C
        JNZ lcrq
lsnl:   POP B
        RET
lkvu:   LHLD lesr
        PCHL
        DB 021h, 0FEh, 018h, 022h, 0E7h, 08Fh, 00Eh, 021h, 0CDh, 0BCh
        DB 0C2h, 021h, 0A0h, 018h, 022h, 0E7h, 08Fh, 0C9h, 000h
lycb:   LDA litt
        JMP lgts
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
lolj:   CALL liut
        JMP lkvu
        DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0E5h, 0D5h, 0C5h, 0F5h
        DB 079h, 02Ah, 0FCh, 08Fh, 0EBh, 0C3h, 050h, 0C0h
liut:   JMP lmwv
lyqo:   CALL loxw
        CPI 081h
        JNC lqyx
        CPI 021h
        JC lszy
        MOV C,A
        CPI 040h
        JNC luaz
        LDA litt
        ORA A
        MOV A,C
        JNZ lszy
        XRI 010h
lszy:   STA lwba
        STA lwon
        RET
luaz:   CPI 07Fh
        JZ lycb
        LDA laec
lgts:   ORA A
        MOV A,C
        JNZ lszy
        SUI 020h
        JMP lszy
lwpn:   STA lwon
        LDA lwba
        RET
lqyx:   CPI 081h
        JNZ lcfd
        LDA laec
        ORA A
        MVI A,001h
        JZ lege
        XRA A
lege:   STA laec
        ORA A
        LXI H,0C2C8h
        PUSH H
        JZ lghf
        JMP liig
lcfd:   CPI 08Ch
        JNZ lkjh
        LXI H,0FFFFh
lcsq:   SHLD lifg
        JMP liut
larp:   LHLD lmki
        PCHL
lsol:   PUSH B
        PUSH D
        PUSH H
        CALL lkvu
        CALL lolj
        NOP
        NOP
        POP H
        POP D
        POP B
        RET
lypo:   MVI C,010h
lqmk:   MVI B,0FFh
        CALL lapp
        DCR C
        JNZ lqmk
        JMP lsnl
        DB 0C5h, 006h, 015h, 0CDh, 090h, 0C1h, 0C1h, 0C3h, 087h, 0C2h
lmwv:   CALL lsml
        CALL luom
        CPI 080h
        JZ lwpn
        JMP lyqo
lkjh:   CPI 08Bh
        JNZ larp
        LXI H,00000h
        JMP lcsq
lwca:   PUSH B
        PUSH D
lyro:   MVI C,000h
        MOV D,A
        LDA loww
        ANI 001h
        MOV E,A
lqzx:   MOV A,C
        ANI 07Fh
        RLC
        MOV C,A
lgus:   LDA loww
        CPI 080h
        JC letr
        ANI 001h
        CMP E
        JZ lgus
        ORA C
        MOV C,A
        CALL livt
        LDA loww
        ANI 001h
        MOV E,A
        MOV A,D
        ORA A
        JP lkwu
        MOV A,C
        CPI 0E6h
        JNZ lmxv
        XRA A
        STA lyno
        JMP loyw
lmxv:   CPI 019h
        JNZ lqzx
        MVI A,0FFh
        STA lsay
loyw:   MVI D,009h
lkwu:   DCR D
        JNZ lqzx
        LDA lsay
        XRA C
        POP D
        POP B
        RET
livt:   LDA lubz
        MOV B,A
        JMP lapp
        DB 0C5h, 0D5h, 0F5h, 057h, 00Eh, 008h, 07Ah, 007h, 057h, 0E6h
        DB 001h, 0F6h, 00Eh, 032h, 003h, 0FFh, 05Fh, 0CDh, 084h, 0C4h
        DB 07Bh, 0EEh, 001h, 032h, 003h, 0FFh, 0CDh, 084h, 0C4h, 00Dh
        DB 0C2h, 0D6h, 0C3h, 0F1h, 0D1h, 0C1h, 0C9h, 000h, 000h, 000h
        DB 000h
lqnk:   MVI A,0FFh
        CALL lwca
        MOV L,A
        MVI A,008h
        CALL lwca
        MOV H,A
        SHLD lydb
        MVI A,008h
        CALL lwca
        MOV E,A
        MVI A,008h
        CALL lwca
        MOV D,A
lcgd:   MVI A,008h
        CALL lwca
        MOV M,A
        CALL lafc
        INX H
        JNZ lcgd
        RET
        DB 03Eh, 0FFh, 0C3h, 016h, 0C4h
lafc:   MOV A,H
        CMP D
        RNZ
        MOV A,L
        CMP E
        RET
lgif:   MOV A,M
        STAX B
        INX H
        INX B
        CALL lafc
        JNZ lgif
        RET
lkkh:   MOV A,M
        MOV C,A
        CPI 000h
        RZ
        CALL lijg
        INX H
        JMP lkkh
lede:   LXI H,lmli
        LXI D,lomj
        LXI B,08FE3h
        CALL lgif
        CALL lkkh
        CALL lqnk
        LHLD lydb
        PCHL
letr:   CALL lsol
        CPI 00Dh
        JZ lupm
        CPI 00Ah
        JZ lwqn
        STA lubz
        MVI A,0FFh
        JMP lyro
lwqn:   LHLD lasp
        PCHL

lmli:   DB 000h, 0C0h, 0C8h, 0C2h, 0A0h, 018h, 000h, 000h, 000h, 000h
        DB 054h, 0C3h, 020h, 020h, 050h, 040h, 000h, 03Ah, 0FEh, 08Fh
        DB 0C3h, 0CCh, 0C3h, 000h, 000h, 000h, 000h, 028h, 03Ch
lomj:   DB 01Fh, 02Ah, 020h, 052h, 055h, 04Eh, 022h, 043h, 04Fh, 04Dh
        DB 03Ah, 022h, 000h, 0FFh, 0FFh, 0FFh, 081h, 00Ch, 019h, 01Ah
        DB 020h, 020h, 020h, 008h, 080h, 018h, 00Ah, 00Dh, 000h, 000h
        DB 000h, 000h, 071h, 07Eh, 073h, 06Dh, 069h, 074h, 078h, 062h
        DB 060h, 02Ch, 02Fh, 07Fh, 000h, 000h, 000h, 000h, 066h, 079h
        DB 077h, 061h, 070h, 072h, 06Fh, 06Ch, 064h, 076h, 07Ch, 02Eh
        DB 000h, 000h, 000h, 000h, 06Ah, 063h, 075h, 06Bh, 065h, 06Eh
        DB 067h, 07Bh, 07Dh, 07Ah, 068h, 03Ah, 000h, 000h, 000h, 000h
        DB 03Bh, 031h, 032h, 033h, 034h, 035h, 036h, 037h, 038h, 039h
        DB 030h, 02Dh, 000h, 000h, 000h, 000h, 082h, 083h, 084h, 085h
        DB 086h, 087h, 088h, 089h, 08Ah, 08Bh, 08Ch, 01Fh, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 004h, 004h, 004h, 004h, 004h, 000h, 004h, 000h, 00Ah, 00Ah
        DB 00Ah, 000h, 000h, 000h, 000h, 000h, 00Ah, 00Ah, 01Fh, 00Ah
        DB 01Fh, 00Ah, 00Ah, 000h, 004h, 00Fh, 014h, 00Eh, 005h, 01Eh
        DB 004h, 000h, 018h, 019h, 002h, 004h, 008h, 013h, 003h, 000h
        DB 004h, 00Ah, 00Ah, 00Ch, 015h, 012h, 00Dh, 000h, 006h, 006h
        DB 002h, 004h, 000h, 000h, 000h, 000h, 002h, 004h, 008h, 008h
        DB 008h, 004h, 002h, 000h, 008h, 004h, 002h, 002h, 002h, 004h
        DB 008h, 000h, 000h, 004h, 015h, 00Eh, 015h, 004h, 000h, 000h
        DB 000h, 004h, 004h, 01Fh, 004h, 004h, 000h, 000h, 000h, 000h
        DB 000h, 00Ch, 00Ch, 004h, 008h, 000h, 000h, 000h, 000h, 01Fh
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 00Ch
        DB 00Ch, 000h, 000h, 001h, 002h, 004h, 008h, 010h, 000h, 000h
        DB 00Eh, 011h, 013h, 015h, 019h, 011h, 00Eh, 000h, 004h, 00Ch
        DB 004h, 004h, 004h, 004h, 00Eh, 000h, 00Eh, 011h, 001h, 006h
        DB 008h, 010h, 01Fh, 000h, 01Fh, 001h, 002h, 006h, 001h, 011h
        DB 00Eh, 000h, 002h, 006h, 00Ah, 012h, 01Fh, 002h, 002h, 000h
        DB 01Fh, 010h, 01Eh, 001h, 001h, 011h, 00Eh, 000h, 007h, 008h
        DB 010h, 01Eh, 011h, 011h, 00Eh, 000h, 01Fh, 001h, 002h, 004h
        DB 008h, 008h, 008h, 000h, 00Eh, 011h, 011h, 00Eh, 011h, 011h
        DB 00Eh, 000h, 00Eh, 011h, 011h, 00Fh, 001h, 002h, 01Ch, 000h
        DB 000h, 00Ch, 00Ch, 000h, 000h, 00Ch, 00Ch, 000h, 00Ch, 00Ch
        DB 000h, 00Ch, 00Ch, 004h, 008h, 000h, 002h, 004h, 008h, 010h
        DB 008h, 004h, 002h, 000h, 000h, 000h, 01Fh, 000h, 01Fh, 000h
        DB 000h, 000h, 008h, 004h, 002h, 001h, 002h, 004h, 008h, 000h
        DB 00Eh, 011h, 001h, 002h, 004h, 000h, 004h, 000h, 00Eh, 011h
        DB 013h, 015h, 017h, 010h, 00Eh, 000h, 004h, 00Ah, 011h, 011h
        DB 01Fh, 011h, 011h, 000h, 01Eh, 011h, 011h, 01Eh, 011h, 011h
        DB 01Eh, 000h, 00Eh, 011h, 010h, 010h, 010h, 011h, 00Eh, 000h
        DB 01Eh, 009h, 009h, 009h, 009h, 009h, 01Eh, 000h, 01Fh, 010h
        DB 010h, 01Eh, 010h, 010h, 01Fh, 000h, 01Fh, 010h, 010h, 01Eh
        DB 010h, 010h, 010h, 000h, 00Eh, 011h, 010h, 010h, 013h, 011h
        DB 00Fh, 000h, 011h, 011h, 011h, 01Fh, 011h, 011h, 011h, 000h
        DB 00Eh, 004h, 004h, 004h, 004h, 004h, 00Eh, 000h, 001h, 001h
        DB 001h, 001h, 011h, 011h, 00Eh, 000h, 011h, 012h, 014h, 018h
        DB 014h, 012h, 011h, 000h, 010h, 010h, 010h, 010h, 010h, 011h
        DB 01Fh, 000h, 011h, 01Bh, 015h, 015h, 011h, 011h, 011h, 000h
        DB 011h, 011h, 019h, 015h, 013h, 011h, 011h, 000h, 00Eh, 011h
        DB 011h, 011h, 011h, 011h, 00Eh, 000h, 01Eh, 011h, 011h, 01Eh
        DB 010h, 010h, 010h, 000h, 00Eh, 011h, 011h, 011h, 015h, 012h
        DB 00Dh, 000h, 01Eh, 011h, 011h, 01Eh, 014h, 012h, 011h, 000h
        DB 00Eh, 011h, 010h, 00Eh, 001h, 011h, 00Eh, 000h, 01Fh, 004h
        DB 004h, 004h, 004h, 004h, 004h, 000h, 011h, 011h, 011h, 011h
        DB 011h, 011h, 00Eh, 000h, 011h, 011h, 011h, 00Ah, 00Ah, 004h
        DB 004h, 000h, 011h, 011h, 011h, 015h, 015h, 015h, 00Ah, 000h
        DB 011h, 011h, 00Ah, 004h, 00Ah, 011h, 011h, 000h, 011h, 011h
        DB 00Ah, 004h, 004h, 004h, 004h, 000h, 01Fh, 001h, 002h, 00Eh
        DB 008h, 010h, 01Fh, 000h, 00Eh, 008h, 008h, 008h, 008h, 008h
        DB 00Eh, 000h, 000h, 010h, 008h, 004h, 002h, 001h, 000h, 000h
        DB 00Eh, 002h, 002h, 002h, 002h, 002h, 00Eh, 000h, 00Eh, 011h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 01Fh, 012h, 015h, 015h, 01Dh, 015h, 015h
        DB 012h, 000h, 004h, 00Ah, 011h, 011h, 01Fh, 011h, 011h, 000h
        DB 01Fh, 010h, 010h, 01Eh, 011h, 011h, 01Eh, 000h, 012h, 012h
        DB 012h, 012h, 012h, 01Fh, 001h, 000h, 006h, 00Ah, 00Ah, 00Ah
        DB 00Ah, 01Fh, 011h, 000h, 01Fh, 010h, 010h, 01Eh, 010h, 010h
        DB 01Fh, 000h, 004h, 01Fh, 015h, 015h, 01Fh, 004h, 004h, 000h
        DB 01Fh, 011h, 010h, 010h, 010h, 010h, 010h, 000h, 011h, 011h
        DB 00Ah, 004h, 00Ah, 011h, 011h, 000h, 011h, 011h, 013h, 015h
        DB 019h, 011h, 011h, 000h, 015h, 011h, 013h, 015h, 019h, 011h
        DB 011h, 000h, 011h, 012h, 014h, 018h, 014h, 012h, 011h, 000h
        DB 007h, 009h, 009h, 009h, 009h, 009h, 019h, 000h, 011h, 01Bh
        DB 015h, 015h, 011h, 011h, 011h, 000h, 011h, 011h, 011h, 01Fh
        DB 011h, 011h, 011h, 000h, 00Eh, 011h, 011h, 011h, 011h, 011h
        DB 00Eh, 000h, 01Fh, 011h, 011h, 011h, 011h, 011h, 011h, 000h
        DB 00Fh, 011h, 011h, 00Fh, 005h, 009h, 011h, 000h, 01Eh, 011h
        DB 011h, 01Eh, 010h, 010h, 010h, 000h, 00Eh, 011h, 010h, 010h
        DB 010h, 011h, 00Eh, 000h, 01Fh, 004h, 004h, 004h, 004h, 004h
        DB 004h, 000h, 011h, 011h, 011h, 00Ah, 004h, 008h, 010h, 000h
        DB 011h, 015h, 015h, 00Eh, 015h, 015h, 011h, 000h, 01Eh, 011h
        DB 011h, 01Eh, 011h, 011h, 01Eh, 000h, 010h, 010h, 010h, 01Eh
        DB 011h, 011h, 01Eh, 000h, 011h, 011h, 011h, 019h, 015h, 015h
        DB 019h, 000h, 00Eh, 011h, 001h, 006h, 001h, 011h, 00Eh, 000h
        DB 011h, 015h, 015h, 015h, 015h, 015h, 01Fh, 000h, 00Eh, 011h
        DB 001h, 007h, 001h, 011h, 00Eh, 000h, 015h, 015h, 015h, 015h
        DB 015h, 01Fh, 001h, 000h, 011h, 011h, 011h, 01Fh, 001h, 001h
        DB 001h, 000h, 03Fh, 03Fh, 03Fh, 03Fh, 03Fh, 03Fh, 03Fh, 03Fh
END