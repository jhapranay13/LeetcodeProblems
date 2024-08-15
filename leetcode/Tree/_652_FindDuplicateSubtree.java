package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, return all duplicate subtrees.
 * 
 *         For each kind of duplicate subtrees, you only need to return the root
 *         node of any one of them.
 * 
 *         Two trees are duplicate if they have the same structure with the same
 *         node values.
 * 
 * 
 * 
 *         Example 1:
 *         					1
 *         				   / \
 * 						  2   4	
 * 						 / \
 * 						4   2
 * 						   /
 * 						  4		
 * 
 *         Input: root = [1,2,3,4,null,2,4,null,null,4] Output: [[2,4],[4]]
 *         
 *         Example 2:
 * 							2
 * 						   / \
 * 						  1   1
 * 
 *         Input: root = [2,1,1] Output: [[1]] 
 *         
 *         Example 3:
 * 							2
 * 						   / \
 * 						  2   2
 * 						 /   /
 * 						3   3		
 * 	
 *         Input: root = [2,2,2,3,null,3,null] Output: [[2,3],[3]]
 * 
 * 
 *         Constraints:
 * 
 *         The number of the nodes in the tree will be in the range [1, 10^4]
 *         -200 <= Node.val <= 200
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

public class _652_FindDuplicateSubtree {

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

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		Map<String, Integer> serialFreqMap = new HashMap<>();
		List<TreeNode> ans = new ArrayList<>();
		getSerialForAllSubTree(root, serialFreqMap, ans);
		return ans;
	}

	private String getSerialForAllSubTree(TreeNode node, Map<String, Integer> serialFreqMap,
			List<TreeNode> ans) {
		if (node == null) {
			return "#";
		}
		String serial = node.val + "," + getSerialForAllSubTree(node.left, 
				serialFreqMap, ans) + 
				"," + getSerialForAllSubTree(node.right, serialFreqMap, ans);
		int count = serialFreqMap.getOrDefault(serial, 0);
		serialFreqMap.put(serial, ++count);

		if (count == 2) {
			ans.add(node);
		}
		return serial;
	}
}
