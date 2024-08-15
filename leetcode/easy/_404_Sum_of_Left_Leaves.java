package leetcode.easy;

/**
 *
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 *
 *
 * Example 1:
 *                  3
 *                /   \
 *               9     20
 *                    /  \
 *                  15    7
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -1000 <= Node.val <= 1000
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

public class _404_Sum_of_Left_Leaves {
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

    public int sumOfLeftLeaves(TreeNode root) {
        return recur(root, false);
    }

    private int recur(TreeNode node, boolean doAdd) {

        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return doAdd ? node.val : 0;
        }
        int left = recur(node.left, true);
        int right = recur(node.right, false);
        return left + right;
    }
}
