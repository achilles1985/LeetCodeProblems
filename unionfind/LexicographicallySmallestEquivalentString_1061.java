package unionfind;

/** M
 * Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters.
 * For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 *     Reflexivity: 'a' == 'a'
 *     Symmetry: 'a' == 'b' implies 'b' == 'a'
 *     Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
 *
 * For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.
 * Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.
 *
 * Example 1:
 * Input: A = "parker", B = "morris", S = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
 *
 * Example 2:
 * Input: A = "hello", B = "world", S = "hold"
 * Output: "hdld"
 * Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
 *
 * Example 3:
 * Input: A = "leetcode", B = "programs", S = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 *
 * Note:
 *     String A, B and S consist of only lowercase English letters from 'a' - 'z'.
 *     The lengths of string A, B and S are between 1 and 1000.
 *     String A and B are of the same length.
 */
public class LexicographicallySmallestEquivalentString_1061 {

    public static void main(String[] args) {
        LexicographicallySmallestEquivalentString_1061 s = new LexicographicallySmallestEquivalentString_1061();
        System.out.println(s.smallestEquivalentString("parker", "morris", "parser")); // makkek
        System.out.println(s.smallestEquivalentString("hello", "world", "hold")); // hdld
        System.out.println(s.smallestEquivalentString("leetcode", "programs", "sourcecode")); // aauaaaaada
    }

    // O(A + S) - time, O(A) - space
    public String smallestEquivalentString(String A, String B, String S) {
        UnionFind uf = new UnionFind();
        for (int i = 0; i < A.length(); i++) {
            uf.union(A.charAt(i) - 'a', B.charAt(i) - 'a');
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ;i < S.length(); i++) {
            int ch = S.charAt(i) - 'a';
            builder.append(smallestInConnectedComponent(uf, ch)); // we can keep always the smallest parent by modifying union() method
        }
        return builder.toString();
    }

    private char smallestInConnectedComponent(UnionFind uf, int x) {
        int min = x;
        int parent = uf.find(x);
        for (int i = 0; i < 26; i++) {
            if (uf.find(i) == parent) {
                min = Math.min(min, i);
            }
        }
        return (char)(min + 'a');
    }

    private static class UnionFind {
        int[] id = new int[26];
        public UnionFind() {
            for (int i = 0; i < 26; i++) {
                id[i] = i;
            }
        }
        public void union(int u, int v) {
            id[find(u)] = find(v);
            // make sure parent is always smallest in the connected component
            /* if (a < b) {
                id[b] = a;
            } else {
                id[a] = b;
            }*/
        }
        public int find(int u) {
            while (u != id[u]) {
                id[u] = id[id[u]];
                u = id[u];
            }
            return u;
        }
    }
}
