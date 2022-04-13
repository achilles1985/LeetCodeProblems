package slidingWindow.medium;

/** M [marked]
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
// https://leetcode.com/problems/longest-repeating-character-replacement/discuss/358879/Java-Solution-Explained-and-Easy-to-Understand-for-Interviews
public class LongestRepeatingCharacterReplacement_424 {

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement_424 s = new LongestRepeatingCharacterReplacement_424();
        System.out.println(s.characterReplacement("ABAB", 2)); //4
        System.out.println(s.characterReplacement("AABABBA", 1)); //4
    }

    // O(n) - time, O(1) - space
    public int characterReplacement(String s, int k) {
        int mostFrequentLetter = 0, ans = 0;
        int[] freq = new int[26];
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            mostFrequentLetter = Math.max(mostFrequentLetter, freq[c - 'A']);
            while (right - left + 1 - mostFrequentLetter > k) {
                char cc = s.charAt(left++);
                freq[cc - 'A']--;
            }
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
