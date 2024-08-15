package leetcode.medium;

/**
 *
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * A subtree of a node node is node plus every node that is a descendant of node.
 *
 *
 *
 * Example 1:
 *              1                             1
 *               \                             \
 *                0      ======>                0
 *               / \                             \
 *              0   1                             1
 *
 * Input: root = [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 * Example 2:
 *                1                                1
 *              /   \                               \
 *            0      1      ========>                1
 *          /  \    / \                               \
 *         0    0  0   1                               1
 *
 * Input: root = [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 * Example 3:
 *              1                                   1
 *            /   \                               /   \
 *           1     0                             1     0
 *         /  \   /  \      ===========>       /  \     \
 *        1    1 0    1                       1    1     1
 *       /
 *      0
 *
 * Input: root = [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 200].
 * Node.val is either 0 or 1.
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

public class _814_Binary_Tree_Pruning {
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

    public TreeNode pruneTree(TreeNode root) {
        return recur(root);
    }

    private TreeNode recur(TreeNode node) {

        if (node == null) {
            return null;
        }
        node.left = recur(node.left);
        node.right = recur(node.right);

        if (node.val == 0 && node.left == null && node.right == null) {
            return null;
        }
        return node;
    }
}
