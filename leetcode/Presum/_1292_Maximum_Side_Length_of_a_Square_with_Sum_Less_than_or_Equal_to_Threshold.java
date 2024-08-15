package leetcode.Presum;

/**
 *
 *
 * Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 *
 *
 * Example 1:
 *                ___
 *              [|1___1|,3,2,4,3,2],
 *              [|1__1||,3,2,4,3,2],
 *              [|1___1|,3,2,4,3,2]
 *
 * two squares with 4 1's
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 *
 * Example 2:
 *
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 10^4
 * 0 <= threshold <= 10^5
 *
 *
 */

public class _1292_Maximum_Side_Length_of_a_Square_with_Sum_Less_than_or_Equal_to_Threshold {
    public int maxSideLength(int[][] mat, int threshold) {
        int[][] presum = new int[mat.length + 1][mat[0].length + 1];

        for (int i = 1; i <= mat.length; i++) {

            for (int j = 1; j <= mat[0].length; j++) {
                presum[i][j] = presum[i - 1][j] + presum[i][j - 1] -
                        presum[i - 1][j - 1] + mat[i - 1][j - 1] ;
            }
        }
        int length = 0;
        int ans = 0;

        for (int r = 0; r <= mat.length; r++) {

            for (int c = 0; c <= mat[0].length; c++) {

                while (r + length <= mat.length && c + length <= mat[0].length) {

                    if (presum[r + length][c + length] - presum[r][c + length] -
                            presum[r + length][c] + presum[r][c] <= threshold) {
                        ans = length++;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
