package leetcode.Sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Pranay Jha
 *
 *         You are driving a vehicle that has capacity empty seats initially
 *         available for passengers. The vehicle only drives east (ie. it cannot
 *         turn around and drive west.)
 * 
 *         Given a list of trips, trip[i] = [num_passengers, start_location,
 *         end_location] contains information about the i-th trip: the number of
 *         passengers that must be picked up, and the locations to pick them up
 *         and drop them off. The locations are given as the number of
 *         kilometers due east from your vehicle's initial location.
 * 
 *         Return true if and only if it is possible to pick up and drop off all
 *         passengers for all the given trips.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: trips = [[2,1,5],[3,3,7]], capacity = 4 Output: false
 * 
 *         Example 2:
 * 
 *         Input: trips = [[2,1,5],[3,3,7]], capacity = 5 Output: true
 * 
 *         Example 3:
 * 
 *         Input: trips = [[2,1,5],[3,5,7]], capacity = 3 Output: true
 * 
 *         Example 4:
 * 
 *         Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11 Output: true
 * 
 * 
 * 
 *         Constraints:
 * 
 *         trips.length <= 1000 trips[i].length == 3 1 <= trips[i][0] <= 100 0
 *         <= trips[i][1] < trips[i][2] <= 1000 1 <= capacity <= 100000
 *
 */

public class _1094_CarPooling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean carPooling(int[][] trips, int capacity) {
		int[][] holder = new int[trips.length * 2][2];
		int index = 0;

		for (int[] trip : trips) {
			holder[index++] = new int[] { trip[1], trip[0] };
			holder[index++] = new int[] { trip[2], -trip[0] };
		}
		Arrays.sort(holder, new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {

				if (x[0] == y[0]) {
					return x[1] - y[1];
				}
				return x[0] - y[0];
			}
		});
		int currentCapacity = 0;

		for (int[] passengerInfo : holder) {
			currentCapacity += passengerInfo[1];

			if (currentCapacity > capacity) {
				return false;
			}
		}
		return true;
	}

}
