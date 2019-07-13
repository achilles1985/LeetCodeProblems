package bitManipulation;

public class Utils {

    public static void main(String[] args) {
        print(decimalToBinary(7));
        print(decimalToBinary(17));
    }

    public static int[] decimalToBinary(int decimal) {
        int[] binary = new int[32];
        int i = 0;
        while (decimal > 0) {
            binary[i++] = decimal%2;
            decimal = decimal/2;
        }
        int m = 0;
        int n = 31;
        while (n > m) {
            int temp = binary[m];
            binary[m] = binary[n];
            binary[n] = temp;
            m++;
            n--;
        }

        return binary;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }
}
