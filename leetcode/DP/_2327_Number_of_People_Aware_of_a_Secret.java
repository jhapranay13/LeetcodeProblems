package leetcode.DP;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * On day 1, one person discovers a secret.
 *
 * You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.
 *
 * Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, delay = 2, forget = 4
 * Output: 5
 * Explanation:
 * Day 1: Suppose the first person is named A. (1 person)
 * Day 2: A is the only person who knows the secret. (1 person)
 * Day 3: A shares the secret with a new person, B. (2 people)
 * Day 4: A shares the secret with a new person, C. (3 people)
 * Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
 * Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
 * Example 2:
 *
 * Input: n = 4, delay = 1, forget = 3
 * Output: 6
 * Explanation:
 * Day 1: The first person is named A. (1 person)
 * Day 2: A shares the secret with B. (2 people)
 * Day 3: A and B share the secret with 2 new people, C and D. (4 people)
 * Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)
 *
 *
 * Constraints:
 *
 * 2 <= n <= 1000
 * 1 <= delay < forget <= n
 *
 */

public class _2327_Number_of_People_Aware_of_a_Secret {
    //Bottom up approach
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);

        for (int currDay = n; currDay > 0; currDay--) {
            long ans = 0;

            if (currDay + forget > n) {
                ans++;
            }

            for (int nextDay = currDay + delay; nextDay < currDay + forget; nextDay++) {

                if (nextDay > n) {
                    continue;
                }
                ans = ans + memo[nextDay];
                ans %= mod;
            }
            memo[currDay] = ans % mod;
        }
        return (int)memo[1];
    }
    //=============================================================================================
    // Top Down
    private long mod = 1000000007;

    public int peopleAwareOfSecret1(int n, int delay, int forget) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return (int)recur(n, delay, forget, 1, memo);
    }

    private long recur(int n, int delay, int forget, int currDay, long[] memo) {

        if (currDay > n) {
            return 0;
        }

        if (memo[currDay] > -1L) {
            return memo[currDay];
        }
        long ans = 0;

        if (currDay + forget > n) {
            ans++;
        }

        for (int nextDay = currDay + delay; nextDay < currDay + forget; nextDay++) {
            ans = ans + recur(n, delay, forget, nextDay, memo);
            ans %= mod;
        }
        return memo[currDay] = ans % mod;
    }
    //=============================================================================================
    // TLE Queue
    public int peopleAwareOfSecret2(int n, int delay, int forget) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {1});
        int day = 1;

        while (!q.isEmpty() && day <= n) {
            int size = q.size();

            while (size-- > 0) {
                int[] curr = q.poll();

                if (curr[0] + forget == day) {
                    continue;
                }

                if (day >= curr[0] + delay) {
                    q.offer(new int[] {day});
                }
                q.offer(curr);
            }
            day++;
        }
        return q.size();
    }
}
