package algorithmstechniqes;
/**
 * Quickselect is a selection algorithm to find the k-th smallest/largest element in an unordered list.
 */
public class QuickSelect {

    public static void main(String[] args) {
        QuickSelect s = new QuickSelect();
        System.out.println(s.findKthLargest(new int[]{1,8,5,6,3,2,4}, 3)); //5
        System.out.println(s.findKthSmallest(new int[]{1,8,5,6,3,2,4}, 3)); //3
    }

    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        partialQuickSort(nums, length-k, 0, length-1);
        return nums[length-k];
    }

    public int findKthSmallest(int[] nums, int k) {
        int length = nums.length;
        partialQuickSort(nums, k-1, 0, length-1);
        return nums[k-1];
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

    // https://bidhutkarki.wordpress.com/2015/11/05/quick-select-algorithm-java-implementation/
    public int partialQuickSortIterative(int[] arr, int startIndex, int endIndex, int k) {
        if (startIndex == endIndex) {
            return arr[startIndex];
        }
        while (true) {
            int pivot = pivot(arr, startIndex, endIndex);
            if (pivot == k) {
                return arr[pivot];
            } else if (k < pivot) {
                endIndex = pivot - 1;
            } else {
                startIndex = pivot + 1;
            }
        }
    }

    private int pivot(int[] nums, int start, int end) {
        int i = start; // all element that are behind i are less than a pivot
        int j = start; // scanner
        int pivot = nums[end];
        while (j < end) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
            j++;
        }
        swap(nums, i, end);

        return i;
    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
