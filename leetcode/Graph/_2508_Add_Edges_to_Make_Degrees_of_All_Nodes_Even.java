package leetcode.Graph;

import java.util.*;

/**
 *
 * There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
 *
 * You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
 *
 * Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
 *
 * The degree of a node is the number of edges connected to it.
 *
 *
 *
 * Example 1:
 *
 *             2 ----5
 *           /|  \
 *         1  |   \
 *           \|    \
 *            4 --- 3
 *
 *            connecting 4 and 5 will make all nodes even
 *
 * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding an edge.
 * Every node in the resulting graph is connected to an even number of edges.
 *
 *  Example 2:
 *
 *
 *          1          3
 *          |          |
 *          2          4
 *
 *         connecting 1 and 3 and 2 and 4 will make all nodes even
 * Input: n = 4, edges = [[1,2],[3,4]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding two edges.
 *
 *
 * Example 3:
 *
 *                   1
 *                /  |  \
 *               2   3   4
 *
 * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
 * Output: false
 * Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
 *
 *
 * Constraints:
 *
 * 3 <= n <= 10^5
 * 2 <= edges.length <= 10^5
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There are no repeated edges.
 *
 */
public class _2508_Add_Edges_to_Make_Degrees_of_All_Nodes_Even {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n + 1];
        Map<Integer, Set<Integer>> adjacency = new HashMap<>();

        for (List<Integer> edge : edges) {
            int node1 = edge.get(0);
            int node2 = edge.get(1);
            indegree[node1]++;
            indegree[node2]++;
            Set<Integer> list = adjacency.getOrDefault(node1, new HashSet<>());
            list.add(node2);
            adjacency.put(node1, list);
            list = adjacency.getOrDefault(node2, new HashSet<>());
            list.add(node1);
            adjacency.put(node2, list);
        }
        List<Integer> oddList = new ArrayList<>();

        for (int i = 1; i < indegree.length; i++) {

            if (indegree[i] % 2 == 1) {
                oddList.add(i);
            }
        }

        if (oddList.size() == 0) {
            return true;
        }

        if (oddList.size() == 2) {
            int node1 = oddList.get(0);
            int node2 = oddList.get(1);
            // If both nodes don't have direct edge
            if (!adjacency.get(node1).contains(node2) &&
                    !adjacency.get(node2).contains(node1)) {
                return true;
            }
            // if they can be connected to any other node also the node count will be even
            for (int i = 1; i <= n; i++) {

                if (!adjacency.getOrDefault(i, new HashSet<>()).contains(node1) &&
                        !adjacency.getOrDefault(i, new HashSet<>()).contains(node2)) {
                    return true;
                }
            }
        }
        // if 3 odd count no solution possible coz even if we connect the 3 the middle node will become odd
        if (oddList.size() == 4) {
            // If any 2 pair of nodes that do not have direct connection
            int node1 = oddList.get(0);
            int node2 = oddList.get(1);
            int node3 = oddList.get(2);
            int node4 = oddList.get(3);

            if ((!adjacency.get(node1).contains(node2) && !adjacency.get(node3).contains(node4)) ||
                    (!adjacency.get(node1).contains(node3) && !adjacency.get(node2).contains(node4)) ||
                    (!adjacency.get(node1).contains(node4) && !adjacency.get(node3).contains(node2))) {
                return true;
            }

        }
        // in any other case loke oddList size is greater than 4 it is not possible with 2 edges
        return false;
    }
}
