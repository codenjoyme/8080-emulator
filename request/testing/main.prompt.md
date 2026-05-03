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

Первая команда из TODO — `ACI_XX`. Беру её в UPD4.