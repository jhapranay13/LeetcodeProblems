package leetcode.medium;


/**
 *
 *
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 * Example 2:
 *
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 * Example 3:
 *
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -104 <= arr[i] <= 10^4
 *
 */
public class _1186_Maximum_Subarray_Sum_with_One_Deletion {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int maxTillHereLeftToRight[] = new int[n];
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // Kadane's algorithm
        for (int i = 0; i < arr.length; i++) {
            sum = Math.max(arr[i], arr[i] + sum);
            max = Math.max(max, sum);
            maxTillHereLeftToRight[i] = sum;
        }
        int maxTillHereRightToLeft[] = new int[n];
        max = Integer.MIN_VALUE;
        sum = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            sum = Math.max(arr[i], arr[i] + sum);
            max = Math.max(max, sum);
            maxTillHereRightToLeft[i] = sum;
        }
        int ans = max;

        for (int i = 1; i < arr.length - 1; i++) {
            int leftMaxSum = i == 0 ? 0 : maxTillHereLeftToRight[i - 1];
            int rightMaxSum = i == arr.length - 1 ? 0 : maxTillHereRightToLeft[i + 1];
            ans = Math.max(ans, leftMaxSum + rightMaxSum);
        }
        return ans;
    }
}
