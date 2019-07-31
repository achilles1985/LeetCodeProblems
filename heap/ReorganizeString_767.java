package heap;

import java.util.Arrays;
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
        System.out.println(s.reorganizeString2("aab")); // "aba"
        System.out.println(s.reorganizeString("aaab")); // ""
        System.out.println(s.reorganizeString("a")); // "a"
        System.out.println(s.reorganizeString("vvvlo")); // "vovlv"
    }

    // O(n*log(n)) - time, O(n) - space
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        }

        Queue<Character> heap = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));
        for (Character key: map.keySet()) {
            heap.add(key);
        }

        StringBuilder sb = new StringBuilder();
        char prev = '_';
        while (!heap.isEmpty()) {
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
    public String reorganizeString2(String S) {
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
}
