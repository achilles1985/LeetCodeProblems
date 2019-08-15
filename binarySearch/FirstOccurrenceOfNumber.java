package binarySearch;

public class FirstOccurrenceOfNumber {

    public static void main(String[] args) {
        FirstOccurrenceOfNumber s = new FirstOccurrenceOfNumber();
        System.out.println(s.searchFirstOfK(new int[]{1,2,3,3,3,4,5}, 3)); //2
        System.out.println(s.searchFirstOfK(new int[]{3,3,3,3}, 3)); //0
        System.out.println(s.searchFirstOfK(new int[]{1,2,3,4}, 4)); //3
    }

    public int searchFirstOfK(int[] nums, int k) {
        int low = 0;
        int high = nums.length-1;
        int res = -1;
        while (low < high) {
            int mid = low + (high-low)/2;
            if (nums[mid] == k) {
                res = mid;
                high = mid-1;
            } else if (nums[mid] > k) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }
}
