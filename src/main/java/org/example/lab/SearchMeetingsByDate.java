package org.example.lab;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchMeetingsByDate {
    final static String FOLDER = "files/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date (YYYY-MM-DD) to search for meetings: ");
        String userInputDate = scanner.nextLine();
        scanner.close();

        searchMeetingsByDate(userInputDate);
    }

    public static void searchMeetingsByDate(String date) {
        try {
            String inputFile = FOLDER + "meetings.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(inputFile));

            NodeList meetingList = doc.getElementsByTagName("Meeting");
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><body><h2>Meetings on ").append(date).append("</h2><table border=\"1\">");
            htmlContent.append("<tr><th>Date</th><th>Time</th><th>Person</th><th>Place</th></tr>");

            boolean meetingFound = false;

            for (int i = 0; i < meetingList.getLength(); i++) {
                Node meeting = meetingList.item(i);
                if (meeting.getNodeType() == Node.ELEMENT_NODE) {
                    Element meetingElement = (Element) meeting;
                    String meetingDate = meetingElement.getAttribute("date");
                    if (meetingDate.equals(date)) {
                        meetingFound = true;
                        String time = meetingElement.getElementsByTagName("Time").item(0).getTextContent();
                        String person = meetingElement.getElementsByTagName("Person").item(0).getTextContent();
                        String place = meetingElement.getElementsByTagName("Place").item(0).getTextContent();
                        htmlContent.append("<tr><td>").append(meetingDate)
                                .append("</td><td>").append(time)
                                .append("</td><td>").append(person)
                                .append("</td><td>").append(place)
                                .append("</td></tr>");
                    }
                }
            }

            if (!meetingFound) {
                htmlContent.append("<tr><td colspan=\"4\">No meetings found for this date</td></tr>");
            }

            htmlContent.append("</table></body></html>");
            saveHTMLFile(FOLDER + "meetings_on_" + date + ".html", htmlContent.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHTMLFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                java.io.FileWriter writer = new java.io.FileWriter(file);
                writer.write(content);
                writer.close();
                System.out.println("HTML file generated: " + fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
