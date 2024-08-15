package leetcode.HashMapHashSet;

import java.util.*;

/**
 *
 *There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 * Example 2:
 *
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 * Example 3:
 *
 * Input: adjacentPairs = [[100000,-100000]]
 * Output: [100000,-100000]
 *
 *
 * Constraints:
 *
 * nums.length == n
 * adjacentPairs.length == n - 1
 * adjacentPairs[i].length == 2
 * 2 <= n <= 10^5
 * -10^5 <= nums[i], ui, vi <= 10^5
 * There exists some nums that has adjacentPairs as its pairs.
 *
 *
 */

public class _1743_Restore_the_Array_From_Adjacent_Pairs {
    // Recursive approach
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> adjacecncy = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            int node1 = pair[0];
            int node2 = pair[1];
            List<Integer> adj = adjacecncy.getOrDefault(node1, new ArrayList<>());
            adj.add(node2);
            adjacecncy.put(node1, adj);
            adj = adjacecncy.getOrDefault(node2, new ArrayList<>());
            adj.add(node1);
            adjacecncy.put(node2, adj);
        }
        int start = -1;
        // Sort of Kahn's algo
        for (int key : adjacecncy.keySet()) {

            if (adjacecncy.get(key).size() == 1) {
                start = key;
                break;
            }
        }
        int[] ans = new int[adjacecncy.size()];
        recur(adjacecncy, start, -1, ans, 0);
        return ans;
    }

    private void recur(Map<Integer, List<Integer>> adjacecncy, int start, int parent,int[] ans, int index) {

        if (index == ans.length) {
            return;
        }
        ans[index] = start;
        List<Integer> adj = adjacecncy.get(start);

        for (int next : adj) {

            if (next == start || next == parent) {
                continue;
            }
            recur(adjacecncy, next, start, ans,index + 1);
        }
    }
    //=============================================================================================
    // BFS
    public int[] restoreArray1(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> adjacecncy = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            int node1 = pair[0];
            int node2 = pair[1];
            List<Integer> adj = adjacecncy.getOrDefault(node1, new ArrayList<>());
            adj.add(node2);
            adjacecncy.put(node1, adj);
            adj = adjacecncy.getOrDefault(node2, new ArrayList<>());
            adj.add(node1);
            adjacecncy.put(node2, adj);
        }
        int start = -1;
        // Sort of Kahn's algo
        for (int key : adjacecncy.keySet()) {

            if (adjacecncy.get(key).size() == 1) {
                start = key;
                break;
            }
        }
        int[] ans = new int[adjacecncy.size()];
        bfs(adjacecncy, start, ans);
        return ans;
    }

    private void bfs(Map<Integer, List<Integer>> adjacecncy, int start, int[] ans) {
        Deque<Integer> q = new LinkedList<>();
        int index = 0;
        int prev = -1;
        q.offer(start);

        while (!q.isEmpty()) {
            int curr = q.poll();
            ans[index++] = curr;
            List<Integer> adj = adjacecncy.get(curr);

            for (int next : adj) {

                if (next == curr || next == prev) {
                    continue;
                }
                q.offer(next);
            }
            prev = curr;
        }
    }
}
