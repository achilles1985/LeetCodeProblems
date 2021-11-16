package unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2
 * indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * Example 1:
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 * Constraints:
 *     1 <= s.length <= 10^5
 *     0 <= pairs.length <= 10^5
 *     0 <= pairs[i][0], pairs[i][1] < s.length
 *     s only contains lower case English letters.
 */
/*
    Group pairs with union find where each group is sorted
 */
public class SmallestStringWithSwaps_1202 {

    public static void main(String[] args) {
        SmallestStringWithSwaps_1202 s = new SmallestStringWithSwaps_1202();
        System.out.println(s.smallestStringWithSwaps("dcab", Arrays.asList(Arrays.asList(0,3), Arrays.asList(1,2)))); //bacd
        System.out.println(s.smallestStringWithSwaps("dcab", Arrays.asList(Arrays.asList(0,3), Arrays.asList(1,2), Arrays.asList(0,2)))); //abcd
    }

    // O(n*log(n)) - time, O(n) - space
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        DisjointSet ds = new DisjointSet(s.length());
        for (List<Integer> pair: pairs) { //p
            ds.union(pair.get(0), pair.get(1));
        }
        Map<Integer, Queue<Character>> graph = new HashMap<>();
        for (int i = 0; i < s.length(); i++) { //n
            int p1 = ds.find(i);
            graph.computeIfAbsent(p1, k -> graph.getOrDefault(k, new PriorityQueue<>())).add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Character c = graph.get(ds.find(i)).poll();
            sb.append(c);
        }

        return sb.toString();
    }

    private static final class DisjointSet {
        int[] parent;

        public DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) { // O(1)
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) { // O(log(n)), with union-by-rank O(1)
            int p1 = find(x);
            int p2 = find(y);
            parent[p2] = p1;
        }
    }
}
