package tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class TestDiffApply {
    public static void main(String[] args) {
        try {
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

                // Выводим атрибут locationUrl
                System.out.println("Путь к файлу Java с тестом: " + locationUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
