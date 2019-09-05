package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** E
 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 Find all the elements of [1, n] inclusive that do not appear in this array.
 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:
 Input:
 [4,3,2,7,8,2,3,1]
 Output:
 [5,6]
 */
public class FindAllNumbersDisappearedInArray_448 {

    public static void main(String[] args) {
        FindAllNumbersDisappearedInArray_448 s = new FindAllNumbersDisappearedInArray_448();
        System.out.println(s.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1})); // [5,6]
        System.out.println(s.findDisappearedNumbers2(new int[]{4,3,2,7,8,2,3,1})); // [5,6]
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i+1);
            }
        }
        return list;
    }

    // O(n) - time, O(n) - space
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                list.add(i);
            }
        }

        return list;
    }
}
