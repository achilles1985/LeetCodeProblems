package slidingWindow.medium;

import java.util.HashSet;
import java.util.Set;

/** M
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 * Example 1:
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c
 * are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 *
 * Example 2:
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 *
 * Example 3:
 * Input: s = "abc"
 * Output: 1
 *
 * Constraints:
 *     3 <= s.length <= 5 x 10^4
 *     s only consists of a, b or c characters.
 */
public class NumberOfSubstringsContainingAllThreeCharacters_1358 {

    public static void main(String[] args) {
        NumberOfSubstringsContainingAllThreeCharacters_1358 s = new NumberOfSubstringsContainingAllThreeCharacters_1358();
        System.out.println(s.numberOfSubstrings("abcabc")); //10
        System.out.println(s.numberOfSubstrings("aaacb")); //3
        System.out.println(s.numberOfSubstrings("abc")); //1
    }

    // O(n^3) - time, O(1) - space
    public int numberOfSubstringsBF(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (int k = 3; k <= s.length(); k++) {
            for (int i = 0; i <= s.length() - k; i++) {
                Set<Character> set = new HashSet<>();
                for (int j = i; j < i + k; j++) {
                    set.add(s.charAt(j));
                }
                if (set.size() == 3) {
                    count++;
                }
            }
        }
        return count;
    }

    // O(n^3) - time, O(1) - space
    public int numberOfSubstringsBF2(int[] nums, int k) {
        int result = 0;
        for (int s = k; s < nums.length; s++) {
            for (int i = 0; i < nums.length - k; i++) {
                int count = 0;
                for (int j = i; j < i + s; j++) {
                    if (nums[j]%2 != 0) {
                        count++;
                    }
                }
                if (count >= k) {
                    result++;
                }
            }
        }
        return result;
    }

    // O(n) - time, space
    public int numberOfSubstrings(String s) {
        int[] freq = {0,0,0};
        int count = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                freq[s.charAt(left++) - 'a']--;
            }
            count += left;
        }
        return count;
    }
}
