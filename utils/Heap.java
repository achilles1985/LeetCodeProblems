package utils;

import java.util.Arrays;

public class Heap {
    private int[] arr;
    private int capacity = 2;

    private int size = 0;

    public Heap() {
        arr = new int[capacity];
    }

    public Heap(int[] arr) {
        this();
        for (int item: arr) {
            add(item);
        }
    }

    public void add(int item) {
        ensureCapacity();
        arr[size] = item;
        trickleUp(size);
        size++;
    }

    public int peak() {
        int peak = arr[0];
        arr[0] = arr[size-1];
        arr[size-1] = 0;
        size--;
        trickleDown();

        return peak;
    }

    public void sort() {
        int length = size-1;
        for (int i = length; i >= 0 ; i--) {
            swap(0, i);
            length--;
            trickleDown(0, length);
        }
    }

    public void print() {
        System.out.print(arr[0]);
        for (int i = 1; i < size; i++) {
            System.out.print("," + arr[i]);
        }
    }

    private void trickleDown() {
        trickleDown(0, size-1);
    }

    private void trickleUp(int childIdx) {
        if (childIdx == 0) {
            return;
        }
        int parent = (childIdx-1)/2;
        if (arr[childIdx] > arr[parent]) {
            swap(childIdx, parent);
            trickleUp(parent);
        }
    }

    private void trickleDown(int start, int end) {
        if (start >= end) {
            return;
        }
        int childLeft = 2*start + 1;
        int childRight = 2*start + 2;
        if (childLeft <= end && arr[start] < arr[childLeft]) {
            swap(start, childLeft);
            trickleDown(childLeft, end);
        }
        if (childRight <= end && arr[start] < arr[childRight]) {
            swap(start, childRight);
            trickleDown(childRight, end);
        }
    }

    private void swap(int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            arr = Arrays.copyOf(arr, capacity);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(3);
        heap.add(1);
        heap.add(2);
        heap.add(4);
        heap.add(5);
        heap.add(7);
        heap.add(6);

        //heap.sort();

        System.out.println(heap.peak());
        System.out.println(heap.peak());
        System.out.println(heap.peak());

        heap.print();
    }
}
