package day7;

import java.util.Map;
import java.util.Objects;

public class BagRules {
    private String mainBag;
    private Map<String, Integer> contains;

    public BagRules(String mainBag, Map<String, Integer> contains) {
        this.mainBag = mainBag;
        this.contains = contains;
    }

    @Override
    public String toString() {
        return "BagRules{" +
                "mainBag='" + mainBag + '\'' +
                ", contains=" + contains +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagRules bagRules = (BagRules) o;
        return Objects.equals(mainBag, bagRules.mainBag) && Objects.equals(contains, bagRules.contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainBag, contains);
    }

    public String getMainBag() {
        return mainBag;
    }

    public void setMainBag(String mainBag) {
        this.mainBag = mainBag;
    }

    public Map<String, Integer> getContains() {
        return contains;
    }

    public void setContains(Map<String, Integer> contains) {
        this.contains = contains;
    }
}
