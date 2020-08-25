package binarySearch.medium;

/**
 * M
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * Example:
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
 * received 0, 1, 3, 5, 6 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 * <p>
 * Note:
 * If there are several possible values for h, the maximum one is taken as the h-index.
 * Follow up:
 * This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
 * Could you solve it in logarithmic time complexity?
 */
public class H_Index_II_275 {

    public static void main(String[] args) {
        H_Index_II_275 s = new H_Index_II_275();
        System.out.println(s.hIndex(new int[] {0, 1, 3, 5, 6})); //3
        System.out.println(s.hIndex(new int[] {1, 1, 1, 1, 100})); //1
        System.out.println(s.hIndex(new int[] {1, 1, 2, 2, 3})); //2
        System.out.println(s.hIndex(new int[] {1, 1, 3, 4, 4, 6, 7, 7, 8})); //4
        System.out.println(s.hIndex(new int[] {0})); //0
        System.out.println(s.hIndex(new int[] {100})); //1
        System.out.println(s.hIndex(new int[] {1, 2})); //1
    }

    public int hIndexBF(int[] citations) {
        int idx = 0, n = citations.length;
        for (int c : citations) {
            if (c >= n - idx) {
                return n - idx;
            } else {
                idx++;
            }
        }
        return 0;
    }

    // If num[i] == size-i, we found H-index
    // O(log(n)) - time, O(1) - space
    public int hIndex(int[] citations) {
        int size = citations.length;
        int low = 0;
        int high = size - 1;
        int res = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (citations[mid] >= size - mid) {
                res = size - mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }
}
