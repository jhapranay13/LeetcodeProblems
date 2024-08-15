package leetcode.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
 *
 * The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
 *
 * Your task is to move the box 'B' to the target position 'T' under the following rules:
 *
 * The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
 * The character '.' represents the floor which means a free cell to walk.
 * The character '#' represents the wall which means an obstacle (impossible to walk there).
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#",".","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 3
 * Explanation: We return only the number of times the box is pushed.
 * Example 2:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#","#","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: -1
 * Example 3:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T",".",".","#","#"],
 *                ["#",".","#","B",".","#"],
 *                ["#",".",".",".",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 5
 * Explanation: push the box down, left, left, up and up.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid contains only characters '.', '#', 'S', 'T', or 'B'.
 * There is only one character 'S', 'B', and 'T' in the grid.
 *
 */

public class _1263_Minimum_Moves_to_Move_a_Box_to_Their_Target_Location {
    // Up,    Right,   Bottom  & Left
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minPushBox(char[][] grid) {
        int[] boxPos = new int[2];
        int[] targetPos = new int[2];
        int[] playerPos = new int[2];

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {
                char ch = grid[r][c];

                if (ch == 'S') {
                    playerPos[0] = r;
                    playerPos[1] = c;
                } else if (ch == 'B') {
                    boxPos[0] = r;
                    boxPos[1] = c;
                } else if (ch == 'T') {
                    targetPos[0] = r;
                    targetPos[1] = c;
                }
            }
        }
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {boxPos[0], boxPos[1], playerPos[0], playerPos[1]});
        int[][][] v = new int[grid.length][grid[0].length][dirs.length];
        int push = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int data[] = q.poll();
                int br = data[0];
                int bc = data[1];
                int pr = data[2];
                int pc = data[3];

                if (br == targetPos[0] && bc == targetPos[1]) {
                    return push;
                }

                for (int d = 0; d < dirs.length; d++) {
                    if (v[br][bc][d] == 1) {
                        continue;
                    }
                    int[] dir = dirs[d];
                    int nr = br + dir[0];
                    int nc = bc + dir[1];
                    // in oreder to push the box in this direction a person has to reach this box
                    int prevR = br - dir[0];
                    int prevC = bc - dir[1];

                    if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
                            grid[nr][nc] == '#') {
                        continue;
                    }

                    if (prevR < 0 || prevC < 0 || prevR == grid.length ||
                            prevC == grid[0].length || grid[prevR][prevC] == '#') {
                        continue;
                    }
                    // If person can reach box and push from a particular direction
                    // then it will end up at box pos and box will move by one direction
                    if (isReachable(prevR, prevC, br, bc, pr, pc, grid)){
                        v[br][bc][d] = 1;
                        q.offer(new int[] {nr, nc, br, bc});
                    }
                }
            }
            push++;
        }
        return -1;
    }

    // can player reach a position to push ?
    private boolean isReachable(int pushPosR, int pushPosC, int boxPosR, int boxPosC,
                                int personR, int personC, char[][] grid) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {personR, personC});//curr pos of person
        boolean[][] v = new boolean[grid.length][grid[0].length];
        v[boxPosR][boxPosC] = true;  // cannot pass box current pos
        v[personR][personC] = true;

        while (!q.isEmpty()) {

            int data[] = q.poll();
            int r = data[0];
            int c = data[1];

            if (r == pushPosR && c == pushPosC) {
                return true;
            }

            for (int d = 0; d < dirs.length; d++) {
                int[] dir = dirs[d];
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
                        grid[nr][nc] == '#' || v[nr][nc]) {
                    continue;
                }
                v[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }

        }
        return false;
    }
}
