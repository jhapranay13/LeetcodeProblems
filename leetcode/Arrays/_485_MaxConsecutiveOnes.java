package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a binary array nums, return the maximum number of consecutive
 *         1's in the array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,1,0,1,1,1] Output: 3 Explanation: The first two
 *         digits or the last three digits are consecutive 1s. The maximum
 *         number of consecutive 1s is 3. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,0,1,1,0,1] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 nums[i] is either 0 or 1.
 *
 */

public class _485_MaxConsecutiveOnes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Sliding window technique
	public int findMaxConsecutiveOnes(int[] nums) {
		int slow = 0;
		int fast = 0;
		int ans = 0;

		while (fast < nums.length) {

			if (nums[fast] == 1) {
				ans = Math.max(ans, fast - slow + 1);
			} else {

				while (fast < nums.length && nums[fast] != 1) {
					fast++;
				}
				slow = fast;
				continue;
			}
			fast++;
		}
		return ans;
	}
	//=============================================================================
	//Normal way
	public int findMaxConsecutiveOnes1(int[] nums) {
		int count = 0;
		int ans = 0;

		for (int num : nums) {

			if (num == 0) {
				ans = Math.max(ans, count);
				count = 0;
			} else {
				count++;
			}
		}
		ans = Math.max(ans, count);

		return ans;
	}
}
