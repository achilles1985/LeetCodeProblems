package string.ReverseWordsInString_151;

/** Medium
 * Given an input string, reverse the string word by word.

 Example 1:
 Input: "the sky is blue"
 Output: "blue is sky the"

 Example 2:
 Input: "  hello world!  "
 Output: "world! hello"
 Explanation: Your reversed string should not contain leading or trailing spaces.

 Example 3:
 Input: "a good   example"
 Output: "example good a"
 Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

 Note:
 A word is defined as a sequence of non-space characters.
 Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class Solution {

    // O(n) - time, O(1) - space
    public String reverseWords(String s) {
        String reversedSentence = reverse(s.trim());
        String[] words = reversedSentence.split("\\s+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(reverse(words[i]));
            if (i < words.length-1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    private String reverse(String sentence) {
        int i = 0;
        int j = sentence.length() - 1;
        char[] chars = sentence.toCharArray();
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    // second variant
    public String reverseWords2(String s) {
        char[] chars = s.toCharArray();
        reverse2(chars, 0, chars.length-1);
        int start = 0, end;
        while ((end = find(chars, ' ', start)) != -1) {
            reverse2(chars, start, end);
            start = end + 1;
        }

        reverse2(chars, start, chars.length-1);

        return String.valueOf(chars);
    }

    private void reverse2(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }

    private int find(char[] chars, char target, int start) {
        for (int i = start; i < chars.length; i++) {
            if (chars[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
