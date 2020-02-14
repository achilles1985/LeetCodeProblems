package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * H
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 * <p>
 * Example 1:
 * Input:
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * Output: "wertf"
 * <p>
 * Example 2:
 * Input:
 * [
 * "z",
 * "x"
 * ]
 * Output: "zx"
 * <p>
 * Example 3:
 * Input:
 * [
 * "z",
 * "x",
 * "z"
 * ]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 * <p>
 * Note:
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary_269 {

    public static void main(String[] args) {
        AlienDictionary_269 s = new AlienDictionary_269();
        System.out.println(s.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt", "te"})); // "wertf"
        System.out.println(s.alienOrder(new String[]{"za", "zb", "ca", "cb"})); // "abzc"
        System.out.println(s.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"})); // "wertf"
        System.out.println(s.alienOrder(new String[]{"zy", "zx"})); // "yxz"
        System.out.println(s.alienOrder(new String[]{"abc", "abc"})); // "abc"
        System.out.println(s.alienOrder(new String[]{"z", "x"})); // "zx"
        System.out.println(s.alienOrder(new String[]{"z", "x", "z"})); // ""
    }

    // O(n) - time, O(n) - space, n - total number of letters in the words
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word: words) {
            for (Character c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        int[] indegree = new int[26];
        for (int i = 1; i < words.length; i++) {
            String left = words[i - 1];
            String right = words[i];
            for (int j = 0; j < Math.min(left.length(), right.length()); j++) {
                char from = left.charAt(j);
                char to = right.charAt(j);
                if (from != to) {
                    if (!graph.get(from).contains(to)) {
                        graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new HashSet<>())).add(to);
                        indegree[to - 'a']++;
                    }
                    break;
                }
            }
        }
        String res = topologicalSort(graph, indegree);

        return res.length() != graph.size() ? "" : res;
    }

    private String topologicalSort(Map<Character, Set<Character>> graph, int[] inDegree) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return sb.toString();
    }
}
