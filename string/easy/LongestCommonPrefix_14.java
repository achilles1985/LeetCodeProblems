package string.easy;

/** E
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Note:
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix_14 {

    public static void main(String[] args) {
        LongestCommonPrefix_14 s = new LongestCommonPrefix_14();
        System.out.println(s.longestCommonPrefix(new String[] {"dog","racecar","car"})); //

        System.out.println(s.longestCommonPrefixBS(new String[] {"aa","a"})); // a
        System.out.println(s.longestCommonPrefixBS(new String[] {"flower","flow","flight"})); // fl
        System.out.println(s.longestCommonPrefixBS(new String[] {"reflower","flow","flight"})); //
    }

    // O(min(strs)*strs.length) - time, O(1) - space
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    // O(s*log(m)) - time, s - sum of all chars in strings. In the worst case we have nnn equal strings with length m.
    public String longestCommonPrefixBS(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }
}
