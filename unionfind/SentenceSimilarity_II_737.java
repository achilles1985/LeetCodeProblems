package unionfind;

import java.util.*;

/**M
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs
 * pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the
 * similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and
 * "good" are similar, then "great" and "fine" are similar.
 *
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great"
 * being similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"],
 * pairs = [] are similar, even though there are no specified similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 =
 * ["great"] can never be similar to words2 = ["doubleplus","good"].
 *
 * Note:
 *     The length of words1 and words2 will not exceed 1000.
 *     The length of pairs will not exceed 2000.
 *     The length of each pairs[i] will be 2.
 *     The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
/*
Hint:
    Consider the graphs where each pair in "pairs" is an edge.
    Two words are similar if they are the same, or are in the same connected component of this graph.
    For each of words1, do dfs and check whether words1[i]==words2[i]
 */
public class SentenceSimilarity_II_737 {

    public static void main(String[] args) {
        SentenceSimilarity_II_737 s = new SentenceSimilarity_II_737();
        System.out.println(s.areSentencesSimilarTwo2(
                new String[]{"great", "acting", "skills"},
                new String[]{"fine", "drama", "talent"},
                Arrays.asList(Arrays.asList("great", "good"), Arrays.asList("fine", "good"), Arrays.asList("acting","drama"), Arrays.asList("skills","talent")))); // true
    }

    // O(P+W) - time, O(P) - space, P - number of pairs, W - number of words
    public boolean areSentencesSimilarTwo2(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair: pairs) {
            String from = pair.get(0);
            String to = pair.get(1);
            graph.computeIfAbsent(from, k-> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k-> new ArrayList<>()).add(from);
        }
        for (int i = 0; i < words1.length; i++) {
            String source = words1[i];
            String target = words2[i];
            if (source.equals(target)) {
                continue;
            }
            if (!dfs(source,target,new HashSet<>(),graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(String source, String target, Set<String> seen, Map<String, List<String>> graph) {
        if (seen.contains(source)) {
            return false;
        }
        if (target.equals(source)) {
            return true;
        }
        seen.add(source);
        for (String child: graph.getOrDefault(source, Collections.emptyList())) {
            if (dfs(child,target,seen,graph)) {
                return true;
            }
        }
        return false;
    }

    // O(P+W) - time, O(P) - space, P - number of pairs, W - number of words
    // If we do not use union-by-rank, O(W*log(P) + P) - time
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        DisjointSet ds = new DisjointSet(pairs);
        for (List<String> pair: pairs) {
            for (String item: pair){
                if (!map.containsKey(item)) {
                    map.put(item, count++);
                }
            }
            ds.union(map.get(pair.get(0)), map.get(pair.get(1)));
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]) || ds.find(map.get(words1[i])) != ds.find(map.get(words2[i]))) {
                return false;
            }
        }

        return true;
    }

    private static class DisjointSet {
        private int[] parent;
        private int[] rank;

        DisjointSet(List<List<String>> pairs) {
            int size = 2 * pairs.size();
            parent = new int[size];
            rank = new int[size];
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

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            if (p1 != p2) {
                if (rank[p1] > rank[p2]) {
                    parent[p2] = p1;
                } else if (rank[p1] < rank[p2]) {
                    parent[p1] = p2;
                } else {
                    parent[p2] = p1;
                    rank[p1]++;
                }
            }
        }
        // throught
    }
}
