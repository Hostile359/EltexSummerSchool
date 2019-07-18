package ru.eltex;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.ArrayList;

class Main {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/resources/index.xml"));
        NodeList msgElements = document.getDocumentElement().getElementsByTagName("msg");
        for (int i = 0; i < msgElements.getLength(); i++) {
            Node msg = msgElements.item(i);
            NamedNodeMap attributes = msg.getAttributes();
            System.out.print("Message to " + attributes.getNamedItem("to").getNodeValue() + ", from " + attributes.getNamedItem("from").getNodeValue());
            System.out.print(", title: " + attributes.getNamedItem("title").getNodeValue() + "\n");//", from " + attributes.getNamedItem("from").getNodeValue());
            System.out.println("Body text: " + msg.getTextContent() + "\n");
        }
    }
}
