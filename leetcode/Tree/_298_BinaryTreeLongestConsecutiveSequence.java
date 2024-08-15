package leetcode.Tree;

/**
 *
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 *
 *
 * Example 1:
 *                      1
 *                       \
 *                        3
 *                       / \
 *                      2   4
 *                           \
 *                            5
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * Example 2:
 *                      2
 *                       \
 *                        3
 *                       /
 *                      2
 *                    /
 *                   1
 *
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 *
 */

public class _298_BinaryTreeLongestConsecutiveSequence {
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
    public int longestConsecutive(TreeNode root) {
        recur(root);
        return ans;
    }

    private int ans = 0;

    private int recur(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = recur(node.left);
        int right = recur(node.right);

        if (node.left != null && node.val + 1 == node.left.val) {
            left++;
        } else {
            left = 1;
        }

        if (node.right != null && node.val + 1 == node.right.val) {
            right++;
        } else {
            right = 1;
        }
        ans = Math.max(ans, Math.max(left, right));
        return Math.max(left, right);
    }
}
