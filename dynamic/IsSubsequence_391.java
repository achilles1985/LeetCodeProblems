package dynamic;

/** M
 Given a string s and a string t, check if s is subsequence of t.
 You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

 Example 1:
 s = "abc", t = "ahbgdc"
 Return true.

 Example 2:
 s = "axc", t = "ahbgdc"
 Return false.

 Follow up:
 If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence.
 In this scenario, how would you change your code?

 Credits:
 Special thanks to @pbrother for adding this problem and creating all test cases.
 */
public class IsSubsequence_391 {

    public static void main(String[] args) {
        IsSubsequence_391 s = new IsSubsequence_391();
        System.out.println(s.isSubsequenceIterative("abc", "ahbgdc")); // true
        System.out.println(s.isSubsequence("axc", "ahbgdc")); // false
        System.out.println(s.isSubsequence("b", "c")); // false

        System.out.println(s.isSubsequenceRecursion("abc", "ahbgdc")); // true
        System.out.println(s.isSubsequenceRecursion("axc", "ahbgdc")); // false
    }

    // O((t.length) - time, O(1) - space
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        if (t.isEmpty()) {
            return false;
        }
        if (s.length() == 1 && t.length() == 1) {
            return s.charAt(0) == t.charAt(0);
        }

        int i = 0;
        int j = 0;
        while (i < s.length()-1 && j < t.length()-1) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length()-1 ? true : false;
    }

    public boolean isSubsequenceRecursion(String s, String t) {
        return isSubsequenceRecursion(s, t, 0, 0);
    }

    public boolean isSubsequenceIterative(String s, String t) {
        int i;
        int j = 0;
        int m = s.length();
        for (i = 0; i < t.length() && j < s.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }

        return j == m;
    }

    private boolean isSubsequenceRecursion(String s, String t, int i, int j) {
        if (s.length() == i) {
            return true;
        }
        if (t.length() == j) {
            return false;
        }
        if (s.charAt(i) == t.charAt(j)) {
            return isSubsequenceRecursion(s, t, i+1, j+1);
        }
        return isSubsequenceRecursion(s, t, i, j+1);
    }
}
