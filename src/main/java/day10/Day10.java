package day10;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Day10 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        final URI joltagesUri = Day10.class.getClassLoader().getResource("day10/joltages").toURI();
        final List<Integer> asListOfInts = fileReader.getAsListOfInts(joltagesUri);

        AdaptorCalculator adaptorCalculator = new AdaptorCalculator();
        System.out.println(adaptorCalculator.productOfOneAndThreeDiff(asListOfInts));
    }
}
