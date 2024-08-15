package leetcode.BFS;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * Given a positive integer n, you can apply one of the following operations:
 *
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 8
 * Output: 3
 * Explanation: 8 -> 4 -> 2 -> 1
 * Example 2:
 *
 * Input: n = 7
 * Output: 4
 * Explanation: 7 -> 8 -> 4 -> 2 -> 1
 * or 7 -> 6 -> 3 -> 2 -> 1
 * Example 3:
 *
 * Input: n = 4
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 *
 */

public class _397_Integer_Replacement {
    //DP Top Down
    public int integerReplacement(int n) {
        Map<Long, Integer> memo = new HashMap<>();
        return recur(n, memo);
    }

    private int recur(long n, Map<Long, Integer> memo) {

        if (n == 1) {
            return 0;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int ans = 0;

        if (n % 2 == 0) {
            ans = 1 + recur(n / 2, memo);
        } else {
            int addStep = recur(n + 1, memo);
            int subStep = recur(n - 1, memo);
            ans = 1 + Math.min(addStep, subStep);
        }
        memo.put(n, ans);
        return ans;
    }
    //=============================================================================================
    // BFS Shortest path approach
    public int integerReplacement1(int n) {
        Deque<Long> q = new LinkedList<>();
        q.offer((long)n);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                long node = q.poll();

                if (node == 1) {
                    return ans;
                }

                if (node % 2 != 0) {
                    q.offer(node + 1);

                    if (node - 1 > 0) {
                        q.offer(node - 1);
                    }
                } else {
                    q.offer(node / 2);
                }
            }
            ans++;
        }
        return ans;
    }
}
