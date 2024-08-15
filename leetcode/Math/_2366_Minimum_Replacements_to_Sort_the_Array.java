package leetcode.Math;

/**
 *
 * You are given a 0-indexed integer array nums. In one operation you can replace any element of the array with any two elements that sum to it.
 *
 * For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2 and 4 and convert nums to [5,2,4,7].
 * Return the minimum number of operations to make an array that is sorted in non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,9,3]
 * Output: 2
 * Explanation: Here are the steps to sort the array in non-decreasing order:
 * - From [3,9,3], replace the 9 with 3 and 6 so the array becomes [3,3,6,3]
 * - From [3,3,6,3], replace the 6 with 3 and 3 so the array becomes [3,3,3,3,3]
 * There are 2 steps to sort the array in non-decreasing order. Therefore, we return 2.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 * Explanation: The array is already in non-decreasing order. Therefore, we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 */

public class _2366_Minimum_Replacements_to_Sort_the_Array {
    public long minimumReplacement(int[] nums) {
        long ans = 0;
        int prev = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i --) {

            if (prev < nums[i]) {
                int div = 0;

                if (nums[i] % prev == 0) {
                    div = nums[i] / prev;
                } else {
                    div = nums[i] / prev + 1;
                }
                ans += div - 1;
                /*
                what is reason behind for finding prev by prev=nums[i]/div; how it find best fit
                suppose a number is 10 and we have to divide it into 3 times,
                then our aim is to make minimum no from these three as maximum as possible,
                but why ??
                suppose array is 3 10 4
                then our ans is , 2
                because we divide 10 into 3 pieces i.e, 3 3 4
                but what if i divide it into 2 4 4, ?
                still the answer is 2 ???
                No answers is 3 now,
                */
                prev = nums[i] / div;
            } else {
                prev = nums[i];
            }
        }
        return ans;
    }
}
