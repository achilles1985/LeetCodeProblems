package string;

import java.util.Arrays;
import java.util.stream.Collectors;

/** M
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

 Example 1:
 Input: num1 = "2", num2 = "3"
 Output: "6"
 Example 2:

 Input: num1 = "123", num2 = "456"
 Output: "56088"
 Note:
 The length of both num1 and num2 is < 110.
 Both num1 and num2 contain only digits 0-9.
 Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings_43 {

    public static void main(String[] args) {
        MultiplyStrings_43 s = new MultiplyStrings_43();
        System.out.println(s.multiply("2", "3")); // 6
        System.out.println(s.multiply("123", "456")); // 56088
        System.out.println(s.multiply("999", "999")); // 56088
    }

    // O(m*n) - time, O(m+n) - space for the result
    public String multiply(String num1, String num2) {
        int[] temp = new int[num1.length() + num2.length()];
        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                temp[i+j+1] = (temp[i+j+1]) + (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                temp[i+j] = temp[i+j] + temp[i+j+1]/10;
                temp[i+j+1] = temp[i+j+1]%10;
            }
        }

        int nonZeroIndex = 0;
        while (nonZeroIndex < temp.length && temp[nonZeroIndex] == 0) {
            nonZeroIndex++;
        }
        int[] res = Arrays.copyOfRange(temp, nonZeroIndex, temp.length);
        if (res.length == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i: res) {
            sb.append(i);
        }

        return sb.toString();
    }
}
