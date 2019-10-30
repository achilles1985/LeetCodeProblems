package graph.HasCycle;

import graph.utils.Graph;
import graph.utils.Node;

import java.util.HashSet;
import java.util.Set;

public class DFSolution {

    public boolean hasCycleUndirectedGraph(Graph graph) {
        Set<Node> visited = new HashSet<>();
        for (Node node: graph.getNodes().values()) {
            if (visited.contains(node)) {
                continue;
            }
            if (hasCycleUndirectedGraph(visited, node, null)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasCycleDirectedGraph(Graph graph) {
        Set<Node> visited = new HashSet<>();
        Set<Node> dfs = new HashSet<>();
        for (Node node: graph.nodes.values()) {
            if (visited.contains(node)) {
                continue;
            }
            if (hasCycleDirectedGraph(visited, node, dfs)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleDirectedGraph(Set<Node> visited, Node node, Set<Node> dfs) {
        if (dfs.contains(node)) {
            return true;
        }
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        dfs.add(node);
        for (Node child: node.adjacent) {
            if (hasCycleDirectedGraph(visited, child, dfs)) {
                return true;
            }
        }
        dfs.remove(node);

        return false;
    }

    private boolean hasCycleUndirectedGraph(Set<Node> visited, Node node, Node parent) {
        visited.add(node);
        for (Node child: node.adjacent) {
            if (child.equals(parent)) {
                continue;
            }
            if (visited.contains(child)) {
                return true;
            }
            if (hasCycleUndirectedGraph(visited, child, node)) {
                return true;
            }

        }

        return false;
    }

}
