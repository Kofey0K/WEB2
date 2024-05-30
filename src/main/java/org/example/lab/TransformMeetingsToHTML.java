package org.example.lab;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class TransformMeetingsToHTML {
    public static void main(String[] args) {
        try {
            final String FOLDER = "files/";
            String inputFile = FOLDER + "meetings.xml";
            String xslFile = FOLDER + "meetings.xsl";
            String outputFile = FOLDER + "meetings.html";

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File(xslFile)));
            transformer.transform(new StreamSource(new File(inputFile)), new StreamResult(new File(outputFile)));

            System.out.println("HTML file generated: " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
