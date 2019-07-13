package heap.KthSmallestElementInSortedMatrix_378;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = {{10, 20, 30, 40}, {15, 25, 35, 45}, {24, 29, 37, 48}, {32, 33, 39, 50}};

        System.out.println(s.kthSmallest2(matrix, 3)); // 20
        System.out.println(s.kthSmallest2(matrix, 7)); // 30
    }
}
