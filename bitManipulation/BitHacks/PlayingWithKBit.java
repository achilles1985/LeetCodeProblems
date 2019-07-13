package bitManipulation.BitHacks;

public class PlayingWithKBit {

    public static void main(String[] args) {
        System.out.println(turnOffKthBit(10, 2)); // 8
        System.out.println(turnOffKthBit(8, 4)); // 0
        System.out.println(turnOffKthBit(170, 6)); // 138

        System.out.println(turnOnKthBit(10, 3)); // 14
        System.out.println(turnOnKthBit(8, 4)); // 8
        System.out.println(turnOnKthBit(170, 7)); // 234

        System.out.println(checkIfKthBitIsSet(10, 2)); // true
        System.out.println(checkIfKthBitIsSet(10, 3)); // false

        System.out.println(unsetRightMostBit(5)); // 4
        System.out.println(unsetRightMostBit(20)); // 16

        System.out.println(isPowerOf2(20)); // false
        System.out.println(isPowerOf2(16)); // true
        System.out.println(isPowerOf2(1)); // false

        System.out.println(findPositionOfRightMostBit(12)); // 3
        System.out.println(findPositionOfRightMostBit(20)); // 3
        System.out.println(findPositionOfRightMostBit(0)); // 0
        System.out.println(findPositionOfRightMostBit(1)); // 1

        System.out.println(findPositionOfOnlyBit(1)); // 1
        System.out.println(findPositionOfOnlyBit(16)); // 5

        System.out.println(isOddNumberOf1Bit(16)); // false
        System.out.println(isOddNumberOf1Bit(31)); // false
        System.out.println(isOddNumberOf1Bit(5)); // true

        System.out.println(toLowerCase('A')); // a
        System.out.println(toUpperCase('a')); // A

        System.out.println(findAbsValue(11)); // 11
        System.out.println(findAbsValue(-11)); // 11

        System.out.println(isPolindrom(9)); // true
        System.out.println(isPolindrom(4)); // false
        System.out.println(isPolindrom(27)); // true

    }

    private static int turnOffKthBit(int num, int k) {
        return num ^ (1 << (k - 1));
    }

    private static int turnOnKthBit(int num, int k) {
        return num | (1 << (k - 1));
    }

    private static boolean checkIfKthBitIsSet(int num, int k) {
        return (num & (1 << (k - 1))) != 0 ? true : false;
    }

    private static int unsetRightMostBit(int num) {
        return num & (num - 1);
    }

    private static boolean isPowerOf2(int num) {
        return (num & (num - 1)) == 0;
    }

    private static int findPositionOfRightMostBit(int num) {
        num = num ^ (num & (num - 1));
        int count = 0;
        while (num > 0) {
            num >>= 1;
            count++;
        }

        return count;
    }

    private static int findPositionOfOnlyBit(int num) {
        return (int ) (Math.log10(num) / Math.log10(2) + 1);
    }

    private static boolean isOddNumberOf1Bit(int num) {
        boolean odd = false;
        while (num > 0) {
            odd = !odd;
            num  = num & (num - 1);
        }

        return odd;
    }

    private static char toLowerCase(char c) {
        return (char) (c | 32);
    }

    private static char toUpperCase(char c) {
        return (char) (c ^ 32);
    }

    private static int findAbsValue(int num) {
        int mask = num >> 31;
        return (num + mask) ^ mask;
    }

    private static boolean isPolindrom(int num) {
        // the trick is to reverse the bits and check if they are the same
        int rev = 0;
        int temp = num;
        while (temp > 0) {
            rev = (rev << 1) | (temp & 1);
            temp >>= 1;
        }

        return num == rev;
    }

    private static int findPositionOfRightMostBit2(int num) {
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                return i;
            }
        }
        return -1;
    }
}
