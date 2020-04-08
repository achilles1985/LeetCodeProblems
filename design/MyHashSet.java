package design;

/**E
 * Design a HashSet without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 *
 *     add(value): Insert a value into the HashSet.
 *     contains(value) : Return whether the value exists in the HashSet or not.
 *     remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 *     Note:
 *     All values will be in the range of [0, 1000000].
 *     The number of operations will be in the range of [1, 10000].
 *     Please do not use the built-in HashSet library.
 */
public class MyHashSet {

    private boolean[] arr;

    /** Initialize your data structure here. */
    public MyHashSet() {
        arr = new boolean[1000001];
    }

    public void add(int key) {
        arr[key] = true;
    }

    public void remove(int key) {
        arr[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key] == true;
    }

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1)); // true
        System.out.println(hashSet.contains(3)); // false (not found)
        hashSet.add(2);
        System.out.println(hashSet.contains(2)); // true
        hashSet.remove(2);
        System.out.println(hashSet.contains(2)); // false (already removed)
    }
}
