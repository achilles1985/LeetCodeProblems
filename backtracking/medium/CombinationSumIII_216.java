package backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** M [?]
  Find all possible combinations of k numbers that add up to a number n,
  given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

  Example 1:
  Input: k = 3, n = 7
  Output:
  [[1,2,4]]

  Example 2:
  Input: k = 3, n = 9
  Output:
  [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII_216 {

    public static void main(String[] args) {
        CombinationSumIII_216 s = new CombinationSumIII_216();
        System.out.println(s.combinationSum3_II(3, 7)); //[1,2,4]
        System.out.println(s.combinationSum3_II(3, 9)); //[[1,2,6], [1,3,5], [2,3,4]]
    }

    // O(9!/(9-k)!*k!) - time, O(k*9) - space (recursion never go deeper then k)
    public List<List<Integer>> combinationSum3_I(int k, int n) {
        Set<List<Integer>> result = new HashSet<>();
        helper(k, n, new ArrayList<>(), 0, 1, result);

        return new ArrayList<>(result);
    }

    private void helper(int k, int target, List<Integer> list, int sum, int start, Set<List<Integer>> result) {
        if (list.size() > k) {
            return;
        }
        if (sum == target && list.size() == k) {
            result.add(new ArrayList<>(list)); //9
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (sum + i > target) {
                continue;
            }
            list.add(i);
            helper(k, target, list, sum+i, i+1, result);
            list.remove(list.size()-1);
        }
    }

    // O(9!/(9-k)!*k!) - time, O(k*9) -space
    public List<List<Integer>> combinationSum3_II(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3Utils(1, n, k, res, new ArrayList<>());

        return res;
    }

    private void combinationSum3Utils(int start, int target, int length, List<List<Integer>> res, List<Integer> list) {
        if (target == 0 && length == 0) {
            res.add(new ArrayList<>(list));
        }

        for (int i = start; i < 10; i++) {
            if (target < 0) {
                break;
            }
            list.add(i);
            int newTarget = target - i;
            int newLength = length - 1;
            combinationSum3Utils(i+1, newTarget, newLength, res, list);
            list.remove(list.size()-1);
        }
    }
}
