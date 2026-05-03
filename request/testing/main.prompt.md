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


