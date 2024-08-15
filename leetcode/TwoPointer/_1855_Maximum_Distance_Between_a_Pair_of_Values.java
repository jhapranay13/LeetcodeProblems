package leetcode.TwoPointer;

/**
 *
 * You are given two non-increasing 0-indexed integer arrays nums1​​​​​​ and nums2​​​​​​.
 *
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i​​​​.
 *
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 *
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
 * The maximum distance is 2 with pair (2,4).
 * Example 2:
 *
 * Input: nums1 = [2,2,2], nums2 = [10,10,1]
 * Output: 1
 * Explanation: The valid pairs are (0,0), (0,1), and (1,1).
 * The maximum distance is 1 with pair (0,1).
 * Example 3:
 *
 * Input: nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * Output: 2
 * Explanation: The valid pairs are (2,2), (2,3), (2,4), (3,3), and (3,4).
 * The maximum distance is 2 with pair (2,4).
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^5
 * Both nums1 and nums2 are non-increasing.
 *
 */

public class _1855_Maximum_Distance_Between_a_Pair_of_Values {
    // Two Pointers
    public int maxDistance(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int ans = 0;

        while (index1 < nums1.length && index2 < nums2.length) {

            if (nums1[index1] > nums2[index2]) {
                index1++;
            } else {
                ans = Math.max(ans, index2 - index1);
                index2++;
            }
        }
        return ans;
    }
    //=============================================================================================
    // Binary Search
    public int maxDistance1(int[] nums1, int[] nums2) {
        int ans = 0;

        for (int i = 0; i < nums1.length; i++) {
            int index = binarySearch(nums2, nums1[i]);

            if (index >= i) {
                ans = Math.max(ans, index - i);
            }
        }
        return Math.max(ans, 0);
    }

    private int binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (hi - lo == 1) {

                if (nums[hi] >= target) {
                    return hi;
                } else {
                    return lo;
                }
            }

            if (nums[pivot] >= target) {
                lo = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        return hi;
    }
}
