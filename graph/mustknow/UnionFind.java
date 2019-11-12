package graph.mustknow;

import java.util.HashMap;
import java.util.Map;

/**
 Disjoint sets using path compression and union by rank
 O(n) - space, O(m) - time, m - number of operations

 Can be used for cycle detection, max spanning tree finding
  */
public class UnionFind {

    Map<Integer, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        UnionFind ds = new UnionFind();
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

        System.out.println(ds.findSet(1).parent.data);
        System.out.println(ds.findSet(2).parent.data);
        System.out.println(ds.findSet(3).parent.data);
        System.out.println(ds.findSet(4).parent.data);
        System.out.println(ds.findSet(5).parent.data);
        System.out.println(ds.findSet(6).parent.data);
        System.out.println(ds.findSet(7).parent.data);
    }

    // O(1)
    public void makeSet(int data) {
        Node node = new Node(data);
        node.parent = node;
        nodes.put(data, node);
    }

    public Node findSet(int data) {
        return findSet(nodes.get(data));
    }

    // Not only finds, but also reassigns parent while finding
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }

    // Combines two sets together to one.Does union by rank. Path compression, set parent with max rank.
    public void union(int val1, int val2) {
        Node node1 = nodes.get(val1);
        Node node2 = nodes.get(val2);
        Node paren1 = findSet(node1);
        Node paren2 = findSet(node2);
        if (paren1.rank > paren2.rank) {
            paren2.parent = paren1;
        } else if (paren2.rank > paren1.rank) {
            paren1.parent = paren2;
        } else {
            paren2.parent = paren1;
            paren1.rank += 1;
        }
    }

    private static class Node {
        int data;
        int rank;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }
}
