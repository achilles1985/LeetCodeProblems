package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReadWriteFiles {

    String read(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        return lines.stream().collect(Collectors.joining());
    }

    void write(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes());
    }
}
