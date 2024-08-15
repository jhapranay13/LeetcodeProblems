package leetcode.Sorting;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums of non-negative integers, return an array
 *         consisting of all the even elements of nums, followed by all the odd
 *         elements of nums.
 * 
 *         You may return any answer array that satisfies this condition.
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,1,2,4] Output: [2,4,3,1] The outputs [4,2,3,1],
 *         [2,4,1,3], and [4,2,1,3] would also be accepted.
 * 
 * 
 *         Note:
 * 
 *         1 <= nums.length <= 5000 0 <= nums[i] <= 5000
 *
 */

public class _905_SortArrayByParity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public int[] sortArrayByParity(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		int curr = 0;

		while (curr <= hi) {
			int num = nums[curr];

			if (num % 2 == 0) {
				int temp = nums[curr];
				nums[curr++] = nums[lo];
				nums[lo++] = temp;
			} else {
				int temp = nums[curr];
				nums[curr] = nums[hi];
				nums[hi--] = temp;
			}
		}
		return nums;
	}

	// ==============================================================================
	// Different Version
	public int[] sortArrayByParity1(int[] A) {
		int lo = 0;
		int hi = A.length - 1;

		for (int i = 0; i < hi; i++) {

			if (A[i] % 2 != 0) {
				swap(A, i, hi--);
				i--;
			}
		}
		return A;
	}

	private void swap(int[] a, int from, int to) {
		int temp = a[from];
		a[from] = a[to];
		a[to] = temp;
	}
}
