package graph.WordLadder_127;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * M
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:
 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5
 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Example 2:
 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0
 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class Solution {

    /*
        Time Complexity: O(M×N), where M is the length of words and N is the total number of words in the input word list.
        Finding out all the transformations takes M iterations for each of the N words. Also, breadth first search in the worst case might go to each of the N words.

        Space Complexity: O(M×N), to store all M transformations for each of the N words, in the dictionary.
        Visited dictionary is of N size. Queue for BFS in worst case would need space for all N words.
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> dictionary = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + '*' + word.substring(i+1);
                dictionary.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
                //List<String> words = dictionary.getOrDefault(key, new ArrayList<>());
                //words.add(word);
                //dictionary.put(key, words);
            }
        }

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(new Vertex(beginWord, 1));
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            String currentWord = current.getWord();
            Integer currentLevel = current.getLevel();
            if (currentWord.equals(endWord)) {
                return currentLevel;
            }
            for (int i = 0; i < currentWord.length(); i++) {
                String key = currentWord.substring(0, i) + '*' + currentWord.substring(i+1);
                List<String> curWords = dictionary.getOrDefault(key, new ArrayList<>());
                for (String curWord : curWords) {
                    if (!visited.contains(curWord)) {
                        queue.add(new Vertex(curWord, currentLevel + 1));
                        visited.add(curWord);
                    }
                }
            }
        }
        return 0;
    }

    private static class Vertex {
        String word;
        int level;

        public Vertex(String word, int level) {
            this.word = word;
            this.level = level;
        }

        public String getWord() {
            return word;
        }

        public int getLevel() {
            return level;
        }
    }

}
