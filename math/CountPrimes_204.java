package math;

/** E
 Count the number of prime numbers less than a non-negative number, n.

 Example:
 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes_204 {

    public static void main(String[] args) {
        CountPrimes_204 s = new CountPrimes_204();
        System.out.println(s.countPrimesBF(10)); // 4
    }

    // O(1) - space
    public int countPrimesBF(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) { // O(n^2) - time, if we use isPrime2() - O(n*√n) - time
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++) { // since the number must not be divisible by any number > n / 2
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrime2(int n) {
        for (int i = 2; i*i <= n; i++) { // we only need to consider factors up to √n
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
