package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

Example 3:

Input: nums = [0]
Output: [0]

Example 4:

Input: nums = [1]
Output: [1]

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 *
 */

public class _75_SortColors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void sortColors(int[] nums) {
		int start = 0;
		int end = nums.length - 1;

		for (int curr = 0; curr < nums.length; curr++) {

			if (nums[curr] == 0) {
				swap(nums, curr, start++);
			} else if (nums[curr] == 2 && curr < end) {
				swap(nums, curr--, end--);
			} 
		}
	}

	private void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
}
