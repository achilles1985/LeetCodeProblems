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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ParseXml {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String path = "utils/text.xml";

        Parser parser = new Parser();
        String text = parser.getContent(path);
        Set<String> distinct = parser.getDistinctWords(text);
        System.out.println(distinct);

        Set<String> s1 = new HashSet<>(Arrays.asList("a", "b", "c", "z"));
        Set<String> s2 = new HashSet<>(Arrays.asList("a", "b", "n", "m"));
        //Set<String> s1 = new HashSet<>(Arrays.asList(""));
        //Set<String> s2 = new HashSet<>(Arrays.asList(""));
        System.out.println(parser.calculateSimilarity(s1, s2));
    }

    private static final class Parser {

        private Set<String> getDistinctWords(String text) throws IOException {
            Set<String> stopWords = new HashSet<>(Arrays.asList("a", "the", "i", "am", "are"));
            return Arrays.stream(text.split("\\W+"))
                    .map(w -> w.toLowerCase())
                    .filter(w -> !stopWords.contains(w))
                    .collect(Collectors.toSet());
        }

        private static String getContent(String path) throws IOException, SAXException, ParserConfigurationException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Paths.get(path).toFile());

            NodeList header = document.getDocumentElement().getElementsByTagName("Header");
            NodeList body = document.getDocumentElement().getElementsByTagName("Body");

            return header.item(0).getTextContent() + body.item(0).getTextContent();
        }

        private double calculateSimilarity(Set<String> distinct1, Set<String> distinct2) {
            long matches = 0;
            for (String word: distinct1) {
                if (distinct2.contains(word)) {
                    matches++;
                }
            }
            double totalDistinct = (distinct1.size() + distinct2.size()) - matches;

            return matches/totalDistinct;
        }
    }

}
