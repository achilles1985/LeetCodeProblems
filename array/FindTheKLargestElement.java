package array;

public class FindTheKLargestElement {

    public static void main(String[] args) {
        FindTheKLargestElement s = new FindTheKLargestElement();
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
