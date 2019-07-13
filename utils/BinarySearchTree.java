package utils;

public class BinarySearchTree {

    public TreeNode root;

    public void insert(Integer data) {
        if (root == null) {
            root = new TreeNode(data);
            return;
        }
        insert(data, root);
    }

    public boolean exists(int data) {
        return exists(data, root);
    }

    public void delete(int data) {
        delete(root, data);
    }

    public TreeNode delete(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.val) {
            node.left = delete(node.left, value);
        } else if (value > node.val) {
            node.right = delete(node.right, value);
        } else {
            // Leaf node deletion case
            if (node.left == null && node.right == null) {
                node = null;
            }
            // Node to be deleted has one child case
            // Node to be deleted has right child
            else if (node.left == null) {
                node = node.right;
            }
            // Node to be deleted has left child
            else if (node.right == null) {
                node = node.left;
            }
            // Node to be deleted has two children case
            else {
                TreeNode successor = findMin(node.right);
                // Copy the value
                node.val = successor.val;
                // delete successor node instead
                node.right = delete(node.right, successor.val);
            }
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private boolean exists(int data, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (data == node.val) {
            return true;
        }
        if (data < node.val) {
            return exists(data, node.left);
        }
        return exists(data, node.right);
    }

    private void insert(int data, TreeNode node) {
        if (data < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(data);
                return;
            }
            insert(data, node.left);
        } else {
            if (node.right == null) {
                node.right = new TreeNode(data);
                return;
            }
            insert(data, node.right);
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public static void main(String[] args) {
        // 10,5,15,3,7,null,18
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(18);
        tree.delete(9);

        //System.out.println(tree.exists(18));
        tree.printPreOrder();
    }

    public void insertAll(int[] values) {
        for (int val: values) {
            insert(val);
        }
    }
}
