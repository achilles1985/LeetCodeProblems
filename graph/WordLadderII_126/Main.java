package graph.WordLadderII_126;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"))); // [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
        //System.out.println(s.findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"))); //  []
    }
}
