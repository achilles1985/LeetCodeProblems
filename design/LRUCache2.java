package design;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache2(int initialCapacity, int capacity) {
        super(initialCapacity);
        this.capacity = capacity;
    }

    // O(1) - time
    public Integer get(Integer key) {
        return super.getOrDefault(key,-1);
    }

    // O(1) - time
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
