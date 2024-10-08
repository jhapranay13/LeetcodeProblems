package leetcode.Tree;

/**
 *
 * Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
 *
 * A subtree of a tree is any node of that tree plus all its descendants.
 *
 * The average value of a tree is the sum of its values, divided by the number of nodes.
 *
 *
 *
 * Example 1:
 *                  5
 *                 / \
 *                6   1
 *
 * Input: root = [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 *
 * Example 2:
 *
 * Input: root = [0,null,1]
 * Output: 1.00000
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 105
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

public class _1120_MaximumAvarageSubTree {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode() {
         }

         TreeNode(int val) {
             this.val = val;
         }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

     public double maximumAverageSubtree(TreeNode root) {
        recur(root);
        return ans;
    }
    double ans = 0.0;

    private double[] recur(TreeNode node) {
        if (node == null) {
            return new double[] {0, 0};
        }
        int val = node.val;
        double[] left = recur(node.left);
        double[] right = recur(node.right);
        double totalVal = left[0] + right[0] + val;
        double totalCount = 1 + left[1] + right[1];
        ans = Math.max(ans, (double)(totalVal / totalCount));
        return new double[] {totalVal, totalCount};
    }
}
