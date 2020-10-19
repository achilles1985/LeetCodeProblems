package backtracking.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**M
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
 * these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 *     The number at the ith position is divisible by i.
 *     i is divisible by the number at the ith position.
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1, 2]:
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 *
 * Note:
 *     N is a positive integer and will not exceed 15.
 */
public class BeautifulArrangement_526 {

    public static void main(String[] args) {
        BeautifulArrangement_526 s = new BeautifulArrangement_526();
        System.out.println(s.countArrangementBF(3)); //3
        System.out.println(s.countArrangementBF(2)); //2

        System.out.println(s.countArrangement(3)); //3
        System.out.println(s.countArrangement(2)); //2
    }

    // O(n!) - time (to generate all permutations), O(n) - space (recursion stack)
    public int countArrangementBF(int N) {
        int count = 0;
        List<List<Integer>> permutations = generateAll(N);
        for (List<Integer> permutation: permutations) {
            if (isBeautifullyArranged(permutation)) {
                count++;
            }
        }
        return count;
    }

    private List<List<Integer>> generateAll(int N) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[N];
        permute(N, new ArrayList<>(), result, visited);

        return result;
    }

    private void permute(int N, List<Integer> temp, List<List<Integer>> result, boolean[] visited) {
        if (temp.size() == N) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i-1]) {
                continue;
            }
            temp.add(i);
            visited[i-1] = true;
            permute(N, temp, result, visited);
            temp.remove(temp.size()-1);
            visited[i-1] = false;
        }
    }

    // O(k) - time, k - number of valid permutations
    public int countArrangement(int N) {
        AtomicInteger count = new AtomicInteger(0);
        boolean[] used = new boolean[N+1];
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }
        countArrangementHelper(nums, new ArrayList<>(), count, used);
        return count.get();
    }

    // O(n!) - time, O(n) - space (Brute Forth)
    public int countArrangementBF2(int N) {
        AtomicInteger count = new AtomicInteger(0);
        boolean[] used = new boolean[N+1];
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }
        countArrangementHelperBF2(nums, new ArrayList<>(), count, used);
        return count.get();
    }

    private void countArrangementHelper(int[] nums, ArrayList<Integer> list, AtomicInteger count, boolean[] used) {
        if (list.size() == nums.length) {
            if (isBeautifullyArranged(list)) {
                count.incrementAndGet();
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            // do not add element to the list if it fails conditions
            if (list.size() > 1 && nums[i]%(list.size()) != 0 && (list.size())%nums[i] != 0) {
                list.remove(list.size()-1);
                continue;
            }
            used[i] = true;
            countArrangementHelper(nums, list, count, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }

    private void countArrangementHelperBF2(int[] nums, ArrayList<Integer> list, AtomicInteger count, boolean[] used) {
        if (list.size() == nums.length) {
            if (isBeautifullyArranged(list)) {
                count.incrementAndGet();
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            countArrangementHelperBF2(nums, list, count, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }

    private boolean isBeautifullyArranged(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)%(i+1) != 0 && (i+1)%list.get(i) != 0) {
                return false;
            }
        }
        return true;
    }
}
