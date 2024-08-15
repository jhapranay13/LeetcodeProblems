package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.

A leaf is a node with no children.



Example 1:

				5
              //  \\
             4     8
            //    / \\
           11   13   4
          / \\      //\
         7   2      5  1

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
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

public class _113_PathSum2 {

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

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		ArrayList<Integer> partial = new ArrayList<>();
		List<List<Integer>> ans = new ArrayList<>();
		recur(root, targetSum, 0, ans, partial);
		return ans;
	}

	private void recur(TreeNode node, int targetSum, int currSum, List<List<Integer>> ans,
			ArrayList<Integer> partial) {

		if (node == null) {
			return;
		}
		currSum += node.val;
		partial.add(node.val);

		if (node.left == null && node.right == null) {

			if (currSum == targetSum) {
				ans.add((ArrayList<Integer>)partial.clone()); 
			}
		}
		recur(node.left, targetSum, currSum, ans, partial);
		recur(node.right, targetSum, currSum, ans, partial);
		partial.remove(partial.size() - 1);
	}
}
