package graph.GraphValidTree_261;

import graph.DisjointSet;

/**
 * https://leetcode.com/problems/graph-valid-tree/?tab=Description
 *
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 (each edge is a pair of nodes), write a function to check whether these
 edges make up a valid tree.

 For example:
 Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

 Hint:
 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return?
 Is this case a valid tree?
 According to the definition of tree on Wikipedia: “a tree is an undirected
 graph in which any two vertices are connected by exactly one path. In other
 words, any connected graph without simple cycles is a tree.”

 Note: you can assume that no duplicate edges will appear in edges. Since
 all edges are undirected, [0, 1] is the same as [1, 0] and thus will not
 appear together in edges.
 */
// it can be done with union-find algorithm
//http://www.geeksforgeeks.org/union-find/
//https://discuss.leetcode.com/topic/21712/ac-java-union-find-solution

public class Solution {

    public boolean validTree(int n, int[][] edges) {
        DisjointSet disjointSet = new DisjointSet();
        for (int[] edge: edges) {
            disjointSet.makeSet(edge[0]);
            disjointSet.makeSet(edge[1]);
        }

        for (int[] edge: edges) {
            int v1 = edge[0];
            int parent1 = disjointSet.findSet(v1);
            int v2 = edge[1];
            int parent2 = disjointSet.findSet(v2);

            if (parent1 == parent2) {
                return false;
            }

            disjointSet.union(v1, v2);
        }

        return true;
    }
}
