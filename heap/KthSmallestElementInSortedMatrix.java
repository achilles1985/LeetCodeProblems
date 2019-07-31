package heap;

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
public class KthSmallestElementInSortedMatrix {

    public static void main(String[] args) {
        KthSmallestElementInSortedMatrix s = new KthSmallestElementInSortedMatrix();
        System.out.println(s.kthSmallest(new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8)); // 13
        System.out.println(s.kthSmallestMergeSort(new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8)); // 13
    }

    // O(n*m), O(k) - space
    public int kthSmallest(int[][] matrix, int k) {
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

    // O(n*log(n)) - time, O(m*n) - space
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
}
