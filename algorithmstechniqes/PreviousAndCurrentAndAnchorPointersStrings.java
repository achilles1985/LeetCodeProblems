package algorithmstechniqes;

public class PreviousAndCurrentAndAnchorPointersStrings {

    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00")); //0
        System.out.println(countBinarySubstrings("00110011")); //6
        System.out.println(countBinarySubstrings("10101")); //4

        System.out.println(compress(new char[] {'a', 'a', 'a', 'a', 'b', 'b', 'c', 'c', 'c'})); //6
        System.out.println(compress(new char[] {'a'})); //1
        System.out.println(compress(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'})); //4
    }

    //CountBinarySubstrings_696.
    // O(n) - time, O(1) - space
    private static int countBinarySubstrings(String s) {
        int ans = 0, prev = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans + Math.min(prev, cur);
    }

    // StringCompression_443
    // O(n) - time, O(1) - space
    private static int compress(char[] chars) {
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
