package leetcode.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * A maze consists of n rooms numbered from 1 to n, and some rooms are connected by corridors. You are given a 2D integer array corridors where corridors[i] = [room1i, room2i] indicates that there is a corridor connecting room1i and room2i, allowing a person in the maze to go from room1i to room2i and vice versa.
 *
 * The designer of the maze wants to know how confusing the maze is. The confusion score of the maze is the number of different cycles of length 3.
 *
 * For example, 1 → 2 → 3 → 1 is a cycle of length 3, but 1 → 2 → 3 → 4 and 1 → 2 → 3 → 2 → 1 are not.
 * Two cycles are considered to be different if one or more of the rooms visited in the first cycle is not in the second cycle.
 *
 * Return the confusion score of the maze.
 *
 *
 *
 * Example 1:
 *                  5
 *                  |
 *                  2
 *               //   \\
 *              1 ===== 4
 *              \\    //
 *                 3
 *
 * Input: n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
 * Output: 2
 * Explanation:
 * One cycle of length 3 is 4 → 1 → 3 → 4, denoted in red.
 * Note that this is the same cycle as 3 → 4 → 1 → 3 or 1 → 3 → 4 → 1 because the rooms are the same.
 * Another cycle of length 3 is 1 → 2 → 4 → 1, denoted in blue.
 * Thus, there are two different cycles of length 3.
 * Example 2:
 *              1      3
 *              |      |
 *              2      4
 *
 * Input: n = 4, corridors = [[1,2],[3,4]]
 * Output: 0
 * Explanation:
 * There are no cycles of length 3.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 1000
 * 1 <= corridors.length <= 5 * 10^4
 * corridors[i].length == 2
 * 1 <= room1i, room2i <= n
 * room1i != room2i
 * There are no duplicate corridors.
 *
 *
 */

public class _2077_Paths_in_Maze_That_Lead_to_Same_Room {
    public int numberOfPaths(int n, int[][] corridors) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Map<Integer, Set<Integer>> revAdj = new HashMap<>();
        //always from low to high
        //and from high to low
        for (int[] corridor : corridors) {
            int pt1 = corridor[0];
            int pt2 = corridor[1];

            if (pt1 > pt2) {
                Set<Integer> list = adj.getOrDefault(pt2, new HashSet<>());
                list.add(pt1);
                adj.put(pt2, list);
                list = revAdj.getOrDefault(pt1, new HashSet<>());
                list.add(pt2);
                revAdj.put(pt1, list);
            } else {
                Set<Integer> list = adj.getOrDefault(pt1, new HashSet<>());
                list.add(pt2);
                adj.put(pt1, list);
                list = revAdj.getOrDefault(pt2, new HashSet<>());
                list.add(pt1);
                revAdj.put(pt2, list);
            }
        }

        for (int i = 0; i < n; i++) {
            recur(adj, revAdj, i, i, 1);
        }
        return ans;
    }
    private int ans = 0;

    private void recur(Map<Integer, Set<Integer>> adj, Map<Integer, Set<Integer>> revAdj,
                       int curr, int start, int length) {

        if (length == 3) {
            if (revAdj.get(curr).contains(start)) {
                ans++;
            }
            return;
        }

        for (int next : adj.getOrDefault(curr, new HashSet<>())) {
            recur(adj, revAdj, next, start, length + 1);
        }
    }
}
