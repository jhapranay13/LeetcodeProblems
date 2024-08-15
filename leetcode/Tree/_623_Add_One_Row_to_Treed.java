package leetcode.Tree;

/**
 *
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 *
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
 *
 *
 * Example 1:
 *                  4                                   4
 *               /     \                             /     \
 *              2       6        ==>                1       1
 *           /    \   /                           /          \
 *          3     1  5                           2            6
 *                                             /  \         /
 *                                            3    1       5
 *
 * Input: root = [4,2,6,3,1,5], val = 1, depth = 2
 * Output: [4,1,1,2,null,null,6,3,1,5]
 *
 * Example 2:
 *
 *                 4                                    4
 *               /                                    /
 *             2                                    2
 *           /  \               ==>               /   \
 *          3    1                               1     1
 *                                             /        \
 *                                            3          1
 *
 *
 * Input: root = [4,2,null,3,1], val = 1, depth = 3
 * Output: [4,2,null,1,1,3,null,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * The depth of the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 * -10^5 <= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
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

public class _623_Add_One_Row_to_Treed {
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

    public TreeNode addOneRow(TreeNode root, int val, int depth) {

        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        return recur(root, val, depth, 0);
    }

    private TreeNode recur(TreeNode node, int val, int depth, int dir) {
        TreeNode ans = null;

        if (depth == 1) {
            ans = new TreeNode(val);
        }

        if (node == null) {
            return ans;
        }

        if (ans != null) {

            if (dir == 1) {
                ans.left = node;
            } else {
                ans.right = node;
            }
            return ans;
        } else {
            ans = node;
        }
        ans.left = recur(ans.left, val, depth - 1, 1);
        ans.right = recur(ans.right, val, depth - 1, -1);
        return ans;
    }
}
