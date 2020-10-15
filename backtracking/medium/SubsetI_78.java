package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/** M [MARKED]
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 Note: The solution set must not contain duplicate subsets.

 Example:
 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class SubsetI_78 {

    public static void main(String[] args) {
        SubsetI_78 s = new SubsetI_78();
        System.out.println(s.subsets3(new int[] {1,2,3}));
        System.out.println(s.subsets(new int[] {1,2,3,4,5}));
        System.out.println(s.subsets(new int[] {4,4,4,1,4}));
    }

    // O(2^n) - time (since we either add an item to the set or not), O(2^n) - space (total number of sets generated, does not include recursion stack)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, list, res);

        return res;
    }

    // O(n*2^n) - time, O(2^n) - space
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> resultSet = new ArrayList<>();
        resultSet.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> subSet = new ArrayList<>();
            for (List set: resultSet) {
                List<Integer> copySet = new ArrayList<>(set);
                copySet.add(nums[i]);
                subSet.add(copySet);
            }
            for (List<Integer> set: subSet) {
                resultSet.add(set);
            }
        }
        return resultSet;
    }

    // O(n*2^n) - time, O(2^n) - space
    // Bit manipulation
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;
        for (int i = 1 << n; i < 1 << (n + 1); i++) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);
            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            output.add(curr);
        }
        return output;
    }

    private void dfs(int[] nums, int start, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i+1, list, result);
            list.remove(list.size()-1);
        }
    }
}
