package math;

/**M
 * Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Constraints:
 *     -100.0 < x < 100.0
 *     -231 <= n <= 231-1
 *     -104 <= xn <= 104
 */
public class Pow_50 {

    // O(n) - time, O(1) - space
    public double myPowBF(double x, int n) {
        double result = 1.0;
        for (int i = 1; i <= Math.abs(n); i++) {
            result *= x;
        }
        if (n < 0) {
            return 1/result;
        }
        return result;
    }

    // O(log(n)) - time, space
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
