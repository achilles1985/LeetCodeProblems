package backtracking;

import java.util.ArrayList;
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
        System.out.println(s.countArrangement2(3)); //3
        System.out.println(s.countArrangement2(2)); //2
    }

    // O(k) - time, k - number of valid permutations
    public int countArrangement2(int N) {
        AtomicInteger count = new AtomicInteger(0);
        boolean[] used = new boolean[N+1];
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }
        countArrangementHelper2(nums, new ArrayList<>(), count, used);
        return count.get();
    }

    // O(n!) - time, O(n) - space (Brute Forth)
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

    private void countArrangementHelper2(int[] nums, ArrayList<Integer> list, AtomicInteger count, boolean[] used) {
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
            countArrangementHelper2(nums, list, count, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
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
            used[i] = true;
            countArrangementHelper(nums, list, count, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }

    private boolean isBeautifullyArranged(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)%(i+1) != 0 && (i+1)%list.get(i) != 0) {
                return false;
            }
        }
        return true;
    }
}
