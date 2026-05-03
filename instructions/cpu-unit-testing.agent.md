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
4. After `start()`, write `asrtCpu(...)` — run the test once to get the actual output, then paste it as expected.
5. Mark the command done in `readme.md` (`- [ ]` → `- [x]`).
6. Run the full `CpuTest` class to ensure no regressions.
7. Commit with message: `Add tests for <MNEMONIC>`.

## How to find the opcode hex for a mnemonic

- Look in `src/main/java/spec/Cpu.java` for the `switch` or opcode table.
- Or assemble a one-line program: `givenPr("ADD B\nNOP\n")` — `givenMm` will tell you the bytes.

## Running tests

```
.\mvnw.cmd test "-Dtest=CpuTest"
.\mvnw.cmd test "-Dtest=CpuTest#codeXX__MNEMONIC"
```
