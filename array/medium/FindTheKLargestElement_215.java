package array.medium;

/** M
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class FindTheKLargestElement_215 {

    public static void main(String[] args) {
        FindTheKLargestElement_215 s = new FindTheKLargestElement_215();
        System.out.println(s.findKLargest(new int[]{3,2,1,5,4}, 3)); //3
        System.out.println(s.findKLargest(new int[]{1,1,1,1}, 3)); //1
    }

    // O(n) - time average case, O(n^2) - worst case, O(1) - space
    public int findKLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length-1;
        int pivot = high;
        while (low <= high) {
            pivot = partition(nums, low, high, pivot);
            if (pivot == k-1) {
                return nums[pivot];
            }
            if (pivot < k-1) {
                low = pivot+1;
                pivot++;
            } else {
                high = pivot-1;
                pivot--;
            }
        }

        return pivot;
    }

    private int partition(int[] nums, int low, int high, int pivotIdx) {
        int pivot = nums[pivotIdx];
        int i = low;
        for (int j = i; j < high; j++) {
            if (nums[i] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, high);

        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
