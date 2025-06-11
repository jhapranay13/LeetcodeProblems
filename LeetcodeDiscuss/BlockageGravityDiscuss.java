package LeetcodeDiscuss;


/**
 *
 * Given a matrix
 * 2 - means a blockage we can't move downwards
 * 0 - means free space
 * 1 - connected nodes
 *
 * Due to gravitational pull all the 1's are dragged downwards until there is no blockage ( 2's )
 *
 * input:
 *
 * 1,0,0,0,1
 * 1,1,1,1,1
 * 0,0,1,1,1
 * 2,2,0,0,0
 *
 * Output:
 *
 * 0,0,0,0,0
 * 1,0,0,0,1
 * 1,1,1,1,1
 * 2,2,1,1,1
 *
 * what is the approach to solve this problem ?
 *
 *
 * 0
 *
 */
public class BlockageGravityDiscuss {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {2, 2, 0, 0, 0}
        };
        printMatrix(matrix);
        applyGravity(matrix);
        printMatrix(matrix);

        int[][] matrix3 = {
                {1, 1, 1},
                {2, 2, 2},
                {1, 1, 1}
        };
        System.out.println("Original:");
        printMatrix(matrix3);
        applyGravity(matrix3);
        System.out.println("After Gravity:");
        printMatrix(matrix3);
        // Expected output: (should remain unchanged as no empty space below)
        // 1 1 1
        // 2 2 2
        // 1 1 1
        System.out.println("--- Test Case 4: Single Column Fall ---");
        int[][] matrix4 = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Original:");
        printMatrix(matrix4);
        applyGravity(matrix4);
        System.out.println("After Gravity:");
        printMatrix(matrix4);
        // Expected output:
        // 0 0 0
        // 0 0 0
        // 1 0 0
        System.out.println("--- Test Case 5: Multiple Columns, Complex Fall ---");
        int[][] matrix5 = {
                {1, 0, 2},
                {0, 1, 0},
                {1, 0, 2}
        };
        System.out.println("Original:");
        printMatrix(matrix5);
        applyGravity(matrix5);
        System.out.println("After Gravity:");
        printMatrix(matrix5);
        // Expected output:
        // 0 0 0
        // 1 0 2
        // 1 1 2

        System.out.println("--- Test Case 6: Jagged or Empty Matrix (Edge Cases) ---");
        // Note: The provided applyGravity implementation assumes a rectangular matrix.
        // If jagged matrices are allowed, the implementation would need to be more robust.
        // For standard matrix problems, rectangular is usually assumed.

        System.out.println("Empty Matrix:");
        int[][] matrix6 = {};
        System.out.println("Original (empty):");
        printMatrix(matrix6); // Will print nothing
        applyGravity(matrix6);
        System.out.println("After Gravity (empty):");
        printMatrix(matrix6); // Will print nothing

        System.out.println("Matrix with empty rows (but valid structure):");
        int[][] matrix7 = {{}, {}, {}}; // This represents 3 rows, 0 columns.
        System.out.println("Original (empty columns):");
        printMatrix(matrix7); // Will print empty lines
        applyGravity(matrix7);
        System.out.println("After Gravity (empty columns):");
        printMatrix(matrix7); // Will print empty lines

        System.out.println("--- Test Case 8: Blocks hitting other blocks ---");
        int[][] matrix8 = {
                {0, 0, 1},
                {1, 0, 0},
                {0, 2, 0}
        };
        System.out.println("Original:");
        printMatrix(matrix8);
        applyGravity(matrix8);
        System.out.println("After Gravity:");
        printMatrix(matrix8);
        // Expected output:
        // 0 0 0
        // 0 0 1
        // 1 2 0

        System.out.println("--- Test Case 9: All blocks on top, empty space below ---");
        int[][] matrix9 = {
                {1, 1, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        System.out.println("Original:");
        printMatrix(matrix9);
        applyGravity(matrix9);
        System.out.println("After Gravity:");
        printMatrix(matrix9);
        // Expected output:
        // 0 0 0
        // 1 1 1
        // 1 1 1
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    private static void applyGravity(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return; // Handle empty matrix case
        }
        int r = matrix.length -1;
        int[] anchor = new int[matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++) {
            anchor[i] = r;
        }

        while (r >= 0) {

            for (int c = 0; c < matrix[0].length; c++) {

                if (matrix[r][c] == 2) {
                    anchor[c] = r - 1;
                } else if (matrix[r][c] == 1){
                    int row = anchor[c]--;
                    matrix[row][c] = matrix[r][c];

                    if (row != r) {
                        matrix[r][c] = 0;
                    }
                }
            }
            r--;
        }
    }
}
