package leetcode.Graph.UnionFind;

import java.util.*;

/**
 *
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 *
 *          |
 *          |
 *          |       X
 *          |
 *          |
 *          |
 *          |
 *          |
 *          |
 *          |    X        X
 *          |
 *          X____________________X____________________________
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 *
 */

public class _1584_Min_Cost_to_Connect_All_Points {
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            parent = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {

            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public boolean union(int x, int y) {
            int parentx = find(x);
            int parenty = find(y);

            if (parent[x] == parent[y]) {
                return false;
            }
            parent[parenty] = parentx;
            return true;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {
                int[] connection = new int[3];
                connection[0] = i;
                connection[1] = j;
                int[] point1 = points[i];
                int[] point2 = points[j];
                int distance = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
                connection[2] = distance;
                pq.offer(connection);
            }
        }
        int ans = 0;
        UnionFind uf = new UnionFind(points.length);

        while (!pq.isEmpty()) {
            int[] connection = pq.poll();

            if (uf.union(connection[0], connection[1])) {
                ans += connection[2];
            }
        }
        return ans;
    }
    //=============================================================================================
    // Prim's Algo
    public int minCostConnectPoints1(int[][] points) {

        if (points.length == 1) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Map<Integer, List<int[]>> adjacency = new HashMap<>();

        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {
                int[] connection1 = new int[2];
                int[] connection2 = new int[2];
                connection1[0] = j;
                connection2[0] = i;
                int[] point1 = points[i];
                int[] point2 = points[j];
                int distance = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
                connection1[1] = distance;
                connection2[1] = distance;
                List<int[]> list1 = adjacency.getOrDefault(i, new ArrayList<>());
                list1.add(connection1);
                adjacency.put(i, list1);
                List<int[]> list2 = adjacency.getOrDefault(j, new ArrayList<>());
                list2.add(connection2);
                adjacency.put(j, list2);
            }
        }
        int ans = 0;
        int numberOfNodes = points.length;
        Set<Integer> v = new HashSet<>();
        pq.offer(new int[] {0, 0});

        while (!pq.isEmpty() && v.size() != numberOfNodes) {
            int[] connection = pq.poll();
            int node = connection[0];

            if (v.contains(node)) {
                continue;
            }
            int distance = connection[1];
            ans += distance;
            v.add(node);
            List<int[]> adjacent = adjacency.get(node);

            for (int[] child : adjacent) {
                int nextNode = child[0];

                if (v.contains(nextNode)) {
                    continue;
                }
                int nextDistance = child[1];
                pq.offer(new int[] {nextNode, nextDistance});
            }
        }
        return ans;
    }
}
