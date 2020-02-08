package tree;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** M
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
 * node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 *     Search for a node to remove.
 *     If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * Example:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * Another valid answer is [5,2,6,null,4,null,7].
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 */
/*
 if element to be deleted has left and right subtrees, find next greater element in the right subtree,
 nextGreater.left = element.left, root.left = element.rightChild or root.right == element.leftChild
 */
public class DeleteNodeInBST_450 {

    public static void main(String[] args) {
        DeleteNodeInBST_450 s = new DeleteNodeInBST_450();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        TreeUtils.print(s.deleteNode(root, 3));
    }

    // O(n) - time, space
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) { // root is the key
            return mergeLeft(root.left, root.right);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode mergeLeft(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        TreeNode iterator = rightNode; // looks for next greater
        while (iterator.left != null) {
            iterator = iterator.left;
        }
        iterator.left = leftNode;
        return rightNode;
    }

}
