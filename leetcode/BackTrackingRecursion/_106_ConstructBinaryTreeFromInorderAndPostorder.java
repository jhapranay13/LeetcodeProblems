package leetcode.BackTrackingRecursion;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * @author Pranay Jha
 *
 *Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
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


public class _106_ConstructBinaryTreeFromInorderAndPostorder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class TreeNode {
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

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		initInorderPosMap(inorder);
		AtomicInteger index = new AtomicInteger(postorder.length - 1);
		return dfs(postorder, index, 0, postorder.length - 1);
	}

	private Map<Integer, Integer> inorderPosMap = new HashMap<>();

	private TreeNode dfs(int[] postorder, AtomicInteger index, int lo, int hi) {

		if (lo > hi) {
			return null;
		}
		int postIndex = index.getAndDecrement();
		TreeNode node = new TreeNode();
		node.val = postorder[postIndex];
		int inIndex = inorderPosMap.get(node.val);
		node.right = dfs(postorder, index, inIndex + 1, hi);
		node.left = dfs(postorder, index, lo, inIndex - 1);
		return node;
	}
	//Can use linear search instead of this logic too
	private void initInorderPosMap(int[] inorder) {

		for (int i = 0; i < inorder.length; i++) {
			inorderPosMap.put(inorder[i], i);
		}
	}
	//=============================================================================================
	//Different Approach
	private int index = 0;

	private TreeNode recur(int[] postOrder, Map<Integer, Integer> posMap, int lo, int hi) {

		if (lo > hi || index < 0) {
			return null;
		}
		TreeNode node = new TreeNode();
		node.val = postOrder[index];
		int pos = posMap.get(node.val);
		index--;

		if (index < 0) {
			return node;
		}
		int currIndex = posMap.get(node.val);
		int nextIndex = posMap.get(postOrder[index]);

		if (currIndex < nextIndex) {
			node.right = recur(postOrder, posMap, pos + 1, hi);
		}

		if (index < 0) {
			return node;
		}
		nextIndex = posMap.get(postOrder[index]);

		if (currIndex > nextIndex) {
			node.left = recur(postOrder, posMap, lo, pos - 1);
		}
		return node;
	}
}
