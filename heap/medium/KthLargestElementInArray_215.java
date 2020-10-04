package heap.medium;

// https://leetcode.com/problems/kth-largest-element-in-an-array/

import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 not the kth distinct element.

 Example 1:
 Input: [3,2,1,5,6,4] and k = 2
 Output: 5

 Example 2:
 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray_215 {

    public static void main(String[] args) {
        KthLargestElementInArray_215 s = new KthLargestElementInArray_215();
        System.out.println(s.findKthLargest(new int[] {3,2,1,5,6,4}, 2)); //5
        System.out.println(s.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4)); //4

        System.out.println(s.findKthLargestQuickSelect(new int[] {3,2,1,5,6,4}, 2)); //5
        System.out.println(s.findKthLargestQuickSelect(new int[] {3,2,3,1,2,4,5,5,6}, 4)); //4
    }

    // O(n*log(k)) - time, O(k) - space to keep heap
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num: nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();
    }

    // O(n) - time best case, O(n^2) - worse case, O(1) - space
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int length = nums.length;
        partialQuickSort(nums, length-k, 0, length-1);

        return nums[length-k];
    }

    private void partialQuickSort(int[] nums, int kIdx, int start, int end) {
        int p = pivot(nums, start, end);
        if (p != kIdx) {
            if (p < kIdx) {
                partialQuickSort(nums, kIdx, p+1, end);
            } else {
                partialQuickSort(nums, kIdx, start, p-1);
            }
        }
    }

    private int pivot(int[] nums, int start, int end) {
        int i = start; int j = start;
        int pivot = end;
        while (j < end) {
            if (nums[j] <= nums[pivot]) {
                swap(nums, i, j);
                i++;
            }
            j++;
        }
        swap(nums, i, pivot);

        return i;
    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

   //https://github.com/bephrem1/backtobackswe/blob/master/Sorting%2C%20Searching%2C%20%26%20Heaps/kthLargestElement.java
/*    public int findKthLargest(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        Random rand = new Random(0);
        while (left <= right) {
            int choosenPivotIndex = rand.nextInt(right - left + 1) + left;
            int finalIndexOfChoosenPivot = partition(arr, left, right, choosenPivotIndex);
            if (finalIndexOfChoosenPivot == n - k) {
                return arr[finalIndexOfChoosenPivot];
            } else if (finalIndexOfChoosenPivot > n - k) {
                right = finalIndexOfChoosenPivot - 1;
            } else {
                left = finalIndexOfChoosenPivot + 1;
            }
        }
        return -1; // this will never be reached, necessary to compile
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        int lesserItemsTailIndex = left;
        swap(arr, pivotIndex, right);
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, lesserItemsTailIndex);
                lesserItemsTailIndex++;
            }
        }
        swap(arr, right, lesserItemsTailIndex);
        return lesserItemsTailIndex;
    }

    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }*/
}
