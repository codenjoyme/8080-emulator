;*********************************************************************************************************
; 
; Autogenerated by DizAssembler:
;   https://github.com/codenjoyme/8080-emulator/blob/master/src/main/java/spec/assembler/DizAssembler.java
; From: src/main/resources/lik/roms/01_zagr.bin
; In test: DizAssemblerTest.testDecompileTest_lik_romZagruzchik()
; 
;*********************************************************************************************************

         CPU  8080
         .ORG 0C000h
ljyzh    EQU 00000h
ljypl    EQU 00008h
ljyzb    EQU 00300h
lnftz    EQU 00323h
lkahc    EQU 018A0h
lnpsu    EQU 018FEh
lnimf    EQU 0266Fh
lohyk    EQU 07FFFh
lcauo    EQU 08FE1h
lftte    EQU 08FE3h
lbxtu    EQU 08FE5h
lfqsk    EQU 08FE7h
lcaks    EQU 08FE9h
lftji    EQU 08FEBh
lbxjy    EQU 08FEDh
lfqio    EQU 08FEFh
lcmve    EQU 08FF0h
lcasc    EQU 08FF1h
lgftu    EQU 08FF2h
lftqs    EQU 08FF3h
lcjuk    EQU 08FF4h
lgcta    EQU 08FF6h
lcmli    EQU 08FF8h
lgfjy    EQU 08FFAh
lcjko    EQU 08FFCh
lbxhm    EQU 08FFDh
lgcje    EQU 08FFEh
lfqgc    EQU 08FFFh
ldldh    EQU 0C800h
ldstf    EQU 0FF00h
ldgqd    EQU 0FF01h
lhlrv    EQU 0FF02h
lgzot    EQU 0FF03h
lgwbe    EQU 0FFFFh
         JMP lgsdt
lgsdt:   LXI SP,lohyk
         MVI A,082h
         STA lgzot
         JMP ldiiv
         DB 000h, 000h
ldlft:   PUSH H
         PUSH B
         LXI H,ljyzh
         DAD SP
         SHLD lgcta
         LXI SP,0C000h
         LHLD lgfjy
         LXI B,ljyzb
lhegf:   PUSH H
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
         JNZ lhegf
         LHLD lgcta
         SPHL
         POP B
         POP H
         RET
lgozx:   PUSH H
         PUSH D
         PUSH B
         PUSH PSW
         MOV A,C
         CPI 021h
         JC ldifc
lczfh:   LHLD lcjko
         MOV A,H
         CPI 0BEh
         JNC lheds
lhdxd:   ADI 003h
         STA lbxhm
         XCHG
ldlfx:   MOV A,C
         STA lcaks
         SUI 020h
         LHLD lfqsk
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
         SHLD lcmli
         MVI B,008h
         NOP
lhbdd:   LDAX D
         MOV L,A
         MVI H,000h
         MOV A,C
lgrqz:   DAD H
         DAD H
         DCR A
         JNZ lgrqz
         PUSH H
         INX D
         DCR B
         JNZ lhbdd
         MVI B,008h
         LHLD lcmli
ldhxo:   POP D
         MOV A,D
         CALL lgsdj
         MOV M,A
         INR H
         MOV A,E
         CALL lgsdj
         MOV M,A
         DCR H
         DCR L
         DCR B
         JNZ ldhxo
lhats:   POP PSW
         POP B
         POP D
         POP H
         RET
lgsdc:   MOV C,A
         LDA lgfjy
         ORA A
         JNZ lgosm
         MOV A,C
         CMA
         ANA M
         RET
lgosm:   MOV A,M
         ORA C
         RET
lheds:   MOV A,L
         CPI 0F5h
         JNC ldiho
         ADI 00Ah
         MOV L,A
         STA lcjko
         MVI H,000h
         MOV A,H
         JMP lhdxd
ldiho:   CALL lcvtp
         NOP
         CALL ldlft
         LXI H,ljypl
         SHLD lcjko
         JMP lczfh
ldifc:   LHLD lcjko
         CPI 020h
         JZ lgpdb
         CPI 00Ah
         JZ lgsbj
         CPI 00Dh
         JZ ldkxv
         CPI 018h
         JZ lgpdb
         CPI 008h
         JZ lcvtz
         CPI 019h
         JZ lcysh
         CPI 01Ah
         JZ lcwep
         CPI 00Ch
         JZ lczcx
         CPI 01Fh
         JZ lgpat
         JMP lcvsh
lgpdb:   MOV A,H
         CPI 0BEh
         JNC lgsbj
         ADI 003h
         MOV H,A
         JMP lcvsh
lgsbj:   MVI H,000h
         MOV A,L
         CPI 0F5h
         JNC lczep
         ADI 00Ah
         MOV L,A
         JMP lcvsh
lczep:   CALL lcvtp
         NOP
         JMP lgpat
ldkxv:   MVI H,000h
         JMP lcvsh
lcvtz:   MOV A,H
         CPI 002h
         JC lcvsh
         SUI 003h
         MOV H,A
         JMP lcvsh
lcysh:   MOV A,L
         CPI 011h
         JC lcvsh
         SUI 00Ah
         MOV L,A
         JMP lcvsh
lcwep:   MOV A,L
         CPI 0F5h
         JNC lcvsh
         ADI 00Ah
         MOV L,A
         JMP lcvsh
lczcx:   LXI H,ljypl
         JMP lcvsh
lgpat:   CALL ldlft
         JMP lczcx
lcvsh:   SHLD lcjko
         JMP lhats
lgsdj:   MOV C,A
         LDA lcaks
         CPI 07Fh
         MOV A,C
         JZ lgsdc
         XRA M
         RET
         DB 000h
ldlfj:   PUSH H
         PUSH B
         LHLD lcasc
lcwbn:   MVI A,00Bh
         STA lgzot
         CALL lgote
         MVI A,00Ah
         STA lgzot
         CALL lgote
         DCR H
         JNZ lcwbn
         NOP
         NOP
         NOP
         POP B
         POP H
         RET
lgote:   MOV B,L
ldlfu:   DCR B
         JNZ ldlfu
         RET
lcwby:   PUSH PSW
         MVI A,040h
         STA lgftu
         CALL ldlfj
         POP PSW
         RET
ldlhq:   PUSH H
         MVI A,040h
lgsde:   STA lcasc
         CALL ldlfj
         POP H
         RET
lgrti:   PUSH H
         MVI A,050h
         JMP lgsde
lczcc:   PUSH H
         PUSH B
         MVI B,0FFh
lcwbi:   NOP
         NOP
         NOP
         LDA lcjuk
         ORA A
         JZ lhegk
         CALL ldiev
         LDA ldgqd
         ANI 002h
         JZ lgrtt
lhdxe:   CALL lhduj
         LDA ldstf
         CPI 0FFh
         JNZ lgoqg
         LDA lhlrv
         ORI 0F0h
         CPI 0FFh
         JNZ lcweb
         JMP lcwbi
lhegk:   CALL ldiev
         LDA ldgqd
         ANI 002h
         JNZ lhedy
         MVI B,0FFh
         JMP lhdxe
lhedy:   DCR B
         JNZ lhdxe
         STA lcjuk
         NOP
         NOP
         NOP
         JMP lcwbi
lgoqg:   MOV L,A
         MVI H,0FFh
         JMP ldkyb
lcweb:   MOV H,A
         MVI L,0FFh
ldkyb:   MVI C,0FBh
lhdwr:   INR C
         DAD H
         JC lhdwr
         MOV L,C
         MVI B,0FFh
lheeb:   CALL ldiev
         LDA ldgqd
         ORI 003h
         CPI 0FFh
         JNZ lcwaz
         DCR B
         JNZ lheeb
         JMP lcwbi
lhbfd:   LDA ldgqd
         CMA
         ANI 0F7h
         RET
lcvtp:   CALL lhbfd
         CNZ ldlhl
         RET
         DB 000h
lcwaz:   MVI C,0FDh
lgozp:   INR C
         RRC
         JC lgozp
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
lgrtt:   STA lcjuk
         NOP
         NOP
         NOP
         JMP lcwbi
ldiev:   MVI A,082h
         STA lgzot
         RET
lhduj:   MVI A,091h
         STA lgzot
         RET
ldlhl:   PUSH B
         LDA lfqio
         CPI 080h
         JZ lhbfz
lcyun:   MVI C,0FFh
lgrtd:   CALL ldiev
         LDA ldgqd
         ORI 003h
         CPI 0FFh
         JNZ lcyun
         MVI B,015h
         CALL ldlfu
         DCR C
         JNZ lgrtd
lczeu:   POP B
         RET
lgsdk:   LHLD lbxjy
         PCHL
         LXI H,lnpsu
         SHLD lfqsk
         MVI C,021h
         CALL ldhue
         LXI H,lkahc
         SHLD lfqsk
         RET
         DB 000h
lhdue:   LDA lcjuk
         JMP lgozs
         DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
         DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
ldleu:   CALL ldkye
         JMP lgsdk
         DB 0FFh, 0FFh, 0FFh, 0FFh, 0FFh, 0FFh
ldhue:   PUSH H
         PUSH D
         PUSH B
         PUSH PSW
         MOV A,C
         LHLD lcjko
         XCHG
         JMP ldlfx
ldkye:   JMP lhatr
lgrts:   CALL lcwby
         CPI 081h
         JNC ldkyd
         CPI 021h
         JC ldkxo
         MOV C,A
         CPI 040h
         JNC lgosi
         LDA lcjuk
         ORA A
         MOV A,C
         JNZ ldkxo
         XRI 010h
ldkxo:   STA lcmve
         STA lfqio
         RET
lgosi:   CPI 07Fh
         JZ lhdue
         LDA lftji
lgozs:   ORA A
         MOV A,C
         JNZ ldkxo
         SUI 020h
         JMP ldkxo
lczex:   STA lfqio
         LDA lcmve
         RET
ldkyd:   CPI 081h
         JNZ lcwdn
         LDA lftji
         ORA A
         MVI A,001h
         JZ lgpah
         XRA A
lgpah:   STA lftji
         ORA A
         LXI H,0C2C8h
         PUSH H
         JZ lgrti
         JMP ldlhq
lcwdn:   CPI 08Ch
         JNZ ldhwx
         LXI H,lgwbe
lcvtr:   SHLD lgfjy
         JMP ldkye
lgsal:   LHLD lbxtu
         PCHL
lgozr:   PUSH B
         PUSH D
         PUSH H
         CALL lgsdk
         CALL ldleu
         NOP
         NOP
         POP H
         POP D
         POP B
         RET
lhbfz:   MVI C,010h
ldkyh:   MVI B,0FFh
         CALL ldlfu
         DCR C
         JNZ ldkyh
         JMP lczeu
         DB 0C5h, 006h, 015h, 0CDh, 090h, 0C1h, 0C1h, 0C3h, 087h, 0C2h
lhatr:   CALL ldlhl
         CALL lczcc
         CPI 080h
         JZ lczex
         JMP lgrts
ldhwx:   CPI 08Bh
         JNZ lgsal
         LXI H,ljyzh
         JMP lcvtr
lgozv:   PUSH B
         PUSH D
lcysd:   MVI C,000h
         MOV D,A
         LDA ldgqd
         ANI 001h
         MOV E,A
lhego:   MOV A,C
         ANI 07Fh
         RLC
         MOV C,A
lgpcs:   LDA ldgqd
         CPI 080h
         JC lhdvx
         ANI 001h
         CMP E
         JZ lgpcs
         ORA C
         MOV C,A
         CALL lcyve
         LDA ldgqd
         ANI 001h
         MOV E,A
         MOV A,D
         ORA A
         JP lhasw
         MOV A,C
         CPI 0E6h
         JNZ lhedm
         XRA A
         STA lcmli
         JMP ldhug
lhedm:   CPI 019h
         JNZ lhego
         MVI A,0FFh
         STA lftqs
ldhug:   MVI D,009h
lhasw:   DCR D
         JNZ lhego
         LDA lftqs
         XRA C
         POP D
         POP B
         RET
lcyve:   LDA lfqgc
ldhxm:   MOV B,A
         JMP ldlfu
         PUSH B
         PUSH D
         PUSH PSW
         MOV D,A
         MVI C,008h
lhbdm:   MOV A,D
         RLC
         MOV D,A
         ANI 001h
         ORI 00Eh
         STA lgzot
         MOV E,A
         CALL ldiiq
         MOV A,E
         XRI 001h
         STA lgzot
         CALL ldiiq
         DCR C
         JNZ lhbdm
         POP PSW
         POP D
         POP B
         RET
         DB 000h, 000h, 000h, 000h
lcysc:   MVI A,0FFh
         CALL lgozv
         MOV L,A
         MVI A,008h
         CALL lgozv
         MOV H,A
         SHLD lftte
         MVI A,008h
         CALL lgozv
         MOV E,A
         MVI A,008h
         CALL lgozv
         MOV D,A
ldigf:   MVI A,008h
         CALL lgozv
         MOV M,A
         CALL lgpdp
         INX H
         JNZ ldigf
         RET
         DB 03Eh, 0FFh, 0C3h, 016h, 0C4h
lgpdp:   MOV A,H
         CMP D
         RNZ
         MOV A,L
         CMP E
         RET
lcvvd:   MOV A,M
         STAX B
         INX H
         INX B
         CALL lgpdp
         JNZ lcvvd
         RET
ldkwn:   MOV A,M
         MOV C,A
         CPI 000h
         RZ
         CALL lgozx
         INX H
         JMP ldkwn
ldiiv:   LXI H,lgscb
         LXI D,ldlgy
         LXI B,lftte
         CALL lcvvd
         CALL ldkwn
         CALL lcysc
         LHLD lftte
         PCHL
lhdvx:   CALL lgozr
         CPI 00Dh
         JZ ldldh
         CPI 00Ah
         JZ lgotx
         STA lfqgc
         MVI A,0FFh
         JMP lcysd
lgotx:   LHLD lcauo
         PCHL

lgscb:   DB 000h, 0C0h, 0C8h, 0C2h, 0A0h, 018h, 000h, 000h, 000h, 000h
         DB 054h, 0C3h, 020h, 020h, 050h, 040h, 000h
ldiiq:   LDA lgcje
         JMP ldhxm
         DB 000h, 000h, 000h, 000h, 028h, 03Ch
ldlgy:   DB 01Fh, 02Ah, 020h, 052h, 055h, 04Eh, 022h, 043h, 04Fh, 04Dh
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