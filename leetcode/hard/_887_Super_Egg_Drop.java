package leetcode.hard;


/**
 *
 * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
 *
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 1, n = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1. If it breaks, we know that f = 0.
 * Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
 * If it does not break, then we know f = 2.
 * Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
 * Example 2:
 *
 * Input: k = 2, n = 6
 * Output: 3
 * Example 3:
 *
 * Input: k = 3, n = 14
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= 100
 * 1 <= n <= 104
 *
 */

public class _887_Super_Egg_Drop {
    public int superEggDrop(int k, int n) {
        Integer[][] memo = new Integer[k + 1][n + 1];
        return recur(k, n, memo);
    }

    private int recur(int eggs, int floor, Integer[][] memo) {

        if (floor == 0 || floor == 1) {
            return floor;
        }
        /// if eggs == 1 we have to check all the remaining floors
        if (eggs == 1) {
            return floor;
        }

        if (memo[eggs][floor] != null) {
            return memo[eggs][floor];
        }
        int lo = 1;
        int hi = floor;
        int ans = Integer.MAX_VALUE;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            //checking if egg breaks then check lower floors or go down
            int eggBreak = recur(eggs - 1, pivot - 1, memo);
            // if eggs does not break go up
            int eggDoesNotBreak = recur(eggs, floor - pivot, memo);
            int temp = 1 + Math.max(eggBreak, eggDoesNotBreak);
            ans = Math.min(ans, temp);

            if (eggBreak < eggDoesNotBreak) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return memo[eggs][floor] = ans;
    }
}
