       CPU  8080
       .ORG 00000h
lccd   EQU 02000h
lcdd   EQU 04000h
lsll   EQU 09000h
lmhi   EQU 02018h
lqkk   EQU 0DF5Ah
lcqq   EQU 0201Ah
lerr   EQU 0201Bh
ligg   EQU 0201Dh
leee   EQU 0201Fh
lifg   EQU 02020h
lkuu   EQU 0FFE0h
lqjk   EQU 0FFE1h
lgss   EQU 0FFE2h
lqxx   EQU 02022h
loij   EQU 0FFE3h
laop   EQU 02023h
lgef   EQU 020A5h
lmuv   EQU 020A6h
lskl   EQU 0202Ah
labc   EQU 03FFFh
       LXI SP,labc
       LXI H,lccd
       XRA A
       MVI C,000h
lede:  MOV M,A
       INX H
       DCR C
       JNZ lede
       INR A
       STA lgef
       LXI H,00001h
       SHLD lifg
       LXI H,lkgh
       SHLD lmhi
       MVI A,091h
       STA loij
       MVI A,0BCh
       STA lqjk
       MVI A,005h
       STA lskl
leqr:  CALL lulm
lcpq:  CALL lwmn
       CALL lyno
       LXI H,laop
       MOV A,M
       CMA
       MOV M,A
       ORA A
       JNZ lcpq
       JMP leqr

lacc:  DB 0AFh, 032h, 029h, 020h, 0CDh, 09Dh, 003h, 031h, 0FFh, 03Fh
       DB 0CDh, 0E0h, 004h, 0CDh, 0EBh, 003h, 021h, 000h, 020h, 01Eh
       DB 006h, 0CDh, 063h, 005h, 0D5h, 05Eh, 023h, 056h, 023h, 0CDh
       DB 08Ch, 002h, 0E3h, 07Dh, 0E3h, 0CCh, 081h, 000h, 0D1h, 01Dh
       DB 0C2h, 059h, 000h, 03Ah
lkgh:  DB 01Fh, 020h, 05Fh, 01Dh, 0CAh, 07Dh, 000h, 0CDh, 063h, 005h
       DB 0C3h, 073h, 000h, 003h, 0C3h, 054h, 000h, 0E5h, 0C5h, 0FEh
       DB 001h, 0CAh, 04Bh, 002h, 0D6h, 007h, 02Fh, 021h, 02Bh, 020h
       DB 05Eh, 023h, 056h, 023h, 032h, 0A3h, 020h, 0B7h, 0C2h, 0F5h
       DB 000h, 0B6h, 0F2h, 0A9h, 000h, 021h, 040h, 020h, 0CDh, 07Ch
       DB 004h, 021h, 050h, 020h, 0CDh, 07Ch, 004h, 0EBh, 07Eh, 0FEh
       DB 004h, 0CCh, 0B4h, 005h, 011h, 020h, 000h, 019h, 07Eh, 0FEh
       DB 004h, 0CCh, 0B4h, 005h, 03Ah, 029h, 020h, 0FEh, 064h, 0CAh
       DB 030h, 002h, 03Ah, 01Dh, 020h, 0FEh, 0FEh, 0CAh, 000h, 000h
       DB 01Eh, 009h, 021h, 0B8h, 008h, 023h, 0BEh, 023h, 0CAh, 0DCh
       DB 000h, 01Dh, 0C2h, 0CFh, 000h, 0C3h, 016h, 002h, 0AFh, 032h
       DB 01Dh, 020h, 07Eh, 0E6h, 001h, 0CAh, 0EDh, 000h, 03Ah, 017h
       DB 020h, 0B7h, 0C2h, 016h, 002h, 07Eh, 021h, 02Eh, 020h, 077h
       DB 0C3h, 0F3h, 001h, 0D5h, 087h, 087h, 05Fh, 087h, 083h, 05Fh
       DB 016h, 000h, 021h, 02Bh, 020h, 019h, 0C1h, 05Eh, 023h, 056h
       DB 023h, 07Eh
lyoo:  DB 0B7h, 0FAh, 0F4h, 001h, 0C5h, 0E3h, 0CDh, 0A2h, 002h, 0CAh
       DB 0CDh, 001h, 001h, 0C0h, 0FFh, 009h, 0CDh, 0B8h, 002h, 0CAh
       DB 0CDh, 001h, 023h, 0CDh, 0B8h, 002h, 0CAh, 0CDh, 001h, 03Ah
       DB 0A3h, 020h, 0E6h, 001h, 0C2h, 05Dh, 001h, 0EBh, 001h, 000h
       DB 006h, 009h, 07Eh, 0FEh, 021h, 0CAh, 0E1h, 001h, 0E6h, 001h
       DB 07Eh, 0CAh, 03Fh, 001h, 0AFh, 0B7h, 0CAh, 0E1h, 001h, 0FEh
       DB 020h, 0CAh, 0E1h, 001h, 0E1h, 0E5h, 04Fh, 07Eh, 0B7h, 079h
       DB 0CAh, 057h, 001h, 023h, 023h, 0BEh, 0C2h, 0E1h, 001h, 0E1h
       DB 023h, 077h, 0C3h, 0F3h, 001h, 021h, 05Ah, 0DFh, 019h, 0EBh
       DB 021h, 02Bh, 020h, 04Eh, 023h, 046h, 021h, 05Ah, 0DFh, 009h
       DB 029h, 029h, 029h, 0EBh, 029h, 029h, 029h, 0EBh, 001h, 020h
       DB 020h, 07Dh, 0BBh, 0CAh, 083h, 001h, 00Eh, 0FEh, 0DAh, 083h
list:  DB 001h, 00Eh, 002h, 07Ch, 0BAh, 0CAh, 08Fh, 001h, 006h, 0FCh
       DB 0DAh, 08Fh, 001h, 006h, 004h, 0E1h, 0E5h, 023h, 023h, 07Eh
       DB 0B7h, 0C2h, 0C0h, 001h, 02Bh, 07Eh, 0FEh, 020h, 0C2h, 0ACh
       DB 001h, 0CDh, 0EDh, 005h, 0E6h, 004h, 0C2h, 057h, 001h, 03Eh
       DB 0FCh, 0C3h, 057h, 001h, 0CDh, 0EDh, 005h, 0B7h, 07Eh, 0FAh
       DB 0BAh, 001h, 0CDh, 07Bh, 003h, 0C3h, 057h, 001h, 0CDh, 088h
       DB 003h, 0C3h, 057h, 001h, 0CDh, 0EDh, 005h, 0E6h, 001h, 079h
       DB 0CAh, 057h, 001h, 078h, 0C3h, 057h, 001h, 021h, 02Dh, 020h
       DB 07Eh, 0B7h, 0FAh, 0DAh, 001h, 03Eh, 005h, 0CDh, 05Eh, 002h
       DB 0E1h, 023h, 036h, 000h, 0C3h, 0F3h, 001h, 0E1h, 023h, 0CDh
       DB 0EDh, 005h, 0FEh, 007h, 0DAh, 0F0h, 001h, 023h, 07Eh, 0FEh
       DB 000h, 02Bh, 0CCh, 01Ch, 002h, 02Bh, 0E5h, 02Bh, 02Bh, 0CDh
       DB 00Ah, 006h, 0EBh, 02Ah, 018h, 020h, 0EBh, 03Ah, 0A3h, 020h
       DB 0B7h, 0C2h, 009h, 002h, 011h, 060h, 000h, 0E1h, 07Eh, 0FEh
       DB 001h, 0C2h, 051h, 002h, 011h, 030h, 000h, 0C3h, 051h, 002h
       DB 021h, 02Eh, 020h, 0C3h, 0F3h, 001h, 0CDh, 0EDh, 005h, 0E6h
       DB 006h, 0C6h, 0FCh, 0C2h, 028h, 002h, 03Eh, 004h, 023h, 0BEh
       DB 02Bh, 0CAh, 01Ch, 002h, 077h, 0C9h, 02Ah, 018h, 020h, 02Bh
       DB 02Bh, 02Bh, 02Bh, 022h, 018h, 020h, 0CDh, 0ABh, 004h, 021h
       DB 02Ah, 020h, 03Eh, 009h, 0BEh, 0D4h, 049h, 002h, 0C3h, 044h
       DB 000h, 034h, 0C9h, 0CDh, 0BEh, 002h, 011h, 030h, 000h, 0E1h
       DB 0E5h, 019h, 0EBh, 0C1h, 0E1h, 02Bh, 072h, 02Bh, 073h, 023h
       DB 023h, 0C9h, 036h, 0F0h, 001h, 0B7h, 00Ch, 0FEh, 005h, 0C2h
       DB 078h, 002h, 0E5h, 021h, 02Ah, 020h, 035h, 07Eh, 021h, 004h
       DB 0B5h, 0CDh, 099h, 005h, 0E1h, 001h, 037h, 00Ah, 023h, 023h
       DB 023h, 023h, 036h, 002h, 023h, 036h, 002h, 023h, 071h, 023h
       DB 070h, 021h, 050h, 020h, 0CDh, 07Ch, 004h, 0C9h, 078h, 0BAh
       DB 0C0h, 079h, 0BBh, 0C9h, 0C5h, 04Eh, 023h, 046h, 0C5h, 001h
       DB 00Bh, 000h, 009h, 0E3h, 0CDh, 0A2h, 002h, 0E1h, 0C1h, 0C9h
       DB 0CDh, 0B8h, 002h, 0C8h, 023h, 0CDh, 0B8h, 002h, 0C8h, 001h
       DB 020h, 000h, 009h, 0CDh, 0B8h, 002h, 0C8h, 02Bh, 0CDh, 0B8h
       DB 002h, 0C9h, 07Ch, 0BAh, 0C0h, 07Dh, 0BBh, 0C9h, 02Ah, 014h
       DB 020h, 03Ah, 017h, 020h, 0B7h, 0CAh, 035h, 003h, 03Dh, 032h
       DB 017h, 020h, 0CDh, 06Fh, 003h, 03Ah, 016h, 020h, 05Fh, 0B7h
       DB 03Eh, 0FFh, 0FAh, 0DAh, 002h, 0AFh, 057h, 04Dh, 044h, 019h
       DB 0E5h, 03Eh, 003h, 0BEh, 0CAh, 0F6h, 002h, 011h, 05Ah, 0DFh
       DB 019h, 029h, 029h, 029h, 07Ch, 0B7h, 0FAh, 0F6h, 002h, 0FEh
       DB 030h, 0DAh, 000h, 003h, 03Ah, 016h, 020h, 02Fh, 03Ch, 032h
       DB 016h, 020h, 0E1h, 0C5h, 0E1h, 022h, 014h, 020h, 03Ah, 017h
       DB 020h, 0B7h, 0C8h, 0CDh, 06Fh, 003h, 00Eh, 005h, 0EBh, 021h
       DB 02Bh, 020h, 0CDh, 092h, 002h, 0CCh, 01Dh, 003h, 00Dh, 0C2h
       DB 012h, 003h, 0C9h, 0E5h, 0C5h, 079h, 001h, 0F6h, 0FFh, 009h
       DB 04Fh, 07Eh, 0B7h, 0FAh, 032h, 003h, 079h, 0CDh, 05Eh, 002h
       DB 0AFh, 032h, 017h, 020h, 0C1h, 0E1h, 0C9h, 021h, 02Fh, 020h
       DB 07Eh, 05Fh, 0E6h, 001h, 0C8h, 023h, 023h, 07Eh, 023h, 0BEh
       DB 0C0h, 02Bh, 02Bh, 02Bh, 036h, 000h, 03Eh, 030h, 032h, 017h
       DB 020h, 07Bh, 0E6h, 0E0h, 0F6h, 020h, 032h, 016h, 020h, 021h
       DB 040h, 020h, 0CDh, 07Ch, 004h, 021h, 02Bh, 020h, 05Eh, 023h
       DB 056h, 0EBh, 0FAh, 069h, 003h, 011h, 020h, 000h, 019h, 022h
       DB 014h, 020h, 0C3h, 0BEh, 002h, 0E5h, 0CDh, 035h, 005h, 023h
       DB 023h, 023h, 07Eh, 02Fh, 077h, 0E1h, 0C9h, 087h, 05Fh, 0E6h
       DB 007h, 07Bh, 0C0h, 0B7h, 03Eh, 002h, 0F8h, 02Fh, 03Ch, 0C9h
       DB 0B7h, 0F2h, 08Dh, 003h, 037h, 01Fh, 0CAh, 097h, 003h, 05Fh
       DB 0E6h, 001h, 07Bh, 0C8h, 0B7h, 03Eh, 004h, 0F8h, 02Fh, 03Ch
       DB 0C9h, 00Eh, 064h, 0CDh, 0EDh, 005h, 0E6h, 01Fh, 0FEh, 002h
       DB 0DAh, 09Fh, 003h, 0FEh, 01Eh, 0D2h, 09Fh, 003h, 047h, 02Ah
       DB 020h, 020h, 07Ch, 0E6h, 01Fh, 06Fh, 0CDh, 0EDh, 005h, 0E6h
       DB 00Fh, 085h, 06Fh, 026h, 000h, 029h, 029h, 029h, 029h, 029h
       DB 07Dh, 0B0h, 06Fh, 011h, 0A6h, 020h, 019h, 0AFh, 0BEh, 0C2h
       DB 09Fh, 003h, 023h, 0BEh, 0C2h, 09Fh, 003h, 023h, 0BEh, 0CAh
       DB 09Fh, 003h, 07Eh, 0FEh, 004h, 0D2h, 09Fh, 003h, 02Bh, 036h
       DB 005h, 02Bh, 036h, 004h, 00Dh, 0C2h, 09Fh, 003h, 0C9h, 021h
       DB 004h, 09Bh, 011h, 097h, 00Dh, 001h, 007h, 005h, 0CDh, 018h
       DB 005h, 0CDh, 0B0h, 005h, 021h, 004h, 0ACh, 011h, 0BAh, 00Dh
       DB 001h, 007h, 008h, 0CDh, 018h, 005h, 021h, 004h, 0B5h, 03Ah
       DB 02Ah, 020h, 0CDh, 099h, 005h, 021h, 02Bh, 020h, 011h, 0D0h
       DB 020h, 00Eh, 008h, 0C3h, 01Dh, 004h, 011h, 0C9h, 020h, 073h
       DB 023h, 072h, 023h, 036h, 000h, 023h, 036h, 020h, 023h, 036h
       DB 000h, 023h, 036h, 000h, 023h, 036h, 004h, 023h, 036h, 001h
       DB 023h, 011h, 0B7h, 00Ch, 079h, 0FEh, 008h, 0C2h, 03Fh, 004h
       DB 011h, 077h, 00Ah, 073h, 023h, 072h, 023h, 073h, 023h, 072h
       DB 023h, 00Dh, 0C2h, 01Ah, 004h, 021h, 000h, 020h, 00Eh, 005h
       DB 011h, 000h, 000h, 073h, 023h, 072h, 023h, 03Eh, 07Fh, 083h
       DB 05Fh, 0D2h, 05Fh, 004h, 014h, 00Dh, 0C2h, 053h, 004h, 0AFh
       DB 047h, 04Fh, 077h, 023h, 077h, 032h, 017h, 020h, 021h, 0A6h
       DB 026h, 011h, 000h, 006h, 036h, 021h, 023h, 01Bh, 07Bh, 0B2h
       DB 0C2h, 072h, 004h, 0C9h, 0F5h, 03Eh, 00Bh, 032h, 0E3h, 0FFh
       DB 0CDh, 093h, 004h, 03Eh, 00Ah, 032h, 0E3h, 0FFh, 0CDh, 093h
       DB 004h, 025h, 0C2h, 07Dh, 004h, 0F1h, 0C9h, 07Dh, 03Dh, 0C2h
       DB 094h, 004h, 0C9h, 03Ah, 02Ah, 020h, 0B7h, 0C2h, 04Bh, 000h
       DB 0CDh, 063h, 005h, 0FEh, 0FEh, 0C2h, 0A0h, 004h, 0C3h, 000h
       DB 000h
lulm:  LXI H,lgrs
       LXI B,list
       LDA lgef
lktu:  DAD B
       DCR A
       JNZ lktu
       XCHG
       LXI H,lmuv
lsxy:  PUSH B
       MVI C,004h
       LDAX D
       INX D
lqwx:  RLC
       RLC
       MOV B,A
       ANI 003h
       MOV M,A
       INX H
       DCR C
       MOV A,B
       JNZ lqwx
       POP B
       DCX B
       MOV A,B
       ORA C
       JNZ lsxy
       LXI H,lgef
       MOV A,M
       CPI 001h
       INR M
       RC
       MVI M,001h
       RET
lwmn:  LXI H,lmuv
       LXI D,luyz
lyab:  CALL lwza
       INX H
       DCX D
       MOV A,E
       ORA D
       JNZ lyab
       RET
lyno:  LXI H,lacc
       PUSH H
       LXI B,lcdd
       LXI H,leee
lkhh:  CALL lgff
       LDA ligg
       MVI M,001h
       CPI 00Bh
       RZ
       MVI M,010h
       CPI 00Dh
       RZ
       MVI M,020h
       CPI 00Eh
       RZ
       DCX B
       MOV A,C
       ORA B
       JNZ lkhh
       POP H
       RET
lapp:  PUSH PSW
       PUSH H
       PUSH B
lojj:  PUSH H
       PUSH B
lmii:  LDA laop
       XCHG
       XRA M
       INX H
       XCHG
       MOV M,A
       INR L
       DCR C
       JNZ lmii
       POP B
       POP H
       INR H
       DCR B
       JNZ lojj
       POP B
       POP H
       POP PSW
       RET
lwnn:  PUSH D
       LXI D,lqkk
       DAD D
       DAD H
       DAD H
       DAD H
       LXI D,lsll
       DAD D
       POP D
       RET
lwza:  PUSH PSW
       PUSH D
       PUSH B
       PUSH H
       MOV A,M
       RLC
       RLC
       RLC
       MOV E,A
       MVI D,000h
       LXI H,lumm
       DAD D
       XCHG
       POP H
       PUSH H
       CALL lwnn
       LXI B,lyoo
       CALL lapp
       POP H
       POP B
       POP D
       POP PSW
       RET
lgff:  PUSH H
       LXI H,lcqq
       INR M
       LHLD lerr
       LDA lgss
       ANI 00Fh
       CPI 00Fh
       JNZ litt
       LDA lkuu
litt:  CMP L
       JZ lmvv
       MOV L,A
       MVI H,000h
lmvv:  INR H
       INR A
       JZ loww
       MOV A,H
       CPI 004h
       JC loww
       LDA lcqq
       STA lqxx
       MOV A,L
       STA ligg
loww:  SHLD lerr
       POP H
       RET
       DB 0C5h, 0D5h, 0EBh, 06Fh, 026h, 000h, 029h, 029h, 029h, 001h
       DB 0F2h, 00Dh, 009h, 0EBh, 001h, 008h, 001h, 0CDh, 018h, 005h
       DB 0D1h, 0C1h, 0C9h, 0E5h, 0C3h, 0C2h, 005h, 036h, 000h, 0E5h
       DB 023h, 036h, 000h, 021h, 029h, 020h, 034h, 02Bh, 0CDh, 0E2h
       DB 005h, 00Eh, 005h, 021h, 023h, 020h, 0AFh, 023h, 0BEh, 0C2h
       DB 0D2h, 005h, 00Dh, 0C2h, 0C8h, 005h, 00Ch, 0EBh, 021h, 004h
       DB 0A1h, 01Ah, 013h, 0CDh, 099h, 005h, 024h, 00Dh, 0C2h, 0D6h
       DB 005h, 0E1h, 0C9h, 034h, 07Eh, 0FEh, 00Ah, 0D8h, 036h, 000h
       DB 02Bh, 0C3h, 0E2h, 005h, 0E5h, 0C5h, 02Ah, 020h, 020h, 00Eh
       DB 010h, 07Ch, 029h, 0E6h, 060h, 0EAh, 0FCh, 005h, 023h, 00Dh
       DB 0C2h, 0F4h, 005h
luyz:  DB 022h, 020h, 020h, 03Ah, 022h, 020h, 085h, 0C1h, 0E1h, 0C9h
       DB 05Eh, 023h, 056h, 023h, 07Eh, 0B7h, 0FAh, 006h, 007h, 0CDh
       DB 057h, 007h, 023h, 07Eh, 0FEh, 020h, 0CAh, 02Fh, 006h, 023h
       DB 0BEh, 02Bh, 0CAh, 001h, 007h, 0CDh, 02Ah, 008h, 0C2h, 0D7h
       DB 007h, 0CDh, 0A7h, 008h, 0C2h, 0EFh, 007h, 02Bh, 0AFh, 0BEh
       DB 077h, 023h, 023h, 0C2h, 001h, 008h, 07Eh, 0CDh, 02Ah, 008h
       DB 07Eh, 0C2h, 0C1h, 007h, 0CDh, 0A7h, 008h, 0C2h, 0C6h, 007h
       DB 04Eh, 0AFh, 077h, 023h, 071h, 023h, 04Bh, 042h, 0F5h, 0C5h
       DB 0D5h, 0E5h, 057h, 0E6h, 001h, 07Ah, 0CAh, 068h, 006h, 00Eh
       DB 006h, 021h, 0EBh, 008h, 0B7h, 0F2h, 08Ah, 006h, 021h, 0F1h
       DB 008h, 0C3h, 08Ah, 006h, 0B7h, 0F2h, 075h, 006h, 02Fh, 03Ch
       DB 0FEh, 002h, 0CAh, 075h, 006h, 03Eh, 006h, 087h, 016h, 000h
       DB 05Fh, 03Ah, 0A3h, 020h, 0B7h, 0CAh, 084h, 006h, 07Bh, 0C6h
       DB 010h, 05Fh, 021h, 0CBh, 008h, 019h, 00Eh, 004h, 0D1h, 0D5h
       DB 07Eh, 012h, 013h, 023h, 00Dh, 0C2h, 08Ch, 006h, 0E1h, 0D1h
       DB 0C1h, 0F1h, 0F5h, 0D5h, 0C5h, 07Eh, 023h, 0BEh, 0C2h, 0ABh
       DB 006h, 036h, 001h, 023h, 05Eh, 023h, 056h, 023h, 0C3h, 0B3h
       DB 006h, 034h, 023h, 023h, 023h, 05Eh, 023h, 056h, 02Bh, 0E3h
       DB 0E5h, 0CDh, 035h, 005h, 001h, 010h, 002h, 0CDh, 018h, 005h
       DB 0E1h, 0E3h, 073h, 023h, 072h, 001h, 0F5h, 0FFh, 009h, 0C1h
       DB 071h, 023h, 070h, 023h, 03Ah, 0A3h, 020h, 0B7h, 0C2h, 0DDh
       DB 006h, 023h, 023h, 07Eh, 0D1h, 0D5h, 021h, 000h, 006h, 019h
       DB 077h, 0E1h, 0F1h, 001h, 01Fh, 000h, 0FEh, 002h, 0D8h, 0FEh
       DB 0FFh, 0C8h, 0B7h, 0F2h, 0F3h, 006h, 0FEh, 0FEh, 0CAh, 0F2h
       DB 006h, 009h, 023h, 0CDh, 043h, 005h, 0E6h, 002h, 0CAh, 0FCh
       DB 006h, 009h, 023h, 0CDh, 043h, 005h, 0C9h, 036h, 020h, 0C3h
       DB 02Fh, 006h, 034h, 0CAh, 02Eh, 007h, 023h, 023h, 023h, 023h
       DB 0FEh, 0FAh, 0C2h, 028h, 007h
lovw:  DB 03Ah, 0A3h, 020h, 0B7h, 0CAh, 028h, 007h, 0E5h, 001h, 0F7h
       DB 00Ch, 036h, 005h, 023h, 036h, 005h, 023h, 071h, 023h, 070h
       DB 0E1h, 0AFh, 04Bh, 042h, 0C3h, 098h, 006h, 0EBh, 0CDh, 043h
       DB 005h, 023h, 0CDh, 043h, 005h, 001h, 020h, 000h, 009h, 0CDh
       DB 043h, 005h, 02Bh, 0CDh, 043h, 005h, 0EBh, 023h, 036h, 000h
       DB 02Bh, 02Bh, 001h, 0C9h, 020h, 070h, 02Bh, 071h, 03Ah, 0A3h
       DB 020h, 0B7h, 0CAh, 099h, 004h, 0C3h, 00Ah, 006h, 03Eh, 002h
       DB 0CDh, 02Ah, 008h, 0C8h, 0E5h, 0D5h, 0EBh, 011h, 0A4h, 008h
       DB 0D5h, 023h, 0CDh, 0B2h, 008h, 0C8h, 023h, 0CDh, 0B2h, 008h
       DB 0C8h, 011h, 01Fh, 000h, 019h, 0CDh, 0B2h, 008h, 0C8h, 023h
       DB 0CDh, 0B2h, 008h, 0C8h, 0D1h, 0D1h, 0E1h, 0E3h, 0E1h, 07Eh
       DB 0FEh, 001h, 0CAh, 0BDh, 007h, 036h, 000h, 023h, 023h, 07Eh
       DB 0FEh, 0FEh, 0C2h, 0ACh, 007h, 023h, 07Eh, 0CDh, 02Ah, 008h
       DB 07Eh, 0CAh, 09Fh, 007h, 0E6h, 002h, 07Eh, 0CAh, 0A2h, 007h
       DB 0AFh, 04Bh, 042h, 02Bh, 077h, 07Eh, 023h, 036h, 000h, 023h
       DB 0C3h, 04Eh, 006h, 02Bh, 02Bh, 036h, 001h, 023h, 023h, 07Eh
       DB 036h, 002h, 023h, 077h, 023h, 03Eh, 002h, 0C3h, 04Eh, 006h
       DB 03Eh, 002h, 023h, 023h, 023h, 023h, 0C3h, 098h, 006h, 02Bh
       DB 02Bh, 036h, 002h, 023h, 023h, 07Eh, 036h, 0FEh, 023h, 077h
       DB 023h, 03Eh, 0FEh, 0C3h, 04Eh, 006h, 02Bh, 036h, 000h, 023h
       DB 07Eh, 0F5h, 0C5h, 006h, 003h, 03Eh, 020h, 04Eh, 077h, 079h
       DB 023h, 005h, 0C2h, 0E2h, 007h, 0C1h, 0F1h, 0C3h, 04Eh, 006h
       DB 02Bh, 036h, 002h, 023h, 07Eh, 036h, 020h, 023h, 036h, 0FEh
       DB 023h, 077h, 023h, 03Eh, 0FEh, 0C3h, 04Eh, 006h, 023h, 07Eh
       DB 0CDh, 02Ah, 008h, 0CAh, 015h, 008h, 0C5h, 07Eh, 02Bh, 046h
       DB 077h, 023h, 070h, 023h, 0C1h, 0C3h, 04Eh, 006h, 0CDh, 0A7h
       DB 008h, 02Bh, 0CAh, 046h, 006h, 02Bh, 02Bh, 036h, 002h, 023h
       DB 023h, 03Eh, 0FEh, 077h, 023h, 023h, 0C3h, 04Eh, 006h, 0EBh
       DB 0D5h, 04Dh, 044h, 0E5h, 0E5h, 021h, 0A4h, 020h, 036h, 000h
       DB 021h, 0A4h, 008h, 0E3h, 0FEh, 002h, 0CAh, 05Ch, 008h, 0D8h
       DB 0FEh, 0FCh, 0CAh, 074h, 008h, 0DAh, 08Dh, 008h, 0FEh, 0FEh
       DB 0C0h, 0E5h, 011h, 05Ah, 0DFh, 019h, 07Dh, 0E6h, 01Fh, 0FEh
       DB 002h, 0E1h, 0C8h, 02Bh, 04Dh, 044h, 0C3h, 06Ah, 008h, 0E5h
       DB 011h, 05Ch, 0DFh, 019h, 07Dh, 0E6h, 01Fh, 0E1h, 0C8h, 023h
       DB 04Dh, 044h, 023h, 03Eh, 003h, 0BEh, 0C8h, 011h, 020h, 000h
       DB 019h, 0BEh, 0C9h, 011h, 03Ah, 0DFh, 019h, 07Dh, 0F6h, 01Fh
       DB 0BCh, 0C8h, 011h, 0A6h, 020h, 019h, 04Dh, 044h, 03Eh, 003h
       DB 0BEh, 0C8h, 032h, 0A4h, 020h, 023h, 0BEh, 0C9h, 0E5h, 011h
       DB 07Ah, 0DFh, 019h, 029h, 029h, 029h, 03Eh, 030h, 0BCh, 0E1h
       DB 0C8h, 011h, 020h, 000h, 019h, 04Dh, 044h, 019h, 0C3h, 083h
       DB 008h, 0D1h, 0E1h, 0C9h, 03Ah, 0A4h, 020h, 0B7h, 0C8h, 03Eh
       DB 0FEh, 0CDh, 02Ah, 008h, 0C9h, 07Eh, 0FEh, 001h, 0C8h, 0FEh
       DB 002h, 0C9h, 07Fh, 0FCh, 0BFh, 002h, 0DFh, 004h, 0EFh, 0FFh
       DB 0F7h, 0FEh, 0FBh, 001h, 0FDh, 000h, 0FEh, 000h, 0DCh, 000h
       DB 004h, 004h, 077h, 00Ah, 002h, 002h, 037h, 00Ah, 005h, 005h
       DB 0F7h, 008h, 005h, 005h, 097h, 009h, 002h, 002h, 0B7h, 00Ch
       DB 002h, 002h, 0B7h, 00Ch, 005h, 005h, 017h, 00Ch, 005h, 005h
       DB 077h, 00Bh, 004h, 002h, 077h, 00Ah, 0F7h, 00Ah, 004h, 002h
       DB 077h, 00Ah, 037h, 00Bh, 000h, 001h, 001h, 000h, 00Fh, 01Fh
       DB 037h, 01Fh, 007h, 007h, 007h, 006h, 006h, 00Ch, 018h, 018h
       DB 0E0h, 0F0h, 0E0h, 0C0h, 0E0h, 0ECh, 0F8h, 0E0h, 0C0h, 0F0h
       DB 01Ch, 00Ch, 018h, 030h, 000h, 000h, 000h, 001h, 001h, 000h
       DB 007h, 00Fh, 00Fh, 007h, 007h, 007h, 007h, 006h, 07Ch, 040h
       DB 000h, 000h, 0E0h, 0F0h, 0E0h, 0C0h, 0E0h, 0E0h, 0F8h, 0E0h
       DB 0C0h, 0F8h, 00Ch, 006h, 007h, 000h, 000h, 000h, 000h, 001h
       DB 001h, 000h, 01Fh, 037h, 027h, 037h, 007h, 0E7h, 036h, 01Ch
       DB 000h, 000h, 000h, 000h, 060h, 0F0h, 0E0h, 0C0h, 0E0h, 0E6h
       DB 0FCh, 0E0h, 0C0h, 0E0h, 030h, 018h, 030h, 060h, 030h, 000h
       DB 000h, 001h, 001h, 000h, 03Fh, 0EFh, 0C7h, 0C7h, 007h, 003h
       DB 019h, 00Eh, 003h, 000h, 001h, 003h, 0E0h, 0F0h, 0E0h, 0C3h
       DB 0E3h, 0F6h, 0FCh, 0E0h, 0C0h, 0E0h, 0F0h, 0F0h, 0E0h, 0C0h
       DB 080h, 000h, 000h, 001h, 001h, 000h, 01Fh, 06Fh, 067h, 037h
       DB 007h, 003h, 000h, 003h, 000h, 001h, 003h, 006h, 0E0h, 0F0h
       DB 0E0h, 0C0h, 0E0h, 0F6h, 0F6h, 0F3h, 0C0h, 0E0h, 0F8h, 0CCh
       DB 0F8h, 080h, 000h, 000h, 007h, 00Fh, 007h, 003h, 007h, 037h
       DB 01Fh, 007h, 003h, 00Fh, 038h, 030h, 018h, 00Ch, 000h, 000h
       DB 000h, 080h, 080h, 000h, 0F0h, 0F8h, 0ECh, 0F8h, 0E0h, 0E0h
       DB 0E0h, 060h, 060h, 030h, 018h, 018h, 007h, 00Fh, 007h, 003h
       DB 007h, 007h, 01Fh, 007h, 003h, 01Fh, 030h, 060h, 0E0h, 000h
       DB 000h, 000h, 000h, 080h, 080h, 000h, 0E0h, 0F0h, 0F0h, 0E0h
       DB 0E0h, 0E0h, 0E0h, 060h, 03Eh, 002h, 000h, 000h, 007h, 00Fh
       DB 007h, 003h, 007h, 067h, 03Fh, 007h, 003h, 007h, 00Ch, 018h
       DB 00Ch, 006h, 00Ch, 000h, 000h, 080h, 080h, 000h, 0F8h, 0ECh
       DB 0E4h, 0ECh, 0E0h, 0E7h, 06Ch, 038h, 000h, 000h, 000h, 000h
       DB 007h, 00Fh, 007h, 0C3h, 0C7h, 06Fh, 03Fh, 007h, 003h, 007h
       DB 00Fh, 00Fh, 007h, 003h, 001h, 000h, 000h, 080h, 080h, 000h
       DB 0FCh, 0F7h, 0E3h, 0E3h, 0E0h, 0C0h, 098h, 070h, 0C0h, 000h
       DB 080h, 0C0h, 007h, 00Fh, 007h, 003h, 007h, 06Fh, 06Fh, 03Fh
       DB 003h, 007h, 01Fh, 033h, 01Fh, 001h, 000h, 000h, 000h, 080h
       DB 080h, 000h, 0F8h, 0F6h, 0E6h, 0ECh, 0E0h, 0C0h, 000h, 0C0h
       DB 000h, 080h, 0C0h, 060h, 001h, 003h, 003h, 001h, 03Fh, 0ECh
       DB 0C7h, 047h, 063h, 007h, 006h, 00Eh, 000h, 000h, 000h, 000h
       DB 086h, 0C2h, 0C2h, 086h, 0FCh, 030h, 0E0h, 0E0h, 0C0h, 0E0h
       DB 070h, 030h, 030h, 030h, 030h, 038h, 061h, 043h, 043h, 061h
       DB 03Fh, 00Ch, 007h, 007h, 003h, 007h, 00Eh, 00Ch, 00Ch, 00Ch
       DB 00Ch, 01Ch, 080h, 0C0h, 0C0h, 080h, 0FCh, 037h, 0E3h, 0E2h
       DB 0C6h, 0E0h, 060h, 070h, 000h, 000h, 000h, 000h, 000h, 001h
       DB 001h, 000h, 01Fh, 07Ch, 067h, 047h, 0C3h, 007h, 00Eh, 00Ch
       DB 00Ch, 00Ch, 00Ch, 01Ch, 0E0h, 0F0h, 0E0h, 0C0h, 0F8h, 03Ch
       DB 0ECh, 0E8h, 0D8h, 0E0h, 070h, 030h, 030h, 030h, 030h, 038h
       DB 001h, 003h, 003h, 001h, 03Fh, 06Ch, 06Fh, 047h, 063h, 007h
       DB 00Eh, 00Ch, 00Ch, 00Ch, 00Ch, 01Ch, 080h, 0C0h, 0C0h, 080h
       DB 0FCh, 036h, 0F6h, 0E2h, 0C6h, 0E0h, 070h, 030h, 030h, 030h
       DB 030h, 038h, 007h, 00Fh, 007h, 003h, 01Fh, 03Ch, 037h, 017h
       DB 01Bh, 00Eh, 00Eh, 00Ch, 00Ch, 00Ch, 00Ch, 01Ch, 000h, 080h
       DB 080h, 000h, 0F8h, 03Eh, 0E6h, 0E2h, 0C3h, 0E0h, 070h, 030h
       DB 030h, 030h, 030h, 038h, 001h, 003h, 003h, 001h, 03Fh, 06Ch
       DB 06Fh, 047h, 063h, 007h, 00Eh, 00Ch, 00Ch, 00Ch, 00Ch, 01Ch
       DB 080h, 0C0h, 0C0h, 080h, 0FCh, 036h, 0F6h, 0E2h, 0C6h, 0E0h
       DB 070h, 030h, 030h, 030h, 030h, 038h, 038h, 07Ch, 078h, 030h
       DB 07Fh, 0C3h, 0FFh, 0FFh, 07Ch, 07Ch, 066h, 066h, 063h, 061h
       DB 061h, 061h, 000h, 000h, 000h, 000h, 000h, 080h, 080h, 0C0h
       DB 0D0h, 0F0h, 030h, 010h, 000h, 080h, 080h, 0C0h, 038h, 07Ch
       DB 078h, 030h, 07Fh, 0C3h, 0FEh, 0FEh, 07Ch, 07Ch, 06Eh, 066h
       DB 063h, 061h, 061h, 061h, 000h, 000h, 000h, 00Fh, 0FCh, 0C0h
       DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 080h, 080h, 0C0h
       DB 000h, 000h, 000h, 000h, 000h, 001h, 001h, 003h, 00Bh, 00Fh
       DB 00Ch, 008h, 000h, 001h, 001h, 003h, 01Ch, 03Eh, 01Eh, 00Ch
       DB 0FEh, 0C3h, 0FFh, 0FFh, 03Eh, 03Eh, 066h, 066h, 0C6h, 086h
       DB 086h, 086h, 000h, 000h, 000h, 0F0h, 03Fh, 003h, 000h, 000h
       DB 000h, 000h, 000h, 000h, 000h, 001h, 001h, 003h, 01Ch, 03Eh
       DB 01Eh, 00Ch, 0FEh, 0C3h, 07Fh, 07Fh, 03Eh, 03Eh, 066h, 066h
       DB 0C6h, 086h, 086h, 086h, 007h, 007h, 003h, 0C1h, 0C7h, 06Fh
       DB 03Eh, 00Dh, 001h, 007h, 00Fh, 00Fh, 007h, 003h, 001h, 000h
       DB 0C0h, 0E0h, 0B8h, 084h, 0F8h, 0F6h, 0E3h, 083h, 0C0h, 0E0h
       DB 098h, 074h, 0C0h, 000h, 080h, 0C0h, 003h, 007h, 003h, 001h
       DB 007h, 06Fh, 05Bh, 037h, 003h, 007h, 01Fh, 033h, 01Fh, 001h
       DB 000h, 000h, 080h, 0D8h, 0F0h, 0A0h, 0C0h, 0F4h, 066h, 0CCh
       DB 0E0h, 0E0h, 000h, 0C0h, 020h, 080h, 0C0h, 060h, 003h, 007h
       DB 007h, 003h, 007h, 037h, 01Dh, 007h, 003h, 00Fh, 03Ah, 030h
       DB 018h, 00Ch, 018h, 000h, 098h, 0E4h, 0C0h, 000h, 0F0h, 0F8h
       DB 0ACh, 0D8h, 0F0h, 0C0h, 0E0h, 0E0h, 070h, 010h, 018h, 030h
       DB 007h, 00Fh, 00Fh, 003h, 007h, 00Fh, 03Fh, 006h, 001h, 01Fh
       DB 030h, 060h, 0E0h, 000h, 000h, 000h, 000h, 0C0h, 0E0h, 010h
       DB 0C8h, 0F4h, 0F0h, 0E0h, 0D0h, 0F0h, 0E0h, 060h, 03Eh, 002h
       DB 000h, 000h, 003h, 007h, 007h, 003h, 007h, 06Fh, 03Fh, 006h
       DB 001h, 007h, 00Dh, 018h, 00Ch, 006h, 00Ch, 000h, 088h, 0F4h
       DB 0E0h, 000h, 0B8h, 0ECh, 0E4h, 0CCh, 0C8h, 0E7h, 06Ch, 038h
       DB 000h, 000h, 000h, 000h, 001h, 013h, 00Fh, 001h, 03Fh, 06Fh
       DB 0C7h, 0C2h, 007h, 003h, 019h, 02Eh, 003h, 000h, 001h, 003h
       DB 0C0h, 0E0h, 0E0h, 0C3h, 0E3h, 0F6h, 0FCh, 0A0h, 0C0h, 0C0h
       DB 0E0h, 070h, 0F0h, 0C0h, 080h, 000h, 001h, 007h, 00Fh, 011h
       DB 01Fh, 06Fh, 066h, 033h, 007h, 007h, 001h, 003h, 004h, 001h
       DB 003h, 006h, 0C0h, 0E0h, 0E0h, 0C3h, 0E3h, 0F6h, 0FCh, 0A0h
       DB 0C0h, 060h, 0F0h, 0F0h, 0E0h, 0C0h, 080h, 000h, 001h, 007h
       DB 00Fh, 011h, 01Fh, 06Fh, 067h, 032h, 007h, 007h, 001h, 003h
       DB 004h, 001h, 003h, 006h, 0C0h, 0E0h, 0C0h, 080h, 0E0h, 0F2h
       DB 0D6h, 0FCh, 0C0h, 0E0h, 0F8h, 0CCh, 0F8h, 080h, 000h, 000h
       DB 019h, 007h, 003h, 000h, 00Fh, 01Fh, 037h, 01Dh, 007h, 007h
       DB 006h, 007h, 00Eh, 00Ch, 010h, 010h, 0C0h, 0E0h, 0E0h, 0C0h
       DB 0E0h, 0ECh, 0F8h, 0A0h, 0C0h, 0F0h, 0B8h, 00Ch, 018h, 010h
       DB 008h, 000h, 001h, 003h, 007h, 009h, 037h, 00Fh, 01Dh, 00Bh
       DB 00Dh, 00Fh, 007h, 006h, 07Ch, 040h, 000h, 000h, 0C0h, 0E0h
       DB 0C0h, 080h, 0E0h, 0F2h, 0DCh, 060h, 0C0h, 0F8h, 00Ch, 006h
       DB 007h, 000h, 000h, 000h, 031h, 023h, 023h, 031h, 018h, 00Fh
       DB 00Dh, 007h, 003h, 003h, 003h, 007h, 006h, 006h, 004h, 00Ch
       DB 0C0h, 0F2h, 0F4h, 0F8h, 0C0h, 0F8h, 0DCh, 0F2h, 0E6h, 0F4h
       DB 0B0h, 030h, 018h, 000h, 000h, 000h, 003h, 007h, 007h, 00Dh
       DB 00Dh, 07Fh, 0D5h, 047h, 063h, 007h, 007h, 006h, 00Ch, 000h
       DB 000h, 000h, 0C6h, 0E2h, 0C2h, 086h, 08Ch, 0F8h, 0B0h, 0E0h
       DB 0E0h, 0E0h, 070h, 070h, 030h, 030h, 010h, 018h, 003h, 003h
       DB 003h, 005h, 03Fh, 06Dh, 047h, 04Bh, 06Ah
lgrs:  DB 006h, 00Eh, 00Ch, 00Ch, 008h, 008h, 018h, 080h, 0C0h, 0E0h
       DB 090h, 0FCh, 0B6h, 0E3h, 062h, 0D6h, 0E0h, 030h, 030h, 030h
       DB 010h, 010h, 008h, 001h, 003h, 023h, 019h, 004h, 064h, 010h
       DB 04Ah, 022h, 008h, 016h, 00Ch, 00Ch, 00Ch, 008h, 018h, 080h
       DB 0C0h, 0E0h, 08Ch, 060h, 022h, 0D2h, 054h, 080h, 098h, 060h
       DB 0B0h, 030h, 010h, 010h, 008h, 012h, 00Bh, 04Eh, 028h, 028h
       DB 004h, 010h, 070h, 000h, 058h, 0C2h, 004h, 018h, 005h, 009h
       DB 01Ch, 020h, 088h, 0D2h, 040h, 084h, 058h, 010h, 006h, 012h
       DB 00Ch, 042h, 060h, 014h, 090h, 020h, 038h, 005h, 042h, 010h
       DB 008h, 080h, 000h, 040h, 080h, 000h, 040h, 000h, 090h, 000h
       DB 024h, 000h, 009h, 040h, 024h, 088h, 001h, 004h, 000h, 002h
       DB 000h, 001h, 000h, 004h, 002h, 020h, 010h, 040h, 020h, 002h
       DB 001h, 010h, 040h, 000h, 006h, 04Fh, 00Fh, 007h, 083h, 003h
       DB 014h, 018h, 01Ch, 040h, 001h, 000h, 022h, 009h, 003h, 003h
       DB 034h, 079h, 0F8h, 0B0h, 060h, 0C0h, 081h, 000h, 002h, 020h
       DB 000h, 03Eh, 063h, 063h, 060h, 063h, 063h, 03Eh, 063h, 063h
       DB 063h, 07Fh, 003h, 003h, 003h, 07Fh, 060h, 060h, 07Eh, 060h
       DB 060h, 07Fh, 07Fh, 07Fh, 01Ch, 01Ch, 01Ch, 01Ch, 01Ch, 000h
       DB 01Ch, 01Ch, 000h, 01Ch, 01Ch, 000h, 07Fh, 07Fh, 063h, 063h
       DB 063h, 063h, 063h, 03Eh, 063h, 063h, 063h, 063h, 063h, 03Eh
       DB 07Fh, 07Fh, 063h, 063h, 063h, 063h, 063h, 063h, 063h, 07Bh
       DB 06Dh, 06Dh, 06Dh, 07Bh, 07Fh, 07Fh, 01Ch, 01Ch, 01Ch, 01Ch
       DB 01Ch, 03Eh, 063h, 063h, 063h, 063h, 063h, 03Eh, 063h, 066h
       DB 06Ch, 078h, 06Ch, 066h, 063h, 000h, 01Ch, 01Ch, 000h, 01Ch
       DB 01Ch, 000h, 01Ch, 036h, 077h, 077h, 077h, 036h, 01Ch, 000h
       DB 01Eh, 03Eh, 06Eh, 00Eh, 00Eh, 00Eh, 03Fh, 000h, 01Eh, 067h
       DB 067h, 00Eh, 038h, 060h, 07Fh, 000h, 01Eh, 067h, 067h, 00Eh
       DB 067h, 067h, 01Eh, 000h, 006h, 00Eh, 016h, 026h, 07Fh, 006h
       DB 006h, 000h, 07Fh, 060h, 07Eh, 007h, 067h, 067h, 03Eh, 000h
       DB 03Eh, 060h, 07Eh, 067h, 067h, 067h, 03Eh, 000h, 07Fh, 07Fh
       DB 007h, 00Eh, 01Ch, 01Ch, 01Ch, 000h, 03Eh, 077h, 077h, 03Eh
       DB 077h, 077h, 03Eh, 000h, 03Eh, 073h, 073h, 073h, 03Fh, 006h
       DB 03Ch, 000h
lumm:  DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 0DFh, 020h
       DB 020h, 020h, 020h, 020h, 020h, 020h, 0FBh, 004h, 004h, 004h
       DB 004h, 004h, 004h, 004h, 07Fh, 07Fh, 07Fh, 000h, 0F7h, 0F7h
       DB 0F7h, 000h, 000h, 000h, 018h, 03Ch, 018h, 018h, 018h, 03Ch
       DB 07Eh, 07Eh, 042h, 042h, 042h, 042h, 07Eh, 03Ch, 0F0h, 0AAh
       DB 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh, 055h, 0AAh
       DB 055h, 000h, 00Fh, 0FFh, 0C0h, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
       DB 00Ch, 015h, 040h, 0FFh, 005h, 055h, 055h, 057h, 00Ch, 02Ah
       DB 080h, 0FFh, 00Ah, 0AAh, 0AAh, 0ABh, 00Ch, 030h, 0C0h, 000h
       DB 000h, 000h, 0F0h, 0C3h, 00Ch, 030h, 055h, 055h, 05Ch, 000h
       DB 0F0h, 0C3h, 00Ch, 030h, 0AAh, 0AAh, 0AFh, 0F0h, 0F0h, 0C3h
       DB 00Ch, 030h, 0C0h, 00Fh, 00Fh, 0F0h, 0F0h, 0C3h, 00Ch, 030h
       DB 0C0h, 00Fh, 000h, 030h, 0F0h, 003h, 00Ch, 000h, 0C3h, 00Fh
       DB 000h, 030h, 0F0h, 003h, 00Ch, 000h, 0C3h, 00Fh, 00Ch, 030h
       DB 0F0h, 0C3h, 00Fh, 0F0h, 0C3h, 00Fh, 00Ch, 030h, 000h, 0C3h
       DB 00Fh, 0F0h, 0C3h, 00Fh, 00Ch, 030h, 000h, 0C3h, 00Ch, 000h
       DB 0C3h, 00Fh, 00Ch, 030h, 000h, 0C3h, 00Ch, 000h, 0C3h, 000h
       DB 055h, 055h, 054h, 0C3h, 00Ch, 015h, 0C3h, 000h, 0AAh, 0AAh
       DB 0A8h, 0C3h, 00Ch, 02Ah, 0C3h, 0C0h, 003h, 00Fh, 0F0h, 0C3h
       DB 00Ch, 030h, 0C0h, 0C0h, 00Fh, 00Fh, 0F0h, 0C3h, 00Ch, 030h
       DB 0C0h, 0C0h, 03Ch, 00Fh, 0F0h, 0C3h, 00Ch, 030h, 0F0h, 0C0h
       DB 0F0h, 003h, 0F0h, 0C3h, 00Ch, 030h, 0F0h, 055h, 055h, 040h
       DB 0F0h, 0C3h, 00Ch, 030h, 0F0h, 0AAh, 0AAh, 080h, 030h, 0C3h
       DB 00Ch, 030h, 000h, 000h, 030h, 0F0h, 015h, 055h, 00Ch, 030h
       DB 000h, 000h, 030h, 0F0h, 02Ah, 0AAh, 00Ch, 03Fh, 0FCh, 03Ch
       DB 030h, 0F0h, 003h, 0C3h, 00Ch, 00Fh, 00Ch, 03Ch, 030h, 0F0h
       DB 003h, 0C3h, 00Ch, 003h, 00Ch, 03Ch, 030h, 0FFh, 0C3h, 0C3h
       DB 00Ch, 015h, 055h, 070h, 030h, 0FFh, 0C3h, 0C3h, 00Ch, 02Ah
       DB 0AAh, 080h, 030h, 057h, 0C3h, 0C3h, 00Ch, 03Fh, 00Fh, 000h
       DB 0F0h, 0ABh, 0C3h, 0C3h, 00Ch, 03Fh, 00Fh, 003h, 0C0h, 0C0h
       DB 0C3h, 0C3h, 00Ch, 03Fh, 00Fh, 00Fh, 000h, 0C0h, 0C3h, 0C3h
       DB 00Ch, 03Fh, 00Fh, 00Ch, 000h, 0F0h, 0C3h, 0C3h, 00Ch, 03Fh
       DB 00Fh, 00Ch, 000h, 0F0h, 0C0h, 003h, 005h, 055h, 04Fh, 00Ch
       DB 000h, 0F0h, 0C0h, 003h, 00Ah, 0AAh, 08Fh, 00Ch, 015h, 0F0h
       DB 0F0h, 00Fh, 00Ch, 030h, 000h, 00Ch, 02Ah, 0F0h, 0F0h, 03Fh
       DB 00Ch, 030h, 000h, 00Ch, 030h, 000h, 0F0h, 0FFh, 00Ch, 030h
       DB 0FFh, 00Ch, 030h, 000h, 0F0h, 0F3h, 00Ch, 000h, 0FFh, 00Ch
       DB 030h, 0F0h, 0F0h, 0F3h, 00Ch, 030h, 055h, 055h, 055h, 0F0h
       DB 000h, 0FFh, 00Ch, 030h, 0AAh, 0AAh, 0AAh, 0F0h, 000h, 03Fh
       DB 00Ch, 030h, 003h, 00Fh, 000h, 0F0h, 000h, 00Fh, 00Ch, 030h
       DB 003h, 00Ch, 003h, 0F0h, 0F0h, 003h, 00Ch, 030h, 003h, 000h
       DB 00Fh, 000h, 0F0h, 003h, 00Ch, 030h, 003h, 000h, 03Fh, 000h
       DB 0F0h, 003h, 00Dh, 055h, 055h, 054h, 015h, 055h, 055h, 057h
       DB 00Eh, 0AAh, 0AAh, 0A8h, 02Ah, 0AAh, 0AAh, 0ABh, 00Fh, 0FFh
       DB 0FFh, 0FCh, 03Fh, 0FFh, 0FFh, 0FFh
END