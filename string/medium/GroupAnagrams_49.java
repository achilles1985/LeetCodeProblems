package string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** M
 Given an array of strings, group anagrams together.

 Example:
 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]

 Note:
 All inputs will be in lowercase.
 The order of your output does not matter.
 */
// Q: 1. All words have the same length? Duplicates possible?
public class GroupAnagrams_49 {

    public static void main(String[] args) {
        GroupAnagrams_49 s = new GroupAnagrams_49();
        System.out.println(s.groupAnagrams2(new String[]{"bdddddddddd", "bbbbbbbbbbc"}));
        System.out.println(s.groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    // O(n*m) - time, O(n*m) - space, where m - max length of the string
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word: strs) {
            String hash = hash(word);
            map.computeIfAbsent(hash, k -> new ArrayList<>()).add(word);
        }

        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            list.add(entry.getValue());
        }

        return list;
    }

    // O(n*(m*log(m))) - time, O(n) - space
    public List<List<String>> groupAnagramsBF(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word: strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            map.computeIfAbsent(str, k -> new ArrayList<>()).add(word);
        }

        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    // "bdddddddddd" != "bbbbbbbbbbc"
    //010100000000000000000000000 == 010100000000000000000000000
    //#0#1#0#10#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0 != #0#10#1#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0
    private String hash(String word) {
        int[] arr = new int[26];
        for (int i = 0; i < word.length(); i++) {
            arr[word.charAt((i))-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append("#").append(arr[i]); //'#' - is important (see above))
        }

        return sb.toString();
    }
}
