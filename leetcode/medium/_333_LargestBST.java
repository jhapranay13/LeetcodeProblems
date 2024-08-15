package leetcode.medium;

/**
 *
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 *
 *
 *
 * Example 1:
 *                  10
 *                /   \
 *               5     15
 *             /  \     \
 *            1    8     7
 *
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 * Example 2:
 *
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -104 <= Node.val <= 104
 *
 *
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
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

public class _333_LargestBST {

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

    public int largestBSTSubtree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int ans = recur(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int left = 0;
        int right = 0;

        left = largestBSTSubtree(root.left);
        right = largestBSTSubtree(root.right);
        return Math.max(ans, Math.max(left, right));
    }

    private int recur(TreeNode node, int min, int max) {

        if (node == null) {
            return 0;
        }

        if (node.val <= min || node.val >= max) {
            return -1;
        }
        int left = recur(node.left, min, node.val);
        int right = recur(node.right, node.val, max);

        if (left == -1 || right == -1) {
            return -1;
        }
        return left + right + 1;
    }
}
