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
  как в отладке тестов на эталонной машине.

Список тестов и их исходных кодов:
- [8080apofig.asm](../../../../src/main/resources/test/8080apofig/8080apofig.asm)
  * Программа выполняет операции, записывает результаты в память для проверки контрольных сумм и использует 
    XOR SHIFT для рандомизации регистров, улучшая тестовое покрытие.
  * [x] Есть исходники.
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тут](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_apofig_8080_exerciser()`.
    + [результаты](../../../../src/test/resources/testLik_diagnostic_apofig_8080_exerciser)

- [8080ex1.asm](/src/main/resources/test/8080ex1/8080ex1.asm)
  * The basic CPU Excerciser. This file is a copy of the vanilla file.
  * [x] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_zexlax_8080_exerciser()`
    + [результаты](../../../../src/test/resources/testLik_diagnostic_zexlax_8080_exerciser)
   
- [8080EXER.COM](../../../../src/main/resources/test/8080exer/8080EXER.COM)
  * Vanilla basic CPU Excerciser having CRCs from the real KR580VM80A
    contributed by Alexander Timoshenko and Viacheslav Slavinsky.
  * [ ] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [8080pre.asm](../../../../src/main/resources/test/8080pre/8080pre.asm)
  * 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles.
    The preliminary test.
  * [x] Есть исходники.
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_exerciser_preliminary()`
    + [результаты](../../../../src/test/resources/testLik_diagnostic_exerciser_preliminary)
  
- [8085EXER.MAC](../../../../src/main/resources/test/8085exer/8085EXER.MAC)
  * Пока еще не изученный тест.
  * [x] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [CPUTEST.COM](../../../../src/main/resources/test/cputest/CPUTEST.COM)
  * Diagnostics II, version 1.2, CPU test by Supersoft Associates.
  * [ ] Есть исходники.
  * [ ] Отлажен на эмуляторе.
  * [ ] Добавлен как интеграционный тест.
  
- [hello_world.asm](../../../../src/main/resources/test/hello-world/hello_world.asm)
  * Тест выводящий надпись `HELLO WORLD` на экран в ЛИКе.
  * [x] Есть исходники.
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_helloWorld()`
    + [результаты](../../../../src/test/resources/testLik_helloWorld)
  
- [test.asm](../../../../src/main/resources/test/test/test.asm)
  * 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates.
  * [x] Есть исходники.
  * [x] Отлажен на эмуляторе.
  * [x] Добавлен как интеграционный тест:
    + [тест](../../../../src/test/java/spec/IntegrationTest.java)`testLik_diagnostic_microcosm()`
    + [результаты](../../../../src/test/resources/testLik_diagnostic_microcosm)

Алгоритм компиляции:
- Копируем исходник [сюда](https://svofski.github.io/pretty-8080-assembler/)
- Кликаем на кнопку BIN
- Скаченный браузером файл с расширением `*.RKS` копируем в соответствующую 
  тесту папку `src/main/resources/test/*`.
- В классе `IntegrationTest` создаем тест:
```java
public class IntegrationTest extends AbstractTest {
    @Test
    public void testLik_diagnostic_microcosm() {
        // given
        Lik.loadRom(base, roms);
        hard.loadData(CPU_TESTS_RESOURCES + "test/test.rks", Lik.PLATFORM);
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
  `test/resources/testLik_diagnostic_microcosm/*` - там будет:
  * `trace.log` - трассировка выполнения программы на определенном уровне вложенности.
  * `cpu.log` - состояние процессора на момент окончания программы.
  * `end.png` - скриншот окна эмулятора на момент окончания программы.

Зеркала репозиториев содержится в папке `src/main/resources/test/arch` 
на случай потери доступа к оригинальным репозиториям.
