package leetcode.medium;

/**
 *
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 *
 *
 * Example 1:
 *
 *           0                    0                    0                    0                   0
 *         /   \                /   \                /   \                /   \               /   \
 *       0      0              0     0              0     0              0     0             0     0
 *            /   \                /   \          /   \  /  \          /  \                /   \
 *           0     0              0     0        0    0 0    0        0    0              0     0
 *               /  \           /  \                                     /  \            / \
 *              0    0         0    0                                   0    0          0   0
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Example 2:
 *
 * Input: n = 3
 * Output: [[0,0,0]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
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

public class _894_All_Possible_Full_Binary_Trees {
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

    public List<TreeNode> allPossibleFBT(int n) {
        return recur(n);
    }

    private List<TreeNode> recur(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(new TreeNode());
            return ans;
        }


        for (int i = 2; i <= n; i++) {
            List<TreeNode> lefts = recur(i - 1);
            List<TreeNode> rights = recur(n - i);

            for (TreeNode left : lefts) {

                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode();
                    node.left = left;
                    node.right = right;
                    ans.add(node);
                }
            }
        }
        return ans;
    }
}
