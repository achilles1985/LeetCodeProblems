package graph.CourseSchedule_207;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.canFinish(2, new int[][] {{1, 0}})); // true
        System.out.println(s.canFinish(2, new int[][] {{1, 0}, {0, 1}})); // false
    }
}
