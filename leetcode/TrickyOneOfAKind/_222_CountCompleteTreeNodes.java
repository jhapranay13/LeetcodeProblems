package leetcode.TrickyOneOfAKind;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a complete binary tree, return the number of the
 *         nodes in the tree.
 * 
 *         According to Wikipedia, every level, except possibly the last, is
 *         completely filled in a complete binary tree, and all nodes in the
 *         last level are as far left as possible. It can have between 1 and 2h
 *         nodes inclusive at the last level h.
 * 
 *         Design an algorithm that runs in less than O(n) time complexity.
 * 
 *         Example 1:
 * 
 * 							1
 * 						  /   \	
 * 						 2	   3
 * 						/ \   /
 * 					   4   5 6
 * 
 *         Input: root = [1,2,3,4,5,6] Output: 6 
 *         
 *         Example 2:
 * 
 *         Input: root = [] Output: 0 Example 3:
 * 
 *         Input: root = [1] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 5 * 104]. 0 <=
 *         Node.val <= 5 * 104 The tree is guaranteed to be complete.
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

public class _222_CountCompleteTreeNodes {

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

	public int countNodes(TreeNode root) {
		int depth = calculateDepth(root);

		if (depth == 0) {
			return 1;
		}
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int terminate = 0;

		while (!queue.isEmpty() && terminate++ <= depth - 1) {
			Deque<TreeNode> nextQueue = new LinkedList<>();
			int size = queue.size();

			while (size-- > 0) {
				TreeNode node = queue.poll();

				if (node.left == null) {
					break;
				}
				nextQueue.offer(node.left);

				if (node.right == null) {
					break;
				}
				nextQueue.offer(node.right);
			}
			queue = nextQueue;
		}
		int lastLevelSize = queue.size();
		int sizeOfaboveLevels = (int)Math.pow(2, depth) - 1;
		return lastLevelSize + sizeOfaboveLevels;
	}

	private int calculateDepth(TreeNode root) {
		int depth = -1;

		while (root != null) {
			root = root.left;
			depth++;
		}
		return depth;
	}
	//=============================================================================
	//Another Recursive Approach
	public int countNodes1(TreeNode root) {

		if (root == null) {
			return 0;
		}
		return 1 + countNodes1(root.right) + countNodes1(root.left);
	}
	//=============================================================================
	//Binary Search Approach
	public int countNodes2(TreeNode root) {

		if (root == null) {
			return 0;
		}
		int depth = calculateDepth(root);

		if (depth == 0) {
			return 1;
		}
		int lo = 1;
		int hi = (int)Math.pow(2, depth) - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (exists(pivot, depth, root)) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return (int)Math.pow(2, depth) - 1 + lo;
	}

	private boolean exists(int pivot, int depth, TreeNode root) {
		int lo = 0;
		int hi = (int)Math.pow(2, depth) - 1;

		for (int i = 0; i < depth; i++) {
			int pivot1 = lo + (hi - lo) / 2;

			if (pivot <= pivot1) {
				root = root.left;
				hi = pivot1;
			} else {
				root = root.right;
				lo = pivot1 + 1;
			}
		}
		return root != null;
	}
	//=============================================================================================
	//Another Approach
	public int countNodes3(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int depth = calculateDepth(root);

		if (depth == 0) {
			return 1;
		}
		int lo = 0;
		int hi = (int)Math.pow(2, depth) - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (exists1(pivot, depth, root)) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return (int)Math.pow(2, depth) - 1 + lo;
	}

	private boolean exists1(int pivot, int depth, TreeNode node) {
		int lo = 0;
		int hi = (int)Math.pow(2, depth) - 1;

		while (depth-- > 0) {
			int p = lo + (hi - lo) / 2;

			if (p >= pivot) {
				node = node.left;
				hi = p;
			} else {
				node = node.right;
				lo = p + 1;
			}
		}
		return node != null;
	}
}
