package bitManipulation.PowerOfFour_342;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isPowerOfFour3(16)); // true;
        System.out.println(s.isPowerOfFour2(5)); // false;
        System.out.println(s.isPowerOfFour(4)); // true;
        System.out.println(s.isPowerOfFour(64)); // true;
        System.out.println(s.isPowerOfFour(1)); // false;
        System.out.println(s.isPowerOfFour(101)); // false;
    }
}
