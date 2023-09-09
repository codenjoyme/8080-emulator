Intro
-----

Этот проект создался как инструмент для решения более общей задачи - реверс-инжиниринг
Бытового Компьютера Лик производства завода Электронмаш, г. Черновцы в 1988 году. 

Данная тема была впервые мной заявлена 
[на форуме https://zx-pk.ru](https://zx-pk.ru/threads/29118-vse-pro-quot-lik-quot-(modifikatsiya-spetsialista).html)
2 мая 2018 года. С тех пор я периодически (запоями) делаю набеги к задачам. 

Мое путешествие в мир компьютерной инженерии начался с игр на этом компьютере. 
После того как игры перестали загружаться с магнитофонных кассет мне пришлось 
освоить BASIC. А после того, как ПЗУ микросхема с BASIC начала сбоить и Basic больше 
не загружался мне пришлось освоиться и в Assembler. Это было очень увлекательно. 
Препарирование игрушек, чтобы разобраться как там все устроено. Ночные кодинги и дебаги 
перед маленьким мониторчиком, от которого жутко болели глаза. 

Сам компьютер достаточно редкий даже в кругу ценителей ретро компьютеров. Я свой экземпляр 
искал 7 лет. Вскоре он приедет ко мне, что даст толчок в реверс-инжиниринге.  

Я хочу вернуть молодость! 

Так же мне давно хотелось написать свой эмулятор БК Лик на java. 
В прошлом я совершал попытки эмулировать процессор и частично реализовал команды,
но позже нашел готовое решение на [страничках форума](http://www.nedopc.org/forum/viewtopic.php?f=90&t=9475) 
и порадовался, так как всю эту работу уже сделали до меня. С этого момента начался разбор сырцов. 

Так как Java ушла далеко вперед и Applets уже не в моде, после серии попыток
запустить этот проект в Intellij Idea было решено мигрировать его. Google Driven Development
подсказал, что проще всего будет перевести на JNLP технологию (которая так же устарела,
но в отличие от Applets еще может быть запущена в современных браузерах).
Два вечера и завелось. В будущем проект будет переведен на более модерновые UI,
но пока в нем тестов нет рефачить опасно.

Позже код был отрефакторен полностью, так что связать его с оригинальным кодом
практически не возможно. Мне очень хочется отметить вклад всех Авторов,
а потому был создан этот раздел и все коммиты, которые я смог найти на просторах сети
были добавлены в git history этого проекта. В OpenSource считаю важно не только
делиться, но и помнить всех, кто так или иначе повлиял на тебя в прошлом.

Спасибо всем Авторам!

План действий
-------------
- [x] Завести проект в любом виде.
- [x] Перевести проект c Ant на Maven.
- [x] Запустить на нем Монитор-1М / Basic ЛИК V2.
- [x] Запустить на нем игру Клад.
- [x] Снабдить документацией.
- [x] Восстановить историческую связь с https://github.com/begoon/jasper  
- [x] И тут https://web.archive.org/web/20061229151903/http://www.spectrum.lovely.net/jasp1_1j.zip
- [x] Изучить так же правки сделанные Автором тут https://github.com/Arlorean/Jasper
- [x] И тут немного истории есть https://github.com/arlorean/casper
- [ ] Определиться с наследуемой лицензией и настроить maven плагин.
      Причем сообщить, что игры не распостраняются под той же лицензией.
- [x] Отформатировать проект по JCC, сохранив всю документацию.
- [x] Сделать переключение ROM и загрузку RKS на лету, без перезапуска сервера по хоткею
      ',' в области NumLock загружаются RKS программы, а по хоткею '0' в области NumLock 
      переключается ПЗУ Лик/Специалист. 
- [ ] Пофиксить серверную версию после добавление OpenFileDialog. 
- [x] Покрыть тестами.
- [x] Принять решение, что делать со старым Spectrum кодом.
- [x] Отдебажить, почему не работает игра Клад. 
- [x] Отдебажить, почему Basic глючит (сравнивая с эталонным эмулятором emu80v4). 
- [x] Перевести на модерновый UI фреймворк. Был выбран Swing и JNLP + Swing. 
      Теперь можно запускать приложение c UI из IDE и дебажить в нем.
- [x] Добавлены тесты а-ля "Экзорцист".
- [x] По тестам "Экзорцист" надо убедиться что все команды работают.
- [x] Запустить и отладить второй тест "Экзорцист".
- [ ] Запустить и отладить третий тест "Экзорцист". 
- [ ] Запустить и отладить четвертый тест "Экзорцист". 
- [ ] Попробовать в бейсике запустить какую-то большую игру и подебажить ошибки. 
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
- [x] Закончить с портированием команд.
- [ ] Дописать юнит тесты для каждой не протестированной команды.
- [ ] Реализовать в TestMemory возможность проверять какие участки памяти поменялись.
- [x] Реализовать возможность трейсить выполнение CPU в рантайме.
- [x] Возможность определять зацикливания, вход/выход в процедуры.
- [x] Разобраться как работает IOPorts.
- [x] Поправить все клавиши клавиатуры (они не работали корректно).
- [x] Покрыть IOPorts тестами с максимальным кавераджем и порефакторить. 
- [x] Сделать возможным в интеграционных тестах нажимать (програмно) клавиши.
- [x] Перевести все найденные в сети игрушки по Лику в репозиторий.
- [ ] Добавить описание к игрушкам Лика.
- [ ] Найти еще игрушек для Специалиста.
- [ ] Возможность дебажить на UI.
- [x] Сделать два дружественных скрипта для сборки приложения с запуском jnlp сервера и для 
      запуска клиентского приложения.
- [ ] Сделать метод печатающий в png заданной высота Range из памяти - полезно будет для отладки.    
- [x] Реализовать возможность record/replay как прототип.
- [x] Сделать record/replay с форматом записи в файл соответствующего типа. 
      Record автоматически просиходит при запуске эмулятора. А вот replay по клавише 
      '/' в области NumLock.
- [x] Сделать по хоткею ('*' в области NumLock) максимальное ускорение/замедление эмуляции системы.
- [x] Сделать по хоткею ('+', '-' в области NumLock) возможность плавно наращивать/уменьшать
      скорость эмуляции системы.
- [x] Реализовать более умную остановку в тесте по достижению некоторого условия, 
      а не просто спустя 10M тиков.
- [x] Существенно улучшить производительность (как cpu, так и отрисовки).
- [x] В интеграционных тестах большие trace/cpu assert блоки вынести в отдельные файлы, как с png.
- [x] Реализована загрузка rks файлов с помощью эмулятора из OS. 
- [x] Сделать возможным после запуска rks собрать статистическую информацию, в каких ячейках 
      бывал процессор, дабы понять, где есть программа, а где данные. Это на следующем этапе 
      позволит  
- [ ] Сделать что-то еще...
- [ ] И еще что-то...

Благодарности
-------------
- Проект иначально взят тут [http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip](http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip),
  но у него большая история.   
- Спасибо всем по цепочке fork'ов, благодаря которым эту код базу могу подхватить дальше. 
  * [Clive Sinclair](https://en.wikipedia.org/wiki/Clive_Sinclair) за создание 
    (Sinclair ZX Spectrum)[https://en.wikipedia.org/wiki/ZX_Spectrum]. Если по правде, именно 
    с него я начинал играть на компьютере, но я почему-то этого почти не помню, т.к. был мал.
    Эта платформа вдохновила тысячи людей на создание своих проектов. Это одна из историй. 
  * [Andrew Pollard](https://www.linkedin.com/in/andrew-pollard-507ab114/) за первые наработки 
    эмулятора Z80 на платформе Motorola 68000, с последующим портом кода на C.
  * [Adam Davidson](https://github.com/Arlorean) за порт этого эмулятора на Java и 
    создание [Jasper](https://github.com/Arlorean/Jasper).
  * [Philip M. Scull](http://www.liaquay.co.uk/) за [вторую жизнь Jasper](http://www.liaquay.co.uk/spectrum/index.html).
  * [Alexander Demin](https://github.com/begoon) за [третью жизнь Jasper](https://github.com/begoon/jasper).
  * [Lavr](http://www.nedopc.org/forum/viewtopic.php?f=90&t=9475&sid=3c4f9246cfcd00ec9596ad225f1bf31c) 
    за проделанную работу по запуску Специалист'а (старший брат Лик'а) и подробные комментарии 
    в коде. 
- Спасибо Организаторам и комьюнити за возможность держать связь с коллегами по цеху:
  * [форума nedopc.org](http://www.nedopc.org/forum). 
  * [форума zx-pk.ru](https://zx-pk.ru) (именно на этом форуме я и купил свой второй Лик).
- Спасибо svofski за чудный [assembler редактор](https://svofski.github.io/pretty-8080-assembler/).
- Спасибо Viktor Pykhonin (vpyk) за [крутой эмулятор](https://github.com/vpyk/emu80v4).
  * Отдельное спасибо за [исходники](https://github.com/vpyk/emu80v4/blob/master/src/Cpu8080.cpp) 
  и emu80-дебаггер они очень помогли в поиске отличий между процессором Z80 и i8080.
- Спасибо Автору статьи [Различия между процессорами i8080 (он же КР580ВМ80А) и Z80](https://webhamster.ru/mytetrashare/index/mtb0/1637215098k115beckd4).
- Спасибо Диме Вилюжанину за то, что помог купить реальный Лик.
- Спасибо моему Папе за то, что привил мне любовь к инженерии.
- Спасибо инженерам Черновицкого завода 'Электронмаш' за Лик.
- Спасибо Лищинскому К.В за римейк 
  [игры Клад](https://www.old-games.ru/wiki/%D0%9A%D0%BB%D0%B0%D0%B4) на Лик'е.
- Спасибо Oracle за Java.
- Спасибо Markdown за то, что я не мучаюсь с версткой этой странички.

Предустановка окружения
-----------------
- Для запуска необходимо установить java 1.8 версии.
- Прописать переменную окружения JAVA_HOME ссылающуюся на папку с установленной java.
- Затем добавить в Path переменную `JAVA_HOME/bin`.
- Выполнить команду в консоли `java -version` и увидеть версию java.

Запуск java приложения из IDE (development environment)
----------------- 
- Для открытия проекта в IDE стоит импортировать его как maven проект.
- Запустить класс `spec.Main`.
- Так же для Intellij Idea есть конфигурации запуска в папке `.run`.
- В проекте присутствуют unit/integration тесты с хорошим code-coverage -
  рекомендуем начать работу с их запуска.

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

From Adam Davidson (Feb 10, 1998)
--------------

From site: [http://www.spectrum.lovely.net/](https://web.archive.org/web/20071029174951/http://www.spectrum.lovely.net/)

Jasper V1.1 is now available with the following enhancements:

- Screen updating is now much faster thanks to [David Dawkins](mailto:atari-st@lovely.net) image handling tips.
- The class files have been shrunk to speed download times.
- Progress of snapshot loading uses a progress bar thanks to [Adam Doppelt](https://web.archive.org/web/20120119215721/http://www.gurge.com/amd/old/index.html).
- The screen is double buffered for smoother display of games which use scrolling.
- Click on progress bar to toggle between Full and Slow speed (slow to 100%).
- Saving snapshots has been removed to save on class file size.
- The emulator has been updated to handle JDK1.1 as well as JDK1.02.
- [Source code](https://web.archive.org/web/20071029174951/http://www.spectrum.lovely.net/jasp1_1j.zip) is now available for download.
- Jasper.class now replaces SpectrumApplet.class as the main class file.

For a Quick Start try out [Manic Miner](https://web.archive.org/web/20071030041200/http://www.spectrum.lovely.net/Shadows.html).

After seeing Jasper, a friend of ours, [David Dawkins](mailto:atari-st@lovely.net), 
decided to do an Atari ST Emulator. So far, it is looking very good. Look 
at [his website](https://web.archive.org/web/20071014001353/http://www.atari-st.lovely.net/#DEMO) for more details.

The Sinclair ZX Spectrum ( TS 2068 for those in the US) was a home computer 
released in 1982 by a British company called Sinclair Research Limited. 
The chip inside the Spectrum was a Z80 clocking in at 3.5Mhz, not quite 
the 1GHz of todays Intel machines. It only had 48Kb of memory, PC speaker 
style sound and only audio cassette tape for storage, but in the 1980's it 
was the business.

Due to demand, we've added a zip file to save downloading the .class files each 
time, [jasp1_1c.zip](https://web.archive.org/web/20071029174951/http://www.spectrum.lovely.net/jasp1_1c.zip).
The source code is now available for download, [jasp1_1j.zip](https://web.archive.org/web/20071029174951/http://www.spectrum.lovely.net/jasp1_1j.zip). 
We haven't included any game snapshots with this so you'll have to download these 
yourself from ftp://ftp.nvg.unit.no/pub/spectrum/snaps/games .

With the [old version](https://web.archive.org/web/20071029174951/http://www.spectrum.lovely.net/jasp1_0c.zip)
you can convert between .z80 and .sna snapshot formats. 
Jasper automatically detects which type to load and saves by default as a .z80, 
unless you add a .sna extension to the end (PgUp/PgDn do LOAD and SAVE). 
LOAD and SAVE also work from within BASIC but are limited to 10 characters for 
the filename.

[Gamelan](https://web.archive.org/web/20071029174951/http://www.gamelan.com/) 
say this applet is cool. Thanks to them and to everyone else who has sent 
us email, the response has been greatly appreciated.

The Java(tm) Centre, who I can't seem to find their web address anymore, 
have given us a "Golden Duke" award for this applet.

[Java(tm) Review Service](https://web.archive.org/web/20071029174951/http://www.jars.com/) 
have rated us as a top 1% applet.

This version contains support for old Netscape/JDK implementations by providing 
a sleep statement every Z80 interrupt (5 milliseconds by default), so that other 
threads are not blocked. If you are running Internet Explorer 3.0 or Netcsape 3.0 
then click on the "Speed: 100%, Sleep:5" text at the foot of the screen to speed things up.

If you click on the progress bar at the foot of the screen, it will speed
up the emulator although on some systems you may find that this locks up
your browser. If it works, subsequent clicks on the progress bar will toggle
between Fast and Slow modes. In Fast mode the Spectrum will try to consume
as much CPU time as it can, making the Spectrum run as fast as possible. In
Slow mode if the Spectrum is running over 100% (faster than the
original Spectrum) it will try to slow itself down to be closer to 100%.

The AMDProgressBar class was written by [Adam Doppelt](http://www.cs.brown.edu/people/amd/).

![Adam Doppelt](https://web.archive.org/web/20110605195031im_/http://gurge.com/amd/images/adam2d2.jpg)

Comments: Adam Doppelt [amd@gurge.com](mailto:amd@gurge.com)

Comments: Adam Davidson [spectrum@odie.demon.co.uk](mailto:spectrum@odie.demon.co.uk).

From Adam Davidson (Dec 30, 2017) 
---------------------------------

### Background

From site: [https://github.com/Arlorean/Jasper](https://github.com/Arlorean/Jasper)

A friend of mine originally wrote this emulator in 68000 assembler 
in order to play [Steve Davies Snooker](http://www.worldofspectrum.org/infoseekid.cgi?id=0004896) 
on his Atari ST back in 1988.
He then ported it to 'C'. At this point I was keen to play 
[Match Day](http://torinak.com/qaop#!matchday) with two players, 
each on their own Sun Sparcstation 1 that we used at University 
at the time in 1991. This was done using X-Windows windows on 
different displays while running the emulator as a server process 
updating the independent X-Displays.

Time then passed until Sun released their first version of Java in 
1996 and I was hooked. I instantly started porting the 'C' code to 
Java and created an applet that could eventually run in a browser, 
if they were ever supported.
Competition is a great thing. In 1997 Netscape and Internet Explorer 
both released browsers with Java applet support baked in. I took a 
week vacation and got the emulator up and running and it was slow as 
anything, unplayable on the 133Mhz Pentium that I had at the time.
Shortly afterwards though the JIT compilers arrived and suddenly the 
applet ran in real-time (most of the time) and we decided to release 
it and put up a webpage.
I tried contacting the orignal firms that wrote some of the games to 
ask if they were OK with us putting up a snapshot of their game on our 
site for people to play. Contacting companies that wrote software in 
the 1982-1986 timeframe was VERY hard. We got some OKs but many just 
ignored us. In the end we put up a selection of games and waited to 
see what would happen.

Surprisingly we were contacted by Sun Microsystem's lawyers with a 
cease and desist letter because we called in "Java ZX Spectrum Emulator"! 
Of all the companies I thought would complain, I didn't expect Sun to 
be one of them as they were trying to promote Java heavily at the time.
We had to change the name to Jasper (JAva SPectrum EmulatoR).

The original browser applet APIs didn't really have support for sound 
in the way we needed it to emulate the ZX Specturm audio correctly 
so we never managed to get sound support in there.

Some other kind users have also [uploaded the source code](https://github.com/begoon/jasper) 
we wrote. Many thanks to them for doing that and keeping our names 
on the repositories.

From Adam Davidson (01 May, 2020)
----------------------------------

From site: [https://github.com/Arlorean/Casper](https://github.com/Arlorean/Casper/)

This is a C# port of [Jasper](https://github.com/Arlorean/Jasper) (Java Spectrum Emulator) 
for .NET Standard (2.0.3) and Windows Forms.
This project is a C# port of [Jasper](https://github.com/Arlorean/Jasper) (Java Spectrum Emulator)
for .NET Standard (2.0.3), Windows Forms (on .NET Framework 4.6.1),
and now [Blazor Server-side](https://docs.microsoft.com/en-gb/aspnet/core/blazor/hosting-models#blazor-webassembly)
and [Blazor Client-side](https://docs.microsoft.com/en-gb/aspnet/core/blazor/hosting-models#blazor-server) (using WebAssembly)
on [.NET Core 3.1](https://devblogs.microsoft.com/dotnet/announcing-net-core-3-1/).

### History

In **1982** the hugely popular home computer the [Sinclair ZX Spectrum](https://en.wikipedia.org/wiki/ZX_Spectrum)
was created by [Clive Sinclair](https://en.wikipedia.org/wiki/Clive_Sinclair).
It seeded a generation of software developers and I owe my entire career
to that one purchasing decision made for me when I was 12 years old. Thanks Mum!

In **1989** my friend [Andrew Pollard](https://www.linkedin.com/in/andrew-pollard-507ab114/)
created this emulator in [Motorola 68000](https://en.wikipedia.org/wiki/Motorola_68000)
assembly code in order to play [Steve Davis Snooker](https://www.worldofspectrum.org/infoseekid.cgi?id=0004896)
on his [Atari ST](https://en.wikipedia.org/wiki/Atari_ST) because he was disappointed in the
[Atari ST version](https://en.wikipedia.org/wiki/Steve_Davis_World_Snooker).

In **1992** Andrew ported the emulator to C code and we then ported it to [X Windows](https://en.wikipedia.org/wiki/X_Window_System)
so we could play [Match Day](https://www.worldofspectrum.org/infoseekid.cgi?id=0003067) where
each of us played on our own [Sun SparcStation](https://en.wikipedia.org/wiki/SPARCstation_1)
with the emulator essentially running as a backend server.

In **1996** Sun released their first version of the Java Develop Kit with the tag line *write once, run anywhere*.
I was completely sold, especially when I saw their Web Browser implemention
[HotJava](https://en.wikipedia.org/wiki/HotJava) written 100% in Java
with a new [Java Applet](https://en.wikipedia.org/wiki/Java_applet) model
for running Java content inside a Web Browser.
I could see that we could resurrent the emulator and port it to Java.
Yes it would be slow, since the Java Bytecode was interpretted,
but Just-in-Time (JIT) compilation was coming and I knew I couldn't wait.

In **1997** during the [browser wars](https://en.wikipedia.org/wiki/Browser_wars) Netscape and Internet Explorer
were both viaing to be the best Web Browser on the market and Java Applet performance
was a differentiator for them. They both came our with JIT support for Java Applets around the same
time and suddenly the Java Spectrum Emulator (Jasper) got a new lease of life.
With JIT it ran in real-time on a 133Mhz Pentium in 16Mb RAM. We even got some
interest from the press, [Internet Magazine](https://en.wikipedia.org/wiki/Internet_Magazine)
ran an article on [our website](https://web.archive.org/web/19980210232053/http://www.spectrum.lovely.net/) in March 1997:

![](https://github.com/Arlorean/Casper/raw/master/docs/InternetMagazine-March1997-Article.jpg)

In **2019** in a bid to learn about Blazor I decided to ressurect the old emulator
code again and port it to C#. I did a Windows Forms reference first so I could
make sure things were working as they should be. Then I moved on to the Blazor
version. I could see the same story of interpretted byte code initially with the
promise of JIT or AOT (Ahead-of-Time) compilation hopefully making the
Blazor WebAssembly version run in real-time once again.

I hit a roadblock straight away. The promise of Blazor was that you
didn't have to write JavaScript, you could create an app, in the browser,
with just C# code. However I couldn't see how to write a canvas object in C#
without all the messy [JavaScript interop](https://docs.microsoft.com/en-us/aspnet/core/blazor/call-javascript-from-dotnet)
which I really didn't want to deal with.

In **2020**, [Coronavirus](https://en.wikipedia.org/wiki/2019%E2%80%9320_coronavirus_pandemic)
lock-down has given us a lot of time for fun projects so I thought I'd revisit this one.
Blazor (or [Razor](https://docs.microsoft.com/en-us/aspnet/core/razor-pages))
is all about DOM manipulation but the [HTML Canvas](https://www.w3schools.com/html/html5_canvas.asp)
doesn't have a DOM, it's all immediate.
However I realized that HTML Scalable Vector Graphics ([SVG](https://www.w3schools.com/html/html5_svg.asp))
does and so I set about creating a Blazor SVG version of the ZX Spectrum Emulator. Crazy, right?!

### Credits

[Andrew Pollard](https://www.linkedin.com/in/andrew-pollard-507ab114/) who created the
original emulator code and painstakingly went through each [Z80 Mnemonic](http://www.worldofspectrum.org/z88forever/dn327/z80inst2.htm) checking
that they functioned as they did in the original [Zilog Z80](https://en.wikipedia.org/wiki/Zilog_Z80).

[Amstrad](https://en.wikipedia.org/wiki/Amstrad) who now own the ZX Spectrum brand are the owners of the
[Spectrum.rom](https://github.com/Arlorean/Casper/blob/master/Casper.Shared/Resources/Spectrum.rom) file, which is a copy of the original ZX Spectrum 16K ROM code.
In [a post on comp.sys.sinclair](https://www.worldofspectrum.org/permits/amstrad-roms.txt) they requested
this notice be attached whenever their ROM files were redistributed:
> *Amstrad have kindly given their permission for the redistribution of their copyrighted material but retain that copyright*
The font used on the web page is the [ZX82 System Font](http://www.type-invaders.com/sinclair/zx82system/)
by [Paul van der Laan](http://www.type-invaders.com/sinclair/).

The [Keyboard SVG](https://github.com/Arlorean/Casper/blob/master/docs/zxspectrum_keyboard.svg) was derived from a [PowerPoint of the ZX Spectrum Keyboard](https://softspectrum48.weebly.com/uploads/6/6/7/5/66753101/zx_keyboard.pptx)
by [Magnus Krook](https://softspectrum48.weebly.com/).

The [Manic Miner](https://github.com/Arlorean/Casper/blob/master/Casper.Shared/Resources/Games/ManicMiner.z80) game was
written by [Matthew Smith](https://en.wikipedia.org/wiki/Matthew_Smith_(games_programmer))
and published by [Bug Byte Software Limited](https://www.worldofspectrum.org/infoseekid.cgi?id=0003012) in 1983.

From Philip M. Scull (08 Jun, 2004)
-----------------------------------

From site: [http://www.liaquay.co.uk/spectrum/index.html](http://www.liaquay.co.uk/spectrum/index.html)

Jasper - ZX Spectrum Emulator
by Adam Davidson & Andrew Pollard
hacked about by Philip M. Scull

Source for Hacked Jaster. Please note this is not an offical Jasper release.
[Hacked Jasper Source - Release 1.0](http://www.liaquay.co.uk/spectrum/hjasper.zip)

Visit the original jasper [www.spectrum.lovely.net](http://www.spectrum.lovely.net/)

Comments, complaints, congratulations to [phil@liaquay.co.uk](mailto:phil@liaquay.co.uk)

From Alexander Demin (Oct 26, 2012)
--------------

From site: [https://github.com/begoon/jasper](https://github.com/begoon/jasper)

This is a fork of the original version of Jasper 1.1j by
Adam Davidson and Andrew Pollard. Previously it was available at
http://spectrum.lovely.net/, but currently this website is unavailable.

Also this fork includes a fix allowing screen scaling using the `pixelScale`
variable.

From Lavr (13 Apr, 2011)
--------------

From site: [http://www.nedopc.org/forum](http://www.nedopc.org/forum/viewtopic.php?f=90&t=9475&sid=3c4f9246cfcd00ec9596ad225f1bf31c)

[Эмулятор «Специалист» на Java](http://sgu-wap.narod.ru/SP_MX/SPECOLD/)

В принципе его как такового не было... :wink: И его так бы и не было, если бы я его не написал. 

Исходники доступны [ЗДЕСЬ](http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip).

К действию меня сподвиг вот этот довольно давно существующий проект:
Jasper - ZX Spectrum Emulator by Adam Davidson & Andrew Pollard
[http://www.liaquay.co.uk/spectrum/index.html](http://www.liaquay.co.uk/spectrum/index.html)


Джентельмены Adam Davidson & Andrew Pollard люди не жадные и исходников
своих не прятали. Исходники и сейчас доступны здесь:
The source code is available online from [http://www.odie.demon.co.uk/spectrum/jasp1_1j.zip](https://web.archive.org/web/20110822225120/http://www.odie.demon.co.uk/spectrum/jasp1_1j.zip).
The AMDProgressBar class was written by Adam Doppelt. (специально проверил).
Исходники весьма понятны и очень мне помогли при эмуяции ZX Spectrum на
платформе J2M сотовых телефонов.

Задача очень культурно и неглупо разбита на классы:
- Z80.class - эмуляция процессора;
- Spectrum.class - эмуляция ZX Spectrum;
- Jasper.class - интерфейс с браузером и др.;
- AMDProgressBar.class - полоска внизу, позволяет регулировать скорость
эмуляции и др., в принципе без неё можно обойтись, поправив коды.

И у меня всё это запросто работало под IE 5.0 без всяких претензий и с
тройным запасом по скорости.
Этого никак не скажешь о сегодняшних эмуляторах на JS:
- [http://code.google.com/p/radio86/](http://code.google.com/p/radio86/)
- и вдохновляющий прототип [http://jsspeccy.zxdemo.org/](http://jsspeccy.zxdemo.org/)
И броузеры - им не броузеры, и скорости эмуляции как у спринтерских черепах...

Поскольку «Специалист» по идеологии очень близок к «ZX Spectrum», мне
хотелось написать его эмулятор самому. По моим прикидкам это всё вполне
осуществимо на основе имеющихся исходников.

Если Ленин хотел реорганизовать рабкрин, то здесь придётся в основном
реорганизовать экран и карту памяти. :wink:

Вижу я, что всё по жизни сам не успеешь - возможно кому-то этот проект
будет интересен.

PS. Идеи глупо держать под подушкой, там они гниют и тухнут.
Хороший слоган!: [http://easy-coding.blogspot.com/](http://easy-coding.blogspot.com/)
Поэтому, кому надо - вот [исходники «Специалиста» на Java](http://sgu-wap.narod.ru/SP_MX/SPECOLD/Spec1987.zip).

Oleksandr Baglai (17 Jan, 2022)
----------------

Please scroll up and [read Intro](https://github.com/codenjoyme/8080-emulator#Intro).