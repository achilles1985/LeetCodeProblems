package bfs;

import java.util.*;

/** H
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *     For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target.
 * You can travel between bus stops by buses only.
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 * Example 1:
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Example 2:
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 * Constraints:
 *     1 <= routes.length <= 500.
 *     1 <= routes[i].length <= 105
 *     All the values of routes[i] are unique.
 *     sum(routes[i].length) <= 105
 *     0 <= routes[i][j] < 106
 *     0 <= source, target < 106
 */
public class BusRoutes_815 {

    public static void main(String[] args) {
        BusRoutes_815 s = new BusRoutes_815();
        System.out.println(s.numBusesToDestination(new int[][]{{1,2,7},{3,6,7}},1,6)); //2
        System.out.println(s.numBusesToDestination(new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}},15,12)); //-1
    }

    // O(n^2) - time, space
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int stop = routes[i][j];
                graph.computeIfAbsent(stop, (key)->new HashSet<>()).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.add(source);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int poll = queue.poll();
                if (poll == target) {
                    return level;
                }
                for (Integer nextBus: graph.getOrDefault(poll, Collections.emptySet())) {
                    if (seen.contains(nextBus)) {
                        continue;
                    }
                    seen.add(nextBus);
                    for (int nextStop = 0; nextStop < routes[nextBus].length; nextStop++) {
                        queue.add(routes[nextBus][nextStop]);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
