package day7;

import java.util.*;

public class BagCalculator {

    public long bagsWithinGoldenBags(List<BagRules> rules) {
        System.out.println(rules);
        Map<String, Integer> shinyBagContents =
        rules.stream().filter(rule-> "shiny gold".equals(rule.getMainBag()))
                .map(BagRules::getContains)
                .findFirst() // given from the problem description (single shiny bag)
                .orElseThrow(()-> new RuntimeException("No Shiny Bags found"));

        long count = shinyBagContents.values().stream()
                .reduce(0, Integer::sum);


        return -1;
    }

    public int atLeastOneShinyGoldContainers(List<BagRules> rules){
        return atLeastOneShinyGoldContainersSet(rules).size();
    }

    private Set<String> atLeastOneShinyGoldContainersSet(List<BagRules> rules){
        Set<String> uniqueGoldBagContainers = new HashSet<>();

        for(BagRules rule : rules) {
            if(rule.getContains().containsKey("shiny gold")) {
                uniqueGoldBagContainers.add(rule.getMainBag());
            }
        }
        searchForShinyGoldBagsInDepth(rules, uniqueGoldBagContainers);

        return uniqueGoldBagContainers;
    }

    private void searchForShinyGoldBagsInDepth(List<BagRules> rules, Set<String> uniqueGoldBagContainers) {
        Set<String> set = new HashSet<>(uniqueGoldBagContainers);
        for(String uniqueGoldBagContainer : set) {
            for(BagRules rule: rules) {
                if(rule.getContains().containsKey(uniqueGoldBagContainer)) {
                    uniqueGoldBagContainers.add(rule.getMainBag());
                }
            }
        }
        if(set.size()!=uniqueGoldBagContainers.size()) {
            searchForShinyGoldBagsInDepth(rules, uniqueGoldBagContainers);
        }
    }
}
