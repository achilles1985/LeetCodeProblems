package stack.EvaluateReversePolishNotation_150;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.evalRPN(new String[] { "2", "1", "+", "3", "*" })); // 9
        System.out.println(s.evalRPN(new String[] { "4", "13", "5", "/", "+" })); // 6
        System.out.println(s.evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" })); // 22
    }
}
