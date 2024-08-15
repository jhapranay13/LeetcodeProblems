package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         A conveyor belt has packages that must be shipped from one port to
 *         another within days days.
 * 
 *         The ith package on the conveyor belt has a weight of weights[i]. Each
 *         day, we load the ship with packages on the conveyor belt (in the
 *         order given by weights). We may not load more weight than the maximum
 *         weight capacity of the ship.
 * 
 *         Return the least weight capacity of the ship that will result in all
 *         the packages on the conveyor belt being shipped within days days.
 * 
 *         Example 1:
 * 
 *         Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5 Output: 15
 *         Explanation: A ship capacity of 15 is the minimum to ship all the
 *         packages in 5 days like this: 1st day: 1, 2, 3, 4, 5 2nd day: 6, 7
 *         3rd day: 8 4th day: 9 5th day: 10
 * 
 *         Note that the cargo must be shipped in the order given, so using a
 *         ship of capacity 14 and splitting the packages into parts like (2, 3,
 *         4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * 
 *         Example 2:
 * 
 *         Input: weights = [3,2,2,4,1,4], days = 3 Output: 6 Explanation: A
 *         ship capacity of 6 is the minimum to ship all the packages in 3 days
 *         like this: 1st day: 3, 2 2nd day: 2, 4 3rd day: 1, 4
 * 
 *         Example 3:
 * 
 *         Input: weights = [1,2,3,1,1], days = 4 Output: 3 Explanation: 1st
 *         day: 1 2nd day: 2 3rd day: 3 4th day: 1, 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= days <= weights.length <= 5 * 104 1 <= weights[i] <= 500
 *
 */

public class _1011_CapacityToShipPackagesWithinDDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int shipWithinDays(int[] weights, int days) {
		int maxWeight = Integer.MIN_VALUE;
		int totalWeight = 0;

		for (int weight : weights) {
			totalWeight += weight;
			maxWeight = Math.max(maxWeight, weight);
		}
		int lo = maxWeight;
		int hi = totalWeight;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (canBeShipped(weights, days, pivot)) {
				hi = pivot;
			} else {
				lo = pivot + 1;
			}
		}
		return hi;
	}

	private boolean canBeShipped(int[] weights, int days, int capacity) {
		int total = 0;
		int numDay = 0;

		for (int i = 0; i < weights.length; i++) {
			total += weights[i];

			if (total > capacity) {
				numDay++;
				total = weights[i];
			}
		}
		numDay++;
		return numDay <= days;
	}
}
