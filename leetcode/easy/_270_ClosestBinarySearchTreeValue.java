package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary search tree and a target value, return the
 *         value in the BST that is closest to the target.
 * 
 * 
 * 
 *         Example 1:
 * 								4
 * 							   / \	
 *                            2   5
 *                           / \
 *                          1   3
 *                           
 *         Input: root = [4,2,5,1,3], target = 3.714286 Output: 4 Example 2:
 * 
 *         Input: root = [1], target = 4.428571 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 104]. 0 <=
 *         Node.val <= 109 -109 <= target <= 109
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

public class _270_ClosestBinarySearchTreeValue {

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

	public int closestValue(TreeNode root, double target) {
		int ans = Integer.MAX_VALUE;
		double closest = Integer.MAX_VALUE;

		while (root != null) {
			int val = root.val;
			double diff = Math.abs(val - target);

			if (diff < closest) {
				closest = diff;
				ans = val;
			}

			if (root.val > target) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return ans;
	}
	//==============================================================================
	//Recursive Approach
	public int closestValue1(TreeNode root, double target) {

		if( root == null ) {
			return 0;
		}
		getClosest( root, target );
		return closestVal;
	}
	private int closestVal = 0;
	private double diff = Double.MAX_VALUE;

	private void getClosest( TreeNode node, double target ) {

		if( node == null ) {
			return;
		}
		double difference = Math.abs( target - node.val );

		if( difference < diff ) {
			diff = difference;
			closestVal = node.val;
		}

		if( target < node.val ) {
			getClosest( node.left, target );
			return;
		} 
		getClosest( node.right, target );
	} 
}
