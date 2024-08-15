package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array arr and a target value target, return the
 *         integer value such that when we change all the integers larger than
 *         value in the given array to be equal to value, the sum of the array
 *         gets as close as possible (in absolute difference) to target.
 * 
 *         In case of a tie, return the minimum such integer.
 * 
 *         Notice that the answer is not neccesarilly a number from arr.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [4,9,3], target = 10 Output: 3 Explanation: When using 3
 *         arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
 * 
 *         Example 2:
 * 
 *         Input: arr = [2,3,5], target = 10 Output: 5
 * 
 *         Example 3:
 * 
 *         Input: arr = [60864,25176,27249,21296,20204], target = 56803 Output:
 *         11361
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 104 1 <= arr[i], target <= 105
 *
 */

public class _1300_SumOfMutatedArrayClosestToTheTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findBestValue(int[] arr, int target) {
		int lo = 0;
		int hi = Integer.MIN_VALUE;

		for (int num : arr) {
			lo = Math.min(lo, num);
			hi = Math.max(hi, num);
		}
		int closest = Integer.MAX_VALUE;
		int ans = 0;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;
			int sum = getSum(arr, pivot);
			int diff = Math.abs(target - sum);

			if (diff < closest) {
				ans = pivot;
				closest = diff;
			} else if (diff == closest && pivot < ans) {
				ans = pivot;
			}

			if (sum < target) {
				lo = pivot + 1;
			} else if (sum > target) {
				hi = pivot - 1;
			} else {
				return pivot;
			}
		}
		return ans;
	}

	private int getSum(int[] arr, int num) {
		int sum = 0;

		for (int n : arr) {

			if (n >= num) {
				sum += num;
			} else {
				sum += n;
			}
		}
		return sum;
	}
}
