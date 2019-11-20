package backtracking;

/**M
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 *
 * Example:
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 *              excluding 11,22,33,44,55,66,77,88,99
 */
public class CountNumbersWithUniqueDigits_357 {

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits_357 s = new CountNumbersWithUniqueDigits_357();
        System.out.println(s.countNumbersWithUniqueDigits2(0)); //2
        System.out.println(s.countNumbersWithUniqueDigits2(1)); //10
        System.out.println(s.countNumbersWithUniqueDigits2(2)); //91
        System.out.println(s.countNumbersWithUniqueDigits2(8)); //739
    }

    //https://leetcode.com/problems/count-numbers-with-unique-digits/discuss/83094/Simple-JAVA-Solution-with-explanation.
    public int countNumbersWithUniqueDigits2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int product = 9;
            for (int j = 0; j < i - 1; j++) {
                product *= (9 - j);
            }
            dp[i] = dp[i - 1] + product;
        }
        return dp[n];
    }

    // O(10^n) - time, O(1) - space (Brute Force)
    public int countNumbersWithUniqueDigits(int n) {
        int count = 1;
        for (int i = 1; i < Math.pow(10, n); i++) {
            if (allUnique(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean allUnique(int num) {
        int[] unique = new int[10];
        while (num > 0) {
            int digit = num%10;
            if (unique[digit] != 0) {
                return false;
            }
            unique[digit]++;
            num /= 10;
        }
        return true;
    }
}
