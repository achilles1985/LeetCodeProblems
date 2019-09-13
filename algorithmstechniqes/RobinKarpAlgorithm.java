package algorithmstechniqes;

/**
 * https://medium.com/@harsha444/rabin-karp-algorithm-for-pattern-searching-explained-with-example-7dcdfa6b1c64, based on rolling hash
 */
public class RobinKarpAlgorithm {

    public static void main(String[] args) {
        RobinKarpAlgorithm s = new RobinKarpAlgorithm();
        System.out.println(s.patternSearch("arp", "robinkarp")); // true
        System.out.println(s.patternSearch("bin", "robinkarp")); // true
        System.out.println(s.patternSearch("lol", "robinkarp")); // false
        System.out.println(s.patternSearch("ro", "robinkarp")); // true
    }

    // O(n) - time, O(1) - space
    public boolean patternSearch(String pattern, String text) {
        int patternHash = newHash(pattern, 0, pattern.length());
        int textHash = newHash(text, 0, pattern.length());
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (patternHash == textHash) {
                for (int j = 0; j < pattern.length(); j++) {
                    if (pattern.charAt(j) != text.charAt(i+j)) {
                        return false;
                    }
                }
                return true;
            }
            if (i + pattern.length() <= text.length()-1) {
                textHash = rollingHash(text, textHash, i, pattern.length());
            }
        }

        return false;
    }

    // val1 = text[0]*3^0 + text[1]*3^1 + text[2]*3^2
    // val2 = text[1]*3^1 + text[2]*3^2 - text[0]*3^0 + text[3]*3^3)
    // val3 = val2/3, where 3 - prime number
    private int rollingHash(String text, int oldTextHash, int start, int patternLength) {
        int val1 = oldTextHash - (text.charAt(start)-'0');
        int val2 = val1 + (text.charAt(start + patternLength)-'0')*(int)Math.pow(3, patternLength);
        int newHash = val2/3;
        return newHash;
    }

    // text[0]*3^0 + text[1]*3^1 + text[2]*3^2 ... + text[i]*3^i
    private int newHash(String str, int start, int end) {
        int hash = 0;
        for (int i = start; i < end; i++) {
            hash += (str.charAt(i)-'0')*Math.pow(3, i);
        }
        return hash;
    }
}
