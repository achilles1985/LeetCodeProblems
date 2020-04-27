package algorithmstechniqes;

/**
 * Given an array of integers of size ‘n’.
 Our aim is to calculate the maximum sum of ‘k’
 consecutive elements in the array.

 Input  : arr[] = {100, 200, 300, 400}
 k = 2
 Output : 700

 Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
 k = 4
 Output : 39
 We get maximum sum by adding subarray {4, 2, 10, 23}
 of size 4.
 */
public class WindowSlidingTechnique {

    public static void main(String[] args) {
        System.out.println(bruteForce(new int[] {1, 4, 2, 10, 23, 3, 1, 0, 20}, 4)); // 39
        System.out.println(windowSliding(new int[] {1, 4, 2, 10, 23, 3, 1, 0, 20}, 4)); // 39
    }

    // O(n^2) - time, O(1) - space
    private static int bruteForce(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length - k; i++) {
            int curSum = 0;
            for (int j = 0; j < k; j++) {
                curSum += arr[i+j];
            }
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    private static int windowSliding(int[] arr, int k) {
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int curSum = maxSum;
        for (int i = k; i < arr.length; i++) {
            curSum = curSum + arr[i] - arr[i-k];
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

}
