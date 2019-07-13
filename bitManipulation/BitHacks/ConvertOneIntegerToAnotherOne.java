package bitManipulation.BitHacks;

/**
 Conversion: Write a function to determine the number of bits you would need to flip to convert
 integer A to integer B.

 EXAMPLE
 Input: 29 (or: 111(1), 15 (or: (1111)
 Output: 2
 */
public class ConvertOneIntegerToAnotherOne {

    public static void main(String[] args) {
        System.out.println(countBitsDifferent(29, 15));
    }

    private static int countBitsDifferent(int a, int b) {
        int count = 0;
        for (int i = a^b; i > 0; i >>= 1) {
            count += (i & 1);
        }

        return count;
    }

}
