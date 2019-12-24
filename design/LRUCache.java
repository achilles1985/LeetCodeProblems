package design;

import java.util.HashMap;
import java.util.Map;

/**M
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
public class LRUCache {
    private CacheItem head;
    private CacheItem tail;

    private Map<Integer, CacheItem> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new CacheItem();
        tail = new CacheItem();
        map = new HashMap<>();

        head.next = tail;
        tail.prev = head;
    }

    // O(1) - time
    public void put(int key, int value) {
        CacheItem oldNode = map.get(key);
        if (oldNode == null) {
            CacheItem newNode = new CacheItem(key, value);
            map.put(key, newNode);
            addFront(newNode);
            if (map.size() > capacity) {
                removeFromLRUCache();
            }
        } else {
            oldNode.value = value;
            map.put(key, oldNode);
            moveToHead(oldNode);
        }
    }

    // O(1) - time
    public int get(int key) {
        CacheItem current = map.get(key);
        if (current == null) {
            return -1;
        }
        moveToHead(current);
        return current.value;
    }

    private void moveToHead(CacheItem node) {
        removeFromList(node);
        addFront(node);
    }

    private void removeFromList(CacheItem node) {
        CacheItem next = node.next;
        CacheItem prev = node.prev;
        prev.next = next;
        next.prev = prev;
    }

    private void addFront(CacheItem node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeFromLRUCache() {
        CacheItem node = tail.prev;
        removeFromList(node);
        map.remove(node.key);
    }

    private static class CacheItem {
        int key;
        int value;
        CacheItem next;
        CacheItem prev;

        public CacheItem(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public CacheItem() {
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 1
        cache.put(3, 3);                        // evicts key 2
        System.out.println(cache.get(2));       // -1
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // -1
        System.out.println(cache.get(3));       // 3
        System.out.println(cache.get(4));       // 4
    }
}
