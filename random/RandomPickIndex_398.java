package random;

import java.util.Random;

/** M
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 *
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */

// https://leetcode.com/problems/random-pick-index/discuss/786282/with-clear-explanation%3A-Reservoir-Sampling-Solution
/*
 * Lets take a simple problem: from a stream of integers, at each step, choose a random number from numbers given till then.
 *
 * eg:
 * 1) Position = 0, Number = 1, count = 1 ==> we have no choice other than 1 ==> result = 1
 *
 * 2) Position = 1, Number = 2, count = 2 ==> Now we have to choose one number from [1,2] with equal probabality
 * So with the help of random function, generate a random number between 0 and 1.
 * - If it gives 0 ==> choose "1", else if it gives 1 ==> choose "2".
 * - In other words, this can be stated as: "If the random number generated is 1, choose the current number else if the random number generated is 0, choose the previous result i.e "1")
 *
 * 3) Position = 2, Number = 3, count = 3 ==> we have to choose one number from [1,2,3] with equal probability.
 * Since count = 3, generate a random number between 0 and 2.
 *     If it gives 0 ==> cho0se "1"
 *.    If it gives 1 ==> choose "2"
 *     If it gives 2 ==> choose "3" (the current number).
 * So we have to give 1/3 probability for each number.
 * - The same can me stated as: for the current number we have to give 1/3 probability and for the previous result, we have to give 2/3 probability,
 * since that holds two numbers result.
 * - In other words: "If the random number generated number is 2, choose the current number, else if the random number generated is either 0 or 1,
 * choose the previous result" ==> random(count) == 2 ==> result = currentNum, else don't change the result.
 *
 * So this can be generalized as:
 * 1. Keep counting the target number occurances
 * 2. Generate a random number within current count range:
 *    - if the generated number is any particular number(this can be choosen any number because that will have 1/count probability), choose the current number and assign it to result
 *    - else choose the previous result. This way previous result gets (count-1)/count probability
 */
public class RandomPickIndex_398 {
    private int[] nums;

    private Random random = new Random();

    public static void main(String[] args) {
        RandomPickIndex_398 s1 = new RandomPickIndex_398(new int[]{1,2});
        System.out.println(s1.pick(2));

        RandomPickIndex_398 s2 = new RandomPickIndex_398(new int[]{1,2,5,3,4,3,3,7});
        System.out.println(s2.pick(3));
    }

    public RandomPickIndex_398(int[] nums) {
        this.nums = nums;
    }

    // O(n) - time
    public int pick(int target) {
        int count = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (random.nextInt(++count) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }

}
