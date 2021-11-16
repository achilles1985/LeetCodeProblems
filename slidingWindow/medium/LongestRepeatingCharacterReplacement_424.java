package slidingWindow.medium;

/** M
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 * Input:
 * s = "ABAB", k = 2
 * Output:
 * 4Ø
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 * Input:
 * s = "AABABBA", k = 1
 * Output:
 * 4
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
        int left = 0, right = 0;
        int[] frequency = new int[26];
        int maxFrequency = 0;
        int maxLength = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            frequency[c - 'A']++;
            maxFrequency = Math.max(maxFrequency, frequency[c - 'A']);
            while (right - left + 1 - maxFrequency > k) {
                frequency[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
