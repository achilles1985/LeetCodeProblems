package sorting.medium;

import utils.SolutionUtils;

import java.util.ArrayList;
import java.util.List;

/** M
 * Given an array of integers nums, sort the array in ascending order.
 *
 * Example 1:
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 * Constraints:
 *     1 <= nums.length <= 5 * 104
 *     -5 * 104 <= nums[i] <= 5 * 104
 */
public class SortArray_912 {

    public static void main(String[] args) {
        SortArray_912 s = new SortArray_912();
        SolutionUtils.print(s.sortArray(new int[]{5,1,1,2,0,0})); //[0,0,1,1,2,5]
    }

    // O(n) - time, O(1) - space
    public int[] sortArray(int[] nums) {
        int[] buckets = new int[100001];
        for (int num: nums) {
            buckets[num + 50000]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 100000; i++) {
            if (buckets[i] == 0) {
                continue;
            }
            int count = buckets[i];
            for (int j = count; j > 0; j--) {
                list.add(i - 50000);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
