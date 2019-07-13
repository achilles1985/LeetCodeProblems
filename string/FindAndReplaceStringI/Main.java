package string.FindAndReplaceStringI;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.replaceAndRemove("acdbbca")); // ddcdcdd
        //System.out.println(s.replaceAndRemove("aaa")); // dddddd - not valid case since size must stay unchanged
    }
}
