package assessments.amazon;

import java.util.*;

/*
    1. Cycle is possible? self loop is possible?
 */
public class LongestSequenceOfItems {

    public static void main(String[] args) {
        LongestSequenceOfItems s = new LongestSequenceOfItems();
        System.out.println(s.findSequence2(Arrays.asList(
                Arrays.asList("item1", "item2"),
                Arrays.asList("item2", "item3"),
                Arrays.asList("item4", "item5"),
                Arrays.asList("item3", "item6")))); //[item1, item2, item3, item6]
    }

    // O(E + V) - time, O(V) - space
    public List<String> findSequence2(List<List<String>> itemAssociation) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> edge: itemAssociation) {
            String from = edge.get(0);
            String to = edge.get(1);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String node: graph.keySet()) {
            if (!seen.contains(node)) {
                List<String> nodes = new ArrayList<>();
                dfs(node, nodes, seen, graph);
                if (!nodes.isEmpty()) {
                    if (nodes.size() > result.size()) {
                        result = nodes;
                    }
                }
            }
        }
        return result;
    }

    private void dfs(String node, List<String> nodes, Set<String> seen, Map<String, List<String>> graph) {
        if (seen.contains(node)) {
            return;
        }
        nodes.add(node);
        seen.add(node);
        for (String child: graph.getOrDefault(node, new ArrayList<>())) {
            dfs(child, nodes, seen, graph);
        }
    }

    // O(n^2) - time (can be improved to O(n) - if using union-by-rank), O(n) - space , where n - number of items
    public List<String> findSequence(List<List<String>> itemAssociation) {
        UnionFind uf = new UnionFind(itemAssociation.size());
        Map<String, Integer> itemToIndex = new HashMap<>();
        int count = 0;
        for (List<String> pair: itemAssociation) { //E
            for (String item: pair) {
                if (!itemToIndex.containsKey(item)) {
                    itemToIndex.put(item, count++);
                }
            }
            uf.union(itemToIndex.get(pair.get(0)), itemToIndex.get(pair.get(1))); //n
        }

        // traverse through the items and find the longest union sequence
        Map<Integer, List<String>> parentToItems = new HashMap<>();
        for (String item: itemToIndex.keySet()) {
            int parent = uf.find(itemToIndex.get(item));
            parentToItems.computeIfAbsent(parent, key -> new ArrayList<>()).add(item);
        }
        int max = 0;
        List<String> sequence = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry: parentToItems.entrySet()) {
            List<String> items = entry.getValue();
            if (items.size() > max) {
                max = items.size();
                sequence = items;
            }
        }

        return sequence;
    }

    private static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            int size = 2*n;
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);
            parent[p2] = p1;
        }
    }
}
