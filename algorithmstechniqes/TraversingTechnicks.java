package algorithmstechniqes;

public class TraversingTechnicks {

    public static void main(String[] args) {
        TraversingTechnicks s = new TraversingTechnicks();
        System.out.println("First technic");
        s.first(new int[]{1,2,3,4,5}); //
        System.out.println("Second technic");
        s.second(new int[]{1,2,3,4,5}); //
        System.out.println("Third technic");
        s.third(new int[]{1,2,3,4,5}); //
    }

    private void first(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0, k = i; k < nums.length; j++, k++) {
                System.out.print(nums[j] + ":" + nums[k] + ";");
            }
            System.out.println();
        }
    }

    private void second(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(nums[k] + ",");
                }
                System.out.println();
            }
        }
    }

    private void third(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = j; j + i <= nums.length && k < j + i; k++) {
                    System.out.print(nums[k] + ",");
                }
                System.out.println();
            }
        }
    }

}
