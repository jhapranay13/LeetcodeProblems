package leetcode.medium;

import java.util.Arrays;

/**
 *
 * Given two integer arrays nums1 and nums2 of length n, count the pairs of indices (i, j) such that i < j and nums1[i] + nums1[j] > nums2[i] + nums2[j].
 *
 * Return the number of pairs satisfying the condition.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,1,2,1], nums2 = [1,2,1,2]
 * Output: 1
 * Explanation: The pairs satisfying the condition are:
 * - (0, 2) where 2 + 2 > 1 + 1.
 * Example 2:
 *
 * Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
 * Output: 5
 * Explanation: The pairs satisfying the condition are:
 * - (0, 1) where 1 + 10 > 1 + 4.
 * - (0, 2) where 1 + 6 > 1 + 1.
 * - (1, 2) where 10 + 6 > 4 + 1.
 * - (1, 3) where 10 + 2 > 4 + 5.
 * - (2, 3) where 6 + 2 > 1 + 5.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^5
 *
 */

public class _1885_Count_Pairs_in_Two_Arrays {
    //Two Pointer
    /*
        nums1[i] + nums1[j] > nums2[i] + nums2[j]
        putting all I in one side and J in one side
        nums1[i] - nums2[i] > nums2[j] - nums1[j]
        putting all values on on side
        nums1[i] - nums2[i] + nums1[j] - nums2[j] > 0
        where i < j
        So basically it is that sum of difference of i and j should be greater than 0.
    */
    public long countPairs(int[] nums1, int[] nums2) {
        int diff[] = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            diff[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(diff);
        long ans = 0;
        int lo = 0;
        int hi = diff.length - 1;

        while (lo < hi) {

            if (diff[lo] + diff[hi] > 0) {
                ans += hi - lo;
                hi--;
            } else {
                lo++;
            }
        }
        return ans;
    }
    //=============================================================================================
    //Binary search
    public long countPairs1(int[] nums1, int[] nums2) {
        int diff[] = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            diff[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(diff);
        long ans = 0;

        for (int i = diff.length - 1; i >= 0; i--) {
            int dif = diff[i];
            int index = binarySearchLessThan(diff, dif);

            if (index >= i || index == -1) {
                break;
            }
            ans += i - index;
        }
        return ans;
    }

    private int binarySearchLessThan(int[] arr, int val) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr[pivot] + val <= 0) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
