package design;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose n people are sitting in a circular table with names A, B, C, D,….
 * Given a name, we need to print all n people (in order) starting from given name.
 */
public class CircularArray {

    public static void main(String[] args) {
        CircularArray s = new CircularArray();
        System.out.println(s.allNeibours2(new int[]{1,2,3,4,5}, 4)); //[3, 4, 5, 1, 2]
    }

    public List<Integer> allNeibours(int[] arr, int start) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i < arr.length + start; i++) {
            result.add(arr[i%arr.length]);
        }
        return result;
    }

    public List<Integer> allNeibours2(int[] arr, int start) {
        List<Integer> result = new ArrayList<>();
        int count = 1;
        while (count <= arr.length) {
            result.add(arr[start%arr.length]);
            start++;
            count++;
        }
        return result;
    }
}
