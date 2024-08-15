package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree and an integer targetSum, return the
 *         number of paths where the sum of the values along the path equals
 *         targetSum.
 * 
 *         The path does not need to start or end at the root or a leaf, but it
 *         must go downwards (i.e., traveling only from parent nodes to child
 *         nodes).
 * 
 * 
 * 
 *         Example 1:
 *         					10
 *                         /  \
 *                        /    \
 *                       /      \
 *                      5       -3
 *                    // \\      \\
 *                    3   2       11
 *                   //    \\
 *                  3       1 
 * 
 * 
 *         Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 *         Output: 3 Explanation: The paths that sum to 8 are shown. Example 2:
 * 
 *         Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *         Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 1000]. -109 <=
 *         Node.val <= 109 -1000 <= targetSum <= 1000
 *
 */

import java.util.HashMap;
import java.util.Map;

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

public class _437_PathSum3 {

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

	public int pathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}
		int ans = recur(root, targetSum);
		int left = pathSum(root.left, targetSum);
		int right = pathSum(root.right, targetSum);
		return ans + left + right;
	}

	private int recur(TreeNode node, int target) {

		if (node == null) {
			return 0;
		}
		int ans = 0;

		if (target - node.val == 0) {
			ans = 1;    
		}
		int left = recur(node.left, target - node.val);
		int right = recur(node.right, target - node.val);
		return ans + left + right;
	}
	//=============================================================================================
	//Using Similar Concept to 560. Presum in hashmap types. Or Two Sum types
	public int pathSum1(TreeNode root, int targetSum) {
		Map<Integer, Integer> sumCountMap = new HashMap<>();
		sumCountMap.put(0, 1);
		recur(root, targetSum, sumCountMap, 0);
		return ans;
	}

	private int ans = 0;

	private void recur(TreeNode node, int targetSum, Map<Integer, Integer> sumCountMap, int sum) {

		if (node == null) {
			return;
		}
		int currSum = sum + node.val;
		ans += sumCountMap.getOrDefault(currSum - targetSum, 0);
		sumCountMap.put(currSum, sumCountMap.getOrDefault(currSum, 0) + 1);
		recur(node.left, targetSum, sumCountMap, currSum);
		recur(node.right, targetSum, sumCountMap, currSum);
		//Removing or reducing since we will be going on seperate branch
		if (sumCountMap.get(currSum) == 1) {
			sumCountMap.remove(currSum);
		} else {
			sumCountMap.put(currSum, sumCountMap.get(currSum) - 1);
		}
	}
}
