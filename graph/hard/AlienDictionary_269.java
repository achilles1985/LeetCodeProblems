package graph.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * H [marked]
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 * <p>
 * Example 1:
 * Input:
 * [ "wrt", "wrf", "er", "ett", "rftt"]
 * Output: "wertf"
 * <p>
 * Example 2:
 * Input:
 * ["z", "x"]
 * Output: "zx"
 * <p>
 * Example 3:
 * Input:
 * ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 * <p>
 * Note:
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
/*
Questions
    1. only lower case letter? size <= 26?
    2. x,z,x - > ""; x,x -> x
 */
public class AlienDictionary_269 {

    public static void main(String[] args) {
        AlienDictionary_269 s = new AlienDictionary_269();

        System.out.println(s.alienOrder(new String[] {"abc", "ab"})); // ""
        System.out.println(s.alienOrder(new String[] {"z", "z"})); // "z"
        System.out.println(s.alienOrder(new String[] {"z", "x", "z"})); // ""
        System.out.println(s.alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt", "te"})); // "wertf"
        System.out.println(s.alienOrder(new String[] {"za", "zb", "ca", "cb"})); // "zcab"
        System.out.println(s.alienOrder(new String[] {"zy", "zx"})); // "zyx"
        System.out.println(s.alienOrder(new String[] {"z", "x"})); // "zx"
        System.out.println(s.alienOrder(new String[] {"z", "x", "z"})); // ""
    }

    // O(chars) - time, O(1) - space
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }
        Map<Character, List<Character>> edges = buildGraph(words); // O(chars)

        return topoligicalSort(edges); // O(26+25^2) = O(1) - time
    }

    private String topoligicalSort(Map<Character, List<Character>> graph) {
        Set<Character> visited = new HashSet<>();
        Set<Character> dfs = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for (Character node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(graph, node, visited, stack, dfs)) {
                    return "";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private boolean dfs(Map<Character, List<Character>> graph, Character node, Set<Character> visited, Stack<Character> stack, Set<Character> dfs) {
        if (dfs.contains(node)) {
            return true;
        }
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        dfs.add(node);
        for (Character child : graph.getOrDefault(node, Collections.emptyList())) {
            if (dfs(graph, child, visited, stack, dfs)) {
                return true;
            }
        }
        stack.push(node);
        dfs.remove(node);

        return false;
    }

    private Map<Character, List<Character>> buildGraph(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
            }
        }

        String prevWord = words[0];
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (prevWord.length() > word.length() && prevWord.startsWith(word)) {
                return new HashMap<>();
            }
            int k = 0, j = 0;
            while (k < prevWord.length() && j < word.length()) {
                if (prevWord.charAt(k) != word.charAt(j)) {
                    graph.get(prevWord.charAt(k)).add(word.charAt(j));
                    break;
                }
                k++;
                j++;
            }
            prevWord = word;
        }

        return graph;
    }
}
