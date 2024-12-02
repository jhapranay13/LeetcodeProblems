package leetcode.DP;

/**
 *
 *
 * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
 *
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 *
 * The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
 * The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
 * The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
 * Return true if the array has at least one valid partition. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,4,4,5,6]
 * Output: true
 * Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
 * This partition is valid, so we return true.
 * Example 2:
 *
 * Input: nums = [1,1,1,2]
 * Output: false
 * Explanation: There is no valid partition for this array.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 *
 */

public class _2369_Check_if_There_is_a_Valid_Partition_For_The_Array {
    // Top down
    public boolean validPartition(int[] nums) {
        Boolean[] memo = new Boolean[nums.length + 1];
        return recur(nums, 0, memo);
    }

    private boolean recur(int[] nums, int index, Boolean[] memo) {

        if (index == nums.length) {
            return memo[index] = true;
        }

        if (memo[index] != null) {
            return memo[index];
        }
        boolean ans = false;

        if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            ans = recur(nums, index + 2, memo);
        }

        if (!ans && index + 2 < nums.length) {

            if(nums[index] == nums[index + 1] && nums[index] == nums[index + 2]) {
                ans = recur(nums, index + 3, memo);
            }

            if (!ans &&
                    (nums[index] - nums[index + 1] == -1 &&
                            nums[index + 1] - nums[index + 2] == -1)) {
                ans = recur(nums, index + 3, memo);
            }
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    // Bottom up
    public boolean validPartition1(int[] nums) {
        Boolean[] memo = new Boolean[nums.length + 1];
        memo[nums.length] = true;

        for (int index = nums.length - 1; index >= 0; index--) {
            boolean ans = false;

            if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
                ans = memo[index + 2];
            }

            if (!ans && index + 2 < nums.length) {

                if(nums[index] == nums[index + 1] && nums[index] == nums[index + 2]) {
                    ans = memo[index + 3];
                }

                if (!ans &&
                        (nums[index] - nums[index + 1] == -1 &&
                                nums[index + 1] - nums[index + 2] == -1)) {
                    ans = memo[index + 3];
                }
            }
            memo[index] = ans;
        }
        return memo[0];
    }
}
