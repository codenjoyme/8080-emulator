# CPU Unit Testing

- Tests live in `src/test/java/spec/CpuTest.java`, extending `spec.stuff.AbstractTest`.
- Each test method name follows the pattern: `code<HEX>__<MNEMONIC>()` (e.g. `code3E__MVI_A`).
- Test structure is always **given / when / then**.

## Test anatomy

```java
@Test
public void codeXX__MNEMONIC() {
    // given
    givenPr("LXI B,1234\n" +   // set up registers via assembler mnemonics
            "LXI D,5678\n" +
            "MNEMONIC operands\n" +
            "NOP\n");           // terminal NOP required

    givenMm("01 34 12\n" +     // same program in hex bytes (must match givenPr exactly)
            "11 78 56\n" +
            "XX ...\n" +
            "00");

    // when
    start();

    // then
    asrtCpu("BC:  1234\n" +    // full CPU state snapshot
            ...);
}
```

## Key helpers

- `givenPr(String)` — loads assembler source; validates that disassembly round-trips back to the same text.
- `givenMm(String)` — writes raw hex bytes; validates they match what `givenPr` produced. Use only one of the two, or both together (they must agree).
- `start()` — runs the CPU for exactly as many ticks as there are instructions.
- `asrtCpu(String)` — asserts full `cpu.toStringDetails()` dump (all registers + flags in binary).
- `cpuShort(String)` — asserts short `cpu.toString()` (BC/DE/HL/AF/SP/PC as 16-bit hex).
- `asrtMem(String)` — asserts that only the memory cells that changed match the given hex dump.

## CPU state format

```
BC:  XXXX        ← 16-bit pairs
DE:  XXXX
HL:  XXXX
AF:  XXXX        ← A = accumulator, F = flags byte
SP:  XXXX
PC:  XXXX        ← address after last executed instruction
...
     sz0h0p1c    ← flag bit labels (S Z - H - P/V 1 C)
F:   XXXXXXXX   ← flag byte in binary (bit 1 is always 1)
ts:  false/true  ← Sign flag
tz:  false/true  ← Zero flag
th:  false/true  ← Half-carry flag
tp:  false/true  ← Parity flag
tc:  false/true  ← Carry flag
```

## Flags to test for arithmetic commands

- **S (sign)** — set if result bit 7 is 1.
- **Z (zero)** — set if result is 0.
- **H (half-carry)** — set if carry out of bit 3.
- **P (parity)** — set if number of 1-bits in result is even.
- **C (carry)** — set if result overflowed 8 bits (ADD/ADI/ADC) or borrowed (SUB/SUI/SBB).

## Corner cases to cover per arithmetic command

- Result = 0 → Z=1, S=0.
- Result has bit 7 set → S=1.
- Carry/borrow generated → C=1.
- Half-carry from low nibble to high nibble → H=1.
- Even number of 1-bits in result → P=1.
- Overflow from 0xFF + 1 → wraps to 0x00, C=1.
- All register variants (B, C, D, E, H, L, M, A) for `_R` suffix commands.

## Workflow for each new command

1. Look up the command implementation in `src/main/java/spec/` to understand opcode and behavior.
2. Write the minimum number of test cases to cover all corner cases.
3. Use `LXI`/`MVI` to set up register state before the tested instruction.
4. Run the full `CpuTest` class; use actual failure output to correct expected values.
5. Mark the command done in `readme.md` (`- [ ]` → `- [x]`).
6. **One-commit workflow per UPD cycle:**
   - Append `### RESULT` to the current `## UPD[N]` block in `main.prompt.md`.
   - Update `readme.md` (mark done) and `release.md` (add commit entry).
   - `git add src/test/java/spec/CpuTest.java readme.md request/testing/main.prompt.md release.md`
   - Commit message: `Add test for <MNEMONIC> (<brief description>)`.
   - After committing: write next `## UPD[N+1]` with `go` in `main.prompt.md` — **do NOT commit this yet**.
7. Also update `instructions/cpu-unit-testing.agent.md` each cycle with any new learnings.

## asrtMem — memory write tracking

`asrtMem(String)` checks all cells written since last `start()`. Format per changed cell: `ADDR: oldVal -> val1 -> val2 -> ...`

**CRITICAL — PORTS double-write:** The hardware has a PORTS I/O range `0xF800–0xFFFE`. Any `write8(addr, v)` where addr is in that range triggers **two** writes to the `TrackUpdatedMemory`:
1. via `IOPorts.write8()` → `memory.write8()` (port handler)
2. via `HardwareData.write8()` → `memory.write8()` (direct write, always happens)

Address `0xFFFF` is **NOT** in PORTS range, so it gets only ONE write.

So for stack operations that push to high memory (default SP=0x0000 → push → SP=0xFFFE):
- `asrtMem("FFFE: 00 -> 03 -> 03\nFFFF: 00 -> 00")` — FFFE gets double-write because it's in PORTS; FFFF gets single write.
- With `LXI SP,FFFC`: push → FFFA, FFFB — both in PORTS, both get double-writes: `asrtMem("FFFA: 00 -> 06 -> 06\nFFFB: 00 -> 00 -> 00")`

**trackOnlyUpdates = false** (default in tests): ALL writes are tracked, including same-value writes (e.g. `00 -> 00`). Do NOT assume 0x00 writes are invisible.

After calling `asrtMem(...)` the tracker is reset. The `after()` hook calls `asrtMem("")` — since you already called `asrtMem` in the test body, the tracker is empty and `""` passes.

For instructions that write to arbitrary addresses (STA, SHLD, PUSH, CALL), always call `asrtMem("...")` yourself in the test body before the test exits.

## Tick count and branching instructions

`givenMm` counts **lines** and calls `record.after(N).stopCpu()` — the CPU stops after exactly N instructions execute.

For linear programs (no jumps), line count = instruction count. But for branching instructions (CALL, JMP, etc.):
- Some lines in `givenMm` may represent memory bytes that are **never executed** (e.g. dead NOPs between CALL and its target).
- The CPU continues executing from the target address until the tick limit is hit.
- After the tick limit fires, the CPU always runs **one more instruction** (the stop fires before the next loop iteration, and the interrupt check fires — since `ticksPerInterrupt=1` in tests — and breaks the loop).
- Design tests so the program flows naturally from target into NOPs (memory initialized to 0x00 = NOP), and the final PC value can be predicted from the tick count.

**Example for CALL:** `CALL 0005` + 4 NOPs in givenMm (5 lines → 5 ticks):
- tick 1: CALL (opcode at 0x0000) pushes return addr 0x0003 (addr after 3-byte CALL), jumps to 0x0005
- ticks 2–5: 4 NOPs starting from 0x0005, PC advances: 0x0006, 0x0007, 0x0008, 0x0009
- After tick 5 CPU stops **after** executing NOP at 0x0008 → Final PC = **0x0009**

Wait — PC is updated **before** the instruction stops (rPC.inc16() fires on opcode fetch). So after executing the NOP at addr 0x0008, PC becomes 0x0009 before stop fires. But actually the CPU stop fires between iterations, so the **last executed** NOP increments PC in its opcode read: addr 0x0008 → PC = 0x0009.

**Correction from actual tests:** After CALL 0005 (3 bytes, at addr 0x0002 → return addr 0x0005) + 3 NOPs → Final PC = **0x0008** (NOP at 0x0007 was the last executed). Always verify with actual test run output.

**Rule:** Always run tests and use the actual failure output to correct expected PC values. Don't try to predict PC without running.

## Special flag behaviors

- **ANA_R / ANI_XX H flag:** NOT standard half-carry. H = `(A | operand) & 0x08 != 0`. C always cleared.
- **ANA_R / ANI_XX C flag:** Always cleared to 0 regardless of previous carry.
- **XRA_R / XRI_XX H flag:** Always 0 (H cleared). C always cleared.
- Standard H (arithmetic): borrow from bit4 or carry from bit3.
- P flag: set if number of 1-bits in result is even (including 0).

## Indirect register sentinel for jump/call tests

For conditional JMP and CALL instructions, verifying that the jump target was actually reached (not just checking PC) adds confidence. Use `INR C` (`0x0C`) as a sentinel:

**Pattern:**
- Place `INR C` immediately after the jump/call instruction in the instruction stream.
- For **taken** tests: jump target is set to skip over `INR C` → C stays **0x00**, proving the jump was taken.
- For **not-taken** tests: `INR C` executes normally → C becomes **0x01**, proving fall-through happened.

**Example — taken (JC jumps to 0x0008):**
```
Memory:
0x0000: 3E FF   (MVI A,FF)
0x0002: C6 01   (ADI 01 → carry=1)
0x0004: DA 08 00 (JC 0x0008 — taken, target past INR C)
0x0007: 0C      (INR C — SKIPPED, C stays 0x00 → jump was taken)
0x0008: 00      (NOP — jump target)
```

**Example — not taken (JC falls through):**
```
Memory:
0x0000: DA 06 00 (JC 0x0006 — not taken, C=0 carry)
0x0003: 0C       (INR C — EXECUTED, C becomes 0x01 → jump was NOT taken)
```

**Always assert C register in jump tests** (C=0x00 → taken, C=0x01 → not taken).

**IMPORTANT — `INR C` modifies flags in not-taken tests:** When the jump is NOT taken, `INR C` executes and sets Z, S, H, P flags based on the result (0x01). This means the `AF` register value at the end of a not-taken test will reflect the flags AFTER `INR C`, not the flags set by the pre-jump setup code. Carry (C flag) is NOT changed by `INR`. Always calculate the correct post-INR-C flags when writing not-taken test assertions.

Example: if a setup produces `F=0x46` (Z=1, P=1), then `INR C` (C becomes 0x01) changes to `F=0x02` (Z=0, P=0). If carry was set (F=0x57), after `INR C` carry is preserved: `F=0x03` (only carry+bit1).

**Note:** Use `INR C` (`0x0C`), not `INR B`, in tests that set up B register in the precondition (e.g., CC_XXYY uses `MVI B,01`). C register is unused in all existing setups.

## Pattern for conditional RETURN tests (RC, RM, RNC, RNZ, RP, RPE, RPO, RZ)

Return instructions need to pop from the stack — so CALL must run first to push a return address. To prevent the subroutine from being re-executed after returning, use the **JMP-over-subroutine** pattern:

```
0x0000-0x0002: JMP 0005       (skip over subroutine)
0x0003:        RETURN_INSTR   (subroutine: RX/RC/RM/etc.)
0x0004:        INR C          (sentinel — skipped if return taken)
0x0005-0x0007: CALL 0003      (setup then call the subroutine)
0x0008+:       NOP × N        (return lands here, NOPs fill remaining ticks)
```

- Subroutine is at **low address** (0x0003); return point is at **high address** (0x0008+).
- After returning, NOP sequence advances PC away from subroutine, preventing re-execution.
- CALL at 0x0005-0x0007 pushes return address **0x0008** (byte after 3-byte CALL) to stack.
- `asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00")` for CALL that pushes 0x0008.

**IMPORTANT:** For the *not-taken* variant of return instructions, NO CALL is needed — just the return instruction followed by INR C directly (return not taken → falls through to INR C). No asrtMem needed for the not-taken variant since no stack writes occur.

## Setting flags for conditional CALL tests

For CALL-if-condition tests, you need to SET the tested flag first. Practical ways:
- **Z=1 (zero):** `ADD A` with A=0 → `87` (0+0=0, Z=1, P=1).
- **S=1 (sign):** `MVI A,40` + `ADD A` → A=0x80 (S=1). opcode `87` = `ADD A`. **Do NOT use `MVI A,80`** — MVI doesn't set flags.
- **C=1 (carry):** `MVI A,FF` + `ADI 01` → overflow, C=1. Or `MVI B,FF` + `ADC B` with A=0x01.
- **P=1 (even parity):** `ADI 03` → A=0x03 (bits: 00000011, two 1s = even, P=1).
- **P=0 (odd parity):** `ADI 01` → A=0x01 (one 1-bit = odd, P=0).
- **Default (no prior ops):** Z=0, S=0, C=0, H=0, P=0, F=0x02 (only bit1 always set).

## Finding ADD_A opcode: `0x87` (ADD A), `0x80`=ADD B … `0x87`=ADD A

- `ADD A` → `0x87`, not `0x80` (`0x80` = `ADD B`).
- `ADD A` with `givenMm` expects `87`, not `80`.

## No-op system instructions (DI, EI)

`DI` (0xF3) and `EI` (0xFB) are currently no-ops in this emulator (the interrupt system is unimplemented). Tests for these just verify no registers change and PC advances by 1.

## How to find the opcode hex for a mnemonic

- Look in `src/main/java/spec/Cpu.java` for the `switch` or opcode table.
- Or assemble a one-line program: `givenPr("ADD B\nNOP\n")` — `givenMm` will tell you the bytes.

## Running tests

```
& ".\mvnw.cmd" test "-Dtest=CpuTest"
```

**Always use `& ".\mvnw.cmd"`** (with `&` prefix on Windows), never bare `.\mvnw.cmd`. Run the full `CpuTest` class — old Surefire 2.12.4 cannot handle long `-Dtest=ClassName#method+method...` strings.
