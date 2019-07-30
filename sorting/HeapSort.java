package sorting;

import utils.Heap;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,4,2,5,6,4,0,7,8,9,1,2,3,7,6,8};
        Heap heap = new Heap(array);
        heap.sort();
        heap.print();
    }
}
