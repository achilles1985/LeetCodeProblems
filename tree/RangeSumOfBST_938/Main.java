package tree.RangeSumOfBST_938;

import utils.BinarySearchTree;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        BinarySearchTree tree = new BinarySearchTree();
        // 10,5,15,3,7,null,18
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(18);

        System.out.println(s.rangeSumBST(tree.root, 7, 15));

    }
}
