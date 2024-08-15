package leetcode.medium;

/**
 *
 *You are given an m x n binary matrix grid.
 *
 * In one operation, you can choose any row or column and flip each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 *
 * Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.
 *
 *
 *
 * Example 1:
 *              [0,1,0],        [0,1,0],       [0,0,0],
 *              [1,0,1],  -->   [0,1,0],  -->  [0,0,0],
 *              [0,1,0]         [0,1,0]        [0,0,0]
 *
 * Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
 * Output: true
 * Explanation: One possible way to remove all 1's from grid is to:
 * - Flip the middle row
 * - Flip the middle column
 * Example 2:
 *
 *
 * Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
 * Output: false
 * Explanation: It is impossible to remove all 1's from grid.
 * Example 3:
 *
 *
 * Input: grid = [[0]]
 * Output: true
 * Explanation: There are no 1's in grid.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is either 0 or 1.
 *
 */

public class _2128_RemoveAllOnesWithRowAndColumnFlips {
    //The logic is if the difference between two consicutive columns is same as
    //the first row or marker row then we can Flip it to make it all zeros or
    //Make it all ones. So we choose first row as marker row
    public boolean removeOnes(int[][] grid) {
        boolean ans = true;

        for (int row = 1; row < grid.length; row++) {

            for (int col = 1; col < grid[row].length; col++) {
                int firstRowDiff = Math.abs(grid[0][col - 1] - grid[0][col]);
                int currRowDiff = Math.abs(grid[row][col - 1] - grid[row][col]);

                if (firstRowDiff != currRowDiff) {
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }
}
