package leetcode.Graph;

import java.util.*;

/**
 *
 *
 * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
 *
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 *
 * Return the minimum possible score of a path between cities 1 and n.
 *
 * Note:
 *
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1 and n.
 *
 *
 * Example 1:
 *
 *                                  1
 *                                /   \
 *                          7   /      \  9
 *                             4 ------ 2
 *                                 5   /
 *                                   / 2
 *                                  3
 *
 * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 * Output: 5
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
 * It can be shown that no other path has less score.
 * Example 2:
 *
 *
 *  *                                  1
 *  *                                /   \
 *  *                          4   /      \ 2
 *  *                             3        2
 *  *                              \
 *  *                             7 \
 *  *                                3
 *
 *
 * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 * Output: 2
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 * 1 <= roads.length <= 10^5
 * roads[i].length == 3
 * 1 <= ai, bi <= n
 * ai != bi
 * 1 <= distancei <= 10^4
 * There are no repeated edges.
 * There is at least one path between 1 and n.
 *
 */

public class _2492_Minimum_Score_of_a_Path_Between_Two_Cities {
    //BFS
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int[] road : roads) {
            int node1 = road[0];
            int node2 = road[1];
            int dist = road[2];
            List<int[]> list = adjacency.getOrDefault(node1, new ArrayList<>());
            list.add(new int[]{node2, dist});
            adjacency.put(node1, list);
            list = adjacency.getOrDefault(node2, new ArrayList<>());
            list.add(new int[]{node1, dist});
            adjacency.put(node2, list);
        }
        return bfs(adjacency, n);
    }

    private int bfs(Map<Integer, List<int[]>> adjacency, int n) {
        Deque<Integer> q = new LinkedList<>();
        q.offer(1);
        int ans = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            List<int[]> list = adjacency.getOrDefault(node, new ArrayList<>());

            for (int[] child : list) {
                int chNode = child[0];
                int dist = child[1];
                ans = Math.min(ans, dist);

                if (!visited[chNode]) {
                    q.offer(chNode);
                    visited[chNode] = true;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // DFS
    public int minScore1(int n, int[][] roads) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int[] road : roads) {
            int node1 = road[0];
            int node2 = road[1];
            int dist = road[2];
            List<int[]> list = adjacency.getOrDefault(node1, new ArrayList<>());
            list.add(new int[]{node2, dist});
            adjacency.put(node1, list);
            list = adjacency.getOrDefault(node2, new ArrayList<>());
            list.add(new int[]{node1, dist});
            adjacency.put(node2, list);
        }
        boolean[] visited = new boolean[n + 1];

        return dfs(adjacency, visited, 1);
    }

    private int dfs(Map<Integer, List<int[]>> adjacency,
                    boolean[] visited, int node) {
        visited[node] = true;
        int ans = Integer.MAX_VALUE;

        List<int[]> list = adjacency.getOrDefault(node, new ArrayList<>());

        for (int[] child : list) {
            int chNode = child[0];
            int dist = child[1];
            ans = Math.min(ans, dist);

            if (!visited[chNode]) {
                ans = Math.min(ans, dist);
                int temp = dfs(adjacency, visited, chNode);
                ans = Math.min(ans, temp);
            }
        }
        return ans;
    }
    //=============================================================================================
    // Union Find
    class Union {
        int[] cache;

        public Union(int size) {
            this.cache = new int[size];

            for (int i = 1; i < size; i++) {
                this.cache[i] = i;
            }
        }

        public int find(int x) {

            if (this.cache[x] != x) {
                return this.cache[x] = find(this.cache[x]);
            }
            return cache[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                this.cache[py] = px;
            }
        }
    }
    public int minScore2(int n, int[][] roads) {
        Union uf = new Union(n + 1);

        for (int[] road : roads) {
            uf.union(road[0], road[1]);
        }
        int ans = Integer.MAX_VALUE;

        for (int[] road : roads) {

            if (uf.find(1) == uf.find(road[0])) {
                ans = Math.min(ans, road[2]);
            }
        }
        return ans;
    }
}
