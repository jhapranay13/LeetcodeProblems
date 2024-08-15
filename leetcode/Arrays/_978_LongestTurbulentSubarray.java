package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array arr, return the length of a maximum size
 *         turbulent subarray of arr.
 * 
 *         A subarray is turbulent if the comparison sign flips between each
 *         adjacent pair of elements in the subarray.
 * 
 *         More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is
 *         said to be turbulent if and only if:
 * 
 *         For i <= k < j: arr[k] > arr[k + 1] when k is odd, and arr[k] < arr[k
 *         + 1] when k is even. Or, for i <= k < j: arr[k] > arr[k + 1] when k
 *         is even, and arr[k] < arr[k + 1] when k is odd.
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [9,4,2,10,7,8,8,1,9] Output: 5 Explanation: arr[1] >
 *         arr[2] < arr[3] > arr[4] < arr[5]
 * 
 *         Example 2:
 * 
 *         Input: arr = [4,8,12,16] Output: 2
 * 
 *         Example 3:
 * 
 *         Input: arr = [100] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 4 * 104 0 <= arr[i] <= 109
 *
 */

public class _978_LongestTurbulentSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxTurbulenceSize(int[] A) {

		if (A == null || A.length == 0) {
			return 0;
		}

		if (A.length == 1) {
			return 1;
		}
		int slow = 0;
		int max = 0;

		for (int fast = 1; fast < A.length; fast++) {

			if ((fast == A.length - 1 && A[fast] != A[fast - 1]) || (A[fast - 1] < A[fast] && A[fast] > A[fast + 1])
					|| (A[fast - 1] > A[fast] && A[fast] < A[fast + 1])) {
				max = Math.max(max, fast - slow + 1);
			} else {

				if (A[fast] != A[fast - 1]) {
					max = Math.max(max, fast - slow + 1);
				} else {
					max = Math.max(max, 1);
				}
				slow = fast;
			}
		}
		return max;
	}

	// ==============================================================================
	// Different version
	public int maxTurbulenceSize1(int[] arr) {

		int slow = 0;
		int fast = 1;
		int answer = 1;
		long leftDifference = 0;
		long rightDifference;

		while (fast < arr.length) {
			rightDifference = arr[fast] - arr[fast - 1];
			if (leftDifference * rightDifference > 0)
				slow = fast - 1;
			else if (rightDifference == 0)
				slow = fast;
			leftDifference = rightDifference;
			answer = Math.max(answer, fast - slow + 1);
			fast++;
		}
		return answer;
	}
}
