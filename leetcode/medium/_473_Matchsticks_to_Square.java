package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 *
 *
 * Example 1:
 *                  ^--------->^
 *                  |          |
 *                  ^          |
 *                  |_________>|
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 *
 * Constraints:
 *
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 *
 */

public class _473_Matchsticks_to_Square {
    // Similar to 698. Partition to K Equal Sum Subsets
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;

        for (int matchstick : matchsticks) {
            sum += matchstick;
        }

        if (sum % 4 != 0) {
            return false;
        }
        int target = sum / 4;
        int[] v = new int[matchsticks.length];
        Map<String, Boolean> memo = new HashMap<>();
        return recur(matchsticks, v, target, 4, 0, memo);
    }

    private boolean recur(int[] matchsticks, int[] v, int target, int k, int currSum,
                          Map<String, Boolean> memo) {

        if (k == 0) {
            return true;
        }
        String key = Arrays.toString(v) + "|" + k + "|" + currSum;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        boolean ans = false;

        for (int i = 0; i < matchsticks.length; i++) {

            if (v[i] == 1) {
                continue;
            }
            v[i] = 1;
            int sum = currSum + matchsticks[i];

            if (sum < target) {
                ans = recur(matchsticks, v, target, k, sum, memo);
            } else if (sum == target) {
                ans = recur(matchsticks, v, target, k - 1, 0, memo);
            }

            if (ans) {
                break;
            }
            v[i] = 0;
        }
        memo.put(key, ans);
        return ans;
    }
}
