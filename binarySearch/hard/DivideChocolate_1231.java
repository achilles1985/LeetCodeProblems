package binarySearch.hard;

/**
 * H
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 * <p>
 * Example 1:
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * <p>
 * Example 2:
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * <p>
 * Example 3:
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 * <p>
 * Constraints:
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 */
public class DivideChocolate_1231 {

    public static void main(String[] args) {
        DivideChocolate_1231 s = new DivideChocolate_1231();
        System.out.println(s.maximizeSweetness(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 5)); //6
        System.out.println(s.maximizeSweetness(new int[]{5, 6, 7, 8, 9, 1, 2, 3, 4}, 8)); //1
        System.out.println(s.maximizeSweetness(new int[]{1, 2, 2, 1, 2, 2, 1, 2, 2}, 2)); //5
    }

    // O(log(n) * n) - time, O(1) - space
    public int maximizeSweetness(int[] sweetness, int K) {
        int min = sweetness[0];
        int max = 0;
        for (int i = 0; i < sweetness.length; i++) {
            max += sweetness[i];
            min = Math.min(min, sweetness[i]);
        }
        int left = min, right = max;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canSplitChocolateWithAtLeastKCut(sweetness, mid, K + 1)) {
                // we can split chocolate with at least K+1 friends (K cuts), mid could be answer
                // but try to get more closer to K by increasing mid
                result = mid; // since mid could be answer but we don't to stuck on loop
                left = mid + 1;
            } else {
                // we can not split chocolate with at least K+1 friends, mid is high, lower that
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean canSplitChocolateWithAtLeastKCut(int[] sweetness, int minSweetness, int K) {
        int current = 0;
        int friends = 0;
        for (int sweet : sweetness) {
            current = current + sweet;
            if (current >= minSweetness) {
                current = 0;
                friends++;
            }
        }
        return friends >= K;
    }

}
