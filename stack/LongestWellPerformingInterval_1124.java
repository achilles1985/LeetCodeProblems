package stack;

/** M
 We are given hours, a list of the number of hours worked per day for a given employee.
 A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 Return the length of the longest well-performing interval.

 Example 1:
 Input: hours = [9,9,6,0,6,6,9]
 Output: 3
 Explanation: The longest well-performing interval is [9,9,6].

 Constraints:
 1 <= hours.length <= 10000
 0 <= hours[i] <= 16
 */
public class LongestWellPerformingInterval_1124 {

    public static void main(String[] args) {
        LongestWellPerformingInterval_1124 s = new LongestWellPerformingInterval_1124();

        System.out.println(s.longestWPI(new int[]{9,9,6,0,6,6,9})); //3
        System.out.println(s.longestWPI(new int[]{9,9,9})); //3
        System.out.println(s.longestWPI(new int[]{8,8,8})); //0
    }

    // O(n) - time, O(1) - space
    public int longestWPI(int[] hours) {
        for (int i = 0; i < hours.length; i++) {
            hours[i] = hours[i] <= 8 ? -1 : 1;
        }

        int sum = hours[0];
        int maxSum = 0;
        for (int i = 1; i < hours.length; i++) {
            if (hours[i] + sum > sum) {
                sum += hours[i];
            } else {
                sum = hours[i];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

}
