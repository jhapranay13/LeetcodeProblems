package leetcode.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:

			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]

Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
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

public class _103_BinaryTreeLevelOrderZigZagTraversal {

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

	//==============================================================================
	//BFS
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}
		int leftRight = 0;
		Deque<TreeNode> q = new LinkedList<>();
		LinkedList<Integer> partial = new LinkedList<>();
		q.add(root);
		int size = q.size();

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			size--;

			if (leftRight == 0) {
				partial.add(node.val);
			} else {
				partial.addFirst(node.val);
			}

			if (node.left != null) {
				q.offer(node.left);
			}

			if (node.right != null) {
				q.offer(node.right);
			}

			if (size == 0) {
				result.add(partial);
				partial = new LinkedList<>();
				size = q.size();
				leftRight = leftRight == 0 ? 1 : 0;
			}
		}
		return result;
	}
	//=============================================================================
	//DFS
	public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {

		if (root != null) {
			dfs(root, 0);
		}
		return ans;
	}

	private List<List<Integer>> ans = new ArrayList<>();

	private void dfs(TreeNode root, int level) {

		if (root == null) {
			return;
		}

		if (ans.size() == level) {
			ans.add(new ArrayList<Integer>());
		}

		if (level % 2 == 0) {
			ans.get(level).add(root.val); 
		} else {
			ans.get(level).add(0, root.val);
		}
		dfs(root.left, level + 1);
		dfs(root.right, level + 1);
	}
}
