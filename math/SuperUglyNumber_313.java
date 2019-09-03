package math;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/** M
 Write a program to find the nth super ugly number.
 Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

 Example:
 Input: n = 12, primes = [2,7,13,19]
 Output: 32
 Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
 super ugly numbers given primes = [2,7,13,19] of size 4.

 Note:
 1 is a super ugly number for any given primes.
 The given numbers in primes are in ascending order.
 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
public class SuperUglyNumber_313 {

    public static void main(String[] args) {
        SuperUglyNumber_313 s = new SuperUglyNumber_313();
        System.out.println(s.nthSuperUglyNumber(12, new int[]{2,7,13,19})); // 32
    }

    public int nthSuperUglyNumber1(int n, int[] primes) {
        if (n < 0) {
            return -1;
        }
        SortedSet<Integer> set = new TreeSet<>();
        set.add(1);
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < primes.length; j++) {
               heap.offer(i * primes[j]);
            }
            set.add(heap.poll());
        }

        while (!heap.isEmpty() && set.size() < n) {
            set.add(heap.poll());
        }

        return set.last();
    }

    // https://leetcode.com/problems/super-ugly-number/discuss/335658/Java-O(nk)-solution
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(primes == null || primes.length == 0) {
            return 0;
        }
        int[] res = new int[n];
        int[] index = new int[primes.length];
        res[0] = 1;

        for(int i=1; i<n; i++){
            res[i] = Integer.MAX_VALUE;
            for(int j=0; j<primes.length; j++){
                int idx = index[j];
                res[i] = Math.min(res[i], res[idx]*primes[j]);//get next
            }
            for(int j=0; j<primes.length; j++){
                if(res[i] == res[index[j]]*primes[j]){
                    index[j]++;//move the used one
                }
            }
        }
        return res[n-1];
    }
}
