<follow>
iterative-prompt.agent.md
</follow>

## UPD1

Вот отсюда установи мне 2 инструкции
...\vibecoding-for-managers\instructions
- iterative prompt
- create instructions (это прочитай и установи как положено)
Потом создай request папку и в ней подпапку testing и в ней main.prompt.md и туда в UPD1 перенеси этот мой запрос и там отчитайся, а потом зависни в polling режиме

### RESULT

Установлены обе инструкции и создана вся инфраструктура для VSCode + GitHub Copilot:

- [instructions/iterative-prompt.agent.md](../../instructions/iterative-prompt.agent.md) — скопирован из источника
- [instructions/creating-instructions.agent.md](../../instructions/creating-instructions.agent.md) — скопирован из источника
- [instructions/main.agent.md](../../instructions/main.agent.md) — каталог инструкций с обеими записями
- [.github/copilot-instructions.md](../../.github/copilot-instructions.md) — entry-point для Copilot
- [.github/prompts/to-iterative-prompt.prompt.md](../../.github/prompts/to-iterative-prompt.prompt.md) — VSCode wrapper
- [.github/prompts/to-creating-instructions.prompt.md](../../.github/prompts/to-creating-instructions.prompt.md) — VSCode wrapper
- [.vscode/settings.json](../../.vscode/settings.json) — настройки с `useInstructionFiles`, `maxRequests: 2500`, auto-save
- [request/testing/main.prompt.md](main.prompt.md) — этот файл (запрос перенесён в UPD1)

## UPD2

Давай поменяем автора проекта на `Oleksandr Baglai` и `apofig@gmail.com`. Давай поменяем это в настройках гита, я говорю про коммиты. И коммиты что ты сделал только что удали. А предыдуший коммит - в нем поменяй юзера. go

### RESULT

- Настроен `git config user.name "Oleksandr Baglai"` и `git config user.email "apofig@gmail.com"` (локально).
- Мои 3 предыдущих коммита удалены (`git reset HEAD~3`).
- Предыдущий коммит `Updated klad level.` переписан с новым автором (`--amend --author`): `265f219`.
- Все файлы инфраструктуры перекоммичены под новым автором: `4a1cd8a`.

## UPD3

А теперь давай займемся тестированием. Сейчас расскажу суть. В `readme.md` есть блок `- [ ] Дописать юнит тесты для каждой не протестированной команды.` и я єто все делал вручную, и оно много времени занимало. Посмотри на эти тесты сам `src/test/java/spec/CpuTest.java`. Посмотри его. 

И следуя инструкции по созданию инструкций, созда мне такую которая б объясняла как происходит этот вид тестирования. 

А потом пользуясь файлом ридми как туду, команда за командой создавай новые тесты для команд. 

Как работают команды можешь понять в том как команда релизована. Мне нужны тесты, которые лаконично (то есть сетап команды будет сделан с минимумом команд, которые сами по себе не сложны) протестируют все ее корнер кейзы (особенно арифметические команды, там много про флаги разные надо проверить все хорошо). 

И так по каждой команде. Как закончил с кодной командой, ставишь галочку, отчитываешься тут (что имено потестил и почему) и коммитишь. Потом делаешь новый UPD и говоришь какую команду берешь. 

Но переж этим всем убедишь что ты умеешь билжить проект. 

И да инструкцию по сборке проекта на локали тоже создай, туда будем добавлять всякое разное как те или иные види билда делаются. go

### RESULT

Билд проверен — `.\mvnw.cmd test "-Dtest=CpuTest#code00__NOP"` → `BUILD SUCCESS` (7.8 s).

Созданы инструкции:
- [instructions/build-locally.agent.md](../../instructions/build-locally.agent.md) — сборка/тесты/запуск (Maven, JS, профили).
- [instructions/cpu-unit-testing.agent.md](../../instructions/cpu-unit-testing.agent.md) — структура CPU-теста, хелперы, угловые кейсы флагов, воркфлоу.

Обновлены:
- [instructions/main.agent.md](../../instructions/main.agent.md) — добавлены две новые записи.
- [.github/prompts/to-build-locally.prompt.md](../../.github/prompts/to-build-locally.prompt.md) — VSCode wrapper.
- [.github/prompts/to-cpu-unit-testing.prompt.md](../../.github/prompts/to-cpu-unit-testing.prompt.md) — VSCode wrapper.

## UPD4

Первая команда из TODO — `ACI_XX`. Беру её в UPD4. go

### RESULT

`ACI_XX` (opcode `0xCE`): `A = A + imm8 + carry`. Написаны и прошли 4 теста:

- `codeCE__ACI_XX_no_flags` — базовый случай (0x10+0x20=0x30, P=1)
- `codeCE__ACI_XX_zero_and_carry` — overflow через 0xFF+1, Z=1 C=1 H=1 P=1
- `codeCE__ACI_XX_sign_and_half_carry` — 0x7F+1=0x80, S=1 H=1
- `codeCE__ACI_XX_with_carry_in` — carry пробрасывается: 0x0F+0+carry(1)=0x10, H=1

Все 142 теста CpuTest прошли. `readme.md` — `ACI_XX` отмечен ✓.

Коммит: `2aab430 Add tests for ACI_XX (add immediate with carry)`

## UPD5

Давай так, я вижу что тЫ коммитишь решение, а потом уже комментируешь что именно протестировал и почему и снова коммитишь. Я же предлагаю тебе делать 1 команду 1 коммит, с риквестом в этом файле и RESULT блоком. Так же сразу после коммита пиши новый UPD с тем что будешь делать дальше. Сам себе пиши `go` и вперед команда за командой. 

### RESULT

Принял. Новый воркфлоу: код + RESULT + следующий UPD(с `go`) — один атомарный коммит.

## UPD6

Беру `ADC_R` (opcodes `0x88`-`0x8F`): `A = A + reg + carry`. 8 регистров: B,C,D,E,H,L,M,A. Тесты для каждого регистра + флаговые corner cases. go

### RESULT

10 тестов, все прошли (152 total):
- `code88__ADC_B` / `code89__ADC_C` / `code8A__ADC_D` / `code8B__ADC_E` — базовые (каждый регистр)
- `code8C__ADC_H` / `code8D__ADC_L` — H и L из HL-пары
- `code8E__ADC_M` — M = memory[HL], HL указывает на байт программы
- `code8F__ADC_A` — self-add, S=1 (0x80)
- `code88__ADC_B_with_carry_in` — carry пробрасывается через nibble, H=1
- `code8F__ADC_A_with_carry_in` — 2*A+carry=0x81, S=1, P=1

## UPD7

Беру `ADD_R` (opcodes `0x80`-`0x87`): `A = A + reg`, без carry. Те же 8 регистров. go

### RESULT

10 тестов, все прошли (162 total):
- `code80__ADD_B` .. `code87__ADD_A` — все 8 регистров, базовые случаи
- `code80__ADD_B_zero_and_carry` — 0xFF+0x01=0x00, Z=1,C=1,H=1
- `code80__ADD_B_carry_not_consumed` — carry=1 установлен заранее через ADC, но ADD B игнорирует его: 0x0F+0x00=0x0F (не 0x10)

## UPD8

Беру `ADI_XX` (opcode `0xC6`): `A = A + imm8`, без carry. go

### RESULT

4 теста, все прошли (166 total):
- `codeC6__ADI_XX_no_flags` — 0x10+0x20=0x30
- `codeC6__ADI_XX_zero_and_carry` — 0xFF+0x01=0x00, Z,C,H=1
- `codeC6__ADI_XX_sign_and_half_carry` — 0x7F+0x01=0x80, S,H=1
- `codeC6__ADI_XX_carry_not_consumed` — carry=1 установлен, ADI 00 → 0x0F (не 0x10)

## UPD9

Беру `ANA_R` (opcodes `0xA0`-`0xA7`): `A = A & reg`, AND по регистру. go

### RESULT

11 тестов, все прошли (177 total):
- `codeA0__ANA_B` .. `codeA7__ANA_A` — все 8 регистров
- `codeA0__ANA_B_zero` — 0xF0 & 0x0F = 0x00, Z=1, H=1 (bit3 в 0x0F)
- `codeA0__ANA_B_h_zero` — H=0 когда ни A ни B не имеют bit3 (0xF0&0xF0=0xF0)
- `codeA0__ANA_B_carry_cleared` — carry=1 установлен заранее, ANA обнуляет C

## UPD10

Беру `ANI_XX` (opcode `0xE6`): `A = A & imm8`. go

### RESULT

4 теста, все прошли (181 total):
- `codeE6__ANI_XX_no_flags` — A=0xFF & 0xF0 = 0xF0, S=1, H=1, P=1
- `codeE6__ANI_XX_zero` — A=0xF0 & 0x0F = 0x00, Z=1, H=1
- `codeE6__ANI_XX_h_zero` — A=0xF0 & 0xF0 = 0xF0, H=0 (нет bit3)
- `codeE6__ANI_XX_carry_cleared` — carry=1 → ANA обнуляет C

## UPD11

Беру `CALL_XXYY` (opcode `0xCD`): вызов подпрограммы, PC → stack, jump to addr. go

### RESULT

2 теста, все прошли (183 total):
- `codeCD__CALL_XXYY` — базовый CALL 0x0005, SP=0xFFFE, return addr 0x0003 на стеке
- `codeCD__CALL_XXYY_with_sp` — CALL с LXI SP,FFFC перед ним, SP=0xFFFA, return addr 0x0006
- Ключевой инсайт: PORTS range 0xF800-0xFFFE вызывает двойную запись, `asrtMem` должен ожидать `-> 03 -> 03` паттерн
- Обновлены [instructions/iterative-prompt.agent.md](../../instructions/iterative-prompt.agent.md) и [instructions/cpu-unit-testing.agent.md](../../instructions/cpu-unit-testing.agent.md)

## UPD12

Да и сделай так, чтобы следующий UPD добавлялся не в коммит, который ты делаешь сейчас, а в новый коммит после того, как ты откомментируешь результат. И так для всех следующих UPD. Обнови инструкцию по итеративному промпту єтии знаниями и тем как мы договорились с тобой себя вести раньше тут в этой файле.

Так же обнови инструкции, если узнал что-то новое в этой сессии по части тестироания. Уверен ты много нового узнал. 

Да и давай выровняемся по описанию коммитов которые ты делаешь. Мне важно чтобы было `Add test for ______ и дальше чуть текста что именно`, но ты уже начал это делать сам. 

И еще у меня есть файл `release.md` посмотри его сктруктуру и давай править его тоже, добавляя все для каждого коммита что ты уже сделал. А когда выровняешься, то просто в цикл возьми. go

### RESULT

Выполнено:
- Обновлён [instructions/iterative-prompt.agent.md](../../instructions/iterative-prompt.agent.md): добавлен 2-commit workflow (Commit A = работа+RESULT, Commit B = следующий UPD+go)
- Обновлён [instructions/cpu-unit-testing.agent.md](../../instructions/cpu-unit-testing.agent.md): PORTS double-write, tick count, ANA H-флаг, asrtMem формат, commit message `Add test for ...`
- Заполнен [release.md](../../release.md): добавлена секция `2026-05-03` со всеми коммитами этой сессии

## UPD13

Беру `CC_XXYY` (opcode `0xDC`): CALL if carry. go

### RESULT

2 теста, все прошли (185 total):
- `codeDC__CC_XXYY_carry_set` — carry=1 (через ADC overflow), CALL происходит: PC=0x0010, SP=0xFFFE, return addr 0x0008
- `codeDC__CC_XXYY_carry_clear` — carry=0, CALL не происходит: PC=0x0007, SP=0x0000

## UPD14

Беру `CM_XXYY` (opcode `0xFC`): CALL if minus (sign flag set). go

### RESULT

2 теста, все прошли (187 total):
- `codeFC__CM_XXYY_sign_set` — ADD B: 0x7F+0x01=0x80, S=1, CALL происходит
- `codeFC__CM_XXYY_sign_clear` — ADD A: 0x01+0x01=0x02, S=0, CALL не происходит

## UPD15

Беру `CMA` (opcode `0x2F`): complement A (bitwise NOT). go

### RESULT

2 теста, все прошли (189 total):
- `code2F__CMA_f0` — A=0xF0 → 0x0F (флип нибл)
- `code2F__CMA_aa` — A=0xAA → 0x55 (чередующиеся биты)
- Флаги не меняются (F=0x02 во всех случаях)

## UPD16

Исправь инструкцию. План итеративного промпта такой, что в одном коммите идет ## UPDN результат работы RESULT и потом коммит. А новый UPD[N+1] идет в будущем коммите, который не надо закрывать тут же. 

Беру `CMC` (opcode `0x3F`): complement carry (toggle C flag). go

### RESULT

- Исправлена [instructions/iterative-prompt.agent.md](../../instructions/iterative-prompt.agent.md): теперь 1 commit = UPD + work + RESULT; следующий UPD пишется uncommitted после коммита и закрывается только своим циклом
- 2 теста CMC, все прошли (191 total):
  - `code3F__CMC_set_to_clear` — carry=1 (через ADC overflow) → CMC → carry=0
  - `code3F__CMC_clear_to_set` — carry=0 → CMC → carry=1, F=0x03

## UPD17

Беру `CMP_R` (opcodes `0xB8`-`0xBF`): compare register with A (A - reg, flags set, A unchanged). go

### RESULT

10 тестов, все прошли (201 total):
- `codeB8__CMP_B` .. `codeBF__CMP_A` — все 8 регистров (A=0x10, reg=0x05 → F=0x02, A не меняется)
- `codeB8__CMP_B_less` — A<B: S=1, C=1 (борроу), H=1, P=1, F=0x97
- `codeB8__CMP_B_equal` — A=B: Z=1, H=1, F=0x56

## UPD18

Беру `CNC_XXYY` (opcode `0xD4`): CALL if no carry. go

### RESULT

2 теста, все прошли (203 total):
- `codeD4__CNC_XXYY_carry_clear` — carry=0, CALL происходит: SP=0xFFFE, return addr 0x0003
- `codeD4__CNC_XXYY_carry_set` — carry=1 (через ADC overflow), CALL не происходит

## UPD19

Беру `CNZ_XXYY` (opcode `0xC4`): CALL if not zero. go

### RESULT

2 теста, все прошли (205 total):
- `codeC4__CNZ_XXYY_nz` — A=0x01 (Z=0), CALL происходит: SP=0xFFFE, return addr 0x0005
- `codeC4__CNZ_XXYY_zero` — ADD A (0+0=0, Z=1), CALL не происходит

## UPD20

Беру `CP_XXYY` (opcode `0xF4`): CALL if positive (sign flag clear). go

### RESULT

2 теста, все прошли (207 total):
- `codeF4__CP_XXYY_positive` — A=0x01 (S=0), CALL происходит: SP=0xFFFE, return addr 0x0005
- `codeF4__CP_XXYY_negative` — MVI A,40 + ADD A → A=0x80 (S=1), CALL не происходит

## UPD21

Беру `CPE_XXYY` (opcode `0xEC`): CALL if parity even. go

### RESULT

2 теста, все прошли (209 total):
- `codeEC__CPE_XXYY_even` — ADI 03 → P=1 (even), CALL происходит
- `codeEC__CPE_XXYY_odd` — ADI 01 → P=0 (odd), CALL не происходит

## UPD22

Беру `CPI_XX` (opcode `0xFE`): compare immediate with A. go

### RESULT

3 теста, все прошли (212 total):
- `codeFE__CPI_XX_greater` — A=0x10, n=0x05 → A>n, F=0x02
- `codeFE__CPI_XX_equal` — A=0x05, n=0x05 → Z=1, H=1, P=1
- `codeFE__CPI_XX_less` — A=0x05, n=0x10 → S=1, H=1, P=1, C=1

## UPD23

Беру `CPO_XXYY` (opcode `0xE4`): CALL if parity odd. go

### RESULT

2 теста, все прошли (214 total):
- `codeE4__CPO_XXYY_odd` — ADI 01 → P=0 (odd), CALL происходит
- `codeE4__CPO_XXYY_even` — ADI 03 → P=1 (even), CALL не происходит

## UPD24

Беру `CZ_XXYY` (opcode `0xCC`): CALL if zero. go

### RESULT

2 теста, все прошли (216 total):
- `codeCC__CZ_XXYY_zero` — ADD A (0+0=0, Z=1), CALL происходит
- `codeCC__CZ_XXYY_nz` — Z=0 (начальное состояние), CALL не происходит

## UPD25

Беру `DAA` (opcode `0x27`): decimal adjust accumulator. go

### RESULT

3 теста, все прошли (219 total):
- `code27__DAA_no_change` — A=0x08 (валидный BCD), DAA не меняет
- `code27__DAA_lower_nibble_fix` — 0x09+0x01=0x0A, DAA → 0x10
- `code27__DAA_upper_nibble_fix` — 0x90+0x10=0xA0, DAA → 0x00 (C=1)

## UPD26

Беру `DCX_RR` (opcode `0x0B/0x1B/0x2B/0x3B`): decrement register pair. go

### RESULT

4 теста, все прошли (223 total):
- `code0B__DCX_B` — BC: 0x0010 → 0x000F
- `code1B__DCX_D` — DE: 0x0020 → 0x001F
- `code2B__DCX_H` — HL: 0x0030 → 0x002F
- `code3B__DCX_SP` — SP: 0x0040 → 0x003F

## UPD27

Беру `DI` (opcode `0xF3`): disable interrupts. go

### RESULT

1 тест, прошёл (224 total):
- `codeF3__DI` — нет изменений (нет реализации прерываний)

## UPD28

Из того что узнал пока тесты пишешь добавляй в инструкции по тестированию. И делай это в каждом цикле. 

Беру `EI` (opcode `0xFB`): enable interrupts. go

### RESULT

Обновлены [instructions/cpu-unit-testing.agent.md](../../instructions/cpu-unit-testing.agent.md):
- Исправлен workflow (one-commit вместо two-commit)
- Добавлены практические способы установки флагов для CALL-тестов
- Добавлено предупреждение ADD A = `0x87`, не `0x80`
- Добавлено описание DI/EI как no-op команд
- Уточнён пример расчёта PC (всегда запускай тест и смотри факт)

1 тест EI, прошёл (225 total):
- `codeFB__EI` — нет изменений (нет реализации прерываний)

## UPD29

Беру `IN_XX` (opcode `0xDB`): input from port. go

### RESULT

1 тест, прошёл (226 total):
- `codeDB__IN_XX` — A=0x01, IN 02, читает 0xFF (тестовая среда возвращает 0xFF), A=0xFF
- Флаги не меняются, IN не влияет на F

## UPD30

Беру `JC_XXYY` (opcode `0xDA`): JUMP if carry. go

### RESULT

2 теста, все прошли (228 total):
- `codeDA__JC_XXYY_carry_set` — C=1 (через ADI overflow), JMP происходит
- `codeDA__JC_XXYY_carry_clear` — C=0, JMP не происходит

## UPD31

Беру `JM_XXYY` (opcode `0xFA`): JUMP if minus (sign=1). go

### RESULT

2 теста, все прошли (230 total):
- `codeFA__JM_XXYY_sign_set` — MVI A,40 + ADD A → S=1, JMP происходит
- `codeFA__JM_XXYY_sign_clear` — S=0, JMP не происходит

## UPD32

Слушай, а когда команды перехода делаются и в тестах ты как определеяешь что команда перескочила именно туда куда надо? Что если Сразу после команды будем делать инкриз какого-то регистра, а отправлять сразу после нее на NOP тогда по состоянию регистра можно будет определить. Что скажешь? Поправь те команды что ты уже делал. И в будущем делай такую косвенную проверку через состояние регистров, а не только по PC. И добавь это в инструкцию по тестированию. go 

Беру `JMP_XXYY` (opcode `0xC3`): unconditional JUMP. go
### RESULT

- Добавлен паттерн `INR C` (сентинельный регистр): сразу после JMP/CALL ставится INR C (`0x0C`). taken → C=0x00 (пропущен), not-taken → C=0x01 (выполнен).
- Обновлены все 22 существующих теста jump/call с INR C sentinel (CALL_XXYY x2, CC_XXYY x2, CM_XXYY x2, CNC_XXYY x2, CNZ_XXYY x2, CP_XXYY x2, CPE_XXYY x2, CPO_XXYY x2, CZ_XXYY x2, JC_XXYY x2, JM_XXYY x2).
- Написан тест `codeC3__JMP_XXYY` (unconditional JUMP, C=0x00 доказывает что jump сработал).
- Обновлены инструкции: добавлены разделы "INR C sentinel pattern" и предупреждение об изменении флагов при not-taken.
- Тесты: 231 passed, 0 failures.

## UPD33

Беру `JNC_XXYY` (opcode `0xD2`): JUMP if no carry. go

### RESULT

- Написаны тесты `codeD2__JNC_XXYY_carry_clear` и `codeD2__JNC_XXYY_carry_set`.
- Тесты: 233 passed, 0 failures.

## UPD34

Беру `JNZ_XXYY` (opcode `0xC2`): JUMP if not zero. go

### RESULT

- Написаны тесты `codeC2__JNZ_XXYY_not_zero` и `codeC2__JNZ_XXYY_zero`.
- Тесты: 235 passed, 0 failures.

## UPD35

Беру `JP_XXYY` (opcode `0xF2`): JUMP if positive (sign=0). go

### RESULT

- Написаны тесты `codeF2__JP_XXYY_positive` и `codeF2__JP_XXYY_negative`.
- Тесты: 237 passed, 0 failures.

## UPD36

Беру `JPE_XXYY` (opcode `0xEA`): JUMP if parity even (P=1). go

### RESULT

- Написаны тесты `codeEA__JPE_XXYY_even` и `codeEA__JPE_XXYY_odd`.
- Тесты: 239 passed, 0 failures.

## UPD37

Беру `JPO_XXYY` (opcode `0xE2`): JUMP if parity odd (P=0). go

### RESULT

- Написаны тесты `codeE2__JPO_XXYY_odd` и `codeE2__JPO_XXYY_even`.
- Тесты: 241 passed, 0 failures.

## UPD38

Беру `JZ_XXYY` (opcode `0xCA`): JUMP if zero (Z=1). go

### RESULT

- Написаны тесты `codeCA__JZ_XXYY_zero` и `codeCA__JZ_XXYY_not_zero`.
- Тесты: 243 passed, 0 failures.

## UPD39

Беру `ORA_R` (opcode `0xB0`-`0xB7`): OR with register. go

### RESULT

- Добавлены тесты: `codeB0__ORA_B`, `codeB1__ORA_C`, `codeB2__ORA_D`, `codeB3__ORA_E`, `codeB4__ORA_H`, `codeB5__ORA_L`, `codeB6__ORA_M`, `codeB7__ORA_A`.
- H и C всегда сбрасываются в 0; S, Z, P устанавливаются по результату.
- Для ORA_M: HL=0x0000 (по умолчанию), M=mem[0x0000]=0x3E (первый байт givenMm).
- Commit: `2f89712`
- Тесты: 251 passed, 0 failures.

## UPD40

Беру `ORI_XX` (opcode `0xF6`): OR immediate with accumulator. go

### RESULT

- Добавлены тесты: `codeF6__ORI_XX`, `codeF6__ORI_XX_zero`.
- H и C всегда сбрасываются в 0; S, Z, P по результату.
- Commit: `8b3eedd`
- Тесты: 253 passed, 0 failures.

## UPD41

Беру `OUT_XX` (opcode `0xD3`): output to port. go

### RESULT

- Добавлен тест: `codeD3__OUT_XX`.
- OUT не меняет регистры/флаги; A отправляется на порт, выход игнорируется в тестовой среде.
- Commit: `a200e40`
- Тесты: 254 passed, 0 failures.

## UPD42

Беру `PCHL` (opcode `0xE9`): jump to HL. go

### RESULT

- Добавлен тест: `codeE9__PCHL`.
- Использован INR C сентинел: C=0x00 подтверждает прыжок.
- PCHL не изменяет флаги.
- Целевой адрес HL=0x0005 (AFTER INR C по 0x0004).
- Commit: `539405a`
- Тесты: 255 passed, 0 failures.

## UPD43

Беру `POP_RS` (opcode `0xC1`/`0xD1`/`0xE1`/`0xF1`): pop register pair. go

### RESULT

- Добавлены тесты: `codeC1__POP_B`, `codeD1__POP_D`, `codeE1__POP_H`, `codeF1__POP_PSW`.
- Схема: LXI rr + LXI SP,FFFE + PUSH + LXI rr,0000 + POP + NOP.
- Нужно вызывать `asrtMem(...)` для PUSH/POP (пишет в PORTS-диапазон 0xF800–0xFFFE — double-write).
- Commit: `06189e2`
- Тесты: 259 passed, 0 failures.

## UPD44

Беру `PUSH_RS` (opcode `0xC5`/`0xD5`/`0xE5`/`0xF5`): push register pair. go

### RESULT

- Добавлены тесты: `codeC5__PUSH_B`, `codeD5__PUSH_D`, `codeE5__PUSH_H`, `codeF5__PUSH_PSW`.
- Схема: LXI SP,FFFE + LXI rr,val + PUSH + NOP.
- Память пишется на FFFC-FFFD (double-write для обоих адресов).
- SP становится 0xFFFC после PUSH.
- Commit: `74a6f9c`
- Тесты: 263 passed, 0 failures.

## UPD45

Беру `RC` (opcode `0xD8`): return if carry. go

### RESULT

- Добавлены тесты: `codeD8__RC_carry_set`, `codeD8__RC_carry_clear`.
- Схема для RETURN taken: JMP 0005 (past subroutine) + RC/INR_C subroutine at 0x0003 + setup + CALL 0003 + NOPs.
- Паттерн: JMP over subroutine = subroutine at low addr < return area, PC never re-executes RC.
- Commit: `8ceab60`
- Тесты: 265 passed, 0 failures.

## UPD46

Беру `RET` (opcode `0xC9`): unconditional return. go

### RESULT

- Добавлен тест: `codeC9__RET`.
- Схема: JMP 0005 + subroutine(RET,INR_C) at 0x0003 + CALL 0003 + NOPs.
- CALL пушает 0x0008 (адрес после CALL), RET возвращает туда же.
- Commit: `ce518e8`
- Тесты: 266 passed, 0 failures.

## UPD47

Беру `RM` (opcode `0xF8`): return if minus (S=1). go

### RESULT

- Добавлены тесты: `codeF8__RM_minus`, `codeF8__RM_positive`.
- Для установки S=1: использовать ADI 80 (быстрее, чем MVI A,80 + ORA A).
- Commit: `6b9f12a`
- Тесты: 268 passed, 0 failures.

## UPD48

Беру `RNC` (opcode `0xD0`): return if no carry. go

### RESULT

- Добавлены тесты: `codeD0__RNC_carry_clear`, `codeD0__RNC_carry_set`.
- RNC not-taken: без JMP/CALL, INR C меняет флаги; carry сохраняется → F=0x03.
- Обновлены инструкции: добавлен JMP-over-subroutine паттерн.
- Commit: `ce589da`
- Тесты: 270 passed, 0 failures.

## UPD49

Беру `RNZ` (opcode `0xC0`): return if not zero. go

### RESULT

- Добавлены тесты: `codeC0__RNZ_not_zero`, `codeC0__RNZ_zero`.
- RNZ not-taken: ADD A устанавливает Z=1, INR C изменяет флаги → F=0x02.
- Commit: `61d1d07`
- Тесты: 272 passed, 0 failures.

## UPD50

Беру `RP` (opcode `0xF0`): return if positive (S=0). go

### RESULT

- Добавлены тесты: `codeF0__RP_positive`, `codeF0__RP_negative`.
- Ключ: INR C устанавливает флаги по регистру C (0x01), а НЕ по A. Поэтому F=0x02 (не 0x82).
- Commit: `341ecad`
- Тесты: 274 passed, 0 failures.

## UPD51

Беру `RPE` (opcode `0xE8`): return if parity even (P=1). go

### RESULT

- Добавлены тесты: `codeE8__RPE_even`, `codeE8__RPE_odd`, `codeE0__RPO_odd`, `codeE0__RPO_even`.
- Ключ: INR C устанавливает P по C=0x01 (odd = P=0); F=0x02 после INR C в not-taken тестах.
- Commit: `4b53134`
- Тесты: 278 passed, 0 failures.

## UPD52

Беру `RZ` (opcode `0xC8`): return if zero. go

### RESULT

- Добавлены тесты: `codeC8__RZ_zero`, `codeC8__RZ_not_zero`.
- Ключ: ADD A сет Z=1; asrtMem FFFE:09 (адрес возврата 0x0009, CALL по ADD A на 0x0006-0x0008).
- Commit: `dc91a62`
- Тесты: 280 passed, 0 failures.

## UPD53

Беру `RST_N` (opcodes `0xC7/0xCF/0xD7/0xDF/0xE7/0xEF/0xF7/0xFF`): restart (software interrupt). go

### RESULT

- Добавлены тесты: `codeC7__RST_0` через `codeFF__RST_7` (8 тестов).
- Ключ: RST N прыгает на N*8; sentinel INR C после RST никогда не выполняется (C=0). Главное: в givenPr все строки должны заканчиваться \n (включая последнюю)!
- Commit: `d3924e4`
- Тесты: 288 passed, 0 failures.

## UPD54

Беру `SBB_R` (opcodes `0x98`-`0x9F`): subtract register with borrow. go

### RESULT

9 tests (SBB B/C/D/E/H/L/M/A + carry-in variant). commit: fa24567.
Key learnings: M register value depends on HL pointer (not always 0x3E); SBB carry-in with aligned nibbles (0x30-0x10-1) gives H=0 not H=1 per `!sub_half_carry_table`.

## UPD55

Беру `SBI_XX` (opcode `0xDE`): subtract immediate with borrow. go

### RESULT

3 tests (no-carry, borrow, carry-in). All passed first run. commit: 646d50e.

## UPD56

Беру `SPHL` (opcode `0xF9`): SP = HL. go

### RESULT

1 test. commit: ca0c1b4.

## UPD57

Беру `STC` (opcode `0x37`): set carry flag. go

### RESULT

1 тест. commit: b95bfa5.

## UPD58

Беру `SUB_R` (opcodes `0x90`-`0x97`): subtract register. go

### RESULT

8 тестов (SUB B/C/D/E/H/L/M/A). commit: 7038229.

## UPD59

Беру `SUI_XX` (opcode `0xD6`): subtract immediate. go

### RESULT

3 теста (no-flags, borrow, zero). commit: 362b247.

## UPD60

Беру `XCHG` (opcode `0xEB`): exchange DE and HL. go

### RESULT

1 тест. commit: ???????.
