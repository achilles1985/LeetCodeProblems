package backtracking.medium;

import java.util.*;

/** M [marked]
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
        System.out.println(s.letterCombinationsBFS("23")); // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        System.out.println(s.letterCombinations("12"));
        System.out.println(s.letterCombinations("234")); //[adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg, beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]
        System.out.println(s.letterCombinations("23")); // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    }

    // O((4^n)*n) - time, O(n)-space, n - number of digits in the input, 4 - because we have at most 4 letters for each number
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Character, List<Character>> map = buildMap();

        List<String> result = new ArrayList<>();
        dfs(digits, map, new StringBuilder(), result);

        return result;
    }

    // O(n*4^n) - time, O(4^n) - space
    public List<String> letterCombinationsBFS(String digits) {
        if (digits == null || digits.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Character, List<Character>> map = buildMap();
        Queue<String> queue = new LinkedList<>();
        for (Character c: map.get(digits.charAt(0))) {
            queue.add(String.valueOf(c));
        }
        int level = 1;
        while (!queue.isEmpty() && level < digits.length()) {
            int size = queue.size();
            while (size-- > 0) {
                String poll = queue.poll();
                for (Character c: map.get(digits.charAt(level))) {
                    String next = poll + c;
                    queue.add(next);
                }
            }
            level++;
        }
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }

    private void dfs(String digits, Map<Character, List<Character>> map, StringBuilder sb, List<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString()); //n
            return;
        }
        for (Character c: map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            dfs(digits, map, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void dfs2(String digits, int row, StringBuilder sb, List<String> result, Map<Character, char[]> map) {
        if (digits.length() == sb.length()) {
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < map.get(digits.charAt(row)).length; i++) {
            char[] chars = map.get(digits.charAt(row));
            sb.append(chars[i]);
            dfs2(digits, row+1, sb, result, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private Map<Character, List<Character>> buildMap() {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','u','v'));
        map.put('9', Arrays.asList('w','x','y','z'));
        return map;
    }
}
