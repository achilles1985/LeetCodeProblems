package sorting;

import utils.SolutionUtils;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort s = new MergeSort();
        int[] arr0 = new int[] {7,1,3,6,4,2};
        int[] arr1 = new int[] {2,1,4,6,5,7,9,10};
        int[] arr2 = new int[] {7,6,5,4,3,2,1};
        int[] arr3 = new int[] {1,2,3,4,5,6,7};

        s.sort(arr0);
        s.sort(arr1);
        s.sort(arr2);
        s.sort(arr3);
        SolutionUtils.print(arr1);
        SolutionUtils.print(arr2);
        SolutionUtils.print(arr3);
    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left+right)/2;
        sort(arr, left, mid);
        sort(arr, mid+1, right);
        merge(arr, left, right);
    }

    private void merge(int[] arr, int left, int right) {
        int[] temp = new int[right-left+1];
        int mid = (left+right)/2;
        int i = left;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        int m = left;
        for (int n = 0; n < temp.length; n++) {
            arr[m++] = temp[n];
        }
    }
}
