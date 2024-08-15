package leetcode.BFS;

import java.util.*;

/**
 *
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 *
 *
 * Example 1:
 *                  3 <-----  2
 *              / /^
 *              1
 *            //^
 *            0 <-----4 ------> 5
 *              ------
 *
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 2:
 *
 *        0 <------ 1 -------> 2 <------ 3 --------> 4
 *                   ----------          ------------
 *
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 *
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= n <= 5 * 10^4
 * connections.length == n - 1
 * connections[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 *
 */

public class _1466_Reorder_Routes_to_Make_All_Paths_Lead_to_the_City_Zero {
    //DFS Approach
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int[] connect : connections) {
            List<int[]> adj = adjacency.getOrDefault(connect[0], new ArrayList<>());
            adj.add(new int[] {connect[1], 1});
            adjacency.put(connect[0], adj);
            adj = adjacency.getOrDefault(connect[1], new ArrayList<>());
            adj.add(new int[] {connect[0], 0});
            adjacency.put(connect[1], adj);
        }
        Set<Integer> v = new HashSet<>();
        int count = recur(adjacency, v, 0, -1);
        return count;
    }

    private int recur(Map<Integer, List<int[]>> adjacency, Set<Integer> v, int curr, int parent) {

        if (v.contains(curr)) {
            return 0;
        }
        v.add(curr);
        List<int[]> adj = adjacency.get(curr);
        int count = 0;

        for (int[] data : adj) {

            if (data[0] != parent) {
                count += data[1] + recur(adjacency, v, data[0], curr);
            }
        }
        return count;
    }
    //=============================================================================================
    //BFS Apprach
    public int minReorder1(int n, int[][] connections) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int[] connect : connections) {
            List<int[]> adj = adjacency.getOrDefault(connect[0], new ArrayList<>());
            adj.add(new int[] {connect[1], 1});
            adjacency.put(connect[0], adj);
            adj = adjacency.getOrDefault(connect[1], new ArrayList<>());
            adj.add(new int[] {connect[0], 0});
            adjacency.put(connect[1], adj);
        }
        Set<Integer> v = new HashSet<>();
        int count = bfs(adjacency, v);
        return count;
    }

    private int bfs(Map<Integer, List<int[]>> adjacency, Set<Integer> v) {
        int count = 0;
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        v.add(0);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                List<int[]> adj = adjacency.get(node);

                for (int[] data : adj) {

                    if (v.add(data[0])) {
                        count += data[1];
                        q.offer(data[0]);
                    }
                }
            }
        }
        return count;
    }
}
