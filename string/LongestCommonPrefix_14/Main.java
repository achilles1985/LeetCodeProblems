package string.LongestCommonPrefix_14;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestCommonPrefix3(new String[] {"a", "a"})); // "a"
        System.out.println(s.longestCommonPrefix3(new String[] {"flower","flow","flight"})); // fl
        System.out.println(s.longestCommonPrefix3(new String[] {"dog","racecar","car"})); // ""
        System.out.println(s.longestCommonPrefix3(new String[] {"dog","","dog"})); // ""
        System.out.println(s.longestCommonPrefix3(new String[] {""})); // ""
        //System.out.println(s.longestCommonPrefix3(new String[] {null})); // ""
        System.out.println(s.longestCommonPrefix3(new String[] {"b"})); // "b"
    }
}
