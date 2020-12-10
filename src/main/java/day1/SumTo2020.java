package day1;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class SumTo2020 {

    public int getProductOf2(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i) + list.get(j)) == 2020) {
                    return list.get(i) * list.get(j);
                }
            }
        }
        throw new RuntimeException("None of the inputs were summing to 2020");
    }

    public int getProductOf3(List<Integer> list) {
        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for(int y = j + 1; y < list.size(); y++)
                if ((list.get(i) + list.get(j)) + list.get(y) == 2020) {
                    return list.get(i) * list.get(j) * list.get(y);
                }
            }
        }
        throw new RuntimeException("None of the inputs were summing to 2020");
    }


}
