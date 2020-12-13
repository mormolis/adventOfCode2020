package day7;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BagCalculator {

    private final ConcurrentHashMap<String, Integer> tree = new ConcurrentHashMap<>();

    public long bagsWithinGoldenBags(List<BagRules> rules) {

        tree.putAll(
                rules.stream().filter(rule -> "shiny gold".equals(rule.getMainBag()))
                        .map(BagRules::getContains)
                        .findFirst() // given from the problem description (single shiny bag)
                        .orElseThrow(() -> new RuntimeException("No Shiny Bags found"))
        );

        populateTree(rules);
        System.out.println(tree);
        return tree.values().stream().reduce(0, Integer::sum);

    }

    private void populateTree(List<BagRules> rules) {
        final Set<Map.Entry<String, Integer>> entries = tree.entrySet();
        Map<String, Integer> toGoToTheTree = new HashMap<>(tree);
        for (Map.Entry<String, Integer> entry : entries) {
            final Map<String, Integer> mustContain = findRuleMultipliedBy(entry.getKey(), entry.getValue(), rules);
            toGoToTheTree.putAll(mustContain);
        }
        if (tree.size() == toGoToTheTree.size()) {
            return;
        }
        tree.putAll(toGoToTheTree);
        populateTree(rules);
    }

    private Map<String, Integer> findRuleMultipliedBy(String bag, int multiplier, List<BagRules> rules) {
        for (BagRules rule : rules) {
            if (rule.getMainBag().equals(bag)) {
                final Map<String, Integer> contains = rule.getContains();
                return multiplyValuesWith(multiplier, contains);
            }
        }
        System.out.println("empty rule!!!!");
        return new HashMap<>();
    }

    private Map<String, Integer> multiplyValuesWith(int multiplier, Map<String, Integer> contains) {
        Map<String, Integer> multiplied = new HashMap<>();
        final Set<Map.Entry<String, Integer>> entries = contains.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            multiplied.put(entry.getKey(), entry.getValue() * multiplier);
        }
        return multiplied;
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
