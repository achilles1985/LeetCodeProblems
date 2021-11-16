package tree.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.TreeNode;

/** M [marked]
 *      1
 *    /  \
 *   2    3
 *  /    / \
 * 4    2   4
 *     /
 *    4
 * Given the root of a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with the same node values.
 */
public class FindDuplicateSubtrees_652 {

    public static void main(String[] args) {
        FindDuplicateSubtrees_652 s = new FindDuplicateSubtrees_652();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        System.out.println(s.findDuplicateSubtrees(root)); //4,2
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        helper(root, map, result);

        return result;
    }

    private String helper(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
        if (root == null) {
            return "X";
        }
        String left = helper(root.left, map, result);
        String right = helper(root.right, map, result);
        String serial = root.val + "->" + left + "->" + right;
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) {
            result.add(root);
        }

        return serial;
    }
}
