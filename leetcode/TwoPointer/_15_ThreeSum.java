package leetcode.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, return all the triplets [nums[i],
 *         nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] +
 *         nums[j] + nums[k] == 0.
 * 
 *         Notice that the solution set must not contain duplicate triplets.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]]
 * 
 *         Example 2:
 * 
 *         Input: nums = [] Output: []
 * 
 *         Example 3:
 * 
 *         Input: nums = [0] Output: []
 * 
 * 
 *         Constraints:
 * 
 *         0 <= nums.length <= 3000 -105 <= nums[i] <= 105
 *
 */
public class _15_ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {

			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			for (int j = i + 1, k = nums.length - 1; j < k;) {

				if (j > i + 1 && nums[j] == nums[j - 1]) {
					j++;
					continue;
				}

				if (k < nums.length - 1 && nums[k] == nums[k + 1]) {
					k--;
					continue;
				}
				int sum = nums[i] + nums[j] + nums[k];

				if (sum < 0) {
					j++;
				} else if (sum > 0) {
					k--;
				} else {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					temp.add(nums[k]);
					ans.add(temp);
					j++;
					k--;
				}
			}
		}
		return ans;
	}
	// Time complexity n^2
}
