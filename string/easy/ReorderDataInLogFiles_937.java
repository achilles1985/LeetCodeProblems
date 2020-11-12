package string.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.SolutionUtils;

/**E
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *     Each word after the identifier will consist only of lowercase letters, or;
 *     Each word after the identifier will consist only of digits.
 *
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * Return the final order of the logs.
 *
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 * Constraints:
 *     0 <= logs.length <= 100
 *     3 <= logs[i].length <= 100
 *     logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderDataInLogFiles_937 {

    public static void main(String[] args) {
        ReorderDataInLogFiles_937 s = new ReorderDataInLogFiles_937();
        SolutionUtils.print(s.reorderLogFiles2(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"}));
        SolutionUtils.print(s.reorderLogFiles2(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"}));
    }

    //O(m*n*log(n)) - time, where m - max length of hte log, n - number of logs. For each compare() call 2 compare strings with takes m time.
    public String[] reorderLogFiles2(String[] logs) {
        Arrays.sort(logs, new MyComparator());
        return logs;
    }

    // O(m*n*log(n)) - time, O(m) - space, where n - number of logs
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return new String[]{};
        }
        List<String> digitLogs = new ArrayList<>();
        List<String[]> wordLogs = new ArrayList<>();
        //Queue<String[]> wordLogs = new PriorityQueue<>((p1,p2) -> p1[1].compareTo(p2[1]) == 0 ? p1[0].compareTo(p2[0]) : p1[1].compareTo(p2[1]));
        for (String log: logs) {
            String[] pair = log.split(" ", 2);
            if (Character.isDigit(pair[1].charAt(0))) {
                digitLogs.add(log);
            } else {
                wordLogs.add(pair);
            }
        }
        Collections.sort(wordLogs, (p1, p2) -> p1[1].compareTo(p2[1]) == 0 ? p1[0].compareTo(p2[0]) : p1[1].compareTo(p2[1]));

        return merge(wordLogs, digitLogs);
    }

    private String[] merge(List<String[]> wordLogs, List<String> digitLogs) {
        List<String> results = new ArrayList<>();
        for (String[] log: wordLogs) {
            results.add(log[0] + " " + log[1]);
        }
        results.addAll(digitLogs);

        return results.toArray(new String[]{});
    }

    private class MyComparator implements Comparator<String> {

        @Override
        public int compare(String log1, String log2) {
            String[] pair1 = log1.split(" ", 2);
            String[] pair2 = log2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(pair1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(pair2[1].charAt(0));

            if (!isDigit1 && !isDigit2) {
                return pair1[1].compareTo(pair2[1]) == 0 ? pair1[0].compareTo(pair2[0]) : pair1[1].compareTo(pair2[1]);
            }
            if (!isDigit1 && isDigit2) {
                return -1;
            } else if (isDigit1 && !isDigit2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
