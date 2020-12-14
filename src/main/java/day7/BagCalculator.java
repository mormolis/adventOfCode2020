package day7;

import java.util.*;

public class BagCalculator {

    public int bagsWithinGoldenBags(List<BagRules> rules) {
        final ArrayDeque<Bag> stackOfBags = new ArrayDeque<>();
        stackOfBags.push(new Bag("shiny gold", 1)); // given from problem description "single shiny bag"
        int total = 1;
        while (!stackOfBags.isEmpty()) {
            final Bag bagToCheck = stackOfBags.pop();
            for (BagRules rule : rules) {
                if (rule.getMainBag().equals(bagToCheck.getName())) {
                    final Map<String, Integer> contains = rule.getContains();
                    for (Map.Entry<String, Integer> bag : contains.entrySet()) {
                        stackOfBags.push(new Bag(bag.getKey(), bag.getValue() * bagToCheck.getCapacity()));
                        total += bag.getValue() * bagToCheck.getCapacity();
                    }
                }
            }
        }
        return total;
    }


    public int atLeastOneShinyGoldContainers(List<BagRules> rules) {
        return atLeastOneShinyGoldContainersSet(rules).size();
    }

    private Set<String> atLeastOneShinyGoldContainersSet(List<BagRules> rules) {
        Set<String> uniqueGoldBagContainers = new HashSet<>();

        for (BagRules rule : rules) {
            if (rule.getContains().containsKey("shiny gold")) {
                uniqueGoldBagContainers.add(rule.getMainBag());
            }
        }
        searchForShinyGoldBagsInDepth(rules, uniqueGoldBagContainers);

        return uniqueGoldBagContainers;
    }

    private void searchForShinyGoldBagsInDepth(List<BagRules> rules, Set<String> uniqueGoldBagContainers) {
        Set<String> set = new HashSet<>(uniqueGoldBagContainers);
        for (String uniqueGoldBagContainer : set) {
            for (BagRules rule : rules) {
                if (rule.getContains().containsKey(uniqueGoldBagContainer)) {
                    uniqueGoldBagContainers.add(rule.getMainBag());
                }
            }
        }
        if (set.size() != uniqueGoldBagContainers.size()) {
            searchForShinyGoldBagsInDepth(rules, uniqueGoldBagContainers);
        }
    }
}
