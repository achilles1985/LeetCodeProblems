package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 Given a list of list of Strings. Print cartesian product of lists.
 input -> {"Hello", "World"} , {"Game"}, {"Go","Home"}
 output ->
 Hello Game Go
 Hello Game Home
 World Game Go
 World Game Home
 */
/*
Solution: Take the first element from each row, then second and so on. Take the second element from the last row and so on.
 */
public class WordCombination {

    public static void main(String[] args) {
        WordCombination s = new WordCombination();
        //System.out.println(s.combine(new String [][]{{"Hello", "World"}, {"Game"}, {"One", "Two"}}));
        System.out.println(s.combine2(new String [][]{{"Hello", "World"}, {"Game"}, {"One", "Two"}}));
    }

    public List<List<String>> combine2(String[][] input) {
        List<List<String>> result = new ArrayList<>();
        combineUtils2(0, input, new ArrayList<>(), result);

        return result;
    }

    private void combineUtils2(int row, String[][] input, List<String> list, List<List<String>> result) {
        if (list.size() == input.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < input[row].length; i++) {
                list.add(input[row][i]);
                combineUtils2(row+1, input, list, result);
                list.remove(list.size()-1);
        }
    }

    public List<String> combine(String[][] input) {
        List<String> result = new ArrayList<>();
        combineUtils(0, input, new ArrayList<>(), result);

        return result;
    }

    private void combineUtils(int pos, String[][] input, ArrayList<String> list, List<String> result) {
        if (list.size() == input.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append(" ").append(list.get(i));
            }
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < input[pos].length; i++) {
            list.add(input[pos][i]);
            combineUtils(pos+1, input, list, result);
            list.remove(list.size()-1);
        }
    }


}
