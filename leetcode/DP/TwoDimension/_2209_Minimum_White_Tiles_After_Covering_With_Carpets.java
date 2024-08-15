package leetcode.DP.TwoDimension;

/**
 *
 *
 * You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:
 *
 * floor[i] = '0' denotes that the ith tile of the floor is colored black.
 * On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
 * You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles. Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.
 *
 * Return the minimum number of white tiles still visible.
 *
 *
 *
 * Example 1:
 *                          ___ ___
 *                      1 0 1 1 0 1 0 1"
 *
 * Input: floor = "10110101", numCarpets = 2, carpetLen = 2
 * Output: 2
 * Explanation:
 * The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
 * No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
 * Example 2:
 *                  _____
 *                       ____
 *                  1 1 1 1 1
 *
 * Input: floor = "11111", numCarpets = 2, carpetLen = 3
 * Output: 0
 * Explanation:
 * The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
 * Note that the carpets are able to overlap one another.
 *
 *
 * Constraints:
 *
 * 1 <= carpetLen <= floor.length <= 1000
 * floor[i] is either '0' or '1'.
 * 1 <= numCarpets <= 1000
 *
 *
 */

public class _2209_Minimum_White_Tiles_After_Covering_With_Carpets {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int[] presum = new int[floor.length()];
        presum[0] = floor.charAt(0) == '1' ? 1 : 0;

        for (int i = 1; i < floor.length(); i++) {
            presum[i] = presum[i - 1] + (floor.charAt(i) == '1' ? 1 : 0);
        }
        int total = presum[floor.length() - 1];
        int[][] memo = new int[numCarpets + 1][ floor.length()];
        return total - recur(presum, carpetLen, numCarpets, 0, memo);
    }

    private int recur(int[] presum, int carpetLen, int numCarpets, int index, int[][] memo) {

        if (index >= presum.length || numCarpets == 0) {
            return 0;
        }

        if (memo[numCarpets][index] > 0) {
            return memo[numCarpets][index];
        }
        int end = ((index + carpetLen - 1) >= presum.length ? presum.length - 1 :
                (index + carpetLen - 1));
        int numOfOnes = 0;

        if (index == 0) {
            numOfOnes = presum[end];
        } else {
            numOfOnes = presum[end] - presum[index - 1];
        }
        int include = numOfOnes + recur(presum, carpetLen, numCarpets - 1, end + 1, memo);
        int exclude = recur(presum, carpetLen, numCarpets, index + 1, memo);
        return memo[numCarpets][index] = Math.max(include, exclude);
    }
}
