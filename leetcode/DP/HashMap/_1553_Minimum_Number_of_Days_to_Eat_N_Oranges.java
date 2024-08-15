package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:
 *
 * Eat one orange.
 * If the number of remaining oranges n is divisible by 2 then you can eat n / 2 oranges.
 * If the number of remaining oranges n is divisible by 3 then you can eat 2 * (n / 3) oranges.
 * You can only choose one of the actions per day.
 *
 * Given the integer n, return the minimum number of days to eat n oranges.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: You have 10 oranges.
 * Day 1: Eat 1 orange,  10 - 1 = 9.
 * Day 2: Eat 6 oranges, 9 - 2*(9/3) = 9 - 6 = 3. (Since 9 is divisible by 3)
 * Day 3: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1.
 * Day 4: Eat the last orange  1 - 1  = 0.
 * You need at least 4 days to eat the 10 oranges.
 * Example 2:
 *
 * Input: n = 6
 * Output: 3
 * Explanation: You have 6 oranges.
 * Day 1: Eat 3 oranges, 6 - 6/2 = 6 - 3 = 3. (Since 6 is divisible by 2).
 * Day 2: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. (Since 3 is divisible by 3)
 * Day 3: Eat the last orange  1 - 1  = 0.
 * You need at least 3 days to eat the 6 oranges.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^9
 *
 */

public class _1553_Minimum_Number_of_Days_to_Eat_N_Oranges {
    public int minDays(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return recur(n, memo);
    }

    private int recur(int numberOfOranges, Map<Integer, Integer> memo) {

        if (numberOfOranges == 0) {
            return 0;
        }

        if (numberOfOranges == 1) {
            return 1;
        }

        if (memo.get(numberOfOranges) != null) {
            return memo.get(numberOfOranges);
        }
        int ans = Integer.MAX_VALUE;

        if (numberOfOranges % 3 == 0) {

            if (numberOfOranges % 2 == 0) {
                ans = 1 + Math.min(recur(numberOfOranges - (numberOfOranges / 2), memo),
                        recur(numberOfOranges - (2 * (numberOfOranges / 3)), memo));
            } else {
                ans = 1 + Math.min(recur(numberOfOranges - 1, memo),
                        recur(numberOfOranges - (2 * (numberOfOranges / 3)), memo));
            }
        } else if (numberOfOranges % 2 == 0) {
            ans = 1 + Math.min(recur(numberOfOranges - (numberOfOranges / 2), memo),
                    recur(numberOfOranges - 1, memo));
        } else {
            ans = 1 + recur(numberOfOranges - 1, memo);
        }
        memo.put(numberOfOranges, ans);
        return ans;
    }
}
