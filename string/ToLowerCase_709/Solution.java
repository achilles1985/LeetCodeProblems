package string.ToLowerCase_709;

public class Solution {

    // O(n) - time
    public String toLowerCase2(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (symbol >= 'A' && symbol <= 'Z') {
                chars[i] = (char) (symbol + ('a' - 'A'));
            }
        }

        return String.valueOf(chars);
    }
}
