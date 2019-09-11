package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** M
 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 Note:
 The solution set must not contain duplicate triplets.

 Example:
 Given array nums = [-1, 0, 1, 2, -1, -4],
 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */
public class ThreeSum_15 {

    public static void main(String[] args) {
        ThreeSum_15 s = new ThreeSum_15();
        System.out.println(s.threeSumBF(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(s.threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(s.threeSum2(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(s.threeSum3(new int[]{-1,0,1,2,-1,-4}));
    }

    // O(n^3) - time, O(1) - space
    public List<List<Integer>> threeSumBF(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if ((nums[i] + nums[j] + nums[k]) == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(list);
                        set.add(list);
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    // O(n^2) - time, O(n) - space
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int opposite = -1*sum;
                if (map.containsKey(opposite) && map.get(opposite) > j) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], opposite);
                    Collections.sort(list);
                    set.add(list);
                }
            }
        }

        return new ArrayList<>(set);
    }

    // O(n^2) - time, O(1) - space if set is a part of res
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int sum = 0 - nums[i];
            int lo = i+1;
            int hi = nums.length-1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] < sum) {
                    lo++;
                } else if (nums[lo] + nums[hi] > sum) {
                    hi--;
                } else {
                    List<Integer> list = Arrays.asList(nums[i], nums[lo], nums[hi]);
                    Collections.sort(list);
                    set.add(list);
                    lo++;
                    hi--;
                }
            }
        }

        return new ArrayList<>(set);
    }

    // O(n^2) - time, O(1) - space if set is a part of res
    public List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int l = i + 1, r = nums.length - 1;
                while (l < r) {
                    int v = nums[i] + nums[l] + nums[r];
                    if (v == 0) {
                        ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if (v < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }
}
