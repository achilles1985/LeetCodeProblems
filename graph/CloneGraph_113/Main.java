package graph.CloneGraph_113;

import graph.utils.Node;

public class Main {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.adjacent.add(n2);
        n1.adjacent.add(n4);
        n2.adjacent.add(n1);
        n2.adjacent.add(n3);
        n3.adjacent.add(n2);
        n3.adjacent.add(n4);
        n4.adjacent.add(n3);
        n4.adjacent.add(n1);

        Solution s = new Solution();
        Node clone = s.cloneGraph(n1);
    }
}
