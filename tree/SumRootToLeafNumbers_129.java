package tree;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/** M
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRootToLeafNumbers_129 {

    public static void main(String[] args) {
        SumRootToLeafNumbers_129 s = new SumRootToLeafNumbers_129();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(s.sumNumbers(root));
    }

    // O(n) - time, space
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        sumNumbers(root, list);

        return 0;
    }

    private void sumNumbers(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        sumNumbers(root.left, list);
        list.add(root.val);
        sumNumbers(root.right, list);
    }
}
