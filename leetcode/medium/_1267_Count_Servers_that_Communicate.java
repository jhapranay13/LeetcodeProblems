package leetcode.medium;

import java.util.Arrays;

/**
 *
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 *
 *
 *
 * Example 1:
 *              X---
 *              |  |
 *              ---X
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 *
 * Example 2:
 *               X---
 *               |  |
 *               X--X
 *
 *
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 * Example 3:
 *
 *          X----X--------
 *          |____|___X___|
 *          |____|___X___|
 *          |____|___|___X
 *
 *
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 *
 */

public class _1267_Count_Servers_that_Communicate {
    //Simple Array Solution
    public int countServers(int[][] grid) {
        int[] rows = new int[grid.length];
        int[] cols = new int[grid[0].length];

        for (int r = 0; r < rows.length; r++) {

            for (int c = 0; c < cols.length; c++) {

                if (grid[r][c] == 1) {
                    rows[r]++;
                    cols[c]++;
                }
            }
        }
        int ans = 0;

        for (int r = 0; r < rows.length; r++) {

            for (int c = 0; c < cols.length; c++) {

                if (grid[r][c] == 1 && (rows[r] > 1 || cols[c] > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    //Union Find Solution
    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int size) {
            this.parent = new int[size];
            this.size = new int[size];

            for(int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
            Arrays.fill(this.size, 1);
        }

        public int find(int x) {

            if (x != parent[x]) {
                return parent[x] = find(parent[x]);
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
    private int[][] dirs = {{0, 1}, {1, 0}};

    public int countServers1(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind uf = new UnionFind(row * col);

        for (int r = 0; r < row; r++) {
            int prev = -1;

            for(int c = 0; c < col; c++) {

                if (grid[r][c] == 0) {
                    continue;
                }
                int currId = c + (r * col);

                if (prev == -1) {
                    prev = currId;
                    continue;
                }
                uf.union(prev, currId);
            }
        }

        for(int c = 0; c < col; c++) {
            int prev = -1;

            for (int r = 0; r < row; r++) {

                if (grid[r][c] == 0) {
                    continue;
                }
                int currId = c + (r * col);

                if (prev == -1) {
                    prev = currId;
                    continue;
                }
                uf.union(prev, currId);
            }
        }
        int ans = 0;
        int[] parent = uf.parent;

        for (int i = 0; i < parent.length; i++) {

            if (parent[i] == i && uf.size[i] > 1) {
                ans += uf.size[i];
            }
        }
        return ans;
    }
}
