package string.LengthOfLastWord_58;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 If the last word does not exist, return 0.
 Note: A word is defined as a character sequence consists of non-space characters only.

 Example:
 Input: "Hello World"
 Output: 5

 */
public class Solution {

    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.trim();
        char[] chars = s.toCharArray();
        int counter = 0;
        for (int k = chars.length - 1; k >= 0; k--) {
            if (chars[k] == ' ') {
                return counter;
            }
            counter++;
        }

        return counter;
    }

}
