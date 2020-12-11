package day4;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day4 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI passports = Objects.requireNonNull(Day4.class.getClassLoader().getResource("day4/passports")).toURI();
        final List<String> listOfPassport = fileReader.getAsListOfStringsWithSeparator(passports, "\n\n");

        PassportValidator passportValidator = new PassportValidator();
        System.out.println(passportValidator.containsAllRequiredFields(listOfPassport)); //190
        System.out.println(passportValidator.isPassportValid(listOfPassport)); //121
    }
}
