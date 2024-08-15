package leetcode.medium;

/**
 *
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
 *
 * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
 *
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 *
 *
 *
 * Example 1:
 *
 *       ________________________________________>
 *      |    __________________________>         |
 *      |   |   _______________________V_________V____
 *      |   |  |   1 -> |    2    |    3    |    4   |
 *      |   |  |________|____V____|____V____|________|
 *      |    < ______________|          |
 *      |______________________________V
 *
 *
 * Input: rows = 1, cols = 4, rStart = 0, cStart = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 * Example 2:
 *
 *
 * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 100
 * 0 <= rStart < rows
 * 0 <= cStart < cols
 *
 */

public class _885_Spiral_Matrix_III {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int length = 0;
        int ans[][] = new int[rows * cols][2];
        int index = 0;

        while (index < rows * cols ) {
            int currRow = rStart;
            int currCol = cStart;
            length++; // length keeps on increasing on this phase
            int rowEnd = currRow + length;
            int colEnd = currCol + length;

            while (currCol < colEnd) {

                if (currCol >= 0 && currRow >= 0 && currCol < cols && currRow < rows) {
                    ans[index++] = new int[]{currRow, currCol};
                }
                currCol++;
            }

            while (currRow < rowEnd) {
                if (currCol >= 0 && currRow >= 0 && currCol < cols && currRow < rows) {
                    ans[index++] = new int[]{currRow, currCol};
                }
                currRow++;
            }
            length++;//length keeps on increasing in this phase
            int colStart = currCol - length;

            while (currCol > colStart) {

                if (currCol >= 0 && currRow >= 0 && currCol < cols && currRow < rows) {
                    ans[index++] = new int[]{currRow, currCol};
                }
                currCol--;
            }
            int rowStart = currRow - length;

            while (currRow > rowStart) {
                if (currCol >= 0 && currRow >= 0 && currCol < cols && currRow < rows) {
                    ans[index++] = new int[]{currRow, currCol};
                }
                currRow--;
            }
            rStart = currRow;
            cStart = currCol;
        }
        return ans;
    }
}
