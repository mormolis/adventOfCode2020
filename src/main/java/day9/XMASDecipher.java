package day9;

import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;

public class XMASDecipher {

    public static final int PREAMBLE_SIZE = 25;

    public long sumOfMinAndMax(List<Long> digits) {
        long shouldSumToThis = firstNumberToFailCheck(digits);
        //the list of numbers between the shouldSumToThis are not contiguous, therefore should be split
        List<List<Long>> twoContiguousLists = separateIntoTwoContiguousLists(digits, shouldSumToThis);
        for (List<Long> list : twoContiguousLists) {
            for (int firstIndex = 0; firstIndex < list.size(); firstIndex++) {
                long sum = 0;
                int lastIndex = findLastIndex(shouldSumToThis, list, sum, firstIndex);
                if (lastIndex != -1) {
                    return sumMaxAndMinWithinRangeOf(list, firstIndex, lastIndex);
                }
            }
        }
        throw new RuntimeException("Sum could not be found!");
    }

    private int findLastIndex(long shouldSumToThis, List<Long> list, long sum, int startIndex) {
        while (startIndex < list.size() && sum < shouldSumToThis) {
            sum += list.get(startIndex);
            if (sum == shouldSumToThis) {
                return startIndex;
            }
            startIndex++;
        }
        return -1;
    }

    private long sumMaxAndMinWithinRangeOf(List<Long> list, int j, int lastIndex) {
        List<Long> numbers = list.subList(j, lastIndex +1);
        final LongSummaryStatistics longSummaryStatistics = numbers.stream().mapToLong(l -> l).summaryStatistics();
        return longSummaryStatistics.getMin() + longSummaryStatistics.getMax();
    }

    private List<List<Long>> separateIntoTwoContiguousLists(List<Long> digits, long sum) {
        final int splitIndex = digits.indexOf(sum);
        final List<Long> firstList = digits.subList(0, splitIndex);
        final List<Long> secondList = digits.subList(splitIndex + 1, digits.size());
        return List.of(firstList, secondList);
    }

    public long firstNumberToFailCheck(List<Long> digits) {
        LinkedList<Long> preambleList = new LinkedList<>();
        populatePreambleList(digits, preambleList);
        for (int i = PREAMBLE_SIZE; i < digits.size(); i++) {
            final Long current = digits.get(i);
            for (int pi = 0; pi < PREAMBLE_SIZE; pi++) {
                final Long checking = preambleList.remove(pi); //temporarily remove the element from the list to avoid comparing with itself
                final long needsToBeInThePreambleList = Math.abs(current - checking);
                if (preambleList.contains(needsToBeInThePreambleList)) {
                    preambleList.add(pi, checking); // put the element back
                    break;
                }
                preambleList.add(pi, checking); // put the element back
                if (allNumbersHaveBeenCheckedInThePreamble(pi)) {
                    return current;
                }
            }
            preambleList.removeFirst();
            preambleList.addLast(current);
        }
        throw new RuntimeException("The Sequence is fine...");
    }

    private void populatePreambleList(List<Long> digits, LinkedList<Long> preableList) {
        for (int i = 0; i < PREAMBLE_SIZE; i++) {
            preableList.addLast(digits.get(i));
        }
    }

    private boolean allNumbersHaveBeenCheckedInThePreamble(int pi) {
        return pi + 1 == PREAMBLE_SIZE;
    }
}
