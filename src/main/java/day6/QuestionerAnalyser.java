package day6;

import java.util.*;

public class QuestionerAnalyser {

    public int sumOfAffirmativeAnswers(List<String> groupedAnswers) {
        return groupedAnswers.stream()
                .map(this::affirmativeAnswersPerGroup)
                .reduce(0, Integer::sum);
    }

    public int sumOfGroupsAnsweredYesToALlQuestions(List<String> groupedAnswers) {
        int sum = 0;
        for (String group : groupedAnswers) { //group = "asd asd awarw"
            final String[] formattedGroup = group.split(" "); //[asd,asd,awarw]
                sum+= commonAnsweredQuestions(formattedGroup);
        }
        return sum;
    }

    private int commonAnsweredQuestions(String[] formattedGroup) {
        Map<Character, Integer> map = countSameAnswersWithinGroup(formattedGroup);

        final Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        int counter = 0;
        for(Map.Entry<Character,Integer> entry : entries) {
            if(questionHasBeenAnsweredByAllPeopleInGroup(formattedGroup, entry)){
                counter++;
            }
        }
        return counter;
    }

    private boolean questionHasBeenAnsweredByAllPeopleInGroup(String[] formattedGroup, Map.Entry<Character, Integer> entry) {
        return entry.getValue() == formattedGroup.length;
    }

    private Map<Character, Integer> countSameAnswersWithinGroup(String[] formattedGroup) {
        Map<Character, Integer> map = new HashMap<>();
        for(String answers : formattedGroup){
            for(int i=0; i<answers.length(); i ++){
                final char c = answers.charAt(i);
                if(map.containsKey(c)) {
                    map.put(c,map.get(c)+1);
                } else {
                    map.put(c,1);
                }
            }
        }
        return map;
    }

    private int affirmativeAnswersPerGroup(String groupAnswers) {
        return (int) Arrays.stream(groupAnswers.split(""))
                .distinct()
                .count();
    }
}
