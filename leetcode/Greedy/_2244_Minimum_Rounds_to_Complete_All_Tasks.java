package leetcode.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2.
 * - In the second round, you complete 2 tasks of difficulty level 3.
 * - In the third round, you complete 3 tasks of difficulty level 4.
 * - In the fourth round, you complete 2 tasks of difficulty level 4.
 * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
 * Example 2:
 *
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 *
 */

public class _2244_Minimum_Rounds_to_Complete_All_Tasks {
    public int minimumRounds(int[] tasks) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int task : tasks) {
            count.put(task, count.getOrDefault(task, 0) + 1);
        }

        for (int key : count.keySet()) {
            int cnt = count.get(key);

            if (cnt == 1) {
                return -1;
            }

            if (cnt > 1) {
                int remainder = cnt % 3;

                if (remainder == 0) {
                    ans += cnt / 3;
                } else {
                    ans += cnt / 3 + 1;// Since then we will divide it by 2 which will add extra round
                }
            }
        }
        return ans;
    }
}
