package string;

/** E
 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:
 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class AddStrings_415 {

    public static void main(String[] args) {
        AddStrings_415 s = new AddStrings_415();

        System.out.println(s.addStrings("123", "456")); // 579
        System.out.println(s.addStrings("123", "0")); // 123
        System.out.println(s.addStrings("12", "456")); // 468
        System.out.println(s.addStrings("1", "9")); // 10
        System.out.println(s.addStrings("10", "9")); // 19
        System.out.println(s.addStrings("", "9")); // 9
        System.out.println(s.addStrings("9", "99")); // 19
        System.out.println(s.addStrings("999", "999")); // 19
        System.out.println(s.addStrings("237", "284")); // 19
    }

    // O(n) - time, O(1) - space
    public String addStrings(String num1, String num2) {
        int l1 = num1.length()-1;
        int l2 = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (l1 >= 0 || l2 >= 0 || carry > 0) {
            int n1 = l1 >= 0 ? num1.charAt(l1)-'0' : 0;
            int n2 = l2 >= 0 ? num2.charAt(l2)-'0' : 0;
            int sum = n1 + n2 + carry;
            sb.append(sum%10);
            carry = sum/10;
            l1--;
            l2--;
        }

        return sb.reverse().toString();
    }
}
