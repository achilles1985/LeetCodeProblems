package graph.AlienDictionary;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/** M
 * There is a new alien language which uses the latin alphabet.
 However, the order among letters are unknown to you. You receive a
 list of words from the dictionary, where words are sorted lexicographically
 by the rules of this new language. Derive the order of letters in this language.
 For example,
 Given the following words in dictionary,
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".
 Note:
 You may assume all letters are in lowercase.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 */
public class Solution {

    public String alienOrder(String[] words, int nuumberOfLetters) {
        // create a graph
        List<char[]> edges = new ArrayList<>();
        for (int i = 1; i < nuumberOfLetters; i++) {
            String w1 = words[i-1];
            String w2 = words[i];
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    edges.add(new char[] {w1.charAt(j), w2.charAt(j)});
                }
            }
        }

        List<Character> sorted = topologicaSort(edges);
        StringBuilder builder = new StringBuilder();
        for (Character c: sorted) {
            builder.append(c);
        }

        return builder.toString();
    }

    private List<Character> topologicaSort(List<char[]> edges) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (char[] edge: edges) {
            char v1 = edge[0];
            char v2 = edge[1];
            graph.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
        }

        Set<Character> visited = new HashSet<>();
        Deque<Character> stack = new LinkedList<>();
        for (Character node: graph.keySet()) {
            if (visited.contains(node)) {
                continue;
            }

            topologicaSortUtils(visited, node, stack, graph);
        }

        List<Character> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.poll());
        }
        return res;
    }

    private void topologicaSortUtils(Set<Character> visited, Character node, Deque<Character> stack, Map<Character, List<Character>> graph) {
        visited.add(node);
        if (graph.containsKey(node)) {
            for (Character child : graph.get(node)) {
                if (!visited.contains(child)) {
                    topologicaSortUtils(visited, child, stack, graph);
                }
            }
        }

        stack.push(node);
    }
}
