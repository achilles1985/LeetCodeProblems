package assessments.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
