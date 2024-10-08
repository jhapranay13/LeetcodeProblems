package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         The thief has found himself a new place for his thievery again. There
 *         is only one entrance to this area, called root.
 * 
 *         Besides the root, each house has one and only one parent house. After
 *         a tour, the smart thief realized that all houses in this place form a
 *         binary tree. It will automatically contact the police if two
 *         directly-linked houses were broken into on the same night.
 * 
 *         Given the root of the binary tree, return the maximum amount of money
 *         the thief can rob without alerting the police.
 * 
 *         Example 1:
 *         					3
 *         				   / \
 *         				  2   3
 *                         \   \
 *                          3	1	
 * 
 *         Input: root = [3,2,3,null,3,null,1] Output: 7 Explanation: Maximum
 *         amount of money the thief can rob = 3 + 3 + 1 = 7. 
 *         
 *         
 *         Example 2:
 * 							3
 *         				   / \
 *         				  4   5
 *                       / \   \
 *                      1   3	1
 * 
 *         Input: root = [3,4,5,1,3,null,1] Output: 9 Explanation: Maximum
 *         amount of money the thief can rob = 4 + 5 = 9.
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 104]. 0 <=
 *         Node.val <= 104
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

public class _337_HouseRobber3 {

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
	//=============================================================================
	//Recursive Solution
	public int rob(TreeNode root) {
		return dfs(root);
	}

	private int dfs(TreeNode node) {

		if (node == null) {
			return 0;
		}
		int include = node.val;
		int exclude = 0;

		if (node.left != null) {
			include += dfs(node.left.left) + dfs(node.left.right);
		}

		if (node.right != null) {
			include += dfs(node.right.left) + dfs(node.right.right);
		}
		exclude = dfs(node.left) + dfs(node.right);
		return Math.max(include, exclude);
	}
	//=============================================================================
	//DP Top Down HashMap memo Solution
	public int rob1(TreeNode root) {
        return dfs1(root);
    }
    private Map<TreeNode, Integer> memo = new HashMap<>();
    
    private int dfs1(TreeNode node) {
        
        if (node == null) {
            return 0;
        }
        
        if (memo.get(node) != null) {
            return memo.get(node);
        }
        int include = node.val;
        int exclude = 0;
        
        if (node.left != null) {
            include += dfs1(node.left.left) + dfs1(node.left.right);
        }
        
        if (node.right != null) {
            include += dfs1(node.right.left) + dfs1(node.right.right);
        }
        exclude = dfs1(node.left) + dfs1(node.right);
        int max = Math.max(include, exclude);
        memo.put(node, max);
        return max;
    }
}
