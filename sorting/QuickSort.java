package sorting;

import utils.SolutionUtils;

// https://www.geeksforgeeks.org/quick-sort/
/*
    Has O(n^2) in worse case when pivot is end of the array and the array is sorted desc. 9,8,7,6,5
    n+(n-1)+(n+2)+...1 = n(n-1)/2 = n^2
 */
public class QuickSort {

    // O(n*log(n)) - time, O(1) - space
    public static void main(String[] args) {
        QuickSort s = new QuickSort();

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
        SolutionUtils.print(arr0);
        SolutionUtils.print(arr1);
        SolutionUtils.print(arr2);
        SolutionUtils.print(arr3);
        SolutionUtils.print(arr4);
    }

    private void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    private void sort(int[] arr, int i, int j) {
        if (i < j) {
            int pivot = partition(arr, i, j);
            sort(arr, i, pivot-1);
            sort(arr, pivot+1, j);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = i; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, j, i);
                i++;
            }
        }

        swap(arr, i, high);

        return i;
    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}

