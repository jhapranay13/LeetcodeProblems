package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         The n-queens puzzle is the problem of placing n queens on an n x n
 *         chessboard such that no two queens attack each other.
 * 
 *         Given an integer n, return all distinct solutions to the n-queens
 *         puzzle. You may return the answer in any order.
 * 
 *         Each solution contains a distinct board configuration of the
 *         n-queens' placement, where 'Q' and '.' both indicate a queen and an
 *         empty space, respectively.
 * 
 *         Example 1:
 * 
 * 
 *         Input: n = 4 Output:
 *         [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *         Explanation: There exist two distinct solutions to the 4-queens
 *         puzzle as shown above
 * 
 *         Example 2:
 * 
 *         Input: n = 1 Output: [["Q"]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 9
 *
 */

public class _51_NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ans = new ArrayList<>();
		int[] pos = new int[n];
		rec(n, pos, ans, 0);
		return ans;
	}

	private void rec(int n, int[] pos, List<List<String>> ans, int row) {

		if (row == n) {
			List<String> p = new ArrayList<>();
			StringBuilder line = new StringBuilder();

			for (int i = 0; i < n; i++) {
				line.append(".");
			}

			for (int po : pos) {
				line.replace(po, po + 1, "Q");
				p.add(line.toString());
				line.replace(po, po + 1, ".");
			}
			ans.add(p);
		}

		for (int i = 0; i < n; i++) {

			if (row == 0) {
				pos[row] = i;
				rec(n, pos, ans, row + 1);
			} else {
				int left = i - 1;
				int right = i + 1;
				boolean flag = true;

				for (int j = row - 1; j >= 0; j--) {

					if (left == pos[j] || right == pos[j] || i == pos[j]) {
						flag = false;
						break;
					}
					left--;
					right++;
				}

				if (flag) {
					pos[row] = i;
					rec(n, pos, ans, row + 1);
				}
			}
		}
	}
}
