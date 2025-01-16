	org	00h
lowbrd:	equ	14h
upbrd:	equ	17h
left:	equ	8
right:	equ	18h
up:	equ	19h
down:	equ	1AH

screen:	equ	9000h
glk:	equ	9010h

key:	equ	0C81Bh
getkey:	equ	0C803h
outchr:	equ	0C809h
print:	equ	0C818h
byte:	equ	0C821h

start:	lxi	h, msg1
	call	print
m10:	call	getkey
	mov	c,a
	sui	30h
	jc	m10
	cpi	10
	jnc	m10
	call	outchr
	add	a
	mov	b,a
	add	a
	add	b
	cma
	inr	a
	mov	b,a
	mvi	c,0FFh
	lxi	h,4000h
	dad	b
	shld	pause
start1:
	lxi	h,msg3
	call	print
rndloop:
	call	rnd
	call	key
	cpi	0Dh
	jnz	rndloop

	mvi	c,1Fh
	call	outchr
	lxi	h,screen
	push	h
	lxi	b,1
	mvi	e,63
	call	border
	push	h
	lxi	h,9750h
	mvi	e,63
	call	border
	pop	h
	mvi	c,78
	mvi	e,25
	call	border
	pop	h
	mvi	e,25
	call	border
	lxi	h,msg2
	call	print
	lxi	h, 9720h
	lxi	b,-78
	push	h
	push	b
	lhld	head
	shld	tail
	jmp	m7

loop:
	call	key
	lxi	h,-1
	cpi	left
	jz	m0
	lxi	h,1
	cpi	right
	jz	m0
	lxi	h,-78
	cpi	up
	jz	m0
	lxi	h,78
	cpi	down
	jz	m0
	xthl
m0:
	pop	b
	pop	d
	xchg
	dad	d
	push	h
	push	d
	mov	c,m
	mvi	m,'o'
	xchg
	lhld	head
	mov	m,e
	inx	h
	mov	m,d
	inx	h
	mov	a,h
	cpi	upbrd
	jnz	no
	mvi	h,lowbrd
no:	shld	head
	lhld	pause
m6:	dcx	h
	mov	a,l
	ora	h
	jnz	m6
	mov	a,c
	ora	a
	jz	m3
	cpi	7Fh
	jnz	start1
m7:	lda	length
	sta	growth
m1:	lxi	h,msg1
	call	print
	call	rnd
	mov	c,h
	call	outchr
	mov	c,l
	call	outchr
	mov	a,m
	ora	a
	jnz	m1
	mvi	c,7Fh
	call	outchr
m3:	lxi	h,growth
	dcr	m
	jm	m5
	lxi	h,glk
	mvi	a,3Ah
m4:	inr	m
	cmp	m
	jnz	loop
	mvi	m,30h
	dcx	h
	jmp	m4
m5:	inr	m
	lhld	tail
	mov	e,m
	inx	h
	mov	d,m
	inx	h
	mov	a,h
	cpi	upbrd
	jnz	no2
	mvi	h,lowbrd
no2:	shld	tail
	xra	a
	stax	d
	jmp	loop

border:
	mvi	m,'+'
	dad	b
	dcr	e
	jnz	border
	ret

rnd:
	lhld	randval
	mvi	c,16
rnd0:	mov	a,h
	dad	h
	ani	60h
	jpe	rnd1
	inx	h
rnd1:	dcr	c
	jnz	rnd0
	shld	randval
	ret

msg0:	db	0Dh, 0Ah
	db	"*** SNAKE ***"
	db	0Dh, 0Ah
	db	"Speed (0..9)? ",0

msg1:	db	7,1Bh,'Y',0

msg2:	db	7,1Bh,'Y',20h,3Ah
	db	"Length = 000",0

msg3:	db	1Bh, 'Y', 56, 57
	db	"Press ENTER"
	db	0

length:	db	7
randval:db	1
head:	dw	1400h
tail:	ds	2
growth:	ds	1
pause:	ds	2

end
	
	

