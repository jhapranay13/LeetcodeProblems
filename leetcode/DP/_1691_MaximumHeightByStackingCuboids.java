package leetcode.DP;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi, heighti] (0-indexed). Choose a subset of cuboids and place them on each other.
 *
 * You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <= heightj. You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
 *
 * Return the maximum height of the stacked cuboids.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * Output: 190
 * Explanation:
 * Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
 * Cuboid 0 is placed next with the 45x20 side facing down with height 50.
 * Cuboid 2 is placed next with the 23x12 side facing down with height 45.
 * The total height is 95 + 50 + 45 = 190.
 *
 *
 * Example 2:
 *
 * Input: cuboids = [[38,25,45],[76,35,3]]
 * Output: 76
 * Explanation:
 * You can't place any of the cuboids on the other.
 * We choose cuboid 1 and rotate it so that the 35x3 side is facing down and its height is 76.
 *
 *
 * Example 3:
 *
 * Input: cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * Output: 102
 * Explanation:
 * After rearranging the cuboids, you can see that all cuboids have the same dimension.
 * You can place the 11x7 side down on all cuboids so their heights are 17.
 * The maximum height of stacked cuboids is 6 * 17 = 102.
 *
 *
 * Constraints:
 *
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= widthi, lengthi, heighti <= 100
 *
 */

public class _1691_MaximumHeightByStackingCuboids {
    //=============================================================================================
    //Top Down Approach
    public int maxHeight(int[][] cubeoids) {
        //Since it is a cubeoid it does not matter what is length or breadth of it. We can maximize on Height
        //The problem is then like Envolopes or Russian doll problem
        for (int[] cube : cubeoids) {
            Arrays.sort(cube);
        }
        Arrays.sort(cubeoids, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {

                if (x[2] == y[2]) {

                    if (x[1] == y[1]) {
                        return y[0] - x[0];
                    }
                    return y[1] - x[1];
                }
                return y[2] - x[2];
            }
        });
        int memo[] = new int[cubeoids.length];
        int ans = 0;

        for (int i = 0; i < cubeoids.length; i++){
            int tempAns = recur(cubeoids, i, memo);
            ans = Math.max(ans, tempAns);
        }
        return ans;
    }

    private int recur(int[][] cubeoids, int index, int[] memo) {

        if (index == cubeoids.length) {
            return 0;
        }

        if (memo[index] > 0) {
            return memo[index];
        }
        int[] curr = cubeoids[index];
        int ans = curr[2];

        for (int i = index + 1; i < cubeoids.length; i++) {

            if (cubeoids[i][2] <= curr[2] && cubeoids[i][1] <= curr[1] && cubeoids[i][0] <= curr[0]) {
                ans = Math.max(ans, curr[2] + recur(cubeoids, i, memo));
            }
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    //Bottom up approach
    public int maxHeight2(int[][] cubeoids) {
        //Since it is a cubeoid it does not matter what is length or breadth of it. We can maximize on Height
        //The problem is then like Envolopes or Russian doll problem
        for (int[] cube : cubeoids) {
            Arrays.sort(cube);
        }
        Arrays.sort(cubeoids, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {

                if (x[2] == y[2]) {

                    if (x[1] == y[1]) {
                        return y[0] - x[0];
                    }
                    return y[1] - x[1];
                }
                return y[2] - x[2];
            }
        });
        int memo[] = new int[cubeoids.length + 1];
        int max = 0;

        for (int index = cubeoids.length - 1; index >= 0; index--){

            int[] curr = cubeoids[index];
            int ans = curr[2];

            for (int i = index + 1; i < cubeoids.length; i++) {

                if (cubeoids[i][2] <= curr[2] && cubeoids[i][1] <= curr[1] && cubeoids[i][0] <= curr[0]) {
                    ans = Math.max(ans, curr[2] + memo[i]);
                }
            }
            memo[index] = ans;
            max = Math.max(max, memo[index]);
        }
        return max;
    }
}
