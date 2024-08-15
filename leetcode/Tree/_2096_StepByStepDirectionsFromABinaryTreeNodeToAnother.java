package leetcode.Tree;

/**
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 *
 * Example 1:
 *                  5
 *                /   \
 *               1     2
 *             /     /  \
 *            3     6    4
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * Example 2:
 *
 *                  2
 *                /
 *               1
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 *
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

public class _2096_StepByStepDirectionsFromABinaryTreeNodeToAnother {

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

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = getLca(root, startValue, destValue);
        StringBuilder ans = new StringBuilder();
        StringBuilder p = new StringBuilder();
        findAndDirection(lca, startValue, p);

        for (int i = 0; i < p.length(); i++) {
            ans.append("U");
        }
        findAndDirection(lca, destValue, ans);
        return ans.toString();
    }

    private boolean findAndDirection(TreeNode node, int val, StringBuilder p) {

        if (node == null) {
            return false;
        }

        if (node.val == val) {
            return true;
        }
        p.append("L");

        if (findAndDirection(node.left, val, p)) {
            return true;
        }
        p.deleteCharAt(p.length() - 1);
        p.append("R");

        if (findAndDirection(node.right, val, p)) {
            return true;
        }
        p.deleteCharAt(p.length() - 1);
        return false;
    }

    private TreeNode getLca(TreeNode node, int val1, int val2) {

        if (node == null) {
            return null;
        }
        TreeNode left = getLca(node.left, val1, val2);
        TreeNode right = getLca(node.right, val1, val2);

        if (val1 == node.val || val2 == node.val) {
            return node;
        }

        if (left != null && right != null) {
            return node;
        }

        if (left != null) {
            return left;
        }
        return right;
    }
}
