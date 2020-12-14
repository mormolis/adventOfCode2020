package day8;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI instructionsUri = Day8.class.getClassLoader().getResource("day8/instructions").toURI();
        final List<String> asListOfStrings = fileReader.getAsListOfStrings(instructionsUri);

        InstructionsRunner instructionsRunner = new InstructionsRunner(asListOfStrings);
//        System.out.println(instructionsRunner.accumulatorValueSecondTimeInstruction(asListOfStrings)); //1859
        System.out.println(instructionsRunner.swapAndCount());
    }
}
