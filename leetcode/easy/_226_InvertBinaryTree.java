package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, invert the tree, and return its
 *         root.
 * 
 *         Example 1:
 * 						4                                 4
 * 					  /   \	                            /   \
 *                   2     7                           7     2
 *                  / \   / \     ==========>>        / \   / \
 *                 1   3 6   9                       9   6 3   1     
 * 							
 *         Input: root = [4,2,7,1,3,6,9] Output: [4,7,2,9,6,3,1]
 *         
 *         Example 2:
 *         
 *        				2                                 2
 * 					  /   \	                            /   \
 *                   1     3                           3     1
 *                                 ==========>> 
 * 
 * 
 *         Input: root = [2,1,3] Output: [2,3,1] Example 
 *         
 *         3:
 * 
 *         Input: root = [] Output: []
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 100]. -100 <=
 *         Node.val <= 100
 *
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


public class _226_InvertBinaryTree {

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

	public TreeNode invertTree(TreeNode root) {

		if (root == null) {
			return root;
		}
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = invertTree(right);
		root.right = invertTree(left);
		return root;
	}
}