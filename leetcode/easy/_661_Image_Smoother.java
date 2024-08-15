package leetcode.easy;

/**
 *
 *
 * An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).
 *
 *
 * Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: img = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[0,0,0],[0,0,0],[0,0,0]]
 * Explanation:
 * For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * Example 2:
 *
 *
 * Input: img = [[100,200,100],[200,50,200],[100,200,100]]
 * Output: [[137,141,137],[141,138,141],[137,141,137]]
 * Explanation:
 * For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 * For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 * For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 *
 *
 * Constraints:
 *
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 *
 *
 */

public class _661_Image_Smoother {
    public int[][] imageSmoother(int[][] img) {
        int[][] presum = new int[img.length][img[0].length];

        for (int r = 0; r < img.length; r++) {

            for (int c = 0; c < img[0].length; c++) {

                if (r == 0 && c == 0) {
                    presum[r][c] = img[r][c];
                } else if (r == 0) {
                    presum[r][c] = presum[r][c - 1] + img[r][c];
                } else if (c == 0) {
                    presum[r][c] = presum[r - 1][c] + img[r][c];
                } else {
                    presum[r][c] = presum[r][c - 1] + presum[r - 1][c] + img[r][c] -
                            presum[r - 1][c - 1];
                }
            }
        }

        for (int r = 0; r < img.length; r++) {
            int rstart = Math.max(r - 1, 0);
            int rend = Math.min(r + 1, img.length - 1);

            for (int c = 0; c < img[0].length; c++) {
                int cstart = Math.max(c - 1, 0);
                int cend = Math.min(c + 1, img[0].length - 1);
                int sum = getRangeSum(presum, rstart, rend, cstart, cend);
                int numOfPos = (rend - rstart + 1) * (cend - cstart + 1);
                img[r][c] = sum / numOfPos;
            }
        }
        return img;
    }

    private int getRangeSum(int[][] presum, int rstart, int rend, int cstart, int cend) {
        int sum = presum[rend][cend];

        if (rstart > 0) {
            sum -= presum[rstart - 1][cend];
        }

        if (cstart > 0) {
            sum -= presum[rend][cstart - 1];
        }

        if (rstart > 0 && cstart > 0) {
            sum += presum[rstart - 1][cstart - 1];
        }
        return sum;
    }
}
