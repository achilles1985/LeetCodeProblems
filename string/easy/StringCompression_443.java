package string.easy;

/**
 * E
 * Given an array of characters, compress it in-place.
 * The length after compression must always be smaller than or equal to the original array.
 * Every element of the array should be a character (not int) of length 1.
 * After you are done modifying the input array in-place, return the new length of the array.
 * <p>
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * <p>
 * Example 1:
 * Input:
 * ["a","a","b","b","c","c","c"]
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * <p>
 * Example 2:
 * Input:
 * ["a"]
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * Explanation:
 * Nothing is replaced.
 * <p>
 * Example 3:
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * <p>
 * Note:
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */
/*
    Mistakes:
    1. Pay attention to array ends while comparing current and prev(next)!
 */
public class StringCompression_443 {

    public static void main(String[] args) {
        StringCompression_443 s = new StringCompression_443();
        System.out.println(s.compress(new char[] {'a', 'a', 'a', 'a', 'b', 'b', 'c', 'c', 'c'})); //6
        System.out.println(s.compress(new char[] {'a'})); //1
        System.out.println(s.compress(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'})); //4
    }

    // O(n) - time, O(1) - space
    public int compress(char[] chars) {
        int write = 0;
        int anchor = 0; // beginning of the sequence
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[anchor]) {
                chars[write++] = chars[read];
                if (read - anchor + 1 > 1) {
                    for (char c : String.valueOf(read - anchor + 1).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }

        return write;
    }

}
