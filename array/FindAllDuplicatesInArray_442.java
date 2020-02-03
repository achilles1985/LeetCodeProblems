package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**M
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [2,3]
 */
public class FindAllDuplicatesInArray_442 {

    public static void main(String[] args) {
        FindAllDuplicatesInArray_442 s = new FindAllDuplicatesInArray_442();
        System.out.println(s.findDuplicates2(new int[]{4,3,2,7,8,2,3,1})); // [2,3]
    }

    // O(n) - time, space
    public List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num: nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: numToCount.entrySet()) {
            if (entry.getValue() == 2) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // O(n) - time, O(1) - space
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] < 0) {
                result.add(Math.abs(val+1));
            } else {
                nums[val] = -nums[val];
            }
        }
        return result;
    }
}
