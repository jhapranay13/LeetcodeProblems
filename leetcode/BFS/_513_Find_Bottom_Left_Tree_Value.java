package leetcode.BFS;

/**
 *
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 *
 *
 * Example 1:
 *                  2
 *                /   \
 *               1     3
 *
 * Input: root = [2,1,3]
 * Output: 1
 * Example 2:
 *                   1
 *                /    \
 *               2      3
 *              /     /   \
 *             4     5     6
 *                  /
 *                 7
 *
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */

import java.util.Deque;
import java.util.LinkedList;

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

public class _513_Find_Bottom_Left_Tree_Value {
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

    public int findBottomLeftValue(TreeNode root) {
        int ans = root.val;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            ans = q.peek().val;

            while (size-- > 0) {
                TreeNode node = q.poll();

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }
}
