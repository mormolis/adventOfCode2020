package day9;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Day9 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        final URI digits = Objects.requireNonNull(Day9.class.getClassLoader().getResource("day9/digits")).toURI();
        final List<Long> asListOfDigits = fileReader.getAsListOfLongs(digits);

        XMASDecipher xmasDecipher = new XMASDecipher();
        System.out.println(xmasDecipher.firstNumberToFailCheck(asListOfDigits)); //373803594
        System.out.println(xmasDecipher.sumOfMinAndMax(asListOfDigits));
    }
}
