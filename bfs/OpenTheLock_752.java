package bfs;

import java.util.*;

/**
 * M
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * <p>
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * <p>
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 * <p>
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 * <p>
 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */
public class OpenTheLock_752 {

    public static void main(String[] args) {
        OpenTheLock_752 s = new OpenTheLock_752();
        System.out.println(s.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202")); //6
    }

    // O(1) - time, space since there is only 1000 nodes to traverse from 0000 to 9999
    public int openLock(String[] deadends, String target) {
        // BFS because we want to find the minimum numbers of times switching!
        if (target.equals("0000")) {
            return 0;
        }
        Set<String> visited = new HashSet<>(Arrays.asList(deadends)); // don't revisit locations! and let's add all of the deadends to our visited hashset
        if (visited.contains("0000")) {
            return -1;
        }

        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    // add both up and down!
                    char c = curr.charAt(i);
                    // the - 0 converts the character to an int so math can be performed,
                    // once the numbers are added they get converted back to a string! -- clean code inspired by top voted answer
                    String up = curr.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + curr.substring(i + 1);
                    String dwn = curr.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + curr.substring(i + 1);

                    if (up.equals(target) || dwn.equals(target)) {
                        return level + 1;
                    }
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    if (!visited.contains(dwn)) {
                        q.offer(dwn);
                        visited.add(dwn);
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
