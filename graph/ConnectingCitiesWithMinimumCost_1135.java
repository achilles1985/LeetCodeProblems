package graph;

import java.util.Arrays;

import graph.utils.DisjointSet;

public class ConnectingCitiesWithMinimumCost_1135 {

    public static void main(String[] args) {
        ConnectingCitiesWithMinimumCost_1135 s = new ConnectingCitiesWithMinimumCost_1135();
        System.out.println(s.minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}})); //6
        System.out.println(s.minimumCost(4, new int[][]{{1,2,5},{3,4,4}})); //-1
    }

    // O(n*log(n)) - time, O(n) - space
    public int minimumCost(int N, int[][] connections) {
        int count = 0;
        //sort edges in ascending order
        Arrays.sort(connections, (e1, e2) -> e1[2]-e2[2]);
        DisjointSet ds = new DisjointSet();
        for (int[] edge: connections) {
            ds.makeSet(edge[0]);;
            ds.makeSet(edge[1]);;
        }
        for (int[] edge: connections) {
            int val1 = edge[0];
            int val2 = edge[1];
            int p1 = ds.findSet(val1);
            int p2 = ds.findSet(val2);
            if (p1 != p2) {
               ds.union(val1, val2);
               count += edge[2];
            }
        }
        // if any of sets has different parent, graph is disconnected
        int parent = ds.findSet(1);
        for (int i = 2; i <= N; i++) {
            if (parent != ds.findSet(i)) {
                return -1;
            }
        }
        return count;
    }
}
