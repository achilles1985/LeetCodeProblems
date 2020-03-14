package dynamic;

import java.util.*;

/**M
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no
 * deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 *
 * Example 1:
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 *
 * Example 2:
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character
 * "d" in target string.
 *
 * Example 3:
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 * Constraints:
 *     Both the source and target strings consist of only lowercase English letters from "a"-"z".
 *     The lengths of source and target string are between 1 and 1000.
 */
/*
    Traverse through source several times. each time we reach end of source, increment counter.
 */
public class ShortestWayToFormString_1055 {

    public static void main(String[] args) {
        ShortestWayToFormString_1055 s = new ShortestWayToFormString_1055();
        System.out.println(s.shortestWay3("xyz", "xzyxz")); //3 xz + y + xz
        System.out.println(s.shortestWay3("abc", "abcbc")); //2 abc + bc
        System.out.println(s.shortestWay3("abc", "acdbc")); //-1
        System.out.println(s.shortestWay3("adbsc", "addddddddddddsbc")); //13
    }

    // O(m*n) - time, O(1) - space the same as shortsWay2
    public int shortestWay3(String source, String target) {
        if (source.length() == 0 || target.length() == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> indexesByChar = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            indexesByChar.computeIfAbsent(source.charAt(i)-'a', key -> indexesByChar.getOrDefault(key, new ArrayList<>())).add(i);
        }
        int j = 0;
        int result = 1;
        for (int i = 0; i < target.length(); ) {
            List<Integer> indexes = indexesByChar.getOrDefault(target.charAt(i) - 'a', new ArrayList<>());
            if (indexes.isEmpty()) {
                return -1;
            }
            int foundPosition = Collections.binarySearch(indexes, j);
            if (foundPosition < 0) {
                foundPosition = -foundPosition - 1;
            }
            if (foundPosition == indexes.size()) {
                result++;
                j = 0;
            } else {
                j = indexes.get(foundPosition) + 1;
                i++;
            }
        }
        return result;
    }

    // O(m*n) - time, O(1) - space
    public int shortestWay(String source, String target) {
        boolean[] unique = new boolean[26];
        for (int i = 0; i < source.length(); i++) {
            unique[source.charAt(i)-'a'] = true;
        }
        int j = 0;
        int result = 1;
        for (int i = 0; i < target.length(); i++, j++) {
            if (!unique[target.charAt(i) - 'a']) {
                return -1;
            }
            while (j < source.length() && source.charAt(j) != target.charAt(i)) {
                j++;
            }
            if (j == source.length()) {
                result++;
                j = -1;
                i--;
            }
        }
        return result;
    }

    // O(m*log(n)) - time, O(1) - space
    public int shortestWay2(String source, String target) {
        char[] cs = source.toCharArray(), ts = target.toCharArray();
        int res = 1;
        List<Integer>[] idx = new List[26];
        for (int i = 0; i < 26; i++) {
            idx[i] = new ArrayList<>();
        }
        for (int i = 0; i < cs.length; i++) {
            idx[cs[i] - 'a'].add(i);
        }
        int j = 0;
        for (int i = 0; i < ts.length; ) {
            List<Integer> tar = idx[ts[i] - 'a'];
            if (tar.isEmpty()) {
                return -1;
            }
            int k = Collections.binarySearch(tar,j);
            if (k < 0) {
                k = -k - 1;
            }
            if (k == tar.size()) {
                res++;
                j = 0;
            } else {
                j = tar.get(k) + 1;
                i++;
            }

        }
        return res;
    }
}
