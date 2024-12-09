;;		This is a K580 assembler program
;;		used to test the KASM translator.

;;	All the commands below should be OK.
;;	They cover all argument types.

	org	1000h

var1:	equ	2000

label1:
	nop		; no args
	add	c	; RM8_1
	inr     c	; RM8_2
	adi	-10	; DATA8
	rst	7	; 0..7
	dad	b	; R16_1 (B, D, H, SP)
	push	psw	; R16_2 (B, D, H, PSW)
label2:
	lda	1000	; ADR16
	mov	a,m	; RM8_RM8
	mvi	a,10	; RM8_IMM
	lxi	h,1000	; R16_IMM (decimal)
	lxi	h,1000b	;   ---   (binary)
	lxi	h,0FFFFh;   ---   (hexadecimal)
	lxi	h,'MZ'	;   ---   (character)
	lxi	h, $	;   ---   (the $ symbol)
	lxi	h, var1	;   ---   (EQU definition)
	ldax	d	; RADR
	jmp	label2	; a thoughtful comment


data1:	db	0, 'A', "aaa"	; 00 41 61 61 61
data2:	dw	0FFFFh, 'zz'	; FF FF 7A 7A
data3:	ds	10		; 10 undefined bytes

end