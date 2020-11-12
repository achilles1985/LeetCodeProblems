package assessments.zalando;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        Test1 s = new Test1();
        System.out.println(s.solution2(new int[]{0,4,-1,0,3}, new int[]{0,-2,5,0,3}));
        System.out.println(s.solution2(new int[]{4, -1, 0, 3}, new int[]{-2, 6, 0, 4}));
        System.out.println(s.solution3(3,2, new int[]{2,1,1,0,1}));
    }

    public String solution3(int U, int L, int[] C) {
        int colSum = 0;
        for (int i = 0; i < C.length; i++) {
            colSum += C[i];
        }
        if (U + L != colSum) {
            return "IMPOSSIBLE";
        }
        StringBuilder upper = new StringBuilder();
        StringBuilder lower = new StringBuilder();
        for (int col: C) {
            if (col == 0) {
                upper.append("0");
                lower.append("0");
            } else if (col == 2) {
                upper.append("1");
                lower.append("1");
                U--;
                L--;
            } else {
                if (U > 0) {
                    upper.append("1");
                    lower.append("0");
                    U--;
                } else {
                    upper.append("0");
                    lower.append("1");
                    L--;
                }
            }
        }
        return upper.toString() + "," + lower.toString();
    }

    public int solution2(int[] A, int[] B) {
        List<Integer> indexesA = findFairIndex(A);
        List<Integer> indexesB = findFairIndex(B);
        indexesA.retainAll(indexesB);

        return indexesA.size();
    }

    private List<Integer> findFairIndex(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] prefixSum = calculatePrefixSum(nums);
        for (int i = 1; i < nums.length - 1; i++) {
            if (prefixSum[i] == (prefixSum[nums.length - 1] - prefixSum[i])) {
                result.add(i);
            }
        }
        return result;
    }

    private int[] calculatePrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        return prefixSum;
    }

    public int solution1(int N) {
        int originSum = sum(N);
        for (int nextNumber = N+1; nextNumber <= 50000; nextNumber++) {
            if (sum(nextNumber) == originSum) {
                return nextNumber;
            }
        }
        return 0;
    }

    private int sum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number%10;
            number /= 10;
        }
        return sum;
    }
}