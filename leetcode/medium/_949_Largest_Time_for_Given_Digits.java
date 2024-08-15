package leetcode.medium;

/**
 *
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
 *
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
 *
 * Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4]
 * Output: "23:41"
 * Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.
 * Example 2:
 *
 * Input: arr = [5,5,5,5]
 * Output: ""
 * Explanation: There are no valid 24-hour times as "55:55" is not valid.
 *
 *
 * Constraints:
 *
 * arr.length == 4
 * 0 <= arr[i] <= 9
 *
 */

public class _949_Largest_Time_for_Given_Digits {
    int ans = -1;

    public String largestTimeFromDigits(int[] arr) {
        recur(arr, 0);

        if (ans == -1) {
            return "";
        }
        return String.format("%02d:%02d", ans / 60, ans % 60);
    }

    private void recur(int[] arr, int index) {

        if (index == arr.length) {
            checkAndUpdate(arr);
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            recur(arr, index + 1);
            swap(arr, i, index);
        }
    }

    private void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void checkAndUpdate(int[] arr) {
        int index = 0;
        int total = 0;

        while (index < 2) {
            total *= 10;
            total += arr[index++];

        }

        if (total > 23) {
            return;
        }
        total *= 60;
        int min = 0;

        while (index < 4) {
            min *= 10;
            min += arr[index++];
        }

        if (min > 59) {
            return;
        }
        ans = Math.max(total + min, ans);
    }
}
