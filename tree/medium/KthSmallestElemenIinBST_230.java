package tree.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import utils.TreeNode;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

/** M
 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Example 1:
 Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
 \
 2
 Output: 1

 Example 2:
 Input: root = [5,3,6,2,4,null,null,1], k = 3
     5
    / \
   3   6
  / \
 2   4
/
1
 Output: 3

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallestElemenIinBST_230 {

    public static void main(String[] args) {
        KthSmallestElemenIinBST_230 s = new KthSmallestElemenIinBST_230();
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        System.out.println(s.kthSmallest2(root1, 1)); //1
        System.out.println(s.kthSmallest2(root2, 3)); //3
    }

    // O(n*log(k)) - time, O(n+k) - space
    public int kthSmallest(TreeNode root, int k) {
        Queue<Integer> heap = new PriorityQueue<>((n1,n2) -> n2-n1);
        kthSmallestUtils(root, k, heap);
        return  heap.peek();
    }

    // O(n) - time, O(n) - space
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        kthSmallest2Utils(root, k, list);
        return list.get(k-1);
    }

    private void kthSmallest2Utils(TreeNode root, int k, List<Integer> list) {
        if (root == null) {
            return;
        }
        kthSmallest2Utils(root.left, k, list);
        list.add(root.val);
        kthSmallest2Utils(root.right, k, list);
    }

    private void kthSmallestUtils(TreeNode root, int k, Queue<Integer> heap) {
        if (root == null) {
            return;
        }
        heap.add(root.val);
        if (heap.size() > k) {
            heap.poll();
        }
        kthSmallestUtils(root.left, k, heap);
        kthSmallestUtils(root.right, k, heap);
    }
}
