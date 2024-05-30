package org.example.lab;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseMeetingsSAX extends DefaultHandler {
    private String currentElement;
    private String currentDate;

    public static void main(String[] args) {
        String fileName = "files/meetings.xml";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ParseMeetingsSAX handler = new ParseMeetingsSAX();
            saxParser.parse(new File(fileName), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if (qName.equalsIgnoreCase("Meeting")) {
            currentDate = attributes.getValue("date");
            System.out.println("Meeting Date: " + currentDate);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement != null) {
            String content = new String(ch, start, length).trim();
            if (currentElement.equalsIgnoreCase("Time")) {
                System.out.println("Time: " + content);
            } else if (currentElement.equalsIgnoreCase("Person")) {
                System.out.println("Person: " + content);
            } else if (currentElement.equalsIgnoreCase("Place")) {
                System.out.println("Place: " + content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = null;
    }
}