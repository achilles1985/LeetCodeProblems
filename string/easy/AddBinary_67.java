package string.easy;

/** E
 Given two binary strings, return their sum (also a binary string).
 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:
 Input: a = "11", b = "1"
 Output: "100"

 Example 2:
 Input: a = "1010", b = "1011"
 Output: "10101"
 */
public class AddBinary_67 {

    public static void main(String[] args) {
        AddBinary_67 s = new AddBinary_67();
        System.out.println(s.addBinary("11", "1")); // 100
        System.out.println(s.addBinary("1010", "1011")); // 10101
    }

    // O(n) - time, O(1) - space
    public String addBinary(String a, String b) {
        int l1 = a.length()-1;
        int l2 = b.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (l1 >= 0 || l2 >= 0 || carry > 0) {
            int n1 = l1 >= 0 ? a.charAt(l1)-'0' : 0;
            int n2 = l2 >= 0 ? b.charAt(l2)-'0' : 0;
            int sum = n1 + n2 + carry;
            sb.append(sum%2);
            carry = sum/2;
            l1--;
            l2--;
        }

        return sb.reverse().toString();
    }
}
