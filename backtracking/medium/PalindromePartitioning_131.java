package backtracking.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * M [marked]
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning_131 {

    public static void main(String[] args) {
        PalindromePartitioning_131 s = new PalindromePartitioning_131();
        System.out.println(s.partition("rotor")); //[[r, o, t, o, r], [r, oto, r], [rotor]]
        System.out.println(s.partition("aab")); //[[a, a, b], [aa, b]]
        System.out.println(s.partition("aaabbbcc")); //[[a, a, a, b, b, b, c, c], [a, a, a, b, b, b, cc], [a, a, a, b, bb, c, c], [a, a, a, b, bb, cc], [a, a, a, bb, b, c, c], [a, a, a, bb, b, cc], [a, a, a, bbb, c, c], [a, a, a, bbb, cc], [a, aa, b, b, b, c, c], [a, aa, b, b, b, cc], [a, aa, b, bb, c, c], [a, aa, b, bb, cc], [a, aa, bb, b, c, c], [a, aa, bb, b, cc], [a, aa, bbb, c, c], [a, aa, bbb, cc], [aa, a, b, b, b, c, c], [aa, a, b, b, b, cc], [aa, a, b, bb, c, c], [aa, a, b, bb, cc], [aa, a, bb, b, c, c], [aa, a, bb, b, cc], [aa, a, bbb, c, c], [aa, a, bbb, cc], [aaa, b, b, b, c, c], [aaa, b, b, b, cc], [aaa, b, bb, c, c], [aaa, b, bb, cc], [aaa, bb, b, c, c], [aaa, bb, b, cc], [aaa, bbb, c, c], [aaa, bbb, cc]]
    }

    // O(n*2^n) - time
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new LinkedList();
        backtrack(s, partitions, new LinkedList<>(), 0);
        return partitions;
    }

    private void backtrack(String s, List<List<String>> partitions, List<String> part, int start) {
        if (start == s.length()) {
            partitions.add(new LinkedList(part));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String prefix = s.substring(start, i + 1);
            if (isPalindrome(prefix)) { //can be optimized to O(1) if using dp[][] (if str[start] == str[end] and str[start+1] == str[end-1] -> palindrome)
                part.add(prefix);
                backtrack(s, partitions, part, i + 1);
                part.remove(part.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String word) {
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
