package array.medium;

import java.util.*;

/**M[marked]
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 *     0 <= i < j < nums.length
 *     |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 *
 * Example 1:
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Example 3:
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 *
 * Constraints:
 *     1 <= nums.length <= 104
 *     -107 <= nums[i] <= 107
 *     0 <= k <= 107
 */
public class KDiffPairsInArray_532 {

    public static void main(String[] args) {
        KDiffPairsInArray_532 s = new KDiffPairsInArray_532();
        System.out.println(s.findPairs(new int[]{3,1,4,1,5}, 2)); //2
        System.out.println(s.findPairs(new int[]{1,2,3,4,5}, 1)); //4
        System.out.println(s.findPairs(new int[]{1,3,1,5,4}, 0)); //1
    }

    // O(n^2) - time, O(n) - time
    public int findPairsBF(int[] nums, int k) {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    List<Integer> pair = Arrays.asList(nums[i], nums[j]);
                    Collections.sort(pair);
                    set.add(pair);
                }
            }
        }

        return set.size();
    }

    //O(n*log(n)) - time, O(1) - space
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 1;
        int result = 0;
        while (left < nums.length && right < nums.length) {
            if (left == right || nums[right] - nums[left] < k) {
                // List item 1 in the text
                right++;
            } else if (nums[right] - nums[left] > k) {
                // List item 2 in the text
                left++;
            } else {
                // List item 3 in the text
                left++;
                result++;
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }

        return result;
    }

    // O(n) - time, space
    public int findPairs2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num: map.keySet()) {
            if (k > 0 && map.containsKey(num + k)) {
                count++;
            } else if (k == 0 && map.get(num) > 1) { // in case (1,1,2,3), k = 0
                count++;
            }
        }

        return count;
    }
}
