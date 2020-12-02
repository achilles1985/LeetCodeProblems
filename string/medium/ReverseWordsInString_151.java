package string.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/** M
 * Given an input string, reverse the string word by word.
 * <p>
 * Example 1:
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Note:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class ReverseWordsInString_151 {

    public static void main(String[] args) {
        ReverseWordsInString_151 s = new ReverseWordsInString_151();
        System.out.println(s.reverseWords2("a good   example")); // example good a
        System.out.println(s.reverseWords2("the sky is blue")); // blue is sky the
        System.out.println(s.reverseWords2("  hello world!  ")); // world! hello

        System.out.println(s.reverseWords("a good   example")); // example good a
        System.out.println(s.reverseWords("the sky is blue")); // blue is sky the
        System.out.println(s.reverseWords("  hello world!  ")); // world! hello
    }

    // O(n) - time, space
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String trimmed = s.trim();
        List<String> list = Arrays.asList(trimmed.split("\\s+"));
        Collections.reverse(list);

        return list.stream().collect(Collectors.joining(" "));
    }

    // O(n) - time, space
    public String reverseWords2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        String words = s.trim();
        String trimmed = trimMiddle(words);
        char[] arr = trimmed.toCharArray();
        reverse(arr, 0, trimmed.length() - 1);

        int left = 0;
        int right = 0;
        while (left < trimmed.length()) {
            while (right < arr.length && arr[right] != ' ') {
                right++;
            }
            reverse(arr, left, right - 1);
            left = right + 1;
            right = left;
        }
        reverse(arr, left, arr.length - 1);

        return String.valueOf(arr);
    }

    private String trimMiddle(String s) {
        String trimmed = s.trim();
        char[] chars = trimmed.toCharArray();
        int left = 0;
        int right = chars.length-1;
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            if (chars[left] != ' ') {
                sb.append(chars[left]);
            } else {
                if (sb.charAt(sb.length()-1) != ' ') {
                    sb.append(chars[left]);
                }
            }
            left++;
        }
        return sb.toString();
    }

    private void reverse(char[] arr, int start, int end) {
        while (start <= end) {
            swap(start, end, arr);
            start++;
            end--;
        }
    }

    private void swap(int i, int j, char[] arr) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
