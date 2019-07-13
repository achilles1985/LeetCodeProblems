package heap.TopKFrequentWords_692;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        List<String> res1 = s.topKFrequent2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2); // [i, love]
        List<String> res2 = s.topKFrequent2(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4); // [the, is, sunny, day]

        System.out.println(res1);
        System.out.println(res2);
    }
}
