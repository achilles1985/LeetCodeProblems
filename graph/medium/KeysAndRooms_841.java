package graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/** M
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 Initially, all the rooms start locked (except for room 0).
 You can walk back and forth between rooms freely.
 Return true if and only if you can enter every room.

 Example 1:
 Input: [[1],[2],[3],[]]
 Output: true
 Explanation:
 We start in room 0, and pick up key 1.
 We then go to room 1, and pick up key 2.
 We then go to room 2, and pick up key 3.
 We then go to room 3.  Since we were able to go to every room, we return true.

 Example 2:
 Input: [[1,3],[3,0,1],[2],[0]]
 Output: false
 Explanation: We can't enter the room with number 2.
 */
public class KeysAndRooms_841 {

    public static void main(String[] args) {
        KeysAndRooms_841 s = new KeysAndRooms_841();
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(3));
        rooms1.add(new ArrayList<>());

        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));

        System.out.println(s.canVisitAllRooms(rooms1)); // true
        System.out.println(s.canVisitAllRooms(rooms2)); // false
    }

    // O(n) - time,space, n - number of rooms
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            visited.add(node);
            for (Integer child: rooms.get(node)) {
                if (!visited.contains(child)) {
                    stack.push(child);
                }
            }
        }
        return visited.size() == rooms.size();
    }
}
