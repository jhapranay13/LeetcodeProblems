package leetcode.easy;

/**
 *
 * Given an array of integers arr, return true if we can partition the array into three non-empty parts with equal sums.
 *
 * Formally, we can partition the array if we can find indexes i + 1 < j with (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * Example 2:
 *
 * Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * Example 3:
 *
 * Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 5 * 10^4
 * -10^4 <= arr[i] <= 10^4
 *
 */

public class _1013_Partition_Array_Into_Three_Parts_With_Equal_Sum {
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        if (sum % 3 != 0) {
            return false;
        }
        int limit = sum / 3;
        int count = 0;
        int currSum = 0;

        for (int num : arr) {
            currSum += num;

            if (currSum == limit) {
                count++;
                currSum = 0;
            }
        }
        return count >= 3;
    }
}
