package graph.NetworkDelayTime_743;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.networkDelayTime(new int[][] {{1,2,1}}, 2, 2)); // 2
        System.out.println(s.networkDelayTime(new int[][] {{1,2,1},{2,1,3}}, 2, 2)); // 2
        System.out.println(s.networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}}, 4, 2)); // 2
    }
}
