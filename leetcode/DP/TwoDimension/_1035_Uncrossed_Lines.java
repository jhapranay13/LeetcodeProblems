package leetcode.DP.TwoDimension;

/**
 *
 *
 * You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 *
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 *
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 *
 *
 * Example 1:
 *
 *              1     4     2
 *              |       \
 *              |         \
 *              1     2     4
 *
 * Input: nums1 = [1,4,2], nums2 = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
 * Example 2:
 *
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 *
 */

public class _1035_Uncrossed_Lines {
    // The whole idea is that both the indexes cannot be less than previous one for lines to not intersect
    // Top down
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        Integer[][] memo = new Integer[nums1.length][nums2.length];
        return recur(nums1, nums2, 0, 0, memo);
    }

    private int recur(int[] nums1, int[] nums2, int index1, int index2, Integer[][] memo) {

        if (index1 >= nums1.length || index2 >= nums2.length) {
            return 0;
        }

        if (memo[index1][index2] != null) {
            return memo[index1][index2];
        }
        int inc = 0;

        if (nums1[index1] == nums2[index2]) {
            inc = 1 + recur(nums1, nums2, index1 + 1, index2 + 1, memo);
        }
        int skip = Math.max(recur(nums1, nums2, index1 + 1, index2, memo),
                recur(nums1, nums2, index1, index2 + 1, memo));
        return memo[index1][index2] = Math.max(inc, skip);
    }
    //=============================================================================================
    // BOTTOM Up
    public int maxUncrossedLines1(int[] nums1, int[] nums2) {
        int[][] memo = new int[nums1.length + 1][nums2.length + 1];

        for (int index1 = nums1.length - 1; index1 >= 0; index1--) {

            for (int index2 = nums2.length - 1; index2 >= 0; index2--) {
                int inc = 0;

                if (nums1[index1] == nums2[index2]) {
                    inc = 1 + memo[index1 + 1][index2 + 1];
                }
                int skip = Math.max(memo[index1 + 1][index2],
                        memo[index1][index2 + 1]);
                memo[index1][index2] = Math.max(inc, skip);
            }
        }
        return memo[0][0];
    }
}
