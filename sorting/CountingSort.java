package sorting;

import utils.SolutionUtils;

// https://www.programiz.com/dsa/counting-sort
// Assumes: input consist of integer os smaller range
public class CountingSort {

    public static void main(String[] args) {
        CountingSort s = new CountingSort();

        int[] arr5 = new int[] {3,4,5,8,9,7};
        int[] arr0 = new int[] {50,23,9,18,61,32};
        int[] arr1 = new int[] {2,1,4,6,5,7,9,10};
        int[] arr2 = new int[] {7,6,5,4,3,2,1};
        int[] arr3 = new int[] {1,2,3,4,5,6,7};
        int[] arr4 = new int[] {1,8,3,9,4,5,7};

        s.sort(arr5);
        s.sort(arr0);
        s.sort(arr1);
        s.sort(arr2);
        s.sort(arr3);
        s.sort(arr4);
    }

    // O(n + m), m, n - length of input and frequency arrays
    private void sort(int[] arr) {
        // find max to create a frequency array of the right size
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int freq[] = new int[max+1];
        // create frequency array
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }
        // sum prev + curr to find out how many elements <= curr
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i-1];
        }
        int[] sorted = new int[freq.length];
        // find element position in a result array and decrement counter in frequency array. It means we inserted the element in its right position, so count down the counter.
        for (int i = 0; i < arr.length; i++) {
            int idx = freq[arr[i]];
            sorted[idx] = arr[i];
            freq[arr[i]]--;
        }

        SolutionUtils.print(sorted);
    }
}
