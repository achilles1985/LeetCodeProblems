package stack.MinimumAddToMakeParenthesesValid_921;

public class Main {

    public static void main(String[] args) {
        Solution s  = new Solution();
        System.out.println(s.minAddToMakeValid("(()")); // 1
        System.out.println(s.minAddToMakeValid("(((")); // 3
        System.out.println(s.minAddToMakeValid("()")); // 0
        System.out.println(s.minAddToMakeValid("()))((")); // 4
    }
}
