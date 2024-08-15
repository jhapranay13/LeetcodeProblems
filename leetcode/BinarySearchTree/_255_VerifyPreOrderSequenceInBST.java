package leetcode.BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of unique integers preorder, return true if it is the
 *         correct preorder traversal sequence of a binary search tree.
 * 
 *         Example 1:
 * 						5
 * 					   / \
 *                    /   \
 *                   2     6
 *                  / \
 *                 1   3 
 * 
 *         Input: preorder = [5,2,1,3,6] Output: true 
 *         
 *         
 *         Example 2:
 * 
 *         Input: preorder = [5,2,6,1,3] Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= preorder.length <= 104 1 <= preorder[i] <= 104 All the elements
 *         of preorder are unique.
 * 
 * 
 *         Follow up: Could you do it using only constant space complexity?
 *
 */

public class _255_VerifyPreOrderSequenceInBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	public boolean verifyPreorder(int[] preorder) {

		for (int i = 0; i < preorder.length; i++) {
			int val = preorder[i];
			int j = i + 1;

			while (j < preorder.length && preorder[j] < val ) {
				j++;
			}

			while (j < preorder.length) {

				if (preorder[j] < val) {
					return false;
				}
				j++;
			}
		}
		return true;
	}
	//=============================================================================
	//Recursive Solution
	public boolean verifyPreorder1(int[] preorder) {
		return recur(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private int index = 0;

	private boolean recur(int[] preorder, int min, int max) {

		if (index >= preorder.length) {
			return true;
		}
		int val = preorder[index];

		if (val < min || val > max) {
			return false;
		}
		index++;
		boolean left = recur(preorder, min, val);
		boolean right = recur(preorder, val, max);
		return left || right;
	}
	//=============================================================================
	//Stack based Solution
	public boolean verifyPreorder2(int[] preorder) {
		Deque<Integer> stack = new LinkedList<>();
		int prev = 0;

		for (int curr : preorder) {

			while (!stack.isEmpty() && curr > stack.peek()) {
				prev = stack.pop();
			}

			if (!stack.isEmpty() && prev > curr) {
				return false;
			}
			stack.push(curr);
		}
		return true;
	}
}
