package leetcode.DP;

import java.util.*;

/**
 *
 *
 * Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.
 *
 * From the starting cell, you can move to any other cell in the same row or column, but only if the value of the destination cell is strictly greater than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.
 *
 * Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.
 *
 * Return an integer denoting the maximum number of cells that can be visited.
 *
 *
 *
 * Example 1:
 *
 *      3  1
 *         |
 *         V
 *      3  4
 *
 * Input: mat = [[3,1],[3,4]]
 * Output: 2
 * Explanation: The image shows how we can visit 2 cells starting from row 1, column 2. It can be shown that we cannot visit more than 2 cells no matter where we start from, so the answer is 2.
 * Example 2:
 *
 *
 *
 * Input: mat = [[1,1],[1,1]]
 * Output: 1
 * Explanation: Since the cells must be strictly increasing, we can only visit one cell in this example.
 * Example 3:
 *        _____
 *       |     V
 *       3  1  6
 *       ^     |
 *       |     V
 *      -9  5  7
 *
 * Input: mat = [[3,1,6],[-9,5,7]]
 * Output: 4
 * Explanation: The image above shows how we can visit 4 cells starting from row 2, column 1. It can be shown that we cannot visit more than 4 cells no matter where we start from, so the answer is 4.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * -105 <= mat[i][j] <= 105
 *
 *
 */

public class _2713_Maximum_Strictly_Increasing_Cells_in_a_Matrix {
    // TLE For both
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        int maxCells = 1;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cells = dfs(mat, dp, i, j, m, n);
                maxCells = Math.max(maxCells, cells);
            }
        }

        return maxCells;
    }

    private int dfs(int[][] mat, int[][] dp, int i, int j, int m, int n) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int maxCells = 1;
        for (int col = 0; col < n; col++) {
            if ( mat[i][col] > mat[i][j]) {
                int cells = 1 + dfs(mat, dp, i, col, m, n);
                maxCells = Math.max(maxCells, cells);
            }
        }
        for (int row = 0; row < m; row++) {
            if (mat[row][j] > mat[i][j]) {
                int cells = 1 + dfs(mat, dp, row, j, m, n);
                maxCells = Math.max(maxCells, cells);
            }
        }

        return dp[i][j] = maxCells;


    }

    //Binary search
    public int maxIncreasingCells1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, List<int[]>> colRowValMap = new HashMap<>();
        Map<Integer, List<int[]>> rowColValMap = new HashMap<>();

        for (int r = 0; r < m; r++) {

            for (int c = 0; c < n; c++) {
                List<int[]> rowList =  colRowValMap.getOrDefault(c, new ArrayList<>());
                rowList.add(new int[] {mat[r][c], r});
                colRowValMap.put(c, rowList);
                List<int[]> colList =  rowColValMap.getOrDefault(r, new ArrayList<>());
                colList.add(new int[] {mat[r][c], c});
                rowColValMap.put(r, colList);
            }
        }

        for (int col : colRowValMap.keySet()) {
            Collections.sort(colRowValMap.get(col), (a, b) -> a[0] - b[0]);
        }

        for (int row : rowColValMap.keySet()) {
            Collections.sort(rowColValMap.get(row), (a, b) -> a[0] - b[0]);
        }
        int ans = 0;
        int[][] memo = new int[m][n];

        for (int r = 0; r < m; r++) {

            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, recur(mat, rowColValMap, colRowValMap, r, c, memo));
            }
        }
        return ans;
    }

    public int recur(int[][] mat, Map<Integer, List<int[]>> rowColValMap,
                     Map<Integer, List<int[]>> colRowValMap, int r, int c, int[][] memo) {

        if (memo[r][c] > 0) {
            return memo[r][c];
        }
        int ans = 1;

        List<int[]> cols = rowColValMap.get(r);
        int colIndex = binarySearchJustGreaterThan(cols, mat[r][c]);
        int colans = 0;

        if (colIndex != -1) {

            for (int nc = colIndex; nc < cols.size(); nc++) {
                int[] ncl = cols.get(nc);
                colans = Math.max(colans, recur(mat, rowColValMap, colRowValMap, r, ncl[1], memo));
            }
        }

        List<int[]> rows = colRowValMap.get(c);
        int rowIndex = binarySearchJustGreaterThan(rows, mat[r][c]);
        int rowans = 0;

        if (rowIndex != -1) {

            for (int nr = rowIndex; nr < rows.size(); nr++) {
                int[] nrs = rows.get(nr);
                rowans = Math.max(rowans, recur(mat, rowColValMap, colRowValMap, nrs[1], c, memo));
            }
        }
        return memo[r][c] = ans + Math.max(colans, rowans);
    }

    public int binarySearchJustGreaterThan(List<int[]> list, int target) {
        int ans = -1;
        int lo = 0;
        int hi = list.size() - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (list.get(pivot)[0] > target) {
                hi = pivot - 1;
                ans = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
}
