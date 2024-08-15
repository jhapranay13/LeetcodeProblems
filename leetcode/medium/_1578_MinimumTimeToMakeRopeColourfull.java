package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

public class _1578_MinimumTimeToMakeRopeColourfull {

    public int minCost(String colors, int[] neededTime) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < neededTime.length; i++) {

            if (!stack.isEmpty()) {
                int prevIndex = stack.peek();

                if (colors.charAt(i) == colors.charAt(prevIndex)) {

                    if (neededTime[i] > neededTime[prevIndex]) {
                        ans += neededTime[prevIndex];
                        stack.pop();
                    } else {
                        ans += neededTime[i];
                        continue;
                    }
                }
            }
            stack.push(i);
        }
        return ans;
    }
    //=============================================================================================
    //Top Down TLE
    public int minCost1(String colors, int[] neededTime) {
        int[][] memo = new int[neededTime.length + 1][neededTime.length + 1];
        return recur(colors, neededTime, 0, -1, memo);
    }

    private int recur(String colors, int[] neededTime, int index, int prevIndex, int[][] memo) {

        if (index == neededTime.length) {
            return 0;
        }

        if (memo[index][prevIndex + 1] > 0) {
            return memo[index][prevIndex + 1];
        }
        int ans = Integer.MAX_VALUE / 10;
        int nextIndex = index + 1;

        if (prevIndex != -1) {

            if (colors.charAt(prevIndex) == colors.charAt(index)) {
                ans = Math.min(ans,
                        neededTime[index] + recur(colors, neededTime, index + 1, prevIndex, memo));
                ans = Math.min(ans,
                        neededTime[prevIndex] + recur(colors, neededTime, index + 1, index, memo));
            } else {
                ans = Math.min(ans, recur(colors, neededTime, index + 1, index, memo));
            }
        } else {
            ans = Math.min(ans, recur(colors, neededTime, index + 1, index, memo));
        }
        return memo[index][prevIndex + 1] = ans;
    }
    //=============================================================================================
    //Bottom up TLE
    public int minCost2(String colors, int[] neededTime) {
        int[][] memo = new int[neededTime.length + 1][neededTime.length + 2];

        for (int prevIndex = neededTime.length - 1; prevIndex >= 0; prevIndex--) {

            for (int index = neededTime.length - 1; index >= 1; index--) {
                int ans = Integer.MAX_VALUE / 10;
                int nextIndex = index + 1;

                if (colors.charAt(prevIndex) == colors.charAt(index)) {
                    ans = Math.min(ans,
                            neededTime[index] + memo[index + 1][prevIndex]);
                    ans = Math.min(ans,
                            neededTime[prevIndex] + memo[index + 1][index]);
                } else {
                    ans = Math.min(ans,  memo[index + 1][index]);
                }
                memo[index][prevIndex] = ans;
            }
        }
        return memo[1][0];
    }
}
