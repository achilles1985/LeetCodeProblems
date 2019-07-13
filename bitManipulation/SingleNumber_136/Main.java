package bitManipulation.SingleNumber_136;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.singleNumber(new int[] {1,2,1,2,4})); // 4
        System.out.println(s.singleNumber(new int[] {2,2,4})); // 4
        System.out.println(s.singleNumber(new int[] {2,2,Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3})); // 3
    }
}
