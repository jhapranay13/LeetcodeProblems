package leetcode.DP;

import java.util.Arrays;

/**
 *
 *
 * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 *
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
 *
 * Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
 *
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 *
 *
 *
 * Example 1:
 *
 * Input: satisfaction = [-1,-8,0,5,-9]
 * Output: 14
 * Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
 * Each dish is prepared in one unit of time.
 * Example 2:
 *
 * Input: satisfaction = [4,3,2]
 * Output: 20
 * Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
 * Example 3:
 *
 * Input: satisfaction = [-1,-4,-5]
 * Output: 0
 * Explanation: People do not like the dishes. No dish is prepared.
 *
 *
 * Constraints:
 *
 * n == satisfaction.length
 * 1 <= n <= 500
 * -1000 <= satisfaction[i] <= 1000
 *
 */

public class _1402_Reducing_Dishes {
    // Top down
    public int maxSatisfaction(int[] satisfaction) {
        //Greedy Approach if we discard minimum still we might get the max
        // answer coz of  time[i] * satisfaction[i]
        Arrays.sort(satisfaction);
        int[][] memo = new int[satisfaction.length][satisfaction.length + 1];

        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return recur(satisfaction, 0, 1, memo);
    }

    private int recur(int[] satisfaction, int index, int time, int[][] memo) {

        if (index == satisfaction.length) {
            return 0;
        }

        if (memo[index][time] != -1) {
            return memo[index][time];
        }

        int include = satisfaction[index] * time + recur(satisfaction, index + 1, time + 1, memo);
        int exclude = recur(satisfaction, index + 1, time, memo);
        return memo[index][time] = Math.max(include, exclude);
    }
    //=============================================================================================
    //Bottom up approach
    public int maxSatisfaction1(int[] satisfaction) {
        //Greedy Approach if we discard minimum still we might get the max
        // answer coz of  time[i] * satisfaction[i]
        Arrays.sort(satisfaction);
        int[][] memo = new int[satisfaction.length + 1][satisfaction.length + 2];

        for (int index = satisfaction.length - 1; index >= 0; index--) {

            for (int time = satisfaction.length; time >= 1; time--) {
                int include = satisfaction[index] * time + memo[index + 1][time + 1];
                int exclude = memo[index + 1][time];
                memo[index][time] = Math.max(include, exclude);
            }
        }
        return memo[0][1];
    }
}
