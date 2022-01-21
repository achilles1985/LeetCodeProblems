package binarySearch.medium;

import java.util.HashSet;
import java.util.Set;

/** M
 Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

 Example 1:
 Input: A = [4,7,9,10], K = 1
 Output: 5
 Explanation:

 The first missing number is 5.

 Example 2:
 Input: A = [4,7,9,10], K = 3
 Output: 8
 Explanation:
 The missing numbers are [5,6,8,...], hence the third missing number is 8.

 Example 3:
 Input: A = [1,2,4], K = 3
 Output: 6
 Explanation:
 The missing numbers are [3,5,6,7,...], hence the third missing number is 6.

 Note:
 1 <= A.length <= 50000
 1 <= A[i] <= 1e7
 1 <= K <= 1e8
 */
/*
    is k within the range? if no missing element? duplicates?
 */
public class MissingElementInSortedArray_1060 {

    public static void main(String[] args) {
        MissingElementInSortedArray_1060 s = new MissingElementInSortedArray_1060();
        System.out.println(s.missingElement(new int[] {1,3,6,10,12,16,22,25}, 8)); //13
        System.out.println(s.missingElement(new int[] {1,2,3,4,7,8}, 2)); //6
        System.out.println(s.missingElement(new int[] {1,2,3,4,5}, 3)); //6

        System.out.println(s.missingElement(new int[] {1,2,4}, 3)); //6
        System.out.println(s.missingElement(new int[] {4,7,9,10}, 3)); //8
        System.out.println(s.missingElement(new int[] {4,7,9,10}, 1)); //5
        System.out.println(s.missingElement(new int[] {1,2,4}, 3)); //6
    }

    // O(n+k) - time, O(1) - space
    public int missingElementBF2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i-1];
            if (diff > 1) {
                int num = nums[i-1];
                while (num < nums[i]-1) {
                    num++;
                    count++;
                    if (count == k) {
                        return num;
                    }
                }
            }
        }
        if (count < k) {
            return nums[nums.length-1] + k - count;
        }
        return 0;
    }

    // O(n+k) - time, O(n) - space
    public int missingElementBF(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int min = nums[0];
        int max = nums[nums.length-1];
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                k--;
            }
            if (k == 0) {
                return i;
            }
        }
        return max + k;
    }

    // O(log(N)) - time, O(1) - space
    public int missingElement(int[] nums, int k) {
        if (nums.length < k) {
            return nums[nums.length - 1] + k - missing(nums, nums.length - 1);
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (missing(nums, mid) < k) { // how many elements missed between nums[mid] and nums[0]
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left - 1] + k - missing(nums, left - 1);
    }

    private int missing(int[] nums, int idx) {
        return nums[idx] - nums[0] - idx;
    }

}
