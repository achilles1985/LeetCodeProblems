package bitManipulation.FindDifference_389;

public class Solution {

    // O(n + m) - time, O(1) - space
    public char findTheDifference(String s, String t) {
        int ans = 0;
        for (char c: s.toCharArray()) {
            ans ^= c;
        }

        for (char c: t.toCharArray()) {
            ans ^= c;
        }

        return (char) ans;
    }
}
