package leetcode.Tree;

/**
 *
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 *                     1                      1                      1
 *                   /  \        ==>        /            ===>
 *                  2    3                2
 *                /  \
 *               4    5
 *
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 *
 *
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

public class _366_FindLeavesOfBinaryTree {
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

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recur(root, ans);
        return ans;
    }

    //Building the answer and index from leaf to the node and checking if partial
    //exist in the index received. If not creata one and add
    private int recur(TreeNode node, List<List<Integer>> ans) {
        List<Integer> p = null;

        if (node.left == null && node.right == null) {

            if (ans.size() == 0) {
                p = new ArrayList<>();
                ans.add(p);
            } else {
                p = ans.get(0);
            }
            p.add(node.val);
            return 0;
        }
        int left = -1;
        int right = -1;

        if (node.left != null) {
            left = recur(node.left, ans);
        }

        if (node.right != null) {
            right = recur(node.right, ans);
        }
        int max = Math.max(right, left);

        if (ans.size() <= max + 1) {
            p = new ArrayList<>();
            ans.add(p);
        } else {
            p = ans.get(max + 1);
        }
        p.add(node.val);
        return max + 1;
    }
}
