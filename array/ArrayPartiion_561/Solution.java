package array.ArrayPartiion_561;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 Example 1:

 Input: [1,4,3,2]

 Output: 4
 Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

 Note:
 n is a positive integer, which is in the range of [1, 10000].
 All the integers in the array will be in the range of [-10000, 10000].

 */
public class Solution {

    public int arrayPairSum(int[] arr) {
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i+=2) {
            sum += arr[i];
        }

        return sum;
    }

    /*
    Find min in each pair and put it in the list. Then sort the list and sum last n element to fine the solution.
     */
    public int arrayPairSumBruteForth(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = arr[j];
                }
            }
            list.add(min);
        }

        Collections.sort(list);
        int i = arr.length/2;
        int result = 0;
        while (i > 0) {
            result += list.get(i);
            i--;
        }

        return result;
    }
}
