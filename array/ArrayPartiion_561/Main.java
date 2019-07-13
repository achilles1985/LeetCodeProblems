package array.ArrayPartiion_561;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {2,4,1,3};
        int[] arr2 = new int[] {1,4,3,2};
        Solution solution = new Solution();
        System.out.println(solution.arrayPairSum(arr));
        System.out.println(solution.arrayPairSumBruteForth(arr2));
    }
}
