package stack.NextGreaterElementII_503;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] res1 = s.nextGreaterElements(new int[]{1, 2, 1}); // [2,-1,2]
        int[] res2 = s.nextGreaterElements(new int[]{2, 4}); // [4,-1]
        int[] res3 = s.nextGreaterElements(new int[]{1, 3, 4, 2}); // [3, 4,-1,3]
        System.out.println(Arrays.toString(res1));
        System.out.println(Arrays.toString(res2));
        System.out.println(Arrays.toString(res3));
    }
}
