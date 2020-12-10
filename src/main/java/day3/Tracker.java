package day3;

import java.util.List;

public class Tracker {

    public int traverse(List<String> terrain, int rightPace, int downPace) {
        int trees = 0;
        int i = rightPace;
        for (int lineIndex = downPace; lineIndex < terrain.size(); lineIndex+=downPace) {
            StringBuilder line = new StringBuilder(terrain.get(lineIndex));
            extendLineWhenLimitHasReached(terrain, i, lineIndex, line);

            if (line.charAt(i) == '#') {
                trees++;
            }

            i += rightPace;
        }
        return trees;
    }

    private void extendLineWhenLimitHasReached(List<String> terrain, int i, int lineIndex, StringBuilder line) {
        while (line.length() <= i) {
            line.append(terrain.get(lineIndex));
        }
    }
}
