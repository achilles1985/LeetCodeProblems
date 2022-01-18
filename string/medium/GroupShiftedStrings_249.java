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
        System.out.println(s.groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z", "al"})); //[["acef"],["a","z"],["al"],["abc","bcd","xyz"],["az","ba"]]
        System.out.println(s.groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"})); //[[abc, bcd, xyz], [acef], [az, ba], [a, z]]
    }

    // O(n) - time, space
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String hash = generateHash(str);
            map.computeIfAbsent(hash, h -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public String generateHash(String word) {
        char[] chars = word.toCharArray();
        StringBuilder hashKey = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            char c = (char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a');
            hashKey.append(c);
        }

        return hashKey.toString();
    }

}
