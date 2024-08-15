package leetcode.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums of n integers, return an array of all the unique
 *         quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 
 *         0 <= a, b, c, d < n a, b, c, and d are distinct. nums[a] + nums[b] +
 *         nums[c] + nums[d] == target You may return the answer in any order.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,0,-1,0,-2,2], target = 0 Output:
 *         [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 *         Example 2:
 * 
 *         Input: nums = [2,2,2,2,2], target = 8 Output: [[2,2,2,2]]
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 200 -109 <= nums[i] <= 109 -109 <= target <= 109
 *
 *
 */

public class _18_FourSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {

			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			for (int j = i + 1; j < nums.length; j++) {

				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				int lo = j + 1;
				int hi = nums.length - 1;

				while (lo < hi) {

					if (lo > j + 1 && nums[lo] == nums[lo - 1]) {
						lo++;
						continue;
					}

					if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
						hi--;
						continue;
					}
					int sum = nums[i] + nums[j] + nums[lo] + nums[hi];

					if (sum > target) {
						hi--;
					} else if (sum < target) {
						lo++;
					} else {
						List<Integer> temp = new ArrayList<>();
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[lo++]);
						temp.add(nums[hi--]);
						ans.add(temp);
					}
				}
			}
		}
		return ans;
	}
	// Time Complexity O(N^3)
	// =========================================================================================================

	public List<List<Integer>> fourSum1(int[] nums, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		Arrays.sort(nums);
		recur(nums, ans, partial, target, 0, 0, 4);
		return ans;
	}

	private void recur(int[] nums, List<List<Integer>> ans, ArrayList<Integer> partial, int target, int index, int sum,
			int k) {

		if (k == 2) {
			twoSum(nums, ans, partial, index, target, sum);
			return;
		}

		for (int i = index; i < nums.length; i++) {

			if (i > index && nums[i] == nums[i - 1]) {
				continue;
			}
			partial.add(nums[i]);
			recur(nums, ans, partial, target, i + 1, sum + nums[i], k - 1);
			partial.remove(partial.size() - 1);
		}
	}

	private void twoSum(int nums[], List<List<Integer>> ans, ArrayList<Integer> partial, int index, int target,
			int sum) {
		int lo = index;
		int hi = nums.length - 1;

		while (lo < hi) {

			if (lo > index && nums[lo] == nums[lo - 1]) {
				lo++;
				continue;
			}

			if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
				hi--;
				continue;
			}
			int add = nums[lo] + nums[hi] + sum;

			if (add < target) {
				lo++;
			} else if (add > target) {
				hi--;
			} else {
				partial.add(nums[lo++]);
				partial.add(nums[hi--]);
				ans.add((ArrayList<Integer>) partial.clone());
				partial.remove(partial.size() - 1);
				partial.remove(partial.size() - 1);
			}
		}
	}
	// Same time complexity. Solution can be used for K sum.
}
