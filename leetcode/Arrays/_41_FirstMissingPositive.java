package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an unsorted integer array nums, return the smallest missing
 *         positive integer.
 * 
 *         You must implement an algorithm that runs in O(n) time and uses
 *         constant extra space.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,0] Output: 3 
 *         
 *         Example 2:
 * 
 *         Input: nums = [3,4,-1,1] Output: 2 
 *         
 *         Example 3:
 * 
 *         Input: nums = [7,8,9,11,12] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 5 * 105 -231 <= nums[i] <= 231 - 1
 *
 */

public class _41_FirstMissingPositive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int firstMissingPositive(int[] nums) {

		if (nums == null || nums.length == 0) {
			return 1;
		}

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] <= 0 || nums[i] > nums.length) {
				nums[i] = -1;
			}
		}
		int index = 0;

		while (index < nums.length) {

			if (nums[index] != -1 && nums[index] != index + 1) {
				int toIndex = nums[index] - 1;

				if (nums[index] == nums[toIndex]) {
					nums[index] = -1;
					continue;
				}
				swap(nums, index, toIndex);
			} else {
				index++;
			}
		}
		int ans = -1;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] == -1) {
				ans = i + 1;
				break;
			}
		}
		return ans == -1 ? nums.length + 1 : ans;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
