package dynamic.hard;

/** H
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:
 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.

 Example 1:
 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".

 Example 2:
 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

 Example 3:
 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".

 Example 4:
 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

 Example 5:
 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false
 */
public class RegularExpressionMatching_10 {

    public static void main(String[] args) {
        RegularExpressionMatching_10 s = new RegularExpressionMatching_10();

        System.out.println(s.isMatch("a", "")); // false
        System.out.println(s.isMatch("aa", "c*a*")); // true
        System.out.println(s.isMatch("ab", ".*")); // true
        System.out.println(s.isMatch("abc", "a*")); // false
        System.out.println(s.isMatch("abc", "a*b*c*")); // true

        System.out.println(s.isMatch("aab", "c*a*b")); // true
        System.out.println(s.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(s.isMatch("ab", ".*")); // true
        System.out.println(s.isMatch("aa", "a*")); // true

        System.out.println(s.isMatch("Tushar","Tushar"));
        System.out.println(s.isMatch("Tusha","Tushar*a*b*"));
        System.out.println(s.isMatch("","a*b*"));
        System.out.println(s.isMatch("abbbbccc","a*ab*bbbbc*"));
        System.out.println(s.isMatch("abbbbccc","aa*bbb*bbbc*"));
        System.out.println(s.isMatch("abbbbccc",".*bcc"));
        System.out.println(s.isMatch("abbbbccc",".*bcc*"));
        System.out.println(s.isMatch("abbbbccc",".*bcc*"));
        System.out.println(s.isMatch("aaa","ab*a*c*a"));
    }

    public boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean[][] m = new boolean[text.length + 1][pattern.length + 1];
        m[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < m[0].length; i++) {
            if (pattern[i-1] == '*') {
                m[0][i] = m[0][i - 2];
            }
        }

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    m[i][j] = m[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    m[i][j] = m[i][j - 2];
                    if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                        m[i][j] = m[i][j] | m[i - 1][j];
                    }
                } else {
                    m[i][j] = false;
                }
            }
        }

        return m[text.length][pattern.length];
    }

    // Recursive approach
    public boolean matchRegexRecursive(char[] str, char[] pattern){
        return matchRegexRecursive(str,pattern,0,0);
    }

    private boolean matchRegexRecursive(char text[], char pattern[], int pInput, int pPattern){
        //if pPattern has reached end of pattern means pPattern should also reach end of text for match
        //to happen
        if(pPattern == pattern.length) {
            return pInput == text.length;
        }

        //if next character is not * means either current value at pattern and text should be same
        //or current value at pattern should be . in which case you can skip one character of text
        if(pPattern == pattern.length - 1 || pattern[pPattern+1] != '*') {
            return (pInput < text.length && (text[pInput] == pattern[pPattern] || pattern[pPattern] == '.'))
                    && matchRegexRecursive(text, pattern, pInput+1, pPattern+1);
        }

        //if we have case like abc and ad*bc so here we totally skip d*
        if(matchRegexRecursive(text, pattern, pInput, pPattern+2)){
            return true;
        }

        //For case like abbc and ab*c match first b with b* and then next b to c. If that does not work out
        //then try next b with b* and then c with c and so on.
        //if pattern current val is . then skip one character at time from text till we either reach end of text
        //or a match is found
        while(pInput < text.length && (text[pInput] == pattern[pPattern] || pattern[pPattern] == '.')){
            if( matchRegexRecursive(text, pattern, pInput+1, pPattern+2)){
                return true;
            }
            pInput++;
        }
        return false;
    }

}
