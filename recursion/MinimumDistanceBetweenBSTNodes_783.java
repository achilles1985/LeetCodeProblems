package recursion;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** E
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

 Example :
 Input: root = [4,2,6,1,3,null,null]
 Output: 1
 Explanation:
 Note that root is a TreeNode object, not an array.

 The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
      4
    /   \
   2      6
 / \
1   3

 while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.

 Note:
 The size of the BST will be between 2 and 100.
 The BST is always valid, each node's value is an integer, and each node's value is different.
 */
public class MinimumDistanceBetweenBSTNodes_783 {

    // O(n) - time, O(n) - space
    public int minDiffInBST(TreeNode root) {
        List<Integer> numbers = new ArrayList<>();
        inorder(root, numbers);

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < numbers.size(); i++) {
            min = Math.min(min, numbers.get(i) - numbers.get(i-1));
        }

        return min;
    }

    private void inorder(TreeNode root, List<Integer> numbers) {
        if (root == null) {
            return;
        }

        inorder(root.left, numbers);
        numbers.add(root.val);
        inorder(root.right, numbers);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(6);
        root.left = n1;
        root.right = n4;
        root.left.left = n2;
        root.left.right = n3;

        MinimumDistanceBetweenBSTNodes_783 s = new MinimumDistanceBetweenBSTNodes_783();
        System.out.println(s.minDiffInBST(root));
    }
}
