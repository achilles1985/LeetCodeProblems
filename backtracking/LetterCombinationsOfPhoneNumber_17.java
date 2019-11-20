package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 Example:
 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfPhoneNumber_17 {

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber_17 s = new LetterCombinationsOfPhoneNumber_17();
        System.out.println(s.letterCombinations("234"));
        System.out.println(s.letterCombinations("23")); // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    }

    // O((4^n)*n) - time, O(n + 4^n)-space, n - number of digits in the input, 4 - because we have at most 4 letters for each number
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        Map<Character, char[]> lettersMap = new HashMap<>();
        lettersMap.put('0', new char[]{});
        lettersMap.put('1', new char[]{});
        lettersMap.put('2', new char[]{'a', 'b', 'c'});
        lettersMap.put('3', new char[]{'d', 'e', 'f'});
        lettersMap.put('4', new char[]{'g', 'h', 'i'});
        lettersMap.put('5', new char[]{'j', 'k', 'l'});
        lettersMap.put('6', new char[]{'m', 'n', 'o'});
        lettersMap.put('7', new char[]{'p', 'q', 'r', 's'});
        lettersMap.put('8', new char[]{'t', 'u', 'v'});
        lettersMap.put('9', new char[]{'w', 'x', 'y', 'z'});

        List<String> result = new ArrayList<>();
        dfs(digits, new StringBuilder(), result, lettersMap);

        return result;
    }

    private void dfs(String digits, StringBuilder sb, List<String> result, Map<Character, char[]> map) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c: map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            dfs(digits, sb, result, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
