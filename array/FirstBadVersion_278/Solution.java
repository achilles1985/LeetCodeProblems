package array.FirstBadVersion_278;

public class Solution {

    public int firstBad(int distance) {
        return firstBad(1, distance);
    }

    private int firstBad(int start, int end) {
        if (start > end) {
            return start;
        }

        int middle = start + (end-start)/2;
        if (isBad(middle)) {
            return firstBad(start, middle-1);
        } else {
            return firstBad(middle+1, end);
        }
    }

    private boolean isBad(int e) {
        return e >= 2 ? true : false;
    }
}
