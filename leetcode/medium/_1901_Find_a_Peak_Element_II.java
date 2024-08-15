package leetcode.medium;

/**
 *
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
 *
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 *
 *
 * Example 1:
 *                -1  -1  -1  -1
 *                -1   1   4  -1
 *                -1   3   2  -1
 *                -1  -1  -1  -1
 *
 *
 * Input: mat = [[1,4],[3,2]]
 * Output: [0,1]
 * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
 * Example 2:
 *                 -1  -1  -1  -1  -1
 *                 -1  10  10  15  -1
 *                 -1  21  30  14  -1
 *                 -1   7  10  32  -1
 *                 -1  -1  -1  -1  -1
 *
 *
 * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * Output: [1,1]
 * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 10^5
 * No two adjacent cells are equal.
 *
 */

public class _1901_Find_a_Peak_Element_II {
    public int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int lo = 0;
        int hi = cols - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int maxRow = 0;

            for (int r = 0; r < rows; r++) {

                if (mat[r][pivot] > mat[maxRow][pivot]) {
                    maxRow = r;
                }
            }

            if ((pivot == 0 || mat[maxRow][pivot] > mat[maxRow][pivot - 1]) &&
                    (pivot == cols - 1 || mat[maxRow][pivot] > mat[maxRow][pivot + 1])) {
                return new int[] {maxRow, pivot};
            }

            if (pivot > 0 && mat[maxRow][pivot] < mat[maxRow][pivot - 1]) {
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return new int[] {-1, -1};
    }
}
