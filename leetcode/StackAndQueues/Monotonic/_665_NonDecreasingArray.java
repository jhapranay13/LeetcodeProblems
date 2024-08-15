package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums with n integers, your task is to check if it
 *         could become non-decreasing by modifying at most one element.
 * 
 *         We define an array is non-decreasing if nums[i] <= nums[i + 1] holds
 *         for every i (0-based) such that (0 <= i <= n - 2).
 * 
 *         Example 1:
 * 
 *         Input: nums = [4,2,3] Output: true Explanation: You could modify the
 *         first 4 to 1 to get a non-decreasing array. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [4,2,1] Output: false Explanation: You can't get a
 *         non-decreasing array by modify at most one element.
 * 
 * 
 *         Constraints:
 * 
 *         n == nums.length 1 <= n <= 104 -105 <= nums[i] <= 105
 *
 */

public class _665_NonDecreasingArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public boolean checkPossibility(int[] nums) {
		int count = 0;

		for (int i = 0; i < nums.length - 1; i++) {

			if (count > 1) {
				return false;
			}

			if (nums[i] > nums[i + 1]) {
				count++;
				// Check to see if ith value can change
				if (i == 0 || (i + 1 < nums.length && i - 1 >= 0 && nums[i - 1] <= nums[i + 1])) {
					continue;
				}
				// check to see if i + 1 value can change if i th value cannot change
				if (i + 2 < nums.length && nums[i] > nums[i + 2]) {
					count++;
					i++;
				}

			}
		}
		return count <= 1;
	}
	//=============================================================================
	//Using Monotonic Stack
	public boolean checkPossibility1(int[] nums) {
		int count = 0;
		Deque<Integer> monoStack = new LinkedList<>();

		for (int i = 0; i < nums.length; i++) {

			while (!monoStack.isEmpty() && monoStack.peek() > nums[i]) {
				//if last elemnt is less
				if (i == nums.length - 1) {
					count++;
					break;
				}
				//if we have to remove the ith element and it solves the problem
				if (i + 1 < nums.length && monoStack.peek() < nums[i + 1]) {
					i++;
					count++;
					break;
				}
				//else remove all other elements
				monoStack.pop();
				count++;
			}
			monoStack.push(nums[i]);
		}
		return count <= 1;
	}
}
