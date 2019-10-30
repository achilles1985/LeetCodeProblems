package graph.utils;

import java.util.HashMap;
import java.util.Map;

public class WeightedNode {
    public String data;
    public int weight = Integer.MAX_VALUE;

    public Map<WeightedNode, Integer> adjacent = new HashMap<>();

    public WeightedNode(String data) {
        this.data = data;
    }

    public void addAdjacent(WeightedNode node, int distance) {
        adjacent.put(node, distance);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedNode node = (WeightedNode) o;

        return data != null ? data.equals(node.data) : node.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
