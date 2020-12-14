package day7;

import java.util.*;

public class BagCalculator {

    private final ArrayDeque<Bag> stackOfBags = new ArrayDeque<>();
    private final ArrayDeque<Integer> stackOfNumbers = new ArrayDeque<>();

    public int bagsWithinGoldenBags(List<BagRules> rules) {
        stackOfBags.push(new Bag("shiny gold", 1)); // given from problem description "single shiny bag"
        stackOfNumbers.push(1);
        while(!stackOfBags.isEmpty()) {
            final Bag bagToCheck = stackOfBags.pop();
            for(BagRules rule : rules){
                if(rule.getMainBag().equals(bagToCheck.getName())){
                    final Map<String, Integer> contains = rule.getContains();
                    for(Map.Entry<String,Integer> bag : contains.entrySet()){
                        stackOfBags.push(new Bag(bag.getKey(), bag.getValue() * bagToCheck.getCapacity()));
                        stackOfNumbers.push(bag.getValue()* bagToCheck.getCapacity());
                    }
                }
            }
        }

        // -1 the initial shiny bag
        return stackOfNumbers.stream().reduce(1, Integer::sum) - 1;

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
