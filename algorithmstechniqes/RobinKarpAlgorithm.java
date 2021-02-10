package algorithmstechniqes;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * https://medium.com/@harsha444/rabin-karp-algorithm-for-pattern-searching-explained-with-example-7dcdfa6b1c64, based on rolling hash
 https://leetcode.com/problems/longest-duplicate-substring/solution/
 */

public class RobinKarpAlgorithm {

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(0);
        RobinKarpAlgorithm s = new RobinKarpAlgorithm();
        System.out.println(s.patternSearchBF("ro", "robinkarp")); // 0
        System.out.println(s.patternSearchBF("arp", "robinkarp")); // 6
        System.out.println(s.patternSearchBF("bin", "robinkarp")); // 2
        System.out.println(s.patternSearchBF("lol", "robinkarp")); // -1

        System.out.println(s.patternSearch("ro", "robinkarp")); // 0
        System.out.println(s.patternSearch("arp", "robinkarp")); // 6
        System.out.println(s.patternSearch("bin", "robinkarp")); // 2
        System.out.println(s.patternSearch("lol", "robinkarp")); // -1
    }

    // O(p*t) - time, O(1) - space
    public int patternSearchBF(String pattern, String text) {
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            String sub = text.substring(i,  i + pattern.length());
            if (pattern.equals(sub)) {
                return i;
            }
        }
        return -1;
    }

    // O(p+t) - time, O(1) - space
    public int patternSearch(String pattern, String text) {
        if (pattern == null || pattern.isEmpty()) {
            return 0;
        }
        if (text == null || text.isEmpty() || text.length() < pattern.length()) {
            return -1;
        }
        int pHash = 0;
        // pHash = p[0]*3^0 + p[1]*3^1 + p[2]*s^2
        for (int i = 0; i < pattern.length(); i++) {
            pHash = pHash + (pattern.charAt(i) - 'a') * (int) Math.pow(3.0, i);
        }
        int tHash = 0;
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            // hash of first pLength substr in text
            if (i == 0) {
                for (int j = 0; j < pattern.length(); j++) {
                    tHash = tHash + (text.charAt(j) - 'a') * (int) Math.pow(3.0, j);
                }
            } else {
                //                               first substr char    last substr char
                // rollingHash: tHash = (tHash - text[i - 1] * 3^0      + text[i+patternL-1] * 3^patternL)/3
                tHash = (tHash - (text.charAt(i - 1) - 'a') + (text.charAt(i + pattern.length() - 1) - 'a') * (int) Math.pow(3.0, pattern.length()))/3;
            }
            if (pHash == tHash) {
                return i;
            }
        }
        return -1;
    }

}
