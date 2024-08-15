package leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary search tree, a target value, and an
 *         integer k, return the k values in the BST that are closest to the
 *         target. You may return the answer in any order.
 * 
 *         You are guaranteed to have only one unique set of k values in the BST
 *         that are closest to the target.
 * 
 * 
 * 
 *         Example 1:
 * 						4
 * 					  /	  \
 * 					 2     5
 * 					/ \	
 * 				   1   3
 * 
 *         Input: root = [4,2,5,1,3], target = 3.714286, k = 2 Output: [4,3]
 *         
 *         Example 2:
 * 
 *         Input: root = [1], target = 0.000000, k = 1 Output: [1]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is n. 1 <= k <= n <= 104. 0 <=
 *         Node.val <= 109 -109 <= target <= 109
 * 
 * 
 *         Follow up: Assume that the BST is balanced. Could you solve it in
 *         less than O(n) runtime (where n = total nodes)?
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

public class _272_ClosestBinarySearchTreeValue2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> ans = new ArrayList<>();

		if (root == null) {
			return ans;
		}
		populateQ(root, target, k);

		while (!pq.isEmpty()) {
			double[] d = pq.poll();
			ans.add((int) (d[0]));
		}
		return ans;
	}

	private void populateQ(TreeNode node, double target, int k) {

		if (node == null) {
			return;
		}
		int val = node.val;
		double diff = Math.abs(target - val);
		pq.offer(new double[] { val, diff });

		if (pq.size() > k) {
			pq.poll();
		}
		populateQ(node.left, target, k);
		populateQ(node.right, target, k);
	}

	private PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {

		public int compare(double[] d1, double[] d2) {
			Double done = d1[1];
			Double dtwo = d2[1];
			return dtwo.compareTo(done);
		}
	});
}
