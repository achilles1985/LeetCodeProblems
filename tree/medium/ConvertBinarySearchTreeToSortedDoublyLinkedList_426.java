package tree.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import tree.utils.Node;
import utils.TreeNode;

/** M
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers
 * in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList_426 {

    // O(n) - time, O(h) - space
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root;
        while (head.left != null) {
            head = head.left;
        }
        TreeNode tail = dfs(root, new TreeNode(-1));
        tail.right = head;
        head.left = tail;
        return head;
    }

    private TreeNode dfs(TreeNode node, TreeNode prev) {
        if (node == null) {
            return null;
        }
        TreeNode left = dfs(node.left, prev);
        prev = (left == null) ? prev : left;
        prev.right = node;
        node.left = prev;

        TreeNode right = dfs(node.right, node);
        return (right != null) ? right : node;
    }

    // O(n) - time, O(h) - space
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root, head = null, tail = null;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (head == null) {
                head = curr;
            } else {
                tail.right = curr;
                curr.left = tail;
            }
            tail = curr;
            curr = curr.right;
        }

        head.left = tail;
        tail.right = head;

        return head;
    }

    public static void main(String[] args) {
        ConvertBinarySearchTreeToSortedDoublyLinkedList_426 s = new ConvertBinarySearchTreeToSortedDoublyLinkedList_426();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(10);

        System.out.println(s.treeToDoublyList2(root));
    }
}
