package graph.NumberOfConnectedComponentsInUndirectedGraph_323;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1

        System.out.println(s.findAllComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.findAllComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1

    }
}
