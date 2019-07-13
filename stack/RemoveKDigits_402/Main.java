package stack.RemoveKDigits_402;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        String res1 = s.removeKdigits("1432219", 3); // 1219
        String res2 = s.removeKdigits("10200", 1); // 200
        String res3 = s.removeKdigits("10", 2); // 0
        String res4 = s.removeKdigits("12345", 3); // 12
        String res5 = s.removeKdigits("54321", 3); // 21

        System.out.println("1432219=" + res1);
        System.out.println("10200=" + res2);
        System.out.println("10=" + res3);
        System.out.println("12345=" + res4);
        System.out.println("54321=" + res5);
    }
}
