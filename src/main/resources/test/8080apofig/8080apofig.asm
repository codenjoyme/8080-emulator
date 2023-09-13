;******************************************************************************
;
; Created by Oleksandr Baglai to be able to work on LIK and assemble with
;     https://svofski.github.io/pretty-8080-assembler/
;     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)
;
;******************************************************************************

        .PROJECT 8080apofig.rks
        CPU     8080
; This is such a crutch in order to generate a valid rks file.
; This program will be launched from address 0004.
        .ORG    00000h
        DB      (begin & 0FFh), (begin / 0FFh)      ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) / 0FFh)  ; END ADDR IN MEMORY

begin:  JMP     start

bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800h     ; LIK MONITOR-1M

msg1:   DB      00Dh, 00Ah, '8080 INSTRUCTION TEST (KR580VM80A CPU) BY APOFIG', 00Dh, 00Ah, '$'
msg2:   DB      'TESTS COMPLETE$', 00Dh, 00Ah, '$'

start:
        LXI     H,msg1
        CALL    msg

done:   LXI     H,msg2
        CALL    msg
        JMP     wboot                ; warm boot

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

end: