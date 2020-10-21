package array.easy;

import java.util.HashSet;
import java.util.Set;

/** E
 Write an algorithm to determine if a number is "happy".
 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum o
 f the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 Those numbers for which this process ends in 1 are happy numbers.

 Example:
 Input: 19
 Output: true
 Explanation:
 1^2 + 9^2 = 82
 8^2 + 2^2 = 68
 6^2 + 8^2 = 100
 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber_202 {

    public static void main(String[] args) {
        HappyNumber_202 s = new HappyNumber_202();
        System.out.println(s.isHappy(19)); // true
        System.out.println(s.isHappy(12)); // true
    }

    // O(k*d) - time, where k - times of while loop execution, d - number of digits in a number; O(k) - space
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += (n%10)*(n%10);
                n /= 10;
            }
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
        }
        return true;
    }
}
