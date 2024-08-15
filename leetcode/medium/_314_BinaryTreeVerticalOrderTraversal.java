package leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, return the vertical order traversal
 *         of its nodes' values. (i.e., from top to bottom, column by column).
 * 
 *         If two nodes are in the same row and column, the order should be from
 *         left to right.
 * 
 * 
 * 
 *         Example 1:
 * 						3
 * 					   / \
 * 					  9   20
 * 						 /  \
 * 						15   7			
 * 
 *         Input: root = [3,9,20,null,null,15,7] Output: [[9],[3,15],[20],[7]]
 *         
 *         Example 2:
 * 						
 * 						3
 * 					   / \
 *                    /   \
 *                   9     8
 *                  / \   / \
 *                 4   0 1   7
 * 
 *         Input: root = [3,9,8,4,0,1,7] Output: [[4],[9],[3,0,1],[8],[7]]
 *         
 *         Example 3:
 * 						3
 * 					   / \
 *                    /   \
 *                   9     8
 *                  / \   / \
 *                 4   0 1   7		
 *                 	  /	  \
 *                   5     2
 * 
 *         Input: root = [3,9,8,4,0,1,7,null,null,null,2,5] Output:
 *         [[4],[9,5],[3,0,1],[8,2],[7]] Example 4:
 * 
 *         Input: root = [] Output: []
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 100]. -100 <=
 *         Node.val <= 100
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


public class _314_BinaryTreeVerticalOrderTraversal {

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

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();

		if(root == null) {
			return ans;
		}
		Map<Integer, List<int[]>> weightMap = new HashMap<>();
		recur(root, weightMap, 0, 0);

		while (minWeight <= maxWeight) {
			List<int[]> weightList = weightMap.getOrDefault(minWeight++, new ArrayList<>());
			Collections.sort(weightList, new Comparator<int[]>() 	{
				public int compare(int[] arr1, int[] arr2) {
					return arr1[1] - arr2[1];
				}
			});
			List<Integer> temp = new ArrayList<>();

			for (int[] wList: weightList) {
				temp.add(wList[0]);
			}
			ans.add(temp);
		}
		return ans;
	}
	private int minWeight = 0;
	private int maxWeight = 0;

	private void recur(TreeNode node, Map<Integer, List<int[]>> weightMap, int weight, 
			int level) {

		if (node == null) {
			return;
		}
		List<int[]> weightList = weightMap.getOrDefault(weight, new ArrayList<>());
		weightList.add(new int[] {node.val, level});
		weightMap.put(weight, weightList);
		minWeight = Math.min(minWeight, weight);
		maxWeight = Math.max(maxWeight, weight);
		recur(node.left, weightMap, weight - 1, level + 1);
		recur(node.right, weightMap, weight + 1, level + 1);
	}
}
