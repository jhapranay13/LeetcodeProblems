package leetcode.BinarySearch;

/**
 *
 * Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,5], nums2 = [3,4], k = 2
 * Output: 8
 * Explanation: The 2 smallest products are:
 * - nums1[0] * nums2[0] = 2 * 3 = 6
 * - nums1[0] * nums2[1] = 2 * 4 = 8
 * The 2nd smallest product is 8.
 * Example 2:
 *
 * Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
 * Output: 0
 * Explanation: The 6 smallest products are:
 * - nums1[0] * nums2[1] = (-4) * 4 = -16
 * - nums1[0] * nums2[0] = (-4) * 2 = -8
 * - nums1[1] * nums2[1] = (-2) * 4 = -8
 * - nums1[1] * nums2[0] = (-2) * 2 = -4
 * - nums1[2] * nums2[0] = 0 * 2 = 0
 * - nums1[2] * nums2[1] = 0 * 4 = 0
 * The 6th smallest product is 0.
 * Example 3:
 *
 * Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
 * Output: -6
 * Explanation: The 3 smallest products are:
 * - nums1[0] * nums2[4] = (-2) * 5 = -10
 * - nums1[0] * nums2[3] = (-2) * 4 = -8
 * - nums1[4] * nums2[0] = 2 * (-3) = -6
 * The 3rd smallest product is -6.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 5 * 10^4
 * -10^5 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 and nums2 are sorted.
 *
 */

public class _2040_Kth_Smallest_Product_of_Two_Sorted_Arrays {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long lo = (long)-1e11;
        long hi = (long)1e11;
        long ans = 0;

        while (lo < hi) {
            long pivot =  lo + (hi - lo) / 2;

            if (countNumber(nums1, nums2 , pivot) < k) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot;
            }
        }
        return ans;
    }

    private long countNumber(int[] nums1, int[] nums2, long prod) {
        long ans = 0;

        for (int n1 : nums1) {
            long count = 0;
            int lo = 0;
            int hi = nums2.length - 1;

            if (n1 > 0) {

                while (lo <= hi) {
                    int pivot = lo + (hi - lo) / 2;
                    // if product is less than or equal to then all the previous val is also less
                    if ((long)n1 * nums2[pivot] <= prod) {
                        lo = pivot + 1;
                        count = pivot + 1;
                    } else {
                        hi = pivot - 1;
                    }
                }
                ans = ans + count;
            } else {

                while (lo <= hi) {
                    int pivot = lo + (hi - lo) / 2;
                    // if product is less than then all greater than this will also be less
                    // since it is -ve and all the number lesser can be -ve so it could be greater
                    if ((long)n1 * nums2[pivot] <= prod) {
                        count = nums2.length - pivot;
                        hi = pivot - 1;
                    } else {
                        lo = pivot + 1;
                    }
                }
                ans = ans + count;
            }
        }
        return ans;
    }
}
