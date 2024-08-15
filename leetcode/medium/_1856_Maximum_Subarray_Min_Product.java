package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 * Example 2:
 *
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 * Example 3:
 *
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^7
 *
 */

public class _1856_Maximum_Subarray_Min_Product {
    public int maxSumMinProduct(int[] nums) {
        long[] presum = new long[nums.length];
        presum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            presum[i] = presum[i - 1] + (long)nums[i];
        }
        int[] leftRange = new int[nums.length];
        Deque<Integer> mq = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                leftRange[i] = -1;
                mq.push(i);
            } else {

                while (!mq.isEmpty() && nums[mq.peek()] >= nums[i]) {
                    mq.pop();
                }
                leftRange[i] = mq.isEmpty() ? -1 : mq.peek();
                mq.push(i);
            }
        }
        mq.clear();
        int[] rightRange = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {

            if (nums.length - 1 == i) {
                rightRange[i] = nums.length;
                mq.push(i);
            } else {

                while (!mq.isEmpty() && nums[mq.peek()] >= nums[i]) {
                    mq.pop();
                }
                rightRange[i] = mq.isEmpty() ? nums.length : mq.peek();
                mq.push(i);
            }
        }
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int start = leftRange[i] + 1;
            int end = rightRange[i] - 1;
            long sum = presum[end] - (start == 0 ? 0 : presum[start - 1]);
            ans = Math.max(ans, nums[i] * sum);
        }
        return (int)(ans % 1000000007);
    }
}
