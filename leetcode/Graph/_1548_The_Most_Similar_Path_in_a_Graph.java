package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly three upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e., the cities and the roads are forming an undirected connected graph).
 *
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
 *
 * You need to return the order of the nodes in the path with the minimum edit distance. The path should be of the same length of targetPath and should be valid (i.e., there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
 *
 * The edit distance is defined as follows:
 *
 *  define editDistance(targetPath, mypath) {
 *      dis != 0;
 *      a = targetPath.length
 *      b = mypath.length
 *
 *      if (a != b) {
 *          return 100000000
 *      }
 *
 *      for (i = 0; i < a; i++) {
 *
 *          if (targetpath[i] != mypath[i]) {
 *              dis += 1;
 *          }
 *      }
 *      return dis;
 *  }
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
 * Output: [0,2,4,2]
 * Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
 * [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
 * Example 2:
 *
 *
 * Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
 * Output: [0,1,0,1,0,1,0,1]
 * Explanation: Any path in this graph has edit distance = 8 with targetPath.
 * Example 3:
 *
 *
 *
 * Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 * Output: [3,4,5,4,3,2,1]
 * Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
 * It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * m == roads.length
 * n - 1 <= m <= (n * (n - 1) / 2)
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
 * names.length == n
 * names[i].length == 3
 * names[i] consists of upper-case English letters.
 * There can be two cities with the same name.
 * 1 <= targetPath.length <= 100
 * targetPath[i].length == 3
 * targetPath[i] consists of upper-case English letters.
 *
 *
 * Follow up: If each node can be visited only once in the path, What should you change in your solution?
 *
 */

public class _1548_The_Most_Similar_Path_in_a_Graph {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjacency.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            adjacency.get(x).add(y);
            adjacency.get(y).add(x);
        }
        int distance = Integer.MAX_VALUE;
        int node = -1;
        int[][] nextMinNode = new int[n][targetPath.length];
        Integer[][] memo = new Integer[n][targetPath.length];

        for (int i = 0; i < n; i++) {
            int temp = recur(adjacency, names, targetPath,nextMinNode, 0, i, memo);

            if (temp < distance) {
                node = i;
                distance = temp;
            }
        }
        List<Integer> ans = new ArrayList<>();
        genrateAns(nextMinNode, node, ans, targetPath.length);
        return ans;
    }

    private int recur(Map<Integer, List<Integer>> adjacency, String[] names, String[] targetPath,int[][] nextMinNode,
                      int indexTp, int node, Integer[][] memo) {

        if (indexTp == targetPath.length) {
            return 0;
        }

        if (memo[node][indexTp] != null) {
            return memo[node][indexTp];
        }
        int minDistance = Integer.MAX_VALUE;
        int nextMin = -1;
        List<Integer> adj = adjacency.get(node);

        for (int nextNode : adj) {
            int distance = recur(adjacency, names, targetPath, nextMinNode, indexTp + 1, nextNode, memo);
            // Checking the edit distance i.e targetPathName is dofferent from current node name then distanc is +1
            if (!names[node].equals(targetPath[indexTp])) {
                distance++;
            }

            if (distance < minDistance) {
                minDistance = distance;
                nextMin = nextNode;
            }
        }
        nextMinNode[node][indexTp] = nextMin;
        return memo[node][indexTp] = minDistance;
    }

    private void genrateAns(int[][] nextMinNode, int node, List<Integer> ans, int length) {
        int curr = node;

        for (int i = 0; i < length; i++) {
            ans.add(curr);
            curr = nextMinNode[curr][i];
        }
    }
}
