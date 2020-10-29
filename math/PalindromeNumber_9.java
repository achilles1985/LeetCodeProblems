package math;

import java.util.concurrent.atomic.AtomicInteger;

/**E
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Follow up: Could you solve it without converting the integer to a string?
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Example 4:
 * Input: x = -101
 * Output: false
 */
public class PalindromeNumber_9 {

    public static void main(String[] args) {
        PalindromeNumber_9 s = new PalindromeNumber_9();
        System.out.println(s.isPalindrome(121)); //true
        System.out.println(s.isPalindrome(132)); //false
        System.out.println(s.isPalindrome(-132)); //false
    }

    // O(log10(n)) - time (We divided the input by 10 for every iteration), O(1) - time
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int r = 0;
        while (x > 0) {
            r = r*10 + x%10;
            x /= 10;
        }
        return r == origin;
    }

}
