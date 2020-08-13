package utils;

public class TestStrings {

    public static void main(String[] args) {
        // StringBuilder
        StringBuilder sb = new StringBuilder("abcde");
        System.out.println(sb.charAt(0)); // a
        System.out.println(sb.indexOf("cd")); // 2

        sb.insert(2, "xx");
        System.out.println(sb.toString()); //abxxcde

        sb.deleteCharAt(0);
        System.out.println(sb.toString()); // bxxcde

        System.out.println(sb.reverse()); // edcxxb

        char[] chars = String.valueOf(12345).toCharArray(); // ['1','2','3','4','5']
    }
}
