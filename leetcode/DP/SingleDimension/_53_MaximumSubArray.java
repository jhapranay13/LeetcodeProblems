package leetcode.DP.SingleDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
 
Constraints:

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105
 

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 */


public class _53_MaximumSubArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Kadane's Algorithm DP
	public int maxSubArray(int[] nums) {
		int max = nums[0];
		int curr = nums[0];

		for (int i = 1; i < nums.length; i++) {
			curr = Math.max(nums[i], nums[i] + curr);
			max = Math.max(max, curr);
		}
		return max;
	}

	//================================================================================

	public int maxSubArray1(int[] nums) {
		return mergeSortImpl(nums, 0, nums.length - 1);
	}

	private int mergeSortImpl(int[] nums, int lo, int hi) {

		if (lo == hi) {
			return nums[lo];
		}
		int pivot = lo + (hi - lo) / 2;
		int left = mergeSortImpl(nums, lo, pivot);
		int right = mergeSortImpl(nums, pivot + 1, hi);
		int mergeSum = merge(nums, lo, pivot, hi);            
		return Math.max(mergeSum, Math.max(left, right));
	}
	//Finding out the crossing from mid to lo and from mid + 1 to hi
	private int merge(int[] nums, int lo, int mid, int hi) {
		int curr = 0;
		int left = Integer.MIN_VALUE;

		for (int i = mid; i >= lo; i--) {
			curr += nums[i];
			left = Math.max(left, curr);
		}
		int right = Integer.MIN_VALUE;
		curr = 0;

		for (int i = mid + 1; i <= hi; i++) {
			curr += nums[i];
			right = Math.max(right, curr);
		}
		return left + right;
	}

	//===============================================================================
	//Recursive approch
	public int maxSubArray2(int[] nums) {
		recur(nums, nums.length - 1);
		return max;
	}

	private int max = Integer.MIN_VALUE;

	public int recur(int[] nums, int index) {

		if (index == 0) {
			return max = nums[index];
		}
		int nextSum = recur(nums, index - 1);
		int sum = Math.max(nums[index], nextSum + nums[index]);
		max = Math.max(sum, max);
		return sum;
	}
	//===============================================================================
	//Top Down approch
	public int maxSubArra2y(int[] nums) {
		int[] memo = new int[nums.length];
		Arrays.fill(memo, Integer.MIN_VALUE);
		recurMemo(nums, nums.length - 1, memo);
		return max;
	}

	//private int max = Integer.MIN_VALUE;

	public int recurMemo(int[] nums, int index, int[] memo) {

		if (index == 0) {
			return max = nums[index];
		}

		if (memo[index] != Integer.MIN_VALUE) {
			return memo[index];
		}
		int nextSum = recurMemo(nums, index - 1, memo);
		int sum = Math.max(nums[index], nextSum + nums[index]);
		max = Math.max(sum, max);
		return memo[index] = sum;
	}

	//===============================================================================
	//Brute Force
	public int maxSubArray3(int[] nums) {
		int maxSubarray = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			int currentSubarray = 0;
			for (int j = i; j < nums.length; j++) {
				currentSubarray += nums[j];
				maxSubarray = Math.max(maxSubarray, currentSubarray);
			}
		}

		return maxSubarray;
	}
}
