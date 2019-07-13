package heap.KthSmallestElementInSortedMatrix_378;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n row n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:
 10, 20, 30, 40
 15, 25, 35, 45
 24, 29, 37, 48
 32, 33, 39, 50
 The 3rd smallest element is 20 and 7th smallest element is 30
 */
public class Solution {

    // O(n^2*log(n)), O(k) - space
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int counter = k;
        for (int i = 0; i < matrix.length; i++) {
            if (counter <= 1) {
                break;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                heap.add(matrix[i][j]);
                if (heap.size() > k && counter > 1) {
                    heap.poll();
                    counter--;
                }
            }
        }
        return heap.peek();
    }

    // https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20Sorted%20Matrix.java
    // O(rowsNumber + k*log(k)) - time, O(k) - space
    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0) {
            return 0;
        }
        int rowsNumber = matrix.length;
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // Initialize the queue with head elements
        for (int i = 0; i < rowsNumber; i++) {
            queue.offer(new Node(i, 0, matrix[i][0]));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (k == 1) {
                return node.val;
            }
            if (node.column + 1 < rowsNumber) {
                queue.offer(new Node(node.row, node.column + 1, matrix[node.row][node.column + 1]));
            }
            k--;
        }

        return 0;
    }

    private class Node {
        int row;
        int column;
        int val;
        public Node(int row, int column, int val) {
            this.row = row;
            this.column = column;
            this.val = val;
        }
    }
}
