package leetcode.Graph.ShortestPath;

import java.util.*;

/**
 *
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.
 *
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 *
 * A binary matrix is a matrix with all cells equal to 0 or 1 only.
 *
 * A zero matrix is a matrix with all cells equal to 0.
 *
 *
 *
 * Example 1:
 *
 *              [0,0],  --->  [1,0],  ---->    [0,1], ---->    [0,0],
 *              [0,1]         [1,0]            [1,1]           [0,0]
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
 * Example 2:
 *
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We do not need to change it.
 * Example 3:
 *
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix cannot be a zero matrix.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 3
 * mat[i][j] is either 0 or 1.
 *
 */

public class _1284_MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    //=============================================================================================
    //Recursive Top Down DFS
    public int minFlips(int[][] mat) {

        //Flipping a cell (and indirectly its neighbours) 1 time is equivalent to
        // flipping them 3 / 5 / 7 ... times, and so does not flipping is equivalent
        // to flipping 2 / 4 / 6 ... times. So it's enough to only check flipping vs. not
        // flipping per cell.
        //The order of the flip does not matter.
        for (int r = 0; r < mat.length; r++) {

            for (int c = 0; c < mat[0].length; c++) {

                if (mat[r][c] == 1) {
                    countOne++;
                }
            }
        }
        int[] memo = new int[mat[0].length * mat.length];
        Arrays.fill(memo, -1);
        return recur(mat, 0, memo);
    }
    private int countOne = 0;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int recur(int[][] mat, int idx, int[] memo) {

        if (countOne == 0) {
            return 0;
        }

        if (idx == mat.length * mat[0].length) {
            return -1;
        }

        if (memo[idx] != -1) {
            return memo[idx];
        }
        int noFlip = recur(mat, idx + 1, memo);
        int r = idx / mat[0].length;
        int c = idx % mat[0].length;
        flip(mat, r, c);
        int flip = recur(mat, idx + 1, memo);
        flip(mat, r, c);

        if (noFlip != -1 && flip != -1) {
            return memo[idx] = Math.min(noFlip, flip);
        }

        if (noFlip != -1 && flip == -1) {
            return memo[idx] = noFlip;
        }

        if (noFlip == -1 && flip != -1) {
            return memo[idx] = 1 + flip;
        }
        return memo[idx] = -1;
    }

    private void flip(int[][] mat, int r, int c) {

        if (mat[r][c] == 0) {
            mat[r][c] = 1;
            countOne++;
        } else {
            mat[r][c] = 0;
            countOne--;
        }

        for (int dir[] : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nc >= 0 && nr < mat.length && nc < mat[0].length) {

                if (mat[nr][nc] == 0) {
                    mat[nr][nc] = 1;
                    countOne++;
                } else {
                    mat[nr][nc] = 0;
                    countOne--;
                }
            }
        }
    }
    //=============================================================================================
    //BFS Shortest Path
    //Using arrays to maintian the state coz rows * cols cannot be more than 9 according to
    //the constraint. So instead of maintinag the state as a string or an array
    //we can maintian the state in bits.
    public int minFlips1(int[][] mat) {
        Set<Integer> v = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        int initState = 0;
        int rows = mat.length;
        int cols = mat[0].length;

        for (int r = 0; r < mat.length; r++) {

            for (int c = 0; c < mat[0].length; c++) {
                //setting the bit at the idx of row and col
                if (mat[r][c] == 1) {
                    int mask = 1 << (r * cols + c);
                    initState ^= mask;
                }
            }
        }
        q.add(initState); //adding Current State
        v.add(initState);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int state = q.poll();

                if (state == 0) {
                    return ans;
                }

                for (int i = 0; i < rows * cols; i++) {
                    int nextState = filpAndGetState(state, i, rows, cols);

                    if (!v.contains(nextState)) {
                        v.add(nextState);
                        q.offer(nextState);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    private int filpAndGetState(int state, int idx, int rows, int cols) {
        int r = idx / cols;
        int c = idx % cols;
        int mask = 1 << idx;
        int s = state;
        s ^= mask;


        for (int dir[] : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols) {
                int nidx = nr * cols + nc;
                int nmask = 1 << nidx;
                s ^= nmask;
            }
        }
        return s;
    }
}
