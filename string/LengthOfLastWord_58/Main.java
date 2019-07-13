package string.LengthOfLastWord_58;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLastWord("Hello world")); // 5
        System.out.println(s.lengthOfLastWord("Hello world my")); // 2
        System.out.println(s.lengthOfLastWord(" Hello world my ")); // 2
        System.out.println(s.lengthOfLastWord("Hello")); // 5
        System.out.println(s.lengthOfLastWord("Hellooooooo me")); // 2
        System.out.println(s.lengthOfLastWord("a")); // 1
    }
}
