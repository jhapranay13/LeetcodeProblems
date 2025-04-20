package leetcode.Greedy;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 *
 */

public class _334_Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        int firstNum = Integer.MAX_VALUE;
        int secondNum = Integer.MAX_VALUE;

        for (int num : nums) {

            if (num <= firstNum) {
                firstNum = num;
            } else if (num <= secondNum) {
                secondNum = num;
            } else {
                return true;
            }
        }
        return false;
    }
    public boolean increasingTriplet2(int[] nums) {
        int leftToRightMin[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                leftToRightMin[i] = nums[i];
            } else {
                leftToRightMin[i] = Math.min(leftToRightMin[i - 1], nums[i]);
            }
        }
        int rightToLeftMax[] = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {

            if (i == nums.length - 1) {
                rightToLeftMax[i] = nums[i];
            } else {
                rightToLeftMax[i] = Math.max(rightToLeftMax[i + 1], nums[i]);
            }
        }

        for (int i = 1; i < nums.length - 1; i++) {

            if (leftToRightMin[i - 1] < nums[i] && rightToLeftMax[i + 1] > nums[i]) {
                return true;
            }
        }
        return false;
    }
}
