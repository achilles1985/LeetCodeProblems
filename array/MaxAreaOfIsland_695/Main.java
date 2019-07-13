package array.MaxAreaOfIsland_695;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxAreaOfIsland(new int[][]{
                {0,1},
                {1,1}})); //3
        System.out.println(s.maxAreaOfIsland(new int[][]{
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,1},
                {0,0,0,1,0}})); //9
        System.out.println(s.maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}})); // 6
        System.out.println(s.maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}})); //0
    }
}
