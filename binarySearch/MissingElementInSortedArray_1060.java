package binarySearch;

/** M
 Given a list of n-1 integers and these integers are in the range of 1 to n. There are no duplicates in list.
 One of the integers is missing in the list. Write an efficient code to find the missing integer.

 Examples:
 Input : arr[] = [1, 2, 3, 4, 6, 7, 8]
 Output : 5

 Input : arr[] = [1, 2, 3, 4, 5, 6, 8, 9]
 Output : 7
 */
public class MissingElementInSortedArray_1060 {

    public static void main(String[] args) {
        MissingElementInSortedArray_1060 s = new MissingElementInSortedArray_1060();
        System.out.println(s.search(new int[] {1,2,3,4,6,7,8})); //5
        System.out.println(s.search(new int[] {1,2,3,4,5,6,8,9})); //7
        System.out.println(s.search(new int[] {1,2,4,5})); //3
    }

    // O(log(n)) - time, O(1) - space
    public int search(int nums[]) {
        int l = 0;
        int r = nums.length-1;
        while (l < r) {
            int m = (l + r)/2;
            if ((nums[m]-m) == 1 && (nums[m+1] - (m+1) == 2)) {
                return nums[m] + 1;
            }
            if (nums[m]-m == 1 && nums[m+1]-(m+1) == 1) {
                l = m;
            }
            if (nums[m]-m == 2 && nums[m+1]-(m+1) == 2) {
                r = m;
            }
        }
        return -1;
    }

    public int search2(int nums[]) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] != mid + 1 && nums[mid - 1] == mid) {
                return (mid + 1);
            }
            if (nums[mid] != mid + 1) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
