        .project hello_world.rks
        CPU     8080
        .ORG    0000h
        DB      04h, 00h    ; RKS HEADER/START MEMORY 
        DB      32h, 00h    ; RKS HEADER/END MEMORY
        
        LXI     H, string
        CALL    msg
        JMP     0C800h      ; LIK MONITOR-1M

;
string: DB      0dh, 0ah, "HELLO WORLD", 0dh, 0ah, '$'

;
bdos    EQU     0C037H  ;LIK PRINT CHAR PROCEDURE

;
;MESSAGE OUTPUT ROUTINE
;
msg:    MOV     A,M     ; Get data
        CPI     '$'     ; End?
        RZ
        CALL    pchar   ; Output
        INX     H       ; Next
        JMP     msg     ; Do all
;
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