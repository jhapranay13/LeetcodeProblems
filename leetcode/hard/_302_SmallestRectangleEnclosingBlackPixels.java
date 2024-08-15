package leetcode.hard;

/**
 *
 * You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
 *
 * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
 *
 * Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * You must write an algorithm with less than O(mn) runtime complexity
 *
 *
 *
 * Example 1:
 *                  ["0","0","1","0"],
 *                  ["0","1","1","0"],
 *                  ["0","1","0","0"]
 *
 * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 * Output: 6
 * Example 2:
 *
 * Input: image = [["1"]], x = 0, y = 0
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 100
 * image[i][j] is either '0' or '1'.
 * 1 <= x < m
 * 1 <= y < n
 * image[x][y] == '1'.
 * The black pixels in the image only form one component.
 *
 */

public class _302_SmallestRectangleEnclosingBlackPixels {

    //=============================================================================================
    //DFS Approach
    public int minArea1(char[][] image, int x, int y) {

        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }
        minr = maxr = x;
        minc = maxc = y;
        boolean[][] v = new boolean[image.length][image[0].length];
        recur(image, x, y, v);
        return (maxr - minr) * (maxc - minc);
    }
    private int minr;
    private int maxr;
    private int minc;
    private int maxc;

    private void recur(char[][] image, int r, int c, boolean[][] v) {

        if (r >= image.length || c >= image[0].length || r < 0 || c < 0 || image[r][c] == '0' ||
                v[r][c]) {
            return;
        }
        v[r][c] = true;
        minr = Math.min(minr, r);
        minc = Math.min(minc, c);
        maxr = Math.max(maxr, r + 1);
        maxc = Math.max(maxc, c + 1);
        recur(image, r - 1, c, v);
        recur(image, r + 1, c, v);
        recur(image, r, c - 1, v);
        recur(image, r, c + 1, v);
    }

    //=============================================================================================
    //Binary Search Approach
    public int minArea(char[][] image, int x, int y) {
        int rows = image.length, cols = image[0].length;
        int lo = getLow(image, 0, x);
        int hi = getHi(image, x, rows - 1);
        int left = getLeft(image, 0, y);
        int right = getRight(image, y, cols - 1);
        return (hi - lo + 1) * (right - left + 1);
    }

    private int getLow(char[][] image, int lo, int hi) {
        int pos = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (checkRow(image, mid)) {
                pos = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return pos;
    }

    private int getHi(char[][] image, int lo, int hi) {
        int pos = lo;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (checkRow(image, mid)) {
                pos = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return pos;
    }

    private int getLeft(char[][] image, int left, int right) {
        int pos = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (checkCol(image, mid)) {
                pos = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private int getRight(char[][] image, int left, int right) {
        int pos = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (checkCol(image, mid)) {
                pos = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return pos;
    }

    private boolean checkRow(char[][] image, int r) {
        for (char pixel : image[r]) {
            if (pixel == '1') {
                return true;
            }
        }
        return false;
    }

    private boolean checkCol(char[][] image, int c) {
        for (int r = 0; r < image.length; ++r) {
            if (image[r][c] == '1') {
                return true;
            }
        }
        return false;
    }
}
