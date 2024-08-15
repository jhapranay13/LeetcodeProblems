package leetcode.BackTrackingRecursion;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
			0            0
		   / \         /  \
		 -3   9      -10   5 
		 /   /         \    \
	  -10   5          -3    9

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:

			1               3
			 \             /
			  3           1

Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
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

public class _108_ConvertSortedArrayToBinarySearchTree {

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

	public TreeNode sortedArrayToBST(int[] nums) {
		return recur(nums, 0, nums.length - 1);
	}

	private TreeNode recur(int[] nums, int lo, int hi) {

		if (lo > hi) {
			return null;
		}
		int pivot = lo + (hi - lo) / 2;
		TreeNode node = new TreeNode();
		node.val = nums[pivot];
		node.left = recur(nums, lo, pivot - 1);
		node.right = recur(nums, pivot + 1, hi);
		return node;
	}
}
