package leetcode.BinarySearchTree;

/**
 *
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *                     4
 *  *                /  \
 *  *               2    6
 *  *             /  \
 *  *            1    3
 *  *
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *                 1
 *  *             / \
 *  *            0   48
 *  *               /  \
 *  *              12   49
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 100].
 * 0 <= Node.val <= 105
 *
 *
 * Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 *
 */

public class _783_Minimum_Distance_Between_BST_Nodes {
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
    private int ans = Integer.MAX_VALUE;
    TreeNode prev = null;

    public int minDiffInBST(TreeNode root) {
        recur(root);
        return ans;
    }
    // In Order Traversal
    private void recur(TreeNode node) {

        if (node == null) {
            return;
        }
        recur(node.left);

        if (prev != null) {
            ans = Math.min(ans, node.val - prev.val);
        }
        prev = node;
        recur(node.right);
    }
}
