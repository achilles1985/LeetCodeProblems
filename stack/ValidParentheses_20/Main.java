package stack.ValidParentheses_20;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("()")); // true
        System.out.println(s.isValid("()[]{}")); // true
        System.out.println(s.isValid("(}")); // false
        System.out.println(s.isValid("(])]")); // false
        System.out.println(s.isValid("{[]}")); // true
    }
}
