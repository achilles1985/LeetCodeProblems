package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * M
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. You can return the output in any order.
 * <p>
 * Example 1:
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * <p>
 * Example 2:
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 * <p>
 * Example 3:
 * Input: S = "12345"
 * Output: ["12345"]
 * <p>
 * Example 4:
 * Input: S = "0"
 * Output: ["0"]
 * <p>
 * Constraints:
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
/*
        a1b2            i=0, when it's at a, since it's a letter, we have two branches: a, A
     /        \
    a1b2       A1b2     i=1 when it's at 1, we only have 1 branch which is itself
     |          |
    a1b2       A1b2     i=2 when it's at b, we have two branches: b, B
    /  \        / \
  a1b2 a1B2  A1b2 A1B2  i=3  when it's at 2, we only have one branch.
   |    |     |     |
  a1b2 a1B2  A1b2  A1B2 i=4 = S.length(). End recursion, add permutation to ans.

  During this process, we are changing the S char array itself
 */
public class LetterCasePermutation_784 {

    public static void main(String[] args) {
        LetterCasePermutation_784 s = new LetterCasePermutation_784();
        System.out.println(s.letterCasePermutation("a1b2")); //["a1b2","a1B2","A1b2","A1B2"]
        System.out.println(s.letterCasePermutation("3z4")); //["3z4","3Z4"]
        System.out.println(s.letterCasePermutation("12345")); //["12345"]
    }

    // O(n*2^n) - time, space
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, 0, S.toCharArray());
        return ans;
    }

    public void backtrack(List<String> ans, int i, char[] S) {
        if (i == S.length) {
            ans.add(new String(S));
        }
        else {
            if (Character.isLetter(S[i])) { //If it's letter
                S[i] = Character.toUpperCase(S[i]);
                backtrack(ans, i + 1, S); //Upper case branch
                S[i] = Character.toLowerCase(S[i]);
                backtrack(ans, i + 1, S); //Lower case branch
            } else {
                backtrack(ans, i + 1, S);
            }
        }
    }
}
