        .project hello_world.rks
        CPU     8080
        .ORG    00000h
        DB      004h, 000h    ; RKS HEADER/START MEMORY
        DB      032h, 000h    ; RKS HEADER/END MEMORY
        
        LXI     H, string
        CALL    msg
        JMP     0C800h        ; LIK MONITOR-1M

;
string: DB      00Dh, 00Ah, "HELLO WORLD", 00Dh, 00Ah, '$'

;
bdos    EQU     0C037h        ; LIK PRINT CHAR PROCEDURE

;
;MESSAGE OUTPUT ROUTINE
;
msg:    MOV     A,M           ; Get data
        CPI     '$'           ; End?
        RZ
        CALL    pchar         ; Output
        INX     H             ; Next
        JMP     msg           ; Do all
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