package string.NextGreaterElement_III_556;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.nextGreaterElement(3254)); // 3425
        System.out.println(s.nextGreaterElement(2543)); // 5432
        System.out.println(s.nextGreaterElement(21)); // -1
        System.out.println(s.nextGreaterElement(12)); // 21
        System.out.println(s.nextGreaterElement(1_999_999_999)); // 21
    }
}
