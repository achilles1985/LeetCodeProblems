package array.FindAllNumbersDisappearedInArray_448;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [5,6]
 */
public class Solution {

    // O(n) - time, O(n) - space
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] arr = new int[8];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]-1] = nums[i];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                result.add(i+1);
            }
        }

        return result;
    }

    // O(n) - time, O(1) - space
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
          if (nums[i-1] != i) {
              swap(i-1, nums[i-1]-1, nums);
          }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }

    private void swap(int from, int to, int[] arr) {
        int fromValue = arr[from];
        int toValue = arr[to];
        if (fromValue == toValue) {
            arr[from] = 0;
        } else {
            arr[from] = toValue;
            arr[to] = fromValue;
        }
    }

    // O(n) - time, O(1) - space
    public static List<Integer> findDisappearedNumbers3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int n = Math.abs(nums[i]);
            nums[n - 1] = -Math.abs(nums[n - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

}
