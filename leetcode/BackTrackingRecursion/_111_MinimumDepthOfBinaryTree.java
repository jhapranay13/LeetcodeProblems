package leetcode.BackTrackingRecursion;

/**
 * 
 * @author Pranay Jha
 *
 *Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:

Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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



public class _111_MinimumDepthOfBinaryTree {

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

	public int minDepth1(TreeNode root) {
		return recur(root);
	}

	private int recur(TreeNode node) {

		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null) {
			return 1;
		}
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;

		if (node.left != null) {
			left = recur(node.left);
		}

		if (node.right != null) {
			right = recur(node.right);
		}
		return 1 + Math.min(left, right);
	}
	//==============================================================================
	//Another Version
	public int minDepth(TreeNode root) {

		if( root == null ) {
			return 0;
		}
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;

		if( root.left != null ) {
			left = minDepth( root.left );
		}

		if( root.right != null ) {
			right = minDepth( root.right );
		}

		if( root.left == null && root.right == null  ) {
			left = 0;
		}
		return 1 + Math.min( left, right );
	}
}
