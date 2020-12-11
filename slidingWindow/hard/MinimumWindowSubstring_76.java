package slidingWindow.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * H [marked]
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring_76 {

    public static void main(String[] args) {
        MinimumWindowSubstring_76 s = new MinimumWindowSubstring_76();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC")); //BANC
        System.out.println(s.minWindow("bba", "ab")); //ba

        System.out.println(s.minWindowBF("bba", "ab")); //ba
        System.out.println(s.minWindowBF("ADOBECODEBANC", "ABC")); //BANC
    }

    // O(s^2) - time, O(t) - space
    public String minWindowBF(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            Map<Character, Integer> clone = new HashMap<>(map);
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                sb.append(c);
                if (clone.containsKey(c) && clone.get(c) > 0) {
                    clone.put(c, clone.get(c) - 1);
                    count++;
                }
                if (count == t.length()) {
                    if (sb.length() < min) {
                        min = sb.length();
                        result = sb.toString();
                    }
                }
            }
        }
        return result;
    }

    // O(t + s) - time, O(t + s) - space, s - since we create result each time when encounter currLength < min
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c: t.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int counter = t.length();
        int min = Integer.MAX_VALUE;
        String result = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (frequency.containsKey(rightChar)) {
                frequency.put(rightChar, frequency.getOrDefault(rightChar, 0) - 1);
                if (frequency.get(rightChar) >= 0) {
                    counter--;
                }
            }
            while (counter == 0 && left <= right) {
                int currLength = right - left + 1;
                if (currLength < min) {
                    min = currLength;
                    result = s.substring(left, right + 1);
                }
                char leftChar = s.charAt(left);
                if (frequency.containsKey(leftChar)) {
                    frequency.put(leftChar, frequency.get(leftChar) + 1);
                    if (frequency.get(leftChar) >= 1) {
                        counter++;
                    }
                }
                left++;
            }
            right++;
        }
        return result;
    }

}
