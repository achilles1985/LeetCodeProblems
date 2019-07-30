package sorting;

import utils.SolutionUtils;

public class InsertionSort {

    public static void main(String[] args) {
        InsertionSort s = new InsertionSort();
        int[] arr1 = new int[] {2,1,4,6,5,7,9,10};
        int[] arr2 = new int[] {7,6,5,4,3,2,1};
        int[] arr3 = new int[] {1,2,3,4,5,6,7};

        s.sort(arr1);
        s.sort(arr2);
        s.sort(arr3);
        SolutionUtils.print(arr1);
        SolutionUtils.print(arr2);
        SolutionUtils.print(arr3);
    }

    public void sort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = i-1; j >= 0 ; j--) {
               if (input[j] > input[j+1]) {
                   swap(input, j+1, j);
               }
            }
        }
    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
