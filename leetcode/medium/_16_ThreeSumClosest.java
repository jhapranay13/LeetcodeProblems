package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.



Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
 *
 */
public class _16_ThreeSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int ans = 0;
		int closest = Integer.MAX_VALUE;

		outer:
			for (int i = 0; i < nums.length; i++) {

				for (int j = i + 1, k = nums.length - 1; j < k; ) {
					int sum = nums[i] + nums[j] + nums[k];

					if (sum == target) {
						ans = sum;
						break outer;
					}
					int diff = Math.abs(target - sum);

					if (diff < closest) {
						closest = diff;
						ans = sum;
					}

					if (sum > target) {
						k--;
					} else {
						j++;
					}
				}
			}
		return ans;
	}
	// Time complexity n^2
	//==================================================================================================

	public int threeSumClosest1(int[] nums, int target) {
		Arrays.sort(nums);
		int ans = 0;
		int closest = Integer.MAX_VALUE;

		outer:
			for (int i = 0; i < nums.length; i++) {
				int lo = i + 1;
				int hi = nums.length - 1;

				while (lo < hi) {
					int sum = nums[i] + nums[lo] + nums[hi];

					if (sum == target) {
						ans = sum;
						break outer;      
					}
					int diff = Math.abs(target - sum);

					if (diff < closest) {
						closest = diff;
						ans = sum;
					}

					if (sum < target) {
						lo++;
					} else {
						hi--;
					}   
				}
			}
		return ans;
	}
}
