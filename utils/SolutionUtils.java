package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SolutionUtils {

    private SolutionUtils() {
    }

    public static Integer[] toObjectArray(int[] array) {
        return Arrays.stream(array)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.print("]");
        }
        System.out.println();
    }

    public static void print(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
        System.out.println();
    }

    public static <T> void print(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print("," + arr[i]);
        }
        System.out.println();
    }

    public static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static <T> void printArray(T[] array) {
        printArray(array, array.length-1);
        System.out.println();
    }

    private static <T> void printArray(T[] array, int index) {
        if (index < 0) {
            return;
        }

        printArray(array, index-1);
        System.out.print(array[index] + ", ");
    }
}
