package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a binary array nums and an integer k, return the maximum number
 *         of consecutive 1's in the array if you can flip at most k 0's.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 Output: 6 Explanation:
 *         [1,1,1,0,0,1,1,1,1,1,1] Bolded numbers were flipped from 0 to 1. The
 *         longest subarray is underlined.
 * 
 *         Example 2:
 * 
 *         Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3 Output:
 *         10 Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1] Bolded
 *         numbers were flipped from 0 to 1. The longest subarray is underlined.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 nums[i] is either 0 or 1. 0 <= k <=
 *         nums.length
 *
 */

public class _1004_MaxConsecutivrOnes3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestOnes(int[] nums, int k) {
		int slow = 0;
		int fast = 0;
		int count = 0;
		int ans = 0;

		while (fast < nums.length) {

			if (nums[fast] == 0) {
				count++;
			}

			if (count > k) {
				while (slow < fast && nums[slow] != 0) {
					slow++;
				}
				slow++;
				count--;
			}
			ans = Math.max(ans, fast - slow + 1);
			fast++;
		}
		return ans;
	}
}
