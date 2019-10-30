package graph.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class WeightedGraph {

    private List<WeightedNode> nodes = new ArrayList<>();

    public void addNode(WeightedNode node) {
        nodes.add(node);
    }

    // Finds all shortest path weights started from the specified node
    // Space complexity - O(E + V)
    // Time complexity - O(E*log(V))
    public Map<WeightedNode, Integer> findAllShortestPathsByDijkstras(WeightedNode from) {
        if (from == null) {
            return Collections.emptyMap();
        }

        Map<WeightedNode, Integer> nodeToWeight = new HashMap<>();
        Set<WeightedNode> visited = new HashSet<>();
        Queue<WeightedNode> queue = new PriorityQueue<>(Comparator.comparingInt(WeightedNode::getWeight));
        from.setWeight(0);
        queue.add(from);
        while (!queue.isEmpty()) {
            WeightedNode curr = queue.poll();
            nodeToWeight.put(curr, curr.weight);
            for (Map.Entry<WeightedNode, Integer> entry: curr.adjacent.entrySet()) {
                WeightedNode vertex = entry.getKey();
                if (!visited.contains(vertex)) {
                    if (vertex.weight > curr.weight + curr.adjacent.get(vertex)) {
                        vertex.weight = curr.weight + curr.adjacent.get(vertex);
                    }
                    queue.add(vertex);
                }
            }
            visited.add(curr);
        }

        return nodeToWeight;
    }

/*    public Map<WeightedNode, Integer> findMinimumSpanningTree() {
        Map<WeightedNode, Integer> result = new HashMap<>();

        Queue<Map.Entry<WeightedNode, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(entry -> entry.getValue()));
        Set<Map.Entry<WeightedNode, Integer>> visited = new HashSet<>();
        Map<WeightedNode, Integer> vertexToDistance = new HashMap<>();

        Map.Entry<WeightedNode, Integer> minEdge = nodes.get(0).adjacent.entrySet().stream()
                .min(Comparator.comparing(entry -> entry.getValue()))
                .orElse(null);

        queue.add(minEdge);
        while (!queue.isEmpty()) {
            Map.Entry<WeightedNode, Integer> cur = queue.poll();
            if (vertexToDistance.containsKey(cur.getKey())) {
                result.put(cur.getKey(), cur.getValue());
            }
            for (Map.Entry<WeightedNode, Integer> child: cur.getKey().adjacent.entrySet()) {
                if (!visited.contains(child)) {
                    queue.add(child);
                }
            }
            visited.add(cur);
        }

        return result;
    }*/

    public static void main(String[] args) {
        WeightedNode nodeA = new WeightedNode("A");
        WeightedNode nodeB = new WeightedNode("B");
        WeightedNode nodeC = new WeightedNode("C");
        WeightedNode nodeD = new WeightedNode("D");
        WeightedNode nodeE = new WeightedNode("E");
        WeightedNode nodeF = new WeightedNode("F");

        nodeA.addAdjacent(nodeB, 4);
        nodeA.addAdjacent(nodeC, 5);
        nodeA.addAdjacent(nodeD, 6);

        nodeB.addAdjacent(nodeA, 4);
        nodeB.addAdjacent(nodeC, 2);
        nodeB.addAdjacent(nodeE, 9);

        nodeC.addAdjacent(nodeA, 5);
        nodeC.addAdjacent(nodeB, 2);
        nodeC.addAdjacent(nodeD, 4);
        nodeC.addAdjacent(nodeF, 1);

        nodeD.addAdjacent(nodeA, 6);
        nodeD.addAdjacent(nodeC, 4);
        nodeD.addAdjacent(nodeF, 5);

        nodeE.addAdjacent(nodeB, 9);
        nodeE.addAdjacent(nodeF, 3);

        nodeF.addAdjacent(nodeE, 3);
        nodeF.addAdjacent(nodeC, 1);
        nodeF.addAdjacent(nodeD, 5);

        WeightedGraph graph = new WeightedGraph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        System.out.println(graph.findAllShortestPathsByDijkstras(nodeA));
    }
}
