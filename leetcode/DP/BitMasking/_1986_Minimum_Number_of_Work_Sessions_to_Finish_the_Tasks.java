package leetcode.DP.BitMasking;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * Example 2:
 *
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * Example 3:
 *
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 *
 *
 * Constraints:
 *
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 *
 */

public class _1986_Minimum_Number_of_Work_Sessions_to_Finish_the_Tasks {
    // Top down Bit mask
    public int minSessions(int[] tasks, int sessionTime) {
        int[][] memo = new int[1 << tasks.length][sessionTime + 1];
        int ans = recur(tasks, sessionTime, 0, 0, memo);
        return ans;
    }

    private int recur(int[] tasks, int sessionTime, int v, int sum, int[][] memo) {
        // All bits are set till that length
        if (v == ((1 << tasks.length) - 1)) {

            if (sum == 0) {
                return 0;
            }
            //If sum is not zero then another session
            return 1;
        }

        if (memo[v][sum] > 0) {
            return memo[v][sum];
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < tasks.length; i++) {
            int mask = 1 << i;

            if ((v & mask) == 0) {

                if (sum + tasks[i] < sessionTime) {
                    int nextMask = v ^ mask;
                    int temp = recur(tasks, sessionTime, nextMask, sum + tasks[i], memo);
                    ans = Math.min(temp, ans);
                } else  if (sum + tasks[i] == sessionTime) {
                    int nextMask = v ^ mask;
                    int temp = 1 + recur(tasks, sessionTime, nextMask, 0, memo);
                    ans = Math.min(temp, ans);
                } else {
                    int temp = 1 + recur(tasks, sessionTime, v, 0, memo);
                    ans = Math.min(temp, ans);
                }
            }
        }
        return memo[v][sum] = ans;
    }

    //=============================================================================================
    // Top down Memo HashMap TLE
    public int minSessions1(int[] tasks, int sessionTime) {
        Map<String, Integer> memo = new HashMap<>();
        int ans = recur(tasks, sessionTime, 0, 0, memo);
        return ans;
    }

    private int recur(int[] tasks, int sessionTime, int v, int sum, Map<String, Integer> memo) {
        // All bits are set till that length
        if (v == ((1 << tasks.length) - 1)) {

            if (sum == 0) {
                return 0;
            }
            //If sum is not zero then another session
            return 1;
        }
        String key = v + "|" + sum;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < tasks.length; i++) {
            int mask = 1 << i;

            if ((v & mask) == 0) {

                if (sum + tasks[i] < sessionTime) {
                    int nextMask = v ^ mask;
                    int temp = recur(tasks, sessionTime, nextMask, sum + tasks[i], memo);
                    ans = Math.min(temp, ans);
                } else  if (sum + tasks[i] == sessionTime) {
                    int nextMask = v ^ mask;
                    int temp = 1 + recur(tasks, sessionTime, nextMask, 0, memo);
                    ans = Math.min(temp, ans);
                } else {
                    int temp = 1 + recur(tasks, sessionTime, v, 0, memo);
                    ans = Math.min(temp, ans);
                }
            }
        }
        memo.put(key, ans);
        return ans;
    }


    //=============================================================================================
    // Binary Search and back tracking
    public int minSessions2(int[] tasks, int sessionTime) {
        int lo = 0;
        int hi = tasks.length;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (check(tasks, sessionTime, new int[pivot], 0)) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return hi;
    }

    private boolean check(int[] tasks, int sessionTime, int[] sessions, int index) {

        if (index == tasks.length) {
            return true;
        }

        for (int i = 0 ; i < sessions.length; i++) {

            if (sessions[i] + tasks[index] <= sessionTime) {
                sessions[i] += tasks[index];

                if (check(tasks, sessionTime, sessions, index + 1)) {
                    return true;
                }
                sessions[i] -= tasks[index];
            }
            // If the value was not updated and sessio[i] == 0 that means value is
            // larger than session time so no result can be possible
            if (sessions[i] == 0) {
                break;
            }
        }
        return false;
    }
}
