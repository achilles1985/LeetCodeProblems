package trie.TopKFrequentWords_692;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = "the".compareTo("is");

        int[] arr = new int[] {3,1,2,5,7,4,6,9,8,10,11};
        PriorityQueue<Integer> q = new PriorityQueue<>((n1, n2) -> n2 > n1 ? -1 : ((n1 == n2) ? 0 : 1));
        int k = 7;
        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
            if (q.size() > k) {
                q.poll();
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!q.isEmpty()) {
            list.add(q.poll());
        }


        System.out.println(s.topKFrequent2(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2)); // ["i", "love"]
        System.out.println(s.topKFrequent2(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)); // ["the", "is", "sunny", "day"]
    }
}
