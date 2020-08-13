package string.medium;

public class ReverseWordsInString_II_186 {

    public static void main(String[] args) {
        ReverseWordsInString_II_186 s = new ReverseWordsInString_II_186();
        char[] c1 = "a good example".toCharArray(); // example good a
        char[] c2 = "the sky is blue".toCharArray(); // blue is sky the
        char[] c3 = "hello world!".toCharArray(); // world! hello
        s.reverseWords(c1);
        s.reverseWords(c2);
        s.reverseWords(c3);

        System.out.println(String.valueOf(c1)); // example good a
        System.out.println(String.valueOf(c2)); // blue is sky the
        System.out.println(String.valueOf(c3)); // world! hello
    }

    public void reverseWords(char[] s) {
        reverse(0, s.length - 1, s);
        reverseEachWord(s);
    }

    private void reverse(int left, int right, char[] s) {
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    private void reverseEachWord(char[] s) {
        int left = 0;
        int right = 0;
        while (right < s.length) {
            while (right < s.length && s[right] != ' ') {
                right++;
            }
            reverse(left, right - 1, s);
            left = right + 1;
            right++;
        }
    }
}
