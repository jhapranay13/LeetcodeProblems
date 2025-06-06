package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.
 *
 * Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
 *
 * You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
 *
 * Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.
 *
 *
 *
 * Example 1:
 *
 *     0  0         1  0     1  0      1  1
 *     0  0         0  0     1  0      1  0
 *
 * Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * Output: 2
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 2.
 *
 * Example 2:
 *
 *  *     0  0         1  0     1  1
 *  *     0  0         0  0     0  0
 *
 *
 * Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
 * Output: 1
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 1.
 *
 * Example 3:
 *
 *  *     0  0  0       0  1  0     0  1  0      0  1  0       0  1  0
 *  *     0  0  0       0  0  0     1  0  0      1  0  0       1  1  0
 *  *     0  0  0       0  0  0     0  0  0      0  0  1       0  0  1
 *
 * Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
 * Output: 3
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 3.
 *
 *
 * Constraints:
 *
 * 2 <= row, col <= 2 * 104
 * 4 <= row * col <= 2 * 104
 * cells.length == row * col
 * 1 <= ri <= row
 * 1 <= ci <= col
 * All the values of cells are unique.
 *
 */

public class _1970_Last_Day_Where_You_Can_Still_Cross {
    class UnionFind {
        int[] parent;
        int n;

        public UnionFind(int size) {
            this.parent = new int[size + 2]; // +2 for left and right
            this.n = size;

            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {

            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
            }
        }
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int size = row * col;
        UnionFind uf = new UnionFind(size);
        Set<Integer> waterSet = new HashSet<>();

        for (int i = 0; i < cells.length; i++) {
            int[] cell = cells[i];
            int r = cell[0] - 1;
            int c = cell[1] - 1;
            int idx = c + r * col;
            waterSet.add(idx);

            if (c == 0) {
                uf.union(idx, size); // union to left
            }

            if (c == col - 1) {
                uf.union(idx, size + 1); // union to the right
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int nidx = nc + nr * col;

                if (nr < 0 || nc < 0 || nr == row || nc == col || !waterSet.contains(nidx)) {
                    continue;
                }
                uf.union(idx, nidx);
            }
            int parentLeft = uf.find(size);
            int parentRight = uf.find(size + 1);

            if (parentLeft == parentRight) {
                return i;
            }
        }
        return -1;
    }
}
