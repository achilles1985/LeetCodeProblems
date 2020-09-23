package tree.easy;

import utils.TreeNode;

/**E
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder
 * traversing way.
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty
 * parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary
 * tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 * Output: "1(2(4))(3)"
 * Explanation: Originally it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 *
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and
 * the output.
 */
public class ConstructStringFromBinaryTree_606 {

    public static void main(String[] args) {
        ConstructStringFromBinaryTree_606 s = new ConstructStringFromBinaryTree_606();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(4);

        System.out.println(s.tree2str(root));
        System.out.println(s.tree2str(root1));
    }

    //O(n) - time, O(h) - space, where h - height of the tree, h=log(n) - for balanced, n - skewed one.
    public String tree2str(TreeNode t) {
        if (t == null ) {
            return "";
        }
        if (t.left == null && t.right == null) {
            return t.val + "";
        }
        if (t.right == null) {
            return t.val + "(" + tree2str(t.left) + ")";
        }
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }
}
