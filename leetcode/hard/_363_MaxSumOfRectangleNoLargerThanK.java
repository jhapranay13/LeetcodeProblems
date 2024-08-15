package leetcode.hard;

/**
 *
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 *
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 *
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 *
 * Follow up: What if the number of rows is much larger than the number of columns?
 *
 *
 */

public class _363_MaxSumOfRectangleNoLargerThanK {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int pre[][] = new int[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {

                if (c == 0) {
                    pre[r][c] = matrix[r][c];
                } else {
                    pre[r][c] = matrix[r][c] + pre[r][c - 1];
                }
            }
        }
        int ans = Integer.MIN_VALUE;

        for (int left = 0; left < matrix[0].length; left++) {
            for (int top = 0; top < matrix.length; top++) {

                for (int c = left; c < matrix[0].length; c++) {
                    int sum = 0;

                    for (int r = top; r < matrix.length; r++) {

                        if (left == 0) {
                            sum += pre[r][c];
                        } else {
                            sum+= pre[r][c] - pre[r][left - 1];
                        }

                        if (sum <= k) {
                            ans = Math.max(ans, sum);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
