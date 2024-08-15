package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 *
 *
 *
 * Example 1:
 *                      0
 *                   //    \\
 *                  1       2
 *               //   \\   /   \
 *               4     5 3     6
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 *                         0
 *  *                   //    \\
 *  *                  1       2
 *  *                /   \\   /  \
 *  *               4     5 3     6
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * fromi < toi
 * hasApple.length == n
 *
 */

public class _1443_Minimum_Time_to_Collect_All_Apples_in_a_Tree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            adjacency.put(edge[0], list);
            list = adjacency.getOrDefault(edge[1], new ArrayList<>());
            list.add(edge[0]);
            adjacency.put(edge[1], list);
        }
        return recur(adjacency, hasApple, 0, -1);
    }

    private int recur(Map<Integer, List<Integer>> adjacency, List<Boolean> hasApple,
                      int node, int parent) {

        int ans = 0;
        List<Integer> adj = adjacency.get(node);

        for (int child : adj) {

            if (child == parent) {
                continue;
            }
            ans += recur(adjacency, hasApple, child, node);
        }
        // if ans is greater than 0 that means child has apple so adding 2 for that path
        if ((hasApple.get(node) || ans > 0) && node != 0) {
            ans += 2; // Adding coming and going path from this node
        }
        return ans;
    }
}
