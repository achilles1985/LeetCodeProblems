package string.easy;

/** E
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInString_387 {

    public static void main(String[] args) {
        FirstUniqueCharacterInString_387 s = new FirstUniqueCharacterInString_387();
        System.out.println(s.firstUniqChar("leetcode")); //0
        System.out.println(s.firstUniqChar("loveleetcode")); //2
        System.out.println(s.firstUniqChar("lloo")); //-1
    }

    // O(n) - time, O(1) - space
    public int firstUniqChar(String s) {
        char[] chars = new char[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
