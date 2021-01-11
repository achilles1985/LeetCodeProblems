package heap.medium;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 If possible, output any possible result.  If not possible, return the empty string.

 Example 1:
 Input: S = "aab"
 Output: "aba"

 Example 2:
 Input: S = "aaab"
 Output: ""

 Note:
 S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString_767 {

    public static void main(String[] args) {
        ReorganizeString_767 s = new ReorganizeString_767();
        System.out.println(s.reorganizeString("aab")); // "aba"

        System.out.println(s.reorganizeString("aaab")); // ""
        System.out.println(s.reorganizeString("aaaabcd")); // "abacada"
        System.out.println(s.reorganizeString("vvvlo")); // "vovlv"
    }

    // O(n) - time, O(1) - space
    public String reorganizeString(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            freq.put(c, freq.getOrDefault(c,0)+1);
        }

        int max = Collections.max(freq.values());
        if (max > (S.length() + 1)/2) {
            return "";
        }

        Queue<FreqEntry> maxHeap = new PriorityQueue<>((e1,e2) -> e2.count-e1.count);
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            maxHeap.add(new FreqEntry(entry.getKey(), entry.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            FreqEntry entry = maxHeap.poll(); //in this case we use most frequent char
            if (sb.length() == 0 || sb.charAt(sb.length()-1) != entry.symbol) {
                sb.append(entry.symbol);
                if (entry.count-1 > 0) {
                    maxHeap.add(new FreqEntry(entry.symbol, entry.count-1));
                }
            } else { //if most frequent one has been used previously - use next one from the min heap
                if (!maxHeap.isEmpty()) {
                    FreqEntry entry2 = maxHeap.poll();
                    sb.append(entry2.symbol);
                    if (entry2.count-1 > 0) {
                        maxHeap.add(new FreqEntry(entry2.symbol, entry2.count-1));
                    }
                }
                maxHeap.add(entry); //put back most frequent one for next iterations
            }
        }
        return sb.toString();
    }

    // O(n) - time, O(1) - space, since we deal only with 26 letters
    public String reorganizeString2(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) { //n
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        }

        Queue<Character> heap = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));
        for (Character key: map.keySet()) { //26
            heap.add(key);
        }

        StringBuilder sb = new StringBuilder();
        char prev = '_';
        while (!heap.isEmpty()) { //26
            char cur = heap.poll();
            sb.append(cur);
            map.put(cur, map.get(cur)-1);
            if (prev != '_' && map.get(prev) > 0) {
                heap.add(prev);
            }
            prev = cur;
        }

        return sb.toString().length() == S.length() ? sb.toString() : "";
    }

    // https://leetcode.com/problems/reorganize-string/solution/
    public String reorganizeString3(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) {
            counts[c-'a'] += 100;
        }
        for (int i = 0; i < 26; ++i) {
            counts[i] += i;
        }
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) {
                return "";
            }
            for (int i = 0; i < ct; ++i) {
                if (t >= N) {
                    t = 0;
                }
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }

    private static class FreqEntry {
        char symbol;
        int count;

        public FreqEntry(char symbol, int count) {
            this.symbol = symbol;
            this.count = count;
        }
    }
}
