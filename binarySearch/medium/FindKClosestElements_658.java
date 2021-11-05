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
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<int[]> maxHeap = new PriorityQueue<>((e1,e2) -> e1[1] == e2[1] ? e2[0] - e1[0] : e2[1] - e1[1]);
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(new int[]{arr[i], Math.abs(arr[i] - x)});
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int[] entry: maxHeap) {
            result.add(entry[0]);
        }
        Collections.sort(result);

        return result;
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
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
