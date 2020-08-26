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
public class MissingElementInSortedArray_1060 {

    public static void main(String[] args) {
        MissingElementInSortedArray_1060 s = new MissingElementInSortedArray_1060();
        System.out.println(s.missingElement(new int[] {1,2,4}, 3)); //6
        System.out.println(s.missingElement(new int[] {4,7,9,10}, 1)); //5
        System.out.println(s.missingElement(new int[] {4,7,9,10}, 3)); //8
        System.out.println(s.missingElement(new int[] {1,2,4}, 3)); //6
    }

    // O(n+k) - time, O(n) - space
    public int missingElementBF(int[] nums, int k) {
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < nums.length; i++) { //N
            unique.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) { // N
            int count = 1;
            while (!unique.contains(nums[i] + count)) { //K
                k--;
                if (k == 0) {
                    return nums[i] + count;
                }
                count++;
            }
        }

        return 0;
    }

    // O(log(N)) - time, O(1) - space
    public int missingElement(int[] nums, int k) {
        if (nums.length < k) {
            return nums[nums.length - 1] + k - missing(nums, nums.length - 1);
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (missing(nums, mid) < k) {
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
