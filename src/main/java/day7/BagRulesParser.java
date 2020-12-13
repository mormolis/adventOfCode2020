package day7;

import java.util.*;

public class BagRulesParser {

    public List<BagRules> parseFile(List<String> rules){
        List<BagRules> bagRules = new ArrayList<>();
        for(String rule: rules) {
            final String[] split = rule.split(" contain ");
            final String mainBag = split[0]
                    .replace(" bags", "")
                    .replace(" bag", "");

            final Map<String, Integer> contains = extractRules(split[1]);
            bagRules.add(new BagRules(mainBag, contains));
        }
        return bagRules;
    }

    //contains is like: 4 pale salmon bags, 1 mirrored bronze bag, 4 vibrant tomato bags, 2 dark bronze bags.
    private Map<String, Integer> extractRules(String contains) {
        final String[] split = contains.split(", ");
        Map<String, Integer> mappings = new HashMap<>();
        if("no other bags.".equals(contains)) {
            return mappings;
        }
        for(String content: split) { //4 pale salmon bags
            final String numberAndBag = content.replace(" bags", "")
                    .replace(" bag", "")
                    .replace(".", "");

            mappings.put(numberAndBag.substring(2),Integer.parseInt(numberAndBag.substring(0,1)));
        }
        return mappings;
    }
}
