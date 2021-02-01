package graph.medium;

import java.util.*;

/**
 * M [marked]
 Given two words (beginWord and endWord), and a dictionary's word list,
 find the length of shortest transformation sequence from beginWord to endWord, such that:
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
/*
Mistakes:
    1. Convert given word (hit) to *it, h*t, hi*. be careful with determining start/end parts:
              for (int i = 0; i < currentWord.length(); i++) {
                String start = i == 0 ? "" : currentWord.substring(0,i);
                String end = i + 1 < currentWord.length() ? currentWord.substring(i+1): "";
                for (int j = 0; j < 26; j++) {
                    String modString = start + (char) (j + 'a') + end;
 */
public class WordLadder_127 {

    public static void main(String[] args) {
        WordLadder_127 s = new WordLadder_127();
        System.out.println(s.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"))); // 5
        System.out.println(s.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"))); // 0
        System.out.println(s.ladderLength("lost", "miss", Arrays.asList("most","mist","miss","lost","fist", "fish"))); // 4
    }

    // O(n*m) - time, n - length of the word, m - total number of words. O(n) -space
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<WordDistance> queue = new LinkedList<>();
        queue.add(new WordDistance(beginWord, 1));
        set.add(beginWord);
        while (!queue.isEmpty()) { //n
            WordDistance entry = queue.poll();
            if (entry.getWord().equals(endWord)) {
                return entry.getDistance();
            }
            for (int i = 0; i < entry.getWord().length(); i++) { //len
                StringBuilder sb = new StringBuilder(entry.getWord());
                for (int j = 0; j < 26; j++) {
                    sb.setCharAt(i, (char)(j+'a'));
                    String newWord = sb.toString();
                    if (set.contains(newWord)) {
                        queue.add(new WordDistance(newWord, entry.getDistance() + 1));
                        set.remove(newWord);
                    }
                }
            }
        }

        return 0;
    }

    // O(n*m) - time, n - length of the word, m - total number of words. O(n) -space
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.isEmpty()) {
            return 0;
        }
        Set<String> dictionary = new HashSet<>(wordList);
        Queue<StringWithDistance> queue = new LinkedList<>();
        queue.add(new StringWithDistance(beginWord, 1));
        while (!queue.isEmpty()) {
            StringWithDistance entry = queue.poll();
            if (entry.word.equals(endWord)) {
                return entry.distance;
            }
            String currentWord = entry.word;
            for (int i = 0; i < currentWord.length(); i++) {
                String start = i == 0 ? "" : currentWord.substring(0,i);
                String end = i + 1 < currentWord.length() ? currentWord.substring(i+1): "";
                for (int j = 0; j < 26; j++) {
                    String modString = start + (char) (j + 'a') + end;
                    if (dictionary.remove(modString)) {
                        queue.add(new StringWithDistance(modString, entry.distance + 1));
                    }
                }
            }
        }
        return  0;
    }

    private static class StringWithDistance {
        String word;
        int distance;

        StringWithDistance(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }

    private static class WordDistance {
        String word;
        int distance;

        WordDistance(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }

        String getWord() {
            return word;
        }
        int getDistance() {
            return distance;
        }
    }
}
