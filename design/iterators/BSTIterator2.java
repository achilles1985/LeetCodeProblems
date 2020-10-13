package design.iterators;

import java.util.Stack;

import utils.TreeNode;

// https://leetcode.com/problems/binary-search-tree-iterator/

/** M [MARKED]
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
        7
       / \
      3   15
         / \
        9   20
 Example:
 BSTIterator iterator = new BSTIterator(root);
 iterator.next();    // return 3
 iterator.next();    // return 7
 iterator.hasNext(); // return true
 iterator.next();    // return 9
 iterator.hasNext(); // return true
 iterator.next();    // return 15
 iterator.hasNext(); // return true
 iterator.next();    // return 20
 iterator.hasNext(); // return false

 Note:
 next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class BSTIterator2 {
    private Stack<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
        stack = new Stack<>();
        populate(root);
    }

    // O(h) - time, space
    private void populate(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    // O(1) - time, space
    public int next() {
        TreeNode curr = stack.pop();
        if (curr.right != null) {
            populate(curr.right);
        }
        return curr.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator2 iterator = new BSTIterator2(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }


}
