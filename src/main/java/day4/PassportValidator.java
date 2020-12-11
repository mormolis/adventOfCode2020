package day4;

import java.util.List;
import java.util.regex.Pattern;

public class PassportValidator {

    private static final List<String> REQUIRED_PASSPORT_FIELDS = List.of("iyr", "byr", "eyr", "hgt", "hcl", "ecl", "pid");
    private static final String OPTIONAL_FIELD = "cid";
    private static final List<String> EYE_COLOURS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    private static final Pattern HAIR_COLOUR_PATTERN = Pattern.compile("#[a-f0-9]{6}");


    public int containsAllRequiredFields(List<String> passportEntries) {
        return (int) passportEntries.stream()
                .filter(this::entryHasAllRequiredFields)
                .count();
    }

    public int isPassportValid(List<String> passportEntries) {
            return (int) passportEntries.stream()
                    .filter(this::entryHasAllRequiredFields)
                    .filter(entry -> allFieldsAreValid(entry.split(" ")))
                    .count();
    }

    //field -> xxx:yyy
    private boolean allFieldsAreValid(String[] passportFields) {
        boolean result = true;
        try {
            for (String field : passportFields) {
                String value = field.substring(4);
                result = validateDateOfBirth(result, field, value);
                result = validateIssuanceYear(result, field, value);
                result = validateExpirationYear(result, field, value);
                result = validateHeight(result, field, value);
                result = validateHairColour(result, field, value);
                result = validateEyeColour(result, field, value);
                result = validatePassportId(result, field, value);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return result;
    }

    private boolean validatePassportId(boolean result, String field, String value) {
        if (field.startsWith("pid:")) {
            Integer.parseInt(value); // number or throw
            result = result && value.length() == 9;
        }
        return result;
    }

    private boolean validateEyeColour(boolean result, String field, String value) {
        if (field.startsWith("ecl:")) {
            result = result && EYE_COLOURS.contains(value);
        }
        return result;
    }

    private boolean validateHairColour(boolean result, String field, String value) {
        if (field.startsWith("hcl:")) {
            result = result && HAIR_COLOUR_PATTERN.matcher(value).matches();
        }
        return result;
    }

    private boolean validateHeight(boolean result, String field, String value) {
        if (field.startsWith("hgt:")) {
            final int height = Integer.parseInt(field.substring(4, field.length() - 2));
            if (value.endsWith("cm")) {
                result = result && height >= 150 && height <= 193;
            } else if (value.endsWith("in")) {
                result = result && height >= 59 && height <= 76;
            } else {
                result = false;
            }
        }
        return result;
    }

    private boolean validateExpirationYear(boolean result, String field, String value) {
        if (field.startsWith("eyr:")) {
            final int byr = Integer.parseInt(value);
            result = result && (byr >= 2020 && byr <= 2030);
        }
        return result;
    }

    private boolean validateIssuanceYear(boolean result, String field, String value) {
        if (field.startsWith("iyr:")) {
            final int byr = Integer.parseInt(value);
            result = result && (byr >= 2010 && byr <= 2020);
        }
        return result;
    }

    private boolean validateDateOfBirth(boolean result, String field, String value) {
        if (field.startsWith("byr:")) {
            final int byr = Integer.parseInt(value);
            result = result && (byr >= 1920 && byr <= 2002);
        }
        return result;
    }

    private boolean entryHasAllRequiredFields(String entry) {
        return REQUIRED_PASSPORT_FIELDS.stream()
                .allMatch(s -> entry.contains(s + ":"));
    }
}
