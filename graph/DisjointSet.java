package graph;

import java.util.HashMap;
import java.util.Map;

// O(n) - space, O() - time
public class DisjointSet {

    private Map<Integer, Node> nodes = new HashMap<>();

    public static class Node {
        public int data;
        public int rank;
        public Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    public void makeSet(int data) {
        Node node = new Node(data);
        node.parent = node;
        nodes.put(data, node);
    }

    public int findSet(int data) {
        return findSet(nodes.get(data)).data;
    }

    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }

        node.parent = findSet(node.parent);

        return node.parent;
    }


    public void union(int data1, int data2) {
        Node node1 = nodes.get(data1);
        Node node2 = nodes.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if (parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + parent2.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }

}
