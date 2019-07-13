package string.ValidPalindromeII_680;

/** Easy
 *  Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True

 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.

 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class Solution {

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int counter = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (s.charAt(i+1) == s.charAt(j) && counter < 1) {
                    i++;
                    counter++;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j-1) && counter < 1) {
                    j--;
                    counter++;
                    continue;
                }
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
