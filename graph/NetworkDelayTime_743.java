package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/** M
 There are N network nodes, labelled 1 to N.
 Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 Output: 2

 Note:
 N will be in the range [1, 100].
 K will be in the range [1, N].
 The length of times will be in the range [1, 6000].
 All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
/*
 Use Dijkstra's algorithm
 */
public class NetworkDelayTime_743 {

    public static void main(String[] args) {
        NetworkDelayTime_743 s = new NetworkDelayTime_743();
        System.out.println(s.networkDelayTime(new int[][] {{1,2,1}}, 2, 2)); // 2
        System.out.println(s.networkDelayTime(new int[][] {{1,2,1},{2,1,3}}, 2, 2)); // 2
        System.out.println(s.networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}}, 4, 2)); // 2
    }

    // O(n*log(n)) - time for heap implementation, O(n) - space, n - number of nodes
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) {
            return -1;
        }

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            int v1 = edge[0];
            int v2 = edge[1];
            int weight = edge[2];
            graph.computeIfAbsent(v1, key -> new ArrayList<>()).add(new int[] {v2, weight});
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> weights = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            weights.put(i, Integer.MAX_VALUE);
        }

        Queue<int[]> heap = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        heap.add(new int[] {K, 0});
        weights.put(K, 0);
        while (!heap.isEmpty()) {
            int[] pair = heap.poll();
            int currVertex = pair[0];
            int currWeight = pair[1];
            if (!graph.containsKey(currVertex) ) {
                continue;
            }
            for (int[] adjacent: graph.get(currVertex)) {
                int childVertex = adjacent[0];
                int distance = adjacent[1];
                if (!visited.contains(childVertex) && weights.containsKey(childVertex)) {
                    int newWeight = currWeight + distance;
                    if (weights.get(childVertex) > newWeight) {
                        weights.put(childVertex, newWeight);
                        heap.add(new int[] {childVertex, newWeight});
                    }
                }
            }
            visited.add(currVertex);
        }

        int max = -1;
        for (int weight: weights.values()) {
            max = Math.max(max, weight);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

/*    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge: times) {
            List<int[]> pair = graph.get(edge[0]);
            if (pair == null) {
                pair = new ArrayList<>();
            }
            pair.add(new int[] {edge[1], edge[2]});
            graph.put(edge[0], pair);
        }

        Queue<int[]> heap = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        heap.offer(new int[] {K, 0});
        Map<Integer, Integer> destination = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            if (graph.get(curr[0]) != null) {
                for (int[] adj : graph.get(curr[0])) {
                    if (!destination.containsKey(adj[0])) {
                        heap.offer(new int[]{adj[0], adj[1] + curr[1]});
                        destination.put(adj[0], adj[1] + curr[1]);
                    }
                }
            }
        }

        int max = 0;
        for (int weight: destination.values()) {
            max = Math.max(max, weight);
        }

        return max;
    }*/

    public int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }

}
