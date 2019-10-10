package utils;

import java.util.Arrays;

public class ArraysTest {

    public static void main(String[] args) {
        int[] arr = new int[]{12,5,17};
        String[] arr2 = new String[]{"t", "a", "b"};
        SolutionUtils.print(arr);
        SolutionUtils.print(arr2);

        // copyOf
        int[] copy = Arrays.copyOf(arr, 5);
        String[] strCopy1 = Arrays.copyOf(arr2, 5);
        SolutionUtils.print(copy);
        SolutionUtils.print(strCopy1);

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
    }
}
