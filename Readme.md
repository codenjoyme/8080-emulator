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
- [ ] Определиться с наследуемой лицензией и настроить maven плагин. 
- [ ] Отформатировать проект по JCC, сохранив всю документацию.
- [ ] Сделать переключение ROM и загрузку RKS на лету, без перезапуска сервера.
- [ ] Покрыть тестами.
- [ ] Принять решение, что делать со старым Spectrum кодом.
- [ ] Отдебажить, почему не работает игра Клад и Basic глючит сравнивая с 
  эталонным эмулятором emu80v4. 
- [ ] Перевести на модерновый UI фреймворк.
- [ ] Добавить поддержку звука.
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

Запуск приложения
-----------------
- Для запуска необходимо установить java 1.8 версии.
- Прописать переменную окружения JAVA_HOME ссылающуюся на папку с установленной java.
- Затем добавить в Path переменную `JAVA_HOME/bin`.
- Выполнить команду в консоли `java -version` и увидеть версию java.   
- Затем запустить скрипт `buil/run.sh`. Если нет linux можно 
  использовать тулы типа `cygwin`/`mingw`.
- Скрипт в какой-то момент остановится (сервер запустился) - 
  окно закрывать не стоит, его закрытие приведет к остановке сервера.
```
[INFO] Started ServerConnector@697c9218{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
[INFO] Started Server@15200332{STARTING}[11.0.7,sto=0] @8448ms
```
- После стоит проследовать в `JAVA_HOME/bin` и запустить там `javacpl`.
- В табчике `Security` 
  + Ппоставить галочку `Enable Java content for browser and Web Start applications`.
  + Выбрать опцию `Very high` для `Security level for applications not on the Exception Site list`.
  + Добавить в `Exception Site list` новый сайт `http://localhost:8080/`
- В табчике `Advanced`
  + В разделе `Debugging` выбрать галочки `Enable tracing`, `Enable logging` и `Show applet lifecycle exceptions`.
  + В разделе `Java console` выбрать опцию `Show console`.
- Остальное оставляем без изменений. Кликаем `Apply` и закрываем окно. 
- Так мы настроили вывод логов из Applet в консоль, которая откроется при запуске приложения.
- Направляемся в браузер и набираем в адресной строке `http://localhost:8080/`. 
  В этот момент Applet незамедлительно попробует загрузиться.
- Если агент безопасности спросит можно ли это сделать - разрешаем ему открыть приложение.
- Приветствуем надпись `* RUN"COM:"`
- В открытом окне кликаем на панельку с надписью `Full Speed: 100000%` - это замедлит 
  эмулятор до удобоваримых скоростей. Там отобразится `Slow Speed: 300%`.
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
- Радуемся игре `Клад`. Не долго радуемся, она глючит и поиграть не получится.

Загрузка других ROM/RKS
-----------------------
- В классе `spec.Spec1987` есть код
```
//--- для ПК "ЛИК" ---------------------------------------------------------------
boolean lik = true; // ЛИК или Специалист
if (lik) {
    URL likRom1URL = new URL(baseURL, "lik/01_zagr.BIN");
    spechard.loadROM(likRom1URL.toString(), likRom1URL.openStream(), 0xC000);

    URL likRom2URL = new URL(baseURL, "lik/02_mon-1m.BIN");
    spechard.loadROM(likRom2URL.toString(), likRom2URL.openStream(), 0xC800);

    URL likRom3URL = new URL(baseURL, "lik/03_mon-1m_basicLik.BIN");
    spechard.loadROM(likRom3URL.toString(), likRom3URL.openStream(), 0xD000);

    URL likRom4URL = new URL(baseURL, "lik/04_basicLik.BIN");
    spechard.loadROM(likRom4URL.toString(), likRom4URL.openStream(), 0xD800);

    URL likRom5URL = new URL(baseURL, "lik/05_basicLik.BIN");
    spechard.loadROM(likRom5URL.toString(), likRom5URL.openStream(), 0xE000);

    URL likRom6URL = new URL(baseURL, "lik/06_basicLik.BIN");
    spechard.loadROM(likRom6URL.toString(), likRom6URL.openStream(), 0xE800);

    URL kladURL = new URL(baseURL, "lik/apps/klad.rks");
    spechard.loadRKS(kladURL.toString(), kladURL.openStream());
} else {
//--- для ПК "Специалист" ---------------------------------------------------------------
    URL specRom0URL = new URL(baseURL, "specialist/monitor0.rom");
    spechard.loadROM(specRom0URL.toString(), specRom0URL.openStream(), 0xC000);

    URL specRom1URL = new URL(baseURL, "specialist/monitor1.rom");
    spechard.loadROM(specRom1URL.toString(), specRom1URL.openStream(), 0xC800);
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