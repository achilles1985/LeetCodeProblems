package heap.medium;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:
 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 12, 15]
 ],
 k = 8,
 return 13.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class KthSmallestElementInSortedMatrix_378 {

    public static void main(String[] args) {
        KthSmallestElementInSortedMatrix_378 s = new KthSmallestElementInSortedMatrix_378();
        System.out.println(s.kthSmallest3(new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8)); // 13
        System.out.println(s.kthSmallestBF(new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8)); // 13
        System.out.println(s.kthSmallestMergeSort(new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8)); // 13
    }

    // O(n*m*log(k), O(k) - space
    public int kthSmallestBF(int[][] matrix, int k) {
        Queue<Integer> heap = new PriorityQueue<>((e1, e2) -> e2-e1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heap.add(matrix[i][j]);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        return heap.peek();
    }

    // Move via all rows at the same time (like in MergeKSortedLists)
    // O(rows*log(rows) + k*log(k)) - time, O(rows) - space
    public int kthSmallest3(int[][] matrix, int k) {
        Queue<Node> minHeap = new PriorityQueue<>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);
        for (int i = 0; i < matrix.length; i++) { //O(rows*log(rows)
            minHeap.add(new Node(i, 0));
        }
        int count = 0;
        while (!minHeap.isEmpty()) { //O(k*log(k)
            Node polled = minHeap.poll();
            if (++count == k) {
                return matrix[polled.row][polled.col];
            }
            if (polled.col + 1 < matrix[0].length){
                polled.col++;
                minHeap.add(polled);
            }
        }
        return -1;
    }

    // O(?) - time, O(m*n) - space
    public int kthSmallestMergeSort(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int end = matrix.length-1;
        int[] arr = sort(matrix, 0, end);
        return arr[k-1];
    }

    private int[] sort(int[][] matrix, int start, int end) {
        if (start >= end) {
            return matrix[end];
        }
        int mid = start + (end - start) /2;
        int[] left = sort(matrix, start, mid);
        int[] right = sort(matrix, mid+1, end);

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] temp = new int[left.length + right.length];
        int i = 0; int j = 0; int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                temp[k++] = left[i++];
            } else {
                temp[k++] = right[j++];
            }
        }

        while (i < left.length) {
            temp[k++] = left[i++];
        }
        while (j < right.length) {
            temp[k++] = right[j++];
        }

        return temp;
    }

    private static class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
