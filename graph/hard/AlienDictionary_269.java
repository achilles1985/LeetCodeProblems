package graph.hard;

import java.util.*;

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
    2. Edge cases: [x,z,x] - > ""; [x,x] -> x; [abc, ab] -> ""
 */
// Find relation between chars, build graph, topological sort
public class AlienDictionary_269 {

    public static void main(String[] args) {
        AlienDictionary_269 s = new AlienDictionary_269();
        System.out.println(s.alienOrder(new String[] {"aba"})); // "ab"
        System.out.println(s.alienOrder(new String[] {"abc", "ab"})); // ""
        System.out.println(s.alienOrder(new String[] {"z", "z"})); // "z"
        System.out.println(s.alienOrder(new String[] {"z", "x", "z"})); // ""
        System.out.println(s.alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt", "te"})); // "wertf"
        System.out.println(s.alienOrder(new String[] {"za", "zb", "ca", "cb"})); // "zcab"
        System.out.println(s.alienOrder(new String[] {"zy", "zx"})); // "zyx"
        System.out.println(s.alienOrder(new String[] {"z", "x"})); // "zx"
        System.out.println(s.alienOrder(new String[] {"z", "x", "z"})); // ""
    }

    // O(c) - time, O(1) - space, where c - total number of chars in the word list
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> map = new HashMap<>();
        for (String word: words) {
            for (int i = 0; i < word.length(); i++) {
                map.put(word.charAt(i), new ArrayList<>());
            }
        }
        for (int k = 1; k < words.length; k++) {
            String prev = words[k-1];
            String curr = words[k];
            if (curr.length() < prev.length() && prev.startsWith(curr)) {
                return "";
            }
            int i = 0, j = 0;
            while (i < prev.length() && j < curr.length()) {
                if (prev.charAt(i) != curr.charAt(j)) {
                    map.get(prev.charAt(i)).add(curr.charAt(j));
                    break;
                }
                i++; j++;
            }
        }

        return topologicSort(map);
    }

    private String topologicSort(Map<Character, List<Character>> graph) {
        Map<Character, Integer> visited = new HashMap<>();
        Deque<Character> stack = new ArrayDeque<>();
        for (Character node: graph.keySet()) {
            if (!visited.containsKey(node)) {
                if (hasCycle(graph, visited, stack, node)) {
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

    private boolean hasCycle(Map<Character, List<Character>> graph, Map<Character, Integer> visited, Deque<Character> stack, Character node) {
        if (visited.containsKey(node) && visited.get(node) == -1) {
            return true;
        }
        if (visited.containsKey(node)) {
            return false;
        }
        visited.put(node, -1);
        for (Character child: graph.getOrDefault(node, Collections.emptyList())) {
            if (hasCycle(graph, visited, stack, child)) {
                return true;
            }
        }
        visited.put(node, 1);
        stack.push(node);

        return false;
    }
}
