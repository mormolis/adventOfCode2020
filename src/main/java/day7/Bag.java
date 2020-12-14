package day7;

import java.util.Objects;

class Bag {
    private final String name;
    private final int capacity;

    public Bag(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return capacity == bag.capacity && Objects.equals(name, bag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }
}