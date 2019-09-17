package string;

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
