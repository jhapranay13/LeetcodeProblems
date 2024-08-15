package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         The n-queens puzzle is the problem of placing n queens on an n x n
 *         chessboard such that no two queens attack each other.
 * 
 *         Given an integer n, return the number of distinct solutions to the
 *         n-queens puzzle.
 * 
 *         Example 1:
 * 
 *         Input: n = 4 Output: 2 Explanation: There are two distinct solutions
 *         to the 4-queens puzzle as shown.
 * 
 *         Example 2:
 * 
 *         Input: n = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 9
 *
 */

public class _52_NQueens2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int totalNQueens(int n) {
		int[] pos = new int[n];
		return rec(n, pos, 0);
	}

	private int rec(int n, int[] pos, int curr) {

		if (n == curr) {
			return 1;
		}
		int ans = 0;

		for (int i = 0; i < n; i++) {

			if (curr != 0) {
				boolean flag = true;
				int left = i - 1;
				int right = i + 1;

				for (int j = curr - 1; j >= 0; j--) {

					if (pos[j] == i || pos[j] == left || pos[j] == right) {
						flag = false;
						break;
					}
					left--;
					right++;
				}

				if (flag) {
					pos[curr] = i;
					ans += rec(n, pos, curr + 1);
				}
			} else {
				pos[curr] = i;
				ans += rec(n, pos, curr + 1);
			}
		}
		return ans;
	}
}
