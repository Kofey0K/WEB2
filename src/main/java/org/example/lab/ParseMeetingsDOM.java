package org.example.lab;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseMeetingsDOM {
    public static void main(String[] args) {
        try {
            String fileName = "files/meetings.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));

            NodeList meetingList = doc.getElementsByTagName("Meeting");
            for (int i = 0; i < meetingList.getLength(); i++) {
                Node meeting = meetingList.item(i);
                if (meeting.getNodeType() == Node.ELEMENT_NODE) {
                    Element meetingElement = (Element) meeting;
                    String date = meetingElement.getAttribute("date");
                    String time = meetingElement.getElementsByTagName("Time").item(0).getTextContent();
                    String person = meetingElement.getElementsByTagName("Person").item(0).getTextContent();
                    String place = meetingElement.getElementsByTagName("Place").item(0).getTextContent();
                    System.out.println("Meeting Date: " + date);
                    System.out.println("Time: " + time);
                    System.out.println("Person: " + person);
                    System.out.println("Place: " + place);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

