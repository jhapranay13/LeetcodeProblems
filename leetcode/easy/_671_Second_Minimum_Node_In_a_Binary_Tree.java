package leetcode.easy;

/**
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 *                      2
 *                    /  \
 *                   2    5
 *                      /  \
 *                     5    2
 *
 * Input: root = [2,2,5,null,null,5,7]
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * Example 2:
 *                      2
 *                    /  \
 *                   2    2
 *
 * Input: root = [2,2,2]
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 25].
 * 1 <= Node.val <= 231 - 1
 * root.val == min(root.left.val, root.right.val) for each internal node of the tree.
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

public class _671_Second_Minimum_Node_In_a_Binary_Tree {
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

    long max = Long.MAX_VALUE;
    long max2 = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        recur(root);
        return max2 == Long.MAX_VALUE ? -1 : (int)max2;
    }

    private void recur(TreeNode node) {

        if (node == null) {
            return;
        }

        if (node.val < max) {
            max2 = max;
            max = node.val;
        } else {

            if (max != node.val && max2 > node.val) {
                max2 = node.val;
            }
        }
        recur(node.left);
        recur(node.right);
    }
}
