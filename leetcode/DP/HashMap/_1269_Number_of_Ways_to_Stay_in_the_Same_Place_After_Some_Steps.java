package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).
 *
 * Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * Example 2:
 *
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 *
 * Input: steps = 4, arrLen = 2
 * Output: 8
 *
 *
 * Constraints:
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 *
 *
 */

public class _1269_Number_of_Ways_to_Stay_in_the_Same_Place_After_Some_Steps {
    public int numWays(int steps, int arrLen) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(arrLen, steps, 0, memo);
    }
    int mod = 1000000007;

    private int recur(int arrLen, int steps, int index, Map<String, Integer> memo) {

        if (steps == 0 && index == 0) {
            return 1;
        }

        if (steps == 0 || index < 0 || index == arrLen) {
            return 0;
        }
        String key = steps + "|" + index;

        if (memo.get(key) != null) {
            return memo.get(key);
        }
        int stay = recur(arrLen, steps - 1, index, memo);
        int moveLeft = recur(arrLen, steps - 1, index - 1, memo);
        int moveRight = recur(arrLen, steps - 1, index + 1, memo);
        int ans = ((stay + moveLeft) % mod + moveRight) % mod;
        memo.put(key, ans);
        return ans;
    }
}
