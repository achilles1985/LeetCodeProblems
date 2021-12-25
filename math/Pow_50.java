package math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**M [marked]
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
        Map<Integer, List<Integer>> map = new HashMap<>();
        final Collection<List<Integer>> values = map.values();
        new ArrayList<>(values);

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
    // 2^10 = 2^5*2^5 = (2*2)^5 = 4^5 = 4*4^2*4^2 = 4*16^2 = 4*16*16 = 1024 each time we decrese n by halve with leads to log(n)
    public double myPow(double x, int n) {
        if(n < 0) {
            return 1.0/pow(x, n);
        }
        return pow(x, n);
    }

    private double pow(double x, int n){
        if(n == 0) return 1;
        if(n == 1) return x;
        if(n%2 == 0) {
            return pow(x * x, n/2);
        }
        return x * pow(x * x, n/2);
    }
}
