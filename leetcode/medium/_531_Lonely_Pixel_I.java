package leetcode.medium;

/**
 *
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Example 2:
 *
 *
 * Input: picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 500
 * picture[i][j] is 'W' or 'B'.
 *
 */

public class _531_Lonely_Pixel_I {
    public int findLonelyPixel(char[][] picture) {
        int rows[] = new int[picture.length];
        int cols[] = new int[picture[0].length];

        for (int r = 0; r < picture.length; r++) {

            for (int c = 0; c < picture[0].length; c++) {

                if (picture[r][c] == 'B') {
                    rows[r]++;
                    cols[c]++;
                }
            }
        }
        int ans = 0;

        for (int r = 0; r < picture.length; r++) {

            for (int c = 0; c < picture[0].length; c++) {

                if (picture[r][c] == 'B' && rows[r] == 1 && cols[c] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
