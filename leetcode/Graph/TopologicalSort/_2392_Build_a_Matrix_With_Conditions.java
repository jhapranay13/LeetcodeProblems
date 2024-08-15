package leetcode.Graph.TopologicalSort;

import java.util.*;

/**
 *
 *
 * You are given a positive integer k. You are also given:
 *
 * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
 * a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
 * The two arrays contain integers from 1 to k.
 *
 * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
 *
 * The matrix should also satisfy the following conditions:
 *
 * The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
 * The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
 * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
 * Output: [[3,0,0],[0,0,1],[0,2,0]]
 * Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
 * The row conditions are the following:
 * - Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
 * - Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
 * The column conditions are the following:
 * - Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
 * - Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
 * Note that there may be multiple correct answers.
 * Example 2:
 *
 * Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
 * Output: []
 * Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
 * No matrix can satisfy all the conditions, so we return the empty matrix.
 *
 *
 * Constraints:
 *
 * 2 <= k <= 400
 * 1 <= rowConditions.length, colConditions.length <= 10^4
 * rowConditions[i].length == colConditions[i].length == 2
 * 1 <= abovei, belowi, lefti, righti <= k
 * abovei != belowi
 * lefti != righti
 *
 */

public class _2392_Build_a_Matrix_With_Conditions {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rp = new int[k + 1]; //row position
        int[] cp = new int[k + 1]; //col position;
        Arrays.fill(rp, -1);
        Arrays.fill(cp, -1);

        if (!buildMatrix(rowConditions, rp) || !buildMatrix(colConditions, cp)) {
            return new int[0][0];
        }
        int[][] ans = new int[k][k];

        for (int i = 1; i <= k; i++) {
            ans[rp[i]][cp[i]] = i;
        }
        return ans;
    }

    private boolean buildMatrix(int[][] rowConditions, int[] p) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] incoming = new int[p.length];

        for (int[] row : rowConditions) {
            int start = row[0];
            int end = row[1];
            incoming[end]++;
            List<Integer> list = adj.getOrDefault(start, new ArrayList<>());
            list.add(end);
            adj.put(start, list);
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 1; i < incoming.length; i++) {

            if (incoming[i] == 0) {
                q.offer(i);
            }
        }
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int curr = q.poll();
                p[curr] = depth++;
                List<Integer> list = adj.getOrDefault(curr, new ArrayList<>());

                for (int next : list) {

                    if (--incoming[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        for (int i = 0; i < p.length; i++) {

            if (incoming[i] > 0) {
                return false;
            }
        }

        for (int i = 0; i < p.length; i++) {

            if (p[i] == -1) {
                p[i] = depth++;
            }
        }
        return true;
    }
}
