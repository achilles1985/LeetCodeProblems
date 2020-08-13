package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ArraysTest {

    public static void main(String[] args) {
        Map<String, String> s1 = new HashMap();
        s1.put(null, "abs");
        s1.put("abc", "123");
        s1.put(null, "njk");
        int[] arr = new int[]{12,5,17};
        String[] arr2 = new String[]{"t", "a", "b"};
        SolutionUtils.print(arr);
        SolutionUtils.print(arr2);

        // copyOf
        int[] copy = Arrays.copyOf(arr, 5);
        SolutionUtils.print(copy);

        // copyOfRange
        String[] strCopy2 = Arrays.copyOfRange(arr2, 2, 3);
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
    }
}
