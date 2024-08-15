package leetcode.TwoPointer;

/**
 *
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 21
 * Example 2:
 *
 * Input: n = 21
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 *
 *
 */

public class _556_Next_Greater_Element_III {

    public int nextGreaterElement(int n) {
        char[] num = ("" + n).toCharArray();
        int index = -1;

        for (int i = num.length - 1; i > 0; i--) {

            if (num[i] > num[i - 1]) {
                index = i - 1;
                break;
            }
        }

        if (index == -1) {
            return index;
        }
        int lo = index + 1;
        int hi = num.length - 1;
        int justGreaterIndex = lo;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (num[pivot] > num[index]) {
                lo = pivot + 1;
                justGreaterIndex = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        swap(num, index, justGreaterIndex);
        lo = index + 1;
        hi = num.length - 1;

        while (lo < hi) {
            swap(num, lo++, hi--);
        }
        long ans = 0;

        for (int i = 0; i < num.length; i++) {
            ans *= 10;
            ans += Integer.parseInt("" + num[i]);

            if (ans > Integer.MAX_VALUE) {
                return -1;
            }
        }
        return (int)ans;
    }

    private void swap (char[] num, int x, int y) {
        char temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }
}
