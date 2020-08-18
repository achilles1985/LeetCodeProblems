package string.medium;

import java.util.Arrays;
import java.util.List;

/** M
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
 *
 * Example 1:
 * Input: ["23:59","00:00"]
 * Output: 1
 *
 * Note:
 *     The number of time points in the given list is at least 2 and won't exceed 20000.
 *     The input time is legal and ranges from 00:00 to 23:59.
 */
public class MinimumTimeDifference_539 {

    public static void main(String[] args) {
        MinimumTimeDifference_539 s = new MinimumTimeDifference_539();
        System.out.println(s.findMinDifference(Arrays.asList("05:31","22:08","00:35"))); //147
        System.out.println(s.findMinDifference(Arrays.asList("15:10","16:10", "16:11", "18:20"))); //1
        System.out.println(s.findMinDifference(Arrays.asList("15:10","15:10"))); //0
        System.out.println(s.findMinDifference(Arrays.asList("23:59","00:00"))); //1
    }

    // O(n^2) - time, O(n) - space
    public int findMinDifferenceBF(List<String> timePoints) {
        int[] times = new int[timePoints.size()];
        int k = 0;
        for (String timePoint: timePoints) {
            times[k++] = getMinutes(timePoint);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < times.length; i++) {
            for (int j = i + 1; j < times.length; j++) {
                int diff = times[i] - times[j];
                min = Math.min(min, Math.abs(diff));
                min = Math.min(min, 1440 - Math.abs(diff));
            }
        }
        return min;
    }

    // O(n) - time, O(1) - space
    public int findMinDifference(List<String> timePoints) {
        boolean[] minutes = new boolean[60*24];
        for (String timePoint: timePoints) {
            if (minutes[getMinutes(timePoint)]) {
                return 0;
            }
            minutes[getMinutes(timePoint)] = true;
        }

        int minTimeDifference = Integer.MAX_VALUE;
        int previousTime = -1, currentTime = -1, firstTime = -1;
        for(int i = 0; i < 1440; i++) {
            if(minutes[i]) {
                if(previousTime == - 1) {
                    previousTime = i;
                    firstTime = i;
                    continue;
                }
                currentTime = i;
                minTimeDifference = Math.min(minTimeDifference, currentTime - previousTime);
                minTimeDifference = Math.min(minTimeDifference, 1440 - (currentTime - previousTime));
                previousTime = i;
            }
        }
        minTimeDifference = Math.min(minTimeDifference, currentTime - firstTime);
        minTimeDifference = Math.min(minTimeDifference, 1440 - (currentTime - firstTime));

        return minTimeDifference;
    }

    private int getMinutes(String timePoint) {
        String[] time = timePoint.split(":");

        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
    }
}
