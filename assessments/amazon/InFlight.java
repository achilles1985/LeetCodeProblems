package assessments.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are on a flight and wanna watch two movies during this flight.
 * You are given List<Integer> movieDurations which includes all the movie durations.
 * You are also given the duration of the flight which is d in minutes.
 * Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).
 *
 * Find the pair of movies with the longest total duration and return they indexes.
 * If multiple found, return the pair with the longest movie.
 *
 * Example 1:
 * Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
 * Output: [0, 6]O
 * Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)
 */
public class InFlight {

    public static void main(String[] args) {
        InFlight s = new InFlight();
        System.out.println(s.foo(90, Arrays.asList(1,10,25,35,60)));
    }

    public List<Integer> foo(int flightDuration, List<Integer> movieDuration) {
        if (movieDuration == null || movieDuration.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, Integer> movieToIndex = new HashMap<>();
        for (int i = 0; i < movieDuration.size(); i++) {
            int movie = movieDuration.get(i);
            movieToIndex.put(movie, i);
        }
        Collections.sort(movieDuration);

        int target = flightDuration - 30;
        int left = 0, right = movieDuration.size() - 1;
        int maxSum = -1;
        int i = -1, j = -1;
        while (left < right) {
            int sum = movieDuration.get(left) + movieDuration.get(right);
            if (sum <= target) {
                if (sum > maxSum) {
                    maxSum = sum;
                    i = movieToIndex.get(movieDuration.get(left));
                    j = movieToIndex.get(movieDuration.get(right));
                }
                left++;
            } else {
                right--;
            }
        }

        return Arrays.asList(i, j);
    }
}
