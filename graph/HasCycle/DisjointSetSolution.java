package graph.HasCycle;

import graph.DisjointSet;
import graph.Edge;
import graph.Graph;

public class DisjointSetSolution {

    public boolean hasCycle(Graph graph) {
        DisjointSet set = new DisjointSet();
        for (Integer node : graph.getNodes().keySet()) {
            set.makeSet(node);
        }

        for (Edge edge : graph.edges) {
            int v1 = edge.vertex1.data;
            int parent1 = set.findSet(v1);
            int v2 = edge.vertex2.data;
            int parent2 = set.findSet(v2);
            if (parent1 == parent2) {
                return true;
            }
            set.union(v1, v2);
        }

        return false;
    }
}
