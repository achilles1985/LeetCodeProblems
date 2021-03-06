package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationNoRepetitions {

    public static void main(String[] args) {
        CombinationNoRepetitions s = new CombinationNoRepetitions();
        System.out.println(s.combine(new int[] {1,2,3,4}, 2));
    }

    public List<List<Integer>> combine(int[] n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 0, new ArrayList<>(), result);

        return result;
    }

    private void dfs(int[] n, int k, int start, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < n.length; i++) {
            if (temp.contains(n[i])) {
                continue;
            }
            temp.add(n[i]);
            dfs(n, k, start+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }

}
