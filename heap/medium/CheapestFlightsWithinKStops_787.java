package heap.medium;

import java.util.*;

/** M [marked]
 There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 Now given all the cities and flights, together with starting city src and the destination dst,
 your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200
 Explanation:
 The graph looks like this:
        0
   100/  \500
     /    \
    1--100-2

 The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500
 Explanation:
 The graph looks like this:

 The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

 Note:
 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops_787 {

    public static void main(String[] args) {
        CheapestFlightsWithinKStops_787 s = new CheapestFlightsWithinKStops_787();
        System.out.println(s.findCheapestPrice(3, new int[][] {{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 0)); // 500
        System.out.println(s.findCheapestPrice(3, new int[][] {{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 1)); // 200
        System.out.println(s.findCheapestPriceDynamic(3, new int[][] {{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 1)); // 200

    }


    // O(E + V*log(V)) - time, E - number of flights, V - number of cities, O(E+V) - space (Dijkastra algorithm)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int stop) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight: flights) { //O(E)
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            graph.computeIfAbsent(from, f -> new HashMap<>()).put(to, price);
        }

        Queue<Item> heap = new PriorityQueue<>((i1, i2) -> i1.cost-i2.cost);
        heap.add(new Item(src, 0, 0));
        while (!heap.isEmpty()) {
            Item popped = heap.poll();
            if (popped.node == dst) {
                return popped.cost;
            }
            Map<Integer, Integer> adjacents = graph.getOrDefault(popped.node, new HashMap<>());
            for (int adjacent: adjacents.keySet()) {
                if (popped.stopsSoFar <= stop) {
                    heap.add(new Item(adjacent, popped.cost + adjacents.get(adjacent), popped.stopsSoFar+1));
                }
            }
        }

        return -1;
    }

    // O(E + V*log(V)) - time, E - number of flights, V - number of cities, O(E+V) - space (Dijkastra algorithm)
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: flights) {
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, key -> new ArrayList<>()).add(new int[]{to, edge[2]});
        }

        Queue<int[]> minHeap = new PriorityQueue<>((e1,e2) -> e1[1] - e2[1]);
        minHeap.add(new int[]{src,0,0});
        while (!minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            if (polled[0] == dst) {
                return polled[1];
            }
            for (int[] child: graph.getOrDefault(polled[0], Collections.emptyList())) {
                if (polled[2] <= k) {
                    minHeap.add(new int[]{child[0], child[1] + polled[1], polled[2] +1});
                }
            }
        }
        return -1;
    }

    // O(K*flights) - time, space
    public int findCheapestPriceDynamic(int n, int[][] flights, int src, int dst, int K) {
        long[][] dp = new long[K+2][n];
        for (long[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for (int i = 1; i < K+2; i++) {
            dp[i][src] = 0;
            for (int[] f : flights) {
                dp[i][f[1]] = Math.min(dp[i][f[1]], dp[i-1][f[0]] + f[2]);
            }
        }
        return dp[K+1][dst] == Integer.MAX_VALUE ? -1 : (int)dp[K+1][dst];
    }

    private static class Item {
        int node;
        int cost;
        int stopsSoFar;

        public Item(int node, int cost, int stopsSoFar) {
            this.node = node;
            this.cost = cost;
            this.stopsSoFar = stopsSoFar;
        }
    }

}
