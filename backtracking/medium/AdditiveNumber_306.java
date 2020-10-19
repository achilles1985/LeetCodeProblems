package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 * Additive number is a string whose digits can form additive sequence.
 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Example 1:
 Input: "112358"
 Output: true
 Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

 Example 2:
 Input: "199100199"
 Output: true
 Explanation: The additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199

 Follow up:
 How would you handle overflow for very large input integers?
 */
public class AdditiveNumber_306 {

    public static void main(String[] args) {
        AdditiveNumber_306 s= new AdditiveNumber_306();
        System.out.println(s.isAdditiveNumber("231458")); // false
        System.out.println(s.isAdditiveNumber("111")); // false
        System.out.println(s.isAdditiveNumber("123446")); // true
        System.out.println(s.isAdditiveNumber("112358")); // true
        System.out.println(s.isAdditiveNumber("199100199")); // true
    }

    // Similar to SplitArrayToFibonacciSequence_842
    // O(exponential) - time, O(n) - space
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }
        return isAdditiveNumber(num, new ArrayList<>(), 0);
    }

    private boolean isAdditiveNumber(String str, List<Integer> list, int start) {
        if (start == str.length()) {
            return list.size() >= 3;
        }
        int num = 0;
        for (int i = start; i < str.length(); i++) {
            num = 10*num + (str.charAt(i) - '0');
            if (isValidNum(str, num, list, start, i)) {
                list.add(num);
                if (isAdditiveNumber(str, list, i+1)) {
                    return true;
                }
                list.remove(list.size()-1);
            }
        }
        return false;
    }

    private boolean isValidNum(String str, long num, List<Integer> list, int start, int end) {
        if (start < end && str.charAt(start) == '0') {
            return false;
        }
        if (list.size() < 2) {
            return true;
        }
        int last = list.size()-1;

        return list.get(last) + list.get(last-1) == num;
    }

}
