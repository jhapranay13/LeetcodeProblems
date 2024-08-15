package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Example 1:
                5
              //  \
             4     8
            //    / \
           11   13   4
          / \\        \
         7   2         1

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: false

Example 3:

Input: root = [1,2], targetSum = 0
Output: false

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
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

public class _112_PathSum {

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

	public boolean hasPathSum(TreeNode root, int targetSum) {
		return recur(root, 0, targetSum);
	}

	private boolean recur(TreeNode node, int currSum, int targetSum) {

		if (node == null) {
			return false;
		}
		currSum += node.val;

		if (node.left == null && node.right == null) {
			return currSum == targetSum;
		}
		return recur(node.left, currSum, targetSum) || 
				recur(node.right, currSum, targetSum);
	}
}
