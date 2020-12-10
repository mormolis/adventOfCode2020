package day2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    private static final Pattern REGEX_PATTERN = Pattern.compile("(\\d*)-(\\d*) (.): (.*)");

    public int numberOfValidPasswords(List<String> passwordDb, Validator validator) {
        int validPasswords = 0;

        for (String entry : passwordDb) {
            Matcher matcher = REGEX_PATTERN.matcher(entry);
            if(matcher.find()){
                int downLimit = Integer.parseInt(matcher.group(1));
                int upLimit = Integer.parseInt(matcher.group(2));
                String letter = matcher.group(3);
                String password = matcher.group(4);
                System.out.println(password);
                if (validator.passwordIsValid(downLimit,upLimit,letter,password)) {
                    validPasswords++;
                }
            }
        }
        return validPasswords;
    }

}
