package leetcode.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.



Example 1:

		1					1
	   / \				   / \
	  2   3               2   3
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:

		1                   1
	   /					 \
	  2                       2

Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:

		1					1
	   / \                 / \
	  2   1               1   2

Input: p = [1,2,1], q = [1,1,2]
Output: false


Constraints:

The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
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

public class _100_SameTree {

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

	public boolean isSameTree(TreeNode p, TreeNode q) {
		return recur(p, q);
	}

	private boolean recur(TreeNode p, TreeNode q) {

		if ((p == null && q != null) || (p != null && q == null)) {
			return false;
		}

		if (p == null && q == null) {
			return true;
		}

		if (p.val != q.val) {
			return false;
		}

		if (p.val == q.val) {
			return recur(p.left, q.left) && recur(p.right, q.right);
		}
		return false;
	}
	//===============================================================================
	//Iterative approach
	public boolean isSameTree1(TreeNode p, TreeNode q) {

		if(p == null && q == null) {
			return true;
		}

		if (!check(p, q)) {
			return false;
		} 
		Deque<TreeNode> pQ = new LinkedList<>();
		Deque<TreeNode> qQ = new LinkedList<>();
		pQ.addLast(p);
		qQ.addLast(q);

		while (!pQ.isEmpty() && !qQ.isEmpty()) {
			p = pQ.removeFirst();
			q = qQ.removeFirst();

			if (!check(p, q)) {
				return false;
			}

			if (p != null) {

				if (!check(p.left, q.left)) {
					return false;
				}

				if (p.left!= null) {
					pQ.add(p.left);
					qQ.add(q.left);
				}

				if (!check(p.right, q.right)) {
					return false;
				}

				if (p.right != null) {
					pQ.add(p.right);
					qQ.add(q.right);
				}
			}
		}
		return true;
	}

	private boolean check(TreeNode p, TreeNode q) {

		if (p == null && q == null) {
			return true;
		}

		if (p == null || q == null) {
			return false;
		}

		if (p.val == q.val) {
			return true;
		}
		return false;
	}
}
