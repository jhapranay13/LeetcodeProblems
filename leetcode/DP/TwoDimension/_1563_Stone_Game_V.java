package leetcode.DP.TwoDimension;

/**
 *
 * There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 *
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 *
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 *
 * Return the maximum score that Alice can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
 * Example 2:
 *
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 * Example 3:
 *
 * Input: stoneValue = [4]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 */

public class _1563_Stone_Game_V {
    public int stoneGameV(int[] stoneValue) {
        int[] presum = new int[stoneValue.length];
        presum[0] = stoneValue[0];

        for (int i = 1; i< stoneValue.length; i++) {
            presum[i] = stoneValue[i] + presum[i - 1];
        }
        Integer[][] memo = new Integer[stoneValue.length][stoneValue.length];
        return recur(presum, stoneValue, 0, stoneValue.length - 1, memo);
    }

    private int recur(int[] presum, int[] stoneValue,int start, int end, Integer[][] memo) {

        /*if (start + 1 == end) {
            return Math.min(stoneValue[start], stoneValue[end]);
        }*/

        if (start >= end) {
            return 0;
        }

        if (memo[start][end] != null) {
            return memo[start][end];
        }
        int ans = 0;

        for (int i = start; i <= end; i++) {
            int left = start == 0 ? presum[i] : presum[i] - presum[start -  1];
            int right = presum[end] - presum[i];

            if (left < right) {
                ans = Math.max(ans, left + recur(presum, stoneValue,start, i, memo));
            } else if(left > right) {
                ans = Math.max(ans, right + recur(presum, stoneValue,i + 1, end, memo));
            } else {
                ans = Math.max(ans, Math.max(right + recur(presum, stoneValue,i + 1, end, memo),
                        left + recur(presum, stoneValue,start, i, memo)));
            }
        }
        return memo[start][end] = ans;
    }
}
