package leetcode.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         ou are given an array of points in the X-Y plane points where
 *         points[i] = [xi, yi].
 * 
 *         Return the minimum area of a rectangle formed from these points, with
 *         sides parallel to the X and Y axes. If there is not any such
 *         rectangle, return 0.
 * 
 *
 *         Example 1:
 * 
 * 
 *         Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]] Output: 4
 * 
 *         Example 2:
 * 
 * 
 *         Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= points.length <= 500 points[i].length == 2 0 <= xi, yi <= 4 *
 *         104 All the given points are unique.
 *
 */

public class _939_MinimumAreaRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Checking for Diagonal and opposite points.
	public int minAreaRect(int[][] points) {
		Map<Integer, Set<Integer>> pointConnectMap = new HashMap<>();

		for (int i = 0; i < points.length; i++) {
			int[] point = points[i];
			Set<Integer> connectionSet = pointConnectMap.getOrDefault(point[0], new HashSet<>());
			connectionSet.add(point[1]);
			pointConnectMap.put(point[0], connectionSet);
		}
		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < points.length; i++) {
			int[] pointA = points[i];

			for (int j = i + 1; j < points.length; j++) {
				int[] pointB = points[j];
				// considering that two points lie in the diagonal
				if (pointA[0] != pointB[0] && pointA[1] != pointB[1]) {
					// checking opposite sides.
					if (pointConnectMap.get(pointA[0]).contains(pointB[1])
							&& pointConnectMap.get(pointB[0]).contains(pointA[1])) {
						ans = Math.min(ans, Math.abs(pointA[0] - pointB[0]) * Math.abs(pointA[1] - pointB[1]));
					}
				}
			}
		}
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}

	// ======================================================================
	// Making lines. TLE
	public int minAreaRect1(int[][] points) {
		int n = points.length;
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < n; i++)
			set.add(points[i][0] + " " + points[i][1]);

		int area = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (points[i][0] == points[j][0] || points[i][1] == points[j][1])
					continue;
				String point1 = points[i][0] + " " + points[j][1];
				String point2 = points[j][0] + " " + points[i][1];
				if (set.contains(point1) && set.contains(point2)) {
					int temp = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]);
					area = Math.min(area, temp);
				}
			}
		}
		if (area == Integer.MAX_VALUE)
			return 0;
		else
			return area;
	}
}
