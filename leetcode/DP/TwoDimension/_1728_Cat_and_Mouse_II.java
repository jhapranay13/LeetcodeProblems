package leetcode.DP.TwoDimension;

/**
 *
 * A game is played by a cat and a mouse named Cat and Mouse.
 *
 * The environment is represented by a grid of size rows x cols, where each element is a wall, floor, player (Cat, Mouse), or food.
 *
 * Players are represented by the characters 'C'(Cat),'M'(Mouse).
 * Floors are represented by the character '.' and can be walked on.
 * Walls are represented by the character '#' and cannot be walked on.
 * Food is represented by the character 'F' and can be walked on.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * Mouse and Cat play according to the following rules:
 *
 * Mouse moves first, then they take turns to move.
 * During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the grid.
 * catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.
 * Staying in the same position is allowed.
 * Mouse can jump over Cat.
 * The game can end in 4 ways:
 *
 * If Cat occupies the same position as Mouse, Cat wins.
 * If Cat reaches the food first, Cat wins.
 * If Mouse reaches the food first, Mouse wins.
 * If Mouse cannot get to the food within 1000 turns, Cat wins.
 * Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * Output: true
 * Explanation: Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
 * Example 2:
 *
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * Output: true
 * Example 3:
 *
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] consist only of characters 'C', 'M', 'F', '.', and '#'.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * 1 <= catJump, mouseJump <= 8
 *
 */

public class _1728_Cat_and_Mouse_II {
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int mr = -1;
        int mc = -1;
        int cr = -1;
        int cc = -1;
        int fr = -1;
        int fc = -1;

        for (int r = 0; r < grid.length; r++) {
            String row = grid[r];

            for (int c = 0; c < row.length(); c++) {

                if (row.charAt(c) == '#') {
                    continue;
                } else if (row.charAt(c) == 'M') {
                    mr = r;
                    mc = c;
                } else if (row.charAt(c) == 'C') {
                    cr = r;
                    cc = c;
                } else if (row.charAt(c) == 'F') {
                    fr = r;
                    fc = c;
                }
            }
        }
        int MAX_STEP = 1000;
        int rows = grid.length;
        int cols = grid[0].length();
        Boolean[][][][][][] memo = new Boolean[rows][cols][rows][cols][rows * cols * 2][2];
        return recur(grid, MAX_STEP, catJump, mouseJump, fr, fc, mr, mc, cr, cc, 0, 0, memo);
    }
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    private boolean recur(String[] grid, int MAX_STEP, int catJump, int mouseJump, int fr, int fc, int mr,
                          int mc, int cr, int cc, int step,
                          int isMouseTurn, Boolean[][][][][][] memo) {
        // if cat gets mouse or cat gets food or we reach MAX_STEP

        if (grid[0].length() * grid.length * 2 == step) {
            return false;
        }

        if (cr == fr && cc == fc) {
            return false;
        }

        if (cr == mr && cc == mc) {
            return false;
        }

        if (mr == fr && mc == fc) {
            return true;
        }

        if (memo[mr][mc][cr][cc][step][isMouseTurn] != null) {
            return memo[mr][mc][cr][cc][step][isMouseTurn];
        }
        boolean ans = false;

        if (isMouseTurn == 0) {

            for (int[] dir : dirs) {

                for (int jump = 0; jump <= mouseJump; jump++) {
                    int nr = mr + dir[0] * jump;
                    int nc = mc + dir[1] * jump;

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length() ||
                            grid[nr].charAt(nc) == '#') {
                        break;
                    }
                    ans = recur(grid, MAX_STEP, catJump, mouseJump, fr, fc, nr, nc, cr, cc, step + 1, 1, memo);
                    //breaking when mouse wins since they are playing optimally

                    if (ans) {
                        break;
                    }
                }

                if (ans) {
                    break;
                }
            }
        } else {

            for (int[] dir : dirs) {

                for (int jump = 0; jump <= catJump; jump++) {
                    int nr = cr + dir[0] * jump;
                    int nc = cc + dir[1] * jump;

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length() ||
                            grid[nr].charAt(nc) == '#') {
                        break;
                    }
                    //breaking when cat wins since they are playing optimally
                    ans = recur(grid, MAX_STEP, catJump, mouseJump, fr, fc, mr, mc, nr, nc, step + 1, 0, memo);

                    if (!ans) {
                        break;
                    }
                }

                if (!ans) {
                    break;
                }
            }
        }
        return memo[mr][mc][cr][cc][step][isMouseTurn] = ans;
    }
}
