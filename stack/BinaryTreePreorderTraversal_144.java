package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import utils.TreeNode;

/** M
 Given a binary tree, return the preorder traversal of its nodes' values.

 Example:
 Input: [1,null,2,3]
    1
     \
      2
     /
    3

 Output: [1,2,3]

 Input: [1,2,3,4,5,6,7]
         1
       /  \
      2    3
     / \   /
    4   5 6
   /
 7
 Output: [1,2,4,5,3,6]

 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal_144 {

    public static void main(String[] args) {
        BinaryTreePreorderTraversal_144 s = new BinaryTreePreorderTraversal_144();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.right.left = new TreeNode(6);

        System.out.println(s.preorderTraversal(root));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }
}
