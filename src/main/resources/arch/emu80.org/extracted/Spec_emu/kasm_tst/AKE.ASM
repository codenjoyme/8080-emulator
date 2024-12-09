-------------------------- SNAKE.ASM ---------------------------
	mvi  b,6			; Snake building
	lxi  h, 9000h
cop:	lxi  d, table
	mvi  c,8
   co2: ldax d
	mov  m,a
	inx  d
	inx  h
	dcr c
	jnz co2
	dcr b
	jnz cop


	lxi  h, 9000h			; Snake moving
	lxi  d, 9030h
  loop: ldax d  
	mov  b,m
	mov  m,a
	mov  a,b
	stax d
	call delay 
	inx  h
	inx  d
	mov  a,d
	cpi  0c0h
	jnz  more
	mvi  d,90h
	jmp  loop
  more: mov  a,h
	cpi  0c0h
	jnz  loop
	mvi  h,90h
	jmp  loop 

table:   db   10h,3Ch,42h,42h,42h,42h,3Ch,8	; Snake body

delay:  push b					; Snake waiting
	lxi  b,250
loo:	dcx  b
	mov  a,b
	ora  c
	jnz  loo
	pop  b
	ret 
----------------------------------------------------------------