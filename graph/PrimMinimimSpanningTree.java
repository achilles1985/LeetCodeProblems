package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import graph.utils.Edge;
import graph.utils.Graph;
import graph.utils.Node;

public class PrimMinimimSpanningTree {

    public Set<Edge> getMST(Graph graph) {
        Queue<Node> heap = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        graph.nodes.values().iterator().next().weight = 0;
        for (Node node: graph.nodes.values()) {
            heap.add(node);
        }

        Map<Node, Edge> edgeByNode = new HashMap<>();
        Set<Edge> result = new HashSet<>();
        while (!heap.isEmpty()) {
            Node curr = heap.poll();
            Edge resultEdge = edgeByNode.get(curr);
            if (resultEdge != null) {
                result.add(resultEdge);
            }

            for (Node child: curr.adjacent) {
                Edge edge = curr.getEdgeBy(child);
                if (edge == null) {
                    continue;
                }
                if (child.weight > edge.distance) {
                    child.weight = edge.distance;
                    heap.remove(child);
                    heap.add(child);
                    edgeByNode.put(child, edge);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimMinimimSpanningTree mst = new PrimMinimimSpanningTree();
        Set<Edge> result = mst.getMST(graph);
        for (Edge edge : result) {
            System.out.println(edge.vertex1.data + " " + edge.vertex2.data);
        }
    }
}
