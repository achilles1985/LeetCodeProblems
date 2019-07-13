package graph;

public class Edge {
    public int distance;
    public Node vertex1;
    public Node vertex2;

    public Edge(Node vertex1, Node vertex2, int distance) {
        this.distance = distance;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (vertex1 != null ? !vertex1.equals(edge.vertex1) : edge.vertex1 != null) return false;
        return vertex2 != null ? vertex2.equals(edge.vertex2) : edge.vertex2 == null;
    }

    @Override
    public int hashCode() {
        int result = vertex1 != null ? vertex1.hashCode() : 0;
        result = 31 * result + (vertex2 != null ? vertex2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return vertex1.data + "-" + vertex2.data;
    }
}
