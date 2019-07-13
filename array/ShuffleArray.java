package array;

import java.util.Random;

public class ShuffleArray {

    public static void main(String[] args) {
        ShuffleArray s = new ShuffleArray();
        int[] arr = new int[] {1,2,3,4,5};
        s.shuffle(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public void shuffle(int[] arr) {
        Random random = new Random();
        int l = arr.length-1;
        for (int i = 0; i < arr.length; i++) {
            int j = random.nextInt(l-1);
            swap(arr, i, j);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
