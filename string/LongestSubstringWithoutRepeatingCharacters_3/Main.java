package string.LongestSubstringWithoutRepeatingCharacters_3;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring2("abcabcbb")); // 3 (abc)
        System.out.println(s.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(s.lengthOfLongestSubstring("pwwkew")); // 3 (wke)
    }
}
