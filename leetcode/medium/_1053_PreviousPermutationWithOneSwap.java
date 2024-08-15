package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of positive integers arr (not necessarily distinct),
 *         return the lexicographically largest permutation that is smaller than
 *         arr, that can be made with exactly one swap (A swap exchanges the
 *         positions of two numbers arr[i] and arr[j]). If it cannot be done,
 *         then return the same array.
 * 
 *         Example 1:
 * 
 *         Input: arr = [3,2,1] Output: [3,1,2] Explanation: Swapping 2 and 1.
 * 
 *         Example 2:
 * 
 *         Input: arr = [1,1,5] Output: [1,1,5] Explanation: This is already the
 *         smallest permutation.
 * 
 *         Example 3:
 * 
 *         Input: arr = [1,9,4,6,7] Output: [1,7,4,6,9] Explanation: Swapping 9
 *         and 7.
 * 
 *         Example 4:
 * 
 *         Input: arr = [3,1,1,3] Output: [1,3,1,3] Explanation: Swapping 1 and
 *         3.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 104 1 <= arr[i] <= 104
 *
 */

public class _1053_PreviousPermutationWithOneSwap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] prevPermOpt1(int[] arr) {

		for (int i = arr.length - 1; i > 0; i--) {

			if (arr[i] < arr[i - 1]) {
				int index = binarySearchLessThan(arr, i, arr[i - 1]);
				swap(arr, i - 1, index);
				break;
			}
		}
		return arr;
	}

	private void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	private int binarySearchLessThan(int[] arr, int lo, int val) {

		int hi = arr.length - 1;

		if (lo == hi) {
			return lo;
		}

		while (lo < hi) {

			if (hi - lo == 1) {
				if (arr[hi] < val) {
					lo = hi;
				}
				break;
			}
			int pivot = lo + (hi - lo) / 2;

			if (arr[pivot] < val) {
				lo = pivot;
			} else {
				hi = pivot - 1;
			}
		}

		while (lo > 0 && arr[lo] == arr[lo - 1]) {
			lo--;
		}
		return lo;
	}

	// =============================================================================
	// Another version or a simpler version of Binary Search Just less than
	private int binarySearchLessThan1(int[] arr, int lo, int val) {

		int hi = arr.length - 1;
		int ans = -1;

		if (lo == hi) {
			return lo;
		}

		while (lo <= hi) {

			int pivot = lo + (hi - lo) / 2;

			if (arr[pivot] < val) {
				lo = pivot + 1;
				ans = pivot;
			} else {
				hi = pivot - 1;
			}
		}
		while (ans > 0 && arr[ans] == arr[ans - 1]) {
			ans--;
		}
		return ans;
	}
}
