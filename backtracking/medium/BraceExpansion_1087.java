package backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import utils.SolutionUtils;

/**M [marked]
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there
 * is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a",
 * "b", "c"].
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 * Example 1:
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 *
 * Example 2:
 * Input: "abcd"
 * Output: ["abcd"]
 *
 * Note:
 *     1 <= S.length <= 50
 *     There are no nested curly brackets.
 *     All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
/*
    1. Any special chars except '{},"?
    2. Can string be without special chars like, "abcd"?
    3. "{a,b,c}
 */
public class BraceExpansion_1087 {

    public static void main(String[] args) {
        BraceExpansion_1087 s = new BraceExpansion_1087();
        SolutionUtils.print(s.expand("abcde")); // ["abcde"]
        SolutionUtils.print(s.expand("{a,b}")); // ["a,b"]
        SolutionUtils.print(s.expand("{a,b}c{d,e}f")); //["acdf","acef","bcdf","bcef"]
        SolutionUtils.print(s.expand("{a,b}{z,x,y}")); // ["ax","ay","az","bx","by","bz"]
    }

    // O(n^n) - time, O(n) - space - number of tokens in input string
    public String[] expand(String S) {
        if (S == null || S.isEmpty()) {
            return new String[]{};
        }
        List<List<String>> list1 = tokenize(S);
        if (list1.size() == 1) {
            return new String[]{list1.get(0).get(0)};
        }
        List<String> result = new ArrayList<>();
        expandHelper(list1, result, new StringBuilder());
        Collections.sort(result);

        return result.toArray(new String[]{});
    }

    private List<List<String>> tokenize(String input) {
        List<List<String>> result = new ArrayList<>();
        String[] s1 = input.split("[{}]");
        if (s1.length == 1) {
            List<String> list = new ArrayList<>();
            list.add(s1[0]);
            result.add(list);
            return result;
        }
        for (String str1: s1) {
            List<String> list2 = new ArrayList<>();
            for (String str2: str1.split(",")) {
                if (!str2.isEmpty()) {
                    list2.add(str2);
                }
            }
            if (!list2.isEmpty()) {
                result.add(list2);
            }
        }
        return result;
    }

    private List<List<Character>> tokenize2(String S) {
        Stack<Character> stack = new Stack<>();
        List<List<Character>> result = new ArrayList<>();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c)) {
                stack.push(c);
            } else if ('{' == c || '}' == c) {
                List<Character> words = new ArrayList<>();
                while (!stack.isEmpty()) {
                    words.add(stack.pop());
                }
                if (!words.isEmpty()) {
                    result.add(words);
                }
            }
        }
        List<Character> words = new ArrayList<>();
        while (!stack.isEmpty()) {
            words.add(stack.pop());
        }
        if (!words.isEmpty()) {
            result.add(words);
        }

        return result;
    }

    private void expandHelper(List<List<String>> input, List<String> result, StringBuilder sb) {
        if (sb.length() == input.size()) {
            result.add(sb.toString());
            return;
        }
        for (String str: input.get(sb.length())) {
            sb.append(str);
            expandHelper(input, result, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

}
