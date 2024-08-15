package leetcode.BinarySearch;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * You are given a 0-indexed 2D integer array grid of size m x n which represents a field. Each cell has one of three values:
 *
 * 0 represents grass,
 * 1 represents fire,
 * 2 represents a wall that you and fire cannot pass through.
 * You are situated in the top-left cell, (0, 0), and you want to travel to the safehouse at the bottom-right cell, (m - 1, n - 1). Every minute, you may move to an adjacent grass cell. After your move, every fire cell will spread to all adjacent cells that are not walls.
 *
 * Return the maximum number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse. If this is impossible, return -1. If you can always reach the safehouse regardless of the minutes stayed, return 109.
 *
 * Note that even if the fire spreads to the safehouse immediately after you have reached it, it will be counted as safely reaching the safehouse.
 *
 * A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
 * Output: 3
 * Explanation: The figure above shows the scenario where you stay in the initial position for 3 minutes.
 * You will still be able to safely reach the safehouse.
 * Staying for more than 3 minutes will not allow you to safely reach the safehouse.
 * Example 2:
 *
 *              ________________________________                     ________________________________
 *             |    P   |       |       |      |                    |        |   P F |       |      |
 *             |________|_______|_______|______|                    |________|_______|_______|______|
 *             |        |    F  |   W   |      |  After 1 min=>     |    F   |    F  |   W   |      |
 *             |________|_______|_______|______|                    |________|_______|_______|______|
 *             |        |   W   |       |      |                    |        |   W   |       |      |
 *             |________|_______|_______|______|                    |________|_______|_______|______|
 *
 * Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
 * Output: -1
 * Explanation: The figure above shows the scenario where you immediately move towards the safehouse.
 * Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
 * Thus, -1 is returned.
 * Example 3:
 *      `
 *              _________________________
 *             |    P   |       |       |
 *             |________|_______|_______|
 *             |  W     |    w  |       |
 *             |________|_______|_______|
 *             |    F   |   W   |       |
 *             |________|_______|_______|
 *
 * Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
 * Output: 1000000000
 * Explanation: The figure above shows the initial grid.
 * Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
 * Thus, 109 is returned.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 300
 * 4 <= m * n <= 2 * 104
 * grid[i][j] is either 0, 1, or 2.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 *
 */

public class _2258_Escape_the_Spreading_Fire {
    // Two BFS first to check minutes taken by fire to reach a pos 2nd for the person to reach a pos
    public int maximumMinutes(int[][] grid) {
        int[][] timeFire = new int[grid.length][grid[0].length];
        int[][] timePerson = new int[grid.length][grid[0].length];
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    int id = c + r * grid[0].length;
                    q.offer(new int[] {r, c, 0});
                    v.add(id);
                } else {
                    timeFire[r][c] = Integer.MAX_VALUE;
                }
                timePerson[r][c] = Integer.MAX_VALUE;
            }
        }
        //Shortest path fire can take
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();
                int r = data[0];
                int c = data[1];
                int timeTaken = data[2];
                timeFire[r][c] = timeTaken;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int id = nc + nr * grid[0].length;

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
                            grid[nr][nc] == 2 || v.contains(id)) {
                        continue;
                    }
                    v.add(id);
                    q.offer(new int[] {nr, nc, timeTaken + 1});
                }
            }
        }

        q = new LinkedList<>();
        q.offer(new int[] {0, 0, 0});
        v.clear();
        v.add(0);
        int ans = Integer.MAX_VALUE;
        // Shortest time person can take before fire reaches
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();
                int r = data[0];
                int c = data[1];
                int timeTaken = data[2];
                timePerson[r][c] = timeTaken;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int id = nc + nr * grid[0].length;
                    ans = Math.min(ans, timeFire[r][c] - timeTaken);


                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
                            grid[nr][nc] == 2 || v.contains(id) ||
                            (timeFire[nr][nc] < timeTaken + 1 &&
                                    nr != grid.length - 1 && nc != grid[0].length - 1) ) {
                        continue;
                    }
                    v.add(id);
                    q.offer(new int[] {nr, nc, timeTaken + 1});
                }
            }
        }
        int re = grid.length - 1;
        int ce = grid[0].length - 1;

        if (timeFire[re][ce] == Integer.MAX_VALUE &&
                timePerson[re][ce] != Integer.MAX_VALUE) {
            return 1000000000;
        }
        /**
         If a person reaches the safehouse, they escape even if the fire spreads to the
         safehouse in the same minute. So, when the safehouse is the first cell when person
         and fire cross their paths, we do not subtract one.
         To check if the safehouse is the first cell when person and fire "meet", we compare
         the difference for two adjacent (to the safehouse) cells. If the difference is larger
         for any of the adjacent cells, then we do not need to subtract one.
         For the following example, the result is 2, not 1:
         */
        if (timePerson[re][ce] != Integer.MAX_VALUE) {
            int diff = timeFire[re][ce] - timePerson[re][ce];
            int diffLeftCol = timeFire[re][ce - 1] - timePerson[re][ce - 1];
            int diffColTop = timeFire[re - 1][ce] - timePerson[re - 1][ce];

            if (diffLeftCol > diff || diffColTop > diff) {
                return diff;
            }
            return diff - 1;
        }
        return -1;
    }
    int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //=============================================================================================
    // Using Binary Search with BFS
    public int maximumMinutes1(int[][] grid) {
        int[][] timeFire = new int[grid.length][grid[0].length];
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    int id = c + r * grid[0].length;
                    q.offer(new int[] {r, c, 0});
                    v.add(id);
                } else {
                    timeFire[r][c] = Integer.MAX_VALUE;
                }
            }
        }
        //Shortest path fire can take
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();
                int r = data[0];
                int c = data[1];
                int timeTaken = data[2];
                timeFire[r][c] = timeTaken;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int id = nc + nr * grid[0].length;

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
                            grid[nr][nc] == 2 || v.contains(id)) {
                        continue;
                    }
                    v.add(id);
                    q.offer(new int[] {nr, nc, timeTaken + 1});
                }
            }
        }
        int re = grid.length - 1;
        int ce = grid[0].length - 1;
        int lo = 0;
        int hi = (re + 1) * (ce + 1);
        Integer ans = null;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isPossible(timeFire, pivot, grid)) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }

        if (ans == null) {
            return - 1;
        }

        if (ans == (re + 1) * (ce + 1)) {
            return 1000000000;

        }
        return ans;
    }

    private boolean isPossible(int timeFire[][], int startTime, int[][] grid) {
        Set<Integer> v = new HashSet<>();
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        int re = timeFire.length - 1;
        int ce = timeFire[0].length - 1;
        v.add(0);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] point = q.poll();
                int r = point[0];
                int c = point[1];

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    int id = nc + (nr * (ce + 1));

                    if (nr < 0 || nc < 0 || nr > re || nc > ce ||
                            v.contains(id) || grid[nr][nc] == 2) {
                        continue;
                    }
                    if (timeFire[nr][nc] <= (startTime + 1)) {

                        if (nr != re  || nc != ce || timeFire[nr][nc] < (startTime + 1))
                            continue;
                    }

                    if (nr == re && nc == ce) {
                        return (timeFire[nr][nc] == Integer.MAX_VALUE ||
                                timeFire[nr][nc] >= startTime);
                    }
                    v.add(id);
                    q.offer(new int[] {nr, nc});
                }
            }
            startTime++;
        }
        return false;
    }
}
