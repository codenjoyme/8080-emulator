        .project hello_world.rks
        CPU     8080
; This is such a crutch in order to generate a valid rks file.
; This program will be launched from address 0004.
        .ORG    00000h
        DB      (start & 0FFh), (start / 0FFh)      ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) / 0FFh)  ; END ADDR IN MEMORY

start:  LXI     H, mssg
        CALL    msg
        MVI     A,0FFh
        CALL    byteo      ; SHOW EXIT CODE
        JMP     wboot      ; exit
;
mssg:   DB      00Dh, 00Ah, "HELLO WORLD", 00Dh, 00Ah, '$'
;
bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800h     ; LIK MONITOR-1M
;
;MESSAGE OUTPUT ROUTINE
;
msg:    MOV     A,M        ; Get data
        CPI     '$'        ; End?
        RZ
        CALL    pchar      ; Output
        INX     H          ; Next
        JMP     msg        ; Do all
;
;CHARACTER OUTPUT ROUTINE
;
pchar:  PUSH    PSW
        PUSH    D
        PUSH    H
        MOV     C,M
        CALL    bdos
        POP     H
        POP     D
        POP     PSW
        RET
;
;HEX BYTE OUTPUT ROUTINE
;
byteo:  PUSH    PSW
        CALL    byto1
        MOV     M,A
        CALL    pchar
        POP     PSW
        CALL    byto2
        MOV     M,A
        JMP     pchar
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