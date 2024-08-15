package leetcode.hard;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 *
 */

public class _827_MakingALargeIsland {

    //=============================================================================================
    //Union Find approach
    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i]++;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }
            return x;
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
                size[px] += size[py];
            }
        }
    }
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int largestIsland(int[][] grid) {
        int N = grid.length;
        UnionFind uf = new UnionFind(N * N);
        boolean zeroFlag = false;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {

                    if (c + 1 < N && grid[r][c + 1] == 1) {
                        uf.union(r * N + c, r * N + c + 1);
                    }

                    if (r + 1 < N && grid[r + 1][c] == 1) {
                        uf.union(r * N + c, (r + 1) * N + c);
                    }
                } else {
                    zeroFlag = true;
                }
            }
        }
        if (!zeroFlag) {
            return N * N;
        }
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 0) {
                    int temp = 1;
                    Set<Integer> v = new HashSet<>();

                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];

                        if (nr >= 0 && nc >= 0 && nr < N && nc < N && grid[nr][nc] == 1) {
                            int id = nr * N + nc;
                            int parent = uf.find(id);

                            if (v.add(parent)) {
                                temp += uf.size[parent];
                            }
                        }
                    }
                    ans = Math.max(ans, temp);
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    //DFS Approach TLE
    public int largestIsland1(int[][] grid) {
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 0) {
                    ans = Math.max(ans, recur(grid, r, c));
                }
            }
        }

        if (ans == 0) {
            return grid.length * grid[0].length;
        }
        return ans;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int recur(int[][] grid, int r, int c) {
        Set<Integer> v = new HashSet<>();
        int length = grid.length;
        v.add(r * grid.length + c);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(r * grid.length + c);

        while (!stack.isEmpty()) {
            int code = stack.pop();
            int pr = code / grid.length;
            int pc = code % grid.length;

            for (int i = 0; i < dirs.length; i++) {
                int nr = pr + dirs[i][0];
                int nc = pc + dirs[i][1];

                if (!v.contains(nr * grid.length + nc) && nr >= 0 && nc >= 0 && nr < length &&
                        nc < length && grid[nr][nc] == 1) {
                    stack.push(nr * grid.length + nc);
                    v.add(nr * grid.length + nc);
                }
            }
        }
        return v.size();
    }
}
