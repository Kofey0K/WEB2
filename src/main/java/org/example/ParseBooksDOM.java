package org.example;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ParseBooksDOM {

    /**
     * Обробляє елемент <some_element></some_element>
     *
     * @param node
     * @param indent відступ
     */
    private void dumpElement(Element node, String indent) {
        System.out.println(indent +
                "ELEMENT: " +
                node.getTagName());
        // Обробляємо атрибути елемента
        NamedNodeMap nm = node.getAttributes();
        for (int i = 0; i < nm.getLength(); i++)
            printNode(nm.item(i), indent + " 	");
    }

    /**
     * Обробляє атрибут <... some_atribute = "some_value">
     *
     * @param node
     * @param indent відступ
     */
    private void dumpAttributeNode(Attr node, String indent) {
        System.out.println(indent +
                "ATTRIBUTE" +
                node . getName () + "=\"" +
                node . getValue () + "\"");
    }

    /**
     * Обробляє текстовий вміст
     * елемента <...>some_text</...>
     *
     * @param node
     * @param indent відступ
     */
    private void dumpTextNode(Text node, String indent) {
        System.out.println(indent + "TEXT: " + node.getData());
    }

    /**
     * Рекурсивна процедура обробки вузлів
     * XML- документа
     *
     *@ param node оброблюваний вузол
     *@ param indent відступ
     */
    private void printNode(Node node, String indent) {
        // Отримуємо тип вузла
        int type = node.getNodeType();

        switch (type) {
            case Node.ATTRIBUTE_NODE:
                dumpAttributeNode((Attr) node, indent);
                break;
            case Node.ELEMENT_NODE:
                dumpElement((Element) node, indent);
                break;
            case Node.TEXT_NODE:
                dumpTextNode((Text) node, indent);
                break;

        }

        // Рекурсивно обробляємо дочірні вузли
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
            printNode(list.item(i), indent + " 	");
    }

    /**
     * Оголошення методу main
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String filename = "files/books.xml";

            // Створюємо екземпляр класу DocumentBuilderFactory
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            // Створюємо екземпляр класу DocumentBuilder
            DocumentBuilder docBuilder =
                    factory.newDocumentBuilder();

            // Запускаємо аналіз вхідного файлу
            Document doc = docBuilder.parse(new File(filename));

            // Виводимо дерево DOM на екран
            new ParseBooksDOM().printNode(doc, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
