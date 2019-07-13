package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMinimimSpanningTree {


    public List<Edge> getMST(Graph graph) {
        DisjointSet set = new DisjointSet();
        for (Integer node: graph.nodes.keySet()) {
            set.makeSet(node);
        }

        Collections.sort(graph.edges, (e1, e2) -> e1.distance - e2.distance);
        List<Edge> result = new ArrayList<>();
        for (Edge e: graph.edges) {
            int p1 = set.findSet(e.vertex1.data);
            int p2 = set.findSet(e.vertex2.data);
            if (p1 != p2) {
                result.add(e);
                set.union(e.vertex1.data, e.vertex2.data);
            }
            int i = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);
        KruskalMinimimSpanningTree mst = new KruskalMinimimSpanningTree();
        List<Edge> result = mst.getMST(graph); // 1-3, 2-5, 2-4, 6-5, 4-7, 1-2
        for (Edge edge : result) {
            System.out.print(edge + " ");
        }
    }
}
