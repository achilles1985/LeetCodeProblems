package intervals;

import java.util.Map;
import java.util.TreeMap;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 ¥
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 * Example 3:
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 *
 * Example 4:
 * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * Output: true

 * Constraints:
 *     1 <= trips.length <= 1000
 *     trips[i].length == 3
 *     1 <= numPassengersi <= 100
 *     0 <= fromi < toi <= 1000
 *     1 <= capacity <= 105
 */
public class CarPooling_1094 {
    public static void main(String[] args) {
        CarPooling_1094 s = new CarPooling_1094();
        System.out.println(s.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 4)); //false
        System.out.println(s.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 5)); //true
    }

    // O(n*log(n)) - time, O(n) - space
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new TreeMap<>();
        for (int[] trip : trips) {
            int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
            timestamp.put(trip[1], startPassenger);

            int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
            timestamp.put(trip[2], endPassenger);
        }
        int usedCapacity = 0;
        for (int passengerChange : timestamp.values()) {
            usedCapacity += passengerChange;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }

    // bucket sort
    // O(10001*n) = O(n) - time, O(1001) = O(1) - space
}
