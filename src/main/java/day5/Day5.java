package day5;

import commons.FileReader;
import day3.Day3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day5 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI boardingPassesUri = Objects.requireNonNull(Day3.class.getClassLoader().getResource("day5/boarding_passes")).toURI();
        List<String> boardingPasses = fileReader.getAsListOfStrings(boardingPassesUri);
        BoardingPassAnalyser boardingPassAnalyser = new BoardingPassAnalyser();
        System.out.println(boardingPassAnalyser.findMaxId(boardingPasses)); //871
        System.out.println(boardingPassAnalyser.findSeat(boardingPasses)); //640
    }
}
