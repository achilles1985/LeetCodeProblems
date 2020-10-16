package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations_77 {

    public static void main(String[] args) {
        Combinations_77 s = new Combinations_77();
        System.out.println(s.combine2(4, 2));
    }

    // O(k*n!/(n-k)!k!) - time, O(n!/(n-k)!k!) - space to keep all combinations, k - append an item of size k to the output list.
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k > n || k == 0) {
            return result;
        }
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i < 1 << n; i <<= 1) {
            for (int j = 1 << i; j < 1 << n; j <<= 1) {
                String mask = Integer.toBinaryString(i | j);
                List<Integer> list = new ArrayList<>();
                for (int l = n; l >= 1; i--) {
                    if (mask.charAt(l-1) == '1') {
                        list.add(l);
                    }
                }
                lists.add(list);
            }
        }
        return lists;
    }

    private void backtrack(int c, int k, int n, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = c; i <= n; i++) {
            curr.add(i);
            backtrack(i + 1, k, n, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}
