package leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 *
 *
 * Example 1:
 *
 *                  [1,2,3],
 *                  [4,5,6],
 *                  [7,8,9]
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * Example 2:
 *
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 *
 */

public class _498_DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int[] ans = new int[mat.length * mat[0].length];
        int index = 0;
        int direction = 0;

        for (int col = 0; col < mat[0].length; col++) {
            int c = col;
            int r = 0;
            List<Integer> list = new ArrayList<>();

            while (c >= 0 && r < mat.length) {
                int val = mat[r++][c--];
                list.add(val);
            }

            if (direction == 0) {
                Collections.reverse(list);
            }

            for (int n : list) {
                ans[index++] = n;
            }
            direction ^= 1;
        }

        for (int row = 1; row < mat.length; row++) {
            int r = row;
            int c = mat[0].length - 1;
            List<Integer> list = new ArrayList<>();

            while (c >= 0 && r < mat.length) {
                int val = mat[r++][c--];
                list.add(val);
            }

            if (direction == 0) {
                Collections.reverse(list);
            }

            for (int n : list) {
                ans[index++] = n;
            }
            direction ^= 1;
        }
        return ans;
    }
}
