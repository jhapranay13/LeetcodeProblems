package leetcode.hard;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given two integer arrays of the same length nums1 and nums2.
 *         In one operation, you are allowed to swap nums1[i] with nums2[i].
 * 
 *         For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can
 *         swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 =
 *         [5,6,7,8]. Return the minimum number of needed operations to make
 *         nums1 and nums2 strictly increasing. The test cases are generated so
 *         that the given input always makes it possible.
 * 
 *         An array arr is strictly increasing if and only if arr[0] < arr[1] <
 *         arr[2] < ... < arr[arr.length - 1].
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7] Output: 1 Explanation:
 *         Swap nums1[3] and nums2[3]. Then the sequences are: nums1 = [1, 3, 5,
 *         7] and nums2 = [1, 2, 3, 4] which are both strictly increasing.
 * 
 * 
 *         Example 2:
 * 
 *         Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         2 <= nums1.length <= 105 nums2.length == nums1.length 0 <= nums1[i],
 *         nums2[i] <= 2 * 105
 *
 */

public class _801_MinimumSwapsToMakeSequencesIncreasing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minSwap(int[] A, int[] B) {

		if (A == null || B == null || A.length == 1) {
			return 0;
		}
		int memo[][] = new int[2][A.length];

		for (int[] mem : memo) {
			Arrays.fill(mem, -1);
		}
		int min = dfs(A, B, 0, memo, 0);
		return min;
	}
	//Instead of using a[] and b[] as state we can introduce new variable swapped as state
	//Since using a[] and b[] as state and put it in memo in hashMap can cause TLE
	public int dfs(int[] a, int[] b, int indx, int[][] memo, int swapped) {

		if (indx == a.length) {
			return 0;
		}

		if (memo[swapped][indx] > -1) {
			return memo[swapped][indx];
		}
		int temp1 = Integer.MAX_VALUE;
		int temp2 = Integer.MAX_VALUE;

		if (indx == 0) {

			// if( a[ indx + 1] > b[ indx ] && b[ indx + 1 ] > a[ indx ] ) {
			swap(a, b, indx);
			temp1 = 1 + dfs(a, b, indx + 1, memo, 1);
			swap(a, b, indx);
			// }
			// if( a[ indx + 1] >= a[ indx ] && b[ indx + 1 ] >= b[ indx ] ) {
			temp2 = dfs(a, b, indx + 1, memo, 0);
			// }
		} else {

			if (a[indx] > b[indx - 1] && b[indx] > a[indx - 1]) {
				swap(a, b, indx);
				temp1 = 1 + dfs(a, b, indx + 1, memo, 1);
				swap(a, b, indx);
			}

			if (a[indx] > a[indx - 1] && b[indx] > b[indx - 1]) {
				temp2 = dfs(a, b, indx + 1, memo, 0);
			}
		}

		return memo[swapped][indx] = Math.min(temp1, temp2);
	}

	private void swap(int[] A, int[] B, int index) {
		int temp = A[index];
		A[index] = B[index];
		B[index] = temp;
	}
}
