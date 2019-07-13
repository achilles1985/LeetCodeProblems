package graph.WordLadderII_126;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/** H
 Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 Only one letter can be changed at a time
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

 Note:
 Return an empty list if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:
 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]
 Output:
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]

 Example 2:
 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]
 Output: []

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class Solution {

    // https://leetcode.com/problems/word-ladder-ii/discuss/297620/JAVA-BFS-%2B-DFS
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length();
        // preprocessing for the intermediate words
        // the adjacency map provides the adjacent words index for the words in wordList
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        List<String> newWordList = new ArrayList<>();
        newWordList.add(beginWord);
        for (String word : wordList) {
            if (!word.equals(beginWord)) {
                newWordList.add(word);
            }
        }
        Map<String, Integer> wordDict = new HashMap<>();
        for (int i = 0; i < newWordList.size(); i ++) {
            wordDict.put(newWordList.get(i), i);
            neighbors.put(i, new ArrayList<>());
        }
        for (int i = 0; i < newWordList.size(); i ++) {
            for (int j = 0; j < L; j++) {
                char[] chars = newWordList.get(i).toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[j] = c;
                    String newWord = new String(chars);
                    if (wordDict.containsKey(newWord) && i != wordDict.get(newWord)) {
                        List<Integer> adjacencies = neighbors.get(i);
                        adjacencies.add(wordDict.get(newWord));
                    }
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        // end index
        int end = newWordList.indexOf(endWord);
        // corner case
        if (end == 0 || end == -1) return result;
        // bfs
        Map<Integer, Integer> ladders = new HashMap<>();
        bfs(neighbors, ladders, end);
        // combine
        List<String> solution = new ArrayList<>();
        dfs(newWordList, neighbors, solution, result, ladders, 0, end);
        return result;
    }
    // breadth first search
    private void bfs( Map<Integer, List<Integer>> neighbors, Map<Integer, Integer> ladders, int end) {
        Queue<Integer> Q = new LinkedList<>();
        boolean reachEnd =false;
        Q.add(0);
        ladders.put(0, 1);
        while (!reachEnd && end != -1 && !Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i ++) {
                Integer cur = Q.poll();
                if (cur == end) reachEnd = true;
                for (Integer next  : neighbors.get(cur)) {
                    if (!ladders.containsKey(next)) {
                        Q.add(next);
                        ladders.put(next, ladders.get(cur) + 1);
                    }
                }
            }
        }

    }

    private void dfs(List<String> newWordList, Map<Integer, List<Integer>> neighbors, List<String> solution, List<List<String>> result, Map<Integer, Integer> ladders, int cur, int end) {
        solution.add(newWordList.get(cur));
        if (cur == end) {
            result.add(new ArrayList<>(solution));
        } else {
            for (int next : neighbors.get(cur)){
                if (ladders.containsKey(next) && ladders.get(next) == ladders.get(cur) + 1) {
                    dfs(newWordList, neighbors, solution, result, ladders, next, end);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }
}
