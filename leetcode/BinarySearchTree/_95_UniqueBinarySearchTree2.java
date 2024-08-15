package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.



Example 1:

	   1       1              2           3         3
		\       \            /\          /         /  
		3        2          1  3        2         1
	   /          \                    /           \
	  2	           3                  1             2

Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 8
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
public class _95_UniqueBinarySearchTree2 {

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

	public List<TreeNode> generateTrees(int n) {
		return recur(1, n);
	}

	private List<TreeNode> recur(int start, int end) {
		List<TreeNode> ans = new ArrayList<>();

		if (start > end) {
			ans.add(null);
			return ans;
		}

		for (int i = start; i <= end; i++) {
			List<TreeNode> left = recur(start, i - 1);
			List<TreeNode> right = recur(i + 1, end);

			for (TreeNode l : left) {

				for(TreeNode r : right) {
					TreeNode node = new TreeNode();
					node.val = i;
					node.left = l;
					node.right = r;
					ans.add(node);
				}
			}
		}
		return ans;
	}
}
