package tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Arrays;

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
        File xmlFile = new File("target/Test Results - CpuTest.xml");

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
            System.out.printf("%s %s\n", path.first, path.second);

            // Получаем элемент <diff>
            Element diffElement = (Element) testElement.getElementsByTagName("diff").item(0);

            // Получаем атрибуты actual и expected
            String actual = diffElement.getAttribute("actual");
            String expected = diffElement.getAttribute("expected");

            System.out.printf("%s\n%s\n", actual, expected);
        }
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
