package leetcode.StackAndQueues;

/**
 *
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *                          4(30)
 *                        /       \
 *                    1(36)         5(21)
 *                   /     \       /     \
 *              0(36)      2(35)  5(26)   7(15)
 *                         \              /
 *                         3(33)         8(8)
 *
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * Example 2:
 *
 * Input: root = [0,null,1]
 * Output: [1,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 0 <= Node.val <= 100
 * All the values in the tree are unique.
 *
 *
 * Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
 *
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class _1038_Binary_Search_Tree_to_Greater_Sum_Tree {

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    //Recursive Approach
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        TreeNode left = bstToGst(root.left);
        return root;
    }
    //=============================================================================================
    //Stack based Approach
    public TreeNode bstToGst1(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {

            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }
        return root;
    }
}
