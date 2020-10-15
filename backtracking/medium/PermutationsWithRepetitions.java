package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class PermutationsWithRepetitions {

    public static void main(String[] args) {
        PermutationsWithRepetitions s = new PermutationsWithRepetitions();
        System.out.println(s.permute(new int[] {1,2,3}, 2));
        System.out.println(s.permute(new int[] {1,2,3,4}, 3));
    }

    private List<List<Integer>> permute(int[] n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        permute(n, k, new ArrayList<>(), result);

        return result;
    }

    private void permute(int[] n, int k, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < n.length; i++) {
            temp.add(n[i]);
            permute(n, k, temp, result);
            temp.remove(temp.size()-1);
        }
    }
}
