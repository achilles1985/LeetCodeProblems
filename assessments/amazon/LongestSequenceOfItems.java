package assessments.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSequenceOfItems {

    public static void main(String[] args) {
        LongestSequenceOfItems s = new LongestSequenceOfItems();
        System.out.println(s.findSequence(Arrays.asList(
                Arrays.asList("item1", "item2"),
                Arrays.asList("item2", "item3"),
                Arrays.asList("item4", "item5"),
                Arrays.asList("item3", "item6"))));
    }

    // O(n^2) - time (can be improved to O(n) - if using union-by-rank), O(n) - space , where n - number of items
    public List<String> findSequence(List<List<String>> itemAssociation) {
        UnionFind uf = new UnionFind(itemAssociation.size());
        Map<String, Integer> itemToIndex = new HashMap<>();
        int count = 0;
        for (List<String> pair: itemAssociation) { //n
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
            final int parent = uf.find(itemToIndex.get(item));
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
