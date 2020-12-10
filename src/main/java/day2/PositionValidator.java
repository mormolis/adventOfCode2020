package day2;

public class PositionValidator implements Validator {
    @Override
    public boolean passwordIsValid(int firstPosition, int secondPosition, String letter, String password) {
        String[] letters = password.split("");
        try {
            return letters[firstPosition - 1].equals(letter) ^ letters[secondPosition - 1].equals(letter);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
