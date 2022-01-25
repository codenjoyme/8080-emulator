Зачем старались?
----------------
Хотелось написать свой эмулятор БК ЛИК. Ибо ностальгия. Начал даже эмулировать процессор, 
но позже нашел готовое решение на [страничках форума](http://www.nedopc.org/forum/viewtopic.php?f=90&t=9475).
Так как Java ушла далеко вперед и Applets уже не в моде, после серии попыток 
запустить этот проект в Intellij Idea было решено мигрировать его. Google Driven Development 
подсказал, что проще всего будет перевести на JNLP технологию (которая так же устарела,
но в отличие от Applets еще может быть запущена в современных браузерах).
Два вечера и завелось. В будущем проект будет переведен на более модерновые UI, 
но пока в нем тестов нет рефачить опасно. 

План действий
-------------
- [x] Завести проект в любом виде.
- [x] Перевести проект c Ant на Maven.
- [x] Запустить на нем Монитор-1М / Basic ЛИК V2.
- [x] Запустить на нем игру Клад.
- [x] Снабдить документацией.
- [ ] Восстановить историческую связь с https://github.com/begoon/jasper  
- [ ] И тут https://web.archive.org/web/20061229151903/http://www.spectrum.lovely.net/jasp1_1j.zip
- [ ] Изучить так же правки сделанные Автором тут https://github.com/Arlorean/Jasper
- [ ] И тут немного истории есть https://github.com/arlorean/casper
- [ ] Определиться с наследуемой лицензией и настроить maven плагин.
      Причем сообщить, что игры не распостраняются под той же лицензией.
- [x] Отформатировать проект по JCC, сохранив всю документацию.
- [ ] Сделать переключение ROM и загрузку RKS на лету, без перезапуска сервера.
- [x] Покрыть тестами.
- [x] Принять решение, что делать со старым Spectrum кодом.
- [x] Отдебажить, почему не работает игра Клад. 
- [ ] Отдебажить, почему Basic глючит (сравнивая с эталонным эмулятором emu80v4). 
- [x] Перевести на модерновый UI фреймворк. Был выбран Swing и JNLP + Swing. 
      Теперь можно запускать приложение c UI из IDE и дебажить в нем.
- [x] Добавлены тесты а-ля "Экзорцист".
- [ ] По тестам "Экзорцист" надо убедиться что все команды работают. 
- [ ] Добавить поддержку звука.
- [ ] Сделать возможным по рисайзу скейлить окно с содержимым. 
- [x] Разделить GODObjects на компоненты.
- [x] Отрефакторить видео логику, там ужас сейчас как не просто.
- [x] Реализовать сохранение скриншота в png файл. 
- [x] Написать интеграционные тесты, которые будут тестить разные игрушки на большом 
      количестве тиков.
- [x] Код сильно отрефакторен. Удален старый код ZX Spectrum, расширенные
      команды Z80, фичи, которые пока что не сильно нужны. Сильно упрощал
      везде, где только можно. 
- [x] Реализовать ассемблер/дизассемблер для удобного превращения hex кодов в asm и назад.
- [ ] Закончить с портированием команд.
- [ ] Реализовать в TestMemory возможность проверять какие участки памяти поменялись.
- [ ] Реализовать возможность трейсить выполнение CPU в рантайме.
- [ ] Возможность определять зацикливания, вход/выход в процедуры.
- [x] Разобраться как работает IOPorts.
- [x] Поправить все клавиши клавиатуры (они не работали корректно).
- [x] Покрыть IOPorts тестами с максимальным кавераджем и порефакторить. 
- [x] Сделать возможным в интеграционных тестах нажимать (програмно) клавиши.
- [ ] Перевести все найденные в сети игрушки по Лику в репозиторий, снабдить описанием. 
- [ ] Возможность дебажить на UI.
- [x] Сделать два дружественных скрипта для сборки приложения с запуском jnlp сервера и для 
      запуска клиентского приложения.
- [ ] Сделать метод печатающий в png заданной высота Range из памяти - полезно будет для отладки.    
- [x] Реализовать возможность record/replay как прототип.
- [x] Сделать record/replay с форматом записи в файл соответствующего типа. 
      Record автоматически просиходит при запуске эмулятора. А вот replay по клавише 
      '/' в области NumLock.
- [x] Сделать по хоткею ('*' в области NumLock) ускорение/замедление эмуляции системы. 
- [ ] Сделать что-то еще...
- [ ] И еще что-то...

Благодарности
-------------
- Проект взят тут [http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip](http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip).
- Спасибо Lavr за проделанную работу и подробные комментарии. 
- Спасибо всем по цепочке fork'ов, благодаря которым эту код базу могу подхватить дальше.     
- Спасибо Организаторам и комьюнити [форума nedopc.org](http://www.nedopc.org/forum).
- Спасибо Организаторам и комьюнити [форума zx-pk.ru](https://zx-pk.ru).
- Спасибо svofski за чудный [assembler редактор](https://svofski.github.io/pretty-8080-assembler/).
- Спасибо Viktor Pykhonin (vpyk) за [крутой эмулятор](https://github.com/vpyk/emu80v4).
- Спасибо моему Папе за то, что привил мне любовь к инженерии.
- Спасибо инженерам завода 'Электронмаш' за ЛИК и игру Клад.
- Спасибо Oracle за Java.
- Спасибо Markdown за то, что я не мучаюсь с версткой.

Предустановка окружеия
-----------------
- Для запуска необходимо установить java 1.8 версии.
- Прописать переменную окружения JAVA_HOME ссылающуюся на папку с установленной java.
- Затем добавить в Path переменную `JAVA_HOME/bin`.
- Выполнить команду в консоли `java -version` и увидеть версию java.

Запуск клиентского приложения 
----------------- 
- Для этого стоит запустить скрипт `build/build-client.sh`. Если нет linux можно
  использовать тулы типа `cygwin`/`mingw`.
- В процессе сборки создастся папка `build/out` в которой будет все необходимое 
  для запуска. Скрипт `build/out/run.sh` запустится автоматически. 
  В будущем не стоит пересобирать все приложение - для запуска достаточно 
  запуска скрипта `build/out/run.sh`. 

Запуск jnlp сервера 
----------------- 
- Для этого стоит запустить скрипт `build/build-server.sh`. Если нет linux можно 
  использовать тулы типа `cygwin`/`mingw`.
- Скрипт в какой-то момент остановится (сервер запустился) - 
  окно закрывать не стоит, его закрытие приведет к остановке сервера.
```
[INFO] Started ServerConnector@697c9218{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
[INFO] Started Server@15200332{STARTING}[11.0.7,sto=0] @8448ms
```
- После стоит проследовать в `JAVA_HOME/bin` и запустить там `javacpl`.
- В tab `Security` 
  + Поставить галочку `Enable Java content for browser and Web Start applications`.
  + Выбрать опцию `Very high` для `Security level for applications not on the Exception Site list`.
  + Добавить в `Exception Site list` новый сайт `http://localhost:8080/`
- В tab `Advanced`
  + В разделе `Debugging` выбрать галочки `Enable tracing`, `Enable logging` и `Show applet lifecycle exceptions`.
  + В разделе `Java console` выбрать опцию `Show console`.
- Остальное оставляем без изменений. Кликаем `Apply` и закрываем окно. 
- Так мы настроили вывод логов из Applet в консоль, которая откроется при запуске приложения.
- Направляемся в браузер и набираем в адресной строке `http://localhost:8080/`. 
  В этот момент Applet незамедлительно попробует загрузиться.
- Для другого URL необходимо править файл `build-server.sh` в 
  области 
```
eval_echo "HOST=localhost"
eval_echo "PORT=8080"
```
- Если агент безопасности спросит можно ли это сделать - разрешаем ему открыть приложение.
- Если сервер более не нужен - стоит нажать Ctrl-C в консоли, иначе если просто закрыть 
  окно консоли - процесс будет висеть в памяти, не давая запустить сервер второй раз. 

Работа в окне эмулятора
-----------------
- Приветствуем надпись `* RUN"COM:"`
- Жмем на клавиатуре клавишу `Pause Break`, затем `End`, затем `Enter`.
- Приветствуем надпись
```
* RUN"COM:"
* МОНИТОР-1М *
===>
``` 
- Нажимаем клавишу `B`, затем `Enter`.
- Приветствуем надпись
```
* BASIC ЛИК V2 *
OK
``` 
- Нажимаем на клавиатуре клавишу `Pause Break`, затем `End`, затем `Enter`.
- Нажимаем на клавиатуре клавишу `J`, затем `Enter`. 
- Радуемся игре `Клад`. 

Загрузка других ROM/RKS
-----------------------
- В методе `spec.Application.loadRoms()` можно поменять настройки загружаеых ROM
```
boolean lik = true;
if (lik) {
    Lik.loadRom(base, hard.roms());
    Lik.loadGame(base, hard.roms(), "klad");
    // Lik.loadTest(base, hard.roms(), "test");
} else {
    Specialist.loadRom(base, hard.roms());
    Specialist.loadGame(base, hard.roms(), "blobcop");
}

```
- Отредактировав его можно выбрать любой ROM/RKS загрузить его в определенное место памяти.
- После сервер jetty стоит перезапустить, закрыть старый Applet и открыть его повторно.
- В Java консоли Applet можно увидеть как и куда происходит загрузка
```
network: Cache entry not found [url: http://localhost:8080/lik/01_zagr.BIN, version: null]
network: Connecting http://localhost:8080/lik/01_zagr.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/01_zagr.BIN' into [C000:C800]
network: Cache entry not found [url: http://localhost:8080/lik/02_mon-1m.BIN, version: null]
network: Connecting http://localhost:8080/lik/02_mon-1m.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/02_mon-1m.BIN' into [C800:D000]
network: Cache entry not found [url: http://localhost:8080/lik/03_mon-1m_basicLik.BIN, version: null]
network: Connecting http://localhost:8080/lik/03_mon-1m_basicLik.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/03_mon-1m_basicLik.BIN' into [D000:D800]
network: Cache entry not found [url: http://localhost:8080/lik/04_basicLik.BIN, version: null]
network: Connecting http://localhost:8080/lik/04_basicLik.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/04_basicLik.BIN' into [D800:E000]
network: Cache entry not found [url: http://localhost:8080/lik/05_basicLik.BIN, version: null]
network: Connecting http://localhost:8080/lik/05_basicLik.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/05_basicLik.BIN' into [E000:E800]
network: Cache entry not found [url: http://localhost:8080/lik/06_basicLik.BIN, version: null]
network: Connecting http://localhost:8080/lik/06_basicLik.BIN with proxy=DIRECT
Loading 'http://localhost:8080/lik/06_basicLik.BIN' into [E800:F000]
network: Cache entry not found [url: http://localhost:8080/lik/apps/klad.rks, version: null]
network: Connecting http://localhost:8080/lik/apps/klad.rks with proxy=DIRECT
Loading 'http://localhost:8080/lik/apps/klad.rks' into [0000:4430]
cache: MemoryCache: removed entry http://localhost:8080/spec.jnlp
```

От Авторов
--------------

BASIC [http://www.nvg.unit.no/sinclair/planet/faq.htm](Online Manuals)

If you click on the progress bar at the foot of the screen, it will speed
up the emulator although on some systems you may find that this locks up
your browser. If it works, subsequent clicks on the progress bar will toggle
between Fast and Slow modes. In Fast mode the Spectrum will try to consume
as much CPU time as it can, making the Spectrum run as fast as possible. In
Slow mode if the Spectrum is running over 100% (faster than the
original Spectrum) it will try to slow itself down to be closer to 100%.

The source code is available online from 
[http://www.spectrum.lovely.net/jasp1_1j.zip](here). 
The AMDProgressBar class was written by [http://www.cs.brown.edu/people/amd/](Adam Doppelt).

Comments: [mailto:spectrum@odie.demon.co.uk](spectrum@odie.demon.co.uk).

Enjoy!