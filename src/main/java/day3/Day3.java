package day3;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day3 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI terrainUri = Objects.requireNonNull(Day3.class.getClassLoader().getResource("day3/terrain")).toURI();
        List<String> terrain = fileReader.getAsListOfStrings(terrainUri);

        Tracker tracker = new Tracker();
        long product = (long) tracker.traverse(terrain, 1, 1) *
                tracker.traverse(terrain,3,1) *
                tracker.traverse(terrain,5,1) *
                tracker.traverse(terrain,7,1) *
                tracker.traverse(terrain,1,2);
        System.out.println(product);

    }
}
