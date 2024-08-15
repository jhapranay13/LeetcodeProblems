package leetcode.Graph.UnionFind;

import java.util.Arrays;

/**
 *
 * You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space. A brick is stable if:
 *
 * It is directly connected to the top of the grid, or
 * At least one other brick in its four adjacent cells is stable.
 * You are also given an array hits, which is a sequence of erasures we want to apply. Each time we want to erase the brick at the location hits[i] = (rowi, coli). The brick on that location (if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will fall. Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).
 *
 * Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.
 *
 * Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * Output: [2]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 *  [1,1,1,0]]
 * We erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 *  [0,1,1,0]]
 * The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * Hence the result is [2].
 * Example 2:
 *
 * Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * We erase the underlined brick at (1,1), resulting in the grid:
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * All remaining bricks are still stable, so no bricks fall. The grid remains the same:
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * Next, we erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * Once again, all remaining bricks are still stable, so no bricks fall.
 * Hence the result is [0,0].
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] is 0 or 1.
 * 1 <= hits.length <= 4 * 10^4
 * hits[i].length == 2
 * 0 <= xi <= m - 1
 * 0 <= yi <= n - 1
 * All (xi, yi) are unique.
 *
 */

public class _803_Bricks_Falling_When_Hit {
    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int size) {
            this.parent = new int[size];
            this.size = new int[size];

            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
            Arrays.fill(this.size, 1);
        }

        public int find(int x) {

            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public void union(int x, int y) {
            int[] tempParent = parent;
            int[] tempSize = size;
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
                size[px] += size[py];
            }
        }

        public int getTopSize() {
            return size[find(size.length - 1)] - 1;
        }
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {

        for (int[] hit : hits) {
            int r = hit[0];
            int c = hit[1];

            if (grid[r][c] == 1) {
                grid[r][c] = 2;// To seperate the hits and then do union find
            }
        }
        // adding extra so that we can keep the Top layer and it's connection seperate
        UnionFind uf = new UnionFind(grid.length * grid[0].length + 1);
        int topLayerSpot = grid.length * grid[0].length;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {
                int idx = r * grid[0].length + c;
                // Maintaing top spot
                if (r == 0 && grid[r][c] == 1) {
                    uf.union(idx, topLayerSpot);
                }
                // Union Top
                if (r > 0 && grid[r - 1][c] == 1 && grid[r][c] == 1) {
                    int topIdx = (r - 1) * grid[0].length + c;
                    uf.union(idx, topIdx);
                }
                // Union Left
                if (c > 0 && grid[r][c - 1] == 1 && grid[r][c] == 1) {
                    int leftIdx = r * grid[0].length + (c - 1);
                    uf.union(idx, leftIdx);
                }
            }
        }
        int[] ans = new int[hits.length];
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0];
            int c = hits[i][1];

            if (grid[r][c] != 0) {
                int preJoiningTopLayerSize = uf.getTopSize();
                int idx = c + r * grid[0].length;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] != 1) {
                        continue;
                    }
                    int nIdx = nc + nr * grid[0].length;
                    uf.union(idx, nIdx);
                }
                // if row  is top layer union it with top
                if (r == 0) {
                    uf.union(idx, topLayerSpot);
                }
                int postJoiningTopLayerSize = uf.getTopSize();
                grid[r][c] = 1;
                // - 1 because substracting current hit cell from the size
                ans[i] = Math.max(0, postJoiningTopLayerSize - preJoiningTopLayerSize - 1);
            }
        }
        return ans;
    }
}
