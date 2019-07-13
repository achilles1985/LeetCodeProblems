package graph.WordLadder_127;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"))); // 5
        System.out.println(s.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"))); // 0
        System.out.println(s.ladderLength("lost", "miss", Arrays.asList("most","mist","miss","lost","fist", "fish"))); // 4
    }
}
