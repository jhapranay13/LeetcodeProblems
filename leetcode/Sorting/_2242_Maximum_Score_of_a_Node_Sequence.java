package leetcode.Sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * There is an undirected graph with n nodes, numbered from 0 to n - 1.
 *
 * You are given a 0-indexed integer array scores of length n where scores[i] denotes the score of node i. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A node sequence is valid if it meets the following conditions:
 *
 * There is an edge connecting every pair of adjacent nodes in the sequence.
 * No node appears more than once in the sequence.
 * The score of a node sequence is defined as the sum of the scores of the nodes in the sequence.
 *
 * Return the maximum score of a valid node sequence with a length of 4. If no such sequence exists, return -1.
 *
 *                  1(2)
 *                / |   \
 *              /   |    \
 *           0(5)---------2(9)--------4(4)
 *                  |    /
 *                  |  /
 *                  3(8)
 *
 *
 * Example 1:
 *
 *
 * Input: scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
 * Output: 24
 * Explanation: The figure above shows the graph and the chosen node sequence [0,1,2,3].
 * The score of the node sequence is 5 + 2 + 9 + 8 = 24.
 * It can be shown that no other node sequence has a score of more than 24.
 * Note that the sequences [3,1,2,0] and [1,0,2,3] are also valid and have a score of 24.
 * The sequence [0,3,2,4] is not valid since no edge connects nodes 0 and 3.
 *
 *
 * Example 2:
 *
 *
 *                       2(4)                2(6)
 *                    /  |   \                |
 *                  /    |    \               |
 *                0(9)  1(20)  5(12)         4(11)
 *
 * Input: scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
 * Output: -1
 * Explanation: The figure above shows the graph.
 * There are no valid node sequences of length 4, so we return -1.
 *
 *
 * Constraints:
 *
 * n == scores.length
 * 4 <= n <= 5 * 10^4
 * 1 <= scores[i] <= 10^8
 * 0 <= edges.length <= 5 * 10^4
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 *
 */

public class _2242_Maximum_Score_of_a_Node_Sequence {
    public int maximumScore(int[] scores, int[][] edges) {
        PriorityQueue<Integer>[] neighbours = new PriorityQueue[scores.length];

        for (int i = 0; i < scores.length; i++) {
            neighbours[i] = new PriorityQueue<>(new Comparator<Integer>() {
                public int compare(Integer x, Integer y) {
                    return scores[x] - scores[y];
                }
            });
        }

        for (int[] edge : edges) {
            neighbours[edge[0]].offer(edge[1]);

            if (neighbours[edge[0]].size() > 3) {
                neighbours[edge[0]].poll();
            }
            neighbours[edge[1]].offer(edge[0]);

            if (neighbours[edge[1]].size() > 3) {
                neighbours[edge[1]].poll();
            }
        }
        int ans = -1;

        for (int[] edge : edges) {
            int e1 = edge[0];
            int e2 = edge[1];

            for (int e3 : neighbours[e1]) {

                for (int e4 : neighbours[e2]) {

                    if (e1 != e4 && e3 != e2 && e3 != e4) {
                        ans = Math.max(ans, scores[e1] + scores[e2] + scores[e3] + scores[e4]);
                    }
                }
            }
        }
        return ans;
    }
}
