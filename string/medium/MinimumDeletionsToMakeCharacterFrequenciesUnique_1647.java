package string.medium;

import java.util.*;

/** M
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 *
 * Example 2:
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 *
 * Example 3:
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 *
 * Constraints:
 *     1 <= s.length <= 105
 *     s contains only lowercase English letters.
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique_1647 {

    public static void main(String[] args) {
        MinimumDeletionsToMakeCharacterFrequenciesUnique_1647 s = new MinimumDeletionsToMakeCharacterFrequenciesUnique_1647();
        System.out.println(s.minDeletions("aaabbbcc")); //2
        System.out.println(s.minDeletions("aaaabbbbccccddeefg")); //8
        System.out.println(s.minDeletions("aaabbc")); //0
    }

    // O(n) - time, O(1) - space
    public int minDeletions(String s) {
        int arr[] = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        Set<Integer> set = new HashSet<>();
        int deleted = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) {
                continue;
            }
            while (arr[i] > 0 && set.contains(arr[i])) {
                arr[i]--;
                deleted++;
            }
            set.add(arr[i]);
        }

        return deleted;
    }

}
