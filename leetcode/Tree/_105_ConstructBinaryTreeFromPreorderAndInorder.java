package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Pranay Jha
 *
 *Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:

			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
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

public class _105_ConstructBinaryTreeFromPreorderAndInorder {

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

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		initInorderPosition(inorder);
		AtomicInteger index = new AtomicInteger(0);
		return dfs(preorder, index, 0, preorder.length - 1);
	}

	private Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

	private TreeNode dfs(int[] preorder, AtomicInteger index, int lo, int hi ) {

		if (lo > hi) {
			return null;
		}
		int idx = index.getAndIncrement();
		TreeNode node = new TreeNode();
		node.val = preorder[idx];
		int inorderIndex = inOrderIndexMap.get(preorder[idx]);
		node.left = dfs(preorder, index, lo, inorderIndex - 1);
		node.right = dfs(preorder, index, inorderIndex + 1, hi);
		return node;
	}
	//Can also use linear search in place of this logic    
	private void initInorderPosition(int[] inorder) {

		for (int i = 0; i < inorder.length; i++) {
			inOrderIndexMap.put(inorder[i], i);
		}
	}
	//=============================================================================================
	//Different recursive approach
	private int index = 0;
	
	private TreeNode recur(int[] preorder, Map<Integer, Integer> posMap, int lo, int hi) {

		if (lo > hi || index >= preorder.length) {
			return null;
		}
		TreeNode node = new TreeNode();
		node.val = preorder[index];
		int pos = posMap.get(node.val);
		index++;

		if (index >= preorder.length) {
			return node;
		}
		int currIndex = posMap.get(node.val);
		int nextIndex = posMap.get(preorder[index]);

		if (nextIndex < currIndex) {
			node.left = recur(preorder, posMap, lo, currIndex - 1);
		}

		if (index >= preorder.length) {
			return node;
		}
		nextIndex = posMap.get(preorder[index]);

		if (nextIndex > currIndex) {
			node.right = recur(preorder, posMap, currIndex + 1, hi);
		}
		return node;
	}
}
