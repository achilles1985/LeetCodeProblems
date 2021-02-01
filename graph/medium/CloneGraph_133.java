package graph.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * M
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Example:
 * 1-2
 * | |
 * 4-3
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},
 * {"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * <p>
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * <p>
 * Note:
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 */
/*
Questions:
    1. Any self-loops, repeated edges? size?
 */
public class CloneGraph_133 {

    public static void main(String[] args) {
        CloneGraph_133 s = new CloneGraph_133();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n3);
        n4.neighbors.add(n1);

        Node clone = s.cloneGraph(n1);
        Node clone2 = s.cloneGraphBFS(n1);
    }

    // O(V+E) - time, O(V) - space (because of visited)
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return cloneGraphHelper(node, visited);
    }

    // O(V+E) - time, O(V) - space
    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> visited = new HashMap<>(); // V
        Queue<Node> queue = new LinkedList<>(); // W - max queue size
        queue.add(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Node polled = queue.poll();
            for (Node child : polled.neighbors) {
                if (!visited.containsKey(child)) {
                    queue.add(child);
                    visited.put(child, new Node(child.val, new ArrayList<>()));
                }
                visited.get(polled).neighbors.add(visited.get(child));
            }
        }
        return visited.get(node);
    }

    private Node cloneGraphHelper(Node node, Map<Node, Node> visited) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphHelper(neighbor, visited));
        }
        return cloneNode;
    }

    // Definition for a Node.
    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    ;
}
