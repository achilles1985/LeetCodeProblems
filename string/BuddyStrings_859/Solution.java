package string.BuddyStrings_859;

/** Easy
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
 */
public class Solution {

    public boolean buddyStrings(String A, String B) {
        if (A.isEmpty() && B.isEmpty()) {
            return true;
        }
        if (A.isEmpty() || B.isEmpty()) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }

        if (A.equals(B)) {
            // If all the chars are unique, no swap might lead to A==B
            int[] count = new int[26];
            // count number of each char in a string
            for (int i = 0; i < A.length(); i++) {
                count[A.charAt(i) - 'a']++;
            }
            for (int c: count) {
                if (c > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1;
            int second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1) {
                        first = i;
                    } else if (second == -1) {
                        second = i;
                    } else {
                        return false;
                    }
                }
            }

            if (second != -1) {
                return B.charAt(first) == A.charAt(second) && A.charAt(first) == B.charAt(second);
            }
            return false;
        }
    }

}
