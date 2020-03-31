package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
  https://leetcode.com/problems/combination-sum-iii/
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
        System.out.println(s.combinationSum3(3, 7)); //[1,2,4]
        System.out.println(s.combinationSum3(3, 9)); //[[1,2,6], [1,3,5], [2,3,4]]
    }

    // O(n^10) - time
    public List<List<Integer>> combinationSum3(int k, int n) {
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
