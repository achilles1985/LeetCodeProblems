package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkastrasAlgorithms {

    public Map<Node, Integer> findAllShortestPaths(Node start) {
        Map<Node, Integer> paths = new HashMap<>();
        Queue<Node> heap = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        Set<Node> visited = new HashSet<>();

        start.weight = 0;
        heap.add(start);
        visited.add(start);

        while (!heap.isEmpty()) {
            Node current = heap.poll();
            paths.put(current, current.weight);
            for (Edge edge: current.edges) {
                Node adjacent = current.getAdjacentByEdge(edge);
                if (!visited.contains(adjacent)) {
                    int newWeight = current.weight + edge.distance;
                    if (adjacent.weight > newWeight) {
                        adjacent.weight = newWeight;
                        heap.add(adjacent);
                    }
                }
            }
            visited.add(current);
        }

        return paths;
    }

    public int[] findAllShortestPaths(int[][] graph, int start) {
        int size = graph.length;
        int[] weights = new int[size];
        Arrays.fill(weights, Integer.MAX_VALUE);
        boolean[] visited = new boolean[size];

        Queue<int[]> heap = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        weights[start] = 0;
        visited[start] = true;
        heap.add(new int[] {start, weights[start]});

        while (!heap.isEmpty()) {
            int[] pair = heap.poll();
            int vertex = pair[0];
            for (int i = 0; i < graph.length; i++) {
                if (!visited[i]) {
                    int newWeight = weights[vertex] + graph[vertex][i];
                    if (graph[vertex][i] != 0 && weights[i] > newWeight) {
                        weights[i] = newWeight;
                        heap.add(new int[] {i, newWeight});
                    }
                }
            }
            visited[vertex] = true;
        }

        return weights;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.addEdge(1,2,4);
        graph.addEdge(1,3,5);
        graph.addEdge(1,4,6);
        graph.addEdge(2,3,2);
        graph.addEdge(2,5,9);
        graph.addEdge(3,4,4);
        graph.addEdge(3,6,1);
        graph.addEdge(4,6,5);
        graph.addEdge(5,6,3);

        int[][] matrix = new int[][] {
                {0,4,5,6,0,0},
                {4,0,2,0,9,0},
                {5,2,0,4,0,1},
                {6,0,4,0,0,5},
                {0,9,0,0,0,3},
                {0,0,1,5,3,0}};

        DijkastrasAlgorithms algorithms = new DijkastrasAlgorithms();
        algorithms.findAllShortestPaths(graph.nodes.get(1));
        algorithms.findAllShortestPaths(matrix, 0);
    }
}
