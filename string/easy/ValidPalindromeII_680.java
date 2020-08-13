package string.easy;

/**
 * Easy
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * <p>
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * <p>
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII_680 {

    public static void main(String[] args) {
        ValidPalindromeII_680 s = new ValidPalindromeII_680();
        System.out.println(s.validPalindrome("cbbcc")); // true
        System.out.println(s.validPalindrome("abc")); // false
        System.out.println(s.validPalindrome("aba")); // true
        System.out.println(s.validPalindrome("abca")); // true
        System.out.println(s.validPalindrome("abcedba")); // false
        System.out.println(s.validPalindrome("abceeba")); // true
        System.out.println(s.validPalindrome("abcedkeba")); // false
    }

    // O(n) - time, O(1) - space
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                // if either left substring or right substring are palindrom, return true
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
