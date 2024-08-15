package leetcode.Arrays;

/**
 *
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 *
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 *
 * Return arr after applying all the updates.
 *
 *
 *
 * Example 1:
 *              0  0  0  0  0
 *                     V
 *              0  2  2  2  0
 *                     V
 *              0  2  5  5  3
 *                     V
 *             -2  0  3  5  3
 *
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 * Example 2:
 *
 * Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
 * Output: [0,-4,2,2,2,4,4,-4,-4,-4]
 *
 *
 * Constraints:
 *
 * 1 <= length <= 105
 * 0 <= updates.length <= 104
 * 0 <= startIdxi <= endIdxi < length
 * -1000 <= inci <= 1000
 *
 *
 */

public class _370_RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];

        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            ans[start] += val;
            //Since for prefix sum we will move left to right. left value will be added to
            //right so after the end we will have to subtract the same value.
            if (end < length - 1) {
                ans[end + 1] -= val;
            }
        }

        for (int i = 1; i < length; i++) {
            ans[i] = ans[i - 1] + ans[i];
        }
        return ans;
    }
}
