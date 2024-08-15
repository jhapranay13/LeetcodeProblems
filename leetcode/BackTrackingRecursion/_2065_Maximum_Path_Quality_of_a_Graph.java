package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive). You are given a 0-indexed integer array values where values[i] is the value of the ith node. You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel between the two nodes. Finally, you are given an integer maxTime.
 *
 * A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete. You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).
 *
 * Return the maximum quality of a valid path.
 *
 * Note: There are at most four edges connected to each node.
 *
 *
 *
 * Example 1:
 *                      15
 *             1(32) ------------- 2(10)
 *      10   /
 *         /
 *     (0)0
 *        \
 *      10 \
 *          3(43)
 *
 * Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * Output: 75
 * Explanation:
 * One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
 *
 * Example 2:
 *
 *
 *                           10
 *  *             1(10) ------------- 2(15)
 *  *      10   /
 *  *         /
 *  *     (5)0
 *  *        \
 *  *      10 \
 *  *          3(20)
 *
 * Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * Output: 25
 * Explanation:
 * One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
 * The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
 *
 *
 * Example 3:
 *
 *
 *
 *  *  *             1(2)
 *  *  *      10   / |  \   11
 *  *  *         /   |   \
 *  *  *     (1)0    |    2 (3)
 *  *  *           13|   /
 *  *  *             |  /12
 *  *  *            3(4)
 *
 * Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * Output: 7
 * Explanation:
 * One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.
 *
 *
 * Constraints:
 *
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 10^8
 * 0 <= edges.length <= 2000
 * edges[j].length == 3
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * All the pairs [uj, vj] are unique.
 * There are at most four edges connected to each node.
 * The graph may not be connected.
 *
 */

public class _2065_Maximum_Path_Quality_of_a_Graph {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            int time = edge[2];
            List<int[]> adj = adjacency.getOrDefault(node1, new ArrayList<>());
            adj.add(new int[] {node2, time});
            adjacency.put(node1, adj);
            adj = adjacency.getOrDefault(node2, new ArrayList<>());
            adj.add(new int[] {node1, time});
            adjacency.put(node2, adj);
        }
        Map<Integer, Integer> vf = new HashMap<>();
        recur(adjacency, values, maxTime, vf, 0, 0, 0);
        return ans;
    }
    private int ans = 0;

    private void recur(Map<Integer, List<int[]>> adjacency, int[] values, int maxTime,
                       Map<Integer, Integer> vf, int node, int currTime, int quality) {

        if (currTime > maxTime) {
            return;
        }
        int currQuality = quality;

        if (vf.getOrDefault(node, 0) == 0) {
            currQuality += values[node];
        }

        if (node == 0) {
            ans = Math.max(ans, currQuality);
        }
        vf.put(node, vf.getOrDefault(node, 0) + 1);
        List<int[]> adj = adjacency.getOrDefault(node, new ArrayList<>());

        for (int[] nextNode : adj) {
            int nextTime = currTime + nextNode[1];
            recur(adjacency, values, maxTime, vf, nextNode[0], nextTime, currQuality);
        }
        vf.put(node, vf.get(node) - 1);

        if (vf.get(node) == 0) {
            vf.remove(node);
        }
    }
}
