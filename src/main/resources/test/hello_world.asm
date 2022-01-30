;******************************************************************************
; Prints 'HELLO WORLD\nEE'
;
; Adapted by Oleksandr Baglai to be able to work on LIK and assemble with
;     https://svofski.github.io/pretty-8080-assembler/
;     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)
;
;******************************************************************************

        .project hello_world.rks
        CPU     8080
; This is such a crutch in order to generate a valid rks file.
; This program will be launched from address 0004.
        .ORG    00000h
        DB      (start & 0FFh), (start / 0FFh)      ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) / 0FFh)  ; END ADDR IN MEMORY

start:  LXI     H, mssg
        CALL    msg
        MVI     A,0FEh
        CALL    byteo      ; SHOW EXIT CODE
        JMP     wboot      ; exit
;
mssg:   DB      00Dh, 00Ah, 'HELLO WORLD', 00Dh, 00Ah, '$'
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
        MOV     A,M        ; Get data
        CPI     '$'        ; End?
        JZ      msge       ; Exit
        MOV     A,M
        CALL    pchar      ; Output
        INX     H          ; Next
        JMP     msg        ; Do all
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