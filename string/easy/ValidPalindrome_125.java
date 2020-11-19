package string.easy;

public class ValidPalindrome_125 {

    public static void main(String[] args) {
        ValidPalindrome_125 s = new ValidPalindrome_125();
        System.out.println(s.isPalindrome(".,"));
    }

    // O(n) - time, O(1) - space
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int left = 0, right = s.length()-1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))  {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
