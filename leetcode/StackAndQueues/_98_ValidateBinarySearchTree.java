package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jh
 *
 *Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:
				2
               / \
              1   3
Input: root = [2,1,3]
Output: true

Example 2:
 					 5 
 				   /  \	
				  1	   4
				      / \
				     3   6
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
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
public class _98_ValidateBinarySearchTree {

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

	public boolean isValidBST(TreeNode root) {
		return recur(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean recur(TreeNode node, long min, long max) {

		if (node == null) {
			return true;
		}

		if (node.val > min && node.val < max ) {
			boolean left = recur(node.left, min, node.val);
			boolean right = recur(node.right, node.val, max);
			return left && right;
		}
		return false;
	}
	//=============================================================================
	//Stack Implementation(Inorder traversal gives sorted result in BST)
	public boolean isValidBST1(TreeNode root) {

		if( root == null || ( root.left == null && root.right == null ) ) {
			return true;
		}
		Deque< TreeNode > stack = new LinkedList<>();
		TreeNode curr = root;
		long prev = Long.MIN_VALUE;

		while( curr!= null || !stack.isEmpty() ) {

			if( curr != null ) {
				stack.push( curr );
				curr = curr.left;
			} else {
				TreeNode temp = stack.pop();

				if( temp.val <= prev ) {
					return false;
				}
				prev = temp.val;
				curr = temp.right;
			}
		}
		return true;
	}

}
