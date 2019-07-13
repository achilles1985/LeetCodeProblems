package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int data;
    public int weight = Integer.MAX_VALUE;
    public List<Node> adjacent = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();

    public Node(int data) {
        this.data = data;
    }

    public Node getAdjacentByEdge(Edge edge) {
        return edge.vertex1.equals(this) ? edge.vertex2 : edge.vertex1;
    }

    public Edge getEdgeBy(Node v2) {
        for (Edge edge: edges) {
            if (edge.vertex2.data == v2.data && edge.vertex1.data == data || edge.vertex1.data == v2.data && edge.vertex2.data == data) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return data == node.data;
    }

    @Override
    public int hashCode() {
        return data;
    }
}
