package unionfind;

import java.util.Arrays;

/**M
 * In a social group, there are N people, with unique integer ids from 0 to N-1.
 *
 * We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp,
 * and the ids of two different people.
 *
 * Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is
 * friends with B, then B is friends with A.
 *
 * Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted
 * with B.
 *
 * Return the earliest time for which every person became acquainted with every other person. Return -1 if there is
 * no such earliest time.
 *
 * Example 1:
 *
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],
 * [20190312,1,2],[20190322,4,5]], N = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship
 * groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship
 * groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship
 * groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship
 * groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 *
 * Note:
 *     2 <= N <= 100
 *     1 <= logs.length <= 10^4
 *     0 <= logs[i][0] <= 10^9
 *     0 <= logs[i][1], logs[i][2] <= N - 1
 *     It's guaranteed that all timestamps in logs[i][0] are different.
 *     logs are not necessarily ordered by some criteria.
 *     logs[i][1] != logs[i][2]
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends_1101 {

    public static void main(String[] args) {
        TheEarliestMomentWhenEveryoneBecomeFriends_1101 s = new TheEarliestMomentWhenEveryoneBecomeFriends_1101();
        System.out.println(s.earliestAcq(new int[][] {
                {3,0,3},
                {4,1,2},
                {0,2,0},
                {2,0,2},
                {8,0,3},
                {1,0,1},
                {5,1,2},
                {7,3,1},
                {6,1,0},
                {9,3,0}}, 4)); //3
        System.out.println(s.earliestAcq(new int[][] {
                {20190101,0,1},
                {20190104,3,4},
                {20190107,2,3},
                {20190211,1,5},
                {20190224,2,4},
                {20190301,0,3},
                {20190312,1,2},
                {20190322,4,5}}, 6)); //20190301
    }

    // O(n*log(n)) - time, O(V) - space
    public int earliestAcq(int[][] logs, int N) {
        if (logs == null || logs.length == 0) {
            return -1;
        }
        Arrays.sort(logs, (a1,a2) -> a1[0]-a2[0]);
        DisjointSet ds = new DisjointSet(N);
        for (int[] log: logs) {
            if (ds.find(log[1]) != ds.find(log[2])) {
                ds.union(log[1], log[2]);
            }
            if (ds.count == 1) {
                return log[0];
            }
        }
        return -1;
    }

    private static final class DisjointSet {
        int count;
        int[] parent;

        DisjointSet(int size) {
            parent = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        void union(int node1, int node2) {
            parent[find(parent[node2])] = parent[node1];
            count--;
        }
    }
}
