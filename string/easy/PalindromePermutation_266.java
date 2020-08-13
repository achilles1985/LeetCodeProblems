package string.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * <p>
 * Example 1:
 * Input: "code"
 * Output: false
 * <p>
 * Example 2:
 * Input: "aab"
 * Output: true
 * <p>
 * Example 3:
 * Input: "carerac"
 * Output: true
 */
/*
Questions:
    1. Only letters & only lowercase?
    2. Max string length?
 */
public class PalindromePermutation_266 {

    public static void main(String[] args) {
        PalindromePermutation_266 s = new PalindromePermutation_266();
        System.out.println(s.canPermutePalindrome2("aB/Ba%hh%")); // true
        System.out.println(s.canPermutePalindrome2("aB//Ba%hh%")); // true
        System.out.println(s.canPermutePalindrome2("AaBb//a")); // false
        System.out.println(s.canPermutePalindrome2("code")); // false
        System.out.println(s.canPermutePalindrome2("aab")); // true
        System.out.println(s.canPermutePalindrome2("carerac")); // true
    }

    // O(n) - time, O(1) - space, because the number of distinct characters are bounded
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> lettersToCount = new HashMap<>();
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            lettersToCount.put(s.charAt(i), lettersToCount.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int count : lettersToCount.values()) {
            if (count % 2 != 0) {
                counter++;
            }
        }
        return counter <= 1;
    }

    // O(n) - time, O(1) - space
    public boolean canPermutePalindrome2(String s) {
        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!letters.add(s.charAt(i))) {
                letters.remove(s.charAt(i));
            }
        }

        return letters.size() <= 1;
    }
}
