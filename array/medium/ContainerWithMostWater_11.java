package array.medium;

/** M [marked]
 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.
 The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 In this case, the max area of water (blue section) the container can contain is 49.

 Example:
 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49
 */
public class ContainerWithMostWater_11 {

    public static void main(String[] args) {
        ContainerWithMostWater_11 s = new ContainerWithMostWater_11();

        System.out.println(s.maxArea(new int[]{1,8,6,2,5,4,8,3,7})); //49
        System.out.println(s.maxArea(new int[]{1,2,1})); //2

        System.out.println(s.maxArea2(new int[]{1,8,6,2,5,4,8,3,7})); //49
        System.out.println(s.maxArea2(new int[]{1,2,1})); //2
    }

    // O(n^2) - time, O(1) - space
    public int maxArea(int[] nums) {
        int maxArea = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                int area;
                if (nums[j] < nums[i]) {
                   area = nums[j]*(i-j);
                } else {
                    area = nums[i]*(i-j);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    // O(n) - time, O(1) - space
    public int maxArea2(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int maxArea = 0;
        while (left < right) {
            int area;
            if (nums[left] < nums[right]) {
                area = nums[left]*(right-left);
                left++;
            } else {
                area = nums[right]*(right-left);
                right--;
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
