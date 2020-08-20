package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * M
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * <p>
 * Example:
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 */
public class GroupShiftedStrings_249 {

    public static void main(String[] args) {
        GroupShiftedStrings_249 s = new GroupShiftedStrings_249();
        System.out.println(s.groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }

    // O(n) - time, space
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String hash = generateHash(str);
            map.computeIfAbsent(hash, h -> new ArrayList<>()).add(str);
        }
        for (List<String> lists : map.values()) {
            result.add(lists);
        }

        return result;
    }

    public String generateHash(String word) {
        if (word.length() == 1) {
            return "No shift";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < word.length(); i++) { // no more than 26 times if no duplicates in a word
            char prev = word.charAt(i - 1);
            char curr = word.charAt(i);
            int diff = (curr - 'a') - (prev - 'a');
            if (diff < 0) {
                diff = diff + 26;
            }
            sb.append(diff);
        }

        return sb.toString();
    }

}
