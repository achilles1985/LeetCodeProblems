package tree.MergeTwoBinaryTrees_617;

import utils.BinarySearchTree;
import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree t1 = new BinarySearchTree();
        BinarySearchTree t2 = new BinarySearchTree();
        t1.insertAll(new int[] {1, 3, 2, 5});
        t2.insertAll(new int[] {2, 1, 3, 4, 7});

        Solution s = new Solution();
        TreeNode merged = s.mergeTrees(t1.root, t2.root);
    }
}
