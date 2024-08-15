package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 *
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 *
 */

public class _718_MaximumLengthOfRepeatedSubarray {
    //Can also be solved using binary search and Rabin karp rolling hash
    //===============================================================================================
    //Top Down Approach
    public int findLength(int[] nums1, int[] nums2) {
        int ans = 0;
        int memo[][] = new int[nums1.length + 1][nums2.length + 1];

        for (int x = 0; x < nums1.length; x++) {

            for (int y = 0; y < nums2.length; y++) {
                int inc = 0;

                if (nums1[x] == nums2[y]) {
                    inc = 1 + recur(nums1, nums2, x + 1, y + 1, memo);
                }
                memo[x][y] = inc;
                ans = Math.max(ans, recur(nums1, nums2, x, y, memo));
            }
        }
        return ans;
    }

    private int recur(int[] nums1, int[] nums2, int x, int y, int[][] memo) {

        if (nums1.length == x || nums2.length == y) {
            return 0;
        }

        if (memo[x][y] > 0) {
            return memo[x][y];
        }
        int inc = 0;

        if (nums1[x] == nums2[y]) {
            inc = 1 + recur(nums1, nums2, x + 1, y + 1, memo);
        }
        return memo[x][y] = inc;
    }
    //===============================================================================================
    //Bottom up Approach
    public int findLength1(int[] nums1, int[] nums2) {
        int ans = 0;
        int memo[][] = new int[nums1.length + 1][nums2.length + 1];

        for (int x = nums1.length - 1; x >= 0; x--) {

            for (int y = nums2.length - 1; y >= 0; y--) {
                int inc = 0;

                if (nums1[x] == nums2[y]) {
                    inc = 1 + memo[x + 1][y + 1];
                }
                memo[x][y] = inc;
                ans = Math.max(ans, memo[x][y]);
            }
        }
        return ans;
    }
    //=============================================================================================
    //Binary Search + Rabin Karp
    public int findLength2(int[] nums1, int[] nums2) {
        int lo = 0;
        int hi = Math.min(nums1.length, nums2.length) - 1;

        while (lo <= hi) {
            int length = lo + (hi - lo) / 2;

            if (check(nums1, nums2, length)) {
                lo = length + 1;
            } else {
                hi = length - 1;
            }
        }
        return lo;
    }
    private long MOD = 10000000007L;

    private boolean check(int[] nums1, int[] nums2, int length) {
        long[] power = new long[length + 1];
        power[0] = 1;
        int prime = 107;

        for (int i = 1; i <= length; i++) {
            power[i] = (power[i - 1] * prime) % MOD;
        }
        int start = 0;
        int end = 0;
        long hash1 = 0;
        Set<Long> hashes = new HashSet<>();

        while (end < nums1.length) {
            hash1 = (hash1 + (nums1[end] * power[length - (end - start)]) % MOD) % MOD;

            if (end >= length) {
                hashes.add(hash1);
                hash1 = ((hash1 - (nums1[start++] * power[length]) % MOD) + MOD) % MOD;//+ MOD to take care of neagtive
                hash1 = (hash1 * prime) % MOD;
            }
            end++;
        }
        start = 0;
        end = 0;
        long hash2 = 0;

        while (end < nums2.length) {
            hash2 = (hash2 + (nums2[end] * power[length - (end - start)]) % MOD ) % MOD;

            if (end >= length) {

                if (hashes.contains(hash2)) {
                    return true;
                }
                hash2 = ((hash2 - (nums2[start++] * power[length]) % MOD) + MOD) % MOD;
                hash2 = (hash2 * prime) % MOD;
            }
            end++;
        }
        return false;
    }
}
