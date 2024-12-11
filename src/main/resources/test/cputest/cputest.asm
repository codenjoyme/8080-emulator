        CPU  8080
        .ORG 00000h
lmhi    EQU 0C300h
laop    EQU 0CD00h
lkih    EQU 05800h
lkwu    EQU 0E100h
lced    EQU 05801h
lqyx    EQU 05802h
lmvv    EQU 0FD03h
lwon    EQU 05803h
lcfd    EQU 0FF03h
lwpn    EQU 0FE03h
lypo    EQU 05804h
lwba    EQU 0A805h
lggf    EQU 0BA05h
lsml    EQU 0B105h
lunm    EQU 0AC05h
lege    EQU 0FF05h
lozw    EQU 0D705h
lsol    EQU 09305h
lwqn    EQU 08705h
lkxu    EQU 09205h
lupm    EQU 09705h
lcrq    EQU 05807h
lmji    EQU 05817h
luzz    EQU 05818h
lokj    EQU 05830h
lojj    EQU 0D540h
lede    EQU 04F43h
ladc    EQU 05958h
lasp    EQU 0586Dh
lgts    EQU 05876h
lqlk    EQU 05884h
lwza    EQU 0EACDh
lgus    EQU 058F2h
letr    EQU 058F3h
lcsq    EQU 058F4h
larp    EQU 058F5h
lyqo    EQU 058F6h
luom    EQU 058F7h
lsnl    EQU 058F8h
lqmk    EQU 058F9h
lolj    EQU 058FAh
lmki    EQU 058FBh
lkjh    EQU 058FCh
liig    EQU 058FDh
lghf    EQU 058FEh
lihg    EQU 058FFh
livt:   MVI A,002h
        INR A
        JPE labc
        JMP lccd
        DB 0F3h
lkgh:   DB 031h, 0FFh, 02Fh, 0CDh, 04Fh, 021h, 0CDh, 04Eh, 00Bh
lyab:   DB 00Dh
lacc:   DB 00Ah, 044h, 049h, 041h, 047h, 04Eh, 04Fh, 053h, 054h, 049h
        DB 043h, 053h
lsxy:   DB 020h, 049h, 049h, 020h, 056h, 031h, 02Eh, 032h, 020h, 02Dh
        DB 020h
lwnn:   MOV B,E
        MOV D,B
        MOV D,L
        NONE
        MOV D,H
        MOV B,L
        MOV D,E
        MOV D,H
lumm:   DCR C
        LDAX B
        MOV B,E
        MOV C,A
        MOV D,B
        MOV E,C
        MOV D,D
        MOV C,C
        MOV B,A
        MOV C,B
        MOV D,H
        NONE
        NONE
        MOV B,E
        DAD H
        NONE
        LXI SP,lgef
        LXI SP,lifg
        NONE
        MOV D,E
        MOV D,L
        MOV D,B
        MOV B,L
        MOV D,D
        MOV D,E
        MOV C,A
        MOV B,M
        MOV D,H
        NONE
        MOV B,C
        MOV D,E
        MOV D,E
        MOV C,A
        MOV B,E
        MOV C,C
        MOV B,C
        MOV D,H
        MOV B,L
        MOV D,E
        DCR C
        LDAX B
        LDAX B
        NOP
        JMP loij
        DB 041h, 054h, 045h, 053h, 00Dh, 00Ah, 00Ah, 000h, 0CDh, 00Fh
        DB 009h, 0C3h, 08Dh, 001h, 021h, 078h, 00Ch, 0CDh, 04Eh, 00Bh
        DB 044h, 042h, 046h, 04Ch, 041h, 047h, 020h, 049h, 053h, 020h
        DB 041h, 054h, 020h, 000h, 0CDh, 08Bh, 00Bh, 0CDh, 065h, 00Ch
        DB 01Eh, 041h, 00Eh, 002h, 0CDh, 005h, 000h, 01Eh, 042h, 0CDh
        DB 0E6h, 003h, 01Eh, 043h
        CALL lqjk
        LDA lskl
        ORA A
        JNZ lulm
        CALL lwmn
        JMP lyno
        DB 0CDh, 04Eh, 00Bh, 044h, 000h, 03Eh, 045h, 00Eh, 016h, 0CDh
        DB 00Eh, 00Bh, 03Ch, 00Dh, 0C2h, 0B4h, 001h, 0C3h, 0CAh, 001h
        DB 051h, 052h, 053h, 054h, 055h, 056h, 057h, 058h, 059h, 05Ah
        DB 000h, 03Eh, 002h, 03Ch, 0EAh, 0D5h, 001h, 03Eh, 001h, 0C3h
        DB 0D6h, 001h, 0AFh, 032h, 072h, 00Ch, 0CDh, 00Fh, 009h, 0C3h
        DB 02Bh, 002h, 032h, 073h, 00Ch, 0B7h, 0C2h, 02Bh, 002h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 054h, 055h, 052h, 04Bh, 045h, 059h
        DB 02Dh, 02Dh, 047h, 04Fh, 020h, 046h, 049h, 04Eh, 044h, 020h
        DB 041h, 020h, 05Ah, 038h, 030h, 021h, 020h, 020h, 020h, 04Fh
        DB 052h, 020h, 049h, 020h
lccd:   MOV D,A
        MOV C,A
        MOV C,M
        DAA
        MOV D,H
        NONE
        MOV D,H
        MOV B,L
        MOV D,E
        MOV D,H
        NONE
        MOV D,H
        MOV C,B
        MOV B,L
        NONE
        MOV D,D
        MOV B,L
        MOV B,M
        MOV D,D
        MOV B,L
        MOV D,E
        MOV C,B
        NONE
        MOV D,D
        MOV B,L
        MOV B,A
        MOV C,C
        MOV D,E
        MOV D,H
        MOV B,L
        MOV D,D
        DCR C
        LDAX B
        NOP
        CALL lcpq
        JMP leqr
        DB 0AFh, 0CDh, 01Dh, 009h, 032h, 072h, 00Ch, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 043h, 050h, 055h, 020h, 049h, 053h, 020h, 000h
        DB 03Ah, 072h, 00Ch, 0B7h, 0CAh, 080h, 002h, 0CDh, 04Eh, 00Bh
        DB 05Ah, 038h, 030h, 000h, 03Ah, 073h, 00Ch, 0B7h, 0C2h, 08Dh
        DB 002h, 0CDh, 04Eh, 00Bh, 020h, 028h, 052h, 045h, 046h, 052h
        DB 045h, 053h, 048h, 020h, 052h, 045h, 047h, 049h, 053h, 054h
loij:   MOV B,L
        MOV D,D
        NONE
        MOV C,M
        MOV C,A
        MOV D,H
        NONE
        MOV B,E
        MOV C,B
        MOV B,L
        MOV B,E
        MOV C,E
        MOV B,L
        MOV B,H
        DAD H
        NOP
        JMP lgrs
        DB 0CDh, 04Eh, 00Bh, 038h, 030h, 038h, 030h, 02Fh, 038h, 030h
        DB 038h, 035h, 000h, 0AFh, 001h, 001h, 000h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 042h, 045h, 047h, 049h, 04Eh, 020h, 054h, 049h
        DB 04Dh, 049h, 04Eh, 047h, 020h, 054h, 045h, 053h, 054h, 00Dh
        DB 00Ah, 000h, 0CDh
lulm:   MOV C,M
        INR C
        CALL lcpq
lyno:   JMP list
        DB 0CDh, 01Dh, 009h, 0C3h, 0C5h, 002h, 021h, 000h, 000h, 03Ch
        DB 0C2h, 0BCh, 002h, 009h, 0D2h, 0BCh, 002h, 0EBh, 0CDh, 04Eh
        DB 00Ch, 0CDh, 04Eh, 00Bh, 045h, 04Eh, 044h, 020h, 054h, 049h
        DB 04Dh, 049h, 04Eh, 047h, 020h, 054h, 045h, 053h, 054h, 00Dh
        DB 00Ah, 000h, 0CDh, 0F0h, 003h, 0CDh, 01Dh, 009h, 0C3h, 0ECh
        DB 002h, 0CDh, 00Fh, 009h, 0C3h, 057h, 003h, 0CDh, 04Eh, 00Bh
        DB 054h, 041h, 042h, 04Ch, 045h, 020h, 045h, 04Eh, 044h, 020h
        DB 000h, 02Ah, 07Eh, 00Ch, 0CDh
lgif:   DB 08Bh, 00Bh, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
laqp:   DB 054h, 041h, 042h, 04Ch, 045h, 020h, 053h, 049h, 05Ah, 045h
        DB 020h, 000h, 011h, 084h, 0F3h, 019h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 065h, 00Ch, 0C3h, 02Ah, 003h, 02Ah, 07Eh, 00Ch, 02Bh, 036h
        DB 001h, 02Bh, 036h, 001h, 02Bh, 036h, 001h, 0CDh, 067h, 00Ah
        DB 03Ah, 074h, 00Ch, 021h, 075h, 00Ch, 086h, 057h, 03Eh, 001h
        DB 0CDh
leqr:   CPI 008h
        STA lskl
        STA lktu
        LDA lktu
        LXI H,00C75h
        ADD M
        SUB D
        MOV D,A
        LDA lmuv
        SUB D
        STA lmuv
        CALL lovw
        JMP lccd
        DB 0C7h, 02Ah, 07Ah, 00Ch, 07Ch, 0B5h, 0CAh, 072h, 003h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 04Eh, 00Bh, 020h, 045h, 052h, 052h, 04Fh
        DB 052h, 053h, 00Dh, 00Ah, 000h, 0C3h, 084h, 003h, 0CDh, 04Eh
        DB 00Bh, 043h, 050h, 055h, 020h, 054h, 045h, 053h, 054h, 053h
        DB 020h, 04Fh, 04Bh, 00Dh, 00Ah, 000h, 0CDh, 02Bh, 00Ch, 0C3h
        DB 08Dh, 001h, 0F5h, 0CDh, 01Dh
lgrs:   DAD B
        JMP lqwx
        DB 0CDh, 00Fh, 009h, 0C3h, 0B7h, 003h, 0F1h, 0F5h, 0D5h, 057h
        DB 03Ah, 079h, 00Ch, 092h, 032h, 079h, 00Ch, 0D1h, 0F1h, 0F5h
        DB 0CDh, 04Eh, 00Bh, 03Ch, 000h, 0CDh, 0A1h, 00Bh, 0CDh, 04Eh
        DB 00Bh, 03Eh, 000h, 0CDh, 02Bh, 00Ch, 0F1h, 0C9h, 0CDh, 065h
        DB 00Ch, 0CDh, 04Eh, 00Bh, 043h, 048h, 045h, 043h, 04Bh, 053h
        DB 055h, 04Dh
list:   NONE
        MOV B,L
        MOV D,D
        MOV D,D
        MOV C,A
        MOV D,D
        DCR L
        DCR L
        MOV C,L
        MOV B,L
        MOV C,L
        MOV C,A
        MOV D,D
        MOV E,C
        NONE
        MOV C,L
        MOV B,C
        MOV E,C
        NONE
        MOV B,D
        MOV B,L
        NONE
        MOV B,D
        MOV B,C
        MOV B,H
        LDA lsxy
        POP PSW
        CALL luyz
        RST 0
        CALL lqjk
        RET
        DB 00Eh, 002h, 0CDh, 0BDh, 020h, 0C9h, 021h, 00Eh, 00Bh, 0CDh
        DB 0C0h, 009h, 0CDh, 023h, 006h, 021h, 080h, 00Ch, 022h, 07Eh
        DB 00Ch, 03Eh
liut:   DB 000h, 032h, 047h, 004h, 032h, 048h, 004h, 021h, 061h, 004h
        DB 016h, 001h
lkvu:   DB 07Eh, 023h, 0FEh, 000h, 0C2h, 024h, 004h, 0CDh, 016h, 009h
        DB 0CDh, 0FEh, 008h, 0CDh, 065h, 00Ch, 014h, 07Ah, 0FEh, 004h
        DB 0FAh, 00Ch, 004h, 0C9h, 032h, 046h, 004h, 07Ah, 0FEh, 002h
        DB 0FAh, 03Dh, 004h, 07Eh, 023h, 032h, 047h, 004h, 07Ah, 0FEh
        DB 003h, 0FAh, 03Dh, 004h, 07Eh, 023h, 032h
lctq:   DB 048h, 004h, 0D5h, 0E5h, 0F5h
lmii:   DB 021h, 0AAh, 00Ah, 0CDh, 0C0h, 009h, 0E4h, 05Fh, 004h, 0CDh
        DB 02Bh, 006h, 021h, 0DCh, 00Ah, 0CDh, 0C0h, 009h, 0CDh, 023h
        DB 006h, 0F1h, 0E1h, 0D1h
lesr:   DB 0C3h, 00Ch, 004h, 00Bh, 0C3h, 049h, 004h, 003h, 0C9h, 003h
        DB 004h, 005h, 007h, 009h, 00Bh, 00Ch, 00Dh, 00Fh, 013h, 014h
        DB 015h, 017h, 019h, 01Bh, 01Ch, 01Dh, 01Fh, 023h, 024h, 025h
        DB 027h, 029h, 02Bh, 02Ch, 02Dh, 02Fh, 037h, 039h, 03Ch, 03Dh
        DB 03Fh, 040h, 041h, 042h, 043h, 044h, 045h, 047h, 048h, 049h
        DB 04Ah, 04Bh, 04Ch, 04Dh, 04Fh, 050h, 051h, 052h, 053h, 054h
        DB 055h, 057h, 058h
lqwx:   MOV E,C
        MOV E,D
        MOV E,E
        MOV E,H
        MOV E,L
        MOV E,A
        MOV H,B
        MOV H,C
        MOV H,D
        MOV H,E
        MOV H,H
        MOV H,L
        MOV H,A
        MOV L,B
        MOV L,C
        MOV L,D
        MOV L,E
        MOV L,H
        MOV L,L
        MOV L,A
        MOV A,B
        MOV A,B
        MOV A,C
        MOV A,D
        MOV A,E
        MOV A,H
        MOV A,L
        MOV A,A
        ADD B
        ADD C
        ADD D
        ADD E
        ADD H
        ADD L
        ADD A
        ADC B
        ADC C
        ADC D
        ADC E
        ADC H
        ADC L
        SUB B
        SUB C
        SUB D
        SUB E
        SUB H
        SUB L
        SUB A
        ANA B
        ANA C
        ANA D
        ANA E
        ANA H
        ANA L
        ANA A
        XRA B
        XRA C
        XRA D
        XRA E
        XRA H
        XRA L
        XRA A
        ORA B
        ORA C
        ORA D
        ORA E
        ORA H
        ORA L
        ORA A
        CMP B
        CMP C
        CMP D
        CMP E
        CMP H
        CMP L
        CMP A
        XCHG
        DI
        NOP
        MVI B,002h
        MVI C,003h
lqjk:   MVI D,004h
        MVI E,005h
        MVI H,006h
        MVI L,007h
        MVI A,009h
        ADI 00Ah
        ACI 00Bh
        SUI 00Ch
        SBI 00Dh
        ANI 00Eh
        XRI 00Fh
        ORI 010h
        CPI 011h
        MVI H,063h
        PUSH H
        POP B
        MVI B,058h
        PUSH B
        POP D
        PUSH PSW
        POP H
        INX SP
        DCX SP
        NOP
        LXI B,00C76h
        LXI D,lyab
        LXI H,lacc
        SHLD lcdd
        LHLD leee
        LHLD lcdd
        STA lcdd
        LDA lgff
        LDA lcdd
        LXI D,00C76h
        MVI A,04Dh
        STAX D
        MVI A,042h
        STAX B
        INR A
        LDAX B
        MOV B,B
        INX B
        LDAX B
        MOV B,B
        PUSH D
        XTHL
        POP B
        LXI H,00C76h
        MOV M,A
        MOV B,M
        INR B
        MOV M,B
        MOV C,M
        INR C
        MOV M,C
        MOV D,M
        INR D
        MOV M,E
        MOV A,M
        INR A
        MOV M,A
        MOV D,H
        MOV H,M
        MOV H,D
        MOV D,L
        MOV L,M
        MOV L,D
        ADD M
        MOV M,B
        ADD M
        MOV M,B
        MOV B,B
        ADC M
        MOV M,B
        MOV B,B
        SUB M
        MOV M,B
        MOV B,B
        ANA M
        MOV M,B
        MOV B,B
        XRA M
        MOV M,B
        MOV B,B
        ORA M
        MOV M,B
        MOV B,B
        CMP M
        MOV M,B
        MOV B,B
        MVI M,058h
        MOV B,B
        LXI H,0045Bh
        PCHL
        DB 03Ch, 004h, 011h, 0FFh, 0FFh
lmwv:   DB 0D5h, 0F1h, 07Fh, 0E5h, 0C9h, 01Bh, 0E5h, 0C8h, 01Bh, 0E5h
        DB 0D8h, 01Bh, 0E5h, 0F8h, 01Bh, 0E5h, 0E8h, 01Bh, 0C0h, 01Bh
        DB 01Bh, 0D0h, 01Bh, 01Bh, 0F0h, 01Bh, 01Bh, 0E0h, 01Bh, 01Bh
        DB 0C3h, 05Bh, 004h, 0CAh, 05Bh, 004h, 0DAh, 05Bh, 004h, 0FAh
        DB 05Bh, 004h, 0EAh, 05Bh, 004h, 0C2h, 05Bh, 004h, 0D2h, 05Bh
        DB 004h, 0F2h, 05Bh, 004h, 0E2h, 05Bh, 004h, 0CDh, 05Fh, 004h
        DB 0CCh, 05Fh, 004h, 0DCh, 05Fh, 004h, 0FCh, 05Fh, 004h, 0ECh
        DB 05Fh, 004h, 0C4h, 05Fh, 004h, 0D4h, 05Fh, 004h, 0F4h, 05Fh
        DB 004h, 0E4h, 05Fh, 004h, 011h, 000h, 000h, 0D5h, 0F1h, 07Fh
        DB 0E5h, 0C9h, 01Bh, 0C8h, 01Bh, 01Bh, 0D8h, 01Bh, 01Bh, 0F8h
        DB 01Bh, 01Bh, 0E8h, 01Bh, 01Bh, 0E5h, 0C0h, 01Bh, 0E5h, 0D0h
        DB 01Bh, 0E5h, 0F0h, 01Bh, 0E5h, 0E0h, 01Bh, 0C3h, 05Bh, 004h
        DB 0CAh, 05Bh, 004h, 0DAh, 05Bh, 004h, 0FAh, 05Bh, 004h, 0EAh
        DB 05Bh, 004h, 0C2h, 05Bh, 004h, 0D2h, 05Bh
lefe:   DB 004h, 0F2h, 05Bh
leur:   DB 004h, 0E2h, 05Bh, 004h, 0CDh, 05Fh, 004h, 0CCh, 05Fh, 004h
        DB 0DCh, 05Fh, 004h, 0FCh, 05Fh, 004h, 0ECh, 05Fh, 004h, 0C4h
        DB 05Fh
lybb:   DB 004h
lwaa:   DB 0D4h, 05Fh, 004h, 0F4h
leee:   DB 05Fh, 004h, 0E4h, 05Fh, 004h, 000h, 0E5h, 021h, 0AAh
lgff:   DB 00Ah, 0CDh, 03Ah, 009h, 0C9h, 0E5h, 021h, 0DCh, 00Ah, 0CDh
        DB 03Ah, 009h, 0E5h, 0D5h, 0F5h, 021h, 0AAh, 00Ah, 011h, 0DCh
        DB 00Ah, 0CDh, 016h, 009h, 0CDh, 0FEh, 008h, 0CDh, 023h, 007h
        DB 0CDh, 016h, 009h, 0CDh, 0FEh, 008h, 0CDh, 06Ch, 00Ch, 0CDh
        DB 075h, 006h, 0F1h, 0D1h, 0E1h, 0C9h, 01Fh, 04Bh, 001h, 059h
        DB 044h, 010h, 062h, 063h, 064h, 065h, 061h, 066h, 068h, 06Ch
        DB 042h, 043h, 044h, 045h, 041h, 046h, 048h, 04Ch, 049h, 058h
        DB 04Ah, 059h, 073h, 070h, 056h, 052h, 024h, 024h, 024h, 0C5h
        DB 00Eh, 0FFh, 0E5h, 02Ah, 07Eh, 00Ch, 022h, 058h, 006h, 0E1h
        DB 0AFh, 032h, 07Ch, 00Ch, 00Ch, 079h, 0FEh, 019h, 0F2h, 0D4h
        DB 006h, 02Bh, 07Eh, 032h, 054h, 006h, 0EBh, 02Bh, 046h, 0F5h
        DB 078h, 032h, 055h, 006h, 0F1h, 0EBh, 0B8h, 0CAh, 078h, 006h
        DB 03Ah, 072h, 00Ch, 0B7h, 0C2h, 0ABh, 006h, 079h, 0FEh, 008h
        DB 0F2h, 0E9h, 006h, 079h, 0FEh, 017h, 0F2h, 0E9h, 006h, 079h
        DB 032h, 056h, 006h, 0CDh, 09Dh, 007h, 0CDh, 016h, 009h, 0CDh
        DB 0FEh, 008h, 0CDh, 053h, 007h, 03Ah, 055h, 006h, 0CDh, 09Dh
        DB 007h, 03Ah, 07Ch, 00Ch, 0B7h, 0CDh, 00Fh, 009h, 0C4h, 0C6h
        DB 007h, 0C3h, 078h, 006h, 03Eh, 058h, 0CDh, 09Dh, 007h, 03Eh
        DB 059h, 0CDh, 09Dh, 007h, 0C1h, 0CDh, 016h, 009h, 0CDh, 0FEh
        DB 008h, 0CDh, 065h, 00Ch, 0C9h, 0CDh, 0FEh, 008h, 0C3h, 0D4h
        DB 006h, 0E5h, 02Ah, 058h, 006h, 022h, 07Eh, 00Ch, 0E1h, 0CDh
        DB 018h, 007h, 0FEh, 058h, 0CAh, 005h, 007h, 0CDh, 018h, 007h
        DB 0C3h, 0E9h, 006h, 0CDh, 018h, 007h, 0FEh, 059h, 0C2h, 0E9h
lszy:   DB 006h, 02Ah, 07Eh, 00Ch, 02Bh, 02Bh, 022h, 07Eh
loxw:   DB 00Ch, 0C3h, 0D4h, 006h, 0E5h, 02Ah, 07Eh, 00Ch, 07Eh, 023h
        DB 022h, 07Eh, 00Ch, 0E1h, 0C9h, 03Ah, 046h, 004h, 0CDh, 0A1h
        DB 00Bh, 03Ah, 047h, 004h, 0CDh, 0A1h, 00Bh, 03Ah, 048h, 004h
        DB 0CDh, 0A1h, 00Bh, 0C9h, 0E5h, 021h, 05Ah, 006h, 0CDh
lgvs:   DB 083h, 00Bh, 0FEh, 041h, 0FAh, 04Eh, 007h, 0FEh, 05Bh, 0F2h
        DB 04Eh, 007h, 0F5h, 03Eh, 05Ah, 0CDh, 00Eh, 00Bh, 0F1h, 0CDh
        DB 00Eh, 00Bh, 0E1h, 0C9h, 0E5h, 0D5h, 03Ah, 056h, 006h, 0CDh
        DB 0A1h, 00Bh, 0CDh, 04Eh, 00Bh, 028h, 000h, 0CDh, 036h, 007h
        DB 0CDh, 04Eh, 00Bh, 029h, 03Ah, 000h, 03Ah, 054h, 006h, 0CDh
liwt:   DB 0A1h, 00Bh, 0CDh, 04Eh, 00Bh, 02Dh, 03Eh, 000h, 03Ah
luaz:   DB 055h, 006h, 0CDh, 0A1h, 00Bh, 0CDh, 04Eh, 00Bh, 028h, 000h
        DB 02Ah, 07Eh, 00Ch, 023h, 07Eh, 0CDh, 0A1h, 00Bh, 0CDh, 04Eh
        DB 00Bh, 02Eh, 000h, 03Ah, 057h, 006h, 0CDh, 0A1h, 00Bh, 0CDh
        DB 04Eh, 00Bh, 029h, 02Ch, 020h, 000h, 0D1h, 0E1h, 0C9h, 0F5h
        DB 0E5h, 02Ah, 07Eh, 00Ch, 0F5h, 07Eh, 032h, 057h, 006h, 0F1h
        DB 0BEh, 0CAh, 0B7h, 007h, 0F5h, 03Ah, 074h, 00Ch, 032h, 07Ch
        DB 00Ch, 0F1h, 0C3h, 0B7h, 007h, 0CDh, 0FEh, 008h, 0CDh, 0C4h
        DB 007h, 023h, 022h, 07Eh, 00Ch, 0E1h, 0F1h, 0C9h, 077h, 0C9h
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 043h, 050h, 055h, 020h, 046h
        DB 041h, 049h, 04Ch, 045h, 044h, 03Ah, 020h, 000h, 0F5h, 03Eh
        DB 001h, 032h, 07Ch, 00Ch, 0F1h, 02Ah, 07Ah, 00Ch, 023h, 022h
        DB 07Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 045h, 052h, 052h
        DB 04Fh, 052h, 020h, 043h, 04Fh, 055h, 04Eh, 054h, 020h, 000h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 04Eh, 00Bh, 048h, 00Dh, 00Ah, 000h
        DB 02Ah, 07Eh, 00Ch, 0F5h, 0CDh, 01Dh, 009h, 0CDh, 053h, 007h
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 049h, 04Eh, 053h, 054h, 052h
        DB 055h, 043h, 054h, 049h, 04Fh, 04Eh, 020h, 053h, 045h, 051h
        DB 055h, 045h, 04Eh, 043h, 045h, 020h, 057h, 041h, 053h, 020h
        DB 000h, 0CDh, 023h, 007h, 0CDh, 04Eh, 00Bh, 048h, 00Dh, 00Ah
        DB 052h, 045h, 047h, 049h, 053h, 054h, 045h, 052h, 020h, 000h
        DB 03Ah, 056h, 006h, 0CDh, 036h, 007h, 0F1h, 0F5h, 0CDh, 04Eh
        DB 00Bh, 020h, 043h, 04Fh, 04Eh, 054h, 041h, 049h, 04Eh, 053h
        DB 020h, 000h, 0D5h, 03Ah, 055h, 006h, 0CDh, 096h, 00Bh, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 042h, 055h, 054h, 020h, 053h, 048h
        DB 04Fh, 055h, 04Ch, 044h, 020h, 043h, 04Fh, 04Eh, 054h, 041h
        DB 049h, 04Eh, 020h, 000h, 02Bh, 07Eh, 023h, 0CDh, 096h, 00Bh
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 052h, 045h, 047h, 049h, 053h
        DB 054h, 045h, 052h, 020h, 056h, 041h, 04Ch, 055h, 045h, 020h
        DB 042h, 045h, 046h, 04Fh, 052h, 045h, 020h, 049h, 04Eh, 053h
        DB 054h, 052h, 055h, 043h, 054h, 049h, 04Fh, 04Eh, 020h, 053h
        DB 045h, 051h, 055h, 045h, 04Eh, 043h, 045h, 020h, 057h, 041h
        DB 053h, 020h, 000h, 03Ah, 054h, 006h, 0CDh, 096h, 00Bh, 0D1h
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 054h, 045h, 053h, 054h, 020h
        DB 04Eh, 055h, 04Dh, 042h, 045h, 052h, 020h, 000h, 0D5h, 011h
        DB 083h, 0F3h, 019h, 0D1h, 0CDh, 06Ch, 00Ch, 0CDh, 08Bh, 00Bh
        DB 0CDh, 04Eh, 00Bh, 048h, 00Dh, 00Ah, 00Ah, 000h, 0CDh, 02Bh
        DB 00Ch, 0F1h, 0C9h, 0F5h, 0CDh, 0FEh, 008h, 0C3h, 00Dh, 009h
        DB 0F1h, 0E3h, 0F5h, 023h, 023h, 023h, 0AFh, 0BEh, 023h, 0C2h
        DB 0EFh, 008h, 0F1h, 0E3h, 0C9h, 0CDh, 0FEh, 008h, 0CDh, 0A1h
        DB 00Bh, 0C9h, 0F5h, 03Ah, 074h, 00Ch, 0B7h, 0CAh, 00Dh, 009h
        DB 0F1h, 0E3h, 023h, 023h, 023h, 0E3h, 0C9h, 0F1h, 0C9h, 0CDh
        DB 0FEh, 008h, 0C3h, 007h, 009h, 0C9h, 0CDh, 01Dh, 009h, 0C3h
        DB 007h, 009h, 0C9h, 0F5h, 03Ah, 078h, 00Ch, 0FEh, 001h, 0CAh
        DB 00Dh, 009h, 0F1h, 0C3h, 007h, 009h, 0CDh, 0B2h, 00Bh, 0CDh
        DB 02Bh, 00Ch, 0C9h, 0F5h, 0AFh, 03Ch, 0B7h, 0C2h, 033h, 009h
        DB 0F1h, 0C9h, 022h, 0BCh, 009h, 0E1h, 0E3h, 0E5h, 0F5h, 02Ah
        DB 0BCh, 009h, 02Bh, 070h, 02Bh, 071h, 02Bh, 072h, 02Bh, 073h
        DB 0D1h, 02Bh, 072h, 02Bh, 073h, 0EBh, 0E3h, 0EBh, 02Bh, 072h
        DB 02Bh, 073h, 0EBh, 0E3h, 0EBh, 0E3h, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0E3h, 0F5h, 02Bh, 070h, 02Bh, 071h, 02Bh, 072h, 02Bh
        DB 073h, 0D1h, 02Bh, 072h, 02Bh, 073h, 0EBh, 0E3h, 0EBh, 02Bh
        DB 072h, 02Bh, 073h, 0E5h, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0E1h
        DB 0CDh, 03Ch, 00Ah, 0DDh, 0E5h, 0CDh, 031h, 00Ah, 0E5h, 000h
        DB 0D1h, 02Bh, 072h, 02Bh, 073h, 0CDh, 03Ch, 00Ah, 0FDh, 0E5h
        DB 0CDh, 031h, 00Ah, 0E5h, 000h, 0D1h, 02Bh, 072h, 02Bh, 073h
        DB 0EBh, 021h, 0FEh, 0FFh, 039h, 0EBh, 02Bh, 072h, 02Bh, 073h
        DB 0CDh, 03Ch, 00Ah, 0EDh, 057h, 02Bh, 077h, 0CDh, 03Ch, 00Ah
        DB 0EDh, 05Fh, 02Bh, 077h, 0E1h, 02Ah, 0BCh, 009h, 0CDh, 0C0h
        DB 009h, 0C9h, 0AAh, 00Ah, 000h, 000h, 02Bh, 046h, 02Bh, 04Eh
        DB 02Bh, 056h, 02Bh, 05Eh, 0D5h, 02Bh, 056h, 02Bh, 05Eh, 0D5h
        DB 0F1h, 02Bh, 056h, 02Bh, 05Eh, 0E3h, 0EBh, 0CDh, 031h, 00Ah
        DB 033h, 033h, 0CDh, 031h, 00Ah, 0C9h, 000h, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0E1h, 02Bh, 046h, 02Bh, 04Eh, 02Bh, 056h, 02Bh
        DB 05Eh, 0D5h, 02Bh, 056h, 02Bh, 05Eh, 0D5h, 0F1h, 02Bh, 056h
        DB 02Bh, 05Eh, 0D5h, 02Bh, 056h, 02Bh, 05Eh, 0D5h
lcpq:   CALL lkhh
        NONE
        POP H
        CALL lqkk
        POP D
        NOP
        DCX H
        MOV D,M
        DCX H
        MOV E,M
        PUSH D
lkuu:   CALL lkhh
        NONE
        POP H
        CALL lqkk
        POP D
        NOP
        DCX H
        DCX H
        PUSH PSW
        DCX H
        MOV A,M
        CALL lkhh
        NONE
        MOV B,A
        DCX H
        MOV A,M
        CALL lkhh
        NONE
        MOV C,A
        POP PSW
        POP H
        POP D
        CALL lkhh
        NONE
        NONE
        RET
        DB 0F5h, 03Ah, 072h, 00Ch, 0B7h, 0CAh, 00Dh, 009h, 0C3h, 044h
        DB 00Ah, 0F5h, 03Ah, 072h, 00Ch, 0B7h, 0C2h, 00Dh, 009h, 0F1h
        DB 0E3h, 023h, 023h, 0E3h, 0C9h, 011h, 009h, 001h, 02Ah, 07Eh
        DB 00Ch, 0CDh, 054h, 00Ah, 0C9h, 0C5h, 07Ch, 02Fh, 047h, 07Dh
        DB 02Fh, 04Fh, 003h, 0AFh, 062h, 06Bh, 086h, 009h, 013h, 0D2h
        DB 05Dh, 00Ah, 0C1h, 0C9h, 0CDh, 04Ah, 00Ah, 0B7h, 0C4h, 08Ah
        DB 003h, 01Eh, 044h, 0CDh, 0EAh, 003h, 0C9h, 000h, 000h, 000h
        DB 000h, 078h, 00Ah, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 074h, 000h, 0EFh
        DB 02Fh, 00Fh, 000h, 010h, 000h, 011h, 000h, 012h, 000h, 013h
        DB 000h, 014h, 000h, 05Bh, 004h, 000h, 000h, 0F8h, 0FFh, 0F7h
        DB 000h, 0AAh, 00Ah, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 04Bh, 000h, 0EFh
        DB 02Fh, 00Fh, 000h, 010h, 000h, 011h, 000h, 012h, 000h, 013h
        DB 000h, 014h, 000h, 05Bh, 004h, 000h, 000h, 0F8h, 0FFh, 0F7h
        DB 000h, 0DCh, 00Ah, 001h, 000h, 002h, 000h, 003h, 000h, 004h
        DB 000h, 005h, 000h, 006h, 000h, 007h, 000h, 008h, 000h, 009h
        DB 000h, 00Ah, 000h, 00Bh, 000h, 00Ch, 000h, 00Dh, 000h, 00Eh
        DB 000h, 00Fh, 000h, 010h, 000h, 011h, 000h, 012h, 000h, 013h
        DB 000h, 014h, 000h, 015h, 000h, 016h, 000h, 017h, 000h, 018h
        DB 000h, 0C5h, 0D5h, 0E5h, 0F5h, 05Fh, 0CDh, 0EAh, 003h, 0F1h
        DB 0E1h, 0D1h, 0C1h, 0C9h, 0C5h, 0D5h, 0E5h, 00Eh, 001h, 0CDh
        DB 005h, 000h, 0E1h, 0D1h, 0C1h, 0C9h, 0E5h, 0C5h, 0D5h, 0F5h
        DB 079h, 0FEh, 002h, 0C2h, 03Dh, 00Bh
lqkk:   MOV A,E
        CPI 00Dh
        JZ lsll
        CALL lumm
        JMP lsll
lkhh:   DCX B
        CPI 001h
        JNZ lsll
        CALL lwnn
        ORA A
        JZ lyoo
        POP PSW
        POP D
        POP B
        POP H
        RET
        DB 0E3h, 0F5h, 07Eh, 023h, 0B7h, 0C2h, 059h, 00Bh, 0F1h, 0E3h
        DB 0C9h, 0CDh, 00Eh, 00Bh, 0C3h, 050h, 00Bh, 0F5h, 0E5h, 0D5h
        DB 0E6h, 00Fh, 021h, 073h, 00Bh
lwmn:   MVI D,000h
        MOV E,A
        DAD D
        MOV A,M
        CALL lapp
        POP D
        POP H
        POP PSW
        RET
        DB 030h, 031h, 032h, 033h, 034h, 035h, 036h, 037h, 038h, 039h
        DB 041h, 042h, 043h, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh
        DB 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh
        DB 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h
        DB 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h
        DB 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h
        DB 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h
        DB 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h
        DB 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 062h, 06Bh, 0CDh, 08Bh
lapp:   DCX B
        CALL lcqq
        LHLD lerr
        CALL lgss
        CALL lcqq
        LXI H,00000h
        DAD SP
        CALL lgss
        POP PSW
        LHLD lerr
        CALL lovw
        RET
        DB 002h, 0CDh, 0F6h, 020h, 0C7h, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch
lyoo:   STA litt
        POP PSW
        CPI 003h
        JNZ lkuu
lsll:   DCR C
        DAD B
        RST 0
        INX B
        NONE
lqxx:   PUSH PSW
        LDA loww
        CPI 053h
        JZ lkuu
        CALL lqxx
        RLC
        NOP
        POP PSW
        RET
        DB 0CDh
lmxv:   ORA D
        DCX B
        CALL lsyy
        RET
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh
        DB 020h, 000h, 0C9h, 001h, 001h, 001h, 001h, 058h, 000h, 000h
        DB 017h, 000h, 000h, 000h, 000h, 046h, 010h, 001h, 019h, 015h
        DB 0EFh, 058h, 059h, 000h, 001h, 005h, 000h, 058h
lgss:   MOV E,C
        NOP
        NOP
        DCR B
        MOV B,D
        MOV E,B
        MOV E,C
        DCR B
        MOV B,B
        MOV E,B
        MOV E,C
        RLC
        MVI L,058h
        MOV E,C
        LXI B,luzz
        MOV E,C
        LXI B,lwaa
luyz:   NONE
        MOV E,B
        MOV E,C
        LXI B,lybb
        LDAX B
        MOV E,B
        MOV E,C
        DCR B
        NOP
        MOV E,B
        MOV E,C
        INX B
        NONE
        MOV E,B
        MOV E,C
        STAX B
        LXI B,05958h
        STAX B
        NOP
        DCR B
        MOV B,D
        MOV E,B
        MOV E,C
        DCR B
        MOV B,B
        MOV E,B
        MOV E,C
        RLC
        MOV B,M
        MOV E,B
        MOV E,C
        INX B
        RAL
        MOV E,B
        MOV E,C
        INX B
        NONE
        DCR B
        NONE
        MOV E,B
        MOV E,C
        INX B
        RAL
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        DCR B
        NOP
        MOV E,B
        MOV E,C
        RLC
        MOV B,A
        MOV E,B
        MOV E,C
        MVI B,001h
        MOV E,B
        MOV E,C
        DCR B
        MOV B,D
        MVI B,000h
        MOV E,B
        MOV E,C
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        DCR B
        MOV B,H
        RLC
        ADC M
        MOV E,B
        MOV E,C
        RLC
        ADC L
        MOV E,B
        MOV E,C
        DCR B
        ADC B
        RLC
        ADC M
        MOV E,B
        MOV E,C
        DCR B
        ADC D
        RLC
        ADC L
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        CMP D
        MOV E,B
        MOV E,C
        DCR B
        XRA C
        MOV E,B
        MOV E,C
        DCR B
        ORA B
        MVI B,030h
        RLC
        ADD H
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV D,B
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        CMP D
        MOV E,B
        MOV E,C
        DCR B
        XRA C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        NONE
        MOV E,B
        MOV E,C
        NOP
        NOP
lerr:   MOV E,B
lsyy:   MOV E,C
        NOP
        RAL
        MOV E,B
        MOV E,C
        NOP
        NONE
        MOV E,B
        MOV E,C
        NOP
        ADD H
        MOV E,B
        MOV E,C
        NOP
        RST 7
        MOV E,B
        MOV E,C
        LXI B,lihg
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,lkih
        MOV E,C
        LXI B,lmji
        MOV E,C
        LXI B,lokj
litt:   MOV E,C
        LXI B,lqlk
        MOV E,C
        LXI B,lihg
        MOV E,C
        STAX B
        RST 7
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        STAX B
        RAL
        MOV E,B
        MOV E,C
        STAX B
        NONE
        MOV E,B
lovw:   MOV E,C
        STAX B
        ADD H
        MOV E,B
        MOV E,C
        STAX B
        RST 7
lcqq:   MOV E,B
        MOV E,C
        INX B
        RST 7
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
lktu:   MOV E,B
lskl:   MOV E,C
lcdd:   MOV E,B
        MOV E,C
        INX B
lmuv:   NONE
        MOV E,B
        MOV E,C
        INX B
        ADD H
        MOV E,B
        MOV E,C
        INX B
        RST 7
        MOV E,B
        MOV E,C
        MVI B,0FFh
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,084h
        MOV E,B
        MOV E,C
        MVI B,0FFh
        MOV E,B
        MOV E,C
        RLC
        RST 7
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        CPI 005h
        CMP C
        MOV E,B
        MOV E,C
        INR B
        NONE
        MOV E,B
        MOV E,C
        INR B
        CM ladc
        INR B
        EI
        MOV E,B
        MOV E,C
        INR B
        JM ladc
        INR B
        SPHL
        MOV E,B
        MOV E,C
        INR B
        JP lsml
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        DI
        DCR B
        ORA E
        MOV E,B
        MOV E,C
        INR B
        CP ladc
        INR B
        PUSH PSW
        MOV E,B
        MOV E,C
        INR B
        ORI 058h
        MOV E,C
        INR B
        RST 6
        MOV E,B
        MOV E,C
        INR B
        RM
        DCR B
        CMP E
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,D
        MOV E,B
        MOV E,C
        DCR B
        MOV D,H
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        XRA H
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,H
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        XRA H
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,H
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        XRA H
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,H
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        XRA H
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        DCR B
        MOV L,D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        STAX B
        MOV E,B
        MOV E,C
        LXI B,lwon
        MOV E,C
        STAX B
        INR B
        MOV E,B
        MOV E,C
        INX B
        DCR B
        MOV E,B
        MOV E,C
        MVI B,006h
        MOV E,B
        MOV E,C
        RLC
        RLC
        MOV E,B
        MOV E,C
        INR B
        DAD B
        MOV E,B
        MOV E,C
        INR B
        INX D
        DCR B
        NONE
        MOV E,B
        MOV E,C
        INR B
        MVI E,005h
        NONE
        MOV E,B
        MOV E,C
        INR B
        STAX D
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        INR B
        DCR B
        DCR B
        STAX D
        MOV E,B
        MOV E,C
        INR B
        INR B
        DCR B
        NONE
        MOV E,B
        MOV E,C
        INR B
        DCX B
        DCR B
        NONE
        MOV E,B
        MOV E,C
        INR B
        DCX D
        DCR B
        INR C
        MOV E,B
        MOV E,C
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        MVI B,063h
        MOV E,B
        MOV E,C
        NOP
        MOV H,E
        LXI B,lcrq
        MOV E,C
        NOP
        MOV E,B
        MOV E,B
        MOV E,C
        STAX B
        MOV E,B
        INX B
        RLC
        MOV E,B
        MOV E,C
        MVI B,01Bh
        RLC
        STAX B
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        INR C
        LXI B,lgts
        MOV E,C
        STAX B
        NOP
        INX B
        INX D
        MOV E,B
        MOV E,C
        MVI B,000h
        RLC
        INR D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,01Dh
        RLC
        LHLD ladc
        MVI B,000h
        RLC
        INR D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        LDA ladc
        INR B
        DCX D
        MOV E,B
        MOV E,C
        STAX B
        INR C
        INX B
        HLT
        MOV E,B
        MOV E,C
        INR B
        MOV C,L
        MOV E,B
        MOV E,C
        INR B
        MOV B,D
        MOV E,B
        MOV E,C
        DCR B
        NOP
        MOV E,B
        MOV E,C
        LXI B,lmwv
        NOP
        MOV E,B
        MOV E,C
        NOP
        NOP
        LXI B,loxw
        INR C
        RLC
        HLT
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        LXI B,05958h
        LXI B,lqyx
        MOV E,C
        STAX B
        INX B
        MOV E,B
        MOV E,C
        INR B
        MOV M,A
        DCR B
        NONE
        MOV E,B
        MOV E,C
        STAX B
        INR C
        MVI B,077h
        MOV E,B
        MOV E,C
        STAX B
        HLT
        MVI B,00Ch
        RLC
        MOV M,A
        MOV E,B
        MOV E,C
        INR B
        XRI 005h
        XRA H
        RLC
        HLT
        MOV E,B
        MOV E,C
        INR B
        RST 5
        DCR B
        XRA B
        MOV E,B
        MOV E,C
        INR B
        RP
        DCR B
        ORA B
        MOV E,B
        MOV E,C
        INR B
        RST 5
        DCR B
        CMP D
        MOV E,B
        MOV E,C
        INR B
        LXI B,lycb
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,H
        MOV E,B
        MOV E,C
        INR B
        LXI B,00005h
        MOV E,B
        MOV E,C
        DCR B
        MOV B,D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,004h
        RLC
        MOV E,E
        MOV E,B
        MOV E,C
        LXI B,lced
        MOV E,C
        STAX B
        RST 7
        INX B
        RST 7
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        RST 7
        MOV E,B
        MOV E,C
        LXI B,lkih
        MOV E,C
        NOP
        NOP
        LXI B,lihg
        MOV E,C
        LXI B,lghf
        MOV E,C
        LXI B,liig
        MOV E,C
        LXI B,lkjh
        MOV E,C
        INX B
        NONE
        MOV E,B
        MOV E,C
        INX B
        EI
        MOV E,B
        MOV E,C
        INX B
        SPHL
        MOV E,B
        MOV E,C
        INX B
        RST 6
        MOV E,B
        MOV E,C
        LXI B,lmki
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,luom
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lmki
        MOV E,C
        LXI B,lkjh
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        STAX B
        NOP
        INX B
        NOP
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        NOP
        MOV E,B
        MOV E,C
        LXI B,lmki
        MOV E,C
        STAX B
        RST 7
        INX B
        CPI 058h
        MOV E,C
        INX B
        CM ladc
        INX B
        JM ladc
        INX B
        RM
        MOV E,B
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,luom
        MOV E,C
        LXI B,lyqo
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,larp
        MOV E,C
        LXI B,lcsq
        MOV E,C
        LXI B,letr
        MOV E,C
        LXI B,lgus
        MOV E,C
        LXI B,letr
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,lcsq
        MOV E,C
        LXI B,larp
        MOV E,C
        LXI B,lyqo
        MOV E,C
        LXI B,luom
        MOV E,C
        CPI 003h
        JNZ lkuu
        RST 0
        NOP
        NOP
        PUSH PSW
        LDA loww
        CPI 053h
        JZ lkuu
        CALL lqxx
        RLC
        NOP
        POP PSW
        RET
        DB 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h
        DB 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h
        DB 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h
        DB 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h
        DB 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah
        DB 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0CDh, 000h, 043h, 050h, 055h, 020h
lycb:   DB 020h, 020h, 020h, 020h, 043h, 04Fh, 04Dh, 001h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 043h, 050h
        DB 055h, 046h, 049h, 058h, 020h, 020h, 048h, 045h, 058h, 000h
        DB 000h, 000h, 012h, 0EFh, 0F0h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 043h, 050h, 055h, 032h, 020h, 020h, 020h, 020h, 043h, 04Fh
        DB 04Dh, 000h, 000h, 000h, 020h, 0F1h, 0F2h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h
        DB 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h
        DB 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h, 0E5h
        DB 0E5h, 0E5h, 0E5h, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh
        DB 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh
        DB 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h
        DB 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h
        DB 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h
        DB 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h
        DB 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h
        DB 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah
        DB 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h
        DB 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh
        DB 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh
        DB 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch
        DB 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh
        DB 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh
        DB 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh
        DB 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h
        DB 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h
        DB 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h
        DB 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh
        DB 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h
        DB 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h
        DB 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h
        DB 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h
        DB 069h, 0CDh
lmyv:   DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h
        DB 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh
        DB 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh
        DB 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h
        DB 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh
        DB 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh
        DB 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh
        DB 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h
        DB 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h
        DB 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h
        DB 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h
        DB 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h
        DB 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah
        DB 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h
        DB 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh
        DB 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh
        DB 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch
        DB 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh
        DB 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh
        DB 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh
        DB 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h
        DB 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h
        DB 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h
        DB 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh
        DB 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h
        DB 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h
        DB 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h
        DB 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h
        DB 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh
        DB 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh
        DB 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch
        DB 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h
        DB 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h
        DB 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h
        DB 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h
        DB 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h
        DB 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch
        DB 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h
        DB 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h
        DB 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh
        DB 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah
        DB 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h
        DB 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h
        DB 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h
        DB 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh
        DB 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h
        DB 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h
        DB 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh
        DB 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh
        DB 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h
        DB 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah
        DB 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h
        DB 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h
        DB 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch
        DB 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h
        DB 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch
        DB 00Ch, 060h, 069h, 0CDh
lyro:   DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h
        DB 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh
        DB 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh
        DB 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h
        DB 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh
        DB 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh
        DB 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh
        DB 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h
        DB 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h
        DB 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h
        DB 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h
        DB 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h
        DB 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah
        DB 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h
        DB 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh
        DB 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh
        DB 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch
        DB 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh
        DB 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh
        DB 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh
        DB 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h
        DB 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h
        DB 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h
        DB 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh
        DB 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h
        DB 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h
        DB 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h
        DB 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h
        DB 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh
        DB 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh
        DB 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch
        DB 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h
        DB 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h
        DB 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h
        DB 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h
        DB 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h
        DB 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch
        DB 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h
        NONE
        MOV B,D
        NONE
        MOV B,E
        NONE
        NONE
        MOV B,H
        NONE
        MOV B,L
        NONE
        NONE
        MOV C,B
        NONE
        MOV C,H
        NONE
        NONE
        MOV D,E
        NONE
        MOV D,B
        DCR C
        LDAX B
        NOP
        POP H
        PUSH H
        PUSH PSW
        CALL lgss
        CALL lcqq
        POP PSW
        PUSH PSW
        CALL luyz
        POP H
        PUSH H
        MOV A,L
        CALL luyz
        CALL lcqq
        MOV H,B
        MOV L,C
        CALL lgss
        CALL lcqq
        MOV H,D
        MOV L,E
        CALL lgss
        CALL lcqq
        LHLD lerr
        CALL lgss
        CALL lcqq
        LXI H,00000h
        DAD SP
        CALL lgss
        POP PSW
        LHLD lerr
        CALL lovw
        RET
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh
        CALL lmxv
        POP PSW
        RET
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch
        DB 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh
        DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah
        MOV C,H
        INR C
        CPI 053h
        JZ lkuu
        CALL lqxx
        RLC
        NOP
        POP PSW
        RET
        DB 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h
        DB 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h
        DB 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h
        DB 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h
        DB 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah
        DB 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h
        DB 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch
        DB 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh
        DB 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh
        DB 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh
        DB 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh
        DB 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h
        DB 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch
        DB 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h
        DB 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh
        DB 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch
        DB 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh
        DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h
        DB 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h
        DB 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h
        DB 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h
        DB 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh
        DB 000h, 000h, 000h, 000h, 000h, 044h, 049h, 041h, 047h, 020h
        DB 020h, 020h, 020h, 04Ch, 04Fh, 047h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0F5h, 0E5h, 0D5h
        DB 0C5h, 00Eh, 00Dh, 0CDh, 005h, 000h, 021h, 080h, 000h, 022h
        DB 001h, 020h, 03Eh, 080h, 032h, 003h, 020h, 0C1h, 0D1h, 0E1h
        DB 0F1h, 0C9h, 0E5h, 0D5h, 0C5h, 011h, 004h, 020h, 00Eh, 00Fh
        DB 0CDh, 005h, 000h, 0FEh, 0FFh, 0C2h, 052h, 020h, 037h, 0C3h
        DB 053h, 020h, 0A7h, 0C1h, 0D1h, 0E1h, 0C9h, 0E5h, 0D5h, 0C5h
        DB 00Eh, 016h, 0CDh, 005h, 000h, 0FEh, 0FFh, 0C2h, 068h, 020h
        DB 037h, 0C3h, 069h, 020h, 0A7h, 0C1h, 0D1h, 0E1h, 0C9h, 0E5h
        DB 0D5h, 0C5h, 00Eh, 014h, 0CDh, 005h, 000h, 0A7h, 0CAh, 07Ah
        DB 020h, 037h, 0C1h, 0D1h, 0E1h, 0C9h, 0D5h, 0C5h, 0CDh, 06Dh
        DB 020h, 0D2h, 080h, 020h, 0FEh, 001h, 0CAh, 08Fh, 020h, 037h
        DB 0C3h, 0A9h, 020h, 03Ah, 024h, 020h, 03Dh, 032h, 024h, 020h
        DB 021h, 080h, 000h, 006h, 080h, 03Eh, 01Ah, 0BEh, 0CAh, 0A9h
        DB 020h, 023h, 005h, 0C2h, 09Dh, 020h, 021h, 000h, 000h, 0C1h
        DB 0D1h, 0C9h, 0E5h, 0D5h, 0C5h, 00Eh, 015h, 0CDh, 005h, 000h
        DB 0A7h, 0CAh, 0B9h, 020h, 037h, 0C1h, 0D1h, 0E1h, 0C9h, 0F5h
        DB 00Eh, 002h, 0CDh, 005h, 000h, 03Ah, 000h, 020h, 0B7h, 0C2h
        DB 0CCh, 020h, 0F1h, 0C9h, 0F1h, 0E5h, 0D5h, 0C5h, 04Fh, 02Ah
        DB 001h, 020h, 077h, 023h, 03Ah, 003h, 020h, 03Dh, 0C2h, 0EBh
        DB 020h, 011h, 004h, 020h, 0CDh, 0ACh, 020h, 0DAh, 0EBh, 020h
        DB 03Eh, 080h, 021h, 080h, 000h, 022h, 001h, 020h, 032h, 003h
        DB 020h, 079h, 0C1h, 0D1h, 0E1h, 0C9h, 0F5h, 03Ah, 000h, 020h
        DB 0B7h, 0C2h, 000h, 021h, 0F1h, 0C9h, 0E5h, 0D5h, 0C5h, 02Ah
        DB 001h, 020h, 036h, 01Ah, 011h, 004h, 020h, 0CDh, 0ACh, 020h
        DB 0D2h, 026h, 021h, 0CDh, 04Eh, 00Bh, 043h, 041h, 04Eh, 04Eh
        DB 04Fh, 054h, 020h, 046h, 04Ch, 055h, 053h, 048h, 020h, 046h
        DB 049h, 04Ch, 045h, 000h, 00Eh, 010h, 0CDh, 005h, 000h, 0FEh
        DB 0FFh, 0C2h, 04Ah, 021h, 0CDh, 04Eh, 00Bh, 043h, 041h, 04Eh
        DB 04Eh, 04Fh, 054h, 020h, 043h, 04Ch, 04Fh, 053h, 045h, 020h
        DB 054h, 048h, 045h, 020h, 046h, 049h, 04Ch, 045h, 000h, 0C7h
        DB 0C1h, 0D1h, 0E1h, 0F1h, 0C9h, 0F5h, 0E5h, 0D5h, 0C5h, 0AFh
        DB 032h, 000h, 020h, 021h, 080h, 000h, 07Eh, 0FEh, 004h, 0C2h
        DB 00Ah, 022h, 023h, 07Eh, 0FEh, 020h, 0C2h, 00Ah, 022h, 023h
        DB 07Eh, 0FEh, 04Ch, 0C2h, 00Ah, 022h, 023h, 07Eh, 0FEh, 04Fh
        DB 0C2h, 00Ah, 022h, 023h, 07Eh, 0FEh, 047h, 0C2h, 00Ah, 022h
        DB 03Eh, 001h, 032h, 000h, 020h, 0CDh, 025h, 020h, 011h, 004h
        DB 020h, 0CDh, 03Eh, 020h, 0D2h, 0C9h, 021h, 0CDh, 057h, 020h
        DB 0D2h, 0AEh, 021h, 0CDh, 04Eh, 00Bh, 044h, 049h, 053h, 04Bh
        DB 020h, 04Fh, 052h, 020h, 044h, 049h, 052h, 045h, 043h, 054h
        DB 04Fh, 052h, 059h, 020h, 046h, 055h, 04Ch, 04Ch, 000h, 0C7h
        DB 0CDh, 03Eh, 020h, 0D2h, 00Ah, 022h, 0CDh, 04Eh, 00Bh, 043h
        DB 041h, 04Eh, 04Eh, 04Fh, 054h, 020h, 04Fh, 050h, 045h, 04Eh
        DB 020h, 046h, 049h, 04Ch, 045h, 000h, 0C7h, 0CDh, 07Eh, 020h
        DB 0D2h, 0E4h, 021h, 0CDh, 04Eh, 00Bh, 043h, 041h, 04Eh, 04Eh
        DB 04Fh, 054h, 020h, 052h, 045h, 041h, 044h, 020h, 046h, 049h
        DB 04Ch, 045h, 000h, 0C7h, 022h, 001h, 020h, 011h, 000h, 001h
        DB 07Bh, 095h, 032h, 003h, 020h, 07Ah, 09Ch, 0D2h, 00Ah, 022h
        DB 0CDh, 04Eh, 00Bh, 042h, 055h, 046h, 046h, 045h, 052h, 020h
        DB 04Dh, 041h, 054h, 048h, 020h, 045h, 052h, 052h, 04Fh, 052h
        DB 000h, 0C7h, 01Eh, 000h, 03Eh, 00Dh, 0CDh, 0BDh, 020h, 03Eh
        DB 00Ah, 0CDh, 0BDh, 020h, 03Eh, 00Dh, 0CDh, 0BDh, 020h, 03Eh
        DB 00Ah, 0CDh, 0BDh, 020h, 03Eh, 02Ah, 0CDh, 0BDh, 020h, 0CDh
        DB 0BDh, 020h, 0C1h, 0D1h, 0E1h, 0F1h, 0C9h, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh
        DB 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh
        DB 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh
        DB 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh
        DB 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h
        DB 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch
        DB 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h
        DB 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh
        DB 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch
        DB 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh
        DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h
        DB 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h
        DB 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h
        DB 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h
        DB 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh
        DB 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah
        DB 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h
        DB 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h
        DB 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh
        DB 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h
        DB 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh
        DB 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch
        DB 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh
        DB 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h
        DB 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h
        DB 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h
        DB 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h
        DB 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h
        DB 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h
        DB 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h
        DB 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh
        DB 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h
        DB 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h
        DB 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh
        DB 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h
        DB 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h
        DB 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h
        DB 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h
        DB 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h
        DB 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah
        DB 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h
        DB 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch
        DB 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh
        DB 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh
        DB 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh
        DB 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh
        DB 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h
        DB 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch
        DB 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h
        DB 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh
        DB 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch
        DB 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh
        DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h
        DB 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h
        DB 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h
        DB 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h
        DB 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh
        DB 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah
        DB 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h
        DB 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h
        DB 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh
        DB 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h
        DB 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh
        DB 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch
        DB 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh
        DB 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h
        DB 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h
        DB 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h
        DB 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h
        DB 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h
        DB 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h
        DB 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h
        DB 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh
        DB 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h
        DB 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h
        DB 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh
        DB 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h
        DB 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h
        DB 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h
        DB 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h
        DB 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h
        DB 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah
        DB 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h
        DB 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch
        DB 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh
        DB 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh
        DB 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh
        DB 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh
        DB 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h
        DB 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch
        DB 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h
        DB 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh
        DB 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch
        DB 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh
        DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh
lifg:   DB 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 0E6h, 032h, 00Dh
        DB 03Ah, 012h, 034h, 06Eh, 03Ah, 0E6h, 032h, 00Dh, 03Ah, 002h
        DB 020h, 0E6h, 032h, 00Dh, 03Ah, 006h, 00Ah, 0E6h, 032h, 056h
        DB 059h, 0B4h, 036h, 057h, 03Fh, 056h, 059h, 056h, 059h, 002h
        DB 000h, 0E6h, 032h, 00Dh, 03Ah, 01Ah, 03Ah, 000h, 000h, 017h
        DB 004h, 0F7h, 000h, 043h, 03Bh, 046h, 000h, 07Eh, 032h, 0CDh
        DB 0F3h, 031h, 0FFh, 02Fh, 0CDh, 04Fh, 021h, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 044h, 049h, 041h, 047h, 04Eh, 04Fh, 053h, 054h
        DB 049h, 043h, 053h, 020h, 049h, 049h, 020h, 056h, 031h, 02Eh
        DB 032h, 020h, 02Dh, 020h, 043h, 050h, 055h, 020h, 054h, 045h
        DB 053h, 054h, 00Dh, 00Ah, 043h, 04Fh, 050h, 059h, 052h, 049h
        DB 047h, 048h, 054h, 020h, 028h, 043h, 029h, 020h, 031h, 039h
        DB 038h, 031h, 020h, 02Dh, 020h, 053h, 055h, 050h, 045h, 052h
        DB 053h, 04Fh, 046h, 054h, 020h, 041h, 053h, 053h, 04Fh, 043h
        DB 049h, 041h, 054h, 045h, 053h, 00Dh, 00Ah, 00Ah, 000h, 0C3h
        DB 064h, 030h, 041h, 054h, 045h, 053h, 00Dh, 00Ah, 00Ah, 000h
        DB 0CDh, 006h, 038h, 0C3h, 084h, 030h, 021h, 06Fh, 03Bh, 0CDh
        DB 045h, 03Ah, 044h, 042h, 046h, 04Ch, 041h, 047h, 020h, 049h
        DB 053h, 020h, 041h, 054h, 020h, 000h, 0CDh, 082h, 03Ah, 0CDh
        DB 05Ch, 03Bh, 01Eh, 041h, 00Eh, 002h, 0CDh, 005h, 000h, 01Eh
        DB 042h, 0CDh, 0DDh, 032h, 01Eh, 043h, 0CDh, 0E1h, 032h, 03Ah
        DB 06Ch, 03Bh, 0B7h, 0C2h, 0A2h, 030h, 0CDh, 05Eh, 039h, 0C3h
        DB 0A7h, 030h, 0CDh, 045h, 03Ah, 044h, 000h, 03Eh, 045h, 00Eh
        DB 016h, 0CDh, 005h, 03Ah, 03Ch, 00Dh, 0C2h, 0ABh, 030h, 0C3h
        DB 0C1h, 030h, 051h, 052h, 053h, 054h, 055h, 056h, 057h, 058h
        DB 059h, 05Ah, 000h, 03Eh, 002h, 03Ch, 0EAh, 0CCh, 030h, 03Eh
        DB 001h, 0C3h, 0CDh, 030h, 0AFh, 032h, 069h, 03Bh, 0CDh, 006h
        DB 038h, 0C3h, 022h, 031h, 032h, 06Ah, 03Bh, 0B7h, 0C2h, 022h
        DB 031h, 0CDh, 045h, 03Ah, 00Dh, 00Ah, 054h, 055h, 052h, 04Bh
        DB 045h, 059h, 02Dh, 02Dh, 047h, 04Fh, 020h, 046h, 049h, 04Eh
        DB 044h, 020h, 041h, 020h, 05Ah, 038h, 030h, 021h, 020h, 020h
        DB 020h, 04Fh, 052h, 020h, 049h, 020h
labc:   MOV D,A
        MOV C,A
        MOV C,M
        DAA
        MOV D,H
        NONE
        MOV D,H
        MOV B,L
        MOV D,E
        MOV D,H
        NONE
        MOV D,H
        MOV C,B
        MOV B,L
        NONE
        MOV D,D
        MOV B,L
        MOV B,M
        MOV D,D
        MOV B,L
        MOV D,E
        MOV C,B
        NONE
        MOV D,D
        MOV B,L
        MOV B,A
        MOV C,C
        MOV D,E
        MOV D,H
        MOV B,L
        MOV D,D
        DCR C
        LDAX B
        NOP
        CALL loyw
        JMP lqzx
        DB 0AFh, 0CDh, 014h, 038h, 032h, 069h, 03Bh, 0CDh
leie:   DB 045h
lsby:   DB 03Ah, 00Dh, 00Ah, 043h, 050h, 055h, 020h, 049h, 053h, 020h
        DB 000h, 03Ah, 069h, 03Bh, 0B7h, 0CAh, 077h, 031h, 0CDh
lgjf:   DB 045h, 03Ah, 05Ah, 038h, 030h, 000h, 03Ah, 06Ah, 03Bh, 0B7h
        DB 0C2h, 084h, 031h, 0CDh, 045h, 03Ah, 020h, 028h, 052h, 045h
        DB 046h, 052h, 045h, 053h, 048h, 020h, 052h, 045h, 047h, 049h
        DB 053h, 054h, 045h, 052h, 020h, 04Eh, 04Fh, 054h, 020h, 043h
        DB 048h, 045h, 043h, 04Bh, 045h, 044h, 029h, 000h, 0C3h, 084h
        DB 031h, 0CDh, 045h, 03Ah, 038h, 030h, 038h, 030h, 02Fh, 038h
        DB 030h, 038h, 035h, 000h, 0AFh, 001h, 001h, 000h, 0CDh, 045h
        DB 03Ah, 00Dh, 00Ah, 042h, 045h, 047h, 049h, 04Eh, 020h, 054h
        DB 049h, 04Dh, 049h, 04Eh, 047h, 020h, 054h, 045h, 053h, 054h
        DB 00Dh, 00Ah, 000h, 0CDh, 045h, 03Bh, 0CDh, 0F5h, 037h, 0C3h
        DB 0BCh, 031h, 0CDh, 014h, 038h, 0C3h, 0BCh, 031h, 021h, 000h
        DB 000h, 03Ch, 0C2h, 0B3h, 031h, 009h, 0D2h, 0B3h, 031h, 0EBh
        DB 0CDh, 045h, 03Bh, 0CDh, 045h, 03Ah, 045h, 04Eh, 044h, 020h
        DB 054h, 049h, 04Dh, 049h, 04Eh, 047h, 020h, 054h, 045h, 053h
        DB 054h, 00Dh, 00Ah, 000h, 0CDh, 0E7h, 032h, 0CDh, 014h, 038h
        DB 0C3h, 0E3h, 031h, 0CDh, 006h, 038h, 0C3h, 04Eh, 032h, 0CDh
        DB 045h, 03Ah, 054h, 041h, 042h, 04Ch, 045h, 020h, 045h, 04Eh
        DB 044h, 020h, 000h, 02Ah, 075h, 03Bh, 0CDh, 082h, 03Ah, 0CDh
        DB 045h, 03Ah, 00Dh, 00Ah, 054h, 041h, 042h, 04Ch, 045h, 020h
        DB 053h, 049h, 05Ah, 045h, 020h, 000h, 011h, 08Dh, 0C4h, 019h
        DB 0CDh, 082h, 03Ah, 0CDh, 05Ch, 03Bh, 0C3h, 021h, 032h, 02Ah
        DB 075h, 03Bh, 02Bh, 036h, 001h, 02Bh, 036h, 001h, 02Bh, 036h
        DB 001h, 0CDh, 05Eh, 039h, 03Ah, 06Bh, 03Bh, 021h, 06Ch, 03Bh
        DB 086h, 057h, 03Eh, 001h, 0CDh
lqzx:   PUSH PSW
        STC
        STA lsay
        STA lubz
        LDA lubz
lqax:   LXI H,03B6Ch
        ADD M
        SUB D
        MOV D,A
        LDA lwca
        SUB D
        STA lwca
        CALL lydb
        JMP labc
        DB 0C7h, 02Ah, 071h, 03Bh, 07Ch, 0B5h, 0CAh, 069h, 032h, 0CDh
        DB 082h, 03Ah, 0CDh, 045h, 03Ah, 020h, 045h, 052h, 052h, 04Fh
        DB 052h, 053h, 00Dh, 00Ah, 000h, 0C3h, 07Bh, 032h, 0CDh, 045h
        DB 03Ah, 043h, 050h, 055h, 020h, 054h, 045h, 053h, 054h, 053h
        DB 020h, 04Fh, 04Bh, 00Dh, 00Ah, 000h, 0CDh, 022h, 03Bh, 0C3h
        DB 084h, 030h, 0F5h, 0CDh, 014h, 038h, 0C3h, 08Eh, 032h, 0CDh
        DB 006h, 038h, 0C3h, 0AEh, 032h, 0F1h, 0F5h, 0D5h, 057h, 03Ah
        DB 070h, 03Bh, 092h, 032h, 070h, 03Bh, 0D1h, 0F1h, 0F5h, 0CDh
        DB 045h, 03Ah, 03Ch, 000h, 0CDh, 098h, 03Ah, 0CDh, 045h, 03Ah
        DB 03Eh, 000h, 0CDh, 022h, 03Bh, 0F1h, 0C9h, 0CDh, 05Ch, 03Bh
        DB 0CDh, 045h, 03Ah, 043h, 048h, 045h, 043h, 04Bh, 053h, 055h
        DB 04Dh, 020h, 045h, 052h, 052h, 04Fh, 052h, 02Dh, 02Dh, 04Dh
        DB 045h, 04Dh, 04Fh, 052h, 059h, 020h, 04Dh, 041h, 059h, 020h
        DB 042h, 045h, 020h, 042h, 041h, 044h, 03Ah, 020h, 000h, 0F1h
        DB 0CDh, 098h, 03Ah, 0C7h, 0CDh, 0E1h, 032h, 0C9h, 00Eh, 002h
        DB 0CDh, 0BDh, 020h, 0C9h, 021h, 005h, 03Ah, 0CDh, 0B7h, 038h
        DB 0CDh, 01Ah, 035h, 021h, 077h, 03Bh, 022h, 075h, 03Bh, 03Eh
        DB 000h, 032h, 03Eh, 033h, 032h, 03Fh, 033h, 021h, 058h, 033h
        DB 016h, 001h, 07Eh, 023h, 0FEh, 000h, 0C2h, 01Bh, 033h, 0CDh
        DB 00Dh, 038h, 0CDh, 0F5h, 037h, 0CDh, 05Ch, 03Bh, 014h, 07Ah
        DB 0FEh, 004h, 0FAh, 003h, 033h, 0C9h, 032h, 03Dh, 033h, 07Ah
        DB 0FEh, 002h, 0FAh, 034h, 033h, 07Eh, 023h, 032h, 03Eh, 033h
        DB 07Ah, 0FEh, 003h, 0FAh, 034h, 033h, 07Eh, 023h, 032h
levr:   DB 03Fh
lqok:   DB 033h, 0D5h
luqm:   DB 0E5h, 0F5h, 021h, 0A1h, 039h, 0CDh, 0B7h, 038h, 0E4h, 056h
        DB 033h, 0CDh
lspl:   DB 022h, 035h, 021h
lixt:   DB 0D3h, 039h, 0CDh, 0B7h, 038h, 0CDh, 01Ah, 035h, 0F1h, 0E1h
        DB 0D1h, 0C3h, 003h, 033h, 00Bh, 0C3h, 040h, 033h, 003h, 0C9h
        DB 003h, 004h, 005h, 007h, 009h, 00Bh, 00Ch, 00Dh, 00Fh, 013h
        DB 014h, 015h, 017h, 019h, 01Bh, 01Ch, 01Dh, 01Fh, 023h, 024h
        DB 025h, 027h, 029h, 02Bh, 02Ch, 02Dh, 02Fh, 037h, 039h, 03Ch
        DB 03Dh, 03Fh, 040h, 041h, 042h, 043h, 044h, 045h, 047h, 048h
        DB 049h, 04Ah, 04Bh, 04Ch, 04Dh, 04Fh, 050h, 051h, 052h, 053h
        DB 054h, 055h, 057h, 058h, 059h, 05Ah, 05Bh, 05Ch, 05Dh, 05Fh
        DB 060h, 061h, 062h, 063h, 064h, 065h, 067h, 068h, 069h, 06Ah
        DB 06Bh, 06Ch, 06Dh, 06Fh, 078h, 078h, 079h, 07Ah, 07Bh, 07Ch
        DB 07Dh, 07Fh, 080h, 081h, 082h, 083h, 084h, 085h, 087h, 088h
        DB 089h, 08Ah, 08Bh, 08Ch, 08Dh, 090h, 091h, 092h, 093h, 094h
        DB 095h, 097h, 0A0h, 0A1h, 0A2h, 0A3h, 0A4h, 0A5h, 0A7h, 0A8h
        DB 0A9h, 0AAh, 0ABh, 0ACh, 0ADh, 0AFh, 0B0h, 0B1h, 0B2h, 0B3h
        DB 0B4h, 0B5h, 0B7h, 0B8h, 0B9h, 0BAh, 0BBh, 0BCh, 0BDh, 0BFh
        DB 0EBh, 0F3h, 000h, 006h, 002h, 00Eh, 003h, 016h, 004h, 01Eh
        DB 005h, 026h, 006h, 02Eh, 007h, 03Eh, 009h, 0C6h, 00Ah, 0CEh
        DB 00Bh, 0D6h, 00Ch, 0DEh, 00Dh, 0E6h, 00Eh, 0EEh, 00Fh, 0F6h
        DB 010h, 0FEh, 011h, 026h, 063h, 0E5h, 0C1h, 006h, 058h, 0C5h
        DB 0D1h, 0F5h, 0E1h, 033h, 03Bh, 000h, 001h, 06Dh, 03Bh, 011h
        DB 013h, 000h, 021h, 014h, 000h, 022h, 06Dh, 03Bh, 02Ah, 014h
        DB 034h, 02Ah, 06Dh, 03Bh, 032h, 06Dh, 03Bh, 03Ah, 01Dh, 034h
        DB 03Ah, 06Dh, 03Bh, 011h, 06Dh, 03Bh, 03Eh, 04Dh, 012h, 03Eh
        DB 042h, 002h, 03Ch, 00Ah, 040h, 003h, 00Ah, 040h
lcuq:   DB 0D5h, 0E3h, 0C1h, 021h, 06Dh, 03Bh, 077h, 046h, 004h, 070h
        DB 04Eh, 00Ch, 071h, 056h, 014h, 073h, 07Eh
lyso:   DB 03Ch, 077h, 054h, 066h, 062h, 055h, 06Eh, 06Ah, 086h, 070h
        DB 086h, 070h, 040h, 08Eh, 070h, 040h, 096h, 070h, 040h, 0A6h
        DB 070h, 040h, 0AEh, 070h, 040h, 0B6h, 070h, 040h, 0BEh, 070h
        DB 040h, 036h, 058h, 040h, 021h, 052h, 033h, 0E9h, 03Ch, 004h
        DB 011h, 0FFh, 0FFh, 0D5h, 0F1h, 07Fh, 0E5h, 0C9h, 01Bh, 0E5h
        DB 0C8h, 01Bh, 0E5h, 0D8h, 01Bh, 0E5h, 0F8h, 01Bh, 0E5h, 0E8h
        DB 01Bh, 0C0h, 01Bh, 01Bh, 0D0h, 01Bh, 01Bh, 0F0h, 01Bh, 01Bh
        DB 0E0h, 01Bh, 01Bh, 0C3h, 052h, 033h, 0CAh, 052h, 033h, 0DAh
        DB 052h, 033h, 0FAh, 052h, 033h, 0EAh, 052h, 033h, 0C2h, 052h
        DB 033h, 0D2h, 052h, 033h, 0F2h, 052h, 033h, 0E2h, 052h, 033h
        DB 0CDh, 056h, 033h, 0CCh, 056h, 033h, 0DCh, 056h, 033h, 0FCh
        DB 056h, 033h, 0ECh, 056h, 033h, 0C4h, 056h, 033h, 0D4h, 056h
        DB 033h, 0F4h, 056h, 033h, 0E4h, 056h, 033h, 011h, 000h, 000h
        DB 0D5h, 0F1h, 07Fh, 0E5h, 0C9h, 01Bh, 0C8h, 01Bh, 01Bh, 0D8h
        DB 01Bh, 01Bh, 0F8h, 01Bh, 01Bh, 0E8h, 01Bh, 01Bh, 0E5h, 0C0h
        DB 01Bh, 0E5h, 0D0h, 01Bh, 0E5h, 0F0h, 01Bh, 0E5h, 0E0h, 01Bh
        DB 0C3h, 052h, 033h, 0CAh, 052h, 033h, 0DAh, 052h, 033h, 0FAh
        DB 052h, 033h, 0EAh, 052h, 033h, 0C2h, 052h, 033h, 0D2h, 052h
        DB 033h, 0F2h, 052h, 033h, 0E2h, 052h, 033h, 0CDh, 056h, 033h
        DB 0CCh, 056h, 033h, 0DCh, 056h, 033h, 0FCh, 056h, 033h, 0ECh
        DB 056h, 033h, 0C4h, 056h, 033h, 0D4h, 056h, 033h, 0F4h, 056h
        DB 033h, 0E4h, 056h, 033h, 000h, 0E5h, 021h, 0A1h, 039h, 0CDh
        DB 031h, 038h, 0C9h, 0E5h, 021h, 0D3h, 039h, 0CDh, 031h, 038h
        DB 0E5h, 0D5h, 0F5h, 021h, 0A1h, 039h, 011h
lagc:   DB 0D3h, 039h, 0CDh, 00Dh, 038h
lchd:   DB 0CDh, 0F5h, 037h, 0CDh, 01Ah, 036h, 0CDh, 00Dh, 038h, 0CDh
        DB 0F5h, 037h, 0CDh, 063h, 03Bh, 0CDh
likg:   DB 06Ch, 035h, 0F1h, 0D1h, 0E1h, 0C9h, 0F6h, 0F7h, 001h, 059h
        DB 057h, 03Fh, 062h, 063h, 064h, 065h, 061h, 066h, 068h, 06Ch
        DB 042h, 043h, 044h, 045h, 041h, 046h, 048h, 04Ch, 049h, 058h
        DB 04Ah, 059h, 073h, 070h, 056h, 052h, 024h, 024h, 024h, 0C5h
        DB 00Eh, 0FFh, 0E5h, 02Ah, 075h, 03Bh, 022h, 04Fh, 035h, 0E1h
        DB 0AFh, 032h, 073h, 03Bh, 00Ch, 079h, 0FEh, 019h, 0F2h, 0CBh
        DB 035h, 02Bh, 07Eh, 032h, 04Bh, 035h, 0EBh, 02Bh, 046h, 0F5h
        DB 078h, 032h, 04Ch, 035h, 0F1h, 0EBh, 0B8h, 0CAh, 06Fh, 035h
        DB 03Ah, 069h, 03Bh, 0B7h, 0C2h, 0A2h, 035h, 079h, 0FEh, 008h
        DB 0F2h, 0E0h, 035h, 079h, 0FEh, 017h, 0F2h, 0E0h, 035h, 079h
        DB 032h, 04Dh, 035h, 0CDh, 094h, 036h, 0CDh, 00Dh, 038h, 0CDh
        DB 0F5h, 037h, 0CDh, 04Ah, 036h, 03Ah, 04Ch, 035h, 0CDh, 094h
        DB 036h, 03Ah, 073h, 03Bh, 0B7h, 0CDh, 006h, 038h, 0C4h, 0BDh
        DB 036h, 0C3h, 06Fh, 035h, 03Eh, 058h, 0CDh, 094h, 036h, 03Eh
        DB 059h, 0CDh, 094h, 036h, 0C1h, 0CDh, 00Dh, 038h, 0CDh, 0F5h
        DB 037h, 0CDh, 05Ch, 03Bh, 0C9h, 0CDh, 0F5h, 037h, 0C3h, 0CBh
        DB 035h, 0E5h, 02Ah, 04Fh, 035h, 022h, 075h, 03Bh, 0E1h, 0CDh
        DB 00Fh, 036h, 0FEh, 058h, 0CAh, 0FCh, 035h, 0CDh, 00Fh, 036h
        DB 0C3h, 0E0h, 035h, 0CDh, 00Fh, 036h, 0FEh, 059h, 0C2h, 0E0h
        DB 035h, 02Ah, 075h, 03Bh, 02Bh, 02Bh, 022h, 075h, 03Bh, 0C3h
        DB 0CBh, 035h, 0E5h, 02Ah, 075h, 03Bh, 07Eh, 023h, 022h, 075h
        DB 03Bh, 0E1h, 0C9h, 03Ah, 03Dh, 033h, 0CDh, 098h, 03Ah, 03Ah
        DB 03Eh, 033h, 0CDh, 098h, 03Ah, 03Ah, 03Fh, 033h, 0CDh, 098h
        DB 03Ah, 0C9h, 0E5h, 021h, 051h, 035h, 0CDh
lonj:   DB 07Ah, 03Ah, 0FEh, 041h, 0FAh, 045h, 036h, 0FEh, 05Bh, 0F2h
        DB 045h, 036h, 0F5h, 03Eh, 05Ah, 0CDh, 005h, 03Ah, 0F1h, 0CDh
        DB 005h, 03Ah, 0E1h, 0C9h, 0E5h, 0D5h, 03Ah, 04Dh, 035h, 0CDh
        DB 098h, 03Ah, 0CDh, 045h, 03Ah, 028h, 000h, 0CDh, 02Dh, 036h
        DB 0CDh, 045h, 03Ah, 029h, 03Ah, 000h, 03Ah, 04Bh, 035h, 0CDh
        DB 098h, 03Ah, 0CDh, 045h, 03Ah, 02Dh, 03Eh, 000h, 03Ah, 04Ch
        DB 035h, 0CDh, 098h, 03Ah, 0CDh, 045h, 03Ah, 028h, 000h, 02Ah
        DB 075h, 03Bh, 023h, 07Eh, 0CDh, 098h, 03Ah, 0CDh, 045h, 03Ah
        DB 02Eh, 000h, 03Ah, 04Eh, 035h, 0CDh, 098h, 03Ah, 0CDh, 045h
        DB 03Ah, 029h, 02Ch, 020h, 000h, 0D1h, 0E1h, 0C9h, 0F5h, 0E5h
        DB 02Ah, 075h, 03Bh, 0F5h, 07Eh, 032h, 04Eh, 035h, 0F1h, 0BEh
        DB 0CAh, 0AEh, 036h, 0F5h, 03Ah, 06Bh, 03Bh, 032h, 073h, 03Bh
        DB 0F1h, 0C3h, 0AEh, 036h, 0CDh, 0F5h, 037h, 0CDh, 0BBh, 036h
        DB 023h, 022h, 075h, 03Bh, 0E1h, 0F1h, 0C9h, 077h, 0C9h, 0CDh
        DB 045h, 03Ah, 00Dh, 00Ah, 043h, 050h, 055h, 020h, 046h, 041h
        DB 049h, 04Ch, 045h, 044h, 03Ah, 020h, 000h, 0F5h, 03Eh, 001h
        DB 032h, 073h, 03Bh, 0F1h, 02Ah, 071h, 03Bh, 023h, 022h, 071h
        DB 03Bh, 0CDh, 045h, 03Ah, 00Dh, 00Ah, 045h, 052h, 052h, 04Fh
        DB 052h, 020h, 043h, 04Fh, 055h, 04Eh, 054h, 020h, 000h, 0CDh
        DB 082h, 03Ah, 0CDh, 045h, 03Ah, 048h, 00Dh, 00Ah, 000h, 02Ah
        DB 075h, 03Bh, 0F5h, 0CDh, 014h, 038h, 0CDh, 04Ah, 036h, 0CDh
        DB 045h, 03Ah, 00Dh, 00Ah, 049h, 04Eh, 053h, 054h, 052h, 055h
        DB 043h, 054h, 049h, 04Fh, 04Eh, 020h, 053h, 045h, 051h, 055h
        DB 045h, 04Eh, 043h, 045h, 020h, 057h, 041h, 053h, 020h, 000h
        DB 0CDh, 01Ah, 036h, 0CDh, 045h, 03Ah, 048h, 00Dh, 00Ah, 052h
        DB 045h, 047h, 049h, 053h, 054h
lucz:   DB 045h, 052h, 020h
lwda:   DB 000h, 03Ah, 04Dh, 035h, 0CDh, 02Dh, 036h, 0F1h, 0F5h, 0CDh
        DB 045h, 03Ah, 020h, 043h, 04Fh, 04Eh, 054h, 041h, 049h, 04Eh
        DB 053h, 020h, 000h, 0D5h, 03Ah, 04Ch, 035h, 0CDh, 08Dh, 03Ah
        DB 0CDh, 045h, 03Ah, 00Dh, 00Ah, 042h, 055h, 054h, 020h, 053h
        DB 048h, 04Fh, 055h, 04Ch, 044h, 020h, 043h, 04Fh, 04Eh, 054h
        DB 041h, 049h, 04Eh, 020h, 000h, 02Bh, 07Eh, 023h, 0CDh, 08Dh
        DB 03Ah, 0CDh, 045h, 03Ah, 00Dh, 00Ah, 052h, 045h, 047h, 049h
        DB 053h, 054h, 045h, 052h, 020h, 056h, 041h, 04Ch, 055h, 045h
        DB 020h, 042h, 045h, 046h, 04Fh, 052h, 045h, 020h, 049h, 04Eh
        DB 053h, 054h, 052h, 055h, 043h, 054h, 049h, 04Fh, 04Eh, 020h
        DB 053h, 045h, 051h, 055h, 045h, 04Eh, 043h, 045h, 020h, 057h
        DB 041h, 053h, 020h, 000h, 03Ah, 04Bh, 035h, 0CDh, 08Dh, 03Ah
        DB 0D1h, 0CDh, 045h, 03Ah, 00Dh, 00Ah, 054h, 045h, 053h, 054h
        DB 020h, 04Eh, 055h, 04Dh, 042h, 045h, 052h, 020h, 000h, 0D5h
        DB 011h, 08Ch, 0C4h, 019h, 0D1h, 0CDh, 063h, 03Bh, 0CDh, 082h
        DB 03Ah, 0CDh, 045h, 03Ah, 048h, 00Dh, 00Ah, 00Ah, 000h, 0CDh
        DB 022h, 03Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0F5h, 037h, 0C3h, 004h
        DB 038h, 0F1h, 0E3h, 0F5h, 023h, 023h, 023h, 0AFh, 0BEh, 023h
        DB 0C2h, 0E6h, 037h, 0F1h, 0E3h, 0C9h, 0CDh, 0F5h, 037h, 0CDh
        DB 098h, 03Ah, 0C9h, 0F5h, 03Ah, 06Bh, 03Bh, 0B7h, 0CAh, 004h
        DB 038h, 0F1h, 0E3h, 023h, 023h, 023h, 0E3h, 0C9h, 0F1h, 0C9h
        DB 0CDh, 0F5h, 037h, 0C3h, 0FEh, 037h, 0C9h, 0CDh, 014h, 038h
        DB 0C3h, 0FEh, 037h, 0C9h, 0F5h, 03Ah, 06Fh, 03Bh, 0FEh, 001h
        DB 0CAh, 004h, 038h, 0F1h, 0C3h, 0FEh, 037h, 0CDh, 0A9h, 03Ah
        DB 0CDh, 022h, 03Bh, 0C9h, 0F5h, 0AFh, 03Ch, 0B7h, 0C2h, 02Ah
        DB 038h, 0F1h, 0C9h, 022h, 0B3h, 038h
lyeb:   DB 0E1h, 0E3h, 0E5h, 0F5h, 02Ah, 0B3h, 038h, 02Bh, 070h, 02Bh
        DB 071h, 02Bh, 072h, 02Bh, 073h, 0D1h, 02Bh, 072h, 02Bh, 073h
        DB 0EBh, 0E3h, 0EBh, 02Bh, 072h, 02Bh, 073h, 0EBh, 0E3h, 0EBh
        DB 0E3h, 0CDh, 033h, 039h, 008h, 0D9h, 0E3h, 0F5h, 02Bh, 070h
        DB 02Bh, 071h, 02Bh, 072h, 02Bh, 073h, 0D1h, 02Bh, 072h, 02Bh
        DB 073h, 0EBh, 0E3h, 0EBh, 02Bh, 072h, 02Bh, 073h, 0E5h, 0CDh
        DB 033h, 039h, 008h, 0D9h, 0E1h, 0CDh, 033h, 039h, 0DDh, 0E5h
        DB 0CDh, 028h, 039h, 0E5h, 000h, 0D1h, 02Bh, 072h, 02Bh, 073h
        DB 0CDh, 033h, 039h, 0FDh, 0E5h, 0CDh, 028h, 039h, 0E5h, 000h
        DB 0D1h, 02Bh, 072h, 02Bh, 073h, 0EBh, 021h, 0FEh, 0FFh, 039h
        DB 0EBh, 02Bh, 072h, 02Bh, 073h, 0CDh, 033h, 039h, 0EDh, 057h
        DB 02Bh, 077h, 0CDh, 033h, 039h, 0EDh, 05Fh, 02Bh, 077h, 0E1h
        DB 02Ah, 0B3h, 038h, 0CDh, 0B7h, 038h, 0C9h, 0A1h, 039h, 000h
        DB 000h, 02Bh, 046h, 02Bh, 04Eh, 02Bh, 056h, 02Bh, 05Eh, 0D5h
        DB 02Bh, 056h, 02Bh, 05Eh, 0D5h, 0F1h, 02Bh, 056h, 02Bh, 05Eh
        DB 0E3h, 0EBh, 0CDh, 028h, 039h, 033h, 033h, 0CDh, 028h, 039h
        DB 0C9h, 000h, 0CDh, 033h, 039h, 008h, 0D9h, 0E1h, 02Bh, 046h
        DB 02Bh, 04Eh, 02Bh, 056h, 02Bh, 05Eh, 0D5h, 02Bh, 056h, 02Bh
        DB 05Eh, 0D5h, 0F1h, 02Bh, 056h, 02Bh, 05Eh, 0D5h, 02Bh, 056h
        DB 02Bh, 05Eh, 0D5h
loyw:   CALL lafc
        NONE
        POP H
        CALL lcgd
        POP D
        NOP
        DCX H
        MOV D,M
        DCX H
        MOV E,M
        PUSH D
lmli:   CALL lafc
        NONE
        POP H
        CALL lcgd
        POP D
        NOP
        DCX H
        DCX H
        PUSH PSW
        DCX H
        MOV A,M
        CALL lafc
        NONE
        MOV B,A
        DCX H
        MOV A,M
        CALL lafc
        NONE
        MOV C,A
        POP PSW
        POP H
        POP D
        CALL lafc
        NONE
        NONE
        RET
        DB 0F5h, 03Ah, 069h, 03Bh, 0B7h, 0CAh, 004h, 038h, 0C3h, 03Bh
        DB 039h, 0F5h, 03Ah, 069h, 03Bh, 0B7h, 0C2h
lgef:   DB 004h, 038h, 0F1h, 0E3h, 023h, 023h, 0E3h, 0C9h, 011h, 000h
        DB 030h, 02Ah, 075h, 03Bh, 0CDh, 04Bh, 039h, 0C9h, 0C5h, 07Ch
        DB 02Fh, 047h, 07Dh, 02Fh, 04Fh, 003h, 0AFh, 062h, 06Bh, 086h
        DB 009h, 013h, 0D2h, 054h, 039h, 0C1h, 0C9h, 0CDh, 041h, 039h
        DB 0B7h, 0C4h, 081h, 032h, 01Eh, 044h, 0CDh, 0E1h, 032h, 0C9h
        DB 000h, 000h, 000h, 000h, 06Fh, 039h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0EFh, 02Fh, 08Fh, 039h, 091h, 039h, 052h, 033h
        DB 002h, 000h, 002h, 000h, 0F7h, 000h, 052h, 033h, 002h, 000h
        DB 0F8h, 0FFh, 0F7h, 000h, 0A1h, 039h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 0EFh, 02Fh, 0C1h, 039h, 0C3h, 039h, 052h, 033h
        DB 002h, 000h, 002h, 000h, 0F7h, 000h, 052h, 033h, 002h, 000h
        DB 0F8h, 0FFh, 0F7h, 000h, 0D3h, 039h, 001h, 000h, 002h, 000h
        DB 003h, 000h, 004h, 000h, 005h, 000h, 006h, 000h, 007h, 000h
        DB 008h, 000h, 009h, 000h, 00Ah, 000h, 00Bh, 000h, 00Ch, 000h
        DB 00Dh, 000h, 00Eh, 000h, 00Fh, 000h, 010h, 000h, 011h, 000h
        DB 012h, 000h, 013h, 000h, 014h, 000h, 015h, 000h, 016h, 000h
        DB 017h, 000h, 018h, 000h, 0C5h, 0D5h, 0E5h, 0F5h, 05Fh, 0CDh
        DB 0E1h, 032h, 0F1h, 0E1h, 0D1h, 0C1h, 0C9h, 0C5h, 0D5h, 0E5h
        DB 00Eh, 001h, 0CDh, 005h, 000h, 0E1h, 0D1h, 0C1h, 0C9h, 0E5h
        DB 0C5h, 0D5h, 0F5h, 079h, 0FEh, 002h, 0C2h, 034h, 03Ah
lcgd:   MOV A,E
        CPI 00Dh
        JZ lehe
        CALL lumm
        JMP lehe
lafc:   LDA lgif
latp:   JNZ lehe
        CALL lwnn
        ORA A
        JZ lijg
        POP PSW
        POP D
        POP B
        POP H
        RET
        DB 0E3h, 0F5h, 07Eh, 023h, 0B7h, 0C2h, 050h, 03Ah, 0F1h, 0E3h
        DB 0C9h, 0CDh, 005h, 03Ah, 0C3h, 047h, 03Ah, 0F5h, 0E5h, 0D5h
        DB 0E6h, 00Fh, 021h, 06Ah, 03Ah, 016h, 000h, 05Fh, 019h, 07Eh
        DB 0CDh, 005h, 03Ah, 0D1h, 0E1h, 0F1h, 0C9h, 030h, 031h, 032h
        DB 033h, 034h, 035h, 036h, 037h, 038h, 039h, 041h, 042h, 043h
        DB 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h
        DB 0C9h, 0F5h, 07Ch, 0CDh, 098h, 03Ah, 07Dh, 0CDh, 098h, 03Ah
        DB 0F1h, 0C9h, 0F5h, 0CDh, 098h, 03Ah, 03Eh, 048h, 0CDh, 005h
        DB 03Ah, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 056h
        DB 03Ah, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 056h, 03Ah, 0F1h, 0C9h
        DB 0CDh, 0BAh, 03Ah, 0CDh, 033h, 039h, 008h, 0D9h, 0CDh, 0BAh
        DB 03Ah, 0CDh, 033h, 039h, 008h, 0D9h, 0C9h, 022h, 021h, 03Bh
        DB 0CDh, 045h, 03Ah, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h
        DB 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h
        DB 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h
        DB 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h
        DB 0CDh, 082h, 03Ah, 0CDh, 063h, 03Bh, 0F1h, 0F5h, 0CDh, 098h
        DB 03Ah, 0E1h, 0E5h, 07Dh, 0CDh, 098h, 03Ah, 0CDh, 063h, 03Bh
        DB 060h, 069h, 0CDh, 082h, 03Ah, 0CDh, 063h, 03Bh, 062h, 06Bh
        DB 0CDh, 082h, 03Ah, 0CDh, 063h, 03Bh, 02Ah, 021h, 03Bh, 0CDh
        DB 082h, 03Ah, 0CDh, 063h, 03Bh, 021h, 000h, 000h, 039h, 0CDh
        DB 082h, 03Ah, 0F1h, 02Ah, 021h, 03Bh, 0CDh, 05Ch, 03Bh, 0C9h
        DB 002h, 0CDh, 0F6h, 020h, 0C7h, 020h, 0C7h, 0CAh, 004h, 038h
        DB 0FEh, 043h, 0CAh, 004h, 038h, 0CDh, 012h, 03Ah, 032h, 043h
        DB 03Bh, 0F5h, 02Fh, 03Ch
lijg:   STA lkkh
        POP PSW
        CPI 003h
        JNZ lmli
lehe:   INR B
        NONE
        RST 0
        INX B
        NONE
lqnk:   PUSH PSW
        LDA lomj
        CPI 053h
        JZ lmli
        CALL lqnk
        RLC
        NOP
        POP PSW
        RET
        DB 0CDh, 0A9h, 03Ah, 0CDh, 022h, 03Bh, 0C9h, 0CDh, 045h, 03Ah
        DB 00Dh, 00Ah, 000h, 0C9h, 0CDh, 045h, 03Ah, 020h, 000h, 0C9h
        DB 000h, 000h, 001h, 001h, 058h, 000h, 000h, 098h, 000h, 000h
        DB 000h, 000h, 059h, 03Fh, 001h, 019h, 058h, 059h, 000h, 001h
        DB 005h, 002h, 058h, 059h, 000h, 000h, 005h, 056h, 058h, 059h
        DB 058h, 059h, 007h, 02Eh, 058h, 059h, 001h, 018h, 058h, 059h
        DB 001h, 019h, 005h, 002h, 058h, 059h, 001h, 018h, 005h, 016h
        DB 058h, 059h, 058h, 059h, 003h, 018h, 058h, 059h, 002h, 001h
        DB 005h, 002h, 058h, 059h, 002h, 000h, 005h, 056h, 058h, 059h
        DB 058h, 059h, 007h, 046h, 058h, 059h, 003h, 017h, 058h, 059h
        DB 003h, 018h, 005h, 006h, 058h, 059h, 003h, 017h, 005h, 016h
        DB 058h, 059h, 058h, 059h, 007h, 047h, 058h, 059h, 005h, 002h
        DB 006h, 001h, 058h, 059h, 005h, 056h, 006h, 000h, 058h, 059h
        DB 004h, 006h, 005h, 006h, 058h, 059h, 007h, 08Eh, 058h, 059h
        DB 007h, 08Dh, 058h, 059h, 005h, 086h, 007h, 08Eh, 058h, 059h
        DB 005h, 096h, 007h, 08Dh, 058h, 059h, 004h, 0F9h, 058h, 059h
        DB 005h, 097h, 058h, 059h, 005h, 096h, 006h, 030h, 007h, 084h
        DB 058h, 059h, 004h, 0FAh, 005h, 086h, 058h, 059h, 004h, 0F9h
        DB 005h, 096h, 058h, 059h, 005h, 097h, 058h, 059h, 058h, 059h
        DB 000h, 018h, 058h, 059h, 000h, 000h, 058h, 059h, 000h, 017h
        DB 058h, 059h, 000h, 030h, 058h, 059h, 000h, 084h, 058h, 059h
        DB 000h, 0F9h, 058h, 059h, 001h, 0F9h, 058h, 059h, 058h, 059h
        DB 001h, 000h, 058h, 059h, 001h, 017h, 058h, 059h, 001h, 030h
        DB 058h, 059h, 001h, 084h, 058h, 059h, 001h, 0F9h
lomj:   DB 058h
lkkh:   DB 059h, 002h, 0F9h, 058h, 059h, 058h, 059h, 058h, 059h, 002h
        DB 017h, 058h, 059h, 002h, 030h, 058h, 059h, 002h, 084h, 058h
        DB 059h, 002h, 0F9h, 058h
lydb:   MOV E,C
        INX B
        SPHL
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INX B
        NONE
        MOV E,B
        MOV E,C
lubz:   INX B
lsay:   ADD H
        MOV E,B
        MOV E,C
        INX B
lwca:   SPHL
        MOV E,B
        MOV E,C
        MVI B,0F9h
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,084h
        MOV E,B
        MOV E,C
        MVI B,0F9h
        MOV E,B
        MOV E,C
        RLC
        SPHL
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        JP lsol
        MOV E,B
        MOV E,C
        INR B
        XCHG
        DCR B
        ADD A
        MOV E,B
        MOV E,C
        INR B
        CPO lupm
        MOV E,B
        MOV E,C
        INR B
        NONE
        DCR B
        ADD A
        MOV E,B
        MOV E,C
        INR B
        SUI 005h
        SUB E
        MOV E,B
        MOV E,C
        INR B
        RST 1
        DCR B
        ADD A
        MOV E,B
        MOV E,C
        INR B
        SBB M
        DCR B
        SUB E
        MOV E,B
        MOV E,C
        INR B
        SBB B
        MOV E,B
        MOV E,C
        INR B
        SUB D
        MOV E,B
        MOV E,C
        INR B
        ADC H
        DCR B
        ADD E
        MOV E,B
        MOV E,C
        INR B
        ADD M
        DCR B
        SUB E
        MOV E,B
        MOV E,C
        INR B
        ADD B
        MOV E,B
        MOV E,C
        INR B
        MOV A,D
        DCR B
        INX B
        MOV E,B
        MOV E,C
        INR B
        ADD C
        DCR B
        SUB A
        MOV E,B
        MOV E,C
        INR B
        ADC B
        DCR B
        ADD A
        MOV E,B
        MOV E,C
        INR B
        ADC A
        DCR B
        ADD E
        MOV E,B
        MOV E,C
        INR B
        SUB M
        DCR B
        SUB A
        MOV E,B
        MOV E,C
        INR B
        SBB L
        DCR B
        ADD E
        MOV E,B
        MOV E,C
        INR B
        ANA H
        DCR B
        SUB E
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV D,M
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        INR B
        SPHL
        DCR B
        ADD M
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        INR B
        SPHL
        DCR B
        ADD M
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        INR B
        SPHL
        DCR B
        ADD M
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        SPHL
        DCR B
        ADD M
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        DCR B
        MOV D,M
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        STAX B
        MOV E,B
        MOV E,C
        LXI B,lwon
        MOV E,C
        STAX B
        INR B
        MOV E,B
        MOV E,C
        INX B
        DCR B
        MOV E,B
        MOV E,C
        MVI B,006h
        MOV E,B
        MOV E,C
        RLC
        RLC
        MOV E,B
        MOV E,C
        INR B
        DAD B
        MOV E,B
        MOV E,C
        INR B
        INX D
        DCR B
        STAX D
        MOV E,B
        MOV E,C
        INR B
        MVI E,005h
        MVI B,058h
        MOV E,C
        INR B
        STAX D
        DCR B
        MVI D,058h
        MOV E,C
        INR B
        DCR B
        DCR B
        MVI B,058h
        MOV E,C
        INR B
        INR B
        DCR B
        STAX D
        MOV E,B
        MOV E,C
        INR B
        DCX B
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        INR B
        DCX D
        DCR B
        MVI B,058h
        MOV E,C
        DCR B
        MVI D,058h
        MOV E,C
        MVI B,063h
        MOV E,B
        MOV E,C
        NOP
        MOV H,E
        LXI B,lcrq
        MOV E,C
        NOP
        MOV E,B
        MOV E,B
        MOV E,C
        STAX B
        MOV E,B
        INX B
        RLC
        MOV E,B
        MOV E,C
        MVI B,01Bh
        RLC
        MVI D,058h
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        DCX SP
        LXI B,lasp
        MOV E,C
        STAX B
        NOP
        INX B
        INX D
        MOV E,B
        MOV E,C
        MVI B,000h
        RLC
        INR D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,014h
        RLC
        LHLD ladc
        MVI B,000h
        RLC
        INR D
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        INR B
        LDA ladc
        INR B
        DCX D
        MOV E,B
        MOV E,C
        STAX B
        DCX SP
        INX B
        MOV L,L
        MOV E,B
        MOV E,C
        INR B
        MOV C,L
        MOV E,B
        MOV E,C
        INR B
        MOV B,D
        MOV E,B
        MOV E,C
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        LXI B,0046Eh
        NOP
        MOV E,B
        MOV E,C
        NOP
        NOP
        LXI B,loxw
        DCX SP
        RLC
        MOV L,L
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        NOP
        LXI B,05958h
        LXI B,lqyx
        MOV E,C
        STAX B
        INX B
        DCR B
        MVI B,058h
        MOV E,C
        INR B
        MOV L,M
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        STAX B
        DCX SP
        MVI B,06Eh
        MOV E,B
        MOV E,C
        STAX B
        MOV L,L
        MVI B,03Bh
        RLC
        MOV L,M
        MOV E,B
        MOV E,C
        INR B
        CC lkxu
        RLC
        MOV L,L
        MOV E,B
        MOV E,C
        INR B
        NONE
        DCR B
        ADD M
        MOV E,B
        MOV E,C
        INR B
        SBI 058h
        MOV E,C
        INR B
        NONE
        DCR B
        SUB M
        MOV E,B
        MOV E,C
        INR B
        LXI B,lmyv
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        MOV B,M
        MOV E,B
        MOV E,C
        INR B
        LXI B,laqp
        MOV E,B
        MOV E,C
        DCR B
        MOV D,M
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MVI B,033h
        RLC
        MOV D,D
        MOV E,B
        MOV E,C
        LXI B,lced
        MOV E,C
        STAX B
        RST 7
        INX B
        RST 7
        MOV E,B
        MOV E,C
        INR B
        RST 7
        DCR B
        RST 2
        MOV E,B
        MOV E,C
        LXI B,lkih
        MOV E,C
        NOP
        NOP
        LXI B,lihg
        MOV E,C
        LXI B,lghf
        MOV E,C
        LXI B,liig
        MOV E,C
        LXI B,lkjh
        MOV E,C
        INX B
        NONE
        MOV E,B
        MOV E,C
        INX B
        EI
        MOV E,B
        MOV E,C
        INX B
        SPHL
        MOV E,B
        MOV E,C
        INX B
        RST 6
        MOV E,B
        MOV E,C
        LXI B,lmki
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,luom
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lmki
        MOV E,C
        LXI B,lkjh
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        STAX B
        NOP
        INX B
        NOP
        MOV E,B
        MOV E,C
        INR B
        NOP
        DCR B
        STAX B
        MOV E,B
        MOV E,C
        LXI B,lmki
        MOV E,C
        STAX B
        RST 7
        INX B
        CPI 058h
        MOV E,C
        INX B
        CM ladc
        INX B
        JM ladc
        INX B
        RM
        MOV E,B
        MOV E,C
        LXI B,lolj
        MOV E,C
        LXI B,lqmk
        MOV E,C
        LXI B,lsnl
        MOV E,C
        LXI B,luom
        MOV E,C
        LXI B,lyqo
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,larp
        MOV E,C
        LXI B,lcsq
        MOV E,C
        LXI B,letr
        MOV E,C
        LXI B,lgus
        MOV E,C
        LXI B,letr
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        MOV E,B
        MOV E,C
        LXI B,lcsq
        MOV E,C
        LXI B,larp
        MOV E,C
        LXI B,lyqo
        MOV E,C
        LXI B,luom
        MOV E,C
        MOV B,H
        INX SP
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MVI M,030h
        NONE
        NONE
        NONE
        MOV B,H
        NONE
        MOV B,C
        NONE
        MOV B,C
        NONE
        NONE
        MOV B,E
        MOV B,H
        NONE
        MVI M,033h
        NONE
        MOV B,E
        INX SP
        NONE
        INR M
        INX SP
        NONE
        STA lucz
        MOV B,M
        INX SP
        MOV B,D
        MOV B,E
        MOV B,H
        INR M
        DCR M
        INX SP
        MOV B,C
        MOV B,C
        MVI M,00Dh
        LDAX B
        LDA lsby
        INX SP
        NONE
        STC
        NONE
        NONE
        NONE
        INR M
        INR M
        INR M
        STA lwda
        INR M
        MOV B,E
        INR M
        LXI SP,lyeb
        STA lagc
        DAD SP
        DCR M
        INX SP
        STA lagc
        LXI SP,lchd
        STA leie
        NONE
        MOV B,E
        MOV B,H
        NONE
        STA lgjf
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        NONE
        NONE
        NONE
        NONE
        INX SP
        MOV B,C
        MOV B,E
        MOV B,H
        DCR M
        MOV B,E
        INX SP
        MOV B,D
        LXI SP,likg
        LXI SP,lklh
        NONE
        STA lmmi
        NONE
        DCR M
        NONE
        NONE
        LXI SP,likg
        STA lmmi
        MOV B,H
        MOV B,H
        INX SP
        STA lonj
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        DAD SP
        NONE
        NONE
        NONE
        LXI SP,likg
        INX SP
        MOV B,E
        MOV B,H
        MOV B,L
        LXI SP,lqok
        INX SP
        MOV B,C
        MVI M,043h
        INX SP
        MOV B,D
        MOV B,D
        STC
        MOV B,E
        STA lspl
        INX SP
        NONE
        MOV B,E
        MOV B,H
        DCR M
        MOV B,L
        INX SP
        DAD SP
        MOV B,E
        INX SP
        DAD SP
        MOV B,E
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,C
        NONE
        NONE
        NONE
        MOV B,C
        STC
        INX SP
        NONE
        MOV B,E
        MOV B,H
        INR M
        DCR M
        INX SP
        MOV B,C
        INR M
        INR M
        NONE
        NONE
        MOV B,E
        MOV B,H
        INR M
        DCR M
        INX SP
        MOV B,C
        INR M
        DCR M
        INR M
        MVI M,034h
        STC
        INR M
        NONE
        INR M
        DAD SP
        INR M
        MOV B,C
        MOV B,E
        NONE
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,D
        NONE
        NONE
        NONE
        INR M
        MOV B,D
        INR M
        MOV B,E
        INR M
        MOV B,H
        INR M
        MOV B,L
        INR M
        MOV B,M
        DCR M
        NONE
        DCR M
        LXI SP,luqm
        DCR M
        INX SP
        DCR M
        INR M
        DCR M
        DCR M
        DCR M
        MVI M,035h
        STC
        DCR M
        NONE
        DCR M
        DAD SP
        DCR M
        MOV B,C
        MOV B,L
        NONE
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,E
        NONE
        NONE
        NONE
        NONE
        NONE
        INX SP
        MOV B,L
        NONE
        STA lwrn
        MOV B,L
        MOV B,C
        MOV B,E
        MOV B,E
        INX SP
        NONE
        INX SP
        MOV B,L
        NONE
        LXI SP,lyso
        MOV B,E
        MOV B,H
        INX SP
        NONE
        MOV B,C
        MOV B,M
        INX SP
        STA latp
        INX SP
        MOV B,D
        LXI SP,00D41h
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,H
        NONE
        NONE
        NONE
        MOV B,E
        MOV B,H
        NONE
        MVI M,033h
        NONE
        MOV B,E
        INX SP
        STA lcuq
        LXI SP,lqok
        MVI M,041h
        INX SP
        MOV B,D
        MOV B,D
        STC
        MOV B,E
        STA levr
        INX SP
        LXI SP,lmmi
        INR M
        DCR M
        INX SP
        MOV B,C
        MOV B,L
        NONE
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,L
        NONE
        NONE
        NONE
        NONE
        MOV B,H
        NONE
        MOV B,C
        DCR M
        INR M
        DCR M
        DCR M
        DCR M
        STA lgws
        INR M
        DCR M
        DCR M
        DAD SP
        STA lixt
        MOV B,H
        INR M
        STC
        INR M
        MOV B,M
        STA lagc
        MVI M,034h
        DAD SP
        INR M
        MOV B,L
        MOV B,M
        NONE
        DCR C
        LDAX B
        LDA lsby
        INX SP
        NONE
        MOV B,M
        NONE
        NONE
        NONE
        INR M
        INR M
        STA lagc
        INR C
        MOV H,D
        MOV L,E
        CALL lgss
        CALL lcqq
        LHLD lerr
        CALL lgss
        CALL lcqq
        LXI H,00000h
        DAD SP
        CALL lgss
        POP PSW
        LHLD lerr
        CALL lovw
        RET
        DB 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch
        DB 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h
        DB 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h
        DB 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h
        DB 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h
        DB 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh
        DB 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah
        DB 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h
        DB 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h
        DB 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h
        DB 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah
        DB 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh
        DB 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch
        DB 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h
        DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h
        DB 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h
        DB 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h
        DB 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h
        DB 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh
        DB 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah
        DB 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h
        DB 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h
        DB 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh
        DB 032h
lgws:   DB 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh
ligg:   DB 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h
        DB 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh
        DB 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h
        DB 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch
        DB 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h
        DB 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch
        DB 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h
        DB 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h
        DB 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h
        DB 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h
        DB 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh
        DB 00Dh, 009h, 0CDh, 01Bh, 00Bh
lwrn:   DB 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h
        DB 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh
        DB 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch
        DB 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh
        DB 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h
        DB 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h
        DB 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h
        DB 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h
        DB 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh
        DB 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h
        DB 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h
        DB 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h
        DB 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h
        DB 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh
        DB 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h
        DB 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h
        DB 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh
        DB 009h, 0C7h
lmmi:   DB 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h
        DB 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h
        DB 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h
        DB 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h
        DB 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h
        DB 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h
        DB 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h
        DB 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h
        DB 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h
        DB 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh
        DB 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah
        DB 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h
        DB 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h
        DB 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h
lklh:   DB 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h
        DB 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h
        DB 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh
        DB 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h
        DB 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h
        DB 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh
        DB 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh
        DB 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h
        DB 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah
        DB 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h
        DB 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h
        DB 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch
        DB 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h
        DB 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch
        DB 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h
        DB 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch
        DB 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h
        DB 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch
        DB 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh
        DB 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h
        DB 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh
        DB 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch
        DB 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h
        DB 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h
        DB 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh
        DB 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h
        DB 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch
        DB 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h
        DB 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h
        DB 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh
        DB 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch
        DB 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh
        DB 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h
        DB 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h
        DB 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h
        DB 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h
        DB 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh
        DB 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h
        DB 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah
        DB 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh
        DB 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh
        DB 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h
        DB 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh
        DB 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh
        DB 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh
        DB 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh
        DB 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh
        DB 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh
        DB 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh
        DB 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh
        DB 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h
        DB 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h
        DB 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h
        DB 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h
        DB 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h
        DB 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h
        DB 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh
        DB 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah
        DB 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h
        DB 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh
        DB 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h
        DB 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh
        DB 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch
        DB 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h, 000h, 0F5h
        DB 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh
        DB 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh
        DB 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh
        DB 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 00Ch, 044h
        DB 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h
        DB 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h
        DB 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh
        DB 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh
        DB 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh
        DB 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh
        DB 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h, 020h, 020h
        DB 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h, 020h, 020h
        DB 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch, 020h, 020h
        DB 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh
        DB 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h
        DB 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh
        DB 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh
        DB 00Bh, 0CDh, 06Ch, 00Ch, 021h, 000h, 000h, 039h, 0CDh, 08Bh
        DB 00Bh, 0F1h, 02Ah, 02Ah, 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h
        DB 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh
        DB 043h, 0CAh, 00Dh, 009h, 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch
        DB 0F5h, 02Fh, 03Ch, 032h, 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h
        DB 00Dh, 009h, 0C7h, 000h, 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh
        DB 053h, 0CAh, 00Dh, 009h, 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h
        DB 0C9h, 0CDh, 0B2h, 00Bh, 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh
        DB 00Bh, 00Dh, 00Ah, 000h, 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h
        DB 0C9h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 080h, 00Ch, 044h, 045h, 046h, 0D5h, 016h
        DB 000h, 05Fh, 019h, 07Eh, 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h
        DB 00Bh, 07Dh, 0CDh, 0A1h, 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h
        DB 00Bh, 03Eh, 048h, 0CDh, 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh
        DB 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh
        DB 0CDh, 05Fh, 00Bh, 0F1h, 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch
        DB 00Ah, 008h, 0D9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h
        DB 0D9h, 0C9h, 022h, 02Ah, 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah
        DB 020h, 050h, 020h, 043h, 020h, 020h, 041h, 020h, 046h, 020h
        DB 020h, 042h, 020h, 043h, 020h, 020h, 044h, 020h, 045h, 020h
        DB 020h, 048h, 020h, 04Ch, 020h, 020h, 053h, 020h, 050h, 00Dh
        DB 00Ah, 000h, 0E1h, 0E5h, 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 0F1h, 0F5h, 0CDh, 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh
        DB 0A1h, 00Bh, 0CDh, 06Ch, 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh
        DB 0CDh, 06Ch, 00Ch, 062h, 06Bh, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
        DB 00Ch, 02Ah, 02Ah, 00Ch, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch
        DB 021h, 000h, 000h, 039h, 0CDh, 08Bh, 00Bh, 0F1h, 02Ah, 02Ah
        DB 00Ch, 0CDh, 065h, 00Ch, 0C9h, 002h, 0F5h, 03Ah, 04Ch, 00Ch
        DB 0FEh, 053h, 0CAh, 00Dh, 009h, 0FEh, 043h, 0CAh, 00Dh, 009h
        DB 0CDh, 01Bh, 00Bh, 032h, 04Ch, 00Ch, 0F5h, 02Fh, 03Ch, 032h
        DB 04Dh, 00Ch, 0F1h, 0FEh, 003h, 0C2h, 00Dh, 009h, 0C7h, 000h
        DB 000h, 0F5h, 03Ah, 04Ch, 00Ch, 0FEh, 053h, 0CAh, 00Dh, 009h
        DB 0CDh, 04Eh, 00Bh, 007h, 000h, 0F1h, 0C9h, 0CDh, 0B2h, 00Bh
        DB 0CDh, 02Bh, 00Ch, 0C9h, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 000h
        DB 0C9h, 0CDh, 04Eh, 00Bh, 020h, 000h, 0C9h, 000h, 000h, 000h
        DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h
        DB 00Ch, 044h, 045h, 046h, 0D5h, 016h, 000h, 05Fh, 019h, 07Eh
        DB 0D1h, 0C9h, 0F5h, 07Ch, 0CDh, 0A1h, 00Bh, 07Dh, 0CDh, 0A1h
        DB 00Bh, 0F1h, 0C9h, 0F5h, 0CDh, 0A1h, 00Bh, 03Eh, 048h, 0CDh
        DB 00Eh, 00Bh, 0F1h, 0C9h, 0F5h, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh
        DB 05Fh, 00Bh, 00Fh, 00Fh, 00Fh, 00Fh, 0CDh, 05Fh, 00Bh, 0F1h
        DB 0C9h, 0CDh, 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0CDh
        DB 0C3h, 00Bh, 0CDh, 03Ch, 00Ah, 008h, 0D9h, 0C9h, 022h, 02Ah
        DB 00Ch, 0CDh, 04Eh, 00Bh, 00Dh, 00Ah, 020h, 050h, 020h, 043h
        DB 020h, 020h, 041h, 020h, 046h, 020h, 020h, 042h, 020h, 043h
        DB 020h, 020h, 044h, 020h, 045h, 020h, 020h, 048h, 020h, 04Ch
        DB 020h, 020h, 053h, 020h, 050h, 00Dh, 00Ah, 000h, 0E1h, 0E5h
        DB 0F5h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch, 00Ch, 0F1h, 0F5h, 0CDh
        DB 0A1h, 00Bh, 0E1h, 0E5h, 07Dh, 0CDh, 0A1h, 00Bh, 0CDh, 06Ch
        DB 00Ch, 060h, 069h, 0CDh, 08Bh, 00Bh, 0CDh, 06Ch
END