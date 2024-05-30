package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Клас є спадкоємцем оброблювача SAX
 * org.xml.sax.helpers.DefaultHandler
 */
public class ParseBooksSAX extends DefaultHandler {

    // Рядковий буфер для накопичення символів
// текстових елементів документа
    private StringBuffer stringBuffer;

    public static void main(String args[]) {
        String fileName = "files/books.xml";

        // Створюємо екземпляр оброблювача SAX
        DefaultHandler defaultHandler = new ParseBooksSAX();
        // Створюємо екземпляр класу SAXParserFactory
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            // Створюємо екземпляр класу SAXParser
            SAXParser Sax_Parser = saxParserFactory.newSAXParser();
            // Запуск парсера XML файлу
            Sax_Parser.parse(new File(fileName), defaultHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Обробляє подія початку документа
     */
    public void startDocument() throws SAXException {
        System.out.println(
                "<?xml version = '1.0' encoding = 'UTF-8'?>");
    }

    /**
     * Обробляє подія кінця документа
     */
    public void endDocument() throws SAXException {
    }

    /**
     * Обробляє подію початку елемента <...>
     */
    public void startElement(
            String namespaceURI,
            String localName,
            String qName,
            Attributes attrs) throws SAXException {
        displayBufferText();
        if ("".equals(localName))
            localName = qName;
        System.out.print("<" + localName);
        if (attrs != null ){
            // Отримуємо кількість атрибутів елемента
            for (int i = 0; i < attrs.getLength(); i++) {
                // Отримуємо локальне ім'я атрибуту
                String aName = attrs.getLocalName(i);
                if ("".equals(aName))
                    aName = attrs.getQName(i);
                System.out.print(" ");
                // Отримуємо значення атрибуту
                System.out.print(aName +
                                "=\"" +
                        attrs.getValue(i) +
                                "\"");
                    }
                }
                System. out .print(">");
            }
            
            /**
             * Обробляє подію кінця елемента </...>
             */
            public void endElement(
                    String namespaceURI,
                    String localName,
                    String qName) throws SAXException {
                displayBufferText();
                if ("".equals(localName))
                    localName = qName;
                System. out .print("</" + localName + ">");
            }
            
            /** Обробляє подію символьних даних
             * текстового елемента <...>some_text</...>
             */	 
            public void characters(
                    char buf[],
                    int offset,
                    int len) throws SAXException {
                String s = new String(buf, offset, len);
                // якщо рядковий буфер дорівнює null, створюємо новий
                if (stringBuffer == null ) {
                    stringBuffer = new StringBuffer(s);
                } else {
                    // Додаємо символи в рядковий буфер
                    stringBuffer.append(s);
                }
            }
            
            /**
             * Виводить текст, зібраний у рядковому буфері
             */
            private void displayBufferText() {
                if (stringBuffer!= null ) {
                    System. out .print(stringBuffer.toString());
                    stringBuffer = null ;
                }		
            }
            }
