package leetcode.Tree;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given the root of a binary tree, return the length of the longest
 *         path, where each node in the path has the same value. This path may
 *         or may not pass through the root.
 * 
 *         The length of the path between two nodes is represented by the number
 *         of edges between them.
 * 
 * 
 * 
 *         Example 1:
 *         
 *         						5
 *         					   / \
 * 							  4   5
 *                           / \   \
 *                          1   1   5 
 * 
 *         Input: root = [5,4,5,1,1,5] Output: 2 
 *         
 *         Example 2:
 * 								1
 *         					   / \
 * 							  4   5
 *                           / \   \
 *                          4   4   5 
 * 
 *         Input: root = [1,4,5,4,4,5] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 104]. -1000 <=
 *         Node.val <= 1000 The depth of the tree will not exceed 1000.
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

public class _687_LongestUnivalPath {

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

	public int longestUnivaluePath(TreeNode root) {
		recur(root);
		return max;
	}

	private int max = 0;

	private int recur(TreeNode node) {

		if (node == null) {
			return 0;
		}
		int left = recur(node.left);
		int right = recur(node.right);

		if (node.left != null && node.val == node.left.val) {
			left += 1;
		} else {
			left = 0;
		}

		if (node.right != null && node.val == node.right.val) {
			right += 1;
		} else {
			right  = 0;
		}
		max = Math.max(max, left + right);
		return Math.max(left, right);
	}
	//=======================================================================================
	//Little different Approach
	public int longestUnivaluePath1(TreeNode root) {
		recur1(root);
		return ans;
	}
	int ans = 0;

	private int recur1(TreeNode node) {

		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null) {
			return 0;
		}
		int left = 0;
		boolean leftValEqualFlag = false;

		if (node.left != null) {
			left = recur1(node.left);

			if (node.left.val == node.val) {
				leftValEqualFlag = true;
			}

		}
		int right = 0;
		boolean rightValEqualFlag = false;

		if (node.right != null) {
			right = recur1(node.right);

			if (node.right.val == node.val) {
				rightValEqualFlag = true;
			}
		}

		if (leftValEqualFlag && rightValEqualFlag) {
			ans = Math.max(ans, left + right + 2);
			return Math.max(left, right) + 1;
		}

		if (leftValEqualFlag) {
			ans = Math.max(ans, left + 1);
			return left + 1;
		}

		if (rightValEqualFlag) {
			ans = Math.max(ans, right + 1);
			return right + 1;
		}
		return 0;
	}
}
