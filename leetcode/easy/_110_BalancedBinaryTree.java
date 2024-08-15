package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:

			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:

				1
			   / \
			  2   2
			 / \
			3   3
		   / \
		  4   4

Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:

Input: root = []
Output: true


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104
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

public class _110_BalancedBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

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

	public boolean isBalanced(TreeNode root) {
		recur(root);
		return ans;
	}

	private boolean ans = true;

	private int recur(TreeNode node) {

		if (node == null) {
			return 0;
		}
		int left = recur(node.left);
		int right = recur(node.right);

		if (Math.abs(left - right) > 1) {
			ans = false;
		}
		return 1 + Math.max(left, right);
	}
}
