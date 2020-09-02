package array.easy;

import java.util.Arrays;

import utils.SolutionUtils;

/** E
 Given an array of DISTINCT elements, rearrange the elements of array in zig-zag fashion in O(n) time.
 The converted array should be in form a < b > c < d > e < f.

 Input: arr[] = {4, 3, 7, 8, 6, 2, 1}
 Output: arr[] = {3, 7, 4, 8, 2, 6, 1}

 Input: arr[] = {1, 4, 3, 2}
 Output: arr[] = {1, 4, 2, 3}
 */
public class ZigZagArrangement {

    public static void main(String[] args) {
        ZigZagArrangement s = new ZigZagArrangement();
        s.rearrange3(new int[]{4,3,7,8,6,2,1});
        s.rearrange3(new int[]{1,4,3,2});
    }

    public void rearrange3(int input[]) {
        if (input == null || input.length == 0) {
            return;
        }
        for (int i = 1; i < input.length; i+=2) {
            if (input[i] < input[i-1]) {
                swap(i, i-1, input);
            }
            if (i+1 < input.length && input[i] < input[i+1]) {
                swap(i, i+1, input);
            }
        }
    }

    // O(n) - time, O(1) - space
    public void rearrange2(int input[]) {
        boolean shouldBeLess = true;
        for (int i = 1; i < input.length; i++) {
            if (shouldBeLess) {
                if (input[i-1] > input[i]) {
                    swap(i-1, i, input);
                }
            } else {
                if (input[i-1] < input[i]) {
                    swap(i-1, i, input);
                }
            }
            shouldBeLess = !shouldBeLess;
        }

        SolutionUtils.print(input);
    }

    // O(n*log(n)) - time, O(1) - space
    public void rearrange(int input[]) {
        Arrays.sort(input);
        for (int i = 2; i < input.length; i += 2) {
            swap(i-1,i,input);
        }

        SolutionUtils.print(input);
    }

    private void swap(int i, int j, int[] input) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
