package slidingWindow.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**H?
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 *     For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Constraints:
 *     1 <= nums.length <= 2 * 104
 *     1 <= nums[i], k <= nums.length
 */
// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235235/C%2B%2BJava-with-picture-prefixed-sliding-window
// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
public class SubarraysWithKDifferentIntegers_992 {

    public static void main(String[] args) {
        SubarraysWithKDifferentIntegers_992 s = new SubarraysWithKDifferentIntegers_992();
        System.out.println(s.subarraysWithKDistinct(new int[]{1,2,1,2,3},2)); //7
        System.out.println(s.subarraysWithKDistinct(new int[]{2,1,2,1,2},2)); //10?
    }

    // O(n^3) - time, O(n) - space
    public int subarraysWithKDistinctBF(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int count = 0;
        for (int s = k; s < nums.length; s++) {
            for (int i = 0; i <= nums.length-s; i++) {
                Set<Integer> unique = new HashSet<>();
                for (int j = i; j < i+s; j++) {
                    unique.add(nums[j]);
                }
                if (unique.size() == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // O(n) - time, space
    // https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums,k) - atMost(nums,k-1);
    }

    private int atMost(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < nums.length; right++) {
            int c = nums[right];
            map.put(c, map.getOrDefault(c,0) + 1);
            while (map.size() > k) {
                int cc = nums[left++];
                map.put(cc, map.get(cc) - 1);
                if (map.get(cc) == 0) {
                    map.remove(cc);
                }
            }
            count += (right-left);
        }

        return count;
    }
}
