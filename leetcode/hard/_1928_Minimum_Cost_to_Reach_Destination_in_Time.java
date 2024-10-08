package leetcode.hard;

import java.util.*;

/**
 *
 * There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.
 *
 * Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j.
 *
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities).
 *
 * Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.
 *
 *
 *
 * Example 1:
 *                      10
 *             (1)1 ------------2(2)
 *        10    /                \  10
 *            /                   \
 *        (5)0                      5(3)
 *           \                    /  15
 *      1     \                  /
 *        (20) 3 -------------- 4 (20)
 *                    10
 *
 * Input: maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 11
 * Explanation: The path to take is 0 -> 1 -> 2 -> 5, which takes 30 minutes and has $11 worth of passing fees.
 * Example 2:
 *
 *                      10
 *             (1)1 ------------2(2)
 *        10    /                \  10
 *            /                   \
 *        (5)0                      5(3)
 *           \                    /  15
 *      1     \                  /
 *        (20) 3 -------------- 4 (20)
 *                    10
 *
 * Input: maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 48
 * Explanation: The path to take is 0 -> 3 -> 4 -> 5, which takes 26 minutes and has $48 worth of passing fees.
 * You cannot take path 0 -> 1 -> 2 -> 5 since it would take too long.
 * Example 3:
 *
 * Input: maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: -1
 * Explanation: There is no way to reach city 5 from city 0 within 25 minutes.
 *
 *
 * Constraints:
 *
 * 1 <= maxTime <= 1000
 * n == passingFees.length
 * 2 <= n <= 1000
 * n - 1 <= edges.length <= 1000
 * 0 <= xi, yi <= n - 1
 * 1 <= timei <= 1000
 * 1 <= passingFees[j] <= 1000
 * The graph may contain multiple edges between two nodes.
 * The graph does not contain self loops.
 *
 */

public class _1928_Minimum_Cost_to_Reach_Destination_in_Time {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[1] - b[1] : a[2] - b[2]);
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
        int[] currTime = new int[passingFees.length];
        //time should be less than this to process
        Arrays.fill(currTime, maxTime + 1);
        pq.offer(new int[] {0, 0, passingFees[0]});
        currTime[0] = 0;

        while (!pq.isEmpty()) {
            int[] nodeData = pq.poll();
            int node = nodeData[0];
            int time = nodeData[1];
            int fees = nodeData[2];

            if (node == passingFees.length - 1) {
                return fees;
            }
            List<int[]> adj = adjacency.get(node);

            for (int[] nextData : adj) {
                int nextNode = nextData[0];
                int nextTime = nextData[1] + time;

                if (nextTime >= currTime[nextNode]) {
                    continue;
                }
                currTime[nextNode] = nextTime;
                pq.offer(new int[] {nextNode, nextTime, fees + passingFees[nextNode]});
            }
        }
        return -1;
    }
}
