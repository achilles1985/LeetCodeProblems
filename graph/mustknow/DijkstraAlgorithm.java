package graph.mustknow;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        DijkstraAlgorithm s = new DijkstraAlgorithm();
        System.out.println(s.findShortestPath(0, 5, new int[][]{
                {0,1,4}, {0,2,5},{0,3,6},
                {1,2,2}, {1,4,9},
                {2,3,4}, {2,5,1},
                {3,5,5}, {4,5,3}
        }));
    }

    // O(E + V*log(V)) - time, O(V + E) - space
    public int findShortestPath(int from, int to, int[][] edges) {
        Map<Integer, Node> idToNode = new HashMap<>();
        Map<Node, List<Edge>> graph = new HashMap<>();
        for (int[] edge: edges) {
            Node fromNode = new Node(edge[0]);
            Node toNode = new Node(edge[1]);

            idToNode.putIfAbsent(edge[0], fromNode);
            idToNode.putIfAbsent(edge[1], toNode);

            graph.computeIfAbsent(fromNode, k -> graph.getOrDefault(k, new ArrayList<>())).add(new Edge(edge[0], edge[1], edge[2]));
            graph.computeIfAbsent(toNode, k -> graph.getOrDefault(k, new ArrayList<>())).add(new Edge(edge[1], edge[0], edge[2]));
        }
        Queue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        Node start = idToNode.get(from);
        start.weight = 0;
        minHeap.add(start);
        while (!minHeap.isEmpty()) {
            Node currNode = minHeap.poll();
            if (currNode.id == to) {
                return currNode.weight;
            }
            currNode.visited = true;
            for (Edge edge: graph.getOrDefault(currNode, new ArrayList<>())) {
                Node fromNode = idToNode.get(edge.from);
                Node toNode = idToNode.get(edge.to);
                if (!toNode.visited && fromNode.weight + edge.distance < toNode.weight) {
                    toNode.weight = fromNode.weight + edge.distance;
                    minHeap.add(toNode);
                }
            }
        }

        return -1;
    }

    private static class Node {
        int id;
        int weight;
        boolean visited;

        Node(int id) {
            this.id = id;
            this.weight = Integer.MAX_VALUE;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Node other = (Node) obj;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    private static class Edge {
        int from;
        int to;
        int distance;

        Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
}
