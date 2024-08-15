package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
 *
 * If there is no common element, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 * Example 2:
 *
 * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 10^4
 * mat[i] is sorted in strictly increasing order.
 *
 *
 */

public class _1198_Find_Smallest_Common_Element_in_All_Rows {
    public int smallestCommonElement(int[][] mat) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[i].length; j++) {

                if (j != 0 && mat[i][j] == mat[i][j - 1]) {
                    continue;
                }
                count.put(mat[i][j], count.getOrDefault(mat[i][j], 0) + 1);
            }
        }
        int ans = Integer.MAX_VALUE;

        for (int key : count.keySet()) {
            int cnt = count.get(key);

            if (cnt == mat.length) {
                ans = Math.min(ans, key);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    //=============================================================================================
    // Binary search Approach
    public int smallestCommonElement1(int[][] mat) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int j = 0; j < mat[0].length; j++) {
            int target = mat[0][j];
            count.put(target, count.getOrDefault(target, 0) + 1);

            for (int i = 1; i < mat.length; i++) {

                if (j != 0 && mat[i][j] == mat[i][j - 1]) {
                    continue;
                }
                int pos = binarySearch(mat, i, target);

                if (pos != -1) {
                    count.put(target, count.getOrDefault(target, 0) + 1);
                }
            }

            if (count.getOrDefault(target, 0) == mat.length) {
                return target;
            }
        }
        return -1;
    }

    private int binarySearch(int[][] mat, int r, int target) {
        int ans = -1;
        int lo = 0;
        int hi = mat[r].length - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (mat[r][pivot] == target) {
                return pivot;
            } else if (mat[r][pivot] < target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
