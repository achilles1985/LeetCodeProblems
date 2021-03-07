package array.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**H
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

/*
Questions to ask:
    1. Can negative, duplicate numbers might be in the array?
 */
public class LongestConsecutiveSequence_128 {

    public static void main(String[] args) {
        LongestConsecutiveSequence_128 s = new LongestConsecutiveSequence_128();
        System.out.println(s.longestConsecutive3(new int[]{1,2,0,1})); // 3
        System.out.println(s.longestConsecutive3(new int[]{0,3,7,2,5,8,4,6,0,1})); // 9
        System.out.println(s.longestConsecutive3(new int[]{100,4,200,1,3,2})); // 4 [1,2,3,4]

        System.out.println(s.longestConsecutive4(new int[]{2,1,3,5,10,11,12})); // 3
        System.out.println(s.longestConsecutive4(new int[]{9,1,4,7,3,-1,0,5,8,-1,6})); // 7
        System.out.println(s.longestConsecutive4(new int[]{9,1,4,7,3,-1,0,5,8,-1,6})); // 7
        System.out.println(s.longestConsecutive4(new int[]{100,4,101,102,200,1,3,2})); // 4 [1,2,3,4]
        System.out.println(s.longestConsecutive4(new int[]{1,2,0,1})); // 3
    }

    // O(n^2) - time, O(n) - space
    public int longestConsecutiveBF(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int max = 1;
        for (int num: nums) {
            int cur = 1;
            int val = num;
            while (set.contains(val+1)) {
                cur++;
                val++;
            }
            max = Math.max(max, cur);
        }
        return max;
    }

    // O(n*log(n)) - time, O(1) - space
    public int longestConsecutive3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }

    // Not sure if runs in O(n) time
    // O(n) - time, space
    public int longestConsecutive4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int localMax = 1;
            int val = nums[i];
            if (!seen.contains(val)) {
                while (set.contains(val + 1)) { // build sequences from numbers that are not already part of a longer sequence
                    seen.add(val);
                    localMax++;
                    val++;
                }
            }
            max = Math.max(localMax, max);
        }
        return max;
    }

    // O(n) - time, space
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int localMax = 1;
            int val = nums[i];
            if (!set.contains(val-1)) {
                while (set.contains(val + 1)) { // build sequences from numbers that are not already part of a longer sequence
                    localMax++;
                    val++;
                }
            }
            max = Math.max(localMax, max);
        }
        return max;
    }

}
