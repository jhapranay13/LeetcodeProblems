package leetcode.medium;

/**
 *
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2], k = 3
 * Output: 9
 * Example 2:
 *
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * Example 3:
 *
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 */

public class _1191_K_Concatenation_Maximum_Sum {
    // Modified Kadane
    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = 1000000007;
        long ans = arr[0] >= 0 ? arr[0] : 0;
        long currSum = arr[0];
        long sum = arr[0];
        // Making the trip twice to cover all possibilites and patterns
        for (int i = 1; i < Math.min(2, k) * arr.length; i++) {
            int index = i % arr.length;
            currSum = Math.max(arr[index], arr[index] + currSum);
            ans = Math.max(currSum, ans);

            if (i < arr.length) {
                sum += arr[i];
            }
        }

        while (sum > 0 && --k >= 2) {
            ans = (ans + sum) % mod;
        }
        return (int)(ans % mod);
    }
}
