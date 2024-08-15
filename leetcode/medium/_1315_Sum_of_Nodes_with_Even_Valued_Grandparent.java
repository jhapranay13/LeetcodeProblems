package leetcode.medium;

/**
 *
 * Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
 *
 * A grandparent of a node is the parent of its parent if it exists.
 *
 *
 *
 * Example 1:
 *                 6(B)
 *            /        \
 *           7          8(B)
 *         /  \      /    \
 *        2(R) 7(R) 1(R)   3 (R)
 *      /    /  \           \
 *     9    1   4            5 (R)
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 * Example 2:
 *
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * 1 <= Node.val <= 100
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

public class _1315_Sum_of_Nodes_with_Even_Valued_Grandparent {
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
    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        recur(root);
        return sum;
    }

    private void recur(TreeNode node) {

        if (node == null) {
            return;
        }

        if (node.val % 2 == 0) {

            if (node.left != null) {

                if (node.left.left != null) {
                    sum += node.left.left.val;
                }

                if (node.left.right != null) {
                    sum += node.left.right.val;
                }
            }

            if (node.right != null) {

                if (node.right.left != null) {
                    sum += node.right.left.val;
                }

                if (node.right.right != null) {
                    sum += node.right.right.val;
                }
            }
        }
        recur(node.left);
        recur(node.right);
    }
}
