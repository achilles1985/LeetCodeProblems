package string.easy;

/** E
 Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:
 Input: s = "anagram", t = "nagaram"
 Output: true

 Example 2:
 Input: s = "rat", t = "car"
 Output: false

 Note:
 You may assume the string contains only lowercase alphabets.
 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
/*
    Questions:
    1. s1, s2 length? if it's > 2^16 and all chars in a string are equals, might lead to invalid value because of rounding up.
 */
public class ValidAnagram_242 {

    public static void main(String[] args) {
        ValidAnagram_242 s = new ValidAnagram_242();
        System.out.println(s.isAnagram("anagram", "nagaram")); //true
        System.out.println(s.isAnagram("rat", "car")); //false
    }

    // O(n) - time, O(1) - space
    public boolean isAnagram(String s, String t) {
        long k = 0L;
        char[] c = new char[1];
        while (k++ < 131_073L) {
            c[0]++;
        }
        if (s.length() != t.length()) {
            return false;
        }
        char[] counterA = countCharacters(s);
        char[] counterB = countCharacters(t);
        for (int i = 0; i < counterA.length; i++) {
            if (counterB[i] != counterA[i]) {
                return false;
            }
        }

        return true;
    }

    private char[] countCharacters(String s) {
        char[] arrS = new char[26];
        for (int i = 0; i < s.length(); i++) {
            arrS[s.charAt(i)-'a']++;
        }

        return arrS;
    }
}
