package string.medium;

import java.util.ArrayList;
import java.util.List;

/**M
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 *     S will have length in range [1, 500].
 *     S will consist of lowercase English letters ('a' to 'z') only.
 */
public class PartitionLabels_763 {

    public static void main(String[] args) {
        PartitionLabels_763 s = new PartitionLabels_763();
        System.out.println(s.partitionLabels("abccaddbeffe")); //[8,4]
        System.out.println(s.partitionLabels("ababcbacadefegdehijhklij")); //[9,7,8]
        System.out.println(s.partitionLabels("ababab")); //[6]
    }

    // BF, merge intervals tecknic, like in 56. O(n*log(n)) - time

    // O(n) - time, O(1) - space
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}
