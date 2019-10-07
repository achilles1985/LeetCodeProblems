
package string;

/** E
 Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 Example 1:
 Input: A = "ab", B = "ba"
 Output: true

 Example 2:
 Input: A = "ab", B = "ab"
 Output: false

 Example 3:
 Input: A = "aa", B = "aa"
 Output: true

 Example 4:
 Input: A = "aaaaaaabc", B = "aaaaaaacb"
 Output: true

 Example 5:
 Input: A = "", B = "aa"
 Output: false

 Note:
 0 <= A.length <= 20000
 0 <= B.length <= 20000
 A and B consist only of lowercase letters.
 */
public class BuddyStrings_859 {

    public static void main(String[] args) {
        BuddyStrings_859 s = new BuddyStrings_859();
        System.out.println(s.buddyStrings("geeks", "keegs")); // true
        System.out.println(s.buddyStrings("ab", "ba")); // true
        System.out.println(s.buddyStrings("aaabaac", "aaacaab")); // true
        System.out.println(s.buddyStrings("", "aa")); // false
        System.out.println(s.buddyStrings("ab", "ab")); // false
        System.out.println(s.buddyStrings("aabaac", "aackab")); // false
        System.out.println(s.buddyStrings("abc", "acd")); // false
        System.out.println(s.buddyStrings("rsting", "string")); // false
    }

    // O(n) - time, O(1) - space, n - length of A, B
    public boolean buddyStrings(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        char[] aChars = A.toCharArray();
        char[] bChars = B.toCharArray();
        if (A.equals(B)) {
            char[] chars = new char[26];
            for (int i = 0; i < A.length(); i++) {
                chars[aChars[i]-'a']++;
                if (chars[aChars[i]-'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int prev = -1, cur = -1;
            int count = 0;
            for (int i = 0; i < aChars.length; i++) {
                if (aChars[i] != bChars[i]) {
                    count++;
                    prev = cur;
                    cur = i;
                    if (count > 2) {
                        return false;
                    }
                }
            }
            return aChars[prev] == bChars[cur] && aChars[cur] == bChars[prev];
        }
    }

}
