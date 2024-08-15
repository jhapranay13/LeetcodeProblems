package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
Example 1:
				1
			  /	  \
			 2     2
			/ \   / \
		   3   4 4	 3

Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:

				1
             /     \
            2       2
             \       \
              3       3

Input: root = [1,2,2,null,3,null,3]
Output: false
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

public class _101_SymmetricTree {

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
	//recursive approach
	public boolean isSymmetric(TreeNode root) {

		if (root == null) {
			return true;
		}
		return recur(root.left, root.right);
	}

	private boolean recur(TreeNode node1, TreeNode node2) {

		if (node1 == null && node2 == null) {
			return true;
		}

		if (node1 == null || node2 == null) {
			return false;
		}

		if (node1.val == node2.val) {
			return recur(node1.left, node2.right) && recur(node1.right, node2.left);
		}
		return false;
	}
	//===============================================================================
	//Iterative approach
	public boolean isSymmetric1(TreeNode root) {
		Deque<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node1 = queue.poll();
			TreeNode node2 = queue.poll();

			if (node1 == null && node2 == null) {
				continue; 
			}

			if (node1 == null || node2 == null) {
				return false;
			}

			if (node1.val != node2.val) {
				return false;
			}
			queue.add(node1.left);
			queue.add(node2.right);
			queue.add(node1.right);
			queue.add(node2.left);
		}
		return true;
	}
}
