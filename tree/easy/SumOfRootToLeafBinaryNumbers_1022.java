package tree.easy;

import java.util.LinkedList;
import java.util.Queue;
import utils.TreeNode;

/** E
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
 * the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in
 * binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers.
 *
 * Example 1:
 * Input: [1,0,1,0,1,0,1]
 *       1
        / \
       0    1
      /\   / \
     0  1 0   1
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * Note:
 *     The number of nodes in the tree is between 1 and 1000.
 *     node.val is 0 or 1.
 *     The answer will not exceed 2^31 - 1.
 */
public class SumOfRootToLeafBinaryNumbers_1022 {

    public static void main(String[] args) {
        SumOfRootToLeafBinaryNumbers_1022 s = new SumOfRootToLeafBinaryNumbers_1022();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        System.out.println(s.sumRootToLeaf(root)); //22
        System.out.println(s.sumRootToLeafIterative(root)); //22
    }

    // O(n) - time, space
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }

    private int sumRootToLeaf(TreeNode root, Integer sum) {
        if (root == null) {
            return 0;
        }
        //sum = (sum << 1) | root.val;
        sum  = sum*2 + root.val; // the same as for 10 based numbers "123", sum = sum*10 + s[i]
        if (root.left == null && root.right == null) {
            return sum;
        }
        int leftSum = sumRootToLeaf(root.left, sum);
        int rightSum =  sumRootToLeaf(root.right, sum);

        return leftSum + rightSum;
    }

    public int sumRootToLeafIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> sums = new LinkedList<>();
        nodes.add(root);
        sums.add(root.val);
        int total = 0;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int sum = sums.poll();
            if (node.left == null && node.right == null) {
                total += sum;
            }

            if (node.left != null) {
                nodes.add(node.left);
                sums.add(sum*2 + node.left.val);
            }
            if (node.right != null) {
                nodes.add(node.right);
                sums.add(sum*2 + node.right.val);
            }
        }

        return total;
    }

    // O(n) - time, O(1) - space
    public int sumRootToLeafMorrisAlgorithm(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        int steps;
        TreeNode predecessor;

        while (root != null) {
            // If there is a left child,
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step to the left
                // and then to the right till you can.
                predecessor = root.left;
                steps = 1;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                    ++steps;
                }

                // Set link predecessor.right = root
                // and go to explore the left subtree
                if (predecessor.right == null) {
                    currNumber = (currNumber << 1) | root.val;
                    predecessor.right = root;
                    root = root.left;
                }
                // Break the link predecessor.right = root
                // Once the link is broken,
                // it's time to change subtree and go to the right
                else {
                    // If you're on the leaf, update the sum
                    if (predecessor.left == null) {
                        rootToLeaf += currNumber;
                    }
                    // This part of tree is explored, backtrack
                    for(int i = 0; i < steps; ++i) {
                        currNumber >>= 1;
                    }
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                currNumber = (currNumber << 1) | root.val;
                // if you're on the leaf, update the sum
                if (root.right == null) {
                    rootToLeaf += currNumber;
                }
                root = root.right;
            }
        }
        return rootToLeaf;
    }
}
