package dynamic.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** M
 Find the array that sum up to target sum
 */
public class SubsetSum_II {

    public static void main(String[] args) {
        SubsetSum_II s = new SubsetSum_II();
        System.out.println(s.subsetSumBF(new int[]{2,5,7}, 7));
        System.out.println(s.subsetSumBF(new int[]{2,5,7}, 3));
        System.out.println(s.subsetSumBF(new int[]{3,5,2}, 8));

        System.out.println(s.subsetSumDP(new int[]{2,5,7}, 7));
        System.out.println(s.subsetSumDP(new int[]{2,5,7}, 3));
        System.out.println(s.subsetSumDP(new int[]{3,5,2}, 8));
    }

    // O(length^sum) - time, O(sum) - space
    public List<Integer> subsetSumBF(int[] arr, int sum) {
        if (sum == 0) {
            return new ArrayList<>();
        }
        if (sum < 0) {
            return null;
        }
        for (int num: arr) {
            List<Integer> temp = subsetSumBF(arr, sum - num); //sum
            if (temp != null) {
                temp.add(num);
                return temp;
            }
        }
        return null;
    }

    // O(length*sum) - time, O(sum^2) - space (because in a worse case it's a recursion stack(sum) + mac length of an array (sum))
    public List<Integer> subsetSumDP(int[] arr, int sum) {
        return helper(arr, sum, new HashMap<>());
    }

    private List<Integer> helper(int[] arr, int sum, Map<Integer, List<Integer>> cache) {
        if (cache.containsKey(sum)) {
            return cache.get(sum);
        }
        if (sum == 0) {
            return new ArrayList<>();
        }
        if (sum < 0) {
            return null;
        }
        for (int num: arr) {
            List<Integer> temp = subsetSumBF(arr, sum - num);
            if (temp != null) {
                temp.add(num);
                cache.put(sum-num, temp);
                return temp;
            }
        }
        cache.put(sum, null);

        return null;
    }
}
