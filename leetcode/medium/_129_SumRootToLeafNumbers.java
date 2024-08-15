package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.



Example 1:
				1
			   / \
			  2   3

Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
				4
			  /   \
			 9     0
			/ \
		   5   1

Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.


Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
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

public class _129_SumRootToLeafNumbers {

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

	public int sumNumbers(TreeNode root) {

		if (root == null) {
			return 0;
		}
		List<Integer> nums = new ArrayList<>();
		recur(root, nums, 0);
		int sum = 0;

		for (int num : nums) {
			sum += num;
		}
		return sum;
	}

	private void recur(TreeNode node, List<Integer> nums, int currSum) {

		if (node == null) {
			return;
		}
		int val = node.val;
		currSum *= 10;
		currSum += val;

		if (node.left == null && node.right == null) {
			nums.add(currSum);
			return;
		}
		recur(node.left, nums, currSum);
		recur(node.right, nums, currSum);
	}
	//=============================================================================================
	//Another Approach
	public int sumNumbers2(TreeNode root) {

		if (root  == null) {
			return 0;
		}
		recur(root, 0);
		return ans;
	}
	private int ans = 0;

	private void recur(TreeNode node, int sum) {
		int num = sum * 10 + node.val;

		if (node.left == null && node.right == null) {
			ans += num;
			return;
		}

		if (node.left != null) {
			recur(node.left, num);
		}

		if (node.right != null) {
			recur(node.right, num);
		}
	}
}
