package string.easy;

import java.util.Arrays;

/**E
 * You are given an array of strings words and a string chars.
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * Return the sum of lengths of all good strings in words.
 *
 * Example 1:
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 *
 * Example 2:
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation:
 * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 * Note:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * All strings contain lowercase English letters only.
 */
public class FindWordsThatCanBeFormedByCharacters_1160 {

    public static void main(String[] args) {
        FindWordsThatCanBeFormedByCharacters_1160 s = new FindWordsThatCanBeFormedByCharacters_1160();
        System.out.println(s.countCharacters(new String[]{"cat","bt","hat","tree"}, "atach")); //6
        System.out.println(s.countCharacters(new String[]{"hello","world","leetcode"}, "welldonehoneyr")); //10
    }

    // O(words*(max(word) + max(word))) - time, O(1) - space
    public int countCharacters(String[] words, String chars) {
        int[] seen = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            seen[chars.charAt(i) - 'a']++;
        }
        int result = 0;
        for (String word: words) {
            int[] clone = Arrays.copyOf(seen, seen.length);
            int currentCounter = 0;
            for (int i = 0; i < word.length(); i++) {
                if (clone[word.charAt(i) - 'a'] > 0) {
                    clone[word.charAt(i) - 'a']--;
                    currentCounter++;
                } else {
                    break;
                }
            }
            if (currentCounter == word.length()) {
                result += word.length();
            }
        }
        return result;
    }
}
