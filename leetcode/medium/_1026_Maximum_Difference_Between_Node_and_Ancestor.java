package leetcode.medium;

/**
 *
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *                              8
 *                            /   \
 *                          3      10
 *                        /  \       \
 *                       1    6       14
 *                           / \     /
 *                          4   7   13
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *                      1
 *                       \
 *                        2
 *                         \
 *                          0
 *                         /
 *                        3
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 10^5
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

public class _1026_Maximum_Difference_Between_Node_and_Ancestor {
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


    long ans = 0;

    public int maxAncestorDiff(TreeNode root) {
        recur(root);
        return (int)ans;
    }

    private long[] recur(TreeNode node) {

        if (node == null) {
            return null;
        }
        long min = node.val;
        long max = node.val;
        long[] left = recur(node.left);

        if (left != null) {
            ans = Math.max(ans, Math.abs(node.val - left[0]));
            ans = Math.max(ans, Math.abs(node.val - left[1]));
            min = Math.min(min, left[0]);
            max = Math.max(max, left[1]);
        }
        long[] right = recur(node.right);

        if (right != null) {
            ans = Math.max(ans, Math.abs(node.val - right[0]));
            ans = Math.max(ans, Math.abs(node.val - right[1]));
            min = Math.min(min, right[0]);
            max = Math.max(max, right[1]);
        }
        long[] res = new long[] {min, max};
        return res;
    }
}
