package binarySearch.medium;

/** M
 Given a list of n-1 integers and these integers are in the range of 1 to n. There are no duplicates in list.
 One of the integers is missing in the list. Write an efficient code to find the missing integer.

 Examples:
 Input : arr[] = [1, 2, 3, 4, 6, 7, 8]
 Output : 5

 Input : arr[] = [1, 2, 3, 4, 5, 6, 8, 9]
 Output : 7
 */
public class MissingElementInSortedArray_1060_old {

    public static void main(String[] args) {
        MissingElementInSortedArray_1060_old s = new MissingElementInSortedArray_1060_old();
        System.out.println(s.search3(new int[] {1,2,3,4,6})); //5
        System.out.println(s.search3(new int[] {2,3,4,5})); //1
        System.out.println(s.search3(new int[] {1,2,3,4,6,7,8})); //5
        System.out.println(s.search3(new int[] {1,2,3,4,5,6,8,9})); //7
        System.out.println(s.search3(new int[] {1,2,4,5})); //3
    }

    // O(log(n)) - time, O(1) - space
    public int search3(int nums[]) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] - mid == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] - 1;
    }

}
