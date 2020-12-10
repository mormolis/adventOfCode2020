package commons;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public List<Integer> getAsListOfInts(URI uri) {
        return getAsListOfStrings(uri).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

    }

    public List<String> getAsListOfStrings(URI uri) {
        Path path = Paths.get(uri);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not read lines");
        }
    }


}