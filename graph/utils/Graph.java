package graph.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {

    private boolean isDirected;
    public Map<Integer, Node> nodes = new HashMap<>();
    public List<Edge> edges = new ArrayList<>();

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(int source, int destination) {
        createEdge(source, destination, 0);
    }

    public void addEdge(int source, int destination, int weight) {
        createEdge(source, destination, weight);
    }

    private void createEdge(int source, int destination, int weight) {
        Node s = getNode(source);
        Node d = getNode(destination);

        Edge edge = new Edge(s, d, weight);
        s.adjacent.add(d);
        s.edges.add(edge);
        if (!isDirected) {
            d.adjacent.add(s);
            d.edges.add(edge);
        }

        edges.add(edge);
    }

    public boolean dfs(int from, int to) {
        Node fNode = getNode(from);

        Set<Integer> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.add(fNode);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur.data == to) {
                return true;
            }
            for (Node child: cur.adjacent) {
                if (!visited.contains(child.data)) {
                    stack.push(child);
                    visited.add(child.data);
                }
            }

        }
        return false;
    }

    public boolean bfs(int from, int to) {
        Node fNode = getNode(from);

        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(fNode);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.data == to) {
                return true;
            }
            for (Node child: curNode.adjacent) {
                if (!visited.contains(child.data)) {
                    queue.add(child);
                    visited.add(child.data);
                }
            }
        }
        return false;
    }

    private Node getNode(int data) {
        Node node = nodes.get(data);
        if (node == null) {
            node = new Node(data);
            nodes.put(data, node);
        }

        return node;
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);

        System.out.println(graph.dfs(1, 4)); // true
        System.out.println(graph.dfs(1, 7)); // false
        System.out.println(graph.bfs(1, 4)); // true
        System.out.println(graph.bfs(1, 7)); // false
    }
}
