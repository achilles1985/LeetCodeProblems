package string.FirstUniqueCharacterInString_387;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Eeasy
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 Examples:
 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.

 Note: You may assume the string contain only lowercase letters.
 */
public class Solution {

    // O(n) - time, https://leetcode.com/problems/first-unique-character-in-a-string/solution/
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        if (s.length() == 1) {
            return 0;
        }
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
