package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, return an array answer such that
 *         answer[i] is equal to the product of all the elements of nums except
 *         nums[i].
 * 
 *         The product of any prefix or suffix of nums is guaranteed to fit in a
 *         32-bit integer.
 * 
 *         You must write an algorithm that runs in O(n) time and without using
 *         the division operation.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,4] Output: [24,12,8,6] Example 2:
 * 
 *         Input: nums = [-1,1,0,-3,3] Output: [0,0,9,0,0]
 * 
 * 
 *         Constraints:
 * 
 *         2 <= nums.length <= 105 -30 <= nums[i] <= 30 The product of any
 *         prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
 * 
 *         Follow up: Can you solve the problem in O(1) extra space complexity?
 *         (The output array does not count as extra space for space complexity
 *         analysis.)
 */

public class _238_ProductOfArrayExceptSelf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Technique usually used when value of I depends on value of i + 1 and i - 1

	public int[] productExceptSelf(int[] nums) {
		int[] leftToRight = new int[nums.length];
		int[] rightToLeft = new int[nums.length];
		leftToRight[0] = nums[0];

		for (int i = 1; i < nums.length - 1; i++) {
			leftToRight[i] = leftToRight[i - 1] * nums[i];
		}
		rightToLeft[nums.length -1] = nums[nums.length - 1];

		for (int i = nums.length - 2; i >= 0; i--) {
			rightToLeft[i] = rightToLeft[i + 1] * nums[i];
		}
		int[] ans = new int[nums.length];
		ans[0] = rightToLeft[1];
		ans[nums.length - 1] = leftToRight[nums.length - 2];

		for (int i = 1; i < nums.length - 1; i++) {
			ans[i] = rightToLeft[i + 1] * leftToRight[i - 1];
		}
		return ans;
	}
	//=============================================================================
	//Constant Space Version
	public int[] productExceptSelf1(int[] nums) {

        // The length of the input array 
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all 
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the 
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}
