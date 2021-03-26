package unionfind;

import java.util.Random;

/** H
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
 * Also two strings X and Y are similar if they are equal.
 *
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but
 * "star" is not similar to "tars", "rats", or "arts".
 *
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that
 * "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a
 * word is in the group if and only if it is similar to at least one other word in the group.
 *
 * We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups
 * are there?
 *
 * Example 1:
 * Input: A = ["tars","rats","arts","star"]
 * Output: 2
 *
 * Constraints:
 *     1 <= A.length <= 2000
 *     1 <= A[i].length <= 1000
 *     A.length * A[i].length <= 20000
 *     All words in A consist of lowercase letters only.
 *     All words in A have the same length and are anagrams of each other.
 *     The judging time limit has been increased for this question.
 */
/*
    1. All words are unique? (if yes, there is no sense in map<string, int>)
    Group similar words with union find
 */
public class SimilarStringGroups_839 {

    public static void main(String[] args) {
        int n = 100000000 + new Random().nextInt(900000000);

        SimilarStringGroups_839 s = new SimilarStringGroups_839();
        System.out.println(s.numSimilarGroups(new String[]{"tars","rats","arts","star"})); //2
    }

    //O(n^2*m) - time, O(n) - space, n - number of words, m - word's length
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        DisjointSet ds = new DisjointSet(A.length);
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                if (similar(A[i], A[j])) {
                    ds.union(i, j);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (ds.parent[i] == i) {
                result++;
            }
        }
        return result;
    }

    private boolean similar(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
            if (diff > 2) {
                return false;
            }
        }
        return true;
    }

    private static final class DisjointSet {
        int[] parent;

        public DisjointSet(int size) {
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

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            parent[p2] = p1;
        }
    }
}
