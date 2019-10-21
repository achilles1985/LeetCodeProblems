package utils;

public class MyBinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    private static class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    public boolean exist(T data) {
        return exist(root, data);
    }

    public boolean isBST(TreeNode<T> root) {
        return isBST(root, null, null);
    }

    public void print() {
        print(root);
        System.out.println();
    }

    private boolean isBST(TreeNode<T> root, T min, T max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.data.compareTo(min) <= 0) || (max != null && root.data.compareTo(max) > 0)) {
            return false;
        }
        if (!isBST(root.left, min, root.data)) {
            return false;
        }
        if (!isBST(root.right, root.data, max)) {
            return false;
        }
        return true;
    }

    private boolean exist(TreeNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        if (data.compareTo(root.data) < 0) {
            return exist(root.left, data);
        }
        if (data.compareTo(root.data) > 0) {
            return exist(root.right, data);
        }
        return true;
    }

    private TreeNode<T> insert(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    private void print(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + ", ");
        print(root.left);
        print(root.right);
    }

    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.insert(10);
        tree.insert(7);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(9);
        tree.insert(12);
        tree.print();

        System.out.println(tree.exist(4)); // true;
        System.out.println(tree.exist(1)); // false

        System.out.println(tree.isBST(tree.root)); // true
    }
}
