package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/** M [TODO]
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
 *
 * Example 1:
 * Input:
 *  username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 *  website = ["home","about","career","home","cart","maps","home","home","about","career"]
 *  timestamp = [1,2,3,4,5,6,7,8,9,10],
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 * Note:
 *     3 <= N = username.length = timestamp.length = website.length <= 50
 *     1 <= username[i].length <= 10
 *     0 <= timestamp[i] <= 10^9
 *     1 <= website[i].length <= 10
 *     Both username[i] and website[i] contain only lowercase characters.
 *     It is guaranteed that there is at least one user who visited at least 3 websites.
 *     No user visits two websites at the same time.
 */
public class AnalyzeUserWebsiteVisitPattern_1152 {

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPattern_1152 s = new AnalyzeUserWebsiteVisitPattern_1152();
        System.out.println(s.mostVisitedPattern(
                new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                new int[]{1,2,3,4,5,6,7,8,9,10},
                new String[]{"home","about","career","home","cart","maps","home","home","about","career"})); //["home","about","career"]
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Set<String> unique = new HashSet<>();
        Map<String, Integer> frequency = new HashMap<>();
        Queue<Wrapper> queue = new PriorityQueue<>((w1, w2) -> w1.count == w2.count ? w2.site.compareTo(w1.site) : w1.count - w2.count);
        for (int i = 0; i < username.length; i++) {
            String user = username[i];
            String site = website[i];
            if (!unique.contains(user+site)) {
                frequency.put(site, frequency.getOrDefault(site, 0) + 1);
            }
            unique.add(user + site);
        }
        for (Map.Entry<String, Integer> e: frequency.entrySet()) {
            String site = e.getKey();
            int count = e.getValue();
            queue.add(new Wrapper(site, count));
            if (queue.size() > 3) {
                queue.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().site);
        }

        return result;
    }

    private class Wrapper {
        String site;
        int count;

        Wrapper(String site, int count) {
            this.site = site;
            this.count = count;
        }
    }
}
