package string.medium;

import java.util.*;
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
        System.out.println(s.reverseWords4("  hello world!  ")); // world! hello
        System.out.println(s.reverseWords3("a  good   example")); // example good a
        System.out.println(s.reverseWords3("the sky is blue")); // blue is sky the

        System.out.println(s.reverseWords("a good   example")); // example good a
        System.out.println(s.reverseWords("the sky is blue")); // blue is sky the
        System.out.println(s.reverseWords("  hello world!  ")); // world! hello
    }

    // O(n) - time, space
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length-1; i>= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    // O(n) - time, space
    public String reverseWords2(String s) {
        String[] words = s.trim().split("\\s+");
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++; right--;
        }
        return Arrays.stream(words).collect(Collectors.joining(" "));
    }

    // O(n) - time, space
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        // remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        // push word by word in front of deque
        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }

    // O(n) - time, space
    public String reverseWords4(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String trimmed = s.trim();
        List<String> list = Arrays.asList(trimmed.split("\\s+"));
        Collections.reverse(list);

        return list.stream().collect(Collectors.joining(" "));
    }

    // O(n) - time, space
    public String reverseWords5(String s) {
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
