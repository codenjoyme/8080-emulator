package tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestDiffApply {

    public static class Pair<T, U> {

        public Pair(T first, U second) {
            this.second = second;
            this.first = first;
        }

        public final T first;
        public final U second;

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        // Путь к вашему XML-файлу
        File xmlFile = new File("target/test.xml");

        // Создаем фабрику для создания парсера
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Создаем объект Document из XML-файла
        Document document = builder.parse(xmlFile);

        // Получаем корневой элемент
        Element rootElement = document.getDocumentElement();

        // Получаем список всех элементов <test>
        NodeList testElements = rootElement.getElementsByTagName("test");

        // Перебираем элементы <test>
        for (int i = 0; i < testElements.getLength(); i++) {
            Element testElement = (Element) testElements.item(i);

            // Получаем атрибут locationUrl
            String locationUrl = testElement.getAttribute("locationUrl");

            // Получаем атрибут status
            String status = testElement.getAttribute("status");

            // Проверяем, что статус равен "failed"
            if (!"failed".equals(status)) {
                continue;
            }

            // Выводим атрибут locationUrl после преобразования
            Pair<String, String> path = convertToJavaFilePath(locationUrl);
            String clazz = new File("./" + path.first).getAbsolutePath();
            String testName = path.second;

            // Получаем элемент <diff>
            Element diffElement = (Element) testElement.getElementsByTagName("diff").item(0);

            // Получаем атрибуты actual и expected
            String actual = diffElement.getAttribute("actual");
            String expected = diffElement.getAttribute("expected");

            // Получаем элемент <output>
            Element outputElement = (Element) testElement.getElementsByTagName("output").item(0);

            // Получаем текст из элемента output
            String outputText = outputElement.getTextContent();

            // Ищем номер строки в тексте output
            String lineNumber = extractLineNumber(outputText);
            String assertName = extractAssertName(outputText);

            actual = formatStringForJava(actual, assertName);
            expected = formatStringForJava(expected, assertName);

            System.out.printf("%s:%s %s\n", clazz, lineNumber, testName);
            System.out.printf("Expected:\n%s\n\nActual:\n%s\n", expected, actual);

            replaceAssertInJavaTestClass(clazz, lineNumber, expected, actual);
        }
    }

    public static String formatStringForJava(String input, String assertName) {
        // Заменяем все двойные кавычки на экранированные двойные кавычки
        String escapedInput = input.replace("\"", "\\\"");

        // Разбиваем строку на строки по символу новой строки
        String[] lines = escapedInput.split("\\n");

        // Формируем новую строку с префиксом и конкатенируем строки с переводами строки
        StringBuilder formattedString = new StringBuilder();
        formattedString.append("        " + assertName + "(\"");
        for (String line : lines) {
            formattedString.append(line);
            formattedString.append("\\n\" +\n                \"");
        }

        // Удаляем последний добавленный конкатенатор
        formattedString.delete(formattedString.length() - 22, formattedString.length());

        // Завершаем строку кавычками и точкой с запятой
        formattedString.append("n\");");

        return formattedString.toString();
    }

    private static void replaceAssertInJavaTestClass(String clazz,
                                                     String lineNumber, String[] expectedLines,
                                                     String[] actualLines) throws Exception {
        int lineNum = Integer.parseInt(lineNumber);
        Path filePath = Paths.get(clazz);

        // Читаем содержимое файла в список строк
        List<String> lines = Files.readAllLines(filePath);

        // Проверяем, что номер строки не превышает количество строк в файле
        if (lineNum > lines.size()) {
            System.out.println("Номер строки превышает количество строк в файле.");
            return;
        }

        // Заменяем все вхождения expected на actual, начиная с указанной строки
        int i = lineNum - 1;

        // Удаляем строки expected
        for (int j = 0; j < expectedLines.length; j++) {
            lines.remove(i);
        }

        // Вставляем строки actual
        for (int j = actualLines.length - 1; j >= 0; j--) {
            lines.add(i, actualLines[j]);
        }

        // Записываем обновленные строки обратно в файл
        Files.write(filePath, lines);
    }

    private static boolean containsAllLines(String line, String[] expectedLines) {
        for (String expectedLine : expectedLines) {
            if (!line.contains(expectedLine)) {
                return false;
            }
        }
        return true;
    }

    private static void replaceAssertInJavaTestClass(String clazz,
                                                     String lineNumber, String expected,
                                                     String actual) throws Exception
    {
        replaceAssertInJavaTestClass(clazz, lineNumber,
                expected.split("\n"), actual.split("\n"));
    }

    private static String extractLineNumber(String outputText) {
        String[] lines = outputText.split("\n");

        for (String line : lines) {
            if (line.contains("at spec.CpuTest.code")) {
                // Извлекаем номер строки из строки с кодом
                int startIndex = line.lastIndexOf(':') + 1;
                int endIndex = line.lastIndexOf(')');
                if (startIndex >= 0 && endIndex >= 0) {
                    return line.substring(startIndex, endIndex);
                }
            }
        }

        return "Номер строки не найден";
    }

    private static String extractAssertName(String outputText) {
        String[] lines = outputText.split("\n");

        for (String line : lines) {
            if (line.contains("at spec.stuff.AbstractTest")) {
                // Извлекаем номер строки из строки с кодом
                int startIndex = line.indexOf("AbstractTest") + "AbstractTest".length() + 1;
                int endIndex = line.lastIndexOf('(');
                if (startIndex >= 0 && endIndex >= 0) {
                    return line.substring(startIndex, endIndex);
                }
            }
        }

        return "Номер строки не найден";
    }

    public static Pair<String, String> convertToJavaFilePath(String input) {
        // Удаляем начальную часть "java:test://"
        String trimmedPath = input.replace("java:test://", "").replace(".", "/");

        // Разбиваем оставшуюся часть по символу '/'
        String[] parts = trimmedPath.split("/");

        // Получаем имя теста (последний элемент массива)
        String testName = parts[parts.length - 1];

        // Удаляем название теста из массива
        parts = Arrays.copyOf(parts, parts.length - 1);

        // Преобразуем массив в путь
        String javaPath = String.join("/", parts);

        // Соединяем путь и имя класса Java
        return new Pair<>("/src/test/java/" + javaPath + ".java", testName);
    }
}
