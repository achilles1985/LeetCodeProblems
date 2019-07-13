package graph.CourseScheduleII_210;

import utils.SolutionUtils;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] order1 = s.findOrder(2, new int[][]{{1, 0}}); // [0, 1]
        int[] order2 = s.findOrder(2, new int[][]{{1, 0}, {0, 1}});
        int[] order3 = s.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}); //  [0,1,2,3] or [0,2,1,3]

        SolutionUtils.printArray(SolutionUtils.toObjectArray(order1));
        SolutionUtils.printArray(SolutionUtils.toObjectArray(order2));
        SolutionUtils.printArray(SolutionUtils.toObjectArray(order3));
    }
}
