package graph.AllPathsFromSourceToTarget_797;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}})); // [[0,1,3],[0,2,3]]
        System.out.println(s.allPathsSourceTarget(new int[][]{{1}, {}})); // [[0,1]]
    }
}
