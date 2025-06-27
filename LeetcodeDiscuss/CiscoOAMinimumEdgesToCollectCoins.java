package LeetcodeDiscuss;

import java.util.*;

/**
 *
 * Minimum Edge Walk to Collect All Coins in a Tree
 * You are given a tree with n vertices and some of the nodes contain coins.
 *
 * Alex can start at any node, and must return to the same node he started from.
 * He can traverse any edge multiple times, and collects all coins that are within
 * distance ≤ 2 of any node he visits.
 *
 * You need to return the minimum number of edges he must walk to collect all the coins.
 *
 */
public class CiscoOAMinimumEdgesToCollectCoins {
    public static void main(String[] args) {
        int[] coins = {0, 0, 0, 1, 0, 1};
        int[][] edges = {
                {0, 1},
                {1, 2},
                {1, 3},
                {3, 4},
                {3, 5}
        };
        int result = collectTheCoins(coins, edges);
        System.out.println("Minimum number of edges to traverse: " + result); // Expected: 0

        // Test Case 1: Provided example
        int[] coins1 = {1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1};
        int[][] edges1 = {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {8, 7}, {3, 9}, {9, 10}, {10, 11}
        };
        // Expected output: 10 (as stated in your problem description,
        // my dry run suggests 16 with my current logic, indicating a subtle nuance in the problem)
        System.out.println("Test Case 1 (Provided Example):");
        System.out.println("Output: " + collectTheCoins(coins1, edges1));
        System.out.println("Expected: 10\n");

        // Test Case 2: Simple line graph
        // 0 -- 1 -- 2 -- 3
        // Coins at 0 and 3
        // To collect: 0 (from 0), 3 (from 3)
        // Path: 0-1-2-3 (length 3). Round trip = 6.
        // My logic:
        // dfs(3,2) -> coins[3]=1 -> isActive[3]=T -> returns 0
        // dfs(2,1) -> from child 3 (req 0), isActive[2]=T, isActive[3]=T, maxReq=1 -> returns 1
        // dfs(1,0) -> from child 2 (req 1), isActive[1]=T, isActive[2]=T, maxReq=2 -> returns -1
        // dfs(0,-1) -> from child 1 (req -1)
        // coins[0]=1 -> isActive[0]=T, maxReq=0 -> returns 0
        // Active: 0,1,2,3. Edges: (0,1), (1,2), (2,3) -> 3 essential edges * 2 = 6.
        int[] coins2 = {1, 0, 0, 1};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Test Case 2 (Simple Line Graph, coins at ends):");
        System.out.println("Output: " + collectTheCoins(coins2, edges2));
        System.out.println("Expected: 0\n"); // Assuming my logic for "shortest path" holds

        // Test Case 3: Star graph, coin at center and one leaf
        //   0 -- 1 (C)
        //  /|\
        // 2 3 4
        //   |
        //   5 (C)
        // Coins at 1, 5
        // To collect 1: visit 1, or 0.
        // To collect 5: visit 5, or 0, or 3.
        // Path: 1-0-3-5. Length 3. Round trip = 6.
        // My logic:
        // dfs(5,3) -> coins[5]=1 -> isActive[5]=T -> returns 0
        // dfs(3,0) -> from child 5 (req 0), isActive[3]=T,isActive[5]=T, maxReq=1 -> returns 1
        // dfs(1,0) -> coins[1]=1 -> isActive[1]=T -> maxReq=0 -> returns 0
        // dfs(0,-1) -> from child 3 (req 1), isActive[0]=T,isActive[3]=T, maxReq=2 -> returns -1
        //             from child 1 (req 0), isActive[0]=T,isActive[1]=T, maxReq=2 (still 2) -> returns -1
        // Active: 0,1,3,5. Edges: (0,1), (0,3), (3,5) -> 3 essential edges * 2 = 6.
        int[] coins3 = {0, 1, 0, 0, 0, 1};
        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {3, 5}};
        System.out.println("Test Case 3 (Star graph, coins at leaf/branch):");
        System.out.println("Output: " + collectTheCoins(coins3, edges3));
        System.out.println("Expected: 6\n");

        // Test Case 4: No coins
        int[] coins4 = {0, 0, 0};
        int[][] edges4 = {{0, 1}, {1, 2}};
        System.out.println("Test Case 4 (No coins):");
        System.out.println("Output: " + collectTheCoins(coins4, edges4));
        System.out.println("Expected: 0\n");

        // Test Case 5: All coins
        // 0 (C) -- 1 (C) -- 2 (C)
        // Path: 0-1-2. Length 2. Round trip = 4.
        // My logic: all nodes become active. 2 essential edges * 2 = 4.
        int[] coins5 = {1, 1, 1};
        int[][] edges5 = {{0, 1}, {1, 2}};
        System.out.println("Test Case 5 (All coins):");
        System.out.println("Output: " + collectTheCoins(coins5, edges5));
        System.out.println("Expected: 0\n");
    }

    private static int collectTheCoins(int[] coins, int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int [] edge : edges) {
            adjacencyList.putIfAbsent(edge[0], new HashSet<>());
            adjacencyList.putIfAbsent(edge[1], new HashSet<>());
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int n = adjacencyList.size();
        int[] incoming = new int[n];

        for (int[] edge : edges) {
            incoming[edge[1]]++;
            incoming[edge[0]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        // Removing all leaf nodes with no coins
        for (int i = 0; i < n; i++) {

            if (incoming[i] == 1 && coins[i] == 0) {
                queue.offer(i);
                incoming[i]--;
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adjacencyList.get(node)) {
                incoming[neighbor]--;
                adjacencyList.get(neighbor).remove(node);

                if (incoming[neighbor] == 1 && coins[neighbor] == 0) {
                    queue.offer(neighbor);
                    incoming[neighbor]--;
                }
            }
            adjacencyList.remove(node);
        }
        //trimming 2 more levels
        for (int t= 0; t < 2 ; t++) {
            queue.clear();

            for (int i = 0; i < n; i++) {
                if (incoming[i] == 1) {
                    queue.offer(i);
                    incoming[i]--;
                }
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor : adjacencyList.get(node)) {
                    incoming[neighbor]--;
                    adjacencyList.get(neighbor).remove(node);
                }
                adjacencyList.remove(node);
            }
        }
        int ans = 0;
        // adding remaininng edges which will get added twice because of parent and child
        // bi directional
// t    // adjacency
        for (int i =0; i < n; i++) {
            if (adjacencyList.containsKey(i)) {
                ans += adjacencyList.get(i).size();
            }
        }
        return ans;
    }
}
