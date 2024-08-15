package leetcode.TreeSetTreeMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of meeting time intervals intervals where intervals[i]
 *         = [starti, endi], return the minimum number of conference rooms
 *         required.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: intervals = [[0,30],[5,10],[15,20]] Output: 2 
 *         
 *         Example 2:
 * 
 *         Input: intervals = [[7,10],[2,4]] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= intervals.length <= 104 0 <= starti < endi <= 106
 *
 */

public class _253_MeetingRooms2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			} 
		});
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[1] - arr2[1];
			} 
		});

		for (int[] interval : intervals) {
			if (pq.isEmpty()) {
				pq.offer(interval);
				continue;
			}

			if (pq.peek()[1] <= interval[0]) {
				pq.poll();
			}
			pq.offer(interval);
		}
		return pq.size();
	}
	//=============================================================================
	//Normal Sorting
	public int minMeetingRooms1(int[][] intervals) {

		if( intervals == null || intervals.length == 0 ) {
			return 0;
		}
		int minimumRooms = 0;
		int[] startTime = new int[ intervals.length ];
		int[] endTime = new int[ intervals.length ];

		for( int i = 0; i < intervals.length; i++ ) {
			startTime[ i ] = intervals[ i ][ 0 ];
			endTime[ i ] = intervals[ i ][ 1 ];
		}
		Arrays.sort( startTime );
		Arrays.sort( endTime );

		int startPointer = 0;
		int endPointer = 0;

		for( int i = 0; i < startTime.length; i++ ) {

			if( startTime[ startPointer ] >= endTime[ endPointer ] ) {
				minimumRooms--;
				endPointer++;
			}
			minimumRooms++;
			startPointer++;
		}
		return minimumRooms;
	}
	//===========================================================================
	//Another Approach
	public int minMeetingRooms3(int[][] intervals) {
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				return x[1] - y[1];
			}
		});
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				if (x[0] == y[0]) {
					return y[1]  - x[1];
				}
				return x[0] - y[0];
			}
		});
		int ans = 0;

		for (int[] interval : intervals) {

			while (!q.isEmpty() && q.peek()[1] <= interval[0]) {
				q.poll();
			}
			q.offer(interval);
			ans = Math.max(ans, q.size());
		}
		return ans;
	}
	//=============================================================================================
	//TreeMap Solution
	public int minMeetingRooms4(int[][] intervals) {
		TreeMap<Integer, Integer> lineSweep = new TreeMap<>();

		for (int[] interval : intervals) {
			lineSweep.put(interval[0], lineSweep.getOrDefault(interval[0], 0) + 1);
			lineSweep.put(interval[1], lineSweep.getOrDefault(interval[1], 0) - 1);
		}
		int ans = 0;
		int temp = 0;

		for (int key : lineSweep.keySet()) {
			temp += lineSweep.get(key);
			ans = Math.max(temp, ans);
		}
		return ans;
	}
}
