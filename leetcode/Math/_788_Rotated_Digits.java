package leetcode.Math;

/**
 *
 * An integer x is a good if after rotating each digit individually by 180 degrees, we get a valid number that is different from x. Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. For example:
 *
 * 0, 1, and 8 rotate to themselves,
 * 2 and 5 rotate to each other (in this case they are rotated in a different direction, in other words, 2 or 5 gets mirrored),
 * 6 and 9 rotate to each other, and
 * the rest of the numbers do not rotate to any other number and become invalid.
 * Given an integer n, return the number of good integers in the range [1, n].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Example 2:
 *
 * Input: n = 1
 * Output: 0
 * Example 3:
 *
 * Input: n = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 *
 */

public class _788_Rotated_Digits {
    public int rotatedDigits(int n) {
        int[] arr = new int[10];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 5;
        arr[3] = -1;
        arr[4] = -1;
        arr[5] = 2;
        arr[6] = 9;
        arr[7] = -1;
        arr[8] = 8;
        arr[9] = 6;
        int count = 0;

        for(int i = 1; i <=n; i++) {
            count+=rotateNumber(i,arr);
        }
        return count;
    }

    public int rotateNumber(int n,int[] arr) {
        String s  = String.valueOf(n);
        int change = 0;

        for(int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - '0';

            if(arr[index] == -1)
                return 0;
            else if( arr[index] != index)
                change++;
        }

        if(change != 0)
            return 1;
        return 0;
    }
}
