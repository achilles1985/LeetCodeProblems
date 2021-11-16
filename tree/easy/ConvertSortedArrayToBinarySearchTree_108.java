package tree.easy;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** E [marked]
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArrayToBinarySearchTree_108 {

    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree_108 s = new ConvertSortedArrayToBinarySearchTree_108();
        TreeNode root4 = s.sortedArrayToBST(new int[] {1, 2, 2, 3, 3, 4, 5});
        TreeUtils.print(s.sortedArrayToBST(new int[]{1,2,4,5,7,8,9}));
        TreeUtils.print(s.sortedArrayToBST(new int[]{-10,-3,0,5,9}));
        TreeNode root = s.sortedArrayToBST(new int[] {1,1,1,1,1,1,2,2,2,2,2,3,3,4,5,6,7});

        TreeUtils.print(root);
    }

    private TreeNode unsortedArrayToBST(int[] arr) {
        return unsortedArrayToBST(arr, 0, arr.length);
    }

    private TreeNode unsortedArrayToBST(int[] arr, int i, int j) {
        if (i <= j) {
            return null;
        }
        TreeNode root = new TreeNode(arr[i]);
        root.left = unsortedArrayToBST(arr, i+1, j);
        root.right = unsortedArrayToBST(arr, i, j-1);
        return root;
    }

    // O(n) - time, O(h) - space
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid-1);
        node.right = sortedArrayToBST(nums, mid + 1, end);

        return node;
    }
}
