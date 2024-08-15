package leetcode.Tree;

/**
 *
 * ou are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.
 *
 * Return the minimum number of moves required to make every node have exactly one coin.
 *
 *
 *
 * Example 1:
 *                      3
 *                    /  \
 *                   0    0
 *
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * Example 2:
 *                      0
 *                    /  \
 *                   3    0
 *
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= n <= 100
 * 0 <= Node.val <= n
 * The sum of all Node.val is n.
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

public class _979_DistributeCoinsInBinaryTree {

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

    public int distributeCoins(TreeNode root) {
        recur(root);
        return ans;
    }
    private int ans = 0;

    private int recur(TreeNode node) {

        if (node == null) {
            return 0;
        }
        int leftCoinsRequired = recur(node.left);
        int rightCoinsRequired = recur(node.right);
        int coinsLeft = node.val + leftCoinsRequired + rightCoinsRequired;
        //since if val is 0 we return -1 so for every return of -1 added we get the depth of when and how
        //it can be fuilfilled. so adding it to the answer
        ans += Math.abs(leftCoinsRequired) + Math.abs(rightCoinsRequired);
        return coinsLeft - 1;
    }
}
