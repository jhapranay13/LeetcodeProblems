package leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
 *
 * 0 means the cell cannot be walked through.
 * 1 represents an empty cell that can be walked through.
 * A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
 *
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 *
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 *
 * Note: The input is generated such that no two trees have the same height, and there is at least one tree needs to be cut off.
 *
 *
 *
 * Example 1:
 *                  1 -> 2 -> 3
 *                            V
 *                  0    0    4
 *                            V
 *                  7 <- 6 <- 5
 *
 * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 * Output: 6
 * Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
 * Example 2:
 *
 *
 * Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
 * Output: -1
 * Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
 * Example 3:
 *
 * Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
 * Output: 6
 * Explanation: You can follow the same path as Example 1 to cut off all the trees.
 * Note that you can cut off the first tree at (0, 0) before making any steps.
 *
 *
 * Constraints:
 *
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 10^9
 * Heights of all trees are distinct.
 *
 */

public class _675_Cut_Off_Trees_for_Golf_Event {
    //checking if tree of consecutive height is reachable
    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < forest.size(); i++) {

            for (int j = 0; j < forest.get(0).size(); j++) {

                if (forest.get(i).get(j) > 1) {
                    pq.offer(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }
        int ans = 0;
        int r = 0;
        int c = 0;

        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            int shortestPath = getShortestPath(forest, r, c, tree[0], tree[1]);

            if (shortestPath == -1) {
                return -1;
            }
            ans += shortestPath;
            r = tree[0];
            c = tree[1];
        }
        return ans;
    }
    private int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int getShortestPath(List<List<Integer>> forest, int r, int c, int sr, int sc) {
        Deque<int[]> q = new LinkedList<>();
        int[][] v = new int[forest.size()][forest.get(0).size()];
        v[sr][sc] = 1;
        int step = 0;
        q.offer(new int[] {sr, sc});

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] tree = q.poll();

                if (tree[0] == r && tree[1] == c) {
                    return step;
                }

                for (int[] dir : dirs) {
                    int nr = tree[0] + dir[0];
                    int nc = tree[1] + dir[1];

                    if (nr < 0 || nc < 0 || nr >= forest.size() || nc >= forest.get(0).size() ||
                            forest.get(nr).get(nc) == 0) {
                        continue;
                    }

                    if (v[nr][nc] == 0) {
                        v[nr][nc] = 1;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
