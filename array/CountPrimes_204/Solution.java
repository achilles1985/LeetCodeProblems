package array.CountPrimes_204;

import java.util.ArrayList;
import java.util.List;

/**
 * 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * <p>
 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 * https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%AD%D1%80%D0%B0%D1%82%D0%BE%D1%81%D1%84%D0%B5%D0%BD%D0%B0
 * https://en.wikipedia.org/wiki/Sieve_of_Atkin
 * http://stackoverflow.com/questions/453793/which-is-the-fastest-algorithm-to-find-prime-numbers
 */

public class Solution {

    // O(n^2)
    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime2(i)) {
                primes.add(i);
            }
        }
        return primes.size();
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    boolean isPrime2(int n) {
        //check if n is a multiple of 2
        if (n % 2 == 0) {
            return false;
        }
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
