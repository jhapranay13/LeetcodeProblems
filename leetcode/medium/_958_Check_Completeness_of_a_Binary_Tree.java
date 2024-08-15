package leetcode.medium;

/**
 *
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *                  1
 *                /    \
 *               2      3
 *             /  \    /
 *            4    5  6
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 *
 * Example 2:
 *                   1
 *                /    \
 *               2      3
 *             /  \      \
 *            4    5      7
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 1000
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

public class _958_Check_Completeness_of_a_Binary_Tree {
    public class TreeNode {
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

    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        boolean nullEncountered = false;
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode node = q.poll();

                if (node.left != null) {

                    if (nullEncountered) {
                        return false;
                    }
                    q.offer(node.left);
                } else {
                    nullEncountered = true;
                }

                if (node.right != null) {

                    if (nullEncountered) {
                        return false;
                    }
                    q.offer(node.right);
                } else {
                    nullEncountered = true;
                }
            }
        }
        return true;
    }
}
