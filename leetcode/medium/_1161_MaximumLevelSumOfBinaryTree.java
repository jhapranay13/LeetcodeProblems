package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, the level of its root is 1, the
 *         level of its children is 2, and so on.
 * 
 *         Return the smallest level x such that the sum of all the values of
 *         nodes at level x is maximal.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         1 / \ 7 0 / \ 7 -8
 * 
 *         Input: root = [1,7,0,7,-8,null,null] Output: 2 Explanation: Level 1
 *         sum = 1. Level 2 sum = 7 + 0 = 7. Level 3 sum = 7 + -8 = -1. So we
 *         return the level with the maximum sum which is level 2. Example 2:
 * 
 *         Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 *         Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 104]. -105 <=
 *         Node.val <= 105
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


public class _1161_MaximumLevelSumOfBinaryTree {

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

	public int maxLevelSum(TreeNode root) {
		Deque<TreeNode> q = new LinkedList<>();
		int sum = Integer.MIN_VALUE;
		int ans = 0;
		int level = 1;
		q.offer(root);

		while (!q.isEmpty()) {
			int size = q.size();
			int currSum = 0;

			while (size-- > 0) {
				TreeNode node = q.poll();
				currSum += node.val;

				if (node.left != null) {
					q.offer(node.left);
				}

				if (node.right != null) {
					q.offer(node.right);
				}
			}

			if (currSum > sum) {
				sum = currSum;
				ans = level;
			}
			level++;
		}
		return ans;
	}
}
