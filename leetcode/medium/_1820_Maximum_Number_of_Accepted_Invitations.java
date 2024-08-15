package leetcode.medium;

import java.util.*;

/**
 *
 * There are m boys and n girls in a class attending an upcoming party.
 *
 * You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.
 *
 * Return the maximum possible number of accepted invitations.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1],
 *                [1,0,1],
 *                [0,0,1]]
 * Output: 3
 * Explanation: The invitations are sent as follows:
 * - The 1st boy invites the 2nd girl.
 * - The 2nd boy invites the 1st girl.
 * - The 3rd boy invites the 3rd girl.
 * Example 2:
 *
 * Input: grid = [[1,0,1,0],
 *                [1,0,0,0],
 *                [0,0,1,0],
 *                [1,1,1,0]]
 * Output: 3
 * Explanation: The invitations are sent as follows:
 * -The 1st boy invites the 3rd girl.
 * -The 2nd boy invites the 1st girl.
 * -The 3rd boy invites no one.
 * -The 4th boy invites the 2nd girl.
 *
 *
 * Constraints:
 *
 * grid.length == m
 * grid[i].length == n
 * 1 <= m, n <= 200
 * grid[i][j] is either 0 or 1.
 *
 */

public class _1820_Maximum_Number_of_Accepted_Invitations {
    public int maximumInvitations1(int[][] grid) {
        int boys = grid.length;
        int girls = grid[0].length;
        Deque<Integer>[] girlsQueue = new Deque[grid.length];

        for (int row = 0; row < boys; row++) {
            girlsQueue[row] = new LinkedList<>();

            for (int col = 0; col < girls; col++) {
                // pushing because trying to match every bot with last available girl
                if (grid[row][col] > 0) {
                    girlsQueue[row].push(col);
                }
            }
        }
        int ans = 0;
        int[] invited = new int[grid[0].length];
        Arrays.fill(invited, -1);

        for (int boy = 0; boy < boys; boy++) {
            ans += recur(girlsQueue, invited, boy);
        }
        return ans;
    }

    private int recur(Deque<Integer>[] girlsQueue, int[] invited, int boy) {

        while(!girlsQueue[boy].isEmpty()) {
            int girl = girlsQueue[boy].poll();
            //Checking if the girl can have other option so as to maximize the choice for the boy
            if (invited[girl] == -1 || recur(girlsQueue, invited, invited[girl]) > 0) {
                invited[girl] = boy;
                return 1;
            }
        }
        return 0;
    }

    //================================================================================
    //TLE. Needs more understanding
    public int maximumInvitations(int[][] grid) {
        List<List<Integer>> girlsQueue = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            List<Integer> queue = new ArrayList<>();

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    queue.add(c);
                }
            }
            girlsQueue.add(queue);
        }
        Set<Integer> vg = new HashSet<>();
        Map<String, Integer> memo = new HashMap<>();
        return recur(girlsQueue, vg, 0, memo);
    }

    private int recur(List<List<Integer>> girlsQueue, Set<Integer> vg, int r, Map<String, Integer> memo) {

        if (r == girlsQueue.size()) {
            return 0;
        }
        String key = vg.toString() + "|" + r;

        if (memo.get(key) != null) {
            return memo.get(key);
        }
        int ans = 0;
        List<Integer> girls = girlsQueue.get(r);

        for (int girl : girls) {
            int temp = 0;

            if (!vg.contains(girl)) {
                vg.add(girl);
                temp = 1 + recur(girlsQueue, vg, r + 1, memo);
                vg.remove(girl);
            }
            ans = Math.max(ans, temp);
        }
        ans = Math.max(ans, recur(girlsQueue, vg, r + 1, memo));
        memo.put(key, ans);
        return ans;
    }
}
