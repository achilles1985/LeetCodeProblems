package bitManipulation.FindDifference_389;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findTheDifference("abcd", "abcde")); // e
        System.out.println(s.findTheDifference("aaaaa", "aaafaa")); // f
        System.out.println(s.findTheDifference("zzaaaa", "zzbaaaa")); // b
    }
}
