package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 *
 * You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
 *
 * Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 4.
 * Example 2:
 *
 *
 * Input: matrix = [[1,0,1,0,1]]
 * Output: 3
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 3.
 * Example 3:
 *
 * Input: matrix = [[1,1,0],[1,0,1]]
 * Output: 2
 * Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 10^5
 * matrix[i][j] is either 0 or 1.
 *
 */

public class _1727_Largest_Submatrix_With_Rearrangements {
    public int largestSubmatrix(int[][] matrix) {
        int[] prev = new int[matrix[0].length];
        int ans = 0;

        for (int[] mat : matrix) {
            int[] curr = mat.clone();

            for (int i = 0; i < curr.length; i++) {
                // if column is zero then no submTRIX CAN BE FORMED
                if (curr[i] != 0) {
                    curr[i] += prev[i];
                }
            }
            prev = curr.clone();
            Arrays.sort(curr);
            // Since we shift entire columns we can sort it and take the range
            // for submatix being created
            for(int i = 0; i < curr.length; i++) {
                int area = curr[i] * (curr.length - i);
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }
}
