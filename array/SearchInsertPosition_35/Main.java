package array.SearchInsertPosition_35;

public class Main {

    public static void main(String[] args) {
        int i = 2%10;
        int arr[] = new int[] {1,3,5,6};
        int target = 22;
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(arr, target));
    }
}
