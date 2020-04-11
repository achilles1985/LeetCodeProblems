package design;

import java.util.*;

public class InsertDeleteGetRandom_380 {

    private Map<Integer, Integer> valueToIndex;
    private List<Integer> values;
    private Random random;

    /**
     * Initialize your data structure here.
     */
    public InsertDeleteGetRandom_380() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    // O(1) - time
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        }
        valueToIndex.put(val, values.size());
        return values.add(val);
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    // O(1) - time, remove element from last index in ArrayList is constant operation.
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }
        int index = valueToIndex.get(val);
        int lastElement = values.get(values.size() - 1);
        values.set(index, lastElement);
        valueToIndex.put(lastElement, index);
        values.remove(values.size() - 1);
        valueToIndex.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }

    public static void main(String[] args) {
        InsertDeleteGetRandom_380 s = new InsertDeleteGetRandom_380();
        s.remove(0);
        s.remove(0);
        s.insert(0);
        s.remove(0);
        s.insert(0);
        System.out.println(s.getRandom());
    }
}
