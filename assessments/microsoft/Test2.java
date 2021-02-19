package assessments.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {

    public static void main(String[] args) {
        Test2 s = new Test2();
        System.out.println(s.task2(5, new int[]{2,2,1,2}, new int[]{1,3,4,4})); //31
    }

    // Minimum Increment and Decrement operations to make array elements equal
    public int task1BF(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                sum += Math.abs(A[i] - A[j]);
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    // Minimum Increment and Decrement operations to make array elements equal
    public int task1(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;

        Arrays.sort(A);
        int midEven = A[len/2];
        int midOdd = A[(len/2)-1];

        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i < len; i++) {
            sum1 = sum1 + Math.abs(A[i] - midEven);
            sum2 = sum2 + Math.abs(A[i] - midOdd);
        }
        return Math.min(sum1, sum2);
    }

    // Find max sum of edges weight which are destibuted manually
    // Incorrect
    public int task2(int N, int[] A, int[] B) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            edges.add(new int[]{A[i],B[i]});
        }
        Map<Integer,Integer> nodeToValue = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            if (!nodeToValue.containsKey(from)) {
                nodeToValue.put(from, N--);
            }
            if (!nodeToValue.containsKey(to)) {
                nodeToValue.put(to, N--);
            }
        }
        int sum = 0;
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            sum += nodeToValue.getOrDefault(from,0);
            sum += nodeToValue.getOrDefault(to,0);
        }

        return sum;
    }

    // task3, Pairing elements to obtain maximum number of even sums
    // https://stackoverflow.com/questions/63279498/pairing-elements-to-obtain-maximum-number-of-even-sums
}
