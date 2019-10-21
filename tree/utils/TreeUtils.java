package tree.utils;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

public final class TreeUtils {

    private TreeUtils() { }

    public static void print(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode popped = queue.poll();
                System.out.print(popped.val + ",");
                if (popped.left != null) {
                    queue.add(popped.left);
                }
                if (popped.right != null) {
                    queue.add(popped.right);
                }
            }
            System.out.println();
        }
    }
}
