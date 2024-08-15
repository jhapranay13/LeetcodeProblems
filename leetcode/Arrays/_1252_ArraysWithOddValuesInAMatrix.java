package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         There is an m x n matrix that is initialized to all 0's. There is
 *         also a 2D array indices where each indices[i] = [ri, ci] represents a
 *         0-indexed location to perform some increment operations on the
 *         matrix.
 * 
 *         For each location indices[i], do both of the following:
 * 
 *         Increment all the cells on row ri. Increment all the cells on column
 *         ci. Given m, n, and indices, return the number of odd-valued cells in
 *         the matrix after applying the increment to all locations in indices.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: m = 2, n = 3, indices = [[0,1],[1,1]] Output: 6 Explanation:
 *         Initial matrix = [[0,0,0],[0,0,0]]. After applying first increment it
 *         becomes [[1,2,1],[0,1,0]]. The final matrix is [[1,3,1],[1,3,1]],
 *         which contains 6 odd numbers.
 * 
 *         Example 2:
 * 
 * 
 *         Input: m = 2, n = 2, indices = [[1,1],[0,0]] Output: 0 Explanation:
 *         Final matrix = [[2,2],[2,2]]. There are no odd numbers in the final
 *         matrix.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= m, n <= 50 1 <= indices.length <= 100 0 <= ri < m 0 <= ci < n
 * 
 * 
 *         Follow up: Could you solve this in O(n + m + indices.length) time
 *         with only O(n + m) extra space?
 *
 */

public class _1252_ArraysWithOddValuesInAMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int oddCells(int n, int m, int[][] indices) {
		int[] rows = new int[ n ];
		int[] cols = new int[ m ];

		for( int [] ind : indices ) {
			rows[ ind[ 0 ] ]++;
			cols[ ind[ 1 ] ]++;
		}
		int oddRowCnt = 0;
		int oddColCnt = 0;

		for( int r : rows ) {

			if( r % 2 != 0) {
				oddRowCnt++;;
			}
		}

		for( int c : cols ) {

			if( c % 2 != 0 ) {
				oddColCnt++;
			}
		}
		int evenRowCnt = n - oddRowCnt;
		int evenColCnt = m - oddColCnt;
		//in the pattern we can see that if row is odd. Column is even. if column is even row is odd.
		// The final result will be odd only when we increment either row or col odd no of times and even no of times respectively
		// Here we consider sum of row_odd * col_even and col_even * row_odd
		// This is because when a place is incremented odd no of times by either row or col and even no of times by the other,
		// then only the resulting value will be odd.
		// If both the row and col are incremented odd no of times, they cancel out each other and the resulting value will be even
		// If both the row and col are incremented even no of times, they cancel out themselves and can never result in the odd result
		int oddCount = oddRowCnt * evenColCnt + evenRowCnt * oddColCnt;
		return  oddCount;
	}
//=================================================================================================

	public int oddCells1(int n, int m, int[][] indices) {
		int oddCount = 0;
		boolean[] rows = new boolean[n];
		boolean[] cols = new boolean[m];

		for (int[] ind : indices) {
			rows[ind[0]] = !rows[ind[0]];
			cols[ind[1]] = !cols[ind[1]];
		}
		int cnt = 0;

		for (boolean r : rows) {

			if (r) {
				oddCount += m;
				cnt++;
			}
		}

		for (boolean c : cols) {

			if (c) {
				oddCount += (n - cnt * 2);
			}
		}
		return oddCount;
	}
}
