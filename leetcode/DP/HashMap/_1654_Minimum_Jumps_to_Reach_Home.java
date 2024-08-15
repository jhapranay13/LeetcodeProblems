package leetcode.DP.HashMap;

import java.util.*;

/**
 *
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 * Example 2:
 *
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 * Example 3:
 *
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 *
 *
 * Constraints:
 *
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * All the elements in forbidden are distinct.
 * Position x is not forbidden.
 *
 */

public class _1654_Minimum_Jumps_to_Reach_Home {
    // BFS
    int limit = 6000;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        Set<Integer> v = new HashSet<>();

        for (int forbid : forbidden) {
            v.add(forbid);
        }

        if (v.contains(x)) {
            return -1;
        }
        v.add(0);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();

                if (data[0] == x) {
                    return level;
                }

                if (data[1] != 1 && data[0] - b > 0 && !v.contains(data[0] - b)) {
                    v.add(data[0] - b);
                    q.offer(new int[]{data[0] - b, 1});
                }


                if (data[0] + a < limit && !v.contains(data[0] + a)) {
                    v.add(data[0] + a);
                    q.offer(new int[]{data[0] + a, 0});
                }
            }
            level++;
        }
        return -1;
    }
    //=============================================================================================
    // Top down Memo

    public int minimumJumps1(int[] forbidden, int a, int b, int x) {
        Set<String> v = new HashSet<>();
        Map<String, Integer> memo = new HashMap<>();

        for (int forbid : forbidden) {
            // Forbidding approach from forword or back
            v.add(forbid + "|" + 0);
            v.add(forbid + "|" + 1);
        }
        int ans = recur(v, a, b, x, 0, 0, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int recur(Set<String> v, int a, int b, int x, int backword, int pos, Map<String, Integer> memo) {

        if (pos == x) {
            return 0;
        }
        String key = pos + "|" + backword;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (pos < 0 || pos >= limit || v.contains(key)) {
            return Integer.MAX_VALUE;
        }


        v.add(key);
        int includeBack = Integer.MAX_VALUE;
        int includeForward = Integer.MAX_VALUE;
        includeForward = recur(v, a, b, x, 0, pos + a, memo);
        includeForward = includeForward == Integer.MAX_VALUE ? includeForward : includeForward + 1;

        if (backword == 0 && pos - b >= 0) {
            includeBack = recur(v, a, b, x, 1, pos - b, memo);
            includeBack = includeBack == Integer.MAX_VALUE ? includeBack : includeBack + 1;
        }
        memo.put(key, Math.min(includeBack, includeForward));
        return Math.min(includeBack, includeForward);
    }
}
