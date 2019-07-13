package graph.CourseSchedule_207;

import graph.DisjointSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** M
 There are a total of n courses you have to take, labeled from 0 to n-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:
 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.

 Example 2:
 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.

 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 */
public class Solution {

    // If there is a cycle, return false
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        DisjointSet set = new DisjointSet();
        for (int[] edge: prerequisites) {
            set.makeSet(edge[0]);
            set.makeSet(edge[1]);
        }

        for (int[] edge: prerequisites) {
            int v1 = edge[0];
            int paren1 = set.findSet(v1);
            int v2 = edge[1];
            int paren2 = set.findSet(v2);

            if (paren1 == paren2) {
                return false;
            }

            set.union(v1, v2);
        }

        return true;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if(numCourses<=1) {
            return true;
        }
        int l = prerequisites.length;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i=1; i<l; i++){
            int k=prerequisites[i-1][0];
            int v=prerequisites[i-1][1];
            if(!map.containsKey(k)) {
                map.put(k, new HashSet<>());
            }
            map.get(k).add(v);

            if(!check(map, prerequisites[i][1], prerequisites[i][0])) {
                return false;
            }
        }

        return true;
    }

    private boolean check(Map<Integer, Set<Integer>>map, int k, int t){
        Set<Integer> set = map.get(k);
        if(set == null) {
            return true;
        }
        if(set.contains(t)) {
            return false;
        }
        for(Integer x:set){
            if(!check(map, x, t)) {
                return false;
            }
        }
        return true;
    }
}
