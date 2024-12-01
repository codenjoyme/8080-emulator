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
        DB      (begin & 0FFh), (begin >> 8)          ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) >> 8)  ; END ADDR IN MEMORY

begin:  JMP     start

bdos    EQU     0C037h     ; LIK PRINT CHAR PROCEDURE
wboot:  JMP     0C800h     ; LIK MONITOR-1M

msg1:   DB      00Dh, 00Ah, '8080 INSTRUCTION TEST (KR580VM80A CPU) BY APOFIG', 00Dh, 00Ah, '$'
msg2:   DB      00Dh, 00Ah, 'TESTS COMPLETE$', 00Dh, 00Ah, '$'

; Программа работает таким образом, чтобы после выполнения каждой отдельной операции состояние
; всех регистров записывается в определенной области памяти. Затем из этой области памяти
; мы получаем контрольную сумму, которую сравниваем с заведомо просчитанными суммами на реальной машине.
; Каждая команда выполняется опеределенное количество раз над все новыми данными. После ее выполнения
; методом XOR SHIFT мы рендомизируем значение всех регистров, так из большого количества итераций надо одной
; командой мы можем рассчитывать на ее хороший тест каверадж. Ну классная идея жеж!

start:
        LXI     H,msg1
        CALL    msg

        CALL    test_command

done:   LXI     H,msg2
        CALL    msg
        JMP     wboot                ; warm boot

; тут мы будем хранить XOR всех регистров после выполнения процедуры calc_hash
xor_data: DB    00

test_command:
        ; Очистка регистра аккумулятора и PSW
        XRA     A
        ; Очистка регистров BC, DE и HL с помощью LXI
        LXI     B, 0000H
        LXI     D, 0000H
        LXI     H, 0000H

        ORI     0b0111_0111
        CALL    calc_hash
        CALL    byteo

        RAL
        CALL    calc_hash
        CALL    byteo

        XRI     0b1110_1000
        CALL    calc_hash
        CALL    byteo

        RLC
        CALL    calc_hash
        CALL    byteo

        ANI     0b1101_1110
        CALL    calc_hash
        CALL    byteo

        RRC
        CALL    calc_hash
        CALL    byteo

        ORI     0b0111_1001
        CALL    calc_hash
        CALL    byteo

        RAR
        CALL    calc_hash
        CALL    byteo

        XRI     0b1010_1100
        CALL    calc_hash
        CALL    byteo

        RAL
        CALL    calc_hash
        CALL    byteo

        ANI     0b1011_1010
        CALL    calc_hash
        CALL    byteo

        RLC
        CALL    calc_hash
        CALL    byteo

        ORI     0b0111_0101
        CALL    calc_hash
        CALL    byteo

        RRC
        CALL    calc_hash
        CALL    byteo

        XRI     0b1110_1010
        CALL    calc_hash
        CALL    byteo

        RAR
        CALL    calc_hash
        CALL    byteo

        ANI     0b1011_1110
        CALL    calc_hash
        CALL    byteo

        RAL
        CALL    calc_hash
        CALL    byteo

        RET

calc_hash:
; сохраняем все регистры в стек
        PUSH B
        PUSH D
        PUSH H
        PUSH PSW

; записываем адрес вершины стека в HL <= SP
        LXI H,0000
        DAD SP

; перемещаемся по стеку в место, где хранятся наши регистры
        MVI C,08      ; Установить значение регистра C равным 8
dcx_loop1:
        DCX H         ; Уменьшить значение регистра H и L на 1
        DCR C         ; Уменьшить значение регистра C на 1
        JNZ dcx_loop1 ; Повторять, пока значение регистра C не станет равным 0
; теперь (HL) указывает на область памяти содержащий начало цепочки: B C D E H L A PSW
; загрузим A из другой части памяти, где накапливаем результат
	LXI D,xor_data ; в DE помещаем адрес xor_data хранилища
	LDAX D        ; загружаем в аккумулятор A=(DE) значение этого хранилища
; мы пройдемся по всей цепочке
   	MVI C,08      ; Установить значение регистра C равным 8
dcx_loop2:
	MOV B,M       ; сохраним значение памяти в B
	XRA B         ; A = A <XOR> B - накапливаем в A энтропию
	INX H         ; Увеличить значение регистра H и L на 1
        DCR C         ; Уменьшить значение регистра C на 1
        JNZ dcx_loop2 ; Повторять, пока значение регистра C не станет равным 0
; сохраним A в области памяти (DE) для будущих накоплений
	STAX D
; загружаем все регистры из стека
        POP B
        POP D
        POP H
        POP PSW
; чтобы продолжить как ни в чем небывало
	RET

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