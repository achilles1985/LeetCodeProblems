package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

    public Stack<Node> toSortList(Graph graph) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();

        for (Node node: graph.nodes.values()) {
            if (visited.contains(node)) {
                continue;
            }
            sortUtils(visited, stack, node);
        }

        return stack;
    }

    private void sortUtils(Set<Node> visited, Stack<Node> stack, Node node) {
        visited.add(node);
        for (Node child: node.adjacent) {
            if (visited.contains(child)) {
                continue;
            }
            sortUtils(visited, stack, child);
        }

        stack.push(node);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(7, 8);

        TopologicalSort sort = new TopologicalSort();
        Stack<Node> sorted = sort.toSortList(graph);
        while (!sorted.isEmpty()) {
            System.out.println(sorted.pop().data);
        }
    }
}
