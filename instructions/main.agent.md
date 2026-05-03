# Instructions Catalog

Each entry below is an instruction file with a one-line description. Optional sub-fields after `+`:
- **Keywords** — trigger words/phrases: if user's request matches, load this instruction.
- **Target** — file glob pattern: if current file or context matches, consider this instruction relevant.
- **Exceptions** — edge cases or clarifications that don't fit in the one-liner.

---

- [`./instructions/iterative-prompt.agent.md`](./iterative-prompt.agent.md) — Iterative prompt workflow: maintain a living `main.prompt.md` file with `## UPD[N]` blocks, polling loop, and atomic commits per update.
  + Keywords: iterative prompt, polling loop, UPD, main.prompt.md, watch file, keep running

- [`./instructions/creating-instructions.agent.md`](./creating-instructions.agent.md) — How to create, update, and install instruction files and IDE wrappers (Copilot, Cursor, Claude Code).
  + Keywords: create instruction, new instruction, install instruction, add instruction, update instruction
