package leetcode.BinarySearchTree;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root node of a binary search tree and two integers low and
 *         high, return the sum of values of all nodes with a value in the
 *         inclusive range [low, high].
 * 
 * 
 * 
 *         Example 1:
 *         					10
 *         				   /  \
 *                        /    \
 * 						 5      15
 * 						/ \       \
 * 					   3   7       18 	
 * 
 *         Input: root = [10,5,15,3,7,null,18], low = 7, high = 15 Output: 32
 *         Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 +
 *         15 = 32. 
 *         
 *         Example 2:
 * 							10
 *         				   /  \
 *                        /    \
 * 						 5      15
 * 						/ \    /  \
 * 					   3   7  13   18
 * 					  /   /
 * 					 1   6		
 * 
 *         Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 *         Output: 23 Explanation: Nodes 6, 7, and 10 are in the range [6, 10].
 *         6 + 7 + 10 = 23.
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 2 * 104]. 1 <=
 *         Node.val <= 105 1 <= low <= high <= 105 All Node.val are unique.
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

public class _938_RangeSumBST {

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

	public int rangeSumBST(TreeNode root, int low, int high) {
		int ans = dfs(root, low, high);
		return ans;
	}

	private int dfs(TreeNode node, int low, int high) {
		if (node == null) {
			return 0;
		}
		int val = node.val;
		int ans = 0;

		if (low <= val && val <= high) {
			ans = val;
		}
		int left = 0;

		if (low < val) {
			left = dfs(node.left, low, high);    
		}
		int right = 0;

		if (high > val) {
			right = dfs(node.right, low, high);
		}
		return ans + left + right;
	}
}
