package stack.NextGreaterElementI_496;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] res1 = s.nextGreaterElement2(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}); // [-1,3,-1]
        int[] res2 = s.nextGreaterElement2(new int[]{2, 4}, new int[]{1,2,3,4}); // [3,-1]
        System.out.println(Arrays.toString(res1));
        System.out.println(Arrays.toString(res2));
    }
}
