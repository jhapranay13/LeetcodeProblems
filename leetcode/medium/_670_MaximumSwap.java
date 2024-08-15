package leetcode.medium;

/**
 *
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 *
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 *
 * Constraints:
 *
 * 0 <= num <= 108
 *
 */

public class _670_MaximumSwap {
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] rightMax = new int[arr.length];
        rightMax[rightMax.length - 1] = rightMax.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {

            if (arr[i] > arr[rightMax[i + 1]]) {
                rightMax[i] = i;
            } else {
                rightMax[i] = rightMax[i + 1];
            }
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < arr[rightMax[i]]) {
                char temp = arr[i];
                arr[i] = arr[rightMax[i]];
                arr[rightMax[i]] = temp;
                break;
            }
        }
        StringBuilder str = new StringBuilder();

        for (char ch : arr) {
            str.append(ch);
        }
        return Integer.parseInt(str.toString());
    }
}
