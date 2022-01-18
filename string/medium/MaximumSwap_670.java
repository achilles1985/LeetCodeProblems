package string.medium;

/** M
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Constraints:
 *     0 <= num <= 108
 */
/*
Use buckets to record the last position of digit 0 ~ 9 in this num.
Loop through the num array from left to right. For each position, we check whether there exists a larger digit in this num (start from 9 to current digit).
We also need to make sure the position of this larger digit is behind the current one.
If we find it, simply swap these two digits and return the result.
 */
public class MaximumSwap_670 {

    public static void main(String[] args) {
        MaximumSwap_670 s = new MaximumSwap_670();
        System.out.println(s.maximumSwap(98368)); //98863
        System.out.println(s.maximumSwap(1993)); //9913
        System.out.println(s.maximumSwap(123)); //321
        System.out.println(s.maximumSwap(321)); //321
        System.out.println(s.maximumSwap(3111)); //3111
        System.out.println(s.maximumSwap(111)); //111
        System.out.println(s.maximumSwap(21)); //21
    }

    // O(n) - time
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;

                    return Integer.parseInt(new String(digits));
                }
            }
        }

        return num;
    }
}
