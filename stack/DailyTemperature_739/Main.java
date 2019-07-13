package stack.DailyTemperature_739;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] result = s.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73}); // [1,1,4,2,1,1,0,0]

        System.out.println(Arrays.toString(result));
    }
}
