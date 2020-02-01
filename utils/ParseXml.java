package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ParseXml {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String path = "utils/text.xml";

        Parser parser = new Parser();
        String text = parser.parseXml(path);
        Set<String> distinct = parser.getText(text);
    }

    private static final class Parser {

        Set<String> getText(String text) throws IOException {
            return Arrays.stream(text.split("\\W+"))
                    .map(w -> w.toLowerCase())
                    .collect(Collectors.toSet());
        }

        String parseXml(String path) throws IOException, SAXException, ParserConfigurationException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Paths.get(path).toFile());

            NodeList header = document.getDocumentElement().getElementsByTagName("Header");
            NodeList body = document.getDocumentElement().getElementsByTagName("Body");

            return header.item(0).getTextContent() + body.item(0).getTextContent();
        }
    }

}
