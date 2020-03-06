package stack;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/** M
 Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.

 Example 1:
 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

 Example 2:
 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

 Example 3:
 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
/*
 Corner cases:
    1. k == 0 or k >= str.length
    2. If all digits in ascending order and k != 0, remove last k digits.
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits s = new RemoveKDigits();
        String s1 = "000001230012300".replaceFirst("^0*", "");

        System.out.println(s.removeKdigitsBF2("112", 1)); // 11
        System.out.println(s.removeKdigitsBF2("100", 1)); // 0
        System.out.println(s.removeKdigitsBF2("10200", 1)); // 200
        System.out.println(s.removeKdigitsBF2("1107", 1)); //107
        System.out.println(s.removeKdigitsBF2("1432219", 3)); // 1219
        System.out.println(s.removeKdigitsBF2("10", 2)); //0
    }

    // O(n) - time, space
    public String removeKdigitsBF2(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (k >= num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && num.charAt(i) < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        if (stack.isEmpty()) {
            return "0";
        }
        while (!stack.isEmpty() && k-- > 0) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Character> it = stack.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        String result = sb.toString().replaceFirst("^0+", "");
        return result.length() == 0 ? "0" : result;
    }

    // O(n^n) - time, O(n) - space
    // If input is longer then 10, NumberFormatException
    public String removeKdigitsBF(String num, int k) {
        SortedSet<Integer> set = new TreeSet<>();
        removeKdigitsUtils(num, num.length()-k, 0, new StringBuilder(), set);

        return String.valueOf(set.first());
    }

    // O(n*k) - time, O(k) - space
    public String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && c < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
            if (k == 0) {
                break;
            }
        }

        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        res = removeTrailingZeros(res);
        res = k == 0 ? res + num.substring(i + 1) : res.substring(0, res.length() - k);
        if (res.length() == 0) {
            return "0";
        }

        return res;
    }

    private String removeTrailingZeros(String str) {
        if (str.startsWith("0")) {
            return str.replaceFirst("^0*", "");
        }

        return str;
    }

    private void removeKdigitsUtils(String num, int k, int start, StringBuilder sb, Set<Integer> set) {
        if (sb.length() == k) {
            String str = sb.toString();
            String formatted = str.startsWith("0") ? str.substring(1) : str;
            set.add(formatted.isEmpty() ? 0 : Integer.parseInt(formatted));
            return;
        }

        for (int i = start; i < num.length(); i++) {
            sb.append(num.charAt(i));
            removeKdigitsUtils(num, k, i+1, sb, set);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
