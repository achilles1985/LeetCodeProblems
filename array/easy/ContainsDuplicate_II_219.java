package array.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** E[marked]
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     -109 <= nums[i] <= 109
 *     0 <= k <= 105
 */
public class ContainsDuplicate_II_219 {

    public static void main(String[] args) {
        ContainsDuplicate_II_219 s = new ContainsDuplicate_II_219();
        System.out.println(s.containsNearbyDuplicate(new int[]{1,2,3,1}, 3)); //true;
        System.out.println(s.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2)); //false;
    }

    //O(n*k) - time, O(1) - space
    public boolean containsNearbyDuplicateBF(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(i-k, 0); j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //O(n) - time, O(n) - space
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    //O(n) - time, O(k) - space
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
