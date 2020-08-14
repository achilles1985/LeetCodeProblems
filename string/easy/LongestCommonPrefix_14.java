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
        System.out.println(s.longestCommonPrefix(new String[] {"aa","a"})); // a
        System.out.println(s.longestCommonPrefix(new String[] {"flower","flow","flight"})); // fl
        System.out.println(s.longestCommonPrefix(new String[] {"dog","racecar","car"})); //
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
}
