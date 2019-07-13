package bitManipulation.BitHacks;

public class IsUniqueCharacter {

    public static void main(String[] args) {
        System.out.println(isUniqueChars("zbcdeefg")); //false
        System.out.println(isUniqueChars("abcdefgh")); //true
    }

    private static boolean isUniqueChars(String str) {
        // keeps 1 shift depending on diff
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int diff = str.charAt(i) - 'a';
            if ((checker & (1 << diff)) > 0) {
                return false;
            }

            checker |= 1 << diff;
        }

        return true;
    }
}
