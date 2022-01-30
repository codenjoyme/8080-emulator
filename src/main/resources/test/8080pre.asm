;
; Preliminary i8080 tests
; Copyright (C) 1994 Frank D. Cringle
;
; This program is free software; you can redistribute it and/or
; modify it under the terms of the GNU General Public License
; as published by the Free Software Foundation; either version 2
; of the License, or (at your option) any later version.
;
; This program is distributed in the hope that it will be useful,
; but WITHOUT ANY WARRANTY; without even the implied warranty of
; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
; GNU General Public License for more details.
;
; You should have received a copy of the GNU General Public License
; along with this program; if not, write to the Free Software
; Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
;
; These tests have two goals.  To start with, we assume the worst and
; successively test the instructions needed to continue testing.
; Then we try to test all instructions which cannot be handled by
; zexlax - the crc-based instruction exerciser.
;
; Initially errors are 'reported' by jumping to 0.  This should reboot
; cp/m, so if the program terminates without any output one of the
; early tests failed.  Later errors are reported by outputting an
; address via the bdos conout routine.  The address can be located in
; a listing of this program.
;
; If the program runs to completion it displays a suitable message.

;******************************************************************************
;
; Modified by Ian Bartholomew to run a preliminary test on an 8080 CPU
;
; Modified by Oleksandr Baglai to be able to work on LIK and assemble with
;     https://svofski.github.io/pretty-8080-assembler/
;     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)
;
;******************************************************************************

        .PROJECT 8080pre.rks
        CPU     8080
; This is such a crutch in order to generate a valid rks file.
; This program will be launched from address 0004.
        .ORG    00000h
        DB      (start & 0FFh), (start / 0FFh)          ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) / 0FFh)  ; END ADDR IN MEMORY

start:  LXI     H, mssg
        CALL    msg
        JMP     test       ; start test
;
mssg:   DB      00Dh, 00Ah, '8080 PRELIMINARY TESTS'
        DB      00Dh, 00Ah, 'COPYRIGHT (C) 1994 FRANK D. CRINGLE', 00Dh, 00Ah, '$'
;
bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800h     ; LIK MONITOR-1M
;
;MESSAGE OUTPUT ROUTINE
;
msg:    PUSH    B          ; Push state
        PUSH    D
        PUSH    H
        PUSH    PSW
msgs:   MOV     A,M        ; Get data
        CPI     '$'        ; End?
        JZ      msge       ; Exit
        MOV     A,M
        CALL    pchar      ; Output
        INX     H          ; Next
        JMP     msgs       ; Do all
msge:   POP     PSW        ; Pop state
        POP     H
        POP     D
        POP     B
        RET
;
;CHARACTER OUTPUT ROUTINE
;
pchar:  PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        MOV     C,A
        CALL    bdos
        POP     PSW
        POP     H
        POP     D
        POP     B
        RET
;
;HEX BYTE OUTPUT ROUTINE
;
byteo:  PUSH    B
        PUSH    D
        PUSH    H
        PUSH    PSW
        PUSH    PSW
        CALL    byto1
        CALL    pchar
        POP     PSW
        CALL    byto2
        CALL    pchar
        POP     PSW
        POP     H
        POP     D
        POP     B
        RET
byto1:  RRC
        RRC
        RRC
        RRC
byto2:  ANI     00Fh
        CPI     00Ah
        JM      byto3
        ADI     7
byto3:  ADI     030h
        RET
;
; Start testing
;
test:   MVI     a,1        ; test simple compares and z/nz jumps
        CPI     2
        JZ      fail1
        CPI     1
        JNZ     fail1
        JMP     lab0
        HLT                ; emergency exit
        DB      0FFh
        JMP     lab0
fail1:  CALL    error

lab0:   CALL    lab2       ; does a simple call work?

lab1:   JMP     error      ; fail

lab2:   POP     h          ; check return address
        MOV     A,H
        CPI     (lab1 / 0FFh)
        JZ      lab3
        CALL    error
lab3:   MOV     A,L
        CPI     (lab1 & 0FFh)
        JZ      lab4
        CALL    error

; test presence and uniqueness of all machine registers
; (except ir)
lab4:   LXI     SP,regs1
        POP     PSW
        POP     B
        POP     D
        POP     H
        LXI     SP,regs2+8
        PUSH    H
        PUSH    D
        PUSH    B
        PUSH    PSW

        LDA     regs2 + 0/2
        CPI     2
        JNZ     fail2
        LDA     regs2 + 2/2
        CPI     4
        JNZ     fail2
        LDA     regs2 + 4/2
        CPI     6
        JNZ     fail2
        LDA     regs2 + 6/2
        CPI     8
        JNZ     fail2
        LDA     regs2 + 8/2
        CPI     10
        JNZ     fail2
        LDA     regs2 + 10/2
        CPI     12
        JNZ     fail2
        LDA     regs2 + 12/2
        CPI     14
        JNZ     fail2
        LDA     regs2 + 14/2
        CPI     16
        JNZ     fail2
        JMP     lab5
fail2:  CALL    error

; test access to memory via (hl)
lab5:   LXI     H,hlval
        MOV     A,M
        CPI     0A5h
        JNZ     fail3
        LXI     H,hlval+1
        MOV     A,M
        CPI     03Ch
        JNZ     fail3
        JMP     lab6
fail3:  CALL    error

; test unconditional return
lab6:   LXI     SP,stack
        LXI     H,lab6_1
        PUSH    H
        RET
        CALL    error

; test instructions needed for hex output
lab6_1: MVI     A,0FFh
        ANI     0Fh
        CPI     0Fh
        JNZ     fail4
        MVI     A,05Ah
        ANI     0Fh
        CPI     0Ah
        JNZ     fail4
        RRC
        CPI     05h
        JNZ     fail4
        RRC
        CPI     82h
        JNZ     fail4
        RRC
        CPI     41h
        JNZ     fail4
        RRC
        CPI     0A0h
        JNZ     fail4
        LXI     H,01234h
        PUSH    H
        POP     B
        MOV     A,B
        CPI     12h
        JNZ     fail4
        MOV     A,C
        CPI     34h
        JNZ     fail4
        JMP     lab0_c
fail4:  CALL    error

; from now on we can report errors by displaying an address

; test conditional call, ret, jp, jr
; commands CC, CNC, JC, JNC, RC, RNC
lab0_c: LXI     H,001h            ; FLAG
        PUSH    H
        POP     PSW
        CC      lab1_c
        CALL    error
lab1_c: POP     H
        LXI     H,0D7h ^ 001h     ; FLAG
        PUSH    H
        POP     PSW
        CNC     lab2_c
        CALL    error
lab2_c: POP     H
        LXI     H,lab3_c
        PUSH    H
        LXI     H,001h            ; FLAG
        PUSH    H
        POP     PSW
        RC
        CALL    error
lab3_c: LXI     H,lab4_c
        PUSH    H
        LXI     H,0D7h ^ 001h     ; FLAG
        PUSH    H
        POP     PSW
        RNC
        CALL    error
lab4_c: LXI     H,001h            ; FLAG
        PUSH    H
        POP     PSW
        JC      lab5_c
        CALL    error
lab5_c: LXI     H,0D7h ^ 001h     ; FLAG
        PUSH    H
        POP     PSW
        JNC     lab0_p
        CALL    error

; commands CPE, CPO, JPE, JPO, RPE, RPO
lab0_p: LXI     H,004h            ; FLAG
        PUSH    H
        POP     PSW
        CPE     lab1_p
        CALL    error
lab1_p: POP     H
        LXI     H,0D7h ^ 004h     ; FLAG
        PUSH    H
        POP     PSW
        CPO     lab2_p
        CALL    error
lab2_p: POP     H
        LXI     H,lab3_p
        PUSH    H
        LXI     H,004h            ; FLAG
        PUSH    H
        POP     PSW
        RPE
        CALL    error
lab3_p: LXI     H,lab4_p
        PUSH    H
        LXI     H,0D7h ^ 004h     ; FLAG
        PUSH    H
        POP     PSW
        RPO
        CALL    error
lab4_p: LXI     H,004h            ; FLAG
        PUSH    H
        POP     PSW
        JPE     lab5_p
        CALL    error
lab5_p: LXI     H,0D7h ^ 004h     ; FLAG
        PUSH    H
        POP     PSW
        JPO     lab0_z
        CALL    error

; commands CZ, CNZ, JZ, JNZ, RZ, RNZ
lab0_z: LXI     H,040h            ; FLAG
        PUSH    H
        POP     PSW
        CZ      lab1_z
        CALL    error
lab1_z: POP     H
        LXI     H,0D7h ^ 040h     ; FLAG
        PUSH    H
        POP     PSW
        CNZ     lab2_z
        CALL    error
lab2_z: POP     H
        LXI     H,lab3_z
        PUSH    H
        LXI     H,040h            ; FLAG
        PUSH    H
        POP     PSW
        RZ
        CALL    error
lab3_z: LXI     H,lab4_z
        PUSH    H
        LXI     H,0D7h ^ 040h     ; FLAG
        PUSH    H
        POP     PSW
        RNZ
        CALL    error
lab4_z: LXI     H,040h            ; FLAG
        PUSH    H
        POP     PSW
        JZ      lab5_z
        CALL    error
lab5_z: LXI     H,0D7h ^ 040h     ; FLAG
        PUSH    H
        POP     PSW
        JNZ     lab0_h
        CALL    error

; commands CM, CP, JM, JP, RM, RP
lab0_h: LXI     H,080h            ; FLAG
        PUSH    H
        POP     PSW
        CM      lab1_h
        CALL    error
lab1_h: POP     H
        LXI     H,0D7h ^ 080h     ; FLAG
        PUSH    H
        POP     PSW
        CP      lab2_h
        CALL    error
lab2_h: POP     H
        LXI     H,lab3_h
        PUSH    H
        LXI     H,080h            ; FLAG
        PUSH    H
        POP     PSW
        RM
        CALL    error
lab3_h: LXI     H,lab4_h
        PUSH    H
        LXI     H,0D7h ^ 080h     ; FLAG
        PUSH    H
        POP     PSW
        RP
        CALL    error
lab4_h: LXI     H,080h            ; FLAG
        PUSH    H
        POP     PSW
        JM      lab5_h
        CALL    error
lab5_h: LXI     H,0D7h ^ 080h     ; FLAG
        PUSH    H
        POP     PSW
        JP      lab6_2
        CALL    error

; test indirect jumps
lab6_2: LXI     H,lab7
        PCHL
        CALL    error

; djnz (and (partially) inc a, inc hl)
lab7:   MVI    A,0A5h
        MVI    B,4
lab8:   RRC
        DCR    B
        JNZ    lab8
        CPI    05Ah
        CNZ    error
        MVI    B,16
lab9:   INR    A
        DCR    B
        JNZ    lab9
        CPI    06Ah
        CNZ    error
        MVI    B,0
        LXI    H,0
lab10:  INX    H
        DCR    B
        JNZ    lab10
        MOV    A,H
        CPI    1
        CNZ    error
        MOV    A,l
        CPI    0
        CNZ    error

allok:  LXI    H,okmsg
        CALL   msg
        JMP    wboot

okmsg:  DB    00Dh, 00Ah, 'TESTS SUCCESSFUL', '$'
errmsg: DB    00Dh, 00Ah, 'CPU HAS FAILED!', 00Dh, 00Ah, 'ERROR ADDR=', '$'

; display address at top of stack and exit
error:  LXI     H,errmsg  ;OUTPUT ERROR MESSAGE TO CONSOLE
        CALL    msg
        XTHL
        DCX     H
        DCX     H
        DCX     H
        MOV     A,H
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS HIGH BYTE
        MOV     A,L
        CALL    byteo    ;SHOW ERROR EXIT ADDRESS LOW BYTE
        JMP     wboot    ;EXIT TO CP/M WARM BOOT

conout: PUSH   PSW
        PUSH   B
        PUSH   D
        PUSH   H
        CALL   byteo
        POP    H
        POP    D
        POP    B
        POP    PSW
        RET

regs1:  DB     002h, 004h, 006h, 008h, 00Ah, 00Ch, 00Eh, 010h

regs2:  DS     0,8

hlval:  DB     0A5h,03Ch
        DS     300

stack   EQU    $

end: