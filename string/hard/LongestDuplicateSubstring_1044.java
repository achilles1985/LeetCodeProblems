package string.hard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** H
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 *
 * Example 1:
 * Input: s = "banana"
 * Output: "ana"
 *
 * Example 2:
 * Input: s = "abcd"
 * Output: ""
 *
 * Constraints:
 *     2 <= s.length <= 3 * 104
 *     s consists of lowercase English letters.
 */
/*
    Questions: only lowercase letters? always one result?
 */
public class LongestDuplicateSubstring_1044 {

    public static void main(String[] args) {
        LongestDuplicateSubstring_1044 s = new LongestDuplicateSubstring_1044();
        System.out.println(s.longestDupSubstring("nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy")); //ma
        System.out.println(s.longestDupSubstring("abcd")); //
        System.out.println(s.longestDupSubstring("abcde")); //
        System.out.println(s.longestDupSubstring("banana")); //ana
    }

    // O(n^2) - time, space
    public String longestDupSubstringBF(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        Map<String, Integer> map = new TreeMap<>();
        int max = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i,j);
                map.put(sub, map.getOrDefault(sub,0) + 1);
                int counter = map.get(sub);
                if (counter > 1 && sub.length() > max) {
                    result = sub;
                    max = sub.length();
                }
            }
        }
        return result;
    }

    // O(n*log(n)) - time, O(n) - space
    public String longestDupSubstring(String s) {
        int left = 0, right = s.length()-1;
        String result = "";
        while (left < right) {
            int mid = left + (right - left)/2 + 1;
            if (possible(mid, s)) {
                left = mid;
            } else {
                right = mid-1;
            }
        }
        return findSubstring(left, s);
    }

    private String findSubstring(int length, String s) {
        Set<String> set = new HashSet<>();
        int max = 0;
        String result = "";
        for (int i = 0; i <= s.length() - length; i++) {
            String sub = s.substring(i, i+length); // Robin-Karp can be used to generate next substring in O(1) time
            if (set.contains(sub) && sub.length() > max) {
                result = sub;
                max = sub.length();
            }
            set.add(sub);
        }
        return result;
    }

    private boolean possible(int length, String s) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - length; i++) {
            String sub = s.substring(i, i+length);
            if (set.contains(sub)) {
                return true;
            }
            set.add(sub);
        }
        return false;
    }
}
