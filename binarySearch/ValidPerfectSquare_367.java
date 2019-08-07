package binarySearch;

/** E
 Given a positive integer num, write a function which returns True if num is a perfect square else False.
 Note: Do not use any built-in library function such as sqrt.

 Example 1:
 Input: 16
 Output: true

 Example 2:
 Input: 14
 Output: false
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
        if (num == 0 || num == 1) {
            return true;
        }
        int left = 1;
        int right = num/2;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (mid == num/mid) {
                return num - mid*mid == 0;
            }
            if (mid < num/mid) {
                left = mid+1;
                ans = mid;
            } else {
                right = mid-1;
            }
        }

        return num/ans == ans ? true : false;
    }
}
