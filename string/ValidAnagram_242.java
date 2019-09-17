package string;

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
public class ValidAnagram_242 {

    public static void main(String[] args) {
        ValidAnagram_242 s = new ValidAnagram_242();
        System.out.println(s.isAnagram("anagram", "nagaram")); //true
        System.out.println(s.isAnagram("rat", "car")); //false
    }

    // O(n) - time, O(1) - space
    public boolean isAnagram(String s, String t) {
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
