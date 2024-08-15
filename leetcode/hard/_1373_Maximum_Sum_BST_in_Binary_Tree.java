package leetcode.hard;

/**
 *
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *                                  1
 *                               /    \
 *                              4      3
 *                            /  \   //  \\
 *                           2   4  2     5
 *                                      // \\
 *                                     4    6
 *
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 * Example 2:
 *                              4
 *                             /
 *                            3
 *                           / \
 *                          1   2
 *
 *
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 * Example 3:
 *
 * Input: root = [-4,-2,-5]
 * Output: 0
 * Explanation: All values are negatives. Return an empty BST.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 4 * 10^4].
 * -4 * 10^4 <= Node.val <= 4 * 10^4
 *
 */
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

public class _1373_Maximum_Sum_BST_in_Binary_Tree {
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

    private int ans = 0;

    public int maxSumBST(TreeNode root) {
        recur(root);
        return ans;
    }

    private int[] recur(TreeNode node) {

        if (node == null) {
            return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }

        if (node.left == null && node.right == null) {
            ans = Math.max(ans, node.val);
            return new int[] {node.val, node.val, node.val};
        }
        int[] left = recur(node.left);
        int[] right = recur(node.right);

        if (node.left == null && node.right != null) {
            // min right val should be greater than current val
            if (right[0] > node.val) {
                ans = Math.max(ans, right[2] + node.val);
                return new int[] {node.val, right[1], right[2] + node.val};
            }
        }

        if (node.left != null && node.right == null) {
            // Max left val should be less than current val
            if (left[1] < node.val) {
                ans = Math.max(ans, left[2] + node.val);
                return new int[] {left[0], node.val, left[2] + node.val};
            }
        }

        if (node.left != null && node.right != null) {

            if (left[1] < node.val && node.val < right[0]) {
                ans = Math.max(ans, left[2] + right[2] + node.val);
                return new int[] {left[0], right[1], left[2] + right[2] + node.val};
            }
        }
        return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE,0};
    }
}
