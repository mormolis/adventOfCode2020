package day2;

import java.util.Arrays;

public class BoundedValidator implements Validator {
    @Override
    public boolean passwordIsValid(int downLimit, int upLimit, String letter, String password) {
        int numberOfLettersInAString = numberOfLettersInAString(letter, password);
        return numberOfLettersInAString <= upLimit && numberOfLettersInAString >= downLimit;    }

    private int numberOfLettersInAString(String letter, String string) {
        return (int) Arrays.stream(string.split(""))
                .filter(l -> l.equals(letter))
                .count();
    }


}
