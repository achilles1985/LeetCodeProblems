package array.MaxConsecutiveOnes_485;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int max = s.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1});
        System.out.println(max);
    }
}
