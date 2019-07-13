package array.TwoSum_1;

import utils.SolutionUtils;

public class Main {

    public static void main(String[] args) {
        int[] input = new int[] {2, 7, 11, 15};
        int target = 9;

        Solution solution = new Solution();
        int[] result = solution.twoSum(input, target);
        int[] result2 = solution.twoSumLinearTime(input, target);

        SolutionUtils.printArray(SolutionUtils.toObjectArray(result2));
    }
}
