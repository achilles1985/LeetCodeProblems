package binarySearch.medium;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/** M [marked]
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
/*
    1. Is x always present in the array? if k > 0 && k < arr.length?
 */
public class FindKClosestElements_658 {

    public static void main(String[] args) {
        FindKClosestElements_658 s = new FindKClosestElements_658();
        System.out.println(s.findClosestElements4(new int[]{1,2,3,4,5},4,3)); //[1,2,3,4]
        System.out.println(s.findClosestElements(new int[]{1,2,3,4,5},4,3)); //[1,2,3,4]

        System.out.println(s.findClosestElementsBF(new int[]{0,0,0,1,3,5,6,7,8,8},2,2)); //[0,1]
        System.out.println(s.findClosestElementsBF(new int[]{0,0,1,2,3,3,4,7,7,8},3,5)); //[3,3,4]

        System.out.println(s.findClosestElements2(new int[]{1,5,10,11,12,13},4,11)); //[5,10,11,12]
        System.out.println(s.findClosestElements2(new int[]{1,2,3,4,5},4,3)); //[1,2,3,4]
        System.out.println(s.findClosestElements2(new int[]{1,2,3,4,5},4,-1)); //[1,2,3,4]
    }

    // O(n*log(n)) - time, O(1) - space
    public List<Integer> findClosestElementsBF(int[] arr, int k, int x) {
        List<Integer> sorted = Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> Math.abs(a - x) - Math.abs(b - x))
                .collect(toList());
        final List<Integer> sub = sorted.subList(0, k);

        return sub.stream()
                .sorted()
                .collect(toList());
    }

    // O(n*log(k)) - time, O(k) - space
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
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

    // O(n) - time, space
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }
        int[] diffs = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            diffs[i] = Math.abs(arr[i] - x);
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += diffs[i];
        }
        int min = sum;
        int[] range = new int[]{0, k-1};
        for (int i = 1, j = k; j < diffs.length; i++, j++) {
            sum = sum - diffs[i-1] + diffs[j];
            if (sum < min) {
                min = sum;
                range[0] = i;
                range[1] = j;
            }
        }
        int[] result = Arrays.copyOfRange(arr, range[0], range[1]+1);

        return Arrays.stream(result)
                .boxed()
                .collect(Collectors.toList());
    }

    // O(k + log(n)) - time, O(k) - space
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        // use two pointers
        int start = 0;
        int end = arr.length-1;

        while (end-start+1 > k){
            // when any abs dist on both sides is greater than the other side, discard the larger one
            if (Math.abs(arr[start]-x) > Math.abs(arr[end]-x))
                start++;
            else
                end--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++){
            res.add(arr[i]);
        }

        return res;
    }

    // https://leetcode.com/problems/find-k-closest-elements/solution/
    public List<Integer> findClosestElements3(List<Integer> arr, int k, int x) {
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

    // O(n*log(k)) - time, O(k) - space
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }

        Queue<Pair1> heap = new PriorityQueue<>(Comparator.comparing(Pair1::getDistance).thenComparing(Pair1::getIndex).reversed());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Pair1(Math.abs(arr[i]-x), i));
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(arr[heap.poll().index]);
        }

        return result;
    }

    // O(n*log(k)) - time, O(k) - space
    public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }

        Queue<Pair1> heap = new PriorityQueue<>(Comparator.comparing(Pair1::getDistance).thenComparing(Pair1::getIndex).reversed());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Pair1(Math.abs(arr[i]-x), i));
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(arr[heap.poll().index]);
        }
        Collections.sort(result);

        return result;
    }

    private static class Pair {
        private int item;
        private int diff;

        public Pair(int item, int diff) {
            this.item = item;
            this.diff = diff;
        }
    }

    private static class Pair1 {
        int distance;
        int index;

        Pair1(int distance, int index) {
            this.distance = distance;
            this.index = index;
        }

        int getIndex() {
            return index;
        }

        int getDistance() {
            return distance;
        }
    }
}
