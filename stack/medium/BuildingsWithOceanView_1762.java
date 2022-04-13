package stack.medium;

import utils.SolutionUtils;

import java.util.*;

/** M
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions.
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

 * Example 1:
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 *
 * Example 2:
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 *
 * Example 3:
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 * Example 4:
 * Input: heights = [2,2,2,2]
 * Output: [3]
 * Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 *
 * Constraints:
 *     1 <= heights.length <= 105
 *     1 <= heights[i] <= 109
 */
// Edge cases: [2,2,2], [1,2,3], [3,2,1]
public class BuildingsWithOceanView_1762 {

    public static void main(String[] args) {
        BuildingsWithOceanView_1762 s = new BuildingsWithOceanView_1762();
        SolutionUtils.print(s.findBuildings2(new int[]{4,2,3,1})); //[0,2,3]
        SolutionUtils.print(s.findBuildings2(new int[]{2,2,2})); //[2]

        System.out.println(s.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
    }

    // a.b.c.d
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s: cpdomains) {
            String[] sub1 = s.split(" ");
            Integer num = Integer.parseInt(sub1[0]);
            String domain = sub1[1];
            String[] domains = domain.split("\\.");
            Stack<String> stack = new Stack<>();
            for (String sub: domains) {
                stack.push(sub);
            }
            while (!stack.isEmpty()) {
                String sub = stack.pop();
                map.put(sub, map.getOrDefault(sub,0)+num);
                if (!stack.isEmpty()) {
                    stack.push(stack.pop() + "." + sub);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }

        return res;
    }

    // O(n^2) - time, O(n) - space
    public int[] findBuildingsBF(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length-1; i++) {
            boolean has = true;
            for (int j = i+1; j < heights.length; j++) {
                if (heights[i] <= heights[j]) {
                    has = false;
                    break;
                }
            }
            if (has) {
                stack.push(i);
            }
        }
        int[] res = new int[stack.size()+1];
        int k = 0;
        while (!stack.isEmpty()) {
            res[k++] = stack.pollLast();
        }
        res[k] = heights.length - 1;

        return res;
    }

    // O(n) - time, space
    public int[] findBuildings(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        int[] res = new int[stack.size()];
        int k = 0;
        while (!stack.isEmpty()) {
            res[k++] = stack.pollLast();
        }

        return res;
    }

    // O(n) - time, O(1) - space
    public int[] findBuildings2(int[] heights) {
        int max = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > max) {
                stack.push(i);
                max = heights[i];
            }
        }
        int[] res = new int[stack.size()];
        int k = 0;
        while(!stack.isEmpty()) {
            res[k++] = stack.pollFirst();
        }
        return res;
    }
}
