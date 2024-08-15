package leetcode.Arrays;

import java.util.Arrays;

/**
 *
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 *
 * As the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 *
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 * Example 3:
 *
 * Input: arr = [2,1,3], target = 6
 * Output: 1
 * Explanation: (1, 2, 3) occured one time in the array so we return 1.
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 3000
 * 0 <= arr[i] <= 100
 * 0 <= target <= 300
 *
 */

public class _923_3Sum_With_Multiplicity {
    public int threeSumMulti(int[] arr, int target) {
        int MOD = 1_000_000_007;
        int ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i <= arr.length - 3; i++) {
            int lo = i + 1;
            int hi = arr.length - 1;
            int tar = target - arr[i];

            while (lo < hi) {
                int sum = arr[lo] + arr[hi];

                if (sum < tar) {
                    lo++;
                } else if (sum > tar) {
                    hi--;
                } else {

                    if (arr[lo] == arr[hi]) {
                        // n * (n - 1) / 2 //sum of 1 + 2 + 3... n
                        int distance = hi - lo + 1;
                        ans += distance * (distance - 1) / 2;
                        ans %= MOD;
                        break;
                    }
                    int left = lo;
                    int right = hi;
                    int countLeft = 1;
                    int countRight = 1;

                    while (left + 1 < hi && arr[left] == arr[left + 1]) {
                        left++;
                        countLeft++;
                    }

                    while (right - 1 > left && arr[right] == arr[right - 1]) {
                        right--;
                        countRight++;
                    }
                    ans += countLeft * countRight;
                    ans %= MOD;
                    lo += countLeft;
                    hi -= countRight;
                }
            }
        }
        return ans;
    }
}
