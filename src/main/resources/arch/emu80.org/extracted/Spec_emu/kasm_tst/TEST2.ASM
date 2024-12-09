;;	Here are some lines with some errors in them.
;;	They were written to debug error messages.

label1:
la^bel:			; '^' is not a valid character for a label.
label1:			; This label has already been defined.
	burp		; There is no such opcode.
	nop	you	; NOP does not require any arguments.
	inr	z	; There is no such register.
	rst	9	; Valid values are 0..7
	mvi	a,zero	; 'zero' has not been defined.
	db	'a' '0'	; The comma between 'a' and '0' is missing.
	mov	m,m	; There is no such operation.
	lxi	h,labl1	; There is no such label.
	lxi	b,'AAA'	; The character constant is too long.
	equ	1000	; EQU needs a label.
	db	"...	; Not a valid string.
	org	1000h	; ORG must be at the start.
	mvi	a,1000	; 1000 does not fit into a byte.
			; A warning will be generated.

; ***** This is a rather long line which exceeds the size of the read buffer (128 bytes, in fact) and therefore will be truncated.

end
