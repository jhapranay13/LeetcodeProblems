package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 *
 */

public class _56_MergeIntervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals , new Comparator<int[]>() {
			public int compare(int[] i1, int[] i2) {
				return i1[0] - i2[0];
			}
		});
		List<int[]> accumlator = new ArrayList<>();
		int startTime = intervals[0][0];
		int endTime = intervals[0][1];

		for (int i = 1; i < intervals.length; i++) {
			int nextStartTime = intervals[i][0];
			int nextEndTime = intervals[i][1];

			if (nextEndTime < endTime) {
				continue;
			}

			if (nextStartTime <= endTime) {
				endTime = nextEndTime;
			}

			if (nextStartTime > endTime) {
				accumlator.add(new int[] {startTime, endTime});
				startTime = nextStartTime;
				endTime = nextEndTime;
			}
		}
		accumlator.add(new int[] {startTime, endTime});
		return accumlator.toArray(new int[accumlator.size()][]);
	}
	//======================================================================================
	public int[][] merge1(int[][] intervals) {

		if( intervals == null || intervals.length == 0 ) {
			return new int[][]{};
		}
		Arrays.sort( intervals, new Comparator< int[] >() {

			public int compare( int[] i1, int[] i2 ) {
				return i1[ 0 ] - i2[ 0 ];
			}
		} );
		ArrayList< int[] > ans = new ArrayList<>();
		int prevStart = intervals[ 0 ][ 0 ];
		int prevEnd = intervals[ 0 ][ 1 ];

		for( int i = 1; i < intervals.length; i++ ) {
			int[] curr = intervals[ i ];

			if( curr[ 0 ] <= prevEnd ) {

				if( curr[ 1 ] >= prevEnd ) {
					prevEnd = curr[ 1 ];
				}
			} else {
				ans.add( new int[] { prevStart, prevEnd } );
				prevStart = curr[ 0 ];
				prevEnd = curr[ 1 ];
			}
		}
		ans.add( new int[] { prevStart, prevEnd } );
		return ans.toArray( new int[ ans.size() ][] );
	}
}
