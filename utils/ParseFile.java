package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParseFile {

    public static void main(String[] args) throws URISyntaxException {
        ParseFile fp = new ParseFile();
        Set<String> set = fp.parseAndSortFile("restChecks.txt");
        fp.writeToFile(set);
    }

    public void writeToFile(Set<String> set) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("utils/rest.txt"))) {
            for (String line: set) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> parseAndSortFile(String filename) throws URISyntaxException {
        NavigableSet<String> set = new TreeSet<>();
        InputStream resourceAsStream = ParseFile.class.getResourceAsStream(filename);
        try {
            SortedMap<String, String> map = readFromInputStream(resourceAsStream);
            for (Map.Entry<String, String> e: map.entrySet()) {
                set.add(e.getKey() + " -> " + e.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return set;
    }

    private SortedMap<String, String> readFromInputStream(InputStream inputStream) throws IOException {
        SortedMap<String, String> map = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(" -> ");
                map.put(row[0], row[1]);
            }
        }
        return map;
    }
}

