package binarySearch.medium;

public class SingleElementInSortedArray_540 {

    public static void main(String[] args) {
        SingleElementInSortedArray_540 s = new SingleElementInSortedArray_540();
        System.out.println(s.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8})); //2
        System.out.println(s.singleNonDuplicate(new int[]{3,3,7,7,10,11,11})); //10
    }

    //O(log(n)) - time, O(1) - space
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean halvesAreEven = (right - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
