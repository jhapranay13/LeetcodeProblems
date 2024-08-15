package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 * In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
 *
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 *
 * Given the integer array position and the integer m. Return the required force.
 *
 *
 *
 * Example 1:
 *
 *
 *      B         B        B
 *      1   2  3  4  5  6  7
 *
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 * Example 2:
 *
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 *
 *
 * Constraints:
 *
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * All integers in position are distinct.
 * 2 <= m <= position.length
 *
 */

public class _1552_Magnetic_Force_Between_Two_Balls {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int lo = 1;
        int hi = position[position.length-1] - position[0];
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isValid(position, m, pivot)) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private boolean isValid(int[] position, int numBalls, int minDistance) {
        int prev = position[0];
        numBalls--;

        for (int i = 1; i < position.length; i++) {

            if (position[i] - prev >= minDistance) {
                numBalls--;

                if (numBalls == 0) {
                    return true;
                }
                prev = position[i];
            }
        }
        return numBalls == 0;
    }
}
