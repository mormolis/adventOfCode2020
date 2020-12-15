package day10;

import java.util.Formatter;
import java.util.List;
import java.util.stream.LongStream;

public class AdaptorCalculator {

    public int productOfOneAndThreeDiff(List<Integer> joltages) {
        joltages.sort(Integer::compareTo);
        System.out.println(joltages);
        int oneDiff = 0;
        int threeDiff = 0;
        for (int i = 0; i < joltages.size() - 1; i++) {
            int diff = joltages.get(i + 1) - joltages.get(i);
            if (diff == 3) {
                threeDiff++;
            } else if (diff == 1) {
                oneDiff++;
            }
        }
        //+1 for the build in adapters
        return (oneDiff + 1) * (threeDiff + 1);
    }
}
