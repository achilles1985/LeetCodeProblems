package array;

/** E
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:
 Input: [3, 2, 1]
 Output: 1
 Explanation: The third maximum is 1.

 Example 2:
 Input: [1, 2]
 Output: 2
 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

 Example 3:
 Input: [2, 2, 3, 1]
 Output: 1
 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber_414 {

    public static void main(String[] args) {
        ThirdMaximumNumber_414 s = new ThirdMaximumNumber_414();
        System.out.println(s.thirdMax(new int[]{3,5,4,1,2,7})); //4
        System.out.println(s.thirdMax(new int[]{3,2,1})); //1
        System.out.println(s.thirdMax(new int[]{1,2})); //2
        System.out.println(s.thirdMax(new int[]{2,2,3,1})); //1
        System.out.println(s.thirdMax(new int[]{1})); //1
    }

    // O(n) - time, O(1) - space
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int num: nums) {
            if (first < num) {
                third = second;
                second = first;
                first = num;
            } else if (num < first && num > second) {
                third = second;
                second = num;
            } else if (num < second && num > third) {
                third = num;
            }
        }

        return third == Long.MIN_VALUE ? (int)first : (int)third;
    }
}
