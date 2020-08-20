package string.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * M [MARKED] (Simulation)
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * <p>
 * Example 1:
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
 * It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * <p>
 * Example 2:
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */
/*
    Solution:
    Simulate the clock going forward by one minute.
    Each time it moves forward, if all the digits are allowed, then return the current time.
    The natural way to represent the time is as an integer t in the range 0 <= t < 24 * 60.
    Then the hours are t / 60, the minutes are t % 60, and each digit of the hours and minutes can be found by hours / 10, hours % 10 etc.
 */
public class NextClosestTime_681 {

    public static void main(String[] args) {
        NextClosestTime_681 s = new NextClosestTime_681();
        System.out.println(s.nextClosestTime("19:34")); //19:39
        System.out.println(s.nextClosestTime("23:59")); //22:22
        System.out.println(s.nextClosestTime("01:32")); //01:33
        System.out.println(s.nextClosestTime("23:59")); //22:22
        System.out.println(s.nextClosestTime("00:00")); //00:00
    }

    // O(1) - time, space. We try up to 24*60 possibilities to find a correct time.
    public String nextClosestTime(String time) {
        // 1. convert to minutes
        // 2. create set of possible digits
        // 3. increment time by one and check existence in a set
        int minutes = Integer.parseInt(time.substring(0, 2))*60 + Integer.parseInt(time.substring(3));
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            char c = time.charAt(i);
            if (c != ':') {
                unique.add(c - '0');
            }
        }
        for (int i = 1; i <= 24*60; i++) {
            minutes = (minutes + 1)%(24*60);
            int[] digits = new int[] {minutes/60/10, minutes/60%10, minutes%60/10, minutes%60%10};
            int j = 0;
            for (; j < digits.length; j++) {
                if (!unique.contains(digits[j])) {
                    break;
                }
            }
            if (j == digits.length) {
                return String.format("%02d:%02d", minutes/60, minutes%60);
            }
        }
        return "";
    }

}
