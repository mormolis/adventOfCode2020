package day7;

import commons.FileReader;
import day3.Day3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day7 {
    public static void main(String[] args) throws URISyntaxException {

        FileReader fileReader = new FileReader();
//        URI bagRulesList = Objects.requireNonNull(Day3.class.getClassLoader().getResource("day7/test")).toURI();
        URI bagRulesList = Objects.requireNonNull(Day3.class.getClassLoader().getResource("day7/bag_rules")).toURI();
        List<String> rules = fileReader.getAsListOfStrings(bagRulesList);

        BagRulesParser bagRulesParser = new BagRulesParser();
        final List<BagRules> bagRules = bagRulesParser.parseFile(rules);
        System.out.println(new BagCalculator().atLeastOneShinyGoldContainers(bagRules)); //101
        System.out.println(new BagCalculator().bagsWithinGoldenBags(bagRules));
    }
}
