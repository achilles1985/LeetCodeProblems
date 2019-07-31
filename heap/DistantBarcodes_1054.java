package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import utils.SolutionUtils;

/** M
 In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.

 Example 1:
 Input: [1,1,1,2,2,2]
 Output: [2,1,2,1,2,1]

 Example 2:
 Input: [1,1,1,1,2,2,3,3]
 Output: [1,3,1,3,2,1,2,1]

 Note:
 1 <= barcodes.length <= 10000
 1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes_1054 {

    public static void main(String[] args) {
        DistantBarcodes_1054 s = new DistantBarcodes_1054();
        SolutionUtils.print(s.rearrangeBarcodes(new int[] {1,1,1,2,2,2}));
        SolutionUtils.print(s.rearrangeBarcodes(new int[] {1,1,1,1,2,2,3,3}));
    }

    // O(n*log(n)) - time, O(n) - space
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes == null || barcodes.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int code: barcodes) {
            map.put(code, map.getOrDefault(code, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));
        for (int key: map.keySet()) {
            heap.add(key);
        }

        int i = 0;
        int[] res = new int[barcodes.length];
        int prev = -1;
        while (!heap.isEmpty()) {
            int cur = heap.poll();
            res[i++] = cur;
            map.put(cur, map.get(cur)-1);
            if (prev != -1 && map.get(prev) != 0) {
                heap.add(prev);
            }
            prev = cur;
        }

        return res;
    }
}
