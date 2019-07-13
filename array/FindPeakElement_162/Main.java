package array.FindPeakElement_162;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1,3,2,5,4};
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {5,4,3,2,1};
        int peakIndex = s.findPeakElement3(nums);
        int peakIndex1 = s.findPeakElement2(nums1);
        int peakIndex2 = s.findPeakElement2(nums2);
        System.out.println(peakIndex);
        System.out.println(peakIndex1);
        System.out.println(peakIndex2);
    }
}
