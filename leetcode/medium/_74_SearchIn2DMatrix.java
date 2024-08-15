package leetcode.medium;


/**
 * 
 * @author Pranay Jha
 *
 *Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 
Example 1:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 
Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 *
 */


public class _74_SearchIn2DMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean searchMatrix(int[][] matrix, int target) {
		int colNum = matrix[0].length;
		int rowNum = matrix.length;
		int lo = 0;
		int hi = colNum * rowNum - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;
			int row = pivot / colNum;
			int col = pivot % colNum;

			if (matrix[row][col] == target) {
				return true;
			} else if (matrix[row][col] < target) {
				lo += 1;
			} else {
				hi -= 1;
			}
		}
		return false;
	}
	//==========================================================================================
	//Another way of Binary Search

	public boolean searchMatrix1(int[][] matrix, int target) {

		if( matrix == null || matrix.length == 0 || matrix[ 0 ].length == 0 ) {
			return false;
		}
		int row = rowSearch( matrix, target );

		if( row != -1 ) {
			int col = colSearch( matrix, target, row );

			if( col != -1 ) {
				return true;
			}
		}
		return false;
	}

	private int colSearch( int[][] m, int t, int r ) {
		int lo = 0;
		int hi = m[ 0 ].length - 1;

		while( lo <= hi ) {
			int p = lo + ( hi - lo ) / 2;

			if( m[ r ][ p ] == t ) {
				return p;
			} else if( m[ r ][ p ] > t ) {
				hi = p - 1;
			} else {
				lo = p + 1;
			}
		}
		return -1;
	}

	private int rowSearch( int[][] m, int t ) {
		int lo = 0;
		int hi = m.length - 1;
		int last = m[ 0 ].length - 1;

		while( lo <= hi ) {
			int p = lo + ( hi - lo ) / 2;

			if( m[ p ][ 0 ] <= t && m[ p ][ last ] >= t ) {
				return p;
			} else if( m[ p ][ 0 ] > t ) {
				hi = p - 1;
			} else {
				lo  = p + 1;
			}
		}
		return -1;
	}

}
