package string.easy;

/** E
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Example 2:
 * Input: "race a car"
 * Output: false
 *
 * Constraints:
 *     s consists only of printable ASCII characters.
 */
public class ValidPalindrome_125 {

    public static void main(String[] args) {
        ValidPalindrome_125 s = new ValidPalindrome_125();
        System.out.println(s.isPalindrome(".,"));
    }

    // O(n) - time, O(1) - space
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int left = 0, right = s.length()-1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))  {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
