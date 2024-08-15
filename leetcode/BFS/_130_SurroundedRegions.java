package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.



Example 1:

		["X","X","X","X"]             ["X","X","X","X"]
		["X","O","O","X"]  ------\    ["X","X","X","X"]
		["X","X","O","X"]  ------/    ["X","X","X","X"]
		["X","O","X","X"]             ["X","O","X","X"]


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
Example 2:

Input: board = [["X"]]
Output: [["X"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 *
 */

public class _130_SurroundedRegions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void solve(char[][] board) {
		Deque<int[]> queue = new LinkedList<>();

		for (int i = 0; i < board[0].length; i++) {

			if (board[0][i] == 'O') {
				queue.offer(new int[] {0, i});
			}

			if (board[board.length - 1][i] == 'O') {
				queue.offer(new int[] {board.length - 1, i});
			}
		}

		for (int i = 0; i < board.length; i++) {

			if (board[i][0] == 'O') {
				queue.offer(new int[] {i, 0});
			}

			if (board[i][board[0].length - 1] == 'O') {
				queue.offer(new int[] {i, board[0].length - 1});
			}
		}
		int[][] cache = new int[board.length][board[0].length];

		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			cache[pos[0]][pos[1]] = 1;

			for (int[] dir : dirs) {
				int nr = pos[0] + dir[0];
				int nc = pos[1] + dir[1];

				if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length &&
						board[nr][nc] == 'O' && cache[nr][nc] != 1) {
					queue.offer(new int[] {nr, nc});
				}
			}
		}

		for (int i = 0; i < cache.length; i++) {

			for (int j = 0; j < cache[0].length; j++) {
				if (cache[i][j] == 0) {
					board[i][j] = 'X';
				}
			}
		}
	}

	private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
