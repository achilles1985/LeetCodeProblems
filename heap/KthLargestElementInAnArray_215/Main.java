package heap.KthLargestElementInAnArray_215;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)); // 5
        System.out.println(s.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 8)); // 2
    }
}
