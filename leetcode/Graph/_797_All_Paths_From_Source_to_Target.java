package leetcode.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 *
 *
 * Example 1:
 *
 *              0 --> 1
 *              |     |
 *              V     V
 *              2 --->3
 *
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 *               ----------------|
 *              |        0 --->  1
 *              |      /  \    /  \
 *              |     V    V  V    V
 *              V --->4 <---3 <----2
 *              
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 *
 */

public class _797_All_Paths_From_Source_to_Target {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] v = new int[graph.length];
        recur(graph, ans, new ArrayList<>(), 0, v);
        return ans;
    }

    private void recur(int[][] graph, List<List<Integer>> ans, ArrayList<Integer> holder, int curr, int[] v) {


        if (curr == graph.length - 1) {
            holder.add(curr);
            ans.add((List<Integer>)holder.clone());
            holder.remove(holder.size() - 1);
            return;
        }

        holder.add(curr);
        v[curr] = 1;
        int[] children = graph[curr];

        for (int child : children) {

            if (v[child] != 1) {
                recur(graph, ans, holder, child, v);
            }
        }
        holder.remove(holder.size() - 1);
        v[curr] = 0;
    }
}
