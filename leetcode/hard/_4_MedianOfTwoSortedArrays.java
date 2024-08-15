package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two sorted arrays nums1 and nums2 of size m and n respectively,
 *         return the median of the two sorted arrays.
 * 
 *         The overall run time complexity should be O(log (m+n)).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums1 = [1,3], nums2 = [2] Output: 2.00000 Explanation: merged
 *         array = [1,2,3] and median is 2.
 * 
 *         Example 2:
 * 
 *         Input: nums1 = [1,2], nums2 = [3,4] Output: 2.50000 Explanation:
 *         merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 *         Example 3:
 * 
 *         Input: nums1 = [0,0], nums2 = [0,0] Output: 0.00000
 * 
 *         Example 4:
 * 
 *         Input: nums1 = [], nums2 = [1] Output: 1.00000
 * 
 *         Example 5:
 * 
 *         Input: nums1 = [2], nums2 = [] Output: 2.00000
 * 
 * 
 *         Constraints:
 * 
 *         nums1.length == m nums2.length == n 0 <= m <= 1000 0 <= n <= 1000 1
 *         <= m + n <= 2000 -106 <= nums1[i], nums2[i] <= 106
 *
 */

public class _4_MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int num1Length = nums1.length;
		int num2Length = nums2.length;
		// making num1 as array with smaller size to be consistent
		if (num1Length > num2Length) {
			return findMedianSortedArrays(nums2, nums1);
		}
		int lo = 0;
		int hi = nums1.length;

		while (lo <= hi) {
			int pivotA = (lo + hi) / 2;
			int pivotB = (num1Length + num2Length + 1) / 2 - pivotA;

			int sLeft = pivotA == 0 ? Integer.MIN_VALUE : nums1[pivotA - 1];
			int sRight = pivotA == nums1.length ? Integer.MAX_VALUE : nums1[pivotA];
			int lLeft = pivotB == 0 ? Integer.MIN_VALUE : nums2[pivotB - 1];
			int lRight = pivotB == nums2.length ? Integer.MAX_VALUE : nums2[pivotB];

			if (sLeft <= lRight && lLeft <= sRight) {
				if ((num1Length + num2Length) % 2 == 0) {
					return (double) (Math.max(sLeft, lLeft) + Math.min(sRight, lRight)) / 2;
				} else {
					return (double) Math.max(sLeft, lLeft);
				}
			} else if (sLeft > lRight) {
				hi = pivotA - 1;
			} else {
				lo = pivotA + 1;
			}
		}
		return 0.0;
	}

	// ===========================================================================
	// Another Version
	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		int[] small = nums1.length < nums2.length ? nums1 : nums2;
		int[] large = nums1.length < nums2.length ? nums2 : nums1;

		int lo = 0;
		int hi = small.length;

		while (lo <= hi) {
			int spivot = (lo + hi) / 2;
			int lpivot = (small.length + large.length + 1) / 2 - spivot;

			int sleft = spivot == 0 ? Integer.MIN_VALUE : small[spivot - 1];
			int sright = spivot == small.length ? Integer.MAX_VALUE : small[spivot];
			int lleft = lpivot == 0 ? Integer.MIN_VALUE : large[lpivot - 1];
			int lright = lpivot == large.length ? Integer.MAX_VALUE : large[lpivot];

			if (sleft <= lright && lleft <= sright) {

				if ((small.length + large.length) % 2 == 0) {
					return (double) (Math.max(sleft, lleft) + Math.min(sright, lright)) / 2;
				} else {
					return Math.max(sleft, lleft);
				}
			} else if (sleft > lright) {
				hi = spivot - 1;
			} else {
				lo = spivot + 1;
			}
		}
		return 0.0;
	}
}
