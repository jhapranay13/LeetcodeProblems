package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         A path in a binary tree is a sequence of nodes where each pair of
 *         adjacent nodes in the sequence has an edge connecting them. A node
 *         can only appear in the sequence at most once. Note that the path does
 *         not need to pass through the root.
 * 
 *         The path sum of a path is the sum of the node's values in the path.
 * 
 *         Given the root of a binary tree, return the maximum path sum of any
 *         path.
 * 
 * 
 * 
 *         Example 1:
 *         
 *         				1
 *         			   / \
 *         			  2   3		
 * 
 *         Input: root = [1,2,3] Output: 6 Explanation: The optimal path is 2 ->
 *         1 -> 3 with a path sum of 2 + 1 + 3 = 6. 
 *         
 *         Example 2:
 * 
 * 						-10
 * 					   /    \
 * 					  9	     20
 * 							/  \
 * 						  15    7
 * 
 *         Input: root = [-10,9,20,null,null,15,7] Output: 42 Explanation: The
 *         optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 3 * 104]. -1000
 *         <= Node.val <= 1000
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

public class _124_MaximumPathSum {

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

	public int maxPathSum(TreeNode root) {
		recur(root);
		return maxAns;   
	}
	private int maxAns = Integer.MIN_VALUE;

	private int recur(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = Math.max(recur(node.left), 0);
		int right = Math.max(recur(node.right), 0);
		maxAns = Math.max(maxAns, left + right + node.val);
		return node.val + Math.max(left, right);
	}
}
