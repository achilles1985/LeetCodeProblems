package binarySearch.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * M [marked]
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * Return the maximum possible length of s.
 * <p>
 * Example 1:
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * <p>
 * Example 2:
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * <p>
 * Example 3:
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * <p>
 * Constraints:
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 */
/*
    Questions: only lowercase letters?
 */
public class MaximumLengthOfConcatenatedStringWithUniqueCharacters_1239 {

    public static void main(String[] args) {
        MaximumLengthOfConcatenatedStringWithUniqueCharacters_1239 s = new MaximumLengthOfConcatenatedStringWithUniqueCharacters_1239();
        System.out.println(s.maxLength(Arrays.asList("un", "iq", "ue"))); //4
        System.out.println(s.maxLength(Arrays.asList("cha", "r", "act", "ers"))); //6
        System.out.println(s.maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz"))); //26
    }

    // O(2^len) - time, O(words) - space
    public int maxLengthBF(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        AtomicInteger result = new AtomicInteger(0);
        backtrack(arr, 0, new ArrayList<>(), result);

        return result.intValue();
    }

    // O(2^n) - time, O(n) - space, where n - number of words,
    public int maxLength(List<String> arr) {
        int[] res = new int[1];
        List<Integer> words = new ArrayList<>();
        // preprocess to remove strings containing duplicate characters
        for (String s : arr) {
            int temp = getBits(s);
            if (temp == 26) {
                return 26;
            }
            if (temp != -1) {
                words.add(temp);
            }
        }
        backtrack(0, 0, words, res);

        return res[0];
    }

    // backtracking (the same way as building subsets from a list)
    // idx is the index of string we are currently working on
    // s is the string successfully concatenated so far (represented as int, each set bit denotes a corresponding character's appearance)
    public void backtrack(int idx, int s, List<Integer> words, int[] res) {
        res[0] = Math.max(res[0], Integer.bitCount(s));
        for (int i = idx; i < words.size(); ++i) {
            // no duplicate characters
            if ((s & words.get(i)) == 0) {
                // concatenate current string
                s |= words.get(i);
                backtrack(i + 1, s, words, res);
                // remove current string
                s ^= words.get(i);
            }
        }
    }

    // convert string to int, we only have 26 unique characters, so an int (32 bits) is enough to show which characters appear in string
    public int getBits(String s) {
        int bits = 0;
        for (char c : s.toCharArray()) {
            int temp = 1 << (c - 'a');
            // found duplicate characters
            if ((temp & bits) > 0) {
                return -1;
            }
            // set the bit for current character
            bits |= temp;
        }
        return bits;
    }

    private void backtrack(List<String> words, int start, List<String> list, AtomicInteger result) {
        String str = list.stream().collect(Collectors.joining()); //words*length
        if (allUnique(str) && str.length() > result.intValue()) { //words*length
            result.set(str.length());
        }
        for (int i = start; i < words.size(); i++) {
            String next = words.get(i);
            if (!allUnique(next)) {
                continue;
            }
            list.add(next);
            backtrack(words, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    private boolean allUnique(String word) {
        int[] chars = new int[26];
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            chars[index]++;
            if (chars[index] > 1) {
                return false;
            }
        }
        return true;
    }
}
