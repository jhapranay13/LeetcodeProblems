package leetcode.medium;

import java.util.Arrays;

/**
 * You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
 *
 * You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
 *
 * Return the maximum number of white tiles that can be covered by the carpet.
 *
 *
 *
 * Example 1:
 *
 *      _______________      ___________________________     _______________    _________
 *     |  |  |  |  |  |     |  |  |  |  |  |  |  |  |  |    |  |  |  |  |  |   |  |  |  |
 *     |  |  |  |  |  |     |  |  |  |  |  |  |  |  |  |    |  |  |  |  |  |   |  |  |  |
 *     |  |  |  |  |  |     |  |  |  |  |  |  |  |  |  |    |  |  |  |  |  |   |  |  |  |
 *     |  |  |  |  |  |     |  |  |  |  |  |  |  |  |  |    |  |  |  |  |  |   |  |  |  |
 *     ----------------     ----------------------------    ----------------   ----------
 *      1            5       10 11 12                 18     20          25     30     32
 * Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * Output: 9
 * Explanation: Place the carpet starting on tile 10.
 * It covers 9 white tiles, so we return 9.
 * Note that there may be other places where the carpet covers 9 white tiles.
 * It can be shown that the carpet cannot cover more than 9 white tiles.
 * Example 2:
 *
 *         ___      _______
 *  *     |  |      |  |  |
 *  *     |  |      |  |  |
 *  *     |  |      |  |  |
 *  *     |  |      |  |  |
 *  *     ----      -------
 *  *      1         10 11
 *
 *
 * Input: tiles = [[10,11],[1,1]], carpetLen = 2
 * Output: 2
 * Explanation: Place the carpet starting on tile 10.
 * It covers 2 white tiles, so we return 2.
 *
 *
 * Constraints:
 *
 * 1 <= tiles.length <= 5 * 10^4
 * tiles[i].length == 2
 * 1 <= li <= ri <= 10^9
 * 1 <= carpetLen <= 10^9
 * The tiles are non-overlapping.
 *
 */

public class _2271_Maximum_White_Tiles_Covered_by_a_Carpet {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int[] presum = new int[tiles.length]; // Will have number of tiles

        for (int i = 0; i < tiles.length; i++) {
            int numberOfTiles = tiles[i][1] - tiles[i][0] + 1;

            if (i == 0) {
                presum[i] = numberOfTiles;
            } else {
                presum[i] = presum[i - 1] + numberOfTiles;
            }
        }
        int ans = 0;

        for (int i = 0; i < tiles.length; i++) {
            int nextPoint = tiles[i][0] + carpetLen - 1;
            int index = binarySearchLessThanEqualTo(tiles, i, nextPoint);
            int totalTiles = i == 0 ? presum[index] : presum[index] - presum[i - 1];

            if (nextPoint < tiles[index][1]) {
                totalTiles -= (tiles[index][1] - nextPoint);
            }
            ans = Math.max(ans, totalTiles);
        }
        return ans;
    }

    private int binarySearchLessThanEqualTo(int[][] tiles, int index, int nextPoint) {
        int lo = index;
        int hi = tiles.length - 1;
        int ans = lo;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (tiles[pivot][0] <= nextPoint) {
                lo = pivot + 1;
                ans = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

}
