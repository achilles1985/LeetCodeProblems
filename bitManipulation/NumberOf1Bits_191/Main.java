package bitManipulation.NumberOf1Bits_191;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{2,5,1};
        int mask = 0;
        for (int i = 0; i < arr.length; i++) {
            mask = mask | (1 << arr[i]);
        }

        Solution s = new Solution();
        System.out.println(s.hammingWeight(11)); // 3
        System.out.println(s.hammingWeight(128)); // 1
        System.out.println(s.hammingWeight(-3)); // 31
    }
}
