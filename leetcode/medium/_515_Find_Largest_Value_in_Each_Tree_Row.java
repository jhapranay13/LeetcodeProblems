package leetcode.medium;

/**
 *
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 *
 *
 * Example 1:
 *                   1
 *                 /   \
 *                3     2
 *              /  \     \
 *             5    3     9
 *
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 * Example 2:
 *
 * Input: root = [1,2,3]
 * Output: [1,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [0, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */

import java.util.ArrayList;
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

public class _515_Find_Largest_Value_in_Each_Tree_Row {
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

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        recur(root, ans, 0);
        return ans;
    }

    private void recur(TreeNode node, List<Integer> ans, int pos) {

        if (node == null) {
            return;
        }

        if (pos == ans.size()) {
            ans.add(node.val);
        } else {
            ans.set(pos, Math.max(ans.get(pos), node.val));
        }
        recur(node.left, ans, pos + 1);
        recur(node.right, ans, pos + 1);
    }
}
