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

    private Map<Integer, CacheItem> map = new HashMap<>();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        CacheItem current = map.get(key);
        if (current == null) {
            return -1;
        }
        removeFromLinkedList(current);
        addTail(current);

        return current.value;
    }

    private void removeFromLinkedList(CacheItem current) {
        // remove tail
        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else if (current == head) {
            head = head.next;
            head.prev = null;
        } else { // remove from the middle
            CacheItem prev = current.prev;
            CacheItem next = current.next;
            prev.next = next;
            if (next != null) {
                next.prev = prev;
            }
        }
    }

    public void put(int key, int value) {
        if (map.size() == capacity) {
            removeHead();
        }
        CacheItem node = new CacheItem(key, value);
        addTail(node);
        map.put(key, node);
    }

    private void addTail(CacheItem node) {
        if (tail == null) {
            tail = head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
    }

    private void removeHead() {
        map.remove(head.key);
        head = head.next;
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
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
