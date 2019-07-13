package bitManipulation.PowerOfTwo_231;

public class Solution {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        for (int i = 0; i < 32; i++) {
            if (n == (1 << i)) {
                return true;
            }
        }

        return false;
    }

    // use n&(n-1) trick
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n-1)) == 0;
    }
}
