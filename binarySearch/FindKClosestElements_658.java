package binarySearch;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/** M
 Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 The result should also be sorted in ascending order. If there is a tie,
 the smaller elements are always preferred.

 Example 1:
 Input: [1,2,3,4,5], k=4, x=3
 Output: [1,2,3,4]

 Example 2:
 Input: [1,2,3,4,5], k=4, x=-1
 Output: [1,2,3,4]

 Note:
 The value k is positive and will always be smaller than the length of the sorted array.
 Length of the given array is positive and will not exceed 104
 Absolute value of elements in the array and x will not exceed 104
 */
public class FindKClosestElements_658 {

    public static void main(String[] args) {
        FindKClosestElements_658 s = new FindKClosestElements_658();
        System.out.println(s.findClosestElements2(new int[]{0,0,0,1,3,5,6,7,8,8},2,2)); //[1,3]
        System.out.println(s.findClosestElements2(new int[]{0,0,1,2,3,3,4,7,7,8},3,5)); //[3,3,4]

        System.out.println(s.findClosestElements2(new int[]{1,5,10,11,12,13},4,11)); //[10,11,12,13]
        System.out.println(s.findClosestElements2(new int[]{1,2,3,4,5},4,3)); //[1,2,3,4]
        System.out.println(s.findClosestElements2(new int[]{1,2,3,4,5},4,-1)); //[1,2,3,4]
    }

    // O(n*log(k)) - time, O(k) - space
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<Pair> heap = new PriorityQueue<>((h1,h2) -> h2.diff!=h1.diff ? h2.diff-h1.diff : h2.item-h1.item);
        for (int item: arr) {
            heap.add(new Pair(item, Math.abs(item-x)));
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> res = new ArrayList<>(k);
        while (!heap.isEmpty()) {
            res.add(heap.poll().item);
        }

        Collections.sort(res);
        return res;
    }

    // O(k + log(n)) - time, O(1) - space
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int length = arr.length;
        if (x <= arr[0]) {
            return Arrays.stream(Arrays.copyOfRange(arr, 0, k))
                    .boxed()
                    .collect(Collectors.toList());
        } else if (x >= arr[length-1]) {
            return Arrays.stream(Arrays.copyOfRange(arr, length-k, length))
                    .boxed()
                    .collect(Collectors.toList());
        } else {
            int index = Arrays.binarySearch(arr, x);
            if (index < 0) {
                index = -index-1;
            }
            int low = Math.max(0, index-k);
            int hight = Math.min(length-1, index+k);
            while (hight-low > k) {
                if (x - arr[low] <= arr[hight] - x) {
                    hight--;
                } else {
                    low++;
                }
            }
            return Arrays.stream(Arrays.copyOfRange(arr, low, hight))
                    .boxed()
                    .collect(Collectors.toList());
        }
    }

    // https://leetcode.com/problems/find-k-closest-elements/solution/
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        int n = arr.size();
        if (x <= arr.get(0)) {
            return arr.subList(0, k);
        } else if (arr.get(n - 1) <= x) {
            return arr.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(arr, x);
            if (index < 0) {
                index = -index - 1;
            }
            int low = Math.max(0, index - k - 1);
            int high = Math.min(arr.size() - 1, index + k - 1);

            while (high - low > k - 1) {
                if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x)) {
                    high--;
                } else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x)) {
                    low++;
                } else {
                    System.out.println("Unhandled case: " + low + " " + high);
                }
            }
            return arr.subList(low, high + 1);
        }
    }

    private static class Pair {
        private int item;
        private int diff;

        public Pair(int item, int diff) {
            this.item = item;
            this.diff = diff;
        }
    }
}
