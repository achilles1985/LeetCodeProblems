package unionfind;

/**M
 *  There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
 *  nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of
 *  C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the
 * ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 *     N is in range [1,200].
 *     M[i][i] = 1 for all students.
 *     If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles_547 {

    public static void main(String[] args) {
        FriendCircles_547 s = new FriendCircles_547();
        System.out.println(s.findCircleNum(new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}})); // 2
        System.out.println(s.findCircleNum(new int[][]{
                {1,1,0},
                {1,1,1},
                {0,1,1}})); // 1
        System.out.println(s.findCircleNum(new int[][]{
                {1,0,0,0,0},
                {0,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,0},
                {0,0,0,0,1}})); // 5
        System.out.println(s.findCircleNum(new int[][]{
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}})); // 1
    }

    // O(n^3) - time (with compression - n^2 since find has amortized time O(a(n)) where a(n) < 5 in practical), O(n)  - space
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        UnionFind uf  = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i != j && M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < uf.parent.length; i++) {
            if (uf.parent[i] == i) {
                count++;
            }
        }

        return count;
    }

    private static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            int size = n;
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
