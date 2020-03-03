package bitManipulation.BitHacks;

public class Basics {

    public static void main(String[] args) {
        System.out.println(isEven(12)); // true
        System.out.println(isEven(3)); // false

        System.out.println(hasOpositeSign(-2, -7)); // false
        System.out.println(hasOpositeSign(2, 7)); // false
        System.out.println(hasOpositeSign(-2, 7)); // true
        System.out.println(hasOpositeSign(2, -7)); // true

        swapTwoNumbersNoThirdVariable(4,5);
        swapTwoNumbersNoThirdVariable(-4,5);
        swapTwoNumbersNoThirdVariable(143,678);

        System.out.println(clearAllBitsToTheLeft(21, 5)); //5
        System.out.println(clearAllBitsToTheRight(21, 3)); //16

        System.out.println(updateBit(21, 2, 1)); //23
        System.out.println(updateBit(21, 3, 0)); //17
    }

    private static boolean isEven(int num) {
        return (num & 1) == 0 ? true : false;
    }

    private static boolean hasOpositeSign(int n1, int n2) {
        return (n1 ^ n2) < 0;
    }

    private static void swapTwoNumbersNoThirdVariable(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("x=" + x + ", y=" + y);
    }

    private static int clearAllBitsToTheLeft(int num, int k) {
        int mask = (1 << k-1) - 1;
        return num & mask;
    }

    private static int clearAllBitsToTheRight(int num, int k) {
        int mask = (-1 << k);
        return num & mask;
    }

    private static int updateBit(int num, int position, int value) {
        return num & ~(1 << position-1) | (value << position-1);
    }
}
