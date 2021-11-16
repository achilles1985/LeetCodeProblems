package assessments.janeasystem;

import java.util.*;

public class Task1 {

    // growth in 2 dimensions
    public static long twoDimensions(List<String> upRight) {
        Map<String, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        long count = 1;
        for (String entry: upRight) {
            String[] coordinate = entry.split(" ");
            int rows = Integer.parseInt(coordinate[0]);
            int columns = Integer.parseInt(coordinate[1]);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    String key = i  + "," + j;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    if (map.get(key) > max) {
                        max = map.get(key);
                        count = 1;
                    } else if (map.get(key) == max) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static int balancedSum(List<Integer> arr) {
        if (arr == null || arr.isEmpty()) {
            return -1;
        }
        int size = arr.size();
        int lastIdx = size - 1;
        int[] leftSum = new int[arr.size()];
        leftSum[0] = arr.get(0);
        for (int i = 1; i < arr.size() ; i++) {
            leftSum[i] = leftSum[i-1] + arr.get(i);
        }
        int[] rightSum = new int[arr.size()];
        rightSum[lastIdx] = arr.get(lastIdx);
        for (int i = lastIdx-1; i >= 0 ; i--) {
            rightSum[i] = rightSum[i+1] + arr.get(i);
        }
        for (int i = 0; i < arr.size(); i++) {
            if (leftSum[i] == rightSum[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<String> coors = Arrays.asList( "1 4", "2 3", "4 1");
        System.out.println("The Max and count Are:" + twoDimensions(coors));

        System.out.println(balancedSum(Arrays.asList(3,1,2,1))); //1
    }
}
