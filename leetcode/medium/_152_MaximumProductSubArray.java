package leetcode.medium;

/**
 *
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 */

public class _152_MaximumProductSubArray {

    public static void main(String[] args) {

    }
    //Kadane variation
    //=============================================================================================
    //Recursive approach
    public int maxProduct(int[] nums) {
        recur(nums, 0);
        return ans;
    }
    private int ans = 0;
    private int maxSoFar = Integer.MIN_VALUE;
    private int minSoFar = Integer.MAX_VALUE;

    private int recur(int[] nums, int index) {

        if (index == nums.length  - 1) {
            ans = nums[index];
            maxSoFar = nums[index];
            minSoFar = nums[index];
            return nums[index];
        }
        int next = recur(nums, index + 1);
        int curr = nums[index];
        //3 Scenarios for min and max. Curr, Curr * maxSoFar, Curr * minSoFar
        int tempMax = maxSoFar;
        maxSoFar = Math.max(curr, Math.max(curr * maxSoFar, curr * minSoFar));
        minSoFar = Math.min(curr, Math.min(curr * tempMax, curr * minSoFar));
        ans = Math.max(ans, maxSoFar);
        return maxSoFar;
    }
    //=============================================================================================
    //Top Down
    public int maxProduct1(int[] nums) {
        Integer[] memo = new Integer[nums.length];
        recur(nums, 0, memo);
        return ans;
    }

    private int recur(int[] nums, int index, Integer[] memo) {

        if (index == nums.length  - 1) {
            ans = nums[index];
            maxSoFar = nums[index];
            minSoFar = nums[index];
            return nums[index];
        }

        if (memo[index] != null) {
            return memo[index];
        }
        int next = recur(nums, index + 1, memo);
        int curr = nums[index];
        //3 Scenarios for min and max. Curr, Curr * maxSoFar, Curr * minSoFar
        int tempMax = maxSoFar;
        maxSoFar = Math.max(curr, Math.max(curr * maxSoFar, curr * minSoFar));
        minSoFar = Math.min(curr, Math.min(curr * tempMax, curr * minSoFar));
        ans = Math.max(ans, maxSoFar);
        return memo[index] = maxSoFar;
    }
    //=============================================================================================
    //Bottom up
    public int maxProduct2(int[] nums) {
        Integer[] memo = new Integer[nums.length];

        for (int index = nums.length - 1; index >= 0; index--) {

            if (index == nums.length  - 1) {
                ans = nums[index];
                maxSoFar = nums[index];
                minSoFar = nums[index];
                memo[index] = nums[index];
                continue;
            }
            int next = memo[index + 1];
            int curr = nums[index];
            //3 Scenarios for min and max. Curr, Curr * maxSoFar, Curr * minSoFar
            int tempMax = maxSoFar;
            maxSoFar = Math.max(curr, Math.max(curr * maxSoFar, curr * minSoFar));
            minSoFar = Math.min(curr, Math.min(curr * tempMax, curr * minSoFar));
            ans = Math.max(ans, maxSoFar);
            memo[index] = maxSoFar;
        }
        return ans;
    }
}
