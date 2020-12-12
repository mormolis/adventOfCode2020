package day5;

import java.util.List;
import java.util.stream.Collectors;

public class BoardingPassAnalyser {

    public int findSeat(List<String> boardingPasses) {
        final List<Integer> seats = sortSeatNumbers(boardingPasses);
        for (int i = 0; i < seats.size() - 1; i++) {
            if (seats.get(i + 1) - seats.get(i) != 1) {
                return seats.get(i) + 1;
            }
        }
        throw new RuntimeException("Seat not found!");
    }

    public int findMaxId(List<String> boardingPasses) {
        return boardingPasses.stream()
                .map(this::calculateSeatId)
                .max(Integer::compare)
                .orElseThrow(()->new RuntimeException("Could not find max value"));
    }

    private int calculateSeatId(String boardingPass) {

        int[] bounds = {0, 127};
        for (int i = 0; i < 7; i++) {
            char c = boardingPass.charAt(i);
            bounds = calculateNewBounds(c, bounds[0], bounds[1]);
        }
        int finalRow = boardingPass.charAt(6) == 'F' ? bounds[0] : bounds[1];

        bounds = new int[]{0, 7};
        for (int i = 7; i <= 9; i++) {
            char c = boardingPass.charAt(i);
            bounds = calculateNewBounds(c, bounds[0], bounds[1]);
        }
        int finalColumn = boardingPass.charAt(9) == 'R' ? bounds[1] : bounds[0];

        return finalRow * 8 + finalColumn;
    }

    private List<Integer> sortSeatNumbers(List<String> boardingPasses) {
        return boardingPasses.stream().map(this::calculateSeatId)
                .sorted()
                .collect(Collectors.toList());
    }


    /**
     * Start by considering the whole range, rows 0 through 127.
     * F means to take the lower half, keeping rows 0 through 63.
     * B means to take the upper half, keeping rows 32 through 63.
     * F means to take the lower half, keeping rows 32 through 47.
     * B means to take the upper half, keeping rows 40 through 47.
     * B keeps rows 44 through 47.
     * F keeps rows 44 through 45.
     * The final F keeps the lower of the two, row 44.
     */
    int[] calculateNewBounds(char c, int lower, int higher) {
        if (c == 'F' || c == 'L') {
            int newHigher = (higher - lower) / 2 + lower;
            return new int[]{lower, newHigher};
        }
        if (c == 'B' || c == 'R') {
            int newLower = ((higher - lower) / 2) + 1 + lower;
            return new int[]{newLower, higher};
        }
        return new int[]{lower, higher};
    }
}
