        .project hello_world.rks
        CPU     8080
        .ORG    0000h
        DB      04h, 00h    ; RKS HEADER/START MEMORY 
        DB      32h, 00h    ; RKS HEADER/END MEMORY
        
        LXI     H, STRING
        CALL    MSG
        JMP     0C800h      ; LIK MONITOR-1M

;
STRING: DB      0dh, 0ah, "HELLO WORLD", 0dh, 0ah, '$'

;
BDOS    EQU     0C037H  ;LIK PRINT CHAR PROCEDURE

;
;MESSAGE OUTPUT ROUTINE
;
MSG:    MOV     A,M     ; Get data
        CPI     '$'     ; End?
        RZ
        CALL    PCHAR   ; Output
        INX     H       ; Next
        JMP     MSG     ; Do all
;
;
;CHARACTER OUTPUT ROUTINE
;
PCHAR:  PUSH    PSW
        PUSH    D
        PUSH    H
        MOV     C,M
        CALL    BDOS
        POP     H
        POP     D
        POP     PSW
        RET