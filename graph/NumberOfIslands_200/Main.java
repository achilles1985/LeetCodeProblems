package graph.NumberOfIslands_200;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.numIslands(new char[][] {
                {1,0,0,1,0},
                {1,0,1,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}})); // 2
        System.out.println(s.numIslands(new char[][] {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}})); // 3
        System.out.println(s.numIslands(new char[][] {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,1},
                {0,0,0,1,0}})); // 3
        System.out.println(s.numIslands(new char[][] {})); // 0
        System.out.println(s.numIslands(new char[][] {{}})); // 0
        System.out.println(s.numIslands(new char[][] {{}, {}})); // 0

    }
}
