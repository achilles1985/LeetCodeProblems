package heap.TopKFrequentElements_347;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.topKFrequent(new int[]{1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6}, 2)); // [3,4]
        System.out.println(s.topKFrequent(new int[]{1}, 1)); // [1]
    }
}
