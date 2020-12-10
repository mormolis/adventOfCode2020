package day1;

import commons.FileReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * --- Day 1: Report Repair ---
 * <p>
 * After saving Christmas five years in a row, you've decided to take a vacation at a nice resort on a tropical island. Surely, Christmas will go on without you.
 * <p>
 * The tropical island has its own currency and is entirely cash-only. The gold coins used there have a little picture of a starfish; the locals just call them stars. None of the currency exchanges seem to have heard of them, but somehow, you'll need to find fifty of these coins by the time you arrive so you can pay the deposit on your room.
 * <p>
 * To save your vacation, you need to get all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.
 * <p>
 * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
 * <p>
 * For example, suppose your expense report contained the following:
 * <p>
 * 1721
 * 979
 * 366
 * 299
 * 675
 * 1456
 * <p>
 * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
 * <p>
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?
 */
public class Day1 {
    public static void main(String[] args) throws URISyntaxException {
        FileReader fileReader = new FileReader();
        URI expensesFileUri = Objects.requireNonNull(Day1.class.getClassLoader().getResource("day1/expenses")).toURI();
        List<Integer> expensesAsList = fileReader.getAsListOfInts(expensesFileUri);

        SumTo2020 sumTo2020 = new SumTo2020();
        System.out.println("the answer is: " + sumTo2020.getProductOf2(expensesAsList));
        System.out.println("the answer is: " + sumTo2020.getProductOf3(expensesAsList));
    }
}
