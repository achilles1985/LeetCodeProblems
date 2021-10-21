package graph.mustknow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// Disjoint sets using path compression and union by rank
// O(n) - space, O(m) - time, m - number of operations

// Good explanation: https://leetcode.com/articles/redundant-connection/#
public class DisjointSet {

    private Map<Integer, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }

    public static class Node {
        public int data;
        public int rank;
        public Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    // Create a set with only one element.
    public void makeSet(int data) {
        Node node = new Node(data);
        node.parent = node;
        nodes.put(data, node);
    }

    // Finds the representative of this set
    public int findSet(int data) {
        return findSet(nodes.get(data)).data;
    }

    private Node findSet(Node node) {
        if (node.parent != node) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }

    // Combines two sets together to one.Does union by rank
    public void union(int data1, int data2) { // Inverse-Ackermann function which boils down to O(1)
        Node node1 = nodes.get(data1);
        Node node2 = nodes.get(data2);

        // find representative
        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if (parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }

    public Map<Integer, Node> getNodeMap() {
        return nodes;
    }
}
