package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.SolutionUtils;

public class PermuteArray {

    public static void main(String[] args) {
        PermuteArray s = new PermuteArray();
        int[] arr = {1,2,3,4};
        s.applyPermutation(new int[]{2,0,1,3}, arr); // 2314

        List<Integer> list = Arrays.asList(1,2,3,4);
        s.applyPermutation1(Arrays.asList(2,0,1,3), list); // 2314
        System.out.println(list);
    }

    // O(n) - time, space
    void applyPermutation(int[] perm, int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < perm.length; i++) {
            res[perm[i]] = arr[i];
        }
        SolutionUtils.print(res);
    }

    // O(n) - time, O(1) - space
    public void applyPermutation1(List<Integer> perm, List<Integer> A) {
        for (int i = 0; i < A.size(); ++i) {
            int next = i;
            while (perm.get(next) >= 0) {
                Collections.swap(A, i, perm.get(next));
                int temp = perm.get(next);
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }
        for (int i = 0; i < perm.size(); i++) {
            perm.set(i, perm.get(i) + perm.size());
        }
    }
}
