package todo;

import utils.SolutionUtils;

/** M
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:
 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].

 Example 2:
 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSort_II_324 {

    public static void main(String[] args) {
        WiggleSort_II_324 s = new WiggleSort_II_324();

        s.wiggleSort3(new int[]{1,5,1,1,6,4});
        s.wiggleSort3(new int[]{1,2,2,1,2,1,1,1,1,2,2,2});

        s.wiggleSort(new int[]{1,5,1,1,6,4});
        s.wiggleSort(new int[]{1,3,2,3,2,2,2,2,2,3,1,5,7});
        s.wiggleSort(new int[]{1,3,2,2,3,1});
        s.wiggleSort(new int[]{1,2,2,1,2,1,1,1,1,2,2,2}); // [1,2,1,2,1,2,1,2,1,2,1,2]
    }

    // incorrect
    public void wiggleSort3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                }
            } else {
                if (nums[i - 1] < nums[i]) {
                    swap(nums, i - 1, i);
                }
            }
        }
        SolutionUtils.print(nums);
    }

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, n/2);
        int i = 0;
        int j = 0;
        int k = n - 1;
        while (j <= k) {
            if (nums[index(j, n)] > median) {
                swap(nums, index(i++, n), index(j++, n));
            } else if (nums[index(j, n)] < median) {
                swap(nums, index(k--, n), index(j, n));
            } else {
                j++;
            }
        }

        SolutionUtils.print(nums);
    }

    private int findKthLargest(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        while (true) {
            int pivot = nums[r];
            int left = l;
            for (int i = l; i < r; i++) {
                if (nums[i] > pivot) {
                    swap(nums, left++, i);
                }
            }
            swap(nums, left, r);
            if (left < k)
                l = left + 1;
            else if (left > k) {
                r = left - 1;
            } else {
                return nums[k];
            }
        }
    }

    private int index(int i, int n) {
        return (2 * i + 1) %  (n | 1);
    }

    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
