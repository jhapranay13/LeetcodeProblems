package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, return the length of the diameter of
 *         the tree.
 * 
 *         The diameter of a binary tree is the length of the longest path
 *         between any two nodes in a tree. This path may or may not pass
 *         through the root.
 * 
 *         The length of a path between two nodes is represented by the number
 *         of edges between them.
 * 
 *         Example 1:
 * 							1
 * 						   / \
 * 						  2   3
 * 						 / \
 * 						4   5							
 * 
 *         Input: root = [1,2,3,4,5] Output: 3 Explanation: 3is the length of
 *         the path [4,2,1,3] or [5,2,1,3]. 
 *         
 *         Example 2:
 * 
 *         Input: root = [1,2] Output: 1
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 104]. -100 <=
 *         Node.val <= 100
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


public class _543_DiameterOfBinaryTree {

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
	//=============================================================================
	public int diameterOfBinaryTree(TreeNode root) {

		if (root == null) {
			return 0;
		}
		recur(root);
		return diameter;
	}
	private int diameter = 0;

	private int recur(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = recur(node.left);
		int right = recur(node.right);
		int dia = left + right;
		diameter = Math.max(diameter, dia);
		return 1 + Math.max(left, right);
	}
	//=============================================================================
	//Second Varsion
	public int diameterOfBinaryTree1(TreeNode root) {

		if (root == null) {
			return 0;
		}
		recur1(root);
		return diameter - 1;
	}

	private int recur1(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = recur(node.left);
		int right = recur(node.right);
		int dia = 1 + left + right;
		diameter = Math.max(diameter, dia);
		return 1 + Math.max(left, right);
	}
}
