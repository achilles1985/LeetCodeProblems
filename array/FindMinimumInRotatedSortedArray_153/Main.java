package array.FindMinimumInRotatedSortedArray_153;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums2 = {3,4,5,1,2};
        int min1 = s.findMin(nums1);
        int min2 = s.findMin(nums2);
        System.out.println(min1);
        System.out.println(min2);
    }
}
