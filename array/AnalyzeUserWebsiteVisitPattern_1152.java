package array;

import java.util.*;

/** M
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
/*
To be honest, this question's explanation is very confusing and I didn't understand it in contest. Let me explain it here:
Every user visits websites and the websites for each user visits can form 3 - sequences. Our goal is to find the most common 3-seq in those users.
    - we collect the time and website info according to every user
    - n^3 traverse all 3-seq in every user and use count map to record the 3-seq ocurring times(remember to sort by time and avoid same sequence in the same user)
    - get the result
 */
public class AnalyzeUserWebsiteVisitPattern_1152 {

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPattern_1152 s = new AnalyzeUserWebsiteVisitPattern_1152();
        System.out.println(s.mostVisitedPattern2(
                new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                new int[]{1,2,3,4,5,6,7,8,9,10},
                new String[]{"home","about","career","home","cart","maps","home","home","about","career"})); //["home","about","career"]
    }

    // O(n^4) - time, O(n) - space
    public List<String> mostVisitedPattern2(String[] username, int[] timestamp, String[] website) {
        Map<String, List<TimeSite>> userToTimeSites = new HashMap<>();
        int n = username.length;
        // collect the website info for every user, key: username, value: (timestamp, website)
        for (int i = 0; i < n; i++) {
            userToTimeSites.computeIfAbsent(username[i], k -> new ArrayList<>()).add(new TimeSite(timestamp[i], website[i]));
        }
        // count map to record every 3 combination occuring time for the different user.
        Map<String, Integer> sequenceToCount = new HashMap<>();
        String res = "";
        for (String user : userToTimeSites.keySet()) {
            Set<String> uniqueSites = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
            List<TimeSite> timeSites = userToTimeSites.get(user);
            Collections.sort(timeSites, (a, b)->(a.time - b.time)); // sort by time
            // brutal force O(N ^ 3)
            for (int i = 0; i < timeSites.size(); i++) {
                for (int j = i + 1; j < timeSites.size(); j++) {
                    for (int k = j + 1; k < timeSites.size(); k++) {
                        String str = timeSites.get(i).web + " " + timeSites.get(j).web + " " + timeSites.get(k).web;
                        if (!uniqueSites.contains(str)) {
                            sequenceToCount.put(str, sequenceToCount.getOrDefault(str, 0) + 1);
                            uniqueSites.add(str);
                        }
                        if (res.isEmpty()
                                || sequenceToCount.get(res) < sequenceToCount.get(str)
                                || (sequenceToCount.get(res) == sequenceToCount.get(str) && res.compareTo(str) > 0)) {
                            // make sure the right lexi order
                            res = str;
                        }
                    }
                }
            }
        }
        // grab the right answer
        String[] r = res.split(" ");
        List<String> result = new ArrayList<>();
        for (String str : r) {
            result.add(str);
        }
        return result;
    }

    private class TimeSite {
        int time;
        String web;
        public TimeSite(int time, String web) {
            this.time = time;
            this.web = web;
        }
    }
}
