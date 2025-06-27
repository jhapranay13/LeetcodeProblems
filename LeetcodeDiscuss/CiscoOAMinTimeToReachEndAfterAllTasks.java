package LeetcodeDiscuss;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Minimum Time to Reach End Node Visiting Task Nodes
 * You are given a bidirectional tree with n nodes (numbered 0 to n-1) and a list of edges.
 * You are also given:
 *
 * A start_node
 *
 * An end_node
 *
 * A list of task_nodes which must be visited at least once
 *
 * You need to determine the minimum time (in edge traversals) required to:
 *
 * Start from start_node
 *
 * Visit all the task_nodes (in any order)
 *
 * Reach end_node
 *
 * Each edge takes 1 unit time to traverse.
 *
 */
public class CiscoOAMinTimeToReachEndAfterAllTasks {
    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {
                {0, 1}, {1, 2}, {1, 3}, {3, 4}, {4, 5}, {4, 6}
        };

        int start = 0;
        int end = 5;
        List<Integer> tasks = Arrays.asList(2, 6);

        int result = minTime(n, edges, start, end, tasks);
        System.out.println("Minimum time: " + result); // Expected: 8
    }

    private static int minTime(int n, int[][] edges, int start, int end, List<Integer> tasks) {
        // Build the adjacency list for the tree
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new java.util.ArrayList<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new java.util.ArrayList<>()).add(edge[0]);
        }
        List<Integer> criticalNodes = new java.util.ArrayList<>();
        criticalNodes.add(start);

        for (int task : tasks) {
            criticalNodes.add(task);
        }
        criticalNodes.add(end);
        int[][] dist = new int[n][n];

        for (int i = 0; i < criticalNodes.size(); i++) {
            int node = criticalNodes.get(i);
            dist[node] = bfs(adjacencyList, node);
        }
        int currState = 0;
        int endState = (1 << criticalNodes.size() - 2) - 1;
        int[][] memo = new int[adjacencyList.size()][1 << (criticalNodes.size() - 2)];
        return findMinTime(criticalNodes, dist, start, end, currState, endState, memo);
    }

    private static int findMinTime(List<Integer> criticalNodes, int[][] dist,
                                   int curr, int end, int currState, int endState, int[][] memo) {

        if (currState == endState) {
            return dist[curr][end]; // All tasks visited, return distance to end node
        }

        if (memo[curr][currState] > 0) {
            return memo[curr][currState]; // Return memoized result
        }
        int minTime = Integer.MAX_VALUE;

        for (int i = 1; i < criticalNodes.size() - 1; i++) {

            if ((currState & (1 << (i - 1))) == 0) { // If task i is not visited
                int nextState = currState | (1 << (i - 1));
                int nextNode = criticalNodes.get(i);
                int time = dist[curr][nextNode] + findMinTime(criticalNodes, dist, nextNode, end, nextState, endState, memo);
                minTime = Math.min(minTime, time);
            }
        }
        return memo[curr][currState] = minTime;
    }

    private static int[] bfs(Map<Integer, List<Integer>> adjacencyList, Integer start) {
        int[] distances = new int[adjacencyList.size()];
        boolean[] visited = new boolean[adjacencyList.size()];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();

        queue.add(start);
        visited[start] = true;
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adjacencyList.getOrDefault(current, java.util.Collections.emptyList())) {

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[current] + 1;
                    queue.add(neighbor);
                }
            }
        }
        return distances;
    }
}
