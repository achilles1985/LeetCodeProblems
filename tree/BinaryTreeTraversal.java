package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import utils.TreeNode;

/** M
 *
 *         1
 *      /    \
 *     2      3
 *   /  \    /  \
 * 4    5   6    7
 *      /\        \
 *     9  8       11
 *   /
 * 10
 */
public class BinaryTreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(9);
        root.left.right.left.left = new TreeNode(10);
        root.right.right.right = new TreeNode(11);

        BinaryTreeTraversal s = new BinaryTreeTraversal();
        System.out.println(s.preOrderTraversalIterative(root)); // [1, 2, 4, 5, 9, 10, 8, 3, 6, 7, 11]
        System.out.println(s.inOrderTraversalIterative(root)); // [4, 2, 10, 9, 5, 8, 1, 6, 3, 7, 11]
        System.out.println(s.postOrderTraversalIterative(root)); // [4, 10, 9, 8, 5, 2, 6, 11, 7, 3, 1]

        System.out.println(s.preOrderTraversal(root)); // [1, 2, 4, 5, 9, 10, 8, 3, 6, 7, 11]
        System.out.println(s.inOrderTraversal(root)); // [4, 2, 10, 9, 5, 8, 1, 6, 3, 7, 11]
        System.out.println(s.postOrderTraversal(root)); // [4, 10, 9, 8, 5, 2, 6, 11, 7, 3, 1]
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);

        return result;
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);

        return result;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        postOrderTraversal(root, result);

        return result;
    }

    // O(n) - time, space
    public List<Integer> preOrderTraversalIterative(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode popped = stack.pop();
            curr = popped.right;
        }
        return result;
    }

    // O(n) - time, space
    public List<Integer> inOrderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }

    // O(n) - time, space
    public List<Integer> postOrderTraversalIterative(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            valueStack.push(node.val);
            if (node.left != null) {
                nodeStack.push(node.left);
            }
            if (node.right != null) {
                nodeStack.push(node.right);
            }
        }
        while (!valueStack.isEmpty()) {
            result.add(valueStack.pop());
        }

        return result;
    }

    public List<Integer> inOrderTraversalIterative2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (root.left != null) {
                stack.push(root.left);
                root = root.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
                root = node.right;
            }
        }

        return result;
    }

    private void preOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrderTraversal(root.left, result);
        preOrderTraversal(root.right, result);
    }

    private void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, result);
        result.add(root.val);
        inOrderTraversal(root.right, result);
    }

    private void postOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left, result);
        postOrderTraversal(root.right, result);
        result.add(root.val);
    }
}
