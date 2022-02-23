package dynamic.medium;

/** M
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * Example 1:
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 *
 * Example 2:
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 *
 * Example 3:
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 * Constraints:
 *     1 <= cardPoints.length <= 105
 *     1 <= cardPoints[i] <= 104
 *     1 <= k <= cardPoints.length
 */
public class MaximumPointsYouCanObtainFromCards_1423 {

    public static void main(String[] args) {
        MaximumPointsYouCanObtainFromCards_1423 s = new MaximumPointsYouCanObtainFromCards_1423();
        System.out.println(s.maxScore2(new int[]{1,2,3,4,5,6,1}, 3)); //12
        System.out.println(s.maxScore(new int[]{2,2,2}, 2)); //4
        System.out.println(s.maxScore(new int[]{9,7,7,9,7,7,9}, 7)); //55
    }

    // O(n^2) - time, space
    public int maxScore(int[] cardPoints, int k) {
        int[][] dp = new int[cardPoints.length][cardPoints.length];

        return helper(cardPoints, k, 0, cardPoints.length-1, 0, dp);
    }

    // O(k) - time, O(k) - space
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] frontSetOfCards = new int[k + 1];
        int[] rearSetOfCards = new int[k + 1];
        for (int i = 0; i < k; i++) {
            frontSetOfCards[i + 1] = cardPoints[i] + frontSetOfCards[i];
            rearSetOfCards[i + 1] = cardPoints[n - i - 1] + rearSetOfCards[i];
        }

        int maxScore = 0;
        // Each i represents the number of cards we take from the front.
        for (int i = 0; i <= k; i++) {
            int currentScore = frontSetOfCards[i] + rearSetOfCards[k - i];
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

    private int helper(int[] cardPoints, int k, int i, int j, int sum, int[][] dp) {
        if (i > j || k == 0) {
            return sum;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int left = helper(cardPoints, k-1, i+1, j, sum + cardPoints[i], dp);
        int right = helper(cardPoints, k-1, i, j-1, sum + cardPoints[j], dp);

        dp[i][j] = Math.max(left, right);

        return dp[i][j];
    }
}
