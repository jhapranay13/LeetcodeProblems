package leetcode.Arrays;

/**
 *
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * Example 2:
 *
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * Example 3:
 *
 *
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 *
 */

import java.util.List;

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

public class _1428_Leftmost_Column_with_at_Least_a_One {

    interface BinaryMatrix {
      public int get(int row, int col);
      public List<Integer> dimensions();
  }
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int c = cols - 1;
        int r = 0;

        while (r < rows && c >= 0) {

            if (binaryMatrix.get(r, c) == 0) {
                r++;
            } else {
                c--;
            }
        }
        return c == cols - 1 ? -1 : c + 1;
    }

    //=============================================================================================
    //Binary Search
    public int leftMostColumnWithOne1(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int ans = cols;

        for (int r = 0; r < rows; r++) {
            int lo = 0;
            int hi = cols - 1;

            while (lo < hi) {
                int pivot = lo + (hi - lo) / 2;

                if (binaryMatrix.get(r, pivot) == 0) {
                    lo = pivot + 1;
                } else {
                    hi = pivot;
                }
            }

            if (binaryMatrix.get(r, hi) == 1) {
                ans = Math.min(ans, hi);
            }
        }
        return ans == cols ? -1 : ans;
    }
}
