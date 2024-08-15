package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given an m x n char matrix board representing the game board where:
 *
 * 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * 'X' represents a revealed mine.
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
 *
 * Return the board after revealing this position according to the following rules:
 *
 * If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 * If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 * Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * Example 2:
 *
 *
 * Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
 * Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] is either 'M' or 'E'.
 *
 *
 */

public class _529_Minesweeper {

    //DFS Solution
    public char[][] updateBoard(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            board[click[0]][click[1]] = 'B';
            dfs(board, click);
        }
        return board;
    }

    private void dfs(char board[][], int[] pos) {
        int mineCount = findMine(board, pos);

        if (mineCount > 0) {
            board[pos[0]][pos[1]] = (char)(mineCount + '0');
        } else {

            for (int[] dir : dirs) {
                int r = pos[0] + dir[0];
                int c = pos[1] + dir[1];

                if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'E') {
                    board[r][c] = 'B';
                    dfs(board, new int[] {r, c});
                }
            }
        }

    }

    private int findMine(char[][] board, int[] pos) {
        int count = 0;

        for (int[] dir : dirs) {
            int r = pos[0] + dir[0];
            int c = pos[1] + dir[1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {

                if (board[r][c] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }

    private int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
            {-1, -1},
            {1, 1},
            {-1, 1},
            {1, -1}
    };

    //=============================================================================================
    //BFS Solution
    public char[][] updateBoard1(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            board[click[0]][click[1]] = 'B';
            bfs(board, click);
        }
        return board;
    }

    private void bfs(char board[][], int[] click) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(click);

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int mineCount = findMine(board, pos);

            if (mineCount > 0) {
                board[pos[0]][pos[1]] = (char)(mineCount + '0');
            } else {

                for (int[] dir : dirs) {
                    int r = pos[0] + dir[0];
                    int c = pos[1] + dir[1];

                    if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'E') {
                        q.offer(new int[] {r, c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
    }
}
