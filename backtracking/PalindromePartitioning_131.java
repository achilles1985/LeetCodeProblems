package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** M
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 Return all possible palindrome partitioning of s.

 Example:
 Input: "aab"
 Output:
 [
 ["aa","b"],
 ["a","a","b"]
 ]
 */
public class PalindromePartitioning_131 {

    public static void main(String[] args) {
        PalindromePartitioning_131 s = new PalindromePartitioning_131();
        System.out.println(s.partition("rotor"));
        System.out.println(s.partition("aaabbbcc"));
        System.out.println(s.partition("aab"));
    }

    // O(n*2^n) - time
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new LinkedList();
        backtrack(s, partitions, new LinkedList<>(), 0);
        return partitions;
    }

    private void backtrack(String s, List<List<String>> partitions, List<String> part, int start){
        if(start == s.length()){
            partitions.add(new LinkedList(part));
            return;
        }
        for(int i = start+1; i<=s.length(); i++){
            String prefix = s.substring(start, i);
            if(isPalindrome(prefix)) {
                part.add(prefix);
                backtrack(s, partitions, part, i);
                part.remove(part.size()-1);
            }
        }
    }

    private boolean isPalindrome(String word){
        int i = 0;
        int j = word.length()-1;
        while(i<j){
            if(word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
