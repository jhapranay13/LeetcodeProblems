package leetcode.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a rows x cols binary matrix filled with 0's and 1's, find the
 *         largest rectangle containing only 1's and return its area.
 * 
 * 
 * 
 *         Example 1:
 * 						
 * 				["1","0","1","0","0"],
 * 						 ____________				
 * 				["1","0",|"1","1","1"]| 
 * 				["1","1",|"1","1","1"]|
 *                       -------------
 * 				["1","0","0","1","0"]
 * 
 *         Input: matrix =
 *         [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *         Output: 6 Explanation: The maximal rectangle is shown in the above
 *         picture. 
 *         
 *         Example 2:
 * 
 *         Input: matrix = [] Output: 0 
 *         
 *         Example 3:
 * 
 *         Input: matrix = [["0"]] Output: 0 
 *         
 *         Example 4:
 * 
 *         Input: matrix = [["1"]] Output: 1 
 *         
 *         Example 5:
 * 
 *         Input: matrix = [["0","0"]] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         rows == matrix.length cols == matrix[i].length 0 <= row, cols <= 200
 *         matrix[i][j] is '0' or '1'.
 *
 */

public class _85_MaximalRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maximalRectangle(char[][] matrix) {

		if (matrix.length == 0) {
			return 0;
		}
		int[] prevVal = new int[matrix[0].length];
		int ans = 0;

		for (int i = 0; i < matrix.length; i++) {
			int[] currVal = new int[matrix[0].length];

			for (int j = 0; j < matrix[0].length; j++) {

				if (matrix[i][j] == '1') {
					currVal[j] = prevVal[j] + 1;
				}
			}
			ans = Math.max(ans, largestHistogram(currVal));
			prevVal = currVal;
		}
		return ans;
	}

	private int largestHistogram(int[] val) {
		Deque<Integer> monoStack = new LinkedList<>();
		int max = 0;

		for (int i = 0; i < val.length; i++) {

			if (!monoStack.isEmpty() && val[monoStack.peek()] < val[i]) {
				monoStack.push(i);
			} else {
				while (!monoStack.isEmpty() && val[monoStack.peek()] >= val[i]) {
					int pop = monoStack.pop();
					int leftDistance = 0;

					if (monoStack.isEmpty()) {
						leftDistance = pop + 1;
					} else {
						leftDistance = pop - monoStack.peek();
					}
					int rightDistance = i - pop - 1;
					max = Math.max(max, (leftDistance + rightDistance) * val[pop]);
				}
				monoStack.push(i);
			}
		}

		while (!monoStack.isEmpty()) {
			int index = monoStack.pop();
			int rightDistance = val.length - index - 1;
			int leftDistance = 0;

			if (!monoStack.isEmpty()) {
				leftDistance = index - monoStack.peek();
			} else {
				leftDistance = index + 1;
			}
			max = Math.max(max, (leftDistance + rightDistance) * val[index]);
		}
		return max;
	}
	//=====================================================================
	//Different Version for calculation of distance
	public int maximalRectangle1(char[][] matrix) {

		if( matrix.length == 0 ) {
			return 0;
		}
		int colLength = matrix[ 0 ].length;
		int[] prevHist = new int[ colLength ];
		int max = 0;

		for( int r = 0; r < matrix.length; r++  ) {    
			int[] hist = new int[ colLength ];

			for( int c = 0; c < colLength; c++ ) {

				if( matrix[ r ][ c ] == '1' ) {
					hist[ c ] = prevHist[ c ] + 1;
				}
			}
			max = Math.max( max, maxHist( hist ) );
			prevHist = hist;
		}
		return max;
	}

	private int maxHist( int[] hist ) {

		Deque< Integer > stack = new LinkedList<>();
		stack.push( 0 );
		int max = 0;

		for( int i = 1; i < hist.length; i++ ) {

			if( ! stack.isEmpty() && hist[ stack.peek() ] < hist[ i ] ) {
				stack.push( i );
			} else {

				while( !stack.isEmpty() && hist[ stack.peek() ] >= hist[ i ] ) {
					int index = stack.pop();
					int distance = 0;

					if( stack.isEmpty() ) {
						distance = i;
					} else {
						distance = i - index + ( index - stack.peek() - 1 );
					}
					max = Math.max( max, distance * hist[ index ] );
				}
				int distance = !stack.isEmpty() ? i - stack.peek() : i + 1; 
				max = Math.max( max, distance * hist[ i ] );
				stack.push( i );
			}
		}

		while( !stack.isEmpty() ) {
			int index = stack.pop();
			int distance = 0;

			if( stack.isEmpty() ) {
				distance = hist.length;
			} else {
				distance = index - stack.peek() + ( ( hist.length - 1 ) - index );
			}
			max = Math.max( max, distance * hist[ index ] );
		}
		return max;
	}
}
