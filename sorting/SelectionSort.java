package sorting;

import utils.SolutionUtils;

public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();

        int[] arr1 = new int[] {2,1,4,6,5,7,9,10};
        int[] arr2 = new int[] {7,6,5,4,3,2,1};
        int[] arr3 = new int[] {1,2,3,4,5,6,7};

        int[] arr11 = new int[] {2,1,4,6,5,7,9,10};
        int[] arr22 = new int[] {7,6,5,4,3,2,1};
        int[] arr33 = new int[] {1,2,3,4,5,6,7};

        s.sort(arr1);
        s.sort(arr2);
        s.sort(arr3);
        SolutionUtils.print(arr1);
        SolutionUtils.print(arr2);
        SolutionUtils.print(arr3);

        s.sort2(arr11);
        s.sort2(arr22);
        s.sort2(arr33);
        SolutionUtils.print(arr11);
        SolutionUtils.print(arr22);
        SolutionUtils.print(arr33);
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public void sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length-1; j > i; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j-1, j);
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
