package dynamic.medium;

/**
 * Checks if we can compose the given sum from array's numbers
 */
public class CanSum {

    public static void main(String[] args) {
        CanSum s = new CanSum();
        System.out.println(s.canSum(new int[]{2,3,4}, 6)); //true
        System.out.println(s.canSum(new int[]{2,3,4}, 7)); //false
    }

    public boolean canSum(int[] nums, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0) {
            return false;
        }
        for (int num: nums) {
            if (canSum(nums, sum - num)) {
                return true;
            }
        }
        return false;
    }
}
