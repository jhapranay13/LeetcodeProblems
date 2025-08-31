package leetcode.Arrays;

/**
 *
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 9
 *
 */

public class _1089_Duplicate_Zeros {
    public void duplicateZeros(int[] arr) {
        int charCount = 0;
        int lastIndex = 0;
        int index = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {
                int count = 2;

                while (charCount < arr.length && count-- > 0) {
                    charCount++;
                }
                //edge case if the arr[i] == 0 but we don't have enough space for 2
                if (count >= 1) {
                    arr[arr.length - 1] = 0;
                    charCount--;
                    lastIndex = i - 1;
                    index--;
                    break;
                }
            } else {
                charCount++;
            }

            if (charCount == arr.length) {
                lastIndex = i;
                break;
            }
        }

        while (lastIndex >= 0) {
            int temp = arr[lastIndex--];

            if (temp == 0) {
                arr[index--] = temp;
                arr[index--] = temp;
            } else {
                arr[index--] = temp;
            }
        }
    }
    //=============================================================================================
    public void duplicateZeros1(int[] arr) {
        int n = arr.length;
        int zeros = 0;

        // Step 1: Count zeros that will be duplicated
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) zeros++;
        }

        // Step 2: Start from end and shift backwards
        for (int i = n - 1; i >= 0; i--) {
            int newPos = i + zeros;

            // If the new position is within bounds, copy the value
            if (newPos < n) {
                arr[newPos] = arr[i];
            }

            // If it's a zero, we need to duplicate it
            if (arr[i] == 0) {
                zeros--; // We've used one extra space for this zero
                if (i + zeros < n) {
                    arr[i + zeros] = 0;
                }
            }
        }
    }
}
