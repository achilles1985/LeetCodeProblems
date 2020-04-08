package design;

/**
 * E
 * Design a HashMap without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 * <p>
 * Example:
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 * <p>
 * Note:
 * All keys and values will be in the range of [0, 1,000,000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 */
public class MyHashMap2 {

    private Node[] buckets;
    private final static int SIZE = 1000;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap2() {
        buckets = new Node[SIZE];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = key%SIZE;
        if (buckets[hash] == null) {
            buckets[hash] = new Node(key, value);
            return;
        }
        Node temp = buckets[hash];
        while (temp != null) {
            if (temp.key == key) {
                temp.value = value;
                return;
            }
            temp = temp.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = buckets[hash];
        buckets[hash] = newNode;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = key%SIZE;
        Node temp = buckets[hash];
        while (temp != null) {
            if (temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = key%SIZE;
        if (buckets[hash] == null) {
            return;
        }
        Node temp = buckets[hash];
        if (temp.key == key) {
            buckets[hash] = temp.next;
        }
        while (temp.next != null) {
            if (temp.next.key == key) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    private static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap2 hashMap = new MyHashMap2();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));            // returns 1
        System.out.println(hashMap.get(3));            // returns -1 (not found)
        hashMap.put(2, 1);                             // update the existing value
        System.out.println(hashMap.get(2));            // returns 1
        hashMap.remove(2);                        // remove the mapping for 2
        System.out.println(hashMap.get(2));            // returns -1 (not found)
    }
}
