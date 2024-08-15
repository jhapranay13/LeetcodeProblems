package leetcode.Primitive;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 *
 * You are given an m x n grid grid where:
 *
 * '.' is an empty cell.
 * '#' is a wall.
 * '@' is the starting point.
 * Lowercase letters represent keys.
 * Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.
 *
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 *
 * For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 *
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = ["@.a..","###.#","b.A.B"]
 * Output: 8
 * Explanation: Note that the goal is to obtain all the keys not to open all the locks.
 * Example 2:
 *
 *
 * Input: grid = ["@..aA","..B#.","....b"]
 * Output: 6
 * Example 3:
 *
 *
 * Input: grid = ["@Aa"]
 * Output: -1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] is either an English letter, '.', '#', or '@'.
 * The number of keys in the grid is in the range [1, 6].
 * Each key in the grid is unique.
 * Each key in the grid has a matching lock.
 *
 */

public class _864_Shortest_Path_to_Get_All_Keys {
    public int shortestPathAllKeys(String[] grid) {
        Deque<int[]> q = new LinkedList<>();
        int sr = -1;
        int sc = -1;
        int count = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length(); j++) {
                char ch = grid[i].charAt(j);

                if (ch == '@') {
                    sr = i;
                    sc = j;
                }

                if (ch >= 'a' && ch <= 'f') {
                    count = Math.max(count, (ch - 'a') + 1);
                }
            }
        }
        int endState = (1 << count) - 1;
        q.offer(new int[] {0, sr, sc});
        Set<String> v = new HashSet<>();
        v.add( 0 + "|" + sr + "|" + sc);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] currData = q.poll();
                int state = currData[0];
                int r = currData[1];
                int c = currData[2];

                if (state == endState) {
                    return ans;
                }

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length() ||
                            grid[nr].charAt(nc) == '#') {
                        continue;
                    }
                    int nextState = state;
                    char nextCh = grid[nr].charAt(nc);

                    if (nextCh >= 'a' && nextCh <= 'f') {
                        nextState |= 1 << (nextCh - 'a');
                    }
                    String key = nextState + "|" + nr + "|" + nc;

                    if (!v.add(key)) {
                        continue;
                    }

                    if (nextCh >= 'A' && nextCh <= 'F') {

                        if ((nextState & (1 << (nextCh - 'A'))) == 0) {
                            continue;
                        }
                    }
                    q.offer(new int[] {nextState, nr, nc});
                }
            }
            ans++;
        }
        return -1;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
