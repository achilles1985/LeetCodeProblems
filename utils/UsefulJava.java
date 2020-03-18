package utils;

import java.util.*;
import java.util.stream.IntStream;

/*
    1. private inner class is not visible from other classes except outer one. If inner class is public, class's private methods are not visible from other classes, except outer one.
    If inner class is private, its public methods are not visible from other class except outer one.
 */
public class UsefulJava {

    public static void main(String[] args) {
        // computeIfAbsent vs PutIfAbsent
        //computeIfAbsent takes a mapping function, that is called to obtain the value if the key is missing.
        String[] words = new String[]{"malok", "malo", "kalo", "malok", "malok", "malo"};
        Map<String, List<String>> map = new HashMap<>();
        for (String world: words) {
            map.computeIfAbsent(world, w -> new ArrayList<>()).add(world); // If no value for a given key, compute it by function provided and return. Otherwise, return the value.
        }
        System.out.println(map);

        //putIfAbsent takes the value directly.
        Map<String, String> map2 = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map2.putIfAbsent(words[i], words[i]+i); // If value for given key exists, return it, otherwise return newly inserted value (words[i]+i)
        }
        System.out.println(map2);

        //////// Iterators /////////////
        int[][] matrix = new int[][]{{1,2},{3},{4,5}};
        Iterator<int[]> outerIterator = Arrays.stream(matrix).iterator();
        Iterator<Integer> innerIterator = IntStream.of(matrix[0]).boxed().iterator();

        ////////////// from/to binary //////////////////
        int res1 = Integer.parseInt("0101", 2); //5
        String res2 = Integer.toBinaryString(5); //0101

        // List<Integer> to int[]
        List<Integer> nums = Arrays.asList(1,2,3);
        nums.stream()
                .mapToInt(n -> n)
                .toArray();
    }

    // start from second element from the last
    public static void workWithArrays(int[] arr) {
        int i = arr.length-2;
        while (i >= 0 && arr[i] > arr[i+1]) {
            i--;
        }
    }

    public static void traverseStackFromBegin(Stack<Integer> stack) { // or use iterator
        StringBuilder sb = new StringBuilder();
        for (int item: stack) {
            sb.append(item);
        }
    }
}
