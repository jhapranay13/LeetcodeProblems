package leetcode.easy;

/**
 *
 * A binary tree is uni-valued if every node in the tree has the same value.
 *
 * Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.
 *
 *
 *
 * Example 1:
 *                   1
 *                 /   \
 *                1     1
 *               / \     \
 *              1   1     1
 *
 * Input: root = [1,1,1,1,1,null,1]
 * Output: true
 * Example 2:
 *
 *                  2
 *                /  \
 *               2    2
 *             /  \
 *            5    2
 *
 * Input: root = [2,2,2,5,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 0 <= Node.val < 100
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


public class _965_Univalued_Binary_Tree {
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

    public boolean isUnivalTree(TreeNode root) {
        return recur(root, root.val);
    }

    private boolean recur(TreeNode node, int val) {

        if (node == null) {
            return true;
        }

        boolean left = recur(node.left, val);
        boolean right = recur(node.right, val);
        return node.val == val && left && right;
    }
}
