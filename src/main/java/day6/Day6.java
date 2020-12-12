package day6;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day6 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI answersPath = Objects.requireNonNull(Day6.class.getClassLoader().getResource("day6/answers")).toURI();
        List<String> answers = fileReader.getAsListOfStringsWithSeparator(answersPath, "\n\n","");

        QuestionerAnalyser questionerAnalyser = new QuestionerAnalyser();
        System.out.println(questionerAnalyser.sumOfAffirmativeAnswers(answers)); //6587

        List<String> nonGroupedAnswers = fileReader.getAsListOfStringsWithSeparator(answersPath, "\n\n"," ");
        System.out.println(questionerAnalyser.sumOfGroupsAnsweredYesToALlQuestions(nonGroupedAnswers)); //3235
    }
}
