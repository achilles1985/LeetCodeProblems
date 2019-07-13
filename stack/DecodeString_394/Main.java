package stack.DecodeString_394;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("3[a]2[bc], decoded = " + s.decodeString("3[a]2[bc]"));
        System.out.println("3[a2[c]], decoded = " + s.decodeString("3[a2[c]]"));
        System.out.println("2[abc]3[cd]ef, decoded = " + s.decodeString("2[abc]3[cd]ef"));
        System.out.println("2[2[2[a]]], decoded = " + s.decodeString("2[2[2[a]]]"));
    }
}
