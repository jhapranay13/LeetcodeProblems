package leetcode.Tree;

/**
 *
 * Given the root of a binary tree, return true if you can partition the tree into two trees with equal sums of values after removing exactly one edge on the original tree.
 *
 *
 *
 * Example 1:
 *              5                5            10
 *            /   \            /             /   \
 *           10   10          10            2     3
 *               /  \
 *              2    3
 *
 * Input: root = [5,10,10,null,null,2,3]
 * Output: true
 * Example 2:
 *                  1
 *                /   \
 *               2    10
 *                   /  \
 *                  2    20
 *
 * Input: root = [1,2,10,null,null,2,20]
 * Output: false
 * Explanation: You cannot split the tree into two trees with equal sums after removing exactly one edge on the tree.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
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

public class _663_Equal_Tree_Partition {
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

    public boolean checkEqualTree(TreeNode root) {
        Deque<Integer> sumHolder = new LinkedList<>();
        int total = recur(root, sumHolder);
        sumHolder.pop(); // popping the total sum

        if (total % 2 == 0) {

            for (int sum : sumHolder) {

                if (sum == total / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private int recur(TreeNode node, Deque<Integer> sumHolder) {

        if (node == null) {
            return 0;
        }
        int left = recur(node.left,sumHolder);
        int right = recur(node.right, sumHolder);
        int curr = node.val;
        sumHolder.push(left + right + curr);
        return left + right + curr;
    }
}
