package dynamic;

import java.util.Arrays;

public class FibSequence {

    public static void main(String[] args) {
        FibSequence s = new FibSequence();
        System.out.println(s.fibBruteForce(5));
        System.out.println(s.fibDynamicTopDown(5));
        System.out.println(s.fibDynamicBottomUp(5));
        System.out.println(s.fibDynamicBottomUp2(5));
    }

    // 0(n!) - time, O(n) - space?
    public int fibBruteForce(int n) {
        if (n <= 1) {
             return 1;
        }

        int left = fibBruteForce(n-1);
        int right = fibBruteForce(n-2);
        return left + right;
    }

    // O(n) - time, O(n) - space
    public int fibDynamicTopDown(int n) {
        int[] cache = new int[n+1];
        Arrays.fill(cache, -1);
        cache[0] = 1; cache[1] = 1;

        return fibDynamicTopDownUtils(n, cache);
    }

    private int fibDynamicTopDownUtils(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }

        int val = fibDynamicTopDownUtils(n - 1, cache) + fibDynamicTopDownUtils(n - 2, cache);
        cache[n] = val;

        return cache[n];
    }

    // O(n) - time, O(n) - space
    public int fibDynamicBottomUp(int n) {
        int[] cach = new int[n+1];
        cach[0] = 1;
        cach[1] = 1;
        for (int i = 2; i <= n; i++) {
            cach[i] = cach[i-1] + cach[i-2];
        }

        return cach[n];
    }

    // O(n) - time, O(1) - space
    public int fibDynamicBottomUp2(int n) {
        int fib1 = 1, fib2 = 1;
        for (int i = 2; i <= n; i++) {
            int fib = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib;
        }

        return fib2;
    }
}
