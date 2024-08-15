package leetcode.BinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array bloomDay, an integer m and an integer k.
 * 
 *         We need to make m bouquets. To make a bouquet, you need to use k
 *         adjacent flowers from the garden.
 * 
 *         The garden consists of n flowers, the ith flower will bloom in the
 *         bloomDay[i] and then can be used in exactly one bouquet.
 * 
 *         Return the minimum number of days you need to wait to be able to make
 *         m bouquets from the garden. If it is impossible to make m bouquets
 *         return -1.
 * 
 *         Example 1:
 * 
 *         Input: bloomDay = [1,10,3,10,2], m = 3, k = 1 Output: 3 Explanation:
 *         Let's see what happened in the first three days. x means flower
 *         bloomed and _ means flower didn't bloom in the garden. We need 3
 *         bouquets each should contain 1 flower. After day 1: [x, _, _, _, _]
 *         // we can only make one bouquet. After day 2: [x, _, _, _, x] // we
 *         can only make two bouquets. After day 3: [x, _, x, _, x] // we can
 *         make 3 bouquets. The answer is 3.
 * 
 *         Example 2:
 * 
 *         Input: bloomDay = [1,10,3,10,2], m = 3, k = 2 Output: -1 Explanation:
 *         We need 3 bouquets each has 2 flowers, that means we need 6 flowers.
 *         We only have 5 flowers so it is impossible to get the needed bouquets
 *         and we return -1.
 * 
 *         Example 3:
 * 
 *         Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3 Output: 12
 *         Explanation: We need 2 bouquets each should have 3 flowers. Here's
 *         the garden after the 7 and 12 days: After day 7: [x, x, x, x, _, x,
 *         x] We can make one bouquet of the first three flowers that bloomed.
 *         We cannot make another bouquet from the last three flowers that
 *         bloomed because they are not adjacent. After day 12: [x, x, x, x, x,
 *         x, x] It is obvious that we can make two bouquets in different ways.
 * 
 *         Example 4:
 * 
 *         Input: bloomDay = [1000000000,1000000000], m = 1, k = 1 Output:
 *         1000000000 Explanation: You need to wait 1000000000 days to have a
 *         flower ready for a bouquet.
 * 
 *         Example 5:
 * 
 *         Input: bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2 Output: 9
 * 
 * 
 *         Constraints:
 * 
 *         bloomDay.length == n 1 <= n <= 10^5 1 <= bloomDay[i] <= 10^9 1 <= m
 *         <= 10^6 1 <= k <= n
 *
 */

public class _1482_MinimumNumberOfWaysToMakeMBouquets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ==============================================================================
	public int minDays(int[] bloomDay, int m, int k) {
		int lo = 1;
		int hi = 1;

		for (int day : bloomDay) {
			hi = Math.max(hi, day);
		}

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (canMakeMBouquetsWithKFlowers(bloomDay, m, k, pivot)) {
				hi = pivot;
			} else {
				lo = pivot + 1;
			}
		}
		return canMakeMBouquetsWithKFlowers(bloomDay, m, k, hi) ? hi : -1;
	}

	private boolean canMakeMBouquetsWithKFlowers(int[] bloomDay, int m, int k, int day) {
		int numberOfFlowers = 0;
		int numberOfBouquets = 0;

		for (int i = 0; i < bloomDay.length; i++) {
			// logic to check if adjacent can make bouquets
			if (bloomDay[i] <= day) {
				numberOfFlowers++;
			} else {
				numberOfFlowers = 0;
			}

			if (numberOfFlowers == k) {
				numberOfBouquets += numberOfFlowers / k;
				numberOfFlowers = 0;
			}
		}
		return m <= numberOfBouquets;
	}

	// =============================================================================
	// Another version
	public int minDays1(int[] bloomDay, int m, int k) {

		if (m * k > bloomDay.length) {
			return -1;
		}
		int lo = 1;
		int hi = Integer.MIN_VALUE;

		for (int b : bloomDay) {
			hi = Math.max(hi, b);
		}

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (canMake(bloomDay, m, k, pivot)) {
				hi = pivot;
			} else {
				lo = pivot + 1;
			}
		}
		return canMake(bloomDay, m, k, lo) ? lo : -1;
	}

	private boolean canMake(int[] arr, int m, int k, int p) {
		int count = 0;
		int lo = 0;
		int hi = 0;

		for (; hi < arr.length; hi++) {

			if (arr[hi] > p) {
				count += (hi - lo) / (k);
				lo = hi + 1;
			}
		}
		count += (hi - lo) / k;
		return count >= m;
	}
}
