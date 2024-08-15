package leetcode.medium;

/**
 *
 * A split of an integer array is good if:
 *
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 *
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^4
 *
 */

public class _1712_Ways_to_Split_Array_Into_Three_Subarrays {
    public int waysToSplit(int[] nums) {

        if (nums.length == 3) {
            return nums[0] <= nums[1] && nums[1] <= nums[2] ? 1 : 0;
        }
        int[] presum = new int[nums.length];

        for (int i = 0; i < presum.length; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = presum[i - 1] + nums[i];
            }
        }
        long ans = 0;
        long MOD = 1000000007;

        for (int i = 0 ; i < nums.length - 2; i++) {
            int sum = presum[i];
            int leftMin = binarySearchLeftSide(presum, sum, i + 1);
            int sumMid = presum[leftMin] - presum[i];

            if (sum > sumMid || sumMid > presum[nums.length - 1] - presum[leftMin]) {
                continue;
            }
            int rightMax = binarySearchRightSide(presum, i, leftMin);
            // Wea re finding the range so that we can figure of number of subarrays
            // Basically number of subaarays that will fulfil the condition is the number of ways

            ans += rightMax - leftMin + 1;
            ans %= MOD;
        }
        return (int)ans;
    }

    private int binarySearchLeftSide(int[] presum, int target, int start) {
        int lo = start;
        int hi = presum.length - 2;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            int sum = presum[pivot] - presum[start - 1];

            if (sum >= target) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return lo;
    }

    private int binarySearchRightSide(int[] presum, int index, int start) {
        int lo = start;
        int hi = presum.length - 2;
        int ans = lo;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int sum = presum[pivot] - presum[index];
            int rightSum = presum[presum.length - 1] - presum[pivot];

            if (sum > rightSum) {
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
                ans = pivot;
            }
        }
        return ans;
    }
}
