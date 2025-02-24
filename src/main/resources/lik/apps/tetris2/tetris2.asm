;*********************************************************************************************************
; 
; Autogenerated by DizAssembler:
;   https://github.com/codenjoyme/8080-emulator/blob/master/src/main/java/spec/assembler/DizAssembler.java
; From: src/main/resources/lik/apps/tetris2/tetris2.rks
; In test: DizAssemblerTest.testDizAssembly()
; 
;*********************************************************************************************************

         CPU  8080
         .ORG 00000h
lnqhc    EQU 012B6h
lnhhj    EQU 01323h
ljpmy    EQU 022CDh
lnltr    EQU 02313h
lnljv    EQU 0231Bh
lnisl    EQU 02377h
lcmsf    EQU 08008h
lcasc    EQU 08FF1h
lgftu    EQU 08FF2h
lcjuk    EQU 08FF4h
lcjko    EQU 08FFCh
lcstc    EQU 0A0A8h
lcsql    EQU 0A338h
lcsrx    EQU 0A438h
lcsmz    EQU 0AC38h
lcuqz    EQU 0B000h
lgozx    EQU 0C037h
ldlfj    EQU 0C170h
lgozr    EQU 0C337h
ldkwn    EQU 0C438h
ldstf    EQU 0FF00h
ldgqd    EQU 0FF01h
lhlrv    EQU 0FF02h
lgzot    EQU 0FF03h
         MVI C,01Fh
         CALL lgozx
         LXI H,lnovl
         LXI D,ljjln
         MVI C,018h
ljjlp:   LDAX D
         MOV M,A
         INX H
         INX D
         DCR C
         JNZ ljjlp
         CALL ljzap
ljymz:   MVI C,00Ch
         CALL lgozx
         LXI H,ljyyf
         CALL ldkwn
         CALL ljzao
         ANI 00Fh
         RLC
         RLC
         RLC
         RLC
         STA lnclp
         CALL ljmjq
         LXI H,ljjtt
         MVI C,050h
lncqz:   MVI M,000h
         INX H
         DCR C
         JNZ lncqz
         MVI A,0FFh
         STA lnonw
         CALL ljvxv
         CALL lnfkw
ljmmn:   CALL ljzaf
         CALL ljyxg
         LXI H,ljynd
         LXI D,lnfvp
         JMP lnrxl

ljynd:   DB 004h, 000h, 000h, 004h, 000h, 000h, 000h, 001h, 000h, 000h
lnrxl:   MVI C,00Ah
ljvyb:   MOV A,M
         STAX D
         INX H
         INX D
         DCR C
         JNZ ljvyb
ljvof:   CALL ljyzn
         CALL ljjlf
         CALL ljvlu
         CALL ljvvm
         LDA ljvpl
         CPI 000h
         JNZ ljmtc
         MVI A,000h
         STA lnrov
         STA ljjmj
         CALL ljmkc
         CALL lncrr
         CALL ljzap
         LDA ljmnd
         CPI 000h
         CZ ljvxi
         LDA lnoxx
         STA lnfvp
         LDA lncuv
         STA ljvzh
         LDA ljyqf
         STA ljjwf
         CALL lnchl
         JMP ljvof
ljmtc:   LDA lnrov
         CPI 001h
         JZ ljymz
         LDA lncuv
         MOV B,A
         LDA ljvzh
         CMP B
         JZ ljjuy
ljvyq:   LDA ljvzh
         STA lncuv
         CALL ljyzn
         CALL ljjlf
         CALL ljvlu
         CALL ljmkc
         CALL ljmvr
         CALL lncrr
         CALL lnfjt
         CALL lnfkl
         JMP ljmmn
ljjuy:   LDA ljjmj
         CPI 001h
         JZ ljvyq
         LDA lnfvp
         STA lnoxx
         LDA ljjwf
         STA ljyqf
         MVI A,001h
         STA ljjmj
         LDA ljvzh
         INR A
         STA lncuv
         JMP ljvof
ljypn:   LDA lnclp
         MOV B,A
ljvot:   MVI C,0FFh
lnonj:   DCR C
         JNZ lnonj
         DCR B
         JNZ ljvot
         RET
lncrr:   LXI H,lnovl
         LXI D,lnovg
         CALL ljjsl
         RET
ljmvr:   LXI H,lnovw
         LXI D,lnovg
         CALL ljjsl
         RET
lnfkl:   LXI H,lnovw
         LXI D,lnovl
         CALL ljjsl
         RET
ljjsl:   MVI C,018h
lncrb:   LDAX D
         MOV M,A
         INX H
         INX D
         DCR C
         JNZ lncrb
         RET
ljyzn:   CALL ljmtz
         LXI D,lnowa
         MVI C,004h
ljypr:   MOV A,M
         STAX D
         INX H
         INX D
         DCR C
         JNZ ljypr
         RET
ljmtz:   LXI H,ljjij
         LDA ljypt
         MVI D,000h
         MOV E,A
         MVI C,010h
ljvml:   DAD D
         DCR C
         JNZ ljvml
         LDA ljyqf
         MOV E,A
         MVI C,004h
lnctr:   DAD D
         DCR C
         JNZ lnctr
         RET
ljjlf:   LXI H,ljvpl
         MVI M,000h
         LXI D,lnowa
         LDA lnoxx
         CPI 004h
         RZ
         JC ljjta
         SUI 004h
         MOV B,A
ljmwg:   MVI C,004h
         PUSH D
         POP H
ljjvm:   MOV A,M
         RRC
         JC lnoms
         MOV M,A
         INX H
         DCR C
         JNZ ljjvm
         DCR B
         JNZ ljmwg
         RET
ljjta:   MOV B,A
         MVI A,004h
         SUB B
         MOV B,A
lnrlq:   MVI C,004h
         PUSH D
         POP H
lnokw:   MOV A,M
         RLC
         JC lnoms
         MOV M,A
         INX H
         DCR C
         JNZ lnokw
         DCR B
         JNZ lnrlq
         RET
lnoms:   LXI H,ljvpl
         MVI M,001h
         RET
ljvvm:   LDA lnooq
         CPI 000h
         JNZ lnfso
         LXI H,lnovw
         LXI D,lnovg
         MVI C,018h
ljvys:   MOV B,M
         LDAX D
         XRA B
         ANA B
         CMP B
         JNZ lnfso
         INX H
         INX D
         DCR C
         JNZ ljvys
         RET
lnfso:   MVI A,001h
         STA ljvpl
         RET
ljmkc:   LXI H,lnovw
         LXI D,lnovg
         MVI C,018h
ljmvu:   LDAX D
         ORA M
         STAX D
         INX H
         INX D
         DCR C
         JNZ ljmvu
         RET
         DB 021h, 076h, 004h, 011h, 0B6h, 004h, 00Eh, 018h, 01Ah, 077h
         DB 023h, 013h, 00Dh, 0C2h, 0F3h, 001h, 0C9h
ljvlu:   LXI H,lnovg
         MVI C,019h
         PUSH H
lnrxp:   MVI M,000h
         INX H
         DCR C
         JNZ lnrxp
         POP H
         LDA lncuv
         MOV E,A
         MVI D,000h
         DAD D
         LXI D,lnowa
         MVI C,004h
lnouj:   LDAX D
         MOV M,A
         INX H
         INX D
         DCR C
         JNZ lnouj
         RET
lnchl:   PUSH B
         PUSH PSW
         CALL ljvwj
         CPI 008h
         JZ lnrxt
         CPI 018h
         JZ ljvon
         CPI 019h
         JZ lnoun
         CPI 01Ah
         JZ lncth
         LDA ljvzh
         INR A
         STA lncuv
         JMP lnotx
lnrxt:   LDA lnfvp
         DCR A
         STA lnoxx
         JMP lnotx
ljvon:   LDA lnfvp
         INR A
         STA lnoxx
         JMP lnotx
lnoun:   LDA ljjwf
         INR A
         CPI 004h
         JNZ ljmvl
         MVI A,000h
ljmvl:   STA ljyqf
         JMP lnotx
lncth:   MVI A,001h
         STA ljmnd
         LDA ljvzh
         INR A
         STA lncuv
         JMP lnrkv
lnotx:   XRA A
         STA ljmnd
lnrkv:   POP PSW
         POP B
         RET

ljjij:   DB 010h, 010h, 010h, 010h, 000h, 078h, 000h, 000h, 010h, 010h
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
ljjku:   DB 020h, 080h, 02Bh, 060h, 033h, 050h, 030h, 055h, 02Bh, 060h
         DB 033h, 050h, 02Bh, 060h, 030h, 055h, 02Bh, 060h, 022h, 078h
         DB 026h, 06Bh, 0ABh, 080h, 020h, 080h, 02Bh, 060h, 033h, 050h
         DB 030h, 055h, 02Bh, 060h, 033h, 050h, 02Bh, 060h, 030h, 055h
         DB 02Bh, 060h, 020h, 080h, 01Eh, 087h, 098h, 08Fh, 01Dh, 08Fh
         DB 022h, 078h, 028h, 065h, 0FFh, 055h, 01Dh, 08Fh, 022h, 078h
         DB 028h, 065h, 0E4h, 060h, 020h, 080h, 026h, 06Bh, 022h, 078h
         DB 022h, 078h, 020h, 080h, 026h, 06Bh, 022h, 078h, 022h, 078h
         DB 020h, 07Fh, 010h, 0FFh, 014h, 0CAh, 055h, 0BFh, 000h, 000h
         DB 000h, 000h, 000h, 000h, 000h, 000h
ljjln:   DB 000h, 070h, 020h, 02Eh, 028h, 00Ch, 0E8h, 04Eh, 040h, 04Eh
         DB 00Ah, 00Eh, 00Ch, 04Ah, 000h, 04Eh, 048h, 04Eh, 042h, 00Eh
         DB 000h, 055h, 0AAh
ljvxv:   PUSH B
         PUSH H
         PUSH PSW
         MVI A,033h
         LXI H,lcsql
         MVI C,0C8h
         CALL ljmtm
         LXI H,lcsmz
         MVI C,0C8h
         CALL ljmtm
         MVI H,0A4h
         CALL lnrxq
         POP PSW
         POP H
         POP B
         RET
lnrxq:   MVI B,008h
ljvyg:   MVI C,008h
         MVI L,0F8h
         CALL ljmtm
         INR H
         DCR B
         JNZ ljvyg
         RET
ljmtm:   MOV M,A
         RLC
         INR L
         DCR C
         JNZ ljmtm
         RET
ljmjq:   PUSH H
         PUSH D
         PUSH PSW
         LXI H,ljjku
         LXI D,lgftu
lnrxa:   MOV A,M
         CPI 000h
         JZ ljymc
         STAX D
         DCX D
         INX H
         MOV A,M
         STAX D
         INX D
         INX H
         CALL ldlfj
         CALL ljvli
         JMP lnrxa
ljymc:   POP PSW
         POP D
         POP H
         RET
ljvli:   PUSH B
         MVI B,025h
lncgw:   MVI C,0FFh
ljmwc:   DCR C
         JNZ ljmwc
         DCR B
         JNZ lncgw
         POP B
         RET
lnfkw:   PUSH H
         LXI H,00000h
         SHLD ljmxl
         POP H
         RET
ljvvy:   PUSH B
         PUSH PSW
         PUSH H
         LHLD ljmxl
         MOV A,L
         MVI B,001h
         ADD B
         DAA
         MOV L,A
         JNC ljmlq
         MOV A,H
         MVI B,001h
         ADD B
         DAA
         MOV H,A
ljmlq:   SHLD ljmxl
         LXI H,lcmsf
         SHLD lcjko
         LHLD ljmxl
         MOV A,H
         CALL lnfwb
         MOV A,L
         CALL lnfwb
         POP H
         POP PSW
         POP B
         RET

ljmxl:   DB 023h, 000h
lnfwb:   PUSH PSW
         ANI 0F0h
         RRC
         RRC
         RRC
         RRC
         CALL ljvxh
         POP PSW
         ANI 00Fh
         CALL ljvxh
         RET
ljvxh:   ORI 030h
         MOV C,A
         CALL lgozx
         RET
lnfjt:   LXI H,ljjmu
         MVI C,018h
ljyzx:   MOV A,M
         CPI 0FFh
         JZ lnckv
         DCX H
         DCR C
         JNZ ljyzx
         CALL ljzap
         RET
lnckv:   DCX H
         MOV A,M
         INX H
         MOV M,A
         DCX H
         DCR C
         JNZ lnckv
         CALL ljzap
         CALL ljmxp
         JMP lnfjt
ljmxp:   MVI A,080h
         STA lcasc
         MVI A,010h
         STA lgftu
         CALL ldlfj
         RET

lnclp:   DB 010h
ljyyf:   DB 020h, 020h, 073h, 06Bh, 06Fh, 072h, 06Fh, 073h, 074h, 078h
         DB 020h, 028h, 030h, 02Dh, 039h, 029h, 020h, 03Fh, 000h
lnfvp:   DB 004h
ljvzh:   DB 000h
ljjwf:   DB 000h
lnoxx:   DB 004h
lncuv:   DB 000h
ljyqf:   DB 000h
ljmnd:   DB 000h
lnrov:   DB 001h, 000h
ljvpl:   DB 001h
ljjmj:   DB 000h, 0D1h, 0E1h, 0C9h, 0E5h, 0C5h, 00Eh, 008h
ljjtt:   DB 000h
lnovl:   DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
         DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
         DB 0FBh, 0E6h, 0ECh
ljjmu:   DB 03Fh, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lnovw:   DB 000h, 018h, 030h, 018h, 030h, 020h, 038h, 010h, 010h, 010h
         DB 030h, 0FBh, 033h, 032h, 01Fh, 01Bh, 0DFh, 07Fh, 0DAh, 0D7h
         DB 0FBh, 0E6h, 0ECh, 03Fh
lnonw:   DB 0FFh, 000h, 000h, 000h, 000h, 000h, 000h, 000h
lnovg:   DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h, 000h, 000h
lnooq:   DB 000h, 000h, 000h, 072h, 06Fh, 000h, 000h, 000h
lnowa:   DB 000h, 018h, 018h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h, 000h
         DB 000h, 000h
ljzap:   PUSH H
         PUSH D
         PUSH B
         PUSH PSW
         LXI H,lcsrx
         LXI D,lnovl
         MVI C,018h
ljvpz:   LDAX D
         CALL lnryp
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
         JNZ ljvpz
         POP PSW
         POP B
         POP D
         POP H
         RET
lnryp:   PUSH H
         PUSH B
         MVI C,008h
lnoxv:   RLC
         JC ljjtr
         CALL ljyol
ljjmh:   INR H
         DCR C
         JZ lnfjf
         JMP lnoxv
ljjtr:   CALL lnoln
         JMP ljjmh
lnfjf:   POP B
         POP H
         RET
lnoln:   PUSH H
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
ljyol:   PUSH H
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
ljzao:   CALL lgozr
         CPI 030h
         JC ljzao
         CPI 03Ah
         JNC ljzao
         INR A
         MVI C,01Fh
         CALL lgozx
         RET
ljvxi:   LDA lncuv
         ADI 004h
         RAL
         RAL
         STA lcasc
         MVI A,008h
         STA lgftu
         CALL ldlfj
         CALL ljypn
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
ljzaf:   CALL ljmur
         LDA lnoxl
         ANI 007h
         CPI 007h
         JZ ljzaf
         STA ljypt
         RET
ljmur:   LHLD lnoxl
         MVI C,010h
lnovp:   MOV A,H
         DAD H
         ANI 060h
         JPE lnolt
         INX H
lnolt:   DCR C
         JNZ lnovp
         SHLD lnoxl
         RET

lnoxl:   DB 061h, 08Bh
ljypt:   DB 001h
ljmmr:   MVI A,082h
lnflh:   STA lgzot
         RET
lnckn:   MVI A,091h
         JMP lnflh
ljvwj:   PUSH B
         PUSH D
         PUSH H
         LXI H,006A7h
         LXI D,0000Ch
         CALL lnckn
         MVI C,001h
         LDA lhlrv
         RLC
         RLC
         RLC
         RLC
         MVI B,004h
lnfmb:   INX H
         RLC
         JNC ljyzt
         DCR B
         JNZ lnfmb
         LDA ldstf
         MVI B,008h
         DCR C
         JZ lnfmb
         JMP lnrvx
ljyzt:   CALL ljmmr
         LDA ldgqd
         RLC
         MVI B,005h
ljmmv:   DAD D
         RLC
         JNC lnrmb
         DCR B
         JNZ ljmmv
lnrvx:   CALL ljmmr
         MVI A,0FFh
         JMP ljyps
lnrmb:   LDA ldgqd
         ANI 002h
         RRC
         MOV E,A
         MOV A,M
         CPI 021h
         JC ljyps
         CPI 080h
         JNC ljyps
         DCR E
         JNZ ljvnc
         CPI 040h
         JC ljyps
         LDA lcjuk
         ADD M
         JMP ljyps
ljvnc:   CPI 040h
         JC lnoxk
         ANI 01Fh
         JMP ljyps
lnoxk:   XRI 010h
ljyps:   POP H
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
ljyxg:   LXI H,lcuqz
         XRA A
         CALL lnryp
         JMP ljvvy
         DB 0E6h, 0D0h, 000h, 000h, 000h, 000h
END