package array.medium;

import java.util.NavigableSet;
import java.util.TreeSet;

public class ContainsDuplicate_III_220 {

    public static void main(String[] args) {
        ContainsDuplicate_III_220 s = new ContainsDuplicate_III_220();
        System.out.println(s.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3)); //false
        System.out.println(s.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0)); //true
        System.out.println(s.containsNearbyAlmostDuplicate(new int[]{-2147483648, 2147483647}, 1, 1)); //false
    }

    //O(n*k) - time, O(1) - space
    public boolean containsNearbyAlmostDuplicateBF(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = Math.max(i - k, 0); j < i; ++j) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }

        return false;
    }

    //O(n*log(k)) - time, O(k) - space
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && ceil <= (long) (nums[i] + t)) {
                return true;
            }
            Integer floor = set.floor(nums[i]);
            if (floor != null && nums[i] <= (long) (floor + t)) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
