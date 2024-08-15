package leetcode.BFS;

/**
 *
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *                   3
 *                 /   \
 *                9     20
 *                    /   \
 *                  15     7
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Example 2:
 *                      3
 *  *                 /   \
 *  *                9     20
 *  *              /   \
 *  *            15     7
 *
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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


public class _637_Average_of_Levels_in_Binary_Tree {
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

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        ans.add((double)root.val);

        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;

            while (size-- > 0) {
                TreeNode node = q.poll();

                if (node.left != null) {
                    sum += node.left.val;
                    q.offer(node.left);
                }

                if (node.right != null) {
                    sum += node.right.val;
                    q.offer(node.right);
                }
            }

            if (q.size() > 0) {
                ans.add(sum / q.size());
            }
        }
        return ans;
    }
}
