package binarySearch.easy;

/** E
 Given a positive integer num, write a function which returns True if num is a perfect square else False.
 Note: Do not use any built-in library function such as sqrt.

 Example 1:
 Input: 16
 Output: true

 Example 2:
 Input: 14
 Output: false

 Constraints:
    1 <= num <= 2^31 - 1

 */
public class ValidPerfectSquare_367 {

    public static void main(String[] args) {
        ValidPerfectSquare_367 s = new ValidPerfectSquare_367();
        System.out.println(s.isPerfectSquare(5)); // false
        System.out.println(s.isPerfectSquare(16)); // true
        System.out.println(s.isPerfectSquare(14)); // false
        System.out.println(s.isPerfectSquare(9)); // false
    }

    public boolean isPerfectSquare(int num) {
        if (num <= 1) {
            return true;
        }

        long left = 1, right = num/2; // since while multiplying mid*mid it can be overflow.
        while (left <= right) {
            long mid = left + (right - left)/2;
            if (mid*mid == num) {
                return true;
            }
            if (mid*mid < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // Newton's method
    public boolean isPerfectSquareNM(int num) {
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }

        return (x * x == num);
    }
}
