package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * Example:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences_187 {
    private static final int DNA_SEQUENCE = 10;

    private static final Map<Character, Integer> CHAR_TO_INT = new HashMap() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public static void main(String[] args) {
        RepeatedDNASequences_187 s = new RepeatedDNASequences_187();
        System.out.println(s.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")); //["AAAAACCCCC", "CCCCCAAAAA"]
    }

    // O((s-10)*10) - time, space
    public List<String> findRepeatedDnaSequencesBF(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty!");
        }
        Set<String> results = new HashSet<>();
        Set<String> unique = new HashSet<>();
        unique.add(s.substring(0, 10));
        for (int i = 1; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (unique.contains(sub)) {
                results.add(sub);
            }
            unique.add(sub);
        }
        return new ArrayList<>(results);
    }

    // O(n) - time, space (to keep set of hashes)
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.isEmpty() || s.length() < 10) {
            return new ArrayList<>();
        }

        Set<Integer> hashes = new HashSet<>();
        int hash = 0;
        for (int i = 0; i < DNA_SEQUENCE; i++) {
            int c = CHAR_TO_INT.get(s.charAt(i));
            hash = (int) (hash + c * Math.pow(4.0, i));
        }
        hashes.add(hash);
        Set<String> result = new HashSet<>();
        for (int i = 1; i <= s.length() - DNA_SEQUENCE; i++) {
            hash = rehash(hash, s, i);
            if (hashes.contains(hash)) {
                result.add(s.substring(i, i + DNA_SEQUENCE));
            }
            hashes.add(hash);
        }
        return new ArrayList<>(result);
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        int TEN = 10;
        int FOUR = 4;
        int POWER = (int) Math.pow(FOUR, TEN);
        Map<Character, Integer> toInt = new HashMap() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};

        int strL = s.length();
        if (strL <= TEN) {
            return new ArrayList();
        }

        int[] nums = new int[strL];
        for (int i = 0; i < strL; ++i) {
            nums[i] = toInt.get(s.charAt(i));
        }
        int h = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        for (int start = 0; start < strL - TEN + 1; ++start) {
            if (start != 0) {
                h = h * FOUR - nums[start - 1] * POWER + nums[start + TEN - 1];
            } else {
                for (int i = 0; i < TEN; ++i) {
                    h = h * FOUR + nums[i];
                }
            }
            if (seen.contains(h)) {
                output.add(s.substring(start, start + TEN));
            }
            seen.add(h);
        }
        return new ArrayList<>(output);
    }

    private int rehash(int oldHash, String s, int i) {
        int pow = (int) Math.pow(4.0, DNA_SEQUENCE);
        int val1 = oldHash -  CHAR_TO_INT.get(s.charAt(i - 1)) +  CHAR_TO_INT.get(s.charAt(i + DNA_SEQUENCE - 1))* pow;

        return val1 / 4;
    }
}
