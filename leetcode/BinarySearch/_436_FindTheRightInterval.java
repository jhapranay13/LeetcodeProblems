package leetcode.BinarySearch;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of intervals, where intervals[i] = [starti,
 *         endi] and each starti is unique.
 * 
 *         The right interval for an interval i is an interval j such that
 *         startj >= endi and startj is minimized.
 * 
 *         Return an array of right interval indices for each interval i. If no
 *         right interval exists for interval i, then put -1 at index i.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: intervals = [[1,2]] Output: [-1] Explanation: There is only
 *         one interval in the collection, so it outputs -1. Example 2:
 * 
 *         Input: intervals = [[3,4],[2,3],[1,2]] Output: [-1,0,1] Explanation:
 *         There is no right interval for [3,4]. The right interval for [2,3] is
 *         [3,4] since start0 = 3 is the smallest start that is >= end1 = 3. The
 *         right interval for [1,2] is [2,3] since start1 = 2 is the smallest
 *         start that is >= end2 = 2. Example 3:
 * 
 *         Input: intervals = [[1,4],[2,3],[3,4]] Output: [-1,2,-1] Explanation:
 *         There is no right interval for [1,4] and [3,4]. The right interval
 *         for [2,3] is [3,4] since start2 = 3 is the smallest start that is >=
 *         end1 = 3.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= intervals.length <= 2 * 104 intervals[i].length == 2 -106 <=
 *         starti <= endi <= 106 The start point of each interval is unique.
 *
 */

public class _436_FindTheRightInterval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] findRightInterval(int[][] intervals) {
		Map<int[], Integer> posMap = new HashMap<>();
		int position = 0;

		for (int[] interval : intervals) {
			posMap.put(interval, position++);
		}
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			}
		});
		int[] ans = new int[intervals.length];

		for (int[] interval : intervals) {
			int index = binarySearch(intervals, interval[1]);

			if (index == -1) {
				ans[posMap.get(interval)] = -1;
			} else {
				ans[posMap.get(interval)] = posMap.get(intervals[index]);
			}
		}
		return ans;
	}

	private int binarySearch(int[][] interval, int target) {
		int lo = 0;
		int hi = interval.length - 1;

		while (lo <= hi) {

			if (hi - lo <= 0) {

				if (interval[hi][0] >= target) {
					return hi;
				}
			}
			int pivot = lo + (hi - lo) / 2;

			if (interval[pivot][0] == target) {
				return pivot;
			} else if (interval[pivot][0] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return -1;
	}
	//=============================================================================================
	//Treemap Solution
	public int[] findRightInterval1(int[][] intervals) {
		TreeMap<Integer, Integer> intervalHolder = new TreeMap<>();
		int index = 0;

		for (int[] interval : intervals) {
			intervalHolder.put(interval[0], index++);
		}
		int[] ans = new int[intervals.length];
		Arrays.fill(ans, -1);
		index = 0;

		for (int[] interval : intervals) {
			int end = interval[1];
			Map.Entry<Integer,Integer> entry = intervalHolder.ceilingEntry(end);

			if (entry != null) {
				ans[index] = entry.getValue();
			}
			index++;
		}
		return ans;
	}
}
