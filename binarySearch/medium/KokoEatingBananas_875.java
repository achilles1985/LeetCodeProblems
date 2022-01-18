package binarySearch.medium;

/** M
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and
 * eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will
 * not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Constraints:
 *     1 <= piles.length <= 104
 *     piles.length <= h <= 109
 *     1 <= piles[i] <= 109
 */
public class KokoEatingBananas_875 {

    public static void main(String[] args) {
        KokoEatingBananas_875 s = new KokoEatingBananas_875();
        System.out.println(s.minEatingSpeed(new int[]{30,11,23,4,20}, 5)); //30
        System.out.println(s.minEatingSpeed(new int[]{3,6,7,11}, 8)); //4
        System.out.println(s.minEatingSpeed(new int[]{30,11,23,4,20}, 6)); //23
    }
    // O(n*m) - time, BF, where n - length of piles array, m - max possible speed

    // O(n*log(range)) - time, O(1) - space
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile: piles) {
            max = Math.max(max, pile);
        }
        int left = 1, right = max;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (isPossible(mid, piles, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isPossible(int speed, int[] piles, int h) {
        int hourSpent = 0;
        for (int pile: piles) {
            hourSpent += Math.ceil((double) pile / speed);
        }
        return hourSpent <= h;
    }
}
