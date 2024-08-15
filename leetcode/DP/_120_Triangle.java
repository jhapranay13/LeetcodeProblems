package leetcode.DP;

import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Example 2:

Input: triangle = [[-10]]
Output: -10


Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104


Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */

public class _120_Triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//==============================================================================
	//Top Down
	public int minimumTotal(List<List<Integer>> triangle) {
		int memo[][] = new int[triangle.size()][];

		for (int i = 0; i < triangle.size(); i++) {
			memo[i] = new int[triangle.get( i ).size()];
		}
		return recur(triangle, 0, 0, memo);
	}

	private int recur(List<List<Integer>> triangle, int row, int col, int[][] memo) {

		if (row >= triangle.size()) {
			return 0;
		}

		if (memo[row][col] > 0) {
			return memo[row][col];
		}
		int val = triangle.get(row).get(col);
		int sameCol = recur(triangle, row + 1, col, memo);
		int diffCol = recur(triangle, row + 1, col + 1, memo);
		return memo[row][col] = val + Math.min(sameCol, diffCol);
	}
	//================================================================================
	//Bottom up
	public int minimumTotal1(List<List<Integer>> triangle) {
        int memo[][] = new int[triangle.size() + 1][];
        
        for (int i = 0; i <= triangle.size(); i++) {
            
            if (i ==  triangle.size()) {
                memo[i] = new int[triangle.get(i - 1).size() + 1];
                continue;
            }
            memo[i] = new int[triangle.get(i).size()];
        }
        
        for (int row = triangle.size() - 1; row >= 0; row--) {
            
            for (int col = triangle.get(row).size() - 1; col >= 0; col--) {
                int val = triangle.get(row).get(col);
                int sameCol = memo[row + 1][col];
                int diffCol = memo[row + 1][col + 1];
                memo[row][col] = val + Math.min(sameCol, diffCol);        
            }
        }
        return memo[0][0];
    }

}
