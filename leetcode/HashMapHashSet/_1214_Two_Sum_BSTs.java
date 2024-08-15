package leetcode.HashMapHashSet;

/**
 *
 *
 * Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 *
 *
 *
 * Example 1:
 *              2                       1
 *            /  \                    /  \
 *           1    4                  0    3
 *
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 * Output: true
 * Explanation: 2 and 3 sum up to 5.
 *
 * Example 2:
 *
 *              0                       5
 *            /   \                   /   \
 *        -10      10                1     7
 *                                 /  \
 *                                9    2
 *
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [1, 5000].
 * -10^9 <= Node.val, target <= 10^9
 *
 */

import java.util.HashSet;
import java.util.Set;

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

public class _1214_Two_Sum_BSTs {

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

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> holder = new HashSet<>();
        recur(root1, holder);
        return check(root2, holder, target);
    }

    private boolean check(TreeNode root, Set<Integer> holder, int target) {

        if (root == null) {
            return false;
        }
        int diff = target - root.val;

        if (holder.contains(diff)) {
            return true;
        }
        return check(root.left, holder, target) || check(root.right, holder, target);
    }

    private void recur(TreeNode root, Set<Integer> holder) {

        if (root == null) {
            return;
        }
        recur(root.left, holder);
        holder.add(root.val);
        recur(root.right, holder);
    }
}
