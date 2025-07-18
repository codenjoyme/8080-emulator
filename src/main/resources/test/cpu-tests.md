Тесты [взяты тут](https://github.com/begoon/i8080-core). 
Спасибо [begoon](https://github.com/begoon) за вклад. 

Тесты [компилировались тут](https://svofski.github.io/pretty-8080-assembler). 
Исходники компилятора [лежат тут](https://github.com/svofski/pretty-8080-assembler). 
Спасибо [svofski](https://github.com/svofski) за вклад.

Директивы ассемблера (не все работают в pretty-8080-assembler) 
[описаны тут](https://pasmo.speccy.org/pasmodoc.html). 
Спасибо [Автору](mailto:julian.notfound@gmail.com) за вклад.

За реализацией некоторых команд 8080 подглядывал:
* [Тут](https://github.com/superzazu/8080) - 
  спасибо [superzazu](https://github.com/superzazu/8080) за вклад.
* [Тут](https://github.com/vpyk/emu80v4/blob/master/src/Cpu8080.cpp) - 
  спасибо [vpyk](https://github.com/vpyk) за вклад. 
  Отдельное спасибо [за эмулятор](https://emu80.org/distr/) - он очень помог,
  как в отладке тестов на эталонной машине так и в понимании работы процессора.  
 
Список тестов и их исходных кодов:
- [`src/main/resources/test/8080apofig`](../../../../src/main/resources/test/8080apofig)
  * Программа выполняет операции, записывает результаты в память для проверки контрольных сумм и использует 
    XOR SHIFT для рандомизации регистров, улучшая тестовое покрытие.
  * [x] Есть [исходники](../../../../src/main/resources/test/8080apofig/8080apofig.asm).
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тут](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_apofig8080exerciser()`.
    + [результаты](../../../../src/test/resources/testLik/diagnostic/apofig8080exerciser)

- [`src/main/resources/test/8080ex1`](../../../../src/main/resources/test/8080ex1)
  * The basic CPU Excerciser. This file is a copy of the vanilla file.
  * [x] Есть [исходники](../../../../src/main/resources/test/8080ex1/8080ex1.asm).
  * [ ] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_zexlax8080exerciser()`
    + [результаты](../../../../src/test/resources/testLik/diagnostic/zexlax8080exerciser)
   
- [`src/main/resources/test/8080exer`](../../../../src/main/resources/test/8080exer)
  * Vanilla basic CPU Excerciser having CRCs from the real KR580VM80A
    contributed by Alexander Timoshenko and Viacheslav Slavinsky.
  * [ ] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [`src/main/resources/test/8080pre`](../../../../src/main/resources/test/8080pre)
  * 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles.
    The preliminary test.
  * [x] Есть [исходники](../../../../src/main/resources/test/8080pre/8080pre.asm).
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_exerciserPreliminary()`
    + [результаты](../../../../src/test/resources/testLik/diagnostic/exerciserPreliminary)
  
- [`src/main/resources/test/8085exer`](../../../../src/main/resources/test/8085exer)
  * Пока еще не изученный тест.
  * [x] Есть [исходники](../../../../src/main/resources/test/8085exer/8085EXER.MAC).
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [`src/main/resources/test/cputest`](../../../../src/main/resources/test/cputest)
  * Diagnostics II, version 1.2, CPU test by Supersoft Associates.
  * [ ] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [`src/main/resources/test/hello-world`](../../../../src/main/resources/test/hello-world)
  * Тест выводящий надпись `HELLO WORLD` на экран в ЛИКе.
  * [x] Есть [исходники](../../../../src/main/resources/test/hello-world/hello-world.asm).
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_helloWorld()`
    + [результаты](../../../../src/test/resources/testLik/helloWorld)
  
- [`src/main/resources/test/test`](../../../../src/main/resources/test/test)
  * 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates.
  * [x] Есть [исходники](../../../../src/main/resources/test/test/test.asm).
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_microcosm()`
    + [результаты](../../../../src/test/resources/testLik/diagnosticMicrocosm)

Алгоритм компиляции на стороннем компиляторе:
- На примере теста [`src/main/resources/test/test`](../../../../src/main/resources/test/test)
- Копируем [исходник](../../../../src/main/resources/test/test/test.asm) 
  [сюда](https://svofski.github.io/pretty-8080-assembler/)
- Кликаем в приложении на кнопку `BIN`
- Скаченный браузером файл с расширением `*.mem` копируем в соответствующую 
  тесту папку [`src/main/resources/test/test`](../../../../src/main/resources/test/test).

Алгоритм компиляции на локальном компиляторе: <a name="compile-asm"></a>
- В классе [`Assembler`](../../../../src/main/java/svofski/Assembler.java) создаем тест:
```java
    public static void main(String[] args) throws IOException {
        Assembler assembler = new Assembler();
        
        String sourceCode =
                ";************************\n" +
                "; Prints 'HELLO WORLD\\n'\n" +
                ";************************\n" +
                "\n" +
                "        .project hello-world.mem\n" +
                "        .tape специалистъ-mon\n" +
                "        CPU     8080\n" +
                "        .ORG    00000h\n" +
                "\n" +
                "start:  LXI     H,hello\n" +
                "        CALL    bdos\n" +
                "        JMP     wboot      ; exit\n" +
                ";\n" +
                "hello:  DB      00Dh, 00Ah, 'HELLO WORLD', 00Dh, 00Ah, '$'\n" +
                ";\n" +
                "bdos    EQU     0C037h     ; PRINT CHAR PROCEDURE\n" +
                "wboot:  JMP     0C800h     ; BACK TO SYSTEM\n" +
                "end:";
      
        // компилируем
        Bites result = assembler.compile(sourceCode);
      
        // скомпилированный код
        byte[] bytes = result.byteArray();
      
        // сохраняем в файл
        FileUtils.writeByteArrayToFile(new File("target/program.mem"), bytes);
      
        // дамп памяти
        System.out.println(result);
        
        // напечатает
        //       00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F
        // 0000: 21 09 00 CD 37 C0 C3 19 00 0D 0A 48 45 4C 4C 4F
        // 0010: 20 57 4F 52 4C 44 0D 0A 24 C3 00 C8
    }
```

Запуск тестов:
- В классе [`IntegrationTest`](../../../../src/test/java/spec/IntegrationTest.java) создаем тест:
```java
public class IntegrationTest extends AbstractTest {
    @Test
    public void testLik_diagnostic_microcosm() {
        // given
        Lik.loadRom(base, roms);
        hard.loadData(CPU_TESTS_RESOURCES + "test/test.rks", Lik.NAME);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0100));
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x0057));
        // если хочется подебажить внутри
        cpu.modAdd(new DebugWhen(0x0383, () ->
                assertCpu("cpu_at_0x0383")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }
}
```
- Запускаем его и смотрим результат выполнения в папке 
  [`test/resources/testLik_diagnostic_microcosm`](../../../../src/test/resources/testLik_diagnostic_microcosm) - там будет:
  * `trace.log` - трассировка выполнения программы на определенном уровне вложенности.
  * `cpu.log` - состояние процессора на момент окончания программы.
  * `end.png` - скриншот окна эмулятора на момент окончания программы.

Отладка теста на Emu80:
+ После запуска приложения и выбора платформы `ЛИК`
+ Выбираем меню `File` -> `Load` -> `Open file...`
+ Выбираем файл с тестом и нажимаем `Open`
+ Выбираем меню `Platform` -> `Debug` (или нажимаем `Alt-D`) чтобы открыть debug окно
+ Кликаем дважды по адресу команды и выбираем `0000` затем `Enter`
+ Кликаем дважды слева от адреса команды чтобы появился красный breakpoint кружок
+ Выбираем меню `Debug` -> `Run` (или нажимаем `F5`) и ждем пока программа не остановится на breakpoint
+ Переключаемся в окно эмулятора и выполняем `J` а затем `Enter` чтобы запустить программу
+ Эмулятор автоматически остановится на breakpoint
+ Исследуем состояние процессора и памяти, смотрим трассировку выполнения программы сравнивая 
  ее с трассировкой из теста в `trace.log`

Зеркала репозиториев содержится в папке [`src/main/resources/test/arch`](../../../../src/main/resources/test/arch) 
на случай потери доступа к оригинальным репозиториям.
