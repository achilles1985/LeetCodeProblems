package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysTest {

    public static void main(String[] args) {
        Map<String, String> s1 = new HashMap();
        s1.put(null, "abs");
        s1.put("abc", "123");
        s1.put(null, "njk");
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        String[] arr2 = new String[]{"a","b","c","d","e"};
        SolutionUtils.print(arr);
        SolutionUtils.print(arr2);

        // copyOf
        int[] copy = Arrays.copyOf(arr, 3); // [1,2,3]
        SolutionUtils.print(copy);

        // copyOfRange
        String[] strCopy2 = Arrays.copyOfRange(arr2, 1, 3); //[b,c]
        SolutionUtils.print(strCopy2);

        // System.arraycopy()
        int[] dest = new int[10];
        System.arraycopy(arr, 0, dest, 2, 3);
        SolutionUtils.print(dest);

        // sort
        Arrays.sort(arr);
        SolutionUtils.print(arr);

        // binarySearch
        System.out.println(Arrays.binarySearch(arr, 12));
        System.out.println(Arrays.binarySearch(arr, 6));

        List<Integer> l = new ArrayList<>();

        // sorting
        final int[] sorted = Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> a - b)
                .mapToInt(i -> i)
                .toArray();

        Integer[] input = Arrays.stream(arr)
                .boxed()
                .toArray(Integer[]::new);
        Arrays.sort(input, (a, b) -> b - a); // reverse order
        final int[] sortedArr = Arrays.stream(input)
                .mapToInt(Integer::intValue)
                .toArray();

    }
}
