package binarySearch.medium;

// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

/**
 * M [marked]
 A conveyor belt has packages that must be shipped from one port to another within D days.
 The i-th package on the conveyor belt has a weight of weights[i].
 Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 We may not load more weight than the maximum weight capacity of the ship.
 Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.

 Example 1:
 Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 Output: 15
 Explanation:
 A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 1st day: 1, 2, 3, 4, 5
 2nd day: 6, 7
 3rd day: 8
 4th day: 9
 5th day: 10

 Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages
 into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

 Example 2:
 Input: weights = [3,2,2,4,1,4], D = 3
 Output: 6
 Explanation:
 A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 1st day: 3, 2
 2nd day: 2, 4
 3rd day: 1, 4

 Example 3:
 Input: weights = [1,2,3,1,1], D = 4
 Output: 3
 Explanation:
 1st day: 1
 2nd day: 2
 3rd day: 3
 4th day: 1, 1

 Note:
 1 <= D <= weights.length <= 50000
 1 <= weights[i] <= 500
 */
public class CapacityToShipPackagesWithinDDays_1011 {

    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays_1011 s = new CapacityToShipPackagesWithinDDays_1011();
        System.out.println(s.shipWithinDays(new int[] {1,2,3,4,5,6,7,8,9,10}, 5)); // 15;
        System.out.println(s.shipWithinDays(new int[] {3,2,2,4,1,4}, 3)); //6
        System.out.println(s.shipWithinDays(new int[] {1,2,3,1,1}, 4)); //3
    }

    // O(n + n*log(sum(n)-max(n))) - time, O(1) - space
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }

        while (left < right) {
            int mid = (left + right)/2;
            if (isPossible(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isPossible(int[] weights, int d, int capacity) {
        int curwWeight = 0;
        int days = 1;
        for (int i = 0; i < weights.length; i++) {
            if (curwWeight + weights[i] > capacity) {
                curwWeight = weights[i];
                days++;
            } else {
                curwWeight += weights[i];
            }
            if (days > d) {
                return false;
            }
        }
        return true;
    }
}
