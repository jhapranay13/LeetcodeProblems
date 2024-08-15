package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         An integer array is called arithmetic if it consists of at least
 *         three elements and if the difference between any two consecutive
 *         elements is the same.
 * 
 *         For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic
 *         sequences. Given an integer array nums, return the number of
 *         arithmetic subarrays of nums.
 * 
 *         A subarray is a contiguous subsequence of the array.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,4] Output: 3 Explanation: We have 3 arithmetic
 *         slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 5000 -1000 <= nums[i] <= 1000
 *
 */

public class _413_ArithmeticSlices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int numberOfArithmeticSlices(int[] nums) {
		recur(nums, 0);
		return sum;
	}

	private int sum = 0;

	private int recur(int[] nums, int index) {

		if (index >= nums.length - 2) {
			return 0;
		}
		int ans = 0;

		if (nums[index] - nums[index + 1] == nums[index + 1] - nums[index + 2]) {
			ans = 1 + recur(nums, index + 1);
			sum += ans;
		} else {
			recur(nums, index + 1);
		}
		return ans;
	}
}
