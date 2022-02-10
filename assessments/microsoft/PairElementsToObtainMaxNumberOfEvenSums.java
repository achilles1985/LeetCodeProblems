package assessments.microsoft;

/**
 * You are given N numbers on a circle, describe by an array A.
 * Find the maximum number of neibouring pairs whose sum are even.
 *
 * Example 1
 * Given [4,2,5,8,7,3,7]
 * Output: 2
 * (A[0], A[1]), (A[4], A[5])
 *
 *  Example 2
 *  Given [14,21,16,35,22]
 *  Output: 1
 *  (A[0], A[4])
 *
 * Example 1
 * Given [5,5,5,5,5,5]
 * Output: 3
 * (A[0], A[5]), (A[1], A[2]), (A[3], A[4])
 */
public class PairElementsToObtainMaxNumberOfEvenSums {

    public static void main(String[] args) {
        PairElementsToObtainMaxNumberOfEvenSums s = new PairElementsToObtainMaxNumberOfEvenSums();
        System.out.println(s.solution(new int[]{14,21,16,35,22})); //1
        System.out.println(s.solution(new int[]{2,4,5,8,7,3,7})); //2
        System.out.println(s.solution(new int[]{5,5,5,5,5,5})); //3
        System.out.println(s.solution(new int[]{2,2,2,2})); //2
        System.out.println(s.solution(new int[]{2,2,2,2,2})); //2
    }

    public int solution(int[] A) {
        int[] pairSum = new int[A.length];
        int sum = A[0] + A[1];
        int j = 0;
        pairSum[j++] = sum;
        for (int i = 2; i < A.length; i++) {
            sum += A[i];
            sum -= A[i-2];
            pairSum[j++] = sum;
        }
        int count = 0;
        for (int i = 0; i < pairSum.length;) {
            if (i == pairSum.length - 1 && pairSum[0]%2 == 0) {
                i++;
                continue;
            }
            if (pairSum[i]%2 == 0) {
                count++;
                i+=2;
            } else {
                i++;
            }
        }
        return count;
    }
}
