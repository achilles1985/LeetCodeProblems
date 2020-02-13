package sorting;

import utils.SolutionUtils;

import java.util.Arrays;

// https://www.programiz.com/dsa/bucket-sort
// Assumes: elements (usually in [0,1]) are formally distributed
public class BucketSort {

    public static void main(String[] args) {
        BucketSort s = new BucketSort();
        s.sort(new int[]{4,5,4,6,7,2,3,1,4});
    }

    // O(n) - time average, O(n^2) - worse
    public void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int[] buckets = new int[max + 1];
        // frequency array
        for (int i = 0; i < array.length; i++) {
            buckets[array[i]]++;
        }
        for (int i = 0, j = 0; i < buckets.length; i++) {
            while (buckets[i] > 0){
                array[j++] = i;
                buckets[i]--;
            }
        }

        SolutionUtils.print(array);
    }
}
