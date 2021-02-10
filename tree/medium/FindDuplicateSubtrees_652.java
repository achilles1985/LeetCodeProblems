package tree.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.TreeNode;

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

        System.out.println(s.findDuplicateSubtrees(root));
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
