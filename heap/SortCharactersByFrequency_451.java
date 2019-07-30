package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Given a string, sort it in decreasing order based on the frequency of characters.

 Example 1:
 Input:
 "tree"
 Output:
 "eert"

 Explanation:
 'e' appears twice while 'r' and 't' both appear once.
 So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

 Example 2:
 Input:
 "cccaaa"
 Output:
 "cccaaa"

 Explanation:
 Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 Note that "cacaca" is incorrect, as the same characters must be together.

 Example 3:
 Input:
 "Aabb"
 Output:
 "bbAa"

 Explanation:
 "bbaA" is also a valid answer, but "Aabb" is incorrect.
 Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency_451 {

    public static void main(String[] args) {
        SortCharactersByFrequency_451 s = new SortCharactersByFrequency_451();
        System.out.println(s.frequencySort("tree")); // eetr
        System.out.println(s.frequencySort("cccaa")); // cccaa
        System.out.println(s.frequencySort("Aabb")); // bbAa
    }

    // O(k*log(k)) - time, O(k) - space, k - number of unique chars
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        Queue<Character> queue = new PriorityQueue<>((e1, e2) -> map.get(e2) - map.get(e1));
        for (Character key: map.keySet()) {
            queue.add(key);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character key = queue.poll();
            int count = map.get(key);
            while (count > 0) {
                sb.append(key);
                count--;
            }
        }

        return sb.toString();
    }
}
