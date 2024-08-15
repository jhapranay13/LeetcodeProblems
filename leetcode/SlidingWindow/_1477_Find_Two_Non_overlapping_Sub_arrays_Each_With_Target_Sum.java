package leetcode.SlidingWindow;

/**
 *
 * You are given an array of integers arr and an integer target.
 *
 * You have to find two non-overlapping sub-arrays of arr each with a sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 *
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,2,2,4,3], target = 3
 * Output: 2
 * Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
 * Example 2:
 *
 * Input: arr = [7,3,4,7], target = 7
 * Output: 2
 * Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.
 * Example 3:
 *
 * Input: arr = [4,3,2,6,2,3,4], target = 6
 * Output: -1
 * Explanation: We have only one sub-array of sum = 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 *
 */

public class _1477_Find_Two_Non_overlapping_Sub_arrays_Each_With_Target_Sum {
    public int minSumOfLengths(int[] arr, int target) {
        int[] leftToRight = new int[arr.length];
        int fast = 0;
        int slow = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (fast < arr.length) {
            sum += arr[fast];

            while (sum > target) {
                sum -= arr[slow++];
            }

            if (sum == target) {
                minLength = Math.min(minLength, fast - slow + 1);
                leftToRight[fast] = minLength;
            } else {
                leftToRight[fast] = fast > 0 ? leftToRight[fast - 1] : Integer.MAX_VALUE;
            }
            fast++;
        }
        int[] rightLeft = new int[arr.length];
        fast = arr.length - 1;
        slow = fast;
        sum = 0;
        minLength = Integer.MAX_VALUE;

        while (fast >= 0) {
            sum += arr[fast];

            while (sum > target) {
                sum -= arr[slow--];
            }

            if (sum == target) {
                minLength = Math.min(minLength, slow - fast + 1);
                rightLeft[fast] = minLength;
            } else {
                rightLeft[fast] = fast < arr.length - 1 ? rightLeft[fast + 1] : Integer.MAX_VALUE;
            }
            fast--;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            // i + 1 since we don't want it to overlap'
            if (leftToRight[i] < Integer.MAX_VALUE && rightLeft[i + 1] < Integer.MAX_VALUE) {
                ans = Math.min(ans, leftToRight[i] + rightLeft[i + 1]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
