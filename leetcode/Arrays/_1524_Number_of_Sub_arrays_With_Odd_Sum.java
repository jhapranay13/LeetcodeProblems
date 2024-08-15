package leetcode.Arrays;

/**
 *
 * Given an array of integers arr, return the number of subarrays with an odd sum.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 * Example 2:
 *
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 *
 */

public class _1524_Number_of_Sub_arrays_With_Odd_Sum {
    // adding odd to even result in odd and adding even to odd is still odd
    public int numOfSubarrays(int[] arr) {
        int even = 1;
        int odd = 0;
        int sum = 0;
        int ans = 0;
        int mod = 1000000007;

        for (int num : arr) {
            sum += num;

            if (sum % 2 == 0) {
                even++;
                ans = (ans + odd) % mod;
            } else {
                odd++;
                ans = (ans + even) % mod;
            }
        }
        return ans;
    }
}
