; zexlax.z80 - Z80 instruction set exerciser
; Copyright (C) 1994  Frank D. Cringle
;
; This program is free software; you can redistribute it and/or
; modify it under the terms of the GNU General Public License
; as published by the Free Software Foundation; either version 2
; of the License, or (at your option) any later version.
;
; This program is distributed in the hope that it will be useful,
; but WITHOUT ANY WARRANTY; without even the implied warranty of
; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
; GNU General Public License for more details.
;
; You should have received a copy of the GNU General Public License
; along with this program; if not, write to the Free Software
; Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
;
;******************************************************************************
;
; Modified to exercise an 8080 by Ian Bartholomew, February 2009
;
; CRC values for a KR580VM80A CPU
;
; I have made the following changes -
;
; Converted all mnemonics to 8080 and rewritten any Z80 code used
; in the original exerciser.  Changes are tagged with a #idb in the
; source code listing.
;
; Removed any test descriptors that are not used.
;
; Changed the macro definitions to work in M80
;
; The machine state snapshot has been changed to remove the IX/IY registers.
; They have been replaced by two more copies of HL to obviate the need
; for major changes in the exerciser code.
;
; Changed flag mask in all tests to 0FFh to reflect that the 8080, unlike the 8085
; and Z80, does define the unused bits in the flag register - [S Z 0 AC 0 P 1 C]
;
;******************************************************************************
;
; Modified by Oleksandr Baglai to be able to work on LIK and assemble with
;     https://svofski.github.io/pretty-8080-assembler/
;     https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%BA_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80)
;
;******************************************************************************

        .PROJECT 8080ex1.rks
        CPU     8080
; This is such a crutch in order to generate a valid rks file.
; This program will be launched from address 0004.
        .ORG    00000h
        DB      (begin & 0FFh), (begin >> 8)          ; START ADDR IN MEMORY
        DB      ((end - 1) & 0FFh), ((end - 1) >> 8)  ; END ADDR IN MEMORY

begin:    JMP     start

; machine state before test (needs to be at predictably constant address)
msbt:     DS      14
spbt:     DS      2

; For the purposes of this test program, the machine state consists of:
;        a 2 byte memory operand, followed by
;        the registers --,--,HL,DE,BC,AF(flags,acc),SP
; for a total of 16 bytes.

; The program tests instructions (or groups of similar instructions)
; by cycling through a sequence of machine states, executing the test
; instruction for each one and running a 32-bit crc over the resulting
; machine states.  At the end of the sequence the crc is compared to
; an expected value that was found empirically on a real Z80.

; A test case is defined by a descriptor which consists of:
;        a flag mask byte,
;        the base case,
;        the increment vector,
;        the shift vector,
;        the expected crc,
;        a short descriptive message.
;
; The flag mask byte is used to prevent undefined flag bits from
; influencing the results.  Documented flags are as per Mostek Z80
; Technical Manual.
;
; The next three parts of the descriptor are 20 byte vectors
; corresponding to a 4 byte instruction and a 16 byte machine state.
; The first part is the base case, which is the first test case of
; the sequence.  This base is then modified according to the next 2
; vectors.  Each 1 bit in the increment vector specifies a bit to be
; cycled in the form of a binary counter.  For instance, if the byte
; corresponding to the accumulator is set to 0FFh in the increment
; vector, the test will be repeated for all 256 values of the
; accumulator.  Note that 1 bits don't have to be contiguous.  The
; number of test cases 'caused' by the increment vector is equal to
; 2^(number of 1 bits).  The shift vector is similar, but specifies a
; set of bits in the test case that are to be successively inverted.
; Thus the shift vector 'causes' a number of test cases equal to the
; number of 1 bits in it.

; The total number of test cases is the product of those caused by the
; counter and shift vectors and can easily become unweildy.  Each
; individual test case can take a few milliseconds to execute, due to
; the overhead of test setup and crc calculation, so test design is a
; compromise between coverage and execution time.

; This program is designed to detect differences between
; implementations and is not ideal for diagnosing the causes of any
; discrepancies.  However, provided a reference implementation (or
; real system) is available, a failing test case can be isolated by
; hand using a binary search of the test space.


start:
;!        LHLD    6
;!        SPHL
          LXI     D,msg1
          CALL    msg

          LXI     H,tests              ; first test case
loop:     MOV     A,M                  ;  end of list ?
          INX     H
          ORA     M
          JZ      done
          DCX     H
          CALL    stt
          JMP     loop

done:     LXI     D,msg2
          CALL    msg
          JMP     wboot                ; warm boot

tests:
          DW      add16
          DW      alu8i
          DW      alu8r
          DW      daa
          DW      inca
          DW      incb
          DW      incbc
          DW      incc
          DW      incd
          DW      incde
          DW      ince
          DW      inch
          DW      inchl
          DW      incl
          DW      incm
          DW      incsp
          DW      ld162
          DW      ld166
          DW      ld16im
          DW      ld8bd
          DW      ld8im
          DW      ld8rr
          DW      lda
          DW      rot8080
          DW      stabd
          DW      0

; DAD <B,D,H,SP> (19,456 cycles)
add16:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      09h,0,0,0                                    ; insn
          DW      0C4A5h,0C4C7h,0D226h,0A050h,058EAh,08566h    ; memop,--,--,HL,DE,BC
          DB      0C6h,0DEh                                    ; flags,acc
          DW      09BC9h                                       ; SP

                                                               ; second (512 cycles)
          DB      030h,0,0,0                                   ; insn
          DW      0,0,0,0,0F821h,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0000h                                        ; SP

                                                               ; third (38 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,-1,-1,-1                               ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      -1                                           ; SP

;crc      DB      014h, 047h, 04Bh, 0A6h                       ; original expected crc
          DB      048h, 08Bh, 07Fh, 0DAh                       ; current expected crc from emu80
          DB      'DAD <B,D,H,SP>...............$'

; ALUOP NN (28,672 cycles)
alu8i:    DB      0FFH                                         ; flag mask

                                                               ; first
          DB      0C6h,0,0,0                                   ; insn
          DW      09140h,07E3Ch,07A67h,0DF6Dh,05B61h,00B29h    ; memop,--,--,HL,DE,BC
          DB      010h,066h                                    ; flags,acc
          DW      085B2h                                       ; SP

                                                               ; second (2048 cycles)
          DB      038h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

                                                               ; third (14 cycles)
          DB      0,-1,0,0                                     ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      09Eh, 092h, 02Fh, 09Eh                       ; expected crc
          DB      'ALUOP NN.....................$'

; ALUOP <B,C,D,E,H,L,M,A> (753,664 cycles)
alu8r:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      080h,0,0,0                                   ; insn
          DW      0c53Eh,0573Ah,04c4Dh,msbt,0e309h,0a666h      ; memop,--,--,HL,DE,BC
          DB      0d0h,03bh                                    ; flags,acc
          DW      0adbbh                                       ; SP

                                                               ; second (16,384 cycles)
          DB      03Fh,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

                                                               ; third (46 cycles)
          DB      0,0,0,0                                      ; insn
          DW      -1,0,0,0,-1,-1                               ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

;crc      DB      0cFh, 076h, 02Ch, 086h                       ; original expected crc
          DB      075h, 053h, 0A0h, 026h                       ; current expected crc from emu80
          DB      'ALUOP <B,C,D,E,H,L,M,A>......$'

; <DAA,CMA,STC,CMC>
daa:      DB      0FFh                                         ; flag mask

                                                               ; first
          DB      027h,0,0,0                                   ; insn
          DW      02141h,009fAh,01d60h,0a559h,08d5Bh,09079h    ; memop,--,--,HL,DE,BC
          DB      004h,08eh                                    ; flags,acc
          DW      0299dh                                       ; SP

                                                               ; second (65,536 cycles)
          DB      018h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,-1                                      ; flags,acc
          DW      0                                            ; SP

                                                               ; third (1 cycle)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

          DB      0bBh,03Fh,003h,00ch                          ; expected crc
          DB      '<DAA,CMA,STC,CMC>............$'

; <INR,DCR> A (3072 cycles)
inca:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      03Ch,0,0,0                                   ; insn
          DW      04adFh,0d5d8h,0e598h,08a2Bh,0a7b0h,0431bh    ; memop,--,--,HL,DE,BC
          DB      044h,05ah                                    ; flags,acc
          DW      0d030h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0aDh,0b6h,046h,00eh                          ; expected crc
          DB      '<INR,DCR> A..................$'

; <INR,DCR> B (3072 cycles)
incb:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      004h,0,0,0                                   ; insn
          DW      0d623h,0432Dh,07a61h,08180h,05a86h,01e85h    ; memop,--,--,HL,DE,BC
          DB      086h,058h                                    ; flags,acc
          DW      09bbbh                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0ff00h                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,hl,dе,bc
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      083h,0eDh,013h,045h                          ; expected crc
          DB      '<INR,DCR> B..................$'

; <INX,DCX> B (1536 cycles)
incbc:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      003h,0,0,0                                   ; insn
          DW      0cd97h,044aBh,08dc9h,0e3e3h,011cCh,0e8a4h    ; memop,--,--,HL,DE,BC
          DB      002h,049h                                    ; flags,acc
          DW      02a4dh                                       ; SP

                                                               ; second (256 cycles)
          DB      008h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0f821h                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0f7h,092h,087h,0cdh                          ; expected crc
          DB      '<INX,DCX> B..................$'

; <INR,DCR> C (3072 cycles)
incc:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      00Ch,0,0,0                                   ; insn
          DW      0d789h,00935h,0055Bh,09f85h,08b27h,0d208h    ; memop,--,--,HL,DE,BC
          DB      095h,005h                                    ; flags,acc
          DW      00660h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0,0,000FFh                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0e5h,0f6h,072h,01bh                          ; expected crc
          DB      '<INR,DCR> C..................$'

; <INR,DCR> D (3072 cycles)
incd:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      014h,0,0,0                                   ; insn
          DW      0a0eAh,05fbAh,065fBh,0981Ch,038cCh,0debch    ; memop,--,--,HL,DE,BC
          DB      043h,05ch                                    ; flags,acc
          DW      003bdh                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0,0ff00h,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      015h,0b5h,057h,09ah                          ; expected crc
          DB      '<INR,DCR> D..................$'

; <INX,DCX> D (1536 cycles)
incde:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      013h,0,0,0                                   ; insn
          DW      0342Eh,0131Dh,028c9h,00acAh,09967h,03a2eh    ; memop,--,--,HL,DE,BC
          DB      092h,0f6h                                    ; flags,acc
          DW      09d54h                                       ; SP

                                                               ; second (256 cycles)
          DB      008h,0,0,0                                   ; insn
          DW      0,0,0,0,0f821h,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      07Fh,04Eh,025h,001h                          ; expected crc
          DB      '<INX,DCX> D..................$'

; <INR,DCR> E (3072 cycles)
ince:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      01Ch,0,0,0                                   ; insn
          DW      0602Fh,04c0Dh,02402h,0e2f5h,0a0f4h,0a10ah    ; memop,--,--,HL,DE,BC
          DB      013h,032h                                    ; flags,acc
          DW      05925h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0,000FFh,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0cFh,02Ah,0b3h,096h                          ; expected crc
          DB      '<INR,DCR> E..................$'

; <INR,DCR> H (307 cycles)
inch:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      024h,0,0,0                                   ; insn
          DW      01506h,0f2eBh,0e8dDh,0262Bh,011a6h,0bc1ah    ; memop,--,--,HL,DE,BC
          DB      017h,006h                                    ; flags,acc
          DW      02818h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0ff00h,0,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      012h,0b2h,095h,02ch                          ; expected crc
          DB      '<INR,DCR> H..................$'

; <INX,DCX> H (1536 cycles)
inchl:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      023h,0,0,0                                   ; insn
          DW      0c3f4h,007a5h,01b6Dh,04f04h,0e2c2h,0822ah    ; memop,--,--,HL,DE,BC
          DB      057h,0e0h                                    ; flags,acc
          DW      0c3e1h                                       ; SP

                                                               ; second (256 cycles)
          DB      008h,0,0,0                                   ; insn
          DW      0,0,0,0f821h,0,0                             ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      09Fh,02Bh,023h,0c0h                          ; expected crc
          DB      '<INX,DCX> H..................$'

; <INR,DCR> L (3072 cycles)
incl:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      02Ch,0,0,0                                   ; insn
          DW      08031h,0a520h,04356h,0b409h,0f4c1h,0dfa2h    ; memop,--,--,HL,DE,BC
          DB      0d1h,03ch                                    ; flags,acc
          DW      03ea2h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0,0,0,0FFh,0,0                               ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0FFh,057h,0d3h,056h                          ; expected crc
          DB      '<INR,DCR> L..................$'

; <INR,DCR> M (3072 cycles)
incm:     DB      0FFh                                         ; flag mask

                                                               ; first
          DB      034h,0,0,0                                   ; insn
          DW      0b856h,00c7Ch,0e53Eh,msbt,0877Eh,0da58h      ; memop,--,--,HL,DE,BC
          DB      015h,05ch                                    ; flags,acc
          DW      01f37h                                       ; SP

                                                               ; second (512 cycles)
          DB      001h,0,0,0                                   ; insn
          DW      0FFh,0,0,0,0,0                               ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

;crc      DB      092h,0e9h,063h,0bdh                          ; original expected crc
          DB      0A0h, 04Ah, 080h, 063h                       ; current expected crc from emu80
          DB      '<INR,DCR> M..................$'

; <INX,DCX> SP (1536 cycles)
incsp:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      033h,0,0,0                                   ; insn
          DW      0346Fh,0d482h,0d169h,0deb6h,0a494h,0f476h    ; memop,--,--,HL,DE,BC
          DB      053h,002h                                    ; flags,acc
          DW      0855bh                                       ; SP

                                                               ; second (256 cycles)
          DB      008h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0f821h                                       ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0d5h,070h,02Fh,0abh                          ; expected crc
          DB      '<INX,DCX> SP.................$'

; LHLD NNNN (16 cycles)
ld162:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      02Ah,msbt & 0xFF,msbt >> 8,0                 ; insn
          DW      09863h,07830h,02077h,0b1fEh,0b9fAh,0abb8h    ; memop,--,--,HL,DE,BC
          DB      004h,006h                                    ; flags,acc
          DW      06015h                                       ; SP

                                                               ; second (1 cycle)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (16 cycles)
          DB      0,0,0,0                                      ; insn
          DW      -1,0,0,0,0,0                                 ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

          DB      0a9h,0c3h,0d5h,0cbh                          ; expected crc
          DB      'LHLD NNNN....................$'

; SHLD NNNN (16 cycles)
ld166:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      022h,msbt & 0xFF,msbt >> 8,0                 ; insn
          DW      0d003h,07772h,07f53h,03f72h,064eAh,0e180h    ; memop,--,--,HL,DE,BC
          DB      010h,02dh                                    ; flags,acc
          DW      035e9h                                       ; SP

                                                               ; second (1 cycle)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (16 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,-1,0,0                                 ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

          DB      0e8h,086h,04Fh,026h                          ; expected crc
          DB      'SHLD NNNN....................$'

; LXI <B,D,H,SP>,NNNN (64 cycles)
ld16im:   DB      0FFh                                         ; flag mask

                                                               ; first
          DB      1,0,0,0                                      ; insn
          DW      05c1Ch,02d46h,08eb9h,06078h,074b1h,0b30eh    ; memop,--,--,HL,DE,BC
          DB      046h,0d1h                                    ; flags,acc
          DW      030cch                                       ; SP

                                                               ; second (4 cycles)
          DB      030h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (16 cycles)
          DB      0,0FFh,0FFh,0                                ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

          DB      0fCh,0f4h,06Eh,012h                          ; expected crc
          DB      'LXI <B,D,H,SP>,NNNN..........$'             ; descriptive tag

; LDAX <B,D> (44 cycles)
ld8bd:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      00Ah,0,0,0                                   ; insn
          DW      0b3a8h,01d2Ah,07f8Eh,042aCh,msbt,msbt        ; memop,--,--,HL,DE,BC
          DB      0c6h,0b1h                                    ; flags,acc
          DW      0ef8eh                                       ; SP

                                                               ; second (2 cycles)
          DB      010h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (22 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0FFh,0,0,0,0,0                               ; memop,--,--,HL,DE,BC
          DB      0D7h,-1                                      ; flags,acc
          DW      0                                            ; SP

;crc      DB      02Bh,082h,01Dh,05fh                          ; original expected crc
          DB      033h, 0F7h, 07Ah, 039h                       ; current expected crc from emu80
                                                               ; new line is because of new screen if there is no errors
          DB      00Dh, 00Ah, 00Dh, 00Ah, 'LDAX <B,D>...............$'   ; descriptive tag

; MVI <B,C,D,E,H,L,M,A>,NN (64 cycles)
ld8im:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      6,0,0,0                                      ; insn
          DW      0c407h,0f49Dh,0d13Dh,00339h,0de89h,07455h    ; memop,--,--,HL,DE,BC
          DB      053h,0c0h                                    ; flags,acc
          DW      05509h                                       ; SP

                                                               ; second (8 cycles)
          DB      038h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (8 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

          DB      0eAh,0a7h,020h,044h                          ; expected crc
          DB      'MVI <B,C,D,E,H,L,M,A>,NN.....$'             ; descriptive tag

; MOV <BCDEHLA>,<BCDEHLA> (3456 cycles)
ld8rr:    DB      0FFh                                         ; flag mask

                                                               ; first
          DB      040h,0,0,0                                   ; insn
          DW      072a4h,0a024h,061aCh,msbt,082c7h,0718fh      ; memop,--,--,HL,DE,BC
          DB      097h,08fh                                    ; flags,acc
          DW      0ef8eh                                       ; SP

                                                               ; second (64 cycles)
          DB      03Fh,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (54 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0FFh,0,0,0,-1,-1                             ; memop,--,--,HL,DE,BC
          DB      0D7h,-1                                      ; flags,acc
          DW      0                                            ; SP

;crc      DB      010h,0b5h,08Ch,0eeh                          ; original expected crc
          DB      0E8h, 076h, 00Ch, 076h                       ; current expected crc from emu80
          DB      'MOV <BCDEHLA>,<BCDEHLA>......$'             ; descriptive tag

; STA NNNN / LDA NNNN (44 cycles)
lda:      DB       0FFh                                        ; flag mask

                                                               ; first
          DB      032h,msbt & 0xFF,msbt >> 8,0                 ; insn
          DW      0fd68h,0f4eCh,044a0h,0b543h,00653h,0cdbah    ; memop,--,--,HL,DE,BC
          DB      0d2h,04fh                                    ; flags,acc
          DW      01fd8h                                       ; SP

                                                               ; second (2 cycles)
          DB      008h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (22 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0FFh,0,0,0,0,0                               ; memop,--,--,HL,DE,BC
          DB      0D7h,-1                                      ; flags,acc
          DW      0                                            ; SP

          DB      0EDh,057h,0AFh,072h                          ; expected crc
          DB      'STA NNNN / LDA NNNN..........$'             ; descriptive tag

; <RLC,RRC,RAL,RAR> (6144 cycles)
rot8080:  DB       0FFh                                        ; flag mask

                                                               ; first
          DB      7,0,0,0                                      ; insn
          DW      0cb92h,06d43h,00a90h,0c284h,00c53h,0f50eh    ; memop,--,--,HL,DE,BC
          DB      091h,0ebh                                    ; flags,acc
          DW      040fch                                       ; SP

                                                               ; second (1024 cycles)
          DB      018h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

                                                               ; third (6 cycles)
          DB      0,0,0,0                                      ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0D7h,0                                       ; flags,acc
          DW      0                                            ; SP

          DB      0e0h,0d8h,092h,035h                          ; expected crc
          DB      '<RLC,RRC,RAL,RAR>............$'             ; descriptive tag

; STAX <B,D> (96 cycles)
stabd:    DB       0FFh                                        ; flag mask

                                                               ; first
          DB      2,0,0,0                                      ; insn
          DW      00c3Bh,0b592h,06cfFh,0959Eh,msbt,msbt+1      ; memop,--,--,HL,DE,BC
          DB      0c1h,021h                                    ; flags,acc
          DW      0bde7h                                       ; SP

                                                               ; second (4 cycles)
          DB      018h,0,0,0                                   ; insn
          DW      0,0,0,0,0,0                                  ; memop,--,--,HL,DE,BC
          DB      0,0                                          ; flags,acc
          DW      0                                            ; SP

                                                               ; third (24 cycles)
          DB      0,0,0,0                                      ; insn
          DW      -1,0,0,0,0,0                                 ; memop,--,--,HL,DE,BC
          DB      0,-1                                         ; flags,acc
          DW      0                                            ; SP

;crc      DB      02Bh,004h,071h,0e9h                          ; original expected crc
          DB      0DAh, 0DDh, 00Ah, 05Dh                       ; current expected crc from emu80
          DB      'STAX <B,D>...................$'             ; descriptive tag

; start test pointed to by (hl)
stt:      PUSH    H
          MOV     A,M                ; get pointer to test
          INX     H
          MOV     H,M
          MOV     L,A
          MOV     A,M                ; flag mask
          STA     flgmsk+1
          INX     H
          PUSH    H
          LXI     D,20
          DAD     D                  ; point to incmask
          LXI     D,counter
          CALL    initmask
          POP     H
          PUSH    H
          LXI     D,20+20
          DAD     D                  ; point to scanmask
          LXI     D,shifter
          CALL    initmask
          LXI     H,shifter
          MVI     M,1                ; first bit
          POP     H
          PUSH    H
          LXI     D,iut              ; copy initial instruction under test
          LXI     B,4

;#idb ldir replaced with following code
ldir1:    MOV     A,M
          STAX    D
          INX     H
          INX     D
          DCX     B
          MOV     A,B
          ORA     C
          JNZ     ldir1
;#idb

          LXI     D,msbt             ; copy initial machine state
          LXI     B,16

;#idb ldir replaced with following code
ldir2:    MOV     A,M
          STAX    D
          INX     H
          INX     D
          DCX     B
          MOV     A,B
          ORA     C
          JNZ     ldir2
;#idb

          LXI     D,20+20+4          ; skip incmask, scanmask and expcrc
          DAD     D
          XCHG
          CALL    msg                ; show test name
          CALL    initcrc            ; initialise crc
; test loop
tlp:      LDA     iut
          CPI     076h               ; pragmatically avoid halt intructions
          JZ      tlp2
          ANI     0DFh
          CPI     0DDh
          JNZ     tlp1
          LDA     iut+1
          CPI     076h
tlp1:     CNZ     test               ; execute the test instruction
tlp2:     CALL    count              ; increment the counter
          CNZ     shift              ; shift the scan bit
          POP     H                  ; pointer to test case
          JZ      tlp3               ; done if shift returned NZ
          LXI     D,20+20+20
          DAD     D                  ; point to expected crc
          CALL    cmpcrc
          LXI     D,okmsg
          JZ      tlpok
          LXI     D,ermsg1
          CALL    msg
          CALL    phex8
          LXI     D,ermsg2
          CALL    msg
          LXI     H,crcval
          CALL    phex8
          LXI     D,crlf
tlpok:    CALL    msg
          POP     H
          INX     H
          INX     H
          RET

tlp3:     PUSH    H
          MVI     A,1                ; initialise count and shift scanners
          STA     cntbit
          STA     shfbit
          LXI     H,counter
          SHLD    cntbyt
          LXI     H,shifter
          SHLD    shfbyt

          MVI     B,4                ; bytes in iut field
          POP     H                  ; pointer to test case
          PUSH    H
          LXI     D,iut
          CALL    setup              ; setup iut
          MVI     B,16               ; bytes in machine state
          LXI     D,msbt
          CALL    setup              ; setup machine state
          JMP     tlp

; setup a field of the test case
; B  = number of bytes
; hl = pointer to base case
; de = destination
setup:    CALL    subyte
          INX     H
          DCR     B
          JNZ     setup
          RET

subyte:   PUSH    B
          PUSH    D
          PUSH    H
          MOV     C,M                ; get base byte
          LXI     D,20
          DAD     D                  ; point to incmask
          MOV     A,M
          CPI     0
          JZ      subshf
          MVI     B,8                ; 8 bits
subclp:   RRC
          PUSH    PSW
          MVI     A,0
          CC      nxtcbit            ; get next counter bit if mask bit was set
          XRA     C                  ; flip bit if counter bit was set
          RRC
          MOV     C,A
          POP     PSW
          DCR     B
          JNZ     subclp
          MVI     B,8
subshf:   LXI     D,20
          DAD     D                  ; point to shift mask
          MOV     A,M
          CPI     0
          JZ      substr
          MVI     B,8                ; 8 bits
sbshf1:   RRC
          PUSH    PSW
          MVI     A,0
          CC      nxtsbit            ; get next shifter bit if mask bit was set
          XRA     C                  ; flip bit if shifter bit was set
          RRC
          MOV     C,A
          POP     PSW
          DCR     B
          JNZ     sbshf1
substr:   POP     H
          POP     D
          MOV     A,C
          STAX    D                  ; mangled byte to destination
          INX     D
          POP     B
          RET

; get next counter bit in low bit of a
cntbit:   DS      1
cntbyt:   DS      2

nxtcbit:  PUSH    B
          PUSH    H
          LHLD    cntbyt
          MOV     B,M
          LXI     H,cntbit
          MOV     A,M
          MOV     C,A
          RLC
          MOV     M,A
          CPI     1
          JNZ     ncb1
          LHLD    cntbyt
          INX     H
          SHLD    cntbyt
ncb1:     MOV     A,B
          ANA     C
          POP     H
          POP     B
          RZ
          MVI     A,1
          RET

; get next shifter bit in low bit of a
shfbit:   DS      1
shfbyt:   DS      2

nxtsbit:  PUSH    B
          PUSH    H
          LHLD    shfbyt
          MOV     B,M
          LXI     H,shfbit
          MOV     A,M
          MOV     C,A
          RLC
          MOV     M,A
          CPI     1
          JNZ     nsb1
          LHLD    shfbyt
          INX     H
          SHLD    shfbyt
nsb1:     MOV     A,B
          ANA     C
          POP     H
          POP     B
          RZ
          MVI     A,1
          RET


; clear memory at HL, BC bytes
clrmem:   PUSH    PSW
          PUSH    B
          PUSH    D
          PUSH    H
          MVI     M,0
          MOV     D,H
          MOV     E,l
          INX     D
          DCX     B

;#idb ldir replaced with following code
ldir3:    MOV     A,M
          STAX    D
          INX     H
          INX     D
          DCX     B
          MOV     A,B
          ORA     C
          JNZ     ldir3
;#idb

          POP     H
          POP     D
          POP     B
          POP     PSW
          RET

; initialise counter or shifter
; DE = pointer to work area for counter or shifter
; HL = pointer to mask
initmask:
          PUSH    D
          XCHG
          LXI     B,20+20
          CALL    clrmem             ; clear work area
          XCHG
          MVI     B,20               ; byte counter
          MVI     C,1                ; first bit
          MVI     D,0                ; bit counter
imlp:     MOV     E,M
imlp1:    MOV     A,E
          ANA     C
          JZ      imlp2
          INR     D
imlp2:    MOV     A,C
          RLC
          MOV     C,A
          CPI     1
          JNZ     imlp1
          INX     H
          DCR     B
          JNZ     imlp
; got number of 1-bits in mask in reg d
          MOV     A,D
          ANI     0f8h
          RRC
          RRC
          RRC                        ; divide by 8 (get byte offset)
          MOV     L,A
          MVI     H,0
          MOV     A,D
          ANI     7                  ; bit offset
          INR     A
          MOV     B,A
          MVI     A,080h
imlp3:    RLC
          DCR     B
          JNZ     imlp3
          POP     D
          DAD     D
          LXI     D,20
          DAD     D
          MOV     M,A
          RET

; multi-byte counter
count:    PUSH    B
          PUSH    D
          PUSH    H
          LXI     H,counter          ; 20 byte counter starts here
          LXI     D,20               ; somewhere in here is the stop bit
          XCHG
          DAD     D
          XCHG
cntlp:    INR     m
          MOV     A,M
          CPI     0
          JZ      cntlp1             ; overflow to next byte
          MOV     B,A
          LDAX    D
          ANA     B                  ; test for terminal value
          JZ      cntend
          MVI     M,0                ; reset to zero
cntend:   POP     B
          POP     D
          POP     H
          RET

cntlp1:   INX     H
          INX     D
          JMP     cntlp


; multi-byte shifter
shift:    PUSH    B
          PUSH    D
          PUSH    H
          LXI     H,shifter          ; 20 byte shift register starts here
          LXI     D,20               ; somewhere in here is the stop bit
          XCHG
          DAD     D
          XCHG
shflp:    MOV     A,M
          ORA     A
          JZ      shflp1
          MOV     B,A
          LDAX    D
          ANA     B
          JNZ     shlpe
          MOV     A,B
          RLC
          CPI     1
          JNZ     shflp2
          MVI     M,0
          INX     H
          INX     D
shflp2:   MOV     M,A
          XRA     a                  ; set Z
shlpe:    POP     H
          POP     D
          POP     B
          RET
shflp1:   INX     H
          INX     D
          JMP     shflp

counter:  DS     2*20
shifter:  DS     2*20

; test harness
test:     PUSH    PSW
          PUSH    B
          PUSH    D
          PUSH    H
;!        if      0
;!        LXI     D,crlf
;!        CALL    msg
;!        LXI     H,iut
;!        MVI     B,4
;!        CALL    hexstr
;!        MVI     A,' '
;!        CALL    pchar
;!        MVI     B,16
;!        LXI     H,msbt
;!        CALL    hexstr
;!        endif
          DI                         ; disable interrupts

;#idb ld (spsav),sp replaced by following code
;#idb All registers and flages are immediately overwritten so
;#idb no need to preserve any state.
          LXI     H,0                ; save stack pointer
          DAD     sp
          SHLD    spsav
;#idb

          LXI     sp,msbt+2          ; point to test-case machine state

;#idb pop iy
;#idb pop ix both replaced by following code
;#idb Just dummy out ix/iy with copies of HL
          POP     H                  ; and load all regs
          POP     H
;#idb

          POP     H
          POP     D
          POP     B
          POP     PSW

;#idb ld sp,(spbt) replaced with the following code
;#idb HL is copied/restored before/after load so no state changed
          SHLD    temp
          LHLD    spbt
          SPHL
          LHLD    temp
;#idb

iut:      DS      4                 ; max 4 byte instruction under test

;#idb ld (spat),sp replaced with the following code
;#idb Must be very careful to preserve registers and flag
;#idb state resulting from the test.  The temptation is to use the
;#idb stack - but that doesn't work because of the way the app
;#idb uses SP as a quick way of pointing to memory.
;#idb Bit of a code smell, but I can't think of an easier way.
          SHLD    temp
          LXI     H,0
          JC      temp1             ; jump on the state of the C flag set in the test

          DAD     sp                ; this code will clear the C flag (0 + nnnn = nc)
          JMP     temp2             ; C flag is same state as before

temp1:    DAD     sp                ; this code will clear the C flag (0 + nnnn = nc)
          STC                       ; C flage needs re-setting to preserve state

temp2:    SHLD    spat
          LHLD    temp
;#idb

          LXI     sp,spat
          PUSH    psw               ; save other registers
          PUSH    B
          PUSH    D
          PUSH    H

;#idb push ix
;#idb push iy both replaced by following code
;#idb Must match change made to pops made before test
          PUSH    H
          PUSH    H
;#idb

;#idb ld sp,(spsav) replaced with following code
;#idb No need to preserve state
          LHLD    spsav             ; restore stack pointer
          SPHL
;#idb

          EI                        ; enable interrupts
          LHLD    msbt              ; copy memory operand
          SHLD    msat
          LXI     H,flgsat          ; flags after test
          MOV     A,M
flgmsk:   ANI     0FFh              ; mask-out irrelevant bits (self-modified code!)
          MOV     M,A
          MVI     B,16              ; total of 16 bytes of state
          LXI     D,msat
          LXI     H,crcval
tcrc:     LDAX    D
          INX     D
          CALL    updcrc            ; accumulate crc of this test case
          DCR     B
          JNZ     tcrc
;!        if      0
;!        MVI     A,' '
;!        CALL    pchar
;!        LXI     H,crcval
;!        CALL    phex8
;!        LXI     D,crlf
;!        CALL    msg
;!        LXI     H,msat
;!        MVI     B,16
;!        CALL    hexstr
;!        LXI     D,crlf
;!        CALL    msg
;!        endif
          POP     H
          POP     D
          POP     B
          POP     PSW
          RET

;#idb Added to store HL state
temp:     DS      2
;#idb

; machine state after test
msat:     DS      14                ; memop,--,--,HL,DE,BC
spat:     DS      2                 ; stack pointer after test
flgsat    EQU     spat-2            ; flags

spsav:    DS      2                 ; saved stack pointer

; display hex string (pointer in HL, byte count in B)
hexstr:   MOV     A,M
          CALL    byteo
          INX     H
          DCR     B
          JNZ     hexstr
          RET

; display hex
; display the big-endian 32-bit value pointed to by HL
phex8:    PUSh    PSW
          PUSh    B
          PUSh    H
          MVI     B,4
ph8lp:    MOV     A,M
          CALl    byteo
          INX     H
          DCR     B
          JNZ     ph8lp
          POP     H
          POP     B
          POP     PSW
          RET

;
bdos      EQU     0C037h            ; LIK PRINT CHAR PROCEDURE
wboot:    JMP     0C800h            ; LIK MONITOR-1M
;
;MESSAGE OUTPUT ROUTINE
;
msg:      PUSH    B                 ; Push state
          PUSH    D
          PUSH    H
          PUSH    PSW
          XCHG                      ; Swap HL and DE
msgs:     MOV     A,M               ; Get data
          CPI     '$'               ; End?
          JZ      msge              ; Exit
          MOV     A,M
          CALL    pchar             ; Output
          INX     H                 ; Next
          JMP     msgs              ; Do all
msge:     POP     PSW               ; Pop state
          POP     H
          POP     D
          POP     B
          RET
;
;CHARACTER OUTPUT ROUTINE
;
pchar:    PUSH    B
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
byteo:    PUSH    B
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
byto1:    RRC
          RRC
          RRC
          RRC
byto2:    ANI     00Fh
          CPI     00Ah
          JM      byto3
          ADI     7
byto3:    ADI     030h
          RET

msg1:     DB      00Dh, 00Ah, '8080 INSTRUCTION EXERCISER (KR580VM80A CPU)', 00Dh, 00Ah, '$'
msg2:     DB      'TESTS COMPLETE', 00Dh, 00Ah, '$'
okmsg:    DB      '  OK', 00Dh, 00Ah, '$'
ermsg1:   DB      '  ERROR ****', 00Dh, 00Ah, ' CRC EXPECTED: ', '$'
ermsg2:   DB      00Dh, 00Ah, ' FOUND:        ', '$'
crlf:     DB      00Dh, 00Ah, '$'

; compare crc
; HL points to value to compare to crcval
cmpcrc:   PUSH    B
          PUSH    D
          PUSH    H
          LXI     D,crcval
          MVI     B,4
cclp:     LDAX    D
          CMP     m
          JNZ     cce
          INX     H
          INX     D
          DCR     B
          JNZ     cclp
cce:      POP     H
          POP     D
          POP     B
          RET

; 32-bit crc routine
; entry: A contains next byte, HL points to crc
; exit:  crc updated
updcrc:   PUSH    PSW
          PUSH    B
          PUSH    D
          PUSH    H
          PUSH    H
          LXI     D,3
          DAD     D                 ; point to low byte of old crc
          XRA     m                 ; xor with new byte
          MOV     L,A
          MVI     H,0
          DAD     H                 ; use result as index into table of 4 byte entries
          DAD     H
          XCHG
          LXI     H,crctab
          DAD     D                 ; point to selected entry in crctab
          XCHG
          POP     H
          LXI     B,4               ; c = byte count, B = accumulator
crclp:    LDAX    D
          XRA     B
          MOV     B,M
          MOV     M,A
          INX     D
          INX     H
          DCR     C
          JNZ     crclp
;!        IF      0
;!        LXI     H,crcval
;!        CALL    phex8
;!        LXI     D,crlf
;!        CALL    msg
;!        ENDIF
          POP     H
          POP     D
          POP     B
          POP     PSW
          RET

initcrc:  PUSH    PSW
          PUSH    B
          PUSH    H
          LXI     H,crcval
          MVI     A,0FFh
          MVI     B,4
icrclp:   MOV     M,A
          INX     H
          DCR     B
          JNZ     icrclp
          POP     H
          POP     B
          POP     PSW
          RET

crcval:   DS      4

crctab:   DB      000h,000h,000h,000h
          DB      077h,007h,030h,096h
          DB      0EEh,00Eh,061h,02Ch
          DB      099h,009h,051h,0BAh
          DB      007h,06Dh,0C4h,019h
          DB      070h,06Ah,0F4h,08Fh
          DB      0E9h,063h,0A5h,035h
          DB      09Eh,064h,095h,0A3h
          DB      00Eh,0DBh,088h,032h
          DB      079h,0DCh,0B8h,0A4h
          DB      0E0h,0D5h,0E9h,01Eh
          DB      097h,0D2h,0D9h,088h
          DB      009h,0B6h,04Ch,02Bh
          DB      07Eh,0B1h,07Ch,0BDh
          DB      0E7h,0B8h,02Dh,007h
          DB      090h,0BFh,01Dh,091h
          DB      01Dh,0B7h,010h,064h
          DB      06Ah,0B0h,020h,0F2h
          DB      0F3h,0B9h,071h,048h
          DB      084h,0BEh,041h,0DEh
          DB      01Ah,0DAh,0D4h,07Dh
          DB      06Dh,0DDh,0E4h,0EBh
          DB      0F4h,0D4h,0B5h,051h
          DB      083h,0D3h,085h,0C7h
          DB      013h,06Ch,098h,056h
          DB      064h,06Bh,0A8h,0C0h
          DB      0FDh,062h,0F9h,07Ah
          DB      08Ah,065h,0C9h,0ECh
          DB      014h,001h,05Ch,04Fh
          DB      063h,006h,06Ch,0D9h
          DB      0FAh,00Fh,03Dh,063h
          DB      08Dh,008h,00Dh,0F5h
          DB      03Bh,06Eh,020h,0C8h
          DB      04Ch,069h,010h,05Eh
          DB      0D5h,060h,041h,0E4h
          DB      0A2h,067h,071h,072h
          DB      03Ch,003h,0E4h,0D1h
          DB      04Bh,004h,0D4h,047h
          DB      0D2h,00Dh,085h,0FDh
          DB      0A5h,00Ah,0B5h,06Bh
          DB      035h,0B5h,0A8h,0FAh
          DB      042h,0B2h,098h,06Ch
          DB      0DBh,0BBh,0C9h,0D6h
          DB      0ACh,0BCh,0F9h,040h
          DB      032h,0D8h,06Ch,0E3h
          DB      045h,0DFh,05Ch,075h
          DB      0DCh,0D6h,00Dh,0CFh
          DB      0ABh,0D1h,03Dh,059h
          DB      026h,0D9h,030h,0ACh
          DB      051h,0DEh,000h,03Ah
          DB      0C8h,0D7h,051h,080h
          DB      0BFh,0D0h,061h,016h
          DB      021h,0B4h,0F4h,0B5h
          DB      056h,0B3h,0C4h,023h
          DB      0CFh,0BAh,095h,099h
          DB      0B8h,0BDh,0A5h,00Fh
          DB      028h,002h,0B8h,09Eh
          DB      05Fh,005h,088h,008h
          DB      0C6h,00Ch,0D9h,0B2h
          DB      0B1h,00Bh,0E9h,024h
          DB      02Fh,06Fh,07Ch,087h
          DB      058h,068h,04Ch,011h
          DB      0C1h,061h,01Dh,0ABh
          DB      0B6h,066h,02Dh,03Dh
          DB      076h,0DCh,041h,090h
          DB      001h,0DBh,071h,006h
          DB      098h,0D2h,020h,0BCh
          DB      0EFh,0D5h,010h,02Ah
          DB      071h,0B1h,085h,089h
          DB      006h,0B6h,0B5h,01Fh
          DB      09Fh,0BFh,0E4h,0A5h
          DB      0E8h,0B8h,0D4h,033h
          DB      078h,007h,0C9h,0A2h
          DB      00Fh,000h,0F9h,034h
          DB      096h,009h,0A8h,08Eh
          DB      0E1h,00Eh,098h,018h
          DB      07Fh,06Ah,00Dh,0BBh
          DB      008h,06Dh,03Dh,02Dh
          DB      091h,064h,06Ch,097h
          DB      0E6h,063h,05Ch,001h
          DB      06Bh,06Bh,051h,0F4h
          DB      01Ch,06Ch,061h,062h
          DB      085h,065h,030h,0D8h
          DB      0F2h,062h,000h,04Eh
          DB      06Ch,006h,095h,0EDh
          DB      01Bh,001h,0A5h,07Bh
          DB      082h,008h,0F4h,0C1h
          DB      0F5h,00Fh,0C4h,057h
          DB      065h,0B0h,0D9h,0C6h
          DB      012h,0B7h,0E9h,050h
          DB      08Bh,0BEh,0B8h,0EAh
          DB      0FCh,0B9h,088h,07Ch
          DB      062h,0DDh,01Dh,0DFh
          DB      015h,0DAh,02Dh,049h
          DB      08Ch,0D3h,07Ch,0F3h
          DB      0FBh,0D4h,04Ch,065h
          DB      04Dh,0B2h,061h,058h
          DB      03Ah,0B5h,051h,0CEh
          DB      0A3h,0BCh,000h,074h
          DB      0D4h,0BBh,030h,0E2h
          DB      04Ah,0DFh,0A5h,041h
          DB      03Dh,0D8h,095h,0D7h
          DB      0A4h,0D1h,0C4h,06Dh
          DB      0D3h,0D6h,0F4h,0FBh
          DB      043h,069h,0E9h,06Ah
          DB      034h,06Eh,0D9h,0FCh
          DB      0ADh,067h,088h,046h
          DB      0DAh,060h,0B8h,0D0h
          DB      044h,004h,02Dh,073h
          DB      033h,003h,01Dh,0E5h
          DB      0AAh,00Ah,04Ch,05Fh
          DB      0DDh,00Dh,07Ch,0C9h
          DB      050h,005h,071h,03Ch
          DB      027h,002h,041h,0AAh
          DB      0BEh,00Bh,010h,010h
          DB      0C9h,00Ch,020h,086h
          DB      057h,068h,0B5h,025h
          DB      020h,06Fh,085h,0B3h
          DB      0B9h,066h,0D4h,009h
          DB      0CEh,061h,0E4h,09Fh
          DB      05Eh,0DEh,0F9h,00Eh
          DB      029h,0D9h,0C9h,098h
          DB      0B0h,0D0h,098h,022h
          DB      0C7h,0D7h,0A8h,0B4h
          DB      059h,0B3h,03Dh,017h
          DB      02Eh,0B4h,00Dh,081h
          DB      0B7h,0BDh,05Ch,03Bh
          DB      0C0h,0BAh,06Ch,0ADh
          DB      0EDh,0B8h,083h,020h
          DB      09Ah,0BFh,0B3h,0B6h
          DB      003h,0B6h,0E2h,00Ch
          DB      074h,0B1h,0D2h,09Ah
          DB      0EAh,0D5h,047h,039h
          DB      09Dh,0D2h,077h,0AFh
          DB      004h,0DBh,026h,015h
          DB      073h,0DCh,016h,083h
          DB      0E3h,063h,00Bh,012h
          DB      094h,064h,03Bh,084h
          DB      00Dh,06Dh,06Ah,03Eh
          DB      07Ah,06Ah,05Ah,0A8h
          DB      0E4h,00Eh,0CFh,00Bh
          DB      093h,009h,0FFh,09Dh
          DB      00Ah,000h,0AEh,027h
          DB      07Dh,007h,09Eh,0B1h
          DB      0F0h,00Fh,093h,044h
          DB      087h,008h,0A3h,0D2h
          DB      01Eh,001h,0F2h,068h
          DB      069h,006h,0C2h,0FEh
          DB      0F7h,062h,057h,05Dh
          DB      080h,065h,067h,0CBh
          DB      019h,06Ch,036h,071h
          DB      06Eh,06Bh,006h,0E7h
          DB      0FEh,0D4h,01Bh,076h
          DB      089h,0D3h,02Bh,0E0h
          DB      010h,0DAh,07Ah,05Ah
          DB      067h,0DDh,04Ah,0CCh
          DB      0F9h,0B9h,0DFh,06Fh
          DB      08Eh,0BEh,0EFh,0F9h
          DB      017h,0B7h,0BEh,043h
          DB      060h,0B0h,08Eh,0D5h
          DB      0D6h,0D6h,0A3h,0E8h
          DB      0A1h,0D1h,093h,07Eh
          DB      038h,0D8h,0C2h,0C4h
          DB      04Fh,0DFh,0F2h,052h
          DB      0D1h,0BBh,067h,0F1h
          DB      0A6h,0BCh,057h,067h
          DB      03Fh,0B5h,006h,0DDh
          DB      048h,0B2h,036h,04Bh
          DB      0D8h,00Dh,02Bh,0DAh
          DB      0AFh,00Ah,01Bh,04Ch
          DB      036h,003h,04Ah,0F6h
          DB      041h,004h,07Ah,060h
          DB      0DFh,060h,0EFh,0C3h
          DB      0A8h,067h,0DFh,055h
          DB      031h,06Eh,08Eh,0EFh
          DB      046h,069h,0BEh,079h
          DB      0CBh,061h,0B3h,08Ch
          DB      0BCh,066h,083h,01Ah
          DB      025h,06Fh,0D2h,0A0h
          DB      052h,068h,0E2h,036h
          DB      0CCh,00Ch,077h,095h
          DB      0BBh,00Bh,047h,003h
          DB      022h,002h,016h,0B9h
          DB      055h,005h,026h,02Fh
          DB      0C5h,0BAh,03Bh,0BEh
          DB      0B2h,0BDh,00Bh,028h
          DB      02Bh,0B4h,05Ah,092h
          DB      05Ch,0B3h,06Ah,004h
          DB      0C2h,0D7h,0FFh,0A7h
          DB      0B5h,0D0h,0CFh,031h
          DB      02Ch,0D9h,09Eh,08Bh
          DB      05Bh,0DEh,0AEh,01Dh
          DB      09Bh,064h,0C2h,0B0h
          DB      0ECh,063h,0F2h,026h
          DB      075h,06Ah,0A3h,09Ch
          DB      002h,06Dh,093h,00Ah
          DB      09Ch,009h,006h,0A9h
          DB      0EBh,00Eh,036h,03Fh
          DB      072h,007h,067h,085h
          DB      005h,000h,057h,013h
          DB      095h,0BFh,04Ah,082h
          DB      0E2h,0B8h,07Ah,014h
          DB      07Bh,0B1h,02Bh,0AEh
          DB      00Ch,0B6h,01Bh,038h
          DB      092h,0D2h,08Eh,09Bh
          DB      0E5h,0D5h,0BEh,00Dh
          DB      07Ch,0DCh,0EFh,0B7h
          DB      00Bh,0DBh,0DFh,021h
          DB      086h,0D3h,0D2h,0D4h
          DB      0F1h,0D4h,0E2h,042h
          DB      068h,0DDh,0B3h,0F8h
          DB      01Fh,0DAh,083h,06Eh
          DB      081h,0BEh,016h,0CDh
          DB      0F6h,0B9h,026h,05Bh
          DB      06Fh,0B0h,077h,0E1h
          DB      018h,0B7h,047h,077h
          DB      088h,008h,05Ah,0E6h
          DB      0FFh,00Fh,06Ah,070h
          DB      066h,006h,03Bh,0CAh
          DB      011h,001h,00Bh,05Ch
          DB      08Fh,065h,09Eh,0FFh
          DB      0F8h,062h,0AEh,069h
          DB      061h,06Bh,0FFh,0D3h
          DB      016h,06Ch,0CFh,045h
          DB      0A0h,00Ah,0E2h,078h
          DB      0D7h,00Dh,0D2h,0EEh
          DB      04Eh,004h,083h,054h
          DB      039h,003h,0B3h,0C2h
          DB      0A7h,067h,026h,061h
          DB      0D0h,060h,016h,0F7h
          DB      049h,069h,047h,04Dh
          DB      03Eh,06Eh,077h,0DBh
          DB      0AEh,0D1h,06Ah,04Ah
          DB      0D9h,0D6h,05Ah,0DCh
          DB      040h,0DFh,00Bh,066h
          DB      037h,0D8h,03Bh,0F0h
          DB      0A9h,0BCh,0AEh,053h
          DB      0DEh,0BBh,09Eh,0C5h
          DB      047h,0B2h,0CFh,07Fh
          DB      030h,0B5h,0FFh,0E9h
          DB      0BDh,0BDh,0F2h,01Ch
          DB      0CAh,0BAh,0C2h,08Ah
          DB      053h,0B3h,093h,030h
          DB      024h,0B4h,0A3h,0A6h
          DB      0BAh,0D0h,036h,005h
          DB      0CDh,0D7h,006h,093h
          DB      054h,0DEh,057h,029h
          DB      023h,0D9h,067h,0BFh
          DB      0B3h,066h,07Ah,02Eh
          DB      0C4h,061h,04Ah,0B8h
          DB      05Dh,068h,01Bh,002h
          DB      02Ah,06Fh,02Bh,094h
          DB      0B4h,00Bh,0BEh,037h
          DB      0C3h,00Ch,08Eh,0A1h
          DB      05Ah,005h,0DFh,01Bh
          DB      02Dh,002h,0EFh,08Dh

end: